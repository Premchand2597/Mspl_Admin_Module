package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EmployeesUnderReportingManager_Entity;


@Repository
public interface EmployeesUnderReportingManager_Repo extends JpaRepository<EmployeesUnderReportingManager_Entity, Long>{

	@Query(nativeQuery = true, value="""
			
SELECT ed.id, concat(ed.f_name,' ' ,ed.l_name) as employees_under_reporting_manager, d.dept_name FROM employee_details ed inner join departments d on 
d.dept_id = ed.dept_id WHERE :emp_id in (ed.team_lead_name, ed.team_co_name) AND ed.empid != :emp_id order by employees_under_reporting_manager asc;
			
			""")
	List<EmployeesUnderReportingManager_Entity> getAllEmployeesWorkingUnderReportingManager(@Param("emp_id") String emp_id);;
}
