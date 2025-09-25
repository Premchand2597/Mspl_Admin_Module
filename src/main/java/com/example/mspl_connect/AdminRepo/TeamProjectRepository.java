package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.TeamProject;

import jakarta.transaction.Transactional;

@Repository
public interface TeamProjectRepository extends JpaRepository<TeamProject, Long> {

	/*List<TeamProject> findByDepartmentAndStatus(String department, String status);*/
	

	@Query("SELECT tp FROM TeamProject tp WHERE tp.team_members_email LIKE %:email% AND tp.Assign_flag IN ('2', '3')")
	List<TeamProject> findByTeamMembersEmailAndAssignFlag(@Param("email") String email);

	// List<TeamProject> findByTeamMembersEmailContainingAndAssignFlag(String email, String assignFlag);
	List<TeamProject> findByDepartment(String department);


	/* @Query("SELECT tp FROM TeamProject tp WHERE tp.team_members_email LIKE %:email% AND tp.Assign_flag IN ('2', '3')")
	    List<TeamProject> findByTeamMembersEmailContainingAndAssignFlagIn(String email, List<String> flags);*/
	  @Query("SELECT tp FROM TeamProject tp WHERE tp.team_members_email LIKE %:email%")
	    List<TeamProject> findByTeamMembersEmail(@Param("email") String email);
	  
	  @Query("SELECT tp.project_name FROM TeamProject tp WHERE tp.project_name = :projectName")
	    String findProjectName(@Param("projectName") String projectName);
	  
	  @Query("SELECT tp.project_name FROM TeamProject tp WHERE tp.project_name = :projectName")
	  String findProjectNames(@Param("projectName") String projectName);
	  
	/*  @Query("SELECT tp.project_name FROM TeamProject tp WHERE tp.project_name = :projectName")
	 List <String> findnewProjectNames(@Param("projectName") List<String> projectName);*/
	  
	  @Query("SELECT tp.project_name FROM TeamProject tp WHERE tp.project_name IN :projectNames")
	  List<String> findnewProjectNames(@Param("projectNames") List<String> projectNames);


	  //Optional<TeamProject> findByProject_Id(Long projectId);
	  
	  
	  @Query("SELECT DISTINCT tp.project_name FROM TeamProject tp WHERE tp.team_members_email LIKE %:email%")
	    List<String>  findProjectNamesByEmail(@Param("email") String email);

	  @Query("SELECT tp FROM TeamProject tp WHERE tp.team_members_email LIKE %:email%")
	  List<TeamProject> findProjectsByEmail(@Param("email") String email);

	//Optional<TeamProject> findByProjectId(Long projectId);

	@Query(nativeQuery = true, value="Select project_id from team_project where project_id = :projectId")
	String getProjectId(@Param("projectId") String projectId);
	
	@Query(nativeQuery = true, value="Select id from team_project where projectId = :autoId")
	String getAutoId(@Param("autoId") String autoId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update team_project set assign_flag =:flagValue where project_id = :projectId")
	void setAssignFlag(@Param("flagValue") String flagValue, @Param("projectId") String projectId);

	 @Query("SELECT t FROM TeamProject t WHERE t.project_id = :projectId")
	    Optional<TeamProject> findByProject_id(@Param("projectId") String projectId);
	
}
