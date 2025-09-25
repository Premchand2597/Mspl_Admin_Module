package com.example.mspl_connect.Sales_Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Sales_Entity.CrmCompanyDetails;
import com.example.mspl_connect.Service.PermissionService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DealsController {

	 @Autowired
	    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	    @Autowired
		private PermissionRepo permissionRepo;
		
 
	    
		@Autowired
		private EmployeeRepository employeeRepository;

		@Autowired
		private AppraisalRepository appraisalRepository;
		
		@Autowired
		private PermissionService permissionService; 
	
	
	 @GetMapping("/deals")
	    public String crmOverviewPage(HttpSession session,Model model) {
	        // Return the CRM overview page

			String email = (String) session.getAttribute("email");
		    System.out.println("user login "+email);
		    if (email == null) { // Check if session has expired
		        return "redirect:/"; // Redirect to the root mapping (login page)
		    }
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empidpp "+empId);
		    DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		    
			Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
			
			if (permissions.isPresent()) {
				// System.out.println("permissions----"+permissions);
				PermissionsEntity permissionEntity = permissions.get();
				
				
				// System.out.println("permissions----"+permissions);
							String docUploadDatestr = permissionEntity.getDoc_date();
							if(docUploadDatestr !=  null && !docUploadDatestr.isEmpty()) {
								
								LocalDate docUploadDate  = LocalDate.parse(docUploadDatestr,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
								
							    LocalDate currDate = LocalDate.now();
							    System.out.println("docUploadDate"+docUploadDate);
							    System.out.println("currDate"+currDate);
							    
							    if(currDate.isAfter(docUploadDate)) {
							    	System.out.println("Current date is greater than docUploadDate. Updating permission flag...");
							    	permissionService.updateDocUploadPermissionFlag(empId); // Call the method	
							    }
							    else {
							    	System.out.println("No update needed. Document upload date is still valid.");
							    }
							    
							}
				if (permissionEntity.isApprisalAccess()) {

					String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
					System.out.println("dueDateForAppriasal----" + dueDateForAppriasal);

					if (dueDateForAppriasal != null && !dueDateForAppriasal.isEmpty()) { // Check if the date is not null or
										 													// empty
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define date format
						LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter); // Parse due date into
										 														
						LocalDate currentDate = LocalDate.now(); // Get current date
						System.out.println("cuuurreent date "+currentDate);				
						
						if (dueDate.isBefore(currentDate)) { // Compare dates
							permissionEntity.setApprisalAccess(false); // If due date is today or earlier, set
						} else {
							permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
						} 
						
					} else {
						System.out.println("Due date for appraisal is not available."); // Log if due date is null or empty
						permissionEntity.setApprisalAccess(false); // Optionally set apprisalAccess to false if due date is
					}
				}
				System.out.println("permissionEntity in user dshboard"+permissionEntity);
				model.addAttribute("permissions", permissionEntity);
			} else {
				model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			} 	
		    
			   model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
			   model.addAttribute("empId", empId);
			   model.addAttribute("loggedInUserEmail", email);
			   // Fetch CRM details and add to model
			  //  List<CrmCompanyDetails> crmDetails = crmCompanyDetailsService.getAllCompanies();
			  //  System.out.println("pppppppppppp"+crmDetails );
			  //  model.addAttribute("crmDetails", crmDetails);

	        return "User/deals";
	    }
	 
	 
}
