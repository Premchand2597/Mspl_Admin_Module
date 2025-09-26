package com.example.mspl_connect.Controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Service.AttendanceServiceDto;

import jakarta.servlet.http.HttpSession;


@Controller
public class EmployeeAttendanceLive {


	 @Autowired
	 private AttendanceServiceDto attendanceServiceDto;

	 @Autowired
	 private EmployeeRepository employeeRepository;
		
	 @GetMapping("/EmployeeAttendanceLive")
	 @ResponseBody // Ensure it returns JSON data
	 public Map<String, Object> getMonthlyWorkingHours(	    
	     @RequestParam("month") String monthName,HttpSession session, @RequestParam(value = "year", required = false) String selectedYear,
	     @RequestParam("email") String email) {
		 // Retrieve user details from session
	     //String email = (String) session.getAttribute("email");
	     System.out.println("User login:44444 " + email);
	     String empId = employeeRepository.findEmpidByEmail(email);
	     System.out.println("User empid:-- " + empId);

	     // If no year is selected, use the current year
	     if (selectedYear == null || selectedYear.isEmpty()) {
	         selectedYear = String.valueOf(Year.now().getValue()); // Get the current year
	     }
	     
	     System.out.println("Received employeeId:--@ " + empId);
	     System.out.println("Received month parameter:-- " + monthName);

	     // Create a HashMap for month names to numeric values
	     Map<String, Integer> monthMap = new HashMap<>();
	     monthMap.put("January", 1);
	     monthMap.put("February", 2);
	     monthMap.put("March", 3);
	     monthMap.put("April", 4);
	     monthMap.put("May", 5);
	     monthMap.put("June", 6);
	     monthMap.put("July", 7);
	     monthMap.put("August", 8);
	     monthMap.put("September", 9);
	     monthMap.put("October", 10);
	     monthMap.put("November", 11);
	     monthMap.put("December", 12);

	     // Get the numeric month value
	     Integer monthValue = monthMap.get(monthName);
	     if (monthValue == null) {
	         throw new IllegalArgumentException("Invalid month name: " + monthName);
	     }

	     // Get the current year or adjust as needed
	    /* int currentYear = Year.now().getValue();

	     // Create the YearMonth object
	     YearMonth selectedMonth = YearMonth.of(currentYear, monthValue);
	     LocalDate startOfMonth = selectedMonth.atDay(1);
	     LocalDate endOfMonth = selectedMonth.atEndOfMonth();*/

	  // Use the selected year or default to the current year if none is provided
	     int year = Integer.parseInt(selectedYear);

	     // Create the YearMonth object using the selected year and month
	     YearMonth selectedMonth = YearMonth.of(year, monthValue);
	  //   LocalDate startOfMonth = selectedMonth.atDay(1);
	    // LocalDate endOfMonth = selectedMonth.atEndOfMonth();

	  // Adjust the start and end date for custom month range (26th to 25th)
	     LocalDate startOfMonth, endOfMonth;

	     // If month is January, handle previous year's December
	     if (monthValue == 1) {
	         startOfMonth = LocalDate.of(year - 1, 12, 26);
	         endOfMonth = LocalDate.of(year, 1, 25);
	     } else {
	         startOfMonth = LocalDate.of(year, monthValue - 1, 26);
	         endOfMonth = LocalDate.of(year, monthValue, 25);
	     }

	     System.out.println("Custom Month Start: " + startOfMonth);
	     System.out.println("Custom Month End: " + endOfMonth);

	     // Retrieve and filter attendance data for the specific employee
	   //  List<AttendanceDTO> attendanceList = attendanceServiceDto.getFilteredAttendanceForMonth(monthName, empId);
	  // Retrieve attendance within this custom range
	     List<AttendanceDTO> attendanceList = attendanceServiceDto.getFilteredAttendanceForDateRange(startOfMonth, endOfMonth, empId);
	     System.out.println("[[["+attendanceList);
	     List<AttendanceDTO> filteredAttendanceList = attendanceList.stream()
	         .filter(attendance -> !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(startOfMonth) &&
	                                !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(endOfMonth))
	         .collect(Collectors.toList());
	     System.out.println("Total filter list " + filteredAttendanceList);
	     System.out.println("Total filtered records: " + filteredAttendanceList.size());

	     Map<String, String> monthlyWorkingHours = attendanceServiceDto.calculateMonthlyWorkingHours(filteredAttendanceList, selectedMonth);
	     
	   //  TOTAL WORKING HOURS IN A MONTH TILL DATE
	     String totalWorkingHoursInMonth =attendanceServiceDto.calculateTotalWorkingHoursInMonth(selectedMonth, empId);
	     System.out.println("jjj"+totalWorkingHoursInMonth);
	     double totalWorkingDaysExcludingHolidays = attendanceServiceDto.calculateTotalWorkingDaysExcludingHolidays(selectedMonth,empId);
	     System.out.println("@@@ " + totalWorkingDaysExcludingHolidays);
	     //below function to dsplay in the month data IN FRONTEND
	     String totalWorkingHoursInMonthDisplay = attendanceServiceDto.  calculateTotalWorkingHoursInMonthDisplay(selectedMonth, empId);
	     System.out.println("poolll "+totalWorkingHoursInMonthDisplay);
	     int remainingWorkingDays = attendanceServiceDto.calculateRemainingWorkingDaysExcludingHolidays(selectedMonth,empId);
	     
	     Map<String, Double> presentDays = attendanceServiceDto.calculatePresentDays(filteredAttendanceList,selectedMonth,empId);
	     Map<String, Double> absentDays = attendanceServiceDto.calculateAbsentDays(filteredAttendanceList, totalWorkingDaysExcludingHolidays,selectedMonth,empId);
	     // Calculate overtime
	     Map<String, String> overtime = attendanceServiceDto.calculateOvertime(monthlyWorkingHours, totalWorkingHoursInMonth);
	     Map<String, String> undertime = attendanceServiceDto.calculateOvertimeAndUndertime(monthlyWorkingHours, totalWorkingHoursInMonth);

	  // Retrieve employee details
	     EmployeeDetailsEntity employee = employeeRepository.findByEmpIdAtt(empId)
	             .orElseThrow(() -> new RuntimeException("Employee not found"));
	     String employeeName = employee.getFirstName() + " " + employee.getLastName();
	   //  System.out.println("@@@ " + employeeName);
	     
	     Map<String, Object> response = new HashMap<>();
	     response.put("employeeName", employeeName); // Add employee name to the response
	     response.put("hoursWorkedByEmployee", monthlyWorkingHours);
	     response.put("totalWorkingHoursInMonthDisplay",totalWorkingHoursInMonthDisplay);
	     response.put("remainingWorkingDays", remainingWorkingDays); 
	     response.put("totalWorkingDaysExcludingHolidays", totalWorkingDaysExcludingHolidays);
	     response.put("presentDays", presentDays);
	     response.put("absentDays", absentDays);
       response.put("overtime", overtime);
       response.put("undertime", undertime); // Include undertime
       response.put("expectedHoursTillDate", totalWorkingHoursInMonth);
      
	     return response;
	 }

	
	 
