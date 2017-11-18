package ch.zbinden.engineering.elasticsearch.monitoring.scheduler;

import javax.mail.MessagingException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.AlertQuery;
import ch.zbinden.engineering.elasticsearch.monitoring.domain.AlertQueryRepository;
import ch.zbinden.engineering.elasticsearch.monitoring.mail.MailClient;
import ch.zbinden.engineering.elasticsearch.monitoring.service.ElasticSearchService;

@Component
public class AlertServiceScheduler {
	
	private static final Logger LOG = LoggerFactory.getLogger(LogstashEntryDeleteScheduler.class);

	@Autowired
	private MailClient mailClient;
	
	@Autowired
	private ElasticSearchService esService;

	@Autowired
	private AlertQueryRepository alertQueryRepository;
	

	@Scheduled(cron = "${elasticsearch.alert.cron}")
	public void sendAlert() throws MessagingException {
		LOG.info("Will check if an alerty query hits a result");
		for (AlertQuery alertQuery : alertQueryRepository.findAll()) {
			QueryStringQueryBuilder queryStringQuery = QueryBuilders.queryStringQuery(alertQuery.getQuery());
			SearchResponse searchResponse = esService.findByQuery(queryStringQuery);
			if (searchResponse.getHits().totalHits > 0) {
				mailClient.sendAlertMessageForQuery(alertQuery.getQuery());
			}
		}
	}

}
