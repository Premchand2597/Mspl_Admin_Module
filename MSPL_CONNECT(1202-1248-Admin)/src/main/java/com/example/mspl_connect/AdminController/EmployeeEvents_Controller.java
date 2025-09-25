package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.AdminEntity.EmployeeEvent;
import com.example.mspl_connect.AdminService.EmployeeEventService;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeEvents_Controller {

	    @Autowired
	    private EmployeeEventService employeeEventService;
	
	    @Autowired
	    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
		
		@Autowired
		private EmployeeRepository employeeRepository;
	 

		@GetMapping("/EmployeeEvents")
		public String getEventsPage(Model model, HttpSession session) {
		    try { 
		    	
		        String email = (String) session.getAttribute("email");
		        String empId = employeeRepository.findEmpidByEmail(email);
		        List<EmployeeEvent> events = employeeEventService.getAllEvents();
		        
		        // Make sure the events list has properly encoded images and videos
		        events.forEach(event -> {
		        	event.setEventConvertedPic(event.getEventConvertedPic());
		            event.setEventVideo(event.getEventVideo());
		        });
		        
		        DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
		        
		        model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		        model.addAttribute("loggedInUserEmail", email);
		        model.addAttribute("events", events);
		        return "User/Events";
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "error"; 
		    }
		}
}
