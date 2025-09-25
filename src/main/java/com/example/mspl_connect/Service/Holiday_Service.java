package com.example.mspl_connect.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.Holiday_Entity;
import com.example.mspl_connect.Repository.Holiday_Repo;

import jakarta.transaction.Transactional;

@Service
public class Holiday_Service {

	@Autowired
	private Holiday_Repo holidayRepository;
	
	/*@Transactional
	 public String insertHolidayData(Holiday_Entity holiday_Entity) {
		holidayRepository.save(holiday_Entity);
		 return "success";
	 }*/
	
	@Transactional
	 public String insertHolidayData(Holiday_Entity holiday_Entity) {
		holidayRepository.insertHolidayDetails(holiday_Entity.getDate(), holiday_Entity.getName(), holiday_Entity.getDay());
		 return "success";
	 }
	
	@Transactional
    public String updateHolidayData(long id, String date, String day, String name) {
		holidayRepository.update_Holiday_details(id, date, day, name);
	 return "success";
	}
	
	@Transactional
	public String deleteHolidaysData(long id) {
		holidayRepository.delete_HolidayDetails(id);
		return "success";
	}
	
	public List<Holiday_Entity> holidayList(){
		return holidayRepository.findAllByOrderByDateAsc();
	}
	
	public Holiday_Entity fetchHolidayDataForEdit(String auto_id){
		return holidayRepository.editHolidayData(auto_id);
	}
	
	public List<Holiday_Entity> findByDateStartingWithYear(String year){
		return holidayRepository.findByDateStartingWithYearData(year);
	}	
	
	public List<LocalDate> getAllHolidayDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return holidayRepository.findAll().stream()
                .map(holiday -> LocalDate.parse(holiday.getDate(), formatter))
                .collect(Collectors.toList());
    }
	
	
	public List<Holiday_Entity> getAllHolidaysfetchdate() {
        List<Holiday_Entity> holidays = holidayRepository.findAll();
        System.out.println("Number of holidays fetched from service: " + holidays.size());
        return holidays;
    }
	
	public List<Holiday_Entity> getHolidayBasedOnYear(String year){
		return holidayRepository.fetchHolidayBasedOnYear(year);
	}
	
	 public List<Holiday_Entity> getAllHolidaysfetch() {
		 System.out.println("fetched");
	        return holidayRepository.findAll();
	    }
	 
