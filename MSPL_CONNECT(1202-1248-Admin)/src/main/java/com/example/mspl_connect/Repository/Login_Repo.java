package com.example.mspl_connect.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Login_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Login_Repo extends JpaRepository<Login_Entity, Long>{
	
	@Query(nativeQuery = true, value = "select employee_details.id as sl_no, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.gender, employee_details.usertype, roles.role_name, employee_details.email, employee_details.password, employee_details.employee_type, employee_details.otp from employee_details inner join roles on employee_details.role_id = roles.role_id inner join departments on roles.dept_id = departments.dept_id where employee_details.email = :email and employee_details.password = :password and  (employee_details.usertype = 'Admin' or employee_details.usertype = 'Super Admin')")
	Login_Entity checkLoginValidation(@Param("email") String email, @Param("password") String password);

	@Query(nativeQuery = true, value = "select employee_details.id as sl_no, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.gender, employee_details.usertype, roles.role_name, employee_details.email, employee_details.password, employee_details.employee_type, employee_details.otp from employee_details inner join roles on employee_details.role_id = roles.role_id inner join departments on roles.dept_id = departments.dept_id where email = :email and otp = :otp")
	Login_Entity checkValidationForOtp(@Param("email") String email, @Param("otp") String otp);

	
	@Query(nativeQuery = true, value = "select roles.role_name from employee_details inner join roles on employee_details.role_id = roles.role_id and employee_details.dept_id = roles.dept_id where employee_details.email=:email")
	String getRoleName(String email);
	
	@Query(nativeQuery = true, value = "select usertype from employee_details where email=:email")
	String getUserTypeDetail(String email);
	
	@Query(nativeQuery = true, value = "select concat(f_name,' ',l_name) as fullname from mspl_connect.employee_details where email=:email")
	String getLoggedName(String email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update employee_details set otp=:otp where email=:loggedInEmail")
	void updateOTPinDB(String otp, String loggedInEmail);
	
}
