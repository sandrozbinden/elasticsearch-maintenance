package ch.zbinden.engineering.elasticsearch.monitoring.scheduler;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.*;
import ch.zbinden.engineering.elasticsearch.monitoring.mail.MailClient;
import ch.zbinden.engineering.elasticsearch.monitoring.service.ElasticSearchService;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Component
public class AlertServiceScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(LogstashEntryDeleteScheduler.class);

	@Autowired
	private MailClient mailClient;

	@Autowired
	private ElasticSearchService esService;

	@Autowired
	private AlertQueryRepository alertQueryRepository;

	@Autowired
	private AlertRepository alertRepository;

	@Scheduled(cron = "${elasticsearch.alert.cron}")
	public void sendAlert() throws MessagingException {
		LOG.info("Will check if an alerty query hits a result");
		for (String alertQuery : getAlertQueries()) {
			QueryBuilder query = createFindByAlertyQueryQuery(alertQuery);
			String[] todaysAndYesterdaysIndex = getTodayAndYesterdaysIndex();
			SearchResponse searchResponse = esService.findByQuery(query, todaysAndYesterdaysIndex);
			if (searchResponse.getHits().totalHits > 0) {
				if (!alertRepository.findByQueryAndAlertStatus(alertQuery, AlertStatus.OPEN).isPresent()) {
					Alert alert = new Alert(alertQuery);
					alertRepository.save(alert);
					mailClient.sendAlertMessageForQuery(alertQuery);
				}
			}
		}
	}

	private String[] getTodayAndYesterdaysIndex() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String logstashIndexToday = "logstash-" + LocalDate.now().format(dateTimeFormatter);
		String logstashIndexYesterday = "logstash-" + LocalDate.now().minusDays(1).format(dateTimeFormatter);
		return new String[] { logstashIndexToday, logstashIndexYesterday };
	}

	private QueryBuilder createFindByAlertyQueryQuery(String alertQuery) {
		Optional<Alert> completedQuery = alertRepository
				.findFirstByQueryAndAlertStatusOrderByCompletedDateDesc(alertQuery, AlertStatus.CLOSED);
		QueryStringQueryBuilder queryStringQuery = QueryBuilders.queryStringQuery(alertQuery);
		if (completedQuery.isPresent()) {
			String completeDate = completedQuery.get().getCompletedDate().toLocalDateTime()
					.format(DateTimeFormatter.ISO_DATE_TIME);
			RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("@timestamp").gt(completeDate);
			return boolQuery().must(queryStringQuery).must(rangeQuery);
		} else {
			return queryStringQuery;
		}
	}

	private List<String> getAlertQueries() {
		List<AlertQuery> alertQuerties = Lists.newArrayList(alertQueryRepository.findAll());
		return alertQuerties.stream().map(alertQuery -> alertQuery.getQuery()).collect(Collectors.toList());
	}

}
