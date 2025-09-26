package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.ModeOfContactNewCandidates_Entity;
import com.example.mspl_connect.AdminRepo.ModeOfContactNewCandidates_Repo;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ModeOfContactNewCandidates_Service {

	@Autowired
	private ModeOfContactNewCandidates_Repo modeOfContactNewCandidates_Repo;
	
	public void addMoatContactData(ModeOfContactNewCandidates_Entity modeOfContactNewCandidates_Entity,String deptId) {
		modeOfContactNewCandidates_Entity.setDept(deptId);
		modeOfContactNewCandidates_Repo.save(modeOfContactNewCandidates_Entity);
	}
	
	public void addMoatContactDataForSA(ModeOfContactNewCandidates_Entity modeOfContactNewCandidates_Entity) {
		modeOfContactNewCandidates_Repo.save(modeOfContactNewCandidates_Entity);
	}
	
	public List<ModeOfContactNewCandidates_Entity> getAllNewCandidatesContactListData(String status_value,Integer adminDept,String adminDeptName){
		
		List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListDataBasedOnStatus;
		
		if(adminDept == 0) {
			fetchAllCandidateContactListDataBasedOnStatus = modeOfContactNewCandidates_Repo.fetchAllCandidateContactListDataBasedOnStatus(status_value);
		}
		else {
			fetchAllCandidateContactListDataBasedOnStatus = modeOfContactNewCandidates_Repo.fetchAllCandidateContactListDataBasedOnStatus(status_value,adminDeptName);
		}
		
		return fetchAllCandidateContactListDataBasedOnStatus;
	}
	
	public List<ModeOfContactNewCandidates_Entity> getAllNewCandidatesContactListForRemainingStatus(Integer adminDept,String adminDeptName){
		List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListRemainingData;
		if(adminDept == 0) {
			fetchAllCandidateContactListRemainingData = modeOfContactNewCandidates_Repo.fetchAllCandidateContactListRemainingData();
		}
		else {
			fetchAllCandidateContactListRemainingData = modeOfContactNewCandidates_Repo.fetchAllCandidateContactListRemainingData(adminDeptName);
		}
		return fetchAllCandidateContactListRemainingData;
	}
	
	@Transactional
	public boolean setModeOfContactRemarksValue(String remarksValue, String id) {
        int rowsAffected = modeOfContactNewCandidates_Repo.changeModeOfContactRemarksValue(remarksValue, id);
        return rowsAffected > 0;
    }
	
	@Transactional
	 public boolean EmailExistsInTable(String email) {
		String isPresent = modeOfContactNewCandidates_Repo.findEmailExistsOrNot(email);
		if(isPresent == null) {
			return false;
		}else {
			return true;
		}
	 }
	
	
}
