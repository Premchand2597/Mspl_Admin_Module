package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.fetchReportingManagerEmailByEmployeeEmail_Entity;


@Repository
public interface fetchReportingManagerEmailByEmployeeEmail_Repo extends JpaRepository<fetchReportingManagerEmailByEmployeeEmail_Entity, String>{

	@Query(nativeQuery = true, value = "SELECT e1.email AS first_reporting_manager_email, e2.email AS second_reporting_manager_email FROM employee_details e1 LEFT JOIN employee_details e2 ON e2.empid = (SELECT team_co_name FROM employee_details WHERE email = :employee_email) WHERE e1.empid = (SELECT team_lead_name FROM employee_details WHERE email = :employee_email)")
	fetchReportingManagerEmailByEmployeeEmail_Entity getReportingMangersEmailId(@Param("employee_email") String employee_email);
}
