package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatLogin_Entity;

@Repository
public interface MoatLogin_Repo extends JpaRepository<MoatLogin_Entity, Integer>{
	MoatLogin_Entity findByEmail(String email);
}
