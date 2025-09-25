package com.example.mspl_connect.DispatchRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.DispatchEntity.AuditLog;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
	List<AuditLog> findAllByOrderByIdDesc();
}
