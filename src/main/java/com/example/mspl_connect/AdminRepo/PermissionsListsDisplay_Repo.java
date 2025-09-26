package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.PermissionsListsDisplay_Entity;


@Repository
public interface PermissionsListsDisplay_Repo extends JpaRepository<PermissionsListsDisplay_Entity, Long>{

//	@Query(nativeQuery = true, value = "select employee_details.id as emp_details_table_id, employee_details.empid as emp_details_table_empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, permissions.* from employee_details left join permissions on permissions.emp_id = employee_details.empid inner join roles on employee_details.role_id = roles.role_id where roles.role_id <> '0' order by CAST(SUBSTRING(employee_details.empid, 3) AS UNSIGNED) ASC")
//	List<PermissionsListsDisplay_Entity> fetchAllPermissionsDetails();
	
	@Query(nativeQuery = true, value = "select employee_details.id as emp_details_table_id, employee_details.empid as emp_details_table_empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, permissions.* from employee_details left join permissions on permissions.emp_id = employee_details.empid inner join roles on employee_details.role_id = roles.role_id where employee_details.employee_type <> '0' and roles.role_id <> '0' order by CAST(SUBSTRING(employee_details.empid, 3) AS INTEGER) ASC")
	List<PermissionsListsDisplay_Entity> fetchAllPermissionsDetails();
	
//	@Query(nativeQuery = true, value = "select employee_details.id as emp_details_table_id, employee_details.empid as emp_details_table_empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, permissions.* from employee_details left join permissions on permissions.emp_id = employee_details.empid inner join roles on employee_details.role_id = roles.role_id where roles.role_id <> '0' and employee_details.empid IN (:emp_ids) order by CAST(SUBSTRING(employee_details.empid, 3) AS UNSIGNED) ASC")
//	List<PermissionsListsDisplay_Entity> fetchPermissionsDetailsBasedOnEmpId(@Param("emp_ids") List<String> empIds);
	
	@Query(nativeQuery = true, value = "select employee_details.id as emp_details_table_id, employee_details.empid as emp_details_table_empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, permissions.* from employee_details left join permissions on permissions.emp_id = employee_details.empid inner join roles on employee_details.role_id = roles.role_id where roles.role_id <> '0' and employee_details.empid IN (:emp_ids) order by CAST(SUBSTRING(employee_details.empid, 3) AS Integer) ASC")
	List<PermissionsListsDisplay_Entity> fetchPermissionsDetailsBasedOnEmpId(@Param("emp_ids") List<String> empIds);
}
