package com.example.mspl_connect.AdminService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EarnedLeave;
import com.example.mspl_connect.AdminEntity.EarnedLeaveDTO;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveRecord;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;
import com.example.mspl_connect.AdminEntity.YearFlag;
import com.example.mspl_connect.AdminRepo.EarnedLeaveRepository;
import com.example.mspl_connect.AdminRepo.LeaveApplicationRepository;
import com.example.mspl_connect.AdminRepo.LeaveRecordRepository;
import com.example.mspl_connect.AdminRepo.LeaveUtilizedRepository;
import com.example.mspl_connect.AdminRepo.YearFlagRepository;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Service.EmployeeDetaisService;

@Service
public class LeaveApplicationService {

	@Autowired
	private EmployeeDetaisService  employeeDetailsService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	 @Autowired
	 private LeaveApplicationRepository leaveApplicationRepository;
	 
	 @Autowired
	 private YearFlagRepository yearFlagRepository;
	 
	 @Autowired
	 private LeaveRecordRepository leaveRecordRepository;
	 
	 @Autowired
	 private LeaveUtilizedRepository leaveUtilizedRepository;
	 
	 @Autowired
	 private EarnedLeaveRepository earnedLeaveRepository;

	 public LeaveApplication saveLeaveApplication(LeaveApplication leaveApplication) {
	        return leaveApplicationRepository.save(leaveApplication);
	    }
	 
	 
	//TO ADD SL & CL FOR ALL EMPLOYEE    
	

	/* public void addLeaveRecordsForAllEmployees() {
		    List<EmployeeDetailsEntity> allEmployees = employeeRepository.findAll();
		    LocalDate today = LocalDate.now();
		    int currentMonthValue = today.getMonthValue(); // 1 for Jan, 12 for Dec
		    String currentMonth = today.getMonth().toString();

		    // Determine the financial year based on the current date
		    String financialYear;
		    if (today.isAfter(LocalDate.of(today.getYear(), 3, 25))) {
		        financialYear = today.getYear() + "-" + (today.getYear() + 1) % 100;
		    } else {
		        financialYear = (today.getYear() - 1) + "-" + today.getYear() % 100;
		    }

		    // Loop through all employees
		    for (EmployeeDetailsEntity employee : allEmployees) {
		        String employeeEmail = employee.getEmail();
		        String employeeEmpId = employee.getEmpId();

		        // Determine whether it's time for sick leave or casual leave (based on month number)
		        boolean isSickLeaveMonth = (currentMonthValue % 2 != 0); // Odd month = Sick leave, Even month = Casual leave
		        String leaveType = isSickLeaveMonth ? "Sick Leave" : "Casual Leave";

		        // Check if leave records have already been added for this month and financial year
		        LeaveRecord existingLeaveRecord = leaveRecordRepository.findByEmailAndLeaveTypeAndMonthAndYear(
		                employeeEmail, leaveType, currentMonth, financialYear);

		        if (existingLeaveRecord == null) {
		            // Create a new leave record for the employee (adding 1 leave for this month)
		            LeaveRecord leaveRecord = new LeaveRecord();
		            leaveRecord.setLeaveType(leaveType);
		            leaveRecord.setAdded(1); // Add only 1 leave for this month
		            leaveRecord.setMonth(currentMonth);
		            leaveRecord.setRemaining(1); // Initially set to the 1 leave added
		            leaveRecord.setUsed(getUsedLeaveCount(employeeEmail, leaveType.substring(0, 2), "approved")); // SL/CL
		            leaveRecord.setYear(financialYear); // Save as financial year
		            leaveRecord.setEmail(employeeEmail);
		            leaveRecord.setEmpId(employeeEmpId); // Set the employee ID
		            leaveRecordRepository.save(leaveRecord);

		            // Call the updateLeaveUtilized method to update or insert into the leave_utilized table
		            updateLeaveUtilized(employeeEmail, financialYear, leaveType, 1, employeeEmpId); // Called with 5 arguments
		        }

		        // Save a flag in the YearFlag indicating that the task has been executed for this financial year and month for this employee
		        if (!yearFlagRepository.existsByYearAndEmployeeIdAndMonth(financialYear, employee.getEmpId(), currentMonth)) {
		            YearFlag yearFlag = new YearFlag();
		            yearFlag.setYear(financialYear); // Save as financial year
		            yearFlag.setEmployeeId(employee.getEmpId());
		            yearFlag.setMonth(currentMonth); // Save the current month
		            yearFlagRepository.save(yearFlag);
		        }
		    }
		}*/

