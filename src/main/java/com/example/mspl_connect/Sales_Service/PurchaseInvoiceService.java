package com.example.mspl_connect.Sales_Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Sales_Entity.PerformaInvoice;
import com.example.mspl_connect.Sales_Entity.PerformaInvoiceDTO;
import com.example.mspl_connect.Sales_Entity.PerformaInvoiceItem;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoice;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoiceDTO;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoiceItem;
import com.example.mspl_connect.Sales_Entity.PurchaseOrder;
import com.example.mspl_connect.Sales_Repository.PerformaInvoiceItemRepo;
import com.example.mspl_connect.Sales_Repository.PerformaInvoiceRepo;
import com.example.mspl_connect.Sales_Repository.PurchaseInvoiceRepository;
import com.example.mspl_connect.Sales_Repository.PurchaseOrderItemRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.ZoneId;


@Service
public class PurchaseInvoiceService {

    @Autowired
    private PurchaseInvoiceRepository purchaseInvoiceRepository;
    
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository; 
    
    @Autowired
    private JavaMailSender mailSender;
    
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private PerformaInvoiceRepo performaInvoiceRepo;
	
	@Autowired
	private PerformaInvoiceItemRepo performaInvoiceItemRepo;

    
    /*public PurchaseInvoice saveInvoice(PurchaseInvoice invoice) {
    	// System.out.println("invoice---"+invoice);
        for (PurchaseInvoiceItem item : invoice.getItems()) {
        	item.setSale_order_id(invoice.getPurchaseId());
            item.setPurchaseInvoice(invoice); // set the foreign key
        }
        
        return purchaseInvoiceRepository.save(invoice); // saves both invoice and items
    }*/
    
    public PurchaseInvoice saveInvoice(PurchaseInvoice invoice,DisplayEmployessEntity loggedAdminName) {
        for (PurchaseInvoiceItem item : invoice.getItems()) {
            item.setSale_order_id(invoice.getPurchaseId());
            item.setPurchaseInvoice(invoice); // set the foreign key
        }

        PurchaseInvoice savedInvoice = purchaseInvoiceRepository.save(invoice); // saves both invoice and items

        // Now send the invoice email — use the associated order object
        //PurchaseOrder order = savedInvoice.getPurchaseOrder(); // assuming this relation exists
        if (invoice != null) {
            sendInvoiceEmail(invoice,loggedAdminName);
        }

        return savedInvoice;
    }

	public PerformaInvoice savePerforma(PerformaInvoice perofrmainvoice, DisplayEmployessEntity empDetailsByEmpId) {
		
		for (PerformaInvoiceItem item : perofrmainvoice.getItems()) {
            item.setSale_order_id(perofrmainvoice.getPurchaseId());
            item.setPerformaInvoice(perofrmainvoice); // set the foreign key
        } 
		
        PerformaInvoice savedInvoice = performaInvoiceRepo.save(perofrmainvoice); // saves both invoice and items

        return savedInvoice;
	}
    
