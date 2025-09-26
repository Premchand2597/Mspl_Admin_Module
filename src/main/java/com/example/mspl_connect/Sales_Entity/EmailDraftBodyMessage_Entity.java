package com.example.mspl_connect.Sales_Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_draft_box_message")
public class EmailDraftBodyMessage_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
	private long id;
	private String email_draft;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail_draft() {
		return email_draft;
	}
	public void setEmail_draft(String email_draft) {
		this.email_draft = email_draft;
	}
	@Override
	public String toString() {
		return "EmailDraftBodyMessage_Entity [id=" + id + ", email_draft=" + email_draft + "]";
	}
}
