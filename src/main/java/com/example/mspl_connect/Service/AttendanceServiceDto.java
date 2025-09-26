package com.example.mspl_connect.Service;


import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminRepo.LeaveApplicationRepository;
import com.example.mspl_connect.AdminRepo.UserCheckinRepository;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.Holiday_Entity;
import com.example.mspl_connect.Repository.AttendanceRepositoryCustom;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.Holiday_Repo;
 




@Service
public class AttendanceServiceDto {

	@Autowired
    private AttendanceRepositoryCustom attendanceRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	 @Autowired
	 private Holiday_Repo holidayRepository; // Assuming you have a repository for the holidays

	 @Autowired
	 private Holiday_Service holidayService;
	 

		@Autowired
		private EmployeeRepository employeeRepository;
		
		@Autowired
		private UserCheckinRepository userCheckinRepository;

		@Autowired
		private LeaveApplicationRepository leaveApplicationRepository;

		  @Autowired
			private EmployeeDetaisService  employeeDetailsService;
			
		 
		  @Autowired
		    private JdbcTemplate jdbcTemplate;
	 
 private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 
	 private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	 private static final int WORKING_HOURS_PER_DAY = 9;


    public List<AttendanceDTO> getFilteredAttendanceForMonth(String month, String employeeId) {
    	System.out.println("===");
    	System.out.println("=="+month);
    	System.out.println("="+employeeId);
        return attendanceRepository.findAttendanceByMonthAndEmployee(month, employeeId);
    }
    
    public List<AttendanceDTO> getFilteredAttendanceForDateRange(LocalDate startDate, LocalDate endDate, String employeeId) {
    	
    	System.out.println("=uuuuuuu=");
    	System.out.println("=uuuuuuu=="+startDate);
    	System.out.println("=uuuuuuu="+employeeId);
        return attendanceRepository.findAttendanceByDateRange(startDate, endDate, employeeId);
    }

    
    public List<AttendanceDTO> getFilteredAttendanceForPeriod(LocalDate startDate, LocalDate endDate, String empId) {
    	System.out.println("===" + endDate);
    	System.out.println("=="+startDate);
    	System.out.println("="+empId);
    	 return attendanceRepository.findAttendanceBetweenDates(empId, startDate, endDate);
    }
    
    
    public List<AttendanceDTO> getFilteredAttendanceForMonthSecond(String month, String employeeId, int year) {
        System.out.println("===");
        System.out.println("=="+month);
        System.out.println("="+employeeId);
        return attendanceRepository.findAttendanceByMonthAndEmployee(month, employeeId, year);
    }

    
 /*   public List<AttendanceDTO> getFilteredAttendanceForMonthtocountbasedonattendance(String month, String employeeId) {
    	System.out.println("===");
    	System.out.println("=="+month);
    	System.out.println("="+employeeId);
        return attendanceRepository.findAttendanceByMonthAndEmployee(month, employeeId);
    }*/
	
    public List<AttendanceDTO> getFilteredAttendanceForYear(int year, String employeeId) {
    	System.out.println("====++++");
        return attendanceRepository.findAttendanceByyearAndEmployee(year, employeeId);
    }
    
    
    public List<AttendanceDTO> getFilteredAttendanceForDate(String formattedDate, String employeeId) {
        System.out.println("Fetching attendance for date: " + formattedDate + " and employeeId: " + employeeId);
        return attendanceRepository.findAttendanceByDateAndEmployee(formattedDate, employeeId);
    }


