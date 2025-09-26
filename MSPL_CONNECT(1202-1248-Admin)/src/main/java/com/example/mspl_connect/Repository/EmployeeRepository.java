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
	
	Optional<EmployeeDetailsEntity> findByEmpId(String empId);
	
	EmployeeDetailsEntity findByEmail(String email);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set password=:newPassword where empid=:empId", nativeQuery = true)
    int updateEmployeePassword(String empId, String newPassword);
	
	@Query(value = "SELECT count(*) FROM employee_details",nativeQuery = true)
	int totalEmployeCount();
	
	@Query(value = "SELECT count(*) FROM employee_details where dept_id = :deptId",nativeQuery = true)
	int totalDeptEmployeCount(int deptId);
	
	@Query(value = "select count(*) from employee_details where team_lead_name=:empid or team_co_name=:empid",nativeQuery = true)
	int totalDeptEmployeCountByEmpId(String  empid);
	
	@Query(value = "SELECT count(*) FROM (SELECT a.eid, MIN(a.pt) AS first_punch FROM attendance a inner join employee_details e on a.eid=e.empid where pd = CURDATE() GROUP BY eid) AS first_punches",nativeQuery = true)
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
	
	@Procedure(name = "fetch_today_present_employee_for_super_admin")
	@Query(nativeQuery = true, value = "call fetch_today_present_employee_for_super_admin()")
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
	
	@Procedure(name = "fetch_today_present_employee")
	@Query(nativeQuery = true, value = "call fetch_today_present_employee(:empID)")
	@Transactional
	List<Object[]> fetch_today_present_employee(String empID);
	
	@Query(nativeQuery = true, value="SELECT * FROM employee_details WHERE empid = :empId")
	EmployeeDetailsEntity selectEmpDetailsForDocumentsViewById(String empId);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set profile_pic_path=:profile_pic_path, pan_pic_path=:pan_pic_path, aadhar_pic=:aadhar_pic, tenth_pic=:tenth_pic, puc_pic=:puc_pic, degree_pic=:degree_pic where empid=:emp_id", nativeQuery = true)
    int updateEmployeeDocumentsUploads(@Param("emp_id") String emp_id, @Param("profile_pic_path") byte[] profile_pic_path, @Param("pan_pic_path") byte[] pan_pic_path, 
    		@Param("aadhar_pic") byte[] aadhar_pic, @Param("tenth_pic") byte[] tenth_pic, @Param("puc_pic") byte[] puc_pic, @Param("degree_pic") byte[] degree_pic);
	
	@Query(value="SELECT concat(f_name,' ',l_name) as fullname FROM mspl_connect.employee_details where empid=:empid",nativeQuery = true)
	String findEmpidByEmpId(String empid);
	
	@Query(value="SELECT * FROM employee_details e WHERE e.dept_id = :dept", nativeQuery = true)
	List<EmployeeDetailsEntity> findByDeptId(@Param("dept") int dept);
	
	@Modifying
    @Transactional
    @Query(value = "update employee_details set profile_pic_path=:profile_pic_path, pan_pic_path=:pan_pic_path, aadhar_pic=:aadhar_pic, tenth_pic=:tenth_pic, puc_pic=:puc_pic, degree_pic=:degree_pic, offer_letter=:offer_letter where empid=:emp_id", nativeQuery = true)
    int updateEmployeeDocumentsUploads(@Param("emp_id") String emp_id, @Param("profile_pic_path") String profile_pic_path, @Param("pan_pic_path") String pan_pic_path, 
    		@Param("aadhar_pic") String aadhar_pic, @Param("tenth_pic") String tenth_pic, @Param("puc_pic") String puc_pic, 
    		@Param("degree_pic") String degree_pic, @Param("offer_letter") String offer_letter);
	
	@Query(value = "SELECT gender FROM employee_details where empid = :empid",nativeQuery = true)
	String fetchGenderByEmpId(String empid);
	
	
}
