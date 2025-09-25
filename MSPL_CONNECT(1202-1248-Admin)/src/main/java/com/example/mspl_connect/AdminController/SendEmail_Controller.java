package com.example.mspl_connect.AdminController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminService.SendEmail_Service;





@RestController
public class SendEmail_Controller {
	
	@Autowired
	private SendEmail_Service sendEmail_Service;

	 @PostMapping("/sendLinkToEmail")
	    public ResponseEntity<String> sendLinks(@RequestBody Map<String, String> requestData) {
	        try {
	            String email = requestData.get("email");
	            String link = requestData.get("link");
	            sendEmail_Service.sendEmailWithLink(email, link);
	            return ResponseEntity.ok("Link sent successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	}