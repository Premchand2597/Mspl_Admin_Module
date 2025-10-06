package com.example.mspl_connect.AdminController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.AssetReturn;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.AdminService.AssetReturnService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/asset-returns")
public class AssetReturnController {

	  @Autowired
	    private AssetReturnService assetReturnservice;

	    @Autowired
	    private AssetRepository assetRepository;
	    
		@Autowired
		private EmployeeRepository employeeRepository;
		
		
		
	    @Autowired
	    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
	    
	    @GetMapping
	    public List<AssetReturn> getAllRequests() {
	        return assetReturnservice.getAllRequests();
	    }

	   /* @PostMapping("/{id}/approve")
	    public AssetReturn approveRequest(@PathVariable int id) {
	        return assetReturnservice.approveRequest(id);
	    }*/

	    @PostMapping("/{id}/approve")
	    public AssetReturn approveRequest(
	            @PathVariable int id,
	            @RequestParam String empId) {
	        return assetReturnservice.approveRequest(id, empId);
	    }


	    @PostMapping("/{id}/reject")
	    public AssetReturn rejectRequest(@PathVariable int id, @RequestBody String remarks) {
	        return assetReturnservice.rejectRequest(id, remarks);
	    }
	    
		 
		 @PostMapping("/assets/return")
		 @ResponseBody
		 public Map<String, String> returnAssets(@RequestBody List<AssetReturn> assetRequests,
		                                         HttpSession session) {
		     String email = (String) session.getAttribute("email");
		     String empId = employeeRepository.findEmpidByEmail(email);

		     assetReturnservice.returnAssets(assetRequests, empId);

		     Map<String, String> response = new HashMap<>();
		     response.put("message", "Assets returned successfully!");
		     return response;
		 }
		 
		 
	
}
