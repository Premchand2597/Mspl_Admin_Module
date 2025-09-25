package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.AdminEntity.LOP;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminRepo.LOPRepository;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class lopController {

	 @Autowired
	 private LOPRepository lopRepository;
	 

		@Autowired
		private EmployeeRepository employeeRepository;

	
	@PostMapping("/applyLOPLeave/{email}")
	public ResponseEntity<?> applyLOPLeave(@RequestBody LeaveApplication leaveRequest, HttpSession session,@PathVariable("email") String email) {
	    //String email = (String) session.getAttribute("email");
	    System.out.println("Fetching LOP leave request for user;;;;;;;;;;;: " + email);

	    String empId = employeeRepository.findEmpidByEmail(email);
	    System.out.println("Employee ID retrieved: " + empId);

	    // Check for null values
	    if (email == null || empId == null) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Session data not found. Please log in again.");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    }

	    try {
	        // Initialize a new LOP entity
	        LOP lopLeave = new LOP();
	        lopLeave.setEmpId(empId);
	        lopLeave.setLeaveType(leaveRequest.getLeaveType());
	        lopLeave.setEmail(email);

	        LocalDate fromDate = LocalDate.parse(leaveRequest.getFrom_date());
	        LocalDate toDate = LocalDate.parse(leaveRequest.getTo_date());
	        lopLeave.setLeaveStartDate(fromDate.toString());
	        lopLeave.setLeaveEndDate(toDate.toString());

	        long totalLeaveDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1; // Include both dates
	        lopLeave.setTotalLeaveDays(String.valueOf(totalLeaveDays));

	        // Set initial values for leave usage and status
	      //  lopLeave.setLeaveUsed("0");
	       // lopLeave.setLeaveRemaining(String.valueOf(totalLeaveDays));
	        lopLeave.setApplicationDate(LocalDate.now().toString());
	        lopLeave.setApprovedStatus("Pending");

	        // Save LOP leave details to the database
	        lopRepository.save(lopLeave);

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "LOP leave request saved successfully");

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        System.out.println("Error saving LOP leave request: " + e.getMessage());

	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Error saving LOP leave request");

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

}
