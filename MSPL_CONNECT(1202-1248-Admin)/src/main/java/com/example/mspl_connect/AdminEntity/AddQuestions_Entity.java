package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddQuestions_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String questions;
	private String op1;
	private String op2;
	private String op3;
	private String op4;
	private String answers;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
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
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "AddQuestions_Entity [id=" + id + ", questions=" + questions + ", op1=" + op1 + ", op2=" + op2 + ", op3="
				+ op3 + ", op4=" + op4 + ", answers=" + answers + "]";
	}
	
	
	
}
