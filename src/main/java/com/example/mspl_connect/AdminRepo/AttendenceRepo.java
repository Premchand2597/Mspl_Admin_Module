package com.example.mspl_connect.AdminRepo;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mspl_connect.AdminEntity.AttendenceEntity;

@Repository
public interface AttendenceRepo extends JpaRepository<AttendenceEntity, String> {

//	@Query(nativeQuery = true, value = "SELECT pt FROM attendance WHERE eid = :empId AND pd = CURDATE() ORDER BY pdt ASC LIMIT 1")
//	public String getEmpLogedTime(String empId);
	
	@Query(nativeQuery = true, value = "SELECT pt FROM attendance WHERE eid = :empId AND pd::date = CURRENT_DATE ORDER BY pdt ASC LIMIT 1")
	public String getEmpLogedTime(String empId);

//	@Query(nativeQuery = true, value = "SELECT pt FROM attendance WHERE eid = :empId and pd=curdate() ORDER BY pdt DESC LIMIT 1")
//	public String getEmpLastPunchTime(String empId);
	
	@Query(nativeQuery = true, value = "SELECT pt FROM attendance WHERE eid = :empId and pd::date=current_date ORDER BY pdt DESC LIMIT 1")
	public String getEmpLastPunchTime(String empId);

	@Query(nativeQuery = true, value = "select count(distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date and e.dept_id=:deptId order by f_name asc")
	Optional<Integer> getTotalPresentCountByDeptId(int deptId);
	
	/*@Query(nativeQuery = true, value = "select count( distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date() and (e.team_lead_name=:empId or e.team_co_name=:empId) order by f_name asc")
	Integer getTotalPresentCountByAdminId(String empId);*/

//	@Query(nativeQuery = true, value = "select count(distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date() and (e.team_lead_name = :adminid or e.team_co_name = :adminid) and empid  "
//			+ "not in ( "
//			+ "	select la.empid from leave_application la inner join employee_details ed on la.empid = ed.empid where curdate() between la.from_date and la.to_date and la.approvedstatus='Approved' and (ed.team_co_name = :adminid or ed.team_lead = :adminid)  and ed.employee_type = 1"
//			+ "    ) ;")
//	@Transactional
//	//@Procedure(name = "present_count")
//	Integer presentCount(@Param("adminid") String adminid);
	
	@Query(nativeQuery = true, value = """
			
			select count(distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd::date = current_date and 
			(e.team_lead_name = :adminid or e.team_co_name = :adminid) and empid not in (select la.empid from leave_application la inner join employee_details ed 
			on la.empid = ed.empid where current_date between la.from_date::date and la.to_date::date and la.approvedstatus='Approved' and (ed.team_co_name = :adminid or 
			ed.team_lead = :adminid)  and ed.employee_type::int = 1);
						
						""")
				Integer presentCount(@Param("adminid") String adminid);
	
	// absent employeess list for admin
	/*@Query(nativeQuery = true, value = "SELECT concat(e.f_name,' ',e.l_name) as fullname FROM employee_details e LEFT JOIN attendance a ON e.empid = a.eid AND a.pd = current_date() WHERE  (e.team_lead_name=:empid or e.team_co_name=:empid) and a.eid IS NULL")
	Optional<List<String>> getAbsentEmp(String empid);*/
	
//	@Procedure(name="fetch_today_absent_employee")
//	@Query(nativeQuery = true, value = "call fetch_today_absent_employee(:empid)")
//	@Transactional
//	Optional<List<Object[]>> fetch_today_absent_employee(String empid);
	
	@Query(nativeQuery = true, value = """
			
SELECT concat(e.f_name,' ',e.l_name) as fullname, e.profile_pic_path 
	        FROM employee_details e 
	        LEFT JOIN attendance a ON e.empid = a.eid AND a.pd::date = current_date 
	        WHERE (e.team_lead_name = :empid OR e.team_co_name = :empid) AND a.eid IS NULL and e.employee_type = '1'
            UNION
 SELECT concat(ed.f_name,' ',ed.l_name) as fullname, ed.profile_pic_path
      FROM leave_application la
      JOIN employee_details ed ON la.empid = ed.empid
      WHERE CURRENT_DATE BETWEEN la.from_date::date AND la.to_date::date
        AND la.approvedstatus = 'Approved'
        AND (ed.team_lead_name = :empid OR ed.team_co_name = :empid);
			
			""")
	@Transactional
	Optional<List<Object[]>> fetch_today_absent_employee(String empid);

