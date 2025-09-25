package com.example.mspl_connect.AdminController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.AppraisalFromAdmin_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalFromEmployee_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;
import com.example.mspl_connect.AdminEntity.AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalNotificationAndValidationForHR_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalSubmittedCountWithEmpId_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalSubmittedNotSubmittedCount_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalTracker_Entity;
import com.example.mspl_connect.AdminEntity.Appraisal_InsertUpdatedHikedSalary_Entity;
import com.example.mspl_connect.AdminEntity.Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO;
import com.example.mspl_connect.AdminEntity.Appraisal_UserType_EmpId_CurrentFY_DTO;
import com.example.mspl_connect.AdminEntity.ApprisalHR_Entity;
import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminEntity.EmployeesEntityWithoutDocs_Entity;
import com.example.mspl_connect.AdminEntity.HikeRatings_Entity;
import com.example.mspl_connect.AdminEntity.SalaryDetailsEntity;
import com.example.mspl_connect.AdminRepo.AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Repo;
import com.example.mspl_connect.AdminRepo.AppraisalNotificationAndValidationForHR_Repo;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminService.AppraisalFromEmployee_Service;
import com.example.mspl_connect.AdminService.AppraisalNotificationAndValidationForHR_Service;
import com.example.mspl_connect.AdminService.ApprisalHR_Service;
import com.example.mspl_connect.AdminService.SalaryDetailsService;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.PermissionService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ApprisalHR_controller {
	
	@Autowired
	private ApprisalHR_Service apprisalHR_Service;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private AppraisalFromEmployee_Service appraisalFromEmployee_Service;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SalaryDetailsService salaryDetailsService;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Repo appraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Repo;

	@Autowired
	private AppraisalNotificationAndValidationForHR_Service appraisalNotificationAndValidationForHR_Service;
	
	@Autowired
	private AppraisalNotificationAndValidationForHR_Repo appraisalNotificationAndValidationForHR_Repo;
	
	/*@GetMapping("apprisalEmployee")
	public String openApprisalForm() {
		return "HR/ApprisalForm";
	}*/
	
	@PostMapping("/saveAppraisalForHR")
	@ResponseBody
	public String insertAppraisalDetails(@RequestBody List<AppraisalHrEntity> apprisalHR_Entities) {
	    boolean isSuccess = apprisalHR_Service.insertApprisalData(apprisalHR_Entities);
	    
	    for(AppraisalHrEntity ent : apprisalHR_Entities) {
	    	permissionService.updateAppraisalPermission(ent.getEmpId(), true);
	    }
	    
	    if (isSuccess) {
	    	appraisalNotificationAndValidationForHR_Repo.updateQuarterFlagAndQuarterBtnEnableFlagAfterEnablingTheAppraisalLink();
	        return "Appraisal inserted";
	    } else {
	        return "error";
	    }
	}
	
	
	 @GetMapping("/fetchAllEmployeeDetailsExceptDocuments")
	 @ResponseBody
	 public List<EmployeesEntityWithoutDocs_Entity> fetchAllEmployeeDetailsWithoutDocs(HttpSession session){
			String emailid = (String) session.getAttribute("email");
			String LoggedEmpid = employeeRepository.findEmpidByEmail(emailid);
			String adminDept = employeeRepository.findDeptNameByEmpId(LoggedEmpid);
			
		 return appraisalFromEmployee_Service.getAllEmpDetailsWithoutDocs(LoggedEmpid);
	 }
	 
	 @GetMapping("/fetchAllEmployeeDetailsExceptDocumentsSA")
	 @ResponseBody
	 public List<EmployeesEntityWithoutDocs_Entity> fetchAllEmployeeDetailsWithoutDocs(){
		 return appraisalFromEmployee_Service.getAllEmpDetailsWithoutDocs();
	 }
	
	@GetMapping("fetchAllEmployeeNames")
	@ResponseBody
	public List<EmployeeCustomDetailsForApprisal_Entity> getAllEmployeeNames(){
		return apprisalHR_Service.getAllEmployeeNames();
	}
	
	@GetMapping("fetchEmployeeDetailsUsingEmpNames")
	@ResponseBody
	public EmployeeCustomDetailsForApprisal_Entity getEmployeeDataBasedOnEmpNames(@RequestParam("emp_full_name_with_emp_id") String emp_full_name_with_emp_id){
		return apprisalHR_Service.getAllEmployeeDetailsBasedOnEmpNames(emp_full_name_with_emp_id);
	}
	
	
	
	/*@GetMapping("/fetchTeamLeadAndTeamCoEmpIdByUsingEmployeeEmailAndEmpId")
	@ResponseBody
	public TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity fetchTeamLeadAndTeamCoEmpId(@RequestParam("empId") String empId, @RequestParam("employeeEmail") String employeeEmail){
		
		TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity entity = appraisalEmailSendToAdminData_Service.fetchTeamLeadAndTeamCoEmpId(empId, employeeEmail);
		
		if((entity.getTeam_lead_id().equals(null) || entity.getTeam_lead_id().equals("")) && (entity.getTeam_co_id().equals(null) || entity.getTeam_co_id().equals(""))) {
			entity.setTeam_lead_email("");
			entity.setTeam_co_email("");
			entity.setEmployee_email(employeeEmail);
		}else {
			String teamLeadEmail = appraisalEmailSendToAdminData_Service.fetchTeamLeadEmail(entity.getTeam_lead_id());
			String teamCoEmail = appraisalEmailSendToAdminData_Service.fetchTeamCoEmail(entity.getTeam_co_id());
			entity.setTeam_lead_email(teamLeadEmail);
			entity.setTeam_co_email(teamCoEmail);
			entity.setEmployee_email(employeeEmail);
		}
		
		return entity;
	}
	
	@PostMapping("/sendEmailToAdminForWhoNotSubmittedTheAppraisal")
	@ResponseBody
	public String sendEmailToAdminForWhoNotSubmittedTheAppraisal(@RequestBody List<ApprisalEmailSendToAdminFinal_Entity> request) {
		
		if (request == null || request.isEmpty()) {
	        return "No data to send email";
	    }
		
		String result = null;
		
		for(ApprisalEmailSendToAdminFinal_Entity req: request) {
			
			String loggedInEmail = req.getLogged_in_email();
			String auto_id = req.getAuto_id();
			String empEmail = req.getEmployee_email();
			String empName = req.getEmployee_name();
			String teamLeadEmail = req.getTeam_lead_email();
			String teamCoEmail = req.getTeam_co_email();
			String department = req.getDepartment();
			String quarterMonthYear = req.getQuarter_month_year();
			
			try {
				result = apprisalEmailSendToAdminFinal_Service.sendEmailToAdminForUnSubmittedAppraisal(auto_id, loggedInEmail, empEmail, empName, teamLeadEmail, teamCoEmail, department, quarterMonthYear);
			} catch (Exception e) {
				e.printStackTrace();
				return "Failed to send email";
			}
		}
		
		return "success".equals(result) ? "Email Sent" : "No data to send email";
	}
	
	 @GetMapping("/fetchAllAppraisalDataFromEmployeesByUsingEmpId")
	    public ResponseEntity<Map<String, List<Appraisal>>> getAppraisalData(
	            @RequestParam String emp_id, @RequestParam String financialYear) {
		    
	        List<Appraisal> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalData(emp_id, financialYear);
	        List<Appraisal> employeeAppraisalData = appraisalFromEmployee_Service.getFilteredEmployeesAppraisalData(emp_id, financialYear);
	        
	        Map<String, List<Appraisal>> respondData=new HashMap<>();
	        respondData.put("appraisalData", appraisalData);
	        respondData.put("employeeAppraisalData", employeeAppraisalData);
	        
	        return ResponseEntity.ok(respondData);
	    }*/
	
	/*@GetMapping("appraisalDetailViewPage")
	public String openAppraisalDetailViewPage(@RequestParam("emp_id") String emp_id, @RequestParam("fullName") String fullName, 
			 @RequestParam("roleName") String roleName, @RequestParam("deptName") String deptName, 
			 @RequestParam("subDeptName") String subDeptName, @RequestParam("email") String email, Model model) {
			
			model.addAttribute("emp_id", emp_id);
			model.addAttribute("fullName", fullName);
			model.addAttribute("roleName", roleName);
			model.addAttribute("deptName", deptName);
			model.addAttribute("subDeptName", subDeptName);
			model.addAttribute("email", email);
			
		return "AppraisalDetailViewPage";
	}*/
	
	@GetMapping("appraisalDetailViewPage")
	public String openAppraisalDetailViewPage(@RequestParam("emp_id") String emp_id, @RequestParam("fullName") String fullName, 
			 @RequestParam("roleName") String roleName, @RequestParam("deptName") String deptName, 
			 @RequestParam("subDeptName") String subDeptName, @RequestParam("email") String email, 
			 @RequestParam("usertype") String usertype, Model model, HttpSession session) {
		
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		
			appraisalFromEmployee_Service.resetAppraisalSubmittedCount(emp_id);
			
			model.addAttribute("emp_id", emp_id);
			model.addAttribute("fullName", fullName);
			model.addAttribute("roleName", roleName);
			model.addAttribute("deptName", deptName);
			model.addAttribute("subDeptName", subDeptName);
			model.addAttribute("email", email);
			model.addAttribute("usertype", usertype);
			
			// Retrieve user details from session
	 		String loggedInEmail = (String) session.getAttribute("email");
	 		String empId = employeeRepository.findEmpidByEmail(loggedInEmail);
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
			
		return "AppraisalDetailViewPage";
	}
	
	@GetMapping("/fetchAllSalaryDetails/{empId}")
	public ResponseEntity<List<SalaryDetailsEntity>> getSalaryDetailsByEmpId(
	        @PathVariable("empId") String empId) { // Use @PathVariable
		
		System.out.println("hello");
	    List<SalaryDetailsEntity> appraisalData = salaryDetailsService.findByEmpId(empId);
	    System.out.println("......"+appraisalData);
	    return ResponseEntity.ok(appraisalData);
	}


	@GetMapping("/fetchAllAppraisalDataFromUsersByUsingEmpId")
    public ResponseEntity<List<AppraisalFromEmployee_Entity>> getAppraisalDataFromUsers(
            @RequestParam String emp_id, @RequestParam String financialYear) {
	    
		System.out.println("empid----"+emp_id);
		System.out.println("financialYear----"+financialYear);
        List<AppraisalFromEmployee_Entity> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalDataFromUsers(emp_id, financialYear);
        
        appraisalData.stream().forEach(i->System.out.println("Data === "+i));
        
        return ResponseEntity.ok(appraisalData);
        
    }

	@GetMapping("/fetchAllAppraisalDataFromAdminsByUsingEmpId")
    public ResponseEntity<List<AppraisalFromAdmin_Entity>> getAppraisalDataFromAdmin(
            @RequestParam String emp_id, @RequestParam String financialYear) {
	 
        List<AppraisalFromAdmin_Entity> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalDataFromAdmin(emp_id, financialYear);
        
        //System.out.println("Data === "+appraisalData);
        
        return ResponseEntity.ok(appraisalData);
    }
	
	@GetMapping("/fetchAppraisalSentByHRDataBasedOnEmpIdAndYearValue")
    public ResponseEntity<List<AppraisalHrEntity>> fetchAppraisalSentByHRDataBasedOnEmpIdAndYear(
            @RequestParam String emp_id, @RequestParam String financialYear) {
        List<AppraisalHrEntity> appraisalSentByHRData = apprisalHR_Service.getAppraisalSentByHRDataBasedOnEmpIdAndYear(emp_id, financialYear);
        return ResponseEntity.ok(appraisalSentByHRData);
    }
	
	@GetMapping("hikeRatingForm")
	public String openHikeRatingFormForHR(HttpSession session, Model model) {
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
	    
		return "HikeRatingHRForm";
	}
	
	@GetMapping("fetchHikeRatingsDetailsForHR")
	@ResponseBody
	public List<HikeRatings_Entity> getAllHikeRatingsDetailsForHR(){
		return apprisalHR_Service.getAllHikeRatingsdata();
	}
	
	@PostMapping("/saveHikePercentRatingsFromHR")
	public String saveHikePercentRatings(@ModelAttribute("hikeRatings_Entity") HikeRatings_Entity hikeRatings_Entity) {
	    String isSuccess = apprisalHR_Service.insertHikePercentRatings(hikeRatings_Entity);
	    
	    if (isSuccess.equals("success")) {
	        return "redirect:hikeRatingForm";
	    } else {
	        return "error";
	    }
	}
	
	@GetMapping("/fetchEmployeeDetailsForAppraisalTracker")
	 @ResponseBody
	 public List<AppraisalTracker_Entity> fetchAppraisalTrackedBasedOnClickedMonthAndYear(@RequestParam String clickedQuarterMonthYear){
		 return appraisalFromEmployee_Service.getAppraisalTrackedBasedOnClickedMonthAndYear(clickedQuarterMonthYear);
	 }
	
	 @GetMapping("apprisalEmployee")
		public String openApprisalForm(HttpSession session, Model model) {
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
		    
			return "ApprisalForm";
		}
	 
	 @GetMapping("/getAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValueDetails")
	 @ResponseBody
	 public List<Object[]> fetchAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValueDetails(@RequestParam String quarterMonthAndYear){
		 List<Object[]> results = apprisalHR_Service.fetchAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValue(quarterMonthAndYear);
		 
		 /*for (Object[] row : results) {
			    String empId = row[0].toString();
			    String flag = row[1].toString();
			    System.out.println("Emp ID: " + empId + ", Flag: " + flag);
			}*/
		 
		 return results;
	 }
	 
	 @GetMapping("/fetchAppraisalSubmittedNotSubmittedAndTotalCountDetails")
	 @ResponseBody
	 public AppraisalSubmittedNotSubmittedCount_Entity fetchAppraisalSubmittedNotSubmittedAndTotalCountValue(@RequestParam String clickedQuarterMonthYear){
		 return appraisalFromEmployee_Service.getAppraisalSubmittedNotSubmittedAndTotalCountValue(clickedQuarterMonthYear);
	 }
	 
	 @GetMapping("/fetchAppraisalSubmittedCountWithEmpIdData")
		@ResponseBody
		public List<AppraisalSubmittedCountWithEmpId_Entity> fetchAppraisalSubmittedCountWithEmpIdDetails(){
			return appraisalFromEmployee_Service.fetchAppraisalSubmittedCountWithEmpIdDetails();
		}
		
		@PostMapping("/fetchAllAppraisalDataBasedOnTheirEmpIdAndUserType")
	    public ResponseEntity<List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO>> getAllAppraisalDataBasedOnTheirEmpIdAndUserType(@RequestBody List<Appraisal_UserType_EmpId_CurrentFY_DTO> request) {
		 
			List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> allAppraisalData = new ArrayList<>();
			
			for(Appraisal_UserType_EmpId_CurrentFY_DTO req : request) {
				
				String usertype = req.getUsertype();
				String emp_id = req.getEmp_id();
				String financialYear = req.getFinancialYear();
				String hikeAffectFrom = req.getMonthAndYear();
				
				if ("Admin".equals(usertype)) {
					List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> appraisalData = 
					appraisalFromEmployee_Service.getAppraisalMaxScoreAllottedScoreOverallPercentageOverallRatingDataByAdmin(emp_id, financialYear);
					appraisalData.forEach(dto -> dto.setEmp_id(emp_id));
					appraisalData.forEach(dto -> dto.setFinancial_year(financialYear));
					appraisalData.forEach(dto -> dto.setMonthAndYear(hikeAffectFrom));
					allAppraisalData.addAll(appraisalData);				
				}else if("User".equals(usertype)) {
					List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> appraisalData = 
					appraisalFromEmployee_Service.getAppraisalMaxScoreAllottedScoreOverallPercentageOverallRatingDataByUser(emp_id, financialYear);
					appraisalData.forEach(dto -> dto.setEmp_id(emp_id));
					appraisalData.forEach(dto -> dto.setFinancial_year(financialYear));
					appraisalData.forEach(dto -> dto.setMonthAndYear(hikeAffectFrom));
					allAppraisalData.addAll(appraisalData);			
	           }
			}
			
			// Call the insert method
		    insertAppraisalDataWithOverallPercentageOfAllEmployees(allAppraisalData);
	        
	        return ResponseEntity.ok(allAppraisalData);
	    }
		
		public void insertAppraisalDataWithOverallPercentageOfAllEmployees(List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> allAppraisalData) {
		    for (Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO dto : allAppraisalData) {
		    	
		    	appraisalFromEmployee_Service.insertAllData(dto.getEmp_id(), dto.getFinancial_year(), dto.getMax_score(), dto.getAllotted_score(), dto.getOverall_percentage(), dto.getOverall_rating());
		    	
		    	//System.out.println("inserted to yearwise_overall_performance_ratings");
		    	
		    	AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity fetchedUpadtedHikedSalaryData = 
		    		appraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Repo.getAllAppraisalDataWithUpdatedHikeSalary(dto.getEmp_id(), dto.getFinancial_year());
		    	
		    	//System.out.println("fetched all getAllAppraisalDataWithUpdatedHikeSalary");
		    	
		    	appraisalFromEmployee_Service.insertAllUpdatedSalaryHikeDetails(fetchedUpadtedHikedSalaryData.getEmp_id(),
		    			fetchedUpadtedHikedSalaryData.getFinancial_year(), fetchedUpadtedHikedSalaryData.getEmp_name(), 
		    			fetchedUpadtedHikedSalaryData.getOverall_rating(), fetchedUpadtedHikedSalaryData.getCurrent_salary(), 
		    			fetchedUpadtedHikedSalaryData.getHike_percentage(), fetchedUpadtedHikedSalaryData.getSalary_after_hike(), 
		    			fetchedUpadtedHikedSalaryData.getRemarks(), dto.getMonthAndYear());
		    	
		    	//System.out.println("inserted to salary_details");
		    	
		    	employeeRepository.updateSalaryAfterHiking(fetchedUpadtedHikedSalaryData.getEmp_id(), fetchedUpadtedHikedSalaryData.getSalary_after_hike());
		    
		    	//System.out.println("updated to salary_details in employee details");
		    }
		}
		
		@GetMapping("fetchHikedSalaryBasedOnEmpId")
		@ResponseBody
		public List<Appraisal_InsertUpdatedHikedSalary_Entity> getAllHikeRatingsDetailsForHR(@RequestParam String emp_id){
			return appraisalFromEmployee_Service.getUpdatedSalaryAfterHikeBasedOnEmpId(emp_id);
		}
		
		@GetMapping("/checkAppraisalNotificationAndValidationForHR")
		@ResponseBody
		public List<AppraisalNotificationAndValidationForHR_Entity> checkData() {
			return appraisalNotificationAndValidationForHR_Service.fetchAllData();
		}
		
		
		@PostMapping("/updateQuarterFlagAndQuarterBtnEnableFlagForHR")
	 	public ResponseEntity<String> updateQuarterFlagAndQuarterBtnEnableFlagValue(@RequestParam("auto_id") String auto_id) throws IOException {

			boolean isUpdated = appraisalNotificationAndValidationForHR_Service.updateQuarterFlagAndQuarterBtnEnableFlagValues(auto_id);
			
			if (isUpdated) {
			    return ResponseEntity.ok("Appraisal notification value is updated");
			} else {
			    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Appraisal notification value is not updated");
			}
	 	}
		
		@PostMapping("/updateQuarterFlagAndQuarterBtnEnableFlagForHRAfterSubmittingAppraisalDetails")
	 	public ResponseEntity<String> updateQuarterFlagAndQuarterBtnEnableFlagAfterInsertingAppraisalDetailsValues(@RequestParam("currYear") String currYear, @RequestParam("auto_id") String auto_id) throws IOException {

			boolean isUpdated = appraisalNotificationAndValidationForHR_Service.updateQuarterFlagAndQuarterBtnEnableFlagAfterInsertingAppraisalDetails(currYear, auto_id);
			
			if (isUpdated) {
			    return ResponseEntity.ok("Appraisal sent year value is updated");
			} else {
			    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Appraisal sent year value is not updated");
			}
	 	}
		
		@GetMapping("/fetchHikeRatingsDetailsPresentBasedOnFinancialYear")
		@ResponseBody
		public boolean isHikeRatingsDetailsPresentBasedOnFinancialYear(@RequestParam String financialYear){
			return apprisalHR_Service.getHikeRatingsdataBasedOnFinancialYearIsPresent(financialYear);
		}
	
}
