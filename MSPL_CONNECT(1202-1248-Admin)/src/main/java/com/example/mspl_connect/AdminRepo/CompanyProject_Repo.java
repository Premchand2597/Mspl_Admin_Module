package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CompanyProject_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface CompanyProject_Repo extends JpaRepository<CompanyProject_Entity, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM project order by completed_date desc")
	List<CompanyProject_Entity> fetchCompProjectsLists();
	
	@Query(nativeQuery = true, value = "select * from project where id=:auto_id")
	CompanyProject_Entity ediCompProjectData(@Param("auto_id") String auto_id);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update project set project_name=:project_name, description=:description, completed_date=:completed_date, project_manager=:project_manager, team_members=:team_members, department=:department, client=:client where id=:id")
	void update_compProject_details(@Param("id") int id, @Param("project_name") String project_name, @Param("description") String description, 
			@Param("completed_date") String completed_date, @Param("project_manager") String project_manager, 
			@Param("team_members") String team_members, @Param("department") String department, @Param("client") String client);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM project WHERE id=:id")
	void delete_companyProjectDetails(@Param("id") int id);

}
