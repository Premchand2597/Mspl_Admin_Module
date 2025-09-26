package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamCoName_Entity {

	@Id
	private String empid;
	private String team_co_name;

	public String getTeam_co_name() {
		return team_co_name;
	}

	public void setTeam_co_name(String team_co_name) {
		this.team_co_name = team_co_name;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	@Override
	public String toString() {
		return "TeamCoName_Entity [empid=" + empid + ", team_co_name=" + team_co_name + "]";
	}
	
}
