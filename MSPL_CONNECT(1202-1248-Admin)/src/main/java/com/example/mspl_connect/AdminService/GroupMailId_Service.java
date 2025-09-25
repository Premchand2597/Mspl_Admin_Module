package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminRepo.GroupMailId_Repo;
import com.example.mspl_connect.Entity.GroupMailId_Entity;

import jakarta.transaction.Transactional;

@Service
public class GroupMailId_Service {

	@Autowired
	private GroupMailId_Repo groupMailId_Repo;
	
	@Transactional
	 public String insertGroupMailIds(GroupMailId_Entity groupMailIdList) {
		groupMailId_Repo.save(groupMailIdList);
		 return "success";
	 }
	
	public List<GroupMailId_Entity> fetchGroupMailId(){
		return groupMailId_Repo.getGroupMailIdData();
	}
}
