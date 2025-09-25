package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentTotalCount_Entity {

	@Id
	private String student_count;

	public String getStudent_count() {
		return student_count;
	}

	public void setStudent_count(String student_count) {
		this.student_count = student_count;
	}

	@Override
	public String toString() {
		return "StudentTotalCount_Entity [student_count=" + student_count + "]";
	}

}
