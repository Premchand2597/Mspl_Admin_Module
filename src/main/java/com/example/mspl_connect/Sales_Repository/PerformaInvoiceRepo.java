package com.example.mspl_connect.Sales_Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.PerformaInvoice;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoice;

@Repository
public interface PerformaInvoiceRepo extends JpaRepository<PerformaInvoice, Long>{
	
	 Optional<PerformaInvoice> findTopByOrderByIdDesc();

	 @Query(value="select sum(quantity) from performa_invoice_item where sale_order_id = :salesOrderId and p_id = :p_Id", nativeQuery = true)
	 Integer getQuantityByPIdAndSalesOrderID(String salesOrderId, String p_Id);
	 
	 List<PerformaInvoice> findAllByOrderByIdDesc();
	 
	 Optional<PerformaInvoice> findByInvoiceId(String invoiceId);

}
