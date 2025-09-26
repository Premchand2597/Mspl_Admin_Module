package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "active_users")
public class ActiveUser {

	
	 public ActiveUser() {
		super();
	}

	public ActiveUser(String email) {
		super();
		this.email = email;
	}

	@Id
	    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ActiveUser [email=" + email + "]";
	}

}
