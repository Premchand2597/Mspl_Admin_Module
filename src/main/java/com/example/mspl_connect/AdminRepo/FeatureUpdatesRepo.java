package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.FeatureUpdatesEntity;

import jakarta.transaction.Transactional;

@Repository
public interface FeatureUpdatesRepo extends JpaRepository<FeatureUpdatesEntity, Integer>{
	
	@Modifying
    @Transactional
	@Query(nativeQuery = true, value = "update feature_updates set is_read=0 where empid=:empId")
	int updateAdminFlag(String empId);
	
}
