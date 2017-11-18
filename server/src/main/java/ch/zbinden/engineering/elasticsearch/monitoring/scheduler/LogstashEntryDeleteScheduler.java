package ch.zbinden.engineering.elasticsearch.monitoring.scheduler;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.DeleteQuery;
import ch.zbinden.engineering.elasticsearch.monitoring.domain.DeleteQueryRepository;
import ch.zbinden.engineering.elasticsearch.monitoring.service.ElasticSearchService;

@Component
public class LogstashEntryDeleteScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(LogstashEntryDeleteScheduler.class);

	@Autowired
	private ElasticSearchService esService;

	@Autowired
	private DeleteQueryRepository deleteQueryRepository;

	@Scheduled(cron = "${elasticsearch.entry.delete.cron}")
	public void deleteIndicies() {
		LOG.info("Will delete unimported elasticsearch entries to free disk space");
		for (DeleteQuery deleteQuery : deleteQueryRepository.findAll()) {
			QueryStringQueryBuilder queryStringQuery = QueryBuilders.queryStringQuery(deleteQuery.getQuery());
			esService.deleteByQuery(queryStringQuery);
			esService.expungeDeletes();
		}
	}

}