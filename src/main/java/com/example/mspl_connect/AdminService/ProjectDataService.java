package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.ProjectData;
import com.example.mspl_connect.AdminRepo.ProjectDataRepository;

@Service
public class ProjectDataService {

	 @Autowired
	    private ProjectDataRepository projectDataRepository;

	/*public List<Map<String, Object>> getEmployeePerformance(String emailId) {
		    System.out.println("Fetching projects for emailId: " + emailId);

		    List<ProjectData> projectList = projectDataRepository.findByEmailId(emailId);
		    System.out.println("Retrieved project list: " + projectList);

		    List<Map<String, Object>> performanceData = new ArrayList<>();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    for (ProjectData project : projectList) {
		        System.out.println("Processing project: " + project.getProjectId());

		        LocalDate assignDate;
		        LocalDate taskDueDate;
		        LocalDate employeeCompletionDate;

		        try {
		            assignDate = LocalDate.parse(project.getAssignDate(), formatter);
		            taskDueDate = LocalDate.parse(project.getTaskDueDate(), formatter);
		            employeeCompletionDate = project.getEmployeeCompletionDate() != null
		                    ? LocalDate.parse(project.getEmployeeCompletionDate(), formatter)
		                    : null;

		            System.out.println("Parsed Dates - Assign Date: " + assignDate + ", Task Due Date: " + taskDueDate
		                    + ", Employee Completion Date: " + employeeCompletionDate);
		        } catch (DateTimeParseException e) {
		            System.err.println("Invalid date format in ProjectData for project " + project.getProjectId() + ": " + e.getMessage());
		            continue;
		        }

		        long totalDuration = ChronoUnit.DAYS.between(assignDate, taskDueDate);
		        double dailyPercentage = 100.0 / totalDuration;
		        System.out.println("Total assigned duration: " + totalDuration + " days");
		        System.out.println("Daily percentage for each day of assigned duration: " + dailyPercentage + "%");

		        double performance;

		        if (employeeCompletionDate != null) {
		            long employeeDuration = ChronoUnit.DAYS.between(assignDate, employeeCompletionDate);
		            System.out.println("Employee completed the task in: " + employeeDuration + " days");

		            if (!employeeCompletionDate.isAfter(taskDueDate)) {
		                // Task completed on or before due date: full performance
		                performance = 100.0;
		                System.out.println("Task completed on or before the due date. Performance set to: " + performance + "%");
		            } else {
		                // Task completed after due date: calculate negative performance
		                long overdueDays = ChronoUnit.DAYS.between(taskDueDate, employeeCompletionDate);
		                performance = 100 - (overdueDays * dailyPercentage);
		                System.out.println("Task overdue by: " + overdueDays + " days");
		                System.out.println("Performance after overdue deduction: " + performance + "%");
		            }
		        } else {
		            // Task not completed
		            performance = 0;
		            System.out.println("Task not completed. Performance set to: " + performance + "%");
		        }

		        System.out.println("Final calculated performance for project '" + project.getProjectName() + "' and task '" + project.getTaskName() + "': " + performance + "%");

		        Map<String, Object> projectInfo = new HashMap<>();
		        projectInfo.put("projectId", project.getProjectId());
		        projectInfo.put("projectName", project.getProjectName());
		       projectInfo.put("taskName", project.getTaskName());
		        projectInfo.put("subtaskName", project.getSubtask());
		        projectInfo.put("performance", performance);

		        performanceData.add(projectInfo);
		        System.out.println("Added project info: " + projectInfo);
		    }

		    System.out.println("Final performance data: " + performanceData);
		    return performanceData;
		}*/
	
