package com.example.mspl_connect.AdminController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

import com.example.mspl_connect.AdminEntity.EarnedLeave;
import com.example.mspl_connect.AdminEntity.EarnedLeaveDTO;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;
import com.example.mspl_connect.AdminRepo.EarnedLeaveRepository;
import com.example.mspl_connect.AdminRepo.LeaveApplicationRepository;
import com.example.mspl_connect.AdminRepo.LeaveUtilizedRepository;
import com.example.mspl_connect.AdminRepo.MaternityLeaveRepository;
import com.example.mspl_connect.AdminService.EarnedLeaveBalanceDataService;
import com.example.mspl_connect.AdminService.EarnedLeaveService;
import com.example.mspl_connect.AdminService.LeaveApplicationService;
import com.example.mspl_connect.AdminService.LeaveRequestService;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.Holiday_Entity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.Holiday_Repo;
import com.example.mspl_connect.Service.AttendanceServiceDto;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.Holiday_Service;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController {

	   @Autowired
	   private LeaveApplicationRepository leaveApplicationRepository;

	   @Autowired
	   private LeaveApplicationService leaveApplicationService;

	   @Autowired
	   private EmployeeRepository employeeRepository;


	   @Autowired
	   private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	   @Autowired
		private DepartmentRepo departmentRepo;
		
	   @Autowired
		 private LeaveUtilizedRepository leaveUtilizedRepository;
	   @Autowired
		 private AttendanceServiceDto attendanceService;
		 
	   @Autowired
	   private AttendanceServiceDto attendanceServiceDto;
	
	   @Autowired
	   private Holiday_Service holiday_Service;
			
	   @Autowired
	   private Holiday_Repo holidayRepository; // Assuming you have a repository for the holidays

	   @Autowired
	   private EarnedLeaveService earnedLeaveService;
			 
	   @Autowired
	   private EarnedLeaveBalanceDataService earnedLeaveBalanceDataService;
	   
	   @Autowired
	   private LeaveRequestService leaveRequestService;
	 
@Autowired
private EarnedLeaveRepository earnedLeaveRepo;
	   


@Autowired
private MaternityLeaveRepository maternityLeaveRepository;

@Autowired
private EmployeeDetaisService employeeDetailsService;

/*@GetMapping("/employee-leaves")
@ResponseBody
public Map<String, Object> getEmployeeLeaves(HttpSession session, Model model) {
    String email = (String) session.getAttribute("email");
    System.out.println("Fetching leaves for user: " + email);
    
    String empId = employeeRepository.findEmpidByEmail(email);
    System.out.println("Employee ID retrieved: " + empId);

    DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
    List<Object[]> teamLeadAndCoordinator = employeeWitFullDetailes.findTeamLeadAndCoordinatorNamesByEmpid(empId);
    
    System.out.println("Employee Details: " + empDetails);
    System.out.println("Team Lead and Coordinator: " + teamLeadAndCoordinator);

    Map<String, Object> response = new HashMap<>();
    response.put("empDetails", empDetails);
    response.put("teamLeadAndCoordinator", teamLeadAndCoordinator);

    List<LeaveApplication> leaveApplications = leaveApplicationRepository.findByEmpid(empId);
    System.out.println("Number of leave applications fetched: " + leaveApplications.size());

    for (LeaveApplication leaveApplication : leaveApplications) {
        if ("Approved".equals(leaveApplication.getApprovedstatus()) && !leaveApplication.isProcessed()) {
            System.out.println("Updating usage for approved leave application: " + leaveApplication);
            updateLeaveUsage(leaveApplication); // Existing logic for SL and CL
            
            // Check for eL leave type
            if ("EL".equals(leaveApplication.getLeaveType())) {
                System.out.println("Updating earned leave usage...");
                updateEarnedLeaveUsage(leaveApplication); // New method for EL
            }
        } else if ("Rejected".equals(leaveApplication.getApprovedstatus()) && leaveApplication.isProcessed()) {
            System.out.println("Reverting usage for rejected leave application: " + leaveApplication);
            revertLeaveUsage(leaveApplication);
            
            // Check for EL leave type when rejected
            if ("EL".equals(leaveApplication.getLeaveType())) {
                System.out.println("Reverting earned leave usage...");
                revertEarnedLeaveUsage(leaveApplication); // Implement this method to revert EL usage
            }
        }
    }

    response.put("leaveApplications", leaveApplications);
    System.out.println("Completed fetching leaves for user: " + email);
    
    return response;
}*/

/*@GetMapping("/employee-leaves/{email}")
@ResponseBody
public Map<String, Object> getEmployeeLeaves(HttpSession session, Model model, @PathVariable("email") String email) {
    //String email = (String) session.getAttribute("email");
    System.out.println("Fetching leaves for user ///// " + email);
    
    String empId = employeeRepository.findEmpidByEmail(email);
    System.out.println("Employee ID retrieved: " + empId);

    DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
    List<Object[]> teamLeadAndCoordinator = employeeWitFullDetailes.findTeamLeadAndCoordinatorNamesByEmpid(empId);
    System.out.println("teamLeadAndCoordinator----"+teamLeadAndCoordinator);
    
    for (Object[] record : teamLeadAndCoordinator) {
        System.out.print("Record: ");
        for (Object field : record) {
            System.out.print("aaaaa="+field);
        }
        System.out.println(); // For a new line after each record
    }

    
	//resetLeaveRequestCount(email);
    System.out.println("Employee Details: " + empDetails);
    System.out.println("Team Lead and Coordinator: " + teamLeadAndCoordinator);

    Map<String, Object> response = new HashMap<>();
    response.put("empDetails", empDetails);
    response.put("teamLeadAndCoordinator", teamLeadAndCoordinator);

    List<LeaveApplication> leaveApplications = leaveApplicationRepository.findByEmpid(empId);
    System.out.println("Number of leave applications fetched: " + leaveApplications.size());
    leaveApplications.stream().forEach(i-> System.out.println("Number of leave applications fetched: " + leaveApplications));
    

    // Define the current financial year dates
    LocalDate currentDate = LocalDate.now();
    LocalDate startOfFinancialYear = LocalDate.of(currentDate.getYear(), 3, 26);
    LocalDate endOfFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 25);
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date format is yyyy-MM-dd

    for (LeaveApplication leaveApplication : leaveApplications) {
        // Parse fromDate and toDate
        LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date(), dateFormatter);
        LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date(), dateFormatter);
        
        // Check if the leave period falls within the current financial year
        boolean isWithinFinancialYear = (fromDate.isBefore(endOfFinancialYear) && toDate.isAfter(startOfFinancialYear));

        if (isWithinFinancialYear) {
            if ("Approved".equals(leaveApplication.getApprovedstatus()) && !leaveApplication.getProcessed()) {
                System.out.println("Updating usage for approved leave application: " + leaveApplication);
                updateLeaveUsage(leaveApplication); // Existing logic for SL and CL
                
                // Check for eL leave type
                if ("EL".equals(leaveApplication.getLeaveType())) {
                    System.out.println("Updating earned leave usage...");
                    updateEarnedLeaveUsage(leaveApplication); // New method for EL
                }
            } else if ("Rejected".equals(leaveApplication.getApprovedstatus()) && leaveApplication.getProcessed()) {
                System.out.println("Reverting usage for rejected leave application: " + leaveApplication);
                revertLeaveUsage(leaveApplication);
                
                // Check for EL leave type when rejected
                if ("EL".equals(leaveApplication.getLeaveType())) {
                    System.out.println("Reverting earned leave usage...");
                    revertEarnedLeaveUsage(leaveApplication); // Implement this method to revert EL usage
                }
            }
        } else {
            System.out.println("Skipping leave application outside the current financial year: " + leaveApplication);
        }
    }

    response.put("leaveApplications", leaveApplications);
    System.out.println(" Completed fetching leaves for user: " + response);
    
    return response;
}
*/

@GetMapping("/employee-leaves/{email}")
@ResponseBody
public Map<String, Object> getEmployeeLeaves(HttpSession session, Model model, @PathVariable("email") String email) {
    //String email = (String) session.getAttribute("email");
    //System.out.println("Fetching leaves for user------ " + email);
    
    String empId = employeeRepository.findEmpidByEmail(email);
    System.out.println("Employee ID retrieved: " + empId);

    DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
    List<Object[]> teamLeadAndCoordinator = employeeWitFullDetailes.findTeamLeadAndCoordinatorNamesByEmpid(empId);
    
	//resetLeaveRequestCount(email);
    System.out.println("Employee Details: " + empDetails);
    System.out.println("Team Lead and Coordinator: " + teamLeadAndCoordinator.toString());

    Map<String, Object> response = new HashMap<>();
    response.put("empDetails", empDetails);
    response.put("teamLeadAndCoordinator", teamLeadAndCoordinator);

    List<LeaveApplication> leaveApplications = leaveApplicationRepository.findByEmpidOrderByIdDesc(empId);
    leaveApplications.stream().forEach(i->System.out.println(i));
    System.out.println("Number of leave applications fetched: " + leaveApplications.size());    

	/*
	 * // Define the current financial year dates LocalDate currentDate =
	 * LocalDate.now(); LocalDate startOfFinancialYear =
	 * LocalDate.of(currentDate.getYear(), 3, 26); LocalDate endOfFinancialYear =
	 * LocalDate.of(currentDate.getYear() + 1, 3, 25); DateTimeFormatter
	 * dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the
	 * date format is yyyy-MM-dd
	 */
    
    
    // Define the current financial year dates dynamically
       LocalDate currentDate = LocalDate.now();
       LocalDate startOfFinancialYear;
       LocalDate endOfFinancialYear;

       if (currentDate.isBefore(LocalDate.of(currentDate.getYear(), 3, 26))) {
           startOfFinancialYear = LocalDate.of(currentDate.getYear() - 1, 3, 26);
           endOfFinancialYear = LocalDate.of(currentDate.getYear(), 3, 25);
       } else {
           startOfFinancialYear = LocalDate.of(currentDate.getYear(), 3, 26);
           endOfFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 25);
       }

       DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       System.out.println("startOfFinancialYear: " + startOfFinancialYear + ", endOfFinancialYear: " + endOfFinancialYear);

    for (LeaveApplication leaveApplication : leaveApplications) {
        // Parse fromDate and toDate
        LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date(), dateFormatter);
        LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date(), dateFormatter);
        
        // Check if the leave period falls within the current financial year
        boolean isWithinFinancialYear = (fromDate.isBefore(endOfFinancialYear) && toDate.isAfter(startOfFinancialYear));

        if (isWithinFinancialYear) {
            if ("Approved".equals(leaveApplication.getApprovedstatus()) && !leaveApplication.getProcessed()) {
                System.out.println("Updating usage for approved leave application: " + leaveApplication);
                updateLeaveUsage(leaveApplication); // Existing logic for SL and CL
                
                // Check for eL leave type
                if ("EL".equals(leaveApplication.getLeaveType())) {
                    System.out.println("Updating earned leave usage...");
                    updateEarnedLeaveUsage(leaveApplication); // New method for EL
                }
            } else if ("Rejected".equals(leaveApplication.getApprovedstatus()) && leaveApplication.getProcessed()) {
                System.out.println("Reverting usage for rejected leave application: " + leaveApplication);
                revertLeaveUsage(leaveApplication);
                
                // Check for EL leave type when rejected
                if ("EL".equals(leaveApplication.getLeaveType())) {
                    System.out.println("Reverting earned leave usage...");
                    revertEarnedLeaveUsage(leaveApplication); // Implement this method to revert EL usage
                }
            }
        } else {
            System.out.println("Skipping leave application outside the current financial year: " + leaveApplication);
        }
    }

    response.put("leaveApplications", leaveApplications);
    System.out.println("Completed fetching leaves for user: " + leaveApplications);    
    return response;
    
}



