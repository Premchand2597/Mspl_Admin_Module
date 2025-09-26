package com.example.mspl_connect.Sales_Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Sales_DTO.Add_DC_DTO;
import com.example.mspl_connect.Sales_DTO.Add_Quotation_DTO;
import com.example.mspl_connect.Sales_DTO.ProductDTO;
import com.example.mspl_connect.Sales_DTO.QuotationDTO;
import com.example.mspl_connect.Sales_Entity.Add_Quotation_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;
import com.example.mspl_connect.Sales_Entity.EmailDraftBodyMessage_Entity;
import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;
import com.example.mspl_connect.Sales_Entity.fetchReportingManagerEmailByEmployeeEmail_Entity;
import com.example.mspl_connect.Sales_Repository.EmailDraftBodyMessage_Repo;
import com.example.mspl_connect.Sales_Repository.IAdd_DeliveryChallan;
import com.example.mspl_connect.Sales_Repository.IAdd_Product;
import com.example.mspl_connect.Sales_Repository.IAdd_Quotation;
import com.example.mspl_connect.Sales_Repository.fetchReportingManagerEmailByEmployeeEmail_Repo;


@Service
public class Add_DC_Service {
    
    @Autowired
    private final IAdd_DeliveryChallan iAdd_Quotation;
    
    @Autowired
    private fetchReportingManagerEmailByEmployeeEmail_Repo reportingManagerEmailByEmployeeEmail_Repo;
    
    @Autowired
    private EmailDraftBodyMessage_Repo emailDraftBodyMessage_Repo;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
    

    public Add_DC_Service(IAdd_DeliveryChallan iAdd_Quotation) {
        this.iAdd_Quotation = iAdd_Quotation;
    }
    
    
    @Transactional
    public void saveQuotationForDC(Add_DC_DTO quotationDTO, HttpSession session) {
    	
    	String loggedInEmailIdValue = (String) session.getAttribute("email");
 		DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
	     String loggedInFullName = fetchedEmpData.getFullName();
	     quotationDTO.setPrepared_by(loggedInFullName);
    	
        // Save Quotation User Details
        iAdd_Quotation.saveDCDetails(
        	quotationDTO.getQid(),
        	quotationDTO.getqdateTime(),
            quotationDTO.getCustomerName(),
            quotationDTO.getVendorlegalName(),
            quotationDTO.getGstnumber(),
            quotationDTO.getOrderMethod(),            
            quotationDTO.getContactPersonName(),
            quotationDTO.getDesignation(),
            quotationDTO.getContactDetails(),
            quotationDTO.getEmailId(),         
            quotationDTO.getDeliveryAddress(),
            quotationDTO.getShipmentAddress(),
            quotationDTO.getTaxType(),
            quotationDTO.getHonorifics1(),
            quotationDTO.getHonorifics2(),
            quotationDTO.getPrepared_by()
        );

        // Iterate and Save Product Details
        quotationDTO.getProducts().forEach(productDTO -> {
            iAdd_Quotation.saveDCProduct(
                productDTO.getSlNo(),            // Assuming SlNo acts as Product ID
                productDTO.getProductName(),
                productDTO.getHsnCode(),
                productDTO.getPartNumber(), 
                productDTO.getProductDescription(),
                productDTO.getQuantity(),
                productDTO.getPrice(),
                productDTO.getDiscount(),
                productDTO.getTotal(),              
                productDTO.getCgst(),
                productDTO.getSgst(),
                productDTO.getIgst(),
                productDTO.getTotal_with_gst(),
                productDTO.getQid()
            );
        });
    }
    
    
//    @Transactional
//    public void reviseQuotation(Add_Quotation_DTO quotationDTO, HttpSession session) {
//    	
////    	String loggedInEmailIdValue = (String) session.getAttribute("email");
////    	DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
////        String loggedInFullName = fetchedEmpData.getFullName();
////        quotationDTO.setPrepared_by(loggedInFullName);
//    	
//        // Generate the correct next quotation ID
//        String nextQuotationId = getNextQuotationId(quotationDTO.getQid());
//        
//        String quotationPreparedBy = iAdd_Quotation.getQuotationPreparedByDataBasedOnQId(quotationDTO.getQid());
//        quotationDTO.setPrepared_by(quotationPreparedBy);
//        
//        iAdd_Quotation.deleteQuotationDataInQuotationTableAfterRevise(quotationDTO.getQid());
//        iAdd_Quotation.deleteQuotationDataInProductTableAfterRevise(quotationDTO.getQid());
//        
//        quotationDTO.setQid(nextQuotationId); // Set updated QID
//
//        // Save quotation
//        iAdd_Quotation.saveQuotationDetails(
//            quotationDTO.getQid(),
//            quotationDTO.getqdateTime(),
//            quotationDTO.getCustomerName(),
//            quotationDTO.getVendorlegalName(),
//            quotationDTO.getGstnumber(),
//            quotationDTO.getOrderMethod(),
//            quotationDTO.getContactPersonName(),
//            quotationDTO.getDesignation(),
//            quotationDTO.getContactDetails(),
//            quotationDTO.getEmailId(),
//            quotationDTO.getDeliveryAddress(),
//            quotationDTO.getShipmentAddress(),
//            quotationDTO.getTaxType(),
//            quotationDTO.getHonorifics1(),
//            quotationDTO.getHonorifics2(),
//            quotationDTO.getPrepared_by()
//        );
//
//        // Save products with the updated QID
//        quotationDTO.getProducts().forEach(productDTO -> {
//            productDTO.setQid(quotationDTO.getQid());
//            iAdd_Quotation.saveQuotationProduct(
//                productDTO.getSlNo(),
//                productDTO.getProductName(),
//                productDTO.getHsnCode(),
//                productDTO.getPartNumber(),
//                productDTO.getProductDescription(),
//                productDTO.getQuantity(),
//                productDTO.getPrice(),
//                productDTO.getDiscount(),
//                productDTO.getTotal(),
//                productDTO.getCgst(),
//                productDTO.getSgst(),
//                productDTO.getIgst(),
//                productDTO.getTotal_with_gst(),
//                productDTO.getQid()
//            );
//        });
//    }
    
