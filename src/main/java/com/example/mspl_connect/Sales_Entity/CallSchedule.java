package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "call_schedule")
public class CallSchedule {
	  public CallSchedule(Long id, String companyName, String contactPerson, String phoneNumber, LocalDateTime fromTime,
			LocalDateTime toTime, String contactMethod, String notes, String description, Integer callStatus,
			String feedback, String nextAction, LocalDateTime nextSchedule, LocalDateTime callMadeTime,
			String taskDelayReason) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.contactMethod = contactMethod;
		this.notes = notes;
		this.description = description;
		this.callStatus = callStatus;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.nextSchedule = nextSchedule;
		this.callMadeTime = callMadeTime;
		this.taskDelayReason = taskDelayReason;
	}
	public CallSchedule(Long id, String companyName, String contactPerson, String phoneNumber, LocalDateTime fromTime,
			LocalDateTime toTime, String contactMethod, String notes, String description, Integer callStatus,
			String feedback, String nextAction, LocalDateTime nextSchedule, LocalDateTime callMadeTime) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.contactMethod = contactMethod;
		this.notes = notes;
		this.description = description;
		this.callStatus = callStatus;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.nextSchedule = nextSchedule;
		this.callMadeTime = callMadeTime;
	}
	public CallSchedule(Long id, String companyName, String contactPerson, String phoneNumber, LocalDateTime fromTime,
			LocalDateTime toTime, String contactMethod, String notes, String description, Integer callStatus,
			String feedback, String nextAction, LocalDateTime callMadeTime) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.contactMethod = contactMethod;
		this.notes = notes;
		this.description = description;
		this.callStatus = callStatus;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.callMadeTime = callMadeTime;
	}
	public CallSchedule(Long id, String companyName, String contactPerson, String phoneNumber, LocalDateTime fromTime,
			LocalDateTime toTime, String contactMethod, String notes, String description, Integer callStatus) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.contactMethod = contactMethod;
		this.notes = notes;
		this.description = description;
		this.callStatus = callStatus;
	}
	public CallSchedule() {
		super();
	}
	public CallSchedule(Long id, String companyName, String contactPerson, String phoneNumber, LocalDateTime fromTime,
			LocalDateTime toTime, String contactMethod, String notes, String description) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.contactMethod = contactMethod;
		this.notes = notes;
		this.description = description;
	}
	
	 public CallSchedule(String companyName, String contactPerson, String phoneNumber, 
             LocalDateTime fromTime, LocalDateTime toTime, String contactMethod, 
             String description, String nextAction,LocalDateTime nextSchedule) {
this.companyName = companyName;
this.contactPerson = contactPerson;
this.phoneNumber = phoneNumber;
this.fromTime = fromTime;
this.toTime = toTime;
this.contactMethod = contactMethod;
this.description = description;
this.nextAction = nextAction;
this.nextSchedule = nextSchedule;
}
	 
	 

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String companyName;
	    private String contactPerson;
	    private String phoneNumber;
	    private LocalDateTime fromTime;
	    private LocalDateTime toTime;
	    private String contactMethod;
	    private String notes;
	    private String description;
	    // New column to track call status (1 = Call Made, 0 = Call Not Made)
	    private Integer callStatus = 0;

	  
	    private String feedback;

	  
	    private String nextAction;
	    private LocalDateTime nextSchedule; // New field

	    private LocalDateTime callMadeTime;
	    
	    
	    @Column(name = "task_delay_reason")
	    private String taskDelayReason;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getContactPerson() {
			return contactPerson;
		}
		public void setContactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public LocalDateTime getFromTime() {
			return fromTime;
		}
		public void setFromTime(LocalDateTime fromTime) {
			this.fromTime = fromTime;
		}
		public LocalDateTime getToTime() {
			return toTime;
		}
		public void setToTime(LocalDateTime toTime) {
			this.toTime = toTime;
		}
		public String getContactMethod() {
			return contactMethod;
		}
		public void setContactMethod(String contactMethod) {
			this.contactMethod = contactMethod;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getCallStatus() {
			return callStatus;
		}
		public void setCallStatus(Integer callStatus) {
			this.callStatus = callStatus;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		public String getNextAction() {
			return nextAction;
		}
		public void setNextAction(String nextAction) {
			this.nextAction = nextAction;
		}
		public LocalDateTime getCallMadeTime() {
			return callMadeTime;
		}
		public void setCallMadeTime(LocalDateTime callMadeTime) {
			this.callMadeTime = callMadeTime;
		}
		public LocalDateTime getNextSchedule() {
			return nextSchedule;
		}
		public void setNextSchedule(LocalDateTime nextSchedule) {
			this.nextSchedule = nextSchedule;
		}
		public String getTaskDelayReason() {
			return taskDelayReason;
		}
		public void setTaskDelayReason(String taskDelayReason) {
			this.taskDelayReason = taskDelayReason;
		}
}
