package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CasualAndSickLeaveSummaryInsertManually_Entity {

	@Id
	private long id;
	private double added;
	private double remaining;
	private double total_added;
	private double total_used;
	private String user_email;
	private String empid;
	private String month;
	private String last_updated;
	private String remarks;
	private String financial_year;
	private String leave_type;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAdded() {
		return added;
	}
	public void setAdded(double added) {
		this.added = added;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	public double getTotal_added() {
		return total_added;
	}
	public void setTotal_added(double total_added) {
		this.total_added = total_added;
	}
	public double getTotal_used() {
		return total_used;
	}
	public void setTotal_used(double total_used) {
		this.total_used = total_used;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	@Override
	public String toString() {
		return "CasualAndSickLeaveSummaryInsertManually_Entity [id=" + id + ", added=" + added + ", remaining="
				+ remaining + ", total_added=" + total_added + ", total_used=" + total_used + ", user_email="
				+ user_email + ", empid=" + empid + ", month=" + month + ", last_updated=" + last_updated + ", remarks="
				+ remarks + ", financial_year=" + financial_year + ", leave_type=" + leave_type + "]";
	}
	
}
