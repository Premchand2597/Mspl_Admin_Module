package com.example.mspl_connect.Sales_Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.Add_Quotation_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface IAdd_Quotation extends JpaRepository<Add_Quotation_Entity, Long> {
	
//	 	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid NOT LIKE '%\\_%' ORDER BY q.qid DESC LIMIT 1", nativeQuery = true)
//	    String findLastQid(); 
	
//	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid NOT LIKE '%\\_%' OR q.qid LIKE '%\\_%' ORDER BY CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(q.qid, '/', -1), '_', 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
//	String findLastQid();
	
@Query(value="""
			
			SELECT q.qid
			FROM new_quotation q
			WHERE q.qid NOT LIKE '%\\_%' OR q.qid LIKE '%\\_%'
			ORDER BY 
			CAST(
			    split_part(split_part(q.qid, '/', array_length(string_to_array(q.qid, '/'), 1)), '_', 1) AS INTEGER
			) DESC
			LIMIT 1;
			
			""", nativeQuery = true)
	String findLastQid();


	/*@Modifying
 	@Transactional
 	@Query(value = "CALL add_quotation_user_details(:p_qid, :p_dateTime, :p_customer_name, :p_customer_legal_name, :p_gst_number, :p_order_method, :p_contact_person_name, :p_designation, :p_mobile_number, :p_email_id, :p_delivery_address, :p_shipment_address, :p_tax_type,:p_review ,:honorifics1, :honorifics2, :quotationSentBy, :reviseReason, :p_empid, :p_notification_flag)", nativeQuery = true)
    void saveQuotationDetails(
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
        @Param("p_review") Integer review,
        @Param("honorifics1") String honorifics1,
        @Param("honorifics2") String honorifics2,
        @Param("quotationSentBy") String quotationSentBy,
        @Param("reviseReason") String reviseReason,
        @Param("p_empid") String empid ,// ✅ new param here
        @Param("p_notification_flag") Integer notificationFlag 
    );*/

	@Modifying
	@Transactional
	@Query(value = """
	INSERT INTO new_quotation (qid, date_time, customer_name, customer_legal_name, gst_number, order_method, contact_person_name, designation, mobile_number, email_id,
	delivery_address, shipment_address, tax_type, review, email_sent_status, honorifics1, honorifics2, prepared_by, revise_reason, empid, notification_flag)
	VALUES (:qid, :dateTime, :customerName, :customerLegalName, :gstNumber, :orderMethod, :contactPersonName, :designation, :mobileNumber, :emailId, 
	:deliveryAddress, :shipmentAddress, :taxType, :review, '0', :honorifics1, :honorifics2, :quotationSentBy, :reviseReason, :empid, :notificationFlag)
	""", nativeQuery = true)
	void saveQuotationDetails(
	    @Param("qid") String qid,
	    @Param("dateTime") String dateTime,
	    @Param("customerName") String customerName,
	    @Param("customerLegalName") String customerLegalName,
	    @Param("gstNumber") String gstNumber,
	    @Param("orderMethod") String orderMethod,
	    @Param("contactPersonName") String contactPersonName,
	    @Param("designation") String designation,
	    @Param("mobileNumber") String mobileNumber,
	    @Param("emailId") String emailId,	       
	    @Param("deliveryAddress") String deliveryAddress,	        
	    @Param("shipmentAddress") String shipmentAddress,
	    @Param("taxType") String taxType,
	    @Param("review") Integer review,
	    @Param("honorifics1") String honorifics1,
	    @Param("honorifics2") String honorifics2,
	    @Param("quotationSentBy") String quotationSentBy,
	    @Param("reviseReason") String reviseReason,
	    @Param("empid") String empid,
	    @Param("notificationFlag") Integer notificationFlag 
	);
	 
    // Call stored procedure for saving quotation product details
	/*@Modifying
	@Transactional
	@Query(value = "CALL add_quotation_product_details(:p_id, :p_product_name, :p_hsn_code, :p_part_number, :p_description, :p_quantity, :p_price, :p_discount, :p_total_price, :p_cgst, :p_sgst, :p_igst, :p_total_with_tax, :qid, :quotation_id)", nativeQuery = true)
	void saveQuotationProduct(
	    @Param("p_id") String p_id,
	    @Param("p_product_name") String p_product_name,        
	    @Param("p_hsn_code") String p_hsn_code,
	    @Param("p_part_number") String p_part_number,
	    @Param("p_description") String p_description,        
	    @Param("p_quantity") int p_quantity,
	    @Param("p_price") double p_price,
	    @Param("p_discount") int p_discount,
	    @Param("p_total_price") double p_total_price,
	    @Param("p_cgst") double p_cgst,
	    @Param("p_sgst") double p_sgst,
	    @Param("p_igst") double p_igst, 
	    @Param("p_total_with_tax") String p_total_with_tax,        
	    @Param("qid") String qid,
	    @Param("quotation_id") Long quotationId
	);*/
	
	@Modifying
	@Transactional
	@Query(value = """
			
INSERT INTO quotation_products (
        p_id, 
        product_name, 
        hsn_code,
        part_number,
        description,
        quantity, 
        price, 
        discount,
        total_price,
        cgst,
        sgst,
        igst,
        total_with_tax,
        qid,
        quotation_id
    )
    VALUES (
        :p_id, 
        :p_product_name, 
        :p_hsn_code,
        :p_part_number,
        :p_description,   
        :p_quantity, 
        :p_price, 
        :p_discount,
        :p_total_price,
        :p_cgst,
        :p_sgst,
        :p_igst,
        :p_total_with_tax,
        :qid,
        :quotation_id
    );
			
			""", nativeQuery = true)
	void saveQuotationProduct(
	    @Param("p_id") String p_id,
	    @Param("p_product_name") String p_product_name,        
	    @Param("p_hsn_code") String p_hsn_code,
	    @Param("p_part_number") String p_part_number,
	    @Param("p_description") String p_description,        
	    @Param("p_quantity") int p_quantity,
	    @Param("p_price") double p_price,
	    @Param("p_discount") int p_discount,
	    @Param("p_total_price") double p_total_price,
	    @Param("p_cgst") double p_cgst,
	    @Param("p_sgst") double p_sgst,
	    @Param("p_igst") double p_igst, 
	    @Param("p_total_with_tax") String p_total_with_tax,        
	    @Param("qid") String qid,
	    @Param("quotation_id") Long quotation_id
	);

	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by,revise_reason,terminate_reason FROM new_quotation nq LEFT JOIN quotation_products qp ON nq.qid = qp.qid ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWithProductsNative();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM new_quotation nq LEFT JOIN quotation_products qp ON nq.qid = qp.qid where nq.review <> 0 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWithProductsNativeForReview();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM new_quotation nq LEFT JOIN quotation_products qp ON nq.qid = qp.qid where nq.review = 3 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationWonDetails();
	
	@Query(value = "SELECT nq.qid,nq.date_time,nq.customer_name,nq.contact_person_name,nq.designation,nq.mobile_number,nq.email_id,nq.order_method,nq.delivery_address,nq.shipment_address,qp.product_name,qp.quantity,qp.price,qp.discount,qp.total_price,qp.cgst,qp.sgst,qp.igst,nq.review,nq.email_sent_status,nq.honorifics1,nq.honorifics2,nq.prepared_by FROM new_quotation nq LEFT JOIN quotation_products qp ON nq.qid = qp.qid where nq.review = 4 ORDER BY nq.id desc", nativeQuery = true)
	List<Object[]> fetchQuotationLossDetails();


	@Query(value = "SELECT q.qid, q.date_time, q.customer_name, q.customer_legal_name, q.gst_number, " +
            "q.order_method, q.contact_person_name, q.designation, q.mobile_number, q.email_id, " +
            "q.shipment_address, q.delivery_address, q.tax_type, " +
            "p.product_name, p.hsn_code, p.part_number, p.description, p.quantity, p.price, " +
            "p.discount, p.total_price, p.cgst, p.sgst, p.igst, p.total_with_tax,p.quotation_id, q.review, q.email_sent_status, q.honorifics1, q.honorifics2,q.prepared_by,p.p_id " +
            "FROM new_quotation q " +
            "LEFT JOIN quotation_products p ON q.qid = p.qid " +
            "WHERE q.qid = :id", nativeQuery = true)
			List<Object[]> findQuotationDetailsById(@Param("id") String id);

	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update new_quotation set review=:review , terminate_reason=:treminateReason,notification_flag = :notificationFlag where qid=:qid")
	int update_ReviewStatus_ForQuotation(@Param("review") int review, @Param("qid") String qid,@Param("treminateReason") String treminateReason,Integer notificationFlag);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update new_quotation set email_sent_status=:email_sent_status where qid=:qid")
	int update_EmailSentStatus_AfterReview(@Param("email_sent_status") String email_sent_status, @Param("qid") String qid);
	
	@Query(nativeQuery = true, value = "select email_sent_status from new_quotation where qid=:qid")
	String getEmailSentStatusFlagValue(@Param("qid") String qid);
	
	@Query(nativeQuery = true, value = "select review from new_quotation where qid=:qid")
	String getReviewFlagValue(@Param("qid") String qid);
	
	//	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid=:qid", nativeQuery = true)
	//    List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
	//	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid = :qid OR q.qid REGEXP CONCAT('^', :qid, '_\\d+$')", nativeQuery = true)
	//	//List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	//	String checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
//	@Query(value = "SELECT q.qid FROM new_quotation q WHERE q.qid LIKE :qid OR q.qid REGEXP CONCAT('^', :qid, '_\\d+$')", nativeQuery = true)
//	List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
	@Query(value = """
		    SELECT q.qid 
		    FROM new_quotation q 
		    WHERE q.qid = :qid 
		       OR q.qid ~ ('^' || :qid || '_\\d+$')
		    """, nativeQuery = true)
		List<String> checkTheNumberOfTimesExistingQuotationId(@Param("qid") String qid);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="delete from new_quotation where qid=:qid")
	void deleteQuotationDataInQuotationTableAfterRevise(@Param("qid") String qid);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="delete from quotation_products where qid=:qid")
	void deleteQuotationDataInProductTableAfterRevise(@Param("qid") String qid);
	
	@Query(nativeQuery = true, value="select prepared_by from new_quotation where qid=:qid")
	String getQuotationPreparedByDataBasedOnQId(@Param("qid") String qid);


	Optional<Add_Quotation_Entity> findAllByQid(String quotationId);

	@Query(nativeQuery = true,value="select count(q.notification_flag) as count from new_quotation q inner join employee_details ed "
			+ "on concat(ed.f_name,' ',ed.l_name) = q.prepared_by "
			+ "where q.notification_flag = 1 and (ed.team_lead_name = :empId or ed.team_co_name = :empId)")
	Integer getQuotationNotificationByAdmin(String empId);
	
	@Query(value="select count(notification_flag) from new_quotation where notification_flag=4" , nativeQuery = true)
	Integer getReviseQuotationCount();
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update new_quotation set notification_flag=0 where qid=:qid")
	int update_NotificationFlag(@Param("qid") String qid);
	
	@Query(nativeQuery = true,value="select qid from new_quotation q inner join employee_details ed on concat(ed.f_name,' ',ed.l_name) = q.prepared_by where q.notification_flag = 1 and (ed.team_lead_name = :empId or ed.team_co_name = :empId)")
	List<String> getQidByEmpId(String empId);


	@Query(value="SELECT q.qid FROM new_quotation q WHERE q.qid NOT LIKE '%\\\\_%' OR q.qid LIKE '%\\\\_%' and prepared_by = :loggedInFullName ORDER BY CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(q.qid, '/', -1), '_', 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
	String findLastQidBasedOnPreparedByName(@Param("loggedInFullName") String loggedInFullName);
	
	@Query(nativeQuery = true, value="select qid from new_quotation")
	List<String> findAllQid();
	
	@Query(value = "SELECT q.id FROM new_quotation q WHERE q.qid = :qid", nativeQuery = true)
	String findInternalIdByQid(@Param("qid") String qid);

	 // ✅ Add this method
//	 Add_Quotation_Entity findByQid(String qid);
	
	@Query(nativeQuery = true, value="select * from new_quotation where qid=:qid order by id desc limit 1;")
	 Add_Quotation_Entity findByQid(String qid);
	
}
