package com.example.mspl_connect.DispatchService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.DispatchEvent;
import com.example.mspl_connect.DispatchRepo.DispatchEventRepository;


@Service
public class DispatchEventService {
	
	 	@Autowired
	    private DispatchEventRepository eventRepository;

	    public DispatchEvent saveEvent(DispatchEvent event) {
	        return eventRepository.save(event);
	    }

	    public DispatchEventService(DispatchEventRepository eventRepository) {
	        this.eventRepository = eventRepository;
	    }

	    public List<DispatchEvent> getAllEvents() {
	        return eventRepository.findAll();
	    }

	    public List<DispatchEvent> getTodaysEvents() {
	        return eventRepository.findByScheduledDate();
	    }

	    public long getTotalEvents() {
	        return eventRepository.count();
	    }
	    
	    public long getTodayEventsCount() {
	        return eventRepository.getTodayEventCounts();
	    }
	    
	    public List<DispatchEvent> getEventsBasedOnDate(String date) {
	        return eventRepository.findEventsBasedOnDate(date);
	    }
}
