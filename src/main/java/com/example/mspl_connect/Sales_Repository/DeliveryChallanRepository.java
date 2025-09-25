package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.DeliveryChallan;


@Repository
public interface DeliveryChallanRepository extends JpaRepository<DeliveryChallan, Long> {

//	@Query(value = "SELECT COALESCE(MAX(CAST(delivery_note_no AS SIGNED)), 0) FROM delivery_challan_new", nativeQuery = true)
//	int findMaxDeliveryNoteNo();
	
	@Query(value = "SELECT COALESCE(MAX(CAST(delivery_note_no AS numeric)), 0) FROM delivery_challan_new;", nativeQuery = true)
	int findMaxDeliveryNoteNo();	
}
