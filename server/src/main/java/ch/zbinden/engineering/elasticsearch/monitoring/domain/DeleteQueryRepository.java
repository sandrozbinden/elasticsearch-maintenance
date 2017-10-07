package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeleteQueryRepository extends CrudRepository<DeleteQuery, Long>, PagingAndSortingRepository<DeleteQuery, Long> {

}
