package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {
	
	   public ChatMessage(Long id, String senderEmail, String recipientEmail, String content, LocalDateTime timestamp,
			boolean isRead) {
		super();
		this.id = id;
		this.senderEmail = senderEmail;
		this.recipientEmail = recipientEmail;
		this.content = content;
		this.timestamp = timestamp;
		this.isRead = isRead;
	}

	public ChatMessage(Long id, String senderEmail, String recipientEmail, String content, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.senderEmail = senderEmail;
		this.recipientEmail = recipientEmail;
		this.content = content;
		this.timestamp = timestamp;
	}

	public ChatMessage() {
		super();
	}
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String senderEmail;
	    
	    private String recipientEmail;
	    private String content;
	    private LocalDateTime timestamp;
	    
	    private boolean isRead = false;  // New field to track read status
	    
	    
	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

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

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(LocalDateTime timestamp) {
	        this.timestamp = timestamp;
	    }
	    public ChatMessage(String content, String recipientEmail, String senderEmail) {
	        this.content = content;
	        this.recipientEmail = recipientEmail;
	        this.senderEmail = senderEmail;
	        this.timestamp = LocalDateTime.now(); // Optional: Set the timestamp here or in the setter
	    }

		@Override
		public String toString() {
			return "ChatMessage [id=" + id + ", senderEmail=" + senderEmail + ", recipientEmail=" + recipientEmail
					+ ", content=" + content + ", timestamp=" + timestamp + ", isRead=" + isRead + "]";
		}

		public boolean isRead() {
			return isRead;
		}

		public void setRead(boolean isRead) {
			this.isRead = isRead;
		}

}
