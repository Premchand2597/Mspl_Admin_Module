package com.example.mspl_connect.Sales_Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.PurchaseInvoice;

@Repository
public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long> {
	
	 Optional<PurchaseInvoice> findTopByOrderByIdDesc();
	 
	 Optional<PurchaseInvoice> findByInvoiceId(String invoiceId);
	 
	 @Modifying
	 @Query("UPDATE PurchaseInvoice i SET i.approve_by_ac = :approveByAc,i.pdfPath=:pdfPath WHERE i.invoiceId = :invoiceId")
	 int updateApprovalStatus(@Param("invoiceId") String invoiceId, @Param("approveByAc") Integer approveByAc, @Param("pdfPath") String pdfPath);

	 @Query("SELECT i.pdfPath FROM PurchaseInvoice i WHERE i.invoiceId = :invoiceId")
	 String findPdfPathByInvoiceId(@Param("invoiceId") String invoiceId);

	 @Query(nativeQuery = true,value="select pdf_path from purchase_invoice where invoice_id = :invoiceId")
	 String getPdfPathByInvoiceId(String invoiceId);

	 @Query(value="select sum(quantity) from purchase_invoice_item where sale_order_id = :salesOrderId and p_id = :p_Id", nativeQuery = true)
	 Integer getQuantityByPIdAndSalesOrderID(String salesOrderId, String p_Id);

	 List<PurchaseInvoice> findAllByOrderByIdDesc();
	  
	 
}

