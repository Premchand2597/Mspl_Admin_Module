package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.AdminEntity.AssetRequest;
import com.example.mspl_connect.AdminEntity.AssetUpdateLog;
import com.example.mspl_connect.AdminEntity.AssetUpdateRequest;
import com.example.mspl_connect.AdminEntity.Assetes;
import com.example.mspl_connect.AdminEntity.AssignedAssetDetailsDTO;
import com.example.mspl_connect.AdminEntity.AssignedAssets;
import com.example.mspl_connect.AdminEntity.assetsDTO;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.AdminService.AssetRequestService;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsseteController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
    @Autowired
    private AssetRequestService assetRequestService;
	
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
    
	/* @PostMapping("/save")
	 public String requestAsset(@ModelAttribute AssetRequest request,HttpSession session) {
		   
		   String loggedAdminEmpId = (String) session.getAttribute("loggedAdminEmpId");
		   
		   if(request.getEmpId() == null || request.getEmpId().trim().isEmpty()) {
			   request.setEmpId(loggedAdminEmpId);
		   }
		   
		   request.setAssigned_asset_qty(0);
		   
		    
	       assetRequestService.saveRequest(request);
	       
	       return "redirect:/asset?success";
	 }*/
    
	 @PostMapping("/save")
	 @ResponseBody // Optional: only if you want to return plain text/JSON instead of a view
	 public ResponseEntity<String> requestAsset(@ModelAttribute AssetRequest request, HttpSession session) {

	     String loggedAdminEmpId = (String) session.getAttribute("loggedAdminEmpId");

	     if (request.getEmpId() == null || request.getEmpId().trim().isEmpty()) {
	         request.setEmpId(loggedAdminEmpId);
	     }

	     request.setRequested_by(loggedAdminEmpId);
	     request.setAssigned_asset_qty(0);
	     request.setStatus(0);
	     assetRequestService.saveRequest(request);

	     return ResponseEntity.ok("Success"); // Return simple OK response
	 }
	 
	 @PostMapping("/updateAsset")
	 @ResponseBody
	 public ResponseEntity<Map<String, String>> updateAsset(@RequestBody Assetes asset,HttpSession session) {
	     Optional<Assetes> existingAssetOpt = assetRepository.findById(asset.getId());
	     
	     if (existingAssetOpt.isPresent()) {
	    	 
	    	 Assetes existingAsset = existingAssetOpt.get();
	    	 
	    	 int newQuantity = asset.getQuantity();
	    	 int oldQuantity = existingAsset.getQuantity();
	    	 LocalDateTime now = LocalDateTime.now();
	    	 String loggedAdmin = (String) session.getAttribute("loggedAdminEmpId");
	    	 
	    	 DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    	 String formattedDateTime = now.format(formater);
	    	 
	    	/* existingAsset.setQuantity(asset.getQuantity());
	    	 existingAsset.setQuantity((oldQuantity + newQuantity));
	    	 existingAsset.setModifieddate(formattedDateTime);
	    	 existingAsset.setModified_empid(loggedAdmin);
	    	 existingAsset.setAction("modified");
	    	 existingAsset.setAssigned_to("");*/
	    	   // ✅ Case 1: Increase (same as your current code)
	         if (newQuantity > 0) {
	             existingAsset.setQuantity(oldQuantity + newQuantity);
	             existingAsset.setModifieddate(formattedDateTime);
	             existingAsset.setModified_empid(loggedAdmin);
	             existingAsset.setAction("modified");
	             existingAsset.setAssigned_to("");
	         }

	         // ✅ Case 2: Decrease (special handling with remarks)
	         else if (newQuantity < 0) {
	             int decreaseBy = Math.abs(newQuantity);

	             if (oldQuantity < decreaseBy) {
	                 Map<String, String> response = new HashMap<>();
	                 response.put("message", "Cannot decrease more than available quantity!");
	                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	             }

	             existingAsset.setQuantity(oldQuantity - decreaseBy);
	             existingAsset.setModifieddate(formattedDateTime);
	             existingAsset.setModified_empid(loggedAdmin);
	             existingAsset.setAction("modified");
	             existingAsset.setAssigned_to("");
	             existingAsset.setRemarks(asset.getRemarks()); // ✅ remarks required
	         }

	         // No change if entered 0
	         else {
	             Map<String, String> response = new HashMap<>();
	             response.put("message", "No change in quantity");
	             return ResponseEntity.ok(response);
	         }

	    	 
	         assetRepository.save(existingAsset); // Update existing asset
	         
	         Map<String, String> response = new HashMap<>();
	         response.put("message", "Asset updated successfully");
	         return ResponseEntity.ok(response);
	         
	     } else {
	         Map<String, String> response = new HashMap<>();
	         response.put("message", "Asset not found");
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	     }
	 }

	 @DeleteMapping("/deleteAsset/{id}")
	    public ResponseEntity<String> deleteAsset(@PathVariable Integer id) {
	        try {
	            boolean deleted = assetRequestService.deleteAssetById(id);
	            if (deleted) {
	                return ResponseEntity.ok("Asset deleted successfully!");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset not found!");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting asset: " + e.getMessage());
	        }
	    }

	 
	 /*@PostMapping("/assets_save") 
	 public String saveAsset(@ModelAttribute Assetes asset,HttpSession session,Model model, RedirectAttributes redirectAttributes) {
		 
		   String loggedAdmin = (String) session.getAttribute("loggedAdminEmpId"); 
		 
		   boolean alreadyExists = assetRequestService.existsByAssetNameAndDescription(
			        asset.getAsset_name(), asset.getDescription());

			if (alreadyExists) {
				System.out.println("errorrr....");
				redirectAttributes.addAttribute("error", "exists");
			   return "redirect:/asset?error=exists";
			}
		   
	       assetRequestService.saveAsset(asset,loggedAdmin);
	       redirectAttributes.addAttribute("success", "true");
	       return "redirect:/asset?success";
	       
	}*/
	 
	 @PostMapping("/assets_save")
	 @ResponseBody
	 public ResponseEntity<String> saveAsset(@ModelAttribute Assetes asset, HttpSession session) {
		 
	     String loggedAdmin = (String) session.getAttribute("loggedAdminEmpId");

	     boolean alreadyExists = assetRequestService.existsByAssetNameAndDescription(
	         asset.getAsset_name(), asset.getDescription());

	     if (alreadyExists) {
	         return ResponseEntity.status(HttpStatus.CONFLICT).body("Asset already exists");
	     }
	     

	     assetRequestService.saveAsset(asset, loggedAdmin);
	     return ResponseEntity.ok("Asset saved successfully");
	     
	 }

	 
	  @GetMapping("/get-descriptions-asset-type")
	  public ResponseEntity<List<String>> getDescriptionsByAssetType(@RequestParam("assetType") String assetType) {
		  
	        List<String> descriptions = assetRepository.findDescriptionsByAssetType(assetType);

	        if (descriptions.isEmpty()) {
	            return ResponseEntity.noContent().build(); // Returns 204 No Content if no descriptions found
	        }
	        return ResponseEntity.ok(descriptions); // Returns 200 OK with the descriptions
	        
	 }
	 
	  @GetMapping("/get-asset-id")
	  @ResponseBody
	  public Map<String, String> getAssetId(
	          @RequestParam String assetType,
	          @RequestParam String description) {

	      Optional<Assetes> asset = assetRepository.findByAssetNameAndDescription(assetType, description.trim());
	     
	      Map<String, String> response = new HashMap<>();
	      if (asset.isPresent()) {
	    	  System.out.println(" asset.get().getAsset_id()---"+asset.get().getAsset_id());
	          response.put("assetId", asset.get().getAsset_id());
	          response.put("remainingQty",String.valueOf(asset.get().getQuantity()));
	      } else {
	          response.put("assetId", ""); // or handle not found
	      }
	      return response;
	  }

	  
	    @PostMapping("/assigned_assets_save")
	    public String assignAsset(@ModelAttribute AssignedAssets assignedAsset,@RequestParam("referAssetIds") List<String> referAssetIds,  @RequestParam("remarks") List<String> remarks,@RequestParam("sourceTab") String sourceTab) {
	    	 
	        // Save the assigned asset
	    	assetRequestService.saveAsset(assignedAsset, referAssetIds,remarks);
	    	
	    	return "redirect:/asset?tab=" + sourceTab;
	        
	    } 
	    
	    @GetMapping("/getAssetLogs")
	    public ResponseEntity<List<AssetUpdateLog>> getAssetLogs(@RequestParam String assetId) {
	    	
	        List<AssetUpdateLog> logs = assetRequestService.getLogsByAssetId(assetId);
	        
	        return ResponseEntity.ok(logs);
	    }
	    
	    //method to approve asset from asset admin side
	    @PostMapping("/approveAssetRequest")
	    public ResponseEntity<String> approveAssetRequest(@RequestBody Map<String, String> payload) {
	        Long requestId = Long.parseLong(payload.get("requestId"));
	        
	        // Call service to approve the request
	        boolean success = assetRequestService.approveRequestById(requestId);
	        	
	        if (success) {
	            return ResponseEntity.ok("Approved");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to approve");
	        }
	         
	    }
	    
	    @PostMapping("/updateAssetActiveInactiveStatus")
	    public ResponseEntity<String> updateAssetStatus(@RequestBody Map<String, Object> data) {
	    	
	     try {
	    	Integer assetId = Integer.valueOf(data.get("assetId").toString());
	        boolean isActive = Boolean.parseBoolean(data.get("active").toString());
	        
	        boolean updated = assetRequestService.updateAssetStatus(assetId, isActive);
	        
	        if (updated) {
	            return ResponseEntity.ok("Status updated successfully");
	        } else {
	            return ResponseEntity.status(500).body("Failed to update status");
	        }
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(400).body("Invalid data format");
	    }
	     
	}
	    
	    
	    @GetMapping("/validate-asset-availability")
	    public ResponseEntity<Boolean> validateAssetAvailability(
	            @RequestParam String assetType,
	            @RequestParam String description,
	            @RequestParam int quantity) {
	        
	        boolean isAvailable = assetRequestService.isAssetAvailable(assetType, description, quantity);
	        return ResponseEntity.ok(isAvailable);
	    }
	    
	 /*   @GetMapping("/validate-ref-id")
	    public ResponseEntity<Boolean> validateRefId(@RequestParam String refId) {
	        boolean exists = AssignedAssetDetailsRepo.existsByRefAssetId(refId);
	        return ResponseEntity.ok(!exists); 
	        // true = available, false = already used
	    }*/
	    @GetMapping("/validate-ref-id")
	    public ResponseEntity<Boolean> validateRefId(
	            @RequestParam String refId,
	            @RequestParam String assetType,
	            @RequestParam String description) {

	        boolean exists = AssignedAssetDetailsRepo
	            .existsByRefAssetIdAndAssetTypeAndDescription(refId, assetType, description);
	     // Log for debugging
	        System.out.println("Validating Ref ID: " + refId + ", Asset Type: " + assetType + ", Description: " + description);
	        System.out.println("Exists in DB: " + exists + " | Available: " + !exists);

	        return ResponseEntity.ok(!exists); // true = available, false = already used
	    }

	 /*   @GetMapping("/validate-asset-availability")
	    public ResponseEntity<Map<String, Object>> validateAssetAvailability(
	            @RequestParam String assetType,
	            @RequestParam String description,
	            @RequestParam int quantity,
	            @RequestParam(required = false) String refAssetId) {

	        Map<String, Object> response = new HashMap<>();

	        // Existing quantity check
	        boolean isAvailable = assetRequestService.isAssetAvailable(assetType, description, quantity);
	        response.put("quantityAvailable", isAvailable);

	        // New ref_asset_id check
	        boolean isRefAssigned = false;
	        if (refAssetId != null && !refAssetId.isEmpty()) {
	            isRefAssigned = assetRequestService.isRefAssetAlreadyAssigned(refAssetId);
	        }
	        response.put("refAssigned", isRefAssigned);

	        return ResponseEntity.ok(response);
	    }*/

	    
	    @GetMapping("/getAssignedAssetById")
	    public ResponseEntity<List<AssignedAssetDetailsDTO>> getAssignedAssetById(@RequestParam String assetId){
	    	
	    	System.out.println("assetIdssssssss"+assetId);
	    	List<AssignedAssetDetailsDTO> assignedAsset = assetRequestService.getAssignedAssetByAssetId(assetId);
	    	assignedAsset.stream().forEach(i->System.out.println("i===="+i));
	   
	    	if (assignedAsset.isEmpty()) {
	            return ResponseEntity.ok(Collections.emptyList()); // ✅ Return empty array, not 204
	        }
	    	return ResponseEntity.ok(assignedAsset);
	    	
	    }
	    
	    @GetMapping("/getAssetsByEmpId")
	    public ResponseEntity<List<AssignedAssetDetailsDTO>> getAssetByEmpId(@RequestParam String empId){
	    	
	    	List<AssignedAssetDetailsDTO> assetListById = assetRequestService.getAssignedAssetByEmpId(empId);
	    	// assetListById.stream().forEach(i -> System.out.println("iiii"+i));
	    	if(assetListById.isEmpty()) {
	    		return ResponseEntity.noContent().build();
	    	}
	    	return ResponseEntity.ok(assetListById); // ✅ Send actual data
	    	
	    }
	    
	    @PostMapping("/asset/reject")
	    public ResponseEntity<String> rejectAsset(@RequestBody AssetRequest request) {
	        try {
	            assetRequestService.rejectRequest(request.getId());
	            return ResponseEntity.ok("Rejected");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error rejecting asset request");
	        }
	    }
	    
	    // Fetch all assigned assets
	  /*  @GetMapping("/assigned")
	    @ResponseBody
	    public List<AssignedAssets> getAllAssignedAssets() {
	        return AssignedAssetDetailsRepo.findAll();
	    }*/
 
	    @GetMapping("/assigned")
	    @ResponseBody
	    public List<AssignedAssets> getAllAssignedAssets() {
	        List<AssignedAssets> assets = AssignedAssetDetailsRepo.findAll();

	        assets.forEach(asset -> {
	            String empId = asset.getAssigned_to();
	            if (empId != null) {
	            	employeeRepository.findByEmpId(empId).ifPresent(emp -> {
	                    String fullName = emp.getFirstName() + " " + emp.getLastName();
	                    asset.setEmpName(fullName);
	                });
	            }
	        });

	        return assets;
	    }
}
