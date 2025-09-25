package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events_table")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @Column(name="announced_date")
    private String announcedDate;
    
    @Column(name="event_title")
    private String eventTitle;
    
    @Column(name="event_date")
    private String eventDate;
    
    @Column(name="from_time")
    private String fromTime;
    
    @Column(name="to_time")
    private String toTime;
    
    @Column(name="eventDesc")
    private String event_desc;

    @Column(name="event_location")
    private String eventLocation;
    
    private String announcement_converted_flag;
	private String new_annuncement_flag;
	
	private String event_pic;
    
	@Override
	public String toString() {
		return "Event [id=" + id + ", announcedDate=" + announcedDate + ", eventTitle=" + eventTitle + ", eventDate="
				+ eventDate + ", fromTime=" + fromTime + ", toTime=" + toTime + ", event_desc=" + event_desc
				+ ", eventLocation=" + eventLocation + ", announcement_converted_flag=" + announcement_converted_flag
				+ ", new_annuncement_flag=" + new_annuncement_flag + ", event_pic=" + event_pic + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnnouncedDate() {
		return announcedDate;
	}

	public void setAnnouncedDate(String announcedDate) {
		this.announcedDate = announcedDate;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getEvent_desc() {
		return event_desc;
	}

	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getAnnouncement_converted_flag() {
		return announcement_converted_flag;
	}

	public void setAnnouncement_converted_flag(String announcement_converted_flag) {
		this.announcement_converted_flag = announcement_converted_flag;
	}

	public String getNew_annuncement_flag() {
		return new_annuncement_flag;
	}

	public void setNew_annuncement_flag(String new_annuncement_flag) {
		this.new_annuncement_flag = new_annuncement_flag;
	}

	public String getEvent_pic() {
		return event_pic;
	}

	public void setEvent_pic(String event_pic) {
		this.event_pic = event_pic;
	}
}

