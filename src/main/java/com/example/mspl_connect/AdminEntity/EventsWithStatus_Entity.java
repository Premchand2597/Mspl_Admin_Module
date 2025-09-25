package com.example.mspl_connect.AdminEntity;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class EventsWithStatus_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String announced_date;
	private String event_title;
	private String event_date;
	private String from_time;
	private String to_time;
	private String event_desc;
	private String event_location;
	private String event_status;
	private String announcement_converted_flag;
	
	//@Lob
	//private byte[] event_pic;
	
	private String event_pic;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getAnnounced_date() {
		return announced_date;
	}
	public void setAnnounced_date(String announced_date) {
		this.announced_date = announced_date;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getTo_time() {
		return to_time;
	}
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	public String getEvent_desc() {
		return event_desc;
	}
	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}
	public String getEvent_location() {
		return event_location;
	}
	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}
	public String getEvent_status() {
		return event_status;
	}
	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}
	public String getAnnouncement_converted_flag() {
		return announcement_converted_flag;
	}

	public void setAnnouncement_converted_flag(String announcement_converted_flag) {
		this.announcement_converted_flag = announcement_converted_flag;
	}

	public String getEvent_pic() {
		return event_pic;
	}

	public void setEvent_pic(String event_pic) {
		this.event_pic = event_pic;
	}

	@Override
	public String toString() {
		return "EventsWithStatus_Entity [id=" + id + ", announced_date=" + announced_date + ", event_title="
				+ event_title + ", event_date=" + event_date + ", from_time=" + from_time + ", to_time=" + to_time
				+ ", event_desc=" + event_desc + ", event_location=" + event_location + ", event_status=" + event_status
				+ ", announcement_converted_flag=" + announcement_converted_flag + ", event_pic=" + event_pic + "]";
	}
	
}