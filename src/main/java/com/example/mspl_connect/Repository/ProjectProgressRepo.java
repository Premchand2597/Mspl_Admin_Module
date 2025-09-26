package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.ProjectProgress;


@Repository
public interface ProjectProgressRepo extends JpaRepository<ProjectProgress, String>{

	    // get the project progress
		@Query(value="select * from team_progress where project_id= :projectId",nativeQuery = true)
		List<ProjectProgress> findProjectDetailsByProjectId(String projectId);
	
}
