package com.example.mspl_connect.Sales_Controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Sales_DTO.Add_DC_DTO;
import com.example.mspl_connect.Sales_DTO.Add_DC_DTO.ProductDTO;
import com.example.mspl_connect.Sales_DTO.Add_Quotation_DTO;
import com.example.mspl_connect.Sales_DTO.DeliveryChallanDTO;
import com.example.mspl_connect.Sales_DTO.QuotationDTO;
import com.example.mspl_connect.Sales_Entity.Add_Quotation_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;
import com.example.mspl_connect.Sales_Entity.DeliveryChallan;
import com.example.mspl_connect.Sales_Entity.PerformaInvoiceDTO;
import com.example.mspl_connect.Sales_Entity.PurchaseInvoiceDTO;
import com.example.mspl_connect.Sales_Entity.PurchaseOrder;
import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;
import com.example.mspl_connect.Sales_Repository.DeliveryChallanRepository;
import com.example.mspl_connect.Sales_Repository.IAdd_Quotation;
import com.example.mspl_connect.Sales_Repository.PurchaseOrderRepository;
import com.example.mspl_connect.Sales_Service.Add_DC_Service;
import com.example.mspl_connect.Sales_Service.Add_Product_Service;
import com.example.mspl_connect.Sales_Service.Add_Quotation_Service;
import com.example.mspl_connect.Sales_Service.Add_Vendors_Service;
import com.example.mspl_connect.Sales_Service.PurchaseInvoiceService;
import com.example.mspl_connect.Sales_Service.PurchaseOrderService;
import com.example.mspl_connect.Sales_Service.Terms_and_condition_Service;

import jakarta.servlet.http.HttpSession;

@Controller
public class Add_Quotation_Controller {
	
	
	 @Autowired
	 private Add_Quotation_Service quotationService;
	 
	 @Autowired
	 private Add_Product_Service product_Service; 
	 
	 @Autowired
	 private Add_Vendors_Service add_Vendors_Service;
	 
	 @Autowired
	 private Add_Product_Service add_Product_Service;
	 
	 @Autowired
	 private Terms_and_condition_Service terms_and_condition_Service;
	 
	 @Autowired
	 private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
	 
	 @Autowired
	 private Add_DC_Service add_DC_Service;
	 
	 @Autowired
	 private IAdd_Quotation iadd_Quotation;
		
	 @Autowired
	 private Add_Quotation_Service add_Quotation_Service;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	 
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;
	
    @Autowired
    private DeliveryChallanRepository deliveryChallanRepository;
    
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
    
	@GetMapping("/get_qid")
	@ResponseBody
	public String getpid() {
		return quotationService.get_qid(); 
	}
	 
	 @GetMapping("/get_pname")
		@ResponseBody
		public List<String> getpname() {
			return product_Service.get_pname(); 
		}
	 
	 @GetMapping("/get_cname")
		@ResponseBody
		public List<String> getcname() {
		 System.out.println(add_Vendors_Service.get_cname());
			return add_Vendors_Service.get_cname(); 
		}

	 @PostMapping("/quotation/save")
	 public ResponseEntity<String> saveQuotation(@RequestBody Add_Quotation_DTO quotationDTO, @RequestParam String termsAndConditionValue, HttpSession session) {
	     try {
	    	 System.out.println("Received Quotation Data: " + quotationDTO.getQid());
	    	 
	    	 String emailid = (String) session.getAttribute("email");
	 		 String empid = employeeRepository.findEmpidByEmail(emailid);
	    	 String empName = employeeRepository.findEmpidByEmpId(empid);
	    	 
	    	 // Debugging: Print all products received
	         if (quotationDTO.getProducts() != null) {
	             for (Add_Quotation_DTO.ProductDTO product : quotationDTO.getProducts()) {
	                 System.out.println("Product: " + product.getProductName());
	                 System.out.println("Total Price: " + product.getTotal()); // Ensure this is correct
	                 System.out.println("Total Price: " + product.getTotal_with_gst()); 
	                 System.out.println("getPId: " + product.getpId()); 
	             }
	         }
	         
	         Terms_and_condition_Entity termsAndConditionObj = new Terms_and_condition_Entity();
	         termsAndConditionObj.setTerms_and_condition_data(termsAndConditionValue);
	         
	         // save terms and conditions
	         termsAndConditionObj.setQ_id(quotationDTO.getQid());
	         terms_and_condition_Service.insertTermsAndConditionData(termsAndConditionObj);
	         
	         // save Quotation
	         quotationDTO.setReviseReason(null);
	         quotationDTO.setEmpid(empid);
	         
	         System.out.println("quotationDTO in controller === "+ quotationDTO);
	         
	         quotationService.saveQuotation(quotationDTO, session);
	         return ResponseEntity.ok("Quotation saved successfully!");
	         
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save quotation: " + e.getMessage());
	     }
	 }
	 
