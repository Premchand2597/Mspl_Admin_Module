package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendEmail_Service {
	
	@Autowired
    private JavaMailSender mailSender;

	public String sendEmailWithLink(String email, String link) throws Exception {
	  	  MimeMessage message = mailSender.createMimeMessage();

	        try {
	      	  
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            String originalSenderEmail = "noreply@melangesystems.com";
	            
	            helper.setFrom(originalSenderEmail);
	            helper.setTo(email);
	            helper.setSubject("Generated Link");
	            helper.setText("Dear candidate,\nGreetings from Melange Systems Pvt Ltd!!\nAll the best for your online test.\n\nPlease use below link for online test \n"+link+"\n\nRegards,\nThe Melange Team.");
	            
	            mailSender.send(message);
	            return "Success";
	            
	        } catch (MessagingException | MailException e) {
	            System.out.println(e);
	            throw new Exception("Failed to send email");
	        }
	    }
	}