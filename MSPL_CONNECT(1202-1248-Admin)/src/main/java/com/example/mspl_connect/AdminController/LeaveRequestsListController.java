package com.example.mspl_connect.AdminController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminService.LeaveRequestService;
import com.example.mspl_connect.Repository.Login_Repo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")  // Adjust the base URL if necessary
public class LeaveRequestsListController {
	
	@Autowired
    private LeaveRequestService leaveRequestService;
	
	@Autowired
	private Login_Repo login_Repo;

    @PostMapping("/leaveApproveByAdmin")
    public ResponseEntity<String> approveLeaveByAdmin(@RequestBody Map<String, String> requestData,HttpSession session) {
        try {
        	
            String empId = requestData.get("empid");
            String status = requestData.get("status");
            String reason = requestData.get("reason");
            
            String loggedEmail = (String) session.getAttribute("email");
            String loggedUserName = login_Repo.getLoggedName(loggedEmail);
            System.out.println("loggedUserName-----"+loggedUserName);
            
            // Based on the status, handle approval or rejection 
            System.out.println("statusssssssssssss"+status);            
            if ("Approved".equals(status)) {
            	System.out.println("apppppppprove in controller");
                leaveRequestService.approveLeave(empId, loggedUserName,status);
            } else if ("Rejected".equals(status)) {
            	System.out.println("Reject  in controller");
                leaveRequestService.rejectLeave(empId, loggedUserName,status, reason);
            }
            
            //leaveRequestService.approveLeave(empId,loggedUserName,status);
            //Mock implementation for testing
            return new ResponseEntity<>("Leave approved successfully", HttpStatus.OK);
            
        } catch (Exception e) {
        	e.printStackTrace(); // Add this line to log the error
            return new ResponseEntity<>("Error approving leave: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/updateLeaveRequest")
    @ResponseBody    
    public ResponseEntity<String> updateLeaveRequest(@RequestBody LeaveApplication updatedRequest) {
    	 System.out.println("updatedRequest in controller==="+updatedRequest); 
    	 try {
    	        String response = leaveRequestService.updateLeaveRequest(updatedRequest);
    	        if ("Leave request not found.".equals(response)) {
    	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	        }
    	        return ResponseEntity.ok(response);
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update leave request.");
    	    }
    }
}
