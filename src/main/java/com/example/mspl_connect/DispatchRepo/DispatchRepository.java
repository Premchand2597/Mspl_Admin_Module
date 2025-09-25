package com.example.mspl_connect.DispatchRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.DispatchEntity.Dispatch;

import jakarta.transaction.Transactional;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
	 
	 @Query("SELECT DISTINCT d.transportMode FROM Dispatch d")
	 List<String> findDistinctTransportModes();
	  
	 List<Dispatch> findTop5ByOrderByIdDesc();
	 
	 List<Dispatch> findAllByOrderByIdDesc();
	 
	 @Query(value="SELECT COALESCE(SUM(quantity::int), 0) FROM dispatch", nativeQuery = true)
	 long getTotalModulesDispatchedSum();
	 
	 @Query(value="SELECT COALESCE(sum(returned_qty::int), 0) FROM dispatch", nativeQuery = true)
	 long getTotalModulesReturnedSum();
	 
	 @Modifying
	 @Transactional
	 @Query(nativeQuery = true, value = "update dispatch set returned_date=:returnedDate, returned_qty=:returnedQty where id=:auto_id")
	 int updateRetunedDetails(@Param("returnedDate") String returnedDate, @Param("returnedQty") String returnedQty, @Param("auto_id") long auto_id);
	 
	 @Modifying
	 @Transactional
	 @Query(nativeQuery = true, value = "update dispatch set price=:price, total_price=:total_price, description=:description where id=:id")
	 void updatePriceDetails(@Param("price") String price, @Param("total_price") String total_price, @Param("description") String description,
			 @Param("id") long id);
}
