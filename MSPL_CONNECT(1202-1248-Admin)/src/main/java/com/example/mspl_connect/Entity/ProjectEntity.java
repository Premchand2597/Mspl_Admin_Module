package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="team_project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String project_id;
	private String project_name;
	private String department;
	private String completed_date;
	private String project_manager;	
	private String description;
	private String document;
	private String status;	
	private String team_members;
	private String senior_name;
	
	private String completion_date;
	private Integer flag;
	private String assign_flag;
	
	
	
	public String getAssign_flag() {
		return assign_flag;
	}
	public void setAssign_flag(String assign_flag) {
		this.assign_flag = assign_flag;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getCompletion_date() {
		return completion_date;
	}
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}
	public String getSenior_name() {
		return senior_name;
	}
	public void setSenior_name(String senior_name) {
		this.senior_name = senior_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_id() {
		return project_id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
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
	public ProjectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProjectEntity [id=" + id + ", project_id=" + project_id + ", project_name=" + project_name
				+ ", department=" + department + ", completed_date=" + completed_date + ", project_manager="
				+ project_manager + ", description=" + description + ", document=" + document + ", status=" + status
				+ ", team_members=" + team_members + ", senior_name=" + senior_name + ", completion_date="
				+ completion_date + ", flag=" + flag + ", assign_flag=" + assign_flag + "]";
	}	
}
