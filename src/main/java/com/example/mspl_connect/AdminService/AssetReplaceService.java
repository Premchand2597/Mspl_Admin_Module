package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetReplace;
import com.example.mspl_connect.AdminEntity.AssignedAssets;
import com.example.mspl_connect.AdminRepo.AssetReplaceRepository;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssetReturnRepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.PermissionRepo;


import jakarta.servlet.http.HttpSession;

@Service
public class AssetReplaceService {
	
	 @Autowired
	    private AssetReplaceRepository assetReplaceRepo;

	  @Autowired
	    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
	 
	 @Autowired
	    private AssetRepository assetRepository;
 
	 @Autowired
	    private AssetReplaceRepository assetReplaceRepository;

	 @Autowired
	    private PermissionRepo permissionRepo;
	 
		@Autowired
		private EmployeeRepository employeeRepository;
		
		public List<AssetReplace> getAllReplacements() {
		    List<AssetReplace> replacements = assetReplaceRepo.findAll();

		    replacements.forEach(req -> {
		        String empId = req.getSenderEmpId();
		        if (empId != null) {
		            employeeRepository.findByEmpId(empId).ifPresent(emp -> {
		                String fullName = emp.getFirstName() + " " + emp.getLastName();
		                req.setSenderName(fullName); // make sure AssetReplace has senderName field

		                // Print details
		                System.out.println("AssetReplace ID: " + req.getId() +
		                                   ", Asset ID: " + req.getAssetId() +
		                                   ", Sender: " + fullName);
		            });
		        } else {
		            System.out.println("AssetReplace ID: " + req.getId() +
		                               ", Asset ID: " + req.getAssetId() +
		                               ", Sender ID is null");
		        }
		    });

		    return replacements;
		}
		 // Add this method
	
	    
	    // Get asset replace notifications count for logged-in user
	    public Integer getAssetReplaceNotification(HttpSession session) {
	        // Count total pending asset replace notifications
	        Integer pendingCount = assetReplaceRepository.countPendingAssetReplaces();
	        System.out.println("Pending Asset Replace Notifications Count: " + pendingCount);

	        // Fetch all employees who are asset admins
	        List<String> assetAdmins = permissionRepo.findAllAssetAdmins();
	        System.out.println("Asset Admins: " + assetAdmins);

	        if (assetAdmins.isEmpty()) {
	            System.out.println("No Asset Admins found. Returning 0 notifications.");
	            return 0;
	        }

	        // ✅ Get logged-in employee email from session
	        String email = (String) session.getAttribute("email");

	        // ✅ Convert email → empId
	        String empId = employeeRepository.findEmpidByEmail(email);
	        System.out.println("Logged-in EmpId: " + empId);

	        // ✅ Check if this empId is in assetAdmins list
	        if (assetAdmins.contains(empId)) {
	            System.out.println("EmpId " + empId + " is an Asset Admin. Returning count.");
	            return pendingCount;  // Show notification
	        } else {
	            System.out.println("EmpId " + empId + " is NOT an Asset Admin. Returning 0.");
	            return 0;  // No notifications for non-admins
	        }
	    }


	    public void replaceAssets(List<AssetReplace> assetRequests, String empId) {
	        for (AssetReplace req : assetRequests) {
	            AssignedAssets asset = AssignedAssetDetailsRepo.findById(req.getId()).orElse(null);
	            if (asset != null) {

	                boolean existsPending = assetReplaceRepo.existsByAssignedAssetIdAndRefAssetId(
	                        asset.getAssigned_asset_id(),
	                        asset.getRef_asset_id()
	                );

	                if (existsPending) {
	                    throw new RuntimeException("Asset " + asset.getAsset_type() + " already has a pending replacement request!");
	                }

	                AssetReplace assetReplace = new AssetReplace();
	                assetReplace.setAssignedAssetId(asset.getAssigned_asset_id());
	                assetReplace.setAssetId(asset.getAsset_id());
	                assetReplace.setAssetType(asset.getAsset_type());
	                assetReplace.setQuantity(asset.getQuantity());
	                assetReplace.setRefAssetId(asset.getRef_asset_id());
	                assetReplace.setDescription(asset.getDescription());
	                assetReplace.setSenderEmpId(empId);
	                assetReplace.setRequestedAt(LocalDateTime.now());
	                assetReplace.setRemarks(req.getRemarks());
	                //assetReplace.setReplacementAssetId(req.getReplacementAssetId());
	                assetReplace.setStatus("Pending");
	                assetReplace.setNotification(true); 
	             // ✅ Save the old asset id coming from frontend
	                assetReplace.setOldAssetId(req.getId());
	                assetReplaceRepo.save(assetReplace);
	            }
	        }
	    }


	    public List<AssetReplace> getReplacementsByEmployee(String empId) {
	        return assetReplaceRepo.findBySenderEmpId(empId);
	    }

	}

