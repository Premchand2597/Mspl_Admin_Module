package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String financial_year;
	private String emp_id;
	private String email;
	private String max_score;
	private String allotted_score;
	private String overall_percentage;
	private String overall_rating;
	private String financial_year_in_another_table;
	private String rating;
	private String hike_percentage;
	private String emp_name;
	private String current_salary;
	private String salary_after_hike;
	private String remarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMax_score() {
		return max_score;
	}
	public void setMax_score(String max_score) {
		this.max_score = max_score;
	}
	public String getAllotted_score() {
		return allotted_score;
	}
	public void setAllotted_score(String allotted_score) {
		this.allotted_score = allotted_score;
	}
	public String getOverall_percentage() {
		return overall_percentage;
	}
	public void setOverall_percentage(String overall_percentage) {
		this.overall_percentage = overall_percentage;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getFinancial_year_in_another_table() {
		return financial_year_in_another_table;
	}
	public void setFinancial_year_in_another_table(String financial_year_in_another_table) {
		this.financial_year_in_another_table = financial_year_in_another_table;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getHike_percentage() {
		return hike_percentage;
	}
	public void setHike_percentage(String hike_percentage) {
		this.hike_percentage = hike_percentage;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getCurrent_salary() {
		return current_salary;
	}
	public void setCurrent_salary(String current_salary) {
		this.current_salary = current_salary;
	}
	public String getSalary_after_hike() {
		return salary_after_hike;
	}
	public void setSalary_after_hike(String salary_after_hike) {
		this.salary_after_hike = salary_after_hike;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity [id=" + id
				+ ", financial_year=" + financial_year + ", emp_id=" + emp_id + ", email=" + email + ", max_score="
				+ max_score + ", allotted_score=" + allotted_score + ", overall_percentage=" + overall_percentage
				+ ", overall_rating=" + overall_rating + ", financial_year_in_another_table="
				+ financial_year_in_another_table + ", rating=" + rating + ", hike_percentage=" + hike_percentage
				+ ", emp_name=" + emp_name + ", current_salary=" + current_salary + ", salary_after_hike="
				+ salary_after_hike + ", remarks=" + remarks + "]";
	}
	
}
