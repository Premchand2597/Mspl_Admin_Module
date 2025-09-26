package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TeamProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String projectId;    
    private String date;
    private String working_progress;
    private String flag;
    private String task_status;
    private String task_name;
    private String email;
    private Double percent;
  
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWorking_progress() {
		return working_progress;
	}
	public void setWorking_progress(String working_progress) {
		this.working_progress = working_progress;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}	
    
	public Long getId() {
		return id;
	}
	public String getProjectId() {
		return projectId;
	}
	public String getEmail() {
		return email;
	}
	public Double getPercent() {
		return percent;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
}
