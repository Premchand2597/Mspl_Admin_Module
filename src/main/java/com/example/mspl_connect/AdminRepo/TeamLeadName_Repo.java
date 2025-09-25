package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.TeamLeadName_Entity;


@Repository
public interface TeamLeadName_Repo extends JpaRepository<TeamLeadName_Entity, String>{

	@Query(nativeQuery = true, value = "SELECT e.empid, CONCAT(e.f_name, ' ', e.l_name) AS team_lead_name FROM employee_details e INNER JOIN roles r ON r.role_id = e.role_id and r.dept_id = e.dept_id order by team_lead_name")
	List<TeamLeadName_Entity> fetchTeamLeadNameByDept();
}
