package ch.zbinden.engineering.elasticsearch.monitoring.web;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.AlertQuery;

@RepositoryRestResource
public interface AlertQueryWebRepository extends PagingAndSortingRepository<AlertQuery, Long> {

}