	 @GetMapping("/EmployeeAttendanceLiveSecond")
	 @ResponseBody // Ensure it returns JSON data
	 public Map<String, Object> getMonthlyWorkingHoursnew(	    
	     @RequestParam("month") String monthName,HttpSession session, @RequestParam(value = "year", required = false) String selectedYear,
	     @RequestParam("email") String email) {
		 // Retrieve user details from session
	     //String email = (String) session.getAttribute("email");
	     System.out.println("User login: " + email);
	     String empId = employeeRepository.findEmpidByEmail(email);
	     System.out.println("User empid:-- " + empId);

	     // If no year is selected, use the current year
	     if (selectedYear == null || selectedYear.isEmpty()) {
	         selectedYear = String.valueOf(Year.now().getValue()); // Get the current year
	     }
	     
	     System.out.println("Received employeeId:--@ " + empId);
	     System.out.println("Received month parameter:-- " + monthName);
	     System.out.println("Received month parameter:-- " + selectedYear);

	     // Create a HashMap for month names to numeric values
	     Map<String, Integer> monthMap = new HashMap<>();
	     monthMap.put("January", 1);
	     monthMap.put("February", 2);
	     monthMap.put("March", 3);
	     monthMap.put("April", 4);
	     monthMap.put("May", 5);
	     monthMap.put("June", 6);
	     monthMap.put("July", 7);
	     monthMap.put("August", 8);
	     monthMap.put("September", 9);
	     monthMap.put("October", 10);
	     monthMap.put("November", 11);
	     monthMap.put("December", 12);

	     // Get the numeric month value
	     Integer monthValue = monthMap.get(monthName);
	     if (monthValue == null) {
	         throw new IllegalArgumentException("Invalid month name: " + monthName);
	     }

	     // Get the current year or adjust as needed
	    /* int currentYear = Year.now().getValue();

	     // Create the YearMonth object
	     YearMonth selectedMonth = YearMonth.of(currentYear, monthValue);
	     LocalDate startOfMonth = selectedMonth.atDay(1);
	     LocalDate endOfMonth = selectedMonth.atEndOfMonth();*/

	  // Use the selected year or default to the current year if none is provided
	     int year = Integer.parseInt(selectedYear);
 System.out.println("yearr666666666"+year);
	     // Create the YearMonth object using the selected year and month
	     YearMonth selectedMonth = YearMonth.of(year, monthValue);
	    // LocalDate startOfMonth = selectedMonth.atDay(1);
	    // LocalDate endOfMonth = selectedMonth.atEndOfMonth();
	     LocalDate startOfMonth, endOfMonth;

	     // If month is January, handle previous year's December
	     if (monthValue == 1) {
	         startOfMonth = LocalDate.of(year - 1, 12, 26);
	         endOfMonth = LocalDate.of(year, 1, 25);
	     } else {
	         startOfMonth = LocalDate.of(year, monthValue - 1, 26);
	         endOfMonth = LocalDate.of(year, monthValue, 25);
	     }

	     System.out.println("Custom Month Start:7 " + startOfMonth);
	     System.out.println("Custom Month End: 888888" + endOfMonth);

	     
	     // Retrieve and filter attendance data for the specific employee
	    // List<AttendanceDTO> attendanceList = attendanceServiceDto.getFilteredAttendanceForMonthSecond(monthName, empId,year);
	   //  System.out.println("gott"+attendanceList);
	   //  List<AttendanceDTO> filteredAttendanceList = attendanceList.stream()
	    //     .filter(attendance -> !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(startOfMonth) &&
	                              //  !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(endOfMonth))
	    //     .collect(Collectors.toList());
	   //  System.out.println("Total filter list " + filteredAttendanceList);

	     List<AttendanceDTO> attendanceList = attendanceServiceDto.getFilteredAttendanceForDateRange(startOfMonth, endOfMonth, empId);
	     System.out.println("[[["+attendanceList);
	     List<AttendanceDTO> filteredAttendanceList = attendanceList.stream()
	         .filter(attendance -> !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(startOfMonth) &&
	                                !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(endOfMonth))
	         .collect(Collectors.toList());
	     System.out.println("Total filter list888 " + filteredAttendanceList);
	     System.out.println("Total filtered records: " + filteredAttendanceList.size());

	  
	     
	     
	     Map<String, String> monthlyWorkingHours = attendanceServiceDto.calculateMonthlyWorkingHours(filteredAttendanceList, selectedMonth);
	     
	   //  TOTAL WORKING HOURS IN A MONTH TILL DATE
	     String totalWorkingHoursInMonth =attendanceServiceDto.calculateTotalWorkingHoursInMonth(selectedMonth, empId);
	     System.out.println("jjj"+totalWorkingHoursInMonth);
	     double totalWorkingDaysExcludingHolidays = attendanceServiceDto.calculateTotalWorkingDaysExcludingHolidays(selectedMonth,empId);
	     
	     //below function to dsplay in the month data IN FRONTEND
	     String totalWorkingHoursInMonthDisplay = attendanceServiceDto.  calculateTotalWorkingHoursInMonthDisplay(selectedMonth, empId);
	   
	     int remainingWorkingDays = attendanceServiceDto.calculateRemainingWorkingDaysExcludingHolidays(selectedMonth,empId);
	     System.out.println("@@@new " + remainingWorkingDays);
	     
	     Map<String, Double> presentDays = attendanceServiceDto.calculatePresentDays(filteredAttendanceList,selectedMonth,empId);
	     Map<String, Double> absentDays = attendanceServiceDto.calculateAbsentDays(filteredAttendanceList, totalWorkingDaysExcludingHolidays,selectedMonth,empId);
	    // Calculate overtime
	     Map<String, String> overtime = attendanceServiceDto.calculateOvertime(monthlyWorkingHours, totalWorkingHoursInMonth);
	     Map<String, String> undertime = attendanceServiceDto.calculateOvertimeAndUndertime(monthlyWorkingHours, totalWorkingHoursInMonth);
System.out.println("poolll"+undertime + "pkj"+ overtime);
	  // Retrieve employee details
	     EmployeeDetailsEntity employee = employeeRepository.findByEmpIdAtt(empId)
	             .orElseThrow(() -> new RuntimeException("Employee not found"));
	     String employeeName = employee.getFirstName() + " " + employee.getLastName();
	     System.out.println("@@@ " + employeeName);
	     
	     Map<String, Object> response = new HashMap<>();
	     response.put("employeeName", employeeName); // Add employee name to the response
	     response.put("hoursWorkedByEmployee", monthlyWorkingHours);
	     response.put("totalWorkingHoursInMonthDisplay",totalWorkingHoursInMonthDisplay);
	     response.put("remainingWorkingDays", remainingWorkingDays); 
	     response.put("totalWorkingDaysExcludingHolidays", totalWorkingDaysExcludingHolidays);
	     response.put("presentDays", presentDays);
	     response.put("absentDays", absentDays);
       response.put("overtime", overtime);
       response.put("undertime", undertime); // Include undertime
       response.put("expectedHoursTillDate", totalWorkingHoursInMonth);
      
	     return response;
	 }

	

	 
	 @GetMapping("/weeks")
	 public ResponseEntity<List<WeekData>> getWeeksOfMonth(HttpSession session, @RequestParam String month, @RequestParam int year,@RequestParam String email) {
	     // Retrieve user details from session
	     //String email = (String) session.getAttribute("email");
	     System.out.println("User login:::::::: " + email);
	     String empId = employeeRepository.findEmpidByEmail(email);
	     System.out.println("User empid:-- " + empId);

	     // Convert month name to integer (1 for January, 2 for February, etc.)
	     int monthInt = convertMonthNameToInt(month);

	     // Fetch the data from your service or method
	     Map<String, List<LocalDate>> weekMap = attendanceServiceDto.getWeeksOfMonth(monthInt, year);

	     // Convert Map to List<WeekData>
	     List<WeekData> weekDataList = weekMap.entrySet().stream()
	         .map(entry -> new WeekData(entry.getKey(), entry.getValue()))
	         .collect(Collectors.toList());

	     return ResponseEntity.ok(weekDataList);
	 }

