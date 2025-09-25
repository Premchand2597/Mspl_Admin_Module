package com.example.mspl_connect.DispatchService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.AuditLog;
import com.example.mspl_connect.DispatchRepo.AuditLogRepository;

@Service
public class AuditService {
	
	@Autowired 
	private AuditLogRepository auditLogRepository;
	
	 public void logAction(String actionType, String performedBy, String affectedUser) {
	        AuditLog log = new AuditLog(actionType, performedBy, affectedUser, LocalDateTime.now());
	        auditLogRepository.save(log);
	    }
	 
	 public List<AuditLog> getAllLogs() {
		    return auditLogRepository.findAllByOrderByIdDesc(); 
		}


}