    //for Employee
    public Map<String, String> calculateMonthlyWorkingHours(List<AttendanceDTO> attendanceList, YearMonth selectedMonth) {
        // Define the start and end dates for the given month
       // LocalDate startOfMonth = selectedMonth.atDay(1);
       // LocalDate endOfMonth = selectedMonth.atEndOfMonth();
    	// Adjust start and end dates: 26th of previous month to 25th of current month
    	LocalDate startOfMonth = selectedMonth.minusMonths(1).atDay(26); // 26th of previous month
    	LocalDate endOfMonth = selectedMonth.atDay(25); // 25th of current month

        Map<String, Duration> durationMap = attendanceList.stream()
            .filter(attendance -> {
                LocalDate attendanceDate = LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                boolean isInMonth = !attendanceDate.isBefore(startOfMonth) && !attendanceDate.isAfter(endOfMonth);
                return isInMonth;
            })
            .filter(attendance -> attendance.getInTime() != null && attendance.getOutTime() != null)
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid,
                Collectors.reducing(Duration.ZERO, 
                                    attendance -> calculateWorkingHours(attendance.getInTime(), attendance.getOutTime()), 
                                    Duration::plus)
            ));

        // Format the duration into HH:mm:ss
        return durationMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> formatDuration(entry.getValue())
            ));
    }
    
    
    /*---------------------------------------*/
  /*  public String calculateTotalWorkingHoursInMonth(YearMonth selectedMonth) {
        // Calculate total working days in the specified month
        int totalWorkingDays = calculateTotalWorkingDays(selectedMonth);
       // System.out.println("Total working days in the month (excluding Sundays): " + totalWorkingDays);

        // Assuming 9 working hours per day
        Duration totalDuration = Duration.ofHours(9).multipliedBy(totalWorkingDays);
        String formattedDuration = formatDuration(totalDuration);
      //  System.out.println("Total expected working hours in the month (HH:mm:ss): " + formattedDuration);

        return formattedDuration;
    }*/
    
    //for monthly 
    public String calculateTotalWorkingHoursInMonth(YearMonth selectedMonth, String empid) {
        // Get the current date and current year-month
        LocalDate today = LocalDate.now();
     //   YearMonth currentMonth = YearMonth.from(today);
        // Determine the start and end of the custom month period
        LocalDate startOfMonth = selectedMonth.minusMonths(1).atDay(26); // 26th of previous month
        LocalDate endOfMonth = selectedMonth.atDay(25); // 25th of the selected month
     // Check if today falls within this custom month period
        boolean isCurrentCustomMonth = !today.isBefore(startOfMonth) && !today.isAfter(endOfMonth);

        // Determine if the selected month is the current month
      //  boolean isCurrentMonth = selectedMonth.equals(currentMonth);
        // Adjust the end date if it's the current custom month and today is after endOfMonth
        if (isCurrentCustomMonth && today.isAfter(endOfMonth)) {
            endOfMonth = today.minusDays(1);
        }
        System.out.println("Selected Month: " + selectedMonth);
        System.out.println("Current Monthhhhhh: " + isCurrentCustomMonth);
        System.out.println("Is Current Month: " + isCurrentCustomMonth);

        System.out.println("Selected Month: " + selectedMonth);
        System.out.println("Current Date: " + today);
        System.out.println("Custom Start of Month: " + startOfMonth);
        System.out.println("Custom End of Month: " + endOfMonth);
        System.out.println("Is Current Custom Month: " + isCurrentCustomMonth);

        // Calculate the end date for the selected month
      //  LocalDate endOfMonth = selectedMonth.atEndOfMonth();
        if (isCurrentCustomMonth) {
            endOfMonth = today.minusDays(1); // Calculate till yesterday if it's the current month
            System.out.println("End of Month adjusted to:ere " + endOfMonth);
        } else {
            System.out.println("End of Month:DFE " + endOfMonth);
        }

        // Calculate total working days in the period
        double totalWorkingDays = calculateTotalWorkingDays(selectedMonth, endOfMonth,empid);
        System.out.println("Total Working Days:FDE " + totalWorkingDays);
        // Compute total working hours based on full and half days
        long totalHours = (long) (totalWorkingDays * 9);  // Convert to whole hours
        long totalMinutes = (long) ((totalWorkingDays * 9 - totalHours) * 60);  // Convert decimal part to minutes

        // Assuming 9 working hours per day
     // Create Duration using calculated hours and minutes
        Duration totalDuration = Duration.ofHours(totalHours).plusMinutes(totalMinutes);
        String formattedDuration = formatDuration(totalDuration);

        System.out.println("Total Duration (HH:mm:ss): " + formattedDuration);

        return formattedDuration;
    }
   
    
    // To display in month data (TOTAL EXPECTED WORKING HOURS)
  /*imp  public String calculateTotalWorkingHoursInMonthDisplay(YearMonth selectedMonth, String empid) {
        // Calculate total working days in the specified month
    	double  totalWorkingDays = calculateTotalWorkingDays(selectedMonth,empid);
       System.out.println("Total working days in the month (excluding Sundays): ddd" + totalWorkingDays);

        // Assuming 9 working hours per day
        Duration totalDuration = Duration.ofHours(9).multipliedBy(totalWorkingDays);
        String formattedDuration = formatDuration(totalDuration);
        System.out.println("Total expected working hours in the month (HH:mm:ss):ss " + formattedDuration);

        return formattedDuration;
    }*/
    public String calculateTotalWorkingHoursInMonthDisplay(YearMonth selectedMonth, String empid) {
        // Calculate total working days in the specified month
        double totalWorkingDays = calculateTotalWorkingDays(selectedMonth, empid);
        System.out.println("Total working days in the month (including half-days): " + totalWorkingDays);

        // Compute total working hours based on full and half days
        long totalHours = (long) (totalWorkingDays * 9);  // Convert to whole hours
        long totalMinutes = (long) ((totalWorkingDays * 9 - totalHours) * 60);  // Convert decimal part to minutes

        // Create Duration using calculated hours and minutes
        Duration totalDuration = Duration.ofHours(totalHours).plusMinutes(totalMinutes);
        String formattedDuration = formatDuration(totalDuration);

        System.out.println("Total expected working hours in the month (HH:mm:ss): " + formattedDuration);
        return formattedDuration;
    }

    
    
   /* private int calculateTotalWorkingDays(YearMonth selectedMonth, LocalDate endOfMonth) {
        LocalDate startOfMonth = selectedMonth.atDay(1);
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month for Working Days Calculation: " + endOfMonth);

        // Retrieve holidays for the selected month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(selectedMonth);
        System.out.println("Holidays: " + holidays);

        // Calculate total working days within the period excluding Sundays and holidays
        int workingDays = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) && !holidays.contains(date))
                .count();

        System.out.println("Calculated Working Days: @@" + workingDays);
        return workingDays;
    }*/
    
    
   
    
    
   /* private int calculateTotalWorkingDays(YearMonth selectedMonth, LocalDate endOfMonth, String empid) {
        LocalDate startOfMonth = selectedMonth.atDay(1);
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month for Working Days Calculation: " + endOfMonth);
        System.out.println("sssss " + empid);

        // Retrieve holidays for the selected month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(selectedMonth);
        System.out.println("Holidays: " + holidays);

        // Retrieve the employee's leave records for the selected month and check for approved leaves
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        
        // If no leave records are found, initialize an empty set for leave days
        Set<LocalDate> leaveDays = new HashSet<>();
        
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                // Ensure 'approvedstatus' is "Approved" and that 'fromDate' and 'toDate' are not null
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFromDate() != null && record.getToDate() != null) {

                    LocalDate fromDate = LocalDate.parse(record.getFromDate());
                    LocalDate toDate = LocalDate.parse(record.getToDate());
                    
                    // Add all the days in the leave range to the leaveDays set
                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                        leaveDays.add(date);
                    }
                }
            }
        }
        System.out.println("Leave Days: " + leaveDays);

        // Calculate total working days within the period excluding Sundays, holidays, and leave days
        int workingDays = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) && 
                                !holidays.contains(date) && 
                                !leaveDays.contains(date))
                .count();

        System.out.println("Calculated Working Days: " + workingDays);
        return workingDays;
    }*/

   /* private int calculateTotalWorkingDays(YearMonth selectedMonth, LocalDate endOfMonth, String empid) {
        LocalDate startOfMonth = selectedMonth.atDay(1);
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month for Working Days Calculation: " + endOfMonth);
        System.out.println("sssss " + empid);
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email:-- " + email);

        // Retrieve holidays for the selected month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(selectedMonth);
        System.out.println("Holidays: " + holidays);

        // Retrieve the employee's leave records for the selected month and check for approved leaves
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        
        // If no leave records are found, initialize an empty set for leave days
        Set<LocalDate> leaveDays = new HashSet<>();
        
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                // Ensure 'approvedstatus' is "Approved" and that 'fromDate' and 'toDate' are not null
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFromDate() != null && record.getToDate() != null) {

                    LocalDate fromDate = LocalDate.parse(record.getFromDate());
                    LocalDate toDate = LocalDate.parse(record.getToDate());
                    
                    // Add all the days in the leave range to the leaveDays set
                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                        leaveDays.add(date);
                    }
                }
            }
        }
        System.out.println("Leave Days: " + leaveDays);

        // Get the probation completion date
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (probationDateStr == null || probationDateStr.isEmpty()) {
            System.out.println("Error: Probation completion date is null or empty for email: " + email);
            return 0; // Or handle the scenario appropriately
        }
        LocalDate probationCompletionDate = LocalDate.parse(probationDateStr);
      //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        System.out.println("Two Years After Probation Date: " + probationCompletionDate);

        // Calculate total working days within the period excluding Sundays, holidays, and leave days
        int workingDays = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) && 
                                !holidays.contains(date) && 
                                !leaveDays.contains(date) && 
                                (!hasCompletedTwoYears(probationCompletionDate, date) || !isSecondOrFourthSaturday(date)))
                .count();

        System.out.println("Calculated Working Days: " + workingDays);
        return workingDays;
    }*/
    private double  calculateTotalWorkingDays(YearMonth selectedMonth, LocalDate endOfMonth, String empid) {
      //  LocalDate startOfMonth = selectedMonth.atDay(1);
     // Determine start and end of the custom month period
        LocalDate startOfMonth = selectedMonth.minusMonths(1).atDay(26); // 26th of previous month
        LocalDate endOfMonth1 = selectedMonth.atDay(25); // 25th of selected month
        
        System.out.println("Custom Start of Month: " + startOfMonth);
        System.out.println("Custom End of Month: " + endOfMonth1);
        
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month for Working Days Calculation: " + endOfMonth);
        System.out.println("Employee ID: " + empid);
        // Fetch employee details including DOJ
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empid);
        LocalDate doj = null;

        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj()); // Convert String to LocalDate
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }

        System.out.println("Employee DOJ: " + doj);

        // Adjust `startOfMonth` if the employee joined in the same month and year
        if (doj != null && doj.getYear() == selectedMonth.getYear() && doj.getMonth() == selectedMonth.getMonth()) {
            startOfMonth = doj; // Adjust start date to DOJ
            System.out.println("Adjusted Start Date to DOJ: " + startOfMonth);
        }
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email: " + email);

        // Retrieve holidays for the selected month
       // Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(selectedMonth);
        Set<LocalDate> holidays = holidayService.getHolidaysForCustomMonth(startOfMonth, endOfMonth);
        System.out.println("Holidays in Custom Period: " + holidays);

        System.out.println("Holidays: " + holidays);

        // Retrieve the employee's leave records for the selected month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        
        Map<LocalDate, Double> leaveDays = new HashMap<>(); 
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFrom_date() != null && record.getTo_date() != null) {

                    LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                    LocalDate toDate = LocalDate.parse(record.getTo_date());

                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    	double leaveDuration = "Half Day".equals(record.getLeaveDuration()) ? 0.5 : 1.0;
                        leaveDays.put(date, leaveDays.getOrDefault(date, 0.0) + leaveDuration); // Accumulate half-day leaves
                    }
                }
            }
        }
        System.out.println("Leave Daysdddddddd: " + leaveDays);

        // Get the probation completion date
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (probationDateStr == null || probationDateStr.isEmpty()) {
            System.out.println("Error: Probation completion date is null or empty for email: " + email);
            
         // Proceed with the calculation, assuming probation completion date doesn't exist
            return startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) && !holidays.contains(date))
                    .mapToDouble(date -> 1.0 - leaveDays.getOrDefault(date, 0.0)) // Deduct correctly
                    .sum();
        }

        LocalDate probationCompletionDate = LocalDate.parse(probationDateStr);
        System.out.println("Probation Completion Date: " + probationCompletionDate);

       
     // Calculate total working days within the period
        double workingDays = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                !holidays.contains(date) &&
                                (!hasCompletedTwoYears(probationCompletionDate, date) || !isSecondOrFourthSaturday(date)))
                .mapToDouble(date -> 1.0 - leaveDays.getOrDefault(date, 0.0)) // Deduct correctly
                .sum();

        System.out.println("Calculated Working Daysfffffffffff: " + workingDays);
        return workingDays;
    }

    // Check if the employee has completed two years from their probation date
    private boolean hasCompletedTwoYears(LocalDate probationCompletionDate, LocalDate date) {
        return date.isAfter(probationCompletionDate);
    }

    // Check if a date is the second or fourth Saturday of the month
    private boolean isSecondOrFourthSaturday(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
               (getWeekOfMonth(date) == 2 || getWeekOfMonth(date) == 4);
    }
    
 // Get the week of the month for a given date
    private int getWeekOfMonth(LocalDate date) {
        return (date.getDayOfMonth() - 1) / 7 + 1;
    }
   
   

    //
   /* public int calculateTotalWorkingDays(YearMonth yearMonth) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        // Fetch holidays for the specified month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(yearMonth);
        System.out.println("Holidays in month:www " + holidays);

        int workingDays = 0;

        // Iterate through each day of the month
        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date);
            if (isWorkingDay) {
                workingDays++;
            }
            System.out.println("Date: " + date + " is working day: " + isWorkingDay);
        }

        System.out.println("Total working days excluding Sundays and holidays: " + workingDays);
        return workingDays;
    }*/
    
  /* public int calculateTotalWorkingDays(YearMonth yearMonth, String empid) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
 String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email:-- " + email);


        // Fetch holidays for the specified month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(yearMonth);
        System.out.println("Holidays in month: " + holidays);

        // Fetch the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Iterate over leave records to collect the leave days
        for (LeaveApplication record : leaveRecords) {
            // Ensure the leave status is "Approved" and both fromDate and toDate are not null
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());
                
                // Add all the days in the leave range to the leaveDays set
                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        System.out.println("Leave Days: " + leaveDays);

        int workingDays = 0;

        // Iterate through each day of the month
        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && 
                                    !holidays.contains(date) && 
                                    !leaveDays.contains(date);
            if (isWorkingDay) {
                workingDays++;
            }
            System.out.println("Date: " + date + " is working day: " + isWorkingDay);
        }

        System.out.println("Total working days excluding Sundays, holidays, and leave days: " + workingDays);
        return workingDays;
    }*/
  
   /*io public int calculateTotalWorkingDays(YearMonth yearMonth, String empid) {
        // Set the start and end dates for the month
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        // Fetch the employee's email based on employee ID
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email: " + email);

        // Retrieve holidays for the specified month
        Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(yearMonth);
        System.out.println("Holidays in month: " + holidays);

        // Fetch the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Iterate over leave records to collect the leave days
        for (LeaveApplication record : leaveRecords) {
            // Ensure the leave status is "Approved" and both fromDate and toDate are not null
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());

                // Add all the days in the leave range to the leaveDays set
                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        System.out.println("Leave Days:hg " + leaveDays);

        // Get the probation completion date
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
      //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        System.out.println("Two Years After Probation Date:ftd " + twoYearsAfterProbation);

        int workingDays = 0;

        // Iterate through each day of the month
        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && 
                                    !holidays.contains(date) && 
                                    !leaveDays.contains(date) && 
                                    (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date));
            if (isWorkingDay) {
                workingDays++;
            }
            System.out.println("Date: " + date + " is working day:hf " + isWorkingDay);
        }

        System.out.println("Total working days excluding Sundays, holidays, and leave days: hf" + workingDays);
        return workingDays;
    }*/

    public double calculateTotalWorkingDays(YearMonth yearMonth, String empid) {
        // Set start and end dates for the month
     //   LocalDate startOfMonth = yearMonth.atDay(1);
      //  LocalDate endOfMonth = yearMonth.atEndOfMonth();
    	// Set the new start and end range: 26th of the previous month to 25th of the current month
    	LocalDate startOfMonth = yearMonth.minusMonths(1).atDay(26); // 26th of previous month
    	LocalDate endOfMonth = yearMonth.atDay(25); // 25th of current month
    	LocalDate adjustedStartOfMonth = startOfMonth;
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month: dsssss" + endOfMonth);
        System.out.println("Employee ID: " + empid);

        // Fetch employee details (including DOJ)
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empid);
        LocalDate doj = null;

        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj()); // Convert String to LocalDate
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }

        System.out.println("Employee DOJ: " + doj);

        // Adjust `startOfMonth` if the employee joined within this month and year
        if (doj != null && doj.getYear() == yearMonth.getYear() && doj.getMonth() == yearMonth.getMonth()) {
            startOfMonth = doj; // Set start date to DOJ
            System.out.println("Adjusted Start Date to DOJ: " + startOfMonth);
        }

        // Fetch employee email based on empid
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email: " + email);

        // Retrieve holidays for the selected month
     //   Set<LocalDate> holidays = holidayService.getHolidaysForMonthforhour(yearMonth);
        // Fetch all holidays (without filtering them by month)
        Set<LocalDate> allHolidays = holidayService.getAllHolidaysnew();
        
        // Filter holidays dynamically based on the custom date range
        Set<LocalDate> holidays = allHolidays.stream()
            .filter(date -> !date.isBefore(adjustedStartOfMonth) && !date.isAfter(endOfMonth))
            .collect(Collectors.toSet());

        System.out.println("Holidays in month: " + holidays);

        System.out.println("Holidays in month: " + holidays);

        // Retrieve the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        //Set<LocalDate> leaveDays = new HashSet<>();
        Set<LocalDate> fullDayLeaveDays = new HashSet<>();
        Map<LocalDate, Double> halfDayLeaves = new HashMap<>(); // Stores half-day leaves (date → 0.5 deduction)


        // Collect leave days from approved leave records
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFrom_date() != null && record.getTo_date() != null) {

                    LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                    LocalDate toDate = LocalDate.parse(record.getTo_date());
                  //  String leaveDuration = record.getLeaveDuration();
                    
                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    	 if ("Half Day".equals(record.getLeaveDuration())) {
                             halfDayLeaves.put(date, 0.5); // Deduct 0.5 for half-day leaves
                         } else {
                             fullDayLeaveDays.add(date); // Full-day leaves
                         }
                    }
                }
            }
        }
        System.out.println("Full Day Leave Dayssss: " + fullDayLeaveDays);
        System.out.println("Half Day Leave Dayssss: " + halfDayLeaves);


        // Get the probation completion date
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (probationDateStr == null || probationDateStr.isEmpty()) {
            System.out.println("Error: Probation completion date is null or empty for email: " + email);

            // Calculate working days **without probation logic**
            double workingDays = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                    !holidays.contains(date))
                    .mapToDouble(date -> fullDayLeaveDays.contains(date) ? 0.0 : (halfDayLeaves.getOrDefault(date, 1.0)))
                    .sum();

            System.out.println("Calculated Working Days (Without Probation Logic)asas: " + workingDays);
            return workingDays;
        }
        // Parse the probation completion date
        LocalDate twoYearsAfterProbation = LocalDate.parse(probationDateStr);
        System.out.println("Probation Completion Date: " + twoYearsAfterProbation);
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
       // System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);

        // Calculate total working days
     // Calculate total working days **with probation logic**
        double workingDays = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                !holidays.contains(date) &&
                                (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                .mapToDouble(date -> fullDayLeaveDays.contains(date) ? 0.0 : (halfDayLeaves.getOrDefault(date, 1.0)))
                .sum();

        System.out.println("Calculated Working Days: " + workingDays);
        return workingDays;
    }

	// Helper method to format Duration to HH:mm:ss
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    // Convert String date to LocalDate
    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + dateString);
            return null;
        }
    }

    // Check if the attendance date is within the current month
    private boolean isInCurrentMonth(LocalDate date, LocalDate startOfMonth, LocalDate endOfMonth) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(startOfMonth) && !date.isAfter(endOfMonth);
    }

 // Method to calculate working hours between inTime and outTime as a Duration
    private Duration calculateWorkingHours(String inTime, String outTime) {
        if (inTime == null || outTime == null) {
            return Duration.ZERO;
        }
        try {
            java.time.LocalTime start = java.time.LocalTime.parse(inTime);
            java.time.LocalTime end = java.time.LocalTime.parse(outTime);
            return Duration.between(start, end);
        } catch (Exception e) {
            System.out.println("Error calculating working hours between " + inTime + " and " + outTime);
            return Duration.ZERO;
        }
    }
    
    /*---------------------------------------------------------------------------------------------------------*/
    
    
    
    //-----------working days in a month(COLUMN : TOTAL WORKING DAYS)
 // Calculate total working days excluding Sundays and holidays
  /*  public int calculateTotalWorkingDaysExcludingHolidays(YearMonth yearMonth) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
       // System.out.println("Calculating working days from " + startOfMonth + " to " + endOfMonth);

        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
      //  System.out.println("Holidays in month: " + holidays);

        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        int workingDays = 0;

        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date);
            if (isWorkingDay) {
                workingDays++;
            }
            
         //   System.out.println("Date: " + date + " is working day: " + isWorkingDay);
        }

     //   System.out.println("Total working days in the month (excluding Sundays and holidays): " + workingDays);
        return workingDays;
    }*/
    
    //-----------working days in a month(COLUMN : TOTAL WORKING DAYS)
    // Calculate total working days excluding Sundays and holidays and leaves
  /*  public int calculateTotalWorkingDaysExcludingHolidays(YearMonth yearMonth, String empid) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        // Fetch holidays for the specified month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Fetch the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Iterate over leave records to collect the leave days
        for (LeaveApplication record : leaveRecords) {
            // Ensure the leave status is "Approved" and both fromDate and toDate are not null
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());

                // Add all the days in the leave range to the leaveDays set
                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        // Count working days excluding Sundays, holidays, and leave days
        int workingDays = 0;

        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                                    !holidaySet.contains(date) &&
                                    !leaveDays.contains(date);
            if (isWorkingDay) {
                workingDays++;
            }
        }

        return workingDays;
    }*/

  /*  public int calculateTotalWorkingDaysExcludingHolidays(YearMonth yearMonth, String empid) {
    	System.out.println("Calculating total working days excluding holidays for:");
        System.out.println("Year-Month: " + yearMonth);
        System.out.println("Employee ID: " + empid);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month: " + endOfMonth);

        
        // Fetch holidays for the specified month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        System.out.println("Fetched Holidays: " + holidays);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Fetch the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        System.out.println("Leave Records: " + leaveRecords);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Iterate over leave records to collect the leave days
        for (LeaveApplication record : leaveRecords) {
        	   System.out.println("Processing Leave Record: " + record);
            // Ensure the leave status is "Approved" and both fromDate and toDate are not null
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());
                System.out.println("Leave Period: " + fromDate + " to " + toDate);
                // Add all the days in the leave range to the leaveDays set
                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empid);
       
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        //System.out.println("Processing Leave Record: " + twoYearsAfterProbation);
       

        //LocalDate twoYearsAfterProbation = LocalDate.parse(effectiveDateStr);

        // Count working days excluding Sundays, holidays, and leave days
        int workingDays = 0;

        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                                    !holidaySet.contains(date) &&
                                    !leaveDays.contains(date) &&
                                    (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date));
            System.out.println("Date: " + date + " | Is Working Day: " + isWorkingDay);
            if (isWorkingDay) {
                workingDays++;
            }
        }
        System.out.println("Total Working Days: ddd" + workingDays);
        return workingDays;
    }*/
    
  /*imp  public int calculateTotalWorkingDaysExcludingHolidays(YearMonth yearMonth, String empid) {
        System.out.println("Calculating total working days excluding holidays for:");
        System.out.println("Year-Month: " + yearMonth);
        System.out.println("Employee ID: " + empid);
        
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empid);
        LocalDate doj = null;

        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj());  // Assuming DOJ is stored as 'YYYY-MM-DD'
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }

        System.out.println("Employee DOJ: " + doj);

        
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        
        System.out.println("Start of Month: " + startOfMonth);
        System.out.println("End of Month: " + endOfMonth);
        
        // Adjust startOfMonth only if DOJ is within the same year and month
        if (doj != null && doj.getYear() == yearMonth.getYear() && doj.getMonth() == yearMonth.getMonth()) {
            startOfMonth = doj; // Set start date as DOJ
            System.out.println("Adjusted Start Date based on DOJ: " + startOfMonth);
        }
        // Fetch employee email based on empid
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email: " + email);
        
        // Retrieve holidays for the selected month
        Set<LocalDate> holidays = new HashSet<>(holidayService.getHolidaysForMonth(yearMonth));
        System.out.println("Fetched Holidays: " + holidays);
        
        // Retrieve the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();
        
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFromDate() != null && record.getToDate() != null) {
                    
                    LocalDate fromDate = LocalDate.parse(record.getFromDate());
                    LocalDate toDate = LocalDate.parse(record.getToDate());
                    String leaveDuration = record.getLeaveDuration(); // Get leave duration
                    
                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    	
                        leaveDays.add(date);
                    }
                }
            }
        }
        System.out.println("Leave Days: " + leaveDays);
        
        // Get the probation completion date
        String effectiveDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (effectiveDateStr == null || effectiveDateStr.isEmpty()) {
            System.out.println("Alternative Saturday Effective Date is null or empty for email: " + email);
            // Proceed without probation-based logic
            int workingDays = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                    !holidays.contains(date) &&
                                    !leaveDays.contains(date))
                    .count();
            System.out.println("Calculated Working Days (Without Probation Logic): " + workingDays);
            return workingDays;
        }
        
        // Parse the probation completion date
        LocalDate twoYearsAfterProbation = LocalDate.parse(effectiveDateStr);
        System.out.println("Probation Completion Date: " + twoYearsAfterProbation);
     //   LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);
        
        // Calculate total working days excluding Sundays, holidays, and leave days
        int workingDays = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                !holidays.contains(date) &&
                                !leaveDays.contains(date) &&
                                (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                .count();
        
        System.out.println("Calculated Working Days: " + workingDays);
        return workingDays;
    }*/
    
    public double  calculateTotalWorkingDaysExcludingHolidays(YearMonth yearMonth, String empid) {
        System.out.println("Calculating total working days excluding holidays for:");
        System.out.println("Year-Month: " + yearMonth);
        System.out.println("Employee ID: " + empid);
        
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empid);
        LocalDate doj = null;

        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj());  // Assuming DOJ is stored as 'YYYY-MM-DD'
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }

        System.out.println("Employee DOJ: " + doj);
        
      //  LocalDate startOfMonth = yearMonth.atDay(1);
       // LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDate startOfMonth, endOfMonth;

     // If the selected month is January, handle December of the previous year
     if (yearMonth.getMonthValue() == 1) {
         startOfMonth = LocalDate.of(yearMonth.getYear() - 1, 12, 26);
         endOfMonth = LocalDate.of(yearMonth.getYear(), 1, 25);
     } else {
         startOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue() - 1, 26);
         endOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 25);
     }

     System.out.println("Adjusted Start Date: " + startOfMonth);
     System.out.println("Adjusted End Date: " + endOfMonth);
     
        if (doj != null && doj.getYear() == yearMonth.getYear() && doj.getMonth() == yearMonth.getMonth()) {
            startOfMonth = doj; // Set start date as DOJ
            System.out.println("Adjusted Start Date based on DOJ: " + startOfMonth);
        }
        
        String email = employeeRepository.findEmailByEmpId(empid);
        System.out.println("User email: " + email);
      //  Set<LocalDate> holidays = new HashSet<>(holidayService.getHolidaysInRange(startOfMonth, endOfMonth));
        Set<LocalDate> holidays = new HashSet<>(holidayService.getHolidaysBetween(startOfMonth, endOfMonth));

     //  Set<LocalDate> holidays = new HashSet<>(holidayService.getHolidaysForMonth(yearMonth));
        System.out.println("Fetched Holidays: " + holidays);
        
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empid, startOfMonth, endOfMonth);
        Set<LocalDate> fullDayLeaves = new HashSet<>();
        Set<LocalDate> halfDayLeaves = new HashSet<>();
        
        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFrom_date() != null && record.getTo_date() != null) {
                    
                    LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                    LocalDate toDate = LocalDate.parse(record.getTo_date());
                    String leaveDuration = record.getLeaveDuration();
                    
                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                        if ("Full Day".equalsIgnoreCase(leaveDuration)) {
                            fullDayLeaves.add(date);
                        } else if ("Half Day".equalsIgnoreCase(leaveDuration)) {
                            halfDayLeaves.add(date);
                        }
                    }
                }
            }
        }

        System.out.println("Full Day Leave Days: " + fullDayLeaves);
        System.out.println("Half Day Leave Days: " + halfDayLeaves);
        
        String effectiveDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (effectiveDateStr == null || effectiveDateStr.isEmpty()) {
            System.out.println("Alternative Saturday Effective Date is null or empty for email: " + email);
            double workingDays = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                    !holidays.contains(date) )
                                  /* && !fullDayLeaves.contains(date))
                    .mapToDouble(date -> halfDayLeaves.contains(date) ? 0.5 : 1.0)
                    .sum();*/
            .count();
            System.out.println("Calculated Working Days (Without Probation Logic): " + workingDays);
            return  workingDays;
        }
        
        LocalDate twoYearsAfterProbation = LocalDate.parse(effectiveDateStr);
        System.out.println("Probation Completion Date: " + twoYearsAfterProbation);
        System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);
        
        double workingDays = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                !holidays.contains(date) &&
                                //!fullDayLeaves.contains(date) &&
                                (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
              /*  .mapToDouble(date -> halfDayLeaves.contains(date) ? 0.5 : 1.0)
                .sum();*/
                .count();
        
        System.out.println("Calculated Working Days: sss" + workingDays);
        return workingDays;
    }

    
    
 // Calculate remaining working days in the current month excluding Sundays and holidays
 // Calculate remaining working days in the current month excluding Sundays, holidays, and leave days, and considering probation logic
  /*io  public int calculateRemainingWorkingDaysExcludingHolidays(YearMonth yearMonth, String empId) {
    	
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        System.out.println("Today: " + today);
        System.out.println("Start of the month: " + startOfMonth);
        System.out.println("End of the month: " + endOfMonth);

        if (today.isAfter(endOfMonth)) {
            System.out.println("Today is after the end of the month. No remaining days.");
            return 0;
        }

        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        System.out.println("Holidays for the month: " + holidaySet);

        // Retrieve the employee's leave records for the specified month
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Process the leave records to extract leave days
        for (LeaveApplication record : leaveRecords) {
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());

                System.out.println("Processing leave from " + fromDate + " to " + toDate);

                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        System.out.println("Leave days for the employee: " + leaveDays);

        String email = employeeRepository.findEmailByEmpId(empId);
        System.out.println("Employee email: " + email);

        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        //System.out.println("Probation completion date: " + probationCompletionDate);
        System.out.println("Two years after probation: " + twoYearsAfterProbation);

        int remainingWorkingDays = 0;

        for (LocalDate date = today; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                    !holidaySet.contains(date) &&
                    !leaveDays.contains(date) &&
                    (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date));

            if (isWorkingDay) {
                System.out.println("Working day: " + date);
                remainingWorkingDays++;
            } else {
                System.out.println("Non-working day: " + date);
            }
        }

        System.out.println("Total remaining working days: " + remainingWorkingDays);

        return remainingWorkingDays;
    }*/
    
    public int calculateRemainingWorkingDaysExcludingHolidays(YearMonth yearMonth, String empId) {
        // Get today's date and the start/end of the month
        LocalDate today = LocalDate.now();
       // LocalDate startOfMonth = yearMonth.atDay(1);
       // LocalDate endOfMonth = yearMonth.atEndOfMonth();
        
        // Define attendance cycle (26th of previous month to 25th of current month)
        LocalDate startOfMonth, endOfMonth;
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        if (month == 1) { 
        	startOfMonth = LocalDate.of(year - 1, 12, 26);
        	endOfMonth = LocalDate.of(year, 1, 25);
        } else {
        	startOfMonth = LocalDate.of(year, month - 1, 26);
        	endOfMonth = LocalDate.of(year, month, 25);
        }

        System.out.println("Start of attendance cycle: " + startOfMonth);
        System.out.println("End of attendance cycle: " + endOfMonth);
        
        System.out.println("Today: " + today);
        System.out.println("Start of the month: " + startOfMonth);
        System.out.println("End of the month: " + endOfMonth);

        // If today is past the attendance cycle's end, return 0 remaining working days
        if (today.isAfter(endOfMonth)) {
            System.out.println("Today is after the attendance cycle end. No remaining working days.");
            return 0;
        }

        // Fetch holidays for the month
    //    Set<LocalDate> holidays = new HashSet<>(holidayService.getHolidaysForMonth(yearMonth));
     // Get holidays for both the current and previous month
     // Get holidays for both the current and previous month
        Set<LocalDate> holidays = new HashSet<>();
        holidays.addAll(holidayService.getHolidaysForMonth(YearMonth.of(year, month - 1))); // Previous month holidays
        holidays.addAll(holidayService.getHolidaysForMonth(yearMonth)); // Current month holidays

        // Create a new filtered set instead of modifying holidays
        Set<LocalDate> filteredHolidays = holidays.stream()
                .filter(date -> !date.isBefore(startOfMonth) && !date.isAfter(endOfMonth))
                .collect(Collectors.toSet());

        System.out.println("Filtered Holidays for attendance cycle: " + filteredHolidays);

        // Use filteredHolidays in the rest of the method

        System.out.println("Holidays for the month: " + holidays);

        // Retrieve leave records and extract leave days
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfMonth, endOfMonth);
        Set<LocalDate> leaveDays = new HashSet<>();

        if (leaveRecords != null && !leaveRecords.isEmpty()) {
            for (LeaveApplication record : leaveRecords) {
                if ("Approved".equals(record.getApprovedstatus()) &&
                    record.getFrom_date() != null && record.getTo_date() != null) {
                    
                    LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                    LocalDate toDate = LocalDate.parse(record.getTo_date());

                    System.out.println("Processing leave from " + fromDate + " to " + toDate);

                    for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                        leaveDays.add(date);
                    }
                }
            }
        }
        System.out.println("Leave days for the employee: " + leaveDays);

        // Fetch employee email
        String email = employeeRepository.findEmailByEmpId(empId);
        System.out.println("Employee email: " + email);

        // Get the probation completion date
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (probationDateStr == null || probationDateStr.isEmpty()) {
            System.out.println("Error: Probation completion date is null or empty for email: " + email);

            // Proceed without probation-based logic
            int remainingWorkingDays = (int) today.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                    !holidays.contains(date) &&
                                    !leaveDays.contains(date))
                    .count();
            System.out.println("Remaining Working Days (Without Probation Logic): " + remainingWorkingDays);
            return remainingWorkingDays;
        }

        // Parse probation completion date and determine two-year mark
        LocalDate twoYearsAfterProbation = LocalDate.parse(probationDateStr);
      //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        //System.out.println("Probation Completion Date: " + probationCompletionDate);
        System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);

        // Calculate remaining working days
        int remainingWorkingDays = (int) today.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                !holidays.contains(date) &&
                                !leaveDays.contains(date) &&
                                (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                .count();

        System.out.println("Total remaining working days: " + remainingWorkingDays);
        return remainingWorkingDays;
    }


    /* overtime work */
    
 /*   public Map<String, String> calculateOvertime(Map<String, String> monthlyWorkingHours, String totalWorkingHoursInMonth) {
        Duration totalExpectedDuration = parseDuration(totalWorkingHoursInMonth);

        return monthlyWorkingHours.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    Duration workedDuration = parseDuration(entry.getValue()); // Convert String to Duration
                    Duration overtime = workedDuration.minus(totalExpectedDuration);
                    return formatDuration(overtime);
                }
            ));
    }*/
    
    public Map<String, String> calculateOvertime(Map<String, String> monthlyWorkingHours, String totalWorkingHoursInMonth) {
        Duration totalExpectedDuration = parseDuration(totalWorkingHoursInMonth);
        System.out.println("Total Expected Duration: " + formatDuration(totalExpectedDuration));

        return monthlyWorkingHours.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    Duration workedDuration = parseDuration(entry.getValue()); // Convert String to Duration
                    System.out.println("Worked Duration for " + entry.getKey() + ": " + formatDuration(workedDuration));

                    Duration overtime = workedDuration.minus(totalExpectedDuration);
                    System.out.println("Overtime for " + entry.getKey() + ": " + formatDuration(overtime));


                    // If overtime is negative, return "-"
                    if (overtime.isNegative()) {
                        return "-";
                    }

                    return formatDuration(overtime); // Otherwise, return the formatted overtime value
                }
            ));
    }
 /*   public Map<String, String> calculateOvertimeAndUndertime(Map<String, String> monthlyWorkingHours, String totalWorkingHoursInMonth) {
        Duration totalExpectedDuration = parseDuration(totalWorkingHoursInMonth);
        System.out.println("Total Expected Duration: " + formatDuration(totalExpectedDuration));

        return monthlyWorkingHours.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    Duration workedDuration = parseDuration(entry.getValue()); // Convert String to Duration
                    System.out.println("Worked Duration forrt " + entry.getKey() + ": " + formatDuration(workedDuration));

                    Duration overtime = workedDuration.minus(totalExpectedDuration);
                    Duration undertime = totalExpectedDuration.minus(workedDuration);
                    
                    System.out.println("Overtime forfr " + entry.getKey() + ": " + formatDuration(overtime));
                    System.out.println("Undertime forre " + entry.getKey() + ": " + formatDuration(undertime));

                    // If overtime is positive, return it
                    if (!overtime.isNegative()) {
                        return formatDuration(overtime);
                    }
                    
                    // If overtime is negative and undertime is positive, return undertime
                    if (!undertime.isNegative()) {
                        return formatDuration(undertime);
                    }

                    return "-"; // Default case
                }
            ));
    }*/
    
    
    public Map<String, String> calculateOvertimeAndUndertime(
    	    Map<String, String> monthlyWorkingHours,
    	    String totalWorkingHoursInMonth
    	) {
    	    Duration totalExpectedDuration = parseDuration(totalWorkingHoursInMonth);
    	    System.out.println("Total Expected Duration: " + formatDuration(totalExpectedDuration));

    	    return monthlyWorkingHours.entrySet().stream()
    	        .collect(Collectors.toMap(
    	            Map.Entry::getKey,
    	            entry -> {
    	                Duration workedDuration = parseDuration(entry.getValue());
    	                System.out.println("Worked Duration for " + entry.getKey() + ": " + formatDuration(workedDuration));

    	                Duration overtime = workedDuration.minus(totalExpectedDuration);
    	                Duration undertime = totalExpectedDuration.minus(workedDuration);

    	                System.out.println("Intermediate Calculation for " + entry.getKey() + ":");
    	                System.out.println("  Worked Duration: " + formatDuration(workedDuration));
    	                System.out.println("  Total Expected Duration: " + formatDuration(totalExpectedDuration));
    	                System.out.println("  Overtime: " + formatDuration(overtime));
    	                System.out.println("  Undertime: " + (undertime.isNegative() ? "-" : formatDuration(undertime)));

    	                // Only return undertime if it exists, otherwise return "-"
    	                if (!undertime.isNegative()) {
    	                    return  formatDuration(undertime);
    	                }

    	                return "-"; // Return "-" if no undertime
    	            }
    	        ));
    	}







    // Helper method to parse Duration from HH:mm:ss string

    private Duration parseDuration(String durationStr) {
        // Split the durationStr into hours, minutes, and seconds
        String[] parts = durationStr.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid duration format. Expected HH:mm:ss");
        }

        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(parts[2]);

        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }


 
   

    
    /*--------------------present/absent------------------------*/
    
    
 /*   public Map<String, Double> calculatePresentDays(List<AttendanceDTO> attendanceList) {
        Map<String, Double> presentDays = attendanceList.stream()
            .filter(attendance -> attendance.getInTime() != null && attendance.getOutTime() != null)
            .collect(Collectors.groupingBy(
                AttendanceExcel::getName,
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    // Debug print statements
                 //   System.out.println("Employee: " + attendance.getName());
                  //  System.out.println("In Time: " + inTime + ", Out Time: " + outTime);

                    double dayValue;
                    if (inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) {
                        dayValue = 0.5;
                    } else {
                        dayValue = 1.0;
                    }

                  //  System.out.println("Calculated day value: " + dayValue);

                    return dayValue;
                })
            ));

     //   System.out.println("Present days calculation (including half-days): " + presentDays);

        return presentDays;
    }

    public Map<String, Double> calculateAbsentDays(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays) {
        Map<String, Double> presentDays = calculatePresentDays(attendanceList);

        Set<String> employeeNames = attendanceList.stream()
            .map(AttendanceExcel::getName)
            .distinct()
            .collect(Collectors.toSet());

        Map<String, Double> absentDays = employeeNames.stream()
            .collect(Collectors.toMap(
                employeeName -> employeeName,
                employeeName -> {
                    double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                    double absentDayCount = totalWorkingDaysExcludingHolidays - presentDayCount;

                    // Debug print statements
                  //  System.out.println("Employee Name: " + employeeName);
                  //  System.out.println("Present days: " + presentDayCount);
                  //  System.out.println("Absent days (total - present): " + absentDayCount);

                    return absentDayCount;
                }
            ));

       // System.out.println("Absent days calculation (including half-days): " + absentDays);

        return absentDays;
    }*/

  /*  public Map<String, Double> calculatePresentDays(List<AttendanceDTO> attendanceList) {
    	 System.out.println("Calculating present days for attendance list:+++ " + attendanceList);
    	    
        return attendanceList.stream()
            .filter(attendance -> attendance.getInTime() != null && attendance.getOutTime() != null)
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Assuming eid represents employee name/ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    double dayValue;
                    if (inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) {
                        dayValue = 0.5;
                    } else {
                        dayValue = 1.0;
                    }
                    System.out.println("Employee " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);
                    return dayValue;
                })
            ));
    }*/
  /*  public Map<String, Double> calculatePresentDays(List<AttendanceDTO> attendanceList) {
        // Validate and calculate present days
        return attendanceList.stream()
            .filter(attendance -> isValidTime(attendance.getInTime()) && isValidTime(attendance.getOutTime()))
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Assuming eid represents employee name/ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    double dayValue;
                    if (inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) {
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }
                    
                    System.out.println("Employee@@ " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);
                    
                    return dayValue;
                })
            ));
    }*/
    
  /*  public Map<String, Double> calculatePresentDaysYearly(List<AttendanceDTO> attendanceList) {
        // Validate and calculate present days
        return attendanceList.stream()
            .filter(attendance -> isValidTime(attendance.getInTime()) && isValidTime(attendance.getOutTime()))
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Assuming eid represents employee name/ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime)) {
                        return 0.0; // Absent
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                    if (inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) {
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }

                    System.out.println("Employee@@ " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);

                    return dayValue;
                })
            ));
    }*/
    
   /* public Map<String, Double> calculatePresentDaysYearly(List<AttendanceDTO> attendanceList, String empId) {
        // Get the current date and the start of the year
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1); // Set startOfYear to the first day of the current year
        
        // Get holidays for the current year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);
        System.out.println("Holidays for the yearss: " + holidays);
        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        
        // Validate and calculate present days, excluding Sundays, holidays, and second/fourth Saturdays after probation
        return attendanceList.stream()
            .filter(attendance -> {
                LocalDate attendanceDate = LocalDate.parse(attendance.getDate()); // Assuming date is stored in AttendanceDTO as String
                // Check if the attendance date is within the year range, not a Sunday or holiday, and 
                // if it's after the probation period, not a second or fourth Saturday
                return isValidTime(attendance.getInTime()) && isValidTime(attendance.getOutTime()) &&
                       !attendanceDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) && 
                       !holidays.contains(attendanceDate) &&
                       (!attendanceDate.isBefore(startOfYear) && attendanceDate.isBefore(currentDate.plusDays(1))) &&
                       (!attendanceDate.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(attendanceDate)); // Exclude second/fourth Saturday after probation
            })
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Group by employee ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime)) {
                        return 0.0; // Absent
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                    if ((inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) &&
                            Duration.between(inTime, outTime).toMinutes() >= 150 &&
                            Duration.between(inTime, outTime).toMinutes() < 360) { // Between 2.5 and 6 hours
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }

                    System.out.println("Employee@@ee " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);

                    return dayValue;
                })
            ));
        
        
    }*/
   
  /*io  public Map<String, Double> calculatePresentDaysYearly(List<AttendanceDTO> attendanceList, String empId) {
        // Get the current date and the start of the year
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1); // Set startOfYear to the first day of the current year

        // Get holidays for the current year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);
        System.out.println("Holidays for the year: " + holidays);

        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
        System.out.println("Probation Completion Date + 2 years: " + twoYearsAfterProbation);

        // Validate and calculate present days, excluding Sundays, holidays, and second/fourth Saturdays after probation
        return attendanceList.stream()
            .filter(attendance -> {
                LocalDate attendanceDate = LocalDate.parse(attendance.getDate()); // Assuming date is stored in AttendanceDTO as String
                System.out.println("Processing Attendance Entry: " + attendance);

                boolean isValidInTime = isValidTime(attendance.getInTime());
                boolean isValidOutTime = isValidTime(attendance.getOutTime());
                boolean isSunday = attendanceDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
                boolean isHoliday = holidays.contains(attendanceDate);
                boolean isWithinYear = (!attendanceDate.isBefore(startOfYear) && attendanceDate.isBefore(currentDate.plusDays(1)));
                boolean isSecondOrFourthSat = isSecondOrFourthSaturday(attendanceDate);
                boolean isAfterProbation = attendanceDate.isAfter(twoYearsAfterProbation);

                System.out.println("Date: " + attendanceDate +
                    " | ValidInTime: " + isValidInTime +
                    " | ValidOutTime: " + isValidOutTime +
                    " | IsSunday: " + isSunday +
                    " | IsHoliday: " + isHoliday +
                    " | IsWithinYear: " + isWithinYear +
                    " | IsSecondOrFourthSat: " + isSecondOrFourthSat +
                    " | IsAfterProbation: " + isAfterProbation);

                return isValidInTime && isValidOutTime &&
                       !isSunday &&
                       !isHoliday &&
                       isWithinYear &&
                       (!isAfterProbation || !isSecondOrFourthSat); // Exclude second/fourth Saturday after probation
            })
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Group by employee ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());
                    long durationMinutes = Duration.between(inTime, outTime).toMinutes();

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime)|| durationMinutes < 150) {
                        System.out.println("Employee " + attendance.getEid() + " was absent on " + attendance.getDate());
                        return 0.0;
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                    if ((inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) &&
                            Duration.between(inTime, outTime).toMinutes() >= 150 &&
                            Duration.between(inTime, outTime).toMinutes() < 360) { // Between 2.5 and 6 hours
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }

                    System.out.println("Employee " + attendance.getEid() +
                            " | Date: " + attendance.getDate() +
                            " | InTime: " + inTime +
                            " | OutTime: " + outTime +
                            " | DayValue: " + dayValue);

                    return dayValue;
                })
            ));
    }*/
    
    public Map<String, Double> calculatePresentDaysYearly(List<AttendanceDTO> attendanceList, String empId) {
        // Get the current date and the start of the year
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1); // Set startOfYear to the first day of the current year

        // Get holidays for the current year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);
        System.out.println("Holidays for the year: " + holidays);

        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);

        // Use Optional to store the parsed LocalDate safely
        Optional<LocalDate> twoYearsAfterProbation = Optional.ofNullable(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email))
            .filter(date -> !date.isEmpty()) // Filter out empty strings
            .map(LocalDate::parse); // Convert to LocalDate if present

        twoYearsAfterProbation.ifPresent(date -> 
            System.out.println("Probation Completion Date + 2 years: " + date)
        );

        // Validate and calculate present days, excluding Sundays, holidays, and second/fourth Saturdays after probation
        return attendanceList.stream()
            .filter(attendance -> {
                LocalDate attendanceDate = LocalDate.parse(attendance.getDate()); // Assuming date is stored in AttendanceDTO as String
                System.out.println("Processing Attendance Entry: " + attendance);

                boolean isValidInTime = isValidTime(attendance.getInTime());
                boolean isValidOutTime = isValidTime(attendance.getOutTime());
                boolean isSunday = attendanceDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
                boolean isHoliday = holidays.contains(attendanceDate);
                boolean isWithinYear = (!attendanceDate.isBefore(startOfYear) && attendanceDate.isBefore(currentDate.plusDays(1)));
                boolean isSecondOrFourthSat = isSecondOrFourthSaturday(attendanceDate);
                boolean isAfterProbation = twoYearsAfterProbation.map(date -> attendanceDate.isAfter(date)).orElse(false);

                System.out.println("Date: " + attendanceDate +
                    " | ValidInTime: " + isValidInTime +
                    " | ValidOutTime: " + isValidOutTime +
                    " | IsSunday: " + isSunday +
                    " | IsHoliday: " + isHoliday +
                    " | IsWithinYear: " + isWithinYear +
                    " | IsSecondOrFourthSat: " + isSecondOrFourthSat +
                    " | IsAfterProbation: " + isAfterProbation);

                return isValidInTime && isValidOutTime &&
                       !isSunday &&
                       !isHoliday &&
                       isWithinYear &&
                       (!isAfterProbation || !isSecondOrFourthSat); 
                       // Exclude second/fourth Saturday **only if probation date exists**
            })
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Group by employee ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());
                    long durationMinutes = Duration.between(inTime, outTime).toMinutes();

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime) || durationMinutes < 150) {
                        System.out.println("Employee " + attendance.getEid() + " was absent on " + attendance.getDate());
                        return 0.0;
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                    if ((inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) &&
                            durationMinutes >= 150 &&
                            durationMinutes < 360) { // Between 2.5 and 6 hours
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }

                    System.out.println("Employee " + attendance.getEid() +
                            " | Date: " + attendance.getDate() +
                            " | InTime: " + inTime +
                            " | OutTime: " + outTime +
                            " | DayValue: " + dayValue);

                    return dayValue;
                })
            ));
    }



   /* public Map<String, Double> calculatePresentDaysYearly(List<AttendanceDTO> attendanceList, String empId) {
        // Get the current date and the start of the year
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1); // Set startOfYear to the first day of the current year

        // Get holidays for the current year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);
        System.out.println("Holidays for the year: " + holidays);

        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate probationCompletionDate = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
        LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        System.out.println("Probation completion date for " + email + ": " + probationCompletionDate);
        System.out.println("Two years after probation: " + twoYearsAfterProbation);

        // Validate and calculate present days, excluding Sundays, holidays, and second/fourth Saturdays after probation
        return attendanceList.stream()
            .filter(attendance -> {
                LocalDate attendanceDate = LocalDate.parse(attendance.getDate()); // Assuming date is stored in AttendanceDTO as String
                boolean isValid = isValidTime(attendance.getInTime()) &&
                                  isValidTime(attendance.getOutTime()) &&
                                  !attendanceDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                                  !holidays.contains(attendanceDate) &&
                                  (attendanceDate.isAfter(startOfYear) && attendanceDate.isBefore(currentDate.plusDays(1))) &&
                                  (!attendanceDate.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(attendanceDate));

                // Log details about the filtering process
                System.out.println("Attendance Date: " + attendanceDate + 
                        ", InTime: " + attendance.getInTime() + 
                        ", OutTime: " + attendance.getOutTime() + 
                        ", Is Holiday: " + holidays.contains(attendanceDate) + 
                        ", Is Sunday: " + attendanceDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) + 
                        ", Is Within Year Range: " + (attendanceDate.isAfter(startOfYear) && attendanceDate.isBefore(currentDate.plusDays(1))) + 
                        ", Is Within Probation Period: " + (!attendanceDate.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(attendanceDate)) + 
                        ", IsValid: " + isValid);

                return isValid;
            })
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Group by employee ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime)) {
                        return 0.0; // Absent
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                    if (inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) {
                        dayValue = 0.5; // Half day
                    } else {
                        dayValue = 1.0; // Full day
                    }

                    System.out.println("Employee " + attendance.getEid() + ": InTime = " + inTime + 
                                       ", OutTime = " + outTime + ", DayValue = " + dayValue);

                    return dayValue;
                })
            ));
    }*/

    //
   /*io public Map<String, Double> calculatePresentDays(List<AttendanceDTO> attendanceList, YearMonth yearMonth, String empId) {
        // Fetch holidays for the specified month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Get probation completion details
        String email = employeeRepository.findEmailByEmpId(empId);
       // LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
      //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);

       

        LocalDate twoYearsAfterProbation = LocalDate.parse(probationDateStr);
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        System.out.println("Probation Completion Date: " + twoYearsAfterProbation);
        System.out.println("Two Years After Probation: " + twoYearsAfterProbation);

        // Validate and calculate present days excluding Sundays, holidays, and specific Saturdays
        return attendanceList.stream()
            .filter(attendance -> {
                // Parse the date of attendance
                LocalDate date = LocalDate.parse(attendance.getDate());

                // Exclude Sundays, holidays, and specific Saturdays
                return date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                       !holidaySet.contains(date) &&
                       (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date));
            })
            .filter(attendance -> isValidTime(attendance.getInTime()) && isValidTime(attendance.getOutTime()))
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid, // Assuming eid represents employee name/ID
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    // If inTime and outTime are the same, count as absent (0)
                    if (inTime.equals(outTime)) {
                        return 0.0; // Absent
                    }

                    double dayValue;
                    // Check if it's a half-day or full-day based on inTime and outTime
                  
                    
                    if ((inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) &&
                    	    Duration.between(inTime, outTime).toMinutes() >= 150 &&
                    	    Duration.between(inTime, outTime).toMinutes() < 360) { // Between 2.5 and 6 hours
                    	    dayValue = 0.5; // Half day
                    	} else if (Duration.between(inTime, outTime).toMinutes() >= 360) { // 6 hours = 360 minutes
                    	    dayValue = 1.0; // Full day
                    	} else {
                    	    dayValue = 0.0; // Absent
                    	}


                    System.out.println("Employee@@ " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);

                    return dayValue;
                })
            ));
    }*/

    public Map<String, Double> calculatePresentDays(List<AttendanceDTO> attendanceList, YearMonth yearMonth, String empId) {
        // Fetch holidays for the specified month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Get probation completion details
        String email = employeeRepository.findEmailByEmpId(empId);
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        // Determine the custom month range (26th of previous month to 25th of current month)
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        LocalDate startOfMonth, endOfMonth;

        if (month == 1) { // If January, previous month is December of the previous year
            startOfMonth = LocalDate.of(year - 1, 12, 26);
            endOfMonth = LocalDate.of(year, 1, 25);
        } else {
            startOfMonth = LocalDate.of(year, month - 1, 26);
            endOfMonth = LocalDate.of(year, month, 25);
        }

        System.out.println("Custom Month Startpresent: " + startOfMonth);
        System.out.println("Custom Month End: " + endOfMonth);

        // Convert to LocalDate only if non-null
        final Optional<LocalDate> optionalProbationDate = 
            (probationDateStr != null) ? Optional.of(LocalDate.parse(probationDateStr)) : Optional.empty();

        optionalProbationDate.ifPresent(probationDate -> {
            System.out.println("Probation Completion Date: " + probationDate);
            System.out.println("Two Years After Probation: " + probationDate);
        });

        // Validate and calculate present days excluding Sundays, holidays, and specific Saturdays
        return attendanceList.stream()
            .filter(attendance -> {
                LocalDate date = LocalDate.parse(attendance.getDate());
                // Ensure the date is within the custom month range
                if (date.isBefore(startOfMonth) || date.isAfter(endOfMonth)) {
                    return false;
                }
                // Exclude Sundays and holidays
                boolean isExcluded = date.getDayOfWeek() == DayOfWeek.SUNDAY || holidaySet.contains(date);

                // If probation date is available, apply the second/fourth Saturday condition
                if (optionalProbationDate.isPresent()) {
                    LocalDate probationDate = optionalProbationDate.get();
                    isExcluded = isExcluded || (date.isAfter(probationDate) && isSecondOrFourthSaturday(date));
                }

                return !isExcluded;
            })
            .filter(attendance -> isValidTime(attendance.getInTime()) && isValidTime(attendance.getOutTime()))
            .collect(Collectors.groupingBy(
                AttendanceDTO::getEid,
                Collectors.summingDouble(attendance -> {
                    LocalTime inTime = LocalTime.parse(attendance.getInTime());
                    LocalTime outTime = LocalTime.parse(attendance.getOutTime());

                    if (inTime.equals(outTime)) {
                        return 0.0; // Absent
                    }

                    double dayValue;
                    long durationMinutes = Duration.between(inTime, outTime).toMinutes();

                    if ((inTime.isAfter(LocalTime.of(12, 0)) || outTime.isBefore(LocalTime.of(15, 0))) &&
                        durationMinutes >= 150 && durationMinutes < 360) {
                        dayValue = 0.5; // Half day
                    } else if (durationMinutes >= 360) {
                        dayValue = 1.0; // Full day
                    } else {
                        dayValue = 0.0; // Absent
                    }

                    System.out.println("Employee@@ " + attendance.getEid() + ": InTime = " + inTime + ", OutTime = " + outTime + ", DayValue = " + dayValue);

                    return dayValue;
                })
            ));
    }


    // Helper method to validate if a time string is valid
    private boolean isValidTime(String timeStr) {
        if (timeStr == null || timeStr.equals("-")) {
            return false;
        }
        try {
            LocalTime.parse(timeStr); // Check if time can be parsed
            return true;
        } catch (DateTimeParseException e) {
            return false; // Invalid time format
        }
    }

    
  /*  public Map<String, Double> calculateAbsentDaysYearly(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays) {
        Map<String, Double> presentDays = calculatePresentDays(attendanceList);

        Set<String> employeeNames = attendanceList.stream()
            .map(AttendanceDTO::getEid) // Assuming eid represents employee name/ID
            .distinct()
            .collect(Collectors.toSet());

        return employeeNames.stream()
            .collect(Collectors.toMap(
                employeeName -> employeeName,
                employeeName -> {
                    double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                    double absentDayCount = totalWorkingDaysExcludingHolidays - presentDayCount;
                    return Math.max(absentDayCount, 0); // Ensure absent days are not negative
                }
            ));
    }*/
   /* public Map<String, Double> calculateAbsentDaysYearly(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays,String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDaysYearly(attendanceList,empId);

        // Get the current date (today) and yesterday's date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Year currentYear = Year.of(today.getYear());

        // Get holidays for the current year
        List<LocalDate> holidays = holidayService.getHolidaysForYear(currentYear); // Assuming this method exists for yearly holidays
        Set<LocalDate> holidaySet = new HashSet<>(holidays);


        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate probationCompletionDate = LocalDate.parse(employeeDetailsService.getProbationCompletionDate(email));
        LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        // Iterate through the year from January 1st to yesterday's date
        LocalDate startOfYear = LocalDate.of(currentYear.getValue(), 1, 1);
        int workingDaysTillYesterday = (int) startOfYear.datesUntil(yesterday.plusDays(1)) // Include yesterday
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidaySet.contains(date))
                .count();

        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                    employeeName -> employeeName,
                    employeeName -> {
                        double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                        // Absent days are calculated based on working days till yesterday
                        double absentDayCount = workingDaysTillYesterday - presentDayCount;
                        return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                    }
                ));
    }*/
    
   
    
 /*io   public Map<String, Double> calculateAbsentDaysYearly(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDaysYearly(attendanceList, empId);
        System.out.println("Present days calculated: " + presentDays);
        // Get the current date (today) and yesterday's date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Year currentYear = Year.of(today.getYear());

        // Get holidays for the current year
        List<LocalDate> holidays = holidayService.getHolidaysForYear(currentYear); // Assuming this method exists for yearly holidays
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Get the probation completion date
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
      //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        // Set the start of the year dynamically based on the current year
        LocalDate startOfYear = currentYear.getValue() == 2024
                ? LocalDate.of(2024, 8, 23) // Use August 23, 2024, as the start date for the year 2024
                : LocalDate.of(currentYear.getValue(), 1, 1); // Use January 1st for other years

        // Calculate working days till yesterday
        int workingDaysTillYesterday = (int) startOfYear.datesUntil(yesterday.plusDays(1)) // Include yesterday
                .filter(date -> {
                    boolean isSunday = date.getDayOfWeek() == DayOfWeek.SUNDAY;
                    boolean isHoliday = holidaySet.contains(date);
                    boolean isSecondOrFourthSaturday = isSecondOrFourthSaturday(date);
                    boolean excludeSecondFourthSaturday = date.isAfter(twoYearsAfterProbation) && isSecondOrFourthSaturday;

                    // Exclude Sundays, holidays, and second/fourth Saturdays after probation
                    return !isSunday && !isHoliday && !(excludeSecondFourthSaturday);
                })
                .count();
        System.out.println("Working days till yesterday: " + workingDaysTillYesterday);
        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                    employeeName -> employeeName,
                    employeeName -> {
                        double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                        // Absent days are calculated based on working days till yesterday
                        double absentDayCount = workingDaysTillYesterday - presentDayCount;
                        System.out.println("Working days till yesterday: " + absentDayCount);
                        return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                    }
                ));
    }*/
    
    public Map<String, Double> calculateAbsentDaysYearly(List<AttendanceDTO> attendanceList, double totalWorkingDaysExcludingHolidays, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = Optional.ofNullable(calculatePresentDaysYearly(attendanceList, empId))
                                                  .orElse(new HashMap<>()); // Handle null

        System.out.println("Present days calculated: " + presentDays);

        // Get the current date and yesterday's date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
       // Year currentYear = Year.of(today.getYear());

     // Get the current year based on today’s date
        int currentYearValue = today.getYear();
     // Define start and end of the financial year
        LocalDate startOfYear;
        LocalDate endOfYear = LocalDate.of(currentYearValue + 1, 3, 25);

        // If the year is 2024, start from August 23, 2024; otherwise, follow financial year rule
        if (currentYearValue == 2024) {
            startOfYear = LocalDate.of(2024, 8, 23);
        } else {
            startOfYear = LocalDate.of(currentYearValue, 3, 26);
        }
        // Define start and end of the financial year
       // Get the current date and yesterday's date
       
        // Get holidays for the current year
     /*   List<LocalDate> holidays = Optional.ofNullable(holidayService.getHolidaysForYear(currentYearValue))
                                           .orElse(Collections.emptyList()); // Handle null
        Set<LocalDate> holidaySet = new HashSet<>(holidays); */
     // Get holidays for the current year
        List<LocalDate> holidays = Optional.ofNullable(holidayService.getHolidaysForYear(Year.of(currentYearValue)))
                                           .orElse(Collections.emptyList());
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Get the probation completion date (Handle potential null or empty value)
        String email = Optional.ofNullable(employeeRepository.findEmailByEmpId(empId)).orElse("");
        
        Optional<LocalDate> twoYearsAfterProbation = Optional.ofNullable(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email))
                                                             .filter(date -> !date.isEmpty()) // Exclude empty strings
                                                             .map(LocalDate::parse);

        twoYearsAfterProbation.ifPresent(date -> 
            System.out.println("Probation Completion Date + 2 years: " + date)
        );
     // Retrieve employee details and DOJ
        LocalDate doj = null;
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj());
                System.out.println("Employee DOJ: " + doj);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }
        // Set the start of the year dynamically based on the current year
      /*  LocalDate startOfYear = (currentYearValue.getValue() == 2024)
                ? LocalDate.of(2024, 8, 23) // Use August 23, 2024, as the start date for the year 2024
                : LocalDate.of(currentYearValue.getValue(), 1, 1);*/ // Use January 1st for other years
     // If DOJ is in the current year, use DOJ as startOfYear
     // If DOJ is within the financial year, adjust startOfYear
        if (doj != null && (doj.isAfter(startOfYear) || doj.isEqual(startOfYear)) && doj.isBefore(endOfYear)) {
            startOfYear = doj;
        }
        // Calculate working days till yesterday
        int workingDaysTillYesterday = (int) startOfYear.datesUntil(yesterday.plusDays(1)) // Include yesterday
                .filter(date -> {
                    boolean isSunday = date.getDayOfWeek() == DayOfWeek.SUNDAY;
                    boolean isHoliday = holidaySet.contains(date);
                    boolean isSecondOrFourthSaturday = isSecondOrFourthSaturday(date);
                    boolean excludeSecondFourthSaturday = twoYearsAfterProbation.map(d -> date.isAfter(d) && isSecondOrFourthSaturday).orElse(false);

                    // Exclude Sundays, holidays, and second/fourth Saturdays after probation
                    return !isSunday && !isHoliday && !excludeSecondFourthSaturday;
                })
                .count();
        
        System.out.println("Working days till yesterday: " + workingDaysTillYesterday);

        // Extract unique employee names/IDs
        Set<String> employeeNames = Optional.ofNullable(attendanceList)
                                            .orElse(Collections.emptyList()) // Handle null
                                            .stream()
                                            .map(AttendanceDTO::getEid)
                                            .filter(Objects::nonNull) // Filter out null values
                                            .distinct()
                                            .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                    employeeName -> employeeName,
                    employeeName -> {
                        double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                        double absentDayCount = workingDaysTillYesterday - presentDayCount;

                        System.out.println("Absent days for " + employeeName + ": " + absentDayCount);
                        
                        return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                    }
                ));
    }


    
    
  /*  public Map<String, Double> calculateAbsentDays(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays, YearMonth yearMonth, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDays(attendanceList,yearMonth,empId);

        // Get the current date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        YearMonth currentMonth = YearMonth.from(today);

        // Get holidays for the current month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);

        // Calculate working days for previous months or till yesterday for the current month
        int workingDaysTillDate;

        if (yearMonth.equals(currentMonth)) {
            // Logic for the current month (consider only days up to yesterday)
            LocalDate startOfMonth = yearMonth.atDay(1);
            workingDaysTillDate = (int) startOfMonth.datesUntil(yesterday.plusDays(1)) // Include yesterday
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidaySet.contains(date))
                    .count();
        } else {
            // For previous months, consider all working days of the month
            LocalDate startOfMonth = yearMonth.atDay(1);
            LocalDate endOfMonth = yearMonth.atEndOfMonth();
            workingDaysTillDate = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidaySet.contains(date))
                    .count();
        }

        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                    employeeName -> employeeName,
                    employeeName -> {
                        double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                        // Absent days are calculated based on either total working days or working days till yesterday (for the current month)
                        double absentDayCount = workingDaysTillDate - presentDayCount;
                        return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                    }
                ));
    }*/

 /*io  public Map<String, Double> calculateAbsentDays(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays, YearMonth yearMonth, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDays(attendanceList, yearMonth, empId);
        System.out.println("Present Days: " + presentDays);
        System.out.println("Total Working Days (Excluding Holidays): " + totalWorkingDaysExcludingHolidays);

        // Get the probation completion details
        String email = employeeRepository.findEmailByEmpId(empId);
       // LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
     //   LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
       // System.out.println("Probation Completion Date: " + probationCompletionDate);
      //  System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);

        if (probationDateStr == null || probationDateStr.isEmpty()) {
            System.out.println("Error: Probation completion date missing for email: " + email);
            return Collections.emptyMap(); // Return empty map if missing probation date
        }

        LocalDate twoYearsAfterProbation = LocalDate.parse(probationDateStr);
        //LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);

        // Get the current date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        YearMonth currentMonth = YearMonth.from(today);

        // Get holidays for the current month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);
        System.out.println("Holidays for " + yearMonth + ": " + holidaySet);

        // Calculate working days for previous months or till yesterday for the current month
        int workingDaysTillDate;

        if (yearMonth.equals(currentMonth)) {
            // Logic for the current month (consider only days up to yesterday)
            LocalDate startOfMonth = yearMonth.atDay(1);
            workingDaysTillDate = (int) startOfMonth.datesUntil(yesterday.plusDays(1)) // Include yesterday
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Current Month: " + yearMonth);
            System.out.println("Working Days Till Yesterday (" + yesterday + "): " + workingDaysTillDate);
        } else {
            // For previous months, consider all working days of the month
            LocalDate startOfMonth = yearMonth.atDay(1);
            LocalDate endOfMonth = yearMonth.atEndOfMonth();
            workingDaysTillDate = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Previous Month: " + yearMonth);
            System.out.println("Working Days for Full Month: " + workingDaysTillDate);
        }

        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                        employeeName -> employeeName,
                        employeeName -> {
                            double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                            System.out.println("abbpres"+presentDayCount);
                            // Absent days are calculated based on either total working days or working days till yesterday (for the current month)
                            double absentDayCount = workingDaysTillDate - presentDayCount;
                            System.out.println("abb"+absentDayCount);
                            return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                        }
                ));
    }*/
    
    
    
    public Map<String, Double> calculateAbsentDays(List<AttendanceDTO> attendanceList, double  totalWorkingDaysExcludingHolidays, YearMonth yearMonth, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDays(attendanceList, yearMonth, empId);
        System.out.println("Present Days: " + presentDays);
        System.out.println("Total Working Days (Excluding Holidays): " + totalWorkingDaysExcludingHolidays);

     // Get employee DOJ (Date of Joining)
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
        LocalDate doj = null;

        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj());
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }

        System.out.println("Employee DOJ: " + doj);

        // Get the probation completion details
        String email = employeeRepository.findEmailByEmpId(empId);
        String probationDateStr = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);

        // Use Optional to avoid null issues
        final Optional<LocalDate> optionalProbationDate = 
            (probationDateStr != null && !probationDateStr.isEmpty()) ? Optional.of(LocalDate.parse(probationDateStr)) : Optional.empty();

        optionalProbationDate.ifPresent(probationDate -> 
            System.out.println("Two Years After Probation Date: " + probationDate));

        // Get the current date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        YearMonth currentMonth = YearMonth.from(today);

     

        //System.out.println("Effective Current Month: " + effectiveCurrentMonth);
        // Get holidays for the current month
      //  List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
     //   Set<LocalDate> holidaySet = new HashSet<>(holidays);
     // Fetch holidays for both the previous month and current month
     // Fetch holidays for the previous and current months
        List<LocalDate> previousMonthHolidays = holidayService.getHolidaysForMonth(yearMonth.minusMonths(1));
        List<LocalDate> currentMonthHolidays = holidayService.getHolidaysForMonth(yearMonth);

        // Calculate working days for previous months or till yesterday for the current month
        int workingDaysTillDate;
       // LocalDate startOfMonth = yearMonth.atDay(1);
       // LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDate startOfMonth, endOfMonth;

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        if (month == 1) { 
            startOfMonth = LocalDate.of(year - 1, 12, 26);
            endOfMonth = LocalDate.of(year, 1, 25);
        } else {
            startOfMonth = LocalDate.of(year, month - 1, 26);
            endOfMonth = LocalDate.of(year, month, 25);
        }
        
        
        YearMonth effectiveCurrentMonth;
        if (today.isAfter(endOfMonth)) {
            // Move to the next month
            effectiveCurrentMonth = (month == 12) ? 
                YearMonth.of(year + 1, 1) : YearMonth.of(year, month + 1);
        } else {
            effectiveCurrentMonth = YearMonth.of(year, month);
        }

     // Combine and filter holidays to match the attendance cycle
        Set<LocalDate> holidaySet = new HashSet<>();
        for (LocalDate holiday : previousMonthHolidays) {
            if (!holiday.isBefore(startOfMonth)) {
                holidaySet.add(holiday);
            }
        }
        for (LocalDate holiday : currentMonthHolidays) {
            if (!holiday.isAfter(endOfMonth)) {
                holidaySet.add(holiday);
            }
        }
        System.out.println("Filtered Holidays for Attendance Cycle (" + startOfMonth + " to " + endOfMonth + "): " + holidaySet);

        System.out.println("Effective Current Month: " + effectiveCurrentMonth);

        System.out.println("Custom Month Start: " + startOfMonth);
        System.out.println("Custom Month End: " + endOfMonth);

        // Adjust `startOfMonth` if the employee joined in the same month and year
        if (doj != null && doj.getYear() == yearMonth.getYear() && doj.getMonth() == yearMonth.getMonth()) {
            startOfMonth = doj; // Employee joined mid-month, start from DOJ
            System.out.println("Adjusted Start Date to DOJ: " + startOfMonth);
        }
        
        if (yearMonth.equals(effectiveCurrentMonth)) {
            // Logic for the current month (consider only days up to yesterday)
          //  LocalDate startOfMonth = yearMonth.atDay(1);
            workingDaysTillDate = (int) startOfMonth.datesUntil(yesterday.isAfter(endOfMonth) ? endOfMonth.plusDays(1) : yesterday.plusDays(1) ) // Include yesterday
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!optionalProbationDate.isPresent() || 
                             !date.isAfter(optionalProbationDate.get()) || 
                             !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Current Month: " + yearMonth);
            System.out.println("Working Days Till Yesterday (" + yesterday + "): " + workingDaysTillDate);
        } else {
            // For previous months, consider all working days of the month
           // LocalDate startOfMonth = yearMonth.atDay(1);
           // LocalDate endOfMonth = yearMonth.atEndOfMonth();
            workingDaysTillDate = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!optionalProbationDate.isPresent() || 
                             !date.isAfter(optionalProbationDate.get()) || 
                             !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Previous Month: " + yearMonth);
            System.out.println("Working Days for Full Month:asa " + workingDaysTillDate);
        }

        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());

        // Calculate absent days for each employee
        return employeeNames.stream()
                .collect(Collectors.toMap(
                        employeeName -> employeeName,
                        employeeName -> {
                            double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                            System.out.println("Present Days for " + employeeName + ": " + presentDayCount);

                            // Absent days are calculated based on either total working days or working days till yesterday (for the current month)
                            double absentDayCount = workingDaysTillDate - presentDayCount;
                            System.out.println("Absent Days for " + employeeName + ": " + absentDayCount);

                            return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                        }
                ));
    }


 /*   public Map<String, Double> calculateAbsentDays(List<AttendanceDTO> attendanceList, int totalWorkingDaysExcludingHolidays, YearMonth yearMonth, String empId) {
        // Calculate present days for each employee
        Map<String, Double> presentDays = calculatePresentDays(attendanceList, yearMonth, empId);
        System.out.println("Present Days: " + presentDays);

        // Get the probation completion details
        String email = employeeRepository.findEmailByEmpId(empId);
        System.out.println("Employee Email: " + email);

        LocalDate probationCompletionDate = LocalDate.parse(employeeDetailsService.getProbationCompletionDate(email));
        System.out.println("Probation Completion Date: " + probationCompletionDate);

        LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);
        System.out.println("Two Years After Probation Date: " + twoYearsAfterProbation);

        // Get the current date
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        YearMonth currentMonth = YearMonth.from(today);
        System.out.println("Today: " + today);
        System.out.println("Yesterday: " + yesterday);
        System.out.println("Current Month: " + currentMonth);

        // Get holidays for the current month
        List<LocalDate> holidays = holidayService.getHolidaysForMonth(yearMonth);
        Set<LocalDate> holidaySet = new HashSet<>(holidays);
        System.out.println("Holidays for the month: " + holidays);

        // Calculate working days for previous months or till yesterday for the current month
        int workingDaysTillDate;

        if (yearMonth.equals(currentMonth)) {
            // Logic for the current month (consider only days up to yesterday)
            LocalDate startOfMonth = yearMonth.atDay(1);
            workingDaysTillDate = (int) startOfMonth.datesUntil(yesterday.plusDays(1)) // Include yesterday
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Working days till yesterday (current month): " + workingDaysTillDate);
        } else {
            // For previous months, consider all working days of the month
            LocalDate startOfMonth = yearMonth.atDay(1);
            LocalDate endOfMonth = yearMonth.atEndOfMonth();
            workingDaysTillDate = (int) startOfMonth.datesUntil(endOfMonth.plusDays(1))
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                            !holidaySet.contains(date) &&
                            (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday(date)))
                    .count();
            System.out.println("Working days in the previous month: " + workingDaysTillDate);
        }

        // Extract employee names/IDs (assuming eid represents employee ID or name)
        Set<String> employeeNames = attendanceList.stream()
                .map(AttendanceDTO::getEid)
                .distinct()
                .collect(Collectors.toSet());
        System.out.println("Employee Names/IDs: " + employeeNames);

        // Calculate absent days for each employee
        Map<String, Double> absentDays = employeeNames.stream()
                .collect(Collectors.toMap(
                        employeeName -> employeeName,
                        employeeName -> {
                            double presentDayCount = presentDays.getOrDefault(employeeName, 0.0);
                            System.out.println("Employee: " + employeeName + " - Present Days: " + presentDayCount);

                            // Absent days are calculated based on either total working days or working days till yesterday (for the current month)
                            double absentDayCount = workingDaysTillDate - presentDayCount;
                            System.out.println("Employee: " + employeeName + " - Absent Days: " + absentDayCount);

                            return Math.max(absentDayCount, 0.0); // Ensure absent days are not negative
                        }
                ));

        System.out.println("Absent Days for Employees: " + absentDays);

        return absentDays;
    }*/



    