	 @PostMapping("/reviseQuotation/save")
	 public ResponseEntity<String> saveReviseQuotation(@RequestBody Add_Quotation_DTO quotationDTO, @RequestParam String termsAndConditionValue, HttpSession session) {
	     try {
	    	 System.out.println("Received Quotation Data: " + quotationDTO.getQid());
	    	 
	    	 //List<String> theNumberOfTimesExistingQuotationId = quotationService.getTheNumberOfTimesExistingQuotationId(quotationDTO.getQid());
	    	 //int quotationIdExistingCount = theNumberOfTimesExistingQuotationId.size();
	    	 
	    	 //String baseQuotationId = quotationDTO.getQid();
	         //String nextQuotationId = quotationService.getNextQuotationId(baseQuotationId);
	         //quotationDTO.setQid(nextQuotationId);  // Update DTO with new ID
	    	 
	        /* Terms_and_condition_Entity termsAndConditionObj = new Terms_and_condition_Entity();
	         termsAndConditionObj.setTerms_and_condition_data(termsAndConditionValue);
	         termsAndConditionObj.setQ_id(quotationDTO.getQid());
	         terms_and_condition_Service.insertTermsAndConditionData(termsAndConditionObj);*/
	         
	         quotationService.reviseQuotation(quotationDTO, session,termsAndConditionValue);
	         return ResponseEntity.ok("Quotation revised successfully!");
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save quotation: " + e.getMessage());
	     }
	 }
	 
	 @PostMapping("/updateReviewStatusForQuotationReview")
		public ResponseEntity<String> toggReviewStatus(@RequestParam int reviewStatus, @RequestParam String quotationId,@RequestParam(required = false) String treminateReason ,HttpSession session) {
		    try {		    	
		    	
		    	String loggedEmpId = (String) session.getAttribute("loggedAdminEmpId"); 
		    	
		    	System.out.println("reviewStatus---------"+reviewStatus);
		    	
		    	// Normalize treminateReason: convert blank strings to null
		        if (treminateReason != null && treminateReason.trim().isEmpty()) {
		            treminateReason = null;
		        }
		    	
		        boolean isStatusChanged = quotationService.updateReviewStatusForQuotation(reviewStatus, quotationId,treminateReason);
		        
		        // update Notification flagValue  
		        quotationService.updateNotificationFlagValueByQID(quotationId,reviewStatus);
		        
		        if(reviewStatus == 1) {
		        	// System.out.println("reviewStatus is 1");
		        	// send mail to hiher authority persons
			        quotationService.sendQuotationMail(loggedEmpId, quotationId, session);
		        }
		        
		        if (isStatusChanged) {
		            return ResponseEntity.ok("success");
		        } else {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing quotation review Status. Please try again.");
		        }	
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Changing quotation review Status. Please try again.");
		    }
		    
		}

	 @GetMapping("/get_qidBasedOnPreparedBy")
		@ResponseBody
		public String getQuotationIdBasedOnPreparedBy(HttpSession session) {
			return quotationService.fetchLastQidBasedOnPreparedByName(session); 
		}

	 
	 @GetMapping("/add_quotation")
	 public String addQuotation(Model model,HttpSession session) {
	     List<QuotationDTO> quotations = quotationService.getAllQuotation();
	     
	     
	        // update the notification flag value to 0 when admin visit to /sales page
			// get qid based on logged amdin under employees generated quotations
	     
	        String emailid = (String) session.getAttribute("email");
			String empid = employeeRepository.findEmpidByEmail(emailid);
			List<String> q_idsByAdminEmployees = iadd_Quotation.getQidByEmpId(empid);		
			q_idsByAdminEmployees.stream().forEach(i->add_Quotation_Service.updateNotitification_StatusForCustomer(i));
			
	     // Debugging: Print quotation and products
	     for (QuotationDTO q : quotations) {
	         System.out.println("Quotation ID: " + q.getQid());
	         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
	     }
	     
	     System.out.println("quotations---"+quotations);
	     model.addAttribute("quotation", quotations);
	     return "User/Quotation";
	 }
	 
