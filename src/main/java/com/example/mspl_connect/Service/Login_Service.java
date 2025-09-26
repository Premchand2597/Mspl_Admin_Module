package com.example.mspl_connect.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.Login_Entity;
import com.example.mspl_connect.Repository.Login_Repo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Login_Service {

	@Autowired
	private Login_Repo loginRepo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	public Login_Entity autheticate(String email, String password) {
		
		// Validate the hashed password
	    //String hashedPassword = hashPassword(password);'
		
		
	    return loginRepo.checkLoginValidation(email, password);
	    
	}
	
	private String hashPassword(String password) {
        try {
            // Create a SHA-256 digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());

            // Convert byte array into hex format
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }
	public String getLoggedInRoleName(String email) {
		return loginRepo.getRoleName(email);
	}
	
	public String getLoggedInUserType(String email) {
		return loginRepo.getUserTypeDetail(email);
	}
	
    public String generateOTP(String loggedInEmail) {
      Random random = new Random();
      int otpValue = 100_000 + random.nextInt(900_000);
      String otp = String.valueOf(otpValue);
      loginRepo.updateOTPinDB(otp, loggedInEmail);
      
      return otp;
    }
    
    public boolean validateOtp(String loggedInEmail, String otp) {
	    Login_Entity login_entity = loginRepo.checkValidationForOtp(loggedInEmail, otp);
	    //System.out.println(login_entity);
	    return login_entity != null && login_entity.getOtp().equals(otp);
	}
    
    public String sendEmailWithOTP(String email, String otp, String emp_name, String gender) throws Exception {
	  	  MimeMessage message = mailSender.createMimeMessage();
	  	  
		  	  String prefix = null;
		  	  
		  	  if(gender.equals("Male")) {
		  		prefix = "Mr.";
		  	  }else{
		  		prefix = "Ms.";
		  	  }
	        try {
	      	  
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            String fromEmail = "noreply@melangesystems.com"; // Replace with your authorized email address
	            //String originalSenderEmail = "premchand.s@melangesystems.com";
	            
	            // helper.setFrom(originalSenderEmail);
	            helper.setFrom(fromEmail); // Configure the "From" email address
	            helper.setTo(email);
	            
	            if(email.equals("superadminmsplconnect@melangesystems.com")) {
	            	helper.setCc("jagadeshan@melangesystems.com");
	            }
	            
	            helper.setSubject("Generated OTP"); 
	            helper.setText("Dear "+ prefix + emp_name + ",\n\nWe are sending you the One-Time Password (OTP) to securely access your account on our website.\n\nOTP: " + otp + "\n\nPlease enter this OTP on the website to complete your login process.\n\nThank you for using our services.\n\nBest regards,\nThe Melange Team");

	            mailSender.send(message);
	            return "Success: Email sent with OTP";
	            
	        } catch (MessagingException | MailException e) {
	            System.out.println(e);
	            throw new Exception("Failed to send email");
	        }
	    }
}
