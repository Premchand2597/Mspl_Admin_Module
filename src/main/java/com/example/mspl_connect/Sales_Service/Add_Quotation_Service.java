package com.example.mspl_connect.Sales_Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Sales_DTO.Add_Quotation_DTO;
import com.example.mspl_connect.Sales_DTO.ProductDTO;
import com.example.mspl_connect.Sales_DTO.QuotationDTO;
import com.example.mspl_connect.Sales_Entity.Add_Quotation_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;
import com.example.mspl_connect.Sales_Entity.EmailDraftBodyMessage_Entity;
import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;
import com.example.mspl_connect.Sales_Entity.fetchReportingManagerEmailByEmployeeEmail_Entity;
import com.example.mspl_connect.Sales_Repository.EmailDraftBodyMessage_Repo;
import com.example.mspl_connect.Sales_Repository.IAdd_Product;
import com.example.mspl_connect.Sales_Repository.IAdd_Quotation;
import com.example.mspl_connect.Sales_Repository.fetchReportingManagerEmailByEmployeeEmail_Repo;


@Service
public class Add_Quotation_Service {
    
    @Autowired
    private final IAdd_Quotation iAdd_Quotation;
    
    @Autowired
    private fetchReportingManagerEmailByEmployeeEmail_Repo reportingManagerEmailByEmployeeEmail_Repo;
    
    @Autowired
    private EmailDraftBodyMessage_Repo emailDraftBodyMessage_Repo;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
	private Terms_and_condition_Service terms_and_condition_Service;

    public Add_Quotation_Service(IAdd_Quotation iAdd_Quotation) {
        this.iAdd_Quotation = iAdd_Quotation;
    }
    
    
    @Transactional
    public void saveQuotation(Add_Quotation_DTO quotationDTO, HttpSession session) {
    	
    	String loggedInEmailIdValue = (String) session.getAttribute("email");
 		DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
 		String empId = employeeRepository.findEmpidByEmail(loggedInEmailIdValue);

	     String loggedInFullName = fetchedEmpData.getFullName();
	     quotationDTO.setPrepared_by(loggedInFullName);
    	
        // Save Quotation User Details
        iAdd_Quotation.saveQuotationDetails(
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
            0,
            quotationDTO.getHonorifics1(),
            quotationDTO.getHonorifics2(),
            quotationDTO.getPrepared_by(),
            quotationDTO.getReviseReason(),
            quotationDTO.getEmpid(),
            1
        );
       
        // Fetch the saved quotation ONCE and extract the ID
        Add_Quotation_Entity savedQuotation = iAdd_Quotation.findByQid(quotationDTO.getQid());
        final Long quotationId = savedQuotation != null ? savedQuotation.getId() : null;

        if (quotationId != null) {
            System.out.println("Auto-generated Quotation ID: " + quotationId);
        } else {
            System.out.println("Quotation not found for QID: " + quotationDTO.getQid());
        } 
        
        // Iterate and Save Product Details
        quotationDTO.getProducts().forEach(productDTO -> {
        	  
        	 productDTO.setQuotationId(quotationId); // ✅ Set the ID here
        	 // productDTO.setQuotationId(quotationId); // Set quotationId from saved quotation
        	     
        	 // Debugging output
        	 System.out.println("Product DTO: " + productDTO.getpId()); // Print all details of productDTO including quotationId
        	    
          iAdd_Quotation.saveQuotationProduct(
                productDTO.getpId(),            // Assuming SlNo acts as Product ID
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
                productDTO.getQid(),
                productDTO.getQuotationId() 
            );
        });
    }
    
    
    @Transactional
    public void reviseQuotation(Add_Quotation_DTO quotationDTO, HttpSession session,String termsAndCondition) {
    	System.out.println("quotationDTO---"+quotationDTO);
    	
    	//    	String loggedInEmailIdValue = (String) session.getAttribute("email");
    	//    	DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
    	//      String loggedInFullName = fetchedEmpData.getFullName();
    	//      quotationDTO.setPrepared_by(loggedInFullName);
    	
        // Generate the correct next quotation ID
        String nextQuotationId = getNextQuotationId(quotationDTO.getQid());
        
        String quotationPreparedBy = iAdd_Quotation.getQuotationPreparedByDataBasedOnQId(quotationDTO.getQid());
        quotationDTO.setPrepared_by(quotationPreparedBy);
        
        iAdd_Quotation.deleteQuotationDataInQuotationTableAfterRevise(quotationDTO.getQid());
        iAdd_Quotation.deleteQuotationDataInProductTableAfterRevise(quotationDTO.getQid());
        
        // if review reason is "1DraftRevised1" then review=0,notification=1
        // else review=5,notification=3
        Integer reviewValue;
        Integer notificationVlaue;
        if("1DraftRevised1".equals(quotationDTO.getReviseReason())) {
        	System.out.println();
        	reviewValue = 0;
        	notificationVlaue = 1;// new Quotation
        } else {
        	reviewValue = 5;
        	notificationVlaue = 3;// new Quotation
        	quotationDTO.setQid(nextQuotationId); // Set updated QID
        }

        System.out.println("reviewValue---"+reviewValue);
        System.out.println("notificationVlaue---"+notificationVlaue);
        // Save quotation
        iAdd_Quotation.saveQuotationDetails(
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
            reviewValue,
            quotationDTO.getHonorifics1(),
            quotationDTO.getHonorifics2(),
            quotationDTO.getPrepared_by(),
            quotationDTO.getReviseReason(),
            quotationDTO.getEmpid(),
            notificationVlaue 
        );
        
     // Fetch the saved quotation ONCE and extract the ID
        Add_Quotation_Entity savedQuotation = iAdd_Quotation.findByQid(quotationDTO.getQid());
        final Long quotationId = savedQuotation != null ? savedQuotation.getId() : null;

        if (quotationId != null) {
            System.out.println("Auto-generatedRevised Quotation ID: " + quotationId);
        } else {
            System.out.println("QuotationRevised not found for QID: " + quotationDTO.getQid());
        } 
        // Save products with the updated QID
        quotationDTO.getProducts().forEach(productDTO -> {
        	 productDTO.setQuotationId(quotationId); // ✅ Set the ID here
         	// productDTO.setQuotationId(quotationId); // Set quotationId from saved quotation
         	    
         	    // Debugging output
         	    System.out.println("ProductRevised DTO: " + productDTO); // Print all details of productDTO including quotationId

        // Save products with the updated QID
   
            productDTO.setQid(quotationDTO.getQid());
            iAdd_Quotation.saveQuotationProduct(
                productDTO.getpId(),
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
                productDTO.getQid(),
                productDTO.getQuotationId()
            );
        });
        
        System.out.println("nextQuotationId-"+nextQuotationId);
        //save terms and condition with updated Q_id
        Terms_and_condition_Entity termsAndConditionObj = new Terms_and_condition_Entity();
        termsAndConditionObj.setTerms_and_condition_data(termsAndCondition);
        termsAndConditionObj.setQ_id(nextQuotationId);
        terms_and_condition_Service.insertTermsAndConditionData(termsAndConditionObj);
    }
    
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
    
