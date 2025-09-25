package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity {

	@Id
	private long id;
	private String team_lead_id;
	private String team_co_id;
	@Column(nullable = true)
	private String team_lead_email;
	@Column(nullable = true)
	private String team_co_email;
	@Column(nullable = true)
	private String employee_email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeam_lead_id() {
		return team_lead_id;
	}
	public void setTeam_lead_id(String team_lead_id) {
		this.team_lead_id = team_lead_id;
	}
	public String getTeam_co_id() {
		return team_co_id;
	}
	public void setTeam_co_id(String team_co_id) {
		this.team_co_id = team_co_id;
	}
	public String getTeam_lead_email() {
		return team_lead_email;
	}
	public void setTeam_lead_email(String team_lead_email) {
		this.team_lead_email = team_lead_email;
	}
	public String getTeam_co_email() {
		return team_co_email;
	}
	public void setTeam_co_email(String team_co_email) {
		this.team_co_email = team_co_email;
	}
	public String getEmployee_email() {
		return employee_email;
	}
	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}
	@Override
	public String toString() {
		return "TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity [id=" + id + ", team_lead_id="
				+ team_lead_id + ", team_co_id=" + team_co_id + ", team_lead_email=" + team_lead_email
				+ ", team_co_email=" + team_co_email + ", employee_email=" + employee_email + "]";
	}
	
}