private void revertEarnedLeaveUsage(LeaveApplication leaveApplication) {
    System.out.println("=== Reverting Earned Leave (EL) Usage ===");
    System.out.println("Employee Email: " + leaveApplication.getEmployeeEmail());
    System.out.println("Financial Year: " + leaveApplication.getFinancialYear());
    System.out.println("Leave Type: " + leaveApplication.getLeaveType());

    LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date());
    LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date());
    System.out.println("Leave Period: " + fromDate + " to " + toDate);
    LocalDate today = LocalDate.now();
    System.out.println("Leave Period: " + fromDate + " to " + toDate);
    System.out.println("Today's Date: " + today);

 // Ensure leave deduction only occurs after the leave period has ended
    if (!today.isAfter(toDate)) {
        System.out.println("Leave period has not ended yet. Leave deduction will occur after: " + toDate);
        return;
    }
    
    
    double revertedDays = 0;

    if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        //revertedDays = 0.5;
        LocalDate halfDayDate = fromDate; // Assuming half-day leave is for a single date
        boolean isPresent = checkAttendance(leaveApplication.getEmpid(), halfDayDate);
        
        if (isPresent) {
            // Fetch total hours worked for the half-day date
            double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(halfDayDate, leaveApplication.getEmpid());
            System.out.println("Total Hours Worked on " + halfDayDate + ": " + totalHoursWorked);

            if (totalHoursWorked < 6 && totalHoursWorked != 0) {
            	revertedDays = 0.5;
                System.out.println("Total hours less than 6. Deducting 0.5 leave.");
            } else {
                System.out.println("Total hours are 6 or more. Treating as full-day attendance. No leave deduction.");
            }
        } else {
            System.out.println("No attendance marked. Skipping leave deduction for half-day leave.");
           // usedDays = 0.5;
        }
        System.out.println("Half-day leave detected. Reverted days: " + revertedDays);
    } else {
        System.out.println("Reverting EL days based on attendance...");
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            boolean isPresent = checkAttendance(leaveApplication.getEmpid(), date);
            if (!isPresent) {
                System.out.println("Date: " + date + " - Previously deducted for leave. Reverting this day.");
                revertedDays++;
            } else {
            	 // Attendance is marked, so check the total hours worked
                double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(date, leaveApplication.getEmpid());
                System.out.println("Total Hours Worked on " + date + ": " + totalHoursWorked);

                if (totalHoursWorked < 6) {
                    // Only revert leave if the total hours worked is less than 6
                    System.out.println("Total hours less than 6. Adding to revert days.");
                    revertedDays++;
                } else {
                    System.out.println("Total hours are 6 or more. Skipping leave revert for this date.");
                }
                //System.out.println("Date: " + date + " - Attendance marked. Skipping leave revert.");
                //System.out.println("Date: " + date + " - Attendance already marked. No deduction to revert.");
            }
        }
    }

    if (revertedDays == 0) {
        System.out.println("No EL days to revert. Exiting method.");
        return;
    }

    String monthYear = fromDate.getYear() + "-" + String.format("%02d", fromDate.getMonthValue());
    System.out.println("Month-Year: " + monthYear);

    //EarnedLeave earnedLeave = earnedLeaveRepo.findByEmailAndMonthYear(leaveApplication.getEmployeeEmail(), monthYear);
    EarnedLeave earnedLeave = earnedLeaveRepo.findByEmail(leaveApplication.getEmployeeEmail());
    if (earnedLeave != null) {
        System.out.println("Found EarnedLeave entry to revert: " + earnedLeave);

        double newElUsed = (earnedLeave.getElUsed() == null ? 0 : earnedLeave.getElUsed()) - revertedDays;
        earnedLeave.setElUsed(newElUsed);
        earnedLeave.setElremaining(earnedLeave.getEarnedLeave() - newElUsed);
        earnedLeave.setLastIncrementDate(LocalDate.now().toString());

        earnedLeaveRepo.save(earnedLeave);
        System.out.println("EarnedLeave entry reverted successfully.");

        // Mark the leave application as unprocessed
        leaveApplication.setProcessed(false);
        leaveApplicationRepository.save(leaveApplication);
        System.out.println("LeaveApplication marked as unprocessed.");
    } else {
        System.out.println("EarnedLeave entry not found for the employee: " + leaveApplication.getEmployeeEmail() +
                           " | Month-Year: " + monthYear);
    }

    System.out.println("=== Earned Leave (EL) Usage Revert Completed ===");
}


