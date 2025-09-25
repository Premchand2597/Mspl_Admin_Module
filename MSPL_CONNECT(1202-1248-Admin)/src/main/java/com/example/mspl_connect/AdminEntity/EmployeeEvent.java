package com.example.mspl_connect.AdminEntity;

import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "event_converted_table")
public class EmployeeEvent {

	 

	public EmployeeEvent(int id, String eventTitle, String eventDate, String fromTime, String toTime, String eventDesc,
			String eventLocation, String eventVideo, String eventConvertedPic, String base64EventPic,
			String base64EventVideo) {
		super();
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.eventDesc = eventDesc;
		this.eventLocation = eventLocation;
		this.eventVideo = eventVideo;
		this.eventConvertedPic = eventConvertedPic;
		this.base64EventPic = base64EventPic;
		this.base64EventVideo = base64EventVideo;
	}

	public EmployeeEvent() {
		super();
	}

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "event_title", length = 45)
	    private String eventTitle;

	    @Column(name = "event_date", length = 45)
	    private String eventDate;

	    @Column(name = "from_time", length = 45)
	    private String fromTime;

	    @Column(name = "to_time", length = 45)
	    private String toTime;

	    @Column(name = "event_desc", length = 1000)
	    private String eventDesc;

	    @Column(name = "event_location", length = 1000)
	    private String eventLocation;

	  /*  @Lob
	    @Column(name = "event_pic")
	    private String eventPic;*/

	    @Lob
	    @Column(name = "event_video")
	    private String eventVideo;

	    @Lob
	    @Column(name = "event_converted_pic")
	    private String eventConvertedPic;
	    
	    // Transient fields for Base64 conversion
	    @Transient
	    private String base64EventPic;

	    @Transient
	    private String base64EventVideo;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public String getEventDesc() {
			return eventDesc;
		}

		public void setEventDesc(String eventDesc) {
			this.eventDesc = eventDesc;
		}

		public String getEventLocation() {
			return eventLocation;
		}

		public void setEventLocation(String eventLocation) {
			this.eventLocation = eventLocation;
		}

		public String getEventVideo() {
			return eventVideo;
		}

		public void setEventVideo(String eventVideo) {
			this.eventVideo = eventVideo;
		}

		public String getEventConvertedPic() {
			return eventConvertedPic;
		}

		public void setEventConvertedPic(String eventConvertedPic) {
			this.eventConvertedPic = eventConvertedPic;
		}

		public void setBase64EventPic(String base64EventPic) {
			this.base64EventPic = base64EventPic;
		}

		public void setBase64EventVideo(String base64EventVideo) {
			this.base64EventVideo = base64EventVideo;
		}

		@Override
		public String toString() {
			return "EmployeeEvent [id=" + id + ", eventTitle=" + eventTitle + ", eventDate=" + eventDate + ", fromTime="
					+ fromTime + ", toTime=" + toTime + ", eventDesc=" + eventDesc + ", eventLocation=" + eventLocation
					+ ", eventVideo=" + eventVideo + ", eventConvertedPic=" + eventConvertedPic + ", base64EventPic="
					+ base64EventPic + ", base64EventVideo=" + base64EventVideo + "]";
		}
		
}