	    /*public String get_qid() {
	        String lastQid = iAdd_Quotation.findLastQid();
	        //System.out.println("lastQid == "+lastQid);
	        String returnedFY = getCustomCurrentFinancialYear();
	
	        if (lastQid != null && lastQid.matches("MSPL/Q/F\\d{2}-\\d{2}/\\d{3}")) {
	            // Extract numeric part after the last "/"
	            String numericPart = lastQid.substring(lastQid.lastIndexOf("/") + 1);
	            int lastNumber = Integer.parseInt(numericPart);
	            return String.format("MSPL/Q/%s/%03d", returnedFY, lastNumber + 1);
	        }
	
	        return String.format("MSPL/Q/%s/%03d", returnedFY, 1);
	    }*/
    
	    public String get_qidForDC() {
	    	String lastQid = iAdd_Quotation.findLastQidForDC();
	        String returnedFY = getCustomCurrentFinancialYearForDC(); // Now without 'F'
	        
	        if (lastQid != null && lastQid.matches("MSPL/Q/\\d{2}-\\d{2}/\\d{3}(_\\d+)?")) {
	            // Extract numeric part after the last "/"
	            String numericPart = lastQid.substring(lastQid.lastIndexOf("/") + 1);
	            
	            // Remove any suffix (_x) if present
	            if (numericPart.contains("_")) {
	                numericPart = numericPart.substring(0, numericPart.indexOf("_"));
	            }

	            int lastNumber = Integer.parseInt(numericPart);
	            return String.format("MSPL/Q/%s/%03d", returnedFY, lastNumber + 1);
	        }

	        return String.format("MSPL/Q/%s/%03d", returnedFY, 100); // Start from 100
	    }

	
	    public String getCustomCurrentFinancialYearForDC() {
	    	LocalDate today = LocalDate.now();
	        int year = today.getYear();
	        
	        // Determine financial year format without 'F'
	        if (today.getMonthValue() < 3 || (today.getMonthValue() == 3 && today.getDayOfMonth() < 26)) {
	            return String.format("%02d-%02d", (year - 1) % 100, year % 100);
	        } else {
	            return String.format("%02d-%02d", year % 100, (year + 1) % 100);
	        }
	    }
	
	public List<QuotationDTO> getAllQuotationForDC() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWithProductsNativeForDC();
	    Map<String, QuotationDTO> quotationMap = new HashMap<>();

