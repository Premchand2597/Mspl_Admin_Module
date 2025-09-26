package com.example.mspl_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.NewCompanyProject_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface NewCompanyProject_Repo extends JpaRepository<NewCompanyProject_Entity, Integer>{
	
	boolean existsByprojectId(String projectId);
	
	@Query(value="SELECT id FROM team_project ORDER BY id DESC limit 1",nativeQuery = true)
	Integer  findLastId();
	
	@Query(nativeQuery = true, value="Select * from team_project where id=:id")
	NewCompanyProject_Entity fetchCompanyProjectBasedOnId(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update team_project set view_flag=0")
	void updateCompanyViewedPageFlag();
	
	
	NewCompanyProject_Entity findByProjectId(String prjId);
	
}
