package com.example.mspl_connect.Sales_Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;
import com.example.mspl_connect.Sales_Service.Add_Vendors_Service;

@Controller
public class Add_Vendors_Controller {
	
	@Autowired
	private Add_Vendors_Service add_Vendors_Service;
	
	
	public Add_Vendors_Controller(Add_Vendors_Service add_Vendors_Service) {
		
		this.add_Vendors_Service = add_Vendors_Service;
	}
	
	@GetMapping("/get_vid")
	@ResponseBody
	private String get_vid() {
		
		return add_Vendors_Service.get_vid();
		
	}


	@PostMapping("/saveVendor")
	public ResponseEntity<Map<String, String>> saveVendor(@RequestBody Add_Vendors_Entity vendor) {
	    try {
	        //System.out.println("Received Vendor Data: " + vendor);

	        if (vendor.getVid() == null || vendor.getVid().isEmpty()) {
	            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Vendor ID (vid) cannot be null!"));
	        }

	        Add_Vendors_Entity savedVendor = add_Vendors_Service.saveVendor(vendor);
	        return ResponseEntity.ok(Collections.singletonMap("message", "Vendor saved successfully!"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Collections.singletonMap("error", "Error saving vendor: " + e.getMessage()));
	    }
	}


	 @GetMapping("/add_vendors")
	    public String addVendors(Model model) {
	    	  List<Add_Vendors_Entity> vendors = add_Vendors_Service.getAllVendors();
	          System.out.println("Fetched Vendors: " + vendors); // Debugging Line
	          model.addAttribute("vendor", vendors);
	        return "User/Vendors";
	    }
	 
	 
	 
	 	@PostMapping("/updateVendor")
	    @ResponseBody
	    public Map<String, Object> updateVendors(@RequestBody Add_Vendors_Entity vendor) {
	        Map<String, Object> response = new HashMap<>();

	        try {
	            add_Vendors_Service.updateVendor(vendor); // Call service to update DB
	            response.put("success", true);
	        } catch (Exception e) {
	            response.put("success", false);
	            response.put("message", e.getMessage());
	        }
	        return response;
	    }
	 
	    
	 @PostMapping("/deleteVendor")
	    public ResponseEntity<Map<String, String>> deleteVendor(@RequestParam String id) {
	        try {
	            System.out.println("Deleting Product with ID: " + id);
	            add_Vendors_Service.deleteVendor(id);
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Customer deleted successfully!");
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(Collections.singletonMap("error", e.getMessage()));
	        }
	    }

	 
	 
	  
	

}
