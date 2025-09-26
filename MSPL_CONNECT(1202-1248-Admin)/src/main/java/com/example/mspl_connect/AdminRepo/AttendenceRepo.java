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

	@Query(nativeQuery = true, value = "SELECT pt FROM attendance	 WHERE eid = :empId AND pd = CURDATE() ORDER BY pdt ASC LIMIT 1")
	public String getEmpLogedTime(String empId);

	@Query(nativeQuery = true, value = "SELECT pt FROM attendance WHERE eid = :empId and pd=curdate() ORDER BY pdt DESC LIMIT 1")
	public String getEmpLastPunchTime(String empId);

	@Query(nativeQuery = true, value = "select count( distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date() and e.dept_id=:deptId order by f_name asc")
	Optional<Integer> getTotalPresentCountByDeptId(int deptId);

	/*@Query(nativeQuery = true, value = "select count( distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date() and (e.team_lead_name=:empId or e.team_co_name=:empId) order by f_name asc")
	Integer getTotalPresentCountByAdminId(String empId);*/

	@Query(nativeQuery = true, value = "select count(distinct e.f_name) as count from employee_details e inner join attendance a on a.eid = e.empid where a.pd = current_date() and (e.team_lead_name = :adminid or e.team_co_name = :adminid) and empid  "
			+ "not in ( "
			+ "	select la.empid from leave_application la inner join employee_details ed on la.empid = ed.empid where curdate() between la.from_date and la.to_date and la.approvedstatus='Approved' and (ed.team_co_name = :adminid or ed.team_lead = :adminid) "
			+ "    );")
	@Transactional
	//@Procedure(name = "present_count")
	Integer presentCount(@Param("adminid") String adminid);
	
	// absent employeess list for admin
	/*@Query(nativeQuery = true, value = "SELECT concat(e.f_name,' ',e.l_name) as fullname FROM employee_details e LEFT JOIN attendance a ON e.empid = a.eid AND a.pd = current_date() WHERE  (e.team_lead_name=:empid or e.team_co_name=:empid) and a.eid IS NULL")
	Optional<List<String>> getAbsentEmp(String empid);*/
	
	@Procedure(name="fetch_today_absent_employee")
	@Query(nativeQuery = true, value = "call fetch_today_absent_employee(:empid)")
	@Transactional
	Optional<List<Object[]>> fetch_today_absent_employee(String empid);

	/*@Query(nativeQuery = true, value = "SELECT concat(e.f_name,' ',e.l_name) as fullname, e.profile_pic_path " +
	        "FROM employee_details e " +
	        "LEFT JOIN attendance a ON e.empid = a.eid AND a.pd = current_date() " +
	        "WHERE a.eid IS NULL")
	Optional<List<Object[]>> getAbsentEmpForSA();*/
	
	@Procedure(name="fetch_today_absent_employee_for_super_admin")
	@Query(nativeQuery = true, value = "call fetch_today_absent_employee_for_super_admin()")
	@Transactional
	Optional<List<Object[]>> fetch_today_absent_employee_for_super_admin();

	
	@Procedure(name="fetch_today_present_employee_count_for_super_admin")
	@Query(nativeQuery = true, value = "call fetch_today_present_employee_count_for_super_admin()")
	@Transactional
	public Optional<Object[]> fetch_today_present_employee_count_for_super_admin();
	
	 @Modifying
	 @Query("INSERT INTO AttendenceEntity (eid, pt, pd, pdt) VALUES (:eid, :pt, :pd, :pdt)")
	 void insertAttendance(@Param("eid") String eid, @Param("pt") String pt, @Param("pd") String pd, @Param("pdt") String pdt);

}
