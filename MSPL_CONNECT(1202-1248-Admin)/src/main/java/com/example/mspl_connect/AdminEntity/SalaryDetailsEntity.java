package com.example.mspl_connect.AdminEntity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="salary_details")
public class SalaryDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "emp_id") // Maps the Java field empId to the database column emp_id
    private String empId;
	private String total_performance;
	private String old_salary;
	private String hike;
	private String salary_after_hike;
	@Column(name = "financial_year") // Explicit mapping
	private String financialYear;
	private String hike_affect_from;	
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	@Column(name="remarks")
	private String remarks; 	
	
	public String getHike_affect_from() {
		return hike_affect_from;
	}
	public void setHike_affect_from(String hike_affect_from) {
		this.hike_affect_from = hike_affect_from;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getTotal_performance() {
		return total_performance;
	}
	public void setTotal_performance(String total_performance) {
		this.total_performance = total_performance;
	}
	public String getOld_salary() {
		return old_salary;
	}
	public void setOld_salary(String old_salary) {
		this.old_salary = old_salary;
	}
	public String getHike() {
		return hike;
	}
	public void setHike(String hike) {
		this.hike = hike;
	}
	public String getSalary_after_hike() {
		return salary_after_hike;
	}
	public void setSalary_after_hike(String salary_after_hike) {
		this.salary_after_hike = salary_after_hike;
	}
	public String getFinancial_year() {
		return financialYear;
	}
	public void setFinancial_year(String financial_year) {
		this.financialYear = financial_year;
	}
	@Override
	public String toString() {
		return "SalaryDetailsEntity [id=" + id + ", empId=" + empId + ", total_performance=" + total_performance
				+ ", old_salary=" + old_salary + ", hike=" + hike + ", salary_after_hike=" + salary_after_hike
				+ ", financial_year=" + financialYear + ", remarks=" + remarks + "]";
	}
	
		
}
