package com.example.mspl_connect.AdminController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminService.ProjectDataService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class OverallEmployeePerformanceController {

	
	@Autowired
	private EmployeeRepository employeeRepository;


	/*@GetMapping("/getEmployeePerformance")
	public ResponseEntity<List<Map<String, Object>>> getEmployeePerformance(@RequestParam String email) {
	    List<Map<String, Object>> performanceData = new ArrayList<>();

	    // Query the database for relevant project data based on email
	    List<ProjectData> projectList = projectDataRepository.findByEmail(email);

	    for (ProjectData project : projectList) {
	        // Calculate the total allowed duration
	        LocalDate assignDate = LocalDate.parse(project.getAssignDate());
	        LocalDate completionDate = LocalDate.parse(project.getCompletionDate());
	        LocalDate employeeCompletionDate = project.getEmployeeCompletionDate() != null ?
	                LocalDate.parse(project.getEmployeeCompletionDate()) : null;

	        long totalDuration = DAYS.between(assignDate, completionDate);
	        long employeeDuration = employeeCompletionDate != null ?
	                DAYS.between(assignDate, employeeCompletionDate) : 0;

	        // Calculate performance as a percentage
	        double performance;
	        if (employeeCompletionDate != null && employeeCompletionDate.isAfter(completionDate)) {
	            performance = -((double) employeeDuration / totalDuration) * 100;
	        } else if (employeeCompletionDate != null) {
	            performance = ((double) employeeDuration / totalDuration) * 100;
	        } else {
	            performance = 0; // Not completed
	        }

	        // Prepare data for the frontend
	        Map<String, Object> projectInfo = new HashMap<>();
	        projectInfo.put("projectId", project.getProjectId());
	        projectInfo.put("projectName", project.getProjectName());
	        projectInfo.put("taskName", project.getTaskName());
	        projectInfo.put("performance", performance);

	        performanceData.add(projectInfo);
	    }

	    return ResponseEntity.ok(performanceData);
	}*/
	
	@Autowired
    private ProjectDataService projectDataService;

    @GetMapping("/getEmployeePerformance")
    public ResponseEntity<List<Map<String, Object>>> getEmployeePerformance(@RequestParam String email ,HttpSession session, Model model) {
    	 //String email = (String) session.getAttribute("email");
    	    System.out.println("Fetching leaves for user: " + email);
    	    
    	    String empId = employeeRepository.findEmpidByEmail(email);
    	    System.out.println("Employee ID retrieved: " + empId);

        System.out.println("Employee ID retrieved: " + empId);

    	System.out.println("===////");
        List<Map<String, Object>> performanceData = projectDataService.getEmployeePerformance(email);
        System.out.println("Response performance data: " + performanceData);
        return ResponseEntity.ok(performanceData);
    }

   
    @GetMapping("/getEmployeePerformanceForProject")
    public ResponseEntity<List<Map<String, Object>>> getEmployeePerformanceForProject(HttpSession session, @RequestParam String projectId, @RequestParam String projectName, @RequestParam String email) {
        String userEmail = email;
        System.out.println("Fetching performance for user: " + userEmail);

        String employeeId = employeeRepository.findEmpidByEmail(userEmail);
        System.out.println("Employee ID retrieved: " + employeeId);

        List<Map<String, Object>> projectPerformanceResults = projectDataService.calculateProjectPerformance(userEmail, projectId, projectName);
        System.out.println("Calculated performance data: " + projectPerformanceResults);
        return ResponseEntity.ok(projectPerformanceResults);
    }

 //   @RequestParam String projectId,

   /* @GetMapping("/getProjectTasks")
    public ResponseEntity<List<ProjectData>> getProjectTasks( @RequestParam String projectName,HttpSession session) {
    	 String email = (String) session.getAttribute("email");
 	    System.out.println("Fetching leaves for user: " + email);
 	    
 	    String empId = employeeRepository.findEmpidByEmail(email);
 	    System.out.println("Employee ID retrieved: " + empId);

        List<ProjectData> tasks = projectDataService.getTasksForProjectAndEmployee( projectName, email);
        return ResponseEntity.ok(tasks);
    }*/


}
