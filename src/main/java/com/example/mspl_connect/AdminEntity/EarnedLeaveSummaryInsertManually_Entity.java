package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EarnedLeaveSummaryInsertManually_Entity {

	@Id
	private long id;
	private double added_as_earned_leave;
	private double remaining;
	private String email;
	private String emp_id;
	private String incremented_date;
	private String last_updated;
	private String remarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAdded_as_earned_leave() {
		return added_as_earned_leave;
	}
	public void setAdded_as_earned_leave(double added_as_earned_leave) {
		this.added_as_earned_leave = added_as_earned_leave;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getIncremented_date() {
		return incremented_date;
	}
	public void setIncremented_date(String incremented_date) {
		this.incremented_date = incremented_date;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "EarnedLeaveSummaryInsertManually_Entity [id=" + id + ", added_as_earned_leave=" + added_as_earned_leave
				+ ", remaining=" + remaining + ", email=" + email + ", emp_id=" + emp_id + ", incremented_date="
				+ incremented_date + ", last_updated=" + last_updated + ", remarks=" + remarks + "]";
	}
	
}
