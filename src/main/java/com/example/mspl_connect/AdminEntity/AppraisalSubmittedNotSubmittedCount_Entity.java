package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppraisalSubmittedNotSubmittedCount_Entity {

	@Id
	private String total_count;
	private String submitted_count;
	private String not_submitted_count;
	
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getSubmitted_count() {
		return submitted_count;
	}
	public void setSubmitted_count(String submitted_count) {
		this.submitted_count = submitted_count;
	}
	public String getNot_submitted_count() {
		return not_submitted_count;
	}
	public void setNot_submitted_count(String not_submitted_count) {
		this.not_submitted_count = not_submitted_count;
	}
	@Override
	public String toString() {
		return "AppraisalSubmittedNotSubmittedCount_Entity [total_count=" + total_count + ", submitted_count="
				+ submitted_count + ", not_submitted_count=" + not_submitted_count + "]";
	}
	
}
