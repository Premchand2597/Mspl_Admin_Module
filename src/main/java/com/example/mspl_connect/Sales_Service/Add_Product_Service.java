package com.example.mspl_connect.Sales_Service;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.Add_Product_Entity;
import com.example.mspl_connect.Sales_Repository.IAdd_Product;

import jakarta.transaction.Transactional;

@Service
public class Add_Product_Service {

	
	@Autowired
	private IAdd_Product iAdd_Product;
	

	/*
	 * public String get_products() {
	 * 
	 * String lastPid = iAdd_Product.findLastPid();
	 * 
	 * if (lastPid != null && lastPid.startsWith("MSP")) { // Extract the numeric
	 * part, increment it, and format it back int lastNumber =
	 * Integer.parseInt(lastPid.substring(3)); // Extract number part return
	 * String.format("MSP%03d", lastNumber + 1); // Increment and format }
	 * 
	 * return "MSP001"; // First record }
	 */
	
	
	
	public String get_products() {
		String lastPid = iAdd_Product.findLastPid();

	    // Get current date and determine the financial year
	    LocalDate now = LocalDate.now();
	    int currentYear = now.getYear();
	    int nextYear = currentYear + 1;

	    // If before April, use the previous year as the financial year start
	    if (now.getMonthValue() < 4) {
	        currentYear -= 1;
	        nextYear = currentYear + 1;
	    }

	    // Format financial year as "2024-25"
	    String financialYear = String.format("%d-%02d", currentYear, nextYear % 100);

	    if (lastPid != null && lastPid.startsWith("MSPL")) {
	        // Ensure lastQid has the expected format
	        String[] parts = lastPid.split("/");
	        if (parts.length == 4) {
	            try {
	                int lastNumber = Integer.parseInt(parts[3]); // Extract the last numeric part
	                return String.format("MSPL/%s/P/%03d", financialYear, lastNumber + 1);
	            } catch (NumberFormatException e) {
	                System.err.println("Invalid lastQid format: " + lastPid);
	            }
	        }
	    }

	    // First record for the financial year
	    return String.format("MSPL/%s/P/101", financialYear);
	}
	
	
	
	
	public void saveProduct(Add_Product_Entity product) {
        // Call the stored procedure through the repository
        iAdd_Product.addProduct(
            product.getPid(),
            product.getDateTime(),
            product.getPname(),
            product.getHsn_code(),
            product.getPart_number(),
            product.getPdescription(),           
            product.getRemarks()
        );
    }
	
	
	public List<Add_Product_Entity> getAllProducts() {
		   List<Add_Product_Entity> products = iAdd_Product.fetchProducts();
			System.out.println("products before formated"+products);
		    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

		    products.forEach(product -> {
		        String dateTimeStr = product.getDateTime(); // assuming it's a String
		        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter);
		        String formatted = dateTime.format(outputFormatter);
		        product.setDateTime(formatted); // overwrite or store in another field
		    });
		
		System.out.println("products after formated"+products);
        return products;
    }
		
	public List<String> get_pname() {
		
		return iAdd_Product.fetch_pname();
	}

	public void updateProduct(Add_Product_Entity product) {

		 System.out.println("product-----" + product);

		    // Convert the date format
		    String originalDateTime = product.getDateTime(); // "29-03-2025 12:00:03"

		    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    LocalDateTime dateTime = LocalDateTime.parse(originalDateTime, inputFormatter);
		    String formattedDateTime = dateTime.format(outputFormatter);

		    
		    // Pass formatted dateTime to the update method
		    iAdd_Product.update_products(
		            product.getPid(),
		            formattedDateTime, // use the converted format
		            product.getPname(),
		            product.getHsn_code(),
		            product.getPart_number(),
		            product.getPdescription(),
		            product.getRemarks()
		    );
    }
	
	@Transactional
    public void deleteProduct(String pid) {
        int deletedRows = iAdd_Product.deleteProductByPid(pid);
        if (deletedRows == 0) {
            throw new RuntimeException("Product not found!");
        }
    }
	
	public List<Map<String, String>> get_product() {
		
	    List<Object[]> results = iAdd_Product.fetch_product();
	    List<Map<String, String>> products = new ArrayList<>();

	    for (Object[] row : results) {
	        Map<String, String> product = new HashMap<>();
	        product.put("pname", (String) row[0]);
	        product.put("hsn_code", (String) row[1]);
	        product.put("part_number", (String) row[2]);
	        product.put("pdescription", (String) row[3]);
	        product.put("pid",(String) row[4]);
	        
	        products.add(product);
	    }
	    return products;
	}

	
}
