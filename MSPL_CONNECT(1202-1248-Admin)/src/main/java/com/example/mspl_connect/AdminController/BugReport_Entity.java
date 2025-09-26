package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bug_report")
public class BugReport_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long auto_id;
	private String id;
	private String name;
	private String email;
	private String bug_type;
	private String message;
	private String attachment;
	private LocalDateTime bug_reported_on;
	private String bug_status;
	private LocalDateTime bug_replied_on;
	private String emp_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBug_type() {
		return bug_type;
	}
	public void setBug_type(String bug_type) {
		this.bug_type = bug_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public LocalDateTime getBug_reported_on() {
		return bug_reported_on;
	}
	public void setBug_reported_on(LocalDateTime bug_reported_on) {
		this.bug_reported_on = bug_reported_on;
	}
	public long getAuto_id() {
		return auto_id;
	}
	public void setAuto_id(long auto_id) {
		this.auto_id = auto_id;
	}
	public String getBug_status() {
		return bug_status;
	}
	public void setBug_status(String bug_status) {
		this.bug_status = bug_status;
	}
	public LocalDateTime getBug_replied_on() {
		return bug_replied_on;
	}
	public void setBug_replied_on(LocalDateTime bug_replied_on) {
		this.bug_replied_on = bug_replied_on;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	@Override
	public String toString() {
		return "BugReport_Entity [auto_id=" + auto_id + ", id=" + id + ", name=" + name + ", email=" + email
				+ ", bug_type=" + bug_type + ", message=" + message + ", attachment=" + attachment
				+ ", bug_reported_on=" + bug_reported_on + ", bug_status=" + bug_status + ", bug_replied_on="
				+ bug_replied_on + ", emp_id=" + emp_id + "]";
	}
	
}
