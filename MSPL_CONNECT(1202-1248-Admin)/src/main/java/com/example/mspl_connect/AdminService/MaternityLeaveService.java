package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MaternityLeave;
import com.example.mspl_connect.AdminRepo.MaternityLeaveRepository;

@Service
public class MaternityLeaveService {

	
	 @Autowired
	    private MaternityLeaveRepository
	    maternityLeaveRepository;

	    public void saveMaternityLeave(MaternityLeave maternityLeaveRequest) {
	    	System.out.println("ppioio");
	        MaternityLeave maternityLeave = new MaternityLeave(
	                null, // ID is auto-generated
	                maternityLeaveRequest.getEmpId(),
	                maternityLeaveRequest.getLeaveType(),
	                maternityLeaveRequest.getEmail(),
	                maternityLeaveRequest.getLeaveStartDate(),
	                maternityLeaveRequest.getLeaveEndDate(),
	                maternityLeaveRequest.getTotalLeaveDays(),
	                maternityLeaveRequest.getLeaveUsed(),
	                maternityLeaveRequest.getLeaveRemaining(),
	                maternityLeaveRequest.getApplicationDate()
	        );

	        maternityLeaveRepository.save(maternityLeave);
	    }
}
