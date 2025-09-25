package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_balance_snapshot")
public class LeaveBalanceSnapshot {
	 public LeaveBalanceSnapshot() {
		super();
	}
		public LeaveBalanceSnapshot(Long id, Integer requestId, String empId, String employeeEmail, String leaveType,
			double added, double used, double remaining, String status, String financialYear, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.empId = empId;
		this.employeeEmail = employeeEmail;
		this.leaveType = leaveType;
		this.added = added;
		this.used = used;
		this.remaining = remaining;
		this.status = status;
		this.financialYear = financialYear;
		this.createdAt = createdAt;
	}
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Integer requestId;
	    private String empId;
	    private String employeeEmail;
	    private String leaveType;

	    private double added;
	    private double used;
	    private double remaining;

	    private String status; // Approved/Rejected
	    private String financialYear;
	    private LocalDateTime createdAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getRequestId() {
			return requestId;
		}
		public void setRequestId(Integer requestId) {
			this.requestId = requestId;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEmployeeEmail() {
			return employeeEmail;
		}
		public void setEmployeeEmail(String employeeEmail) {
			this.employeeEmail = employeeEmail;
		}
		public String getLeaveType() {
			return leaveType;
		}
		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}
	
		public double getAdded() {
			return added;
		}
		public void setAdded(double added) {
			this.added = added;
		}
		public double getUsed() {
			return used;
		}
		public void setUsed(double used) {
			this.used = used;
		}
		public double getRemaining() {
			return remaining;
		}
		public void setRemaining(double remaining) {
			this.remaining = remaining;
		}
		public void setRemaining(int remaining) {
			this.remaining = remaining;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getFinancialYear() {
			return financialYear;
		}
		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

}