private void updateEarnedLeaveUsage(LeaveApplication leaveApplication) {
    System.out.println("=== Updating Earned Leave (EL) Usage ===");
    System.out.println("Employee Email: " + leaveApplication.getEmployeeEmail());
    System.out.println("Financial Year: " + leaveApplication.getFinancialYear());
    System.out.println("Leave Type: " + leaveApplication.getLeaveType());

    LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date());
    LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date());
    LocalDate today = LocalDate.now();
    System.out.println("Leave Period: " + fromDate + " to " + toDate);
    System.out.println("Today's Date: " + today);

 // Ensure leave deduction only occurs after the leave period has ended
    if (!today.isAfter(toDate)) {
        System.out.println("Leave period has not ended yet. Leave deduction will occur after: " + toDate);
        return;
    }
    double usedDays = 0;


    if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        //usedDays = 0.5;
        System.out.println("Half-day leave detected. Used days: " + usedDays);
        LocalDate halfDayDate = fromDate; // Assuming half-day leave is for a single date
        boolean isPresent = checkAttendance(leaveApplication.getEmpid(), halfDayDate);
        
        if (isPresent) {
            // Fetch total hours worked for the half-day date
            double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(halfDayDate, leaveApplication.getEmpid());
            System.out.println("Total Hours Worked on " + halfDayDate + ": " + totalHoursWorked);

            if (totalHoursWorked < 6 && totalHoursWorked != 0) {
                usedDays = 0.5;
                System.out.println("Total hours less than 6. Deducting 0.5 leave.");
            } else {
                System.out.println("Total hours are 6 or more. Treating as full-day attendance. No leave deduction.");
            }
        } else {
            System.out.println("No attendance marked. Skipping leave deduction for half-day leave.");
           // usedDays = 0.5;
        }
    } else {
        System.out.println("Calculating EL days based on attendance...");
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            boolean isPresent = checkAttendance(leaveApplication.getEmpid(), date);
            if (!isPresent) {
                System.out.println("Date: " + date + " - No attendance marked. Adding to leave days.");
                usedDays++;
            } else {
            	// Fetch total hours worked for the full day
                double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(date, leaveApplication.getEmpid());
                System.out.println("Total Hours Worked on " + date + ": " + totalHoursWorked);

                if (totalHoursWorked < 6) {
                    // If hours worked are less than 6, treat it as absent
                    System.out.println("Total hours worked are less than 6. Treating this day as absent for leave deduction.");
                    usedDays++;
                } else {
                    System.out.println("Total hours are 6 or more. Attendance marked. Skipping leave deduction.");
                }
                System.out.println("Date: " + date + " - Attendance marked. Skipping EL deduction.");
            }
        }
    }


    if (usedDays == 0) {
        System.out.println("No EL days to reduce. Exiting method.");
        return;
    }

    String monthYear = fromDate.getYear() + "-" + String.format("%02d", fromDate.getMonthValue());
    System.out.println("Month-Year: " + monthYear);

    //EarnedLeave earnedLeave = earnedLeaveRepo.findByEmailAndMonthYear(leaveApplication.getEmployeeEmail(), monthYear);
    EarnedLeave earnedLeave = earnedLeaveRepo.findByEmail(leaveApplication.getEmployeeEmail());

    if (earnedLeave != null) {
        System.out.println("Found EarnedLeave entry: " + earnedLeave);

        double newElUsed = (earnedLeave.getElUsed() == null ? 0 : earnedLeave.getElUsed()) + usedDays;
        earnedLeave.setElUsed(newElUsed);
        earnedLeave.setElremaining(earnedLeave.getEarnedLeave() - newElUsed);
        earnedLeave.setLastIncrementDate(LocalDate.now().toString());

        earnedLeaveRepo.save(earnedLeave);
        System.out.println("EarnedLeave entry updated successfully.");

        // Mark the leave application as processed
        leaveApplication.setProcessed(true);
        leaveApplicationRepository.save(leaveApplication);
        System.out.println("LeaveApplication marked as processed.");
    } else {
        System.out.println("EarnedLeave entry not found for the employee: " + leaveApplication.getEmployeeEmail() +
                           " | Month-Year: " + monthYear);
    }

    System.out.println("=== Earned Leave (EL) Usage Update Completed ===");
}


	/*   private void updateLeaveUsage(LeaveApplication leaveApplication) {
		    System.out.println("Updating leave usage for: " + leaveApplication.getEmployeeEmail() + 
		                       " | Financial Year: " + leaveApplication.getFinancialYear() + 
		                       " | Leave Type: " + leaveApplication.getLeaveType());

		    LocalDate fromDate = LocalDate.parse(leaveApplication.getFromDate());
		    LocalDate toDate = LocalDate.parse(leaveApplication.getToDate());

		    long usedDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1; 
		    System.out.println("From Date: " + fromDate + " | To Date: " + toDate + 
		                       " | Used Days: " + usedDays);

		    System.out.println("Leave Application Details:");
		    System.out.println("Employee Email: " + leaveApplication.getEmployeeEmail());
		    System.out.println("Financial Year (from application): " + leaveApplication.getFinancialYear());
		    System.out.println("Leave Type (before normalization): " + leaveApplication.getLeaveType());

		    // Normalize the leave type
		    String leaveType = leaveApplication.getLeaveType();
		    if ("SL".equals(leaveType)) {
		        leaveType = "Sick Leave";
		    } else if ("CL".equals(leaveType)) {
		        leaveType = "Casual Leave";
		    }
		    System.out.println("Leave Type (after normalization): " + leaveType);

		    String financialYear = leaveApplication.getFinancialYear();
		    String formattedFinancialYear = financialYear.substring(0, 4) + "-" + financialYear.substring(7, 9);
		    System.out.println("Formatted Financial Year: " + formattedFinancialYear);

		    System.out.println("Searching LeaveUtilized with:");
		    System.out.println("Email: " + leaveApplication.getEmployeeEmail());
		    System.out.println("Financial Year: " + formattedFinancialYear);
		    System.out.println("Leave Type: " + leaveType);

		    List<LeaveUtilized> allEntries = leaveUtilizedRepository.findByUserEmailAndFinancialYear(leaveApplication.getEmployeeEmail(), formattedFinancialYear);
		    System.out.println("Existing LeaveUtilized entries: " + allEntries);

		    LeaveUtilized leaveUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(
		        leaveApplication.getEmployeeEmail(), 
		        formattedFinancialYear, 
		        leaveType
		    );

		    if (leaveUtilized != null) {
		        System.out.println("Found LeaveUtilized entry: " + leaveUtilized);
		        
		        int newTotalUsed = leaveUtilized.getTotalUsed() + (int) usedDays;
		        System.out.println("Previous Total Used: " + leaveUtilized.getTotalUsed() + 
		                           " | New Total Used: " + newTotalUsed);
		        
		        leaveUtilized.setTotalUsed(newTotalUsed);
		        leaveUtilized.setRemaining(leaveUtilized.getTotalAdded() - newTotalUsed);
		        leaveUtilized.setLastUpdated(LocalDate.now());

		        leaveUtilizedRepository.save(leaveUtilized);
		        System.out.println("LeaveUtilized entry updated successfully.");

		        // Mark the leave application as processed to avoid reprocessing
		        leaveApplication.setProcessed(true);
		        leaveApplicationRepository.save(leaveApplication); // Ensure this save method is present in your repository
		        System.out.println("LeaveApplication marked as processed.");
		    } else {
		        System.out.println("LeaveUtilized entry not found for the employee: " + leaveApplication.getEmployeeEmail() +
		                           " | Financial Year: " + formattedFinancialYear +
		                           " | Leave Type: " + leaveType);
		    }

		    System.out.println("Leave usage update completed for: " + leaveApplication.getEmployeeEmail());
		}*/


