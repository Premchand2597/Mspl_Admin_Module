package com.example.mspl_connect.AdminController;


import java.util.Collections;
import java.util.Formatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.AdminEntity.Appraisal;
import com.example.mspl_connect.AdminEntity.AppraisalAdminEntity;
import com.example.mspl_connect.AdminEntity.ApprisalForAdmin;
import com.example.mspl_connect.AdminRepo.AdminKRASubmitRepo;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.AdminService.AdminKRASubmitService;
import com.example.mspl_connect.AdminService.AppraisalService;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppraisalController {

	    @Autowired
	    private AppraisalService appraisalService;

		@Autowired
		private PermissionRepo permissionRepo;

	    @Autowired
	    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

		@Autowired
		private AttendenceRepo attendenceRepo;

		@Autowired
		private EmployeeRepository employeeRepository;
		
		@Autowired
		private AdminKRASubmitService adminKRASubmitService;
		
	    @PostMapping("/save-appraisal")
	    public ResponseEntity<?> submitAppraisal(@RequestBody AppraisalAdminEntity appraisalEmployee,HttpSession session) {
	    	
	    	//System.out.println("admin app"+appraisalEmployee);
	    	String email = (String) session.getAttribute("email");
	    	String loggedEmpId = employeeRepository.findEmpidByEmail(email);	    	
			//System.out.println("appraisalEmployee ==== "+appraisalEmployee);
	    	
			String empId = appraisalEmployee.getEmpId();
			//System.out.println("user empid "+empId);
	        //System.out.println("Appraisal submitted for employee ID: " + appraisalEmployee);
	        
	        //appraisalEmployee.setEmpId(empId);	        
	        appraisalEmployee.setTotalScore(calculateTotalScore(appraisalEmployee));
	        appraisalEmployee.setApprisal_admin_flag(1);
	        // System.out.println("appraisalEmployee="+appraisalEmployee);
	        appraisalService.saveAppraisal(appraisalEmployee,loggedEmpId);
	        
	        //update apprisal_admin_flag in admin employee table after employees appriasal is updated
	        // System.out.println("loggedEmpId="+loggedEmpId);
	        // System.out.println("empId="+empId);
	        
	        if(loggedEmpId.equals(empId)) {
	        	appraisalService.updateAdminAppraisal(empId);
	        	Optional<PermissionsEntity> optionalPermissions = permissionRepo.findByUserId(empId);
		        if (optionalPermissions.isPresent()) {
		            PermissionsEntity permissionsEntity = optionalPermissions.get();
		            permissionsEntity.setApprisalAccess(false);  // Set apprisalAccess to false
		            permissionRepo.save(permissionsEntity);  // Save updated permissions
		        }
	        } else {
	        	appraisalService.updateAppraisal(empId);
	        }
	        return ResponseEntity.ok().body(Collections.singletonMap("success", true));
	    }
	    
	    
	    @PostMapping("/saveEmployeeAppraisal")
	    public ResponseEntity<?> submitEmployeeAppraisal(@RequestBody AppraisalAdminEntity appraisalEmployee,HttpSession session) {
	    	System.out.println("appraisalEmployee---"+appraisalEmployee);
	    	String email = (String) session.getAttribute("email");
	    	String loggedEmpId = employeeRepository.findEmpidByEmail(email);
	    	
			String empId = appraisalEmployee.getEmpId();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDate = LocalDateTime.now().format(formatter);
	        
	        //appraisalEmployee.setEmpId(empId);	        
	        //appraisalEmployee.setTotalScore(calculateTotalScore(appraisalEmployee));
	        appraisalEmployee.setApprisal_admin_flag(1);
	        appraisalEmployee.setValidated_date(formattedDate);
	        appraisalEmployee.setValidated_by(loggedEmpId);
	        
	        appraisalService.saveAppraisal(appraisalEmployee,loggedEmpId);
	        appraisalService.updateAppraisal(empId);//update apprisal_admin_flag in apprisal_employee table
	        
	        //update apprisal_admin_flag in admin employee table after employees appriasal is updated
	       
	         return ResponseEntity.ok().body(Collections.singletonMap("success", true)); 
	         
	    }
	    
	    

	    private int calculateTotalScore(AppraisalAdminEntity appraisalEmployee) {
	        return appraisalEmployee.getProjectScore() + appraisalEmployee.getAttendanceScore() +
	               appraisalEmployee.getTeamCollaborationScore() + appraisalEmployee.getCommunicationScore() +
	               appraisalEmployee.getInitiativeScore() + appraisalEmployee.getLeadershipScore() +
	               appraisalEmployee.getExtraCurricularScore();
	    }

	    
	    @PostMapping("/saveAdminKRA")
	    public ResponseEntity<String> submitEvaluation(@RequestBody ApprisalForAdmin evaluation) {
	        System.out.println("evaluation---" + evaluation);
	        
	        String empId = evaluation.getEmpId();
	        
	        try {
	            // Save the evaluation
	            ApprisalForAdmin savedEvaluation = adminKRASubmitService.saveadminKRA(evaluation);
	            
	            if (savedEvaluation != null) { // Ensure the save operation was successful
	                // Update the appraisal only if saving was successful
	                appraisalService.updateAdminAppraisal(empId);
	                return new ResponseEntity<>("Evaluation submitted successfully", HttpStatus.CREATED);
	            } else {
	                return new ResponseEntity<>("Failed to save evaluation", HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        } catch (Exception e) {
	            System.err.println("Error occurred while saving evaluation: " + e.getMessage());
	            return new ResponseEntity<>("An error occurred while submitting evaluation", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }


	
}
