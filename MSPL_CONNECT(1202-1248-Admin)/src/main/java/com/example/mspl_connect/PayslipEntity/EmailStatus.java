package com.example.mspl_connect.PayslipEntity;

public class EmailStatus {

	private String employeeName;
    private String emailId;
    private String status;
    private String timestamp;
    private String empId;
	
    public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "EmailStatus [employeeName=" + employeeName + ", emailId=" + emailId + ", status=" + status
				+ ", timestamp=" + timestamp + ", empId=" + empId + "]";
	}
	public EmailStatus(String employeeName, String emailId, String status, String timestamp, String empId) {
		super();
		this.employeeName = employeeName;
		this.emailId = emailId;
		this.status = status;
		this.timestamp = timestamp;
		this.empId = empId;
	}
	public EmailStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
}