private void updateLeaveUsage(LeaveApplication leaveApplication) {
    System.out.println("=== Updating Leave Usage ===");
    System.out.println("Employee Email: " + leaveApplication.getEmployeeEmail());
    System.out.println("Financial Year: " + leaveApplication.getFinancialYear());
    System.out.println("Leave Type: " + leaveApplication.getLeaveType());

    LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date());
    LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date());
    LocalDate today = LocalDate.now();
    System.out.println("Leave Period: " + fromDate + " to " + toDate);
    System.out.println("Today's Date: " + today);

    // Ensure leave deduction only occurs after the leave period has ended
    if (!today.isAfter(toDate)) {
        System.out.println("Leave period has not ended yet. Leave deduction will occur after: " + toDate);
        return;
    }

    // Calculate used days
    double usedDays = 0;

  /*  if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        usedDays = 0.5;
        System.out.println("Half-day leave detected. Used days: " + usedDays);
    } else {
        System.out.println("Calculating leave days...");
        
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            boolean isPresent = checkAttendance(leaveApplication.getEmpid(), date);
            if (!isPresent) {
                System.out.println("Date: " + date + " - No attendance marked. Adding to leave days.");
                usedDays++;
            } else {
                System.out.println("Date: " + date + " - Attendance marked. Skipping leave deduction.");
            }
        }
    }*/
    if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        System.out.println("Half-day leave detected. Checking attendance hours...");
        
        LocalDate halfDayDate = fromDate; // Assuming half-day leave is for a single date
        boolean isPresent = checkAttendance(leaveApplication.getEmpid(), halfDayDate);
        
        if (isPresent) {
            // Fetch total hours worked for the half-day date
            double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(halfDayDate, leaveApplication.getEmpid());
            System.out.println("Total Hours Worked on " + halfDayDate + ": " + totalHoursWorked);

            if (totalHoursWorked < 6 && totalHoursWorked != 0) {
                usedDays = 0.5;
                System.out.println("Total hours less than 6. Deducting 0.5 leave.");
            } else {
                System.out.println("Total hours are 6 or more. Treating as full-day attendance. No leave deduction.");
            }
        } else {
            System.out.println("No attendance marked. Skipping leave deduction for half-day leave.");
           // usedDays = 0.5;
        }
    } else {
        System.out.println("Calculating leave days...");
        
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            boolean isPresent = checkAttendance(leaveApplication.getEmpid(), date);
            if (!isPresent) {
            	
                System.out.println("Date: " + date + " - No attendance marked. Adding to leave days.");
                
                usedDays++;
            } else {
            	 // Fetch total hours worked for the full day
                double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(date, leaveApplication.getEmpid());
                System.out.println("Total Hours Worked on " + date + ": " + totalHoursWorked);
                if (totalHoursWorked < 6) {
                    // If hours worked are less than 6, treat it as absent
                    System.out.println("Total hours worked are less than 6. Treating this day as absent for leave deduction.");
                    usedDays++;
                } else {
                    System.out.println("Total hours are 6 or more. Attendance marked. Skipping leave deduction.");
                    System.out.println("Date: " + date + " - Attendance marked. Skipping leave deduction.");
                }
               
            }
        }
    }
   
    if (usedDays == 0) {
        System.out.println("No leave days to reduce at this time. Exiting method.");
        return;
    }

    String leaveType = leaveApplication.getLeaveType();
    if ("SL".equals(leaveType)) leaveType = "Sick Leave";
    if ("CL".equals(leaveType)) leaveType = "Casual Leave";
    System.out.println("Normalized Leave Type: " + leaveType);

    String financialYear = leaveApplication.getFinancialYear();
    String formattedFinancialYear = financialYear.substring(0, 4) + "-" + financialYear.substring(7, 9);
    System.out.println("Formatted Financial Year: " + formattedFinancialYear);

   /* if (leaveApplication.getIsDateModified() != null && leaveApplication.getIsDateModified()) {
        System.out.println("Leave dates were modified. Reverting previous leave usage.");
        LeaveUtilized leaveUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(
            leaveApplication.getEmployeeEmail(), formattedFinancialYear, leaveType);

        if (leaveUtilized != null) {
            System.out.println("Reverting previous usage for LeaveUtilized entry: " + leaveUtilized);
            revertLeaveUsagenew(leaveApplication, leaveUtilized);
        }
    }*/

    List<LeaveUtilized> allEntries = leaveUtilizedRepository.findByUserEmailAndFinancialYear(
        leaveApplication.getEmployeeEmail(), formattedFinancialYear);
    System.out.println("Existing LeaveUtilized Entries: " + allEntries);

    LeaveUtilized leaveUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(
        leaveApplication.getEmployeeEmail(), formattedFinancialYear, leaveType);

    if (leaveUtilized != null) {
        System.out.println("Found LeaveUtilized entry: " + leaveUtilized);

        // Logic to track and adjust leave based on login dates
        double newTotalUsed = leaveUtilized.getTotalUsed() + usedDays;
        System.out.println("Updating LeaveUtilized: Previous Total Used: " + leaveUtilized.getTotalUsed()
            + ", New Total Used: " + newTotalUsed);

        leaveUtilized.setTotalUsed(newTotalUsed);
        leaveUtilized.setRemaining(leaveUtilized.getTotalAdded() - newTotalUsed);
        leaveUtilized.setLastUpdated(LocalDate.now());

        leaveUtilizedRepository.save(leaveUtilized);
        System.out.println("LeaveUtilized entry updated successfully.");

        // Mark the leave application as processed
        leaveApplication.setProcessed(true);
        leaveApplicationRepository.save(leaveApplication);
        System.out.println("LeaveApplication marked as processed.");
    } else {
        System.out.println("No LeaveUtilized entry found for Leave Type: " + leaveType
            + ", Financial Year: " + formattedFinancialYear + ". Exiting.");
    }

    System.out.println("=== Leave Usage Update Completed ===");
}


private boolean checkAttendance(String empId, LocalDate date) {
    String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    System.out.println("Checking attendance for Employee ID: " + empId + " on Date: " + dateString);

    List<AttendanceDTO> attendanceRecords = attendanceServiceDto.getAttendanceForYear(date.getYear(), empId);
    boolean attendanceExists = attendanceRecords.stream()
        .anyMatch(record -> record.getDate().equals(dateString) && record.getInTime() != null);

    System.out.println("Attendance for Date: " + dateString + " - " + (attendanceExists ? "Present" : "Absent"));
    return attendanceExists;
}




private void revertLeaveUsagenew(LeaveApplication leaveApplication, LeaveUtilized leaveUtilized) {
    System.out.println("Reverting leave usage for: " + leaveApplication.getEmployeeEmail() + 
                       " | Financial Year: " + leaveApplication.getFinancialYear() + 
                       " | Leave Type: " + leaveApplication.getLeaveType());

    // Check if old dates are null
    if (leaveApplication.getOld_from_date() == null || leaveApplication.getOld_from_date() == null) {
        System.out.println("Old dates are null. Skipping leave usage revert.");
        return; // Exit early if old dates are not available
    }

    // Use old dates if available
    LocalDate fromDate = LocalDate.parse(leaveApplication.getOld_from_date());
    LocalDate toDate = LocalDate.parse(leaveApplication.getOld_from_date());

    // Calculate used days, check for half-day leave
    double usedDays;
    if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        usedDays = 0.5;
    } else {
        usedDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1; // Full-day logic
    }

    System.out.println("Old From Date: " + fromDate + " | Old To Date: " + toDate + 
                       " | Used Days: " + usedDays);

    // Normalize the leave type
    String leaveType = leaveApplication.getLeaveType();
    if ("SL".equals(leaveType)) {
        leaveType = "Sick Leave";
    } else if ("CL".equals(leaveType)) {
        leaveType = "Casual Leave";
    }

    String formattedFinancialYear = leaveApplication.getFinancialYear().substring(0, 4) + "-" + leaveApplication.getFinancialYear().substring(7, 9);

    if (leaveUtilized != null) {
        System.out.println("Found LeaveUtilized entry to revert: " + leaveUtilized);
        
        double newTotalUsed = leaveUtilized.getTotalUsed() - usedDays; // Changed to double for half-day logic
        System.out.println("Previous Total Used: " + leaveUtilized.getTotalUsed() + 
                           " | New Total Used after revert: " + newTotalUsed);

        leaveUtilized.setTotalUsed(newTotalUsed);
        leaveUtilized.setRemaining(leaveUtilized.getTotalAdded() - newTotalUsed);
        leaveUtilized.setLastUpdated(LocalDate.now());

        leaveUtilizedRepository.save(leaveUtilized);
        System.out.println("LeaveUtilized entry reverted successfully.");

        // Mark the leave application as unprocessed so it can be reprocessed if needed
        leaveApplication.setProcessed(false);
        leaveApplicationRepository.save(leaveApplication);
        System.out.println("LeaveApplication marked as unprocessed.");
    } else {
        System.out.println("LeaveUtilized entry not found for the employee: " + leaveApplication.getEmployeeEmail() +
                           " | Financial Year: " + formattedFinancialYear +
                           " | Leave Type: " + leaveType);
    }

    System.out.println("Leave usage revert completed for: " + leaveApplication.getEmployeeEmail());
}



