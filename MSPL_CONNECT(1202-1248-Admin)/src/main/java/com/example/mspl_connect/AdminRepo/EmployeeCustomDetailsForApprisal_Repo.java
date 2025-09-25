package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;

@Repository
public interface EmployeeCustomDetailsForApprisal_Repo extends JpaRepository<EmployeeCustomDetailsForApprisal_Entity, Long>{

	@Query(nativeQuery = true, value = "select id, concat(f_name,' ',l_name) as emp_full_name, null as emp_id, null as dept, null as designation, null as email from employee_details")
	List<EmployeeCustomDetailsForApprisal_Entity> fetchAllEmployeeNames();
	
	@Query(nativeQuery = true, value = "select id, concat(employee_details.f_name,' ',employee_details.l_name) as emp_full_name, employee_details.empid as emp_id, departments.dept_name as dept, roles.role_name as designation, employee_details.email from employee_details inner join departments on departments.dept_id = employee_details.dept_id inner join roles on employee_details.role_id = roles.role_id and employee_details.dept_id = roles.dept_id and roles.dept_id = departments.dept_id where concat(employee_details.f_name,\" \",employee_details.l_name,\" \",id) = :emp_full_name_with_emp_id")
	EmployeeCustomDetailsForApprisal_Entity fetchEmployeeDetailsBasedOnEmpNames(@Param("emp_full_name_with_emp_id") String emp_full_name_with_emp_id);
}
