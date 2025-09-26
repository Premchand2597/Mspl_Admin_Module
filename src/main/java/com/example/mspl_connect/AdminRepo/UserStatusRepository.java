package com.example.mspl_connect.AdminRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

	  Optional<UserStatus> findByEmail(String email);
	
}
