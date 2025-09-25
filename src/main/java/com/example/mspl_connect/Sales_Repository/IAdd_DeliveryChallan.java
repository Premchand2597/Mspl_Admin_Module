package com.example.mspl_connect.Sales_Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.Add_DC_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Quotation_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface IAdd_DeliveryChallan extends JpaRepository<Add_DC_Entity, Long> {
	
//	 	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid NOT LIKE '%\\_%' ORDER BY q.qid DESC LIMIT 1", nativeQuery = true)
//	    String findLastQid(); 
	
	@Query(value="SELECT q.qid FROM delivery_challan_products q WHERE q.qid NOT LIKE '%\\_%' OR q.qid LIKE '%\\_%' ORDER BY CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(q.qid, '/', -1), '_', 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
	String findLastQidForDC();


	 	@Modifying
	 	@Transactional
	 	@Query(value = "CALL add_dc_user_details(:p_qid, :p_dateTime, :p_customer_name, :p_customer_legal_name, :p_gst_number, :p_order_method, :p_contact_person_name, :p_designation, :p_mobile_number, :p_email_id, :p_delivery_address, :p_shipment_address, :p_tax_type, :honorifics1, :honorifics2, :quotationSentBy)", nativeQuery = true)
	    void saveDCDetails(
		    @Param("p_qid") String qid,
		    @Param("p_dateTime") String dateTime,
	        @Param("p_customer_name") String customerName,
	        @Param("p_customer_legal_name") String customerLegalName,
	        @Param("p_gst_number") String gstNumber,
	        @Param("p_order_method") String orderMethod,
	        @Param("p_contact_person_name") String contactPersonName,
	        @Param("p_designation") String designation,
	        @Param("p_mobile_number") String mobileNumber,
	        @Param("p_email_id") String emailId,	       
	        @Param("p_delivery_address") String deliveryAddress,	        
	        @Param("p_shipment_address") String shipmentAddress,
	        @Param("p_tax_type") String taxType,
	        @Param("honorifics1") String honorifics1,
	        @Param("honorifics2") String honorifics2,
	        @Param("quotationSentBy") String quotationSentBy
	    );
	 
    // Call stored procedure for saving quotation product details
	@Modifying
	@Transactional
    @Query(value = "CALL add_dc_product_details(:p_id, :p_product_name, :p_hsn_code, :p_part_number, :p_description,  :p_quantity, :p_price, :p_discount, :p_total_price, :p_cgst, :p_sgst, :p_igst, :p_total_with_tax, :qid)", nativeQuery = true)
    void saveDCProduct(
        @Param("p_id") int p_id,
        @Param("p_product_name") String p_product_name,        
        @Param("p_hsn_code") String p_hsn_code,
        @Param("p_part_number") String p_part_number,
        @Param("p_description") String p_description,        
        @Param("p_quantity") int p_quantity,
        @Param("p_price") String p_price,
        @Param("p_discount") int p_discount,
        @Param("p_total_price") String total_price,
        @Param("p_cgst") String p_cgst,
        @Param("p_sgst") String p_sgst,
        @Param("p_igst") String p_igst, 
        @Param("p_total_with_tax") String p_total_with_tax,        
        @Param("qid") String qid
    );
	

	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM delivery_challan nq LEFT JOIN delivery_challan_products qp ON nq.qid = qp.qid ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWithProductsNativeForDC();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM delivery_challan nq LEFT JOIN delivery_challan_products qp ON nq.qid = qp.qid where nq.review <> 0 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWithProductsNativeForReviewForDC();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM delivery_challan nq LEFT JOIN delivery_challan_products qp ON nq.qid = qp.qid where nq.review = 3 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWonDetailsForDC();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM delivery_challan nq LEFT JOIN delivery_challan_products qp ON nq.qid = qp.qid where nq.review = 4 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationLossDetailsForDC();


	@Query(value = "SELECT q.qid, q.date_time, q.customer_name, q.customer_legal_name, q.gst_number, " +
            "q.order_method, q.contact_person_name, q.designation, q.mobile_number, q.email_id, " +
            "q.shipment_address, q.delivery_address, q.tax_type, " +
            "p.product_name, p.hsn_code, p.part_number, p.description, p.quantity, p.price, " +
            "p.discount, p.total_price, p.cgst, p.sgst, p.igst, p.total_with_tax, q.review, q.email_sent_status, q.honorifics1, q.honorifics2,q.prepared_by " +
            "FROM delivery_challan q " +
            "LEFT JOIN delivery_challan_products p ON q.qid = p.qid " +
            "WHERE q.qid = :id", nativeQuery = true)
			List<Object[]> findQuotationDetailsByIdForDC(@Param("id") String id);

	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update delivery_challan set review=:review where qid=:qid")
	int update_ReviewStatus_ForQuotationForDC(@Param("review") int review, @Param("qid") String qid);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update delivery_challan set email_sent_status=:email_sent_status where qid=:qid")
	int update_EmailSentStatus_AfterReviewForDC(@Param("email_sent_status") String email_sent_status, @Param("qid") String qid);
	
	@Query(nativeQuery = true, value = "select email_sent_status from delivery_challan where qid=:qid")
	String getEmailSentStatusFlagValueForDC(@Param("qid") String qid);
	
//	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid=:qid", nativeQuery = true)
//    List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
//	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid = :qid OR q.qid REGEXP CONCAT('^', :qid, '_\\d+$')", nativeQuery = true)
//	//List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
//	String checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
	@Query(value = "SELECT q.qid FROM delivery_challan q WHERE q.qid LIKE :qid OR q.qid REGEXP CONCAT('^', :qid, '_\\d+$')", nativeQuery = true)
	List<String> checkTheNumberOfTimesExistingQuotationIdForDC(@Param("qid") String qid);

	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="delete from delivery_challan where qid=:qid")
	void deleteQuotationDataInQuotationTableAfterReviseForDC(@Param("qid") String qid);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="delete from delivery_challan where qid=:qid")
	void deleteQuotationDataInProductTableAfterReviseForDC(@Param("qid") String qid);
	
	@Query(nativeQuery = true, value="select prepared_by from delivery_challan where qid=:qid")
	String getQuotationPreparedByDataBasedOnQIdForDC(@Param("qid") String qid);
}