	    for (Object[] row : results) {
	        String qid = row[0] != null ? row[0].toString() : "";
	        QuotationDTO quotation = quotationMap.getOrDefault(qid, new QuotationDTO());

	        if (!quotationMap.containsKey(qid)) {
	            quotation.setQid(qid);
	            quotation.setDateTime(row[1] != null ? row[1].toString() : "");
	            quotation.setCustomerName(row[2] != null ? row[2].toString() : "N/A"); // Avoid NullPointerException
	            quotation.setContactPersonName(row[3] != null ? row[3].toString() : "N/A");
	            quotation.setDesignation(row[4] != null ? row[4].toString() : "N/A");
	            quotation.setContactDetails(row[5] != null ? row[5].toString() : "N/A");
	            quotation.setEmailId(row[6] != null ? row[6].toString() : "N/A");
	            quotation.setOrderMethod(row[7] != null ? row[7].toString() : "N/A");
	            quotation.setDeliveryAddress(row[8] != null ? row[8].toString() : "N/A");
	            quotation.setShipmentAddress(row[9] != null ? row[9].toString() : "N/A");
	            quotation.setReview(row[18] != null ? Integer.parseInt(row[18].toString()) : 0);
	            quotation.setEmail_sent_status(row[19] != null ? row[19].toString() : "N/A");
	            quotation.setHonorifics1(row[20] != null ? row[20].toString() : "N/A");
	            quotation.setHonorifics2(row[21] != null ? row[21].toString() : "N/A");
	            quotation.setPrepared_by(row[22] != null ? row[22].toString() : "-");
	            quotation.setProducts(new ArrayList<>());
	            quotationMap.put(qid, quotation);
	        }

	        if (row.length > 10 && row[10] != null) { // Ensure product data exists
	            ProductDTO product = new ProductDTO();
	            product.setProductName(row[10] != null ? row[10].toString() : "N/A");
	            product.setQuantity(row[11] != null ? Integer.parseInt(row[11].toString()) : 0);
	            product.setPrice(row[12] != null ? row[12].toString() : "N/A");
	            product.setDiscount(row[13] != null ? Double.parseDouble(row[13].toString()) : 0.0);
	            product.setTotal(row[14] != null ? row[14].toString() : "N/A");
	            product.setCgst(row[15] != null ?row[15].toString() :"N/A");
	            product.setSgst(row[16] != null ?row[16].toString() :"N/A");
	            product.setIgst(row[17] != null ?row[17].toString() :"N/A");           

	            quotation.getProducts().add(product);
	        }
	    }
	    
	    List<QuotationDTO> sortedQuotations = new ArrayList<>(quotationMap.values());

	    // Sort by date_time in descending order (if needed)
	    sortedQuotations.sort((q1, q2) -> q2.getDateTime().compareTo(q1.getDateTime()));

	    return sortedQuotations;
	    