    /*public String get_qid() {
        String lastQid = iAdd_Quotation.findLastQid();
        String returnedFY = getCustomCurrentFinancialYear(); // Now without 'F'
        
        if (lastQid != null && lastQid.matches("MSPL/Q/\\d{4}-\\d{2}/\\d{3}(_\\d+)?")) {
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
    }*/
    
    public String get_qid() {
        String lastQid = iAdd_Quotation.findLastQid();
        // System.out.println("Last QID from DB: " + lastQid);

        String returnedFY = getCustomCurrentFinancialYear(); // e.g., 2025-26
        // System.out.println("Current Financial Year: " + returnedFY);

        if (lastQid != null && lastQid.matches("MSPL/Q/\\d{4}-\\d{2}/\\d{3}(_\\d+)?")) {
            // System.out.println("Last QID matches pattern");

            String numericPart = lastQid.substring(lastQid.lastIndexOf("/") + 1);
            // System.out.println("Extracted numeric part before removing suffix: " + numericPart);

            if (numericPart.contains("_")) {
                numericPart = numericPart.substring(0, numericPart.indexOf("_"));
                // System.out.println("Numeric part after removing suffix: " + numericPart);
            }

            int lastNumber = Integer.parseInt(numericPart);
            // System.out.println("Last number extracted: " + lastNumber);

            String newQid = String.format("MSPL/Q/%s/%03d", returnedFY, lastNumber + 1);
            // System.out.println("Generated new QID: " + newQid);
            return newQid;
        }

        String newQidStart = String.format("MSPL/Q/%s/%03d", returnedFY, 100);
        // System.out.println("No valid last QID found or pattern mismatch. Starting from: " + newQidStart);
        return newQidStart;
    }

