package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlertQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String query;


	protected AlertQuery() {
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}