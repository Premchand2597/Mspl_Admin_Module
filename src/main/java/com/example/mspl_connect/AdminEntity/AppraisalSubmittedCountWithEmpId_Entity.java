package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppraisalSubmittedCountWithEmpId_Entity {

	private String apprisal_submitted_count;
	@Id
	private String emp_id;
	
	public String getApprisal_submitted_count() {
		return apprisal_submitted_count;
	}
	public void setApprisal_submitted_count(String apprisal_submitted_count) {
		this.apprisal_submitted_count = apprisal_submitted_count;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	@Override
	public String toString() {
		return "AppraisalSubmittedCountWithEmpId_Entity [apprisal_submitted_count=" + apprisal_submitted_count
				+ ", emp_id=" + emp_id + "]";
	}
	
}
