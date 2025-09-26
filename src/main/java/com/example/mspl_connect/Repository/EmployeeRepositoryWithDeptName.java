package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.Employee;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepositoryWithDeptName extends JpaRepository<DisplayEmployessEntity, Integer>{
	
	@Query(value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where ed.email=:email" , nativeQuery = true)
	DisplayEmployessEntity findByEmail(String email);
	
	@Query(value="select password_change_flag from employee_details where email=:email", nativeQuery = true)
	String findPasswordChangeFlagByEmail(String email);
	
//	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where ed.employee_type = 1")
//	public List<DisplayEmployessEntity> getAllEmployees();
	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id order by case when ed.employee_type = '1' then 1 when ed.employee_type = '0' then 2 else 3 end, CAST(SUBSTRING(ed.empid FROM 3) AS INTEGER) ASC")
	public List<DisplayEmployessEntity> getAllEmployees();
	
	
	@Query(
			  nativeQuery = true,
			  value = "SELECT ed.*, d.dept_name, r.role_name, CONCAT(ed.f_name,' ',ed.l_name) AS full_name " +
			          "FROM employee_details ed " +
			          "INNER JOIN departments d ON ed.dept_id = d.dept_id " +
			          "INNER JOIN roles r ON ed.role_id = r.role_id " +
			          "WHERE ed.employee_type = '1' " +   // ✅ Only fetch type = 1
			          "ORDER BY CAST(SUBSTRING(ed.empid FROM 3) AS INTEGER) ASC"
			)
			public List<DisplayEmployessEntity> getAllActiveEmployees();

	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id  where team_lead_name=:loggedAdminEmpid or team_co_name=:loggedAdminEmpid and empid!=:loggedAdminEmpid")
	public List<DisplayEmployessEntity> getAdminDeptEmployees(String loggedAdminEmpid);
	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where ed.empid=:empId")
	DisplayEmployessEntity findByEmpid(String empId);
	
//    @Query(value = "SELECT  CONCAT(f_name, ' ', l_name) AS full_name, DATE_FORMAT(dob, '%d-%m-%Y') FROM employee_details WHERE MONTH(dob) = MONTH(CURRENT_DATE())  AND empid NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0 AND employee_type = 1",nativeQuery = true)
//    List<Object[]> findEmployeesWithUpcomingBirthdays();
    
    @Query(value = """
			
    		SELECT  CONCAT(f_name, ' ', l_name) AS full_name, TO_CHAR(dob::date, 'DD-MM-YYYY') AS formatted_dob FROM employee_details WHERE extract(MONTH from dob::date) = extract(MONTH from CURRENT_DATE) 
    		AND empid NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0 AND employee_type::int = 1;
    					
    					""",nativeQuery = true)
    		    List<Object[]> findEmployeesWithUpcomingBirthdays();
    
//    @Query(value = "SELECT concat(f_name,' ',l_name) as fullName,date_format(doj,'%d-%m-%Y') FROM employee_details WHERE MONTH(doj) = MONTH(CURRENT_DATE()) and empid NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0 And employee_type = 1", 
//    nativeQuery = true)
//    List<Object[]> findEmployeesWithUpcomingAnneversery();
    		    
	    @Query(value = """
	    		
	    		SELECT concat(f_name,' ',l_name) as fullName, TO_CHAR(doj::date, 'DD-MM-YYYY') AS formatted_doj FROM employee_details WHERE extract(MONTH from doj::date) = extract(MONTH from CURRENT_DATE) and empid 
	    		NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0 And employee_type::int = 1;
	    		    		
	    		""", 
	    	    nativeQuery = true)
	    List<Object[]> findEmployeesWithUpcomingAnneversery();
    
    @Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where d.dept_id= :deptId")
	List<DisplayEmployessEntity> findByDeptId(int deptId);
    
    /* This query is used to fetch team_lead & team_coordinator by using Displaydetails entity*/
    @Query("SELECT CONCAT(t.fName, ' ', t.lName) AS teamLeadName, " +
    	       "CONCAT(c.fName, ' ', c.lName) AS coordinatorName " +
    	       "FROM DisplayEmployessEntity e " +
    	       "LEFT JOIN DisplayEmployessEntity t ON e.team_lead = t.empid " +
    	       "LEFT JOIN DisplayEmployessEntity c ON e.team_coordinator = c.empid " +
    	       "WHERE e.empid = :empid")
    List<Object[]> findTeamLeadAndCoordinatorNamesByEmpid(@Param("empid") String empid);

    @Modifying
    @Transactional
    @Query(value = "update  employee_details set password_change_flag=0 where empid=:empId", nativeQuery = true)
    int updatepaswordChangeFlageValue(String empId);
    
    @Query(value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where empid=:empId",nativeQuery = true)
    DisplayEmployessEntity findGenderAndNameByEmpId(String empId);
    
    @Query(value = "select empid from employee_details where dept_id=54 limit 1",nativeQuery = true)
    String findHRName();

    @Query(value = "SELECT email FROM employee_details WHERE CONCAT(f_name, ' ', l_name) = :fullName OR f_name = :fullName", nativeQuery = true)
	String findEmailByFullName(@Param("fullName") String fullName);
    
    @Query("SELECT e.gender FROM EmployeeDetailsEntity e WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
	String findGenderByFullName(@Param("fullName") String fullName);
    
    @Query(value = "select ed.empid,concat(ed.f_name,' ',ed.l_name) as full_name,us.last_seen from employee_details ed LEFT join user_status us on ed.email = us.email where ed.team_lead_name=:loogedEmpId or ed.team_co_name=:loogedEmpId", nativeQuery = true)
    List<Object[]> getEmployeeRecentAppTimeByAdmin(String loogedEmpId);

    @Query(value = "select concat(f_name,' ' ,l_name) as fullName from employee_details where email=:mail", nativeQuery = true)
	String getFullNameByEmailId(String mail);

    @Query(value = "select usertype from employee_details where empid = :empId", nativeQuery = true)
	String getUserTypeByEmpId(String empId);
    
    @Query(value = "SELECT email FROM employee_details WHERE employee_type = '0'", nativeQuery = true)
	List<String> getInactiveEmployeesEmailId();
    
    @Query(nativeQuery = true, value="select empid from employee_details where empid=:empid")
    String checkEmpIdIsAlreadyPresentOrNot(@Param("empid") String empid);
    
    @Query(nativeQuery = true, value="select empid from employee_details order by id desc limit 1")
    String getLastInsertedEmpIdValue();
    
    @Query(value = "SELECT email FROM employee_details WHERE empid=:empid", nativeQuery = true)
	String findEmailByEmpId(@Param("empid") String empid);
    
    @Query(nativeQuery = true, value = "Select concat(f_name,' ',l_name) as fullName from employee_details where empid=:empId")
    String fetchTeamCoOrTeamLeadNameBasedOnHisEmpIdForEmpProfilePageInHR(@Param("empId") String empId);

}
