package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AppraisalNotificationAndValidationForHR_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalNotificationAndValidationForHR_Repo;


@Service
public class AppraisalNotificationAndValidationForHR_Service {

	@Autowired
	private AppraisalNotificationAndValidationForHR_Repo appraisalNotificationAndValidationForHR_Repo;
	
	public List<AppraisalNotificationAndValidationForHR_Entity> fetchAllData(){
		return appraisalNotificationAndValidationForHR_Repo.findAll();
	}
	
	public boolean updateQuarterFlagAndQuarterBtnEnableFlagValues(String auto_id){
		int status = appraisalNotificationAndValidationForHR_Repo.updateQuarterFlagAndQuarterBtnEnableFlag(auto_id);
		return status > 0;
	}
	
	public boolean updateQuarterFlagAndQuarterBtnEnableFlagAfterInsertingAppraisalDetails(String currYear, String auto_id){
		int status = appraisalNotificationAndValidationForHR_Repo.updateQuarterFlagAndQuarterBtnEnableFlagAfterInsertingAppraisalDetails(currYear, auto_id);
		return status > 0;
	}
	
}
