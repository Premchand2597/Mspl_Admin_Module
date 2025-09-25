package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.QuizResponse_Entity;

public interface QuizResponse_Repo extends JpaRepository<QuizResponse_Entity, Long>{

	@Query(nativeQuery = true, value="Select * from quiz_response where student_id=:student_id and question_id=:question_id")
	public QuizResponse_Entity fetch_UpdatedTechnicalMarkValue(@Param("student_id") String student_id, @Param("question_id") String question_id);
}
