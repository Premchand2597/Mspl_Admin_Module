package com.example.mspl_connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.PermissionsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionsEntity, Integer> {
    
	@Query(value="SELECT * FROM permissions where emp_id=:userId",nativeQuery = true)
	Optional<PermissionsEntity> findByUserId(String userId);
    
   @Modifying
   @Transactional
   @Query(nativeQuery = true,value="update permissions set doc_upload=false where emp_id=:empId")
   int updateDocUploadPermissionFlag(String empId);   
   
   @Query(nativeQuery = true, value="select emp_id from permissions where emp_id=:userId")
   String findByUserId_Custom(String userId);
   
   @Modifying
   @Transactional
   @Query(nativeQuery = true, value="insert into permissions(emp_id, interview_access, other_permission1, sales, store, accounts, apprisal_link, attendance, doc_upload, doc_date, crm) values(:empId, false, false, false, false, false, false, false, false, null, false)")
   void insertPermissionAccessForNewEmployee(@Param("empId") String empId);
   
}

