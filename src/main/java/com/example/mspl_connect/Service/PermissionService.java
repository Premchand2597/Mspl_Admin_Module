package com.example.mspl_connect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.PermissionsListsDisplay_Entity;
import com.example.mspl_connect.AdminRepo.PermissionsListsDisplay_Repo;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.transaction.Transactional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;
    
    @Autowired
    private PermissionsListsDisplay_Repo permissionsListsDisplay_Repo;

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
		//System.out.println("userId===="+userId);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
		//System.out.println("userId===="+permission);

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
		//System.out.println("userId===="+userId);
		//System.out.println("enabled===="+enabled);
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
	
	public boolean updateAdminAssetPermission(String userId, Boolean enabled) {
		//System.out.println("userId===="+userId);
		//System.out.println("enabled===="+enabled);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(userId);
	        permission.setAsset_admin(enabled);
	        
	        try {
	            permissionRepo.save(permission);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	        
	}//
	
	public boolean updateCRMPermission(String userId, Boolean enabled) {
		//System.out.println("userId===="+userId);
		//System.out.println("enabled===="+enabled);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(userId);
	        permission.setCrm(enabled);
	        
	        try {
	            permissionRepo.save(permission);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	        
	}
	
	public boolean updateAttendanceLinkPermission(String userId, Boolean enabled) {
		//System.out.println("userId===="+userId);
		//System.out.println("enabled===="+enabled);
		PermissionsEntity permission = permissionRepo.findByUserId(userId)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(userId);
	        permission.setAttendance_link(enabled);
	        
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
	public int updateDocUploadPermissionFlag(String empId) {
		//System.out.println("updateDocUploadPermissionFlaggggggggggggggg");
		return permissionRepo.updateDocUploadPermissionFlag(empId);
	}
	
	public List<PermissionsListsDisplay_Entity> getAllPermissionsDetails(){
		return permissionsListsDisplay_Repo.fetchAllPermissionsDetails();
	}
	
	public List<PermissionsListsDisplay_Entity> getPermissionsDetailsBasedOnEmpId(List<String> empIds){
		return permissionsListsDisplay_Repo.fetchPermissionsDetailsBasedOnEmpId(empIds);
	}
	
public void disableOrEnableInterviewPermission(String emp_id, boolean checkStatus) {
		
		PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(emp_id);
	        permission.setInterviewAccess(checkStatus);;
	        
	        try {
	            permissionRepo.save(permission);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		//permissionRepo.disableOrEnableInterviewPermissionAccess(emp_id, checkStatus);
	}
	
	public void disableOrEnableAttendancePermission(String emp_id, boolean checkStatus) {
		
		PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(emp_id);
	        permission.setAttendance(checkStatus);;
	        
	        try {
	            permissionRepo.save(permission);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		//permissionRepo.disableOrEnableAttendancePermissionAccess(emp_id, checkStatus);
	}
	
	public void disableOrEnableAppraisalPermission(String emp_id, boolean checkStatus) {
		
		PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
	            .orElse(new PermissionsEntity());
		
	        permission.setUserId(emp_id);
	        permission.setAccountsAccess(checkStatus);;
	        
	        try {
	            permissionRepo.save(permission);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		//permissionRepo.disableOrEnableAppraisalPermissionAccess(emp_id, checkStatus);
	}
	
public void updateProfileAccessPermission(String emp_id, boolean checkStatus, String date) {
		
		PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
	            .orElse(new PermissionsEntity());
		
			if(date == null || date.isEmpty()) {
				permission.setDoc_date(permission.getDoc_date());
			}else {
				permission.setDoc_date(date);
			}
		
	        permission.setUserId(emp_id);
	        permission.setDoc_upload(checkStatus);
	        
	        try {
	            permissionRepo.save(permission);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		//permissionRepo.disableOrEnableAppraisalPermissionAccess(emp_id, checkStatus);
	}

public void disableOrEnablePreSalesPermission(String emp_id, boolean checkStatus) {
	
	PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
            .orElse(new PermissionsEntity());
	
        permission.setUserId(emp_id);
        permission.setCrm(checkStatus);;
        
        try {
            permissionRepo.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	//permissionRepo.disableOrEnableAttendancePermissionAccess(emp_id, checkStatus);
}

public void disableOrEnableBookOfKnowledgePermission(String emp_id, boolean checkStatus) {
	
	PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
            .orElse(new PermissionsEntity());
	
        permission.setUserId(emp_id);
        permission.setBook_of_knowledge(checkStatus);;
        
        try {
            permissionRepo.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	//permissionRepo.disableOrEnableAttendancePermissionAccess(emp_id, checkStatus);
}

public void disableOrEnableDispatchPermission(String emp_id, boolean checkStatus) {
	
	PermissionsEntity permission = permissionRepo.findByUserId(emp_id)
            .orElse(new PermissionsEntity());
	
        permission.setUserId(emp_id);
        permission.setDispatch(checkStatus);;
        
        try {
            permissionRepo.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	//permissionRepo.disableOrEnableAttendancePermissionAccess(emp_id, checkStatus);
}

public boolean updateAppraisalPermission(String userId, boolean enabled) {

	System.out.println("userId===="+userId);
	System.out.println("enabled===="+enabled);
	PermissionsEntity permission = permissionRepo.findByUserId(userId)
            .orElse(new PermissionsEntity());
	
        permission.setUserId(userId);
        permission.setApprisalAccess(enabled);;
        
        try {
            permissionRepo.save(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

}
