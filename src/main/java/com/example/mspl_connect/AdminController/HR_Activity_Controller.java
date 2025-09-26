package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HR_Activity_Controller {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;

	@GetMapping("/hrActivity")
	public String openHrActivityPage(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "message", required = false) String message, HttpSession session) {
		
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
	
		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		 //List<EmployeeDetailsEntity> employees = detaisService.getEmployee();
		 List<DisplayEmployessEntity> employeeWithDeptName = detaisService.getEmployeeWithDeptName();
		 
		// in controller or service method
		 employeeWithDeptName.forEach(emp -> {
		     if (emp.getProfilePicPath() != null) {
		         emp.setProfilePicBase64(emp.getProfilePicPath());
		         //System.out.println("0000000000 "+Base64.getEncoder().encodeToString(emp.getProfile_pic_path()));
		     }
		 });
		 
		 
		 //List<EmployeeOnlyDocument_Entity> employeePics = detaisService.selectEmpDetailsForOnlyDocumentsView();
		 
		 //employeeWithDeptName.stream().forEach(emp->System.out.println("/////////="+emp));
		 if (error != null) {
	         model.addAttribute("error", error);	
	     }
	     if (message != null) {
	         model.addAttribute("message", message);
	     }
	     
	     String email = (String) session.getAttribute("email");
	     String empId = employeeRepository.findEmpidByEmail(email);
	     DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
	     
	     	// Retrieve user details from session
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

	    // System.out.println("permissions== "+permissions);
			
	     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	     model.addAttribute("departments", departments);
	     model.addAttribute("employeeWithDeptName",employeeWithDeptName);
	     //model.addAttribute("employeePics",employeePics);
		
		return "HRActivity";
	}
}
