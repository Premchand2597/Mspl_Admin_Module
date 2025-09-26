package com.example.mspl_connect.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.AdminEntity.AttendanceReportGeneratorFinal_DTO;
import com.example.mspl_connect.AdminEntity.EmployeeNamesBasedOnDept_Entity;
import com.example.mspl_connect.AdminEntity.LeaveBalanceDetailsList_Entity;
import com.example.mspl_connect.AdminEntity.LeaveBalanceGroup_DTO;
import com.example.mspl_connect.AdminEntity.SubDeptName_Entity;
import com.example.mspl_connect.AdminEntity.TeamLeadName_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.EmployeeNamesBasedOnDept_Repo;
import com.example.mspl_connect.AdminService.EmployeeAttendanceReportGenerator_Service;
import com.example.mspl_connect.AdminService.LeaveApplicationService;
import com.example.mspl_connect.AdminService.SubDeptName_Service;
import com.example.mspl_connect.AdminService.TeamLeadName_Service;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Repository.RoleRepo;
import com.example.mspl_connect.Service.AttendanceServiceDto;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeDetailsController {
	
	@Autowired
	private EmployeeDetaisService  employeeDetailsService;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo; 
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private SubDeptName_Service subDeptName_Service;
	
	@Autowired
	private EmployeeNamesBasedOnDept_Repo employeeNamesBasedOnDept_Repo;
	
	@Autowired
	private LeaveApplicationService leaveApplicationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;  
	
	@Autowired
	private AttendanceServiceDto attendanceServiceDto;
	
	@Autowired
	private TeamLeadName_Service teamLeadName_Service;
	
	@Autowired
	private EmployeeAttendanceReportGenerator_Service employeeAttendanceReportGenerator_Service;
	
	private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // 16 bytes key (Change it to a secure key)
	
	@PostMapping("/addEmployee")
	public String saveEmployeeDetails(@ModelAttribute EmployeeDetailsEntity employee, 
	                                  @RequestParam("profilePic") MultipartFile profilePic,
	                                  @RequestParam("panPic") MultipartFile panPic,
	                                  RedirectAttributes redirectAttributes) {
	    if (employeeDetailsService.getEmpById(employee.getEmpId()).isPresent()) {
	        redirectAttributes.addFlashAttribute("error", "Employee already exists!");
	        return "redirect:/employee";
	    }

	    try {
	        // Save profilePic to the server
	        //String profilePicPath = saveImage(profilePic);
	        //employee.setProfilePicPath(profilePicPath);

	        // Save panPic to the server
	        String panPicPath = saveImage(panPic);
	       // employee.setPanPicPath(panPicPath);
	        
	        // Save employee to database
	        employeeDetailsService.saveEmployee(employee);
	        
	        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("error", "Failed to upload images.");
	    }
	    return "redirect:/employee";
	}

	private String saveImage(MultipartFile file) throws IOException {
	    if (!file.isEmpty()) {
	        // Create the directory if it doesn't exist
	        Path uploadDirectory = Paths.get("src/main/resources/static/assets/img/");
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }

	        // Generate a unique file name
	        //String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	        String fileName = file.getOriginalFilename();

	        Path filePath = uploadDirectory.resolve(fileName);

	        // Save the file to the file system
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Return the relative path for web access
	        return "assets/img/" + fileName;
	    }
	    return null;
	}

	 @GetMapping("/getDesignations")
	    public ResponseEntity<List<RoleEntity>> getDesignations(@RequestParam int deptId) {
	        List<RoleEntity> designations = roleRepo.findByDepartment(deptId);
	        System.out.println("designations === "+designations);
	        return ResponseEntity.ok(designations);
	    }
	 
	 @GetMapping("/getDesignation")
	    public ResponseEntity<List<RoleEntity>> getDesignation(@RequestParam int deptId) {
		  	//System.out.println("dept id === "+deptId);
	        List<RoleEntity> designations = roleRepo.findByDepartment(deptId);
	        return ResponseEntity.ok(designations);
	    }
	 
	 @GetMapping("/getSubDepartment")
	 public ResponseEntity<List<DepartmentTableEntity>> getSubDepartEntity(@RequestParam int deptId){
		 List<DepartmentTableEntity> departentWithSubDepartment=departmentRepo.findByDepartment(deptId);
		 departentWithSubDepartment.stream().forEach(dept -> System.out.println("dept==="+dept));
		 
		 return ResponseEntity.ok(departentWithSubDepartment);
	 }
	 
	 @GetMapping("/getSubDepartments")
	 public ResponseEntity<List<DepartmentTableEntity>> getSubDepartEntities(@RequestParam int deptId){
		 List<DepartmentTableEntity> departentWithSubDepartment=departmentRepo.findByDepartment(deptId);
		 departentWithSubDepartment.stream().forEach(dept -> System.out.println("dept==="+dept));
		 return ResponseEntity.ok(departentWithSubDepartment);
	 } 	 
	 
	 @GetMapping("/fetchSubDeptNameByUsingDeptId")
	 @ResponseBody
	 public List<SubDeptName_Entity> fetchSubDeptNameByUsingDeptId(@RequestParam("dept_id") int dept_id){
		 System.out.println("subDeptName_Service.getSubDeptNamesByUsingDeptId(dept_id) == "+subDeptName_Service.getSubDeptNamesByUsingDeptId(dept_id));
		 return subDeptName_Service.getSubDeptNamesByUsingDeptId(dept_id);
	 }
	 
	 /*FOR CHAT BOOT*/
	 @GetMapping("/employees/names")
	    public ResponseEntity<List<Map<String, String>>> getEmployeeNames() {
	        List<Map<String, String>> employeeNames = employeeRepository.findAll()
	                .stream()
	                .map(employee -> {
	                    Map<String, String> map = new HashMap<>();
	                    map.put("name", employee.getFirstName() + " " + employee.getLastName());
	                    map.put("email", employee.getEmail()); // Assuming getEmail() returns the employee's email
	                    return map;
	                })
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(employeeNames);
	 }

