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
	
	public GroupMailId_Entity fetchGroupMailIdDataForEdit(String auto_id){
		return groupMailId_Repo.editGroupHolidayData(auto_id);
	}
	
	@Transactional
    public String updateGroupMailIdData(long id, String main_mail_id, String sub_mail_id, String group_mail_id) {
		groupMailId_Repo.update_GroupMailId_details(id, main_mail_id, sub_mail_id, group_mail_id);
		return "success";
	}
	
	@Transactional
	public String deleteGroupMailIdData(long id) {
		groupMailId_Repo.delete_GroupMailIdDetails(id);
		return "success";
	 
	}
}

