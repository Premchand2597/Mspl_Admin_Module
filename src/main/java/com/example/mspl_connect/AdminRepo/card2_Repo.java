package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentApproveCount_Entity;


@Repository
public interface card2_Repo extends JpaRepository<StudentApproveCount_Entity, String> {

    @Query(nativeQuery = true, value = "select count(email) as approve_count from "
    		+ "	(select distinct s.email from student s inner join link_generated_table l on l.candidate_email = s.email where l.dept_name = :dept and s.action='approv' union select moc.candidate_email from moat_candidate_mode_of_contact moc where moc.dept=:dept and moc.remarks='Selected') as total")
    public List<StudentApproveCount_Entity> fetch_approve_count(String dept);
    
//    @Procedure(name = "fetch_approve_count")
//    @Query(nativeQuery = true, value = "call fetch_approve_count()")
//    public List<StudentApproveCount_Entity> fetch_approve_count();
    
    @Query(value = "SELECT * FROM fetch_approve_count()", nativeQuery = true)
    public List<StudentApproveCount_Entity> fetch_approve_count();

}

