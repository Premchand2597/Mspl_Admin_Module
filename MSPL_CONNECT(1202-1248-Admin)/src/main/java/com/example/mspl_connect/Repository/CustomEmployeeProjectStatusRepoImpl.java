package com.example.mspl_connect.Repository;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;

import ch.qos.logback.classic.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomEmployeeProjectStatusRepoImpl implements CustomEmployeeProjectStatusRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EmployeeProjectProgressEntity> findByProjectIdAndTableName(String projectId, String tableName) {
    	
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
    }
}