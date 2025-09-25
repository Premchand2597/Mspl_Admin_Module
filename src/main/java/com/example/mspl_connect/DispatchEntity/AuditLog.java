package com.example.mspl_connect.DispatchEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "audit_log")
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String actionType;
	private String performedBy;
	private String affectedUser;
	private LocalDateTime timestamp;
	
	public AuditLog() {
		 this.timestamp = LocalDateTime.now();
	}

    public AuditLog(String actionType, String performedBy, String affectedUser, LocalDateTime timestamp) {
        this.actionType = actionType;
        this.performedBy = performedBy;
        this.affectedUser = affectedUser;
        this.timestamp = timestamp;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public String getAffectedUser() {
		return affectedUser;
	}

	public void setAffectedUser(String affectedUser) {
		this.affectedUser = affectedUser;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
