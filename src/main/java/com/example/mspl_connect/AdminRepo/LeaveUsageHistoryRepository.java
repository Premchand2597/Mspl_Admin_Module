package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveUsageHistory;


@Repository
public interface LeaveUsageHistoryRepository extends JpaRepository<LeaveUsageHistory, Long>{

	
	List<LeaveUsageHistory> findByLeaveTypeAndFinancialYearAndEmployeeEmail(
	        String leaveType, String financialYear, String employeeEmail);
	
	 List<LeaveUsageHistory> findByLeaveTypeAndFinancialYearAndEmpId(
	            String leaveType, String financialYear, String empId);

}
