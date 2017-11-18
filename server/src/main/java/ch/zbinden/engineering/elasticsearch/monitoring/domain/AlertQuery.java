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
	private String field;
	private String value;

	protected AlertQuery() {
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}