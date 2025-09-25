package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssignedAssetDetailsDTO;
import com.example.mspl_connect.AdminEntity.AssignedAssets;

@Repository
public interface AssignedAssetsRepo extends JpaRepository<AssignedAssets, Integer> {

	 @Query(value="SELECT id FROM assigned_assets ORDER BY id DESC LIMIT 1", nativeQuery = true)
	 Optional<Integer> getLatestAssignedId();
	 

	 
	 @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssignedAssets a WHERE a.ref_asset_id = :refAssetId")
	    boolean existsByRefAssetId(@Param("refAssetId") String refAssetId);

	 @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
		       "FROM AssignedAssets a " +
		       "WHERE a.ref_asset_id = :refAssetId " +
		       "AND a.asset_type = :assetType " +
		       "AND a.description = :description")
		boolean existsByRefAssetIdAndAssetTypeAndDescription(
		        @Param("refAssetId") String refAssetId,
		        @Param("assetType") String assetType,
		        @Param("description") String description);

	 
	 @Query(value="select count(quantity),asset_id from assigned_assets group by asset_id",nativeQuery = true)
	 List<Object[]> getAssetCountGroupedByAssetId();
	 
	 @Query(value = "SELECT * FROM assigned_assets WHERE assigned_asset_id = :assignedAssetId AND assigned_to = :assignedTo AND asset_type = :assetType AND description = :description AND asset_id = :assetId AND ref_asset_id = :refAssetId", 
		       nativeQuery = true)
		Optional<AssignedAssets> findByAssignedAssetIdAndAssignedToAndAssetTypeAndDescriptionAndAssetIdAndRefAssetId(
		        @Param("assignedAssetId") String assignedAssetId,
		        @Param("assignedTo") String assignedTo,
		        @Param("assetType") String assetType,
		        @Param("description") String description,
		        @Param("assetId") String assetId,
		        @Param("refAssetId") String refAssetId);


}