	// Helper method to convert month name to integer
	 private int convertMonthNameToInt(String monthName) {
	     DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
	     return Month.valueOf(monthName.toUpperCase()).getValue();  // January = 1, February = 2, etc.
	 }
	 
	 @GetMapping("/attendance-details")
	 @ResponseBody
	 public ResponseEntity<Map<String, String>> getAttendanceDataForDate(
	     @RequestParam("date") String date, 
	     @RequestParam("email") String email,  
	     HttpSession session) {

		  // Retrieve user details from session
	     //String email = (String) session.getAttribute("email");
	     System.out.println("User login:mmmmmm---- " + email);
	     System.out.println("date:mmmmmm---- " + date);
	     String empId = employeeRepository.findEmpidByEmail(email);
	     System.out.println("User empid:--sd " + empId);

		 
		 
	     System.out.println("Received date request parameter:sd " + date);
	    // System.out.println("Received employeeId request parameter: " + employeeId);
	     
	     // Define the incoming and target date formats
	     DateTimeFormatter incomingFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     
	     String formattedDate;
	     try {
	         // Parse the incoming date
	         LocalDate parsedDate = LocalDate.parse(date, incomingFormat);
	         // Format it to the target format
	         formattedDate = parsedDate.format(targetFormat);
	     } catch (DateTimeParseException e) {
	         System.out.println("Invalid date format: " + date);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                              .body(Collections.singletonMap("error", "Invalid date format"));
	     }

	     List<AttendanceDTO> data = attendanceServiceDto.getFilteredAttendanceForDate(formattedDate, empId);
	     
	     System.out.println("Number of records returned by service: " + data.size());

	     if (data.isEmpty()) {
	         System.out.println("No attendance data found for date: " + formattedDate + " and employeeId: " + empId);
	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                              .body(Collections.singletonMap("error", "No attendance data found"));
	     }
	     
	     // Assume the service returns only one record for the given date and employeeId
	     AttendanceDTO attendance = data.get(0);

	     System.out.println("Attendance details:");
	     System.out.println("In Time: " + attendance.getInTime());
	     System.out.println("Out Time: " + attendance.getOutTime());
	     System.out.println("Total Hours: " + attendance.getTotalHours());

	     Map<String, String> response = new HashMap<>();
	     response.put("inTime", attendance.getInTime());
	     response.put("outTime", attendance.getOutTime());
	     response.put("totalHours", attendance.getTotalHours());

	     return ResponseEntity.ok(response);
	 }
	 


}
