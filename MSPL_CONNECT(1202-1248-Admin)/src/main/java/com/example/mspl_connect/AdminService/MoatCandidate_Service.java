package com.example.mspl_connect.AdminService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MoatCandidate_Entity;
import com.example.mspl_connect.AdminEntity.StudentApproveCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentOnHoldCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentPendingCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentRejectCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentTotalCount_Entity;
import com.example.mspl_connect.AdminRepo.MoatCandidate_Repo;
import com.example.mspl_connect.AdminRepo.ModeOfContactNewCandidates_Repo;
import com.example.mspl_connect.AdminRepo.card1_Repo;
import com.example.mspl_connect.AdminRepo.card2_Repo;
import com.example.mspl_connect.AdminRepo.card3_Repo;
import com.example.mspl_connect.AdminRepo.card4_Repo;
import com.example.mspl_connect.AdminRepo.card5_Repo;

import jakarta.transaction.Transactional;

@Service
public class MoatCandidate_Service {

	@Autowired
	private MoatCandidate_Repo moatCandidate_Repo;
	
	@Autowired
	private card1_Repo card1_repo;
	
    @Autowired
	private card2_Repo card2_repo;
	
    @Autowired
	private card3_Repo card3_repo;
	
    @Autowired
	private card4_Repo card4_repo;
    
    @Autowired
	private card5_Repo card5_repo;
	
    @Autowired
    private ModeOfContactNewCandidates_Repo modeOfContactNewCandidates_Repo;
    
	@Transactional
	public List<MoatCandidate_Entity> getStudentData(){
		return moatCandidate_Repo.fetch_student_data();
	}
	
	@Transactional
	public List<MoatCandidate_Entity> getStudentDataBasedOnAction(String action1,String loggedadmin_dept,Integer deptId){
		//System.out.println("fetchStudentAllDataBasedOnAction 2");
		List<MoatCandidate_Entity> studentDataBasedOnAction;
		//System.out.println("fetchStudentAllDataBasedOnAction 3");
		if(deptId==0) {
			//System.out.println("action in getStudentDataBasedOnAction For Superadmin ---"+action1);
			studentDataBasedOnAction = moatCandidate_Repo.fetch_student_data_based_on_status(action1);
		} else {
			System.out.println("action in getStudentDataBasedOnAction For admin ---"+action1);
			studentDataBasedOnAction = moatCandidate_Repo.fetch_interviewd_student_details_by_dept(action1,loggedadmin_dept);
			
			//System.out.println("studentDataBasedOnAction--------"+studentDataBasedOnAction);
		}
		return studentDataBasedOnAction;
	}
	
	@Transactional
    public List<StudentTotalCount_Entity>  getTotalStudentCount(String deptName,Integer deptId) {
    	List<StudentTotalCount_Entity> totalCandidateCount;
    	if(deptId == 0)
    	{
    		totalCandidateCount = card1_repo.fetch_student_count();
    	}
    	else {
    		totalCandidateCount = card1_repo.fetch_student_count(deptName);
    	}
    	
    	System.out.println("totalCandidateCount==="+totalCandidateCount);
    	/*System.out.println("interviewedCandidateCount--------"+interviewedCandidateCount);
    	
    	Integer screenigCandidateCount = modeOfContactNewCandidates_Repo.countByDept(dept);
    	System.out.println("screenigCandidateCount--------"+screenigCandidateCount);
    	
    	// Calculate total
        Integer totalCandidateCount = interviewedCandidateCount + screenigCandidateCount;
        System.out.println("totalCandidateCount--------" + totalCandidateCount);
        
        // Create a new instance of StudentTotalCount_Entity
        StudentTotalCount_Entity studentTotalCount = new StudentTotalCount_Entity();
        studentTotalCount.setStudent_count(String.valueOf(totalCandidateCount)); // Convert integer to string
        
        // Add to the list
        List<StudentTotalCount_Entity> resultList = new ArrayList<>();
        resultList.add(studentTotalCount);*/
        
        return totalCandidateCount;
        }
    
