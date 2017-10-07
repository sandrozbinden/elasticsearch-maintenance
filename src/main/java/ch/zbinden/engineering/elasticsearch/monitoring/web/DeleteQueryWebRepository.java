package ch.zbinden.engineering.elasticsearch.monitoring.web;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.DeleteQuery;

@RepositoryRestResource(collectionResourceRel = "deletequery", path = "deletequery")
public interface DeleteQueryWebRepository extends PagingAndSortingRepository<DeleteQuery, Long> {

}