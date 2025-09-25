package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maternity_leave")
public class MaternityLeave {

	 public MaternityLeave(Long id, String empId, String leaveType, String email, String leaveStartDate,
			String leaveEndDate, String totalLeaveDays, String leaveUsed, String leaveRemaining, String applicationDate,
			String approvedstatus) {
		super();
		this.id = id;
		this.empId = empId;
		this.leaveType = leaveType;
		this.email = email;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.totalLeaveDays = totalLeaveDays;
		this.leaveUsed = leaveUsed;
		this.leaveRemaining = leaveRemaining;
		this.applicationDate = applicationDate;
		this.approvedstatus = approvedstatus;
	}

	public MaternityLeave() {
		super();
	}

	public MaternityLeave(Long id, String empId, String leaveType, String email, String leaveStartDate,
			String leaveEndDate, String totalLeaveDays, String leaveUsed, String leaveRemaining,
			String applicationDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.leaveType = leaveType;
		this.email = email;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.totalLeaveDays = totalLeaveDays;
		this.leaveUsed = leaveUsed;
		this.leaveRemaining = leaveRemaining;
		this.applicationDate = applicationDate;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String empId;

	    @Column(nullable = false)
	    private String leaveType;

	    @Column(nullable = false)
	    private String email;

	    @Column(name = "leave_start_date", nullable = false)
	    private String leaveStartDate;

	    @Column(name = "leave_end_date", nullable = false)
	    private String leaveEndDate;

	    @Column(name = "total_leave_days", nullable = false)
	    private String totalLeaveDays;

	    @Column(name = "leave_used", nullable = false)
	    private String leaveUsed;

	    @Column(name = "leave_remaining", nullable = false)
	    private String leaveRemaining;

	    @Column(name = "application_date", nullable = false)
	    private String applicationDate;

	    @Column(name = "approvedstatus")
		private String approvedstatus;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getLeaveType() {
			return leaveType;
		}

		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLeaveStartDate() {
			return leaveStartDate;
		}

		public void setLeaveStartDate(String leaveStartDate) {
			this.leaveStartDate = leaveStartDate;
		}

		public String getLeaveEndDate() {
			return leaveEndDate;
		}

		public void setLeaveEndDate(String leaveEndDate) {
			this.leaveEndDate = leaveEndDate;
		}

		public String getTotalLeaveDays() {
			return totalLeaveDays;
		}

		public void setTotalLeaveDays(String totalLeaveDays) {
			this.totalLeaveDays = totalLeaveDays;
		}

		public String getLeaveUsed() {
			return leaveUsed;
		}

		public void setLeaveUsed(String leaveUsed) {
			this.leaveUsed = leaveUsed;
		}

		public String getLeaveRemaining() {
			return leaveRemaining;
		}

		public void setLeaveRemaining(String leaveRemaining) {
			this.leaveRemaining = leaveRemaining;
		}

		public String getApplicationDate() {
			return applicationDate;
		}

		public void setApplicationDate(String applicationDate) {
			this.applicationDate = applicationDate;
		}

		@Override
		public String toString() {
			return "MaternityLeave [id=" + id + ", empId=" + empId + ", leaveType=" + leaveType + ", email=" + email
					+ ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", totalLeaveDays="
					+ totalLeaveDays + ", leaveUsed=" + leaveUsed + ", leaveRemaining=" + leaveRemaining
					+ ", applicationDate=" + applicationDate + "]";
		}

		public String getApprovedstatus() {
			return approvedstatus;
		}

		public void setApprovedstatus(String approvedstatus) {
			this.approvedstatus = approvedstatus;
		}
	
}
