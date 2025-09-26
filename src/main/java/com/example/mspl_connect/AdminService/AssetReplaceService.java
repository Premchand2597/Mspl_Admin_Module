package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetReplace;
import com.example.mspl_connect.AdminRepo.AssetReplaceRepository;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssetReturnRepo;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.PermissionRepo;

@Service
public class AssetReplaceService {
	
	 @Autowired
	    private AssetReplaceRepository assetReplaceRepo;

	  @Autowired
	    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
	 
	 @Autowired
	    private AssetRepository assetRepository;
 
	 
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

}
