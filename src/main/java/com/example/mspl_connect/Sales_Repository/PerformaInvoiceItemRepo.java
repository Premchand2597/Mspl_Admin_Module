package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.PerformaInvoiceItem;
import com.example.mspl_connect.Sales_Entity.PurchaseOrderItem;

@Repository
public interface PerformaInvoiceItemRepo  extends JpaRepository<PerformaInvoiceItem, Long>{

	@Query(nativeQuery = true, value = "select quantity from performa_invoice_item where purchase_order_id = :salesIndexId and p_id=:p_Id  and part_no = :partNo")
	Integer getQuantityBySaleId(Integer salesIndexId,String p_Id,String partNo);
	
}
