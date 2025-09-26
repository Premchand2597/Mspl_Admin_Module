package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveUtilized;

@Repository
public interface LeaveUtilizedRepository extends JpaRepository<LeaveUtilized, Long>{

	LeaveUtilized findByUserEmailAndFinancialYearAndLeaveType(String employeeEmail, String financialYear,
			String leaveType);

	Object findByEmpIdAndLeaveType(String empId, String leaveType);

	List<LeaveUtilized> findByUserEmailAndFinancialYear(String employeeEmail, String financialYear);
	
	List<LeaveUtilized> findByEmpIdAndFinancialYear(String empId, String financialYear);

	  // Query to fetch all leave records for an employee based on empId
    List<LeaveUtilized> findByEmpId(String empId);  // Query to fetch all leave records for an employee based on empId
  
}
