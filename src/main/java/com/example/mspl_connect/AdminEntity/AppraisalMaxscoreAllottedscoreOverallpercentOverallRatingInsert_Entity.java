package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "yearwise_overall_performance_ratings")
public class AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Entity {
	
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
	@Override
	public String toString() {
		return "AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Entity [id=" + id + ", financial_year="
				+ financial_year + ", emp_id=" + emp_id + ", email=" + email + ", max_score=" + max_score
				+ ", allotted_score=" + allotted_score + ", overall_percentage=" + overall_percentage
				+ ", overall_rating=" + overall_rating + "]";
	}

}
