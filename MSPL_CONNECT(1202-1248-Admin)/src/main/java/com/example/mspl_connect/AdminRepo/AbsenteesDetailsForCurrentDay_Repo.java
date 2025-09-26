package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mspl_connect.AdminEntity.AbsenteesDetailsForCurrentDay_Entity;


public interface AbsenteesDetailsForCurrentDay_Repo extends JpaRepository<AbsenteesDetailsForCurrentDay_Entity, Long>{

	@Query(nativeQuery = true, value = "SELECT employee_details.id, employee_details.empid as emp_id, concat(employee_details.f_name,' ',employee_details.l_name) as absent_emp_name, employee_details.profile_pic_path FROM employee_details LEFT JOIN attendance on employee_details.empid = attendance.eid AND attendance.pd = CURDATE() inner join roles on roles.role_id = employee_details.role_id  WHERE attendance.eid IS NULL and employee_details.role_id <> '0'")
	List<AbsenteesDetailsForCurrentDay_Entity> fetchAllAbsenteesForToday();
}
