package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LeaveApplicationWithProfile {
    
    private int id;
    private String empid;
    private String approvedStatus;
    private String approvedBy;
    private Integer department;
    private String employeeName;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fromDate;
    
    private String leaveType;
    private String reason;
    private String reportingTo;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String toDate;
    
    private String employeeEmail;
    private Boolean processed;
    private String rejectionReason;
    private Integer way;
    private String previousStatus;
    private int notificationStatus;
    private String timestamp;
    private Integer statusCount;
    private Boolean isDateModified;
    private String oldFromDate;
    private String oldToDate;
    private String requestedDate;
    private Integer editFlag;
    private String profilePicPath;
    private String leaveDuration;
    private String remarks;
    
    public String getFinancialYear() {
        LocalDate fromDateParsed = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int year = fromDateParsed.getYear();
        return (fromDateParsed.getMonthValue() >= 4) ? year + "-" + (year + 1) : (year - 1) + "-" + year;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmpid() { return empid; }
    public void setEmpid(String empid) { this.empid = empid; }
    public String getApprovedStatus() { return approvedStatus; }
    public void setApprovedStatus(String approvedStatus) { this.approvedStatus = approvedStatus; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public Integer getDepartment() { return department; }
    public void setDepartment(Integer department) { this.department = department; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }
    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getReportingTo() { return reportingTo; }
    public void setReportingTo(String reportingTo) { this.reportingTo = reportingTo; }
    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
    public Boolean getProcessed() { return processed; }
    public void setProcessed(Boolean processed) { this.processed = processed; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public Integer getWay() { return way; }
    public void setWay(Integer way) { this.way = way; }
    public String getPreviousStatus() { return previousStatus; }
    public void setPreviousStatus(String previousStatus) { this.previousStatus = previousStatus; }
    public int getNotificationStatus() { return notificationStatus; }
    public void setNotificationStatus(int notificationStatus) { this.notificationStatus = notificationStatus; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public Integer getStatusCount() { return statusCount; }
    public void setStatusCount(Integer statusCount) { this.statusCount = statusCount; }
    public Boolean getIsDateModified() { return isDateModified; }
    public void setIsDateModified(Boolean isDateModified) { this.isDateModified = isDateModified; }
    public String getOldFromDate() { return oldFromDate; }
    public void setOldFromDate(String oldFromDate) { this.oldFromDate = oldFromDate; }
    public String getOldToDate() { return oldToDate; }
    public void setOldToDate(String oldToDate) { this.oldToDate = oldToDate; }
    public String getRequestedDate() { return requestedDate; }
    public void setRequestedDate(String requestedDate) { this.requestedDate = requestedDate; }
    public Integer getEditFlag() { return editFlag; }
    public void setEditFlag(Integer editFlag) { this.editFlag = editFlag; }
    public String getProfilePicPath() { return profilePicPath; }
    public void setProfilePicPath(String profilePicPath) { this.profilePicPath = profilePicPath; }
    public String getLeaveDuration() { return leaveDuration; }
    public void setLeaveDuration(String leaveDuration) { this.leaveDuration = leaveDuration; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
