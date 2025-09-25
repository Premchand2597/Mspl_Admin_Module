package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_utilized")
public class LeaveUtilized {

	public LeaveUtilized(Long id, String userEmail, String financialYear, String leaveType, double totalAdded,
			double totalUsed, double remaining, String empId, LocalDate lastUpdated) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.financialYear = financialYear;
		this.leaveType = leaveType;
		this.totalAdded = totalAdded;
		this.totalUsed = totalUsed;
		this.remaining = remaining;
		this.empId = empId;
		this.lastUpdated = lastUpdated;
	}

	public LeaveUtilized() {
		super();
	}

	

	@Override
	public String toString() {
		return "LeaveUtilized [id=" + id + ", userEmail=" + userEmail + ", financialYear=" + financialYear
				+ ", leaveType=" + leaveType + ", totalAdded=" + totalAdded + ", totalUsed=" + totalUsed
				+ ", remaining=" + remaining + ", empId=" + empId + ", lastUpdated=" + lastUpdated + "]";
	}



	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "user_email", nullable = false)
	    private String userEmail;

	    @Column(name = "financial_year", nullable = false)
	    private String financialYear;

	    @Column(name = "leave_type", nullable = false)
	    private String leaveType;

	/*    @JsonProperty("totalAdded")
	    @Column(name = "total_added", nullable = false)
	    private int totalAdded;

	    @JsonProperty("totalUsed")
	    @Column(name = "total_used", nullable = false)
	    private int totalUsed;

	    @Column(name = "remaining", nullable = false)
	    private int remaining;*/

	    @JsonProperty("totalAdded")
	    @Column(name = "total_added", nullable = false)
	    private double totalAdded;

	    @JsonProperty("totalUsed")
	    @Column(name = "total_used", nullable = false)
	    private double totalUsed;

	    @Column(name = "remaining", nullable = false)
	    private double remaining;
	    
	    
	    @Column(name = "empid", nullable = false)
	    private String empId;
		
	    
	    @Column(name = "last_updated", nullable = false)
	    private LocalDate lastUpdated;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getFinancialYear() {
			return financialYear;
		}

		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}

		public String getLeaveType() {
			return leaveType;
		}

		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}

	
		public void setTotalAdded(int totalAdded) {
			this.totalAdded = totalAdded;
		}

		

		public void setTotalUsed(int totalUsed) {
			this.totalUsed = totalUsed;
		}

		

		public void setRemaining(int remaining) {
			this.remaining = remaining;
		}

		public LocalDate getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(LocalDate lastUpdated) {
			this.lastUpdated = lastUpdated;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public void setTotalAdded(double totalAdded) {
			this.totalAdded = totalAdded;
		}

		public void setTotalUsed(double totalUsed) {
			this.totalUsed = totalUsed;
		}

		public void setRemaining(double remaining) {
			this.remaining = remaining;
		}

		public double getTotalAdded() {
			return totalAdded;
		}

		public double getTotalUsed() {
			return totalUsed;
		}

		public double getRemaining() {
			return remaining;
		}

	
}
