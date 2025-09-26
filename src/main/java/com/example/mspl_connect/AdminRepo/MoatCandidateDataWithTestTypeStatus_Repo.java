package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatCandidateDataWithTestTypeStatus_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface MoatCandidateDataWithTestTypeStatus_Repo extends JpaRepository<MoatCandidateDataWithTestTypeStatus_Entity, String>{
//	@Query(nativeQuery = true, value = "select student.*,link_generated_table.test_type from student inner join link_generated_table on student.email = link_generated_table.candidate_email where student.id=:student_id order by link_generated_table.id desc limit 1")
//	MoatCandidateDataWithTestTypeStatus_Entity fetchCandidateDataWithTestTypeStatus(@Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value = "select student.*,link_generated_table.test_type from student inner join link_generated_table on student.email = link_generated_table.candidate_email where student.id=CAST(:student_id AS INTEGER) order by link_generated_table.id desc limit 1")
	MoatCandidateDataWithTestTypeStatus_Entity fetchCandidateDataWithTestTypeStatus(@Param("student_id") String student_id);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value="update student set first_round_result=:status where id=:student_id")
//	int updateSelectionStatusforFirstRound(@Param("student_id") String student_id, @Param("status") String status);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set first_round_result=:status where id=cast(:student_id as integer)")
	int updateSelectionStatusforFirstRound(@Param("student_id") String student_id, @Param("status") String status);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value="update student set f2f_round_result=:status where id=:student_id")
//	int updateSelectionStatusforF2FRound(@Param("student_id") String student_id, @Param("status") String status);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set f2f_round_result=:status where id=cast(:student_id as integer)")
	int updateSelectionStatusforF2FRound(@Param("student_id") String student_id, @Param("status") String status);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value="update student set hr_round_result=:status where id=:student_id")
//	int updateSelectionStatusforHRRound(@Param("student_id") String student_id, @Param("status") String status);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set hr_round_result=:status where id=cast(:student_id as integer)")
	int updateSelectionStatusforHRRound(@Param("student_id") String student_id, @Param("status") String status);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set f2f_round_invite_date=:f2f_round_invite_date, f2f_round_invite_time=:f2f_round_invite_time where id=cast(:student_id as integer)")
	int updateF2FInterviewDateAndTime(@Param("student_id") String student_id, @Param("f2f_round_invite_date") String f2f_round_invite_date, 
			@Param("f2f_round_invite_time") String f2f_round_invite_time);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set hr_round_invite_date=:hr_round_invite_date, hr_round_invite_tme=:hr_round_invite_time where id=cast(:student_id as integer)")
	int updateHRInterviewDateAndTime(@Param("student_id") String student_id, @Param("hr_round_invite_date") String hr_round_invite_date, 
			@Param("hr_round_invite_time") String hr_round_invite_time);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value="update student set final_status_viewed_flag=:final_status_viewed_flag where id=:student_id")
//	void updateFinalSelectionStatusFlag(@Param("student_id") String student_id, @Param("final_status_viewed_flag") String final_status_viewed_flag);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set final_status_viewed_flag=:final_status_viewed_flag where id=cast(:student_id as integer)")
	void updateFinalSelectionStatusFlag(@Param("student_id") String student_id, @Param("final_status_viewed_flag") String final_status_viewed_flag);
}