private void revertLeaveUsage(LeaveApplication leaveApplication) {
    System.out.println("=== Reverting Leave Usage ===");
    System.out.println("Employee Email: " + leaveApplication.getEmployeeEmail());
    System.out.println("Financial Year: " + leaveApplication.getFinancialYear());
    System.out.println("Leave Type: " + leaveApplication.getLeaveType());

    LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date());
    LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date());
    LocalDate today = LocalDate.now();

    System.out.println("Leave Period: " + fromDate + " to " + toDate);
    System.out.println("Today's Date: " + today);
    
    if (!today.isAfter(toDate)) {
        System.out.println("Leave period has not ended yet. Leave deduction will occur after: " + toDate);
        return;
    }
    double usedDays = 0;

    if ("Half Day".equalsIgnoreCase(leaveApplication.getLeaveDuration())) {
        // usedDays = 0.5;
         System.out.println("Half-day leave detected. Days to revert: " + usedDays);
         //Extra added based on Attendance
         LocalDate halfDayDate = fromDate; // Assuming half-day leave is for a single date
         boolean isPresent = checkAttendance(leaveApplication.getEmpid(), halfDayDate);
         
         if (isPresent) {
             // Fetch total hours worked for the half-day date
             double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(halfDayDate, leaveApplication.getEmpid());
             System.out.println("Total Hours Worked on " + halfDayDate + ": " + totalHoursWorked);

             if (totalHoursWorked < 6 && totalHoursWorked != 0) {
                 usedDays = 0.5;
                 System.out.println("Total hours less than 6. Reverting 0.5 leave.");
             } else {
                 System.out.println("Total hours are 6 or more. Treating as full-day attendance. Skipping leave revert.");
             }
         } else {
             System.out.println("No attendance marked for half-day leave. Skipping revert for half-day leave.");
         }
     } else {
         System.out.println("Calculating days to revert based on attendance...");
         for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
             boolean isPresent = checkAttendance(leaveApplication.getEmpid(), date);
             if (!isPresent) {
                 System.out.println("Date: " + date + " - No attendance marked. Adding to revert days.");
                 usedDays++;
             } else {
             	 // Attendance is marked, so check the total hours worked
                 double totalHoursWorked = attendanceServiceDto.getTotalHoursWorked(date, leaveApplication.getEmpid());
                 System.out.println("Total Hours Worked on " + date + ": " + totalHoursWorked);

                 if (totalHoursWorked < 6) {
                     // Only revert leave if the total hours worked is less than 6
                     System.out.println("Total hours less than 6. Adding to revert days.");
                     usedDays++;
                 } else {
                     System.out.println("Total hours are 6 or more. Skipping leave revert for this date.");
                 }
                 System.out.println("Date: " + date + " - Attendance marked. Skipping leave revert.");
             }
         }
     }

    if (usedDays == 0) {
        System.out.println("No leave days to revert. Exiting method.");
        return;
    }

    String leaveType = leaveApplication.getLeaveType();
    if ("SL".equals(leaveType)) leaveType = "Sick Leave";
    if ("CL".equals(leaveType)) leaveType = "Casual Leave";
    System.out.println("Normalized Leave Type: " + leaveType);

    String financialYear = leaveApplication.getFinancialYear();
    String formattedFinancialYear = financialYear.substring(0, 4) + "-" + financialYear.substring(7, 9);
    System.out.println("Formatted Financial Year: " + formattedFinancialYear);

    LeaveUtilized leaveUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(
        leaveApplication.getEmployeeEmail(), formattedFinancialYear, leaveType);

    if (leaveUtilized != null) {
        System.out.println("Found LeaveUtilized entry to revert: " + leaveUtilized);

        double newTotalUsed = leaveUtilized.getTotalUsed() - usedDays;
        System.out.println("Previous Total Used: " + leaveUtilized.getTotalUsed() +
                           " | New Total Used after revert: " + newTotalUsed);

        leaveUtilized.setTotalUsed(newTotalUsed);
        leaveUtilized.setRemaining(leaveUtilized.getTotalAdded() - newTotalUsed);
        leaveUtilized.setLastUpdated(LocalDate.now());

        leaveUtilizedRepository.save(leaveUtilized);
        System.out.println("LeaveUtilized entry reverted successfully.");

        // Mark the leave application as unprocessed
        leaveApplication.setProcessed(false);
        leaveApplicationRepository.save(leaveApplication);
        System.out.println("LeaveApplication marked as unprocessed.");
    } else {
        System.out.println("No LeaveUtilized entry found for Leave Type: " + leaveType +
                           " | Financial Year: " + formattedFinancialYear);
    }

    System.out.println("=== Leave Usage Revert Completed ===");
}

	   
	   

	 /*  private void revertLeaveUsage(LeaveApplication leaveApplication) {
		    System.out.println("Reverting leave usage for: " + leaveApplication.getEmployeeEmail() + 
		                       " | Financial Year: " + leaveApplication.getFinancialYear() + 
		                       " | Leave Type: " + leaveApplication.getLeaveType());

		    LocalDate fromDate = LocalDate.parse(leaveApplication.getFromDate());
		    LocalDate toDate = LocalDate.parse(leaveApplication.getToDate());
		    
		    long usedDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1; 
		    System.out.println("From Date: " + fromDate + " | To Date: " + toDate + 
		                       " | Used Days: " + usedDays);

		    // Normalize the leave type
		    String leaveType = leaveApplication.getLeaveType();
		    if ("SL".equals(leaveType)) {
		        leaveType = "Sick Leave";
		    } else if ("CL".equals(leaveType)) {
		        leaveType = "Casual Leave";
		    }
		    
		    String formattedFinancialYear = leaveApplication.getFinancialYear().substring(0, 4) + "-" + leaveApplication.getFinancialYear().substring(7, 9);
		    
		    LeaveUtilized leaveUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(
		        leaveApplication.getEmployeeEmail(), 
		        formattedFinancialYear, 
		        leaveType
		    );

		    if (leaveUtilized != null) {
		        System.out.println("Found LeaveUtilized entry to revert: " + leaveUtilized);
		        
		        int newTotalUsed = leaveUtilized.getTotalUsed() - (int) usedDays;
		        System.out.println("Previous Total Used: " + leaveUtilized.getTotalUsed() + 
		                           " | New Total Used after revert: " + newTotalUsed);
		        
		        leaveUtilized.setTotalUsed(newTotalUsed);
		        leaveUtilized.setRemaining(leaveUtilized.getTotalAdded() - newTotalUsed);
		        leaveUtilized.setLastUpdated(LocalDate.now());

		        leaveUtilizedRepository.save(leaveUtilized);
		        System.out.println("LeaveUtilized entry reverted successfully.");

		        // Mark the leave application as unprocessed so it can be reprocessed if needed
		        leaveApplication.setProcessed(false);
		        leaveApplicationRepository.save(leaveApplication);
		        System.out.println("LeaveApplication marked as unprocessed.");
		    } else {
		        System.out.println("LeaveUtilized entry not found for the employee: " + leaveApplication.getEmployeeEmail() +
		                           " | Financial Year: " + formattedFinancialYear +
		                           " | Leave Type: " + leaveType);
		    }

		    System.out.println("Leave usage revert completed for: " + leaveApplication.getEmployeeEmail());
		}*/

		@GetMapping("/leaveRecords/{email}")
		public ResponseEntity<List<LeaveUtilized>> getLeaveRecords(HttpSession session,@PathVariable("email") String email) {
		    // Retrieve the email from the session
		    //String email = (String) session.getAttribute("email");
		    System.out.println("Email retrieved from session:////ddd////" + email);

		    // Fetch employee ID using the email
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("Fetching leave records for employee ID: " + empId);

		    // Get all leave records for the employee
		    List<LeaveUtilized> leaveRecords = leaveApplicationService.getAllLeaveRecords(empId);
		    System.out.println("Total leave records fetched: " + (leaveRecords != null ? leaveRecords.size() : 0));

		    List<LeaveUtilized> filteredRecords = new ArrayList<>();

		    // Get the current date
		    LocalDate today = LocalDate.now();
		    int currentYear = today.getYear();

		    // Define start and end of the financial year
		    LocalDate startOfFinancialYear = LocalDate.of(currentYear, 3, 26);
		    LocalDate endOfFinancialYear = LocalDate.of(currentYear + 1, 3, 25);

		    // Adjust if the current date is before March 26
		    if (today.isBefore(startOfFinancialYear)) {
		        currentYear--;
		        startOfFinancialYear = LocalDate.of(currentYear, 3, 26);
		        endOfFinancialYear = LocalDate.of(currentYear + 1, 3, 25);
		    }

		    System.out.println("Current date: " + today);
		    System.out.println("Start of financial year: " + startOfFinancialYear);
		    System.out.println("End of financial year: " + endOfFinancialYear);

		    // Filter leave records for the current financial year
		    if (leaveRecords != null && !leaveRecords.isEmpty()) {
		        for (LeaveUtilized record : leaveRecords) {
		            LocalDate leaveDate = record.getLastUpdated(); // Use lastUpdated as the leave date
		            System.out.println("Checking record with lastUpdated: " + leaveDate);

		            // Check if the leave date is within the financial year
		            if (leaveDate != null && !leaveDate.isBefore(startOfFinancialYear) && !leaveDate.isAfter(endOfFinancialYear)) {
		                filteredRecords.add(record);
		                System.out.println("Record added: " + record);
		            }
		        }

		        // Check if there are any filtered records
		        if (!filteredRecords.isEmpty()) {
		            System.out.println("Leave records found for the current financial year: " + filteredRecords);
		            return ResponseEntity.ok(filteredRecords);
		        } else {
		            System.out.println("No leave records found for the current financial year for employee ID: " + empId);
		            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		            return ResponseEntity.ok(filteredRecords);
		        }
		    } else {
		        System.out.println("No leave records found for employee ID: " + empId);
		        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		        return ResponseEntity.ok(filteredRecords);
		    }
		}


	   @PostMapping("/applyLeave/{email}")
	   public ResponseEntity<?> applyLeave(@RequestBody LeaveApplication leaveApplication, HttpSession session,@PathVariable("email") String email) {
		   //System.out.println("hiii///ii.//"+leaveApplication);
	       // Retrieve user details from session
	       // String email = (String) session.getAttribute("email");

	       // Retrieve employee ID from email
		   
		   System.out.println("jhjkdhjh"+leaveApplication);
	       String empId = employeeRepository.findEmpidByEmail(email);
	       
	       // Fetch employee details using empId
	       DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
	       // Set the requested date (current date)
	       String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	      
	       // Set the requested date with date and time
	       leaveApplication.setRequested_date(currentDateTime);
	       System.out.println(";;##"+currentDateTime);
	       
	       String approveAuthority = leaveApplication.getApprovingAuthority();
	       
	       try {
	           // Set additional fields
	           leaveApplication.setEmployeeEmail(email);
	           leaveApplication.setEmpid(empId);
	           leaveApplication.setApprovedstatus("Pending"); // Set default status
	           leaveApplication.setProcessed(false);
	           leaveApplication.setEdit_flag(0);
	           // Set department ID and employee name from retrieved details
	           if (empDetailsByEmpId != null) {
	               String departmentName = empDetailsByEmpId.getDeptName();
	               leaveApplication.setEmployee_name(empDetailsByEmpId.getFullName()); // Use fullName for employee name

	               // Fetch the department ID based on department name
	               Integer deptId = departmentRepo.findIdByDeptName(departmentName);
	               if (deptId != null) {
	                   leaveApplication.setDepartment(deptId); // Set department ID
	               }
	           }
	           
	           if (leaveApplication.getWay() == null) {
	               leaveApplication.setWay(1); // Set default value
	           }
	           
	           // Set the leave duration
	           String leaveDuration = leaveApplication.getLeaveDuration();
	           
	           // Save the leave application
	           System.out.println("before save leaveApplication///"+leaveApplication);
	           leaveApplicationRepository.save(leaveApplication);
	           
	           leaveRequestService.sendApprovalEmail(empDetailsByEmpId.getEmail(),approveAuthority,leaveApplication);
	           
	           // return ResponseEntity.ok().body("Leave request saved successfully");
	           Map<String, String> response = new HashMap<>();
	           response.put("message", "Leave request saved successfully");
	           
	           return ResponseEntity.ok().body(response);
	           
	       } catch (Exception e) {
	           e.printStackTrace(); // Log the exception for debugging
	         //  return ResponseEntity.status(500).body("Error saving leave request");
	           // Return error message in JSON format
	           Map<String, String> response = new HashMap<>();
	           response.put("message", "Error saving leave request");

	           return ResponseEntity.status(500).body(response);
	       }
	   }

	//FOR HR NAME
	/*@GetMapping("/approving-authorities")
	@ResponseBody
	public Map<String, List<String>> getApprovingAuthorities() {
	    Integer deptId = departmentRepo.findIdByDeptName("HR");

	    if (deptId == null) {
	        throw new RuntimeException("Department not found");
	    }

	    List<EmployeeDetailsEntity> employees = employeeRepository.findByDeptId(deptId);
	    List<String> employeeNames = employees.stream()
	                                          .map(emp -> emp.getFirstName() + " " + emp.getLastName())
	                                          .collect(Collectors.toList());

	    return Map.of("approvingAuthorities", employeeNames);
	}*/
	   @GetMapping("/approving-authorities")
	   @ResponseBody
	   public Map<String, List<String>> getApprovingAuthorities() {
	       // Print statement for checking the department ID retrieval
	       System.out.println("Fetching department ID for 'HR'");

	       Integer deptId = departmentRepo.findIdByDeptName("HR");

	       if (deptId == null) {
	           System.out.println("Department not found for 'HR'");  // Print statement when department is not found
	           throw new RuntimeException("Department not found");
	       }

	       // Print statement for successful department ID retrieval
	       System.out.println("Department ID for 'HR' found: " + deptId);
	       
	       List<EmployeeDetailsEntity> employees = employeeRepository.findByDeptId(deptId);

	       // Print statement to check the number of employees found
	       System.out.println("Number of employees found in 'HR' department: " + employees.size());

	       List<String> employeeNames = employees.stream()
	                                             .map(emp -> {
	                                                 // Print each employee's full name
	                                                 String fullName = emp.getFirstName() + " " + emp.getLastName();
	                                                 System.out.println("Employee found: " + fullName);  // Print statement for employee name
	                                                 return fullName;
	                                             })
	                                             .collect(Collectors.toList());

	       // Print statement before returning the map
	       System.out.println("Returning the list of approving authorities: " + employeeNames);

	       return Map.of("approvingAuthorities", employeeNames);
	   }


	/*@GetMapping("/leaveRecords")
	public ResponseEntity<List<LeaveUtilized>> getLeaveRecords(HttpSession session) {
	    String email = (String) session.getAttribute("email");
	    String empId = employeeRepository.findEmpidByEmail(email);
	    
	    System.out.println("Fetching leave records for employee ID: " + empId); // Log

	    List<LeaveUtilized> leaveRecords = leaveApplicationService.getAllLeaveRecords(empId);

	    if (leaveRecords != null && !leaveRecords.isEmpty()) {
	        System.out.println("Leave records found: " + leaveRecords); // Log the data

	        // Convert to JSON and log the output
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            String jsonResponse = mapper.writeValueAsString(leaveRecords);
	            System.out.println("Serialized JSON Response: " + jsonResponse);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }

	        return ResponseEntity.ok(leaveRecords);
	    } else {
	        System.out.println("No leave records found for employee ID: " + empId);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}*/

	
	
