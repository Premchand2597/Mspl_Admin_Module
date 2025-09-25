package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.AppraisalSubmittedCountWithEmpId_Entity;

import jakarta.transaction.Transactional;

public interface AppraisalSubmittedCountWithEmpId_Repo extends JpaRepository<AppraisalSubmittedCountWithEmpId_Entity, String>{

	@Query(nativeQuery = true, value = "select sum(case when apprisal_admin_flag = '1' then 1 else 0 end) as apprisal_submitted_count, emp_id from appraisal_admin group by emp_id")
	List<AppraisalSubmittedCountWithEmpId_Entity> getAppraisalSubmittedCountWithEmpIdDetails();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update appraisal_admin set apprisal_admin_flag = 0 where emp_id=:emp_id")
	void resetAppraisalSubmittedCount(@Param("emp_id") String emp_id);
}
