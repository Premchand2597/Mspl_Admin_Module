package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ProjectData;

@Repository
public interface ProjectDataRepository extends JpaRepository<ProjectData, Long>{
	
	 List<ProjectData> findByEmailId(String emailId);

	 @Query("SELECT DISTINCT tp FROM ProjectData tp WHERE tp.emailId = :email AND tp.employeeCompletionDate IS NOT NULL")
	 List<ProjectData> findProjectsByEmailWithCompletedSubtasks(@Param("email") String email);
	//List<ProjectData> findByProjectNameAndEmpId(String projectName, String empId);
	 List<ProjectData> findByProjectNameAndEmailId(String projectName, String emailId);

}
