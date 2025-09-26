package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.UserCheckin;
import com.example.mspl_connect.AdminService.UserCheckinService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserCheckinController {

	 @Autowired
	    private UserCheckinService userCheckinService;
	
		
		@Autowired
		private EmployeeRepository employeeRepository;
	
		@GetMapping("/user-checkin/{email}")
		public ResponseEntity<List<UserCheckin>> getUserCheckin(HttpSession session, @RequestParam String date, @PathVariable String email) {
		 	    
		    // Enhanced logging
		    //String email = (String) session.getAttribute("email");
		    System.out.println("user date//////////// "+date);
		    System.out.println("user login//////////// "+email);
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empid &&&&"+empId);
		    System.out.println("user empid "+date);
	    List<UserCheckin> checkinData = userCheckinService.findByEmpIdAndDate(empId, date);
	    checkinData.stream().forEach(i->System.out.println("////abcd//"+i));
	    return ResponseEntity.ok(checkinData);
	}
}
