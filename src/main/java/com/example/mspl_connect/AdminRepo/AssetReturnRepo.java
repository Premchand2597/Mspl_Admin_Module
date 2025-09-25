package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssetReturn;



@Repository
public interface AssetReturnRepo extends JpaRepository<AssetReturn, Integer>{
	   boolean existsByAssignedAssetIdAndStatus(String assignedAssetId, String status);
	   
	   // ✅ Check by both assignedAssetId + refAssetId + status
	    boolean existsByAssignedAssetIdAndRefAssetIdAndStatus(String assignedAssetId, String refAssetId, String status);
}
