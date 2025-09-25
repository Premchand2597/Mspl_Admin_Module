package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.ProjectEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ProjectSaveRepo extends JpaRepository<ProjectEntity, Integer>{
	
	@Modifying
	@Transactional
	@Query(value="update  team_project  set project_manager= :projectManneger ,team_members= :teamMemebers,senior_name= :seniourName,assign_flag= :assignFlag,team_members_email=:teamMemebers,start_date=current_date() where id= :id",nativeQuery = true)
	int updateAssignPrj(String projectManneger,String teamMemebers,String seniourName,String assignFlag,int id);
	
	@Modifying
	@Transactional
	@Query(value="insert into project_acceptence_flag(prj_name,prj_id,emp_email,emp_name) values (:prjName, :prjId, :email,:empName)",nativeQuery = true)
	void insertproject_acceptence_flag(String prjName,String prjId,String email,String empName);
	
	@Query(value="select f_name from  employee_details where email= :email",nativeQuery = true)
	String findFirstNameByEmail(String email);	
		
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE all_project_data SET completion_date = DATE_ADD(task_duedate, INTERVAL :days DAY) WHERE email_id = :email  and status_flag=1")
	int updateTaskDueDateByEmail(@Param("days") long days, @Param("email") String email);

	@Query(nativeQuery = true,value="select distinct prj_name from all_project_data where email_id=:email and status_flag=1")
	List<String> findProjectNamesByEmailAndStatusFlag(String email); 
	
	@Query(nativeQuery = true,value="SELECT distinct e.email_id FROM all_project_data e WHERE e.email_id IN :emails")
    List<String> findExistingEmails(@Param("emails") List<String> emails);

}