/*	@GetMapping("/fetch-earned-leave")
	public ResponseEntity<Double> fetchEarnedLeave(@RequestParam int year, HttpSession session) {
	    try {
	        String email = (String) session.getAttribute("email");
	        if (email == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }

	        String empId = employeeRepository.findEmpidByEmail(email);
	        if (empId == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        // Fetch attendance and holidays
	        List<AttendanceDTO> attendanceList = attendanceServiceDto.getAttendanceForYear(year, empId);
	        List<Holiday_Entity> holidays = holiday_Service.getHolidaysForYear(year);
	        
	        // Convert holiday dates to LocalDate
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        List<LocalDate> companyHolidays = holidays.stream()
	                .map(holiday -> LocalDate.parse(holiday.getDate(), dateFormatter))
	                .collect(Collectors.toList());

	        // Calculate earned leave
	        double earnedLeave = calculateEarnedLeave(attendanceList, companyHolidays);

	        double elUsed = 0.0;
	        // Save or update earned leave
	        String monthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
	        earnedLeaveService.saveOrUpdateEarnedLeave(email, earnedLeave, monthYear,empId,elUsed);
            System.out.println("saved succesfully ");
	        
	        return new ResponseEntity<>(earnedLeave, HttpStatus.OK);
	    } catch (Exception e) {
	        System.err.println("Error calculating earned leave: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}*/
	
	

