package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetReplace;
import com.example.mspl_connect.AdminEntity.AssetRequest;
import com.example.mspl_connect.AdminEntity.AssetUpdateLog;
import com.example.mspl_connect.AdminEntity.Assetes;
import com.example.mspl_connect.AdminEntity.AssignedAssetDetailsDTO;
import com.example.mspl_connect.AdminEntity.AssignedAssets;
import com.example.mspl_connect.AdminEntity.assetsDTO;
import com.example.mspl_connect.AdminRepo.AssetLogRepository;
import com.example.mspl_connect.AdminRepo.AssetReplaceRepository;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssetRequestRepository;
import com.example.mspl_connect.AdminRepo.AssetsDTORepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetDetailsRepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;

@Service
public class AssetRequestService {
	
	@Autowired
    private AssetRequestRepository assetRequestRepository;
	
	@Autowired
	private AssetsDTORepo assetsDTORepo;
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssignedAssetsRepo assignedAssetsRepo; 
	
	@Autowired
	private AssetReplaceRepository assetReplaceRepository; 
	
	@Autowired
    private AssetLogRepository assetLogRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	
	@Autowired
	private AssignedAssetDetailsRepo assignedAssetDetailsRepo;
	
	 public void saveRequest(AssetRequest assetRequest) {  
		 
		 LocalDateTime localDateTime = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String formatedDateTime = localDateTime.format(formatter);
		 
		 assetRequest.setAsset_approved(0);
		 assetRequest.setReq_date(formatedDateTime);
		 assetRequestRepository.save(assetRequest);
		 
	 }
	 
	 public List<assetsDTO> findAllAssetsRequests(){
		 return assetsDTORepo.findAll();
	 }
	 
	 public List<AssetRequest> getAssetsAndrequestsByEmpId(String empid){
		 return assetRequestRepository.findAllByEmpId(empid);
	 }

	 public void saveAsset(Assetes asset,String loggedAdmin) {
		 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Define your format
		String formattedDate = LocalDateTime.now().format(formatter); // Convert LocalDateTime to String
			
		Optional<Integer> latestassetIdOpt = assetRepository.getLAtestId();
		System.out.println("pppppp" + latestassetIdOpt);
		Integer latestassetId = 0;
		
		if(latestassetIdOpt.isEmpty()) {
			latestassetId = 0;
		} else {
			latestassetId = latestassetIdOpt.get();
		}
		
		String assetId = "MSPL/IT/" + (latestassetId + 1) ;
		System.out.println("assetId--"+assetId);
		
		asset.setModifieddate(formattedDate); // Set formatted date-time
		asset.setAsset_id(assetId);
		asset.setModified_empid(loggedAdmin);
		asset.setActive_inactive_status(1);
		asset.setDescription(asset.getDescription().trim());
		
	    assetRepository.save(asset); 
	     
	 }
	 
	 public List<Assetes> getAllAssets(){
		  return assetRepository.findAll();
	 }
	 
	 public List<String> getAllAssetsName(){
		  return assetRepository.findAllAssetNames();
	 }

