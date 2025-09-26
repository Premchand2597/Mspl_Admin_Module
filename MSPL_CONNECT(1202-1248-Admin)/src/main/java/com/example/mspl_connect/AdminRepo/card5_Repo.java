package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.StudentOnHoldCount_Entity;


@Repository
public interface card5_Repo extends JpaRepository<StudentOnHoldCount_Entity, String> {

    @Query(nativeQuery = true, value = " select count(email) as onhold_count from  "
    		+ "	(select distinct s.email from student s inner join link_generated_table l on l.candidate_email = s.email where l.dept_name = :dept and s.action='onhold' union select moc.candidate_email from moat_candidate_mode_of_contact moc where moc.dept=:dept and moc.remarks='onhold') as total")
    public List<StudentOnHoldCount_Entity> fetch_onHold_count(String dept);
    
    @Procedure(name = "fetch_onHold_count")
    @Query(nativeQuery = true, value = "call fetch_onHold_count()")
    public List<StudentOnHoldCount_Entity> fetch_onHold_count();

}

