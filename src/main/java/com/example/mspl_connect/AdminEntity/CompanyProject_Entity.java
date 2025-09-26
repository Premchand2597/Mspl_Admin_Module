package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class CompanyProject_Entity {

	@Id
	private int id;
	private String project_name;
	private String description;
	private String completed_date;
	private String project_manager;
	private String team_members;
	private String document;
	private String department;
	private String status;
	private String client;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompleted_date() {
		return completed_date;
	}
	public void setCompleted_date(String completed_date) {
		this.completed_date = completed_date;
	}
	public String getProject_manager() {
		return project_manager;
	}
	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}
	public String getTeam_members() {
		return team_members;
	}
	public void setTeam_members(String team_members) {
		this.team_members = team_members;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "CompanyProject_Entity [id=" + id + ", project_name=" + project_name + ", description=" + description
				+ ", completed_date=" + completed_date + ", project_manager=" + project_manager + ", team_members="
				+ team_members + ", document=" + document + ", department=" + department + ", status=" + status
				+ ", client=" + client + "]";
	}
}