	 @GetMapping("/returnRecentlyInsertedTermsAndConditionDetail")
	 @ResponseBody
	 public Terms_and_condition_Entity getRecentlyInsertedTermsAndConditionDetail(@RequestParam(value = "qId", required = false) String qId) {
		 
		 if (qId != null && !qId.isEmpty()) {
			    System.out.println("QqqqqqqqId=" + qId);
			    //System.out.println("QqqqqqqqId=" + terms_and_condition_Service.getRecentInsertedTermsAndConditionDetail(qId));
			    return terms_and_condition_Service.getRecentInsertedTermsAndConditionDetailNew(qId);
			} else {
			    System.out.println("emmmmmpty");
			   
			    return terms_and_condition_Service.getRecentInsertedTermsAndConditionDetail();
			} 
	 }

	 
	 @GetMapping("/get_vendor_details")
	    public ResponseEntity<Map<String, String>> getVendorDetails(@RequestParam String vendorName) {
	        Map<String, String> vendorDetails = add_Vendors_Service.getVendorDetails(vendorName);

	        if (vendorDetails == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(vendorDetails);
	    }

	 
	 @GetMapping("/customers")
	  @ResponseBody 
	 public List<Map<String, String>> getAllCustomers() {
	     return add_Vendors_Service.getAllCustomers();
	 }
	 
	 @GetMapping("/product_list")
	 @ResponseBody
	 public List<Map<String, String>> getproduct_List() {		 
	     return add_Product_Service.get_product();	 
	 }
	 
	   @GetMapping("/getQuotationDetails")
	    public ResponseEntity<QuotationDTO> getQuotationDetails(@RequestParam String id) {
	        
		    QuotationDTO quotation = quotationService.getQuotationById(id);
	        
	        if (quotation != null) {
	            return ResponseEntity.ok(quotation);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	         
	    }
	  
	  	@GetMapping("/q_won")
		 public String openQWonData(Model model) {
		     List<QuotationDTO> quotations = quotationService.getQuotationWonDetails();
		     
		     // Debugging: Print quotation and products
		     for (QuotationDTO q : quotations) {
		         System.out.println("Quotation ID: " + q.getQid());
		         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
		     }
		     model.addAttribute("quotation", quotations);
		     return "User/Q_Won";
		 }
	  	
	  	@GetMapping("/q_loss")
		 public String openQLossData(Model model) {
		     List<QuotationDTO> quotations = quotationService.getQuotationLossDetails();
		     
		     // Debugging: Print quotation and products
		     for (QuotationDTO q : quotations) {
		         System.out.println("Quotation ID: " + q.getQid());
		         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
		     }
		     model.addAttribute("quotation", quotations);
		     return "User/Q_Loss";
		 }
	  	
	  	 @GetMapping("/dcData1")
		 public String openDCData(Model model, HttpSession session) {
	  		 System.out.println("salesss-----");
				
			 // Retrieve user details from session
		    String email = (String) session.getAttribute("email");
		    if (email == null) { // Check if session has expired
		        return "redirect:/"; // Redirect to the root mapping (login page)
		    }

//			List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
			String emailid = (String) session.getAttribute("email");
			String empId = employeeRepository.findEmpidByEmail(emailid);
		    // List<QuotationDTO> quotations = add_DC_Service.getAllQuotationForDC();
		     
		     // Debugging: Print quotation and products
		    /* for (QuotationDTO q : quotations) {
		         System.out.println("Quotation ID: " + q.getQid());
		         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
		     }*/
		   //  model.addAttribute("quotation", quotations);
			  // Get all delivery challan records (do not filter by empId)
		    List<DeliveryChallan> deliveryChallans = deliveryChallanRepository.findAll(); // or via service
		    System.out.println("hiiiii "+deliveryChallans);
		    model.addAttribute("deliveryChallans", deliveryChallans);

		     return "User/DC_Page";
		 }

	  	
		 
		  	@GetMapping("/dcData")
		  	public String openDCData1(Model model, HttpSession session) {
		  	    System.out.println("salesss-----");

		  	    String email = (String) session.getAttribute("email");
		  	    if (email == null) {
		  	        return "redirect:/"; // session expired → go to login
		  	    }

		  	    // Fetch all delivery challans
		  	    List<DeliveryChallan> deliveryChallans = deliveryChallanRepository.findAll();

		  	    // Convert to DTO and enrich with full name
		  	    List<DeliveryChallanDTO> deliveryChallans1 = deliveryChallans.stream().map(dc -> {
		  	        DeliveryChallanDTO dto = new DeliveryChallanDTO();
		  	        dto.setId(dc.getId());
		  	        dto.setQuotationId(dc.getQuotationId());
		  	        dto.setPurchaseOrderId(dc.getPurchaseOrderId());
		  	        dto.setCreatedDate(dc.getCreatedDate());
		  	        dto.setCreatedByEmpId(dc.getCreatedByEmpId());
		  	        dto.setDeliveryNoteNo(dc.getDeliveryNoteNo());

		  	        // 👇 fetch employee details by EmpId
		  	        DisplayEmployessEntity emp = employeeWitFullDetailes.findByEmpid(dc.getCreatedByEmpId());
		  	        if (emp != null) {
		  	            dto.setCreatedByName(emp.getFullName()); // or emp.getFullName()
		  	        } else {
		  	            dto.setCreatedByName("Unknown");
		  	        }

		  	        return dto;
		  	    }).collect(Collectors.toList());

		  	    model.addAttribute("deliveryChallans", deliveryChallans1);

		  	    return "User/DC_Page";
		  	}

		  	 
	  	 
	  	 
	  	@PostMapping("/dc/save")
		 public ResponseEntity<String> saveDC(@RequestParam String qid, @RequestParam String clickedFor, HttpSession session) {
		     try {
		    	 QuotationDTO quotation = quotationService.getQuotationById(qid);
		    	 //System.out.println("quotation ==1234 == "+quotation);
		    	 
		    	 Add_DC_DTO quotationDTO = new Add_DC_DTO();
		    	 
		    	 LocalDateTime now = LocalDateTime.now();
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    	 String formattedDateTime = now.format(formatter);
		    	 
		    	 quotationDTO.setQid(quotation.getQid());
		    	 quotationDTO.setDate_time(formattedDateTime);
		    	 quotationDTO.setPrepared_by(quotation.getPrepared_by());
		    	 quotationDTO.setHonorifics1(quotation.getHonorifics1());
		    	 quotationDTO.setCustomerName(quotation.getCustomerName());
		    	 quotationDTO.setVendorlegalName(quotation.getCustomerLegalName());
		    	 quotationDTO.setGstnumber(quotation.getGstNumber());
		    	 quotationDTO.setOrderMethod(quotation.getOrderMethod());
		    	 quotationDTO.setHonorifics2(quotation.getHonorifics2());
		    	 quotationDTO.setContactPersonName(quotation.getContactPersonName());
		    	 quotationDTO.setDesignation(quotation.getDesignation());
		    	 quotationDTO.setContactDetails(quotation.getContactDetails());
		    	 quotationDTO.setEmailId(quotation.getEmailId());
		    	 quotationDTO.setDeliveryAddress(quotation.getDeliveryAddress());
		    	 quotationDTO.setShipmentAddress(quotation.getShipmentAddress());
		    	 quotationDTO.setTaxType(quotation.getTax_type());
		    	 quotationDTO.setReview(quotation.getReview());
		    	 quotationDTO.setEmail_sent_status(quotation.getEmail_sent_status());
		    	 
		    	 
		    	// Map Product Details
		    	 if (quotation.getProducts() != null) {
		    	     List<ProductDTO> productList = quotation.getProducts().stream().map(product -> {
		    	         ProductDTO productDTO = new ProductDTO();
		    	         productDTO.setQid(quotation.getQid());
		    	         //productDTO.setQid(product.getQid());
		    	         productDTO.setProductName(product.getProductName());
		    	         productDTO.setHsnCode(product.getHsnCode());
		    	         productDTO.setPartNumber(product.getPartNumber());
		    	         productDTO.setProductDescription(product.getDescription());
		    	         productDTO.setQuantity(product.getQuantity());
		    	         productDTO.setPrice(product.getPrice());
		    	         //productDTO.setDiscount(product.getDiscount());
		    	         productDTO.setTotal(product.getTotal());
		    	         productDTO.setCgst(product.getCgst());
		    	         productDTO.setSgst(product.getSgst());
		    	         productDTO.setIgst(product.getIgst());
		    	         productDTO.setTotal_with_gst(product.getTotal_value());
		    	         return productDTO;
		    	     }).collect(Collectors.toList());

		    	     quotationDTO.setProducts(productList); // Assuming Add_DC_DTO has a products list
		    	 }
		    	 
		         //Terms_and_condition_Entity termsAndConditionObj = new Terms_and_condition_Entity();
		         //termsAndConditionObj.setTerms_and_condition_data(termsAndConditionValue);
		         //terms_and_condition_Service.insertTermsAndConditionData(termsAndConditionObj);
		    	 
		    	 add_DC_Service.saveQuotationForDC(quotationDTO, session);
		         return ResponseEntity.ok("Delivery Challan saved successfully!");
		     } catch (Exception e) {
		         e.printStackTrace();
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save delivery challan: " + e.getMessage());
		     }
		 }
	  	
	  	
		 @GetMapping("/salesOrder")
		  public String getAllPurchaseOrders(Model model,HttpSession session) {
			 
			 // Retrieve user details from session
			 String email = (String) session.getAttribute("email");
			 String empId = employeeRepository.findEmpidByEmail(email);
			 Integer loggedAdminDept = employeeRepository.findDeptIdByEmpId(empId);
			 
		      List<PurchaseOrder> orders = purchaseOrderService.getAllPurchaseOrders();
		      
		      // Fetch the full name for each createdByEmpId
		      for (PurchaseOrder order : orders) {
		          String fullName = employeeRepository.findEmpidByEmpId(order.getCreatedByEmpId());
		          order.setCreatedByEmpId(fullName); // Set the full name instead of empid
		      }
		      
		      model.addAttribute("loggedAdminDept",loggedAdminDept);
		      model.addAttribute("orders", orders);
		      
		      return "User/salesOrder"; // this looks for salesOrder.html or salesOrder.html in templates
		 } 
		 
		 @GetMapping("/invoice")
		  public String getAllInvoice(Model model,HttpSession session) {
			
			  // Retrieve user details from session
			  String email = (String) session.getAttribute("email");
			  String empId = employeeRepository.findEmpidByEmail(email);
			  String userType = employeeWitFullDetailes.getUserTypeByEmpId(empId);
			  
			  //DisplayEmployessEntity loggedAdminDetails = employeeWitFullDetailes.findByEmpid(empId);
			  Integer loggedAdminDept = employeeRepository.findDeptIdByEmpId(empId);
			  
			  List<PurchaseInvoiceDTO> invoiceLists = purchaseInvoiceService.getAllInvoice();
			  
			  model.addAttribute("loggedAdminDept",loggedAdminDept);
			  model.addAttribute("invoiceLists",invoiceLists);
			  model.addAttribute("userType",userType);
		      return "User/Invoice.html"; // this looks for salesOrder.html or salesOrder.html in templates
		      
		  }
		 //performaInvoice
		 @GetMapping("/performaInvoice")
		  public String getAllPerformaInvoiceInvoice(Model model,HttpSession session) {
			  
			  // Retrieve user details from session
			  String email = (String) session.getAttribute("email");
			  String empId = employeeRepository.findEmpidByEmail(email);
			  String userType = employeeWitFullDetailes.getUserTypeByEmpId(empId);
			  
			  //DisplayEmployessEntity loggedAdminDetails = employeeWitFullDetailes.findByEmpid(empId);
			  Integer loggedAdminDept = employeeRepository.findDeptIdByEmpId(empId);
			  
			  List<PerformaInvoiceDTO> performaInvoiceLists = purchaseInvoiceService.getAllPerformaInvoice();
			  
			  model.addAttribute("loggedAdminDept",loggedAdminDept);
			  model.addAttribute("performaInvoiceLists",performaInvoiceLists);
			  model.addAttribute("userType",userType);
		      return "User/performaInvoice.html"; // this looks for salesOrder.html or salesOrder.html in templates
		      
		  }

		 @GetMapping("/checkQuotationUsed")
		  public ResponseEntity<Map<String, Boolean>> checkQuotationUsed(@RequestParam String quotationId) {
		      boolean used = purchaseOrderRepository.countByQuotationId(quotationId) > 0;
		      Map<String, Boolean> response = new HashMap<>();
		      System.out.println("responseeeee "+ response);
		      response.put("used", used);
		      return ResponseEntity.ok(response);
		  }

}
