package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.Appraisal;
import com.example.mspl_connect.AdminEntity.AppraisalForDisplay;
import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;
import com.example.mspl_connect.AdminEntity.AssetDisplayEnityt;
import com.example.mspl_connect.AdminEntity.AsseteEntity;
import com.example.mspl_connect.AdminEntity.EarnedLeaveDTO;
import com.example.mspl_connect.AdminEntity.EmployeeEvent;
import com.example.mspl_connect.AdminEntity.HolidaysList;
import com.example.mspl_connect.AdminEntity.InterCommDetailsEntity;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;
import com.example.mspl_connect.AdminRepo.AdminInterCommRepo;
import com.example.mspl_connect.AdminRepo.AppraisalHrREpo;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.AdminRepo.Events_Repo;
import com.example.mspl_connect.AdminService.AdminHolidayService;
import com.example.mspl_connect.AdminService.AppraisalService;
import com.example.mspl_connect.AdminService.AsseteService;
import com.example.mspl_connect.AdminService.ChangeAnnouncementFlagValueService;
import com.example.mspl_connect.AdminService.EmployeeEventService;
import com.example.mspl_connect.AdminService.Event_Service;
import com.example.mspl_connect.AdminService.GroupMailId_Service;
import com.example.mspl_connect.AdminService.LeaveApplicationService;
import com.example.mspl_connect.AdminService.LeaveRequestService;
import com.example.mspl_connect.AdminService.NotificationService;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.GroupMailId_Entity;
import com.example.mspl_connect.Entity.Holiday_Entity;
import com.example.mspl_connect.Entity.NewCompanyProject_Entity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Entity.ProjectEntity;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.LeaveApplicationRepo;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.AttendanceServiceDto;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.Holiday_Service;
import com.example.mspl_connect.Service.PermissionService;
import com.example.mspl_connect.Service.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminMainPage {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeDetaisService detaisService;

	@Autowired
	private PermissionRepo permissionRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	@Autowired
	private GroupMailId_Service groupMailId_Service;

	@Autowired
	private AdminInterCommRepo adminInterCommRepo;

	@Autowired
	private AdminHolidayService adminHolidayService;

	@Autowired
	private EmployeeDetaisService employeeService;

	@Autowired
	private AsseteService asseteService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private AttendenceRepo attendenceRepo;

	@Autowired
	private LeaveApplicationRepo applicationRepo;

	@Autowired
	private LeaveRequestService leaveRequestService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private EmployeeEventService employeeEventService;

	@Autowired
	private AppraisalService appraisalService;

	@Autowired
	private AppraisalRepository appraisalRepository;

	@Autowired
	private AppraisalHrREpo appraisalHrREpo;

	@Autowired
	private LeaveApplicationService leaveApplicationService;

	@Autowired
	private AttendanceServiceDto attendanceServiceDto;
	
	@Autowired
	private Events_Repo events_Repo; 
 	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
	
	@Autowired
	private Event_Service event_Service; 
	
	@Autowired
	private ChangeAnnouncementFlagValueService announcementFlagValueService;
	
	@Autowired
	private PermissionService permissionService; 
	
	@Autowired
	private Holiday_Service holiday_Service;

	@GetMapping("/userDashboard")
	public String sideNav(HttpSession session, Model model) {

		int totalNoOfDepts = departmentService.totalNoOfDepts();
		int totalNoOfRoles = departmentService.totalNoOfRoles();

		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		System.out.println("empId----"+empId);
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
						    	permissionService.updateDocUploadPermissionFlag(); // Call the method	
						    	System.out.println("cccccccccc");
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
									
					if (dueDate.isBefore(currentDate)) { // Compare dates
						permissionEntity.setApprisalAccess(false); // If due date is today or earlier, set
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

		System.out.println("1");
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);

		String loggedInTime = attendenceRepo.getEmpLogedTime(empId);
		String empLastPunchTime = attendenceRepo.getEmpLastPunchTime(empId);

		List<String> ongoingProjectName = projectService.fetProjectName();
		ongoingProjectName.stream().forEach(i -> System.out.println("project names-----" + i));
		// Optional<Integer> getTotalPresentCountByDeptId =
		// attendenceRepo.getTotalPresentCountByDeptId(adminDept);
		// Integer getTotalRodayPresent =
		// getTotalPresentCountByDeptId.orElse(0);AttendenceRepo
		Integer getTotalRodayPresent = attendenceRepo.presentCount(empId);

		// get the present daya absent emplyee name
		Optional<List<Object[]>> todayAbsentEmployeesNameList = employeeService.fetch_today_absent_employee(empId);

		// Check if the list is present and add it to the model
		todayAbsentEmployeesNameList
				.ifPresent(absentEmployees -> model.addAttribute("absentEmployees", absentEmployees));

		// If it's not present, you can add an empty list or handle it accordingly
		if(todayAbsentEmployeesNameList.isEmpty()) {
			model.addAttribute("absentEmployees", new ArrayList<>());
		}

		//System.out.println("empId----" + empId);
		int totalEmployeCount = employeeRepository.totalDeptEmployeCountByEmpId(empId);

		List<TodayPresentEmpEntity> todayPresentEmpsList = detaisService.getTodayPresentEmpListByDept(empId);
		for(TodayPresentEmpEntity presntees : todayPresentEmpsList) {
	    	if(presntees.getFirst_punch_time().equals(presntees.getLast_punch_time())) {
	    		presntees.setLast_punch_time("-");
	    	}
	    }
		
		if (loggedInTime == null || loggedInTime.isEmpty()) {
			loggedInTime = "00:00:00"; // Default value
		}
		if (empLastPunchTime == null || empLastPunchTime.isEmpty()) {
			empLastPunchTime = "00:00:00"; // Default value
		}
		//System.out.println("2222222222222");
		List<LeaveApplicationWithProfile> usersList = leaveRequestService.getNewLeaveRequest(adminDept, empId);
		//System.out.println("333333333333"+usersList);
		// Define the current financial year (you may need to dynamically calculate this
		// based on the current date)
		String currentFinancialYear = getCurrentFinancialYear();
		// Replace this with dynamic calculation if needed
		// System.out.println("Current Financial Year: " + currentFinancialYear);
		// Get leave records (SL, CL) from the leaveRecords endpoint
		List<LeaveUtilized> leaveRecords = leaveApplicationService.getAllLeaveRecords(empId);
		double totalSL = 0.0;
		double totalCL = 0.0;

		// Loop through the leave records to calculate SL and CL remaining for the
		// current financial year
		if (leaveRecords != null) {
			for (LeaveUtilized leave : leaveRecords) {
				System.out.println("Processing leave record: " + leave);
				if (currentFinancialYear.equals(leave.getFinancialYear())) {
					if ("Sick Leave".equalsIgnoreCase(leave.getLeaveType())) {
						totalSL = leave.getRemaining();
						// System.out.println("Sick Leave (SL) remaining for financial year " +
						// currentFinancialYear + ": " + totalSL);
					} else if ("Casual Leave".equalsIgnoreCase(leave.getLeaveType())) {
						totalCL = leave.getRemaining();
						// System.out.println("Casual Leave (CL) remaining for financial year " +
						// currentFinancialYear + ": " + totalCL);
					}
				}
			}
		} else {
			System.out.println("No leave records found for the employee ID: " + empId);
		}

		// Fetch EL from the calculate-earned-leave endpoint
		EarnedLeaveDTO earnedLeave = leaveApplicationService.getEarnedLeave(email);
		double totalEL = earnedLeave != null ? earnedLeave.getElRemaining() : 0.0;
		// System.out.println("Earned Leave (EL) remaining: " + totalEL);
		//System.out.println("totalSL=" + totalSL);
		//System.out.println("totalCL=" + totalCL);
		//System.out.println("totalEL=" + totalEL);
		// Calculate total available leaves
		double totalLeavesAvailable = totalSL + totalCL + totalEL;

		// Log the total leaves available
		//System.out.println("Total Leaves Available: " + totalLeavesAvailable);

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
		//System.out.println("==ssss" + missingPunchDates);
		// long missingPunches = missingPunchDates.size();
				
		//check password is changed or not
		String passwordChangeFalgValue=employeeRepositoryWithDeptName.findPasswordChangeFlagByEmail(email);
		System.out.println("passwordChangeFalgValue------------------"+passwordChangeFalgValue+" emai-----"+email);
		
		//update docUpload permission table if doc_date < current date
		if (permissions.isPresent()) {
			
			
		} else {
			System.out.println("permission is empty");// Add a default object to avoid null issues
		}
		
		System.out.println("dddddddddddddddd");
		// get employees details with recent application used time
		List<Object[]> getEmployeeRecentAppTimeByAdmin = detaisService.getEmployeeRecentAppTimeByAdmin(empId);
		//getEmployeeRecentAppTimeByAdmin.stream().forEach(i->System.out.println("ssssssssss"+i.getLast_seen()));	
		System.out.println("eeeeeeeeeeeeeeee");
		
		model.addAttribute("employeeRecentPunch", getEmployeeRecentAppTimeByAdmin);
		model.addAttribute("passwordChangeFalgValue",passwordChangeFalgValue.trim());		

		
		model.addAttribute("missingPunches", missingPunches);
		model.addAttribute("missingPunchDates", missingPunchDates);
		model.addAttribute("ongoingProjectName", ongoingProjectName);

		model.addAttribute("totalLeavesAvailable", totalLeavesAvailable);

		// model.addAttribute("leavecount",leavecount);
		model.addAttribute("totalEmployeCount", totalEmployeCount);
		model.addAttribute("totalNoOfDepts", totalNoOfDepts);

		model.addAttribute("totalNoOfRoles", totalNoOfRoles);
		model.addAttribute("email", email);
		model.addAttribute("empId", empId);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		session.setAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("loggedInTime", loggedInTime);
		model.addAttribute("empLastPunchTime", empLastPunchTime);

		model.addAttribute("usersList", usersList);
		model.addAttribute("todayPresentEmpsList", todayPresentEmpsList);

		model.addAttribute("getTotalPresentCountByDeptId", getTotalRodayPresent);

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/";
		}
		return "User/AdminDashboard";
	}

	public static String getCurrentFinancialYear() {
		// Get the current date
		LocalDate today = LocalDate.now();

		// Get the current year
		int currentYear = today.getYear();

		// Financial year starts from April 1st
		if (today.getMonthValue() >= 4) {
			// If the current month is April or later, the financial year is currentYear -
			// currentYear + 1
			return currentYear + "-" + (currentYear + 1) % 100; // Takes last 2 digits of the next year
		} else {
			// If the current month is before April, the financial year is previous year to
			// currentYear
			return (currentYear - 1) + "-" + currentYear % 100; // Takes last 2 digits of the current year
		}
	}

	@GetMapping("/userAccountantDashboard")
	public String accountantDAshboard(HttpSession session, Model model) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		int totalNoOfDepts = departmentService.totalNoOfDepts();
		int totalNoOfRoles = departmentService.totalNoOfRoles();

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
																// true (if needed)
				}

			}
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}

		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
		System.out.println("empDetailsByEmpId===" + empDetailsByEmpId);

		String loggedInTime = attendenceRepo.getEmpLogedTime(empId);
		int totalEmployeCount = employeeRepository.totalDeptEmployeCountByEmpId(empId);

		if (loggedInTime == null || loggedInTime.isEmpty()) {
			loggedInTime = "00:00:00"; // Default value
		}

		List<LeaveApplication> usersList = leaveRequestService.getLeaveRequest(adminDept, empId);

		// model.addAttribute("leavecount",leavecount);
		model.addAttribute("totalEmployeCount", totalEmployeCount);
		model.addAttribute("totalNoOfDepts", totalNoOfDepts);
		model.addAttribute("totalNoOfRoles", totalNoOfRoles);
		model.addAttribute("email", email);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("loggedInTime", loggedInTime);

		model.addAttribute("usersList", usersList);
		return "User/Dashboard";

	}

	@GetMapping("/viewAdminPayslip")
	public String viewAdminPayslip(@RequestParam("empid") String empid, Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}
		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empid);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
		System.out.println("permissions....." + permissions);

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
																// true (if needed)
				}
			}
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);
		System.out.println("////empDetailsByEmpId ==" + empDetailsByEmpId);

		model.addAttribute("departments", departments);
		model.addAttribute("employee", employee);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		return "User/AdminPaySlip";
	}

	@GetMapping("/viewAdminProfile")
	public String viewAdminProfile(@RequestParam("empid") String empid, Model model, HttpSession session) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		// Retrieve logged user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		// clicked user details
		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empid);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
		//String docUploadDueDate 

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

		
		Optional<PermissionsEntity> permissionstoDisplay = permissionRepo.findByUserId(empid);
	    
		
		
		if (permissionstoDisplay.isPresent()) {
			PermissionsEntity permissionEntitytoDisplay = permissionstoDisplay.get();
			
			System.out.println("permissionEntitytoDisplay====="+permissionEntitytoDisplay);
			
	        System.out.println("permissionstoDisplay"+permissionstoDisplay);
	        
	        model.addAttribute("permissionstoDisplay", permissionEntitytoDisplay);
	    } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
	    
		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);
		
		model.addAttribute("departments", departments);
		model.addAttribute("employee", employee);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		return "User/UserEmployeeProfile";
	}

	@GetMapping("/groupMailId")
	public String viewGroupMailIdPage(HttpSession session, Model model) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/";
		}

		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		List<InterCommDetailsEntity> interComm = adminInterCommRepo.findAll();

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

		System.out.println("interComm--" + interComm);
		model.addAttribute("interComm", interComm);
		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);

		return "User/GroupMailId";
	}

	@GetMapping("/interCommView")
	public ResponseEntity<List<InterCommDetailsEntity>> getInterCommDetails() {
		try {
			List<InterCommDetailsEntity> interComm = adminInterCommRepo.findAll();
			System.out.println("Retrieved Data: " + interComm);

			if (interComm.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Send 204 if no data
			}

			return new ResponseEntity<>(interComm, HttpStatus.OK); // Send 200 with data

		} catch (Exception e) {
			System.err.println("Error fetching data: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Send 500 on error
		}
	}

	@GetMapping("/events")
	public String viewEventPage(HttpSession session, Model model) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}
		try {// Retrieve user details from session
			String email = (String) session.getAttribute("email");
			String empId = employeeRepository.findEmpidByEmail(email);
			int adminDept = employeeRepository.findDeptIdByEmpId(empId);
			String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

			Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
			 
			// Update the announcement flag
			announcementFlagValueService.changeAnnouncementFlagValueByEmpId(empId);

			if(permissions.isPresent()) {
				PermissionsEntity permissionEntity = permissions.get();
				if (permissionEntity.isApprisalAccess()) {
					System.out.println("if is Apprisal access");
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
				System.out.println("permissionEntity----" + permissionEntity);
				model.addAttribute("permissions", permissionEntity);
			} else {
				model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			}

			List<EmployeeEvent> events = employeeEventService.getAllEvents();

			// Make sure the events list has properly encoded images and videos
			events.forEach(event -> {
				event.setEventConvertedPic(event.getEventConvertedPic());
				event.setEventVideo(event.getEventVideo());
			});
			
			DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
			
			model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
			model.addAttribute("events", events);

			return "User/EventsPage";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/saveGroupMailIdList")
	public String insertGroupMailId(@ModelAttribute("groupMailIds") GroupMailId_Entity groupMailIds, Model model,
			HttpSession session) {

		String result = groupMailId_Service.insertGroupMailIds(groupMailIds);

		if ("success".equals(result)) {
			return "redirect:groupMailId";
		} else {
			return "error";
		}
	}

	@GetMapping("/fetchGroupMailIdData")
	@ResponseBody
	public List<GroupMailId_Entity> getGroupMailIdDetails() {
		return groupMailId_Service.fetchGroupMailId();
	}

	@GetMapping("/InterCommDetails")
	public String viewIntercomDetails(HttpSession session, Model model) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/";
		}
		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		List<InterCommDetailsEntity> interComm = adminInterCommRepo.findAll();

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
																// true (if needed)
				}
			}
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("interComm", interComm);

		return "User/AdminInertComm";
	}

	@GetMapping("/adminHoliday")
	public String getMethodName(@RequestParam(value = "year", required = false) String year,
			Model model, HttpSession session) {
		System.out.println("hello world");
		
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/";
		}

		// Get the current year if not specified
		if (year == null) {
			year = String.valueOf(LocalDate.now().getYear());
		}

		// Fetch holiday data based on the year
		List<HolidaysList> holidayList = adminHolidayService.getHolidayListByYear(year);
		holidayList.stream().forEach(i -> System.out.println("abcd----" + i));
		// Get all years for the dropdown
		List<Integer> years = adminHolidayService.getAvailableYears();
		model.addAttribute("years", years);
		model.addAttribute("currentYear", year); // Pass the current year for dropdown selection

		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(email);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		System.out.println("permissions....." + permissions);

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
		model.addAttribute("currentYear", year);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("holidayList", holidayList);
		return "User/AdminHoliday";
	}

	@GetMapping("/viewAdminLeaves")
	public String viewAdminLeaves(@RequestParam("empid") String empid, Model model, HttpSession session) {
		
		if(session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}
		
		// Retrieve user details from session
		String email = (String) session.getAttribute("email");
		System.out.println("email---"+email);
		String loggedempId = employeeRepository.findEmpidByEmail(email);
		System.out.println("email---"+loggedempId);
		
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empid);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(loggedempId);
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

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);
		// adminDeptLeaveRequests.stream().forEach(leave->System.out.println("aaaaa---"+leave));

		if (adminDept == 0) {
			List<LeaveApplication> allDeptLeaveRequests = leaveRequestService.findByEmpidNot(loggedempId);
			List<LeaveApplication> getProccessedleaveRequestFoeSA = leaveRequestService.getProccesedleaveRequest(adminDept, empid);
			List<LeaveApplication> adminDeptRejectedLeaveRequests = leaveRequestService.getRejectedleaveRequest(adminDept, empid);
			
			model.addAttribute("usersList", allDeptLeaveRequests);
			model.addAttribute("adminDeptProccessedLeaveRequests", getProccessedleaveRequestFoeSA);
			model.addAttribute("adminDeptRejectedLeaveRequests", adminDeptRejectedLeaveRequests);
			
		} else {
			List<LeaveApplication> adminDeptLeaveRequests = leaveRequestService.getleaveRequest(adminDept, empid);
			List<LeaveApplication> adminDeptProccessedLeaveRequests = leaveRequestService.getProccesedleaveRequest(adminDept, empid);
			List<LeaveApplication> adminDeptRejectedLeaveRequests = leaveRequestService.getRejectedleaveRequest(adminDept, empid);
			
			//System.out.println("adminDeptRejectedLeaveRequests...." + adminDeptRejectedLeaveRequests);
			model.addAttribute("usersList", adminDeptLeaveRequests);
			model.addAttribute("adminDeptProccessedLeaveRequests", adminDeptProccessedLeaveRequests);
			model.addAttribute("adminDeptRejectedLeaveRequests", adminDeptRejectedLeaveRequests);
		}
		// Fetch all holidays
	    List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();
	    
	    // Define the date format used in your String dates (e.g., "yyyy-MM-dd")
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    // Get the current year and generate a list of previous years
		int currentYear = LocalDate.now().getYear();
	    // Filter holidays for the current year
	    List<Holiday_Entity> currentYearHolidays = holidays.stream()
	        .filter(holiday -> {
	            try {
	                LocalDate holidayDate = LocalDate.parse(holiday.getDate(), formatter);
	                return holidayDate.getYear() == currentYear;
	            } catch (Exception e) {
	                // Handle invalid date formats, if any
	                return false;
	            }
	        })
	        .collect(Collectors.toList()); 
	    
	    System.out.println("llkkkk"+ currentYearHolidays);
	    // Pass the filtered holidays to the model
	    model.addAttribute("holidays", currentYearHolidays);

		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		return "User/UserLeavePage";
	}

	@GetMapping("/ongoingProject")
	public String ongoingProject(@ModelAttribute("projectEntity") ProjectEntity projectEntity, Model model,
			HttpSession session) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		String emailid = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		String loggedEmailName = employeeService.loggedEmailIdName(emailid);

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();

		List<ProjectEntity> pendingProjectDetails = projectService.projectByStatus("pending");
		List<ProjectEntity> completedProjectDetails = projectService.projectByStatus("completed");
		List<ProjectEntity> unAssignedProjects = projectService.findUnassignedProjects("IT");

		List<DisplayEmployessEntity> employeNameData = employeeService.findUserByDepartmentWithFullName(adminDept); // findAllProjectsByDepartmentId
		List<ProjectEntity> newProjectDetails = projectService.projectBydepartment();
		List<ProjectEntity> findByDelayProject = projectService.findByDelayProject();
		// List<ProjectEntity> allProjects =
		// projectService.findAllProjectsByDepartmentId();

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
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

		// update the view flag value in project acceptance by user notification
		projectService.updateAcceptedProjectView();

		// Get the error message from flash attributes if it exists
		String errorMessage = (String) session.getAttribute("errorMessage");
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			session.removeAttribute("errorMessage");
		}

		// model.addAttribute("flag",totalNotificationCount);
		model.addAttribute("employeesName", employeNameData);
		model.addAttribute("emailid", emailid);
		model.addAttribute("SeniorEntity", new ProjectEntity());
		model.addAttribute("pendingProjectDetails", pendingProjectDetails);
		model.addAttribute("completedProjectDetails", completedProjectDetails);

		model.addAttribute("newProjectDetails", newProjectDetails);
		model.addAttribute("findByDelayProject", findByDelayProject);
		model.addAttribute("unAssignedProjects", unAssignedProjects);
		model.addAttribute("loggedEmailName", loggedEmailName);

		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("departments", departments);
		model.addAttribute("newCompProj_Entity", new NewCompanyProject_Entity());

		return "OngoingProject";
	}

	@GetMapping("/asset")
	public String assetAdminLeaves(Model model, HttpSession session) {

		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		System.out.println("admin dept in asset===========" + adminDept);

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empid);

		List<AsseteEntity> assetDataByEmp = asseteService.getAssetById(empid);
		List<AsseteEntity> employeesAssetDataByEmp = asseteService.getAssetByDeptId(adminDept, empid);

		// List<AsseteEntity> employeesAssetData = asseteService.getAllAsset(empid);
		List<AssetDisplayEnityt> employeesAssetData = asseteService.getAllUsersAsset(adminDept);
		List<AssetDisplayEnityt> allAssetForSA = asseteService.getAllAssetForSA();
		List<AssetDisplayEnityt> allAssetForStore = asseteService.getAllAssetForStore();

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empid);
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

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);

		List<LeaveApplication> usersList = applicationRepo.getleaveRequest(adminDept, empid);

		// Create a new AssetEntity object
		AsseteEntity assetEntity = new AsseteEntity();

		// Set the empid from empDetailsByEmpId into assetEntity
		assetEntity.setEmpid(empid);

		if (adminDept == 0) {
			model.addAttribute("employeesAssetData", allAssetForSA);
		} else if (adminDept == 74) {
			model.addAttribute("employeesAssetData", allAssetForStore);
		} else {
			model.addAttribute("employeesAssetData", employeesAssetData);
		}

		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("usersList", usersList);
		model.addAttribute("assetDataByEmp", assetDataByEmp);

		model.addAttribute("departments", departments);

		// Add the assetEntity and empDetailsByEmpId to the model
		model.addAttribute("assetEntity", assetEntity);

		return "User/Assets";
	}

	@GetMapping("/sales")
	public String viewAdminPayslip(Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
		System.out.println("permissions....." + permissions);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empid);
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

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);

		model.addAttribute("departments", departments);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("newCompProj_Entity", new NewCompanyProject_Entity());
		return "User/Sales";
	}

	/*@GetMapping("/accounts")
	public String viewAccounts(Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
		System.out.println("permissions....." + permissions);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empid);
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

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);

		model.addAttribute("departments", departments);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		return "Accounts/Accounts";
	}*/
	
	@GetMapping("/accounts")
	public String viewAccounts(Model model, HttpSession session) {
	    return "redirect:/salaryDetailsPage";
	}


	@GetMapping("/store")
	public String viewStore(Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
		System.out.println("permissions....." + permissions);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empid);
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

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empid);

		model.addAttribute("departments", departments);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		return "User/Store";
	}

	@GetMapping("/apprisal")
	public String apprisal(@RequestParam("empid") String empid, Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}

		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String LoggedEmpid = employeeRepository.findEmpidByEmail(emailid);
		String appraisalEmpId = empid;
		String empName = employeeRepository.findEmpidByEmpId(empid);
		String adminDept = employeeRepository.findDeptNameByEmpId(LoggedEmpid);

		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(LoggedEmpid);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(LoggedEmpid);
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

			System.out.println("permissions+++" + permissionEntity);
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(LoggedEmpid);

		// List<Appraisal> appraisal =
		// appraisalService.getEmpAppraisalByDept(adminDept);
		List<AppraisalForDisplay> appraisal = appraisalService.getEmpAppraisalByDept(adminDept);

		// appraisal.stream().forEach(app->System.out.println("appraisal==="+appraisal));
		// appraisalHrREpo

		Optional<AppraisalHrEntity> hrAppraisalData = appraisalHrREpo.findByEmpIdAndApprisalLinkFlag(LoggedEmpid, true);
		hrAppraisalData.ifPresent(a -> {

			model.addAttribute("hrAppraisalData", a);
		});
		Appraisal appraisalByEmpId = appraisalService.getEmpAppraisalByEmail(empid);

		if (appraisalByEmpId != null) {
			model.addAttribute("appraisalByEmpId", appraisalByEmpId);
		}
		model.addAttribute("appraisal", appraisal);
		model.addAttribute("departments", departments);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("appraisalEmpId", appraisalEmpId);
		model.addAttribute("empName", empName);
		return "User/apprisal";
	}

	@GetMapping("/employeeApprisal")
	public String employeesApprisal(@RequestParam("empid") String empid, Model model, HttpSession session) {
		if (session.getAttribute("empDetailsByEmpId") == null) {
			return "redirect:/"; // Redirect to login page
		}
		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String LoggedEmpid = employeeRepository.findEmpidByEmail(emailid);
		String adminDept = employeeRepository.findDeptNameByEmpId(LoggedEmpid);
		String appraisalEmpId = empid;

		String empName = employeeRepository.findEmpidByEmpId(empid);
		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(LoggedEmpid);
		// System.out.println("permissions....."+permissions);

		if (permissions.isPresent()) {
			PermissionsEntity permissionEntity = permissions.get();
			if (permissionEntity.isApprisalAccess()) {
				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(LoggedEmpid);
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

			System.out.println("permissions+++" + permissionEntity);
			model.addAttribute("permissions", permissionEntity);
		} else {
			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
		}

		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(LoggedEmpid);

		List<AppraisalForDisplay> appraisal = appraisalService.getEmpAppraisalByDept(adminDept);
		appraisal.stream().forEach(app -> System.out.println("appraisal===" + appraisal));

		Appraisal appraisalByEmpId = appraisalService.getEmpAppraisalByEmail(empid);

		System.out.println("empid----" + empid);
		System.out.println("appraisalByEmpId----" + appraisalByEmpId);

		if (appraisalByEmpId != null) {
			model.addAttribute("appraisalByEmpId", appraisalByEmpId);
		}

		model.addAttribute("appraisal", appraisal);
		model.addAttribute("departments", departments);
		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		model.addAttribute("appraisalEmpId", appraisalEmpId);
		model.addAttribute("empName", empName);
		return "User/employeesApprisal";
	}

	@GetMapping("/changePassword")
	public String changePassword(HttpSession session, Model model) {
	
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		System.out.println("empid,,,,,,,,,,," + empid);
		model.addAttribute("empid", empid);

		return "User/passwordChangePage";
	}
	
	  @GetMapping("/getFutureLeaveApplications/{email}")
	                    public ResponseEntity<List<LeaveApplication>> getFutureLeaveApplications(HttpSession session,@PathVariable("email") String email) {
	                          //String email = (String) session.getAttribute("email");
	                          System.out.println("Fetching leaves for userrrrrrrrr: " + email);
	                            
	                        List<LeaveApplication> futureLeaves = leaveApplicationService.getFutureLeaveApplications(email);
	                        System.out.println("futureLeaves" + futureLeaves);
	                        return ResponseEntity.ok(futureLeaves);
	                    }
}
