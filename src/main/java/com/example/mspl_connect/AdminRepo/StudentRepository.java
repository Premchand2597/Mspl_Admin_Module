package com.example.mspl_connect.AdminRepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Candidate_Entity;

import jakarta.transaction.Transactional;



@Repository
public interface StudentRepository extends JpaRepository<Candidate_Entity, String>{
	
	boolean existsByEmail(String email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update student set profile_pic_path = :profilePicPath, pan_pic_path = :panPicPath, aadhar_pic = :aadharPic, voter_id_pic = :voterIdPic, tenth_pic = :tenthPic, puc_pic = :pucPic, degree_pic = :degreePic, exp_letter_pic = :expLetterPic, pg_pic = :pgPic, payslip_pic = :payslipPic where id = :candidate_id")
	public int updateSelectedCandidatesDocs(@Param("candidate_id") String candidate_id, @Param("profilePicPath") String profilePicPath,
			@Param("panPicPath") String panPicPath, @Param("aadharPic") String aadharPic, @Param("voterIdPic") String voterIdPic,
			@Param("tenthPic") String tenthPic, @Param("pucPic") String pucPic, @Param("degreePic") String degreePic,
			@Param("expLetterPic") String expLetterPic, @Param("pgPic") String pgPic, @Param("payslipPic") String payslipPic);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update student set test_status_flagg = 1 where id = :candidate_id")
	public void updateCandidateOngoingTestStatusFlag(@Param("candidate_id") String candidate_id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update student set test_status_flagg = 2 where id = :candidate_id")
	public void updateCandidateCompletedTestStatusFlag(@Param("candidate_id") String candidate_id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update student set test_status_flagg = 0 where id = :candidate_id")
	public void resetCandidateTestStatusFlag(@Param("candidate_id") String candidate_id);

}