	 public void addLeaveRecordsForAllEmployees() {
		    List<EmployeeDetailsEntity> allEmployees = employeeRepository.findAll();
		    LocalDate today = LocalDate.now();
		    
		    // Calculate the next month
		    LocalDate nextMonthDate = today.plusMonths(1);
		    String nextMonth = nextMonthDate.getMonth().toString(); // Get the name of the next month
		    int nextMonthValue = nextMonthDate.getMonthValue(); // 1 for Jan, 12 for Dec

		    // Determine the financial year based on the current date
		    String financialYear;
		    if (today.isAfter(LocalDate.of(today.getYear(), 3, 25))) {
		        financialYear = today.getYear() + "-" + (today.getYear() + 1) % 100;
		    } else {
		        financialYear = (today.getYear() - 1) + "-" + today.getYear() % 100;
		    }

		    // Loop through all employees
		    for (EmployeeDetailsEntity employee : allEmployees) {
		        String employeeEmail = employee.getEmail();
		        String employeeEmpId = employee.getEmpId();

		        // Determine whether it's time for sick leave or casual leave (based on month number)
		    //    boolean isSickLeaveMonth = (nextMonthValue % 2 != 0); // Odd month = Sick leave, Even month = Casual leave
		        boolean isSickLeaveMonth = (nextMonthValue % 2 == 0);
		        String leaveType = isSickLeaveMonth ? "Sick Leave" : "Casual Leave";

		        // Check if leave records have already been added for the next month and financial year
		        LeaveRecord existingLeaveRecord = leaveRecordRepository.findByEmailAndLeaveTypeAndMonthAndYear(
		                employeeEmail, leaveType, nextMonth, financialYear);

		        if (existingLeaveRecord == null) {
		            // Create a new leave record for the employee (adding 1 leave for the next month)
		            LeaveRecord leaveRecord = new LeaveRecord();
		            leaveRecord.setLeaveType(leaveType);
		            leaveRecord.setAdded(1); // Add only 1 leave for this month
		            leaveRecord.setMonth(nextMonth);
		            leaveRecord.setRemaining(1); // Initially set to the 1 leave added
		            leaveRecord.setUsed(getUsedLeaveCount(employeeEmail, leaveType.substring(0, 2), "approved")); // SL/CL
		            leaveRecord.setYear(financialYear); // Save as financial year
		            leaveRecord.setEmail(employeeEmail);
		            leaveRecord.setEmpId(employeeEmpId); // Set the employee ID
		            leaveRecordRepository.save(leaveRecord);

		            // Call the updateLeaveUtilized method to update or insert into the leave_utilized table
		            updateLeaveUtilized(employeeEmail, financialYear, leaveType, 1, employeeEmpId); // Called with 5 arguments
		        }

		        // Save a flag in the YearFlag indicating that the task has been executed for this financial year and next month for this employee
		        if (!yearFlagRepository.existsByYearAndEmployeeIdAndMonth(financialYear, employee.getEmpId(), nextMonth)) {
		            YearFlag yearFlag = new YearFlag();
		            yearFlag.setYear(financialYear); // Save as financial year
		            yearFlag.setEmployeeId(employee.getEmpId());
		            yearFlag.setMonth(nextMonth); // Save the next month
		            yearFlagRepository.save(yearFlag);
		        }
		    }
		}


