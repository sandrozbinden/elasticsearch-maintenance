package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeleteQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String query;

	protected DeleteQuery() {
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}