package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EarnedLeave {
	
	public EarnedLeave() {
		super();
	}
	public EarnedLeave(Long id, String email, double earnedLeave, String monthYear, Double elUsed, String empId,
			Double elremaining, String lastIncrementDate) {
		super();
		this.id = id;
		this.email = email;
		this.earnedLeave = earnedLeave;
		this.monthYear = monthYear;
		this.elUsed = elUsed;
		this.empId = empId;
		this.elremaining = elremaining;
		this.lastIncrementDate = lastIncrementDate;
	}
	public EarnedLeave(Long id, String email, double earnedLeave, String monthYear, Double elUsed, String empId,
			Double elremaining) {
		super();
		this.id = id;
		this.email = email;
		this.earnedLeave = earnedLeave;
		this.monthYear = monthYear;
		this.elUsed = elUsed;
		this.empId = empId;
		this.elremaining = elremaining;
	}
	public EarnedLeave(Long id, String email, double earnedLeave, String monthYear, Double elUsed, String empId) {
		super();
		this.id = id;
		this.email = email;
		this.earnedLeave = earnedLeave;
		this.monthYear = monthYear;
		this.elUsed = elUsed;
		this.empId = empId;
	}
	public EarnedLeave(Long id, String email, double earnedLeave, String monthYear, Double elUsed) {
		super();
		this.id = id;
		this.email = email;
		this.earnedLeave = earnedLeave;
		this.monthYear = monthYear;
		this.elUsed = elUsed;
	}
	public EarnedLeave(double elUsed) {
		super();
		this.elUsed = elUsed;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private double earnedLeave;
    private String monthYear;
    private Double elUsed;
private String empId;
private Double elremaining;
// existing fields

private String lastIncrementDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getEarnedLeave() {
		return earnedLeave;
	}
	public void setEarnedLeave(double earnedLeave) {
		this.earnedLeave = earnedLeave;
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
	
	// Optional: Getter with default value
    public double getElUsedOrDefault() {
        return elUsed != null ? elUsed : 0.0;
    }
    
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Double getElremaining() {
		return elremaining;
	}
	public void setElremaining(Double elremaining) {
		this.elremaining = elremaining;
	}
	public String getLastIncrementDate() {
		return lastIncrementDate;
	}
	public void setLastIncrementDate(String lastIncrementDate) {
		this.lastIncrementDate = lastIncrementDate;
	}
	
	
}
