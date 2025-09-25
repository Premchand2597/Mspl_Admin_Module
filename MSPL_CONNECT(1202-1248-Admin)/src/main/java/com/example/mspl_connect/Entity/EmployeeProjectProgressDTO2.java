package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeProjectProgressDTO2 {
	
	@Id
	private String empname;
	private String email;
	private int total_task;
	private int  completed_task;
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
	public int getTotal_task() {
		return total_task;
	}
	public void setTotal_task(int total_task) {
		this.total_task = total_task;
	}
	public int getCompleted_task() {
		return completed_task;
	}
	public void setCompleted_task(int completed_task) {
		this.completed_task = completed_task;
	}
	@Override
	public String toString() {
		return "EmployeeProjectProgressDTO2 [empname=" + empname + ", email=" + email + ", total_task=" + total_task
				+ ", completed_task=" + completed_task + "]";
	}
	
	
}
