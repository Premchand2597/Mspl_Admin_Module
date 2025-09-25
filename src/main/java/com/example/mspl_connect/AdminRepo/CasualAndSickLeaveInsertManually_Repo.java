package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CasualAndSickLeaveInsertManually_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface CasualAndSickLeaveInsertManually_Repo extends JpaRepository<CasualAndSickLeaveInsertManually_Entity, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM leave_utilized where empid=:empid and leave_type=:leaveType order by id desc limit 1")
	CasualAndSickLeaveInsertManually_Entity getPreviousCasualOrSickLeaveDetailsBasedOnEmpidAndLeaveType(@Param("empid") String empid, @Param("leaveType") String leaveType);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update leave_utilized set financial_year=:financial_year, remaining=:remaining, total_added=:total_added, total_used=:total_used, user_email =:user_email, last_updated = curdate(), remarks=:remarks where empid=:empid and leave_type=:leaveType order by id desc limit 1")
//	int updateCasualOrSickLeaveDataBasedOnEmpIdAndLeaveType(@Param("empid") String empid, @Param("leaveType") String leaveType, 
//			@Param("financial_year") String financial_year,  @Param("total_added") double total_added, @Param("total_used") double total_used, 
//			@Param("remaining") double remaining, @Param("user_email") String user_email, @Param("remarks") String remarks);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	    UPDATE leave_utilized
	    SET financial_year = :financial_year,
	        remaining = :remaining,
	        total_added = :total_added,
	        total_used = :total_used,
	        user_email = :user_email,
	        last_updated = CURRENT_DATE,
	        remarks = :remarks
	    WHERE id = (
	        SELECT id FROM leave_utilized
	        WHERE empid = :empid AND leave_type = :leaveType
	        ORDER BY id DESC
	        LIMIT 1
	    )
	""")
	int updateCasualOrSickLeaveDataBasedOnEmpIdAndLeaveType(@Param("empid") String empid,
	                                                        @Param("leaveType") String leaveType,
	                                                        @Param("financial_year") String financial_year,
	                                                        @Param("total_added") double total_added,
	                                                        @Param("total_used") double total_used,
	                                                        @Param("remaining") double remaining,
	                                                        @Param("user_email") String user_email,
	                                                        @Param("remarks") String remarks);

	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into leave_utilized(empid, financial_year, leave_type, remaining, total_added, total_used, user_email, last_updated, remarks) values(:empid, :financial_year, :leave_type, :remaining, :total_added, 0, :user_email, curdate(), :remarks)")
	int insertCasualOrSickLeaveDataForNewEmployee(@Param("empid") String empid, @Param("leave_type") String leave_type, 
			@Param("financial_year") String financial_year,  @Param("total_added") double total_added, @Param("remaining") double remaining, 
			@Param("user_email") String user_email, @Param("remarks") String remarks);
}
