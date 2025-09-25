package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events_table")
public class Event {
    
    @Id
    private int id;
    
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

	@Override
	public String toString() {
		return "Event [id=" + id + ", announcedDate=" + announcedDate + ", eventTitle=" + eventTitle + ", eventDate="
				+ eventDate + ", fromTime=" + fromTime + ", toTime=" + toTime + ", event_desc=" + event_desc
				+ ", eventLocation=" + eventLocation + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}

