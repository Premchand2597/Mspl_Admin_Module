package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Project_Chart_Entity;

@Repository
public interface Project_Chart_Repo  extends JpaRepository<Project_Chart_Entity, String>{

	@Query(
	        nativeQuery = true,
	        value = "WITH TotalTaskWeight AS (SELECT project_id, COALESCE(SUM(task_weight), 0) AS total_task_weight FROM daynamic_ref_table GROUP BY project_id) SELECT CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name, employee_details.email, COALESCE(tp.total_completed_work, 0) AS total_completed_work, COALESCE(ttw.total_task_weight, 0) AS overall_assigned_task FROM employee_details LEFT JOIN (SELECT email, COALESCE(SUM(CASE WHEN task_status = 'completed' THEN percent ELSE 0 END), 0) AS total_completed_work  FROM team_progress WHERE project_id = :projectId GROUP BY email) tp ON employee_details.email = tp.email LEFT JOIN TotalTaskWeight ttw ON ttw.project_id = :projectId WHERE employee_details.email IN (SELECT email_id FROM daynamic_ref_table WHERE project_id = :projectId) GROUP BY emp_name, employee_details.email, tp.total_completed_work, ttw.total_task_weight"
	    )
	    List<Object[]> getProjectPercentWithIndividualStatus(@Param("projectId") String projectId);
	}
