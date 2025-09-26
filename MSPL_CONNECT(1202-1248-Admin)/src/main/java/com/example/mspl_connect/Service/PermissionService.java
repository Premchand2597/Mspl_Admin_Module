package com.example.mspl_connect.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.transaction.Transactional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;

    public boolean updateInterviewPermission(String userId, boolean isEnabled) {
    	
        PermissionsEntity permission = permissionRepo.findByUserId(userId)
            .orElse(new PermissionsEntity());

        permission.setUserId(userId);
        permission.setInterviewAccess(isEnabled);
        
        //System.out.println("hello"+permission);
        
        try {
            permissionRepo.save(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
	public boolean updateSalesPermission(String userId, Boolean enabled) {
		//System.out.println("userId===="+userId);
        PermissionsEntity permission = permissionRepo.findByUserId(userId)
            .orElse(new PermissionsEntity());

        permission.setUserId(userId);
        permission.setSales(enabled);
        
        System.out.println("hello"+permission);
        try {
            permissionRepo.save(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean updateAccountsPermission(String userId, Boolean enabled) {
		System.out.println("userId===="+userId);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
		System.out.println("userId===="+permission);

	        permission.setUserId(userId);
	        permission.setAccountsAccess(enabled);
	        
	        try {
	            permissionRepo.save(permission);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	public boolean updateStorePermission(String userId, Boolean enabled) {
		System.out.println("userId===="+userId);
		System.out.println("enabled===="+enabled);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(userId);
	        permission.setStoreAccess(enabled);;
	        
	        try {
	            permissionRepo.save(permission);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	public  Optional<PermissionsEntity> getGrantedPermissions(String empid) {
	   	 return permissionRepo.findByUserId(empid);
	   }
	
	@Transactional
	public int updateDocUploadPermissionFlag() {
		System.out.println("updateDocUploadPermissionFlaggggggggggggggg");
		return permissionRepo.updateDocUploadPermissionFlag();
	}

}
