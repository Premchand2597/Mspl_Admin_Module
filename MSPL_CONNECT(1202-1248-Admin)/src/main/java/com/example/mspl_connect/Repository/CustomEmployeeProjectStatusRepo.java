package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EmployeeProjectProgressEntity;


public interface CustomEmployeeProjectStatusRepo {
    List<EmployeeProjectProgressEntity> findByProjectIdAndTableName(String projectId, String tableName);    
}