package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalAdminEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AppraisalAdminRepo extends JpaRepository<AppraisalAdminEntity, Integer> {

	@Modifying
    @Transactional
	@Query(nativeQuery = true, value = "update appraisal_employee set apprisal_admin_flag=0 where emp_id=:empId")
	int update(String empId);
	
	@Modifying
    @Transactional
	@Query(nativeQuery = true, value = "update apprisal_hr set apprisal_link_flag=0 where emp_id=:empId")
	int updateAdminFlag(String empId);

}
