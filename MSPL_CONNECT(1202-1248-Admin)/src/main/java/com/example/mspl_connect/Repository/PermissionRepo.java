package com.example.mspl_connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.PermissionsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionsEntity, Integer> {
    
	@Query(value="SELECT * FROM permissions where emp_id=:userId",nativeQuery = true)
	Optional<PermissionsEntity> findByUserId(String userId);
    
   @Modifying
   @Transactional
   @Query(nativeQuery = true,value="update permissions set doc_upload=false")
   int updateDocUploadPermissionFlag();   
   
}

