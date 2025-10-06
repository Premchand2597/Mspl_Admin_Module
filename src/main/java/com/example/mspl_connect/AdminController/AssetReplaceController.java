package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;
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

import com.example.mspl_connect.AdminEntity.AssetReplace;
import com.example.mspl_connect.AdminEntity.Scap;
import com.example.mspl_connect.AdminRepo.AssetReplaceRepository;
import com.example.mspl_connect.AdminRepo.ScapRepository;
import com.example.mspl_connect.AdminService.AssetReplaceService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/asset-replacements")
public class AssetReplaceController {

	@Autowired
	private ScapRepository scapRepo;
	
	  @Autowired
	    private AssetReplaceService assetReplaceservice;

		@Autowired
		private EmployeeRepository employeeRepository;
	  
	 @Autowired
	    private AssetReplaceRepository assetReplaceRepo;

	 @GetMapping
	    public List<AssetReplace> getAllReplacements() {
	        return assetReplaceservice.getAllReplacements();
	    }
	 
	
	 
	 @PostMapping("/replace/{id}/approve")
	 public AssetReplace approveReplaceRequest(
	         @PathVariable int id,
	         @RequestParam String empId) {

	     AssetReplace request = assetReplaceRepo.findById(id)
	             .orElseThrow(() -> new RuntimeException("Replacement request not found"));

	     // Approve the request
	     request.setStatus("Approved");
	     request.setApprovedName(empId); // or senderEmpId depending on logic
	     request.setApprovedAt(LocalDateTime.now());
	     request.setNotification(false);
	     assetReplaceRepo.save(request);

	     Scap scap = new Scap();
	     scap.setAssignedAssetId(request.getAssignedAssetId());
	     //scap.setOldAssetId(request.getOldAssetId());
	     scap.setOldAssetId(request.getOldAssetId() != null ? request.getOldAssetId().toString() : null);
         scap.setAssetId(request.getAssetId());
	     scap.setRefAssetId(request.getRefAssetId());
	     scap.setAssetType(request.getAssetType());
	     scap.setQuantity(request.getQuantity());
	     scap.setAssignedTo(request.getSenderEmpId());
	     scap.setAssignedAt(LocalDateTime.now());
	     scap.setDescription(request.getDescription());
	     scap.setRequestedAt(request.getRequestedAt());
	     scap.setRemarks(request.getRemarks());
	     
	  // NEW FIELDS
	     scap.setSenderEmpId(request.getSenderEmpId());        // who requested replacement
	     scap.setRequestReplaceId(request.getId());
	     
	     scapRepo.save(scap);


	     return request;
	 }

	 @PostMapping("/replace/{id}/reject")
	 public AssetReplace rejectReplaceRequest(
	         @PathVariable int id,
	         @RequestParam String empId) {

	     AssetReplace request = assetReplaceRepo.findById(id)
	             .orElseThrow(() -> new RuntimeException("Replacement request not found"));

	     // Reject the request
	     request.setStatus("Rejected");
	     request.setApprovedName(empId); // or a separate rejectedBy field if you want
	     request.setApprovedAt(LocalDateTime.now()); // timestamp for rejection
	     request.setNotification(false);
	     return assetReplaceRepo.save(request);
	 }
	 
	 
	 @PostMapping("/assets/replace")
	 @ResponseBody
	 public Map<String, String> replaceAssets(@RequestBody List<AssetReplace> assetRequests,
	                                          HttpSession session) {
	     String email = (String) session.getAttribute("email");
	     String empId = employeeRepository.findEmpidByEmail(email);

	     assetReplaceservice.replaceAssets(assetRequests, empId);

	     Map<String, String> response = new HashMap<>();
	     response.put("message", "Assets replacement request submitted successfully!");
	     return response;
	 }
	 

}
