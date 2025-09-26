package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentResult_Entity;

import jakarta.transaction.Transactional;


@Repository
public interface FetchStudentResult_Repo extends JpaRepository<StudentResult_Entity, Integer>{

	@Procedure(name="fetch_student_result")
	@Query(nativeQuery = true, value="call fetch_student_result(:student_id)")
	public List<StudentResult_Entity> fetch_student_result(@Param("student_id") String student_id);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update quiz_response set result = :technicalMarkValue where student_id = :student_id and question_id = :question_id")
    public int updateTechnicalMarks(@Param("student_id") String student_id, @Param("question_id") String question_id, @Param("technicalMarkValue") String technicalMarkValue);
}
