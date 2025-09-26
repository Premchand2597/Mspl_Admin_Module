package com.example.mspl_connect.AdminEntity;

public class Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO {

	private String emp_id;
	private String max_score;
	private String allotted_score;
	private String overall_percentage;
	private String overall_rating;
	private String financial_year;
	private String monthAndYear;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getMonthAndYear() {
		return monthAndYear;
	}
	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	@Override
	public String toString() {
		return "Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO [emp_id=" + emp_id + ", max_score="
				+ max_score + ", allotted_score=" + allotted_score + ", overall_percentage=" + overall_percentage
				+ ", overall_rating=" + overall_rating + ", financial_year=" + financial_year + ", monthAndYear="
				+ monthAndYear + "]";
	}
	
}
