package ch.zbinden.engineering.elasticsearch.monitoring.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String query;
	
	@CreationTimestamp
	private Timestamp createdDate;

	@Enumerated(EnumType.STRING)
	private AlertStatus alertStatus = AlertStatus.OPEN;
	
	private Timestamp completedDate;

	protected Alert() {
	}
	
	public Alert(String query) {
		this.query = query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	
	public Timestamp getCompletedDate() {
		return completedDate;
	}
	
	public void setCompletedDate(Timestamp completedDate) {
		this.completedDate = completedDate;
	}
}