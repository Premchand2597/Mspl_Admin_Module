package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Assetes;
import com.example.mspl_connect.AdminEntity.assetsDTO;

@Repository
public interface AssetRepository extends JpaRepository<Assetes, Integer>{

	 @Query("SELECT distinct a.asset_name FROM Assetes a where active_inactive_status = 1")
	 List<String> findAllAssetNames();
	 
	  @Query(value = "SELECT * FROM assets WHERE asset_id = :asset_id", nativeQuery = true)
	    Optional<Assetes> findByAsset_id(@Param("asset_id") String asset_id);
	  
	// New method: find by asset_id and asset_type
	    @Query(value = "SELECT * FROM assets WHERE asset_id = :asset_id AND asset_name = :assetType", nativeQuery = true)
	    Optional<Assetes> findByAssetIdAndType(
	            @Param("asset_id") String asset_id,
	            @Param("assetType") String assetType
	    );
//	 @Query("SELECT DISTINCT a.description FROM Assetes a WHERE a.asset_name = :assetType and active_inactive_status = 1 and quantity > 0")
//	 List<String> findDescriptionsByAssetType(@Param("assetType") String assetType);
	 
	 @Query(value="SELECT DISTINCT a.description FROM assets a WHERE a.asset_name = :assetType and active_inactive_status = 1 and quantity::int > 0", nativeQuery = true)
	 List<String> findDescriptionsByAssetType(@Param("assetType") String assetType);
	 
	 @Query("SELECT a FROM Assetes a WHERE a.asset_name = :assetName AND a.description = :description")
	 Optional<Assetes> findByAssetNameAndDescription(@Param("assetName") String assetName, @Param("description") String description);
	 
	 @Query(value="SELECT id FROM assets ORDER BY id DESC LIMIT 1", nativeQuery = true)
	 Optional<Integer> getLAtestId();
	 
	 @Query("SELECT COUNT(a) > 0 FROM Assetes a WHERE a.asset_name = :assetName AND a.description = :description")
	 boolean existsByAssetNameAndDescription(@Param("assetName") String assetName, @Param("description") String description);


	//Optional<Assetes> findByAssetNameAndDescription(String assetType, String description);
	 
	//Optional<Assetes> findByAssetNameAndDescription(String assetName, String description);

}
