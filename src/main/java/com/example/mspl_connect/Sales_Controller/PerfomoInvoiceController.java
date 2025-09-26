package com.example.mspl_connect.Sales_Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Sales_Entity.PerformaInvoice;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoice;
import com.example.mspl_connect.Sales_Entity.PurchaseOrder;
import com.example.mspl_connect.Sales_Repository.PurchaseInvoiceRepository;
import com.example.mspl_connect.Sales_Service.PurchaseInvoiceService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/purchase-invoice")
public class PerfomoInvoiceController {
	
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;
     
    @Autowired
    private PurchaseInvoiceRepository purchaseInvoiceRepository;
    
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private EmployeeRepository employeeRepository;
    
    @PostMapping
    public ResponseEntity<?> saveInvoice(@RequestBody PurchaseInvoice invoice,HttpSession session) {
        try {
        	System.out.println("invoice in controller---"+invoice);
        	String loggedEmail = (String) session.getAttribute("email");
        	String empId = employeeRepository.findEmpidByEmail(loggedEmail);
        	DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
        	
        	String loggedAdminName = empDetailsByEmpId.getFullName();
        	
        	invoice.setCreated_by(empId);
        	PurchaseInvoice savedInvoice = purchaseInvoiceService.saveInvoice(invoice,empDetailsByEmpId);
            return ResponseEntity.ok(savedInvoice);
        } catch (Exception e) {
        	
        	// Print the full stack trace in backend
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving invoice: " + e.getMessage());
        }
    } // purchase-invoice
    
    @PostMapping("/performa-invoice")
    public ResponseEntity<?> savePerformaInvoice(@RequestBody PerformaInvoice perofrmainvoice,HttpSession session) {
        try {
        	System.out.println("performa invoice in controller---"+perofrmainvoice);
        	String loggedEmail = (String) session.getAttribute("email");
        	String empId = employeeRepository.findEmpidByEmail(loggedEmail);
        	DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
        	
        	perofrmainvoice.setCreated_by(empId);
        	PerformaInvoice savedInvoice = purchaseInvoiceService.savePerforma(perofrmainvoice,empDetailsByEmpId);
            return ResponseEntity.ok(savedInvoice);
        } catch (Exception e) {
        	// Print the full stack trace in backend
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving invoice: " + e.getMessage());
        }
    }
    
    @GetMapping("/next-invoice-id")
    public ResponseEntity<String> getNextInvoiceNumber() {
        String invoiceNumber = purchaseInvoiceService.getNextInvoiceNumber();
        return ResponseEntity.ok(invoiceNumber);
    }
    
    @GetMapping("/next-performainvoice-id")
    public ResponseEntity<String> getNextPerformaInvoiceNumber() {
        String performaInvoiceNumber = purchaseInvoiceService.getNextPerformaInvoiceNumber();
        return ResponseEntity.ok(performaInvoiceNumber);
    }
    
    @GetMapping("/getInvoice")
    public ResponseEntity<PurchaseInvoice> getPurchaseInvoiceDetails(@RequestParam String invoiceId) {
        
        PurchaseInvoice purchaseInvoice = purchaseInvoiceService.getInvoiceByInvoiceId(invoiceId);
        
        return ResponseEntity.ok(purchaseInvoice);
        
    }
    
    @GetMapping("/getPerformaInvoice")
    public ResponseEntity<PerformaInvoice> getPerformaInvoiceDetails(@RequestParam String invoiceId) {
        System.out.println("invoiceId in getPerform ainvoice"+invoiceId);
        PerformaInvoice purchaseInvoice = purchaseInvoiceService.getPerformaInvoiceByInvoiceId(invoiceId);
        
        return ResponseEntity.ok(purchaseInvoice);
        
    }
    
    /*@PostMapping("/update-approval")
    @Transactional
    public ResponseEntity<String> approveInvoice(@RequestBody Map<String, Object> payload) {
        
    	String invoiceId =  payload.get("invoiceId").toString();
        Integer approveByAc = Integer.valueOf(payload.get("approveByAc").toString());
        
        int updated = purchaseInvoiceRepository.updateApprovalStatus(invoiceId, approveByAc);
        if (updated > 0) {
            return ResponseEntity.ok("Updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        } 
        
    }*/
    
