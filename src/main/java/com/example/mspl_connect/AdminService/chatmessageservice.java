package com.example.mspl_connect.AdminService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.ChatMessage;
import com.example.mspl_connect.AdminRepo.ChatMessageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class chatmessageservice {

	 @Autowired
	    private ChatMessageRepository chatMessageRepository;

	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 @Autowired
	 private SimpMessagingTemplate messagingTemplate;

	 
	   /* @Transactional
	    public int markMessagesAsRead(String recipientEmail, String senderEmail) {
	        return chatMessageRepository.markMessagesAsRead(recipientEmail, senderEmail);
	    }*/
	    
	/* @Transactional
	 public boolean markMessagesAsRead(String recipientEmail, String senderEmail) {
	     int updatedRows = chatMessageRepository.markMessagesAsRead(recipientEmail, senderEmail);
	     if (updatedRows > 0) {
	         entityManager.flush(); // Ensure changes are committed immediately
	         entityManager.clear(); // Prevent stale data issues
	         System.out.println("Messages marked as read: " + updatedRows);
	         return true;
	     }
	     return false;
	 }*/
	 
	 
	/* @Transactional
	 public boolean markMessagesAsRead(String recipientEmail, String senderEmail) {
	     System.out.println("222222222");
		 int updatedRows = chatMessageRepository.markMessagesAsRead(recipientEmail, senderEmail);
		 System.out.println("3333333333"+updatedRows);
		 if (updatedRows > 0) {
	         entityManager.flush(); // Ensure changes are committed immediately
	         entityManager.clear(); // Prevent stale data issues

	         // Notify the sender via WebSocket that messages were read
	         List<ChatMessage> updatedMessages = chatMessageRepository.findChatHistoryBetween(senderEmail, recipientEmail);
	         System.out.println("Sending WebSocket message to: " + senderEmail + " -> " + updatedMessages);

	         messagingTemplate.convertAndSendToUser(senderEmail, "/queue/messages", updatedMessages);
	         System.out.println("Sending WebSocket message to:xc " + senderEmail + " -> " + updatedMessages);

	         System.out.println("Messages marked as read: " + updatedRows);
	         return true;
	     }
	     return false;
	 }*/
	 @Transactional
	 public List<ChatMessage> markMessagesAsRead(String recipientEmail, String senderEmail) {
	     System.out.println("Marking messages as read for: " + recipientEmail + " from: " + senderEmail);

	     int updatedRows = chatMessageRepository.markMessagesAsRead(recipientEmail, senderEmail);
	     System.out.println("Updated rows: " + updatedRows);

	     if (updatedRows > 0) {
	         entityManager.flush(); // Ensure changes are committed
	         entityManager.clear(); // Prevent stale data

	         // Fetch updated messages
	         List<ChatMessage> updatedMessages = chatMessageRepository.findChatHistoryBetween(senderEmail, recipientEmail);
	         System.out.println("Updated Messages List: " + updatedMessages);

	         // Debug WebSocket sending
	         System.out.println("Sending WebSocket update to " + senderEmail + " & " + recipientEmail);
	         messagingTemplate.convertAndSendToUser(senderEmail, "/queue/messages", updatedMessages);
	         messagingTemplate.convertAndSendToUser(recipientEmail, "/queue/messages", updatedMessages);
	         System.out.println("WebSocket update sent successfully!");

	         return updatedMessages;
	     }

	     return Collections.emptyList(); // Return empty list if no updates
	 }

}
