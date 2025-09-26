package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.ApprisalForAdmin;
import com.example.mspl_connect.AdminRepo.AdminKRASubmitRepo;

@Service
public class AdminKRASubmitService {
	
	@Autowired
	private AdminKRASubmitRepo adminKRASubmitService;

    public ApprisalForAdmin saveadminKRA(ApprisalForAdmin evaluation) {    	
    	
    	System.out.println("evaluation="+evaluation);
    	
    	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	String formatter = LocalDateTime.now().format(dateTime);
    	
    	evaluation.setSubmitted_date(formatter);
    	
        return adminKRASubmitService.save(evaluation);
    }

}
