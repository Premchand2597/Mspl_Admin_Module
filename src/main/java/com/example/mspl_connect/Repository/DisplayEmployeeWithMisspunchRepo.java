package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;

import jakarta.transaction.Transactional;

@Repository
public interface DisplayEmployeeWithMisspunchRepo extends JpaRepository<DisplayEmployessWithMissPunchEntity, Integer>{

//	@Procedure(name="employees_data_with_misspunch_for_admin")
//	@Query(nativeQuery = true, value="call employees_data_with_misspunch_for_admin(:loggedAdminEmpid)")
//	public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_admin(String loggedAdminEmpid);
	
	@Query(nativeQuery = true, value="""
			
			SELECT ed.*, 
		       d.dept_name, 
		       r.role_name, 
		       CONCAT(ed.f_name, ' ', ed.l_name) AS full_name, 
		       COALESCE(mp.misspunch_count, 0) AS misspunch_count,
		       p.attendance_link
		FROM employee_details ed
		INNER JOIN departments d ON ed.dept_id = d.dept_id
		INNER JOIN roles r ON ed.role_id = r.role_id
		INNER JOIN permissions p ON p.emp_id = ed.empid
		LEFT JOIN (
		    SELECT eid, COUNT(*) AS misspunch_count
		    FROM (
		        SELECT eid, pd::date
		        FROM attendance
		        WHERE pd >= '2025-01-01'
		          AND pd::date != CURRENT_DATE
		        GROUP BY eid, pd::date
		        HAVING COUNT(*) = 1
		    ) AS misspunches
		    GROUP BY eid
		) mp ON ed.empid = mp.eid
		WHERE (ed.team_lead_name = :loggedAdminEmpid OR ed.team_co_name = :loggedAdminEmpid) 
		  AND ed.empid != :loggedAdminEmpid and ed.employee_type::integer=1;

					
					""")
			public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_admin(String loggedAdminEmpid);
	
//	@Procedure(name="employees_data_with_misspunch_for_superadmin")
//	@Query(nativeQuery = true, value="call employees_data_with_misspunch_for_superadmin(:loggedAdminEmpid)")
//	public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_superadmin(String loggedAdminEmpid);
	
	@Query(nativeQuery = true, value="""
			
			SELECT ed.*, 
			       d.dept_name, 
			       r.role_name, 
			       CONCAT(ed.f_name, ' ', ed.l_name) AS full_name, 
			       COALESCE(mp.misspunch_count, 0) AS misspunch_count,
				   p.attendance_link
			FROM employee_details ed
			INNER JOIN departments d ON ed.dept_id = d.dept_id
			INNER JOIN roles r ON ed.role_id = r.role_id
			INNER JOIN permissions p ON p.emp_id = ed.empid
			LEFT JOIN (
			    SELECT eid, COUNT(*) AS misspunch_count
			    FROM (
			        SELECT eid, DATE(pd)
			        FROM attendance
			        where pd >= '2025-01-01'
			        and pd::date != current_date
			        GROUP BY eid, DATE(pd)
			        HAVING COUNT(*) = 1
			    ) AS misspunches
			    GROUP BY eid
			) mp ON ed.empid = mp.eid
			AND ed.empid != :loggedAdminEmpid
			order by case when ed.employee_type = '1' then 1 when ed.employee_type = '0' then 2 else 3 end, 
			CAST(SUBSTRING(ed.empid FROM 3) AS INTEGER) ASC;
						
						""")
				public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_superadmin(String loggedAdminEmpid);
}
