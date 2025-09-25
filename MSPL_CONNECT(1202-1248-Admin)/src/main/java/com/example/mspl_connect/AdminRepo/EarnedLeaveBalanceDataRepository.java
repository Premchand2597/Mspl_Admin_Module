package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.EarnedLeaveBalanceData;


public interface EarnedLeaveBalanceDataRepository extends JpaRepository<EarnedLeaveBalanceData, Integer> {

	
	@Query("SELECT el.lastIncrementDate FROM EarnedLeaveBalanceData el WHERE el.empId = :empId ORDER BY el.id DESC")
	List<String> findLastIncrementDatesByEmpId(@Param("empId") String empId);

}