	 public void saveAsset(AssignedAssets assignedAsset, List<String> referAssetIds,List<String> remarks) {
		 System.out.println("assignedAsset=="+assignedAsset);
		 System.out.println("referAssetIds-"+referAssetIds);
		 System.out.println("remarks=" + remarks);
		 //String assignedId = "MSPL/01";
		 //assignedAsset.setAssigned_asset_id(assignedId);
		 
		 Integer id = assignedAsset.getId();
		 Long requestId = id != null ? id.longValue() : null;
		 
		 Integer assigningQty = assignedAsset.getQuantity();
		 
		 String description = assignedAsset.getDescription();
		 String asset_type = assignedAsset.getAsset_type();
		 
		 
		    //String assigned_asset_id = null;
		    // Save assigned asset multiple times based on quantity
		    for (int i = 0; i < assignedAsset.getQuantity(); i++) {
		    					
		    				Optional<Integer> assignedAssetIdopt = assignedAssetsRepo.getLatestAssignedId();
		    					
		    	            Integer recentAssignedAssetId = assignedAssetIdopt.orElse(0) + 1;
		    				 System.out.println(" hiii "+recentAssignedAssetId); 	
		    				// Integer recentAssignedAssetId = assignedAssetIdopt.get();
		    		        String assigned_asset_id = "MSPL/"+ recentAssignedAssetId; 
		    		   	 System.out.println(" hiii "+assigned_asset_id); 
		    		        // System.out.println("aaaa"+assigned_asset_id);
		    		        AssignedAssets newAssignedAsset = new AssignedAssets();
		    		        newAssignedAsset.setAssigned_to(assignedAsset.getAssigned_to());
		    		        newAssignedAsset.setAsset_type(assignedAsset.getAsset_type());
		    		        newAssignedAsset.setDescription(assignedAsset.getDescription());
		    		       // newAssignedAsset.setRemarks(assignedAsset.getRemarks());
		    		        newAssignedAsset.setQuantity(1); // Save as individual records		    		        
		    		        newAssignedAsset.setAssigned_asset_id(assigned_asset_id);
		    		        newAssignedAsset.setAsset_id(assignedAsset.getAsset_id());
		    		         
		    		        // ✅ Set refer_asset_id if available
		    		        if (referAssetIds != null && i < referAssetIds.size()) {
		    		            newAssignedAsset.setRef_asset_id(referAssetIds.get(i));
		    		        }
		    		        // ✅ set remarks per row
		    		        if (remarks != null && i < remarks.size()) {
		    		            newAssignedAsset.setRemarks(remarks.get(i));
		    		        }

		    		        assignedAssetsRepo.save(newAssignedAsset); 
		    		          
		    }
		 
		 Optional<Assetes>  getAssetByAssetNameAndDescriptionopt =  assetRepository.findByAssetNameAndDescription(asset_type,description);

		 if(getAssetByAssetNameAndDescriptionopt.isPresent()) { 
			
			 Assetes getAssetByAssetNameAndDescription = getAssetByAssetNameAndDescriptionopt.get();
			 
			 // reduce the quantity in assets table after assign bassed on asset type and desc
			 int remainingQuantity = (getAssetByAssetNameAndDescription.getQuantity() - assignedAsset.getQuantity());
			 getAssetByAssetNameAndDescription.setQuantity(remainingQuantity);
			 getAssetByAssetNameAndDescription.setAction("assigned");
			 getAssetByAssetNameAndDescription.setAssigned_to(assignedAsset.getAssigned_to());
			 
			 assetRepository.save(getAssetByAssetNameAndDescription);
			 
			 //update asset_request table ,reduce qty based on hw much qty is assigneds
			 Optional<AssetRequest> getAssetRequestByIdopt = assetRequestRepository.findById(requestId);
			 if(getAssetRequestByIdopt.isPresent()) {
				 
				 AssetRequest getAssetRequestById = getAssetRequestByIdopt.get();
				 Integer oldQuantity = getAssetRequestById.getAssigned_asset_qty();
				 assigningQty += oldQuantity;
				 getAssetRequestById.setAssigned_asset_qty(assigningQty);
				 
				 assetRequestRepository.save(getAssetRequestById);		 
				 
			 }
		 }
	 }
	 
	 public void replaceAsset(AssignedAssets assignedAsset,
             List<String> referAssetIds,
             List<String> remarks,
             Integer oldAssetId,
             Integer requestId) {

System.out.println("replaceAsset called with oldAssetId=" + oldAssetId +
           ", requestId=" + requestId +
           ", assignedAsset=" + assignedAsset);

if (oldAssetId == null) {
System.out.println("Old Asset ID is null, exiting method.");
return;
}

// Fetch existing assigned asset row
Optional<AssignedAssets> existingOpt = assignedAssetsRepo.findById(oldAssetId);
if (existingOpt.isPresent()) {
AssignedAssets existing = existingOpt.get();
System.out.println("Existing assigned asset found: " + existing);

// Fetch asset by type and description
Optional<Assetes> assetOpt = assetRepository.findByAssetNameAndDescription(
    assignedAsset.getAsset_type(), assignedAsset.getDescription());

if (assetOpt.isPresent()) {
Assetes asset = assetOpt.get();
System.out.println("Matching asset found in assets table: " + asset);

// Set the asset_id in assigned asset
existing.setAsset_id(asset.getAsset_id());
System.out.println("Set asset_id in assigned asset: " + asset.getAsset_id());

// Reduce quantity in assets table
int remainingQuantity = asset.getQuantity() - assignedAsset.getQuantity();
System.out.println("Reducing asset quantity from " + asset.getQuantity() + " to " + remainingQuantity);
asset.setQuantity(remainingQuantity);
asset.setAction("assigned");
asset.setAssigned_to(assignedAsset.getAssigned_to());
assetRepository.save(asset);
System.out.println("Updated asset in assets table.");

// Update assignedAssets fields
existing.setAsset_type(assignedAsset.getAsset_type());
existing.setDescription(assignedAsset.getDescription());
existing.setQuantity(assignedAsset.getQuantity());

if (remarks != null && !remarks.isEmpty()) {
    existing.setRemarks(remarks.get(0));
    System.out.println("Set remarks: " + remarks.get(0));
}

if (referAssetIds != null && !referAssetIds.isEmpty()) {
    existing.setRef_asset_id(referAssetIds.get(0));
    System.out.println("Set refer_asset_id: " + referAssetIds.get(0));
}

assignedAssetsRepo.save(existing);
System.out.println("Updated assigned asset in assignedAssetsRepo.");

// Update assetRequest table
if (requestId != null) {
    Optional<AssetRequest> requestOpt = assetRequestRepository.findById(requestId.longValue());
    if (requestOpt.isPresent()) {
        AssetRequest request = requestOpt.get();
        int oldAssignedQty = request.getAssigned_asset_qty() != null ? request.getAssigned_asset_qty() : 0;
        request.setAssigned_asset_qty(oldAssignedQty + assignedAsset.getQuantity());
        assetRequestRepository.save(request);
        System.out.println("Updated assigned_asset_qty in assetRequest: " + request.getAssigned_asset_qty());
    } else {
        System.out.println("AssetRequest not found for ID: " + requestId);
    }
}
//After updating assigned asset and asset request
Optional<AssetReplace> replaceOpt = assetReplaceRepository.findByOldAssetId(oldAssetId);

if (replaceOpt.isPresent()) {
 AssetReplace assetReplace = replaceOpt.get();
 assetReplace.setStatus("Assigned"); // ✅ Change status
 assetReplace.setReplacementAssetId(existing.getAssigned_asset_id()); // set the new asset id
 assetReplace.setApprovedAt(LocalDateTime.now());
 assetReplace.setApprovedName(assignedAsset.getAssigned_to()); // whoever assigned it
 assetReplaceRepository.save(assetReplace);
 System.out.println("AssetReplace status updated to Assigned.");
} else {
 System.out.println("No AssetReplace record found for oldAssetId=" + oldAssetId);
}


} else {
System.out.println("Asset not found for type: " + assignedAsset.getAsset_type() +
                   " and description: " + assignedAsset.getDescription());
throw new RuntimeException("Asset not found.");
}

} else {
System.out.println("Assigned asset not found for ID: " + oldAssetId);
}
}



	 
	 public boolean deleteAssetById(Integer id) {
	     if (assetRepository.existsById(id)) {
	          assetRepository.deleteById(id);
	          return true;
	     }
	     return false;
	 }
	 
	 
	 public List<AssetUpdateLog> getLogsByAssetId(String assetId) {
	        return assetLogRepository.findByAssetIdOrderByUpdatedAtDesc(assetId);
	    }

