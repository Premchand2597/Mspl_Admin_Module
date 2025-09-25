package com.example.mspl_connect.AdminController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.Releasenote;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.ReleaseNoteRepo;
import com.example.mspl_connect.AdminService.FeatureUpdatesService;
import com.example.mspl_connect.AdminService.ReleaseNoteService;
import com.example.mspl_connect.AdminService.ToDoListService;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ReleasenoteController {


	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
    private ReleaseNoteRepo releaseNoteRepository;
	
	/*
	 * @Autowired private NotificationCount_Service notificationCount_Service;
	 */
	
    @Autowired
    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	 @Autowired
	 private ToDoListService todolistService;
	
	 @Autowired
	 private ReleaseNoteService releaseNoteService;
	 
	 @Autowired
	 private AppraisalRepository appraisalRepository;
	 
	 @Autowired
	 private FeatureUpdatesService featureUpdatesService; 
	    
	
	 @GetMapping("/releasenotes")
	    public String myFavorites(HttpSession session, Model model) {
		  // Retrieve user details from session
		    String email = (String) session.getAttribute("email");
		    System.out.println("user login "+email);
		    if (email == null) { // Check if session has expired
		        return "redirect:/"; // Redirect to the root mapping (login page)
		    }
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empid "+empId);
		    
		   // notificationCount_Service.markFeatureUpdatesAsSeen(empId);
		    
		 DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		 
		   Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		    System.out.println("====%%%%"+permissions);
		    if (permissions.isPresent()) {
				// System.out.println("permissions----"+permissions);
				PermissionsEntity permissionEntity = permissions.get();
				if (permissionEntity.isApprisalAccess()) {

					String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
					System.out.println("dueDateForAppriasal----" + dueDateForAppriasal);

					if (dueDateForAppriasal != null && !dueDateForAppriasal.isEmpty()) { // Check if the date is not null or
																							// empty
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define date format
						LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter); // Parse due date into
																								
						LocalDate currentDate = LocalDate.now(); // Get current date

						if (dueDate.isBefore(currentDate)) { // Compare dates
							permissionEntity.setApprisalAccess(false); // If due date is today or earlier, set
																		// apprisalAccess to false
						} else {
							permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
																		// true
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
		    
		    // Fetch the latest release note document for the given module (e.g., "User")
		    Optional<Releasenote> releaseNote = releaseNoteService.getLatestDocumentByModule("Admin");

		    if (releaseNote.isPresent()) { 
		    	
		        // Get the document details
		        Releasenote note = releaseNote.get();
		        model.addAttribute("documentPath", note.getDocumentPath());
		        model.addAttribute("versionNumber", note.getVersionNumber());
		        model.addAttribute("module", note.getModule());
		        //System.out.println("documentPath///// "+ note.getDocumentPath() );
		        
		    }
		    
		    //make new feature flag value 0 after visit the page
		    releaseNoteService.updateReleaseNoteFlagValue(empId);
		
		   model.addAttribute("loggedInUserEmail", email);
		   model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		   model.addAttribute("empId", empId);

		   return "User/Releasenote";
	    
	 }
	 
	 @GetMapping("fetchReleaseDocumentsByClickedTabModule")
		@ResponseBody
		 public List<Releasenote> getReleaseDocumentsBasedOnClickedModuleData(@RequestParam String module_name){
			 return releaseNoteRepository.fetchReleaseDocumentsBasedOnClickedModule(module_name);
		 }

}
