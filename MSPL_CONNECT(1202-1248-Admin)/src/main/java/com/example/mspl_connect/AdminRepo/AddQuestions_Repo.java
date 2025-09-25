package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AddQuestions_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface AddQuestions_Repo extends JpaRepository<AddQuestions_Entity, String>{
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO apttitude_question(questions, op1, op2, op3, op4, answers) VALUES(:questions, :op1, :op2, :op3, :op4, :answers)")
	int insertApptitudeQuestions(@Param("questions") String questions, 
                                                @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                @Param("op4") String op4, @Param("answers") String answers);
	
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO c_questions(questions, op1, op2, op3, op4, answers) VALUES(:questions, :op1, :op2, :op3, :op4, :answers)")
	int insertCQuestions(@Param("questions") String questions, 
                                                @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                @Param("op4") String op4, @Param("answers") String answers);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO java_questions(questions, op1, op2, op3, op4, answers) VALUES(:questions, :op1, :op2, :op3, :op4, :answers)")
    int insertJavaQuestions(@Param("questions") String questions, 
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO python_questions(questions, op1, op2, op3, op4, answers) VALUES(:questions, :op1, :op2, :op3, :op4, :answers)")
    int insertPythonQuestions(@Param("questions") String questions, 
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO sql_questions(questions, op1, op2, op3, op4, answers) VALUES(:questions, :op1, :op2, :op3, :op4, :answers)")
    int insertSqlQuestions(@Param("questions") String questions, 
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
    
    
    
    /* To retrieve recently inserted questions auto increment id from main tables */
    
    @Query(nativeQuery = true, value="select * from apttitude_question ORDER BY id DESC limit 1")
    AddQuestions_Entity fetchRecentlyInsertedAptitudeQuestionDetails();
    
    @Query(nativeQuery = true, value="select * from c_questions ORDER BY id DESC limit 1")
    AddQuestions_Entity fetchRecentlyInsertedCQuestionDetails();
    
    @Query(nativeQuery = true, value="select * from java_questions ORDER BY id DESC limit 1")
    AddQuestions_Entity fetchRecentlyInsertedJavaQuestionDetails();
    
    @Query(nativeQuery = true, value="select * from python_questions ORDER BY id DESC limit 1")
    AddQuestions_Entity fetchRecentlyInsertedPythonQuestionDetails();
    
    @Query(nativeQuery = true, value="select * from sql_questions ORDER BY id DESC limit 1")
    AddQuestions_Entity fetchRecentlyInsertedSQLQuestionDetails();
    
    
	/* To add questions in backup tables */
    
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO apttitude_question_backup(id, questions, op1, op2, op3, op4, answers) VALUES(:id, :questions, :op1, :op2, :op3, :op4, :answers)")
	void insertApptitudeQuestionsBackup(@Param("questions") String questions, @Param("id") String id,
                                                @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                @Param("op4") String op4, @Param("answers") String answers);
	
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO c_questions_backup(id, questions, op1, op2, op3, op4, answers) VALUES(:id, :questions, :op1, :op2, :op3, :op4, :answers)")
	void insertCQuestionsBackup(@Param("questions") String questions, @Param("id") String id, 
                                                @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                @Param("op4") String op4, @Param("answers") String answers);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO java_questions_backup(id, questions, op1, op2, op3, op4, answers) VALUES(:id, :questions, :op1, :op2, :op3, :op4, :answers)")
    void insertJavaQuestionsBackup(@Param("questions") String questions, @Param("id") String id,
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO python_questions_backup(id, questions, op1, op2, op3, op4, answers) VALUES(:id, :questions, :op1, :op2, :op3, :op4, :answers)")
    void insertPythonQuestionsBackup(@Param("questions") String questions, @Param("id") String id, 
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO sql_questions_backup(id, questions, op1, op2, op3, op4, answers) VALUES(:id, :questions, :op1, :op2, :op3, :op4, :answers)")
    void insertSqlQuestionsBackup(@Param("questions") String questions, @Param("id") String id,
                                                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, 
                                                    @Param("op4") String op4, @Param("answers") String answers);
}