    @PostMapping(value = "/update-approval", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<String> approveInvoice(
            @RequestParam("invoiceId") String invoiceId,
            @RequestParam("approveByAc") Integer approveByAc,
            @RequestParam("invoicePdf") MultipartFile invoicePdf,
            HttpSession session) {

        try {
            // Sanitize invoiceId for filesystem use
            String sanitizedInvoiceId = invoiceId.replace("/", "-").replace("\\", "-");  // e.g. MSPL-IV-25-26-030

            // Set the flat folder path (you want a single flat folder, not nested)
            String directoryPath = "D:/Desktop/EmpDocs/invoice_MSPL-IV-25-26/";
    		String email = (String) session.getAttribute("email");
    		String empId = employeeRepository.findEmpidByEmail(email);
    		//System.out.println("1111"+empId);
    		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
    		//System.out.println("222empDetailsByEmpId="+empDetailsByEmpId);
    		
    		String loggedAdminFullName = empDetailsByEmpId.getFullName();
    		//System.out.println("loggedAdminFullName==="+loggedAdminFullName);
            // Define the final filename
            String fileName = "invoice_" + sanitizedInvoiceId + ".pdf";
            File destinationFile = new File(directoryPath + fileName);

            // Ensure the directory exists
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the uploaded file
            invoicePdf.transferTo(destinationFile);
            
            // Final saved path (for storing in DB)
            String filePathToStoreInDb = destinationFile.getAbsolutePath(); // or use a relative path if preferred
 
            // Update the database
            int updated = purchaseInvoiceRepository.updateApprovalStatus(invoiceId, approveByAc,filePathToStoreInDb);
            purchaseInvoiceService.sendinvoiceApprovedMail(invoiceId,loggedAdminFullName);
            if (updated > 0) {
                return ResponseEntity.ok("Invoice approved and PDF saved.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("PDF saving failed.");
        }
    }
    
    @GetMapping("/purchase-invoice/pdf/{invoiceId:.+}")
    public ResponseEntity<Resource> getInvoicePdf(@PathVariable("invoiceId") String invoiceId) {
    	
        // 1. Find the PurchaseInvoice entity by invoiceId (assumed you have a method)
        Optional<PurchaseInvoice> optionalInvoice = purchaseInvoiceRepository.findByInvoiceId(invoiceId);
        if (optionalInvoice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        PurchaseInvoice invoice = optionalInvoice.get();
        String pdfPath = invoice.getPdfPath();
        if (pdfPath == null || pdfPath.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            // 2. Load the file as a Resource
            Path file = Paths.get(pdfPath);
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // 3. Return the file with proper headers
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName().toString() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	/*
	 * @GetMapping("/purchase-invoice/pdf") public ResponseEntity<Resource>
	 * getInvoicePdf(@RequestParam("invoiceId") String invoiceId) { try { // Get
	 * file path from DB String filePath =
	 * purchaseInvoiceRepository.findPdfPathByInvoiceId(invoiceId); if (filePath ==
	 * null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); }
	 * 
	 * File file = new File(filePath); if (!file.exists()) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); }
	 * 
	 * // Create resource from file Path path = file.toPath(); Resource resource =
	 * new UrlResource(path.toUri());
	 * 
	 * return ResponseEntity.ok() .contentType(MediaType.APPLICATION_PDF)
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" +
	 * file.getName() + "\"") .body(resource);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); } }
	 */

    @GetMapping("/getPdfPathByInvoiceId")
    public String getPdfPathByInvoiceId(@RequestParam("invoiceId") String invoiceId) {
    	System.out.println("paaattthhh==="+purchaseInvoiceService.getPdfPathByInvoiceId(invoiceId));
    	return purchaseInvoiceService.getPdfPathByInvoiceId(invoiceId);
    }

    @GetMapping("/view-pdf")
    public ResponseEntity<Resource> viewPdf(@RequestParam("invoiceId") String invoiceId) {
    	System.out.println("invoiceId----"+invoiceId);
        String pdfPath = purchaseInvoiceService.getPdfPathByInvoiceId(invoiceId);
        //System.out.println("pdfPath---"+pdfPath);
        
        if (pdfPath == null || pdfPath.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path file = Paths.get(pdfPath);
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName().toString() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/check-quantity-valid")
    public ResponseEntity<Map<String, Object>> checkQuantityValid(
        @RequestParam String salesOrderId,
        @RequestParam String productId,
        @RequestParam int quantity,
        @RequestParam int salesIndexId,
        @RequestParam String partNo) {
    	
    	System.out.println("salesOrderIdddd"+salesOrderId);
    	System.out.println("productId"+productId);
    	
    	Map<String, Object> response = purchaseInvoiceService.validateQuantity(
    		        salesOrderId, productId, quantity, salesIndexId,partNo);
    	
    	return ResponseEntity.ok(response);
    		    
    }
    
    @GetMapping("/check-performaInvoicequantity-valid")
    public ResponseEntity<Map<String, Object>> checkPerformaInvoiceQuantityValid(
        @RequestParam String salesOrderId,
        @RequestParam String productId,
        @RequestParam int quantity,
        @RequestParam int salesIndexId,
        @RequestParam String partNo) {
    	
    	System.out.println("salesOrderIdddd"+salesOrderId);
    	System.out.println("productId"+productId);
    	
    	Map<String, Object> response = purchaseInvoiceService.validatePerformaInvoiceQuantity(
    		        salesOrderId, productId, quantity, salesIndexId,partNo);
    	
    	return ResponseEntity.ok(response);
    		    
    }

}

