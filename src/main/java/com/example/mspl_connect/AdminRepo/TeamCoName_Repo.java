package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.TeamCoName_Entity;


@Repository
public interface TeamCoName_Repo extends JpaRepository<TeamCoName_Entity, String>{

	@Query(nativeQuery = true, value = "SELECT e.empid, CONCAT(e.f_name, ' ', e.l_name) AS team_co_name FROM employee_details e INNER JOIN roles r ON r.role_id = e.role_id and r.dept_id = e.dept_id WHERE r.dept_id = :dept_id AND r.role_id = 77")
	List<TeamCoName_Entity> fetchTeamCoNameByDept(@Param("dept_id") int dept_id);
}