	/*@Query(nativeQuery = true, value = "SELECT concat(e.f_name,' ',e.l_name) as fullname, e.profile_pic_path " +
	        "FROM employee_details e " +
	        "LEFT JOIN attendance a ON e.empid = a.eid AND a.pd = current_date() " +
	        "WHERE a.eid IS NULL")
	Optional<List<Object[]>> getAbsentEmpForSA();*/
	
//	@Procedure(name="fetch_today_absent_employee_for_super_admin")
//	@Query(nativeQuery = true, value = "call fetch_today_absent_employee_for_super_admin()")
//	@Transactional
//	Optional<List<Object[]>> fetch_today_absent_employee_for_super_admin();
	
	@Query(nativeQuery = true, value = """
			
SELECT concat(e.f_name,' ',e.l_name) as fullname, e.profile_pic_path 
	        FROM employee_details e 
	        LEFT JOIN attendance a ON e.empid = a.eid AND a.pd::date = current_date 
			inner join permissions pe on pe.emp_id = e.empid
	        WHERE a.eid IS NULL and e.employee_type::integer=1 and pe.attendance_link = true
            UNION
 SELECT concat(ed.f_name,' ',ed.l_name) as fullname, ed.profile_pic_path
      FROM leave_application la
      JOIN employee_details ed ON la.empid = ed.empid
	  inner join permissions p on p.emp_id = ed.empid
      WHERE CURRENT_DATE BETWEEN la.from_date::date AND la.to_date::date
        AND la.approvedstatus = 'Approved' and p.attendance_link = true;
			
			""")
	@Transactional
	Optional<List<Object[]>> fetch_today_absent_employee_for_super_admin();
	
	
//	@Procedure(name="fetch_today_present_employee_count_for_super_admin")
//	@Query(nativeQuery = true, value = "call fetch_today_present_employee_count_for_super_admin()")
//	@Transactional
//	public Optional<Object[]> fetch_today_present_employee_count_for_super_admin();
	
	@Query(nativeQuery = true, value = """
			
SELECT count(a.eid) as total_count
FROM attendance a 
INNER JOIN employee_details e ON a.eid = e.empid 
INNER JOIN departments d ON e.dept_id = d.dept_id
INNER JOIN permissions p ON p.emp_id = e.empid
INNER JOIN (
    SELECT eid, MIN(pdt) AS first_pdt 
    FROM attendance 
    WHERE pd::date = CURRENT_DATE 
    GROUP BY eid
) AS first_punch ON a.eid = first_punch.eid AND a.pdt = first_punch.first_pdt
INNER JOIN (
    SELECT eid, MAX(pt) AS last_pdt 
    FROM attendance 
    WHERE pd::date = CURRENT_DATE 
    GROUP BY eid
) AS last_punch ON a.eid = last_punch.eid  
  AND a.eid NOT IN (
      SELECT la.empid 
      FROM leave_application la
      JOIN employee_details ed ON la.empid = ed.empid
      WHERE CURRENT_DATE BETWEEN la.from_date::date AND la.to_date::date
        AND la.approvedstatus = 'Approved'       
  )
 where p.attendance_link = TRUE ;
			
			""")
	@Transactional
	public Optional<Object[]> fetch_today_present_employee_count_for_super_admin();
	
	 @Modifying
	 @Query("INSERT INTO AttendenceEntity (eid, pt, pd, pdt) VALUES (:eid, :pt, :pd, :pdt)")
	 void insertAttendance(@Param("eid") String eid, @Param("pt") String pt, @Param("pd") String pd, @Param("pdt") String pdt);

	 @Query(nativeQuery = true, value = "SELECT * FROM attendance where eid=:empId and pd=:date")
		public List<AttendenceEntity> isDataPresentOnParticularDateForEmpId(@Param("empId") String empId, @Param("date") String date);
	}
