package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentOnHoldCount_Entity {

	@Id
	private String onhold_count;

	public String getOnhold_count() {
		return onhold_count;
	}

	public void setOnhold_count(String onhold_count) {
		this.onhold_count = onhold_count;
	}

	@Override
	public String toString() {
		return "StudentOnHoldCount_Entity [onhold_count=" + onhold_count + "]";
	}

}
