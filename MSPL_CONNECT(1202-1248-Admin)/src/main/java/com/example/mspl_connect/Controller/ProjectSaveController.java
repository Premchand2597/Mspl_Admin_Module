package com.example.mspl_connect.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.AdminEntity.EmployeeNameEmailDTO;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.Employee;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;
import com.example.mspl_connect.Entity.NewCompanyProject_Entity;
import com.example.mspl_connect.Entity.ProjectChartResponse;
import com.example.mspl_connect.Entity.ProjectEntity;
import com.example.mspl_connect.Entity.ProjectProgress;
import com.example.mspl_connect.Entity.Task;
import com.example.mspl_connect.Entity.TeamProgress;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.ProjectSaveRepo;
import com.example.mspl_connect.Repository.TeamProgressSummary;
import com.example.mspl_connect.Service.ChangeProjectStatusService;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.NewCompanyProject_Service;
import com.example.mspl_connect.Service.ProjectService;
import com.example.mspl_connect.Service.TeamProgressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Controller
public class ProjectSaveController {
	
	@Autowired
	private ProjectService projectService;//projectSave
	
	@Autowired
	private ChangeProjectStatusService changeProjectStatusService;	
	
	@Autowired
    private TeamProgressService teamProgressService;
	
	@Autowired
	private EmployeeDetaisService empDetailsService;
	
