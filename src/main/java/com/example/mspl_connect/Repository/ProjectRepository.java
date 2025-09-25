package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.ProjectEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectRepository  extends JpaRepository<ProjectEntity, Integer> {
	
	List<ProjectEntity> findByStatus(String status);
	
//	@Query(value="SELECT id, project_id, project_name, assigned_date, department, platform_to_work, client_name, completed_date, project_manager, description, document, status, team_members, senior_name,  flag, assign_flag, team_members_email, view_flag, delay_reason, start_date, admin_completion_date,completion_date as completion_date FROM team_project where department=:adminDept ORDER BY CASE WHEN flag = 1 AND assign_flag = 1 THEN 1 WHEN flag = 2 AND assign_flag = 1 THEN 3 WHEN flag = 3 THEN 2 WHEN flag = 4 THEN 4 WHEN flag = 2 AND assign_flag = 2 THEN 5 ELSE 6 END",nativeQuery = true)
//	List<ProjectEntity> findProject(String adminDept);
	
			@Query(value="""
					
		SELECT id, project_id, project_name, assigned_date, department, platform_to_work, client_name, completed_date, project_manager, description, 
		document, status, team_members, senior_name,  flag, assign_flag, team_members_email, view_flag, delay_reason, start_date, admin_completion_date,
		completion_date as completion_date FROM team_project where department=:adminDept ORDER BY CASE WHEN flag::int = 1 AND assign_flag::int = 1 THEN 1 WHEN 
		flag::int = 2 AND assign_flag::int = 1 THEN 3 WHEN flag::int = 3 THEN 2 WHEN flag::int = 4 THEN 4 WHEN flag::int = 2 AND assign_flag::int = 2 THEN 5 ELSE 6 END;

			""",nativeQuery = true)
	List<ProjectEntity> findProject(String adminDept);
	
//	@Query(value="SELECT id, project_id, project_name, assigned_date, department, platform_to_work, client_name, completed_date, project_manager, description, document, status, team_members, senior_name,  flag, assign_flag, team_members_email, view_flag, delay_reason, start_date, admin_completion_date,completion_date as completion_date FROM team_project ORDER BY CASE WHEN flag = 1 AND assign_flag = 1 THEN 1 WHEN flag = 2 AND assign_flag = 1 THEN 3 WHEN flag = 3 THEN 2 WHEN flag = 4 THEN 4 WHEN flag = 2 AND assign_flag = 2 THEN 5 ELSE 6 END",nativeQuery = true)
//	List<ProjectEntity> findProjectForHeadDept();
			
			@Query(value="""
			
			SELECT id, project_id, project_name, assigned_date, department, platform_to_work, client_name, completed_date, project_manager, description, 
			document, status, team_members, senior_name,  flag, assign_flag, team_members_email, view_flag, delay_reason, start_date, admin_completion_date,
			completion_date as completion_date FROM team_project ORDER BY CASE WHEN flag::int = 1 AND assign_flag::int = 1 THEN 1 WHEN flag::int = 2 AND assign_flag::int = 1 THEN 
			3 WHEN flag::int = 3 THEN 2 WHEN flag::int = 4 THEN 4 WHEN flag::int = 2 AND assign_flag::int = 2 THEN 5 ELSE 6 END;
						
					""",nativeQuery = true)
			List<ProjectEntity> findProjectForHeadDept();
	
//	@Query(value = "SELECT id, project_id, project_name, assigned_date, department, platform_to_work , client_name, completed_date , project_manager , description, document, status, team_members, senior_name, flag, assign_flag, team_members_email, view_flag, delay_reason, start_date , admin_completion_date, DATE_FORMAT(completion_date,'%d-%m-%Y') AS completion_date FROM team_project WHERE assign_flag=1 AND flag=2", nativeQuery = true)
//	List<ProjectEntity> findUnassignedProjects(String dept);
			
			@Query(value = """
					
		SELECT id, project_id, project_name, assigned_date, department, platform_to_work , client_name, completed_date , project_manager , description, 
		document, status, team_members, senior_name, flag, assign_flag, team_members_email, view_flag, delay_reason, start_date , admin_completion_date, 
		completion_date FROM team_project WHERE assign_flag::int=1 AND flag::int=2;
					
					""", nativeQuery = true)
			List<ProjectEntity> findUnassignedProjects(String dept);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE team_project SET flag = 0 WHERE (department = 'IT') AND flag::int = 1", nativeQuery = true)
    int updateprojectFlag();
	
//	@Query(value="select * from team_project where (department='IT') and flag=1", nativeQuery = true)
//	List<ProjectEntity> findBydept();
	
	@Query(value="select * from team_project where (department='IT') and flag::int=1", nativeQuery = true)
	List<ProjectEntity> findBydept();
	
//	@Query(value="select * from team_project where (department='IT') and flag=3", nativeQuery = true)
//	List<ProjectEntity> findByDelayProject();
	
	@Query(value="select * from team_project where (department='IT') and flag::int=3", nativeQuery = true)
	List<ProjectEntity> findByDelayProject();
	
//	@Query(value = "select count(*) from team_project where flag=1 AND department=:dept", nativeQuery = true)
//    int findNewProjectFlag(String dept);
	
	@Query(value = """
			
			select count(*) from team_project where flag::int=1 AND department=:dept;
			
			""", nativeQuery = true)
    int findNewProjectFlag(String dept);
	
	@Modifying
    @Transactional
	@Query(value="update team_project set flag= :flag,status='Pending',view_flag='1',admin_completion_date=:adminStartDate where id= :id", nativeQuery = true)
	int updateAcceptedPrj(int id,int flag,String adminStartDate);	
	
	@Modifying
    @Transactional
	@Query(value="update team_project set flag= :flag,status='Pending',delay_reason= :reason,start_date=:startDate,view_flag='1' where id= :id", nativeQuery = true)
	int updateDelayPrj(int id,int flag,String reason,String startDate);
	
	@Modifying
	@Transactional
	@Query(value="update  team_project  set project_manager='VR',team_members='Pa,Pr',senior_name='HA',assign_flag='2' where id='136'",nativeQuery = true)
	int updateAssignPrj();
	
//	@Modifying
//	@Transactional
//	@Query(value="update project_acceptence_flag set flag=3 where flag=2",nativeQuery = true)
//	int updateAcceptedProjectView();
	
	@Modifying
	@Transactional
	@Query(value="update project_acceptence_flag set flag=3 where flag::int=2",nativeQuery = true)
	int updateAcceptedProjectView();

//	@Query(value="select project_name from team_project where flag=2",nativeQuery = true)
//	List<String> findProjectName();
	
	@Query(value="select project_name from team_project where flag::int=2",nativeQuery = true)
	List<String> findProjectName();
	
}
