package com.example.mspl_connect.DispatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchEntity.User;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;
import com.example.mspl_connect.DispatchRepo.UserRepository;


@Service
public class UserService {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private AddUserRepository addUserRepository;
	 
	 	public AddUser findByEmail(String email) {
	        return addUserRepository.findByEmailId(email);
	    }

	    public User save(User user) {
	        return userRepository.save(user);
	    }

	}