    private void sendInvoiceEmail(PurchaseInvoice invoice,DisplayEmployessEntity loggedAdminDetails) {
        try {
        	
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // fix encoding
            // `true` enables multipart (attachments)
            String fromEmail = "noreply@melangesystems.com";
            String toEmail = "satheesh.c@melangesystems.com";
           // String toEmail = "pavan.lingaraju@melangesystems.com";
            
            String toAddressFullName = employeeWitFullDetailes.getFullNameByEmailId(toEmail);
            
            String loggedAdminName = loggedAdminDetails.getFullName();
            String customerName = invoice.getCustName();
            String customerHonorifics = invoice.getCustHonorifics();
            String invoiceCreatedDate = invoice.getCreatedDateTime();
            
            String formattedDate = "";
            
            try {
                Instant instant = Instant.parse(invoiceCreatedDate);
                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Kolkata")); // Change zone if needed
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                formattedDate = zonedDateTime.format(formatter);
            } catch (Exception e) {
                formattedDate = invoiceCreatedDate; // fallback in case of error
            }
            
            Double netValue = invoice.getNetAmt();
            
            helper.setFrom(fromEmail); // Configure the "From" email address
            helper.setTo(toEmail); // Or use: order.getCreatedBy().getEmail()
            helper.setSubject(" Invoice Approval – "+ customerName);
            
            String loggedInGenderPrefix = null;
            loggedInGenderPrefix = "Male".equals(loggedAdminDetails.getGender()) ? "Mr." : "Ms.";
            
            String htmlContent =
            	    "<p>Dear " + toAddressFullName + ",</p>" +
            	    "<p>You have received a new invoice submission from <strong>" + loggedInGenderPrefix + " " + loggedAdminName + "</strong>. Kindly review the details below and take appropriate action.</p>" +

            	    "<p><strong>Invoice Details:</strong></p>" +
            	    "<ul>" +
            	        "<li><strong>Customer Name:</strong> " + customerHonorifics + " " + customerName + ".</li>" +
            	        "<li><strong>Invoice Date:</strong> " + formattedDate + ".</li>" +
            	        "<li><strong>Invoice Value:</strong> ₹" + String.format("%.2f", netValue) + "</li>" +
            	    "</ul>" +

            	    "<p>Please approve or reject the invoice request at your earliest convenience via the <strong>MSPL_CONNECT</strong> application.</p>" +

            	    "<p>Thank you for your prompt attention to this matter.</p>" +
            	    "<br>" +
            	    "<p>Best regards,<br>" +
            	    loggedAdminName + "</p>";

            helper.setText(htmlContent, true); // `true` for HTML

            mailSender.send(message);
            System.out.println("HTML email sent successfully.");
            
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
        
    }

	public List<PurchaseInvoiceDTO> getAllInvoice() {
		
		List<PurchaseInvoice> invoiceEntities = purchaseInvoiceRepository.findAllByOrderByIdDesc();
		
		List<PurchaseInvoiceDTO> invoiceDTOs = invoiceEntities.stream().map(invoice -> {
	        PurchaseInvoiceDTO dto = new PurchaseInvoiceDTO();
	        dto.setId(invoice.getId());
	        dto.setPoNumber(invoice.getPoNumber());
	        dto.setPoDate(invoice.getPoDate());
	        dto.setVendorCode(invoice.getVendorCode());
	        dto.setQuotationId(invoice.getQuotationId());
	        dto.setBillingAddress(invoice.getBillingAddress());
	        dto.setShippingAddress(invoice.getShippingAddress());
	        dto.setCustHonorifics(invoice.getCustHonorifics());
	        dto.setCustName(invoice.getCustName());
	        dto.setCusLeagalName(invoice.getCusLeagalName());
	        dto.setGstNo(invoice.getGstNo());
	        dto.setOrderMehtod(invoice.getOrderMehtod());
	        dto.setContactHonorifics(invoice.getContactHonorifics());
	        dto.setContPersonName(invoice.getContPersonName());
	        dto.setDesignation(invoice.getDesignation());
	        dto.setMobileNo(invoice.getMobileNo());
	        dto.setEmail(invoice.getEmail());
	        dto.setTermsAndConditions(invoice.getTermsAndConditions());
	        dto.setModeOfPayment(invoice.getModeOfPayment());
	        dto.setTermsOfDelivery(invoice.getTermsOfDelivery());
	        dto.setPurchaseId(invoice.getPurchaseId());
	        dto.setCreatedDateTime(invoice.getCreatedDateTime());
	        dto.setInvoice_id(invoice.getInvoice_id());
	        return dto;
	    }).collect(Collectors.toList());
		return invoiceDTOs;
	}
	
	public List<PerformaInvoiceDTO> getAllPerformaInvoice() {
		
		List<PerformaInvoice> performaInvoiceEntities = performaInvoiceRepo.findAllByOrderByIdDesc();
		
		List<PerformaInvoiceDTO> performInvoiceDTOs = performaInvoiceEntities.stream().map(invoice -> {
			PerformaInvoiceDTO dto = new PerformaInvoiceDTO();
	        dto.setId(invoice.getId());
	        dto.setPoNumber(invoice.getPoNumber());
	        dto.setPoDate(invoice.getPoDate());
	        dto.setVendorCode(invoice.getVendorCode());
	        dto.setQuotationId(invoice.getQuotationId());
	        dto.setBillingAddress(invoice.getBillingAddress());
	        dto.setShippingAddress(invoice.getShippingAddress());
	        dto.setCustHonorifics(invoice.getCustHonorifics());
	        dto.setCustName(invoice.getCustName());
	        dto.setCusLeagalName(invoice.getCusLeagalName());
	        dto.setGstNo(invoice.getGstNo());
	        dto.setOrderMehtod(invoice.getOrderMehtod());
	        dto.setContactHonorifics(invoice.getContactHonorifics());
	        dto.setContPersonName(invoice.getContPersonName());
	        dto.setDesignation(invoice.getDesignation());
	        dto.setMobileNo(invoice.getMobileNo());
	        dto.setEmail(invoice.getEmail());
	        dto.setTermsAndConditions(invoice.getTermsAndConditions());
	        dto.setModeOfPayment(invoice.getModeOfPayment());
	        dto.setTermsOfDelivery(invoice.getTermsOfDelivery());
	        dto.setPurchaseId(invoice.getPurchaseId());
	        dto.setCreatedDateTime(invoice.getCreatedDateTime());
	        dto.setInvoice_id(invoice.getInvoice_id());
	        return dto;
	    }).collect(Collectors.toList());
		return performInvoiceDTOs;
	}
	
	public String getNextInvoiceNumber() {
	    Long nextId = purchaseInvoiceRepository.findTopByOrderByIdDesc()
	                    .map(PurchaseInvoice::getId)
	                    .orElse(0L);
	    nextId += 1;

	    // Get current date
	    LocalDate today = LocalDate.now();

	    int yearStart, yearEnd;

	    if (today.getMonthValue() >= 4) {
	        yearStart = today.getYear();           // full year
	        yearEnd = (today.getYear() + 1) % 100;  // full year
	    } else {
	        yearStart = today.getYear() - 1;
	        yearEnd = today.getYear() % 100;
	    }

	    String financialYear = String.format("%d-%02d", yearStart, yearEnd);
	    return String.format("MSPL/INV/%s/%03d", financialYear, nextId);
	}
	
	public String getNextPerformaInvoiceNumber() {
		
		System.out.println("hiiii==getNextPerformaInvoiceNumber");
	    Long nextId = performaInvoiceRepo.findTopByOrderByIdDesc()
	                    .map(PerformaInvoice::getId)
	                    .orElse(0L);
	    System.out.println("nextId---"+nextId);
	    nextId += 1;

	    // Get current date
	    LocalDate today = LocalDate.now();

	    int yearStart, yearEnd;

	    if (today.getMonthValue() >= 4) {
	        yearStart = today.getYear();           // full year
	        yearEnd = (today.getYear() + 1) % 100;  // full year
	    } else {
	        yearStart = today.getYear() - 1;
	        yearEnd = today.getYear() % 100;
	    }

	    String financialYear = String.format("%d-%02d", yearStart, yearEnd);
	    
	    return String.format("MSPL/PI/%s/%03d", financialYear, nextId);
	    
	}
	
	/*public PurchaseInvoice getInvoiceByInvoiceId(String invoiceId) {
	    return purchaseInvoiceRepository.findByInvoiceId(invoiceId)
	            .orElseThrow(() -> new RuntimeException("Invoice not found with invoice_id: " + invoiceId));
	}*/
	
	public PurchaseInvoice getInvoiceByInvoiceId(String invoiceId) {
		PurchaseInvoice invoice = purchaseInvoiceRepository.findByInvoiceId(invoiceId)
	            .orElseThrow(() -> new RuntimeException("Invoice not found with invoice_id: " + invoiceId));

		 // Convert date format
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    
	    String formattedDate = LocalDate.parse(invoice.getDate_of_supply(), inputFormatter)
	                                     .format(outputFormatter);

	    invoice.setDate_of_supply(formattedDate);
	    return invoice;
	}
	
	public PerformaInvoice getPerformaInvoiceByInvoiceId(String performanvoiceId) {
		
		System.out.println("performanvoiceId---"+performanvoiceId);
		PerformaInvoice performaInvoice = performaInvoiceRepo.findByInvoiceId(performanvoiceId)
	            .orElseThrow(() -> new RuntimeException("Invoice not found with invoice_id: " + performanvoiceId));
		
		System.out.println("hiiiii"+performaInvoice);
		 // Convert date format
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    
	    String formattedDate = LocalDate.parse(performaInvoice.getDate_of_supply(), inputFormatter)
	                                     .format(outputFormatter);

	    performaInvoice.setDate_of_supply(formattedDate);
	    
	    System.out.println("hiiiii"+performaInvoice);
	    return performaInvoice;
	}
	
	public String getPdfPathByInvoiceId(String invoiceId) {
		return purchaseInvoiceRepository.getPdfPathByInvoiceId(invoiceId);
	}

	/*public Boolean checkIfQuantityGenerated(String salesOrderId, String productId,Integer enteredQuantity,Integer salesIndexId) {
		
        Integer invoiceGeneratedQuantity = purchaseInvoiceRepository.getQuantityByPIdAndSalesOrderID(salesOrderId,productId);
        Integer saleOrderQuantity = purchaseOrderItemRepository.getQuantityBySaleId(salesIndexId,productId);
        
        System.out.println("invoiceGeneratedQuantity==="+  invoiceGeneratedQuantity);
        System.out.println("saleOrderQuantity==="+  saleOrderQuantity);
        
        int remainingQty = saleOrderQuantity - invoiceGeneratedQuantity;

        return enteredQuantity <= remainingQty;
		
	}*/
	
	public Map<String, Object> validateQuantity(
		    String salesOrderId, String p_Id, int enteredQuantity, int salesIndexId,String partNo) {
			
		    Map<String, Object> response = new HashMap<>();
		    Integer invoiceGeneratedQuantity = purchaseInvoiceRepository
		        .getQuantityByPIdAndSalesOrderID(salesOrderId, p_Id);
		    
		    Integer saleOrderQuantity = purchaseOrderItemRepository
		        .getQuantityBySaleId(salesIndexId, p_Id,partNo);
		    
		    // Set defaults if null
		    int generatedQty = invoiceGeneratedQuantity != null ? invoiceGeneratedQuantity : 0;
		    int orderQty = saleOrderQuantity != null ? saleOrderQuantity : 0;
		    
		    int remainingQty = orderQty - generatedQty;
		    boolean isValid = enteredQuantity <= remainingQty;
		    
		    response.put("valid", isValid);
		    response.put("generatedQty", generatedQty); // ✅ Send generatedQty
		    response.put("orderQty", orderQty); // ✅ Send orderQty
		    
		    if (!isValid) {
		        response.put("message", "Quantity exceeded. Only " + remainingQty + " quantity(s) available.");
		    }
		    return response;
		}
	
	public Map<String, Object> validatePerformaInvoiceQuantity(
		    String salesOrderId, String p_Id, int enteredQuantity, int salesIndexId,String partNo) {
			
		    Map<String, Object> response = new HashMap<>();
		    Integer invoiceGeneratedQuantity = performaInvoiceRepo
		        .getQuantityByPIdAndSalesOrderID(salesOrderId, p_Id);
		    
		    Integer saleOrderQuantity = purchaseOrderItemRepository
			        .getQuantityBySaleId(salesIndexId, p_Id,partNo);
		    
		    // Set defaults if null
		    int generatedQty = invoiceGeneratedQuantity != null ? invoiceGeneratedQuantity : 0;
		    int orderQty = saleOrderQuantity != null ? saleOrderQuantity : 0;
		    
		    int remainingQty = orderQty - generatedQty;
		    boolean isValid = enteredQuantity <= remainingQty;
		    
		    response.put("valid", isValid);
		    response.put("generatedQty", generatedQty); // ✅ Send generatedQty
		    response.put("orderQty", orderQty); // ✅ Send orderQty
		    
		    if (!isValid) {
		        response.put("message", "Quantity exceeded. Only " + remainingQty + " quantity(s) available.");
		    }
		    return response;
		}

	public void sendinvoiceApprovedMail(String invoiceId,String loggedAdminFullName) {
		 try {
	        	//fetch invoice By invoiceId
			    Optional<PurchaseInvoice> invoiceOpt = purchaseInvoiceRepository.findByInvoiceId(invoiceId);
			    PurchaseInvoice invoice = invoiceOpt.orElseThrow(() -> new RuntimeException("Invoice not found"));
			     		 
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // fix encoding
	             
	            String customerName = invoice.getCustName();
	            String invoiceCreatedBy = invoice.getCreated_by();
	             
	        	DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(invoiceCreatedBy);
	        	 
	        	//String invoiceCreated = empDetailsByEmpId.getGender();
	        	String createdBy_honorifics = null;
	        	createdBy_honorifics = "Male".equals(empDetailsByEmpId.getGender()) ? "Mr." : "Ms.";	
	        	String createdByName = empDetailsByEmpId.getFullName();
	        	String createdByMail = empDetailsByEmpId.getEmail();
	        	 
	            // `true` enables multipart (attachments)
	            String fromEmail = "noreply@melangesystems.com";
	            String toEmail = createdByMail;
	        	 
	        	String invoiceCreatedDate = invoice.getCreatedDateTime();
	        	String formattedDate = "";
	        	 
	        	Double netValue = invoice.getNetAmt();	        	
	        	 
	             try {
	                 Instant instant = Instant.parse(invoiceCreatedDate);
	                 ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Kolkata")); // Change zone if needed
	                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                 formattedDate = zonedDateTime.format(formatter);
	             } catch (Exception e) {
	                 formattedDate = invoiceCreatedDate; // fallback in case of error
	             }
	            
	            helper.setFrom(fromEmail); // Configure the "From" email address
	            helper.setTo(toEmail); // Or use: order.getCreatedBy().getEmail()
	            helper.setSubject(" Invoice Approved – "+ customerName); 
	            
	            // Set multiple CC recipients
	            String[] ccEmails = {"sales@melangesystems.com", "store@melangesystems.com"}; 
	           // String[] ccEmails = {"premchand.s@melangesystems.com"}; 
	             
	            helper.setCc(ccEmails);
	            
	            String htmlContent = 
	            		"<p>Dear "+ createdBy_honorifics +" "+createdByName+" </p>"
	            		+ "I have reviewed the invoice submitted by <strong>"+ createdBy_honorifics +" "+createdByName + "</strong>for " +customerName + " dated " + formattedDate +" with a value of ₹"+String.format("%.2f", netValue) 
	            		+ "<br><br><br><p>I hereby approved the invoice for further processing.</p>"
	            		+ "<br><br>"
	            		+ "<p>best Regards,<br>"
	            		+ loggedAdminFullName;
	            
	            helper.setText(htmlContent, true); // `true` for HTML
	            
	            mailSender.send(message);
	            System.out.println("HTML email sent successfully.");
	            
	        } catch (Exception e) {
	            System.err.println("Failed to send email: " + e.getMessage());
	        }		
	}


}

