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
	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id")
	public List<DisplayEmployessEntity> getAllEmployees();
	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id  where team_lead_name=:loggedAdminEmpid or team_co_name=:loggedAdminEmpid and empid!=:loggedAdminEmpid")
	public List<DisplayEmployessEntity> getAdminDeptEmployees(String loggedAdminEmpid);
	
	@Query(nativeQuery = true,value="SELECT ed.*,d.dept_name,r.role_name,concat(ed.f_name,' ',ed.l_name) as full_name FROM employee_details ed inner join departments d on ed.dept_id = d.dept_id inner join roles r on ed.role_id = r.role_id where ed.empid=:empId")
	DisplayEmployessEntity findByEmpid(String empId);
	
    @Query(value = "SELECT  CONCAT(f_name, ' ', l_name) AS full_name, DATE_FORMAT(dob, '%d-%m-%Y') FROM employee_details WHERE MONTH(dob) = MONTH(CURRENT_DATE())  AND empid NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0",nativeQuery = true)
    List<Object[]> findEmployeesWithUpcomingBirthdays();
    
    @Query(value = "SELECT concat(f_name,' ',l_name) as fullName,date_format(doj,'%d-%m-%Y') FROM employee_details WHERE MONTH(doj) = MONTH(CURRENT_DATE()) and empid NOT IN ('MS02', 'MS03', 'MS001', 'MS07') AND dept_id != 0 ", 
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
     
}
