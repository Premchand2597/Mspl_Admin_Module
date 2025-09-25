package com.example.mspl_connect.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.Entity.Event;
import com.example.mspl_connect.Repository.EventRepository;
import com.example.mspl_connect.Service.DashboardService;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.EventService;

@RestController
@RequestMapping("/api")
public class DashboardController {
		
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
    private EventRepository eventRepository;
	
	@Autowired
	private EventService eventService;


    @GetMapping("/attendance")
    public List<Map<String, Object>> getDepartmentAttendance() {
    	int totalEmployeCount = detaisService.totalEmployeCount();
    	int presentCount = dashboardService.totalPresentCount();
    	int absentCount = totalEmployeCount-presentCount;
    	
    	System.out.println("totalEmployeCount="+totalEmployeCount);
    	System.out.println("presentCount="+presentCount);
    	System.out.println("absentCount="+absentCount);
    	
        List<Map<String, Object>> departmentData = new ArrayList<>();
        
        Map<String, Object> present = new HashMap<>();
        present.put("name", "Present");
        present.put("count", presentCount);
        departmentData.add(present);

        Map<String, Object> absent = new HashMap<>();
        absent.put("name", "Absent");
        absent.put("count", absentCount);
        departmentData.add(absent);
        System.out.println("departmentData--"+departmentData);
        return departmentData;
    }
    
    @GetMapping("/events")
    public List<Map<String, String>> getEvents() {
        
    	//List<Event> allEvents = eventRepository.findAll();    	
    	List<Event> allEvents = eventRepository.findEventsForCurrentYear(); 
    	
        //List<Object[]> currentMonthBirthdaysData = eventService.getEmployeesWithUpcomingBirthdays();       
        List<Map<String, String>> filteredEvents = new ArrayList<>(); 
        
        List<Object[]> currentMonthBirthdaysData = eventService.getEmployeesWithUpcomingEvents();
        //List<Object[]> currentMonthAnneverseries = eventService.getEmployeesWithUpcomingAnneversery();
        
        System.out.println("Birthday Data:");
        for (Object[] row : currentMonthBirthdaysData) {
            System.out.println(Arrays.toString(row)); // Print the contents of the array
        }
       
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Handle alternate date format
        
        LocalDate today = LocalDate.now();

        // Process all events
        for (Event event : allEvents) {
            LocalDate eventDate = parseDate(event.getEventDate(), formatter1, formatter2); // Parse with multiple formats
            
            if (eventDate != null && eventDate.getMonth().equals(today.getMonth())) {
                
            	Map<String, String> eventMap = new HashMap<>();
                
                // Format date to "dd-MM-yyyy" before adding it to the map
                String formattedDate = eventDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                eventMap.put("date", formattedDate); 
                eventMap.put("content", event.getEventTitle());
                eventMap.put("badgeColor", getRandomBgColor()); // Assign random background color
                filteredEvents.add(eventMap);
                
            }
        }

        // Process current month birthdays
        for (Object[] birthday : currentMonthBirthdaysData) {
            
        	String fullname = (String) birthday[0];
            String dob = (String) birthday[1];
            String eventType = (String) birthday[2];
            
            LocalDate birthdayDate = parseDate(dob, formatter1, formatter2);

            if (birthdayDate != null) {
                Map<String, String> birthdayMap = new HashMap<>();
                birthdayMap.put("date", dob); // Assuming 'dob' is in the format you need
                birthdayMap.put("content", fullname + "'s " + eventType); // Add event type to contents
                birthdayMap.put("badgeColor", getRandomBgColor()); // Assign random background color
                filteredEvents.add(birthdayMap);
            }             
        }
        
        System.out.println("before filteredEvents=="+filteredEvents);
        // Sort filteredEvents by day of the month
        filteredEvents.sort((map1, map2) -> {
            LocalDate date1 = parseDate(map1.get("date"), formatter2, formatter1);
            LocalDate date2 = parseDate(map2.get("date"), formatter2, formatter1);
            return Integer.compare(date1.getDayOfMonth(), date2.getDayOfMonth()); // Sort by day only
        });
        
        System.out.println("after filteredEvents=="+filteredEvents);
        return filteredEvents;
        
    }

    // Method to handle parsing of multiple date formats
    private LocalDate parseDate(String dateStr, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        return null; // Return null if no valid format is found
    }

    private String getRandomBgColor() {
        String[] colors = {"text-primary", "text-secondary", "text-success", "text-danger", "text-warning", "text-info", "text-light", "text-dark"};
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

    
}

