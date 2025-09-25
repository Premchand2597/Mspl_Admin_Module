package com.example.mspl_connect.AdminService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EventConverted_Entity;
import com.example.mspl_connect.AdminRepo.EventsConverted_Repo;

import jakarta.transaction.Transactional;

@Service
public class EventConverted_Service {

	@Autowired
	private EventsConverted_Repo events_Repo;
	
	//@Autowired
	//private EventCardDetails_Repo eventCardDetails_Repo;
	
	@Transactional
	 public String save(EventConverted_Entity events) {
		events_Repo.save(events);
		return "success";
	 }
	
	public Optional<EventConverted_Entity> getEventById(int id) {
        return  events_Repo.findById(id);
    }

    public List<EventConverted_Entity> getAllEvents() {
        return  events_Repo.fetchAllEventsDataBasedRecentlyCompleted();
    }
	
	/*public List<EventsWithStatus_Entity> getEventDetailsForCard(){
		return eventCardDetails_Repo.fetchEventDetailsOnCard();
	}
	
	public Event_Entity getEventsDataById(String id) {
		return events_Repo.fetchEventsDataBasedOnId(id);
	}*/
}