	/*public List<Map<String, Object>> getEmployeePerformance(String emailId) {
	    System.out.println("Fetching projects for emailId: " + emailId);

	    List<ProjectData> projectList = projectDataRepository.findByEmailId(emailId);
	    System.out.println("Retrieved project list: " + projectList);

	    List<Map<String, Object>> performanceData = new ArrayList<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    double totalPerformance = 0;
	    int completedProjectsCount = 0;

	    
	    for (ProjectData project : projectList) {
	        System.out.println("Processing project: " + project.getProjectId());

	        LocalDate assignDate;
	        LocalDate taskDueDate;
	        LocalDate employeeCompletionDate;

	        try {
	            assignDate = LocalDate.parse(project.getAssignDate(), formatter);
	            taskDueDate = LocalDate.parse(project.getTaskDueDate(), formatter);
	            employeeCompletionDate = project.getEmployeeCompletionDate() != null
	                    ? LocalDate.parse(project.getEmployeeCompletionDate(), formatter)
	                    : null;

	            System.out.println("Parsed Dates - Assign Date: " + assignDate + ", Task Due Date: " + taskDueDate
	                    + ", Employee Completion Date: " + employeeCompletionDate);
	        } catch (DateTimeParseException e) {
	            System.err.println("Invalid date format in ProjectData for project " + project.getProjectId() + ": " + e.getMessage());
	            continue;
	        }

	        long totalDuration = ChronoUnit.DAYS.between(assignDate, taskDueDate);
	        double dailyPercentage = 100.0 / totalDuration;
	        System.out.println("Total assigned duration: " + totalDuration + " days");
	        System.out.println("Daily percentage for each day of assigned duration: " + dailyPercentage + "%");

	        double performance;

	        if (employeeCompletionDate != null) {
	            long employeeDuration = ChronoUnit.DAYS.between(assignDate, employeeCompletionDate);
	            System.out.println("Employee completed the task in: " + employeeDuration + " days");

	            if (!employeeCompletionDate.isAfter(taskDueDate)) {
	                performance = 100.0;
	            } else {
	                long overdueDays = ChronoUnit.DAYS.between(taskDueDate, employeeCompletionDate);
	                performance = 100 - (overdueDays * dailyPercentage);
	            }

	            totalPerformance += performance;
	            completedProjectsCount++;
	        } else {
	            performance = 0;
	        }

	        Map<String, Object> projectInfo = new HashMap<>();
	        projectInfo.put("projectId", project.getProjectId());
	        projectInfo.put("projectName", project.getProjectName());
	        projectInfo.put("taskName", project.getTaskName());
	        projectInfo.put("subtaskName", project.getSubtask());
	        projectInfo.put("performance", performance);

	        performanceData.add(projectInfo);
	    }

	    double overallPerformance = completedProjectsCount > 0 ? totalPerformance / completedProjectsCount : 0;
	    System.out.println("Calculated overall performance: " + overallPerformance + "%");

	    Map<String, Object> overallPerformanceData = new HashMap<>();
	    overallPerformanceData.put("projectName", "Overall Performance");
	    overallPerformanceData.put("performance", overallPerformance);

	    performanceData.add(overallPerformanceData);

	    return performanceData;
	}*/

