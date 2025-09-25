package com.example.mspl_connect.AdminEntity;

public class EarnedLeaveDTO {

	 public EarnedLeaveDTO(double earnedLeave, Double elUsed, Double elRemaining, String empId) {
		super();
		this.earnedLeave = earnedLeave;
		this.elUsed = elUsed;
		this.elRemaining = elRemaining;
		this.empId = empId;
	}
	public EarnedLeaveDTO(Long id, double earnedLeave, String email, String monthYear, Double elUsed,
			Double elRemaining, String empId, String lastIncrementDate) {
		super();
		this.id = id;
		this.earnedLeave = earnedLeave;
		this.email = email;
		this.monthYear = monthYear;
		this.elUsed = elUsed;
		this.elRemaining = elRemaining;
		this.empId = empId;
		this.lastIncrementDate = lastIncrementDate;
	}
	private Long id;
	    private double earnedLeave;
	    private String email;
	    private String monthYear;
	    private Double elUsed;
	    private Double elRemaining;
	    private String empId;
	    private String lastIncrementDate;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public double getEarnedLeave() {
			return earnedLeave;
		}
		public void setEarnedLeave(double earnedLeave) {
			this.earnedLeave = earnedLeave;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMonthYear() {
			return monthYear;
		}
		public void setMonthYear(String monthYear) {
			this.monthYear = monthYear;
		}
		public Double getElUsed() {
			return elUsed;
		}
		public void setElUsed(Double elUsed) {
			this.elUsed = elUsed;
		}
		public Double getElRemaining() {
			return elRemaining;
		}
		public void setElRemaining(Double elRemaining) {
			this.elRemaining = elRemaining;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getLastIncrementDate() {
			return lastIncrementDate;
		}
		public void setLastIncrementDate(String lastIncrementDate) {
			this.lastIncrementDate = lastIncrementDate;
		}
	
}