	    //return new ArrayList<>(quotationMap.values());
	}
	
	
	public List<QuotationDTO> getAllQuotationForReviewForDC() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWithProductsNativeForReviewForDC();
	    Map<String, QuotationDTO> quotationMap = new HashMap<>();

	    for (Object[] row : results) {
	        String qid = row[0] != null ? row[0].toString() : "";
	        QuotationDTO quotation = quotationMap.getOrDefault(qid, new QuotationDTO());

	        if (!quotationMap.containsKey(qid)) {
	            quotation.setQid(qid);
	            quotation.setDateTime(row[1] != null ? row[1].toString() : "");
	            quotation.setCustomerName(row[2] != null ? row[2].toString() : "N/A"); // Avoid NullPointerException
	            quotation.setContactPersonName(row[3] != null ? row[3].toString() : "N/A");
	            quotation.setDesignation(row[4] != null ? row[4].toString() : "N/A");
	            quotation.setContactDetails(row[5] != null ? row[5].toString() : "N/A");
	            quotation.setEmailId(row[6] != null ? row[6].toString() : "N/A");
	            quotation.setOrderMethod(row[7] != null ? row[7].toString() : "N/A");
	            quotation.setDeliveryAddress(row[8] != null ? row[8].toString() : "N/A");
	            quotation.setShipmentAddress(row[9] != null ? row[9].toString() : "N/A");
	            quotation.setReview(row[18] != null ? Integer.parseInt(row[18].toString()) : 0);
	            quotation.setEmail_sent_status(row[19] != null ? row[19].toString() : "N/A");
	            quotation.setHonorifics1(row[20] != null ? row[20].toString() : "N/A");
	            quotation.setHonorifics2(row[21] != null ? row[21].toString() : "N/A");
	            quotation.setPrepared_by(row[22] != null ? row[22].toString() : "-");
	            quotation.setProducts(new ArrayList<>());
	            quotationMap.put(qid, quotation);
	        }

	        if (row.length > 10 && row[10] != null) { // Ensure product data exists
	            ProductDTO product = new ProductDTO();
	            product.setProductName(row[10] != null ? row[10].toString() : "N/A");
	            product.setQuantity(row[11] != null ? Integer.parseInt(row[11].toString()) : 0);
	            product.setPrice(row[12] != null ? row[12].toString() : "N/A");
	            product.setDiscount(row[13] != null ? Double.parseDouble(row[13].toString()) : 0.0);
	            product.setTotal(row[14] != null ? row[14].toString() : "N/A");
	            product.setCgst(row[15] != null ?row[15].toString() :"N/A");
	            product.setSgst(row[16] != null ?row[16].toString() :"N/A");
	            product.setIgst(row[17] != null ?row[17].toString() :"N/A");           

	            quotation.getProducts().add(product);
	        }
	    }
	    
	    List<QuotationDTO> sortedQuotations = new ArrayList<>(quotationMap.values());

	    // Sort by date_time in descending order (if needed)
	    sortedQuotations.sort((q1, q2) -> q2.getDateTime().compareTo(q1.getDateTime()));

	    return sortedQuotations;

	    //return new ArrayList<>(quotationMap.values());
	}
	
	public List<QuotationDTO> getQuotationWonDetailsForDC() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWonDetailsForDC();
	    Map<String, QuotationDTO> quotationMap = new HashMap<>();

	    for (Object[] row : results) {
	        String qid = row[0] != null ? row[0].toString() : "";
	        QuotationDTO quotation = quotationMap.getOrDefault(qid, new QuotationDTO());

	        if (!quotationMap.containsKey(qid)) {
	            quotation.setQid(qid);
	            quotation.setDateTime(row[1] != null ? row[1].toString() : "");
	            quotation.setCustomerName(row[2] != null ? row[2].toString() : "N/A"); // Avoid NullPointerException
	            quotation.setContactPersonName(row[3] != null ? row[3].toString() : "N/A");
	            quotation.setDesignation(row[4] != null ? row[4].toString() : "N/A");
	            quotation.setContactDetails(row[5] != null ? row[5].toString() : "N/A");
	            quotation.setEmailId(row[6] != null ? row[6].toString() : "N/A");
	            quotation.setOrderMethod(row[7] != null ? row[7].toString() : "N/A");
	            quotation.setDeliveryAddress(row[8] != null ? row[8].toString() : "N/A");
	            quotation.setShipmentAddress(row[9] != null ? row[9].toString() : "N/A");
	            quotation.setReview(row[18] != null ? Integer.parseInt(row[18].toString()) : 0);
	            quotation.setEmail_sent_status(row[19] != null ? row[19].toString() : "N/A");
	            quotation.setHonorifics1(row[20] != null ? row[20].toString() : "N/A");
	            quotation.setHonorifics2(row[21] != null ? row[21].toString() : "N/A");
	            quotation.setPrepared_by(row[22] != null ? row[22].toString() : "-");
	            quotation.setProducts(new ArrayList<>());
	            quotationMap.put(qid, quotation);
	        }

	        if (row.length > 10 && row[10] != null) { // Ensure product data exists
	            ProductDTO product = new ProductDTO();
	            product.setProductName(row[10] != null ? row[10].toString() : "N/A");
	            product.setQuantity(row[11] != null ? Integer.parseInt(row[11].toString()) : 0);
	            product.setPrice(row[12] != null ? row[12].toString() : "N/A");
	            product.setDiscount(row[13] != null ? Double.parseDouble(row[13].toString()) : 0.0);
	            product.setTotal(row[14] != null ? row[14].toString() : "N/A");
	            product.setCgst(row[15] != null ?row[15].toString() :"N/A");
	            product.setSgst(row[16] != null ?row[16].toString() :"N/A");
	            product.setIgst(row[17] != null ?row[17].toString() :"N/A");           

	            quotation.getProducts().add(product);
	        }
	    }
	    
	    List<QuotationDTO> sortedQuotations = new ArrayList<>(quotationMap.values());

	    // Sort by date_time in descending order (if needed)
	    sortedQuotations.sort((q1, q2) -> q2.getDateTime().compareTo(q1.getDateTime()));

	    return sortedQuotations;
	}
	
	public List<QuotationDTO> getQuotationLossDetailsForDC() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationLossDetailsForDC();
	    Map<String, QuotationDTO> quotationMap = new HashMap<>();

	    for (Object[] row : results) {
	        String qid = row[0] != null ? row[0].toString() : "";
	        QuotationDTO quotation = quotationMap.getOrDefault(qid, new QuotationDTO());

	        if (!quotationMap.containsKey(qid)) {
	            quotation.setQid(qid);
	            quotation.setDateTime(row[1] != null ? row[1].toString() : "");
	            quotation.setCustomerName(row[2] != null ? row[2].toString() : "N/A"); // Avoid NullPointerException
	            quotation.setContactPersonName(row[3] != null ? row[3].toString() : "N/A");
	            quotation.setDesignation(row[4] != null ? row[4].toString() : "N/A");
	            quotation.setContactDetails(row[5] != null ? row[5].toString() : "N/A");
	            quotation.setEmailId(row[6] != null ? row[6].toString() : "N/A");
	            quotation.setOrderMethod(row[7] != null ? row[7].toString() : "N/A");
	            quotation.setDeliveryAddress(row[8] != null ? row[8].toString() : "N/A");
	            quotation.setShipmentAddress(row[9] != null ? row[9].toString() : "N/A");
	            quotation.setReview(row[18] != null ? Integer.parseInt(row[18].toString()) : 0);
	            quotation.setEmail_sent_status(row[19] != null ? row[19].toString() : "N/A");
	            quotation.setHonorifics1(row[20] != null ? row[20].toString() : "N/A");
	            quotation.setHonorifics2(row[21] != null ? row[21].toString() : "N/A");
	            quotation.setPrepared_by(row[22] != null ? row[22].toString() : "-");
	            quotation.setProducts(new ArrayList<>());
	            quotationMap.put(qid, quotation);
	        }

	        if (row.length > 10 && row[10] != null) { // Ensure product data exists
	            ProductDTO product = new ProductDTO();
	            product.setProductName(row[10] != null ? row[10].toString() : "N/A");
	            product.setQuantity(row[11] != null ? Integer.parseInt(row[11].toString()) : 0);
	            product.setPrice(row[12] != null ? row[12].toString() : "N/A");
	            product.setDiscount(row[13] != null ? Double.parseDouble(row[13].toString()) : 0.0);
	            product.setTotal(row[14] != null ? row[14].toString() : "N/A");
	            product.setCgst(row[15] != null ?row[15].toString() :"N/A");
	            product.setSgst(row[16] != null ?row[16].toString() :"N/A");
	            product.setIgst(row[17] != null ?row[17].toString() :"N/A");           

	            quotation.getProducts().add(product);
	        }
	    }
	    
	    List<QuotationDTO> sortedQuotations = new ArrayList<>(quotationMap.values());

	    // Sort by date_time in descending order (if needed)
	    sortedQuotations.sort((q1, q2) -> q2.getDateTime().compareTo(q1.getDateTime()));

	    return sortedQuotations;
	}
	
	
	 public QuotationDTO getQuotationByIdForDC(String id) {
	        List<Object[]> results = iAdd_Quotation.findQuotationDetailsByIdForDC(id);
	        
	        // Print query results to console
	        System.out.println("Query Results:");
	        for (Object[] row : results) {
	            System.out.println(Arrays.toString(row));
	        }
	        
	        if (results.isEmpty()) {
	            return null; // Return null if no data found
	        }

	        QuotationDTO dto = new QuotationDTO();
	        List<ProductDTO> products = new ArrayList<>();

	        for (Object[] row : results) {
	            dto.setQid(row[0].toString());
	            dto.setDateTime(row[1].toString());
	            dto.setCustomerName(row[2].toString());
	            dto.setCustomerLegalName(row[3].toString());
	            dto.setGstNumber(row[4].toString());
	            dto.setOrderMethod(row[5].toString());
	            dto.setContactPersonName(row[6] != null ? row[6].toString() : "-");
	            dto.setDesignation(row[7] != null ? row[7].toString() : "-");
	            dto.setContactDetails(row[8] != null ? row[8].toString() : "-");
	            dto.setEmailId(row[9] != null ? row[9].toString() : "-");
	            dto.setShipmentAddress(row[10] != null ? row[10].toString() : "-");
	            dto.setDeliveryAddress(row[11] != null ? row[11].toString() : "-");
	            dto.setTax_type(row[12] != null ? row[12].toString() : "-");
	            dto.setReview(row[25] != null ? Integer.parseInt(row[25].toString()) : 0);
	            dto.setEmail_sent_status(row[26] != null ? row[26].toString() : "-");
	            dto.setHonorifics1(row[27] != null ? row[27].toString() : "-");
	            dto.setHonorifics2(row[28] != null ? row[28].toString() : "-");
	            dto.setPrepared_by(row[29] != null ? row[29].toString() : "-");

	            // Map product details
	            ProductDTO product = new ProductDTO();
	            product.setProductName(row[13] != null ? row[13].toString() : "-");
	            product.setHsnCode(row[14] != null ? row[14].toString() : "-");
	            product.setPartNumber(row[15] != null ? row[15].toString() : "-");
	            product.setDescription(row[16] != null ? row[16].toString() : "-");
	            product.setQuantity(row[17] != null ? Integer.parseInt(row[17].toString()) : 0);
	            product.setPrice(row[18] != null ? row[18].toString() : "N/A");
	            product.setDiscount(row[19] != null ? Double.parseDouble(row[19].toString()) : 0.0);
	            product.setTotal(row[20] != null ? row[20].toString() : "N/A");
	            product.setCgst(row[21] != null ? row[21].toString() : "-");
	            product.setSgst(row[22] != null ? row[22].toString() : "-");
	            product.setIgst(row[23] != null ? row[23].toString() : "-");
	            product.setTotal_value(row[24] != null ? row[24].toString() : "-");

	            products.add(product);
	        }

	        dto.setProducts(products);
	        return dto;
	    }

	 	public boolean updateReviewStatusForQuotationForDC(int reviewStatus, String quotationId) {
	 		int rowsAffected = iAdd_Quotation.update_ReviewStatus_ForQuotationForDC(reviewStatus, quotationId);
	        return rowsAffected > 0;
	 	}
	 	
	 	public boolean updateEmailSentStatusForCustomerForDC(String emailStatus, String quotationId) {
	 		int rowsAffected = iAdd_Quotation.update_EmailSentStatus_AfterReviewForDC(emailStatus, quotationId);
	        return rowsAffected > 0;
	 	}
	 	
	 	public String retrieveEmailSentFlagStatusValueForDC(String quotationId) {
	        return iAdd_Quotation.getEmailSentStatusFlagValueForDC(quotationId);
	 	}
	 	
	 	public fetchReportingManagerEmailByEmployeeEmail_Entity getReportingManagerEmailIdBasedOnEmpEmailForDC(String employee_email) {
	 		return reportingManagerEmailByEmployeeEmail_Repo.getReportingMangersEmailId(employee_email);
	 	}
	 	
	 	public EmailDraftBodyMessage_Entity getRecentInsertedEmailDraftDetailForDC() {
			return emailDraftBodyMessage_Repo.fetchRecentInsertedEmailDraftMessageDetail();
		}
	 	
	 	public void insertEmailDraftBodyToDBForDC(EmailDraftBodyMessage_Entity emailDraftBodyMessage_Entity) {
	 		emailDraftBodyMessage_Repo.save(emailDraftBodyMessage_Entity);
	 	}
	 	
	 	public void sendEmailWithAttachmentForDC(String from, String to, String cc, String subject, String message, String password, byte[] pdfBytes, HttpSession session) throws MessagingException, IOException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        String loggedInEmailIdValue = (String) session.getAttribute("email");
	 		DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
		    String loggedInFullName = fetchedEmpData.getFullName();
	        
	        helper.setFrom(from);
	        //helper.setTo(to);
	        
	        // Handling to dynamically (multiple or single email)
	        if (to != null && !to.trim().isEmpty()) {
	            String[] toArray = to.contains(",") ? to.split("\\s*,\\s*") : new String[]{to};
	            helper.setTo(toArray);
	        }
	        
	        // Handling CC dynamically (multiple or single email)
	        if (cc != null && !cc.trim().isEmpty()) {
	            String[] ccArray = cc.contains(",") ? cc.split("\\s*,\\s*") : new String[]{cc};
	            helper.setCc(ccArray);
	        }
	        
	        helper.setSubject(subject);
	        helper.setText(message+"\n\nRegards,\n"+loggedInFullName);

	        // Attach PDF file
	        if(pdfBytes != null) {
	        	helper.addAttachment("Quotation.pdf", new ByteArrayResource(pdfBytes));
	        }
	        mailSender.send(mimeMessage);
	    }
	 	
