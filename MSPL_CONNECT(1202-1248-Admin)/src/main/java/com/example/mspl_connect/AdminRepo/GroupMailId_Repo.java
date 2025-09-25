package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.GroupMailId_Entity;
	
@Repository
public interface GroupMailId_Repo extends JpaRepository<GroupMailId_Entity, Long>{
	
	@Query(nativeQuery = true, value = "Select * from group_mail_id_table")
	List<GroupMailId_Entity> getGroupMailIdData();

}
