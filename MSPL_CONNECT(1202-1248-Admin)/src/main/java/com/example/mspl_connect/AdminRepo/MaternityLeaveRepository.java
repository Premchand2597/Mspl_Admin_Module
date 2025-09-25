package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MaternityLeave;

@Repository
public interface MaternityLeaveRepository extends JpaRepository<MaternityLeave, Long> {
	List<MaternityLeave> findByEmail(String email);
}
