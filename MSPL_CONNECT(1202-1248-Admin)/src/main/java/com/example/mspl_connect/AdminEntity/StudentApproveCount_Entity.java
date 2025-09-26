package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentApproveCount_Entity {

	@Id
	private String approve_count;

	public String getApprove_count() {
		return approve_count;
	}

	public void setApprove_count(String approve_count) {
		this.approve_count = approve_count;
	}

	@Override
	public String toString() {
		return "StudentApproveCount_Entity [approve_count=" + approve_count + "]";
	}

}