/* DATA FOR THE YEAR */
    
    
    // Method to calculate total working hours up to the present month
  /*  public String calculateTotalWorkingHoursInYear(LocalDate startOfYear) {
        LocalDate today = LocalDate.now();
      //  LocalDate endOfCurrentMonth = today.withDayOfMonth(today.lengthOfMonth());

        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, today);
        int totalWorkingDays = 0;

        System.out.println("Calculating working days between " + startOfYear + " and " + today);
        
        for (LocalDate date = startOfYear; !date.isAfter(today); date = date.plusDays(1)) {
            if (isWorkingDay(date, holidays)) {
                totalWorkingDays++;
                System.out.println("Working day: " + date);
            } else {
                System.out.println("Non-working day: " + date);
            }
        }
        System.out.println("Working daysyyy " +totalWorkingDays);
        int totalWorkingHours = totalWorkingDays * WORKING_HOURS_PER_DAY;
        System.out.println("Total working days till current month: " + totalWorkingDays);
        System.out.println("Total working hours till current month: " + totalWorkingHours);
        
        return convertHoursToHMS(totalWorkingHours);
    }*/
    
    
 // Method to calculate total working hours up to the present month
  /*io  public String calculateTotalWorkingHoursInYear(LocalDate startOfYear, String empId) {
        LocalDate today = LocalDate.now();
        // Adjust start date if the year is 2024
        if (startOfYear.getYear() == 2024) {
            startOfYear = LocalDate.of(2024, 8, 23); // Start from August 23, 2024
        }
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, today);
        // Fetch the employee's leave records within the specified period
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, today);
        System.out.println("Leave Records: " + leaveRecords);

        // Collect leave days
        Set<LocalDate> leaveDays = new HashSet<>();
        for (LeaveApplication record : leaveRecords) {
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());

                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        int totalWorkingDays = 0;

        // Fetch employee email based on empId
        String email = employeeRepository.findEmailByEmpId(empId);
        System.out.println("Employee email: " + email);

        // Calculate probation completion date and two years after probation
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

      //  System.out.println("Probation completion date: " + probationCompletionDate);
        System.out.println("Two years after probation: " + twoYearsAfterProbation);

        System.out.println("Calculating working days between " + startOfYear + " and " + today);

        // Iterate over each date
        for (LocalDate date = startOfYear; !date.isAfter(today.minusDays(1)); date = date.plusDays(1)) {
            boolean excludeSecondAndFourthSaturday = 
                !date.isBefore(twoYearsAfterProbation) && 
                isSecondOrFourthSaturday(date);

            if (isWorkingDay(date, holidays, excludeSecondAndFourthSaturday)&& !leaveDays.contains(date)) {
                totalWorkingDays++;
                System.out.println("Working day: " + date);
            } else {
                System.out.println("Non-working day: " + date);
            }
        }

        System.out.println("Total working days till current month: " + totalWorkingDays);
        int totalWorkingHours = totalWorkingDays * WORKING_HOURS_PER_DAY;
        System.out.println("Total working hours till current month: " + totalWorkingHours);

        return convertHoursToHMS(totalWorkingHours);
    }*/
    
    public String calculateTotalWorkingHoursInYear(LocalDate startOfYear, String empId) {
        LocalDate today = LocalDate.now();
        // Retrieve employee details and DOJ
        LocalDate doj = null;
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
        if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
            try {
                doj = LocalDate.parse(empDetails.getDoj());
                System.out.println("Employee DOJ: " + doj);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing DOJ: " + empDetails.getDoj());
            }
        }
        // Adjust start date if the year is 2024
        if (startOfYear.getYear() == 2024) {
            startOfYear = LocalDate.of(2024, 8, 23); // Start from August 23, 2024
        }
        
        // If DOJ is in the current year, use DOJ as startOfYear
        if (doj != null && doj.getYear() == today.getYear()) {
            startOfYear = doj;
        }
        
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, today);
        
        // Fetch the employee's leave records within the specified period
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, today);
        System.out.println("Leave Records: " + leaveRecords);
        Set<LocalDate> fullDayLeaves = new HashSet<>();
        Set<LocalDate> halfDayLeaves = new HashSet<>();

        // Collect leave days
        Set<LocalDate> leaveDays = new HashSet<>();
        for (LeaveApplication record : leaveRecords) {
            if ("Approved".equals(record.getApprovedstatus()) && record.getFrom_date() != null && record.getTo_date() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                LocalDate toDate = LocalDate.parse(record.getTo_date());

                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    //leaveDays.add(date);
                	 if ("Half day".equalsIgnoreCase(record.getLeaveDuration())) { // Case-insensitive check
                         halfDayLeaves.add(date);
                     } else {
                         fullDayLeaves.add(date);
                     }
                }
            }
        }
        
        int totalWorkingDays = 0;
        int totalFullWorkingDays = 0;
        int totalHalfWorkingDays = 0;

        // Fetch employee email based on empId
        String email = employeeRepository.findEmailByEmpId(empId);
        System.out.println("Employee email: " + email);

        // Get probation completion date (checking for null)
        String alternativeSaturdayEffectiveDate = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        LocalDate twoYearsAfterProbation = null;

        if (alternativeSaturdayEffectiveDate != null && !alternativeSaturdayEffectiveDate.isEmpty()) {
            twoYearsAfterProbation = LocalDate.parse(alternativeSaturdayEffectiveDate);
            System.out.println("Two years after probation: " + twoYearsAfterProbation);
        } else {
            System.out.println("Skipping twoYearsAfterProbation calculation as the value is null or empty.");
        }

        System.out.println("Calculating working days between " + startOfYear + " and " + today);

        // Iterate over each date
        for (LocalDate date = startOfYear; !date.isAfter(today.minusDays(1)); date = date.plusDays(1)) {
            // Exclude second and fourth Saturdays only if the date is valid
            boolean excludeSecondAndFourthSaturday = twoYearsAfterProbation != null &&
                    !date.isBefore(twoYearsAfterProbation) &&
                    isSecondOrFourthSaturday(date);

          /*  if (isWorkingDay(date, holidays, excludeSecondAndFourthSaturday) && !leaveDays.contains(date)) {
                totalWorkingDays++;
                System.out.println("Working day: " + date);
            } else {
                System.out.println("Non-working day: " + date);
            }*/

            if (isWorkingDay(date, holidays, excludeSecondAndFourthSaturday)) {
                if (fullDayLeaves.contains(date)) {
                    System.out.println("Full leave day: " + date);
                } else if (halfDayLeaves.contains(date)) {
                    totalHalfWorkingDays++;
                    System.out.println("Half leave day: " + date);
                } else {
                    totalFullWorkingDays++;
                    System.out.println("Working day: " + date);
                }
            } else {
                System.out.println("Non-working day: " + date);
            }
        }

        System.out.println("Total working days till current month: " + totalWorkingDays);
       // int totalWorkingHours = totalWorkingDays * WORKING_HOURS_PER_DAY;
        double totalWorkingHours = (totalFullWorkingDays * WORKING_HOURS_PER_DAY) + (totalHalfWorkingDays * (WORKING_HOURS_PER_DAY / 2.0));
        System.out.println("Total working hours till current month: " + totalWorkingHours);
        System.out.println("Total working hours till current month: " + totalWorkingHours);

        return convertHoursToHMS(totalWorkingHours);
    }

    // Convert total hours to HH:mm:ss format
   /* private String convertHoursToHMS(int totalHours) {
        int hours = totalHours;
        int minutes = 0;
        int seconds = 0;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }*/
 // Convert total hours to HH:mm:ss format
    private String convertHoursToHMS(double totalHours) {
        int hours = (int) totalHours; // Extract the whole number of hours
        int minutes = (int) ((totalHours - hours) * 60); // Convert fractional hours to minutes
        int seconds = 0; // Keep seconds as 0

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
 

    // Check if a given date is a working day
  /*  private boolean isWorkingDay(LocalDate date, Set<LocalDate> holidays) {
        boolean isWorkingDay = !(date.getDayOfWeek().getValue() == 7 || holidays.contains(date)); // 7 represents Sunday
       // System.out.println("Date: " + date + " | Is Working Day: " + isWorkingDay);
        return isWorkingDay;
    }*/
 // Check if a given date is a working day
    private boolean isWorkingDay(LocalDate date, Set<LocalDate> holidays, boolean excludeSecondAndFourthSaturday) {
        // Check if the day is not a Sunday, not a holiday, and not excluded as 2nd or 4th Saturday
        boolean isWorkingDay = 
            !(date.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(date) || excludeSecondAndFourthSaturday);

        return isWorkingDay;
    }


    private Set<LocalDate> getHolidaysInnYear(LocalDate startOfYear, LocalDate endOfYear) {
        List<Holiday_Entity> holidays = holidayRepository.findAll();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        return holidays.stream()
            .map(holiday -> {
                try {
                    return LocalDate.parse(holiday.getDate(), dateFormatter);
                } catch (DateTimeParseException e) {
                 //   System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .filter(date -> !date.isBefore(startOfYear) && !date.isAfter(endOfYear))
            .collect(Collectors.toSet());
    }
    
    
    // Calculate total working days excluding Sundays and holidays up to the current month current month
   /* public int calculateTotalWorkingDaysExcludingHolidays(LocalDate startOfYear) {
        // Determine the end date as the last day of the current month
       // LocalDate endOfCurrentMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

    	 LocalDate currentDate = LocalDate.now();
        // Get holidays between the start of the year and the end of the current month
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);

        // Initialize the total working days counter
        int totalWorkingDays = 0;

        // Iterate through each day from startOfYear to endOfCurrentMonth
        for (LocalDate date = startOfYear; !date.isAfter(currentDate); date = date.plusDays(1)) {
            // Check if the day is a working day (not Sunday and not a holiday)
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date);
            
            // Debug output (optional)
            System.out.println("Date:rrr " + date + " | Is Working Day: " + isWorkingDay);
            
            // If it's a working day, increment the total working days counter
            if (isWorkingDay) {
                totalWorkingDays++;
            }
        }

        // Print and return the total working days
        System.out.println("Total Working Days (excluding Sundays and holidays) up to current month: " + totalWorkingDays);
        return totalWorkingDays;
    }*/

  /*  public int calculateTotalWorkingDaysExcludingHolidaysAndProbation(
    	    LocalDate startOfYear, 
    	    String empId
    	) {
    	    // Determine the current date
    	    LocalDate currentDate = LocalDate.now();

    	    // Fetch employee email based on empId
    	    String email = employeeRepository.findEmailByEmpId(empId);
    	    System.out.println("Employee email: " + email);

    	    // Calculate probation completion date and two years after probation
    	    LocalDate probationCompletionDate = LocalDate.parse(employeeDetailsService.getProbationCompletionDate(email));
    	    LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

    	    System.out.println("Probation completion date: " + probationCompletionDate);
    	    System.out.println("Two years after probation: " + twoYearsAfterProbation);

    	    // Get holidays between the start of the year and the current date
    	    Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);

    	    // Initialize the total working days counter
    	    int totalWorkingDays = 0;

    	    // Iterate through each day from startOfYear to currentDate
    	    for (LocalDate date = startOfYear; !date.isAfter(currentDate); date = date.plusDays(1)) {

    	        // Determine whether to exclude second and fourth Saturdays
    	        boolean excludeSecondAndFourthSaturday = !date.isBefore(twoYearsAfterProbation) && isSecondOrFourthSaturday(date);

    	        // Check if the day is a working day
    	        boolean isWorkingDay = 
    	            date.getDayOfWeek() != DayOfWeek.SUNDAY && 
    	            !excludeSecondAndFourthSaturday && 
    	            !holidays.contains(date);

    	        // Debug output (optional)
    	        System.out.println("Date: " + date + " | Is Working Day: " + isWorkingDay);

    	        // If it's a working day, increment the total working days counter
    	        if (isWorkingDay) {
    	            totalWorkingDays++;
    	        }
    	    }

    	    // Print and return the total working days
    	    System.out.println("Total Working Days (excluding Sundays, holidays, and specific Saturdays): " + totalWorkingDays);
    	    return totalWorkingDays;
    	}*/

  /*io  public int calculateTotalWorkingDaysExcludingHolidaysAndProbation(
    	    LocalDate startOfYear, 
    	    String empId
    	) {
    	    // Determine the current date
    	    LocalDate currentDate = LocalDate.now();

    	    // Adjust start date if the year is 2024
    	    if (startOfYear.getYear() == 2024) {
    	        startOfYear = LocalDate.of(2024, 8, 23); // Adjust start date to August 23, 2024
    	    }

    	    // Fetch employee email based on empId
    	    String email = employeeRepository.findEmailByEmpId(empId);
    	    System.out.println("Employee email: " + email);

    	    // Calculate probation completion date and two years after probation
    	    LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
    	  //  LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

    	//    System.out.println("Probation completion date: " + probationCompletionDate);
    	    System.out.println("Two years after probation: " + twoYearsAfterProbation);

    	    // Get holidays between the start of the year and the current date
    	    Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);

    	    // Fetch the employee's leave records within the specified period
    	    List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, currentDate);
    	    System.out.println("Leave Records: " + leaveRecords);
    	    Set<LocalDate> leaveDays = new HashSet<>();

    	    // Iterate over leave records to collect the leave days
    	    for (LeaveApplication record : leaveRecords) {
    	        System.out.println("Processing Leave Record: " + record);
    	        // Ensure the leave status is "Approved" and both fromDate and toDate are not null
    	        if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
    	            LocalDate fromDate = LocalDate.parse(record.getFromDate());
    	            LocalDate toDate = LocalDate.parse(record.getToDate());
    	            System.out.println("Leave Period: " + fromDate + " to " + toDate);
    	            // Add all the days in the leave range to the leaveDays set
    	            for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
    	                leaveDays.add(date);
    	            }
    	        }
    	    }

    	    // Initialize the total working days counter
    	    int totalWorkingDays = 0;

    	    // Iterate through each day from startOfYear to currentDate
    	    for (LocalDate date = startOfYear; date.isBefore(currentDate); date = date.plusDays(1)) {

    	        // Determine whether to exclude second and fourth Saturdays
    	        boolean excludeSecondAndFourthSaturday = !date.isBefore(twoYearsAfterProbation) && isSecondOrFourthSaturday(date);

    	        // Check if the day is a working day
    	        boolean isWorkingDay = 
    	            date.getDayOfWeek() != DayOfWeek.SUNDAY && 
    	            !excludeSecondAndFourthSaturday && 
    	            !holidays.contains(date)  &&
    	            !leaveDays.contains(date);

    	        // Debug output (optional)
    	        System.out.println("Date: " + date + " | Is Working Day: " + isWorkingDay);

    	        // If it's a working day, increment the total working days counter
    	        if (isWorkingDay) {
    	            totalWorkingDays++;
    	        }
    	    }

    	    // Print and return the total working days
    	    System.out.println("Total Working Days (excluding Sundays, holidays, and specific Saturdays): " + totalWorkingDays);
    	    return totalWorkingDays;
    	}*/
    
    public double calculateTotalWorkingDaysExcludingHolidaysAndProbation(
    	    LocalDate startOfYear, 
    	    String empId
    	) {
    	    // Determine the current date
    	    LocalDate currentDate = LocalDate.now();

    	    // Adjust start date if the year is 2024
    	    if (startOfYear.getYear() == 2024) {
    	        startOfYear = LocalDate.of(2024, 8, 23); // Adjust start date to August 23, 2024
    	    }
    	    // Fetch DOJ (Date of Joining)
    	    LocalDate doj = null;
    	    DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
    	    if (empDetails != null && empDetails.getDoj() != null && !empDetails.getDoj().isEmpty()) {
    	        try {
    	            doj = LocalDate.parse(empDetails.getDoj());
    	            System.out.println("Employee DOJ: " + doj);
    	        } catch (DateTimeParseException e) {
    	            System.err.println("Error parsing DOJ: " + empDetails.getDoj());
    	        }
    	    }
    	    // If DOJ is in the current year, set startOfYear to DOJ
    	    if (doj != null && doj.getYear() == currentDate.getYear()) {
    	        startOfYear = doj;
    	    }

    	    System.out.println("Final Start Date for Calculation: " + startOfYear);

    	    // Fetch employee email based on empId
    	    String email = employeeRepository.findEmailByEmpId(empId);
    	    System.out.println("Employee email: " + email);

    	    // Get the probation completion date (checking for null)
    	    String alternativeSaturdayEffectiveDate = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
    	    LocalDate twoYearsAfterProbation = null;

    	    if (alternativeSaturdayEffectiveDate != null && !alternativeSaturdayEffectiveDate.isEmpty()) {
    	        twoYearsAfterProbation = LocalDate.parse(alternativeSaturdayEffectiveDate);
    	        System.out.println("Two years after probation: " + twoYearsAfterProbation);
    	    } else {
    	        System.out.println("Skipping twoYearsAfterProbation calculation as the value is null or empty.");
    	    }

    	    // Get holidays between the start of the year and the current date
    	    Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, currentDate);

    	    // Fetch the employee's leave records within the specified period
    	    List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, currentDate);
    	    System.out.println("Leave Records: " + leaveRecords);
    	   // Set<LocalDate> leaveDays = new HashSet<>();
    	 // Map to store leave days and whether they are full-day (1.0) or half-day (0.5)
    	    Set<LocalDate> fullDayLeaves = new HashSet<>();
    	    Set<LocalDate> halfDayLeaves = new HashSet<>();

    	    
    	    // Iterate over leave records to collect the leave days
    	    for (LeaveApplication record : leaveRecords) {
    	        System.out.println("Processing Leave Record: " + record);
    	        // Ensure the leave status is "Approved" and both fromDate and toDate are not null
    	        if ("Approved".equals(record.getApprovedstatus()) && record.getFrom_date() != null && record.getTo_date() != null) {
    	            LocalDate fromDate = LocalDate.parse(record.getFrom_date());
    	            LocalDate toDate = LocalDate.parse(record.getTo_date());
    	            String leaveDuration = record.getLeaveDuration();
    	            System.out.println("Leave Period: " + fromDate + " to " + toDate);
    	            // Add all the days in the leave range to the leaveDays set
    	            for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
    	            	 if ("Full Day".equalsIgnoreCase(leaveDuration)) {
    	                     fullDayLeaves.add(date);
    	                 } else if ("Half Day".equalsIgnoreCase(leaveDuration)) {
    	                     halfDayLeaves.add(date);
    	                 }
    	            }
    	        }
    	    }
    	    System.out.println("Full Day Leave dfDays: " + fullDayLeaves);
    	    System.out.println("Half Day Leavedf Days: " + halfDayLeaves);

    	    // Initialize the total working days counter
    	    double totalWorkingDays = 0;

    	    // Iterate through each day from startOfYear to currentDate
    	    for (LocalDate date = startOfYear; date.isBefore(currentDate); date = date.plusDays(1)) {
    	        // Determine whether to exclude second and fourth Saturdays
    	        boolean excludeSecondAndFourthSaturday = twoYearsAfterProbation != null &&
    	                !date.isBefore(twoYearsAfterProbation) &&
    	                isSecondOrFourthSaturday(date);

    	        // Check if the day is a working day
    	        boolean isWorkingDay = 
    	            date.getDayOfWeek() != DayOfWeek.SUNDAY && 
    	            !excludeSecondAndFourthSaturday && 
    	            !holidays.contains(date) ;
    	          // &&  !fullDayLeaves.contains(date);
    	     // Start printing details for each date
    	        System.out.println("---------------------------------------------------");
    	        System.out.println("Date: " + date);
    	        System.out.println("  - Is Sunday? " + (date.getDayOfWeek() == DayOfWeek.SUNDAY));
    	        System.out.println("  - Is Holiday? " + holidays.contains(date));
    	        System.out.println("  - Is Full-Day Leave? " + fullDayLeaves.contains(date));
    	        System.out.println("  - Is Second/Fourth Saturday excluded? " + excludeSecondAndFourthSaturday);
    	        System.out.println("  - Is Half-Day Leave? " + halfDayLeaves.contains(date));

    	        // Debug output (optional)
    	       // System.out.println("Date: " + date + " | Is Working Day: " + isWorkingDay);

    	     /*   if (halfDayLeaves.contains(date)) {
    	            totalWorkingDays += 0.5;
    	            System.out.println("  ⚠️ Half-Day Leave Applied: Added 0.5 Working Day (+0.5)");
    	        } else */if (isWorkingDay) {
    	            totalWorkingDays++;
    	            System.out.println("  ✅ Counted as Full Working Day (+1)");
    	        }

    	        System.out.println("  🔹 Total Working Days So Far: " + totalWorkingDays);
    	    }

    	    // Print and return the total working days
    	    System.out.println("Total Working Days (excluding Sundays, holidays, and specific Saturdays): " + totalWorkingDays);
    	    return totalWorkingDays;
    	}




	 /* calcular yearly working hour */
	    
    // Method to calculate yearly working hours
    public Map<String, String> calculateYearlyWorkingHours(List<AttendanceDTO> attendanceList) {
        Map<String, Duration> yearlyWorkingHours = new HashMap<>();

        for (AttendanceDTO record : attendanceList) {
            String employeeName = record.getEid(); // Assuming `eid` is the employee's name or identifier
            Duration hoursWorked = Duration.ZERO;

            try {
                hoursWorked = calculateHoursWorked(record);
            } catch (DateTimeParseException e) {
                // Log the error and handle it appropriately
                System.err.println("Error parsing time for record: " + record);
                System.err.println("Exception: " + e.getMessage());
                // Optionally, handle the record or skip it
                continue;
            }

            yearlyWorkingHours.merge(employeeName, hoursWorked, Duration::plus);
        }

        // Convert Duration to HH:mm:ss format
        Map<String, String> formattedYearlyWorkingHours = new HashMap<>();
        for (Map.Entry<String, Duration> entry : yearlyWorkingHours.entrySet()) {
            String employeeName = entry.getKey();
            Duration duration = entry.getValue();

            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            formattedYearlyWorkingHours.put(employeeName, formattedTime);
        }

        return formattedYearlyWorkingHours;
    }

    // Method to calculate hours worked for a single record
    private Duration calculateHoursWorked(AttendanceDTO record) throws DateTimeParseException {
        // Assuming in_time and out_time are stored as Strings in "HH:mm:ss" format
        String inTimeStr = record.getInTime();
        String outTimeStr = record.getOutTime();

        // DateTimeFormatter for parsing time strings including seconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Parse times
        LocalTime inTime = LocalTime.parse(inTimeStr, formatter);
        LocalTime outTime = LocalTime.parse(outTimeStr, formatter);

        // Calculate hours worked
        Duration duration = Duration.between(inTime, outTime);
        return duration;
    }
    
    
    public Map<String, String> calculateOvertimeYearly(Map<String, String> yearlyWorkingHours, String totalWorkingHoursInYear) {
        Map<String, String> overtimeMap = new HashMap<>();

        // Convert total expected working hours to seconds for easier comparison
        String[] totalExpectedHoursParts = totalWorkingHoursInYear.split(":");
        long totalExpectedSeconds = Long.parseLong(totalExpectedHoursParts[0]) * 3600
                                  + Long.parseLong(totalExpectedHoursParts[1]) * 60
                                  + Long.parseLong(totalExpectedHoursParts[2]);

        // Calculate overtime/undertime for each employee
        for (Map.Entry<String, String> entry : yearlyWorkingHours.entrySet()) {
            String employeeName = entry.getKey();
            String workedHoursStr = entry.getValue();

            // Convert worked hours to seconds
            String[] workedHoursParts = workedHoursStr.split(":");
            long workedSeconds = Long.parseLong(workedHoursParts[0]) * 3600
                               + Long.parseLong(workedHoursParts[1]) * 60
                               + Long.parseLong(workedHoursParts[2]);

            // Calculate the difference
            long differenceInSeconds = workedSeconds - totalExpectedSeconds;

            // Only include positive differences (overtime)
            if (differenceInSeconds > 0) {
                // Convert the difference back to HH:mm:ss format
                long hours = differenceInSeconds / 3600;
                long minutes = (differenceInSeconds % 3600) / 60;
                long seconds = differenceInSeconds % 60;

                String formattedTimeDifference = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                // Store the result only if it's positive
                overtimeMap.put(employeeName, formattedTimeDifference);
            } else {
                // If the difference is negative or zero, store "-" (or simply don't store anything)
                overtimeMap.put(employeeName, "-");
            }
        }

        return overtimeMap;
    }

   /* public Map<String, List<LocalDate>> getWeeksOfMonth(int month, int year) {
        // Ensure the year is valid
        if (year < 1) {
            throw new IllegalArgumentException("Year must be a positive number");
        }

        // Use LocalDate.of to handle any month and year
        LocalDate firstDayOfMonth;
        try {
            firstDayOfMonth = LocalDate.of(year, month, 1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid month or year provided");
        }

        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Map<String, List<LocalDate>> weeks = new HashMap<>();
        LocalDate startOfWeek = firstMonday;
        int weekNumber = 1;

        while (startOfWeek.isBefore(lastDayOfMonth) || startOfWeek.isEqual(lastDayOfMonth)) {
            LocalDate endOfWeek = startOfWeek.plusWeeks(1).minusDays(1);
            if (endOfWeek.isAfter(lastDayOfMonth)) {
                endOfWeek = lastDayOfMonth;
            }

            List<LocalDate> dates = new ArrayList<>();
            LocalDate currentDate = startOfWeek;
            while (!currentDate.isAfter(endOfWeek)) {
                dates.add(currentDate);
                currentDate = currentDate.plusDays(1);
            }

            weeks.put("Week " + weekNumber, dates);
            startOfWeek = startOfWeek.plusWeeks(1);
            weekNumber++;
        }

        return weeks;
    }*/
    
    public Map<String, List<LocalDate>> getWeeksOfMonth(int month, int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Year must be a positive number");
        }

        // Custom month period: 26th of the previous month to 25th of the current month
        LocalDate startDate = LocalDate.of(year, month, 26).minusMonths(1); // 26th of the previous month
        LocalDate endDate = LocalDate.of(year, month, 25); // 25th of the current month

        Map<String, List<LocalDate>> weeks = new LinkedHashMap<>(); // Maintain order
        LocalDate currentDate = startDate;
        int weekNumber = 1;

        while (!currentDate.isAfter(endDate)) {
            List<LocalDate> weekDates = new ArrayList<>();
            for (int i = 0; i < 7 && !currentDate.isAfter(endDate); i++) {
                weekDates.add(currentDate);
                currentDate = currentDate.plusDays(1);
            }
            weeks.put("Week " + weekNumber, weekDates);
            weekNumber++;
        }

        return weeks;

    }
    
    /*public int calculateRemainingWorkingDaysInYear(int year,String empId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        if (today.isAfter(endOfYear)) {
            // If today is after the end of the year, there are no remaining days
            return 0;
        }

        // Get all holidays for the year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, endOfYear);

        
        // Retrieve the employee's leave records for the specified year
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, endOfYear);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Process the leave records to extract leave days
        for (LeaveApplication record : leaveRecords) {
            if ("Approved".equals(record.getApprovedstatus()) && record.getFromDate() != null && record.getToDate() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFromDate());
                LocalDate toDate = LocalDate.parse(record.getToDate());

                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        // Retrieve employee email and probation completion date for second/fourth Saturday check
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate twoYearsAfterProbation = LocalDate.parse(employeeDetailsService.getAlternativeSaturdayEffectiveDate(email));
       // LocalDate twoYearsAfterProbation = probationCompletionDate.plusYears(2);

        
        int remainingWorkingDays = 0;

        for (LocalDate date = today; !date.isAfter(endOfYear); date = date.plusDays(1)) {
            boolean isSecondOrFourthSaturday = isSecondOrFourthSaturday(date);
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                                    !holidays.contains(date) &&
                                    !leaveDays.contains(date) &&
                                    (!date.isAfter(twoYearsAfterProbation) || !isSecondOrFourthSaturday);
            if (isWorkingDay) {
                remainingWorkingDays++;
            }
        }
        return remainingWorkingDays;
    }*/

    public int calculateRemainingWorkingDaysInYear(int year, String empId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        if (today.isAfter(endOfYear)) {
            // If today is after the end of the year, there are no remaining days
            return 0;
        }

        // Get all holidays for the year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, endOfYear);

        // Retrieve the employee's leave records for the specified year
        List<LeaveApplication> leaveRecords = leaveApplicationRepository.getLeaveRecordsForEmployee(empId, startOfYear, endOfYear);
        Set<LocalDate> leaveDays = new HashSet<>();

        // Process the leave records to extract leave days
        for (LeaveApplication record : leaveRecords) {
            if ("Approved".equals(record.getApprovedstatus()) && record.getFrom_date() != null && record.getTo_date() != null) {
                LocalDate fromDate = LocalDate.parse(record.getFrom_date());
                LocalDate toDate = LocalDate.parse(record.getTo_date());

                for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    leaveDays.add(date);
                }
            }
        }

        // Retrieve employee email and probation completion date for second/fourth Saturday check
        String email = employeeRepository.findEmailByEmpId(empId);
        LocalDate twoYearsAfterProbation = null;

        // Handle null or empty values properly
        String alternativeSaturdayEffectiveDate = employeeDetailsService.getAlternativeSaturdayEffectiveDate(email);
        if (alternativeSaturdayEffectiveDate != null && !alternativeSaturdayEffectiveDate.isEmpty()) {
            twoYearsAfterProbation = LocalDate.parse(alternativeSaturdayEffectiveDate);
            System.out.println("Two years after probation: " + twoYearsAfterProbation);
        } else {
            System.out.println("Skipping twoYearsAfterProbation calculation as the value is null or empty.");
        }

        int remainingWorkingDays = 0;

        for (LocalDate date = today; !date.isAfter(endOfYear); date = date.plusDays(1)) {
            boolean isSecondOrFourthSaturday = twoYearsAfterProbation != null && 
                                               !date.isBefore(twoYearsAfterProbation) && 
                                               isSecondOrFourthSaturday(date);

            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                                   !holidays.contains(date) &&
                                   !leaveDays.contains(date) &&
                                   !isSecondOrFourthSaturday;

            if (isWorkingDay) {
                remainingWorkingDays++;
            }
        }
        return remainingWorkingDays;
    }

   
    public List<AttendanceDTO> getAttendanceForYear(int year, String employeeId) {
    //	System.out.println("jj");
        return attendanceRepository.findAttendanceByyearAndEmployee(year, employeeId);
    }
    
    
    public List<AttendanceDTO> getAttendanceForYearDashboard(int year, String employeeId) {
      //  System.out.println("jj");
        List<AttendanceDTO> attendanceRecords = attendanceRepository.findAttendanceByyearAndEmployee(year, employeeId);

        // Initialize a variable to track total attendance days (considering half-day as 0.5)
        double totalAttendanceDays = 0.0;
        
        // Filter out attendance records where total working hours are less than or equal to 6
        List<AttendanceDTO> filteredRecords = new ArrayList<>();
        for (AttendanceDTO record : attendanceRecords) {
            String totalHoursStr = record.getTotalHours(); // Get the time string

            if (totalHoursStr != null && !totalHoursStr.equals("-")) { // Handle missing values
                try {
                    double totalHours = convertTimeToHours(totalHoursStr);  // Convert time string to hours

                    if (totalHours > 6) {
                        // Full day attendance
                        totalAttendanceDays += 1;  // Full day is counted as 1
                        filteredRecords.add(record);
                        // Print out the details of the record
                        System.out.println("Employee ID: " + employeeId);
                        System.out.println("Total Hours Worked (Full Day): " + totalHours);
                        System.out.println("--------------------------------------");
                    } else if (totalHours >= 2.5 && totalHours <= 4) {
                        // Half day attendance
                        totalAttendanceDays += 0.5;  // Half day is counted as 0.5
                        filteredRecords.add(record);
                        // Print out the details of the record
                    //    System.out.println("Employee ID: " + employeeId);
                     //   System.out.println("Total Hours Worked (Half Day): " + totalHours);
                     //   System.out.println("--------------------------------------");
                    }
                } catch (Exception e) {
                    System.out.println("Error converting total hours value: " + totalHoursStr);
                }
            } else {
                System.out.println("Skipping invalid total hours value: " + totalHoursStr);
            }
        }

        // Print the total attendance days
        System.out.println("Total Attendance Days (considering half-day as 0.5): " + totalAttendanceDays);
        
        // Print the number of filtered records
        System.out.println("Number of filtered attendance records: " + filteredRecords.size());

        
        return filteredRecords;
    }

   /* public double calculateTotalAttendanceDays(List<AttendanceDTO> attendanceRecords) {
        double totalAttendanceDays = 0.0;

        for (AttendanceDTO record : attendanceRecords) {
            String totalHoursStr = record.getTotalHours();
            String attendanceDate = record.getDate(); 
            System.out.println("Processing record - Date: " + attendanceDate + ", Total Hours: " + totalHoursStr);

            if (totalHoursStr != null && !totalHoursStr.equals("-")) {
                try {
                    double totalHours = convertTimeToHours(totalHoursStr);
                    System.out.println("Converted Total Hours: " + totalHours);
                    if (totalHours > 6) {
                        totalAttendanceDays += 1;
                        System.out.println("Full-day counted. Total Attendance Days: " + totalAttendanceDays);
                    } else if (totalHours >= 2.5 && totalHours <= 5) {
                        totalAttendanceDays += 0.5;
                        System.out.println("Half-day counted. Total Attendance Days: " + totalAttendanceDays);
                    }
                } catch (Exception e) {
                    System.out.println("Error converting total hours value: " + totalHoursStr);
                }
            }
        }
        System.out.println("Final Total Attendance Days: " + totalAttendanceDays);
        return totalAttendanceDays;
    }*/

    public double calculateTotalAttendanceDays(List<AttendanceDTO> attendanceRecords) {
        double totalAttendanceDays = 0.0;

        LocalDate[] range = getCurrentFinancialYearRange();
        LocalDate startDate = range[0];
        LocalDate endDate = range[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (AttendanceDTO record : attendanceRecords) {
            String totalHoursStr = record.getTotalHours();
            String attendanceDateStr = record.getDate();

            try {
                LocalDate attendanceDate = LocalDate.parse(attendanceDateStr, formatter);

                // ✅ Filter based on financial year range
                if (attendanceDate.isBefore(startDate) || attendanceDate.isAfter(endDate)) {
                    continue;
                }

                System.out.println("Processing record - Date: " + attendanceDate + ", Total Hours: " + totalHoursStr);

                if (totalHoursStr != null && !totalHoursStr.equals("-")) {
                    double totalHours = convertTimeToHours(totalHoursStr);
                    System.out.println("Converted Total Hours: " + totalHours);

                    if (totalHours > 6) {
                        totalAttendanceDays += 1;
                        System.out.println("Full-day counted. Total Attendance Days: " + totalAttendanceDays);
                    } else if (totalHours >= 2.5 && totalHours <= 5) {
                        totalAttendanceDays += 0.5;
                        System.out.println("Half-day counted. Total Attendance Days: " + totalAttendanceDays);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error parsing or converting total hours for record with date: " + attendanceDateStr);
            }
        }

        System.out.println("Final Total Attendance Days: " + totalAttendanceDays);
        return totalAttendanceDays;
    }


    public LocalDate[] getCurrentFinancialYearRange() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        LocalDate start;
        LocalDate end;

        // If today's date is on or after March 26, we're in the new financial year
        if (today.isAfter(LocalDate.of(year, 3, 25))) {
            start = LocalDate.of(year, 3, 26);
            end = LocalDate.of(year + 1, 3, 25);
        } else {
            // Else we're still in the previous financial year
            start = LocalDate.of(year - 1, 3, 26);
            end = LocalDate.of(year, 3, 25);
        }

        return new LocalDate[]{start, end};
    }

    
 // Helper method to convert time string (HH:mm:ss) to hours (as a double)
    private double convertTimeToHours(String timeStr) throws Exception {
        String[] parts = timeStr.split(":");
        if (parts.length == 3) {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            // Convert to hours
            return hours + minutes / 60.0 + seconds / 3600.0;
        } else {
            throw new Exception("Invalid time format");
        }
    }
    
    public List<AttendanceDTO> getAllAttendance(String employeeId) {
    	System.out.println("ii");
        return attendanceRepository.findAttendanceByEmployee(employeeId);
    }

    
    public double getTotalHoursWorked(LocalDate date, String employeeId) {
        System.out.println("Calculating total hours worked for Date: " + date + ", Employee ID: " + employeeId);

        // Fetch attendance for the year of the given date
        List<AttendanceDTO> attendanceRecords = getAttendanceForYear(date.getYear(), employeeId);

        // Format the date to match the DTO
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Filter for the specific date and retrieve total hours
        Optional<AttendanceDTO> firstRecord = attendanceRecords.stream()
            .filter(record -> record.getDate().equals(dateString))
            .findFirst(); // Get the first record for the date

        Optional<AttendanceDTO> lastRecord = attendanceRecords.stream()
            .filter(record -> record.getDate().equals(dateString))
            .reduce((first, second) -> second); // Get the last record for the date

        // If both first and last records are available, return the total hours directly
        if (firstRecord.isPresent() && lastRecord.isPresent()) {
            String totalHours = firstRecord.get().getTotalHours(); // Get total hours from the first record (or last)
            if (totalHours != null && !totalHours.equals("-")) {
                return convertTimeToDecimal(totalHours);
            }
        }

        // Default to 0.0 if no records found or valid total hours are missing
        return 0.0;
    }

    public double convertTimeToDecimal(String timeString) {
        if (timeString == null || timeString.equals("-")) {
            return 0.0;
        }
        String[] parts = timeString.split(":");
        if (parts.length == 3) {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            // Convert time to hours as a decimal
            return hours + minutes / 60.0 + seconds / 3600.0;
        }
        return 0.0;
    }

  /*  public void saveAttendance(AttendanceDTO attendanceDTO) {
        // Print the attendance data being saved
        System.out.println("Saving attendance data: " +
                "eid=" + attendanceDTO.getEid() + ", " +
                "inTime=" + attendanceDTO.getInTime() + ", " +
                "date=" + attendanceDTO.getDate() + ", " +
                "pdt=" + attendanceDTO.getPdt());

        // Prepare SQL query to insert attendance data
        String sql = "INSERT INTO attendance (eid, pt, pd, pdt) VALUES (?, ?, ?, ?)";

        // Execute the query with parameters
        int rowsAffected = jdbcTemplate.update(sql, 
            attendanceDTO.getEid(), 
            attendanceDTO.getInTime(), 
            attendanceDTO.getDate(), 
            attendanceDTO.getPdt()); // Now this will call the getPdt method

        // Print confirmation message after saving
        if (rowsAffected > 0) {
            System.out.println("Attendance data saved successfully.");
        } else {
            System.out.println("Failed to save attendance data.");
        }
    }*/
    
    public void saveAttendance(AttendanceDTO attendanceDTO) {
        // Print the received AttendanceDTO object
        System.out.println("Received AttendanceDTO object: " + attendanceDTO);

        // SQL statement for inserting attendance data
        String sql = "INSERT INTO attendance (eid, pt, pd, pdt) VALUES (?, ?, ?, ?)";
        System.out.println("SQL Query: " + sql);

        // Extract values from AttendanceDTO
        String eid = attendanceDTO.getEid();
        System.out.println("Extracted eid: " + eid);

        String pt = attendanceDTO.getInTime();
        System.out.println("Extracted inTime (pt): " + pt);

        String pd = attendanceDTO.getDate();
        System.out.println("Extracted date (pd): " + pd);

        String pdt = attendanceDTO.getPdt(); // Combines date and inTime
        System.out.println("Generated combined pdt (date + inTime): " + pdt);

        // Execute the SQL update to insert data
        jdbcTemplate.update(sql, eid, pt, pd, pdt);

        // Confirm success of attendance data saving
        System.out.println("Attendance data saved successfully with details:");
        System.out.println("{eid: " + eid + ", pt: " + pt + ", pd: " + pd + ", pdt: " + pdt + "}");
    }

   /* public void saveUserCheckin(UserCheckin userCheckin) {
        userCheckinRepository.save(userCheckin);
    }*/
    
	/*
	 * public List<AttendanceDTO>
	 * getAttendanceByCurrentyearAndCurrentMonthForLoggedInHR(String employeeId) {
	 * return
	 * attendanceRepository.fetchAttendanceByCurrentyearAndCurrentMonthForLoggedInHR
	 * (employeeId); }
	 */
    
    public List<AttendanceDTO> getAttendanceForEmpIdNotWithYear(String employeeId) {
        return attendanceRepository.findAttendanceByEmployeeNotWithYear(employeeId);
    } 
    
    public int calculateRemainingWorkingDaysInYear(int year) {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        if (today.isAfter(endOfYear)) {
            // If today is after the end of the year, there are no remaining days
            return 0;
        }

        // Get all holidays for the year
        Set<LocalDate> holidays = getHolidaysInnYear(startOfYear, endOfYear);

        int remainingWorkingDays = 0;

        for (LocalDate date = today; !date.isAfter(endOfYear); date = date.plusDays(1)) {
            boolean isWorkingDay = date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date);
            if (isWorkingDay) {
                remainingWorkingDays++;
            }
        }
        return remainingWorkingDays;
    }
    
    public List<AttendanceDTO> getAttendanceForYearByEmpid(int year, String employeeId ,String fromDate,String toDate) {
    	System.out.println("jj");
        return attendanceRepository.findAttendanceByYearAndEmpId(year, employeeId,fromDate,toDate);
    }

}
