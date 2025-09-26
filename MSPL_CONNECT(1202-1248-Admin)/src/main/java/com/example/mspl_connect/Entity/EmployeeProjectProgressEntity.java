package com.example.mspl_connect.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeProjectProgressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String empname;
	private String email;
	private String task_name;
	private int total_subtasks;
	private int total_completed_subtasks;
	
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public int getTotal_subtasks() {
		return total_subtasks;
	}
	public void setTotal_subtasks(int total_subtasks) {
		this.total_subtasks = total_subtasks;
	}
	public int getTotal_completed_subtasks() {
		return total_completed_subtasks;
	}
	public void setTotal_completed_subtasks(int total_completed_subtasks) {
		this.total_completed_subtasks = total_completed_subtasks;
	}
	
	@Override
	public String toString() {
		return "EmployeeProjectProgressEntity [empname=" + empname + ", email=" + email + ", task_name=" + task_name
				+ ", total_subtasks=" + total_subtasks + ", total_completed_subtasks=" + total_completed_subtasks + "]";
	}
	
}
