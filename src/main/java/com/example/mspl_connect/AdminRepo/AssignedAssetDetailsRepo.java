package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.mspl_connect.AdminEntity.AssignedAssetDetailsDTO;

@Repository
public interface AssignedAssetDetailsRepo extends JpaRepository<AssignedAssetDetailsDTO, Integer>{
	
	@Query(nativeQuery = true,value="select a.id,a.assigned_asset_id,a.assigned_to,a.asset_type,a.quantity,a.asset_id,a.remarks, concat(e.f_name,' ',e.l_name) as empname,a.ref_asset_id from assigned_assets a inner join employee_details e on e.empid = a.assigned_to where a.asset_id = :assetId")
	List<AssignedAssetDetailsDTO> getAssignedAssetDetailsByAssetId(String assetId);
	
	@Query(nativeQuery = true,value="select a.id,a.assigned_asset_id,a.assigned_to,a.asset_type,a.quantity,a.asset_id,a.remarks, concat(e.f_name,' ',e.l_name) as empname,a.ref_asset_id from assigned_assets a inner join employee_details e on e.empid = a.assigned_to where assigned_to = :empid")
	List<AssignedAssetDetailsDTO> getAssetByEmpId(String empid);
	
}
