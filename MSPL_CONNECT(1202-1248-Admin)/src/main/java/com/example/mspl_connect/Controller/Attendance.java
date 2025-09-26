package com.example.mspl_connect.Controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;
import com.example.mspl_connect.AdminEntity.UserCheckin;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.LeaveApplicationRepository;
import com.example.mspl_connect.AdminRepo.UserCheckinRepository;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.Holiday_Entity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.AttendanceRepositoryCustom;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.LeaveApplicationRepo;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.AttendanceServiceDto;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.Holiday_Service;
import com.example.mspl_connect.Service.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Attendance {

	   @Autowired
	    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
		
		@Autowired
		private EmployeeRepository employeeRepository;
		
		 @Autowired
		 private AttendanceServiceDto attendanceService;
		 
		 @Autowired
		 private AttendanceServiceDto attendanceServiceDto;
		 
		 @Autowired
		 private Holiday_Service holiday_Service;
		 
		 @Autowired
		 private PermissionRepo permissionRepo;
		 
		 @Autowired
		 private EmployeeDetaisService detaisService;
		 
		 @Autowired
		 private AppraisalRepository appraisalRepository;
		 
		 @Autowired
		 private DepartmentRepo departmentRepo;
		 
		 @Autowired
		 private UserCheckinRepository userCheckinRepository;
		 
		 @Autowired
		 private LeaveApplicationRepository leaveApplicationRepository;
		 
		 @Autowired
		 private EmployeeDetaisService employeeDetailsService; 
		 
		 @Autowired
		 private AttendanceRepositoryCustom  attendanceRepositoryCustom;
		 
		 @Autowired
		 private ProjectService projectService;
		 
		 @Autowired
		 private LeaveApplicationRepo leaveApplicationRepo;
		 
		 @GetMapping("/attendanceLive")
		 public String myFavorites(@RequestParam("email") String email,
				   HttpSession session, Model model,
					@RequestParam(value = "year", required = false) Integer selectedYear,
					@RequestParam(value = "leaveTabActive", required = false) String leaveTabActive) {

			 System.out.println("emailmmmm--"+email); 			 
			 String loogedAdminemail = (String) session.getAttribute("email");			 
		     String empId = employeeRepository.findEmpidByEmail(email);
		     
		     
		     int adminDept = employeeRepository.findDeptIdByEmpId(empId);
			 String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		     String loogedAdminEmpId = employeeRepository.findEmpidByEmail(loogedAdminemail);
		     
		     // Fetch employee details using the empId		     
		     DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
             System.out.println(";;;" + empDetailsByEmpId);
             model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
             
             if (empDetailsByEmpId != null && empDetailsByEmpId.getDoj() != null) {
                 model.addAttribute("doj", empDetailsByEmpId.getDoj().toString()); // Ensure it's a string
                 System.out.println("DOJ from backend: " + empDetailsByEmpId.getDoj());
             } else {
                 model.addAttribute("doj", ""); // Pass an empty string instead of null
             }
             
             
             	System.out.println("1");
		     DisplayEmployessEntity loogedAdminDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);//looged admin data for header
		     System.out.println("2");
		     
		     if ("leaveTabActive".equals(leaveTabActive)) { // Check if the value matches
		        model.addAttribute("leaveTabActive"+leaveTabActive);
		     }
		     
		  // Get the current year and generate a list of previous years
				int currentYear = LocalDate.now().getYear();
				List<Integer> years = new ArrayList<>();
				for (int i = currentYear; i >= 2024; i--) {
					years.add(i);
				}
				model.addAttribute("years", years);

				// Use the selected year or default to the current year
				if (selectedYear == null) {
					selectedYear = currentYear;
				}
				
		     // List of all months
		     List<String> months = Arrays.asList(
		         "January", "February", "March", "April", "May", "June",
		         "July", "August", "September", "October", "November", "December"
		     );
		     
		     // Get the current month value (1 for January, 2 for February, etc.)
		     int currentMonthValue = LocalDate.now().getMonthValue();
		     Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		     System.out.println("3");
		     // System.out.println("empId----"+empId);
		     // System.out.println("permissions----"+permissions);
			 Boolean attendancePermission = false;
			 
			    if(permissions.isPresent()) {
			    	  
			        PermissionsEntity permissionEntity = permissions.get();
			        attendancePermission = permissionEntity.getAttendance(); 

			        model.addAttribute("permissions", permissionEntity);
			    } else {
			        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			    }
			    
			     // Filter the months list to include months up to the current month
					/*
					 * List<String> filteredMonths = months.stream() .limit(currentMonthValue)
					 * .collect(Collectors.toList());
					 */
			    
			    // Filter months for the selected year (show full year if it's a past year)
				List<String> filteredMonths = selectedYear == currentYear
						? months.stream().limit(currentMonthValue).collect(Collectors.toList())
						: months; // For previous years, show all months

		     System.out.println("4");
		     
		     List<String> ongoingProjectName=projectService.fetProjectName();
		     
		        // Fetch attendance data directly from the service
			    List<AttendanceDTO> attendanceData = attendanceService.getAllAttendance(empId);
			    System.out.println("5");
			    System.out.println("88908766"+attendanceData);
			    
			    //update leave notification status change value when user visit attendance page
			    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb");
			    leaveApplicationRepo.updateNotificationStatus(loogedAdminemail);
			    
				// Fetch all holidays
			    List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();
			    
			    // Define the date format used in your String dates (e.g., "yyyy-MM-dd")
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    
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
			    model.addAttribute("attendanceData", attendanceData); // Add attendance data to the model

			    model.addAttribute("attendancePermission", attendancePermission);
			    model.addAttribute("ongoingProjectName",ongoingProjectName);
			 
			     // Add filtered months to the model
			     model.addAttribute("months", filteredMonths);
			     model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
			     model.addAttribute("loogedAdminDetailsByEmpId", loogedAdminDetailsByEmpId);
			     // System.out.println("empDetailsByEmpId........+"+empDetailsByEmpId);
			     
			     return "Attendance";		     
		 }
		 
		 @GetMapping("/attendanceData")
			@ResponseBody
			public Map<String, Object> getAttendanceData(HttpSession session, @RequestParam("year") int selectedYear,@RequestParam("email") String email) {
				// Retrieve user details from session
				//String email = (String) session.getAttribute("email");
				System.out.println("ooooo"+email);
				if (email == null) {
					throw new IllegalStateException("User session expired.");
				}

				String empId = employeeRepository.findEmpidByEmail(email);

				// Fetch attendance data for the selected year
				// List<AttendanceDTO> attendanceData =
				// attendanceService.getAttendanceByYear(empId, selectedYear);
				// List<AttendanceDTO> attendanceData =
				// attendanceService.getAllAttendance(empId);
				// Prepare months for the selected year
				List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
						"September", "October", "November", "December");
				List<String> filteredMonths = selectedYear == LocalDate.now().getYear()
						? months.subList(0, LocalDate.now().getMonthValue())
						: months;

				// Prepare the response
				Map<String, Object> response = new HashMap<>();
				// response.put("attendanceData", attendanceData);
				response.put("months", filteredMonths);

				return response;
			}
		// Helper method to filter leave records for the current financial year
			private List<LeaveUtilized> filterLeaveRecordsForCurrentFinancialYear(List<LeaveUtilized> leaveRecords) {
				List<LeaveUtilized> filteredRecords = new ArrayList<>();
				LocalDate today = LocalDate.now();
				int currentYear = today.getYear();
				LocalDate startOfFinancialYear = LocalDate.of(currentYear, 3, 26);
				LocalDate endOfFinancialYear = LocalDate.of(currentYear + 1, 3, 25);

				if (today.isBefore(startOfFinancialYear)) {
					currentYear--;
					startOfFinancialYear = LocalDate.of(currentYear, 3, 26);
					endOfFinancialYear = LocalDate.of(currentYear + 1, 3, 25);
				}

				for (LeaveUtilized record : leaveRecords) {
					LocalDate leaveDate = record.getLastUpdated();
					if (leaveDate != null && !leaveDate.isBefore(startOfFinancialYear)
							&& !leaveDate.isAfter(endOfFinancialYear)) {
						filteredRecords.add(record);
					}
				}
				return filteredRecords;
			}
			@GetMapping("/attendanceLive/{month}/{email}")
			 @ResponseBody
			 public List<AttendanceDTO> getAttendanceDataForMonth(
			     @PathVariable("month") String month,
			     @PathVariable("email") String email) {

				System.out.println("Received month path variable: " + month);

				// Retrieve user details from session
				//String email = (String) session.getAttribute("email");
				System.out.println("User login: " + email);
				String empId = employeeRepository.findEmpidByEmail(email);
				System.out.println("User empid:-- " + empId);

				
				 // Retrieve probation completion date
			    String probationDateString = employeeDetailsService.getProbationCompletionDate(email);
			    LocalDate probationDate = LocalDate.parse(probationDateString); // Ensure correct format
			    LocalDate twoYearsAfterProbation = probationDate.plusYears(2);
			    boolean isEligibleFor2ndAnd4thSaturday = LocalDate.now().isAfter(twoYearsAfterProbation) || LocalDate.now().isEqual(twoYearsAfterProbation);

			    
			 // Retrieve list of holidays
			    List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();
			    Set<LocalDate> holidayDates = holidays.stream()
			                                          .map(holiday -> LocalDate.parse(holiday.getDate())) // Assuming the date is in String format
			                                          .collect(Collectors.toSet());

				// Get filtered attendance data
				List<AttendanceDTO> data = attendanceService.getFilteredAttendanceForMonth(month, empId);
				
				// Adjust data for 2nd and 4th Saturdays and holidays
			    data.forEach(entry -> {
			        LocalDate entryDate = LocalDate.parse(entry.getDate()); // Parse date from DTO
			        boolean isHoliday = holidayDates.contains(entryDate);

			        if (isHoliday || (isEligibleFor2ndAnd4thSaturday && isSecondOrFourthSaturday(entryDate))) {
			            entry.setUndertime("-"); // Exclude undertime for holidays and eligible Saturdays
			        }
			    });
			    
				System.out.println("Number of records returned by service:lll " + data.size());

				// Return the data as JSON
				return data;
			}


		 @PostMapping("/checkin/{email}")
		 public ResponseEntity<Map<String, String>> checkIn(@RequestBody AttendanceDTO attendanceDTO,HttpSession session,@PathVariable("email") String email) {

			 //String email = (String) session.getAttribute("email");
			 System.out.println("user login:::::"+email);
			 String empId = employeeRepository.findEmpidByEmail(email);
			 System.out.println("user empid "+empId);

		     System.out.println("Received attendance data:");
		     System.out.println("Employee ID (eid): " + attendanceDTO.getEid());
		     System.out.println("Check-in Time (pt): " + attendanceDTO.getInTime());
		     System.out.println("Date (pd): " + attendanceDTO.getDate());
		     System.out.println("Combined Date and Time (pdt): " + attendanceDTO.getPdt());
		     
		     
		     attendanceDTO.setEid(empId);
		     
		     System.out.println("attendanceDTO------"+attendanceDTO);
		     // Call your service to save the attendance record
		     attendanceService.saveAttendance(attendanceDTO);
		     
		     // Create a new UserCheckin object
		     UserCheckin userCheckin = new UserCheckin();
		     userCheckin.setEmpId(empId);
		     userCheckin.setCheckinDate(attendanceDTO.getDate()); // Use the date from AttendanceDTO
		     userCheckin.setCheckinFlag(1); // Set check-in flag to 1
		     userCheckin.setCheckoutFlag(0); // Set check-out flag to 0

		     // Save the user check-in record
		     userCheckinRepository.save(userCheckin);
		     // Create a response object
		     Map<String, String> response = new HashMap<>();
		     response.put("message", "Check-in recorded successfully.");
		     
		     return ResponseEntity.ok(response);
		 }

		 @PostMapping("/checkout/{email}")
		 public ResponseEntity<Map<String, String>> checkout(@RequestBody AttendanceDTO attendanceDTO, HttpSession session,@PathVariable("email") String email) {
		     // Enhanced logging
		     //String email = (String) session.getAttribute("email");
		     System.out.println("User login////// " + email);
		     String empId = employeeRepository.findEmpidByEmail(email);
		     System.out.println("User empId: " + empId);

		     System.out.println("Received attendance data:");
		     System.out.println("Employee ID (eid): " + attendanceDTO.getEid());
		     System.out.println("Check-in Time (pt): " + attendanceDTO.getInTime());
		     System.out.println("Date (pd): " + attendanceDTO.getDate());
		     System.out.println("Combined Date and Time (pdt): " + attendanceDTO.getPdt()); 

		     attendanceDTO.setEid(empId);
		     // Call your service to save the attendance record
		     attendanceService.saveAttendance(attendanceDTO);

		     // Check if a UserCheckin entry already exists for the given empId and date
		  // Check if a UserCheckin entry already exists for the given empId and date (as String)
		     Optional<UserCheckin> existingCheckin = userCheckinRepository.findFirstByEmpIdAndCheckinDate(empId, attendanceDTO.getDate());
	  
		     Map<String, String> response = new HashMap<>();
		     
		     if (existingCheckin.isPresent()) {
		         // Update the checkout flag
		         UserCheckin userCheckin = existingCheckin.get();
		         userCheckin.setCheckoutFlag(1); // Set check-out flag to 1
		         userCheckinRepository.save(userCheckin);
		         response.put("message", "Checkout recorded successfully.");
		     } else {
		         // Handle the case where no check-in record exists for the given date
		         response.put("message", "No check-in record found for the given date.");
		     }
		     
		     return ResponseEntity.ok(response);
		 }

	 @GetMapping("/attendanceCalendar/{year}/{email}")
	 public ResponseEntity<List<AttendanceDTO>> getAttendanceForYear(@PathVariable int year,@PathVariable String email, HttpSession session) {
	     try {
	    	    // Retrieve user details from session
			    System.out.println("Request received for year: " + year);

			    // String email = (String) session.getAttribute("email");
			    System.out.println("user login............. "+email);
			    String empId = employeeRepository.findEmpidByEmail(email);
			    System.out.println("user empidcalendar "+empId);

	         List<AttendanceDTO> attendance = attendanceServiceDto.getAttendanceForYear(year, empId);
	         //attendance.stream().forEach(i->System.out.println("iiiiiiiiii-"+i));
	         
	         return ResponseEntity.ok(attendance);
	     } catch (Exception e) {
	         System.err.println("Error fetching attendance for year: " + e.getMessage());
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	 }
	 
	 @GetMapping("/attendanceExcelData")
	 public ResponseEntity<?> getAttendanceForYearAndEmpId(
	         @RequestParam("year") int year,
	         @RequestParam("empId") String empId,
	         @RequestParam("fromDate") String fromDate,
	         @RequestParam("toDate") String toDate
			 ){
	     try {
	    	   
			 //String empId = employeeRepository.findEmpidByEmail(email);
			 System.out.println("user empidcalendar "+empId);
			 System.out.println("From date==="+fromDate);
			 System.out.println("To date==="+toDate);
			 //String fromDate="2024-11-04";
			 //String toDate="2024-12-21";
			 
	         List<AttendanceDTO> attendance = attendanceServiceDto.getAttendanceForYearByEmpid(year, empId,fromDate,toDate);
	         attendance.stream().forEach(i->System.out.println("..........."+i));
	         return ResponseEntity.ok(attendance);
	         
	     } catch (Exception e) {
	         System.err.println("Error fetching attendance for year: " + e.getMessage());
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	 }
	 
	 
		@GetMapping("/getProbationDate")
		public ResponseEntity<Map<String, String>> getProbationDate(HttpSession session,@RequestParam String email) {

			// Retrieve the user's email from the session
			//String email = (String) session.getAttribute("email");
			
			System.out.println("User login email: " + email);
			
			// Fetch employee ID using the email
			String empId = employeeRepository.findEmpidByEmail(email);
			System.out.println("User empId: " + empId);

			// Get the probation completion date
			//String probationDate = employeeDetailsService.getProbationCompletionDate(email); 
			
			// Get the alternative Saturday effective date
		    String alternativeSaturdayDate = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);

			// Prepare the response
			Map<String, String> response = new HashMap<>();
			response.put("probation_completed_date", alternativeSaturdayDate);

			// Return the response wrapped in ResponseEntity
			return ResponseEntity.ok(response);
			
		}

	 
	 @GetMapping("/holidayCalendar")
		public ResponseEntity<List<Holiday_Entity>> getAllHolidays() {
		    try {
		        List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();
		        //System.out.println("holidayssssssss ==== "+holidays);
		        return ResponseEntity.ok(holidays);
		    } catch (Exception e) {
		        System.err.println("Error fetching holidays: " + e.getMessage());
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}

	 
	 @GetMapping("/employee-leaves-attendance")
	 @ResponseBody
	 public Map<String, Object> getEmployeeLeaves(HttpSession session,@RequestParam("email") String email) {
		 
		 System.out.println("email0000000000"+email);
	     //String email = (String) session.getAttribute("email");
	     String empId = employeeRepository.findEmpidByEmail(email);
	     
	     // Fetch only the leave applications that are approved
	     List<LeaveApplication> approvedLeaveApplications = leaveApplicationRepository.findByEmpidAndApprovedstatus(empId, "Approved");

	     // Format leave data for frontend
	     List<Map<String, String>> leaveDetails = new ArrayList<>();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	     for (LeaveApplication leave : approvedLeaveApplications) {
	         LocalDate startDate = LocalDate.parse(leave.getFrom_date(), formatter);
	         LocalDate endDate = LocalDate.parse(leave.getTo_date(), formatter);
	         
	         while (!startDate.isAfter(endDate)) {
	             Map<String, String> details = new HashMap<>();
	             details.put("date", startDate.toString());
	             details.put("leaveType", leave.getLeaveType());
	             details.put("reason", leave.getReason());
	             leaveDetails.add(details);
	             startDate = startDate.plusDays(1); // Increment day
	         }
	     }

	     Map<String, Object> response = new HashMap<>();
	     response.put("leaveDetails", leaveDetails);
	     return response;
	 }


	 

		@GetMapping("/check")
		public ResponseEntity<Map<String, Boolean>> checkAttendance(@RequestParam String date,@RequestParam String email, HttpSession session) {
			// Fetch the email from the session
			//String email = (String) session.getAttribute("email");
			System.out.println("Starting attendance check...");
			System.out.println("Received date parameter: " + date);
			System.out.println("Checking attendance for email: " + email);

			// Retrieve the employee ID from the email
			String empId = employeeRepository.findEmpidByEmail(email);
			System.out.println("Retrieved Employee ID for email (" + email + "): " + empId);

			// Fetch attendance records for the given date and employee ID
			System.out.println("Fetching attendance records for date: " + date + " and Employee ID: " + empId);
			List<AttendanceDTO> attendance = attendanceRepositoryCustom.findAttendanceByDateAndEmployee(date, empId);
			System.out.println("Attendance records retrieved: " + attendance);

			// Check if attendance exists in the attendance list
			if (!attendance.isEmpty()) {
				System.out.println("Attendance found for Employee ID: " + empId + " on date: " + date);

				// Check if the attendance record exists in the userCheckin table
				List<UserCheckin> existingUserCheckin = userCheckinRepository.findByEmpIdAndCheckinDate(empId, date);
				System.out.println("Existing UserCheckin records: " + existingUserCheckin);

				// Insert a new record if it doesn't exist
				if (existingUserCheckin.isEmpty()) {
					System.out.println("No UserCheckin record exists for Employee ID: " + empId + " on date: " + date);
					UserCheckin newAttendanceRecord = new UserCheckin();
					newAttendanceRecord.setEmpId(empId);
					newAttendanceRecord.setCheckinDate(date);
					newAttendanceRecord.setCheckinFlag(1); // Checked in
					newAttendanceRecord.setCheckoutFlag(0); // Default to no checkout
					userCheckinRepository.save(newAttendanceRecord);
					System.out.println("New UserCheckin record created for Employee ID: " + empId + " on date: " + date);
				} else {
					System.out.println("UserCheckin record already exists for Employee ID: " + empId + " on date: " + date);
				}
			} else {
				System.out.println("No attendance records found for Employee ID: " + empId + " on date: " + date);
			}

			// Return response to frontend
			Map<String, Boolean> response = new HashMap<>();
			response.put("exists", !attendance.isEmpty());
			System.out.println("Final response sent to frontend: " + response);

			return ResponseEntity.ok(response);
		}
		@GetMapping("/attendanceLiveSecond/{month}")
		@ResponseBody
		public List<AttendanceDTO> getAttendanceDataForMonth(@PathVariable("month") String month,
		                                                     @RequestParam(value = "year", required = false) Integer year,
		                                                     @RequestParam(value = "email", required = false) String email,		                                                     
		                                                     HttpSession session) {

		    System.out.println("Received month path variable: " + month);
		    
		    

		    // Retrieve user details from session
		    //String email = (String) session.getAttribute("email");
		    System.out.println("User loginxxxxxx " + email);
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("User empid:-- " + empId);

		    // Use current year if no year is provided
		    if (year == null) {
		        year = java.time.LocalDate.now().getYear();
		    }
		    System.out.println("Year used: " + year);
		    // Retrieve probation completion date
		    String probationDateString = employeeDetailsService.getProbationCompletionDate(email);
		    LocalDate probationDate = LocalDate.parse(probationDateString); // Ensure correct format
		    LocalDate twoYearsAfterProbation = probationDate.plusYears(2);
		    boolean isEligibleFor2ndAnd4thSaturday = LocalDate.now().isAfter(twoYearsAfterProbation) || LocalDate.now().isEqual(twoYearsAfterProbation);

		 // Retrieve list of holidays
		    List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();
		    Set<LocalDate> holidayDates = holidays.stream()
		                                          .map(holiday -> LocalDate.parse(holiday.getDate())) // Assuming the date is in String format
		                                          .collect(Collectors.toSet());

		    // Get filtered attendance data
		    List<AttendanceDTO> data = attendanceService.getFilteredAttendanceForMonthSecond(month, empId, year);
		    
		    // Adjust data for 2nd and 4th Saturdays and holidays
		    data.forEach(entry -> {
		        LocalDate entryDate = LocalDate.parse(entry.getDate()); // Parse date from DTO
		        boolean isHoliday = holidayDates.contains(entryDate);

		        if (isHoliday || (isEligibleFor2ndAnd4thSaturday && isSecondOrFourthSaturday(entryDate))) {
		            entry.setUndertime("-"); // Exclude undertime for holidays and eligible Saturdays
		        }
		    });
		    
		    System.out.println("Number of records returned by service: " + data.size());

		    // Return the data as JSON
		    return data;
		}

		private boolean isSecondOrFourthSaturday(LocalDate date) {
		    if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
		        int dayOfMonth = date.getDayOfMonth();
		        int weekOfMonth = (dayOfMonth - 1) / 7 + 1;
		        return weekOfMonth == 2 || weekOfMonth == 4;
		    }
		    return false;
		}

		
		

}
