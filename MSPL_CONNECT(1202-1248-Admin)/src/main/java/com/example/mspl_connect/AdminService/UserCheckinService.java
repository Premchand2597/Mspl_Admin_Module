package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.UserCheckin;
import com.example.mspl_connect.AdminRepo.UserCheckinRepository;


@Service
public class UserCheckinService {

	
	   @Autowired
	    private UserCheckinRepository userCheckinRepository;

	    public List<UserCheckin> findByEmpIdAndDate(String empId, String date) {
	    	
	        return userCheckinRepository.findByEmpIdAndCheckinDate(empId, date);
	    }
}
