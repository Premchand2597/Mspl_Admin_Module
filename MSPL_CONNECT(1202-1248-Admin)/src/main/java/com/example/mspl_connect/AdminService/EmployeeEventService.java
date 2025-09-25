package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EmployeeEvent;
import com.example.mspl_connect.AdminRepo.EmployeeEventRepository;


@Service
public class EmployeeEventService {

	 @Autowired
	    private EmployeeEventRepository employeeEventRepository;

	    public Optional<EmployeeEvent> getEventById(int id) {
	        return  employeeEventRepository.findById(id);
	    }

	    public List<EmployeeEvent> getAllEvents() {
	        List<EmployeeEvent> eventsInService = employeeEventRepository.fetchAllEventsDataBasedRecentlyCompleted();
	        System.out.println("eventsInService--------"+eventsInService);
	        // Format the eventDate to dd-MM-yyyy
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        eventsInService.forEach(event -> {
	            if (event.getEventDate() != null) {
	                LocalDate eventDate = LocalDate.parse(event.getEventDate()); // Assuming eventDate is in yyyy-MM-dd format
	                String formattedDate = eventDate.format(formatter);
	                event.setEventDate(formattedDate); // Set the formatted date back to the event object
	            }
	            System.out.println("events in service--" + event);
	        });
	        return eventsInService;
	    }

}