/*	private double calculateEarnedLeave(List<AttendanceDTO> attendanceList, List<LocalDate> holidays) {
	    System.out.println("Calculating earned leave...");

	    int continuousDaysPresent = 0;
	    double earnedLeave = 0.0;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    // Convert attendance dates to a set of LocalDate for quick lookup
	    Set<LocalDate> attendanceDates = attendanceList.stream()
	            .map(attendance -> LocalDate.parse(attendance.getDate(), formatter))
	            .collect(Collectors.toSet());

	    // Determine the range of dates to check (from first to last attendance date)
	    LocalDate startDate = attendanceDates.stream().min(LocalDate::compareTo).orElse(null);
	    LocalDate endDate = attendanceDates.stream().max(LocalDate::compareTo).orElse(null);

	    if (startDate == null || endDate == null) {
	        System.out.println("No attendance records found.");
	        return earnedLeave;
	    }

	    System.out.println("Checking dates from " + startDate + " to " + endDate);

	    Set<LocalDate> countedSkipDates = new HashSet<>();

	    // Iterate through each date from startDate to endDate
	    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
	        // Check if the date is a holiday or a Sunday
	        boolean isHolidayOrSunday = holidays.contains(date) || date.getDayOfWeek() == DayOfWeek.SUNDAY;

	        // Check if the current date is in the attendance list
	        if (!attendanceDates.contains(date)) {
	            // If it's not a holiday or Sunday, consider it "absent"
	            if (!isHolidayOrSunday) {
	                continuousDaysPresent = 0; // Reset continuousDaysPresent
	                System.out.println("Date " + date + " is absent.");
	            } else {
	                // Increment continuous days for holidays or Sundays
	                if (!countedSkipDates.contains(date)) {
	                    continuousDaysPresent++;
	                    countedSkipDates.add(date); // Avoid counting the same holiday/Sunday multiple times
	                    System.out.println("Skip date: " + date + " is a holiday or Sunday. Incrementing continuousDaysPresent.");
	                }
	            }
	        } else {
	            // If present, increment the continuousDaysPresent
	            continuousDaysPresent++;
	            System.out.println("Date: " + date + ", Continuous Days Present: " + continuousDaysPresent);

	            // If 21 continuous days are reached, grant earned leave
	            if (continuousDaysPresent >= 21) {
	                earnedLeave += 1.5;
	                System.out.println("1.5 leave added for 21 continuous days present. Earned leave so far: " + earnedLeave);
	                continuousDaysPresent -= 21; // Reset continuous days for next cycle
	            }
	        }
	    }

	    System.out.println("Earned leave calculation completed. Total earned leave: " + earnedLeave);
	    return earnedLeave;
	}*/ 

	   @GetMapping("/fetch-earned-leave")
		public ResponseEntity<Double> fetchEarnedLeave(@RequestParam int year,@RequestParam String email, HttpSession session) {
		    try {  
		    	    // String email = (String) session.getAttribute("email");
		    	 System.out.println("Fetching earned leave for email: " + email);

			        if (email == null) {
			            System.out.println("No email found in session. Unauthorized.");
			            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			        }

			        String empId = employeeRepository.findEmpidByEmail(email);
			        System.out.println("Employee ID fetched: " + empId);

			        if (empId == null) {
			            System.out.println("Employee ID not found for email: " + email);
			            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			        }

			        // Check if earned leave already calculated for this month
			        String monthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
			        System.out.println("Current month and year: " + monthYear);

			   
			     // Check if earned leave already exists for the employee
			        EarnedLeave existingEarnedLeave = earnedLeaveRepo.findByEmail(email);
			        System.out.println("Current month and year: 00" + existingEarnedLeave);
			        if (existingEarnedLeave != null) {
			            System.out.println("Existing earned leave record found.");
			        } else {
			            System.out.println("No earned leave record found for this employee.");
			        }

			        // Fetch attendance and holidays
			        System.out.println("Fetching attendance and holiday data.");
			     //   List<AttendanceDTO> attendanceList = attendanceServiceDto.getAttendanceForYear(year, empId);
			        List<AttendanceDTO> attendanceList = attendanceServiceDto.getAllAttendance(empId);
			       // List<Holiday_Entity> holidays = holiday_Service.getHolidaysForYear(year);
			        List<Holiday_Entity> holidays = holiday_Service.getAllHolidays();

			        System.out.println("Attendance count: " + attendanceList.size() + ", Holiday count: " + holidays.size());

			        // Convert holiday dates to LocalDate
			        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        List<LocalDate> companyHolidays = holidays.stream()
			                .map(holiday -> LocalDate.parse(holiday.getDate(), dateFormatter))
			                .collect(Collectors.toList());

			        System.out.println("Company holidays converted to LocalDate: " + companyHolidays);

			        // Calculate earned leave
			        String lastIncrementDateString = (existingEarnedLeave != null) ? existingEarnedLeave.getLastIncrementDate() : null;
			        System.out.println("Last increment date: " + lastIncrementDateString);

			     //   double earnedLeave = calculateEarnedLeave(attendanceList, companyHolidays, lastIncrementDateString, existingEarnedLeave, email, monthYear, empId);

			       // System.out.println("Final earned leave for employee: " + earnedLeave);
			      // return new ResponseEntity<>(earnedLeave, HttpStatus.OK);
			        return new ResponseEntity<>(0.0, HttpStatus.OK);
			    } catch (Exception e) {
			        System.err.println("Error calculating earned leave: " + e.getMessage());
			        e.printStackTrace();
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			    }
			}


	
	   @GetMapping("/calculate-earned-leave")
		 public ResponseEntity<EarnedLeaveDTO> calculateEarnedLeave(@RequestParam int year,@RequestParam String email, HttpSession session) {
		     try {
		    	// String email = (String) session.getAttribute("email");
		         if (email == null) {
		             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		         }

		         String empId = employeeRepository.findEmpidByEmail(email);
		         if (empId == null) {
		             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		         }

		         String monthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		        // EarnedLeave existingEarnedLeave = earnedLeaveRepo.findByEmailAndMonthYear(email, monthYear);
		         EarnedLeave existingEarnedLeave = earnedLeaveRepo.findByEmail(email);
		      // Check if existing earned leave is null and return a default object
		         if (existingEarnedLeave == null) {
		             EarnedLeaveDTO response = new EarnedLeaveDTO(
		                 null, // id
		                 0.0, // earnedLeave
		                 email,
		                 monthYear,
		                 0.0, // elUsed
		                 0.0, // elremaining
		                 empId,
		                 null // lastIncrementDate
		             );
		             return new ResponseEntity<>(response, HttpStatus.OK); // Return with OK status
		         }

		         EarnedLeaveDTO response = new EarnedLeaveDTO(
		             existingEarnedLeave.getId(),
		             existingEarnedLeave.getEarnedLeave(),
		             existingEarnedLeave.getEmail(),
		             existingEarnedLeave.getMonthYear(),
		             existingEarnedLeave.getElUsed(),
		             existingEarnedLeave.getElremaining(),
		             existingEarnedLeave.getEmpId(),
		             existingEarnedLeave.getLastIncrementDate()
		         );

		         return new ResponseEntity<>(response, HttpStatus.OK);
		     } catch (Exception e) {
		         System.err.println("Error fetching earned leave: " + e.getMessage());
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		     }
		 }


	
	
	 /* Without Half Day */
	/* private double calculateEarnedLeave(List<AttendanceDTO> attendanceList, List<LocalDate> holidays, String lastIncrementDateString, EarnedLeave existingEarnedLeave, String email, String monthYear, String empId) {
		    System.out.println("Calculating earned leave...");

		    int continuousDaysPresent = 0;
		    double earnedLeave = 0.0;
		    LocalDate incrementDate = null;  // Declare incrementDate here
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    // Convert lastIncrementDate string to LocalDate
		    LocalDate lastIncrementDate = (lastIncrementDateString != null) ? LocalDate.parse(lastIncrementDateString, formatter) : null;
		    System.out.println("Parsed last increment date: " + lastIncrementDate);
		    
		    // Convert attendance dates to a set of LocalDate for quick lookup
		    Set<LocalDate> attendanceDates = attendanceList.stream()
		            .map(attendance -> LocalDate.parse(attendance.getDate(), formatter))
		            .collect(Collectors.toSet());
		    System.out.println("Attendance dates: " + attendanceDates);
		    
		    // Determine the range of dates to check (from first to last attendance date)
		    LocalDate startDate = attendanceDates.stream().min(LocalDate::compareTo).orElse(null);
		    LocalDate endDate = attendanceDates.stream().max(LocalDate::compareTo).orElse(null);

		    System.out.println("Start date: " + startDate + ", End date: " + endDate);
		    
		    if (startDate == null || endDate == null) {
		        System.out.println("No attendance records found.");
		        return earnedLeave;
		    }

		    // Adjust the start date if lastIncrementDate is available
		    if (lastIncrementDate != null && lastIncrementDate.isAfter(startDate)) {
		        startDate = lastIncrementDate.plusDays(1); // Start from the next day after the last increment
		        System.out.println("Adjusted start date after last increment: " + startDate);
		    }

		    System.out.println("Checking dates from " + startDate + " to " + endDate);

		    Set<LocalDate> countedSkipDates = new HashSet<>();

		    // Iterate through each date from startDate to endDate
		    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
		        // Check if the date is a holiday or a Sunday
		        boolean isHolidayOrSunday = holidays.contains(date) || date.getDayOfWeek() == DayOfWeek.SUNDAY;
		        System.out.println("Processing date: " + date + " | Is holiday or Sunday: " + isHolidayOrSunday);

		        // Check if the current date is in the attendance list
		        if (!attendanceDates.contains(date)) {
		            // If it's not a holiday or Sunday, consider it "absent"
		            if (!isHolidayOrSunday) {
		                continuousDaysPresent = 0; // Reset continuousDaysPresent
		                System.out.println("Date " + date + " is absent.");
		            } else {
		                // Increment continuous days for holidays or Sundays
		                if (!countedSkipDates.contains(date)) {
		                    continuousDaysPresent++;
		                    countedSkipDates.add(date); // Avoid counting the same holiday/Sunday multiple times
		                    System.out.println("Skip date: " + date + " is a holiday or Sunday. Incrementing continuousDaysPresent.");
		                }
		            }
		        } else {
		            // If present, increment the continuousDaysPresent
		            continuousDaysPresent++;
		            System.out.println("Date: " + date + ", Continuous Days Present: " + continuousDaysPresent);

		            // If 21 continuous days are reached, grant earned leave and update lastIncrementDate
		            if (continuousDaysPresent >= 21) {
		                earnedLeave += 1.5;
		                System.out.println("1.5 leave added for 21 continuous days present. Earned leave so far: " + earnedLeave);
		                
		                // Update incrementDate to the current date when leave is granted
		                incrementDate = date; // Use the date when leave was granted
		                continuousDaysPresent -= 21; // Reset continuous days for next cycle
		            }
		        }
		    }

		    // Save or update earned leave after the loop
		    if (earnedLeave > 0) {
		        earnedLeaveService.saveOrUpdateEarnedLeave(email, earnedLeave, monthYear, empId, null, incrementDate);
		        System.out.println("Earned leave updated in database for increment date: " + incrementDate);
		    }

		    System.out.println("Earned leave calculation completed. Total earned leave: " + earnedLeave);
		    return earnedLeave;
		}*/

	 

	   
	   /*Including the half day */
	   /*	 private double calculateEarnedLeave(List<AttendanceDTO> attendanceList, List<LocalDate> holidays, String lastIncrementDateString, EarnedLeave existingEarnedLeave, String email, String monthYear, String empId) {
	   		    System.out.println("Calculating earned leave...");

	   		    int continuousDaysPresent = 0;
	   		    double earnedLeave = 0.0;
	   		    LocalDate incrementDate = null;  // Declare incrementDate here
	   		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	   		    // Convert lastIncrementDate string to LocalDate
	   		    LocalDate lastIncrementDate = (lastIncrementDateString != null) ? LocalDate.parse(lastIncrementDateString, formatter) : null;
	   		  //  System.out.println("Parsed last increment date: " + lastIncrementDate);
	   		    
	   		    // Convert attendance dates to a set of LocalDate for quick lookup
	   		    Set<LocalDate> attendanceDates = attendanceList.stream()
	   		            .map(attendance -> LocalDate.parse(attendance.getDate(), formatter))
	   		            .collect(Collectors.toSet());
	   		   // System.out.println("Attendance dates: " + attendanceDates);
	   		    
	   		    // Fetch probation completion date and calculate eligibility
	      String probationDateString = employeeDetailsService.getProbationCompletionDate(email);
	      LocalDate probationCompletionDate = (probationDateString != null) ? LocalDate.parse(probationDateString, formatter) : null;
	      boolean isEligibleForSaturdayLeave = probationCompletionDate != null && probationCompletionDate.plusYears(2).isBefore(LocalDate.now());

	    // Skip EL calculation if probation is not completed
	    if (probationCompletionDate != null && !probationCompletionDate.isBefore(LocalDate.now())) {
	        System.out.println("Probation not completed. Skipping earned leave calculation.");
	        return earnedLeave;
	    }
	   		    
	   		    // Determine the range of dates to check (from first to last attendance date)
	   		    LocalDate startDate = attendanceDates.stream().min(LocalDate::compareTo).orElse(null);
	   		    LocalDate endDate = attendanceDates.stream().max(LocalDate::compareTo).orElse(null);

	   		  //  System.out.println("Start date: " + startDate + ", End date: " + endDate);
	   		    
	   		    if (startDate == null || endDate == null) {
	   		        System.out.println("No attendance records found.");
	   		        return earnedLeave;
	   		    }

	   		    // Adjust the start date if lastIncrementDate is available
	   		    if (lastIncrementDate != null && lastIncrementDate.isAfter(startDate)) {
	   		        startDate = lastIncrementDate.plusDays(1); // Start from the next day after the last increment
	   		        System.out.println("Adjusted start date after last increment: " + startDate);
	   		    }

	   		    // Identify second and fourth Saturdays for eligible users
	   		    	    Set<LocalDate> secondAndFourthSaturdays = new HashSet<>();
	   		       if (isEligibleForSaturdayLeave) {
	   		        System.out.println("Calculating second and fourth Saturdays for eligible user...");
	   		        for (LocalDate date = startDate.withDayOfMonth(1); !date.isAfter(endDate); date = date.plusMonths(1).withDayOfMonth(1)) {
	   		            LocalDate secondSaturday = date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
	   		            LocalDate fourthSaturday = date.with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.SATURDAY));
	   		            if (!secondSaturday.isAfter(endDate)) secondAndFourthSaturdays.add(secondSaturday);
	   		            if (!fourthSaturday.isAfter(endDate)) secondAndFourthSaturdays.add(fourthSaturday);
	   		        }
	   		    }
	   		    
	   		    System.out.println("Checking dates from " + startDate + " to " + endDate);

	   		    Set<LocalDate> countedSkipDates = new HashSet<>();

	   		    // Iterate through each date from startDate to endDate
	   		    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
	   		        // Check if the date is a holiday or a Sunday
	   		        boolean isHolidayOrSunday = holidays.contains(date) || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	     boolean isEligibleSaturday = isEligibleForSaturdayLeave && secondAndFourthSaturdays.contains(date);
	   		       // System.out.println("Processing date: " + date + " | Is holiday or Sunday: " + isHolidayOrSunday);
	   		        //System.out.println("Processing date: " + date + " | Is Second or Fourth: " + isEligibleSaturday);

	   		        // Use a final variable to hold the current date for the filter
	   		        LocalDate currentDate = date;
	   		        AttendanceDTO attendanceRecord = attendanceList.stream()
	   		                .filter(attendance -> LocalDate.parse(attendance.getDate(), formatter).isEqual(currentDate))
	   		                .findFirst()
	   		                .orElse(null);

	   		        if (attendanceRecord == null) {
	   		            // If it's not a holiday or Sunday, consider it "absent"
	   		            if (!isHolidayOrSunday && !isEligibleSaturday) { 
	   		                continuousDaysPresent = 0; // Reset continuousDaysPresent
	   		              //  System.out.println("Date " + date + " is absent.");
	   		            } else {
	   		                // Increment continuous days for holidays or Sundays
	   		                if (!countedSkipDates.contains(date)) {
	   		                    continuousDaysPresent++;
	   		                    countedSkipDates.add(date); // Avoid counting the same holiday/Sunday multiple times
	   		                    System.out.println("Skip date: " + date + " is a holiday or Sunday. Incrementing continuousDaysPresent.");
	   		                }
	   		            }
	   		        } else {
	   		        	// If attendance is recorded
	   		            if (isHolidayOrSunday || isEligibleSaturday) {
	   		                // Skip hours check for holidays, Sundays, or eligible Saturdays
	   		                continuousDaysPresent++;
	   		                System.out.println("Date: " + date + " is a holiday, Sunday, or eligible Saturday with attendance. Incrementing continuousDaysPresent.");
	   		            } else {
	   		                // Check total hours for regular working days
	   		            // If present, check total hours for half-day leave
	   		            double totalHours = convertTimeToHours(attendanceRecord.getTotalHours()); // Convert time to hours
	   		            if (totalHours < 6) {
	   		                // Reset continuous days present for half-day leave
	   		                continuousDaysPresent = 0;
	   		                //System.out.println("Date: " + date + " is half-day. Resetting continuousDaysPresent.");
	   		            } else {
	   		                // If present, increment the continuousDaysPresent
	   		                continuousDaysPresent++;
	   		                System.out.println("Date: " + date + ", Continuous Days Present: " + continuousDaysPresent);

	   		                // If 21 continuous days are reached, grant earned leave and update lastIncrementDate
	   		                if (continuousDaysPresent >= 21) {
	   		                    earnedLeave += 1.5;
	   		                    System.out.println("1.5 leave added for 21 continuous days present. Earned leave so far: " + earnedLeave);
	   		                    
	   		                    // Update incrementDate to the current date when leave is granted
	   		                    
	   		                    incrementDate = date; // Use the date when leave was granted
	   		                    
	   		                    System.out.println("Inserting earned leave balance: " +
	   		                    		   "Email: " + email +
	   		                    		   ", EmpId: " + empId +
	   		                    		   ", EarnedLeave: " + earnedLeave +
	   		                    		 
	   		                    		   ", IncrementDate: " + incrementDate +
	   		                    		   ", MonthYear: " + monthYear
	   		                    		);

	   		                    earnedLeaveBalanceDataService.insertEarnedLeaveBalanceData(
	   		                            email, empId, 1.5, 0.0, 1.5, incrementDate, monthYear
	   		                        );
	   		                        System.out.println("New earned leave balance record inserted for 1.5 earned leave on date: " + incrementDate);

	   		                    continuousDaysPresent -= 21; // Reset continuous days for next cycle
	   		                }
	   		            }
	   		        }
	   		        }
	   		    }

	   		    // Save or update earned leave after the loop
	   		    if (earnedLeave > 0) {
	   		        earnedLeaveService.saveOrUpdateEarnedLeave(email, earnedLeave, monthYear, empId, null, incrementDate);
	   		        System.out.println("Earned leave updated in database for increment date: " + incrementDate);
	   		        // Insert a new record into earned_leave_balance_data table
	   		     //   earnedLeaveBalanceDataService.insertEarnedLeaveBalanceData(email, empId, earnedLeave, 0.0, earnedLeave, incrementDate, monthYear);
	   		        System.out.println("New earned leave balance record inserted in earned_leave_balance_data.");
	   		    
	   		    }

	   		    System.out.println("Earned leave calculation completed. Total earned leave: " + earnedLeave);
	   		    return earnedLeave;
	   		}*/

	        // Helper method to convert time string to hours
			private double convertTimeToHours(String time) {
			    String[] parts = time.split(":");
			    if (parts.length == 3) {
			        int hours = Integer.parseInt(parts[0]);
			        int minutes = Integer.parseInt(parts[1]);
			        return hours + minutes / 60.0; // Convert to decimal hours
			    }
			    return 0; // Default return if the format is incorrect
			}

		 /* public ResponseEntity<Void> resetLeaveRequestCount(@RequestParam("email") String email) {
		    	 System.out.println("Resetting leave request count for email: " + email);
		    	 notificationCount_Service.resetLeavePendingCount(email);
		        System.out.println("Leave request count reset for email: " + email);
		        return ResponseEntity.ok().build();
		    }*/
}

