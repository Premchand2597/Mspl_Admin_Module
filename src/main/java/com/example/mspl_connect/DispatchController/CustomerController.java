package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.DispatchEntity.Customer;
import com.example.mspl_connect.DispatchRepo.CustomerRepository;
import com.example.mspl_connect.DispatchService.AuditService;
import com.example.mspl_connect.DispatchService.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping("/customer")
	public String showCustomer(Model model,HttpSession session) {
		if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
		String username = (String) session.getAttribute("username"); 
        model.addAttribute("username", username); 
		
	 model.addAttribute("customer", new Customer());
	 model.addAttribute("customerList", customerRepository.findAllByOrderByIdDesc());  
	return "dispatch/Customer";
	}
	
	 @GetMapping("/getCustomerDetails")
	    @ResponseBody
	    public Customer getCustomerDetails(@RequestParam String customerName) {
	        return customerRepository.findByCustomerName(customerName);
	    }
	
	 @PostMapping("/submitCustomer")
	 public String submitCustomer(
	         @ModelAttribute Customer customer,
	         Model model,
	         HttpSession session) {

	     //System.out.println("Received Customer Name: " + customer.getCustomerName()); 
	     
	     Customer savedCustomer = customerService.saveCustomer(customer);

	     String username = (String) session.getAttribute("username");
	     //System.out.println("DEBUG - Username from session: " + username);
	     
	     if (username == null) {
	         username = "Anonymous"; 
	     }

	     auditService.logAction(
	         "INSERT",
	         username,
	         "Added Customer ID: " + savedCustomer.getId()
	     );

	     model.addAttribute("message", "Customer form submitted successfully");
	     return "redirect:/customer";
	 }
	 
	 @PostMapping("/editCustomer")
	 public String editCustomer(
	         @ModelAttribute Customer customer,
	         Model model,
	         HttpSession session) {

	     Customer savedCustomer = customerService.saveCustomer(customer);

	     String username = (String) session.getAttribute("username");
	     
	     if (username == null) {
	         username = "Anonymous"; 
	     }

	     auditService.logAction(
	         "UPDATE",
	         username,
	         "Updated Customer ID: " + savedCustomer.getId()
	     );

	     model.addAttribute("message", "Customer form submitted successfully");
	     return "redirect:/customer";
	 }
	 
		@PostMapping("/submitCustomerPopupData")
		public ResponseEntity<String> submitCustomerDetails(@ModelAttribute Customer customer, HttpSession session) {
		     Customer savedCustomer = customerService.saveCustomer(customer);
		     
		     String username = (String) session.getAttribute("username");
		     if (username == null) {
		         username = "Anonymous"; 
		     }

		     auditService.logAction(
		         "INSERT",
		         username,
		         "Added Customer ID: " + savedCustomer.getId()
		     );
		     
		     return ResponseEntity.ok("successfull");
		}
		
		@GetMapping("/fetchRecentCustomerDetails")
		@ResponseBody
		public Customer getRecentData() {
			return customerService.fetchRecentDetails();
		}

		
		@GetMapping("/fetchAllCustomerDetails")
		@ResponseBody
		public List<Customer> getAllCustomerDetails() {
			return customerRepository.findAll();
		}
		
		@PostMapping("/deleteCustomerDetailsBasedOnNameAndContactNo")
	    public ResponseEntity<String> deleteCustomerDetails(@RequestParam String name, @RequestParam String contactNo, HttpSession session) {
			customerService.deleteCustomerDataBasedOnNameAndContactNo(name, contactNo);
			
			String username = (String) session.getAttribute("username");
		    if (username == null) {
		        username = "Anonymous";
		    }

		    auditService.logAction(
		        "DELETE",
		        username,
		        "Deleted Customer data name: " +name+" and contact No: "+contactNo
		    );
		    
			return ResponseEntity.ok("Data deleted successfully");
		}
		
		@GetMapping("/fetchCustomerDetailsBasedOnId")
		@ResponseBody
		public Customer getProductDetailsBasedOnId(@RequestParam long id){
			return customerRepository.getCustomerDetailsBasedOnAutoId(id);
		}
		
		@PostMapping("/deleteCustomerById")
		public ResponseEntity<String> deleteCustomer(@RequestParam long id, HttpSession session) {
			customerService.deleteData(id);

		    String username = (String) session.getAttribute("username");
		    if (username == null) {
		        username = "Anonymous";
		    }

		    auditService.logAction(
		        "DELETE",
		        username,
		        "Deleted Customer ID: " + id
		    );
		    return ResponseEntity.ok("data deleted");
		}
		
		@GetMapping("/checkCustomerEmailIdExists")
		@ResponseBody
		public boolean isEmailExists(@RequestParam String email) {
			return customerService.isEmailExistsOrNot(email);
		}
		
		@GetMapping("/checkCustomerContactNoExists")
		@ResponseBody
		public boolean isContactNoExists(@RequestParam String contact_no) {
			return customerService.isContactNoExists(contact_no);
		}

	}
