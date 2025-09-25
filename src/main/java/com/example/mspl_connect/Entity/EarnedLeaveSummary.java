package com.example.mspl_connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "earned_leave_summary")
public class EarnedLeaveSummary {

	public EarnedLeaveSummary(Long id, String empId, Double added, Double remaining, String lastUpdated,
			String incrementedDate, String email, String remarks) {
		super();
		this.id = id;
		this.empId = empId;
		this.added = added;
		this.remaining = remaining;
		this.lastUpdated = lastUpdated;
		this.incrementedDate = incrementedDate;
		Email = email;
		this.remarks = remarks;
	}

	public EarnedLeaveSummary() {
		super();
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "emp_id", nullable = false)
	    private String empId;

	   /* @Column(name = "month", nullable = false)
	    private String month;

	    @Column(name = "financial_year", nullable = false)
	    private String financialYear;*/

	    @Column(name = "added_as_earned_leave", nullable = false)
	    private Double added;

	    @Column(name = "remaining", nullable = false)
	    private Double remaining;

	    @Column(name = "last_updated", nullable = false)
	    private String lastUpdated;

	    @Column(name = "incremented_date")
	    private String incrementedDate;
	    
	    @Column(name = "email")
	    private String Email;
	    
	    private String remarks;


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

		public String getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(String lastUpdated) {
			this.lastUpdated = lastUpdated;
		}

		public String getIncrementedDate() {
			return incrementedDate;
		}

		public void setIncrementedDate(String incrementedDate) {
			this.incrementedDate = incrementedDate;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

}
