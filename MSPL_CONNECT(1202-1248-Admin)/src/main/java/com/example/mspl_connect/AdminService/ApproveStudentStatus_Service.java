package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MoatCandidate_Entity;
import com.example.mspl_connect.AdminRepo.ApproveStudentStatus_Repo;

import jakarta.transaction.Transactional;

@Service
public class ApproveStudentStatus_Service {

	@Autowired
	private ApproveStudentStatus_Repo approveStudentStatus_Repo;
	
	@Transactional
	public boolean setActionStatus(String action_satus, String id, String candidate_action_reason) {
		
		int notificationFlagValue=0;
		
		if(action_satus.equals("approv")) {
			notificationFlagValue = 2;
		}else if(action_satus.equals("reject")) {
			notificationFlagValue = 3;
		}else if(action_satus.equals("onhold")) {
			notificationFlagValue = 4;
		}
		
        int rowsAffected = approveStudentStatus_Repo.changeApproveStatus(action_satus, id, candidate_action_reason, notificationFlagValue);
        return rowsAffected > 0;
    }
	
	@Transactional
	public boolean setF2FMarks(String student_id, String f2f_total_marks, String f2f_assigned_marks, String f2f_remarks) {
        int rowsAffected = approveStudentStatus_Repo.updateF2FMarks(student_id, f2f_total_marks, f2f_assigned_marks, f2f_remarks);
        return rowsAffected > 0;
    }
	
	@Transactional
	public boolean setHRMarks(String student_id, String hr_total_marks, String hr_assigned_marks, String hr_remarks) {
        int rowsAffected = approveStudentStatus_Repo.updateHRMarks(student_id, hr_total_marks, hr_assigned_marks, hr_remarks);
        return rowsAffected > 0;
    }
	
	@Transactional
	public boolean setFirstSecondThirdCTCOffer(String ctcValueOffer, String student_id, int flag) {
		
		if(flag == 1) {
			int rowsAffected = approveStudentStatus_Repo.updateFirstCTCOffer(ctcValueOffer, student_id);
			return rowsAffected > 0;
		}else if(flag == 2) {
			int rowsAffected = approveStudentStatus_Repo.updateSecondCTCOffer(ctcValueOffer, student_id);
			return rowsAffected > 0;
		}else {
			int rowsAffected = approveStudentStatus_Repo.updateThirdCTCOffer(ctcValueOffer, student_id);
			return rowsAffected > 0;
		}
    }
	
	public String fetchCtcValue(String student_id, int flag) {
		
		if(flag == 1) {
			String ctcValue = approveStudentStatus_Repo.getFirstCTCValue(student_id);
			return ctcValue;
		}else if(flag == 2) {
			String ctcValue = approveStudentStatus_Repo.getSecondCTCValue(student_id);
			return ctcValue;
		}else {
			String ctcValue = approveStudentStatus_Repo.getThirdCTCValue(student_id);
			return ctcValue;
		}
    }
	
	@Transactional
	public boolean setCtcResponsefromCandidate(String ctcResponse, String ctcRemarks, String student_id, int flag) {
		
		if(flag == 1) {
			int rowsAffected = approveStudentStatus_Repo.updateFirstCTCResponseAndRemark(ctcResponse, ctcRemarks, student_id);
			return rowsAffected > 0;
		}else if(flag == 2) {
			int rowsAffected = approveStudentStatus_Repo.updateSecondCTCResponseAndRemark(ctcResponse, ctcRemarks, student_id);
			return rowsAffected > 0;
		}else {
			int rowsAffected = approveStudentStatus_Repo.updateThirdCTCResponseAndRemark(ctcResponse, ctcRemarks, student_id);
			return rowsAffected > 0;
		}
    }
	
	public MoatCandidate_Entity fetchCtcResponseAndRemarks(String student_id, int flag) {
	    if (flag == 1) {
	        return approveStudentStatus_Repo.getFirstCTCResponseAndRemarks(student_id); // If not found, return null
	    } else if (flag == 2) {
	        return approveStudentStatus_Repo.getSecondCTCResponseAndRemarks(student_id);
	    } else {
	        return approveStudentStatus_Repo.getThirdCTCResponseAndRemarks(student_id);
	    }
	}
	
	@Transactional
	public boolean setUploadFlag(String doc_upload_flag, String student_id) {
		int rowsAffected = approveStudentStatus_Repo.updateDocUploadFlag(doc_upload_flag, student_id);
		return rowsAffected > 0;
    }
	
	public String fetchDocFlagValue(String student_id) {
		return approveStudentStatus_Repo.getDocUploadFlagValue(student_id);
    }

}
