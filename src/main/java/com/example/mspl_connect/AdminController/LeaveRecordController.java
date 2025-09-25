package com.example.mspl_connect.AdminController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.LeaveRecord;
import com.example.mspl_connect.AdminEntity.LeaveSummary;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;
import com.example.mspl_connect.AdminRepo.EarnedLeaveSummaryRepository;
import com.example.mspl_connect.AdminRepo.LeaveRecordRepository;
import com.example.mspl_connect.AdminRepo.LeaveSummaryRepository;
import com.example.mspl_connect.AdminService.LeaveApplicationService;
import com.example.mspl_connect.Entity.EarnedLeaveSummary;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveRecordController {

	 @Autowired
	    private LeaveRecordRepository leaveRecordRepository;
	 
	 @Autowired
	    private LeaveSummaryRepository leaveSummaryRepository;
	 
	  @Autowired
	    private LeaveApplicationService leaveApplicationService;
	  
	@Autowired
	private EmployeeRepository employeeRepository;
	
	  @Autowired
	    private EarnedLeaveSummaryRepository earnedLeaveSummaryRepository;
	  
	
	@GetMapping("/details")
	public ResponseEntity<List<Map<String, Object>>> getLeaveDetails(HttpSession session) {
	    String email = (String) session.getAttribute("email");
	    System.out.println("Fetching leaves for user: " + email);

	    String empId = employeeRepository.findEmpidByEmail(email);
	    System.out.println("Employee ID retrieved: " + empId);

	    List<LeaveRecord> leaveRecords = leaveRecordRepository.findByEmailAndEmpId(email, empId);
	    List<Map<String, Object>> responseList = new ArrayList<>();

	    for (LeaveRecord record : leaveRecords) {
	        Map<String, Object> recordMap = new HashMap<>();
	        recordMap.put("month", record.getMonth());
	        recordMap.put("added", record.getAdded());
	        recordMap.put("leaveType", record.getLeaveType());
	        responseList.add(recordMap);
	    }

	    return ResponseEntity.ok(responseList);
	}

	@GetMapping("/leaveRecordsdetails")
	public ResponseEntity<List<Map<String, Object>>> getLeaveRecords(HttpSession session) {
	    // Retrieve the email from the session
	    String email = (String) session.getAttribute("email");
	    System.out.println("Email retrieved from session: " + email);

	    // Fetch employee ID using the email
	    String empId = employeeRepository.findEmpidByEmail(email);
	    System.out.println("Fetching leave records for employee ID: " + empId);

	    // Get all leave records for the employee
	    List<LeaveUtilized> leaveRecords = leaveApplicationService.getAllLeaveRecords(empId);

	    // Prepare a list to store transformed records
	    List<Map<String, Object>> responseList = new ArrayList<>();

	    if (leaveRecords != null && !leaveRecords.isEmpty()) {
	        for (LeaveUtilized record : leaveRecords) {
	            Map<String, Object> recordMap = new HashMap<>();
	            recordMap.put("leaveType", record.getLeaveType());

	            // Calculate Previous Remaining (assuming 1 leave is deducted from 'added' column)
	            double previousRemaining = record.getRemaining() - 1;
	            recordMap.put("previousRemaining", previousRemaining);

	            // Calculate Current Remaining
	            double currentRemaining = record.getRemaining();
	            recordMap.put("currentRemaining", currentRemaining);

	            // Print the Previous and Current Remaining values
	            System.out.println("Leave Type: " + record.getLeaveType());
	            System.out.println("Previous Remaining: " + previousRemaining);
	            System.out.println("Current Remaining: " + currentRemaining);

	            // Add additional fields if necessary
	            recordMap.put("financialYear", record.getFinancialYear());

	            // Add the map to the response list
	            responseList.add(recordMap);
	        }
	    }

	    return ResponseEntity.ok(responseList);
	}
	

	
	
	/*used@GetMapping("/mergedLeaveDetails")
	public ResponseEntity<List<Map<String, Object>>> getMergedLeaveDetails(HttpSession session) {
	    // Retrieve the email from the session
	    String email = (String) session.getAttribute("email");
	    System.out.println("Fetching merged leave details for user: " + email);

	    // Fetch employee ID using the email
	    String empId = employeeRepository.findEmpidByEmail(email);
	    System.out.println("Employee ID retrieved: " + empId);

	    // Query the leave_summary table for data related to the employee
	    List<LeaveSummary> leaveSummaryList = leaveSummaryRepository.getLeaveSummaryByEmpid(empId);

	    // Initialize a map to store remaining balances for each leave type by month
	    Map<String, Map<String, Double>> monthBalances = new HashMap<>();

	    // Process the leave summary data and calculate remaining leave balances
	    leaveSummaryList.forEach(record -> {
	        String leaveType = record.getLeaveType(); // Assuming LeaveSummary has a 'getLeaveType' method
	        String month = record.getMonth(); // Assuming LeaveSummary has a 'getMonth' method
	        
	        Double added = record.getAdded(); // Assuming LeaveSummary has a 'getAdded' method
	        Double remaining = record.getRemaining(); // Assuming LeaveSummary has a 'getRemaining' method

	        // Initialize the nested map if it doesn't exist for the leave type
	        monthBalances.putIfAbsent(leaveType, new HashMap<>());

	        // Get the current remaining balance for this leave type and month
	        Double currentRemaining = monthBalances.get(leaveType).getOrDefault(month, remaining);

	        // Deduct the added leaves to calculate the updated current remaining
	        currentRemaining -= added;

	        // Update the balance in the map for this leave type and month
	        monthBalances.get(leaveType).put(month, currentRemaining);
	    });

	    // Prepare the response by combining leave data into a list of maps
	    List<Map<String, Object>> responseList = new ArrayList<>();

	    leaveSummaryList.forEach(record -> {
	        Map<String, Object> recordMap = new HashMap<>();
	        String leaveType = record.getLeaveType(); // Get leave type
	        String month = record.getMonth(); // Get month
	        String financialYear = record.getFinancialYear(); 
	        Double added = record.getAdded(); // Get added leave days
	        Double previousRemaining = record.getRemaining(); // Get previous remaining leave days
	        String lastUpdated = record.getLastUpdated();
	        // Calculate current count: added + remaining
	        Double currentCount = added + previousRemaining;

	        // Determine the color for the current count
	        String color = "black"; // Default color
	        if (currentCount < 0) {
	            color = "red"; // Negative balance (could be yellow or red)
	        } else if (currentCount > 0) {
	            color = "green"; // Positive balance (could be green)
	        }

	        // Populate the record map
	        recordMap.put("month", month);
	        recordMap.put("year", financialYear);
	        recordMap.put("added", added);
	        recordMap.put("leaveType", leaveType);
	        recordMap.put("previousRemaining", previousRemaining); // Rename to previousCount
	        recordMap.put("currentRemaining", currentCount); // Current count = added + remaining
	        recordMap.put("lastUpdated", lastUpdated); 
	        recordMap.put("color", color); // Add color indication

	        // Print out the values for debugging
	        System.out.println("Year: " + financialYear +"Month: " + month + ", Leave Type: " + leaveType + 
	                           ", Added: " + added + ", Previous Count: " + previousRemaining + 
	                           ", Current Count: " + currentCount + ", Color: " + color);

	        // Add the record to the response list
	        responseList.add(recordMap);
	    });

	    // Return the merged leave details as the response
	    return ResponseEntity.ok(responseList);
	}*/
	

    @GetMapping("/mergedLeaveDetails")
    public ResponseEntity<List<Map<String, Object>>> getMergedLeaveDetails(@RequestParam String email, HttpSession session) {
        // Retrieve the email from the session
        //String email = (String) session.getAttribute("email");
        System.out.println("Fetching merged leave details for HRRRRRRRRRRRRR: " + email);

        // Fetch employee ID using the email
        String empId = employeeRepository.findEmpidByEmail(email);
        System.out.println("Employee ID retrieved: " + empId);

        // Fetch SL and CL data from leave_summary
        List<LeaveSummary> leaveSummaryList = leaveSummaryRepository.getLeaveSummaryByEmpid(empId);

        // Fetch EL data from earned_leave_summary
        List<EarnedLeaveSummary> earnedLeaveList = earnedLeaveSummaryRepository.getEarnedLeaveSummaryByEmpid(empId);
     // Debugging to see the contents of earnedLeaveList
        if (earnedLeaveList.isEmpty()) {
            System.out.println("No Earned Leave data found for employee: " + email);
        } else {
            earnedLeaveList.forEach(record -> System.out.println("EL Record: " + record));
        }

        // Response list to combine all leave types
        List<Map<String, Object>> responseList = new ArrayList<>();

        // Process SL and CL leave summary data
        leaveSummaryList.forEach(record -> {
            Map<String, Object> recordMap = new HashMap<>();
            String leaveType = record.getLeaveType();
            String month = record.getMonth();
            String financialYear = record.getFinancialYear();
            Double added = record.getAdded();
            Double previousRemaining = record.getRemaining();
            String lastUpdated = record.getLastUpdated();
            String remarks = record.getRemarks();

            // Calculate current count: added + remaining
            Double currentCount = added + previousRemaining;

            // Determine color for the current count
            String color = "black"; // Default
            if (currentCount < 0) {
                color = "red";
            } else if (currentCount > 0) {
                color = "green";
            }

            // Debug output
            System.out.println("SL/CL Record -> Month: " + month + ", Year: " + financialYear +
                ", Leave Type: " + leaveType + ", Added: " + added +
                ", Previous Remaining: " + previousRemaining + ", Current Remaining: " + currentCount +
                ", Last Updated: " + lastUpdated + ", Color: " + color);

            // Populate the record map
            recordMap.put("month", month);
            recordMap.put("year", financialYear);
            recordMap.put("added", added);
            recordMap.put("leaveType", leaveType);
            recordMap.put("previousRemaining", previousRemaining);
            recordMap.put("currentRemaining", currentCount);
            recordMap.put("lastUpdated", lastUpdated);
            recordMap.put("remarks", remarks);
            recordMap.put("color", color);

            // Add to the response list
            responseList.add(recordMap);
        });

        // Process EL data
        earnedLeaveList.forEach(record -> {
            Map<String, Object> recordMap = new HashMap<>();
            String empIdFromRecord = record.getEmpId();
            String emailFromRecord = record.getEmail();
            String incrementedDate = record.getIncrementedDate();
            Double addedAsEarnedLeave = record.getAdded();
            Double remaining = record.getRemaining();
            String lastUpdated = record.getLastUpdated();
            String remarks = record.getRemarks();
            // Calculate current count: added + remaining
            Double currentCount = addedAsEarnedLeave + remaining;
            // Debug output
            System.out.println("EL Record -> Employee ID: " + empIdFromRecord + ", Email: " + emailFromRecord +
                ", Incremented Date: " + incrementedDate + ", Added as Earned Leave: " + addedAsEarnedLeave +
                ", Remaining: " + remaining + ", Last Updated: " + lastUpdated);

            // Populate the record map
            recordMap.put("empId", empIdFromRecord);
            recordMap.put("email", emailFromRecord);
            recordMap.put("incrementedDate", incrementedDate);
            recordMap.put("added", addedAsEarnedLeave);
            recordMap.put("previousRemaining", remaining);
            recordMap.put("currentRemaining", currentCount);
            recordMap.put("leaveType", "Earned Leave");
            recordMap.put("lastUpdated", lastUpdated);
            recordMap.put("remarks", remarks);
            // Add to the response list
            responseList.add(recordMap);
        });
        // Return the combined response
        return ResponseEntity.ok(responseList);
    }
}
