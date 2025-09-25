package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CompProjectCardDetails_Entity {

	@Id
	private int id;
	private String project_name;
	private String project_status;
	private String project_id;
	
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_status() {
		return project_status;
	}
	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}
	@Override
	public String toString() {
		return "CompProjectCardDetails_Entity [id=" + id + ", project_name=" + project_name + ", project_status="
				+ project_status + ", project_id=" + project_id + "]";
	}
}
