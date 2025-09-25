package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectRepositoryCustom {
	 int fetchNewProjectCounts(String email, String projectName);
	 
	 @Modifying
	 @Transactional
	void updateNotifyFlag(String email,List<String> projectName);
	// int fetchNewProjectCounts(String email, List<String> projectNames);
	// void updateAcceptanceDate(Long projectId, String email);
	// String findAcceptanceDate(String projectId, String email,String taskName);

	List<Map<String, String>> findProjectDetails(String projectId, String email);

	void updateAcceptanceDate(String projectId, String email, String taskName, String subtaskName);

	void updateTaskStatus(String projectId, String email, String taskName,String subtaskName, String status);

	Map<String, String> findAcceptanceDateAndDueDate(String projectId, String email, String taskName, String subtaskName);

	List<EmployeeProjectProgressEntity> findByProjectIdAndTableName(String projectId, String tableName,String email);
	//List<EmployeeProjectProgressEntity> findByProjectIdAndTableName( String tableName);
	
}
