package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentRejectCount_Entity;

@Repository
public interface card3_Repo extends JpaRepository<StudentRejectCount_Entity, String> {

    @Query(nativeQuery = true, value = "select count(email) as reject_count from "
    		+ "	(select distinct s.email from student s inner join link_generated_table l on l.candidate_email = s.email where l.dept_name = :dept and s.action='reject' union select moc.candidate_email from moat_candidate_mode_of_contact moc where moc.dept=:dept and moc.remarks='Rejected') as total")
    List<StudentRejectCount_Entity> fetch_reject_count(String dept);
    
    @Procedure(name = "fetch_reject_count")
    @Query(nativeQuery = true, value = "call fetch_reject_count()")
    public List<StudentRejectCount_Entity> fetch_reject_count();

    
}

