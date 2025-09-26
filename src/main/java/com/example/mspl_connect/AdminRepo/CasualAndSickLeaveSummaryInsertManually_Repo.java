package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CasualAndSickLeaveSummaryInsertManually_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface CasualAndSickLeaveSummaryInsertManually_Repo extends JpaRepository<CasualAndSickLeaveSummaryInsertManually_Entity, Long>{

//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "insert into leave_summary(empid, financial_year, leave_type, month, added, remaining, total_added, total_used, user_email, last_updated, remarks) values(:empid, :financial_year, :leave_type, UPPER(DATE_FORMAT(NOW(), '%M')), :added, :remaining, :total_added, :total_used, :user_email, now(), :remarks)")
//	void insertCasualOrSickLeaveDataInSummary(@Param("empid") String empid, @Param("financial_year") String financial_year, 
//			@Param("leave_type") String leave_type, @Param("added") double added, @Param("remaining") double remaining, 
//			@Param("total_added") double total_added, @Param("total_used") double total_used, @Param("user_email") String user_email, 
//			@Param("remarks") String remarks);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	    INSERT INTO leave_summary(empid, financial_year, leave_type, month, added, remaining, total_added, total_used, user_email, last_updated, remarks)
	    VALUES(:empid, :financial_year, :leave_type, UPPER(TO_CHAR(NOW(), 'FMMonth')), :added, :remaining, :total_added, :total_used, :user_email, NOW(), :remarks)
	""")
	void insertCasualOrSickLeaveDataInSummary(@Param("empid") String empid,
	                                          @Param("financial_year") String financial_year,
	                                          @Param("leave_type") String leave_type,
	                                          @Param("added") double added,
	                                          @Param("remaining") double remaining,
	                                          @Param("total_added") double total_added,
	                                          @Param("total_used") double total_used,
	                                          @Param("user_email") String user_email,
	                                          @Param("remarks") String remarks);

}
