package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveBalanceDetailsList_Entity;


@Repository
public interface LeaveBalanceDetailsList_Repo extends JpaRepository<LeaveBalanceDetailsList_Entity, String>{

//	@Query(nativeQuery = true, value = "SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining END), 0) AS sl, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining END), 0) AS cl, COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days END), 0) AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details left join leave_utilized on leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join departments on departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave on earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and maternity_leave.emp_id = employee_details.empid where roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year GROUP BY empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, departments.dept_name, roles.role_name, earned_leave.elremaining, email, employee_details.gender order by CAST(SUBSTRING(employee_details.empid, 3) AS UNSIGNED) ASC")
//	List<LeaveBalanceDetailsList_Entity> fetchAllEmployeesLeaveBalanceData(@Param("current_financial_year") String current_financial_year);
	
	@Query(nativeQuery = true, 
			value = """ 
					
		SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, 
		employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, 
		COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining::numeric END), 0) AS sl, 
		COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining::numeric END), 0) AS cl, 
		COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days::numeric END), 0) 
		AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details left join leave_utilized on 
		leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join departments on 
		departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave on 
		earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and 
		maternity_leave.emp_id = employee_details.empid where employee_details.employee_type <> '0' and  roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year 
		GROUP BY employee_details.empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, departments.dept_name, roles.role_name, 
		earned_leave.elremaining, employee_details.email, employee_details.gender order by CAST(SUBSTRING(employee_details.empid FROM 3) AS INTEGER) ASC
					
					""")
	List<LeaveBalanceDetailsList_Entity> fetchAllEmployeesLeaveBalanceData(@Param("current_financial_year") String current_financial_year);

//	@Query(nativeQuery = true, value = "SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining END), 0) AS sl, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining END), 0) AS cl, COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days END), 0) AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details left join leave_utilized on leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join departments on departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave on earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and maternity_leave.emp_id = employee_details.empid where roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year and employee_details.email = :emp_email GROUP BY empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, departments.dept_name, roles.role_name, earned_leave.elremaining, email, employee_details.gender")
//	LeaveBalanceDetailsList_Entity fetchEmployeesLeaveBalanceDataBasedOnEmailAndFinancialYear(@Param("current_financial_year") String current_financial_year, @Param("emp_email") String emp_email);

	@Query(nativeQuery = true, value = """
			
SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, 
employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, 
COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining::numeric END), 0) AS sl, 
COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining::numeric END), 0) AS cl, 
COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days::numeric END), 0)
AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details 
left join leave_utilized on leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join 
departments on departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave 
on earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and 
maternity_leave.emp_id = employee_details.empid where roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year and 
employee_details.email = :emp_email GROUP BY employee_details.empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, 
departments.dept_name, roles.role_name, earned_leave.elremaining, employee_details.email, employee_details.gender;
			
			""")
	LeaveBalanceDetailsList_Entity fetchEmployeesLeaveBalanceDataBasedOnEmailAndFinancialYear(@Param("current_financial_year") String current_financial_year, @Param("emp_email") String emp_email);
	
//	@Query(nativeQuery = true, value = "SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining END), 0) AS sl, COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining END), 0) AS cl, COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days END), 0) AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details left join leave_utilized on leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join departments on departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave on earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and maternity_leave.emp_id = employee_details.empid where roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year and employee_details.empid = :empId GROUP BY empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, departments.dept_name, roles.role_name, earned_leave.elremaining, email, employee_details.gender order by CAST(SUBSTRING(employee_details.empid, 3) AS UNSIGNED) ASC")
//	LeaveBalanceDetailsList_Entity fetchEmployeesLeaveBalanceDataBasedOnEmpId(@Param("current_financial_year") String current_financial_year, @Param("empId") String empId);

	@Query(nativeQuery = true, 
			value = """
					
SELECT employee_details.empid, concat(employee_details.f_name,' ',employee_details.l_name) as emp_name, employee_details.email, 
employee_details.gender, leave_utilized.empid as merged_table_emp_id, leave_utilized.financial_year, 
COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Sick Leave' THEN leave_utilized.remaining::numeric END), 0) AS sl, 
COALESCE(MAX(CASE WHEN leave_utilized.leave_type = 'Casual Leave' THEN leave_utilized.remaining::numeric END), 0) AS cl, 
COALESCE(earned_leave.elremaining, 0) AS el, COALESCE(MAX(CASE WHEN maternity_leave.leave_type = 'ML' THEN maternity_leave.total_leave_days::numeric END), 0) 
AS ml, leave_utilized.user_email as merged_table_email, departments.dept_name, roles.role_name FROM employee_details left join leave_utilized on 
leave_utilized.user_email = employee_details.email and leave_utilized.empid = employee_details.empid inner join departments on 
departments.dept_id = employee_details.dept_id inner join roles on roles.role_id = employee_details.role_id left join earned_leave on 
earned_leave.email = employee_details.email left join maternity_leave on maternity_leave.email = employee_details.email and 
maternity_leave.emp_id = employee_details.empid where roles.role_id <> 0 and leave_utilized.financial_year = :current_financial_year and employee_details.empid = :empId
GROUP BY employee_details.empid, emp_name, leave_utilized.financial_year, merged_table_emp_id, merged_table_email, departments.dept_name, roles.role_name, 
earned_leave.elremaining, employee_details.email, employee_details.gender order by CAST(SUBSTRING(employee_details.empid FROM 3) AS INTEGER) ASC;
					
					""")
	LeaveBalanceDetailsList_Entity fetchEmployeesLeaveBalanceDataBasedOnEmpId(@Param("current_financial_year") String current_financial_year, @Param("empId") String empId);

}
