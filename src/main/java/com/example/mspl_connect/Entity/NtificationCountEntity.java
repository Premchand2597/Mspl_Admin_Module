package com.example.mspl_connect.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class NtificationCountEntity {
    
    @Id
    private int id;  // Add an ID field if not present
    private int leaveRequestFlagCount;
    private int newPrjectValueCount;
	private int totalNotificationCount;
	private int appraisalCount;
	private int empAppraisalflag;
	private int adminAppraisalDuedate;
	private int announcementNotification;
	private int leaveRequestStatusChangeValue;
	private int toDoEvents;
	private int newFeatureNotification;
	private int newReleaseNoteNotification;
	private int quotationNotification;
	private int reviseQuotationCount;
	
	
	public int getReviseQuotationCount() {
		return reviseQuotationCount;
	}
	public void setReviseQuotationCount(int reviseQuotationCount) {
		this.reviseQuotationCount = reviseQuotationCount;
	}
	public int getNewReleaseNoteNotification() {
		return newReleaseNoteNotification;
	}
	public void setNewReleaseNoteNotification(int newReleaseNoteNotification) {
		this.newReleaseNoteNotification = newReleaseNoteNotification;
	}
	public int getNewFeatureNotification() {
		return newFeatureNotification;
	}
	public void setNewFeatureNotification(int newFeatureNotification) {
		this.newFeatureNotification = newFeatureNotification;
	}
	public int getToDoEvents() {
		return toDoEvents;
	}
	public void setToDoEvents(int toDoEvents) {
		this.toDoEvents = toDoEvents;
	}
	public int getLeaveRequestStatusChangeValue() {
		return leaveRequestStatusChangeValue;
	}
	public void setLeaveRequestStatusChangeValue(int leaveRequestStatusChangeValue) {
		this.leaveRequestStatusChangeValue = leaveRequestStatusChangeValue;
	}
	public int getAnnouncementNotification() {
		return announcementNotification;
	}
	public void setAnnouncementNotification(int announcementNotification) {
		this.announcementNotification = announcementNotification;
	}
	public int getAdminAppraisalDuedate() {
		return adminAppraisalDuedate;
	}
	public void setAdminAppraisalDuedate(int adminAppraisalDuedate) {
		this.adminAppraisalDuedate = adminAppraisalDuedate;
	}
	public int getEmpAppraisalflag() {
		return empAppraisalflag;
	}
	public void setEmpAppraisalflag(int empAppraisalflag) {
		this.empAppraisalflag = empAppraisalflag;
	}
	public int getAppraisalCount() {
		return appraisalCount;
	}
	public void setAppraisalCount(int appraisalCount) {
		this.appraisalCount = appraisalCount;
	}
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeaveRequestFlagCount() {
		return leaveRequestFlagCount;
	}
	public void setLeaveRequestFlagCount(int leaveRequestFlagCount) {
		this.leaveRequestFlagCount = leaveRequestFlagCount;
	}
	public int getTotalNotificationCount() {
		return totalNotificationCount;
	}
	public void setTotalNotificationCount(int totalNotificationCount) {
		this.totalNotificationCount = totalNotificationCount;
	}
	public int getNewPrjectValueCount() {
		return newPrjectValueCount;
	}
	public void setNewPrjectValueCount(int newPrjectValueCount) {
		this.newPrjectValueCount = newPrjectValueCount;
	}
	
	
	public int getQuotationNotification() {
		return quotationNotification;
	}
	public void setQuotationNotification(int quotationNotification) {
		this.quotationNotification = quotationNotification;
	} 
	
	@Override
	public String toString() {
		return "NtificationCountEntity [id=" + id + ", leaveRequestFlagCount=" + leaveRequestFlagCount
				+ ", newPrjectValueCount=" + newPrjectValueCount + ", totalNotificationCount=" + totalNotificationCount
				+ ", appraisalCount=" + appraisalCount + ", empAppraisalflag=" + empAppraisalflag
				+ ", adminAppraisalDuedate=" + adminAppraisalDuedate + ", announcementNotification="
				+ announcementNotification + ", leaveRequestStatusChangeValue=" + leaveRequestStatusChangeValue
				+ ", toDoEvents=" + toDoEvents + ", newFeatureNotification=" + newFeatureNotification
				+ ", newReleaseNoteNotification=" + newReleaseNoteNotification + ", quotationNotification="
				+ quotationNotification + ", getReviseQuotationCount=" + reviseQuotationCount + "]";
	}
	
	public NtificationCountEntity(int leaveRequestFlagCount , int totalNotificationCount,int newPrjectValueCount,
								  int appraisalCount,int empAppraisalflag,int adminAppraisalDuedate,
								  int announcementNotification,int leaveRequestStatusChangeValue,
								  int toDoEvents, int newFeatureNotification,int newReleaseNoteNotification,
								  int quotationNotification,int reviseQuotationCount) {
		
		super();  
		this.leaveRequestFlagCount = leaveRequestFlagCount;
		this.totalNotificationCount = totalNotificationCount;
		this.newPrjectValueCount = newPrjectValueCount;
		this.appraisalCount = appraisalCount;
		this.empAppraisalflag = empAppraisalflag;
		this.adminAppraisalDuedate = adminAppraisalDuedate;
		this.announcementNotification = announcementNotification;
		this.leaveRequestStatusChangeValue = leaveRequestStatusChangeValue;
		this.toDoEvents = toDoEvents;
		this.newFeatureNotification = newFeatureNotification;
		this.newReleaseNoteNotification = newReleaseNoteNotification;
		this.quotationNotification = quotationNotification;
		this.reviseQuotationCount = reviseQuotationCount;
	}
	
}
