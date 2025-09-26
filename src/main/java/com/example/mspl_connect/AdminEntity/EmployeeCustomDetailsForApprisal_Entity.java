package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeCustomDetailsForApprisal_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_full_name;
	private String emp_id;
	private String dept;
	private String designation;
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_full_name() {
		return emp_full_name;
	}
	public void setEmp_full_name(String emp_full_name) {
		this.emp_full_name = emp_full_name;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "EmployeeCustomDetailsForApprisal_Entity [id=" + id + ", emp_full_name=" + emp_full_name + ", emp_id="
				+ emp_id + ", dept=" + dept + ", designation=" + designation + ", email=" + email + "]";
	}
	
}
