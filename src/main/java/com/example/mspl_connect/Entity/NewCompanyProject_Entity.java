package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_project")
public class NewCompanyProject_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "project_id")
	private String projectId;
	private String project_name;
	
	@Column(name = "department")
	private String department;
	
	private String assigned_date;
	private String completed_date;
	private String project_manager;
	private String description;
	private String document;
	private String status;
	private String team_members;
	private String senior_name;
	private String completion_date;
	private String client_name;
	private String flag;
	private String platform_to_work;
	private String delay_reason;
	private String start_date;
	private String admin_completion_date;
	private String prj_type;
	private String ref_prj;
	
	public String getRef_prj() {
		return ref_prj;
	}
	public void setRef_prj(String ref_prj) {
		this.ref_prj = ref_prj;
	}
	public String getPrj_type() {
		return prj_type;
	}
	public void setPrj_type(String prj_type) {
		this.prj_type = prj_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getTeam_members() {
		return team_members;
	}
	public void setTeam_members(String team_members) {
		this.team_members = team_members;
	}
	public String getSenior_name() {
		return senior_name;
	}
	public void setSenior_name(String senior_name) {
		this.senior_name = senior_name;
	}
	public String getCompletion_date() {
		return completion_date;
	}
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getAssigned_date() {
		return assigned_date;
	}
	public void setAssigned_date(String assigned_date) {
		this.assigned_date = assigned_date;
	}
	public String getPlatform_to_work() {
		return platform_to_work;
	}
	public void setPlatform_to_work(String platform_to_work) {
		this.platform_to_work = platform_to_work;
	}
	public String getDelay_reason() {
		return delay_reason;
	}
	public void setDelay_reason(String delay_reason) {
		this.delay_reason = delay_reason;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getAdmin_completion_date() {
		return admin_completion_date;
	}
	public void setAdmin_completion_date(String admin_completion_date) {
		this.admin_completion_date = admin_completion_date;
	}
	@Override
	public String toString() {
		return "NewCompanyProject_Entity [id=" + id + ", projectId=" + projectId + ", project_name=" + project_name
				+ ", department=" + department + ", assigned_date=" + assigned_date + ", completed_date="
				+ completed_date + ", project_manager=" + project_manager + ", description=" + description
				+ ", document=" + document + ", status=" + status + ", team_members=" + team_members + ", senior_name="
				+ senior_name + ", completion_date=" + completion_date + ", client_name=" + client_name + ", flag="
				+ flag + ", platform_to_work=" + platform_to_work + ", delay_reason=" + delay_reason + ", start_date="
				+ start_date + ", admin_completion_date=" + admin_completion_date + ", prj_type=" + prj_type
				+ ", ref_prj=" + ref_prj + "]";
	}
}
