package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminRepo.AnnnouncementNotificationRepo;

import jakarta.transaction.Transactional;

@Service
public class ChangeAnnouncementFlagValueService {

	@Autowired
	private AnnnouncementNotificationRepo annnouncementNotificationRepo;
	
	@Transactional
    public void changeAnnouncementFlagValueByEmpId(String empId) {
        annnouncementNotificationRepo.changeAnnouncementFlagValueByEmpId(empId);
    }
}
