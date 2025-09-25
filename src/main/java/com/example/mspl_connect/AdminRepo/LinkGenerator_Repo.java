package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LinkGeneratedTableEntity;

import jakarta.transaction.Transactional;

@Repository
public interface LinkGenerator_Repo extends JpaRepository<LinkGeneratedTableEntity, Integer>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="insert into link_generated_table(encrypted_link, supervisor_name, candidate_email, test_duration, test_type, encrypted_data, test_valid_flag, link_created_date, dept_name) values(:url, :supervisor_name, :email, :test_duration, :test_type, :encrypted_data, 1, cast(:linkCreatedDate as date), :dept_name)")
	void insertDetailsToLinkGenerateTable(@Param("url") String url, @Param("email") String email, @Param("test_duration") String test_duration, 
			@Param("supervisor_name") String supervisor_name, @Param("test_type") String test_type, @Param("encrypted_data") String encrypted_data, 
			@Param("dept_name") String dept_name, @Param("linkCreatedDate") String linkCreatedDate);

	@Query(nativeQuery = true, value = "Select * from link_generated_table order by id desc")
	List<LinkGeneratedTableEntity> fetchAllLinkGeneratedData();

}
