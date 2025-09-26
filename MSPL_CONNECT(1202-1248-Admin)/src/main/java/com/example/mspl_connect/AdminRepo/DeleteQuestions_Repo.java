package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;

import jakarta.transaction.Transactional;

public interface DeleteQuestions_Repo extends JpaRepository<AddQuestions_Entity, String>{
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM apttitude_question WHERE id=:id")
	void delete_apptitude_questions(@Param("id") int id);

	 @Modifying
	 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM c_questions WHERE id=:id")
	void delete_c_questions(@Param("id") int id);
	
	 @Modifying
	 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM java_questions WHERE id=:id")
	void delete_java_questions(@Param("id") int id);	
	 
	 @Modifying
	 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM python_questions WHERE id=:id")
	void delete_python_questions(@Param("id") int id);	
	 
	 @Modifying
	 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM sql_questions WHERE id=:id")
	void delete_sql_questions(@Param("id") int id);
	 
}
