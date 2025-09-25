package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "crm_task_schedule")
public class CRMTaskSchedule {
	  public CRMTaskSchedule(Long id, String companyName, String contactPerson, String phoneNumber, String contactMethod,
			String taskName, String assignee, LocalDateTime assignDate, LocalDateTime deadline, String description,
			String priority, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.contactMethod = contactMethod;
		this.taskName = taskName;
		this.assignee = assignee;
		this.assignDate = assignDate;
		this.deadline = deadline;
		this.description = description;
		this.priority = priority;
		this.createdAt = createdAt;
	}
	public CRMTaskSchedule() {
		super();
	}
	
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String companyName;
	    private String contactPerson;
	    private String phoneNumber;
	    private String contactMethod;
	    private String taskName;
	    private String assignee;
	    private LocalDateTime assignDate;
	    private LocalDateTime deadline;
	    
	    @Column(length = 2000)
	    private String description;
	    
	    private String priority;
	    private LocalDateTime createdAt;
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
		public String getContactMethod() {
			return contactMethod;
		}
		public void setContactMethod(String contactMethod) {
			this.contactMethod = contactMethod;
		}
		public String getTaskName() {
			return taskName;
		}
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
		public String getAssignee() {
			return assignee;
		}
		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
		
		public LocalDateTime getDeadline() {
			return deadline;
		}
		public void setDeadline(LocalDateTime deadline) {
			this.deadline = deadline;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getAssignDate() {
			return assignDate;
		}
		public void setAssignDate(LocalDateTime assignDate) {
			this.assignDate = assignDate;
		}
		
	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	    }
}
