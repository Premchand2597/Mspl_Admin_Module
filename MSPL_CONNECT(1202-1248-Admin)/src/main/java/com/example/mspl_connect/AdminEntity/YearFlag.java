package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "year_flag")
public class YearFlag {


	public YearFlag(Integer id, String year, String employeeId, String month) {
		super();
		this.id = id;
		this.year = year;
		this.employeeId = employeeId;
		this.month = month;
	}

	public YearFlag(Integer id, String year, String employeeId) {
		super();
		this.id = id;
		this.year = year;
		this.employeeId = employeeId;
	}

	public YearFlag() {
		super();
	}

	

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	
	 @Column(name = "year")
	 private String year;
	 
	 @Column(name = "employee_id")
	 private String employeeId;

	 @Column(name = "month")
	    private String month;  // New column to track the month
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String string) {
		this.employeeId = string;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
