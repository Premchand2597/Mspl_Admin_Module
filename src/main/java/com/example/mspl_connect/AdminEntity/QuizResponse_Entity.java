package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_response")
public class QuizResponse_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String student_id;
	private String question_id;
	private String selected_option;
	private String output;
	private String result;
	private String inserted_at;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getSelected_option() {
		return selected_option;
	}
	public void setSelected_option(String selected_option) {
		this.selected_option = selected_option;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getInserted_at() {
		return inserted_at;
	}
	public void setInserted_at(String inserted_at) {
		this.inserted_at = inserted_at;
	}
	@Override
	public String toString() {
		return "QuizResponse_Entity [id=" + id + ", student_id=" + student_id + ", question_id=" + question_id
				+ ", selected_option=" + selected_option + ", output=" + output + ", result=" + result
				+ ", inserted_at=" + inserted_at + "]";
	}
	
}
