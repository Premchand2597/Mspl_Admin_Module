package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.SubDeptName_Entity;


@Repository
public interface SubDeptName_Repo extends JpaRepository<SubDeptName_Entity, String>{

	@Query(nativeQuery = true, value = "select sub_dept_name from departments where dept_id = :dept_id")
	List<SubDeptName_Entity> fetchSubDeptNameByDept(@Param("dept_id") int dept_id);
}