/*	 @GetMapping("/fetchEmployeeDetails")
	 public String getEmployeeDetails(Model model) {
	     System.out.println("Endpoint /fetchEmployeeDetails invoked");
	     List<EmployeeDetailsEntity> employees = employeeDetailsService.getAllEmployees();
	     System.out.println("Fetched employees: " + employees);
	     
	     List<Map<String, String>> employeeList = employees.stream().map(emp -> {
	         Map<String, String> employeeData = new HashMap<>();
	         employeeData.put("email", emp.getEmail());
	         employeeData.put("profilePicPath", emp.getProfile_pic_path() != null ? emp.getProfile_pic_path() : "default-avatar.png");
	         return employeeData;
	     }).collect(Collectors.toList());
	     
	     System.out.println("detttttt "+ employeeList);
	     // Add the employee list to the model
	     model.addAttribute("employees", employeeList);
	     
	     // Return the Thymeleaf template name
	     return "User/UserFavorite"; // This should map to `employeeDetails.html` in your templates folder
	 }*/
	 
	 @GetMapping("/fetchEmployeeDetails")
	 @ResponseBody
	 public List<Map<String, String>> getEmployeeDetails() {
	     System.out.println("Endpoint /fetchEmployeeDetails invoked");
	     List<EmployeeDetailsEntity> employees = employeeDetailsService.getAllEmployees();
	     System.out.println("Fetched employees: " + employees);
	     
	     List<Map<String, String>> employeeList = employees.stream().map(emp -> {
	         Map<String, String> employeeData = new HashMap<>();
	         employeeData.put("email", emp.getEmail());
	         employeeData.put("profilePicPath", emp.getProfilePicPath() != null ? emp.getProfilePicPath() : "default-avatar.png");
	         return employeeData;
	     }).collect(Collectors.toList());
	     
	     System.out.println("Employee details: " + employeeList);
	     return employeeList;  // This will return JSON, which will be handled on the frontend
	 }
	 
	 @GetMapping("/fetchInactiveEmployeesEmailIdLists")
	 @ResponseBody
	 public List<String> getInactiveEmployeesEmailId(){
		 return employeeDetailsService.fetchInactiveEmployeesEmailId();
	 }
	 
	 @GetMapping("/fetchAllEmployeeDetails")
	 @ResponseBody
	 public List<DisplayEmployessEntity> fetchAllEmployeeDetails(){
		 return detaisService.getEmployeeWithDeptName();
	 }
	 
		@PostMapping("/saveEmployee")
		public String saveEmployeeDetails(@ModelAttribute EmployeeDetailsEntity employee, RedirectAttributes redirectAttributes){
		    
			if (detaisService.getEmpById(employee.getEmpId()).isPresent()) {
		        redirectAttributes.addFlashAttribute("error", "Employee already exists!");
		        return "redirect:/employee";
		    }
			
			//String encryptedPassword = hashPassword(employee.getPassword());
			//employee.setPassword(encryptedPassword);
			String rawPassword = employee.getPassword();
			String rawPan = employee.getPanCard();
			String rawAadhar = employee.getAdharNo();
	        try {
				String encryptedPassword = encrypt(rawPassword); // Encrypting password
				employee.setPassword(encryptedPassword);
				
				String encryptedPan = encrypt(rawPan); // Encrypting pan
				employee.setPanCard(encryptedPan);
				
				String encryptedAadhar = encrypt(rawAadhar); // Encrypting aadhar
				employee.setAdharNo(encryptedAadhar);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			employee.setPassword_change_flag("1");
		    // Save employee to database
			detaisService.saveEmployee(employee);
			
			// To update candidate_imported_flag_to_student_table
			detaisService.updateImportedToEmployeeTableAsAFlagInStudentTable(employee.getMobileNo());
			
			redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
		    return "redirect:/employee";
		}
		
		public static String encrypt(String data) throws Exception {
	        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    public static String decrypt(String encryptedData) throws Exception {
	        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	        return new String(decryptedBytes);
	    }
	    
	    @GetMapping("/isEmpIdAlreadyPresentOrNot")
		 @ResponseBody
		 public boolean checkEmpIdExistingOrNot(@RequestParam String empid) {
			 return detaisService.isEmpIdAlreadyPresentOrNot(empid);
		 }	
	    
	    @GetMapping("/fetchLastInsertedEmpIdValue")
		 @ResponseBody
		 public String fetchLastInsertedEmpId() {
			 return detaisService.fetchLastInsertedEmpIdValue();
		 }
	    
	    @GetMapping("/getAllEmployeesLeaveBalanceData")
		@ResponseBody
		public List<LeaveBalanceDetailsList_Entity> getAllEmpsLeaveBalanceDetails(@RequestParam String current_financial_year){
			return detaisService.fetchAllEmpsLeaveBalanceDetails(current_financial_year);
		}
		
		@GetMapping("/fetchEmpNamesBasedOnDeptName")
		@ResponseBody
		public List<EmployeeNamesBasedOnDept_Entity> getEmpNamesBasedOnDeptName(@RequestParam String dept_name){
			return employeeNamesBasedOnDept_Repo.fetchEmpNamesBasedOnDeptName(dept_name);
		}
		
		@GetMapping("/getEmployeesLeaveBalanceDataByUsingEmpId")
		@ResponseBody
		public LeaveBalanceDetailsList_Entity getEmpsLeaveBalanceDetailsBasedOnEmpId(@RequestParam String current_financial_year, @RequestParam String empId){
			System.out.println("detaisService.getEmployeesLeaveBalanceDataBasedOnEmpId(current_financial_year, empId); = "+detaisService.getEmployeesLeaveBalanceDataBasedOnEmpId(current_financial_year, empId));
			return detaisService.getEmployeesLeaveBalanceDataBasedOnEmpId(current_financial_year, empId);
		}
		
		@PostMapping("updateOrInsertLeaveBalanceBasedOnEmpid")
		 public ResponseEntity<String> updateOrInsertLeaveBalanceManuallyByHR(@RequestParam String empid, @RequestParam String current_financial_year, 
				 @RequestParam(required = false) String remarks, @RequestBody LeaveBalanceGroup_DTO leaveBalanceGroup){
			 boolean isUpdated = false;
			 isUpdated = leaveApplicationService.updateOrInsertLeaveBalanceManually(empid, current_financial_year, leaveBalanceGroup, remarks);
			 
			 if (isUpdated) {
			        return ResponseEntity.ok("Successfully updated leave balance!");
			    } else {
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update leave balance.");
			    }
		 }
		
		@GetMapping("/employeeFormForSelectedCandidates")
		 public String employeePages(Model model, @RequestParam("selected_Candidate_Id") String selected_Candidate_Id,@RequestParam(value = "error", required = false) String error,@RequestParam(value = "message", required = false) String message, HttpSession session) {
			 
			// Safely check if the session attribute is null
		    if (session.getAttribute("empDetailsByEmpId") == null) {
		        return "redirect:/";
		    }
			
			 List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
			 List<EmployeeDetailsEntity> employees = detaisService.getEmployee();
			 List<DisplayEmployessWithMissPunchEntity> employeeWithDeptName;
			 
		     String email = (String) session.getAttribute("email");
		     String empId = employeeRepository.findEmpidByEmail(email);
		     DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		     
			
		     List<TeamLeadName_Entity> empNamesAndEmpIdLists = teamLeadName_Service.getTeamLeadNameByUsingDeptId();
		     
		 		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		 		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		 		
		 		//employeeWithDeptName.stream().forEach(emp->System.out.println("/////////="+emp));
				 if (error != null) {
			         model.addAttribute("error", error);	
			     }
			     if (message != null) {
			         model.addAttribute("message", message);
			     }
			     
			     Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

			     if (permissions.isPresent()) {
			         PermissionsEntity permissionEntity = permissions.get();
			         model.addAttribute("permissions", permissionEntity);
			     } else {
			        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			    }
			    System.out.println("adminDept==="+adminDept);
			    if(adminDept == 0) {
			    	//employeeWithDeptName = detaisService.getEmployeeWithDeptName();
			    	employeeWithDeptName = detaisService.getSuperAdminDeptEmployees(empId);;
			    }
			    else {
			    	employeeWithDeptName = detaisService.getAdminDeptEmployees(empId);
			    }	   
			    
			     // Fetch attendance count for the current year
					int currentYear = LocalDate.now().getYear();

					System.out.println("..........=" + currentYear + " " + empId);
					List<AttendanceDTO> attendanceRecords = attendanceServiceDto.getAttendanceForYear(currentYear, empId);
					int totalAttendanceDays = attendanceRecords != null ? attendanceRecords.size() : 0;

					// Calculate missing punches (where totalHours is "00:00:00")
					long missingPunches = attendanceRecords.stream()
							.filter(att -> "00:00:00".equals(att.getTotalHours())) // Check for missing punch
							.count();
					// Calculate missing punches (where totalHours is "00:00:00")
					List<String> missingPunchDates = attendanceRecords.stream()
							.filter(att -> "00:00:00".equals(att.getTotalHours())) // Check for missing punch
							.map(AttendanceDTO::getDate) // Extract the date where totalHours is "00:00:00"
							.collect(Collectors.toList());
					
				     //System.out.println("emp details================"+employee);
				     model.addAttribute("empNamesAndEmpIdLists", empNamesAndEmpIdLists);
					
				
		 		model.addAttribute("missingPunchDates", missingPunchDates);
		     //System.out.println("emp details================"+employee);
		     model.addAttribute("empNamesAndEmpIdLists", empNamesAndEmpIdLists);
		     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		     model.addAttribute("departments", departments);
		     model.addAttribute("employeeWithDeptName",employeeWithDeptName);
		     model.addAttribute("selected_Candidate_Id",selected_Candidate_Id);
		     
		     return "employees";
		 } 
		
		@GetMapping("/getAllAttendanceReportBasedOnStartAndEndDate")
		@ResponseBody
	    public List<AttendanceReportGeneratorFinal_DTO> getAttendanceReport(
	            @RequestParam("startDate") String startDate,
	            @RequestParam("endDate") String endDate) {
	        
	        return employeeAttendanceReportGenerator_Service.getAttendanceReportGenerator(startDate, endDate);
	    }
	    
	    @GetMapping("/getAllAttendanceReportBasedOnStartAndEndDateAndEmpId")
		@ResponseBody
	    public List<AttendanceReportGeneratorFinal_DTO> getAttendanceReportBasedONEmpId(
	            @RequestParam("startDate") String startDate,
	            @RequestParam("endDate") String endDate,
	            @RequestParam("emp_id") String emp_id) {
	    	
	    	//System.out.println(startDate+" "+endDate+" "+emp_id);
	        
	        return employeeAttendanceReportGenerator_Service.getAttendanceReportGeneratorByUsingEmpId(startDate, endDate, emp_id);
	    }
	    
	    @PostMapping("/getProbationDateAsAGroup")
	    public ResponseEntity<List<Map<String, String>>> getProbationDateData(
	            @RequestBody List<String> empIds, HttpSession session) {
	        List<Map<String, String>> responseList = new ArrayList<>();

	        for (String empId : empIds) {
	       	 //System.out.println("empId === "+empId);
	            String email = employeeRepository.findEmailByEmpId(empId);
	            //System.out.println("email === "+email);
	            String probationDate = getProbationCompletionDate(email);
	            System.out.println("probationDate === "+probationDate);

	            Map<String, String> response = new HashMap<>();
	            response.put("empid", empId);
	            response.put("probation_completed_date", probationDate);
	            responseList.add(response);
	        }

	        return ResponseEntity.ok(responseList);
	    }
	    
	    public String getProbationCompletionDate(String email) {
	        // Fetch the employee details based on empId
	    	EmployeeDetailsEntity  employee = employeeRepository.findByEmail(email);
	        return employee != null ? employee.getProbation_completed_date() : null; // Return the probation date or null if not found
	    }

}