	 public void updateLeaveUtilized(String employeeEmail, String financialYear, String leaveType, int leaveAdded, String empId) {
		    LeaveUtilized existingUtilized = leaveUtilizedRepository.findByUserEmailAndFinancialYearAndLeaveType(employeeEmail, financialYear, leaveType);
		    
		    if (existingUtilized != null) {
		        // Update the existing record
		        existingUtilized.setTotalAdded(existingUtilized.getTotalAdded() + leaveAdded);
		        existingUtilized.setRemaining(existingUtilized.getTotalAdded() - existingUtilized.getTotalUsed()); // Recalculate remaining
		        existingUtilized.setLastUpdated(LocalDate.now());
		        existingUtilized.setEmpId(empId);  // Update empId if needed
		        leaveUtilizedRepository.save(existingUtilized);
		    } else {
		        // Create a new record
		        LeaveUtilized leaveUtilized = new LeaveUtilized();
		        leaveUtilized.setUserEmail(employeeEmail);
		        leaveUtilized.setFinancialYear(financialYear);
		        leaveUtilized.setLeaveType(leaveType);
		        leaveUtilized.setTotalAdded(leaveAdded);
		        leaveUtilized.setTotalUsed(0); // Initially 0 used
		        leaveUtilized.setRemaining(leaveAdded); // Initially remaining is the same as added
		        leaveUtilized.setLastUpdated(LocalDate.now());
		        leaveUtilized.setEmpId(empId);  // Set empId for new record
		        leaveUtilizedRepository.save(leaveUtilized);
		    }
		}


	   public int getUsedLeaveCount(String employeeEmail, String leaveType, String approvedstatus) {
	        // Query the leave application table to fetch leave records
	        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findByEmployeeEmailAndLeaveTypeAndApprovedstatus(employeeEmail, leaveType, approvedstatus);
	        
	        // Initialize the used leave count
	        int usedLeaveCount = 0;
	        
	        // Iterate through leave applications and count each leave based on the duration
	        for (LeaveApplication leaveApplication : leaveApplications) {
	            // Parse the string representation of dates into LocalDate objects
	            String fromDateStr = leaveApplication.getFrom_date();
	            String toDateStr = leaveApplication.getTo_date();
	            
	            // Calculate the duration of leave for each application
	            long leaveDays = countLeaveDays(fromDateStr, toDateStr);
	            usedLeaveCount += leaveDays;
	        }
	        
	        // Log the result of the query
	        System.out.println("Used Leave Count: " + usedLeaveCount);
	        
	        // Return the total used leave count
	        return usedLeaveCount;
	    }

	   private long countLeaveDays(String fromDateStr, String toDateStr) {
	        // Define a date formatter to parse the string representation of dates
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        
	        // Parse the strings into LocalDate objects
	        LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
	        LocalDate toDate = LocalDate.parse(toDateStr, formatter);
	        
	        // Calculate the duration of leave
	        return ChronoUnit.DAYS.between(fromDate, toDate.plusDays(1)); // Add 1 to include the end date
	    }
	   
	   
	   
	 
	   
	   
	   /*Leave Count Modal*/
	   
	   public List<LeaveUtilized> getAllLeaveRecords(String empId) {
	        return leaveUtilizedRepository.findByEmpId(empId); // Fetch records for the specific employee
	    }


