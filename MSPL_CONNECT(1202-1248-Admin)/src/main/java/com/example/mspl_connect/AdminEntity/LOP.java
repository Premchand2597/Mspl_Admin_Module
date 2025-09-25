package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lop")
public class LOP {

	public LOP() {
		super();
	}

	public LOP(int id, String empId, String leaveType, String email, String leaveStartDate, String leaveEndDate,
			String totalLeaveDays,String applicationDate,
			String approvedStatus) {
		super();
		this.id = id;
		this.empId = empId;
		this.leaveType = leaveType;
		this.email = email;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.totalLeaveDays = totalLeaveDays;
		
		this.applicationDate = applicationDate;
		this.approvedStatus = approvedStatus;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "email")
    private String email;

    @Column(name = "leave_start_date")
    private String leaveStartDate;

    @Column(name = "leave_end_date")
    private String leaveEndDate;

    @Column(name = "total_leave_days")
    private String totalLeaveDays;

  /*  @Column(name = "leave_used")
    private String leaveUsed;

    @Column(name = "leave_remaining")
    private String leaveRemaining;*/

    @Column(name = "application_date")
    private String applicationDate;

    @Column(name = "approvedStatus", columnDefinition = "varchar(50) default 'Pending'")
    private String approvedStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

/*	public String getLeaveUsed() {
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
	}*/

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

}
