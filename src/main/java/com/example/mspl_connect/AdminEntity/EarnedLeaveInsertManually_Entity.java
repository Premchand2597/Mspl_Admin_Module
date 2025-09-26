package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EarnedLeaveInsertManually_Entity {

	@Id
	private long id;
	private double earned_leave;
	private double el_used;
	private double elremaining;
	private String email;
	private String emp_id;
	private String last_increment_date;
	private String month_year;
	private String remarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getEarned_leave() {
		return earned_leave;
	}
	public void setEarned_leave(double earned_leave) {
		this.earned_leave = earned_leave;
	}
	public double getEl_used() {
		return el_used;
	}
	public void setEl_used(double el_used) {
		this.el_used = el_used;
	}
	public double getElremaining() {
		return elremaining;
	}
	public void setElremaining(double elremaining) {
		this.elremaining = elremaining;
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
	public String getLast_increment_date() {
		return last_increment_date;
	}
	public void setLast_increment_date(String last_increment_date) {
		this.last_increment_date = last_increment_date;
	}
	public String getMonth_year() {
		return month_year;
	}
	public void setMonth_year(String month_year) {
		this.month_year = month_year;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "EarnedLeaveInsertManually_Entity [id=" + id + ", earned_leave=" + earned_leave + ", el_used=" + el_used
				+ ", elremaining=" + elremaining + ", email=" + email + ", emp_id=" + emp_id + ", last_increment_date="
				+ last_increment_date + ", month_year=" + month_year + ", remarks=" + remarks + "]";
	}
	
}
