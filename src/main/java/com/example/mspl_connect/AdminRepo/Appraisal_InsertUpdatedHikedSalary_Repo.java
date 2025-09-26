package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Appraisal_InsertUpdatedHikedSalary_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Appraisal_InsertUpdatedHikedSalary_Repo extends JpaRepository<Appraisal_InsertUpdatedHikedSalary_Entity, Long>{

	@Modifying
    @Transactional
	@Query(nativeQuery = true, value="insert into salary_details(emp_id, emp_name, total_performance, old_salary, hike, salary_after_hike, financial_year, hike_affect_from, remarks) values(:emp_id, :emp_name, :total_performance, :old_salary, :hike, :salary_after_hike, :financial_year, :hike_affect_from, :remarks)")
	void saveAllAprraisalUpadtedSalaryAfterHike(@Param("emp_id") String emp_id, @Param("financial_year") String financial_year, 
	@Param("emp_name") String emp_name, @Param("total_performance") String total_performance, 
	@Param("old_salary") String old_salary, @Param("hike") String hike, @Param("salary_after_hike") String salary_after_hike, 
	@Param("remarks") String remarks, @Param("hike_affect_from") String hike_affect_from);
	
	
	@Query(nativeQuery = true, value = "Select * from salary_details where emp_id=:emp_id order by id desc")
	List<Appraisal_InsertUpdatedHikedSalary_Entity> fetchHikedSalaryByUsingEmpId(@Param("emp_id") String emp_id);
}
