package com.example.mspl_connect.Sales_Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.DeliveryChallanDetail;

@Repository
public interface DeliveryChallanDetailRepository extends JpaRepository<DeliveryChallanDetail, Long>  {

	 List<DeliveryChallanDetail> findByDeliveryChallanId(Long deliveryChallanId);
	 
	 
	
}
