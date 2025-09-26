package com.example.mspl_connect.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Controller.TableAlreadyExistsException;
import com.example.mspl_connect.Entity.Task;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomProjectRepositoryImpl implements CustomProjectRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTable(String tableName) throws TableAlreadyExistsException {
        if (tableExists(tableName)) {
            throw new TableAlreadyExistsException("Table " + tableName + " already exists");
        }

        String sql = "CREATE TABLE " + tableName + "("
                   + "id INT AUTO_INCREMENT PRIMARY KEY, "
        		   + "project_id VARCHAR(255),"
                   + "email_id VARCHAR(255),"
                   + "prj_name VARCHAR(255), "    
                   + "completion_date VARCHAR(150),"                   
                   + "notifyflag INT NULL DEFAULT '1',"
                   + "acceptanceDate VARCHAR(150),"
                   + "task_name VARCHAR(150),"
                   + "task_description VARCHAR(500),"
                   + "task_duedate VARCHAR(150),"
                   + "task_weight VARCHAR(150),"
                   + "task_status VARCHAR(150),"
                   + "subtask VARCHAR(150),"
                   + "subtask_description VARCHAR(5000),"
                   +"assign_date VARCHAR(150),"
                   + "status_flag INT"
                   + ")";
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Override
    public void insertValuesIntoTable(String tableName, int prjId, String prjName, String teamMembers, String projectId, String completionDate, List<Task> tasks) {
        //System.out.println(",.,.,.,..,..," + teamMembers);
        String[] teamMembersArray = teamMembers.split(",");
        tasks.stream().forEach(task -> System.out.println("---in customeProjectRepo"+task));
        int taskCounter = 1; //Initialize a task counter for sequential numbering
       
            for (Task task : tasks) {
                String sql = "INSERT INTO " + tableName + " (project_id,email_id, prj_name,completion_date, task_name, task_description, task_duedate, task_weight,subtask,subtask_description,status_flag,assign_date) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                entityManager.createNativeQuery(sql)
                    .setParameter(1, task.getProjectId())
                    .setParameter(2, task.getEmp_mail())
                    .setParameter(3, tableName)
                    .setParameter(4, task.getCompletionDate())
                    .setParameter(5, task.getTaskName())
                    .setParameter(6, task.getMainTaskDescription())
                    .setParameter(7, task.getTaskCompletionDate())
                    .setParameter(8, task.getTaskWeight()) 
                    .setParameter(9, task.getSubTaskName()) 
                    .setParameter(10,task.getTaskDescription())	 
                    .setParameter(11, 1)
                    .setParameter(12, task.getAssign_date())
                    .executeUpdate();
            }
            for (Task task : tasks) {
                String sql = "INSERT INTO all_project_data (project_id,email_id, prj_name,completion_date, task_name, task_description, task_duedate, task_weight,subtask,subtask_description,status_flag,assign_date) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                entityManager.createNativeQuery(sql)
                    .setParameter(1, task.getProjectId())
                    .setParameter(2, task.getEmp_mail())
                    .setParameter(3, tableName)
                    .setParameter(4, task.getCompletionDate())
                    .setParameter(5, task.getTaskName())
                    .setParameter(6, task.getMainTaskDescription())
                    .setParameter(7, task.getTaskCompletionDate())
                    .setParameter(8, task.getTaskWeight()) 
                    .setParameter(9, task.getSubTaskName()) 
                    .setParameter(10, task.getTaskDescription())
                    .setParameter(11, 1)
                    .setParameter(12, task.getAssign_date())
                    .executeUpdate();
            }
        }

    public boolean tableExists(String tableName) {
    	
        String sql = "SHOW TABLES LIKE :tableName";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("tableName", tableName);
        
        return !query.getResultList().isEmpty();
    }
   
    
    @Override
    @Transactional // Ensure the update happens within a transaction
    public void extendDateInDynamicTable(String email, String tableName, long daysBetween) {
        // Construct the SQL query dynamically using the provided tableName
        String sql = "UPDATE " + tableName + 
                     " SET completion_date = DATE_FORMAT(DATE_ADD(STR_TO_DATE(completion_date, '%Y-%m-%d'), INTERVAL :daysBetween DAY), '%Y-%m-%d') " + 
                     " WHERE email_id = :email AND status_flag = 1";
        
        // Execute the query with parameters
        entityManager.createNativeQuery(sql)
                     .setParameter("email", email)
                     .setParameter("daysBetween", daysBetween)
                     .executeUpdate(); // This will execute the update
    }
}