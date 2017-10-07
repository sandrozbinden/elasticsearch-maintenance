package ch.zbinden.engineering.elasticsearch.monitoring.service;

import java.util.Set;

import org.elasticsearch.cluster.metadata.IndexMetaData;

public interface ElasticSearchService {

	Set<IndexMetaData> getLogstashIndicies();

	Set<IndexMetaData> getIndicies();

	void deleteIndicies(Set<IndexMetaData> indiciesToDelete);

	void deleteIndex(IndexMetaData index);

}
