package com.example.mspl_connect.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;

@Service
public class EventService {
	    @Autowired
	    private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;

		/*
		 * public List<Object[]> getEmployeesWithUpcomingBirthdays() { return
		 * employeeRepositoryWithDeptName.findEmployeesWithUpcomingBirthdays(); }
		 */
	    
	    public List<Object[]> getEmployeesWithUpcomingEvents() {
	        List<Object[]> birthdays = employeeRepositoryWithDeptName.findEmployeesWithUpcomingBirthdays();
	        List<Object[]> anniversaries = employeeRepositoryWithDeptName.findEmployeesWithUpcomingAnneversery();
	        
	        birthdays.forEach(i -> System.out.println("Birthday: " + Arrays.toString(i)));
	        anniversaries.forEach(i -> System.out.println("Anniversary: " + Arrays.toString(i)));
	        
	        // Merged list with differentiation
	        List<Object[]> mergedEvents = new ArrayList<>();

	        // Add event type for birthdays
	        for (Object[] birthday : birthdays) {
	            Object[] eventWithLabel = Arrays.copyOf(birthday, birthday.length + 1);
	            eventWithLabel[birthday.length] = "Birthday"; // Add the event type
	            mergedEvents.add(eventWithLabel);
	        }

	        // Add event type for anniversaries with year calculation
	        for (Object[] anniversary : anniversaries) {
	            Object[] eventWithLabel = Arrays.copyOf(anniversary, anniversary.length + 1);
	            String originalDate = (String) anniversary[1]; // Assuming the date is in the second column
	            int anniversaryYear = calculateAnniversaryYear(originalDate) ; // Calculate anniversary year
	            // eventWithLabel[anniversary.length] = anniversaryYear + " year(s) Work Anniversary"; // Add the event type with year
	            
	            // If anniversaryYear is 0, label as "New Joinee", otherwise "X year(s) Work Anniversary"
	            if (anniversaryYear == 0) {
	                eventWithLabel[anniversary.length] = "New Joinee";
	            } else {
	                eventWithLabel[anniversary.length] = anniversaryYear + " year(s) Work Anniversary";
	            }
	            
	            mergedEvents.add(eventWithLabel);
	        }

	        for (Object[] event : mergedEvents) {
	            System.out.println("Event Details: " + Arrays.toString(event));
	        }
	        return mergedEvents;
	    }


	    private int calculateAnniversaryYear(String originalDate) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Adjust format as needed
	        LocalDate original = LocalDate.parse(originalDate, formatter); // Parse the original date
	        LocalDate now = LocalDate.now();

	        // Only consider the year difference based on the month
	        int years = now.getYear() - original.getYear();
	        if (now.getMonthValue() < original.getMonthValue()) {
	            years--; // Subtract a year if the current month is earlier than the event month
	        }

	        return years;
	    }


		/*
		 * public List<Object[]> getEmployeesWithUpcomingAnneversery() { return
		 * employeeRepositoryWithDeptName.findEmployeesWithUpcomingAnneversery(); }
		 */
}
