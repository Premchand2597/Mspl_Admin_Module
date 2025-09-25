package com.example.mspl_connect.DispatchRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




/*@Repository
public interface DeliveryChallanRepository extends JpaRepository<DeliveryChallan, Long> {

	@Query(value = "SELECT COALESCE(MAX(delivery_note_no::integer), 0) FROM delivery_challan_new", nativeQuery = true)
	int findMaxDeliveryNoteNo();
	
}*/
