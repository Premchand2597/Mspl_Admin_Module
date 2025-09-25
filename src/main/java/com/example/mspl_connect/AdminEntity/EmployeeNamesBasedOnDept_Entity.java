package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeNamesBasedOnDept_Entity {

	@Id
	private String empid;
	private String emp_name;
	
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	@Override
	public String toString() {
		return "EmployeeNamesBasedOnDept_Entity [empid=" + empid + ", emp_name=" + emp_name + "]";
	}
	
}
