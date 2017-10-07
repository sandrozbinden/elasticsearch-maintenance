package ch.zbinden.engineering.elasticsearch.monitoring.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
	
	@Autowired
	private Client client;
	
	
	@Value("${logstash.template.name}")
	private String logstashTemplateName;
	
	@Override
	public Set<IndexMetaData> getLogstashIndicies() {
		return getIndicies().stream().filter(indexName -> indexName.getIndex().getName().startsWith(logstashTemplateName)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<IndexMetaData> getIndicies() {
		return Sets.newHashSet(client.admin().cluster().prepareState().get().getState()
				.getMetaData().getIndices().valuesIt());		
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
}
