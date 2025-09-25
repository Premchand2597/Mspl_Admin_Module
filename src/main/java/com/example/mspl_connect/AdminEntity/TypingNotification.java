package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "typing_notifications")
public class TypingNotification {

	 public TypingNotification() {
		super();
	}
	public TypingNotification(String senderEmail, String recipientEmail, boolean typing) {
		super();
		this.senderEmail = senderEmail;
		this.recipientEmail = recipientEmail;
		this.typing = typing;
	}
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String senderEmail;
	    private String recipientEmail;
	    private boolean typing;
		public String getSenderEmail() {
			return senderEmail;
		}
		public void setSenderEmail(String senderEmail) {
			this.senderEmail = senderEmail;
		}
		public String getRecipientEmail() {
			return recipientEmail;
		}
		public void setRecipientEmail(String recipientEmail) {
			this.recipientEmail = recipientEmail;
		}
		public boolean isTyping() {
			return typing;
		}
		public void setTyping(boolean typing) {
			this.typing = typing;
		}
	
}
