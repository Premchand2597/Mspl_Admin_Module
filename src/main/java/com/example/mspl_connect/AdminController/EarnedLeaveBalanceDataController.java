package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminService.EarnedLeaveBalanceDataService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EarnedLeaveBalanceDataController {

	 @Autowired
	    private EarnedLeaveBalanceDataService service;


		@Autowired
		private EmployeeRepository employeeRepository;
		
	
		@GetMapping("/last-increment-date")
		public ResponseEntity<List<String>> getLastIncrementDates(@RequestParam String email) {
		    //String email = (String) session.getAttribute("email");
		    System.out.println("User loginfff: " + email);

		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("User empid: " + empId);

		    List<String> incrementDates = service.getAllIncrementDates(empId);
		    System.out.println("datesssssssss " + incrementDates );
		    return ResponseEntity.ok(incrementDates);
		}

}