	@Autowired
	private NewCompanyProject_Service newCompanyProject_Service;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectSaveRepo projectSaveRepo; 
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@PostMapping("/saveProject")
    public String insertHolidays(@ModelAttribute("projectEntity") ProjectEntity projectEntity,
    	@RequestParam(value = "extendAssignment", required = false, defaultValue = "false") boolean extendAssignment, 
    	@RequestParam(value = "addRequirement", required = false, defaultValue = "false") boolean addRequirement,
    	Model model, HttpSession session, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		 
		System.out.println("addRequirement--"+addRequirement);
		 
		projectEntity.setStatus("pending");
        projectEntity.setAssign_flag("2");
         
        // Implement your logic to update the project assignment
        String projectManager = projectEntity.getProject_manager();
        String teamMembers = projectEntity.getTeam_members();
        String seniorName = projectEntity.getSenior_name();
        String assignFlag = projectEntity.getAssign_flag();        
        String prjName = projectEntity.getProject_name();
        String projectId = projectEntity.getProject_id(); 
        String dept = projectEntity.getDepartment();
         
        int prjId = projectEntity.getId();
         
		/*
		 * System.out.println("prj name="+projectEntity.getProject_name());
		 * System.out.println("prj id="+projectEntity.getProject_id());
		 * System.out.println("prj name="+projectEntity.getProject_name());
		 * System.out.println("prj id="+projectEntity.getProject_id());
		 */
         
        // Extract task data from the form submission
        List<Task> tasks = new ArrayList<>();
        String[] taskEmployees = request.getParameterValues("subtask_emp_name[]");        
        String[] taskNames = request.getParameterValues("maintask_name[]");
         
        String[] taskDescriptions = request.getParameterValues("subtask_description[]");
        String[] mainTaskDescription = request.getParameterValues("mainTaskDescription[]");
        String[] taskWeights = request.getParameterValues("task_weight[]");
        String[] taskCompletionDates = request.getParameterValues("subtask_completion_date[]");
        String[] employeeName = request.getParameterValues("employeeName[]");
        String[] empMail = request.getParameterValues("");
         
        String[] prjIdArray = request.getParameterValues("subTaskprojectId[]");
        String[] projectName = request.getParameterValues("subTaskprojectName[]");
        String[] complationDate = request.getParameterValues("subtask_completion_date[]");
        String[] startDate = request.getParameterValues("subtask_start_date[]");
         
        String[] subtasktaskNames = request.getParameterValues("sub_task[]");         
        String[] teamMemberArr = teamMembers.split(",");
         
        for(int i=0;i<startDate.length;i++) {
        	System.out.println("startDate----"+startDate[i]);
        }
         
        for(String email : teamMemberArr) {        	
        	String empName=projectService.finadNameByEmail(email);
        	//System.out.println("team emails======"+email);
        	projectService.insertproject_acceptence_flag(projectId, prjName,email,empName);         	
        }		 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < subtasktaskNames.length; i++) {
            Task task = new Task();
            task.setEmployeeId(taskEmployees[i]);
            task.setTaskName(taskNames[i]);            
            task.setTaskDescription(taskDescriptions[i]);            
            //task.setTaskWeight(Integer.parseInt(taskWeights[i]));            
            task.setTaskCompletionDate(taskCompletionDates[i]);            
            task.setEmp_mail(taskEmployees[i]);            
            task.setProjectId(prjIdArray[i]);            
            task.setPrj_name(projectName[i]);            
            task.setCompletionDate(complationDate[i]);    
            task.setSubTaskName(subtasktaskNames[i]);
            task.setMainTaskDescription(mainTaskDescription[i]);
            task.setPrjManager(projectManager);

            task.setAssign_date(startDate[i]);
            tasks.add(task); 
        }
        //tasks.stream().forEach(i->System.out.println("tasks before maping--"+i));
        Map<String, Task> latestTaskByEmail = tasks.stream()
                .collect(Collectors.toMap(
                    Task::getEmp_mail,
                    task -> task,
                    (existing, replacement) -> {
                       
                    	// Parse the dates
                        LocalDate existingCompletionDate = LocalDate.parse(existing.getCompletionDate(), formatter);
                        LocalDate replacementCompletionDate = LocalDate.parse(replacement.getCompletionDate(), formatter);
                        LocalDate existingAssignDate = LocalDate.parse(existing.getAssign_date(), formatter);
                        LocalDate replacementAssignDate = LocalDate.parse(replacement.getAssign_date(), formatter);

                        // Determine the latest completionDate
                        Task latestTask = existingCompletionDate.isAfter(replacementCompletionDate) ? existing : replacement;

                        // Update the assign_date to the earliest one between the two tasks
							/*
							 * if (existingAssignDate.isBefore(replacementAssignDate)) {
							 * latestTask.setAssign_date(existing.getAssign_date()); } else {
							 * latestTask.setAssign_date(replacement.getAssign_date()); }
							 */
                        return latestTask;
                    }
                ));
        tasks.stream().forEach(i->System.out.println("tasks after maping--"+i));
        
        
        //if user say yes to extend previous dates
		if (extendAssignment) {

			// Print the results with the number of days between assign_date and
			// completionDate
			latestTaskByEmail.forEach((email, task) -> {

				LocalDate assignDate = LocalDate.parse(task.getAssign_date(), formatter);
				LocalDate completionDate = LocalDate.parse(task.getCompletionDate(), formatter);
				long daysBetween = ChronoUnit.DAYS.between(assignDate, completionDate);

				// System.out.println("emailss...."+email);
				List<String> tableNames = projectService.getProjectByMail(email);
				 String loggedEmail = (String) session.getAttribute("email");
				 
			 	 String empId = employeeRepository.findEmpidByEmail(loggedEmail);			 	    
			 	int adminDept = employeeRepository.findDeptIdByEmpId(empId);
			 	
				tableNames.forEach(tableName -> {	
					// Call the updateExtedDynamicTableDates method for each tableName
					projectService.updateExtedDynamicTableDates(email, tableName, daysBetween,adminDept);
				}); 
				
				// Call the service to update the task_duedate in all_project_data
				projectService.updateTaskDueDates(daysBetween, email);
			});

			// Save or update the project entity and create a new table
			try {
				//System.out.println("tasks in controller++++++" + tasks);
				projectService.saveOrUpdateProject(projectEntity, tasks,addRequirement);
			} catch (TableAlreadyExistsException e) {
				model.addAttribute("errorMessage", e.getMessage());
				redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
				return "redirect:ongoingProject";
			}
		} else {
			
			// Your logic for handling when extendAssignment is false
			System.out.println("extendAssignment is false");

			// Save or update the project entity and create a new table
			try {
				projectService.saveOrUpdateProject(projectEntity, tasks,addRequirement);
			} catch (TableAlreadyExistsException e) {
				model.addAttribute("errorMessage", e.getMessage());
				redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
				return "redirect:ongoingProject";
			}
			
		}
        
