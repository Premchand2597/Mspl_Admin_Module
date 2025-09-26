package com.example.mspl_connect.Repository;

import java.util.List;

import com.example.mspl_connect.Entity.Task;



public interface CustomProjectRepository {
	
	 void createTable(String tableName);
	 void insertValuesIntoTable(String tableName, int prjId, String prjName, String teamMembers,String projectId,String completion_date,List<Task> tasks);
	 void extendDateInDynamicTable(String email,String tableName,long daysBetween);
	
}
