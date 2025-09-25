package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

import com.example.mspl_connect.AdminEntity.AddDynamicDropDownDataInMoatContactList_Entity;
import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;
import com.example.mspl_connect.AdminEntity.CardCountBasedOnInterviewData_Entity;
import com.example.mspl_connect.AdminEntity.CardCountBasedOnModeOfContactData_Entity;
import com.example.mspl_connect.AdminEntity.LinkGeneratedTableEntity;
import com.example.mspl_connect.AdminEntity.MarkDetails_Entity;
import com.example.mspl_connect.AdminEntity.MoatCandidateDataWithTestTypeStatus_Entity;
import com.example.mspl_connect.AdminEntity.MoatCandidate_Entity;
import com.example.mspl_connect.AdminEntity.MoatCandidatesCount_Entity;
import com.example.mspl_connect.AdminEntity.ModeOfContactNewCandidates_Entity;
import com.example.mspl_connect.AdminEntity.QuizResponse_Entity;
import com.example.mspl_connect.AdminEntity.StudentApproveCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentOnHoldCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentPendingCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentRejectCount_Entity;
import com.example.mspl_connect.AdminEntity.StudentResult_Entity;
import com.example.mspl_connect.AdminEntity.StudentTotalCount_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.CardCountBasedOnInterviewData_Repo;
import com.example.mspl_connect.AdminRepo.CardCountBasedOnModeOfContactData_Repo;
import com.example.mspl_connect.AdminService.AddDynamicDropDownDataInMoatContactList_Service;
import com.example.mspl_connect.AdminService.AddQuestions_Service;
import com.example.mspl_connect.AdminService.ApproveStudentStatus_Service;
import com.example.mspl_connect.AdminService.DeleteQuestions_Service;
import com.example.mspl_connect.AdminService.EditQuestions_Service;
import com.example.mspl_connect.AdminService.FetchStudentResult_Service;
import com.example.mspl_connect.AdminService.LinkGenerator_Service;
import com.example.mspl_connect.AdminService.MarkDetails_Service;
import com.example.mspl_connect.AdminService.MoatCandidateCounts_Service;
import com.example.mspl_connect.AdminService.MoatCandidate_Service;
import com.example.mspl_connect.AdminService.MoatLogin_Service;
import com.example.mspl_connect.AdminService.ModeOfContactNewCandidates_Service;
import com.example.mspl_connect.AdminService.SendEmail_Service;
import com.example.mspl_connect.AdminService.StudentService;
import com.example.mspl_connect.AdminService.ViewQuestions_Service;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MoatDetailsForHR_Controller {
	
	@Autowired
	private MoatCandidate_Service moatCandidate_Service;
	
	@Autowired
	private MoatCandidateCounts_Service moatCandidateCounts_Service;
	
	@Autowired
	private MoatLogin_Service moatLogin_Service;
	
	@Autowired
	private LinkGenerator_Service linkGenerator_Service;
	
	@Autowired
	private SendEmail_Service sendEmail_Service;
	
	@Autowired
	private FetchStudentResult_Service fetchStudentResult_Service;
	
	@Autowired
	private MarkDetails_Service markDetails_Service;
	
	@Autowired
	private ApproveStudentStatus_Service approveStudentStatus_Service;
	
	@Autowired
	private AddQuestions_Service addQuestions_Service;
	
	@Autowired
	private ViewQuestions_Service viewQuestions_Service;
	
	@Autowired
	private EditQuestions_Service editQuestions_Service;
	
	@Autowired
	private DeleteQuestions_Service deleteQuestions_Service;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private ModeOfContactNewCandidates_Service modeOfContactNewCandidates_Service;
	
	@Autowired
	private AddDynamicDropDownDataInMoatContactList_Service addDynamicDropDownDataInMoatContactList_Service;
	
	@Autowired
	private CardCountBasedOnInterviewData_Repo
	cardCountBasedOnInterviewData_Repo;
	
	@Autowired
	private CardCountBasedOnModeOfContactData_Repo cardCountBasedOnModeOfContactData_Repo;
	
    @GetMapping("/linkGeneratorForm")
    public String encryptPage() {
        return "EncryptLoginPage";
    }

    @GetMapping("melange-systems/bangalore")
    public String decryptPage1(@RequestParam("token") String token, 
    		@RequestParam("encrypted_data") String encrypted_data, @RequestParam("candidate_email") String candidate_email,
    		@RequestParam("test_duration") String test_duration, @RequestParam("supervisor_name") String supervisor_name, Model model) {
        
    	//tokenData tokenData = tokenDataStore.get(token);
        
		/*
		 * if (tokenData != null) { LocalDateTime now = LocalDateTime.now();
		 * 
		 * // If link was accessed for the first time, set expiration time if
		 * (tokenData.getFirstAccessTime() == null) { tokenData.setFirstAccessTime(now);
		 * tokenData.setExpirationTime(now.plusMinutes(5)); // Set expiration time to 5
		 * minute after first access }
		 * 
		 * // Check if the link has expired if
		 * (now.isBefore(tokenData.getExpirationTime())) {
		 * model.addAttribute("encryptData", tokenData.getEncryptedData()); } else {
		 * model.addAttribute("encryptData", "Link expired");
		 * tokenDataStore.remove(token); // Optionally remove the expired token } } else
		 * { model.addAttribute("encryptData", "Data not found"); }
		 */
        
        model.addAttribute("encryptData", encrypted_data);
        model.addAttribute("candidate_email", candidate_email);
        model.addAttribute("test_duration", test_duration);
        model.addAttribute("supervisor_name", supervisor_name);
        return "MoatDecryptedData";
    }


    @PostMapping("/generateToken")
    @ResponseBody
    public String generateToken(@RequestBody List<String> encryptedData) {
        String token = UUID.randomUUID().toString();  // Generate a unique token
        
        //System.out.println("simple token = "+token);
        //System.out.println("Hashmap token = "+new TokenData(encryptedData));
        
        // Store the encrypted data with no expiration time initially
        // tokenDataStore.put(token, new TokenData(encryptedData));
        
        // Iterate over the tokenDataStore to print all entries
      /*  System.out.println("---- Stored Tokens ----");
        for (Map.Entry<String, TokenData> entry : tokenDataStore.entrySet()) {
            System.out.println("Token: " + entry.getKey() + ", Data: " + entry.getValue());
        } */
        
        return token;
    }
    
    
   /* @PostMapping("/insertLink")
    @ResponseBody
    public ResponseEntity<String> insertLinkToTable(@RequestParam("url") String url, @RequestParam("candidate_email") String candidate_email, 
    				@RequestParam("test_duration") String test_duration, @RequestParam("supervisor_name") String supervisor_name, 
    				@RequestParam("test_type") String test_type, @RequestParam("encrypted_data") String encrypted_data) {
        String result = linkGenerator_Service.sendTODB(url, candidate_email, test_duration, supervisor_name, test_type, encrypted_data);
        return ResponseEntity.ok(result);
    }*/
    
    
    @PostMapping("/insertLink")
    @ResponseBody
    public ResponseEntity<String> insertLinkToTable(
        @RequestParam("url") String url,
        @RequestParam("candidate_emails") String candidateEmails, // Comma-separated list
        @RequestParam("test_duration") String testDuration,
        @RequestParam("supervisor_name") String supervisorName,
        @RequestParam("test_type") String testType,
        @RequestParam("dept_name") String dept_name,
        @RequestParam("encrypted_data") String encryptedData) throws Exception {
        
        String[] emailList = candidateEmails.split(","); // Convert to array
        //String[] combinedDataList = encryptedData.split(",");
        
        //System.out.println(emailList);
        
        for (String email : emailList) {
            // Insert each email with the other details
            linkGenerator_Service.sendTODB(url, email.trim(), testDuration, supervisorName, testType, encryptedData, dept_name);
            //sendEmail_Service.sendEmailWithLink(email.trim(), "http://192.168.1.66:8084/moatUserRegister");
            sendEmail_Service.sendEmailWithLink(email.trim(), "https://www.msplrfdcu.com:9377/moatUserRegister");
        }

        return ResponseEntity.ok("success");
    }

    
    @GetMapping("/getAllLinkGeneratedData")
    @ResponseBody
    public List<LinkGeneratedTableEntity> fetchAllLinkGeneratedData() {
        return linkGenerator_Service.getAllLinkGeneratedData();
    }	
	
	@GetMapping("/totalCandidatesList")
	public String allCandidatePage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		return "HR/TotalCandidates";
	}
	
	@GetMapping("/selectedCandidatesList")
	public String selectedCandidatePage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		return "HR/SelectedCandidates";
	}
	
	@GetMapping("/rejectedCandidatesList")
	public String rejectedCandidatePage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		return "HR/RejectedCandidates";
	}
	
	@GetMapping("fetchCandidatesAllData")
	@ResponseBody
	public List<MoatCandidate_Entity> getCandidatesAllData() {
		return moatCandidate_Service.getStudentData();
	}

	@GetMapping("fetchSelectedCandidatesAllData")
	@ResponseBody
	public List<MoatCandidate_Entity> getSelectedCandidatesAllData() {
		return moatCandidate_Service.getSelectedCandidateData();
	}
	
	@GetMapping("fetchRejectedCandidatesAllData")
	@ResponseBody
	public List<MoatCandidate_Entity> getRejectedCandidatesAllData() {
		return moatCandidate_Service.getRejectedCandidateData();
	}
	
	@GetMapping("fetchCandidatesDataUsingId")
	@ResponseBody
	public MoatCandidate_Entity getCandidatesAllDataById(@RequestParam("candidate_id") String candidate_id) {
		//System.out.println("idddd=== "+candidate_id);
		return moatCandidate_Service.getCandidateDataById(candidate_id);
	}
	
	@GetMapping("/selectedCandidatesListById")
	public String selectedCandidatePageById(@RequestParam("candidate_id") String candidate_id, Model model, HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		moatCandidate_Service.update_candidate_flagData(candidate_id);
		model.addAttribute("candidate_id", candidate_id);
		return "HR/SelectedCandidateProfilePage";
	}
	
	@GetMapping("/rejectedCandidatesListById")
	public String rejectedCandidatePageById(@RequestParam("candidate_id") String candidate_id, Model model, HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		moatCandidate_Service.update_candidate_flagData(candidate_id);
		model.addAttribute("candidate_id", candidate_id);
		return "HR/RejectedCandidateProfilePage";
	}
	
	@GetMapping("/totalCandidatesListById")
	public String totalCandidatePageById(@RequestParam("candidate_id") String candidate_id, @RequestParam("candidateFlagValue") String candidateFlagValue, Model model, HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		//System.out.println(candidate_id+" "+candidateFlagValue);
		/*int flagValue = Integer.parseInt(candidateFlagValue); // Convert string to integer
	    if(flagValue == 1) { // Compare with integer
	        moatCandidate_Service.update_candidate_flagData(candidate_id);
	    }*/
	    moatCandidate_Service.update_candidate_flagData(candidate_id);
		model.addAttribute("candidate_id", candidate_id);
		return "HR/TotalCandidateProfilePage";
	}
	
	@GetMapping("fetchMoatTotalAttendedTotalSelectedTotalRejectedCandidatesCounts")
	@ResponseBody
	public MoatCandidatesCount_Entity countMoatTotalAttendedTotalSelectedTotalRejectedCandidatesDetails() {
		return moatCandidateCounts_Service.countMoatTotalAttendedTotalSelectedTotalRejectedCandidatesList();
	}
	
	
	@GetMapping("/interview")
	 public String interviewPage(HttpSession session,Model model) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
	    
	 // Retrieve user details from session
	 		String email = (String) session.getAttribute("email");
	 		String empId = employeeRepository.findEmpidByEmail(email);
	    
	    Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();

			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define date format
				LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter); // Parse due date into LocalDate
				LocalDate currentDate = LocalDate.now(); // Get current date

				if (dueDate.isBefore(currentDate)) { // Compare dates
					permissionEntity.setApprisalAccess(false);// If due date is today or earlier, set apprisalAccess to
																// false
				} else {
					permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
				}
			}
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}
		 return "MoatAdmin/InterviewPage";
	 }
	 
	 @PostMapping("/postLogin")
	    public String submitLogin(String email, String password, String loggedInEmail, HttpServletRequest request, HttpSession session) {
			//System.out.println("username== "+username);
			//System.out.println("password== "+password);
		 if(loggedInEmail.equals(email)) {
			 if (moatLogin_Service.authenticate(email, password)) {
		        	//session.setAttribute("email", email);
		        	// Store current date and time in session
		            //LocalDateTime currentTime = LocalDateTime.now();
		            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		            // String formattedTime = currentTime.format(formatter);
		            //session.setAttribute("loginTime", formattedTime);
		            //System.out.println("loginTime ++++ "+formattedTime);
				    // Safely check if the session attribute is null
				    
				    if (session.getAttribute("empDetailsByEmpId") == null) {
				        return "redirect:/";
				    }
		            return "redirect:/moatIframe";
		        } else {
		        	request.setAttribute("error", "Invalid Credential");
		        	// Safely check if the session attribute is null
		    	    if (session.getAttribute("empDetailsByEmpId") == null) {
		    	        return "redirect:/";
		    	    }
		            return "MoatAdmin/InterviewPage"; 
		        }
		 } else {
			 	request.setAttribute("error", "You should enter your own authenticated email ID");
			 // Safely check if the session attribute is null
			    if (session.getAttribute("empDetailsByEmpId") == null) {
			        return "redirect:/";
			    }
	            return "MoatAdmin/InterviewPage";
		 }
	        
	    }
	 
	 @GetMapping("/moatIframe")
	 public String moatIframePage(HttpSession session,Model model) {
		    
		    // Safely check if the session attribute is null
		    if (session.getAttribute("empDetailsByEmpId") == null) {
		        return "redirect:/";
		    }
	    
	        // Retrieve user details from session
	 		String email = (String) session.getAttribute("email");
	 		String empId = employeeRepository.findEmpidByEmail(email);
	 		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

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
						} else {
							permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
						}
					} else {
						System.out.println("Due date for appraisal is not available."); // Log if due date is null or empty
						permissionEntity.setApprisalAccess(false); // Optionally set apprisalAccess to false if due date is
					}
				}
				model.addAttribute("permissions", permissionEntity);
			} else {
				model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			} 	
	 		
	 		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
	 		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	 		
		    return "MoatAdmin/MoatIndexPage";
	 }
	 
	 @GetMapping("/moatDashboard")
	 public String moatDashboardPage(HttpSession session) {
		// Safely check if the session attribute is null
		    if (session.getAttribute("empDetailsByEmpId") == null) {
		        return "redirect:/";
		    }
		 return "MoatAdmin/MoatDashboardPage";
	 }
	 
	 @GetMapping("/moatTestLinkArchiveHistory")
	 public String moatTestLinkArchivePage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		 return "MoatAdmin/MoatTestLinkArchivePage";
	 }
	 
	@GetMapping("fetchStudentAllData")
	@ResponseBody
	public List<MoatCandidate_Entity> getStudentAllData() {
		return moatCandidate_Service.getStudentData();
	}
	
	@GetMapping("/fetchStudentAllDataBasedOnAction")
	public ResponseEntity<List<MoatCandidate_Entity>> fetchStudentAllDataBasedOnAction(@RequestParam(value = "action", required = false) String action,HttpSession session) {
	    
		System.out.println("action...."+action);
		
		if (action == null || action.isEmpty()) {
	        action = null; // Explicitly set null for clarity
	    }
		System.out.println("fetchStudentAllDataBasedOnAction 1");
		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		
	    System.out.println("Action received::::: " + action);
	    List<MoatCandidate_Entity> students = moatCandidate_Service.getStudentDataBasedOnAction(action,adminDeptName,adminDept);
	    return ResponseEntity.ok(students);
	    
	}

	
	/*@GetMapping("/fetchCard1Count")
	@ResponseBody
   public List<StudentTotalCount_Entity> fetchTotalStudentCount(HttpSession session) {
	   
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   
       return moatCandidate_Service.getTotalStudentCount(adminDeptName);
   }*/
	
	@GetMapping("/fetchCard1Count")
	@ResponseBody
   public List<StudentTotalCount_Entity> fetchTotalStudentCount(HttpSession session) {
	   
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   
	   List<StudentTotalCount_Entity> totalCandidates = moatCandidate_Service.getTotalStudentCount(adminDeptName,adminDept);
	   
	   System.out.println("totalCandidates---"+totalCandidates);
	   
       return totalCandidates; 
       
   }
    
   @GetMapping("/fetchCard2Count")
   @ResponseBody
   public List<StudentApproveCount_Entity> fetchTotalApproveCount(HttpSession session) {
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   
       return moatCandidate_Service.getTotalApproveCount(adminDeptName,adminDept);
   }
   
   @GetMapping("/fetchCard3Count")
	@ResponseBody
   public List<StudentRejectCount_Entity> fetchTotalRejectCount(HttpSession session) {
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   
       return moatCandidate_Service.getTotalRejectCount(adminDeptName,adminDept);
   }
   
   @GetMapping("/fetchCard4Count")
	@ResponseBody
   public List<StudentPendingCount_Entity> fetchTotalPendingCount(HttpSession session) {
	   
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   
       return moatCandidate_Service.getTotalPendingCount(adminDeptName,adminDept);
   }
   
   @GetMapping("/fetchCard5Count")
	@ResponseBody
   public List<StudentOnHoldCount_Entity> fetchTotalOnHoldCount(HttpSession session) {
	   
	   // Retrieve user details from session
	   String email = (String) session.getAttribute("email");
	   String empId = employeeRepository.findEmpidByEmail(email);
	   int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	   String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	   System.out.println("controlller valueeee==="+moatCandidate_Service.getTotalOnHoldCount(adminDeptName,adminDept));
       return moatCandidate_Service.getTotalOnHoldCount(adminDeptName,adminDept);
       
   }
   
   @GetMapping("/moatTestLinkGenerator")
	 public String moatTestLinkGeneratorPage(HttpSession session, Model model) {
	   // Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
	    
	    List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
	    
	     model.addAttribute("departments", departments);
		 return "MoatAdmin/MoatTestLinkGeneratorPage";
	 }
   
   @GetMapping("/moatAddQues")
	 public String moatAddQuestionPage(HttpSession session) {
	   // Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		 return "MoatAdmin/MoatAddQuestionsPage";
	 }
   
   @GetMapping("/moatViewQues")
	 public String moatViewQuestionPage(HttpSession session) {
	   // Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		 return "MoatAdmin/MoatViewQuestionsPage";
	 }
   
   @GetMapping("/moatResultSheet")
	 public String moatResultSheetPage(@RequestParam("student_id") String student_id, @RequestParam("testStatusFlag") String testStatusFlag, Model model, HttpSession session) {
	   // Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		 //System.out.println("Student id== "+student_id+" testStatusFlag == "+testStatusFlag);
	    if(testStatusFlag.equals("2")) {
	    	studentService.resetTestStatusFlag(student_id);
	    }
		 model.addAttribute("student_id", student_id);
		 session.setAttribute("student_id", student_id);
		 return "MoatAdmin/MoatResultSheetPage";
	 }
   
   @GetMapping("fetchStudentDataFromMoatById")
	@ResponseBody
	public MoatCandidate_Entity getStudentAllDataById(@RequestParam("student_id") String student_id) {
		return moatCandidate_Service.getCandidateDataById(student_id);
	}
   
   
   @GetMapping("fetchStudentResultById")
	@ResponseBody
	public List<StudentResult_Entity> fetchStudentResult(@RequestParam("student_id") String student_id){
		//System.out.println("student_id === "+student_id);
		//System.out.println(fetchStudentResult_Service.getStudentResult(student_id));
		return fetchStudentResult_Service.getStudentResult(student_id);
	}
	
	
	@GetMapping("fetchStudentMarksById")
	@ResponseBody
	public List<MarkDetails_Entity> fetchStudentMarks(@RequestParam("student_id") String student_id){
		//System.out.println("student_id === "+student_id);
		//System.out.println(markDetails_Service.getMarkDetails(student_id));
		return markDetails_Service.getMarkDetails(student_id);
	}
	
	
	@PostMapping("/changeApproveStatus")
	public ResponseEntity<String> toggleApproveStatus(@RequestParam String action_satus, @RequestParam String id, @RequestParam String candidate_action_reason) {
	    try {
	        //System.out.println("Action== " + action_satus + " , Id== " + id);
	        boolean isStatusChanged = approveStudentStatus_Service.setActionStatus(action_satus, id, candidate_action_reason);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing Candidate Status. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing Candidate Status. Please try again.");
	    }
	}
	
	@PostMapping("/updateTechnicalMark")
	public ResponseEntity<String> updateTechnicalMarks(@RequestParam String student_id, @RequestParam String question_id, @RequestParam String technicalMarkValue) {
	    try {
	        //System.out.println("Action== " + action_satus + " , Id== " + id);
	        boolean isStatusChanged = fetchStudentResult_Service.updateTechnicalMarksValue(student_id, question_id, technicalMarkValue);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	    }
	}
	
	@PostMapping("/updateF2FMark")
	public ResponseEntity<String> updateF2FMarks(@RequestParam String student_id, @RequestParam String f2f_total_marks, @RequestParam String f2f_assigned_marks, @RequestParam String f2f_remarks) {
	    try {
	        //System.out.println("Action== " + action_satus + " , Id== " + id);
	        boolean isStatusChanged = approveStudentStatus_Service.setF2FMarks(student_id, f2f_total_marks, f2f_assigned_marks, f2f_remarks);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	    }
	}
	
	@PostMapping("/updateHRMark")
	public ResponseEntity<String> updateHRMarks(@RequestParam String student_id, @RequestParam String hr_total_marks, @RequestParam String hr_assigned_marks, @RequestParam String hr_remarks) {
	    try {
	        //System.out.println("Action== " + action_satus + " , Id== " + id);
	        boolean isStatusChanged = approveStudentStatus_Service.setHRMarks(student_id, hr_total_marks, hr_assigned_marks, hr_remarks);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Technical Marks. Please try again.");
	    }
	}
	
	@GetMapping("fetchAssignedTechnicalMarksValue")
	@ResponseBody
	public QuizResponse_Entity fetch_UpdatedTechnicalMarkValueDetail(@RequestParam("student_id") String student_id, @RequestParam("question_id") String question_id){
		return fetchStudentResult_Service.fetch_UpdatedTechnicalMarkValueDetail(student_id, question_id);
	}
	
	
	@GetMapping("fetchStudentResultWithTestTypeStatusById")
	@ResponseBody
	public MoatCandidateDataWithTestTypeStatus_Entity fetchStudentResultWithTestTypeStatus(@RequestParam("student_id") String student_id){
		return fetchStudentResult_Service.getStudentResultWithTestTypeStatus(student_id);
	}
	
	@PostMapping("/updateCtcOfferValue")
	public ResponseEntity<String> updateCTCOffer(@RequestParam String ctcValueOffer, @RequestParam String student_id, @RequestParam int flag) {
	    try {
	        boolean isStatusChanged = approveStudentStatus_Service.setFirstSecondThirdCTCOffer(ctcValueOffer, student_id, flag);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating CTC values. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating CTC values. Please try again.");
	    }
	}
	
	@GetMapping("/fetchCtcValueAfterInsertion")
	@ResponseBody
	public Map<String, String> fetchCTCValues(@RequestParam("student_id") String student_id) {
		Map<String, String> ctcValues = new HashMap<>();
	    
	    // Fetch the CTC values based on the student_id
	    String firstCtc = approveStudentStatus_Service.fetchCtcValue(student_id, 1);
	    String secondCtc = approveStudentStatus_Service.fetchCtcValue(student_id, 2);
	    String thirdCtc = approveStudentStatus_Service.fetchCtcValue(student_id, 3);
	    
	    // Add values to the response map
	    ctcValues.put("firstCtcOffer", firstCtc);
	    ctcValues.put("secondCtcOffer", secondCtc);
	    ctcValues.put("thirdCtcOffer", thirdCtc);
	    
	    return ctcValues;  // Return the map as JSON
	}
	
	@PostMapping("/updateCtcResponseAndRemarksFromCandidate")
	public ResponseEntity<String> updateCTCResponses(@RequestParam String ctcResponse, @RequestParam String ctcRemarks, @RequestParam String student_id, @RequestParam int flag) {
	    try {
	        boolean isStatusChanged = approveStudentStatus_Service.setCtcResponsefromCandidate(ctcResponse, ctcRemarks, student_id, flag);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating CTC values. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating CTC values. Please try again.");
	    }
	}
	
	@GetMapping("/fetchCtcRemarksAndResponseAfterInsertion")
	@ResponseBody
	public Map<String, Object> fetchCTCResponses(@RequestParam("student_id") String student_id) {
	    Map<String, Object> ctcValues = new HashMap<>();

	    // Fetch the CTC values based on the student_id
	    MoatCandidate_Entity firstCtc = approveStudentStatus_Service.fetchCtcResponseAndRemarks(student_id, 1);
	    MoatCandidate_Entity secondCtc = approveStudentStatus_Service.fetchCtcResponseAndRemarks(student_id, 2);
	    MoatCandidate_Entity thirdCtc = approveStudentStatus_Service.fetchCtcResponseAndRemarks(student_id, 3);

	    // Add first CTC details to the response map
	    if (firstCtc != null) {
	        ctcValues.put("firstCtcOffer", firstCtc.getFirst_ctc_offer());
	        ctcValues.put("firstCtcResponse", firstCtc.getCandidate_response_for_first_ctc_offer());
	        ctcValues.put("firstCtcRemark", firstCtc.getCandidate_remark_for_first_ctc_offer());
	    }

	    // Add second CTC details to the response map
	    if (secondCtc != null) {
	        ctcValues.put("secondCtcOffer", secondCtc.getSecond_ctc_offer());
	        ctcValues.put("secondCtcResponse", secondCtc.getCandidate_response_for_second_ctc_offer());
	        ctcValues.put("secondCtcRemark", secondCtc.getCandidate_remark_for_second_ctc_offer());
	    }

	    // Add third CTC details to the response map
	    if (thirdCtc != null) {
	        ctcValues.put("thirdCtcOffer", thirdCtc.getThird_ctc_offer());
	        ctcValues.put("thirdCtcResponse", thirdCtc.getCandidate_response_for_third_ctc_offer());
	        ctcValues.put("thirdCtcRemark", thirdCtc.getCandidate_remark_for_third_ctc_offer());
	    }
	    
	    //System.out.println("----() "+ctcValues);

	    return ctcValues;  // Return the map as JSON
	}
	
	@PostMapping("/updateDocUploadStatus/{doc_upload_flag}/{student_id}")
	public ResponseEntity<String> updateDocUploadStatus(
	        @PathVariable("doc_upload_flag") String doc_upload_flag, 
	        @PathVariable("student_id") String student_id) {

	    boolean isUpdated = approveStudentStatus_Service.setUploadFlag(doc_upload_flag, student_id);

	    if (isUpdated) {
	        return ResponseEntity.ok("Document upload status updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update document upload status");
	    }
	}
	
	@GetMapping("fetchDocUploadFlagValue")
	@ResponseBody
	public String fetch_DocumentUploadFlagValue(@RequestParam("student_id") String student_id){
		return approveStudentStatus_Service.fetchDocFlagValue(student_id);
	}
	
	
	@GetMapping("/getAddQuestionsForm")
	public String getAddQuestionsForm(AddQuestions_Entity addQuestions_Entity, HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		return "AddQuestions";
	}
	
	@PostMapping("insertQuestionsData")
	public ResponseEntity<String> AddQuestionsData(@RequestParam String languageSelected, @RequestParam String questions, 
	                                                @RequestParam String op1, @RequestParam String op2, @RequestParam String op3, 
	                                                @RequestParam String op4, @RequestParam String answers) {
	    String result = addQuestions_Service.insertQuestions(languageSelected, questions, op1, op2, op3, op4, answers);
	    if ("success".equals(result)) {
	        return ResponseEntity.ok("Questions inserted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert questions.");
	    }
	}
	
	@GetMapping("fetchAllQuestionsData")
	@ResponseBody
	public List<AddQuestions_Entity> getAllQuestionsData(@RequestParam String languageSelected) {
		//System.out.println("language Selected== "+languageSelected);
		//System.out.println(viewQuestions_Service.getAllQuestions(languageSelected));
		return viewQuestions_Service.getAllQuestions(languageSelected);
	}
	
	@PostMapping("/updateQuestions")
    public ResponseEntity<String> updateQuestions(@RequestParam String languageSelected, @RequestParam int id, @RequestParam String questions, @RequestParam String op1, @RequestParam String op2,
    						@RequestParam String op3, @RequestParam String op4, @RequestParam String answers) {
		String result = editQuestions_Service.updateQuestions(languageSelected, id, questions, op1, op2, op3, op4, answers);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("Questions updated successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update questions.");
	    }
	}
	
	@PostMapping("/deleteQuestionsById")
    public ResponseEntity<String> deleteQuestions(@RequestParam String languageSelected, @RequestParam int id) {
		System.out.println("id== "+id);
		String result = deleteQuestions_Service.deleteQuestions(languageSelected, id);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("Question deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete question.");
	    }
	}
	
	@PostMapping("addMoadeOfContactDetails")
	public String insertMoadeOfContactDetails(@ModelAttribute("modeOfContactNewCandidates_Entity") ModeOfContactNewCandidates_Entity modeOfContactNewCandidates_Entity,HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		
		modeOfContactNewCandidates_Service.addMoatContactData(modeOfContactNewCandidates_Entity,adminDeptName);
		return "redirect:moatDashboard";
	}
	
	@GetMapping("getAllModeOfContactDetails")
	@ResponseBody
	public List<ModeOfContactNewCandidates_Entity> fetchAllModeofContactDetails(@RequestParam String status_value,HttpSession session){
		
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		
		//System.out.println("holaaa === "+modeOfContactNewCandidates_Service.getAllNewCandidatesContactListData(status_value));
		if(status_value.equals("remaining")) {
			return modeOfContactNewCandidates_Service.getAllNewCandidatesContactListForRemainingStatus(adminDept,adminDeptName);
		}else {
			return modeOfContactNewCandidates_Service.getAllNewCandidatesContactListData(status_value,adminDept,adminDeptName);
		}
		
	}
	
	@GetMapping("/checkEmailExistsInTable")
    @ResponseBody
    public boolean isEmailAlreadyExistss(@RequestParam("email") String email) {
    	boolean isExists = modeOfContactNewCandidates_Service.EmailExistsInTable(email);
    	return isExists;
    }
	
	@PostMapping("/updateModeOfContactRemarksValue")
	public ResponseEntity<String> setModeOfContactRemarksValueData(@RequestParam String remarksValue, @RequestParam String id) {
	    try {
	        boolean isStatusChanged = modeOfContactNewCandidates_Service.setModeOfContactRemarksValue(remarksValue, id);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing mode of contact remarks value. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing mode of contact remarks value. Please try again.");
	    }
	}
	
	@PostMapping("insertDynamicDropDownDataForMoatContactList")
	public ResponseEntity<String> saveDynamicDropDownDataForMoatContactList(@RequestParam String dropDownStatusNumber, @RequestParam String dropDownValue) {
	    try {
	        boolean isStatusChanged = addDynamicDropDownDataInMoatContactList_Service.addDropDownData(dropDownStatusNumber, dropDownValue);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving dropdown data for moat contact list. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving dropdown data for moat contact list. Please try again.");
	    }
	}
	
	@GetMapping("/checkDropDownDataExistsInTable")
    @ResponseBody
    public boolean isDropDownDataAlreadyExistsInTable(@RequestParam("dropDownStatusNumber") String dropDownStatusNumber, @RequestParam("dropDownValue") String dropDownValue) {
    	boolean isExists = addDynamicDropDownDataInMoatContactList_Service.dropDownDataAlreadyExistsInTable(dropDownStatusNumber, dropDownValue);
    	return isExists;
    }
	
	@GetMapping("fetchAllInsertedDropdownDataForMoatContactList")
	@ResponseBody
	public List<AddDynamicDropDownDataInMoatContactList_Entity> getAllInsertedDropdownData(){
		System.out.println("aaaaaa"+addDynamicDropDownDataInMoatContactList_Service.fetchAllDropDownData());
		return addDynamicDropDownDataInMoatContactList_Service.fetchAllDropDownData();
	}
	
	
	@GetMapping("/fetchIndividualStatusCountsForInterview")
	   @ResponseBody
	   public CardCountBasedOnInterviewData_Entity
	   getIndividualStatusCountsForInterview() {
	      return cardCountBasedOnInterviewData_Repo.fetchInterviewAllTypeOfStatusCountForCards();
	   } 

	@GetMapping("/fetchIndividualStatusCountsForModeOfContact")
	   @ResponseBody
	   public CardCountBasedOnModeOfContactData_Entity getIndividualStatusCountsForModeOfContact() {
	      return cardCountBasedOnModeOfContactData_Repo.fetchModeOfContactAllTypeOfStatusCountForCards();
	   }
}
