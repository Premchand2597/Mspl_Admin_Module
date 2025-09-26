package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CardCountBasedOnInterviewData_Entity {

	@Id
	private String interview_select;
	private String interview_reject; 
	private String interview_onhold;
	private String interview_pending;
	
	public String getInterview_select() {
		return interview_select;
	}
	public void setInterview_select(String interview_select) {
		this.interview_select = interview_select;
	}
	public String getInterview_reject() {
		return interview_reject;
	}
	public void setInterview_reject(String interview_reject) {
		this.interview_reject = interview_reject;
	}
	public String getInterview_onhold() {
		return interview_onhold;
	}
	public void setInterview_onhold(String interview_onhold) {
		this.interview_onhold = interview_onhold;
	}
	public String getInterview_pending() {
		return interview_pending;
	}
	public void setInterview_pending(String interview_pending) {
		this.interview_pending = interview_pending;
	}
	@Override
	public String toString() {
		return "CardCountBasedOnInterviewData_Entity [interview_select=" + interview_select + ", interview_reject="
				+ interview_reject + ", interview_onhold=" + interview_onhold + ", interview_pending="
				+ interview_pending + "]";
	}
	
}
