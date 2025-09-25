package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectBarChart_Entity {
 
	private String empname;
	@Id
	private String email;
	private int total_task;
	private int completed_task;
	private int pending_task;
	private int total_overdue_date;
	
	public String getEmpname() {
		return empname;
	}
	public int getTotal_overdue_date() {
		return total_overdue_date;
	}
	public void setTotal_overdue_date(int total_overdue_date) {
		this.total_overdue_date = total_overdue_date;
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
	public int getPending_task() {
		return pending_task;
	}
	public void setPending_task(int pending_task) {
		this.pending_task = pending_task;
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
		return "ProjectBarChart_Entity [empname=" + empname + ", email=" + email + ", total_task=" + total_task
				+ ", completed_task=" + completed_task + ", pending_task=" + pending_task + ", total_overdue_date="
				+ total_overdue_date + "]";
	}
	public ProjectBarChart_Entity(String empname, String email, int total_task, int completed_task, int pending_task,
			int total_overdue_date) {
		super();
		this.empname = empname;
		this.email = email;
		this.total_task = total_task;
		this.completed_task = completed_task;
		this.pending_task = pending_task;
		this.total_overdue_date = total_overdue_date;
	}
	public ProjectBarChart_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
