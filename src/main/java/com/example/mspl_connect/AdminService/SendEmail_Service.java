package com.example.mspl_connect.AdminService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Service
public class SendEmail_Service {
	
	@Autowired
    private JavaMailSender mailSender;

	@Autowired
	private EmployeeRepository employeeRepository;

	public String sendEmailWithLink(String email, String link, HttpSession session) throws Exception {
	  	  MimeMessage message = mailSender.createMimeMessage();

	        try {
	        	
	        	Set<String> ccList = new HashSet<>();
	        	List<String> fethedHREmails = employeeRepository.getHREmailBasedOnDeptId("54");
	        	String loggedInEmail = (String) session.getAttribute("email");
	        	
	        	for(String hrEmail : fethedHREmails) {
	        		ccList.add(hrEmail);
	        		ccList.add(loggedInEmail);
	        	}
	      	  
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            String originalSenderEmail = "noreply@melangesystems.com";
	            
	            helper.setFrom(originalSenderEmail);
	            helper.setTo(email);
	            //helper.setCc(loggedInEmail);
	            helper.setCc(ccList.toArray(new String[0]));
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