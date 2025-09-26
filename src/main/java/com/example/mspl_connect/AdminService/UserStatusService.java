package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.UserStatus;
import com.example.mspl_connect.AdminRepo.UserStatusRepository;

import jakarta.transaction.Transactional;

@Service
public class UserStatusService {

	 @Autowired
	    private UserStatusRepository userStatusRepository;

	    @Transactional
	    public void updateUserStatus(String email) {
	        Optional<UserStatus> userStatusOpt = userStatusRepository.findByEmail(email);
	        if (userStatusOpt.isPresent()) {
	            UserStatus userStatus = userStatusOpt.get();
	            userStatus.setOnline(false);
	            userStatus.setLastSeen(java.time.LocalDateTime.now());
	            userStatusRepository.save(userStatus);
	            System.out.println("User session expired, updating status to offline: " + email);
	        }
	    }
	
	    
	    @Transactional
	    public void updateUserStatustrue(String email, boolean isOnline) {
	        Optional<UserStatus> userStatusOpt = userStatusRepository.findByEmail(email);
	        UserStatus userStatus = userStatusOpt.orElse(new UserStatus());
	        
	        userStatus.setEmail(email);
	        userStatus.setOnline(isOnline);

	        if (!isOnline) {
	            userStatus.setLastSeen(LocalDateTime.now());
	        }

	        userStatusRepository.save(userStatus);
	        System.out.println("User status updated: " + email + " Online: " + isOnline);
	    }
}
