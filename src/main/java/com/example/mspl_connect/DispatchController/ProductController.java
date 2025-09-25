package com.example.mspl_connect.DispatchController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.DispatchEntity.Product;
import com.example.mspl_connect.DispatchRepo.ProductRepository;
import com.example.mspl_connect.DispatchService.AuditService;
import com.example.mspl_connect.DispatchService.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuditService auditService;

    @GetMapping("/product")
    public String showProduct(Model model, HttpSession session) {
    	if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("product", new Product());
        model.addAttribute("productList", productRepository.findAllByOrderByIdDesc());
        return "dispatch/product";
    }

    @GetMapping("/getProductDetails")
    @ResponseBody
    public Product getProductDetails(@RequestParam String productName) {
        return productRepository.findByProductName(productName);
    }
    
    @GetMapping("/getProductDetailsByPrdNameAndModelNo")
    @ResponseBody
    public Product getProductDetailsUsingPrdNameAndModelNo(@RequestParam String productName, @RequestParam String modelNo) {
        return productRepository.getProductDetailsBasedOnPrdNameAndModelNo(productName, modelNo);
    }
    
    @PostMapping("/insertProduct")
    public String inserttProduct(
            @ModelAttribute Product product,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request,
            HttpSession session) {

        try {
        	
        	String fileName = null;
        	
        	if (file != null && !file.isEmpty()) {
	            fileName = saveImage(file); // Initialize fileName as null
        	} else {
            	//System.out.println("FileFile in else === "+file);
            	fileName = product.getUploadFile();
            }

            // Save only the filename (not the full path) in the product entity
            if (fileName != null) {
            	//System.out.println("FileFile in final === "+file);
                product.setUploadFile(fileName);  // Only save the filename, not the full path
            }

            // Save the product with the file name (filename is stored in the database)
            Product savedProduct = productService.saveProduct(product);

            // Track who performed the action (if needed)
            String username = (String) session.getAttribute("username");
            if (username == null) {
                username = "Anonymous";
            }

            // Log the action (audit log)
            auditService.logAction(
                    "INSERT",
                    username,
                    "Added Product ID: " + savedProduct.getId()
            );

            return "redirect:/product";
        } catch (IOException e) {
            // Handle error during file upload
        	System.out.println(e);
            return "redirect:/product";
        }
    }
    
    @PostMapping("/editProduct")
    public String editProduct(
            @ModelAttribute Product product,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request,
            HttpSession session) {

        try {
        	
        	String fileName = null;
        	
        	if (file != null && !file.isEmpty()) {
	            fileName = saveImage(file); // Initialize fileName as null
        	} else {
            	//System.out.println("FileFile in else === "+file);
            	fileName = product.getUploadFile();
            }

            // Save only the filename (not the full path) in the product entity
            if (fileName != null) {
            	//System.out.println("FileFile in final === "+file);
                product.setUploadFile(fileName);  // Only save the filename, not the full path
            }

            // Save the product with the file name (filename is stored in the database)
            Product savedProduct = productService.saveProduct(product);

            // Track who performed the action (if needed)
            String username = (String) session.getAttribute("username");
            if (username == null) {
                username = "Anonymous";
            }

            // Log the action (audit log)
            auditService.logAction(
                    "UPDATE",
                    username,
                    "Updated Product ID: " + savedProduct.getId()
            );

            return "redirect:/product";
        } catch (IOException e) {
            // Handle error during file upload
        	System.out.println(e);
            return "redirect:/product";
        }
    }

    @PostMapping("/submitProduct")
    public ResponseEntity<String> submitProduct(
            @ModelAttribute Product product,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request,
            HttpSession session) {

        try {
        	String fileName = null;
        	
        	if (file != null && !file.isEmpty()) {
	            fileName = saveImage(file); // Initialize fileName as null
        	} else {
            	//System.out.println("FileFile in else === "+file);
            	fileName = product.getUploadFile();
            }

            // Save only the filename (not the full path) in the product entity
            if (fileName != null) {
            	//System.out.println("FileFile in final === "+file);
                product.setUploadFile(fileName);  // Only save the filename, not the full path
            }

            // Save the product with the file name (filename is stored in the database)
            Product savedProduct = productService.saveProduct(product);

            // Track who performed the action (if needed)
            String username = (String) session.getAttribute("username");
            if (username == null) {
                username = "Anonymous";
            }

            // Log the action (audit log)
            auditService.logAction(
                    "INSERT",
                    username,
                    "Product ID: " + savedProduct.getId()
            );

            return ResponseEntity.ok("Product saved successfully!");
        } catch (IOException e) {
            // Handle error during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/getAllProductDetails")
    @ResponseBody
    public List<Product> getAllProductDetails() {
        return productRepository.findAll();
    }
    
    @GetMapping("/fetchRecentProductDetails")
	@ResponseBody
	public Product getRecentData() {
		return productService.fetchRecentDetails();
	}
    
    @PostMapping("/deleteProductDetailsBasedOnProdNameAndModelNo")
    public ResponseEntity<String> deleteProductDetails(@RequestParam String prodName, @RequestParam String modelNo, HttpSession session) {
    	productService.deleteProductDataBasedOnProdNameAndModuleNo(prodName, modelNo);
    	
    	String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "DELETE",
	        username,
	        "Deleted Product Name: " + prodName+" and Module No: "+modelNo
	    );
    	
		return ResponseEntity.ok("Data deleted successfully");
	}
    
    @GetMapping("/fetchProductDetailsBasedOnId")
	@ResponseBody
	public Product getProductDetailsBasedOnId(@RequestParam long id){
		return productRepository.getProductsDetailsBasedOnAutoId(id);
	}
    
    @PostMapping("/deleteProductById")
	public ResponseEntity<String> deleteProduct(@RequestParam long id, HttpSession session) {
    	productService.deleteData(id);

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "DELETE",
	        username,
	        "Deleted Product ID: " + id
	    );
	    return ResponseEntity.ok("data deleted");
	}
    
    @GetMapping("/checkModelNoExists")
	@ResponseBody
	public boolean isModelNoExists(@RequestParam String modelNo) {
		return productService.isModelNoExistsOrNot(modelNo);
	}
    
    private String saveImage(MultipartFile file) throws IOException {
 		//System.out.println("hoooooooo");
	    if (!file.isEmpty()) {
	    	
	    	/*String contentType = file.getContentType();
	        if (contentType == null || !contentType.startsWith("image/")) {
	            throw new IOException("Invalid file type. Only image files are allowed.");
	        }*/
	    	
	        // Create the directory if it doesn't exist
	        //Path uploadDirectory = Paths.get("C:/Users/COMP/Desktop/EmpDocs");
	    	Path uploadDirectory = Paths.get("D:/Desktop/EmpDocs/");
	    	//Path uploadDirectory = Paths.get("/home/melange/Desktop/EmpDocs/");
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }
	        
	        Path uploadDirectory2 = Paths.get("src/main/resources/static/assets/EmpBackupDocs/");
	        if (!Files.exists(uploadDirectory2)) {
	            Files.createDirectories(uploadDirectory2);
	        }

	        // Generate a unique file name
	        //String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	        String fileName = file.getOriginalFilename();

	        Path filePath = uploadDirectory.resolve(fileName);
	        Path filePath2 = uploadDirectory2.resolve(fileName);

	        // Save the file to the file system
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        Files.copy(file.getInputStream(), filePath2, StandardCopyOption.REPLACE_EXISTING);

	        // Return the relative path for web access
	        return "Desktop/EmpDocs/" + fileName;
	    }
	    return null;
	}
    
}
