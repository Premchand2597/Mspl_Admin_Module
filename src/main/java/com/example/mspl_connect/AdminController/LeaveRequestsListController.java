package com.example.mspl_connect.AdminController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveBalanceSnapshot;
import com.example.mspl_connect.AdminRepo.LeaveBalanceSnapshotRepository;
import com.example.mspl_connect.AdminRepo.LeaveUtilizedRepository;
import com.example.mspl_connect.AdminService.LeaveApplicationService;
import com.example.mspl_connect.AdminService.LeaveRequestService;
import com.example.mspl_connect.Repository.LeaveApplicationRepo;
import com.example.mspl_connect.Repository.Login_Repo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")  // Adjust the base URL if necessary
public class LeaveRequestsListController {
	
	@Autowired
    private LeaveApplicationRepo leaveApplicationRepo;
	
	@Autowired
    private LeaveRequestService leaveRequestService;
	 
	 @Autowired
	 private LeaveUtilizedRepository leaveUtilizedRepository;
	@Autowired
	private Login_Repo login_Repo;
	@Autowired
	private LeaveBalanceSnapshotRepository snapshotRepository;
	@Autowired
	private LeaveApplicationService leaveApplicationService;

    @PostMapping("/leaveApproveByAdmin")
    public ResponseEntity<String> approveLeaveByAdmin(@RequestBody Map<String, String> requestData,HttpSession session) {
        try {
        	
            String empId = requestData.get("empid");
            String status = requestData.get("status");
            String reason = requestData.get("reason");
            String leaveReason = requestData.get("leaveReason");
            
            String loggedEmail = (String) session.getAttribute("email");
            String loggedUserName = login_Repo.getLoggedName(loggedEmail);
            System.out.println("leaveReason-----"+leaveReason);
            
            // Based on the status, handle approval or rejection 
            System.out.println("status"+status);            
            if ("Approved".equals(status)) {
            	System.out.println("apppppppprove in controller");
                leaveRequestService.approveLeave(empId, loggedUserName,status,loggedEmail,leaveReason);
            } else if ("Rejected".equals(status)) {
            	System.out.println("Reject  in controller");
                leaveRequestService.rejectLeave(empId, loggedUserName,status, reason,loggedEmail,leaveReason);
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
    
    @PostMapping("/leaveValidation")
    public ResponseEntity<Map<String, Object>> validateLeave(@RequestBody Map<String, Object> request) {
    	
    	Integer requestId = Integer.parseInt(request.get("requestId").toString());
        String status = (String) request.get("status");
        System.out.println("statussssssss in controller"+status);
        Map<String, Object> response = new HashMap<>();

        if (request.get("requestId") == null) {
            response.put("valid", false);
            response.put("message", "Invalid request ID.");
            return ResponseEntity.badRequest().body(response);
        }

        String result = leaveApplicationService.validateLeaveRequest(requestId,status);

        switch (result) {
            case "VALID":
                response.put("valid", true);
                System.out.println("✅ Leave request is VALID for requestId: " + requestId);

                if ("Approved".equalsIgnoreCase(status) || "Reject".equalsIgnoreCase(status)) {
                    Optional<LeaveApplication> leaveReqOpt = leaveApplicationRepo.findById(requestId);
                    if (leaveReqOpt.isPresent()) {
                        LeaveApplication leaveApplication = leaveReqOpt.get();
                        System.out.println("📌 Processing leave snapshot for requestId: " + requestId + " with status: " + status);

                        boolean snapshotSaved = leaveApplicationService.saveLeaveBalanceSnapshot(
                            leaveApplication,
                            status
                        );

                        if (snapshotSaved) {
                            response.put("message", "Leave " + status.toLowerCase() + " and snapshot saved.");
                            System.out.println("✅ Snapshot saved successfully for requestId: " + requestId);
                        } else {
                            response.put("message", "Leave " + status.toLowerCase() + " but snapshot NOT saved (no LeaveUtilized found).");
                            System.out.println("⚠️ Snapshot NOT saved for requestId: " + requestId);
                        }
                    }
                }
                break;
            case "SYSTEM_GENERATED":
                response.put("valid", false);
                response.put("message", "This leave request falls under a system-generated leave and has already been approved by the MSPLConnect application. No further action can be taken on this request.");
                break;
            case "OVERLAPPING_LEAVE":
                response.put("valid", false);
                response.put("message", "An approved leave already exists for the selected date range. Please check the leave history.");
                break;
            default:
                response.put("valid", false);
                response.put("message", "Invalid leave request.");
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/leaveBalanceSnapshot/{requestId}")
    public ResponseEntity<List<LeaveBalanceSnapshot>> getLeaveBalanceSnapshot(
            @PathVariable Integer requestId,
            @RequestParam String email) {

        List<LeaveBalanceSnapshot> snapshots =
                snapshotRepository.findByRequestIdAndEmployeeEmail(requestId, email);

        return ResponseEntity.ok(snapshots);
    }

    

}
