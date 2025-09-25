package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentPendingCount_Entity {

	@Id
	private String pending_count;

	public String getPending_count() {
		return pending_count;
	}

	public void setPending_count(String pending_count) {
		this.pending_count = pending_count;
	}

	@Override
	public String toString() {
		return "StudentPendingCount_Entity [pending_count=" + pending_count + "]";
	}

}
