package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppraisalFromAdmin_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_id; 
	private String dept;
	private String quarter;
	private String quarter_year;
	private String project_planning_points;
	private String project_planning_desc;
	private String team_leadership_points;
	private String team_leadership_desc;
	private String stakeholder_comm_points;
	private String stakeholder_comm_desc;
	private String budget_cost_points;
	private String budget_cost_description;
	private String risk_management_points;
	private String risk_management_desc;
	private String quality_assurance_points;
	private String quality_assurance_desc;
	private String total_score;
	
	private String user_id;
	private String user_project_planning_points;
	private String user_team_leadership_points;
	private String user_stakeholder_comm_points;
	private String user_budget_cost_points;
	private String user_risk_management_points;
	private String user_quality_assurance_points;
	private String user_total_score;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarter_year() {
		return quarter_year;
	}
	public void setQuarter_year(String quarter_year) {
		this.quarter_year = quarter_year;
	}
	public String getProject_planning_points() {
		return project_planning_points;
	}
	public void setProject_planning_points(String project_planning_points) {
		this.project_planning_points = project_planning_points;
	}
	public String getProject_planning_desc() {
		return project_planning_desc;
	}
	public void setProject_planning_desc(String project_planning_desc) {
		this.project_planning_desc = project_planning_desc;
	}
	public String getTeam_leadership_points() {
		return team_leadership_points;
	}
	public void setTeam_leadership_points(String team_leadership_points) {
		this.team_leadership_points = team_leadership_points;
	}
	public String getTeam_leadership_desc() {
		return team_leadership_desc;
	}
	public void setTeam_leadership_desc(String team_leadership_desc) {
		this.team_leadership_desc = team_leadership_desc;
	}
	public String getStakeholder_comm_points() {
		return stakeholder_comm_points;
	}
	public void setStakeholder_comm_points(String stakeholder_comm_points) {
		this.stakeholder_comm_points = stakeholder_comm_points;
	}
	public String getStakeholder_comm_desc() {
		return stakeholder_comm_desc;
	}
	public void setStakeholder_comm_desc(String stakeholder_comm_desc) {
		this.stakeholder_comm_desc = stakeholder_comm_desc;
	}
	public String getBudget_cost_points() {
		return budget_cost_points;
	}
	public void setBudget_cost_points(String budget_cost_points) {
		this.budget_cost_points = budget_cost_points;
	}
	public String getBudget_cost_description() {
		return budget_cost_description;
	}
	public void setBudget_cost_description(String budget_cost_description) {
		this.budget_cost_description = budget_cost_description;
	}
	public String getRisk_management_points() {
		return risk_management_points;
	}
	public void setRisk_management_points(String risk_management_points) {
		this.risk_management_points = risk_management_points;
	}
	public String getRisk_management_desc() {
		return risk_management_desc;
	}
	public void setRisk_management_desc(String risk_management_desc) {
		this.risk_management_desc = risk_management_desc;
	}
	public String getQuality_assurance_points() {
		return quality_assurance_points;
	}
	public void setQuality_assurance_points(String quality_assurance_points) {
		this.quality_assurance_points = quality_assurance_points;
	}
	public String getQuality_assurance_desc() {
		return quality_assurance_desc;
	}
	public void setQuality_assurance_desc(String quality_assurance_desc) {
		this.quality_assurance_desc = quality_assurance_desc;
	}
	public String getTotal_score() {
		return total_score;
	}
	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_project_planning_points() {
		return user_project_planning_points;
	}
	public void setUser_project_planning_points(String user_project_planning_points) {
		this.user_project_planning_points = user_project_planning_points;
	}
	public String getUser_team_leadership_points() {
		return user_team_leadership_points;
	}
	public void setUser_team_leadership_points(String user_team_leadership_points) {
		this.user_team_leadership_points = user_team_leadership_points;
	}
	public String getUser_stakeholder_comm_points() {
		return user_stakeholder_comm_points;
	}
	public void setUser_stakeholder_comm_points(String user_stakeholder_comm_points) {
		this.user_stakeholder_comm_points = user_stakeholder_comm_points;
	}
	public String getUser_budget_cost_points() {
		return user_budget_cost_points;
	}
	public void setUser_budget_cost_points(String user_budget_cost_points) {
		this.user_budget_cost_points = user_budget_cost_points;
	}
	public String getUser_risk_management_points() {
		return user_risk_management_points;
	}
	public void setUser_risk_management_points(String user_risk_management_points) {
		this.user_risk_management_points = user_risk_management_points;
	}
	public String getUser_quality_assurance_points() {
		return user_quality_assurance_points;
	}
	public void setUser_quality_assurance_points(String user_quality_assurance_points) {
		this.user_quality_assurance_points = user_quality_assurance_points;
	}
	public String getUser_total_score() {
		return user_total_score;
	}
	public void setUser_total_score(String user_total_score) {
		this.user_total_score = user_total_score;
	}
	@Override
	public String toString() {
		return "AppraisalFromAdmin_Entity [id=" + id + ", emp_id=" + emp_id + ", dept=" + dept + ", quarter=" + quarter
				+ ", quarter_year=" + quarter_year + ", project_planning_points=" + project_planning_points
				+ ", project_planning_desc=" + project_planning_desc + ", team_leadership_points="
				+ team_leadership_points + ", team_leadership_desc=" + team_leadership_desc
				+ ", stakeholder_comm_points=" + stakeholder_comm_points + ", stakeholder_comm_desc="
				+ stakeholder_comm_desc + ", budget_cost_points=" + budget_cost_points + ", budget_cost_description="
				+ budget_cost_description + ", risk_management_points=" + risk_management_points
				+ ", risk_management_desc=" + risk_management_desc + ", quality_assurance_points="
				+ quality_assurance_points + ", quality_assurance_desc=" + quality_assurance_desc + ", total_score="
				+ total_score + ", user_id=" + user_id + ", user_project_planning_points="
				+ user_project_planning_points + ", user_team_leadership_points=" + user_team_leadership_points
				+ ", user_stakeholder_comm_points=" + user_stakeholder_comm_points + ", user_budget_cost_points="
				+ user_budget_cost_points + ", user_risk_management_points=" + user_risk_management_points
				+ ", user_quality_assurance_points=" + user_quality_assurance_points + ", user_total_score="
				+ user_total_score + "]";
	}
}
