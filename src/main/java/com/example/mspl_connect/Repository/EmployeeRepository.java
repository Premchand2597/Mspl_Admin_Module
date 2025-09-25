package com.example.mspl_connect.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EmployeeNameEmailDTO;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.Employee;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.Login_Entity;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetailsEntity, Integer>{
	
//	@Query(nativeQuery= true,value="select * from employee_details where employee_type = 1")
//	List<EmployeeDetailsEntity> findAll();
	
	@Query(nativeQuery= true,value="select * from employee_details where employee_type::int = 1")
	List<EmployeeDetailsEntity> findAll();
	
	Optional<EmployeeDetailsEntity> findByEmpId(String empId);
	
	EmployeeDetailsEntity findByEmail(String email);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update employee_details set password=:newPassword where empid=:empId")
    int updateEmployeePassword(String empId, String newPassword);
	
	@Query(value = """
			
SELECT count(*) FROM employee_details ed inner join permissions p on ed.empid = p.emp_id where ed.employee_type::integer = 1 and 
p.attendance_link = true;
			
			""",nativeQuery = true)
	int totalEmployeCount();
	
	@Query(value = "SELECT count(*) FROM employee_details where dept_id = :deptId",nativeQuery = true)
	int totalDeptEmployeCount(int deptId);
	
//	@Query(value = "select count(*) from employee_details where team_lead_name=:empid or team_co_name=:empid and employee_type = 1",nativeQuery = true)
//	int totalDeptEmployeCountByEmpId(String  empid);
	
	@Query(value = "select count(*) from employee_details where team_lead_name=:empid or team_co_name=:empid and employee_type::int = 1",nativeQuery = true)
	int totalDeptEmployeCountByEmpId(String  empid);
	
//	@Query(value = "SELECT count(*) FROM (SELECT a.eid, MIN(a.pt) AS first_punch FROM attendance a inner join employee_details e on a.eid=e.empid where pd = CURDATE() GROUP BY eid) AS first_punches",nativeQuery = true)
//	int totalPresentCount();
	
	@Query(value = """
			
SELECT count(*) FROM (SELECT a.eid, MIN(a.pt) AS first_punch FROM attendance a inner join employee_details e on a.eid=e.empid inner join permissions p
on p.emp_id = e.empid where a.pd::date = CURRENT_DATE and p.attendance_link = true GROUP BY eid) AS first_punches;
						
						""",nativeQuery = true)
				int totalPresentCount();
	
	@Query("SELECT e.empId FROM EmployeeDetailsEntity e WHERE e.email = :email")
	String findEmpidByEmail(@Param("email") String email);
	
	//EmployeeDetailsEntity findByEmail(String email);

	@Query(value = "SELECT dept_id FROM employee_details where empid = :empid",nativeQuery = true)
	Integer findDeptIdByEmpId(String empid);
	
	@Query(value = "SELECT email FROM employee_details where empid = :empid",nativeQuery = true)
	String findEmailByEmpId(String empid); 
	
	@Query(value = "SELECT d.dept_name FROM departments d inner join employee_details e on d.dept_id=e.dept_id where empid = :empid",nativeQuery = true)
	String findDeptNameByEmpId(String empid);
	
	@Query(value = "SELECT dept_id FROM employee_details where dept_name = :deptName",nativeQuery = true)
	Integer findDeptIdByDeptNAme(String deptName);
	
	@Query("SELECT new com.example.mspl_connect.AdminEntity.EmployeeNameEmailDTO(e.id, e.firstName, e.email) " +
		       "FROM EmployeeDetailsEntity e WHERE e.deptId = :dept")
	List<EmployeeNameEmailDTO> findByDeptId1(@Param("dept") int dept);
	
	@Query("SELECT e FROM EmployeeDetailsEntity e WHERE e.empId = :empId")
	Optional<EmployeeDetailsEntity> findByEmpIdAtt(String empId);
	 
	EmployeeDetailsEntity findFirstNameAndLastNameByEmail(String email);
	 
	/*@Query(nativeQuery = true, value = "SELECT a.eid, a.pt, a.pd, a.pdt, e.f_name as emp_name,e.email,d.dept_name " +
		        "FROM attendance a " +
		        "INNER JOIN employee_details e ON a.eid = e.empid inner join departments d on e.dept_id=d.dept_id " +
		        "INNER JOIN (SELECT eid, MIN(pdt) AS first_pdt " +
		        "FROM attendance WHERE pd = CURDATE() " +
		        "GROUP BY eid) AS first_punch " +
		        "ON a.eid = first_punch.eid AND a.pdt = first_punch.first_pdt")
	List<Object[]> getTodayPresentEmpList();*/
	
//	@Procedure(name = "fetch_today_present_employee_for_super_admin")
//	@Query(nativeQuery = true, value = "call fetch_today_present_employee_for_super_admin()")
//	@Transactional
//	List<Object[]> fetch_today_present_employee_for_super_admin();
	
	@Query(nativeQuery = true, value = """
			
			SELECT 
			    e.empid AS eid,
			    e.f_name AS emp_name, 
			    e.email, 
			    d.dept_name, 
			    COALESCE(first_punch.first_pdt, 'Check-In Pending') AS first_punch_time, 
			    COALESCE(last_punch.last_pdt, 'Check-In Pending') AS last_punch_time,
			    COALESCE(TO_CHAR(us.last_seen, 'YYYY-MM-DD HH24:MI:SS'), 'No Data') AS last_seen
			FROM employee_details e  
			INNER JOIN departments d ON e.dept_id = d.dept_id 
			LEFT JOIN (
			    SELECT eid, MIN(pdt) AS first_pdt 
			    FROM attendance 
			    WHERE pd::date = CURRENT_DATE 
			    GROUP BY eid
			) AS first_punch ON e.empid = first_punch.eid  
			LEFT JOIN (
			    SELECT eid, MAX(pdt) AS last_pdt  
			    FROM attendance 
			    WHERE pd::date = CURRENT_DATE 
			    GROUP BY eid
			) AS last_punch ON e.empid = last_punch.eid  
			LEFT JOIN leave_application la ON e.empid = la.empid 
			  AND CURRENT_DATE BETWEEN la.from_date::date AND la.to_date::date
			  AND la.approvedstatus = 'Approved'
			LEFT JOIN user_status us ON e.email = us.email
			LEFT JOIN permissions p on p.emp_id = e.empid
			where e.employee_type::integer=1 and p.attendance_link = true
			GROUP BY e.empid, e.f_name, e.email, d.dept_name, first_punch.first_pdt, last_punch.last_pdt, us.last_seen 
			order by first_punch_time asc;
						
						""")
				@Transactional
				List<Object[]> fetch_today_present_employee_for_super_admin();
	
	/*@Query(nativeQuery = true, value = "SELECT a.eid, a.pt, a.pd, a.pdt, e.f_name AS emp_name, e.email, d.dept_name, last_punch.last_pdt AS last_punch_time "
	        + "FROM attendance a "
	        + "INNER JOIN employee_details e ON a.eid = e.empid "
	        + "INNER JOIN departments d ON e.dept_id = d.dept_id "
	        + "INNER JOIN (SELECT eid, MIN(pdt) AS first_pdt FROM attendance WHERE pd = CURDATE() GROUP BY eid) AS first_punch ON a.eid = first_punch.eid AND a.pdt = first_punch.first_pdt "
	        + "INNER JOIN (SELECT eid, MAX(pt) AS last_pdt FROM attendance WHERE pd = CURDATE() GROUP BY eid) AS last_punch ON a.eid = last_punch.eid "
	        + "WHERE e.team_lead_name = :empID OR e.team_co_name = :empID")
	List<Object[]> getTodayPresentEmpListByDeptId(String empID);*/
	
//	@Procedure(name = "fetch_today_present_employee")
//	@Query(nativeQuery = true, value = "call fetch_today_present_employee(:empID)")
//	@Transactional
//	List<Object[]> fetch_today_present_employee(String empID);
				
	@Query(nativeQuery = true, value = "select * from fetch_today_present_employee(:empID)")
	List<Object[]> fetch_today_present_employee(String empID);
	
	@Query(nativeQuery = true, value="SELECT * FROM employee_details WHERE empid = :empId")
	EmployeeDetailsEntity selectEmpDetailsForDocumentsViewById(String empId);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set profile_pic_path=:profile_pic_path, pan_pic_path=:pan_pic_path, aadhar_pic=:aadhar_pic, tenth_pic=:tenth_pic, puc_pic=:puc_pic, degree_pic=:degree_pic where empid=:emp_id", nativeQuery = true)
    int updateEmployeeDocumentsUploads(@Param("emp_id") String emp_id, @Param("profile_pic_path") byte[] profile_pic_path, @Param("pan_pic_path") byte[] pan_pic_path, 
    		@Param("aadhar_pic") byte[] aadhar_pic, @Param("tenth_pic") byte[] tenth_pic, @Param("puc_pic") byte[] puc_pic, @Param("degree_pic") byte[] degree_pic);
	
	@Query(value="SELECT concat(f_name,' ',l_name) as fullname FROM employee_details where empid=:empid",nativeQuery = true)
	String findEmpidByEmpId(String empid);
	
	@Query(value="SELECT * FROM employee_details e WHERE e.dept_id = :dept", nativeQuery = true)
	List<EmployeeDetailsEntity> findByDeptId(@Param("dept") int dept);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set profile_pic_path=:profile_pic_path, pan_pic_path=:pan_pic_path, aadhar_pic=:aadhar_pic, tenth_pic=:tenth_pic, puc_pic=:puc_pic, degree_pic=:degree_pic, pg_pic=:pg_pic, exp_letter_pic=:exp_letter_pic, payslip_pic=:payslip_pic, offer_letter=:offer_letter, diploma_pic=:diploma_pic, bank_check_pic=:bank_check_pic, other_pic=:other_pic, other_pic2=:other_pic2, other_pic3=:other_pic3, other_pic4=:other_pic4 where empid=:emp_id", nativeQuery = true)
    int updateEmployeeDocumentsUploads(@Param("emp_id") String emp_id, @Param("profile_pic_path") String profile_pic_path, @Param("pan_pic_path") String pan_pic_path, 
    		@Param("aadhar_pic") String aadhar_pic, @Param("tenth_pic") String tenth_pic, @Param("puc_pic") String puc_pic, 
    		@Param("degree_pic") String degree_pic, @Param("offer_letter") String offer_letter,
    		@Param("pg_pic") String pg_pic, @Param("exp_letter_pic") String exp_letter_pic, @Param("payslip_pic") String payslip_pic,
    		@Param("diploma_pic") String diploma_pic, @Param("bank_check_pic") String bank_check_pic, @Param("other_pic") String other_pic, 
    		@Param("other_pic2") String other_pic2, @Param("other_pic3") String other_pic3, @Param("other_pic4") String other_pic4);
	
	@Query(value = "SELECT gender FROM employee_details where empid = :empid",nativeQuery = true)
	String fetchGenderByEmpId(String empid);
	
	@Query(nativeQuery = true, value="SELECT e.team_lead_name, e.team_co_name FROM employee_details e WHERE e.empId = :empId")
	List<Object[]> findTeamLeadAndCoByEmpId(String empId);
	
//	@Modifying
//    @Transactional
//    @Query(value = "UPDATE student s JOIN employee_details e ON s.mobile_number = e.mobile_no SET s.candidate_imported_flag_to_employee_table = 1 where e.mobile_no=:candidateMobileNumber", nativeQuery = true)
//    void updateImportedToEmployeeTableFlagInStudentTable(String candidateMobileNumber);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE student s SET candidate_imported_flag_to_employee_table = 1 from employee_details e where s.mobile_number = e.mobile_no and e.mobile_no=:candidateMobileNumber", nativeQuery = true)
    void updateImportedToEmployeeTableFlagInStudentTable(String candidateMobileNumber);
	
	@Query("SELECT e.employee_type FROM EmployeeDetailsEntity e WHERE e.empId = :emp_id")
	String fetchEmployeeType(@Param("emp_id") String emp_id);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set current_salary=:hiked_salary where empid=:emp_id", nativeQuery = true)
    void updateSalaryAfterHiking(@Param("emp_id") String emp_id, @Param("hiked_salary") String hiked_salary);
	
 	@Query(value = "select email from employee_details where dept_id = cast(:dept_id as integer)", nativeQuery = true)
 	List<String> getHREmailBasedOnDeptId(@Param("dept_id") String dept_id);
 	
 	 @Query(value = "SELECT CONCAT(f_name, ' ', l_name) FROM employee_details WHERE email = :email", nativeQuery = true)
	   String findFullNameByEmail(@Param("email") String email);
 	 
	 @Query(value = "SELECT CONCAT(f_name, ' ', l_name) FROM employee_details WHERE empid = :empId", nativeQuery = true)
	 String findEmployeeNameByEmpId(@Param("empId") String empId);
}
