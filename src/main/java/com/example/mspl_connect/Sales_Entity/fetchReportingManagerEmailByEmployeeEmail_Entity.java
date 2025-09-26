package com.example.mspl_connect.Sales_Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class fetchReportingManagerEmailByEmployeeEmail_Entity {

	@Id
	private String first_reporting_manager_email;
	private String second_reporting_manager_email;
	
	public String getFirst_reporting_manager_email() {
		return first_reporting_manager_email;
	}
	public void setFirst_reporting_manager_email(String first_reporting_manager_email) {
		this.first_reporting_manager_email = first_reporting_manager_email;
	}
	public String getSecond_reporting_manager_email() {
		return second_reporting_manager_email;
	}
	public void setSecond_reporting_manager_email(String second_reporting_manager_email) {
		this.second_reporting_manager_email = second_reporting_manager_email;
	}
	@Override
	public String toString() {
		return "fetchReportingManagerEmailByEmployeeEmail_Entity [first_reporting_manager_email="
				+ first_reporting_manager_email + ", second_reporting_manager_email=" + second_reporting_manager_email
				+ "]";
	}
	
}
