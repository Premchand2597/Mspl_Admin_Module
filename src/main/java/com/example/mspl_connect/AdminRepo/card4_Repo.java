package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentPendingCount_Entity;


@Repository
public interface card4_Repo extends JpaRepository<StudentPendingCount_Entity, String> {

    @Query(nativeQuery = true, value = "select count(email) as pending_count from "
    		+ "	(select distinct s.email from student s inner join link_generated_table l on l.candidate_email = s.email where l.dept_name = :dept and (s.action IS NULL OR s.action = '') union select moc.candidate_email from moat_candidate_mode_of_contact moc where moc.dept=:dept and  moc.remarks not in ('Selected' ,  'Rejected' , 'onhold')) as total")
    List<StudentPendingCount_Entity> fetch_pending_count(String dept);
    
//    @Procedure(name = "fetch_pending_count")
//    @Query(nativeQuery = true, value = "call fetch_pending_count()")
//    public List<StudentPendingCount_Entity> fetch_pending_count();
    
    @Query(nativeQuery = true, value="SELECT * FROM fetch_pending_count();")
    public List<StudentPendingCount_Entity> fetch_pending_count();
    
}