	 /*public List<Map<String, Object>> getEmployeePerformance(String emailId) {
		    System.out.println("Fetching projects for emailId: " + emailId);

		    List<ProjectData> projectList = projectDataRepository.findByEmailId(emailId);
		    System.out.println("Retrieved project list: " + projectList);

		    List<Map<String, Object>> performanceData = new ArrayList<>();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    double totalPerformance = 0;
		    int completedProjectsCount = 0;

		    // Initialize quarterly performance data
		    Map<String, Double> quarterlyPerformance = new HashMap<>();
		    Map<String, Integer> quarterlyCounts = new HashMap<>();
		    String[] quarters = {"Q1", "Q2", "Q3", "Q4"};
		    for (String quarter : quarters) {
		        quarterlyPerformance.put(quarter, 0.0);
		        quarterlyCounts.put(quarter, 0);
		    }

		    for (ProjectData project : projectList) {
		        System.out.println("Processing project: " + project.getProjectId());

		        LocalDate assignDate;
		        LocalDate taskDueDate;
		        LocalDate employeeCompletionDate;

		        try {
		            assignDate = LocalDate.parse(project.getAssignDate(), formatter);
		            taskDueDate = LocalDate.parse(project.getTaskDueDate(), formatter);
		            employeeCompletionDate = project.getEmployeeCompletionDate() != null
		                    ? LocalDate.parse(project.getEmployeeCompletionDate(), formatter)
		                    : null;

		            System.out.println("Parsed Dates - Assign Date: " + assignDate + ", Task Due Date: " + taskDueDate
		                    + ", Employee Completion Date: " + employeeCompletionDate);
		        } catch (DateTimeParseException e) {
		            System.err.println("Invalid date format in ProjectData for project " + project.getProjectId() + ": " + e.getMessage());
		            continue;
		        }

		        long totalDuration = ChronoUnit.DAYS.between(assignDate, taskDueDate);
		        double dailyPercentage = 100.0 / totalDuration;
		        System.out.println("Total assigned duration: " + totalDuration + " days");
		        System.out.println("Daily percentage for each day of assigned duration: " + dailyPercentage + "%");

		        double performance;
		        String quarter = "";

		        if (employeeCompletionDate != null) {
		            long employeeDuration = ChronoUnit.DAYS.between(assignDate, employeeCompletionDate);
		            System.out.println("Employee completed the task in: " + employeeDuration + " days");

		            if (!employeeCompletionDate.isAfter(taskDueDate)) {
		                performance = 100.0;
		            } else {
		                long overdueDays = ChronoUnit.DAYS.between(taskDueDate, employeeCompletionDate);
		                performance = 100 - (overdueDays * dailyPercentage);
		            }

		            totalPerformance += performance;
		            completedProjectsCount++;

		            // Determine quarter based on employeeCompletionDate
		            int month = employeeCompletionDate.getMonthValue();
		            if (month >= 4 && month <= 6) {
		                quarter = "Q1";
		            } else if (month >= 7 && month <= 9) {
		                quarter = "Q2";
		            } else if (month >= 10 && month <= 12) {
		                quarter = "Q3";
		            } else {
		                quarter = "Q4";
		            }

		            // Aggregate performance for the specific quarter
		            quarterlyPerformance.put(quarter, quarterlyPerformance.get(quarter) + performance);
		            quarterlyCounts.put(quarter, quarterlyCounts.get(quarter) + 1);
		        } else {
		            performance = 0;
		        }

		        Map<String, Object> projectInfo = new HashMap<>();
		        projectInfo.put("projectId", project.getProjectId());
		        projectInfo.put("projectName", project.getProjectName());
		        projectInfo.put("taskName", project.getTaskName());
		        projectInfo.put("subtaskName", project.getSubtask());
		        projectInfo.put("performance", performance);
		        projectInfo.put("quarter", quarter);

		        performanceData.add(projectInfo);
		    }

		    double overallPerformance = completedProjectsCount > 0 ? totalPerformance / completedProjectsCount : 0;
		    System.out.println("Calculated overall performance: " + overallPerformance + "%");

		    Map<String, Object> overallPerformanceData = new HashMap<>();
		    overallPerformanceData.put("projectName", "Overall Performance");
		    overallPerformanceData.put("performance", overallPerformance);

		    performanceData.add(overallPerformanceData);

		    // Add quarterly performance data
		    for (String quarter : quarters) {
		        double quarterPerformance = quarterlyCounts.get(quarter) > 0
		                ? quarterlyPerformance.get(quarter) / quarterlyCounts.get(quarter)
		                : 0;

		        Map<String, Object> quarterlyData = new HashMap<>();
		        quarterlyData.put("projectName", "Quarterly Performance");
		        quarterlyData.put("subtaskName", quarter);
		        quarterlyData.put("performance", quarterPerformance);
		        performanceData.add(quarterlyData);
		    }

		    return performanceData;
		}*/
	 
	 
	 public List<Map<String, Object>> getEmployeePerformance(String emailId) {
		    System.out.println("Fetching projects for emailId: " + emailId);

		    List<ProjectData> projectList = projectDataRepository.findByEmailId(emailId);
		    System.out.println("Retrieved project list: " + projectList);

		    List<Map<String, Object>> performanceData = new ArrayList<>();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    
		    // If no projects are assigned, assume 100% performance
		  /*  if (projectList.isEmpty()) {
		        System.out.println("No projects assigned for emailId: " + emailId);
		        Map<String, Object> defaultPerformance = new HashMap<>();
		        defaultPerformance.put("projectName", "No Projects Assigned");
		        defaultPerformance.put("performance", 100.0); // Default to 100% performance
		        defaultPerformance.put("quarter", "N/A"); // No specific quarter
		        performanceData.add(defaultPerformance);
		        return performanceData;
		    }*/

		    double totalPerformance = 0;
		    int completedProjectsCount = 0;

		    // Get the current date to determine the financial year
		    LocalDate currentDate = LocalDate.now();
		    LocalDate startOfFinancialYear;
		    LocalDate endOfFinancialYear;

		    if (currentDate.getMonthValue() >= 4) {
		        // Financial year starts from April 1 of the current year
		        startOfFinancialYear = LocalDate.of(currentDate.getYear(), 4, 1);
		        endOfFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 31);
		    } else {
		        // Financial year started April 1 of the previous year
		        startOfFinancialYear = LocalDate.of(currentDate.getYear() - 1, 4, 1);
		        endOfFinancialYear = LocalDate.of(currentDate.getYear(), 3, 31);
		    }

