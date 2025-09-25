package com.example.mspl_connect.Sales_Controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
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

import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Sales_Entity.DeliveryChallan;
import com.example.mspl_connect.Sales_Entity.DeliveryChallanDTO;
import com.example.mspl_connect.Sales_Entity.DeliveryChallanDetail;
import com.example.mspl_connect.Sales_Entity.GoodsDetailDTO;
import com.example.mspl_connect.Sales_Service.DeliveryChallanService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/delivery-challan")
public class DeliveryChallanController {

	  @Autowired
	    private DeliveryChallanService deliveryChallanService;

	  @Autowired
		private EmployeeRepository employeeRepository;

	  
	    @PostMapping("/save")
	    public ResponseEntity<String> saveDeliveryChallan(@RequestBody DeliveryChallanDTO deliveryChallanDTO, HttpSession session) {
	        // Create DeliveryChallan entity from the DTO
	        DeliveryChallan deliveryChallan = new DeliveryChallan();
	       
	        deliveryChallan.setPurchaseOrderId(deliveryChallanDTO.getPurchaseOrderId());
	        deliveryChallan.setQuotationId(deliveryChallanDTO.getQuotationId());
	        deliveryChallan.setSupplierAddress(deliveryChallanDTO.getSupplierAddress());
	        deliveryChallan.setBuyer(deliveryChallanDTO.getBuyer());
	        deliveryChallan.setReferenceNoDate(deliveryChallanDTO.getReferenceNoDate());
	        deliveryChallan.setDeliveryNoteNo(deliveryChallanDTO.getDeliveryNoteNo());
	        deliveryChallan.setDated(deliveryChallanDTO.getDated());
	        deliveryChallan.setReferenceNo(deliveryChallanDTO.getReferenceNo());
	        deliveryChallan.setOtherReferences(deliveryChallanDTO.getOtherReferences());
	        deliveryChallan.setTermsOfDelivery(deliveryChallanDTO.getTermsOfDelivery());
	        deliveryChallan.setPaymentTerms(deliveryChallanDTO.getPaymentTerms());
	        deliveryChallan.setBuyersOrderNo(deliveryChallanDTO.getBuyersOrderNo());
	        deliveryChallan.setBuyersOrderDate(deliveryChallanDTO.getBuyersOrderDate());
	        deliveryChallan.setRemarks(deliveryChallanDTO.getRemarks());
	        deliveryChallan.setCompanyPan(deliveryChallanDTO.getCompanyPan());
	        deliveryChallan.setBillingCompanyName(deliveryChallanDTO.getBillingCompanyName());
	        deliveryChallan.setRoundedGrandTotal(deliveryChallanDTO.getRoundedGrandTotal());
	        deliveryChallan.setDeliveryAddress(deliveryChallanDTO.getDeliveryAddress());
	        // Set the current date and time
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        deliveryChallan.setCreatedDate(currentDateTime);

	        // Fetch employee email from session
	        String email = (String) session.getAttribute("email");

	        if (email == null) {
	            // If session has expired or user is not logged in
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired or user not logged in.");
	        }

	        // Fetch employee ID using email
	        String empId = employeeRepository.findEmpidByEmail(email);
	        
	        // Set the createdByEmpId in the DeliveryChallan
	        deliveryChallan.setCreatedByEmpId(empId);

	        
	        // Convert goods and tax details into DeliveryChallanDetail entities
	        List<DeliveryChallanDetail> goodsDetails = new ArrayList<>();
	        for (GoodsDetailDTO goodsDTO : deliveryChallanDTO.getGoodsDetails()) {
	            DeliveryChallanDetail detail = new DeliveryChallanDetail();
	            detail.setDescription(goodsDTO.getDescription());
	            detail.setHsnSac(goodsDTO.getHsnSac());
	            detail.setQuantity(goodsDTO.getQuantity());
	            detail.setRate(goodsDTO.getRate());
	            detail.setPerUnit(goodsDTO.getPerUnit());
	            detail.setAmount(goodsDTO.getAmount());

	            detail.setCgstRate(goodsDTO.getCgstRate());
	            detail.setCgstAmount(goodsDTO.getCgstAmount());
	            detail.setSgstRate(goodsDTO.getSgstRate());
	            detail.setSgstAmount(goodsDTO.getSgstAmount());
	            detail.setIgstRate(goodsDTO.getIgstRate());
	            detail.setIgstAmount(goodsDTO.getIgstAmount());
	            detail.setTotalTaxAmount(goodsDTO.getTotalTaxAmount());
	            detail.setTotalWithTax(goodsDTO.getTotalWithTax());

	            goodsDetails.add(detail);
	        }

	        // Save Delivery Challan along with details
	        deliveryChallanService.saveDeliveryChallan(deliveryChallan, goodsDetails);

	        return ResponseEntity.ok("Delivery Challan saved successfully");
	    }
	
	    
	    
	    @GetMapping("/getDeliveryChallan/{id}")
	    public ResponseEntity<DeliveryChallanDTO> getDeliveryChallan(@PathVariable Long id) {
	    	System.out.println("ppppp"+ id);
	        // Fetch the delivery challan from the database using the ID
	        DeliveryChallanDTO deliveryChallan = deliveryChallanService.getDeliveryChallanById(id);
	       // System.out.println("ppppp"+ deliveryChallan);
	        return ResponseEntity.ok(deliveryChallan);
	    }

	    
	    @GetMapping("/getDeliveryChallannew/{id}")
	    public ResponseEntity<DeliveryChallanDTO> getDeliveryChallannew(@PathVariable Long id) {
	    	System.out.println("ppppp"+ id);
	        // Fetch the delivery challan from the database using the ID
	        DeliveryChallanDTO deliveryChallan = deliveryChallanService.getDeliveryChallanById(id);
	        System.out.println("ppppp"+ deliveryChallan);
	        return ResponseEntity.ok(deliveryChallan);
	    }
	    
	    
	    
	    @GetMapping("/api/delivery-challan/view-pdf")
	    public ResponseEntity<byte[]> viewPDF(@RequestParam Long dcId) {
	        System.out.println("dcid " + dcId);

	        DeliveryChallanDTO dto = deliveryChallanService.getDeliveryChallanById(dcId);
	        if (dto == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            //deliveryChallanService.generatePdf(dto, outputStream);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDisposition(ContentDisposition.inline().filename("delivery-challan.pdf").build());

	            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

	    @GetMapping("/api/delivery-challan/download-pdf")
	    public ResponseEntity<byte[]> downloadPDF(@RequestParam Long dcId) {
	        DeliveryChallanDTO dto = deliveryChallanService.getDeliveryChallanById(dcId);
	        if (dto == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            // deliveryChallanService.generatePdf(dto, outputStream);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDisposition(ContentDisposition.attachment().filename("DeliveryChallan_" + dcId + ".pdf").build());

	            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

}
