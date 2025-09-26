package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.ActiveUser;
import com.example.mspl_connect.AdminEntity.ChatMessage;
import com.example.mspl_connect.AdminEntity.TypingNotification;
import com.example.mspl_connect.AdminEntity.UserStatus;
import com.example.mspl_connect.AdminRepo.ActiveUserRepository;
import com.example.mspl_connect.AdminRepo.ChatMessageRepository;
import com.example.mspl_connect.AdminRepo.TypingNotificationRepository;
import com.example.mspl_connect.AdminRepo.UserStatusRepository;
import com.example.mspl_connect.AdminService.ActiveUserService;
import com.example.mspl_connect.AdminService.UserStatusService;
import com.example.mspl_connect.AdminService.chatmessageservice;
import com.example.mspl_connect.Repository.EmployeeRepository;


@Controller
public class ChatController {
	
	 @Autowired
	 private EmployeeRepository employeeRepository;

	  @Autowired
	  private SimpMessagingTemplate simpMessagingTemplate;
	
	  @Autowired
	  private ChatMessageRepository chatMessageRepository;

	  
	  @Autowired
	    private UserStatusRepository userStatusRepository;
	  
	  @Autowired
	  private chatmessageservice chatMessageService;

	  
	  @Autowired
	    private TypingNotificationRepository typingNotificationRepository;
	  
	  @Autowired
	  private ActiveUserService activeUserService;
	  
	  @Autowired
	  private UserStatusService userStatusService;

	/*  @MessageMapping("/chat.sendMessage")
	  @SendToUser("/queue/messages") // Send to the specific user queue
	  public void sendMessage(ChatMessage chatMessage) {
		  System.out.println("Received ChatMessage: " + chatMessage); 
	      String sender = chatMessage.getSenderEmail();
	      String recipient = chatMessage.getRecipientEmail(); // Use the getter for recipientEmail

	      System.out.println("[[Sender: " + sender);
	      System.out.println("Recipient: " + recipient);

	      // Check if the sender is not null
	      if (sender != null) {
	          // Save the message to the database
	          ChatMessage messageToSave = new ChatMessage();
	          messageToSave.setSenderEmail(sender);
	          messageToSave.setRecipientEmail(recipient); // Use the setter for recipientEmail
	          messageToSave.setContent(chatMessage.getContent());
	          messageToSave.setTimestamp(LocalDateTime.now()); // Store the current timestamp

	          //chatMessageRepository.save(messageToSave); // Save message to the database
	          // Save the message to the database, which will auto-generate the id
	          ChatMessage savedMessage = chatMessageRepository.save(messageToSave);
	          // Create the message content for the user
	          String messageContent = sender + ": " + chatMessage.getContent();

	          // Send the message to the specific user
	         // simpMessagingTemplate.convertAndSendToUser(recipient, "/queue/messages", new ChatMessage(messageContent, recipient, sender));
	          // Send message content with all the details (including the auto-generated id) to the recipient
	          // Send message content with all the details (including the auto-generated id) to the recipient
	          simpMessagingTemplate.convertAndSendToUser(recipient, "/queue/messages", new ChatMessage(
	              savedMessage.getId(), // Include the auto-generated id
	              savedMessage.getContent(), 
	              recipient, 
	              sender, 
	              savedMessage.getTimestamp()
	          ));
	          
	          // Log the message sending action
	          System.out.println("Sent message from " + sender + " to " + recipient + ": " + chatMessage.getContent()+" p "+   savedMessage.getId());
	      } else {
	          System.err.println("Sender is null, unable to send message.");
	      }
	  }*/

	 /* @MessageMapping("/chat.sendMessage")
	  @SendToUser("/queue/messages")
	  public void sendMessage(ChatMessage chatMessage) {
	      System.out.println("Received ChatMessage: " + chatMessage); 
	      String sender = chatMessage.getSenderEmail();
	      String recipient = chatMessage.getRecipientEmail();

	      System.out.println("[[Sender: " + sender);
	      System.out.println("Recipient: " + recipient);

	      if (sender != null) {
	          // Save the message to the database
	          ChatMessage messageToSave = new ChatMessage();
	          messageToSave.setSenderEmail(sender);
	          messageToSave.setRecipientEmail(recipient);
	          messageToSave.setContent(chatMessage.getContent());
	          messageToSave.setTimestamp(LocalDateTime.now());

	          // Save the message
	          ChatMessage savedMessage = chatMessageRepository.save(messageToSave);

	          // Prepare response with all details
	          ChatMessage responseMessage = new ChatMessage(
	              savedMessage.getId(),
	              savedMessage.getContent(),
	              recipient,
	              sender,
	              savedMessage.getTimestamp()
	          );

	          // Send the response to both sender and recipient
	          simpMessagingTemplate.convertAndSendToUser(recipient, "/queue/messages", responseMessage);
	          simpMessagingTemplate.convertAndSendToUser(sender, "/queue/messages", responseMessage);

	          System.out.println("Sent message from " + sender + " to " + recipient + ": " + chatMessage.getContent());
	      } else {
	          System.err.println("Sender is null, unable to send message.");
	      }
	  }*/
	  private final SimpMessagingTemplate messagingTemplate;

