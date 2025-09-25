package com.example.mspl_connect;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	    @Bean
	    public JavaMailSender mailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.ionos.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("noreply@melangesystems.com"); // Replace with your email
	        mailSender.setPassword("N00RepL@y@MsPL3456");         // Replace with your email password

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        //props.put("mail.debug", "true");

	        return mailSender;
	    }	
	    
}
