package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EmployeeNamesBasedOnDept_Entity;


@Repository
public interface EmployeeNamesBasedOnDept_Repo extends JpaRepository<EmployeeNamesBasedOnDept_Entity, String>{

	@Query(nativeQuery = true, value = "select employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name from employee_details inner join departments on employee_details.dept_id = departments.dept_id where departments.dept_name =:dept_name")
	List<EmployeeNamesBasedOnDept_Entity> fetchEmpNamesBasedOnDeptName(@Param("dept_name") String dept_name);
}
