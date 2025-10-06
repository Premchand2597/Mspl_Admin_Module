package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssetReturn;



@Repository
public interface AssetReturnRepo extends JpaRepository<AssetReturn, Integer>{
	   boolean existsByAssignedAssetIdAndStatus(String assignedAssetId, String status);
	   
	   // ✅ Check by both assignedAssetId + refAssetId + status
	    boolean existsByAssignedAssetIdAndRefAssetIdAndStatus(String assignedAssetId, String refAssetId, String status);
	    
	    
	
	    @Query(value = "SELECT COUNT(ar.id) " +
	               "FROM asset_return ar " +
	               "WHERE ar.notification = true",
	       nativeQuery = true)
	Integer countPendingAssetReturns();

	    boolean existsByAssignedAssetIdAndRefAssetId(String assignedAssetId, String refAssetId);

	
		List<AssetReturn> findBySenderEmpId(String empId);
}

