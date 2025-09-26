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





}
