package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mspl_connect.Sales_Entity.PurchaseOrderItem;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long>{
	
	@Query(nativeQuery = true, value = "select quantity from purchase_order_items where purchase_order_id = :salesIndexId and p_id=:p_Id  and part_no = :partNo")
	Integer getQuantityBySaleId(Integer salesIndexId,String p_Id,String partNo);

}
