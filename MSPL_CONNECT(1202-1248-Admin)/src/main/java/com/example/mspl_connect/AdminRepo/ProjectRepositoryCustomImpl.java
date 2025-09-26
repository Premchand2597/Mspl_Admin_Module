package com.example.mspl_connect.AdminRepo;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mspl_connect.AdminEntity.TeamProject;
import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Primary
public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

	 @PersistenceContext
	    private EntityManager entityManager;

	    @Autowired
	    private TeamProjectRepository teamProjectRepository;
	    
	    private int previousCount = 0;
	    private Set<String> countedTables = new HashSet<>(); 
	    private Map<String, Integer> tableCounts = new HashMap<>();


	    
	  // new working correct but cmmented because it as showing error when dynamic table is not present   
	  @Override
	    public int fetchNewProjectCounts(String email, String projectName) {
	        String dynamicTableName = teamProjectRepository.findProjectName(projectName);

	        System.out.println("Dynamic table name fetched: " + dynamicTableName);

	        if (dynamicTableName == null) {
	            throw new IllegalArgumentException("Invalid project name");
	        }

	        // Replace spaces with underscores in the table name
	        dynamicTableName = dynamicTableName.replace(" ", "_");
	        // Escape the dynamic table name with backticks (`) to handle spaces
	        dynamicTableName = "`" + dynamicTableName.replace("`", "``") + "`";

	        System.out.println("Executing query for table: " + dynamicTableName);

	        String sql = "SELECT COALESCE(SUM(CASE WHEN tp.assign_flag = '2' AND t.notifyflag = '1' THEN 1 ELSE 0 END), 0) AS view_flag " +
	                     "FROM team_project tp " +
	                     "JOIN " + dynamicTableName + " t ON tp.project_id = t.project_id " +
	                     "WHERE t.email_id = :email";

	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("email", email);

	        // Execute the query
	        Number result = (Number) query.getSingleResult();
	        System.out.println("result: " + result);

	        // Retrieve and store the result
	        int viewFlag = result != null ? result.intValue() : 0;

	        // Optionally, you can log or store this value further
	        System.out.println("View flag for email " + email + ": " + viewFlag);

	        // Check if the table has been previously counted
	        if (tableCounts.containsKey(dynamicTableName)) {
	            int previousViewFlag = tableCounts.get(dynamicTableName);
	            if (previousViewFlag != viewFlag) {
	                // Update the previousCount by removing the old count and adding the new count
	                previousCount = previousCount - previousViewFlag + viewFlag;
	                // Update the count for this table
	                tableCounts.put(dynamicTableName, viewFlag);
	            }
	        } else {
	            // Add the new count to previousCount
	            previousCount += viewFlag;
	            // Store the count for this table
	            tableCounts.put(dynamicTableName, viewFlag);
	        }

	        System.out.println("Updated previous count: " + previousCount);
	        System.out.println("Table counts so far: " + tableCounts);

	        return previousCount;
	    }
	   
	/*   @Override
	    public int fetchNewProjectCounts(String email, String projectName) {
	        // Fetch dynamic table name based on project name
	        String dynamicTableName = teamProjectRepository.findProjectName(projectName);

	        System.out.println("Dynamic table name fetched: " + dynamicTableName);

	        if (dynamicTableName == null) {
	            throw new IllegalArgumentException("Invalid project name");
	        }

	        // Replace spaces with underscores in the table name
	        dynamicTableName = dynamicTableName.replace(" ", "_");
	        // Escape the dynamic table name with backticks (`) to handle spaces
	        dynamicTableName = "`" + dynamicTableName.replace("`", "``") + "`";

	        System.out.println("Executing query for table: " + dynamicTableName);

	        String sql = "SELECT COALESCE(SUM(CASE WHEN tp.assign_flag = '2' AND t.notifyflag = '1' THEN 1 ELSE 0 END), 0) AS view_flag " +
	                     "FROM team_project tp " +
	                     "JOIN " + dynamicTableName + " t ON tp.project_id = t.project_id " +
	                     "WHERE t.email_id = :email";

	        try {
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("email", email);

	            // Execute the query
	            List<?> resultList = query.getResultList();
	            
	            // Initialize the viewFlag variable to accumulate results
	            int viewFlag = 0;

	            // Iterate over the result list and accumulate the view flags
	            for (Object result : resultList) {
	                if (result instanceof Number) {
	                    viewFlag += ((Number) result).intValue();
	                }
	            }

	            System.out.println("Result: " + viewFlag);

	            // Optionally, you can log or store this value further
	            System.out.println("View flag for email " + email + ": " + viewFlag);

	            // Update counts only if the table exists and has valid data
	            if (tableCounts.containsKey(dynamicTableName)) {
	                int previousViewFlag = tableCounts.get(dynamicTableName);
	                if (previousViewFlag != viewFlag) {
	                    // Update the previousCount by removing the old count and adding the new count
	                    previousCount = previousCount - previousViewFlag + viewFlag;
	                    // Update the count for this table
	                    tableCounts.put(dynamicTableName, viewFlag);
	                }
	            } else {
	                // Add the new count to previousCount
	                previousCount += viewFlag;
	                // Store the count for this table
	                tableCounts.put(dynamicTableName, viewFlag);
	            }

	            System.out.println("Updated previous count: " + previousCount);
	            System.out.println("Table counts so far: " + tableCounts);

	            return previousCount;

	        } catch (NoResultException e) {
	            // Handle the case where no result is returned
	            System.out.println("No results found for table: " + dynamicTableName);
	            return previousCount; // Return current previous count without updating

	        } catch (PersistenceException e) {
	            // Handle persistence exceptions that may include table not found scenarios
	            if (e.getCause() instanceof SQLException) {
	                SQLException sqlException = (SQLException) e.getCause();
	                if (sqlException.getSQLState().equals("42S02")) { // SQLState for table not found
	                    System.out.println("Table '" + dynamicTableName + "' does not exist. Skipping this table.");
	                    return previousCount; // Return current previous count without updating
	                } else {
	                    throw new RuntimeException("SQL error occurred: " + sqlException.getMessage(), sqlException);
	                }
	            } else {
	                throw new RuntimeException("Persistence error occurred: " + e.getMessage(), e);
	            }
	        }
	    }*/

	    
	    

	    
	/*  @Override
	    public void updateNotifyFlag(String email, List<String> projectNames) {
	        List<String> dynamicTableNames = teamProjectRepository.findnewProjectNames(projectNames);

	        System.out.println("Dynamic table names fetched for update: " + dynamicTableNames);

	      
	        
	     // Check if the dynamic table names list is empty or null
	        if (dynamicTableNames == null || dynamicTableNames.isEmpty()) {
	            // Log a message and return early without throwing an exception
	            System.out.println("No valid project names found for email: " + email);
	            return;
	        }

	        for (String dynamicTableName : dynamicTableNames) {
	            // Replace spaces with underscores in the table name
	            dynamicTableName = dynamicTableName.replace(" ", "_");
	            // Escape the dynamic table name with backticks (`) to handle spaces
	            dynamicTableName = "`" + dynamicTableName.replace("`", "``") + "`";

	            String updateSql = "UPDATE " + dynamicTableName + " SET notifyflag = 0 WHERE email_id = :email";
	            Query updateQuery = entityManager.createNativeQuery(updateSql);
	            updateQuery.setParameter("email", email);
	            updateQuery.executeUpdate();

	            System.out.println("Notify flag updated for email " + email + " in table " + dynamicTableName);
	        }
	    }*/


	  @Override
	  public void updateNotifyFlag(String email, List<String> projectNames) {
	      List<String> dynamicTableNames = teamProjectRepository.findnewProjectNames(projectNames);
	      System.out.println("Dynamic table names fetched for update: " + dynamicTableNames);

	      // Check if the dynamic table names list is empty or null
	      if (dynamicTableNames == null || dynamicTableNames.isEmpty()) {
	          System.out.println("No valid project names found for email: " + email);
	          return;
	      }

	      // Get the existing table names from the database
	      List<String> existingTables = getExistingTableNames(dynamicTableNames);
	      System.out.println("Existing tables: " + existingTables);

	      for (String dynamicTableName : existingTables) {
	          try {
	              // Replace spaces with underscores in the table name
	              dynamicTableName = dynamicTableName.replace(" ", "_");
	              // Escape the dynamic table name with backticks (`) to handle spaces
	              dynamicTableName = "`" + dynamicTableName.replace("`", "``") + "`";

	              String updateSql = "UPDATE " + dynamicTableName + " SET notifyflag = 0 WHERE email_id = :email";
	              Query updateQuery = entityManager.createNativeQuery(updateSql);
	              updateQuery.setParameter("email", email);
	              updateQuery.executeUpdate();

	              System.out.println("Notify flag updated for email " + email + " in table " + dynamicTableName);
	          } catch (InvalidDataAccessResourceUsageException e) {
	              // Log the error and skip this table
	              System.out.println("Table " + dynamicTableName + " does not exist, skipping update.");
	          } catch (Exception e) {
	              // Log other exceptions (optional)
	              System.out.println("An error occurred while updating notify flag for table " + dynamicTableName + ": " + e.getMessage());
	          }
	      }
	  }

	// Method to check existing tables in the database
	  private List<String> getExistingTableNames(List<String> tableNames) {
	      List<String> existingTables = new ArrayList<>();
	      
	      for (String tableName : tableNames) {
	          // You can use a query to check if the table exists
	          String checkTableSql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'mspl_connect' AND table_name = :tableName";
	          Query checkQuery = entityManager.createNativeQuery(checkTableSql);
	          checkQuery.setParameter("tableName", tableName.toLowerCase()); // Use lower case if your DB stores table names in lower case
	          
	          Long count = (Long) checkQuery.getSingleResult();
	          if (count > 0) {
	              existingTables.add(tableName);
	              System.out.println("llee"+existingTables);
	          } else {
	              System.out.println("Table " + tableName + " does not exist.");
	          }
	      }
	      
	      return existingTables;
	  }

	/*    @Override
	    public void updateNotifyFlag(String email, List<String> projectNames) {
	        List<String> dynamicTableNames = teamProjectRepository.findnewProjectNames(projectNames);

	        System.out.println("Dynamic table names fetched for update: " + dynamicTableNames);

	        // Check if the dynamic table names list is empty or null
	        if (dynamicTableNames == null || dynamicTableNames.isEmpty()) {
	            // Log a message and return early without throwing an exception
	            System.out.println("No valid project names found for email: " + email);
	            return;
	        }

	        for (String dynamicTableName : dynamicTableNames) {
	            // Replace spaces with underscores in the table name
	            dynamicTableName = dynamicTableName.replace(" ", "_");
	            // Escape the dynamic table name with backticks (`) to handle spaces
	            dynamicTableName = "`" + dynamicTableName.replace("`", "``") + "`";

	            // Check if the table exists
	            String checkTableExistsSql = "SHOW TABLES LIKE :tableName";
	            Query checkTableExistsQuery = entityManager.createNativeQuery(checkTableExistsSql);
	            checkTableExistsQuery.setParameter("tableName", dynamicTableName);

	            List<?> result = checkTableExistsQuery.getResultList();
	            if (result.isEmpty()) {
	                // Log a message and skip the update if the table does not exist
	                System.out.println("Table " + dynamicTableName + " does not exist. Skipping update.");
	                continue;
	            }

	            // Proceed with the update if the table exists
	            String updateSql = "UPDATE " + dynamicTableName + " SET notifyflag = 0 WHERE email_id = :email";
	            Query updateQuery = entityManager.createNativeQuery(updateSql);
	            updateQuery.setParameter("email", email);
	            int rowsUpdated = updateQuery.executeUpdate();

	            // Log the number of rows updated
	            System.out.println("Notify flag updated for email " + email + " in table " + dynamicTableName + ". Rows updated: " + rowsUpdated);
	        }
	    }*/



	  



	    

	    
	/*    @Transactional
	    @Override
	    public void updateAcceptanceDate(String projectId, String email, String taskName) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();
	            String projectIdentifier = project.getProject_id();
	            String dynamicTableName = project.getProject_name().replace(" ", "_");

	            // Check if a record already exists for this project, email, and task
	            String checkSql = "SELECT COUNT(*) FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectIdentifier AND task_name = :taskName";
	            Query checkQuery = entityManager.createNativeQuery(checkSql);
	            checkQuery.setParameter("email", email);
	            checkQuery.setParameter("projectIdentifier", projectIdentifier);
	            checkQuery.setParameter("taskName", taskName);
	            
	            int count = ((Number) checkQuery.getSingleResult()).intValue();
	            
	            if (count > 0) {
	                // Update existing record
	                String updateSql = "UPDATE `" + dynamicTableName + "` SET acceptanceDate = :acceptanceDate WHERE email_id = :email AND project_id = :projectIdentifier AND task_name = :taskName";
	                Query updateQuery = entityManager.createNativeQuery(updateSql);
	                
	                // Format LocalDateTime as String
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                String formattedAcceptanceDate = LocalDateTime.now().format(formatter);
	                
	                // Set parameters
	                updateQuery.setParameter("acceptanceDate", formattedAcceptanceDate);
	                updateQuery.setParameter("email", email);
	                updateQuery.setParameter("projectIdentifier", projectIdentifier);
	                updateQuery.setParameter("taskName", taskName);
	                
	                // Execute update
	                int updatedRows = updateQuery.executeUpdate();
	                
	                if (updatedRows == 0) {
	                    throw new IllegalArgumentException("Failed to update acceptanceDate, no rows updated.");
	                }
	                
	                System.out.println("Acceptance date updated for project ID " + projectIdentifier + " and task " + taskName + " in table " + dynamicTableName);
	            } else {
	                throw new IllegalArgumentException("No existing record found for project ID " + projectIdentifier + ", email " + email + ", and task " + taskName);
	            }
	            
	        } catch (Exception e) {
	            System.err.println("Failed to update acceptance date: " + e.getMessage());
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }*/
	  
	  @Transactional
	  @Override
	  public void updateAcceptanceDate(String projectId, String email, String taskName, String subtaskName) {
	      try {
	          // Fetch dynamic table name based on projectId
	          Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	          if (!projectOptional.isPresent()) {
	              throw new IllegalArgumentException("Invalid project ID");
	          }
	          TeamProject project = projectOptional.get();
	          String projectIdentifier = project.getProject_id();
	          String dynamicTableName = project.getProject_name().replace(" ", "_");

	          // Check if a record already exists for this project, email, task, and subtask
	          String checkSql = "SELECT COUNT(*) FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectIdentifier AND task_name = :taskName AND subtask = :subtaskName";
	          Query checkQuery = entityManager.createNativeQuery(checkSql);
	          checkQuery.setParameter("email", email);
	          checkQuery.setParameter("projectIdentifier", projectIdentifier);
	          checkQuery.setParameter("taskName", taskName);
	          checkQuery.setParameter("subtaskName", subtaskName);
	          
	          int count = ((Number) checkQuery.getSingleResult()).intValue();
	          
	          if (count > 0) {
	              // Update existing record for the specific task and subtask
	              String updateSql = "UPDATE `" + dynamicTableName + "` SET acceptanceDate = :acceptanceDate WHERE email_id = :email AND project_id = :projectIdentifier AND task_name = :taskName AND subtask = :subtaskName";
	              Query updateQuery = entityManager.createNativeQuery(updateSql);
	              
	              // Format LocalDateTime as String
	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	              String formattedAcceptanceDate = LocalDateTime.now().format(formatter);
	              
	              // Set parameters
	              updateQuery.setParameter("acceptanceDate", formattedAcceptanceDate);
	              updateQuery.setParameter("email", email);
	              updateQuery.setParameter("projectIdentifier", projectIdentifier);
	              updateQuery.setParameter("taskName", taskName);
	              updateQuery.setParameter("subtaskName", subtaskName);
	              
	              // Execute update
	              int updatedRows = updateQuery.executeUpdate();
	              
	              if (updatedRows == 0) {
	                  throw new IllegalArgumentException("Failed to update acceptanceDate, no rows updated.");
	              }
	              
	              System.out.println("Acceptance date updated for project ID " + projectIdentifier + ", task " + taskName + ", and subtask " + subtaskName + " in table " + dynamicTableName);
	          } else {
	              throw new IllegalArgumentException("No existing record found for project ID " + projectIdentifier + ", email " + email + ", task " + taskName + ", and subtask " + subtaskName);
	          }
	          
	      } catch (Exception e) {
	          System.err.println("Failed to update acceptance date: " + e.getMessage());
	          throw e; // Rethrow or handle the exception as needed
	      }
	  }


	    
	    
	    
	/*    @Override
	    public String findAcceptanceDate(String projectId, String email, String taskName) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_"); // Replace spaces with underscores
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to fetch acceptanceDate
	            String sql = "SELECT acceptanceDate FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectId AND task_name = :taskName";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);
	            query.setParameter("taskName", taskName);

	            // Execute query and fetch acceptance date
	            Object result = query.getSingleResult();
	            if (result != null) {
	                return result.toString(); // Return acceptanceDate as String
	            } else {
	                throw new IllegalArgumentException("No acceptance date found for project ID " + projectId + ", email " + email + ", and task " + taskName);
	            }
	        } catch (Exception e) {
	            System.err.println("Failed to fetch acceptance date: " + e.getMessage());
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }*/
	    
	  /*  @Override
	    public Map<String, String> findAcceptanceDateAndDueDate(String projectId, String email, String taskName) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_");
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to fetch acceptanceDate and taskDueDate
	            String sql = "SELECT acceptanceDate, task_duedate FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectId AND task_name = :taskName";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);
	            query.setParameter("taskName", taskName);

	            // Execute query and fetch results
	            Object[] result = (Object[]) query.getSingleResult();
	            if (result != null) {
	                Map<String, String> dates = new HashMap<>();
	                dates.put("acceptanceDate", result[0].toString());
	                dates.put("taskDueDate", result[1].toString());
	                return dates;
	            } else {
	                throw new IllegalArgumentException("No dates found for project ID " + projectId + ", email " + email + ", and task " + taskName);
	            }
	        } catch (Exception e) {
	            System.err.println("Failed to fetch dates: " + e.getMessage());
	            throw e;
	        }
	    }*/
	  
	  @Override
	  public Map<String, String> findAcceptanceDateAndDueDate(String projectId, String email, String taskName, String subtaskName) {
	      try {
	          // Fetch dynamic table name based on projectId
	          Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	          if (!projectOptional.isPresent()) {
	              throw new IllegalArgumentException("Invalid project ID");
	          }
	          TeamProject project = projectOptional.get();

	          // Construct dynamic table name, sanitizing project name
	          String dynamicTableName = project.getProject_name().replace(" ", "_");
	          System.out.println("Dynamic table name: " + dynamicTableName);

	          // Construct and execute SQL query to fetch acceptanceDate and taskDueDate
	          String sql = "SELECT acceptanceDate, task_duedate FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectId AND task_name = :taskName AND subtask= :subtaskName";
	          Query query = entityManager.createNativeQuery(sql);
	          query.setParameter("email", email);
	          query.setParameter("projectId", projectId);
	          query.setParameter("taskName", taskName);
	          query.setParameter("subtaskName", subtaskName); // Add the subtaskName parameter

	          // Execute query and fetch results
	          Object[] result = (Object[]) query.getSingleResult();
	          if (result != null) {
	              Map<String, String> dates = new HashMap<>();
	              dates.put("acceptanceDate", result[0].toString());
	              dates.put("taskDueDate", result[1].toString());
	              return dates;
	          } else {
	              throw new IllegalArgumentException("No dates found for project ID " + projectId + ", email " + email + ", task " + taskName + ", and subtask " + subtaskName);
	          }
	      } catch (Exception e) {
	          System.err.println("Failed to fetch dates: " + e.getMessage());
	          throw e;
	      }
	  }




	    
	/*    @Override
	    public List<Map<String, String>> findProjectDetails(String projectId, String email) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_"); // Replace spaces with underscores
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to fetch task details including task_status
	            String sql = "SELECT task_name, task_description, task_duedate, task_weight, task_status, subtask " +
	                         "FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectId";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);

	            // Execute query and fetch results
	            List<Object[]> results = query.getResultList();
	            List<Map<String, String>> taskDetailsList = new ArrayList<>();
	            for (Object[] result : results) {
	                Map<String, String> taskDetails = new HashMap<>();
	                taskDetails.put("taskName", result[0].toString());
	                taskDetails.put("taskDescription", result[1].toString());
	                taskDetails.put("taskDueDate", result[2].toString());
	                taskDetails.put("taskWeight", result[3].toString());
	                // Determine if the task is accepted based on task_status
	                taskDetails.put("isAccepted", "Accepted".equals(result[4]) ? "true" : "false");
	                taskDetails.put("subtask", result[5] != null ? result[5].toString() : "No Subtask");
	                
	                taskDetailsList.add(taskDetails);
	            }
	            return taskDetailsList;
	        } catch (Exception e) {
	            System.err.println("Failed to fetch project details: " + e.getMessage());
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }*/
	    @Override
	    public List<Map<String, String>> findProjectDetails(String projectId, String email) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_"); // Replace spaces with underscores
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to fetch task details including task_status
	            String sql = "SELECT task_name, task_description, task_duedate, task_weight, task_status, subtask, subtask_description, assign_date  " +
	                         "FROM `" + dynamicTableName + "` WHERE email_id = :email AND project_id = :projectId";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);

	            // Execute query and fetch results
	            List<Object[]> results = query.getResultList();
	            List<Map<String, String>> taskDetailsList = new ArrayList<>();
	            
	            for (Object[] result : results) {
	                Map<String, String> taskDetails = new HashMap<>();
	                taskDetails.put("taskName", result[0].toString());
	                taskDetails.put("taskDescription", result[1].toString());
	                taskDetails.put("taskDueDate", result[2].toString());
	                taskDetails.put("taskWeight", result[3].toString());
	                // Determine if the task is accepted based on task_status
	                taskDetails.put("isAccepted", "Accepted".equals(result[4]) ? "true" : "false");

	                // Check if subtasks are available
	                String subtask = result[5] != null ? result[5].toString() : null;
	                String subtaskDescription = result[6] != null ? result[6].toString() : null; // Fetch subtask description
	                
	                taskDetails.put("subtask", subtask != null ? subtask : "No Subtask");
	                taskDetails.put("subtaskDescription", subtaskDescription != null ? subtaskDescription : "No Description"); // Handle subtask description
	                
	                taskDetails.put("hasSubtasks", subtask != null && !subtask.isEmpty() ? "true" : "false");

	             // Add assign date field
	                taskDetails.put("assignDate", result[7] != null ? result[7].toString() : "No Date");

	                taskDetailsList.add(taskDetails);
	            }
	            return taskDetailsList;
	        } catch (Exception e) {
	            System.err.println("Failed to fetch project details: " + e.getMessage());
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }



	/*    @Override
	    @Transactional
	    public void updateTaskStatus(String projectId, String email, String taskName, String status) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_");
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to update task status
	            String sql = "UPDATE `" + dynamicTableName + "` SET task_status = :status " +
	                         "WHERE email_id = :email AND project_id = :projectId AND task_name = :taskName";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("status", status);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);
	            query.setParameter("taskName", taskName);

	            // Log parameters and execute update query
	            System.out.println("Executing query with parameters: ");
	            System.out.println("Status: " + status);
	            System.out.println("Email: " + email);
	            System.out.println("Project ID: " + projectId);
	            System.out.println("Task Name: " + taskName);
	            
	            int rowsUpdated = query.executeUpdate();
	            System.out.println("Number of rows updated: " + rowsUpdated);

	        } catch (Exception e) {
	            System.err.println("Failed to update task status: " + e.getMessage());
	            e.printStackTrace(); // Print stack trace for detailed error info
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }*/
	    
	    @Override
	    @Transactional
	    public void updateTaskStatus(String projectId, String email, String taskName, String subtaskName, String status) {
	        try {
	            // Fetch dynamic table name based on projectId
	            Optional<TeamProject> projectOptional = teamProjectRepository.findByProject_id(projectId);
	            if (!projectOptional.isPresent()) {
	                throw new IllegalArgumentException("Invalid project ID");
	            }
	            TeamProject project = projectOptional.get();

	            // Construct dynamic table name, sanitizing project name
	            String dynamicTableName = project.getProject_name().replace(" ", "_");
	            System.out.println("Dynamic table name: " + dynamicTableName);

	            // Construct and execute SQL query to update task status, including subtask
	            String sql = "UPDATE `" + dynamicTableName + "` SET task_status = :status " +
	                         "WHERE email_id = :email AND project_id = :projectId AND task_name = :taskName AND subtask = :subtaskName";
	            Query query = entityManager.createNativeQuery(sql);
	            query.setParameter("status", status);
	            query.setParameter("email", email);
	            query.setParameter("projectId", projectId);
	            query.setParameter("taskName", taskName);
	            query.setParameter("subtaskName", subtaskName); // Include subtask name in the query

	            // Log parameters and execute update query
	            System.out.println("Executing query with parameters: ");
	            System.out.println("Status: " + status);
	            System.out.println("Email: " + email);
	            System.out.println("Project ID: " + projectId);
	            System.out.println("Task Name: " + taskName);
	            System.out.println("Subtask Name: " + subtaskName);
	            
	            int rowsUpdated = query.executeUpdate();
	            System.out.println("Number of rows updated: " + rowsUpdated);

	        } catch (Exception e) {
	            System.err.println("Failed to update task status: " + e.getMessage());
	            e.printStackTrace(); // Print stack trace for detailed error info
	            throw e; // Rethrow or handle the exception as needed
	        }
	    }


	    
	    //For Graph
	    
	  /*  @Override
	    public List<EmployeeProjectProgressEntity> findByProjectIdAndTableName(String projectId, String tableName, String email) {
	            
	        if (tableName == null || tableName.trim().isEmpty()) {
	            throw new IllegalArgumentException("Table name cannot be null or empty");
	        }

	        // Construct SQL query using tableName directly
	        String sql = "SELECT e.f_name AS empname, s.email_id AS email, s.task_name, "
	                + "COUNT(s.subtask) AS total_subtasks, "
	                + "COALESCE(SUM(CASE WHEN tp.task_status = 'completed' THEN 1 ELSE 0 END), 0) AS total_completed_subtasks "
	                + "FROM " + tableName + " s "
	                + "LEFT JOIN team_progress tp ON s.task_name = tp.task_name "
	                + "AND s.subtask = tp.subtask AND tp.project_id = :projectId AND tp.task_status = 'completed' "
	                + "INNER JOIN employee_details e ON e.email = s.email_id "
	                + "WHERE s.project_id = :projectId "
	                + "GROUP BY s.email_id, s.task_name, empname";

	        // Create the native query
	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("projectId", projectId);

	        try {
	            return query.getResultList();
	        } catch (Exception e) {
	            throw e;  // Re-throw the exception or handle it as needed
	        } 
	    }*/

	    
	    @Override
	    public List<EmployeeProjectProgressEntity> findByProjectIdAndTableName(String projectId, String tableName, String email) {
	        if (tableName == null || tableName.trim().isEmpty()) {
	            throw new IllegalArgumentException("Table name cannot be null or empty");
	        }

	        // Updated SQL query to filter by email
	      /*  String sql = "SELECT e.f_name AS empname, s.email_id AS email, s.task_name, "
	                + "COUNT(s.subtask) AS total_subtasks, "
	                + "COALESCE(SUM(CASE WHEN tp.task_status = 'completed' THEN 1 ELSE 0 END), 0) AS total_completed_subtasks "
	                + "FROM " + tableName + " s "
	                + "LEFT JOIN team_progress tp ON s.task_name = tp.task_name "
	                + "AND s.subtask = tp.subtask AND tp.project_id = :projectId AND tp.task_status = 'completed' "
	                + "INNER JOIN employee_details e ON e.email = s.email_id "
	                + "WHERE s.project_id = :projectId AND s.email_id = :email "  // Filter by email
	                + "GROUP BY s.email_id, s.task_name, empname";*/
	     
	        // Updated SQL query with distinct counting for overdue subtasks
	        String sql = "SELECT e.f_name AS empname, s.email_id AS email, s.task_name, "
	                + "COUNT(DISTINCT s.subtask) AS total_subtasks, " // Ensure distinct subtasks
	                + "COALESCE(SUM(CASE WHEN tp.task_status = 'completed' THEN 1 ELSE 0 END), 0) AS total_completed_subtasks, "
	                + "COALESCE(COUNT(DISTINCT CASE WHEN tp.overdue_flag = 1 THEN s.subtask END), 0) AS total_overdue_subtasks " // Handle distinct overdue subtasks
	                + "FROM " + tableName + " s "
	                + "LEFT JOIN team_progress tp ON s.task_name = tp.task_name "
	                + "AND s.subtask = tp.subtask AND tp.project_id = :projectId "
	                + "INNER JOIN employee_details e ON e.email = s.email_id "
	                + "WHERE s.project_id = :projectId AND s.email_id = :email "
	                + "GROUP BY s.email_id, s.task_name, e.f_name";  
	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("projectId", projectId);
	        query.setParameter("email", email);  // Use email instead of empId

	        try {
	            return query.getResultList();
	        } catch (Exception e) {
	            throw e;  // Re-throw or handle the exception as needed
	        }
	    }


}


	

