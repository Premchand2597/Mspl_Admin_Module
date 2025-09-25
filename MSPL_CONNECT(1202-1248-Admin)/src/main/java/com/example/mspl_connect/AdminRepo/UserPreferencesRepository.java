package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.UserPreferences;


@Repository
public interface UserPreferencesRepository  extends JpaRepository<UserPreferences, Long>{
	
	UserPreferences findByEmpId(String empId);

}
