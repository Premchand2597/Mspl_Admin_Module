package com.example.mspl_connect.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.PermissionsListsDisplay_Entity;
import com.example.mspl_connect.Entity.InterviewPermissionRequest;
import com.example.mspl_connect.Service.PermissionService;

@RestController
@RequestMapping
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@PostMapping("/updateInterviewPermission")
	public ResponseEntity<String> updateInterviewPermission(@RequestBody InterviewPermissionRequest request) {
		
	    String userId = request.getUserId();
	    String permissionType = request.getPermissionType();
	    Boolean enabled = request.isEnabled();

	    System.out.println("Updating permission for user:::: " + userId + ", permission: " + permissionType);

	    // Update permissions based on permission type
	    boolean isUpdated;
	    if ("interviewAccess".equals(permissionType)) {
	        isUpdated = permissionService.updateInterviewPermission(userId, enabled);
	    } else if ("salesAccess".equals(permissionType)) {
	        isUpdated = permissionService.updateSalesPermission(userId, enabled);
	    } else if("accountsAccess".equals(permissionType)) {
	    	isUpdated = permissionService.updateAccountsPermission(userId, enabled);
	    } else if("storeAccess".equals(permissionType)) {
	    	isUpdated = permissionService.updateStorePermission(userId, enabled);
	    } else if("assetAdmin".equals(permissionType)) {
	    	isUpdated = permissionService.updateAdminAssetPermission(userId, enabled);
	    } else if("crm".equals(permissionType)) {
	    	isUpdated = permissionService.updateCRMPermission(userId, enabled);
	    }else if("attendanceLink".equals(permissionType)) {
	    	isUpdated = permissionService.updateAttendanceLinkPermission(userId, enabled);
	    }
	    
	    else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid permission type");
	    }

	    if (isUpdated) {
	        return ResponseEntity.ok("Permission updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update permission");
	    }
	}
	
	@PostMapping("/getAllPermissionsEnabledAndDisabledDataForHRActivityPage")
	@ResponseBody
	public List<PermissionsListsDisplay_Entity> getAllPermissionsData(@RequestBody List<Map<String, String>> empIds) {
	    List<String> employeeIds = empIds.stream()
	                                     .map(emp -> emp.get("userId"))
	                                     .filter(Objects::nonNull)
	                                     .collect(Collectors.toList());

	    //System.out.println("Employee IDs: " + employeeIds);

	    if (employeeIds.isEmpty()) {
	        return permissionService.getAllPermissionsDetails();
	    } else {
	        return permissionService.getPermissionsDetailsBasedOnEmpId(employeeIds);
	    }
	}
	
	@PostMapping("/updateDisableOrEnablePermissionsAsAGroupList")
	public void disableOrEnablePermissionAsAList(@RequestBody InterviewPermissionRequest request) {
		String userId = request.getUserId();
        String permissionType = request.getPermissionType();
        boolean enabled = request.isEnabled();
        //System.out.println("myss == "+userId+" "+permissionType+" "+enabled);
        if ("interviewAccess".equals(permissionType)) {
        	permissionService.disableOrEnableInterviewPermission(userId, enabled);
		}else if("attendanceAccess".equals(permissionType)) {
        	permissionService.disableOrEnableAttendancePermission(userId, enabled);
		}else if("appraisalAccess".equals(permissionType)) {
        	permissionService.disableOrEnableAppraisalPermission(userId, enabled);
		}else if("docUploadAccess".equals(permissionType)) {
			String date = request.getDate();
        	permissionService.updateProfileAccessPermission(userId, enabled, date);
		}else if("preSalesAccess".equals(permissionType)) {
        	permissionService.disableOrEnablePreSalesPermission(userId, enabled);
		}else if("bokAccess".equals(permissionType)) {
        	permissionService.disableOrEnableBookOfKnowledgePermission(userId, enabled);
		}else if ("salesAccess".equals(permissionType)) {
	        permissionService.updateSalesPermission(userId, enabled);
		}else if("storeAccess".equals(permissionType)) {
	    	permissionService.updateStorePermission(userId, enabled);
	    }else if("accountsAccess".equals(permissionType)) {
	    	permissionService.updateAccountsPermission(userId, enabled);
	    }else if("assetAdmin".equals(permissionType)) {
	    	permissionService.updateAdminAssetPermission(userId, enabled);
	    }else if("dispatchAccess".equals(permissionType)) {
        	permissionService.disableOrEnableDispatchPermission(userId, enabled);
		}
	}
	
	@GetMapping("/getAllPermissionsEnabledAndDisabledData")
	@ResponseBody
	public List<PermissionsListsDisplay_Entity> getAllPermissionsData(){
		return permissionService.getAllPermissionsDetails();
	}

}
