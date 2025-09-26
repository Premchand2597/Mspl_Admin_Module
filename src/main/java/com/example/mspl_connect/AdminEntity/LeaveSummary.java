package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_summary")
public class LeaveSummary {
	  public LeaveSummary() {
		super();
	}
	public LeaveSummary(Long id, String empid, String financialYear, String leaveType, String month, Double added,
			Double remaining, Double totalAdded, Double totalUsed, String userEmail, String lastUpdated,
			String remarks) {
		super();
		this.id = id;
		this.empid = empid;
		this.financialYear = financialYear;
		this.leaveType = leaveType;
		this.month = month;
		this.added = added;
		this.remaining = remaining;
		this.totalAdded = totalAdded;
		this.totalUsed = totalUsed;
		this.userEmail = userEmail;
		this.lastUpdated = lastUpdated;
		this.remarks = remarks;
	}
	@Id
	    private Long id; // You can change the data type of `id` based on your table's primary key type

	 @Column(name = "empid")
	    private String empid;
	 
	    private String financialYear;
	    private String leaveType;
	    private String month;
	    private Double added;
	    private Double remaining;
	    private Double totalAdded;
	    private Double totalUsed;
	    private String userEmail;
	    private String lastUpdated;
	    private String remarks;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmpid() {
			return empid;
		}
		public void setEmpid(String empid) {
			this.empid = empid;
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
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public Double getAdded() {
			return added;
		}
		public void setAdded(Double added) {
			this.added = added;
		}
		public Double getRemaining() {
			return remaining;
		}
		public void setRemaining(Double remaining) {
			this.remaining = remaining;
		}
		public Double getTotalAdded() {
			return totalAdded;
		}
		public void setTotalAdded(Double totalAdded) {
			this.totalAdded = totalAdded;
		}
		public Double getTotalUsed() {
			return totalUsed;
		}
		public void setTotalUsed(Double totalUsed) {
			this.totalUsed = totalUsed;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getLastUpdated() {
			return lastUpdated;
		}
		public void setLastUpdated(String lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		@Override
		public String toString() {
			return "LeaveSummary [id=" + id + ", empid=" + empid + ", financialYear=" + financialYear + ", leaveType="
					+ leaveType + ", month=" + month + ", added=" + added + ", remaining=" + remaining + ", totalAdded="
					+ totalAdded + ", totalUsed=" + totalUsed + ", userEmail=" + userEmail + ", lastUpdated="
					+ lastUpdated + ", remarks=" + remarks + "]";
		}
}
