package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.Event_Entity;
import com.example.mspl_connect.AdminEntity.EventsWithStatus_Entity;
import com.example.mspl_connect.AdminRepo.AnnnouncementNotificationRepo;
import com.example.mspl_connect.AdminRepo.EventCardDetails_Repo;
import com.example.mspl_connect.AdminRepo.Events_Repo;

import jakarta.transaction.Transactional;

@Service
public class Event_Service {

	@Autowired
	private Events_Repo events_Repo;
	
	@Autowired
	private EventCardDetails_Repo eventCardDetails_Repo;
	
	
	
	/*@Transactional
	 public String save(Event_Entity events) {
		events_Repo.save(events);
		return "success";
	 }*/
	
	public List<EventsWithStatus_Entity> getEventDetailsForCard(){
		return eventCardDetails_Repo.fetchEventDetailsOnCard();
	}
	
	public Event_Entity getEventsDataById(String id) {
		return events_Repo.fetchEventsDataBasedOnId(id);
	}
	
	 /*@Transactional
	 public void updateAnnouncementConvertedToEventsFlagValue(String announcement_id) {
		events_Repo.updateAnnouncementConvertedToEventsFlag(announcement_id);
	 }*/
	
	 
}
