package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssetUpdateLog;


@Repository
public interface AssetLogRepository extends JpaRepository<AssetUpdateLog, Integer> {
	
    //List<AssetUpdateLog> findByAssetIdOrderByUpdatedAtDesc(String assetId);  // Fixed Method
    
	@Query(nativeQuery = true, value="SELECT al.*,concat(ed1.f_name,' ',ed1.l_name) AS modified_by_name,concat(ed2.f_name,' ',ed2.l_name) AS assigned_to_name FROM asset_update_log al INNER JOIN employee_details ed1 ON al.modified_by = ed1.empid LEFT JOIN employee_details ed2 ON al.assigned_to = ed2.empid WHERE al.asset_id = :assetId")
    List<AssetUpdateLog> findByAssetIdOrderByUpdatedAtDesc(String assetId);
    
}