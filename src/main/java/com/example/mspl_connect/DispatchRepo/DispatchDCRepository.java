package com.example.mspl_connect.DispatchRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.DispatchEntity.Dispatch_DC;

import jakarta.transaction.Transactional;

public interface DispatchDCRepository extends JpaRepository<Dispatch_DC, Long> {
	 
	 @Query("SELECT DISTINCT d.transportMode FROM Dispatch d")
	 List<String> findDistinctTransportModes();
	  
	 List<Dispatch_DC> findTop5ByOrderByIdDesc();
	 
	 List<Dispatch_DC> findAllByOrderByIdDesc();
	 
	 @Query(value="SELECT sum(quantity::int) FROM dc_dispatch", nativeQuery = true)
	 long getTotalModulesDispatchedSum();
	 
	 @Modifying
	 @Transactional
	 @Query(nativeQuery = true, value = "update dc_dispatch set returned_date=:returnedDate, returned_qty=:returnedQty where id=:auto_id")
	 int updateRetunedDetails(@Param("returnedDate") String returnedDate, @Param("returnedQty") String returnedQty, @Param("auto_id") long auto_id);
}
