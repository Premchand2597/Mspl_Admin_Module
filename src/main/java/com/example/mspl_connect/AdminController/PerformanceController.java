package com.example.mspl_connect.AdminController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.ProjectData;
import com.example.mspl_connect.AdminRepo.ProjectDataRepository;
import com.example.mspl_connect.AdminRepo.TeamProjectRepository;
import com.example.mspl_connect.AdminService.TeamProjectService;
import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PerformanceController {

	    @Autowired
	    private TeamProjectService teamProjectService;
	    
	    @Autowired
	    private TeamProjectRepository teamProjectRepository;
	 
		@Autowired
		private EmployeeRepository employeeRepository;
		
		@Autowired
		private ProjectDataRepository projectDataRepository;

    @GetMapping("/getEmployeProjectStatus1")
    public ResponseEntity<List<EmployeeProjectProgressEntity>> getEmployeProjectStatus(HttpSession session,@RequestParam String projectId,@RequestParam String projectName) {    	
    	  String email = (String) session.getAttribute("email");
  	    System.out.println("user login "+email);
  	    String empId = employeeRepository.findEmpidByEmail(email);
  	    System.out.println("user empid "+empId);
        List<EmployeeProjectProgressEntity> projectDetails = teamProjectService.getEmployeProjectStatus(projectId,projectName,email);
        System.out.println("..........prj="+projectDetails);
        return ResponseEntity.ok(projectDetails);         
    }


 /*   @GetMapping("/getEmployeProjectStatus")	
    public ResponseEntity<List<EmployeeProjectProgressEntity>> getEmployeProjectStatus(@RequestParam String projectName) {    	
        
        List<EmployeeProjectProgressEntity> projectDetails = teamProjectService.getEmployeProjectStatus(projectName);
        //System.out.println("..........prj="+projectDetails);
        return ResponseEntity.ok(projectDetails);
        
    }*/

  /*  @GetMapping("/getProjectsForGraph")
    @ResponseBody
    public List<Map<String, String>> getProjectNames(HttpSession session) {
        String email = (String) session.getAttribute("email");
        System.out.println("User login: " + email);
        
        List<TeamProject> projects = teamProjectRepository.findProjectsByEmail(email);

        if (projects == null || projects.isEmpty()) {
            System.out.println("No projects found for email: " + email);
            return new ArrayList<>(); // Return an empty list if no projects are found
        }

        // Map each TeamProject to a simplified response containing only project_id and project_name
        List<Map<String, String>> projectInfoList = projects.stream()
                .map(project -> {
                    Map<String, String> projectInfo = new HashMap<>();
                    projectInfo.put("id", String.valueOf(project.getId()));
                    projectInfo.put("projectId", project.getProject_id());
                    projectInfo.put("projectName", project.getProject_name());
                    return projectInfo;
                })
                .collect(Collectors.toList());

        return projectInfoList;
    }*/
    
    @GetMapping("/getProjectsForGraph")
    @ResponseBody
    public List<Map<String, String>> getProjectNames(@RequestParam String email, HttpSession session) {
        //String email = (String) session.getAttribute("email");
        System.out.println("User login: " + email);

        // Retrieve projects with at least one completed subtask
        List<ProjectData> projects = projectDataRepository.findProjectsByEmailWithCompletedSubtasks(email);

        if (projects == null || projects.isEmpty()) {
            System.out.println("No projects found with completed subtasks for email: " + email);
            return new ArrayList<>(); // Return an empty list if no projects are found
        }

        // Map each ProjectData to a simplified response containing only projectId and projectName
        List<Map<String, String>> projectInfoList = projects.stream()
                .map(project -> {
                    Map<String, String> projectInfo = new HashMap<>();
                    projectInfo.put("id", String.valueOf(project.getId()));
                    projectInfo.put("projectId", project.getProjectId());
                    projectInfo.put("projectName", project.getProjectName());
                    return projectInfo;
                })
                .collect(Collectors.toList());

        return projectInfoList;
    }


  

}