/* For count total working days in a month and displaying in EmployeeAttendance */
	  // Fetch holidays and convert to LocalDate
	    public List<LocalDate> getHolidaysForMonth(YearMonth yearMonth) {
	        List<Holiday_Entity> holidays = holidayRepository.findAll();
	        
	        // Adjust pattern based on your actual date format
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d"); 

	        System.out.println("Date Formatter Pattern: " + dateFormatter.toString());
	        
	        List<LocalDate> holidayDates = holidays.stream()
	            .map(holiday -> {
	                System.out.println("Processing holiday date: " + holiday.getDate());
	                try {
	                    LocalDate localDate = LocalDate.parse(holiday.getDate(), dateFormatter);
	                    System.out.println("Parsed LocalDate: " + localDate);
	                    return localDate;
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull) // Filter out null values from parsing errors
	            .peek(date -> System.out.println("Filtered valid date: " + date))
	            .filter(date -> isDateInMonth(date, yearMonth))
	            .peek(date -> System.out.println("Date in month: " + date))
	            .collect(Collectors.toList());
	        
	        System.out.println("Holidays for month: " + holidayDates);
	        
	        return holidayDates;
	    }

	    
	    public List<LocalDate> getHolidaysForYear(Year year) {
	        // Fetch all holidays from the repository
	        List<Holiday_Entity> holidays = holidayRepository.findAll();

	        // Define your date pattern (adjust based on your actual date format in the database)
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

	        // Process and filter holidays that belong to the specified year
	        List<LocalDate> holidayDates = holidays.stream()
	            .map(holiday -> {
	                try {
	                    // Parse the date string into LocalDate
	                    return LocalDate.parse(holiday.getDate(), dateFormatter);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing holiday date: " + holiday.getDate() + " - " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull) // Filter out parsing errors (null values)
	            .filter(date -> date.getYear() == year.getValue()) // Filter holidays to match the specified year
	            .collect(Collectors.toList());

	        System.out.println("Holidays for year " + year + ": " + holidayDates);
	        return holidayDates;
	    }
	    
	    
	    private boolean isDateInMonth(LocalDate date, YearMonth yearMonth) {
	        boolean inMonth = !date.isBefore(yearMonth.atDay(1)) && !date.isAfter(yearMonth.atEndOfMonth());
	        System.out.println("Date " + date + " is in month " + yearMonth + ": " + inMonth);
	        return inMonth;
	    }
	    
	 // Fetch holidays for the given month
	    public Set<LocalDate> getHolidaysForMonthforhour(YearMonth yearMonth) {
	        List<Holiday_Entity> holidays = holidayRepository.findAll();
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d"); 

	        return holidays.stream()
	            .map(holiday -> {
	                try {
	                    return LocalDate.parse(holiday.getDate(), dateFormatter);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull) 
	            .filter(date -> isDateInMonth(date, yearMonth))
	            .collect(Collectors.toSet());
	    }

	    private boolean isDateInMonth1(LocalDate date, YearMonth yearMonth) {
	        return !date.isBefore(yearMonth.atDay(1)) && !date.isAfter(yearMonth.atEndOfMonth());
	    }
	    
	    public List<LocalDate> getHolidaysInMonth(YearMonth yearMonth) {
	        return holidayRepository.findAll().stream()
	                .map(holiday -> LocalDate.parse(holiday.getDate(), DateTimeFormatter.ofPattern("yyyy-M-d")))
	                .filter(date -> date.getYear() == yearMonth.getYear() && date.getMonth() == yearMonth.getMonth())
	                .collect(Collectors.toList());
	    }
	    
	    public List<Holiday_Entity> getHolidaysForYear(int year) {
	        // Fetch all holidays from the repository
	        List<Holiday_Entity> allHolidays = holidayRepository.findAll();
	        // Filter holidays based on the year extracted from the date
	        return allHolidays.stream()
	            .filter(holiday -> {
	                LocalDate holidayDate = LocalDate.parse(holiday.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
	                return holidayDate.getYear() == year;
	            })
	            .collect(Collectors.toList());
	    }
	    
	    public List<Holiday_Entity> getAllHolidays() {
	        // Fetch all holidays from the repository without filtering by year
	        return holidayRepository.findAll();
	    }
	    // Fetch holidays for the given custom month period (26th of prev month - 25th of selected month)
	    public Set<LocalDate> getHolidaysForCustomMonth(LocalDate startOfMonth, LocalDate endOfMonth) {
	        List<Holiday_Entity> holidays = holidayRepository.findAll(); // Fetch all holidays
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d"); 

	        return holidays.stream()
	            .map(holiday -> {
	                try {
	                    return LocalDate.parse(holiday.getDate(), dateFormatter);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull)
	            .filter(date -> !date.isBefore(startOfMonth) && !date.isAfter(endOfMonth)) // Check within custom period
	            .collect(Collectors.toSet());
	    }
	    
	    public Set<LocalDate> getAllHolidaysnew() {
	        List<Holiday_Entity> holidays = holidayRepository.findAll();
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

	        return holidays.stream()
	            .map(holiday -> {
	                try {
	                    return LocalDate.parse(holiday.getDate(), dateFormatter);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull)
	            .collect(Collectors.toSet());
	    }
	    public List<LocalDate> getHolidaysBetween(LocalDate start, LocalDate end) {
	        List<Holiday_Entity> holidays = holidayRepository.findAll();

	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

	        return holidays.stream()
	            .map(holiday -> {
	                try {
	                    return LocalDate.parse(holiday.getDate(), dateFormatter);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Error parsing date: " + holiday.getDate() + " with exception: " + e.getMessage());
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull)
	            .filter(date -> !date.isBefore(start) && !date.isAfter(end))
	            .collect(Collectors.toList());
	    }
	
}
