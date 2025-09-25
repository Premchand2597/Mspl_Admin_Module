package com.example.mspl_connect.AdminService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetReturn;
import com.example.mspl_connect.AdminEntity.Assetes;
import com.example.mspl_connect.AdminEntity.AssignedAssets;
import com.example.mspl_connect.AdminEntity.PermissionsListsDisplay_Entity;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssetRequestRepository;
import com.example.mspl_connect.AdminRepo.AssetReturnRepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.PermissionRepo;

@Service
public class AssetReturnService {
	
	 @Autowired
	    private AssetReturnRepo repo;

	  @Autowired
	    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
	 
	 @Autowired
	    private AssetRepository assetRepository;
    
	 
	 @Autowired
	    private PermissionRepo permissionRepo;
	 
		@Autowired
		private EmployeeRepository employeeRepository;
   /* public List<AssetReturn> getAllRequests() {
        return repo.findAll();
    }*/

    public List<AssetReturn> getAllRequests() {
        List<AssetReturn> assets = repo.findAll();

        assets.forEach(asset -> {
            String empId = asset.getSenderEmpId();
            if (empId != null) {
                employeeRepository.findByEmpId(empId).ifPresent(emp -> {
                    String fullName = emp.getFirstName() + " " + emp.getLastName();
                    asset.setSenderName(fullName);
                });
            }
        });

        return assets;
    }

    /*public AssetReturn approveRequest(int id) {
        AssetReturn request = repo.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Approved");
       // request.setRemarks(null); // clear remarks on approval
        return repo.save(request);
    }*/

  /*  public AssetReturn approveRequest(int id, String approverEmail) {
        AssetReturn request = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        request.setStatus("Approved");
        request.setApprovedBy(approverEmail);          // set approver
        request.setApprovedAt(LocalDateTime.now());   // set approval timestamp
        request.setApprovedName("Asset Admin");  
        return repo.save(request);
    }*/

    public AssetReturn approveRequest(int id, String approverEmpId) {
        // 1. Check if approver has asset_admin permission
        System.out.println("Checking permissions for approver: " + approverEmpId);
        PermissionsEntity approver = permissionRepo
                .findByUserId(approverEmpId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));
        System.out.println("Approver found: " + approver.getUserId() + ", Asset Admin: " + approver.getAsset_admin());

        if (!Boolean.TRUE.equals(approver.getAsset_admin())) {
            System.out.println("Approver does not have Asset Admin permissions!");
            throw new RuntimeException("You do not have Asset Admin permissions to approve this request");
        }


        // 2. Get return request
        System.out.println("Fetching return request with ID: " + id);
        AssetReturn request = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        System.out.println("Return request found: Asset ID = " + request.getAssetId() + ", Quantity = " + request.getQuantity());

        // 3. Update status
        System.out.println("Approving the request...");
        request.setStatus("Approved");
        request.setApprovedBy(approverEmpId);
        request.setApprovedAt(LocalDateTime.now());
        request.setApprovedName("Asset Admin");
        System.out.println("Request status updated to Approved");

        // 4. Update assets quantity
        System.out.println("Fetching asset with Asset ID: " + request.getAssetId() + " and Type: " + request.getAssetType());
        Assetes asset = assetRepository.findByAssetIdAndType(request.getAssetId(), request.getAssetType())
                .orElseThrow(() -> new RuntimeException("Asset not found in inventory"));
        System.out.println("Asset found: Name = " + asset.getAsset_name() + ", Type = " + asset.getAsset_name() + ", Current Quantity = " + asset.getQuantity());

        int updatedQuantity = asset.getQuantity() + request.getQuantity();
        asset.setQuantity(updatedQuantity);
        asset.setModifieddate(LocalDateTime.now().toString());
        asset.setModified_empid(approverEmpId);
        asset.setAction("Returned");
        System.out.println("Asset quantity updated: New Quantity = " + asset.getQuantity());

        assetRepository.save(asset);
        System.out.println("Asset changes saved to database");

        // 5. Update AssignedAssets to reflect return
     // 5. Update AssignedAssets to reflect return
        System.out.println("Updating assigned assets...");
        AssignedAssets assignedAsset = AssignedAssetDetailsRepo
            .findByAssignedAssetIdAndAssignedToAndAssetTypeAndDescriptionAndAssetIdAndRefAssetId(
                request.getAssignedAssetId(),
                request.getSenderEmpId(),
                request.getAssetType(),
                request.getDescription(),
                request.getAssetId(),
                request.getRefAssetId()   // include reference asset ID
            )
            .orElseThrow(() -> new RuntimeException("Assigned asset record not found"));

     // Instead of null, assign the approvedBy value
        assignedAsset.setAssigned_to(request.getApprovedBy());   // mark as no longer assigned
        assignedAsset.setRemarks("Returned to inventory");
        AssignedAssetDetailsRepo.save(assignedAsset);
        System.out.println("AssignedAssets updated: Asset is no longer assigned to " + request.getSenderEmpId());

        // 5. Save request status
        AssetReturn savedRequest = repo.save(request);
        System.out.println("Request saved with status: " + savedRequest.getStatus());

        return savedRequest;
    }

    
    public AssetReturn rejectRequest(int id, String remarks) {
        AssetReturn request = repo.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Rejected");
        request.setRejectionRemarks(remarks);
        return repo.save(request);
    }
    
    
}
