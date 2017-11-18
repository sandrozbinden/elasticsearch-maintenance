package ch.zbinden.engineering.elasticsearch.monitoring.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeAction;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

	@Autowired
	private Client client;

	@Value("${logstash.template.name}")
	private String logstashTemplateName;

	@Override
	public Set<IndexMetaData> getLogstashIndexMetaDatas() {
		return getIndexMetaDatas().stream()
				.filter(indexName -> indexName.getIndex().getName().startsWith(logstashTemplateName))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<IndexMetaData> getIndexMetaDatas() {
		return Sets.newHashSet(
				client.admin().cluster().prepareState().get().getState().getMetaData().getIndices().valuesIt());
	}

	@Override
	public Set<String> getIndexNames() {
		return getIndexMetaDatas().stream().map(index -> index.getIndex().getName()).collect(Collectors.toSet());
	}

	@Override
	public void deleteIndicies(Set<IndexMetaData> indiciesToDelete) {
		indiciesToDelete.forEach(index -> deleteIndex(index));

	}

	@Override
	public void deleteIndex(IndexMetaData index) {
		String name = index.getIndex().getName();
		DeleteIndexResponse deleteResponse = client.admin().indices().delete(new DeleteIndexRequest(name)).actionGet();
		if (deleteResponse.isAcknowledged()) {
			LOG.info("Deleted Index {}", name);
		} else {
			LOG.error("Error deleting Index {} ", name);
		}
	}

	@Override
	public void deleteByQuery(QueryBuilder query) {
		String[] indexNames = Iterables.toArray(getIndexNames(), String.class);
		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).filter(query)
				.source(indexNames).get();
		long deleted = response.getDeleted();
		LOG.info("Deleted {} entries on {} indicies", deleted, indexNames.length);
	}
	
	@Override
	public void expungeDeletes() {
		String[] indexNames = Iterables.toArray(getIndexNames(), String.class);
		ForceMergeResponse forceMergeResponse = ForceMergeAction.INSTANCE.newRequestBuilder(client)
				.setIndices(indexNames).setOnlyExpungeDeletes(true).get();
		LOG.info("Force Merge Response succeed shards: {} failed  shards: {} ",
				forceMergeResponse.getSuccessfulShards(), forceMergeResponse.getFailedShards());
	}
	
	@Override
	public SearchResponse findByQuery(QueryBuilder query) {
		return SearchAction.INSTANCE.newRequestBuilder(client).setQuery(query).get();
	}
}
