package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CompProjectCardDetails_Entity;

@Repository
public interface CompProjectCardDetails_Repo extends JpaRepository<CompProjectCardDetails_Entity, Integer>{

	@Query(nativeQuery = true, value="SELECT id, project_name, project_status, project_id FROM (SELECT id, project_name, project_id, CASE WHEN (flag = '1' OR flag = '0') AND completed_date = 'Pending' THEN 'Assigned Project' WHEN flag = '2' AND completed_date = 'Pending' AND status = 'pending' THEN 'Ongoing Project' WHEN flag = '3' AND completed_date = 'Pending' AND status = 'pending' THEN 'Delayed Project' WHEN flag = '4' AND completed_date <> 'Pending' AND status = 'Completed' THEN 'Completed Project' ELSE 'other_status' END AS project_status FROM team_project) AS subquery WHERE project_status <> 'other_status' order by CASE project_status WHEN 'Assigned Project' THEN 1  WHEN 'Ongoing Project' THEN 2 WHEN 'Delayed Project' THEN 3 WHEN 'Completed Project' THEN 4 ELSE 5 END")
	List<CompProjectCardDetails_Entity> fetchProjectDetailsOnCard();
}
