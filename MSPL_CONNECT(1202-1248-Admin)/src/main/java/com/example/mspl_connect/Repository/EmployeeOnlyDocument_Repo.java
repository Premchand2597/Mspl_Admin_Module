package com.example.mspl_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EmployeeOnlyDocument_Entity;


@Repository
public interface EmployeeOnlyDocument_Repo extends JpaRepository<EmployeeOnlyDocument_Entity, Long>{
	
	@Query(nativeQuery = true, value="SELECT id, empid, profile_pic_path, pan_pic_path, aadhar_pic, tenth_pic, puc_pic, degree_pic FROM employee_details WHERE empid = :empId")
	EmployeeOnlyDocument_Entity selectEmpDetailsForOnlyDocumentsViewById(String empId);
}
