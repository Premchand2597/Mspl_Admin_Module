package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.ChatMessage;

import jakarta.transaction.Transactional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByRecipientEmail(String recipientEmail);

	int countByRecipientEmailAndIsReadFalse(String email);
	  
	/*  @Query("SELECT m FROM ChatMessage m WHERE (m.senderEmail = :user1 AND m.recipientEmail = :user2) OR (m.senderEmail = :user2 AND m.recipientEmail = :user1) ORDER BY m.timestamp ASC")
	    List<ChatMessage> findChatHistoryBetween(@Param("user1") String user1, @Param("user2") String user2);*/
	
	@Query("SELECT m FROM ChatMessage m WHERE (m.senderEmail = :user1 AND m.recipientEmail = :user2 AND m.deletedForSender = false) OR (m.senderEmail = :user2 AND m.recipientEmail = :user1 AND m.deletedForRecipient = false) ORDER BY m.timestamp ASC")
	List<ChatMessage> findChatHistoryBetween(@Param("user1") String user1, @Param("user2") String user2);

	List<ChatMessage> findByRecipientEmailAndIsReadFalse(String recipientEmail);
    // Method to find messages by recipient and sender
    List<ChatMessage> findBySenderEmailAndRecipientEmail(String recipientEmail, String senderEmail);
    
    @Transactional
    @Modifying
    @Query("UPDATE ChatMessage m SET m.isRead = true WHERE m.recipientEmail = :recipientEmail AND m.senderEmail = :senderEmail AND m.isRead = false")
    int markMessagesAsRead(@Param("recipientEmail") String recipientEmail, @Param("senderEmail") String senderEmail);

    List<ChatMessage> findByRecipientEmailOrSenderEmailOrderByTimestampDesc(String email1, String email2);

}
