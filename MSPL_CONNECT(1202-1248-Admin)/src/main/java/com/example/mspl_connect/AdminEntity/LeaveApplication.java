package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LeaveApplication {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
        private String empid;
        private String approvedstatus;
        private String approved_by;
        private Integer department;
        
        private String employee_name;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String from_date;
        
        @Column(name="leave_type")
        private String leaveType;
        private String reason;
        private String reporting_to;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String to_date;
        
        private String employeeEmail;
        private Boolean processed;
        private String rejection_reason;
        private Integer way;
        private String previous_status;
        private int notification_status;
        private String timestamp;
        private Integer status_count;
        private Boolean is_date_modified;
        
        private String old_from_date;
        private String old_to_date;
        
        private String requested_date;
        
        private Integer edit_flag;
        
        @Column(name = "approving_authority")
    	private String approvingAuthority;
        
  

		public Boolean getIs_date_modified() {
			return is_date_modified;
		}
		 		
		public String getApprovingAuthority() {
			return approvingAuthority;
		}

		public void setApprovingAuthority(String approvingAuthority) {
			this.approvingAuthority = approvingAuthority;
		}

		public Integer getEdit_flag() {
			return edit_flag;
		}
		public void setEdit_flag(Integer edit_flag) {
			this.edit_flag = edit_flag;
		}
		
		@Column(name= "leave_duration")
	   	 private String leaveDuration;
	
	   	@Column(name="remarks")
	   	private String remarks;
        
        
	   	public String getRequested_date() {
			return requested_date;
		}
		public void setRequested_date(String requested_date) {
			this.requested_date = requested_date;
		}
        
		public String getLeaveDuration() {
			return leaveDuration;
		}
		public void setLeaveDuration(String leaveDuration) {
			this.leaveDuration = leaveDuration;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getOld_from_date() {
			return old_from_date;
		}
		public void setOld_from_date(String old_from_date) {
			this.old_from_date = old_from_date;
		}
		public String getOld_to_date() {
			return old_to_date;
		}
		public void setOld_to_date(String old_to_date) {
			this.old_to_date = old_to_date;
		}
		public Boolean isIs_date_modified() {
			return is_date_modified;
		}
		public void setIs_date_modified(Boolean is_date_modified) {
			this.is_date_modified = is_date_modified;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmpid() {
			return empid;
		}
		public void setEmpid(String empid) {
			this.empid = empid;
		}
		public String getApprovedstatus() {
			return approvedstatus;
		}
		public void setApprovedstatus(String approvedstatus) {
			this.approvedstatus = approvedstatus;
		}
		public String getApproved_by() {
			return approved_by;
		}
		public void setApproved_by(String approved_by) {
			this.approved_by = approved_by;
		}
		public Integer getDepartment() {
			return department;
		}
		public void setDepartment(Integer department) {
			this.department = department;
		}
		public String getEmployee_name() {
			return employee_name;
		}
		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}
		public String getFrom_date() {
			return from_date;
		}
		public void setFrom_date(String from_date) {
			this.from_date = from_date;
		}
		
		public String getLeaveType() {
			return leaveType;
		}
		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getReporting_to() {
			return reporting_to;
		}
		public void setReporting_to(String reporting_to) {
			this.reporting_to = reporting_to;
		}
		public String getTo_date() {
			return to_date;
		}
		public void setTo_date(String to_date) {
			this.to_date = to_date;
		}
		
		public Boolean getProcessed() {
			return processed;
		}
		public void setProcessed(Boolean processed) {
			this.processed = processed;
		}
		public String getRejection_reason() {
			return rejection_reason;
		}
		public void setRejection_reason(String rejection_reason) {
			this.rejection_reason = rejection_reason;
		}
		public Integer getWay() {
			return way;
		}
		public void setWay(Integer way) {
			this.way = way;
		}
		public String getPrevious_status() {
			return previous_status;
		}
		public void setPrevious_status(String previous_status) {
			this.previous_status = previous_status;
		}
		public int getNotification_status() {
			return notification_status;
		}
		public void setNotification_status(int notification_status) {
			this.notification_status = notification_status;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public Integer getStatus_count() {
			return status_count;
		}
		public void setStatus_count(Integer status_count) {
			this.status_count = status_count;
		}
		
		public String getEmployeeEmail() {
			return employeeEmail;
		}
		public void setEmployeeEmail(String employeeEmail) {
			this.employeeEmail = employeeEmail;
		}
		
		
		 public String getFinancialYear() {
		        // Parse fromDate string to LocalDate
		        LocalDate fromDateParsed = LocalDate.parse(from_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		        
		        int year = fromDateParsed.getYear();
		        if (fromDateParsed.getMonthValue() >= 4) { // Financial year starts in April
		            return year + "-" + (year + 1);
		        } else {
		            return (year - 1) + "-" + year;
		        }
		    }
	      
	        public LeaveApplication(int id, String empid, String approvedstatus, String approved_by, Integer department,
					String employee_name, String from_date, String leaveType, String reason, String reporting_to,
					String to_date, String employeeEmail, Boolean processed, String rejection_reason, Integer way,
					String previous_status, int notification_status, String timestamp, Integer status_count,
					Boolean is_date_modified, String old_from_date, String old_to_date, String requested_date,
					Integer edit_flag, String approvingAuthority, String leaveDuration, String remarks) {
				super();
				this.id = id;
				this.empid = empid;
				this.approvedstatus = approvedstatus;
				this.approved_by = approved_by;
				this.department = department;
				this.employee_name = employee_name;
				this.from_date = from_date;
				this.leaveType = leaveType;
				this.reason = reason;
				this.reporting_to = reporting_to;
				this.to_date = to_date;
				this.employeeEmail = employeeEmail;
				this.processed = processed;
				this.rejection_reason = rejection_reason;
				this.way = way;
				this.previous_status = previous_status;
				this.notification_status = notification_status;
				this.timestamp = timestamp;
				this.status_count = status_count;
				this.is_date_modified = is_date_modified;
				this.old_from_date = old_from_date;
				this.old_to_date = old_to_date;
				this.requested_date = requested_date;
				this.edit_flag = edit_flag;
				this.approvingAuthority = approvingAuthority;
				this.leaveDuration = leaveDuration;
				this.remarks = remarks;
			}
		    @Override
			public String toString() {
				return "LeaveApplication [id=" + id + ", empid=" + empid + ", approvedstatus=" + approvedstatus
						+ ", approved_by=" + approved_by + ", department=" + department + ", employee_name="
						+ employee_name + ", from_date=" + from_date + ", leaveType=" + leaveType + ", reason=" + reason
						+ ", reporting_to=" + reporting_to + ", to_date=" + to_date + ", employeeEmail=" + employeeEmail
						+ ", processed=" + processed + ", rejection_reason=" + rejection_reason + ", way=" + way
						+ ", previous_status=" + previous_status + ", notification_status=" + notification_status
						+ ", timestamp=" + timestamp + ", status_count=" + status_count + ", is_date_modified="
						+ is_date_modified + ", old_from_date=" + old_from_date + ", old_to_date=" + old_to_date
						+ ", requested_date=" + requested_date + ", edit_flag=" + edit_flag + ", approvingAuthority="
						+ approvingAuthority + ", leaveDuration=" + leaveDuration + ", remarks=" + remarks + "]";
			}

			public LeaveApplication() {
				super();
				// TODO Auto-generated constructor stub
			}
		    
		    
}
