package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class ProjectProgress {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String project_id;
	private String email;
	private int percent;
	
	
	public String getProject_id() {
		return project_id;
	}
	
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPercent() {
		return percent;
	}
	
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	@Override
	public String toString() {
		return "ProjectProgress [ project_id=" + project_id + ", email=" + email
				+ ", percent=" + percent  + "]";
	}
	
	
	
	
}
