package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ActiveUser;

@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUser, String>{
	
	@Query("SELECT a.email FROM ActiveUser a")
	List<String> findAllEmails();

}
