package com.example.mspl_connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Repository.ChangeProjectStatusRepo;

import jakarta.transaction.Transactional;

@Service
public class ChangeProjectStatusService {

	@Autowired
	private ChangeProjectStatusRepo changeProjectStatusRepo;
	
	@Transactional
	public boolean setActionStatus(String id) {
		String status="Completed";
        int rowsAffected = changeProjectStatusRepo.changeLeaveApproveStatus(id,status);
        return rowsAffected > 0;
    }
}