	   /* public ChatController(SimpMessagingTemplate messagingTemplate) {
	        this.messagingTemplate = messagingTemplate;
	    }*/
	  public ChatController(SimpMessagingTemplate messagingTemplate, ActiveUserRepository activeUserRepository) {
	        this.messagingTemplate = messagingTemplate;
	        this.activeUserRepository = activeUserRepository;
	    }
	    private final ActiveUserRepository activeUserRepository;

	    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet(); // Thread-safe Set

	    
	/*  @MessageMapping("/chat.sendMessage")
	  @SendToUser("/queue/messages")
	  public ChatMessage sendMessage(ChatMessage chatMessage) {
	      System.out.println("Received ChatMessage: " + chatMessage);
	      String sender = chatMessage.getSenderEmail();
	      String recipient = chatMessage.getRecipientEmail();

	      System.out.println("[[Sender: " + sender);
	      System.out.println("Recipient: " + recipient);

	      if (sender != null) {
	          // Save the message to the database
	          ChatMessage messageToSave = new ChatMessage();
	          messageToSave.setSenderEmail(sender);
	          messageToSave.setRecipientEmail(recipient);
	          messageToSave.setContent(chatMessage.getContent());
	          messageToSave.setTimestamp(LocalDateTime.now());
	          messageToSave.setRead(false); // Mark as unread
	          // Save the message
	          ChatMessage savedMessage = chatMessageRepository.save(messageToSave);

	          // Prepare the response message
	          ChatMessage responseMessage = new ChatMessage(
	              savedMessage.getId(),
	              sender,
	              recipient,
	              savedMessage.getContent(),
	            
	             
	              savedMessage.getTimestamp()
	          );

	         
	          // Print the response to the console
	          System.out.println("Returning ChatMessageSS: " + responseMessage);
	          // Send a notification to the recipient
	          messagingTemplate.convertAndSendToUser(
	              recipient, // Send to recipient
	              "/queue/notifications", 
	              "New message from " + sender + ": " + savedMessage.getContent()
	          );
	          // Return the response message
	          return responseMessage;
	      } else {
	          System.err.println("Sender is null, unable to send message.");
	          return null;
	      }
	  }*/
	    
	    @MessageMapping("/chat.sendMessage")
	    @SendToUser("/queue/messages")
	    public ChatMessage sendMessage(ChatMessage chatMessage) {
	        //System.out.println("Received ChatMessage: " + chatMessage);
	        String sender = chatMessage.getSenderEmail();
	        String recipient = chatMessage.getRecipientEmail();

	        //System.out.println("[[Sender: " + sender);
	       // System.out.println("Recipient: " + recipient);

	        if (sender != null) {
	            // Save the message to the database
	            ChatMessage messageToSave = new ChatMessage();
	            messageToSave.setSenderEmail(sender);
	            messageToSave.setRecipientEmail(recipient);
	            messageToSave.setContent(chatMessage.getContent());
	            messageToSave.setTimestamp(LocalDateTime.now());
	            
	            // If sender and recipient are the same, mark as read, otherwise unread
	            messageToSave.setRead(sender.equals(recipient)); 

	         // Check if this message is a reply to another message
	            if (chatMessage.getRepliedMessage() != null) {
	                ChatMessage repliedMessage = chatMessageRepository.findById(chatMessage.getRepliedMessage().getId()).orElse(null);
	                messageToSave.setRepliedMessage(repliedMessage);
	            }
	            // Save the message
	            ChatMessage savedMessage = chatMessageRepository.save(messageToSave);

	            // Prepare the response message
	            ChatMessage responseMessage = new ChatMessage(
	                savedMessage.getId(),
	                sender,
	                recipient,
	                savedMessage.getContent(),
	                savedMessage.getTimestamp()
	            );

	            // Print the response to the console
	            //System.out.println("Returning ChatMessageSS: " + responseMessage);
	            
	            // Send a notification to the recipient only if sender and recipient are different
	            if (!sender.equals(recipient)) {
	                messagingTemplate.convertAndSendToUser(
	                    recipient, // Send to recipient
	                    "/queue/notifications", 
	                    "New message from " + sender + ": " + savedMessage.getContent()
	                );
	            }
	            
	            // Return the response message
	            return responseMessage;
	        } else {
	            System.err.println("Sender is null, unable to send message.");
	            return null;
	        }
	    }



	  
	  @GetMapping("/unread")
	  public ResponseEntity<List<ChatMessage>> getUnreadMessages(@RequestParam String email) {
		  //	System.out.println("unread messages count");
	      List<ChatMessage> unreadMessages = chatMessageRepository.findByRecipientEmailAndIsReadFalse(email);
		  //System.out.println("unread messages countsdf "+unreadMessages);
	      //System.out.println("unread "+unreadMessages);
	      if (unreadMessages.isEmpty()) {
	          return ResponseEntity.noContent().build(); // Return 204 if no unread messages
	      }
	      return ResponseEntity.ok(unreadMessages); // Return 200 with the unread messages
	  }	 

