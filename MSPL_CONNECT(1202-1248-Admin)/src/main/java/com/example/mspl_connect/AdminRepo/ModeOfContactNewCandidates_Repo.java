package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ModeOfContactNewCandidates_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface ModeOfContactNewCandidates_Repo extends JpaRepository<ModeOfContactNewCandidates_Entity, Long>{

	@Query(nativeQuery = true, value = "select * from moat_candidate_mode_of_contact where remarks=:status_value order by id desc")
	List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListDataBasedOnStatus(@Param("status_value") String status_value);
	
	@Query(nativeQuery = true, value = "select * from moat_candidate_mode_of_contact where remarks=:status_value and dept = :deptName order by id desc")
	List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListDataBasedOnStatus(@Param("status_value") String status_value,String deptName);
	
	@Query(nativeQuery = true, value = "select * from moat_candidate_mode_of_contact where remarks <> 'Rejected' and remarks <> 'Selected' and remarks <> 'Onhold' order by id desc")
	List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListRemainingData();
	
	@Query(nativeQuery = true, value = "select * from moat_candidate_mode_of_contact where remarks <> 'Rejected' and remarks <> 'Selected' and remarks <> 'Onhold'  and dept=:adminDeptName order by id desc")
	List<ModeOfContactNewCandidates_Entity> fetchAllCandidateContactListRemainingData(String adminDeptName);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update moat_candidate_mode_of_contact set remarks = :remarksValue where id = :id")
    public int changeModeOfContactRemarksValue(@Param("remarksValue") String remarksValue, @Param("id") String id);
	
	@Query(nativeQuery = true, value="select candidate_email from moat_candidate_mode_of_contact where candidate_email=:email")
	String findEmailExistsOrNot(@Param("email") String email);
	
	Integer countByDept(@Param("dept") String dept);
	
	Integer countByDeptAndRemarks(@Param("dept") String dept, @Param("remarks") String remarks);
}
