package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssetReplace;




@Repository
public interface AssetReplaceRepository extends JpaRepository<AssetReplace, Integer>{

	// Check if a replacement request already exists for the same assigned + ref asset
    boolean existsByAssignedAssetIdAndRefAssetId(String assignedAssetId, String refAssetId);
    
    List<AssetReplace> findBySenderEmpId(String empId);

 // Repository
    Optional<AssetReplace> findByOldAssetId(Integer oldAssetId);



    /*@Query("SELECT COUNT(a) FROM AssetReplace a WHERE a.senderEmpId = :empId AND a.notification = true")
    Integer countPendingAssetReplaces(@Param("empId") String empId);*/
    
   /* @Query("SELECT COUNT(a) " +
    	       "FROM AssetReplace a " +
    	       "JOIN a.employeeDetails ed " +
    	       "WHERE ed.empId = :empId AND a.notification = true")
    	Integer countPendingAssetReplaces(@Param("empId") String empId);*/
    
    @Query(value = "SELECT COUNT(ar.id) " +
            "FROM asset_replace ar " +
            "WHERE ar.notification = true",
    nativeQuery = true)
Integer countPendingAssetReplaces();



}