	  @GetMapping("/unread-count")
	  public ResponseEntity<Integer> getUnreadMessageCount(@RequestParam String email) {
		 // System.out.println("jiiiii");
	      int unreadCount = chatMessageRepository.countByRecipientEmailAndIsReadFalse(email);
	      //System.out.println("jiiiii "+unreadCount);
	      return ResponseEntity.ok(unreadCount);
	  }

	  @GetMapping("/unread-messages")
	  public ResponseEntity<Map<String, Integer>> getUnreadMessageCounts(@RequestParam String recipientEmail) {
	      // Get all unread messages for the logged-in user
	      List<ChatMessage> unreadMessages = chatMessageRepository.findByRecipientEmailAndIsReadFalse(recipientEmail);

	      // Map to store unread count grouped by sender
	      Map<String, Integer> unreadCounts = new HashMap<>();

	      for (ChatMessage message : unreadMessages) {
	          String sender = message.getSenderEmail(); // Get sender's email
	          unreadCounts.put(sender, unreadCounts.getOrDefault(sender, 0) + 1);
	      }

	      // Print to console for debugging
	     // System.out.println("Unread Message Counts: " + unreadCounts);

	      return ResponseEntity.ok(unreadCounts);
	  }



	  
	  @GetMapping("/messages")
	  public ResponseEntity<List<ChatMessage>> markMessages(
	          @RequestParam String loggedInEmail,
	          @RequestParam String selectedEmployeeEmail) {

	      System.out.println("Received request to get messages");

	      // Retrieve chat history
	      List<ChatMessage> messages = chatMessageRepository.findChatHistoryBetween(loggedInEmail, selectedEmployeeEmail);

	      if (!messages.isEmpty()) {
	          System.out.println("Processing read status update...");

	          List<ChatMessage> updatedMessages = chatMessageService.markMessagesAsRead(loggedInEmail, selectedEmployeeEmail);
	          if (!updatedMessages.isEmpty()) {
	        	  System.out.println("Returning unmodified messages." +updatedMessages );
	              return ResponseEntity.ok(updatedMessages);
	          }
	      }

	      System.out.println("Returning unmodified messages." +messages );
	      return ResponseEntity.ok(messages);
	  }


	  
	  @GetMapping("/recent-chats")
	  public ResponseEntity<List<ChatMessage>> getRecentChats(@RequestParam String email) {
	      // Retrieve all unread messages for the employee (or you can fetch all messages)
	      List<ChatMessage> allMessages = chatMessageRepository.findByRecipientEmailOrSenderEmailOrderByTimestampDesc(email, email);

	      // Log the retrieved messages
	     // System.out.println("All messages retrieved: " + allMessages);

	      // Check if there are any messages
	      if (allMessages.isEmpty()) {
	          //System.out.println("No messages found.");
	          return ResponseEntity.ok(Collections.emptyList());
	      }

	      // Group messages by employee (sender) and sort by the most recent one
	      Map<String, ChatMessage> recentChats = new HashMap<>();

	      for (ChatMessage message : allMessages) {
	          // Use sender email as the key and update with the most recent message
	          String otherEmail = message.getSenderEmail().equals(email) ? message.getRecipientEmail() : message.getSenderEmail();

	          // If the message is newer, or the employee has no previous entry in the map
	          recentChats.putIfAbsent(otherEmail, message);

	          // Update if it's a more recent message
	          if (recentChats.get(otherEmail).getTimestamp().isBefore(message.getTimestamp())) {
	              recentChats.put(otherEmail, message);
	          }
	      }

	      // Log the recentChats map
	      // System.out.println("Grouped messages: " + recentChats);

	      // Convert the map values into a list of recent chats
	      List<ChatMessage> recentChatList = new ArrayList<>(recentChats.values());

	      // Sort the list based on the timestamp (latest first)
	      recentChatList.sort((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()));

	      // Log the final sorted list
	      // System.out.println("Sorted recent chats: " + recentChatList);

	      // Return the list of recent chats
	      return ResponseEntity.ok(recentChatList);
	  }

	  
	  
