package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Employee;


@Repository
public interface FindEmployee extends JpaRepository<Employee, Integer>{
	// Define a method to fetch all employees
	
	/*
	 * @Query(
	 * value="SELECT * FROM employee_details where role='USER' and department = :department"
	 * ,nativeQuery = true) List<Employee> findByDepartment(String department);
	 */
	
	/*
	 * @Query(
	 * value="SELECT * FROM employee_details where department='IT' and designation = :designation"
	 * ,nativeQuery = true) List<Employee> findByDesignation(String designation);
	 */
	
	@Query(value="SELECT * FROM employee_details where dept_id='deptId'",nativeQuery = true)
	List<Employee> findUserByDepartment(int deptId);
	
}