	public boolean approveRequestById(Long requestId) {
		 
		Optional<AssetRequest> getAssetRequestById = assetRequestRepository.findById(requestId);
		
		if(getAssetRequestById.isPresent()) {
			AssetRequest assetReq = getAssetRequestById.get();
			assetReq.setAsset_approved(1);
			assetReq.setStatus(2);
			
			assetRequestRepository.save(assetReq);	
			return true;
			
		}
		
		return false;
	}

	public boolean updateAssetStatus(Integer assetId, boolean isActive) {
		
		Optional<Assetes>  getAssetById =  assetRepository.findById(assetId);
		
		if(getAssetById.isPresent()) {
			
			Assetes assetById = getAssetById.get();
			System.out.println("assetById---"+assetById);
			
			if(isActive)
				assetById.setActive_inactive_status(1);
			else
				assetById.setActive_inactive_status(0);
			
			assetRepository.save(assetById); 
			return  true;
			
		}
		
		return false;
	}

	public boolean isAssetAvailable(String assetType, String description, int quantity) {
		
		Optional<Assetes> asset = assetRepository.findByAssetNameAndDescription(assetType, description);
	    return asset.isPresent() && asset.get().getQuantity() >= quantity;
	    
	}
  
	public boolean isRefAssetAlreadyAssigned(String refAssetId) {
	    // Checks if this ref_asset_id exists in AssignedAssets table
	    return assignedAssetsRepo.existsByRefAssetId(refAssetId);
	}

	
	public List<AssignedAssetDetailsDTO> getAssignedAssetByAssetId(String assetId){
		return assignedAssetDetailsRepo.getAssignedAssetDetailsByAssetId(assetId);
	}

	public List<AssignedAssetDetailsDTO> getAssignedAssetByEmpId(String empId) {
		// TODO Auto-generated method stub
		return assignedAssetDetailsRepo.getAssetByEmpId(empId);
	}
	
	public boolean existsByAssetNameAndDescription(String assetName, String description) {
	    return assetRepository.existsByAssetNameAndDescription(assetName, description);
	}

	public void rejectRequest(Long id) {
	    int updated = assetRequestRepository.updateRejectStatusById(id);
	    if (updated == 0) {
	        throw new RuntimeException("No asset request found with ID: " + id);
	    }
	}	  
	
	
	/* public Map<String, Long> countAssetsByEmployee() {
	        List<Object[]> results = assignedAssetsRepo.countAssetsGroupedByEmployee();
	        Map<String, Long> empAssetCounts = new HashMap<>();

	        for (Object[] row : results) {
	            String empId = (String) row[0];
	            Long count = (Long) row[1];
	            empAssetCounts.put(empId, count);
	        }

	        return empAssetCounts;
	    }*/
	 
	 public Map<String, Long> countAssetsByEmployee() {
		    // Fetch raw counts grouped by empId
		    List<Object[]> results = assignedAssetsRepo.countAssetsGroupedByEmployee();
		    Map<String, Long> empAssetCounts = new HashMap<>();

		    for (Object[] row : results) {
		        String empId = (String) row[0];
		        Long count = (Long) row[1];

		        // Fetch full employee details using empId
		        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);

		        // Use full name if available, else fallback to empId
		        String empName = (empDetails != null) ? empDetails.getFullName() : empId;

		        empAssetCounts.put(empName, count);
		    }

		    return empAssetCounts;
		}
}
 