    public String getCustomCurrentFinancialYear() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        if (today.getMonthValue() < 3 || (today.getMonthValue() == 3 && today.getDayOfMonth() < 26)) {
            return String.format("%d-%02d", year - 1, year % 100);
        } else {
            return String.format("%d-%02d", year, (year + 1) % 100);
        }
    }

	
	public List<QuotationDTO> getAllQuotation() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWithProductsNative();
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
	            quotation.setReviseReason(row[23] != null ? row[23].toString() : "-");
	            quotation.setTerminate_reason(row[24] != null ? row[24].toString() : "-");
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
	
	
	public List<QuotationDTO> getAllQuotationForReview() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWithProductsNativeForReview();
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
	
	public List<QuotationDTO> getQuotationWonDetails() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationWonDetails();
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
	
	public List<QuotationDTO> getQuotationLossDetails() {
	    List<Object[]> results = iAdd_Quotation.fetchQuotationLossDetails();
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
	
	public QuotationDTO getQuotationById(String id) {
        List<Object[]> results = iAdd_Quotation.findQuotationDetailsById(id);
        String internalId = iAdd_Quotation.findInternalIdByQid(id);
        System.out.println("Internal DB ID for QID " + id + " = " + internalId);

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
            String quotationIdFromRow = row[25] != null ? row[25].toString() : "";
	        //System.out.println("pokkkkkkkkk "+ quotationIdFromRow);
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
            dto.setReview(row[26] != null ? Integer.parseInt(row[26].toString()) : 0);
            dto.setEmail_sent_status(row[27] != null ? row[27].toString() : "-");
            dto.setHonorifics1(row[28] != null ? row[28].toString() : "-");
            dto.setHonorifics2(row[29] != null ? row[29].toString() : "-");
            dto.setPrepared_by(row[30] != null ? row[30].toString() : "-");

            // If the quotation ID matches the internal ID, proceed with mapping the product details
            if (internalId.equals(quotationIdFromRow)) { // Map product details
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
	            product.setpId(row[31] != null ? row[31].toString() : "-");
	            products.add(product);
          }
        }

        dto.setProducts(products);
        return dto;
    }

	 
	    //this is calling when i submit the quotation after draft with reviewStatus = 1
	    //this also calling when i Approve the quotation after submite with reviewStatus = 2
	    //this also calling when i Terminate the quotation after submite with reviewStatus = 4
	 	public boolean updateReviewStatusForQuotation(int reviewStatus, String quotationId,String treminateReason) {
	 		
	 		System.out.println("updateReviewStatusForQuotation in service==="+reviewStatus);
	 		
	 		Integer notificationFlag = 0;
	 		if( reviewStatus == 1) {
	 			notificationFlag = 1;
	 		} else if(reviewStatus == 2) {
	 			notificationFlag = 2;
	 		} else if(reviewStatus == 4){
	 			notificationFlag = 4;
	 		}
	 		
	 		System.out.println("notification flag value before update=="+notificationFlag);
	 		int rowsAffected = iAdd_Quotation.update_ReviewStatus_ForQuotation(reviewStatus, quotationId,treminateReason,notificationFlag);
	        return rowsAffected > 0;
	        
	 	}
	 	
	 	public boolean updateEmailSentStatusForCustomer(String emailStatus, String quotationId) {
	 		int rowsAffected = iAdd_Quotation.update_EmailSentStatus_AfterReview(emailStatus, quotationId);
	        return rowsAffected > 0;
	 	}
	 	
	 	public boolean updateNotitification_StatusForCustomer(String quotationId) {
	 		int rowsAffected = iAdd_Quotation.update_NotificationFlag(quotationId);
	        return rowsAffected > 0;
	 	}
	 	
	 	public String retrieveEmailSentFlagStatusValue(String quotationId) {
	        return iAdd_Quotation.getEmailSentStatusFlagValue(quotationId);
	 	}
	 	
	 	public String retrieveReviewFlagStatusValue(String quotationId) {
	        return iAdd_Quotation.getReviewFlagValue(quotationId);
	 	}
	 	
	 	public fetchReportingManagerEmailByEmployeeEmail_Entity getReportingManagerEmailIdBasedOnEmpEmail(String employee_email) {
	 		return reportingManagerEmailByEmployeeEmail_Repo.getReportingMangersEmailId(employee_email);
	 	}
	 	
	 	public EmailDraftBodyMessage_Entity getRecentInsertedEmailDraftDetail() {
			return emailDraftBodyMessage_Repo.fetchRecentInsertedEmailDraftMessageDetail();
		}
	 	
	 	public void insertEmailDraftBodyToDB(EmailDraftBodyMessage_Entity emailDraftBodyMessage_Entity) {
	 		emailDraftBodyMessage_Repo.save(emailDraftBodyMessage_Entity);
	 	}
	 	
	 	public void sendEmailWithAttachment(String from, String to, String cc, String subject, String message, String password, byte[] pdfBytes, HttpSession session,String fileName) throws MessagingException, IOException {
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
	        	helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));
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
	 	
	 	
	 	public String getNextQuotationId(String baseQid) {
	 	    // Fetch all QIDs that start with baseQid
	 	    List<String> existingQuotationIds = iAdd_Quotation.checkTheNumberOfTimesExistingQuotationId(baseQid + "%");

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


	 	public void updateNotificationFlagValueByQID(String quotationId,Integer reviewStatus) {
	        try {
	        			System.out.println("quotationId ---- "+quotationId);
			 		 Optional<Add_Quotation_Entity> optionalQuotation = iAdd_Quotation.findAllByQid(quotationId);
			 		 
			 		 System.out.println("optionalQuotation.get() ---- "+optionalQuotation.get());
		
			         if (optionalQuotation.isPresent()) {
			             Add_Quotation_Entity quotation = optionalQuotation.get();
			             System.out.println("Quotation Found: " + quotation);
			             if(reviewStatus == 2) {
			            	 quotation.setNotification_flag(2);
			             }
			             else {
			            	 quotation.setNotification_flag(1);
			             }
			             
			             iAdd_Quotation.save(quotation);
			             System.out.println("Notification flag updated successfully.");
			         } else {
			             System.out.println("Quotation not found for ID: " + quotationId);
			         }
			     } catch(Exception e) {
			    	 System.err.println("Error updating notification flag for quotation ID: " + quotationId);
			            e.printStackTrace();
			     }
	 	 }


public void sendQuotationMail(String loggedEmpId, String qid , HttpSession session) throws MessagingException {

	 	    List<Object[]> results = employeeRepository.findTeamLeadAndCoByEmpId(loggedEmpId); // Ensure it returns List<Object[]>
	 	    
	 	    String loggedInEmailIdValue = (String) session.getAttribute("email");
	 		DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
		    String loggedInFullName = fetchedEmpData.getFullName();
		    String loggedInGenderPrefix = null, loggedInFullNameWithPrefix = null;
		    
		    loggedInGenderPrefix = "Male".equals(fetchedEmpData.getGender()) ? "Mr." : "Ms.";
		    loggedInFullNameWithPrefix = loggedInGenderPrefix+" "+loggedInFullName;
	 	    
	 	    QuotationDTO quotation = getQuotationById(qid);
	 	    String customerFullName = quotation.getHonorifics1()+" "+quotation.getCustomerName();
	 	    String quotePreparedDate = quotation.getDateTime();	 	    
	 	    LocalDateTime dateTime = LocalDateTime.parse(quotePreparedDate);
	 	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	 	    String formattedDate = dateTime.format(dateFormatter);
	 	    quotePreparedDate = formattedDate;
	 	     
	 	    String totalPriceData = null, teamLeadFullNameWithPrefix = null, teamCoFullNameWithPrefix = null, teamLeadEmail = null, teamCoemail = null;
	 	    if (quotation.getProducts() != null) {
	 	    	double totalSum = 0.00;

	 	       for (ProductDTO product : quotation.getProducts()) {
	 	           String totalStr = product.getTotal();
	 	           if (totalStr != null && !totalStr.equalsIgnoreCase("N/A")) {
	 	               try {
	 	                   totalSum += Double.parseDouble(totalStr);
	 	               } catch (NumberFormatException e) {
	 	                   System.err.println("Invalid total value: " + totalStr);
	 	               }
	 	           }
	 	       }
	 	      totalPriceData = String.format("%.2f", totalSum); 
	 	   }

	 	    if (results != null && !results.isEmpty()) {
	 	      

	 	        for (Object[] result : results) {
	 	            String teamLeadempId = result.length > 0 && result[0] != null ? result[0].toString().trim() : "";
	 	            String teamCoempId = result.length > 1 && result[1] != null ? result[1].toString().trim() : "";

	 	            if (!teamLeadempId.isEmpty()) {
	 	            	
	 	            	teamLeadEmail = employeeRepository.findEmailByEmpId(teamLeadempId);
	 	            	//String fullName = employeeRepository.findEmpidByEmpId(teamLeadempId);
	 	            	
	 	 	   	 		DisplayEmployessEntity fetchedEmpDataForTeamLead = employeeRepositoryWithDeptName.findByEmail(teamLeadEmail);
	 	 	   		    String teamLeadFullName = fetchedEmpDataForTeamLead.getFullName();
	 	 	   		    String teamLeadGenderPrefix = null;
	 	 	   		    
	 	 	   		    teamLeadGenderPrefix = "Male".equals(fetchedEmpDataForTeamLead.getGender()) ? "Mr." : "Ms.";
	 	 	   		    teamLeadFullNameWithPrefix = teamLeadGenderPrefix+" "+teamLeadFullName;
	 	            	
	 	            } else {
	 	                System.out.println("Team Lead Name is empty.");
	 	            }

	 	            if (!teamCoempId.isEmpty()) {
	 	            	teamCoemail = employeeRepository.findEmailByEmpId(teamCoempId);
	 	            	//String fullName = employeeRepository.findEmpidByEmpId(teamCoempId);
	 	            	
	 	            	DisplayEmployessEntity fetchedEmpDataForTeamCo = employeeRepositoryWithDeptName.findByEmail(teamCoemail);
	 	 	   		    String teamCoFullName = fetchedEmpDataForTeamCo.getFullName();
	 	 	   		    String teamCoGenderPrefix = null;
	 	 	   		    
	 	 	   		    teamCoGenderPrefix = "Male".equals(fetchedEmpDataForTeamCo.getGender()) ? "Mr." : "Ms.";
	 	 	   		    teamCoFullNameWithPrefix = teamCoGenderPrefix+" "+teamCoFullName;
	 	            	
	 	                //sendQuatationMailTo(email, teamCoFullNameWithPrefix);
	 	            } else {
	 	                System.out.println("Team Coordinator Name is empty.");
	 	            }

	 	            System.out.println("Team Lead Name: " + teamLeadempId);
	 	            System.out.println("Team Coordinator Name: " + teamCoempId);
	 	            
	 	           sendQuatationMailTo(teamLeadEmail, teamLeadFullNameWithPrefix, teamCoemail, teamCoFullNameWithPrefix, loggedInFullNameWithPrefix, 
	 	        		   customerFullName, quotePreparedDate, totalPriceData);
	 	            
	 	        }
	 	    } else {
	 	        System.out.println("No data found for empId: " + loggedEmpId);
	 	    }
	 	}

		private void sendQuatationMailTo(String teamLeadEmail, String teamLeadFullNameWithPrefix, String teamCoemail, 
				String teamCoFullNameWithPrefix, String loggedInFullNameWithPrefix, String customerFullName, String quotePreparedDate, 
				String totalPriceData) throws MessagingException {
			
				String reportManagerName = null; 
				
				if ((teamLeadEmail != null && !teamLeadEmail.isEmpty()) || (teamCoemail != null && !teamCoemail.isEmpty())) {
				    MimeMessage message = mailSender.createMimeMessage();
				    MimeMessageHelper helper = new MimeMessageHelper(message, true);
				    helper.setFrom("noreply@melangesystems.com");

				    if (teamLeadEmail != null && !teamLeadEmail.isEmpty()) {
				        helper.setTo(teamLeadEmail);
				        reportManagerName = teamLeadFullNameWithPrefix;

				        if (teamCoemail != null && !teamCoemail.isEmpty()) {
				            helper.setCc(teamCoemail);
				        }
				    } else {
				        helper.setTo(teamCoemail);
				        reportManagerName = teamCoFullNameWithPrefix;
				    }

				    helper.setSubject("New Sales Quote Submitted for Your Review");

				    helper.setText(
				        "<p>Dear " + reportManagerName + ",</p>" +
				        "<p>You have received a new sales quote from <b>" + loggedInFullNameWithPrefix +
				        "</b>. Kindly review the details below and approve or reject the request accordingly:</p>" +

				        "<p><b>Sales Request Details:</b></p>" +
				        "<ul>" +
				        "<li><b>Customer Name:</b> " + customerFullName + "</li>" +
				        "<li><b>Sales Quote Date:</b> " + quotePreparedDate + "</li>" +
				        "<li><b>Sales Quote Value:</b> " + totalPriceData + "</li>" +
				        "</ul>" +

				        "<p>You can approve or reject the request via the MSPL_CONNECT Application. Please take action at your earliest convenience.</p>" +
				        "<p>Regards,<br>" + loggedInFullNameWithPrefix + "</p>",
				        true
				    );

				    mailSender.send(message);
				}

			}


public String fetchLastQidBasedOnPreparedByName(HttpSession session) {
			
			String loggedInEmailIdValue = (String) session.getAttribute("email");
	 		DisplayEmployessEntity fetchedEmpData = employeeRepositoryWithDeptName.findByEmail(loggedInEmailIdValue);
		    String loggedInFullName = fetchedEmpData.getFullName();
			
			String lastQid = iAdd_Quotation.findLastQidBasedOnPreparedByName(loggedInFullName);
			return lastQid;
		}

		
		
}
