package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalForDisplay;

@Repository
public interface AppraisalForDisplayRepo extends JpaRepository<AppraisalForDisplay, Long>{

	
	@Query(nativeQuery = true, value = "SELECT ae.*, CONCAT(e.f_name, ' ', e.l_name) AS emp_name " +
		       "FROM appraisal_employee ae " +
		       "INNER JOIN employee_details e ON ae.emp_id = e.empid " +
		       "WHERE ae.department = :dept AND ae.apprisal_admin_flag = 1")
		List<AppraisalForDisplay> findByDepartmentAndApprisalAdminFlag(String dept);
}
