package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LOP;


@Repository
public interface LOPRepository extends JpaRepository<LOP, Integer> {

}
