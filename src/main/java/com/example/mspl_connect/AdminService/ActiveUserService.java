package com.example.mspl_connect.AdminService;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ActiveUserService {

	 private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();

	    public void addActiveUser(String email) {
	        activeUsers.add(email);
	    }

	    public void removeActiveUser(String email) {
	        activeUsers.remove(email);
	    }

	    public Set<String> getActiveUsers() {
	        return activeUsers;
	    }
	    
}
