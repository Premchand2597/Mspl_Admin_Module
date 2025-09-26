package com.example.mspl_connect.AdminEntity;

import org.springframework.web.multipart.MultipartFile;

public class EventConvertedDTO {

    private String event_title;
    private String event_date;
    private String from_time;
    private String to_time;
    private String event_desc;
    private String event_location;
    //private MultipartFile event_pic;
    //private MultipartFile event_converted_pic;
    //private MultipartFile event_video;
    
    //private String event_pic_base64;
    //private String event_video_base64;
    
    private MultipartFile event_converted_pic;
    private MultipartFile event_video;
	
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
	public MultipartFile getEvent_converted_pic() {
		return event_converted_pic;
	}
	public void setEvent_converted_pic(MultipartFile event_converted_pic) {
		this.event_converted_pic = event_converted_pic;
	}
	public MultipartFile getEvent_video() {
		return event_video;
	}
	public void setEvent_video(MultipartFile event_video) {
		this.event_video = event_video;
	}
	
}
