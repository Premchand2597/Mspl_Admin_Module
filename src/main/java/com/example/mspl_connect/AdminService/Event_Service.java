package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AnnouncementNotificationDetailsInsert_Entity;
import com.example.mspl_connect.AdminEntity.Event_Entity;
import com.example.mspl_connect.AdminEntity.EventsWithStatus_Entity;
import com.example.mspl_connect.AdminRepo.AddEvents_Repo;
import com.example.mspl_connect.AdminRepo.AnnnouncementNotificationRepo;
import com.example.mspl_connect.AdminRepo.AnnouncementNotificationDetailsInsert_Repo;
import com.example.mspl_connect.AdminRepo.EventCardDetails_Repo;
import com.example.mspl_connect.AdminRepo.Events_Repo;
import com.example.mspl_connect.Entity.Event;

import jakarta.transaction.Transactional;

@Service
public class Event_Service {

	@Autowired
	private Events_Repo events_Repo;
	
	@Autowired
	private AddEvents_Repo addEvents_Repo;
	
	@Autowired
	private EventCardDetails_Repo eventCardDetails_Repo;
	
	@Autowired
	private AnnouncementNotificationDetailsInsert_Repo announcementNotificationDetailsInsert_Repo;
	
	 public String save(Event events) {
		addEvents_Repo.save(events);
		return "success";
	 }
	 
	 @Transactional
	 public boolean updateAnnouncementDetailsDataByUsingId(String id, String announcementTitle, String date, String fromTime, String toTime, String location, String announcementPicPath) {
		int rowsUpdated = addEvents_Repo.updateAnnouncementDetailsData(id, announcementTitle, date, fromTime, toTime, location, announcementPicPath);
		if (rowsUpdated > 0) {
            return true;
        }else {
        	return false;
        }
	 }
	
	public List<EventsWithStatus_Entity> getEventDetailsForCard(){
		return eventCardDetails_Repo.fetchEventDetailsOnCard();
	}
	
	public Event_Entity getEventsDataById(String id) {
		return events_Repo.fetchEventsDataBasedOnId(id);
	}
	
	@Transactional
	 public void saveAnnouncementDetailsForNotifications(AnnouncementNotificationDetailsInsert_Entity announce) {
		announcementNotificationDetailsInsert_Repo.save(announce);
	 }
	
	@Transactional
	 public void updateAnnouncementConvertedToEventsFlagValue(String announcement_id) {
		events_Repo.updateAnnouncementConvertedToEventsFlag(announcement_id);
	 }
}
