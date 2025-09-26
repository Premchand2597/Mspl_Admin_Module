package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.AdminEntity.TypingNotification;

import jakarta.transaction.Transactional;

public interface TypingNotificationRepository extends JpaRepository<TypingNotification, Long> {

	 @Transactional
    void deleteBySenderEmailAndRecipientEmail(String senderEmail, String recipientEmail);
	
	 
	 TypingNotification findBySenderEmailAndRecipientEmail(String senderEmail, String recipientEmail);
}
