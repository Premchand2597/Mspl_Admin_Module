package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MissedPunchEmpList_Entity;


@Repository
public interface MissedPunchEmpList_Repo extends JpaRepository<MissedPunchEmpList_Entity, String>{

	/*@Query(nativeQuery = true, value = "SELECT employee_details.empid, CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name, departments.dept_name, COUNT(DISTINCT daily_attendance.pd) AS distinct_missed_days, SUM(CASE WHEN daily_attendance.missed_count = 1 THEN 1 ELSE 0 END) AS overall_missed_punches FROM employee_details LEFT JOIN ( SELECT eid, pd, COUNT(*) AS missed_count FROM attendance WHERE pd <> CURDATE() GROUP BY eid, pd HAVING COUNT(*) = 1) AS daily_attendance ON daily_attendance.eid = employee_details.empid INNER JOIN departments \r\n"
			+ "ON departments.dept_id = employee_details.dept_id WHERE YEAR(daily_attendance.pd) = :currentYear GROUP BY employee_details.empid, employee_details.f_name,employee_details.l_name, departments.dept_name ORDER BY overall_missed_punches desc")
	List<MissedPunchEmpList_Entity> fetchMissedPuchListData(@Param("currentYear") int currentYear);*/
	
//	@Query(nativeQuery = true, value = "SELECT employee_details.empid, CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name, departments.dept_name, COUNT(DISTINCT daily_attendance.pd) AS distinct_missed_days, SUM(CASE WHEN daily_attendance.missed_count = 1 THEN 1 ELSE 0 END) AS overall_missed_punches FROM employee_details LEFT JOIN (SELECT eid, pd, COUNT(*) AS missed_count FROM attendance WHERE pd <> CURDATE() AND pd >= '2025-01-01' GROUP BY eid, pd HAVING COUNT(*) = 1) AS daily_attendance ON daily_attendance.eid = employee_details.empid INNER JOIN departments ON departments.dept_id = employee_details.dept_id where employee_details.employee_type <> '0' GROUP BY employee_details.empid, employee_details.f_name,employee_details.l_name, departments.dept_name HAVING overall_missed_punches > 0 ORDER BY overall_missed_punches desc")
//	List<MissedPunchEmpList_Entity> fetchMissedPuchListData();
	
	@Query(nativeQuery = true, value = """
			
SELECT employee_details.empid, CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name, departments.dept_name, 
COUNT(DISTINCT daily_attendance.pd) AS distinct_missed_days, SUM(CASE WHEN daily_attendance.missed_count = 1 THEN 1 ELSE 0 END) AS 
overall_missed_punches FROM employee_details LEFT JOIN (SELECT eid, pd, COUNT(*) AS missed_count FROM attendance WHERE pd::date <> CURRENT_DATE AND 
pd >= '2025-01-01' GROUP BY eid, pd HAVING COUNT(*) = 1) AS daily_attendance ON daily_attendance.eid = employee_details.empid INNER JOIN departments 
ON departments.dept_id = employee_details.dept_id where employee_details.employee_type <> '0' GROUP BY employee_details.empid, 
employee_details.f_name,employee_details.l_name, departments.dept_name HAVING SUM(CASE WHEN daily_attendance.missed_count = 1 THEN 1 ELSE 0 END) > 0 
ORDER BY overall_missed_punches desc;
			
			""")
	List<MissedPunchEmpList_Entity> fetchMissedPuchListData();
}
