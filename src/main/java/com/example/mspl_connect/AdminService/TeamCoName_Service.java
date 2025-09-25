package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.TeamCoName_Entity;
import com.example.mspl_connect.AdminRepo.TeamCoName_Repo;


@Service
public class TeamCoName_Service {

	@Autowired
	private TeamCoName_Repo teamCoName_Repo;
	
	public List<TeamCoName_Entity> getTeamCoNameByUsingDeptId(int dept_id){
		return teamCoName_Repo.fetchTeamCoNameByDept(dept_id);
	}
}