		    // Initialize quarterly performance data
		    Map<String, Double> quarterlyPerformance = new HashMap<>();
		    Map<String, Integer> quarterlyCounts = new HashMap<>();
		    String[] quarters = {"Q1", "Q2", "Q3", "Q4"};
		    for (String quarter : quarters) {
		        quarterlyPerformance.put(quarter, 0.0);
		        quarterlyCounts.put(quarter, 0);
		    }

		    for (ProjectData project : projectList) {
		        System.out.println("Processing project: " + project.getProjectId());

		        LocalDate assignDate;
		        LocalDate taskDueDate;
		        LocalDate employeeCompletionDate;

		        try {
		            assignDate = LocalDate.parse(project.getAssignDate(), formatter);
		            taskDueDate = LocalDate.parse(project.getTaskDueDate(), formatter);
		            employeeCompletionDate = project.getEmployeeCompletionDate() != null
		                    ? LocalDate.parse(project.getEmployeeCompletionDate(), formatter)
		                    : null;

		            System.out.println("Parsed Dates - Assign Date: " + assignDate + ", Task Due Date: " + taskDueDate
		                    + ", Employee Completion Date: " + employeeCompletionDate);
		        } catch (DateTimeParseException e) {
		            System.err.println("Invalid date format in ProjectData for project " + project.getProjectId() + ": " + e.getMessage());
		            continue;
		        }

		        // Check if the project falls within the current financial year
		        if (assignDate.isBefore(startOfFinancialYear) || assignDate.isAfter(endOfFinancialYear)) {
		            System.out.println("Skipping project " + project.getProjectId() + " as it falls outside the financial year");
		            continue;
		        }

		        long totalDuration = ChronoUnit.DAYS.between(assignDate, taskDueDate);
		        double dailyPercentage = 100.0 / totalDuration;
		        System.out.println("Total assigned duration: " + totalDuration + " days");
		        System.out.println("Daily percentage for each day of assigned duration: " + dailyPercentage + "%");

		        double performance;
		        String quarter = "";

		        if (employeeCompletionDate != null) {
		            long employeeDuration = ChronoUnit.DAYS.between(assignDate, employeeCompletionDate);
		            System.out.println("Employee completed the task in: " + employeeDuration + " days");

		            if (!employeeCompletionDate.isAfter(taskDueDate)) {
		                performance = 100.0;
		            } else {
		                long overdueDays = ChronoUnit.DAYS.between(taskDueDate, employeeCompletionDate);
		                performance = 100 - (overdueDays * dailyPercentage);
		            }

		            totalPerformance += performance;
		            completedProjectsCount++;

		            // Determine quarter based on employeeCompletionDate
		            int month = employeeCompletionDate.getMonthValue();
		            if (month >= 4 && month <= 6) {
		                quarter = "Q1";
		            } else if (month >= 7 && month <= 9) {
		                quarter = "Q2";
		            } else if (month >= 10 && month <= 12) {
		                quarter = "Q3";
		            } else {
		                quarter = "Q4";
		            }

		            // Aggregate performance for the specific quarter
		            quarterlyPerformance.put(quarter, quarterlyPerformance.get(quarter) + performance);
		            quarterlyCounts.put(quarter, quarterlyCounts.get(quarter) + 1);
		        } else {
		            performance = 0;
		        }

		        Map<String, Object> projectInfo = new HashMap<>();
		        projectInfo.put("projectId", project.getProjectId());
		        projectInfo.put("projectName", project.getProjectName());
		        projectInfo.put("taskName", project.getTaskName());
		        projectInfo.put("subtaskName", project.getSubtask());
		        projectInfo.put("performance", performance);
		        projectInfo.put("quarter", quarter);

		        performanceData.add(projectInfo);
		    }

		    double overallPerformance = completedProjectsCount > 0 ? totalPerformance / completedProjectsCount : 0;
		    System.out.println("Calculated overall performance: " + overallPerformance + "%");

		    Map<String, Object> overallPerformanceData = new HashMap<>();
		    overallPerformanceData.put("projectName", "Overall Performance");
		    overallPerformanceData.put("performance", overallPerformance);

		    performanceData.add(overallPerformanceData);

		    // Add quarterly performance data
		    for (String quarter : quarters) {
		        double quarterPerformance = quarterlyCounts.get(quarter) > 0
		                ? quarterlyPerformance.get(quarter) / quarterlyCounts.get(quarter)
		                : 0;

		        Map<String, Object> quarterlyData = new HashMap<>();
		        quarterlyData.put("projectName", "Quarterly Performance");
		        quarterlyData.put("subtaskName", quarter);
		        quarterlyData.put("performance", quarterPerformance);
		        performanceData.add(quarterlyData);
		    }

