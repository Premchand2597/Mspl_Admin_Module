package com.example.mspl_connect.AdminService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.TeamProject;
import com.example.mspl_connect.AdminRepo.ProjectRepositoryCustom;
import com.example.mspl_connect.AdminRepo.TeamProjectRepository;
import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;
import com.example.mspl_connect.Entity.TeamProgress;
import com.example.mspl_connect.Repository.TeamProgressRepository;


@Service
public class TeamProjectService {

	@Autowired
	 private TeamProjectRepository teamProjectRepository;
	 
	/* @Autowired
	 private TeamProgressRepository teamProgressRepository;*/

	    @Autowired
	    @Qualifier("projectRepositoryCustomImpl") 
	    private ProjectRepositoryCustom projectRepositoryCustom;
	 

	    private final JdbcTemplate jdbcTemplate;
	    
	    @Autowired
	    public TeamProjectService(TeamProjectRepository teamProjectRepository, JdbcTemplate jdbcTemplate) {
	        this.teamProjectRepository = teamProjectRepository;
	        this.jdbcTemplate = jdbcTemplate;
	    }

	/*public List<TeamProject> getOngoingProjectsByDepartment(String department) {
		// TODO Auto-generated method stub
		return teamProjectRepository.findByDepartment(department);
	}
	
	 public List<TeamProject> findByTeamMembersEmailAndAssignFlag(String loggedInUserEmail) {
		 System.out.println("assign flag is one");
	        return teamProjectRepository.findByTeamMembersEmailAndAssignFlag(loggedInUserEmail);
	    }*/
	 
	 //merging two tables
	/* public List<Map<String, Object>> getJoinedDataForUserProjects() {
	        String userEmail = getLoggedInUserEmail();

	        // Find projects for the logged-in user with assign_flag = 2
	        List<TeamProject> userProjects = teamProjectRepository.findByTeamMembersEmailContainingAndAssignFlag(userEmail, "2");

	        List<Map<String, Object>> results = new ArrayList<>();
	        for (TeamProject project : userProjects) {
	            String projectName = project.getProject_name();
	            String dynamicTableName = "team_project_" + projectName;

	            String sql = "SELECT tp.*, t.* " +
	                         "FROM team_project tp " +
	                         "JOIN " + dynamicTableName + " t ON tp.project_id = t.project_id " +
	                         "WHERE tp.project_name = ? AND tp.team_members_email LIKE ?";
	            String sql = "SELECT tp.*, t.* " +
	                    "FROM team_project tp " +
	                    "JOIN " + dynamicTableName + " t ON tp.project_id = t.project_id " +
	                    "WHERE tp.project_name = ? " +
	                    "AND tp.team_members_email LIKE ? " +
	                    "AND tp.assign_flag = 2 " +
	                    "AND t.notifyflag = 1";


	            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(sql, projectName, "%" + userEmail + "%");
	            results.addAll(queryResults);
	        }

	        return results;
	    }
	 
	 private String getLoggedInUserEmail() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal instanceof UserDetails) {
	            return ((UserDetails) principal).getUsername();
	        } else {
	            return principal.toString();
	        }
	    }*/
	 public List<TeamProject> getAllProjects() {
	        System.out.println("Retrieving all projects from repository..."); // Add console output
	        List<TeamProject> projects = teamProjectRepository.findAll();
	        System.out.println("Retrieved " + projects.size() + " projects."); // Add console output
	        return projects;
	    }
	 
	
	 
	 public void acceptProject(String projectId, String email, String taskName,String subtaskName) {
		 System.out.println("sssssssssss");
		    projectRepositoryCustom.updateAcceptanceDate(projectId, email, taskName,subtaskName);
		}


	 
	/* public String getAcceptanceDate(String projectId, String email, String taskName) {
		 System.out.println("aaaaaaaaa");
		    return projectRepositoryCustom.findAcceptanceDate(projectId, email, taskName);
		}*/

	/* public Map<String, String> getAcceptanceDateAndDueDate(String projectId, String email, String taskName,String subtaskName) {
		 System.out.println("aaaaaaaaa");
		    return projectRepositoryCustom.findAcceptanceDateAndDueDate(projectId, email, taskName,subtaskName);
		}
	 

	 public List<Map<String, String>> getTaskDetails(String projectId, String email) {
		    try {
		        System.out.println("task details");
		        // Fetch task details from the repository
		        return projectRepositoryCustom.findProjectDetails(projectId, email);
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to fetch task details: " + e.getMessage(), e);
		    }
		}




	 public void taskaccept(String projectId, String email, String taskName, String subtaskName,String status) {
		    System.out.println("Task accept method called");
		    projectRepositoryCustom.updateTaskStatus(projectId, email, taskName, subtaskName,status);
		}


	 public List<TeamProgress> getLastTwoActivities(String email) {
	        List<TeamProgress> activities = teamProgressRepository.findLastTwoActivitiesByEmail(email);
	        return activities.size() > 2 ? activities.subList(0, 2) : activities;
	    }*/

	 public List<EmployeeProjectProgressEntity> getEmployeProjectStatus(String projectId,String projectName,String email) {
	        // Implement your logic here to fetch the data based on the projectId
	                String convertedProjectName1=projectName.toLowerCase().replace(" ","_");
	                System.out.println("convertedProjectName1="+convertedProjectName1);
	        return projectRepositoryCustom.findByProjectIdAndTableName(projectId,convertedProjectName1,email);
	    }
	 /*public List<EmployeeProjectProgressEntity> getEmployeProjectStatus(String projectId,String projectName) {
	        // Implement your logic here to fetch the data based on the projectId
	                String convertedProjectName1=projectName.toLowerCase().replace(" ","_");
	                System.out.println("convertedProjectName1="+convertedProjectName1);
	        return projectRepositoryCustom.findByProjectIdAndTableName(projectId,convertedProjectName1);
	    }*/

}

