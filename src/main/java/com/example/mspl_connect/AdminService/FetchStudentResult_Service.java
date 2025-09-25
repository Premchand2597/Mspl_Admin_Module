package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MoatCandidateDataWithTestTypeStatus_Entity;
import com.example.mspl_connect.AdminEntity.QuizResponse_Entity;
import com.example.mspl_connect.AdminEntity.StudentResult_Entity;
import com.example.mspl_connect.AdminRepo.FetchStudentResult_Repo;
import com.example.mspl_connect.AdminRepo.MoatCandidateDataWithTestTypeStatus_Repo;
import com.example.mspl_connect.AdminRepo.QuizResponse_Repo;

import jakarta.transaction.Transactional;

@Service
public class FetchStudentResult_Service {

	@Autowired
	private FetchStudentResult_Repo fetchStudentResult_Repo;
	
	@Autowired
	private QuizResponse_Repo quizResponse_Repo;
	
	@Autowired
	private MoatCandidateDataWithTestTypeStatus_Repo moatCandidateDataWithTestTypeStatus_Repo;
	
	@Transactional
	public List<StudentResult_Entity> getStudentResult(String student_id){
		return fetchStudentResult_Repo.fetch_student_result(student_id);
	}
	
	@Transactional
	public boolean updateTechnicalMarksValue(String student_id, String question_id, String technicalMarkValue) {
        int rowsAffected = fetchStudentResult_Repo.updateTechnicalMarks(student_id, question_id, technicalMarkValue);
        return rowsAffected > 0;
    }
	
	@Transactional
	public QuizResponse_Entity fetch_UpdatedTechnicalMarkValueDetail(String student_id, String question_id){
		return quizResponse_Repo.fetch_UpdatedTechnicalMarkValue(student_id,question_id);
	}
	
	@Transactional
	public MoatCandidateDataWithTestTypeStatus_Entity getStudentResultWithTestTypeStatus(String student_id){
		return moatCandidateDataWithTestTypeStatus_Repo.fetchCandidateDataWithTestTypeStatus(student_id);
	}
	
	
	public boolean updateSelectionStatusforCandidateInEachRounds(String clickedRound, String student_id, String status){
		int isStatusUpdate = 0;
		if("first_round".equals(clickedRound)) {
			isStatusUpdate = moatCandidateDataWithTestTypeStatus_Repo.updateSelectionStatusforFirstRound(student_id, status);
		}else if("F2F ROUND".equals(clickedRound)) {
			isStatusUpdate = moatCandidateDataWithTestTypeStatus_Repo.updateSelectionStatusforF2FRound(student_id, status);
		}else if("HR ROUND".equals(clickedRound)) {
			isStatusUpdate = moatCandidateDataWithTestTypeStatus_Repo.updateSelectionStatusforHRRound(student_id, status);
		}
		return isStatusUpdate > 0;
	}
	
	public boolean updateInvitationForNextRoundDetails(String selectedRound, String student_id, String date, String time){
		int isStatusUpdate = 0;
		if("F2F Round".equals(selectedRound)) {
			isStatusUpdate = moatCandidateDataWithTestTypeStatus_Repo.updateF2FInterviewDateAndTime(student_id, date, time);
		}else if("HR Round".equals(selectedRound)) {
			isStatusUpdate = moatCandidateDataWithTestTypeStatus_Repo.updateHRInterviewDateAndTime(student_id, date, time);
		}
		return isStatusUpdate > 0;
	}
	
	public void updateFinalSelectionStatusFlagView(String student_id, String final_status_viewed_flag){
		moatCandidateDataWithTestTypeStatus_Repo.updateFinalSelectionStatusFlag(student_id, final_status_viewed_flag);
	}

}
