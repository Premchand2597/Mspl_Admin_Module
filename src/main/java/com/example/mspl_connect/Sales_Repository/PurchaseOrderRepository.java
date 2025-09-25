package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.Sales_Entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
	
	@Query(value = "SELECT * FROM purchase_order ORDER BY po_id DESC LIMIT 1", nativeQuery = true)
	PurchaseOrder findLastPoId();

	@Query("SELECT p.purchaseId FROM PurchaseOrder p WHERE p.purchaseId LIKE %:financialYear% ORDER BY p.purchaseId DESC LIMIT 1")
	String findLastPoId(@Param("financialYear") String financialYear);
	
	@Query("SELECT COUNT(p) FROM PurchaseOrder p WHERE p.quotationId = :quotationId")
	int countByQuotationId(@Param("quotationId") String quotationId);

	
}
