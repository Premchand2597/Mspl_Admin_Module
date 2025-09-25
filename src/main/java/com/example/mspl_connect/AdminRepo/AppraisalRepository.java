package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Appraisal;


@Repository
public interface AppraisalRepository extends JpaRepository<Appraisal, Long> {

	@Query(nativeQuery = true,value="select ae.*,concat(e.f_name,' ',e.l_name) as emp_name from appraisal_employee ae inner join employee_details e on  ae.emp_id = e.empid where ae.department=:dept and ae.apprisal_admin_flag=1")
	List<Appraisal> findByDepartmentAndApprisalAdminFlag(String dept);

	@Query(nativeQuery = true,value="SELECT * FROM appraisal_employee where emp_id=:empid and apprisal_admin_flag='1'")
	Appraisal findByEmpId(String empid);
	
	@Query(value="SELECT apprisal_submit_date_admin FROM apprisal_hr where apprisal_link_flag=1 and emp_id=:empId ORDER BY apprisal_submit_date_admin DESC LIMIT 1", nativeQuery = true)
	String getDueDateByEmpid(String empId);
	
	/*
	 * @Query(nativeQuery = true,
	 * value="select count(apprisal_admin_flag) from appraisal_employee where department=:adminDept and apprisal_admin_flag=1"
	 * )
	 */
	//Integer getEmployeesApprisalForPermission(String adminDept);
	
	// Custom default implementation to return 1 without executing a query

//	@Query(nativeQuery = true,value="SELECT count(*) FROM appraisal_employee where apprisal_admin_flag=1 and department=:adminDept")
//    Optional<Integer> getEmployeesApprisalForPermission(String adminDept);
	
	@Query(nativeQuery = true,value="""
			
			SELECT count(*) FROM appraisal_employee where apprisal_admin_flag::int=1 and department=:adminDept;
			
			""")
    Optional<Integer> getEmployeesApprisalForPermission(String adminDept);
    
//	@Query(nativeQuery = true,value = "SELECT CASE WHEN EXISTS (SELECT 1  FROM apprisal_hr WHERE apprisal_link_flag = 1 AND emp_id = :empId AND apprisal_submit_date_admin >= CURRENT_DATE ) THEN 1 ELSE 0  END AS result")
//	Optional<Integer> getHrAppraisalFlagForNotification(String empId);
	
	@Query(nativeQuery = true,value = """
			
			SELECT CASE WHEN EXISTS (SELECT 1 FROM apprisal_hr WHERE apprisal_link_flag::int = 1 AND emp_id = :empId AND apprisal_submit_date_admin >= 
			CURRENT_DATE ) THEN 1 ELSE 0  END AS result;
						
				""")
		Optional<Integer> getHrAppraisalFlagForNotification(String empId);
	
//	@Query(value="select apprisal_submit_date_admin from apprisal_hr where apprisal_link_flag=1 and emp_id=:empId  ORDER BY apprisal_submit_date_admin DESC LIMIT 1",nativeQuery = true)
//	Optional<String> submissionDate(String empId);
	
	@Query(value="""
			
	select apprisal_submit_date_admin from apprisal_hr where apprisal_link_flag::int=1 and emp_id=:empId  ORDER BY apprisal_submit_date_admin DESC LIMIT 1;
				
			""",nativeQuery = true)
	Optional<String> submissionDate(String empId);

}
