package com.example.mspl_connect.AdminRepo;

import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AssetRequest;
import com.example.mspl_connect.AdminEntity.assetsDTO;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest, Long>{
	 
	List<AssetRequest> findAllByEmpId(String empId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE assets_request SET status = 4 WHERE id = :id", nativeQuery = true)
	int updateRejectStatusById(@Param("id") Long id);

	
}