	  @DeleteMapping("/deleteMessage/{id}")
	  public ResponseEntity<String> deleteMessage(
	          @PathVariable Long id,
	          @RequestParam String loggedInEmail) {

	      try {
	          Optional<ChatMessage> messageOptional = chatMessageRepository.findById(id);

	          if (messageOptional.isPresent()) {
	              ChatMessage message = messageOptional.get();

	              // Check if the logged-in user is the sender or recipient
	              if (message.getSenderEmail().equals(loggedInEmail)) {
	                  message.setDeletedForSender(true);
	              } else if (message.getRecipientEmail().equals(loggedInEmail)) {
	                  message.setDeletedForRecipient(true);
	              } else {
	                  return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                          .body("You are not authorized to delete this message");
	              }

	              chatMessageRepository.save(message); // Save changes

	              return ResponseEntity.ok("Message deleted for you");
	          } else {
	              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
	          }

	      } catch (Exception e) {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting message");
	      }
	  }


	/*  @MessageMapping("/userConnected")
	  public void userConnected(@Payload Map<String, String> payload) {
	      System.out.println("Received userConnected event with payload: " + payload);

	      if (payload == null || payload.isEmpty()) {
	          System.out.println("Payload is null or empty!");
	          return;
	      }

	      String email = payload.get("email");
	      System.out.println("Extracted email: " + email);

	      if (email != null && !email.trim().isEmpty()) {
	          activeUsers.add(email);
	          System.out.println("User Connected: " + email);
	          System.out.println("Current Active Users: " + activeUsers);
	          broadcastActiveUsers();
	      } else {
	          System.out.println("User Connected Event: No email found in payload!");
	      }
	  }*/
	  
	/*  @MessageMapping("/userConnected")
	  public void userConnected(@Payload Map<String, String> payload) {
	      //System.out.println("Received userConnected event with payload: " + payload);

	      if (payload == null || payload.isEmpty()) {
	          //System.out.println("Payload is null or empty!");
	          return;
	      }

	      String email = payload.get("email");
	     // System.out.println("Extracted email: " + email);

	      if (email != null && !email.trim().isEmpty()) {
	          activeUsers.add(email);
	         // System.out.println("User Connected: " + email);
	          //System.out.println("Current Active Users: " + activeUsers);
	       // Save in DB
	          ActiveUser savedUser = activeUserRepository.save(new ActiveUser(email));

	          // ✅ Confirm the record is saved by printing it
	         // System.out.println("Saved Active User: " + savedUser);

	          broadcastActiveUsers();

	          // Update last seen status
	          updateUserStatus(email, true);
	      } else {
	          System.out.println("User Connected Event: No email found in payload!");
	      }
	  }
*/
	  
	  
	  @MessageMapping("/userConnected")
	  public void userConnected(@Payload Map<String, String> payload) {
	      //System.out.println("Received userConnected event with payload: " + payload);

	      if (payload == null || payload.isEmpty()) {
	          System.out.println("Payload is null or empty!");
	          return;
	      }

	      String email = payload.get("email");
	      //System.out.println("Extracted email: " + email);

	      if (email != null && !email.trim().isEmpty()) {
	       activeUsers.add(email);
	         // System.out.println("User Connected: " + email);
	         // System.out.println("Current Active Users: " + activeUsers);
	       // Save in DB
	        ActiveUser savedUser = activeUserRepository.save(new ActiveUser(email));

	          // ✅ Confirm the record is saved by printing it
	          //System.out.println("Saved Active User: " + savedUser);

	          broadcastActiveUsers();

	          // Update last seen status
	          updateUserStatus(email, true);
	          
	          
	      } else {
	          System.out.println("User Connected Event: No email found in payload!");
	      }
	  }

