package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CardCountBasedOnInterviewData_Entity;

@Repository
public interface CardCountBasedOnInterviewData_Repo extends JpaRepository<CardCountBasedOnInterviewData_Entity, String>{

	@Query(nativeQuery = true, value="SELECT SUM(CASE WHEN action = 'approv' THEN 1 ELSE 0 END) AS interview_select, SUM(CASE WHEN action = 'reject' THEN 1 ELSE 0 END) AS interview_reject, SUM(CASE WHEN action = 'onhold' THEN 1 ELSE 0 END) AS interview_onhold,  SUM(CASE WHEN COALESCE(action, '') = '' THEN 1 ELSE 0 END) AS interview_pending FROM student s WHERE s.email IN ( SELECT l.candidate_email FROM link_generated_table l WHERE l.dept_name = :deptName )")
	CardCountBasedOnInterviewData_Entity fetchInterviewAllTypeOfStatusCountForCards(String deptName);
	
}
