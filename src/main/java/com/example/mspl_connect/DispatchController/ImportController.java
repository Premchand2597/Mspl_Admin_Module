package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchEntity.Customer;
import com.example.mspl_connect.DispatchEntity.Dispatch;
import com.example.mspl_connect.DispatchEntity.Product;
import com.example.mspl_connect.DispatchEntity.TransportMode_Entity;
import com.example.mspl_connect.DispatchRepo.DispatchRepository;
import com.example.mspl_connect.DispatchService.AddUserService;
import com.example.mspl_connect.DispatchService.AuditService;
import com.example.mspl_connect.DispatchService.CustomerService;
import com.example.mspl_connect.DispatchService.ProductService;
import com.example.mspl_connect.DispatchService.TransportMode_Service;

import jakarta.servlet.http.HttpSession;

@Controller
public class ImportController {
	
	@Autowired
	private DispatchRepository dispatchRepository;
	
	@Autowired
	private TransportMode_Service transportMode_Service;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AddUserService addUserService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
    private ProductService productService;

	@GetMapping("/import")
	public String showImportPage(Model model,HttpSession session) {
		if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
		String username = (String) session.getAttribute("username"); 
        model.addAttribute("username", username); 	
		return "dispatch/Import";
	}
	
	@PostMapping("/saveExcelDispatchData")
	public ResponseEntity<String> saveDispatchData(@RequestBody List<Dispatch> dispatchList, HttpSession session) {
	    try {
				for (Dispatch dis : dispatchList) {
					
					if(dis.getReturnedQty() == null || dis.getReturnedQty().isEmpty() || dis.getReturnedQty() == "") {
						System.out.println(dis.getReturnedQty());
						dis.setReturnedQty("0");
					}
					
					if (dis.getReturnedDate() != null && !dis.getReturnedDate().trim().isEmpty()) {
						System.out.println(dis.getReturnedDate());
					    String returnedDateStr = dis.getReturnedDate().trim();
					    // Optional: convert dd-MM-yyyy to yyyy-MM-dd
					    if (returnedDateStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
					        String[] parts = returnedDateStr.split("-");
					        String formattedDate = parts[2] + "-" + parts[1] + "-" + parts[0];
					        dis.setReturnedDate(formattedDate);
					    } else {
					        dis.setReturnedDate(returnedDateStr); // already in correct format
					    }
					}

					if((dis.getTransportMode() != null && !dis.getTransportMode().isEmpty()) || dis.getTransportMode() != "") {
						System.out.println(dis.getTransportMode());
						boolean isTransportModeDataAlreadyExists = transportMode_Service.isTransportModeExists(dis.getTransportMode());
						if (!isTransportModeDataAlreadyExists) {
							TransportMode_Entity transportMode_Entity = new TransportMode_Entity();
							transportMode_Entity.setName(dis.getTransportMode());
							String executedResult = transportMode_Service.addTransportMode(transportMode_Entity);
							if ("success".equals(executedResult)) {
								String username = (String) session.getAttribute("username");
								if (username == null) {
									username = "Anonymous";
								}
								auditService.logAction("Excel Import", username,
										"Excel imported transport mode data with ID: " + transportMode_Entity.getId());
							}
						}
					}
					
					if((dis.getUserName() != null && !dis.getUserName().isEmpty()) || dis.getUserName() != "") {
						System.out.println(dis.getUserName());
						boolean isUsernameAlreadyExists = addUserService.isUserNameExistsOrNot(dis.getUserName());
						if (!isUsernameAlreadyExists) {
							AddUser addUser = new AddUser();
							addUser.setUserName(dis.getUserName());
							AddUser savedUser = addUserService.saveUser(addUser);

						    String username = (String) session.getAttribute("username");
						    if (username == null) {
						        username = "Anonymous";
						    }

						    auditService.logAction(
						        "Excel Import",
						        username,
						        "Excel imported User ID: " + savedUser.getId()
						    );
						}
					}
					
					if((dis.getCompanyName() != null && !dis.getCompanyName().isEmpty()) || dis.getCompanyName() != "") {
						System.out.println(dis.getCompanyName());
						//boolean isEmailAlreadyExists = customerService.isEmailExistsOrNot(dis.getEmailId());
						//boolean isContactNoAlreadyExists = customerService.isContactNoExists(dis.getContactNo());
						boolean isCompanyNameAlreadyExists = customerService.isCompanyNameExists(dis.getCompanyName());
						if (!isCompanyNameAlreadyExists) {
							Customer customer = new Customer();
							customer.setEmailId(dis.getEmailId());
							customer.setCustomerName(dis.getCustomerName());
							customer.setCompanyAddress(dis.getCompanyAddress());
							customer.setCompanyName(dis.getCompanyName());
							customer.setContactNo(dis.getContactNo());
							Customer savedCustomer = customerService.saveCustomer(customer);

						     String username = (String) session.getAttribute("username");
						     
						     if (username == null) {
						         username = "Anonymous"; 
						     }

						     auditService.logAction(
						         "Excel Import",
						         username,
						         "Excel imported Customer ID: " + savedCustomer.getId()
						     );
						}
					}
					
					if((dis.getProductName() != null && !dis.getProductName().isEmpty()) || dis.getProductName() != "") {
						System.out.println(dis.getProductName());
						boolean isProductAlreadyExists = productService.isProductExistsOrNot(dis.getProductName());
						if (!isProductAlreadyExists) {
							Product product = new Product();
							product.setProductName(dis.getProductName());
							product.setRootFirmwareExternal(dis.getRootFirmwareExternal());
							product.setRootFirmwareInternal(dis.getRootFirmwareInternal());
							product.setRouterFirmwareExternal(dis.getRouterFirmwareExternal());
							product.setRouterFirmwareInternal(dis.getRouterFirmwareInternal());
							product.setModuleNo(dis.getModuleNo());
							product.setAntennaType(dis.getAntennaType());
							product.setFirmwareHardwareVersion(dis.getFirmwareHardwareVersion());
							Product savedProduct = productService.saveProduct(product);

				            String username = (String) session.getAttribute("username");
				            if (username == null) {
				                username = "Anonymous";
				            }

				            auditService.logAction(
				                    "Excel Import",
				                    username,
				                    "Excel imported Product ID: " + savedProduct.getId()
				            );
						}
					}
					
				}
	    	
	    	  dispatchRepository.saveAll(dispatchList);
	          return ResponseEntity.ok("All records processed successfully!");
	      } catch (Exception e) {
	    	  System.out.println(e.getMessage());
	    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process records. Transaction rolled back.");
	      }
	    
	}

}
