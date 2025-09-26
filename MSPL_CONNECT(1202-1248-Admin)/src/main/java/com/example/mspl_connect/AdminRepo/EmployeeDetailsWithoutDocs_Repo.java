package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mspl_connect.AdminEntity.EmployeesEntityWithoutDocs_Entity;


public interface EmployeeDetailsWithoutDocs_Repo extends JpaRepository<EmployeesEntityWithoutDocs_Entity, Long>{

	@Query(nativeQuery = true,value="select ed.id,ed.empid,ed.email,ed.usertype,ed.sub_dept_name,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where team_lead_name=:empid or team_co_name=:empid  or empid = :empid ")
	public List<EmployeesEntityWithoutDocs_Entity> getAllEmployeesWithoutDocsDetails(String empid);
}
