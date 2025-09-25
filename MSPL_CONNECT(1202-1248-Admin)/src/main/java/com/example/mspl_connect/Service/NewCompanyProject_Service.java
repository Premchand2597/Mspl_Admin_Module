package com.example.mspl_connect.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.NewCompanyProject_Entity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.NewCompanyProject_Repo;

import jakarta.transaction.Transactional;

@Service
public class NewCompanyProject_Service {
	
	@Autowired
	private NewCompanyProject_Repo newCompanyProject_Repo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	public boolean isProjectIdExists(String projectId) {
        return newCompanyProject_Repo.existsByprojectId(projectId);
    }

	@Transactional
	public String save(NewCompanyProject_Entity newCompProj_Entity) {
	    // Log the input entity for debugging
	    System.out.println("prj in service" + newCompProj_Entity);

	    // Determine the financial year dynamically
	    String financialYear = getFinancialYear();
	    String prjType = newCompProj_Entity.getPrj_type();
	    
	    // Get the last project ID and calculate the new one
	    Integer lastId = newCompanyProject_Repo.findLastId();
	    int previousPrjId = (lastId != null) ? lastId + 1 : 1;
	    // Create a map for department abbreviations
	    Map<String, String> departmentAbbreviations = new HashMap<>();
	    departmentAbbreviations.put("IT", "IT");
	    departmentAbbreviations.put("Embedded", "Emb");
	    departmentAbbreviations.put("Software", "Sw");
	    departmentAbbreviations.put("Testing", "Test");
	    departmentAbbreviations.put("Super Admin", "SA");
	    
	    // Generate the project ID with the dynamic financial year;
	    // String prjId = "MSPL/" + financialYear + "/" + prjType + "/" + previousPrjId;
	    
	    // Format the completion date if it's not null
	    if (newCompProj_Entity.getCompletion_date() != null) {
	        LocalDate completionDate = LocalDate.parse(newCompProj_Entity.getCompletion_date());
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedCompletionDate = completionDate.format(formatter);
	        newCompProj_Entity.setCompletion_date(formattedCompletionDate);
	    }

	    // Split the department string into individual departments
	    String[] departments = newCompProj_Entity.getDepartment().split(",");

	    // Loop through the departments and create a new entity for each one
	    for (String department : departments) {
	        // Create a new entity by copying the existing one
	        NewCompanyProject_Entity newProject = new NewCompanyProject_Entity();
	        
	        // Get the abbreviation for the department
	        
	        System.out.println("department===="+department);
	        Integer deptId= departmentRepo.findIdByDeptName(department);
	        System.out.println("deptId==="+deptId);
	        
	        System.out.println("prjName before=="+newCompProj_Entity.getProject_name());
	        //String prjName=newCompProj_Entity.getProject_name().replaceAll("\\s", "_") + "_" + deptId;
	        String prjName = newCompProj_Entity.getProject_name().replaceAll("\\s", "_").toLowerCase() + "_" + deptId;

	        //System.out.println("prjName after=="+prjName);
	        
	        
	        //String deptName = projectEntity.getDepartment();
			//System.out.println("deptName----" + deptName);
			//String tableName = projectEntity.getProject_name().replaceAll("\\s", "_") + "_" + deptName;
			//System.out.println("tableName after append----" + tableName);
	        
	        String departmentAbbr = departmentAbbreviations.getOrDefault(department, department); // Default to the full name if not found

	        // Generate the project ID with the department abbreviation and dynamic financial year
	        String prjId = "MSPL/" + financialYear + "/" + prjType + "/" + departmentAbbr + "/" + previousPrjId;

	        // System.out.println("getAdmin_completion_date()-----"+newCompProj_Entity.getAdmin_completion_date());
	        // Set the fields from the original entity, excluding the department
	        
	        newProject.setProject_name(prjName);
	        newProject.setAssigned_date(newCompProj_Entity.getAssigned_date());
	        newProject.setCompleted_date("Pending");
	        newProject.setProject_manager(newCompProj_Entity.getProject_manager());
	        newProject.setDescription(newCompProj_Entity.getDescription());
	        newProject.setDocument(newCompProj_Entity.getDocument());
	        newProject.setStatus(newCompProj_Entity.getStatus());
	        newProject.setTeam_members(newCompProj_Entity.getTeam_members());
	        newProject.setSenior_name(newCompProj_Entity.getSenior_name());
	        newProject.setClient_name(newCompProj_Entity.getClient_name());
	        newProject.setFlag("1");
	        newProject.setPlatform_to_work(newCompProj_Entity.getPlatform_to_work());
	        newProject.setDelay_reason(newCompProj_Entity.getDelay_reason());
	        newProject.setStart_date(newCompProj_Entity.getStart_date());
	        newProject.setAdmin_completion_date(newCompProj_Entity.getAdmin_completion_date());
	        
	        newProject.setCompletion_date(newCompProj_Entity.getCompletion_date());
	        newProject.setPrj_type(newCompProj_Entity.getPrj_type());
	        newProject.setRef_prj(newCompProj_Entity.getRef_prj());

	        // Set the department for the new entity
	        newProject.setDepartment(department);
	        
	        /*NewCompanyProject_Entity project = newCompanyProject_Repo.findByProjectId(prjId);
	        System.out.println("project........"+project);
	        // If no project is found, throw a custom exception
	        if (project != null) {
	        	String empMails=project.getTeam_members();
	            System.out.println("project---"+empMails);
	        }*/
	        
	        // Set the project ID and completion date
	        newProject.setProjectId(prjId);
	        
	        // Save the entity for each department
	        newCompanyProject_Repo.save(newProject);
	    }

	    return "success";
	}


	// Method to calculate the current financial year in the format F24-25
	private String getFinancialYear() {
	    LocalDate today = LocalDate.now(); // Get the current date
	    int currentYear = today.getYear(); // Get the current year
	    LocalDate fiscalYearStart = LocalDate.of(currentYear, Month.MARCH, 26); // Start of the financial year

	    // If today's date is before March 26, use the previous financial year
	    if (today.isBefore(fiscalYearStart)) {
	        int startYear = currentYear - 1;
	        int endYear = currentYear;
	        return "F" + (startYear % 100) + "-" + (endYear % 100);
	    } else {
	        // Otherwise, use the current year and the next year for the financial year
	        int startYear = currentYear;
	        int endYear = currentYear + 1;
	        return "F" + (startYear % 100) + "-" + (endYear % 100);
	    }
	}

	
	public NewCompanyProject_Entity getCompProjectDataById(String id) {
		return newCompanyProject_Repo.fetchCompanyProjectBasedOnId(id);
	}
	
	public void updateCompViewedPageData() {
		newCompanyProject_Repo.updateCompanyViewedPageFlag();
	}
}
