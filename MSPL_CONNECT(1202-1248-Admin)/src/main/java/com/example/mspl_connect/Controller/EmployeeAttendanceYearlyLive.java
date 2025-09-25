package com.example.mspl_connect.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
public class EmployeeAttendanceYearlyLive {

 
	 @Autowired
	 private AttendanceServiceDto attendanceServiceDto;
	
	 @Autowired
	 private EmployeeRepository employeeRepository;

	 @GetMapping("/EmployeeAttendanceYearLive")
	 @ResponseBody
	 public Map<String, Object> getYearlyWorkingHours(@RequestParam("year") int year,@RequestParam("email") String email ,HttpSession session) {
		// Retrieve user details from session
	     //String email = (String) session.getAttribute("email");
	     System.out.println("User login: " + email);
	     String empId = employeeRepository.findEmpidByEmail(email);
	     System.out.println("User empid:-- " + empId);

	     Map<String, Object> response = new HashMap<>();

	     // Define start and end of the year
	     LocalDate startOfYear = LocalDate.of(year, 1, 1);
	     LocalDate endOfYear = LocalDate.of(year, 12, 31);

	     // Retrieve attendance data using AttendanceDTO
	     List<AttendanceDTO> attendanceList = attendanceServiceDto.getFilteredAttendanceForYear(year, empId);
	     
	     List<AttendanceDTO> filteredAttendanceList = attendanceList.stream()
	         .filter(attendance -> !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(startOfYear) &&
	                               !LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(endOfYear))
	         .collect(Collectors.toList());

	     String totalWorkingHoursInYear = attendanceServiceDto.calculateTotalWorkingHoursInYear(startOfYear,empId);
	     int totalWorkingDaysExcludingHolidays = attendanceServiceDto.calculateTotalWorkingDaysExcludingHolidaysAndProbation(startOfYear,empId);
	     int remainingWorkingDaysInYear = attendanceServiceDto.calculateRemainingWorkingDaysInYear(year,empId);


	     Map<String, Double> presentDays = attendanceServiceDto.calculatePresentDaysYearly(filteredAttendanceList,empId);
	     Map<String, Double> absentDays = attendanceServiceDto.calculateAbsentDaysYearly(filteredAttendanceList, totalWorkingDaysExcludingHolidays,empId);
	     Map<String, String> yearlyWorkingHours = attendanceServiceDto.calculateYearlyWorkingHours(filteredAttendanceList);
	     Map<String, String> overtimeMap = attendanceServiceDto.calculateOvertimeYearly(yearlyWorkingHours, totalWorkingHoursInYear);

	     // Retrieve employee details
	     EmployeeDetailsEntity employee = employeeRepository.findByEmpIdAtt(empId)
	             .orElseThrow(() -> new RuntimeException("Employee not found"));
	     String employeeName = employee.getFirstName() + " " + employee.getLastName();
	     System.out.println("@@@ " + employeeName);
	   
	     response.put("employeeName", employeeName); 
	     response.put("hoursWorkedByEmployee", yearlyWorkingHours);
	     response.put("remainingWorkingDaysInYear", remainingWorkingDaysInYear); 
	     response.put("totalWorkingHoursInYear", totalWorkingHoursInYear);
	     response.put("totalWorkingDaysExcludingHolidays", totalWorkingDaysExcludingHolidays);
	     response.put("presentDays", presentDays);
	     response.put("absentDays", absentDays);
	     response.put("overtimeMap", overtimeMap);

	     return response;
	 }

}
