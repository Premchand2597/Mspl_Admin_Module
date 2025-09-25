package com.example.mspl_connect.AdminEntity;

import java.util.Arrays;
import java.util.Base64;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "event_converted_table")
public class EventConverted_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String event_title;
	private String event_date;
	private String from_time;
	private String to_time;
	private String event_desc;
	private String event_location;
	
	//@Lob
	//private byte[] event_pic;
	
	/*@Lob
    private byte[] event_converted_pic;
	    
    @Lob
    private byte[] event_video;*/
	
	private String event_converted_pic;
    
    private String event_video;
    
    /*@Transient
    private String base64EventPic;

    @Transient
    private String base64EventVideo;*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEvent_converted_pic() {
		return event_converted_pic;
	}
	public void setEvent_converted_pic(String event_converted_pic) {
		this.event_converted_pic = event_converted_pic;
	}
	public String getEvent_video() {
		return event_video;
	}
	public void setEvent_video(String event_video) {
		this.event_video = event_video;
	}
	@Override
	public String toString() {
		return "EventConverted_Entity [id=" + id + ", event_title=" + event_title + ", event_date=" + event_date
				+ ", from_time=" + from_time + ", to_time=" + to_time + ", event_desc=" + event_desc
				+ ", event_location=" + event_location + ", event_converted_pic=" + event_converted_pic
				+ ", event_video=" + event_video + "]";
	}
	
	// Base64 encoding for the image
    /*public String getBase64EventPic() {
        if (event_converted_pic != null && event_converted_pic.length > 0) {
            return Base64.getEncoder().encodeToString(event_converted_pic);
        }
        return null;  // Return null if no image is available
    }

    // Base64 encoding for the video
    public String getBase64EventVideo() {
        if (event_video != null && event_video.length > 0) {
            return Base64.getEncoder().encodeToString(event_video);
        }
        return null;  // Return null if no video is available
    }*/
}
