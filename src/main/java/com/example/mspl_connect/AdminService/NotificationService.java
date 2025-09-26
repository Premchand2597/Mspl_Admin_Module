package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Repository.LeaveApplicationRepo;

@Service
public class NotificationService {
	
	@Autowired
	private LeaveApplicationRepo applicationRepo;
	
	public int getNewLeaveRequestCount(int deptId,String empId) {
		return applicationRepo.getNewRequestCount(empId);
	}
	
	public int getNewRequestCountForSA(String empId) {
		return applicationRepo.getNewRequestCountForSA(empId);
	}
	
	public int getLeaveStatusChange(String empId) {
		return applicationRepo.getNotificationStatusByEmailId(empId);
	}
	
	
	
}
