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

import com.example.mspl_connect.Sales_Entity.Add_Product_Entity;
import com.example.mspl_connect.Sales_Repository.IAdd_Product;
import com.example.mspl_connect.Sales_Service.Add_Product_Service;

@Controller
public class Add_Product_Controller {

	@Autowired
	private Add_Product_Service add_Product_Service;
	
	@Autowired
	private IAdd_Product iAdd_Product;
	
	@GetMapping("/get_pid")
	@ResponseBody
	public String getpid() {
		return add_Product_Service.get_products(); 
	}
	
	
    @PostMapping("/save_product")
    public ResponseEntity<String> saveProduct(@RequestBody Add_Product_Entity product) {
        // Calling the service method
    	
    	 System.out.println("Received product ID: " + product.getPid());
         System.out.println("Received product Name: " + product.getPname());
         System.out.println("Received product Description: " + product.getPdescription());
         System.out.println("Received product Date: " + product.getDateTime());
        add_Product_Service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully");
    }
    
    @GetMapping("/add_products")
    public String addProducts(Model model) {
    	  List<Add_Product_Entity> productss = add_Product_Service.getAllProducts();
          System.out.println("Fetched Products: " + productss); // Debugging Line
          model.addAttribute("products", productss);
        return "User/products";
    }
    
    
    @GetMapping("/get_products")
    @ResponseBody
    public List<Add_Product_Entity> getProducts() {
        return iAdd_Product.findAll(); // Assuming you have a JPA repository
    }

    
    
    @PostMapping("/updateProduct")
    @ResponseBody
    public Map<String, Object> updateProduct(@RequestBody Add_Product_Entity product) {
    	
        Map<String, Object> response = new HashMap<>();        
        
        
        try {
            add_Product_Service.updateProduct(product); // Call service to update DB
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/deleteProduct")
    public ResponseEntity<Map<String, String>> deleteProduct(@RequestParam String id) {
        try {
            System.out.println("Deleting Product with ID: " + id);
            add_Product_Service.deleteProduct(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        }
    }




	
	
}
