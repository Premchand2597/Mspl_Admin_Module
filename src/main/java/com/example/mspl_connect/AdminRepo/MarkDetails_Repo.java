package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.MarkDetails_Entity;

import jakarta.transaction.Transactional;

public interface MarkDetails_Repo extends JpaRepository<MarkDetails_Entity, String>{

//	@Procedure(name = "fetch_studentMarksDetails_ByStudentId")
//	@Query(nativeQuery = true, value="fetch_studentMarksDetails_ByStudentId(:student_id)")
//	@Transactional
//	List<MarkDetails_Entity> fetch_studentMarksDetails_ByStudentId(@Param("student_id") String student_id);
	
	@Query(nativeQuery = true, value="select * from fetch_studentMarksDetails_ByStudentId(:student_id)")
	@Transactional
	List<MarkDetails_Entity> fetch_studentMarksDetails_ByStudentId(@Param("student_id") String student_id);
}

