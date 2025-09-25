package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatCandidate_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface ApproveStudentStatus_Repo extends JpaRepository<com.example.mspl_connect.AdminEntity.MoatCandidate_Entity, Long>{

	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set action = :action_satus, candidate_action_reason = :candidate_action_reason, candidate_status_flag = :notificationFlagValue where id = :id")
    public int changeApproveStatus(@Param("action_satus") String action_satus, @Param("id") String id, @Param("candidate_action_reason") String candidate_action_reason, @Param("notificationFlagValue") int notificationFlagValue);

	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set f2f_total_marks = :f2f_total_marks, f2f_assigned_marks = :f2f_assigned_marks, f2f_remarks = :f2f_remarks  where id = :student_id")
    public int updateF2FMarks(@Param("student_id") String student_id, @Param("f2f_total_marks") String f2f_total_marks, 
    		@Param("f2f_assigned_marks") String f2f_assigned_marks, @Param("f2f_remarks") String f2f_remarks);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set hr_total_marks = :hr_total_marks, hr_assigned_marks = :hr_assigned_marks, hr_remarks = :hr_remarks  where id = :student_id")
    public int updateHRMarks(@Param("student_id") String student_id, @Param("hr_total_marks") String hr_total_marks, 
    		@Param("hr_assigned_marks") String hr_assigned_marks, @Param("hr_remarks") String hr_remarks);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set first_ctc_offer = :first_ctc_offer where id = :student_id")
    public int updateFirstCTCOffer(@Param("first_ctc_offer") String first_ctc_offer, @Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set second_ctc_offer = :second_ctc_offer where id = :student_id")
    public int updateSecondCTCOffer(@Param("second_ctc_offer") String second_ctc_offer, @Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set third_ctc_offer = :third_ctc_offer where id = :student_id")
    public int updateThirdCTCOffer(@Param("third_ctc_offer") String third_ctc_offer, @Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value = "select first_ctc_offer from student where id = :student_id")
	public String getFirstCTCValue(@Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value = "select second_ctc_offer from student where id = :student_id")
	public String getSecondCTCValue(@Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value = "select third_ctc_offer from student where id = :student_id")
	public String getThirdCTCValue(@Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set candidate_response_for_first_ctc_offer = :candidate_response_for_first_ctc_offer, candidate_remark_for_first_ctc_offer = :candidate_remark_for_first_ctc_offer where id = :student_id")
    public int updateFirstCTCResponseAndRemark(@Param("candidate_response_for_first_ctc_offer") String candidate_response_for_first_ctc_offer, @Param("candidate_remark_for_first_ctc_offer") String candidate_remark_for_first_ctc_offer, @Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set candidate_response_for_second_ctc_offer = :candidate_response_for_second_ctc_offer, candidate_remark_for_second_ctc_offer = :candidate_remark_for_second_ctc_offer where id = :student_id")
    public int updateSecondCTCResponseAndRemark(@Param("candidate_response_for_second_ctc_offer") String candidate_response_for_second_ctc_offer, @Param("candidate_remark_for_second_ctc_offer") String candidate_remark_for_second_ctc_offer, @Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set candidate_response_for_third_ctc_offer = :candidate_response_for_third_ctc_offer, candidate_remark_for_third_ctc_offer = :candidate_remark_for_third_ctc_offer where id = :student_id")
    public int updateThirdCTCResponseAndRemark(@Param("candidate_response_for_third_ctc_offer") String candidate_response_for_third_ctc_offer, @Param("candidate_remark_for_third_ctc_offer") String candidate_remark_for_third_ctc_offer, @Param("student_id") String student_id);

	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE id = :student_id")
	MoatCandidate_Entity getFirstCTCResponseAndRemarks(@Param("student_id") String student_id);

	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE id = :student_id")
	MoatCandidate_Entity getSecondCTCResponseAndRemarks(@Param("student_id") String student_id);

	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE id = :student_id")
	MoatCandidate_Entity getThirdCTCResponseAndRemarks(@Param("student_id") String student_id);

	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set doc_upload_flag = :doc_upload_flag where id = :student_id")
    public int updateDocUploadFlag(@Param("doc_upload_flag") String doc_upload_flag, @Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value = "select doc_upload_flag from student where id = :student_id")
	public String getDocUploadFlagValue(@Param("student_id") String student_id);

}
