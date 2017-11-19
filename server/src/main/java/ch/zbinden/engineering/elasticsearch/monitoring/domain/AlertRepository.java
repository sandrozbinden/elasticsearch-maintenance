package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlertRepository extends CrudRepository<Alert, Long>, PagingAndSortingRepository<Alert, Long> {

	List<Alert> findByQuery(String query);
	
	Optional<Alert> findByQueryAndAlertStatus(String query, AlertStatus alertStatus);

	Optional<Alert> findFirstByQueryAndAlertStatusOrderByCompletedDateDesc(String query, AlertStatus alertStatus);
}
