package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.InterCommDisplay_Entity;
import com.example.mspl_connect.AdminEntity.InterComm_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.Inter_Comm_Repo;
import com.example.mspl_connect.AdminService.InterComm_Service;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class InterCommMaintain_Controller {
	
	@Autowired
	private InterComm_Service interComm_Service;
	
	@Autowired
	private Inter_Comm_Repo inter_Comm_Repo;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private com.example.mspl_connect.AdminRepo.InterCommDisplay_Repo InterCommDisplay_Repo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	@GetMapping("/addInterComm")
	public String getInterCommForm(Model model, HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
	    
	 // Retrieve user details from session
 		String email = (String) session.getAttribute("email");
 		String empId = employeeRepository.findEmpidByEmail(email);
 		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
 		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

 		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

 		if (permissions.isPresent()) {
 			PermissionsEntity permissionEntity = permissions.get();
 			if (permissionEntity.isApprisalAccess()) {
 				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
 				if (dueDateForAppriasal == null) {
 					// No data found, set apprisalAccess to false
 					permissionEntity.setApprisalAccess(false);
 				} else {
 					// Define date format
 					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 					try {
 						// Parse due date into LocalDate
 						LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter);
 						LocalDate currentDate = LocalDate.now(); // Get current date

 						// Compare dates
 						if (dueDate.isBefore(currentDate)) {
 							// If due date is today or earlier, set apprisalAccess to false
 							permissionEntity.setApprisalAccess(false);
 						} else {
 							// If due date is in the future, set apprisalAccess to true
 							permissionEntity.setApprisalAccess(true);
 						}
 					} catch (DateTimeParseException e) {
 						// Handle invalid date format gracefully (if necessary)
 						permissionEntity.setApprisalAccess(false);
 					}
 				}
 			}
 			model.addAttribute("permissions", permissionEntity);
 		} else {
 			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
 		}

 		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
 		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	    
		model.addAttribute("interCommList", new InterComm_Entity());
		return "InterCommForm";
	}
	
	
	@PostMapping("/saveInterCommList")
	 public String insertInterCommDetails(@ModelAttribute("interCommList") InterComm_Entity interCommList, Model model, HttpSession session) {
		 	
		 String result = interComm_Service.save(interCommList);
		  
		 if("success".equals(result)) {
			 return "redirect:addInterComm";
		 }else {
			 return "error";
		 }
	 }
	
	@GetMapping("/interCommViewForSA")
	@ResponseBody
	public List<InterComm_Entity> getInterCommData() {
		List<InterComm_Entity> interCommLists = interComm_Service.interCommList();
		return interCommLists;
	}
	
	@GetMapping("/interCommViewBasedOnActiveEmployees")
	@ResponseBody
	public List<InterCommDisplay_Entity> getInterCommActiveEmpData() {
		List<InterCommDisplay_Entity> interCommLists = InterCommDisplay_Repo.fetchAllInterCommDataBasedOnActiveEmployees();
		return interCommLists;
	}
	
	@GetMapping("/fetchInterCommDataToEdit")
	@ResponseBody
	public InterComm_Entity editInterCommData(@RequestParam("auto_id") String auto_id){
		return interComm_Service.fetchInterCommDataForEdit(auto_id);
	}
	
	
	@PostMapping("/updateInterCommData")
    public ResponseEntity<String> updateInterComData(@RequestParam("id") int id, @RequestParam("username") String username, 
    		@RequestParam("cell_number") String cell_number, @RequestParam("mail_id") String mail_id, @RequestParam("tele_number") String tele_number,
    		@RequestParam("cubical_number") String cubical_number, @RequestParam("seat_place") String seat_place,
    		@RequestParam("floor_number") String floor_number, @RequestParam("room_number") String room_number) {
		
		//System.out.println("id=== "+id);
		
		String result = interComm_Service.updateInterCommData(id, username, cell_number, mail_id, tele_number, cubical_number, seat_place, floor_number, room_number);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("InterComm details updated successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update InterComm details.");
	    }
	}
	
	@PostMapping("/deleteInterCommById")
    public ResponseEntity<String> deleteInterCommData(@RequestParam int id) {
		System.out.println("id== "+id);
		String result = interComm_Service.deleteInterCommData(id);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("InterComm detail deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete InterComm detail.");
	    }
	}
	
}
