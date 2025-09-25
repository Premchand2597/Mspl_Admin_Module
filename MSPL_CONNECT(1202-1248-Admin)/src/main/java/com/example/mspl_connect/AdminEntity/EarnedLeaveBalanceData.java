package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "earned_leave_balance_data")
public class EarnedLeaveBalanceData {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "earned_leave")
	    private Double earnedLeave;

	    @Column(name = "el_used")
	    private Double elUsed;

	    @Column(name = "elremaining")
	    private Double elRemaining;

	    private String email;

	    @Column(name = "emp_id", unique = true)
	    private String empId;

	    @Column(name = "last_increment_date")
	    private String lastIncrementDate;

	    @Column(name = "month_year")
	    private String monthYear;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Double getEarnedLeave() {
			return earnedLeave;
		}

		public void setEarnedLeave(Double earnedLeave) {
			this.earnedLeave = earnedLeave;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getMonthYear() {
			return monthYear;
		}

		public void setMonthYear(String monthYear) {
			this.monthYear = monthYear;
		}

		@Override
		public String toString() {
			return "EarnedLeaveBalanceData [id=" + id + ", earnedLeave=" + earnedLeave + ", elUsed=" + elUsed
					+ ", elRemaining=" + elRemaining + ", email=" + email + ", empId=" + empId + ", lastIncrementDate="
					+ lastIncrementDate + ", monthYear=" + monthYear + "]";
		}

		  public EarnedLeaveBalanceData() {
			super();
		}

		public EarnedLeaveBalanceData(Integer id, Double earnedLeave, Double elUsed, Double elRemaining, String email,
				String empId, String lastIncrementDate, String monthYear) {
			super();
			this.id = id;
			this.earnedLeave = earnedLeave;
			this.elUsed = elUsed;
			this.elRemaining = elRemaining;
			this.email = email;
			this.empId = empId;
			this.lastIncrementDate = lastIncrementDate;
			this.monthYear = monthYear;
		}
	
}
