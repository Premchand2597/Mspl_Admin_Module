package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.MaternityLeave;
import com.example.mspl_connect.AdminRepo.MaternityLeaveRepository;
import com.example.mspl_connect.AdminService.MaternityLeaveService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MaternityLeaveController {

	
	 @Autowired
	    private MaternityLeaveService maternityLeaveService;

	 @Autowired
	 private MaternityLeaveRepository maternityLeaveRepository;
	 
		@Autowired
		private EmployeeRepository employeeRepository;

		@PostMapping("/applyMaternityLeave/{email}")
		public ResponseEntity<?> applyMaternityLeave(@PathVariable String email,@RequestBody LeaveApplication leaveRequest, HttpSession session) {
			    //String email = (String) session.getAttribute("email");
			    System.out.println("Fetching leaves for user: " + email);
			    
			    String empId = employeeRepository.findEmpidByEmail(email);
			    System.out.println("Employee ID retrieved: " + empId);

		    // Check for null values
		    if(email == null || empId == null) {
		        Map<String, String> errorResponse = new HashMap<>();
		        errorResponse.put("message", "Session data not found. Please log in again.");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		    }

		    try {
		        MaternityLeave maternityLeave = new MaternityLeave();
		        maternityLeave.setEmpId(empId);
		        maternityLeave.setLeaveType(leaveRequest.getLeaveType());
		        maternityLeave.setEmail(email); // Use email from the session

		        LocalDate fromDate = LocalDate.parse(leaveRequest.getFrom_date());
		        LocalDate toDate = fromDate.plusMonths(6);
		        maternityLeave.setLeaveStartDate(fromDate.toString());
		        maternityLeave.setLeaveEndDate(toDate.toString());

		        long totalLeaveDays = ChronoUnit.DAYS.between(fromDate, toDate);
		        maternityLeave.setTotalLeaveDays(String.valueOf(totalLeaveDays));

		        maternityLeave.setLeaveUsed(String.valueOf(totalLeaveDays));
		        maternityLeave.setLeaveRemaining("-");
		        maternityLeave.setApplicationDate(LocalDate.now().toString());
		        maternityLeave.setApprovedstatus("Pending");
		        // Log the maternity leave details for debugging
		        System.out.println("Maternity Leave details: " + maternityLeave);

		        maternityLeaveRepository.save(maternityLeave);

		        Map<String, String> response = new HashMap<>();
		        response.put("message", "Maternity leave request saved successfully");

		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		        // Print error message to console
		        System.out.println("Error saving maternity leave request: " + e.getMessage());

		        // Prepare error response
		        Map<String, String> errorResponse = new HashMap<>();
		        errorResponse.put("message", "Error saving maternity leave request");

		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		    }
		}


		@GetMapping("/fetchMaternityLeave/{email}")
	    public ResponseEntity<?> fetchMaternityLeave(HttpSession session,@PathVariable String email) {
	        // Retrieve the email from the session
	        //String email = (String) session.getAttribute("email");

	        // Check if the email is available in the session
	        if (email == null) {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "Session data not found. Please log in again.");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	        }

	        try {
	            // Fetch maternity leave records by email
	            List<MaternityLeave> maternityLeaves = maternityLeaveRepository.findByEmail(email);

	            // If no records found, return 204 No Content status
	            if (maternityLeaves.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No maternity leave records found.");
	            }

	            // Return the list of maternity leave records
	            return ResponseEntity.ok(maternityLeaves);
	        } catch (Exception e) {
	            // Log the error and return 500 Internal Server Error
	            System.err.println("Error fetching maternity leave records: " + e.getMessage());
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "Error fetching maternity leave records");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	        }
	    }
		
}
