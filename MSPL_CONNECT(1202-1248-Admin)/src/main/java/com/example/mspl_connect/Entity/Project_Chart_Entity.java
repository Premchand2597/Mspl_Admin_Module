package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Project_Chart_Entity {

	private String emp_name;
	@Id
	private String email;
	private int overall_assigned_task;
	private int total_completed_work;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTotal_completed_work() {
		return total_completed_work;
	}
	public void setTotal_completed_work(int total_completed_work) {
		this.total_completed_work = total_completed_work;
	}
	public int getOverall_assigned_task() {
		return overall_assigned_task;
	}
	public void setOverall_assigned_task(int overall_assigned_task) {
		this.overall_assigned_task = overall_assigned_task;
	}
	@Override
	public String toString() {
		return "Project_Chart_Entity [emp_name=" + emp_name + ", email=" + email + ", overall_assigned_task="
				+ overall_assigned_task + ", total_completed_work=" + total_completed_work + "]";
	}
	public Project_Chart_Entity(String emp_name, String email, int overall_assigned_task, int total_completed_work) {
		super();
		this.emp_name = emp_name;
		this.email = email;
		this.overall_assigned_task = overall_assigned_task;
		this.total_completed_work = total_completed_work;
	}
	public Project_Chart_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
