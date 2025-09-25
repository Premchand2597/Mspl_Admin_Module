package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppraisalTracker_Entity {

	@Id
	private long id;
	private String empid;
	private String email;
	private String dept_name;
	private String full_name;
	private String apprisal_send_date;
	private String due_date;
	private String appraisal_status;
	private String appraisal_submitted_date;
	private String appraisal_validated_date;
	private String validated_emp_name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getApprisal_send_date() {
		return apprisal_send_date;
	}
	public void setApprisal_send_date(String apprisal_send_date) {
		this.apprisal_send_date = apprisal_send_date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getAppraisal_status() {
		return appraisal_status;
	}
	public void setAppraisal_status(String appraisal_status) {
		this.appraisal_status = appraisal_status;
	}
	public String getAppraisal_submitted_date() {
		return appraisal_submitted_date;
	}
	public void setAppraisal_submitted_date(String appraisal_submitted_date) {
		this.appraisal_submitted_date = appraisal_submitted_date;
	}
	public String getAppraisal_validated_date() {
		return appraisal_validated_date;
	}
	public void setAppraisal_validated_date(String appraisal_validated_date) {
		this.appraisal_validated_date = appraisal_validated_date;
	}
	public String getValidated_emp_name() {
		return validated_emp_name;
	}
	public void setValidated_emp_name(String validated_emp_name) {
		this.validated_emp_name = validated_emp_name;
	}
	@Override
	public String toString() {
		return "AppraisalTracker_Entity [id=" + id + ", empid=" + empid + ", email=" + email + ", dept_name="
				+ dept_name + ", full_name=" + full_name + ", apprisal_send_date=" + apprisal_send_date + ", due_date="
				+ due_date + ", appraisal_status=" + appraisal_status + ", appraisal_submitted_date="
				+ appraisal_submitted_date + ", appraisal_validated_date=" + appraisal_validated_date
				+ ", validated_emp_name=" + validated_emp_name + "]";
	}
	
}
