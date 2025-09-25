package com.example.mspl_connect.PayslipController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.PayslipEntity.EmailAuthRequest;
import com.example.mspl_connect.PayslipEntity.SenderCredentialStorage;
import com.example.mspl_connect.PayslipService.EmailService;

@RestController
@RequestMapping("/api")
public class EmailValidationController {

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private SenderCredentialStorage credentialStorage; // Inject storage bean

    @PostMapping("/validateSenderCredentials")
    public ResponseEntity<String> validateSenderCredentials(@RequestBody EmailAuthRequest request) {
    	
    	//System.out.println("request--"+request.getEmail());
    	//System.out.println("request--"+request.getPassword());
    	
        boolean isValid = emailService.validateCredentials(request.getEmail(), request.getPassword());
        
        if (isValid) {
        	credentialStorage.setCredentials(request.getEmail(), request.getPassword()); // Store credentials
            return ResponseEntity.ok("Valid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or Password");
        }
    }
}
