package com.example.mspl_connect.AdminRepo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentTotalCount_Entity;

@Repository
public interface card1_Repo extends JpaRepository<StudentTotalCount_Entity, String> {

    @Query(nativeQuery = true, value = " select count(email) as student_count from "
    		+ "	(select s.email from student s inner join link_generated_table l on l.candidate_email = s.email where l.dept_name = :dept union select moc.candidate_email from moat_candidate_mode_of_contact moc where moc.dept=:dept) as total")
    public List<StudentTotalCount_Entity> fetch_student_count(String dept);
    
    @Procedure(name = "fetch_student_count")
    @Query(nativeQuery = true, value = "call fetch_student_count()")
    public List<StudentTotalCount_Entity> fetch_student_count();

    
    

}

