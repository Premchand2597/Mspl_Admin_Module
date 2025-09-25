package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EmployeeProjectProgressDTO2;


@Repository
public interface EmployeeProjectStatusRepo extends JpaRepository<EmployeeProjectProgressDTO2,String>{
	
	@Query(value="SELECT CONCAT(e.first_name, ' ', e.last_name) AS empname, tp.email, dt.task_weight AS total_task, SUM(tp.percent) AS completed_task FROM team_progress tp INNER JOIN (SELECT email_id, project_id, SUM(task_weight) AS task_weight FROM moat GROUP BY email_id, project_id) dt ON dt.email_id = tp.email AND dt.project_id = tp.project_id INNER JOIN employee_details e ON e.email = tp.email WHERE tp.project_id = :prjID AND tp.task_status = 'completed' GROUP BY empname, tp.email",nativeQuery = true)
	List<EmployeeProjectProgressDTO2> findByProjectId(String prjID);

}
