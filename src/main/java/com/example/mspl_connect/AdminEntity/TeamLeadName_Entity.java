package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamLeadName_Entity {

	@Id
	private String empid;
	private String team_lead_name;

	public String getTeam_lead_name() {
		return team_lead_name;
	}

	public void setTeam_lead_name(String team_lead_name) {
		this.team_lead_name = team_lead_name;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	@Override
	public String toString() {
		return "TeamLeadName_Entity [empid=" + empid + ", team_lead_name=" + team_lead_name + "]";
	}
	
}
