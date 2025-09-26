package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_usage_history")
public class LeaveUsageHistory {
	  public LeaveUsageHistory() {
		super();
	}



	public LeaveUsageHistory(Long id, String employeeEmail, String leaveType, String financialYear, LocalDate fromDate,
			LocalDate toDate, String reason, double previousUsed, double newUsed, double previousRemaining,
			double newRemaining, Long leaveApplicationId, String empId, LocalDateTime logDate) {
		super();
		this.id = id;
		this.employeeEmail = employeeEmail;
		this.leaveType = leaveType;
		this.financialYear = financialYear;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
		this.previousUsed = previousUsed;
		this.newUsed = newUsed;
		this.previousRemaining = previousRemaining;
		this.newRemaining = newRemaining;
		this.leaveApplicationId = leaveApplicationId;
		this.empId = empId;
		this.logDate = logDate;
	}



	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String employeeEmail;
	    private String leaveType;
	    private String financialYear;

	    private LocalDate fromDate;
	    private LocalDate toDate;
	    private String reason;

	    private double previousUsed;
	    public LeaveUsageHistory(Long id, String employeeEmail, String leaveType, String financialYear,
				LocalDate fromDate, LocalDate toDate, String reason, double previousUsed, double newUsed,
				double previousRemaining, double newRemaining, Long leaveApplicationId, String empId,
				LocalDateTime logDate, String actionType) {
			super();
			this.id = id;
			this.employeeEmail = employeeEmail;
			this.leaveType = leaveType;
			this.financialYear = financialYear;
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.reason = reason;
			this.previousUsed = previousUsed;
			this.newUsed = newUsed;
			this.previousRemaining = previousRemaining;
			this.newRemaining = newRemaining;
			this.leaveApplicationId = leaveApplicationId;
			this.empId = empId;
			this.logDate = logDate;
			this.actionType = actionType;
		}



		private double newUsed;
	    private double previousRemaining;
	    private double newRemaining;

	    // ✅ Add leaveApplicationId reference
	    private Long leaveApplicationId;

	    public String getActionType() {
			return actionType;
		}



		public void setActionType(String actionType) {
			this.actionType = actionType;
		}



		// ✅ Add empId
	    private String empId;

	    // ✅ Change LocalDate → LocalDateTime
	    private LocalDateTime logDate;
	    // ✅ NEW COLUMN: to track action
	    private String actionType; // values: "REDUCE", "REVOKE"
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getFinancialYear() {
			return financialYear;
		}

		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}

		public LocalDate getFromDate() {
			return fromDate;
		}

		public void setFromDate(LocalDate fromDate) {
			this.fromDate = fromDate;
		}

		public LocalDate getToDate() {
			return toDate;
		}

		public void setToDate(LocalDate toDate) {
			this.toDate = toDate;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public double getPreviousUsed() {
			return previousUsed;
		}

		public void setPreviousUsed(double previousUsed) {
			this.previousUsed = previousUsed;
		}

		public double getNewUsed() {
			return newUsed;
		}

		public void setNewUsed(double newUsed) {
			this.newUsed = newUsed;
		}

		public Long getLeaveApplicationId() {
			return leaveApplicationId;
		}

		public void setLeaveApplicationId(Long leaveApplicationId) {
			this.leaveApplicationId = leaveApplicationId;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public LocalDateTime getLogDate() {
			return logDate;
		}

		public void setLogDate(LocalDateTime logDate) {
			this.logDate = logDate;
		}

		public double getPreviousRemaining() {
			return previousRemaining;
		}

		public void setPreviousRemaining(double previousRemaining) {
			this.previousRemaining = previousRemaining;
		}

		public double getNewRemaining() {
			return newRemaining;
		}

		public void setNewRemaining(double newRemaining) {
			this.newRemaining = newRemaining;
		}

}
