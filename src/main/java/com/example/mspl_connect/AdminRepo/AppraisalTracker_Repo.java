package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalTracker_Entity;


@Repository
public interface AppraisalTracker_Repo extends JpaRepository<AppraisalTracker_Entity, Long>{
//	@Query(nativeQuery = true, value="SELECT apprisal_hr.id, ed.empid, ed.email, d.dept_name, concat(ed.f_name,' ',ed.l_name) as full_name, apprisal_hr.apprisal_send_date, case when ed.usertype = \"Admin\" then apprisal_hr.apprisal_submit_date_admin else apprisal_hr.apprisal_submit_date end as due_date, case when permissions.apprisal_link = 0 and apprisal_hr.apprisal_link_flag = \"0\" then \"Submitted\" else \"Not Submitted\" end as appraisal_status, case when ed.usertype = \"Admin\" then apprisal_by_admin.submitted_date else appraisal_employee.submitted_date end as appraisal_submitted_date, case when ed.usertype = \"Admin\" then apprisal_admin_evaluated.validated_date else appraisal_admin.validated_date end as appraisal_validated_date, CONCAT(ev.f_name, ' ', ev.l_name) AS validated_emp_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id inner join apprisal_hr on apprisal_hr.email = ed.email and apprisal_hr.emp_id = ed.empid left join appraisal_employee on apprisal_hr.emp_id = appraisal_employee.emp_id left join apprisal_by_admin on apprisal_by_admin.emp_id = apprisal_hr.emp_id left join appraisal_admin on appraisal_admin.emp_id = apprisal_hr.emp_id left join apprisal_admin_evaluated on apprisal_admin_evaluated.emp_id = apprisal_hr.emp_id inner join permissions on permissions.emp_id = ed.empid LEFT JOIN employee_details ev ON ev.empid = appraisal_admin.validated_by where r.role_id <> \"0\" and apprisal_hr.quarter_month_year = :clickedQuarterMonthYear order by CAST(SUBSTRING(ed.empid, 3) AS UNSIGNED) ASC")
//	List<AppraisalTracker_Entity> fetchAppraisalTrackedBasedOnClickedMonthAndYear(@Param("clickedQuarterMonthYear") String clickedQuarterMonthYear);
	
	@Query(nativeQuery = true, value="""
			
			SELECT apprisal_hr.id, ed.empid, ed.email, d.dept_name, concat(ed.f_name,' ',ed.l_name) as full_name, apprisal_hr.apprisal_send_date, case when 
			ed.usertype = 'Admin' then apprisal_hr.apprisal_submit_date_admin else apprisal_hr.apprisal_submit_date end as due_date, case when 
			permissions.apprisal_link = false and apprisal_hr.apprisal_link_flag = '0' then 'Submitted' else 'Not Submitted' end as appraisal_status, 
			case when ed.usertype = 'Admin' then apprisal_by_admin.submitted_date else appraisal_employee.submitted_date end as appraisal_submitted_date, 
			case when ed.usertype = 'Admin' then apprisal_admin_evaluated.validated_date else appraisal_admin.validated_date end as appraisal_validated_date, 
			CONCAT(ev.f_name, ' ', ev.l_name) AS validated_emp_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join 
			roles r on ed.role_id = r.role_id inner join apprisal_hr on apprisal_hr.email = ed.email and apprisal_hr.emp_id = ed.empid left join 
			appraisal_employee on apprisal_hr.emp_id = appraisal_employee.emp_id left join apprisal_by_admin on apprisal_by_admin.emp_id = apprisal_hr.emp_id 
			left join appraisal_admin on appraisal_admin.emp_id = apprisal_hr.emp_id left join apprisal_admin_evaluated on 
			apprisal_admin_evaluated.emp_id = apprisal_hr.emp_id inner join permissions on permissions.emp_id = ed.empid LEFT JOIN employee_details ev ON 
			ev.empid = appraisal_admin.validated_by where r.role_id <> '0' and apprisal_hr.quarter_month_year = :clickedQuarterMonthYear order by 
			CAST(SUBSTRING(ed.empid, 3) AS INTEGER) ASC;
						
						""")
				List<AppraisalTracker_Entity> fetchAppraisalTrackedBasedOnClickedMonthAndYear(@Param("clickedQuarterMonthYear") String clickedQuarterMonthYear);
}
