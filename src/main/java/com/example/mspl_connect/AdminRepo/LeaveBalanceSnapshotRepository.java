package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveBalanceSnapshot;

@Repository
public interface LeaveBalanceSnapshotRepository extends JpaRepository<LeaveBalanceSnapshot, Long>{

	// Fetch by both requestId and employeeEmail
    List<LeaveBalanceSnapshot> findByRequestIdAndEmployeeEmail(Integer requestId, String employeeEmail);
	
}