	  // Update last seen in database
	 private void updateUserStatus(String email, boolean isOnline) {
		  //System.out.println("heyy "+email+"email "+isOnline);
	      Optional<UserStatus> userStatusOpt = userStatusRepository.findByEmail(email);
	      UserStatus userStatus = userStatusOpt.orElse(new UserStatus());
	      userStatus.setEmail(email);
	      userStatus.setOnline(isOnline);
	      
	      if (!isOnline) {
	          userStatus.setLastSeen(LocalDateTime.now());
	      }

	      userStatusRepository.save(userStatus);
	  }

	  
	  @Scheduled(fixedRate = 30000) // Runs every 1 minute
	  public void checkInactiveUsers() {
	      LocalDateTime now = LocalDateTime.now();
	      
	      // Remove users who haven't sent a heartbeat in the last 2 minutes
	      activeUserService.getActiveUsers().removeIf(email -> {
	          Optional<UserStatus> userStatusOpt = userStatusRepository.findByEmail(email);
	          if (userStatusOpt.isPresent()) {
	              UserStatus userStatus = userStatusOpt.get();
	              if (!userStatus.isOnline() || userStatus.getLastSeen().isBefore(now.minusMinutes(2))) {
	                  //updateUserStatus(email, false);
	                  return true;
	              }
	          }
	          return false;
	      });

	      broadcastActiveUsers();
	  }



	  // Track user disconnection
	/* @MessageMapping("/userDisconnected")
	  public void userDisconnected(@Payload Map<String, String> payload) {
	      System.out.println("Received userDisconnected event with payload: " + payload);

	      if (payload == null || payload.isEmpty()) {
	          System.out.println("Payload is null or empty!");
	          return;
	      }

	      String email = payload.get("email");
	      System.out.println("Extracted email: " + email);

	      if (email != null && !email.trim().isEmpty()) {
	          activeUsers.remove(email);
	          System.out.println("User Disconnected: " + email);
	          System.out.println("Current Active Users after removal: " + activeUsers);
	          broadcastActiveUsers();
	      } else {
	          System.out.println("User Disconnected Event: No email found in payload!");
	      }
	  }*/
	  
	  @MessageMapping("/userHeartbeat")
	  public void userHeartbeat(@Payload Map<String, String> payload) {
	      String email = payload.get("email");
	      //System.out.println("heartbeat");
	      if (email != null && !email.trim().isEmpty()) {
	          activeUsers.add(email); // Keep the user in the active list
	          // Save in DB if not already present
	          activeUserRepository.save(new ActiveUser(email));

	          updateUserStatus(email, true); // Update online status
	          //  System.out.println("Received heartbeat from: " + email);
	      }
	  }

	 
	  @MessageMapping("/userInactive")
	  public void userInactive(@Payload Map<String, String> payload) {
	      String email = payload.get("email");
	      //System.out.println("User Inactive (Tab switched): " + email);

	      if (email != null && !email.trim().isEmpty()) {
	          updateUserStatus(email, false); // Mark as inactive

	          // ✅ Remove user from active users list
	          activeUsers.remove(email); 
	          // Remove from DB
	          activeUserRepository.deleteById(email);

	          broadcastActiveUsers();
	      }
	  }
	  
	  @MessageMapping("/userDisconnected")
	  public void userDisconnected(@Payload Map<String, String> payload) {
	      //System.out.println("Received userDisconnected event with payload: " + payload);

	      if (payload == null || payload.isEmpty()) {
	          System.out.println("Payload is null or empty!");
	          return;
	      }

	      String email = payload.get("email");
	      //System.out.println("Extracted email: " + email);

	      if (email != null && !email.trim().isEmpty()) {
	          activeUsers.remove(email);
	         // System.out.println("User Disconnected: " + email);
	          //System.out.println("Current Active Users after removal: " + activeUsers);
	          // Remove from DB
	          activeUserRepository.deleteById(email);

	          broadcastActiveUsers();

	          // Update last seen timestamp
	          updateUserStatus(email, false);
	      } else {
	          System.out.println("User Disconnected Event: No email found in payload!");
	      }
	  }

	  
	  @PostMapping("/updateUserStatus")
	  public ResponseEntity<Void> updateUserStatus(@RequestBody Map<String, String> request) {
	      String email = request.get("email");
	      System.out.println("Updating user status for: " + email);
	      
	      activeUserService.removeActiveUser(email); 
	      // ✅ Remove from database
	        activeUserRepository.deleteById(email);

	      userStatusService.updateUserStatus(email);
	      return ResponseEntity.ok().build(); // Return an empty response with HTTP 200 status
	  }

