package com.example.mspl_connect.AdminController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.UserStatus;
import com.example.mspl_connect.AdminRepo.UserStatusRepository;


@RestController

public class UserStatusController {

	
	 @Autowired
	    private UserStatusRepository userStatusRepository;
	
	@GetMapping("/last-seen/{email}")
    public ResponseEntity<?> getLastSeen(@PathVariable String email) {
		System.out.println("last seen "+email);
        Optional<UserStatus> userStatusOpt = userStatusRepository.findByEmail(email);
        System.out.println("last seenxx "+userStatusOpt);
        if (userStatusOpt.isPresent()) {
            UserStatus userStatus = userStatusOpt.get();
            return ResponseEntity.ok(userStatus);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
	
	
}