//	 	public List<String> getTheNumberOfTimesExistingQuotationId(String quotationId) {
//	        return iAdd_Quotation.checkTheNumberOfTimesExistingQuotationId(quotationId);
//	 	}
	 	
	 	
//	 	public String getNextQuotationId(String baseQid) {
//	 	    // Fetch all QIDs that start with baseQid
//	 	    //List<String> existingQuotationIds = iAdd_Quotation.checkTheNumberOfTimesExistingQuotationId(baseQid + "%");
//	 		
//	 		String existingQuotationIds = iAdd_Quotation.checkTheNumberOfTimesExistingQuotationId(baseQid + "%");
//	 	    
//	 	    System.out.println("existingQuotationIds == "+existingQuotationIds);
//
//	 	    int maxSuffix = 0; // Track highest numeric suffix
//	 	    
//	 	   if (existingQuotationIds.contains("_")) {
//	            // Extract the last numeric part after "_"
//	            String[] parts = existingQuotationIds.split("_");
//	            String lastPart = parts[parts.length - 1];
//
//	            try {
//	                int suffix = Integer.parseInt(lastPart);
//	                //maxSuffix = Math.max(maxSuffix, suffix);
//	                maxSuffix = suffix;
//	            } catch (NumberFormatException ignored) {
//	                // Ignore if last part is not a valid number
//	            }
//	        }
	 	    
	 	    

