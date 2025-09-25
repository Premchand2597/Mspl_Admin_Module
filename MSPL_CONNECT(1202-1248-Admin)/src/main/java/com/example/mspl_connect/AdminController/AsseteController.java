package com.example.mspl_connect.AdminController;

import java.util.Map;

import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.AdminEntity.AssetUpdateRequest;
import com.example.mspl_connect.AdminEntity.AsseteEntity;
import com.example.mspl_connect.AdminService.AsseteService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class AsseteController {
	
	@Autowired
	private AsseteService asseteService;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("/saveAsset")
    public String saveAsset(@ModelAttribute("assetEntity") AsseteEntity assetEntity, Model model,HttpSession session) {
		
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);

		asseteService.saveAsset(assetEntity,adminDept);

        // Optionally, add a success message or redirect to another page
        model.addAttribute("message", "Asset saved successfully!");

        // Redirect to a suitable page or return to the form
        return "redirect:/asset"; // Replace with your success page or the form page
    }
	
	// Define the endpoint to forward the request to the store
    @PostMapping("/forwardToStore/{assetId}")
    public ResponseEntity<String> forwardToStore(@PathVariable int assetId) {
        try {
            // Call the service method to update the request_value
        	asseteService.updateRequestValue(assetId, 2);
            return ResponseEntity.ok("Request forwarded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error forwarding the request");
        }
    }
    
    @PostMapping("/updateAssetAvailable")
    public ResponseEntity<String> updateAssetAvailable(@RequestBody Map<String,Integer> payload){
    	    int assetId = payload.get("id");
    	    System.out.println("........................"+assetId);
    	    try {
                // Call the service method to update the request_value
            	asseteService.updateAssetAvailable(assetId, 3);
            	return ResponseEntity.ok("Asset updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error Update the request");
            }
    }
    @PostMapping("/updateAssetDelay")
    public ResponseEntity<String> updateAssetDelay(@RequestBody AssetUpdateRequest request){
    	 
    	    int assetId = request.getId();
    	    String availableDate = request.getAvailableDate();
    	    
    	    try {
                // Call the service method to update the request_value
            	asseteService.updateAssetDelay(assetId, availableDate);
            	return ResponseEntity.ok("Asset updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error Update the request");
            }
    }
}
