package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserStatus {

	public UserStatus() {
		super();
	}
	public UserStatus(Long id, String email, boolean isOnline, LocalDateTime lastSeen) {
		super();
		this.id = id;
		this.email = email;
		this.isOnline = isOnline;
		this.lastSeen = lastSeen;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private boolean isOnline;
    private LocalDateTime lastSeen;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public LocalDateTime getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	} 

	
}
