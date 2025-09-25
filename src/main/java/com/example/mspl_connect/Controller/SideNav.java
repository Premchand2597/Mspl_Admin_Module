package com.example.mspl_connect.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;
import com.example.mspl_connect.AdminEntity.MissedPunchEmpList_Entity;
import com.example.mspl_connect.AdminEntity.PendingLeaveWithProfilePicData_DTO;
import com.example.mspl_connect.AdminEntity.PendingLeaveWithProfilePicData_Entity;
import com.example.mspl_connect.AdminEntity.TeamLeadName_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.AdminRepo.MissedPunchEmpList_Repo;
import com.example.mspl_connect.AdminRepo.PendingLeaveWithProfilePicData_Repo;
import com.example.mspl_connect.AdminService.LeaveRequestService;
import com.example.mspl_connect.AdminService.MoatCandidate_Service;
import com.example.mspl_connect.AdminService.MoatLogin_Service;
import com.example.mspl_connect.AdminService.TeamLeadName_Service;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.DisplayEmployeeWithMisspunchRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.LeaveApplicationRepo;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.AttendanceServiceDto;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SideNav {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private MoatLogin_Service moatLogin_Service;
	
	@Autowired
	private MoatCandidate_Service moatCandidate_Service;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
	
	@Autowired
	private LeaveApplicationRepo applicationRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;  
	 	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private AttendenceRepo attendenceRepo; 
	
	@Autowired
	private DisplayEmployeeWithMisspunchRepo displayEmployeeWithMisspunchRepo;
	
	@Autowired
	private AttendanceServiceDto attendanceServiceDto;
	
	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@Autowired
	private MissedPunchEmpList_Repo missedPunchEmpList_Repo;
	
	@Autowired
	private TeamLeadName_Service teamLeadName_Service;
	
	@GetMapping("/navbar")
	public String sideNav(HttpSession session,Model model) {
		
		// Retrieve user details from session
	    String email = (String) session.getAttribute("email");
	    String roleName = (String) session.getAttribute("role_name");
	    String userType = (String) session.getAttribute("user_type");
	    
	    Integer presentEmployeCount=0;
	    
	    session.setAttribute("role_name", roleName);
		
		int totalNoOfDepts = departmentService.totalNoOfDepts();
		int totalNoOfRoles = departmentService.totalNoOfRoles();
		int totalEmployeCount = detaisService.totalEmployeCount();
		
		String empId = employeeRepository.findEmpidByEmail(email);
		System.out.println("empId-"+empId);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		
		DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		
		List<LeaveApplication> usersList = applicationRepo.getFullleaveRequest();
		List<PendingLeaveWithProfilePicData_DTO> pendingLeavesWithProfilePicLists = leaveRequestService.fetchAllPendingLeavesDetailsWithProfilePics();
		//List<LeaveApplicationWithProfile> usersList = leaveRequestService.getNewLeaveRequestForSA(adminDept, empId);
		System.out.println("aaaaaaaa"+usersList);
		System.out.println("111"+pendingLeavesWithProfilePicLists);
		
		// System.out.println("after 1"+usersList1);
		List<TodayPresentEmpEntity> todayPresentEmpsList = detaisService.getTodayPresentEmpList();
		
		System.out.println("todayPresentEmpsList === "+todayPresentEmpsList);
		for(TodayPresentEmpEntity presntees : todayPresentEmpsList) {
	    	if(presntees.getFirst_punch_time().equals(presntees.getLast_punch_time())) {
	    		presntees.setLast_punch_time("-");
	    	}
	    }
		
		System.out.println("after 2");
		System.out.println("todayPresentEmpsList......"+todayPresentEmpsList);
		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
	    
	     if (permissions.isPresent()) {
	         PermissionsEntity permissionEntity = permissions.get();
	         model.addAttribute("permissions", permissionEntity);
	         session.setAttribute("permissions", permissionEntity);
	     } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	     }
	     
	    //get the present daya absent emplyee name
		   /* Optional<List<String>> todayAbsentEmployeesNameList = attendenceRepo.getAbsentEmpForSA();

		    // Check if the list is present and add it to the model
		    todayAbsentEmployeesNameList.ifPresent(absentEmployees -> 
		        model.addAttribute("absentEmployees", absentEmployees)
		    );*/
	     
	     
	     //Optional<List<String>> todayAbsentEmployeesNameList = attendenceRepo.getAbsentEmpForSA();
	     // get the present daya absent emplyee name
			Optional<List<Object[]>> todayAbsentEmployeesNameList = detaisService.fetch_today_absent_employee_for_super_admin();

			// Check if the list is present and add it to the model
			todayAbsentEmployeesNameList
					.ifPresent(absentEmployees -> model.addAttribute("absentEmployees", absentEmployees));

			 Integer totalpresentEmployeCount = detaisService.fetch_today_present_employee_count();
			 
			
			// If it's not present, you can add an empty list or handle it accordingly
			if(todayAbsentEmployeesNameList.isEmpty()) {
				model.addAttribute("absentEmployees", new ArrayList<>());
			}
		     
		model.addAttribute("empId", empId);
		model.addAttribute("totalEmployeCount",totalEmployeCount);
		model.addAttribute("totalNoOfDepts",totalNoOfDepts);
		model.addAttribute("totalNoOfRoles",totalNoOfRoles);
		model.addAttribute("usersList",usersList);
		model.addAttribute("pendingLeavesWithProfilePicLists", pendingLeavesWithProfilePicLists);
		// Add user details to the model
	    model.addAttribute("email", email);
	    model.addAttribute("roleName", roleName);
	    model.addAttribute("userType", userType);
	    model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	    model.addAttribute("todayPresentEmp",todayPresentEmpsList);
		model.addAttribute("totalpresentEmployeCount",totalpresentEmployeCount);
	    session.setAttribute("empDetailsByEmpId", empDetailsByEmpId);
	    
	    List<MissedPunchEmpList_Entity> missedPunchEmpList = missedPunchEmpList_Repo.fetchMissedPuchListData();
	    model.addAttribute("missedPunchEmpList", missedPunchEmpList);
	    
	    //model.addAttribute("usersList",usersList);
	    
		return "index";		
	}	
	
	@GetMapping("/addDepartment")
	public String addDepartment(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "message", required = false) String message,HttpSession session) {
		
		List<DepartmentTableEntity> depts=departmentService.getAllDepartments();
		

	     String email = (String) session.getAttribute("email");
	     String empId = employeeRepository.findEmpidByEmail(email);
	     DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		
		 if (error != null) {
	         model.addAttribute("error", error);
	     }
	     if (message != null) {
	         model.addAttribute("message", message);
	     }
	     
	     Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
	     //System.out.println("permissions....."+permissions);
	    
	     if (permissions.isPresent()) {
	         PermissionsEntity permissionEntity = permissions.get();
	         model.addAttribute("permissions", permissionEntity);
	     } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
	     
	     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		 model.addAttribute("departments",departmentService.getAllDepartments());
		 model.addAttribute("department", new DepartmentTableEntity());
		return "AddDepartment";
	}
	 
	 @GetMapping("/addRole")
	 public String addRole(Model model, @RequestParam(value = "error", required = false) String error,@RequestParam(value = "message", required = false) String message,HttpSession session) {    
	     List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
	     
	     String email = (String) session.getAttribute("email");
	     String empId = employeeRepository.findEmpidByEmail(email);
	     DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);	     
	     
	     model.addAttribute("departments", departments);
	     
	     if (error != null) {
	         model.addAttribute("error", error);
	     }
	     if (message != null) {
	         model.addAttribute("message", message);
	     }	
	     
	     List<RoleEntity> abcd = departmentService.getAllRolesWithDeptName();
	     
	     Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
	     //System.out.println("permissions....."+permissions);
	    
	     if (permissions.isPresent()) {
	         PermissionsEntity permissionEntity = permissions.get();
	         model.addAttribute("permissions", permissionEntity);
	     } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
		 	
	     model.addAttribute("departments", departments);
	     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	     model.addAttribute("role", new RoleEntity());
	     model.addAttribute("roles",departmentService.getAllRolesWithDeptName());
	     return "AddRole";
	 }	 
	 
	 @GetMapping("/employee")
	 public String employee(Model model,@RequestParam(value = "error", required = false) String error,@RequestParam(value = "message", required = false) String message,HttpSession session) {
		 List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		 List<EmployeeDetailsEntity> employees = detaisService.getEmployee();
		 
		 List<DisplayEmployessWithMissPunchEntity> employeeWithDeptName;
		 
		 // Retrieve user details from session
		 String email = (String) session.getAttribute("email");
		 String empId = employeeRepository.findEmpidByEmail(email);
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
	     //System.out.println("permissions....."+permissions);
	    
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
	    
	     DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
	     
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
			
			List<TeamLeadName_Entity> empNamesAndEmpIdLists = teamLeadName_Service.getTeamLeadNameByUsingDeptId();
			
		     //System.out.println("emp details================"+employee);
		     model.addAttribute("empNamesAndEmpIdLists", empNamesAndEmpIdLists);
			
		 model.addAttribute("missingPunchDates", missingPunchDates);
	     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	     model.addAttribute("departments", departments);
	     model.addAttribute("employeeWithDeptName",employeeWithDeptName);
	     
		 return "User/employees";
	 } 
	 
	 @GetMapping("/getMispunchDAteByEmid")
	 public @ResponseBody List<String> missingPunchDatesByEmpId(@RequestParam("empid") String empid) {
	     System.out.println("empid----" + empid);
	     int currentYear = LocalDate.now().getYear();

	   /* System.out.println("..........=" + currentYear + " " + empid);
	     List<AttendanceDTO> attendanceRecords = attendanceServiceDto.getAttendanceForYear(currentYear, empid);
	     attendanceRecords.stream().forEach(i->System.out.println("attendanceRecords---"));
	     
	     int totalAttendanceDays = attendanceRecords != null ? attendanceRecords.size() : 0;

	     // Calculate missing punches (where totalHours is "00:00:00")
	     List<String> missingPunchDates = attendanceRecords.stream()
	             .filter(att -> "00:00:00".equals(att.getTotalHours())) // Check for missing punch
	             .map(AttendanceDTO::getDate) // Extract the date where totalHours is "00:00:00"
	             .collect(Collectors.toList());

	     missingPunchDates.forEach(i -> System.out.println("i---------" + i));*/
	     
	     System.out.println("..........=" + currentYear + " " + empid);
	     List<AttendanceDTO> attendanceRecords = attendanceServiceDto.getAttendanceForYear(currentYear, empid);

	     //attendanceRecords.stream().forEach(i -> System.out.println("attendanceRecords---" + attendanceRecords));

	     int totalAttendanceDays = attendanceRecords != null ? attendanceRecords.size() : 0;
	     DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     
	     // Calculate missing punches (where totalHours is "00:00:00")
	     List<String> missingPunchDatesWithTime = attendanceRecords.stream()
	             .filter(att -> "00:00:00".equals(att.getTotalHours())) // Check for missing punch
	             .map(att -> {
	                 // Convert Date Format
	                 LocalDate date = LocalDate.parse(att.getDate(), inputFormatter);
	                 String formattedDate = date.format(outputFormatter);
	                 return "Date: " + formattedDate + " | " + att.getInTime();
	             })
	             .collect(Collectors.toList());
	     missingPunchDatesWithTime.forEach(i -> System.out.println("Missing Punch - " + i));

	     
	     return missingPunchDatesWithTime; // Return the list directly
	     
	 }

}

