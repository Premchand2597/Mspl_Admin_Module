package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface EditQuestions_Repo extends JpaRepository<AddQuestions_Entity, String>{
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update apttitude_question set questions=:questions, op1=:op1, op2=:op2, op3=:op3, op4=:op4, answers=:answers where id=:id")
	void update_apptitude_questions(@Param("id") int id, @Param("questions") String questions, @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4, @Param("answers") String answers);
	
	 @Modifying
	 	@Transactional
	@Query(nativeQuery = true, value="update c_questions set questions=:questions, op1=:op1, op2=:op2, op3=:op3, op4=:op4, answers=:answers where id=:id")
	void update_c_questions(@Param("id") int id, @Param("questions") String questions, @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4, @Param("answers") String answers);
	
	 @Modifying
	    @Transactional
	@Query(nativeQuery = true, value="update java_questions set questions=:questions, op1=:op1, op2=:op2, op3=:op3, op4=:op4, answers=:answers where id=:id")
	void update_java_questions(@Param("id") int id, @Param("questions") String questions, @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4, @Param("answers") String answers);
	
	 @Modifying
	    @Transactional
	@Query(nativeQuery = true, value="update python_questions set questions=:questions, op1=:op1, op2=:op2, op3=:op3, op4=:op4, answers=:answers where id=:id")
	void update_python_questions(@Param("id") int id, @Param("questions") String questions, @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4, @Param("answers") String answers);
	
	 @Modifying
	    @Transactional
	@Query(nativeQuery = true, value="update sql_questions set questions=:questions, op1=:op1, op2=:op2, op3=:op3, op4=:op4, answers=:answers where id=:id")
	void update_sql_questions(@Param("id") int id, @Param("questions") String questions, @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4, @Param("answers") String answers);
}
