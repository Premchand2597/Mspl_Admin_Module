package com.example.mspl_connect.DispatchRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.DispatchEntity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
