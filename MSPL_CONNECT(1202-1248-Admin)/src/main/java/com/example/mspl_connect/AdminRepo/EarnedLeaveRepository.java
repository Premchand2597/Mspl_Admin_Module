package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EarnedLeave;

@Repository
public interface EarnedLeaveRepository extends JpaRepository<EarnedLeave, Long> {

	boolean existsByEmailAndMonthYear(String email, String monthYear);

	EarnedLeave findByEmail(String employeeEmail);

	EarnedLeave findByEmailAndMonthYear(String employeeEmail, String monthYearFromDate);

	List<EarnedLeave> findAllByEmail(String userEmail);

}
