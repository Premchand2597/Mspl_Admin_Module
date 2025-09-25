package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LinkGeneratedMoatUserTableEntity;


@Repository
public interface Link_generated_table_repo extends JpaRepository<LinkGeneratedMoatUserTableEntity, Integer>{
	
	@Query(nativeQuery = true, value = "select * from link_generated_table where link_created_date=(SELECT MAX(link_created_date) FROM link_generated_table WHERE candidate_email = :candidateEmail) AND id = (SELECT MAX(id) FROM link_generated_table WHERE candidate_email = :candidateEmail)")
	LinkGeneratedMoatUserTableEntity fetchLinkGeneratedAllDataBasedOnRegisteredEmailId(@Param("candidateEmail") String candidateEmail);
	
	@Modifying
    @Query(nativeQuery = true, value = "DELETE FROM link_generated_table WHERE encrypted_link = :token")
	void deleteTokenDataBasedOnToken(@Param("token") String token);
}