        // update the team_project table after assign the project to employees
		System.out.println("prjId--"+prjId);
		System.out.println("dept--"+dept);
        int result = projectService.updateAssignPrj(projectManager, teamMembers, seniorName, assignFlag, prjId);
        return "redirect:ongoingProject";
    }
	
	@PostMapping("/checkEmails")
    @ResponseBody
    public List<String> checkEmails(@RequestBody List<String> emails) {
		System.out.println("emails from front end........."+emails);
		List<String> dbEmails=projectService.findExistingEmails(emails);
		dbEmails.stream().forEach(email->System.out.println("abcd=="+email));
        return dbEmails;
    }
	
	@PostMapping("/updateStatus")
	public ResponseEntity<String> toggleLeaveApproveStatus(@RequestParam String projectId) {
	    try {	        
	        boolean isStatusChanged = changeProjectStatusService.setActionStatus(projectId);
	        if (isStatusChanged) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing Leave approve Status. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing Leave approve Status. Please try again.");
	    }
	}
	
	
///projectService.updateprojectFlag();//updateAcceptedPrj	
	@GetMapping("/acceptProject")
	public ResponseEntity<?> updateprojectFlag(@RequestParam Integer projectId, @RequestParam("action") String action,@RequestParam String adminStartDate) {
		Integer ReceivedPojectId = projectId;
		
		if ("accept".equalsIgnoreCase(action)) {
			
			projectService.updateAcceptedPrj(projectId,2,adminStartDate);
	        System.out.println("Project accepted: " + projectId);
	        
	    } else if ("delay".equalsIgnoreCase(action)) {
	     	
	    	projectService.updateAcceptedPrj(projectId,3,adminStartDate);
	        //System.out.println("Project delayed: " + projectId);
	        
	    }
        return ResponseEntity.ok().build();
	}
	
	@GetMapping("/acceptdelayProject")
	public ResponseEntity<?> updateDelayProjectStatus( @RequestParam Integer projectId, @RequestParam("delayReason") String reason,@RequestParam("startDate") String startDate){
		
		projectService.updateDelayPrj(projectId,3,reason,startDate);
		return ResponseEntity.ok().build();
	}//compProjectCardsRow
	
	@GetMapping("/getAllProjects")
	public ResponseEntity<?> getAllProjectById(HttpSession session){
		
		// Retrieve user details from session
	    String email = (String) session.getAttribute("email");
	    String empId = employeeRepository.findEmpidByEmail(email);
	    
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	    String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
	    
		List<ProjectEntity> projects = projectService.findAllProjectsByDepartmentId(adminDeptName);
		projects.stream().forEach(prj->System.out.println("--------------------"+prj));
        return ResponseEntity.ok(projects);
	}					
	
	@GetMapping("/getProjectProgress")
    public ResponseEntity<?> getProjectProgress(@RequestParam String projectId) {
		List<ProjectProgress> prjProgress = projectService.findProjectDetailsByProjectId(projectId);
		System.out.println("prjProgress------------"+prjProgress);
		return ResponseEntity.ok(prjProgress);
    }

    @GetMapping("/team-progress")
    public ResponseEntity<?> getTeamProgress(@RequestParam String projectId) {    	
    	List<TeamProgressSummary> prjProgress = teamProgressService.getTeamProgressByProjectId(projectId);
    	return ResponseEntity.ok(prjProgress);
    }
    
    @GetMapping("/completedProjectPercent")
	public ResponseEntity<?> getProjectCompletedPercent(@RequestParam String projectId) {
	    List<ProjectChartResponse> completedProjectPercent = teamProgressService.getProjectCompletedPercentWithIndividualStatus(projectId);
	    return ResponseEntity.ok(completedProjectPercent);
	}

    
    @GetMapping("/ProjectByprjId")
    public ResponseEntity<?> getProjectByprjId(@RequestParam String projectId){    	
    	
    	List<TeamProgress> projectDetails = teamProgressService.getProjectByprjId(projectId);
    	projectDetails.forEach(prj -> System.out.println("............"+prj));
    	
    	return ResponseEntity.ok(projectDetails); 
    	 
    }
    
    @GetMapping("/getEmployeProjectStatus")
    public ResponseEntity<List<EmployeeProjectProgressEntity>> getEmployeProjectStatus(@RequestParam String projectId,@RequestParam String projectName,HttpSession session) {    	
        
    	String email = (String) session.getAttribute("email");
 	    String empId = employeeRepository.findEmpidByEmail(email);
 	    
 		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
 		
 		
 		
 		System.out.println("adminDept=="+adminDept);
 		
        List<EmployeeProjectProgressEntity> projectDetails = projectService.getEmployeProjectStatus(projectId,projectName,adminDept);
        System.out.println("projectId=="+projectId);
        System.out.println("projectName=="+projectName);
        System.out.println("projectDetails===="+projectDetails);
        //System.out.println("..........prj="+projectDetails); 
        return ResponseEntity.ok(projectDetails);
        
    }
    
    //controller to fetch multiple department graph
    /*  @GetMapping("/getEmployeProjectStatus")
    public ResponseEntity<List<EmployeeProjectProgressEntity>> getEmployeProjectStatus(@RequestParam String projectId,@RequestParam String projectName,HttpSession session) {    	
        
    	String email = (String) session.getAttribute("email");
 	    String empId = employeeRepository.findEmpidByEmail(email);
 	    
 		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
 		
 		System.out.println("projectId=="+projectId);
 		System.out.println("projectName=="+projectName);
 		System.out.println("adminDept=="+adminDept);
 		
        List<EmployeeProjectProgressEntity> projectDetails = projectService.getEmployeProjectStatus(projectId,projectName,adminDept);
        System.out.println("projectDetails");
        //System.out.println("..........prj="+projectDetails); 
        return ResponseEntity.ok(projectDetails);
        
    }*/
    
	@GetMapping("/getManagers")
	@ResponseBody
	public Map<String, List<String>> getManagers(@RequestParam String department,HttpSession session) {
		
		String emailid = (String) session.getAttribute("email");
		String empId = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
		
	    List<EmployeeDetailsEntity> employees = empDetailsService.findUserByDepartment(adminDept);
	    List<EmployeeDetailsEntity> TLandTC = empDetailsService.findUserByDepartment(adminDept);

		/*
		 * if (department.equals("Software") || department.equals("Embedded")) { TLandTC
		 * = empDetailsService.findUserByDepartment(adminDept); }
		 */
	    List<String> employeeNames = employees.stream()
	                                          .map(emp -> emp.getFirstName() + " " + emp.getLastName())
	                                          .collect(Collectors.toList());
	    List<String> employeeEmail = employees.stream()
                .map(emp -> emp.getEmail())
                .collect(Collectors.toList());
	    
	    List<String> tlAndTcNames = TLandTC.stream()
	                                       .map(emp -> emp.getFirstName() + " " + emp.getLastName())
	                                       .collect(Collectors.toList());

	    Map<String, List<String>> result = new HashMap<>();
	    result.put("employees", employeeNames);
	    result.put("emails", employeeEmail);
	    result.put("tlAndTc", tlAndTcNames);

	    return result;
	}
	
	@PostMapping("/saveNewCompProjectList")
	public String insertAssignedCompanyProject(
	    @ModelAttribute("newCompProj_Entity") NewCompanyProject_Entity newCompProj_Entity, 
	    Model model, 
	    HttpSession session
	) {
		
		// Convert the list of departments into a comma-separated string
	    String departments = String.join(",", newCompProj_Entity.getDepartment());
	    newCompProj_Entity.setDepartment(departments);
	    
	    // The list of selected departments will now be available in the `departments` field
	    String result = newCompanyProject_Service.save(newCompProj_Entity);
	    return "redirect:ongoingProject";
	    
	}
	
	 @GetMapping("/employees/byDepartment/{departmentId}")
	 public ResponseEntity<List<EmployeeNameEmailDTO>> getEmployeesByDepartment(@PathVariable int departmentId) {
		   List<EmployeeNameEmailDTO> employees = empDetailsService.findUserByDepartment1(departmentId);
		   System.out.println("employees...."+employees);
		    
	       return ResponseEntity.ok(employees);
	 }     
}