//	 	    for (String existingId : existingQuotationIds) {
//	 	        // Check if the ID already contains a "_number"
//	 	        if (existingId.contains("_")) {
//	 	            // Extract the last numeric part after "_"
//	 	            String[] parts = existingId.split("_");
//	 	            String lastPart = parts[parts.length - 1];
//
//	 	            try {
//	 	                int suffix = Integer.parseInt(lastPart);
//	 	                //maxSuffix = Math.max(maxSuffix, suffix);
//	 	                maxSuffix = suffix;
//	 	            } catch (NumberFormatException ignored) {
//	 	                // Ignore if last part is not a valid number
//	 	            }
//	 	        }
//	 	    }

	 	    // If no suffix found, start from _1, otherwise increment existing highest suffix
//	 	    return baseQid + "_" + (maxSuffix + 1);
//	 	}
	 	
	 	
	 	public String getNextQuotationIdForDC(String baseQid) {
	 	    // Fetch all QIDs that start with baseQid
	 	    List<String> existingQuotationIds = iAdd_Quotation.checkTheNumberOfTimesExistingQuotationIdForDC(baseQid + "%");

	 	    //System.out.println("existingQuotationIds == " + existingQuotationIds);

	 	    // If no existing QIDs, return baseQid with _1
	 	    if (existingQuotationIds == null || existingQuotationIds.isEmpty()) {
	 	        return baseQid + "_1";
	 	    }

	 	    int maxSuffix = 0; // Track highest numeric suffix
	 	    String tempBaseQid = baseQid; // Store base QID without "_number"

	 	    for (String existingId : existingQuotationIds) {
	 	        //System.out.println("existingId == " + existingId);

	 	        if (existingId.contains("_")) {
	 	            // Extract the last numeric part after "_"
	 	            String[] parts = existingId.split("_");

	 	            //System.out.println("parts == " + Arrays.toString(parts));

	 	            String lastPart = parts[parts.length - 1];

	 	            //System.out.println("lastPart == " + lastPart);

	 	            try {
	 	                int suffix = Integer.parseInt(lastPart);
	 	                //System.out.println("suffix == " + suffix);

	 	                maxSuffix = Math.max(maxSuffix, suffix);
	 	                //System.out.println("maxSuffix == " + maxSuffix);

	 	                // Store the base QID (everything before "_")
	 	                tempBaseQid = existingId.substring(0, existingId.lastIndexOf("_"));
	 	                //System.out.println("tempBaseQid == " + tempBaseQid);
	 	            } catch (NumberFormatException ignored) {
	 	                // Ignore if last part is not a valid number
	 	            }
	 	        }
	 	    }

	 	    // Generate next QID using tempBaseQid
	 	    return tempBaseQid + "_" + (maxSuffix + 1);
	 	}



}
