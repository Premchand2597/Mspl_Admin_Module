package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcement_notifications")
public class AnnouncementNotificationDetailsInsert_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String event_id;
	private String emp_id;
	private String flag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "AnnouncementNotificationDetailsInsert_Entity [id=" + id + ", event_id=" + event_id + ", emp_id="
				+ emp_id + ", flag=" + flag + "]";
	}
	
}
