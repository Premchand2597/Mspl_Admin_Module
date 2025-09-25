package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MarkDetails_Entity {
	
	@Id
	private String subject_name;
	private String total_questions_count;
	private String questions_attended;
	private String questions_not_attended;
	private String total_correct_answer;
	private String total_wrong_answer;
	private String total_percentage;

	
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getTotal_questions_count() {
		return total_questions_count;
	}
	public void setTotal_questions_count(String total_questions_count) {
		this.total_questions_count = total_questions_count;
	}
	public String getQuestions_attended() {
		return questions_attended;
	}
	public void setQuestions_attended(String questions_attended) {
		this.questions_attended = questions_attended;
	}
	public String getQuestions_not_attended() {
		return questions_not_attended;
	}
	public void setQuestions_not_attended(String questions_not_attended) {
		this.questions_not_attended = questions_not_attended;
	}
	public String getTotal_correct_answer() {
		return total_correct_answer;
	}
	public void setTotal_correct_answer(String total_correct_answer) {
		this.total_correct_answer = total_correct_answer;
	}
	public String getTotal_wrong_answer() {
		return total_wrong_answer;
	}
	public void setTotal_wrong_answer(String total_wrong_answer) {
		this.total_wrong_answer = total_wrong_answer;
	}
	public String getTotal_percentage() {
		return total_percentage;
	}
	public void setTotal_percentage(String total_percentage) {
		this.total_percentage = total_percentage;
	}
	
	@Override
	public String toString() {
		return "MarkDetails_Entity [total_questions_count=" + total_questions_count + ", questions_attended="
				+ questions_attended + ", questions_not_attended=" + questions_not_attended + ", total_correct_answer="
				+ total_correct_answer + ", total_wrong_answer=" + total_wrong_answer + ", total_percentage="
				+ total_percentage + ", subject_name=" + subject_name + "]";
	}
}
