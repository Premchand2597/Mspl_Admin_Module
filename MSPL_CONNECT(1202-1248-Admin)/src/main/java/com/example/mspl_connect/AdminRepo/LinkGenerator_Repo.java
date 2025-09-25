package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LinkGeneratedTableEntity;

@Repository
public interface LinkGenerator_Repo extends JpaRepository<LinkGeneratedTableEntity, Integer>{

	@Query(nativeQuery = true, value = "Select * from link_generated_table")
	List<LinkGeneratedTableEntity> fetchAllLinkGeneratedData();

}
