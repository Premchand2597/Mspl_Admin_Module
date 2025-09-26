package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentRejectCount_Entity {

	@Id
	private String reject_count;

	public String getReject_count() {
		return reject_count;
	}

	public void setReject_count(String reject_count) {
		this.reject_count = reject_count;
	}

	@Override
	public String toString() {
		return "StudentRejectCount_Entity [reject_count=" + reject_count + "]";
	}

}
