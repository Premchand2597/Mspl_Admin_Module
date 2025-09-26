package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentResult_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int response_id;
	private String student_id;
	private String student_full_name;
	private String email;
	private String mobile_number;
	private String gender;
	private String registered_at;
	private String q_id;
	private String subject_name;
	private String question_name;
	private String op1;
	private String op2;
	private String op3;
	private String op4;
	private String selected_option;
	private String logic;
	private String original_answer;
	private String result;
	private String action;
	private String submitted_at;
	private String experience;
	private String candidate_expected_salary_in_lpa;
	private String candidate_join_time;
	private String candidate_action_reason;
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getCandidate_expected_salary_in_lpa() {
		return candidate_expected_salary_in_lpa;
	}
	public void setCandidate_expected_salary_in_lpa(String candidate_expected_salary_in_lpa) {
		this.candidate_expected_salary_in_lpa = candidate_expected_salary_in_lpa;
	}
	public String getCandidate_join_time() {
		return candidate_join_time;
	}
	public void setCandidate_join_time(String candidate_join_time) {
		this.candidate_join_time = candidate_join_time;
	}
	public int getResponse_id() {
		return response_id;
	}
	public void setResponse_id(int response_id) {
		this.response_id = response_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_full_name() {
		return student_full_name;
	}
	public void setStudent_full_name(String student_full_name) {
		this.student_full_name = student_full_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRegistered_at() {
		return registered_at;
	}
	public void setRegistered_at(String registered_at) {
		this.registered_at = registered_at;
	}
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getQuestion_name() {
		return question_name;
	}
	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public String getSelected_option() {
		return selected_option;
	}
	public void setSelected_option(String selected_option) {
		this.selected_option = selected_option;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getOriginal_answer() {
		return original_answer;
	}
	public void setOriginal_answer(String original_answer) {
		this.original_answer = original_answer;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSubmitted_at() {
		return submitted_at;
	}
	public void setSubmitted_at(String submitted_at) {
		this.submitted_at = submitted_at;
	}
	public String getCandidate_action_reason() {
		return candidate_action_reason;
	}
	public void setCandidate_action_reason(String candidate_action_reason) {
		this.candidate_action_reason = candidate_action_reason;
	}
	@Override
	public String toString() {
		return "StudentResult_Entity [response_id=" + response_id + ", student_id=" + student_id
				+ ", student_full_name=" + student_full_name + ", email=" + email + ", mobile_number=" + mobile_number
				+ ", gender=" + gender + ", registered_at=" + registered_at + ", q_id=" + q_id + ", subject_name="
				+ subject_name + ", question_name=" + question_name + ", op1=" + op1 + ", op2=" + op2 + ", op3=" + op3
				+ ", op4=" + op4 + ", selected_option=" + selected_option + ", logic=" + logic + ", original_answer="
				+ original_answer + ", result=" + result + ", action=" + action + ", submitted_at=" + submitted_at
				+ ", experience=" + experience + ", candidate_expected_salary_in_lpa="
				+ candidate_expected_salary_in_lpa + ", candidate_join_time=" + candidate_join_time
				+ ", candidate_action_reason=" + candidate_action_reason + "]";
	}
}
