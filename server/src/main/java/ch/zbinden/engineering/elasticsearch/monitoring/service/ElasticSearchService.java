package ch.zbinden.engineering.elasticsearch.monitoring.service;

import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.index.query.QueryBuilder;

public interface ElasticSearchService {

	Set<IndexMetaData> getLogstashIndexMetaDatas();

	Set<IndexMetaData> getIndexMetaDatas();

	Set<String> getIndexNames();

	void deleteIndicies(Set<IndexMetaData> indiciesToDelete);

	void deleteIndex(IndexMetaData index);

	void expungeDeletes();

	void deleteByQuery(QueryBuilder matchQuery);

	SearchResponse findByQuery(QueryBuilder query);

}
