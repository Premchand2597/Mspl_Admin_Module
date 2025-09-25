package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminService.EmployeeCustomDetails_Service;
import com.example.mspl_connect.DispatchEntity.DispatchEvent;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;
import com.example.mspl_connect.DispatchRepo.ProductRepository;
import com.example.mspl_connect.DispatchService.AuditService;
import com.example.mspl_connect.DispatchService.DispatchEventService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarController {
	
	@Autowired
	private AddUserRepository addUserRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private DispatchEventService eventService;
	
	@Autowired
	private EmployeeCustomDetails_Service employeeCustomDetails_Service;
	
	 @GetMapping("/calendar")
	    public String showCalendar(Model model, HttpSession session) {
		 	if (session.getAttribute("email") == null) {
		        return "redirect:/logout";
		    }
		 	model.addAttribute("distinctUserNames", employeeCustomDetails_Service.fetchAllACtiveEmployeesDistinctUserNames());
		 	model.addAttribute("products", productRepository.findAll());
	        return "dispatch/calendar"; 
	    }
	 
		@PostMapping("/submitEvent")
		public ResponseEntity<String> saveEvent(@RequestBody DispatchEvent event, HttpSession session, Model model) {
		    try {
		    	DispatchEvent savedEvent = eventService.saveEvent(event);

			    String username = (String) session.getAttribute("username");
			    if (username == null) {
			        username = "Anonymous"; 
			    }

			    auditService.logAction(
			        "INSERT",  
			        username,
			        "Calendar ID: " + savedEvent.getId() + ", Event Description: " + savedEvent.getEvent_title()
			    );
		          return ResponseEntity.ok("Successfull");
		      } catch (Exception e) {
		    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add events.");
		      }
		}
		
		@GetMapping("/getAllEventsLists")
		@ResponseBody
	    public List<DispatchEvent> fetchAllCalendarEventx() {
	        return eventService.getAllEvents();
	    }
		
		@GetMapping("/fetchEventsBasedOnDate")
		@ResponseBody
	    public List<DispatchEvent> getEventsBasedOnDate(@RequestParam String date) {
	        return eventService.getEventsBasedOnDate(date);
	    }

}
