package ch.zbinden.engineering.elasticsearch.monitoring.scheduler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.zbinden.engineering.elasticsearch.monitoring.service.ElasticSearchService;

@Component
public class LogstashIndexDeleteScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(LogstashIndexDeleteScheduler.class);
	
	@Autowired
	private ElasticSearchService esService;

	@Value("${logstash.index.keep.max.days}")
	private int maxDaysToKeep;

	@Scheduled(cron = "${elasticsearch.index.delete.cron}")
	public void deleteIndicies() {
		LocalDate dateToKeepIndex = LocalDate.now().minus(maxDaysToKeep, ChronoUnit.DAYS);
		LOG.info("Will delete indicies older than {}", dateToKeepIndex);
		Set<IndexMetaData> indicies = esService.getLogstashIndicies();
		Set<IndexMetaData> indiciesToDelete = indicies.stream()
				.filter(index -> dateToKeepIndex.isAfter(
						Instant.ofEpochMilli(index.getCreationDate()).atZone(ZoneId.systemDefault()).toLocalDate()))
				.collect(Collectors.toSet());
		esService.deleteIndicies(indiciesToDelete);
	}

}