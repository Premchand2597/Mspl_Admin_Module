package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.LeaveUsageHistory;
import com.example.mspl_connect.AdminRepo.LeaveUsageHistoryRepository;
import com.example.mspl_connect.Repository.EmployeeRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveUsageHistoryController {

	@Autowired
    private LeaveUsageHistoryRepository leaveUsageHistoryRepository;
 
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/leave-usage-history")
	public ResponseEntity<List<LeaveUsageHistory>> getLeaveUsageHistory(
	        @RequestParam String leaveType,
	        @RequestParam String year,
	        HttpSession session) {

	    String email = (String) session.getAttribute("email");
	    System.out.println("Fetching leaves for user: " + email);

	    String empId = employeeRepository.findEmpidByEmail(email);
	    System.out.println("Employee ID retrieved: " + empId);

	    List<LeaveUsageHistory> historyList =
	            leaveUsageHistoryRepository.findByLeaveTypeAndFinancialYearAndEmpId(
	                    leaveType, year, empId);

	    // Always return JSON (empty list [] if no records)
	    return ResponseEntity.ok(historyList);
	}



}
