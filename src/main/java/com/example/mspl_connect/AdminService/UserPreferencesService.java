package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.UserPreferences;
import com.example.mspl_connect.AdminRepo.UserPreferencesRepository;

@Service
public class UserPreferencesService {

	@Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public void saveUserPreferences(UserPreferences userPreferences) {
        userPreferencesRepository.save(userPreferences);
    }

    public UserPreferences findByEmpId(String empId) {
        return userPreferencesRepository.findByEmpId(empId);
    }
	
}