	/*@Transactional
    public List<StudentApproveCount_Entity> getTotalApproveCount(String dept) {
    	
    	Integer totalApprovedCandidateCountList = card2_repo.fetch_approve_count(dept);
    	Integer totalScreenerySelectedCandidateCountList = modeOfContactNewCandidates_Repo.countByDeptAndRemarks(dept,"Selected");
    	
    	// Calculate total
        Integer totalSelectedCandidateCount = totalApprovedCandidateCountList + totalScreenerySelectedCandidateCountList;
        System.out.println("totalCandidateCount--------" + totalSelectedCandidateCount);
        
        // Create a new instance of StudentTotalCount_Entity
        StudentApproveCount_Entity studentTotalCount = new StudentApproveCount_Entity();
        studentTotalCount.setApprove_count(String.valueOf(totalSelectedCandidateCount)); // Convert integer to string
        
        // Add to the list
        List<StudentTotalCount_Entity> resultList = new ArrayList<>();
        resultList.add(studentTotalCount);
        
        return resultList;
    }*/
    @Transactional
    public List<StudentApproveCount_Entity> getTotalApproveCount(String dept,Integer deptId) {
    	List<StudentApproveCount_Entity> totalApprovedCandidateCountList;
    	
    	if(deptId==0) {
    		totalApprovedCandidateCountList = card2_repo.fetch_approve_count(); 
    	}
    	else{
	        // Fetch data from repositories
	    	totalApprovedCandidateCountList = card2_repo.fetch_approve_count(dept);
    	}
        /*Integer totalScreenerySelectedCandidateCountList = modeOfContactNewCandidates_Repo.countByDeptAndRemarks(dept, "Selected");

        // Calculate total
        Integer totalSelectedCandidateCount = totalApprovedCandidateCountList + totalScreenerySelectedCandidateCountList;
        System.out.println("totalCandidateCount--------" + totalSelectedCandidateCount);

        // Create a new instance of StudentApproveCount_Entity
        StudentApproveCount_Entity studentApproveCount = new StudentApproveCount_Entity();
        studentApproveCount.setApprove_count(String.valueOf(totalSelectedCandidateCount)); // Convert integer to string

        // Add to the list
        List<StudentApproveCount_Entity> resultList = new ArrayList<>();
        resultList.add(studentApproveCount);*/

        return totalApprovedCandidateCountList;
    }

    
	@Transactional
    public List<StudentRejectCount_Entity> getTotalRejectCount(String dept,Integer adminDept) {
    	List<StudentRejectCount_Entity> totalRejectedCount;
    	if(adminDept==0) {
    		totalRejectedCount = card3_repo.fetch_reject_count();
    	}
    	else {
    		totalRejectedCount = card3_repo.fetch_reject_count(dept);
    	}
    	
    	
    	/*Integer screeneryRejectedCandidateCount = modeOfContactNewCandidates_Repo.countByDeptAndRemarks(dept, "Rejected");
    	
    	String totalRejectedCandidates = String.valueOf(interviewTotalRejectedCount + screeneryRejectedCandidateCount);
    	
    	StudentRejectCount_Entity rejectedtotalCountEntity = new StudentRejectCount_Entity();
    	rejectedtotalCountEntity.setReject_count(totalRejectedCandidates);
    	
    	List<StudentRejectCount_Entity> totalRejectedCandidatesList = new ArrayList<>();
    	totalRejectedCandidatesList.add(rejectedtotalCountEntity);*/
    	
        return totalRejectedCount;
    }
    
	@Transactional
    public List<StudentPendingCount_Entity> getTotalPendingCount(String dept,Integer adminDept) {
    	List<StudentPendingCount_Entity> totalpendingCount ;
    	if(adminDept == 0){
    		totalpendingCount = card4_repo.fetch_pending_count();
    	}
    	else {
    		totalpendingCount = card4_repo.fetch_pending_count(dept);
    	}
    	
        return totalpendingCount;
    }
    
    @Transactional
    public List<StudentOnHoldCount_Entity> getTotalOnHoldCount(String dept,Integer deptId) {
    	
    	List<StudentOnHoldCount_Entity> onHoldCount;
    	
    	if(deptId == 0) {
    		onHoldCount = card5_repo.fetch_onHold_count();
    	} else {
    		onHoldCount = card5_repo.fetch_onHold_count(dept);
    	}
    	
    	System.out.println("onHoldCount----"+onHoldCount);
        return onHoldCount;
        
    }
    
	public List<MoatCandidate_Entity> getSelectedCandidateData(){
		return moatCandidate_Repo.fetch_Selected_Candidate_data();
	}
	
	public List<MoatCandidate_Entity> getRejectedCandidateData(){
		return moatCandidate_Repo.fetch_Rejected_Candidate_data();
	}
	
	public MoatCandidate_Entity getCandidateDataById(String candidate_id){
		return moatCandidate_Repo.fetch_Candidate_data_By_Id(candidate_id);
	}
	
	/*public MoatCandidate_Entity getRejectedCandidateDataById(String candidate_id){
		return moatCandidate_Repo.fetch_Rejected_Candidate_data_By_Id(candidate_id);
	}*/
	
	public void update_candidate_flagData(String candidate_id){
		 moatCandidate_Repo.update_candidate_flag(candidate_id);
	}
}