	  // Notify all clients about active users
	/*  private void broadcastActiveUsers() {
	      System.out.println("Broadcasting Active Users: " + activeUsers);
	      messagingTemplate.convertAndSend("/topic/activeUsers", activeUsers);
	  }*/
	/*  private void broadcastActiveUsers() {
	        List<String> activeUsers = activeUserRepository.findAll()
	                .stream()
	                .map(ActiveUser::getEmail)
	                .collect(Collectors.toList());

	        System.out.println("Broadcasting Active Users: " + activeUsers);
	        messagingTemplate.convertAndSend("/topic/activeUsers", activeUsers);
	    }*/
	  @Scheduled(fixedRate = 1000) // Runs every 1 second (1000ms)
	  private void broadcastActiveUsers() {
		    List<String> activeUsers = activeUserRepository.findAllEmails(); // Directly fetch emails

		   // System.out.println("Broadcasting Active Users: " + activeUsers);
		    messagingTemplate.convertAndSend("/topic/activeUsers", activeUsers);
		}
	  
	  @MessageMapping("/getActiveUsers")
	  @SendTo("/topic/activeUsers")
	  public Set<String> getActiveUsers() {
	      //System.out.println("Fetching active users: " + activeUsers);
	      //return activeUsers;
		  return activeUserService.getActiveUsers();
	  }
	  
	  @MessageMapping("/userTyping")
	    @SendTo("/topic/typing")  
	    public TypingNotification handleTypingEvent(@Payload TypingNotification typingNotification) {
	        System.out.println("📩 Received Typing Notification: " + typingNotification);

	        // Log sender & recipient before deletion
	        System.out.println("🗑️ Deleting previous entry for Sender: " + typingNotification.getSenderEmail() + 
	                           " and Recipient: " + typingNotification.getRecipientEmail());

	        // Remove previous entry (if exists)
	        typingNotificationRepository.deleteBySenderEmailAndRecipientEmail(
	            typingNotification.getSenderEmail(), typingNotification.getRecipientEmail()
	        );
	        System.out.println("✅ Deletion completed (if any record existed)");

	        // Save new typing status
	        TypingNotification entity = new TypingNotification(
	            typingNotification.getSenderEmail(),
	            typingNotification.getRecipientEmail(),
	            typingNotification.isTyping()
	        );
	        typingNotificationRepository.save(entity);

	        return typingNotification; // Broadcast the message to all subscribers
	    }

	  
	  @GetMapping("/getTypingStatus")
	    public ResponseEntity<Map<String, Integer>> getTypingStatus(
	            @RequestParam String senderEmail,
	            @RequestParam String recipientEmail) {

	        TypingNotification typingNotification = typingNotificationRepository.findBySenderEmailAndRecipientEmail(senderEmail, recipientEmail);
	        Map<String, Integer> response = new HashMap<>();
	        response.put("typing", typingNotification != null ? (typingNotification.isTyping() ? 1 : 0) : 0);
	        
	        return ResponseEntity.ok(response);
	    }


	  @PostMapping("/updateUserStatusOnClose")
	 	  public ResponseEntity<Void> updateUserStatusOnClose(@RequestParam String email) {
	 	      System.out.println("User closed the tab. Updating status for: " + email);

	 	      if (email != null && !email.isEmpty()) {
	 	          activeUserService.removeActiveUser(email);
	 	          activeUserRepository.deleteById(email);
	 	          userStatusService.updateUserStatus(email);
	 	      }

	 	      return ResponseEntity.ok().build();
	 	  }

	      @PostMapping("/updateUserStatusOnReload")
	 	  public ResponseEntity<Void> updateUserStatusOnReload(@RequestParam String email) {

		      if (email != null && !email.isEmpty()) {
		    	  
		    	  // Add user to activeUsers Set (Thread-safe Set)
	              activeUserService.addActiveUser(email); 
	              
	              // ✅ Save Active User upon successful login
	              activeUserRepository.save(new ActiveUser(email));

	              
	              // Update user status to online (true)
	              userStatusService.updateUserStatustrue(email, true);

			      
		      }

		      return ResponseEntity.ok().build();
		  }

}
