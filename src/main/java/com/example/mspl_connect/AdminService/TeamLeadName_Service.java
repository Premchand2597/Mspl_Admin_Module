package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.TeamLeadName_Entity;
import com.example.mspl_connect.AdminRepo.TeamLeadName_Repo;

@Service
public class TeamLeadName_Service {

	@Autowired
	private TeamLeadName_Repo teamLeadName_Repo;
	
	public List<TeamLeadName_Entity> getTeamLeadNameByUsingDeptId(){
		return teamLeadName_Repo.fetchTeamLeadNameByDept();
	}
}
