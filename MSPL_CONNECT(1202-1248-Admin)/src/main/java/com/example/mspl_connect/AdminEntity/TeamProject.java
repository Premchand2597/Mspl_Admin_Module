package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TeamProject {
     

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_id")
	private String project_id;
	
	/*@Column(name = "project_name")
    private String projectName;*/
	private String project_name;
	private String description;
	
	@Column(name="completion_date")
	private String completed_date;
	private String project_manager;
	private String team_members;
	private String document;
	private String status;
	private String department;
	private String senior_name;
	private String Assign_flag;
	@Column(name="team_members_email")
	private String team_members_email;

	
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public TeamProject() {
		super();
	}
	
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "TeamProject [id=" + id + ", project_id=" + project_id + ", project_name=" + project_name
				+ ", description=" + description + ", completed_date=" + completed_date + ", project_manager="
				+ project_manager + ", team_members=" + team_members + ", document=" + document + ", status=" + status
				+ ", department=" + department + ", senior_name=" + senior_name + ", Assign_flag=" + Assign_flag
				+ ", team_members_email=" + team_members_email + "]";
	}
	public String getSenior_name() {
		return senior_name;
	}
	public void setSenior_name(String senior_name) {
		this.senior_name = senior_name;
	}
	public String getAssign_flag() {
		return Assign_flag;
	}
	public void setAssign_flag(String assign_flag) {
		Assign_flag = assign_flag;
	}
	public String getTeam_members_email() {
		return team_members_email;
	}
	public void setTeam_members_email(String team_members_email) {
		this.team_members_email = team_members_email;
	}

	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_id() {
		return project_id;
	}
    


	public TeamProject(Long id, String project_id, String project_name, String description, String completed_date,
			String project_manager, String team_members, String document, String status, String department,
			String senior_name, String assign_flag, String team_members_email) {
		super();
		this.id = id;
		this.project_id = project_id;
		this.project_name = project_name;
		this.description = description;
		this.completed_date = completed_date;
		this.project_manager = project_manager;
		this.team_members = team_members;
		this.document = document;
		this.status = status;
		this.department = department;
		this.senior_name = senior_name;
		this.Assign_flag = assign_flag;
		this.team_members_email = team_members_email;
	}
}
