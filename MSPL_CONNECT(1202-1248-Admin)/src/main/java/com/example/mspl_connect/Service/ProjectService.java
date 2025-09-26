package com.example.mspl_connect.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;
import com.example.mspl_connect.Entity.ProjectEntity;
import com.example.mspl_connect.Entity.ProjectProgress;
import com.example.mspl_connect.Entity.Task;
import com.example.mspl_connect.Repository.CustomEmployeeProjectStatusRepo;
import com.example.mspl_connect.Repository.CustomProjectRepository;
import com.example.mspl_connect.Repository.EmployeeProjectStatusRepo;
import com.example.mspl_connect.Repository.ProjectProgressRepo;
import com.example.mspl_connect.Repository.ProjectRepository;
import com.example.mspl_connect.Repository.ProjectSaveRepo;
import com.example.mspl_connect.Repository.dynamic_refRepo;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	/*
	 * @Autowired private CompanyProjectRepository companyProjectRepository;
	 */
	
	@Autowired
	private ProjectSaveRepo projectSaveRepo;
	

    @Autowired
    private CustomProjectRepository customProjectRepository;
    
    @Autowired
    private ProjectProgressRepo progressRepo;
    
    @Autowired
    private EmployeeProjectStatusRepo employeeProjectStatusRepo;
    
    @Autowired
    private dynamic_refRepo dynamicRefRepo;
    
    @Autowired
    private CustomEmployeeProjectStatusRepo customEmployeeProjectStatusRepo; 
	
	public List<ProjectEntity> projectByStatus(String status){
		return projectRepository.findByStatus(status);
	}
	
	public List<ProjectEntity> findUnassignedProjects(String dept){
		List<ProjectEntity> unassigned = projectRepository.findUnassignedProjects(dept);
		 // Define the desired date format
	    DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    // Format the completion_date for each project
	    unassigned.forEach(prj -> {
	        try {
	            LocalDate date = LocalDate.parse(prj.getCompletion_date(), originalFormatter);
	            String formattedDate = date.format(targetFormatter);
	            prj.setCompletion_date(formattedDate);
	        } catch (Exception e) {
	            // Handle any parsing exceptions if needed
	            System.out.println("Error formatting date: " + e.getMessage());
	        }
	    });

	    // Debug output
	    unassigned.forEach(prj -> System.out.println("Formatted Project: " + prj)); 
		return unassigned;
		
	}
	
	public List<ProjectEntity> findAllProjectsByDepartmentId(String adminDept) {
	    System.out.println("adminDept---" + adminDept);

	    if (adminDept.equals("Head Engineer") || adminDept.equals("Super Admin")) {
	        // Fetch projects for Head Engineer
	        List<ProjectEntity> projectsForHeadEng = projectRepository.findProjectForHeadDept();
	        projectsForHeadEng.stream().forEach(i -> System.out.println("before prj=" + i));

	        // Count occurrences of each project_id (after removing the department part)
	        Map<String, Long> projectIdCounts = projectsForHeadEng.stream()
	                .collect(Collectors.groupingBy(
	                        project -> removeDepartmentFromProjectId(project.getProject_id()),
	                        Collectors.counting()
	                ));

	        // Transform project_id to remove the department part and merge departments only for duplicates
	        Map<String, ProjectEntity> uniqueProjects = new LinkedHashMap<>();

	        for (ProjectEntity project : projectsForHeadEng) {
	            String projectId = project.getProject_id();
	            String updatedProjectId = removeDepartmentFromProjectId(projectId);

	            if (projectIdCounts.get(updatedProjectId) > 1) {
	                // Apply merging logic only for duplicate project_ids
	                project.setProject_id(updatedProjectId); // Update the project_id

	                if (uniqueProjects.containsKey(updatedProjectId)) {
	                    // Merge departments if project_id already exists
	                    ProjectEntity existingProject = uniqueProjects.get(updatedProjectId);
	                    String mergedDepartments = mergeDepartments(existingProject.getDepartment(), project.getDepartment());
	                    String mergedProjectName = mergeDepartments(existingProject.getProject_name(), project.getProject_name());
	                    existingProject.setDepartment(mergedDepartments);
	                    existingProject.setProject_name(mergedProjectName);
	                } else {
	                    // Add new unique project
	                    uniqueProjects.put(updatedProjectId, project);
	                }
	            } else {
	                // For non-duplicates, keep the project as is
	                uniqueProjects.put(projectId, project);
	            }
	        }

	        // Debugging output after transformation
	        uniqueProjects.values().forEach(project -> System.out.println("after merger prj=" + project));

	        return new ArrayList<>(uniqueProjects.values());
	    } else {
	        return projectRepository.findProject(adminDept);
	    }
	}

	// Utility method to remove the department part from project_id
	private String removeDepartmentFromProjectId(String projectId) {
	    String[] parts = projectId.split("/"); // Split by "/"
	    if (parts.length >= 4) {
	        // Concatenate without the department part (index 3)
	        return String.join("/", parts[0], parts[1], parts[2], parts[4]);
	    }
	    return projectId; // Return as is if format is unexpected
	}

	// Utility method to merge departments
	private String mergeDepartments(String existingDepartment, String newDepartment) {
	    if (existingDepartment == null || existingDepartment.isEmpty()) {
	        return newDepartment;
	    }
	    if (newDepartment == null || newDepartment.isEmpty()) {
	        return existingDepartment;
	    }
	    Set<String> departmentSet = new LinkedHashSet<>(Arrays.asList(existingDepartment.split(",")));
	    departmentSet.addAll(Arrays.asList(newDepartment.split(",")));
	    return String.join(",", departmentSet); // Return unique, comma-separated departments
	}
	// Utility method to remove the department part from project_id
	/*private String removeDepartmentFromProjectId(String projectId) {
	    String[] parts = projectId.split("/"); // Split by "/"
	    if (parts.length >= 4) {
	        // Concatenate without the department part (index 3)
	        return String.join("/", parts[0], parts[1], parts[2], parts[4]);
	    }
	    return projectId; // Return as is if format is unexpected
	}
	
	// Utility method to merge departments
	private String mergeDepartments(String existingDepartment, String newDepartment) {
	    if (existingDepartment == null || existingDepartment.isEmpty()) {
	        return newDepartment;
	    }
	    if (newDepartment == null || newDepartment.isEmpty()) {
	        return existingDepartment;
	    }
	    Set<String> departmentSet = new LinkedHashSet<>(Arrays.asList(existingDepartment.split(",")));
	    departmentSet.addAll(Arrays.asList(newDepartment.split(",")));
	    return String.join(",", departmentSet); // Return unique, comma-separated departments
	}
	*/

	
	public List<ProjectEntity> projectBydepartment(){		
		return projectRepository.findBydept();
	}
	
    public List<ProjectEntity> findByDelayProject(){		
		return projectRepository.findByDelayProject();
	}
	
	public void updateprojectFlag() {
		projectRepository.updateprojectFlag();
	}
	
	public void updateAcceptedProjectView() {
		projectRepository.updateAcceptedProjectView();
	}
	
	//updateAcceptedPrj
	public void updateAcceptedPrj(int id,int flag,String adminStartDate) {
		projectRepository.updateAcceptedPrj(id,flag,adminStartDate);
	}
	
	public void updateDelayPrj(int id,int flag,String reason,String startDate){
		projectRepository.updateDelayPrj(id,flag,reason,startDate);
	}
	
	public List<ProjectEntity> projectfindAll(){
		return projectRepository.findAll();
	}
	
	public List<ProjectProgress> findProjectDetailsByProjectId(String projectId){
		return progressRepo.findProjectDetailsByProjectId(projectId);
	}
	
	public List<String> fetProjectName() {
	    List<String> ongoingProjects = projectRepository.findProjectName();
	    
	    // Remove trailing numbers and filter duplicates
	    Set<String> uniqueProjects = ongoingProjects.stream()
	            .map(name -> name.replaceAll("_\\d+$", "")) // Remove trailing numbers
	            .collect(Collectors.toCollection(LinkedHashSet::new)); // Keep order and remove duplicates

	    // Print the unique project names
	    uniqueProjects.forEach(i -> System.out.println("uniqueProject: " + i));
	    
	    return List.copyOf(uniqueProjects); // Return as a list
	}

	
	/*
	 * public List<CompanyProjects_Entity> findAllCompanyProject(){ return
	 * companyProjectRepository.findAll(); }
	 */
	
	@Transactional
	public String projectSave(ProjectEntity projectEntity) {
		projectSaveRepo.save(projectEntity);
		return "success";
	}
	
	  @Transactional
	  public void saveOrUpdateProject(ProjectEntity projectEntity, List<Task> tasks,boolean addRequirement) {
	        // Save or update the project entity
	        //projectRepository.save(projectEntity);
		  
		    // Dynamically create a table for the project
		    /*String deptName=projectEntity.getDepartment();
	        System.out.println("deptName----"+deptName);
		    String tableName = projectEntity.getProject_name().replaceAll("\\s", "_");*/
		  
		  String deptName = projectEntity.getDepartment();
		  System.out.println("deptName----" + deptName);
		  String tableName = projectEntity.getProject_name().replaceAll("\\s", "_");
		  System.out.println("tableName after append----" + tableName);

	        // Check if the table exists
	        if(!addRequirement) {
	        	System.out.println("creating table---");
	        	customProjectRepository.createTable(tableName);
	        }	        
	        
	        // Insert values into the newly created table
	        int id = projectEntity.getId();
	        String prjName = projectEntity.getProject_name();
	        String teamMembers = projectEntity.getTeam_members();
	        String projectId = projectEntity.getProject_id();
	        String completion_date = projectEntity.getCompletion_date();
	        
	        for (Task task : tasks) {
	            String email = task.getEmp_mail();
	            String taskWeight = String.valueOf(task.getTaskWeight());
	            String projectId1 = task.getProjectId();
	            System.out.println("email for dynamic ref table"+email);
	            dynamicRefRepo.dynamicRefTable(email, taskWeight, projectId1);
	        }	        
	        
	        customProjectRepository.insertValuesIntoTable(tableName, id, prjName, teamMembers,projectId,completion_date,tasks);
	    }
	
	@Transactional
	public int updateAssignPrj(String projectManneger,String teamMemebers,String seniourName,String assignFlag,int prjId) {
		return projectSaveRepo.updateAssignPrj(projectManneger,teamMemebers,seniourName,assignFlag,prjId);	
	}
	
	public void insertproject_acceptence_flag(String projectId,String prjName,String email,String empName) {		
		projectSaveRepo.insertproject_acceptence_flag(prjName, projectId,email,empName);
	}
	
	public String finadNameByEmail(String email) {
		return projectSaveRepo.findFirstNameByEmail(email);
	}
	
	// Inside your service method
	public void updateTaskDueDates(long daysBetween, String email) {
	    System.out.println("Updating task duedate for email: " + email + " with days: " + daysBetween);
	    projectSaveRepo.updateTaskDueDateByEmail(daysBetween, email);
	}
	
	public List<EmployeeProjectProgressEntity> getEmployeProjectStatus(String projectId, String projectName, int adminDept) {
	    // Implement your logic here to fetch the data based on the projectId
	    
	    String convertedProjectName1 = projectName.toLowerCase().replace(" ", "_");
	    System.out.println("convertedProjectName1=" + convertedProjectName1);
	    System.out.println("projectId=" + projectId);
	    
	    
	    return customEmployeeProjectStatusRepo.findByProjectIdAndTableName(projectId, convertedProjectName1);
	}


	public List<String> getProjectByMail(String email) {
	    return projectSaveRepo.findProjectNamesByEmailAndStatusFlag(email);	
	}

	public void updateExtedDynamicTableDates(String email, String tableName,long daysBetween,int adminDept) {	
		customProjectRepository.extendDateInDynamicTable(email,tableName,daysBetween); 
		
	}

	public List<String> findExistingEmails(List<String> emails) {
	    return projectSaveRepo.findExistingEmails(emails);
	}
	
}
