package com.example.mspl_connect.AdminEntity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "apprisal_hr")
public class ApprisalHR_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_id; 
	private String email;
	private String department;
	private String quarter;
	private String quarter_month_year;
	private Date apprisal_send_date;
	private String apprisal_submit_date;
	private String apprisal_submit_date_admin;
	private String apprisal_link_flag;
	private String apprisal_email_send_flag;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarter_month_year() {
		return quarter_month_year;
	}
	public void setQuarter_month_year(String quarter_month_year) {
		this.quarter_month_year = quarter_month_year;
	}
	public Date getApprisal_send_date() {
		return apprisal_send_date;
	}
	public void setApprisal_send_date(Date apprisal_send_date) {
		this.apprisal_send_date = apprisal_send_date;
	}
	public String getApprisal_submit_date() {
		return apprisal_submit_date;
	}
	public void setApprisal_submit_date(String apprisal_submit_date) {
		this.apprisal_submit_date = apprisal_submit_date;
	}
	public String getApprisal_link_flag() {
		return apprisal_link_flag;
	}
	public void setApprisal_link_flag(String apprisal_link_flag) {
		this.apprisal_link_flag = apprisal_link_flag;
	}
	public String getApprisal_submit_date_admin() {
		return apprisal_submit_date_admin;
	}
	public void setApprisal_submit_date_admin(String apprisal_submit_date_admin) {
		this.apprisal_submit_date_admin = apprisal_submit_date_admin;
	}
	public String getApprisal_email_send_flag() {
		return apprisal_email_send_flag;
	}
	public void setApprisal_email_send_flag(String apprisal_email_send_flag) {
		this.apprisal_email_send_flag = apprisal_email_send_flag;
	}
	@Override
	public String toString() {
		return "ApprisalHR_Entity [id=" + id + ", emp_id=" + emp_id + ", email=" + email + ", department=" + department
				+ ", quarter=" + quarter + ", quarter_month_year=" + quarter_month_year + ", apprisal_send_date="
				+ apprisal_send_date + ", apprisal_submit_date=" + apprisal_submit_date
				+ ", apprisal_submit_date_admin=" + apprisal_submit_date_admin + ", apprisal_link_flag="
				+ apprisal_link_flag + ", apprisal_email_send_flag=" + apprisal_email_send_flag + "]";
	}
	
}