	public void addLeaveRecordsForEmployee(String empId) {
		// TODO Auto-generated method stub
		  List<EmployeeDetailsEntity> allEmployees = employeeRepository.findAll();
		    LocalDate today = LocalDate.now();
		    
		    // Calculate the next month
		    LocalDate nextMonthDate = today.plusMonths(1);
		    String nextMonth = nextMonthDate.getMonth().toString(); // Get the name of the next month
		    int nextMonthValue = nextMonthDate.getMonthValue(); // 1 for Jan, 12 for Dec

		    // Determine the financial year based on the current date
		    String financialYear;
		    if (today.isAfter(LocalDate.of(today.getYear(), 3, 25))) {
		        financialYear = today.getYear() + "-" + (today.getYear() + 1) % 100;
		    } else {
		        financialYear = (today.getYear() - 1) + "-" + today.getYear() % 100;
		    }

		    // Loop through all employees
		    for (EmployeeDetailsEntity employee : allEmployees) {
		        String employeeEmail = employee.getEmail();
		        String employeeEmpId = employee.getEmpId();

		        // Determine whether it's time for sick leave or casual leave (based on month number)
		        boolean isSickLeaveMonth = (nextMonthValue % 2 != 0); // Odd month = Sick leave, Even month = Casual leave
		        String leaveType = isSickLeaveMonth ? "Sick Leave" : "Casual Leave";

		        // Check if leave records have already been added for the next month and financial year
		        LeaveRecord existingLeaveRecord = leaveRecordRepository.findByEmailAndLeaveTypeAndMonthAndYear(
		                employeeEmail, leaveType, nextMonth, financialYear);

		        if (existingLeaveRecord == null) {
		            // Create a new leave record for the employee (adding 1 leave for the next month)
		            LeaveRecord leaveRecord = new LeaveRecord();
		            leaveRecord.setLeaveType(leaveType);
		            leaveRecord.setAdded(1); // Add only 1 leave for this month
		            leaveRecord.setMonth(nextMonth);
		            leaveRecord.setRemaining(1); // Initially set to the 1 leave added
		            leaveRecord.setUsed(getUsedLeaveCount(employeeEmail, leaveType.substring(0, 2), "approved")); // SL/CL
		            leaveRecord.setYear(financialYear); // Save as financial year
		            leaveRecord.setEmail(employeeEmail);
		            leaveRecord.setEmpId(employeeEmpId); // Set the employee ID
		            leaveRecordRepository.save(leaveRecord);

		            // Call the updateLeaveUtilized method to update or insert into the leave_utilized table
		            updateLeaveUtilized(employeeEmail, financialYear, leaveType, 1, employeeEmpId); // Called with 5 arguments
		        }

		        // Save a flag in the YearFlag indicating that the task has been executed for this financial year and next month for this employee
		        if (!yearFlagRepository.existsByYearAndEmployeeIdAndMonth(financialYear, employee.getEmpId(), nextMonth)) {
		            YearFlag yearFlag = new YearFlag();
		            yearFlag.setYear(financialYear); // Save as financial year
		            yearFlag.setEmployeeId(employee.getEmpId());
		            yearFlag.setMonth(nextMonth); // Save the next month
		            yearFlagRepository.save(yearFlag);
		        }
		    }
	}
	

    // Method to get Earned Leave
    public EarnedLeaveDTO getEarnedLeave(String email) {
        String monthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        //EarnedLeave earnedLeave = earnedLeaveRepository.findByEmailAndMonthYear(email, monthYear);
        EarnedLeave earnedLeave = earnedLeaveRepository.findByEmail(email);
        if (earnedLeave == null) {
            return new EarnedLeaveDTO(
                null, 0.0, email, monthYear, 0.0, 0.0, null, null
            );
        }

        return new EarnedLeaveDTO(
            earnedLeave.getId(),
            earnedLeave.getEarnedLeave(),
            earnedLeave.getEmail(),
            earnedLeave.getMonthYear(),
            earnedLeave.getElUsed(),
            earnedLeave.getElremaining(),
            earnedLeave.getEmpId(),
            earnedLeave.getLastIncrementDate()
        );
    }
    
    public List<LeaveApplication> getLeaveRequest(int adminDept, String empId) {
        List<LeaveApplication> leaveList = leaveApplicationRepository.getLeaveRequest(adminDept, empId);
        System.out.println("pppp"+leaveList);
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");

        return leaveList.stream().map(leave -> {
            try {
                String formattedFromDate = targetFormat.format(originalFormat.parse(leave.getFrom_date()));
                leave.setFrom_date(formattedFromDate);

                String formattedToDate = targetFormat.format(originalFormat.parse(leave.getTo_date()));
                leave.setFrom_date(formattedToDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return leave;
        }).collect(Collectors.toList());
    }
    
    
    public List<LeaveApplication> getFutureLeaveApplications(String email) {
        
        LocalDate today = LocalDate.now();
    return leaveApplicationRepository.findFutureLeaveApplicationsByEmployeeEmail(email);
   
}


}
