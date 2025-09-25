package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salary_details")
public class Appraisal_InsertUpdatedHikedSalary_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_id; 
	private String emp_name;
	private String total_performance;
	private String old_salary;
	private String hike;
	private String salary_after_hike;
	private String financial_year;
	private String remarks;
	private String hike_affect_from;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHike_affect_from() {
		return hike_affect_from;
	}
	public void setHike_affect_from(String hike_affect_from) {
		this.hike_affect_from = hike_affect_from;
	}
	@Override
	public String toString() {
		return "Appraisal_InsertUpdatedHikedSalary_Entity [id=" + id + ", emp_id=" + emp_id + ", emp_name=" + emp_name
				+ ", total_performance=" + total_performance + ", old_salary=" + old_salary + ", hike=" + hike
				+ ", salary_after_hike=" + salary_after_hike + ", financial_year=" + financial_year + ", remarks="
				+ remarks + ", hike_affect_from=" + hike_affect_from + "]";
	}
	
}
