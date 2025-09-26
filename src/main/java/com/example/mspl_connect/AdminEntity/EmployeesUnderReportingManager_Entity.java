package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeesUnderReportingManager_Entity {

	@Id
	private long id;
	private String employees_under_reporting_manager;
	private String dept_name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployees_under_reporting_manager() {
		return employees_under_reporting_manager;
	}
	public void setEmployees_under_reporting_manager(String employees_under_reporting_manager) {
		this.employees_under_reporting_manager = employees_under_reporting_manager;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "EmployeesUnderReportingManager_Entity [id=" + id + ", employees_under_reporting_manager="
				+ employees_under_reporting_manager + ", dept_name=" + dept_name + "]";
	}
}
