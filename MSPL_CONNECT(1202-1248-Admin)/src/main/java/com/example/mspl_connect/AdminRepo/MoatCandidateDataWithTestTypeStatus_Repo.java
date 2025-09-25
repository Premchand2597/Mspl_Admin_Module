package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatCandidateDataWithTestTypeStatus_Entity;


@Repository
public interface MoatCandidateDataWithTestTypeStatus_Repo extends JpaRepository<MoatCandidateDataWithTestTypeStatus_Entity, String>{
	@Query(nativeQuery = true, value = "select student.*,link_generated_table.test_type from student inner join link_generated_table on student.email = link_generated_table.candidate_email where student.id=:student_id")
	MoatCandidateDataWithTestTypeStatus_Entity fetchCandidateDataWithTestTypeStatus(@Param("student_id") String student_id);
}
