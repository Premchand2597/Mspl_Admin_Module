package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EarnedLeaveSummaryInsertManually_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface EarnedLeaveSummaryInsertManually_Repo extends JpaRepository<EarnedLeaveSummaryInsertManually_Entity, Long>{

//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "insert into earned_leave_summary(incremented_date, added_as_earned_leave, remaining, emp_id, email, last_updated, remarks) values(curdate(), :added_as_earned_leave, :remaining, :emp_id, :email, now(), :remarks)")
//	void insertEarnedLeaveDataInSummary(@Param("emp_id") String emp_id, @Param("added_as_earned_leave") double added_as_earned_leave, 
//										@Param("remaining") double remaining, @Param("email") String email, @Param("remarks") String remarks);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into earned_leave_summary(incremented_date, added_as_earned_leave, remaining, emp_id, email, last_updated, remarks) values(current_date, :added_as_earned_leave, :remaining, :emp_id, :email, now(), :remarks)")
	void insertEarnedLeaveDataInSummary(@Param("emp_id") String emp_id, @Param("added_as_earned_leave") double added_as_earned_leave, 
										@Param("remaining") double remaining, @Param("email") String email, @Param("remarks") String remarks);
}