		    return performanceData;
		}

	 public List<Map<String, Object>> calculateProjectPerformance(String userEmail, String projectId, String projectName) {
		    System.out.println("Fetching performance for project: " + projectId + " and user: " + userEmail);

		    List<ProjectData> projectList = projectDataRepository.findByEmailId(userEmail);
		    System.out.println("Retrieved project list: " + projectList);

		    List<Map<String, Object>> projectPerformanceResults = new ArrayList<>();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    double totalProjectPerformance = 0;
		    int completedTasksCount = 0;

		    LocalDate currentDate = LocalDate.now();
		    LocalDate startOfFinancialYear;
		    LocalDate endOfFinancialYear;

		    if (currentDate.getMonthValue() >= 4) {
		        startOfFinancialYear = LocalDate.of(currentDate.getYear(), 4, 1);
		        endOfFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 31);
		    } else {
		        startOfFinancialYear = LocalDate.of(currentDate.getYear() - 1, 4, 1);
		        endOfFinancialYear = LocalDate.of(currentDate.getYear(), 3, 31);
		    }

		    for (ProjectData project : projectList) {
		        // Only calculate for the requested project ID and project name
		        if (!project.getProjectId().equals(projectId) || !project.getProjectName().equals(projectName)) {
		            continue; // Skip this project if it doesn't match the requested one
		        }

		        System.out.println("Processing project: " + project.getProjectId());

		        LocalDate taskAssignmentDate;
		        LocalDate taskDueDate;
		        LocalDate taskCompletionDate = null;

		        try {
		            taskAssignmentDate = LocalDate.parse(project.getAssignDate(), formatter);
		            taskDueDate = LocalDate.parse(project.getTaskDueDate(), formatter);
		            if (project.getEmployeeCompletionDate() != null) {
		                taskCompletionDate = LocalDate.parse(project.getEmployeeCompletionDate(), formatter);
		            }
		        } catch (DateTimeParseException e) {
		            System.err.println("Invalid date format in ProjectData for project " + project.getProjectId() + ": " + e.getMessage());
		            continue;
		        }

		        if (taskAssignmentDate.isBefore(startOfFinancialYear) || taskAssignmentDate.isAfter(endOfFinancialYear)) {
		            System.out.println("Skipping project " + project.getProjectId() + " as it falls outside the financial year");
		            continue;
		        }

		        long taskDuration = ChronoUnit.DAYS.between(taskAssignmentDate, taskDueDate);
		        double dailyPerformance = 100.0 / taskDuration;

		        double performanceScore = 0;

		        if (taskCompletionDate != null) {
		            long taskCompletionDuration = ChronoUnit.DAYS.between(taskAssignmentDate, taskCompletionDate);
		            if (!taskCompletionDate.isAfter(taskDueDate)) {
		                performanceScore = 100.0; // Task completed on time
		            } else {
		                long overdueDays = ChronoUnit.DAYS.between(taskDueDate, taskCompletionDate);
		                performanceScore = 100 - (overdueDays * dailyPerformance); // Task completed late
		            }

		            totalProjectPerformance += performanceScore;
		            completedTasksCount++;
		        }

		        Map<String, Object> projectDetails = new HashMap<>();
		        projectDetails.put("projectId", project.getProjectId());
		        projectDetails.put("projectName", project.getProjectName());
		        projectDetails.put("taskName", project.getTaskName());
		        projectDetails.put("subtaskName", project.getSubtask());
		        projectDetails.put("completionPercentage", performanceScore);

		        projectPerformanceResults.add(projectDetails);
		    }

		    // Calculate overall performance if there are completed tasks
		    double overallProjectPerformance = completedTasksCount > 0 ? totalProjectPerformance / completedTasksCount : 0;
		    Map<String, Object> overallPerformance = new HashMap<>();
		    overallPerformance.put("projectName", "Overall Performance");
		    overallPerformance.put("completionPercentage", overallProjectPerformance);

		    projectPerformanceResults.add(overallPerformance);

		    return projectPerformanceResults;
		}

	 public List<ProjectData> getTasksForProjectAndEmployee(String projectName, String email) {
		    return projectDataRepository.findByProjectNameAndEmailId(projectName, email);
		}


}
