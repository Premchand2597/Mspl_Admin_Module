package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetRequest;
import com.example.mspl_connect.AdminEntity.AssetUpdateLog;
import com.example.mspl_connect.AdminEntity.Assetes;
import com.example.mspl_connect.AdminEntity.AssignedAssetDetailsDTO;
import com.example.mspl_connect.AdminEntity.AssignedAssets;
import com.example.mspl_connect.AdminEntity.assetsDTO;
import com.example.mspl_connect.AdminRepo.AssetLogRepository;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssetRequestRepository;
import com.example.mspl_connect.AdminRepo.AssetsDTORepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetDetailsRepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;

import jakarta.servlet.http.HttpSession;

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
    private AssetLogRepository assetLogRepository;
	
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
	
}
 