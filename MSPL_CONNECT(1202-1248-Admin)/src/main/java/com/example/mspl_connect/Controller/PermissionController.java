package com.example.mspl_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	    }else if("storeAccess".equals(permissionType)) {
	    	isUpdated = permissionService.updateStorePermission(userId, enabled);
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

}
