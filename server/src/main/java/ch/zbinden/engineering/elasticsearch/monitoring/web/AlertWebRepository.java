package ch.zbinden.engineering.elasticsearch.monitoring.web;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.Alert;

@RepositoryRestResource
public interface AlertWebRepository extends PagingAndSortingRepository<Alert, Long> {

}