package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MoatLogin_Entity;
import com.example.mspl_connect.AdminRepo.MoatLogin_Repo;

@Service
public class MoatLogin_Service {
	
	@Autowired
	private MoatLogin_Repo moatLogin_Repo;
	
	public boolean authenticate(String email, String password) {
        MoatLogin_Entity user = moatLogin_Repo.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

}