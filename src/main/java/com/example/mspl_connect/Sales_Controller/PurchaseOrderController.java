package com.example.mspl_connect.Sales_Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Sales_Entity.PurchaseOrder;
import com.example.mspl_connect.Sales_Repository.PurchaseOrderRepository;
import com.example.mspl_connect.Sales_Service.PurchaseOrderService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PurchaseOrderController {

	 @Autowired
     private PurchaseOrderService purchaseOrderService;

	 @Autowired
	 private PurchaseOrderRepository purchaseOrderRepository;

		@Autowired
		private EmployeeRepository employeeRepository;

	  @PostMapping("/purchase-orders")
	    public ResponseEntity<?> createPurchaseOrder(@RequestBody PurchaseOrder order, HttpSession session) {
		    
		    String email = (String) session.getAttribute("email");
		  	
		    if (email == null) {
		        // If session has expired or user is not logged in
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired or user not logged in.");
		    }
		    
		    // Fetch employee ID using email
		    String empId = employeeRepository.findEmpidByEmail(email);
		    // Set the createdByEmpId in the order
		    
		    order.setCreatedByEmpId(empId);
		    // Set the created date and time
		    // order.setCreatedDateTime(LocalDateTime.now());
		    
	    	System.out.println("orderrrrr-"+order);
	        PurchaseOrder savedOrder = purchaseOrderService.savePurchaseOrder(order);
	        return ResponseEntity.ok(savedOrder);
	         
	    }

	 
	  @GetMapping("/generate-po-id")
	  public ResponseEntity<String> generatePoId() {
	      LocalDate now = LocalDate.now();
	      int currentYear = now.getYear();
	      int currentMonth = now.getMonthValue();

	      int fyStart = currentMonth >= 4 ? currentYear : currentYear - 1;
	      int fyEnd = fyStart + 1;
	      //String shortFy = String.format("%02d-%02d", fyStart % 100, fyEnd % 100);
	      String shortFy = String.format("%d-%02d", fyStart, fyEnd % 100);

	      // Get the latest PO ID for this financial year
	      String lastPoId = purchaseOrderRepository.findLastPoId(shortFy);

	      int nextSequence = 1; // default if nothing found
	      if (lastPoId != null && !lastPoId.isEmpty()) {
	          String[] parts = lastPoId.split("/");
	          if (parts.length == 4) {
	              try {
	                  nextSequence = Integer.parseInt(parts[3]) + 1;
	              } catch (NumberFormatException e) {
	                  // handle parse error if needed
	              }
	          }
	      }

	      String sequence = String.format("%03d", nextSequence);
	      String generatedId = "MSPL/SO/" + shortFy + "/" + sequence;

	      return ResponseEntity.ok(generatedId);
	  }

	  /*@GetMapping("/viewPurchaseOrder/{purchaseId}")
	  @ResponseBody
	  public PurchaseOrder viewPurchaseOrder(@PathVariable String purchaseId) {
	      return purchaseOrderService.getOrderByPurchaseId(purchaseId);
	  }*/

	  @GetMapping("/getPurchaseOrderDetails/{purchaseId}")
	  public ResponseEntity<PurchaseOrder> getPurchaseOrderDetails(@PathVariable Long purchaseId) {
	      PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseId);
	      return ResponseEntity.ok(purchaseOrder);
	  }
	  
	  @GetMapping("/getPurchaseOrderDetailsnew/{purchaseId}")
	  public ResponseEntity<PurchaseOrder> getPurchaseOrderDetailsnewww(@PathVariable Long purchaseId) {
	      PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseId);
	      return ResponseEntity.ok(purchaseOrder);
	  }

}