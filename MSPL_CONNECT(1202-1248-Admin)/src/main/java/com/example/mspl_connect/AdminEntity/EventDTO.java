package com.example.mspl_connect.AdminEntity;

import org.springframework.web.multipart.MultipartFile;

public class EventDTO {

	private String announced_date;
    private String event_title;
    private String event_date;
    private String from_time;
    private String to_time;
    private String event_desc;
    private String event_location;
    private MultipartFile event_pic;
    private MultipartFile event_video;
    
    private String event_pic_base64;
    private String event_video_base64;
    
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
	public String getEvent_pic_base64() {
		return event_pic_base64;
	}
	public void setEvent_pic_base64(String event_pic_base64) {
		this.event_pic_base64 = event_pic_base64;
	}
	public String getEvent_video_base64() {
		return event_video_base64;
	}
	public void setEvent_video_base64(String event_video_base64) {
		this.event_video_base64 = event_video_base64;
	}
	public MultipartFile getEvent_pic() {
		return event_pic;
	}
	public void setEvent_pic(MultipartFile event_pic) {
		this.event_pic = event_pic;
	}
	public MultipartFile getEvent_video() {
		return event_video;
	}
	public void setEvent_video(MultipartFile event_video) {
		this.event_video = event_video;
	}
	
}
