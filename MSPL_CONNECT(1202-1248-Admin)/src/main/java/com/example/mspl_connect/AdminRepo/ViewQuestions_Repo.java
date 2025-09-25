package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;


@Repository
public interface ViewQuestions_Repo extends JpaRepository<AddQuestions_Entity, String>{

	@Procedure(name="fetch_all_apptitude_questions")
	@Query(nativeQuery = true, value="call fetch_all_apptitude_questions()")
	List<AddQuestions_Entity> fetch_all_apptitude_questions();
	
	@Procedure(name="fetch_all_c_questions")
	@Query(nativeQuery = true, value="call fetch_all_c_questions()")
	List<AddQuestions_Entity> fetch_all_c_questions();
	
	@Procedure(name="fetch_all_java_questions")
	@Query(nativeQuery = true, value="call fetch_all_java_questions()")
	List<AddQuestions_Entity> fetch_all_java_questions();
	
	@Procedure(name="fetch_all_python_questions")
	@Query(nativeQuery = true, value="call fetch_all_python_questions()")
	List<AddQuestions_Entity> fetch_all_python_questions();
	
	@Procedure(name="fetch_all_sql_questions")
	@Query(nativeQuery = true, value="call fetch_all_sql_questions()")
	List<AddQuestions_Entity> fetch_all_sql_questions();
}
