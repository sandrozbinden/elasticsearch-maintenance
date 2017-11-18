package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlertQueryRepository extends CrudRepository<AlertQuery, Long>, PagingAndSortingRepository<AlertQuery, Long> {

}
