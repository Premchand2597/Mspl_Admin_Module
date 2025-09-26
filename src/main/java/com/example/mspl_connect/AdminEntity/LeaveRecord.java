package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_record")
public class LeaveRecord {

	public LeaveRecord(Long id, String leaveType, int added, int used, int remaining, String month, String year,
			String email, String empId) {
		super();
		this.id = id;
		this.leaveType = leaveType;
		this.added = added;
		this.used = used;
		this.remaining = remaining;
		this.month = month;
		this.year = year;
		this.email = email;
		this.empId = empId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "leave_type")
	private String leaveType;

	@Column(name = "added")
	private int added;

	@Column(name = "used")
	private int used;

	@Column(name = "remaining")
	private int remaining;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

	@Column(name = "email")
	private String email;

	@Column(name="empid")
	private String empId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getAdded() {
		return added;
	}

	public void setAdded(int added) {
		this.added = added;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public LeaveRecord() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LeaveRecord(Long id, String leaveType, int added, int used, int remaining, String month, String year,
			String email) {
		super();
		this.id = id;
		this.leaveType = leaveType;
		this.added = added;
		this.used = used;
		this.remaining = remaining;
		this.month = month;
		this.year = year;
		this.email = email;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getYear() {
		return year;
	}
}
