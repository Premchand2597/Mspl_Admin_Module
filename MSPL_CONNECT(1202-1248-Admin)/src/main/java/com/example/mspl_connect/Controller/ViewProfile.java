package com.example.mspl_connect.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.PasswordChangeForm;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.EmployeeOnlyDocument_Entity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.Login_Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewProfile {
	
	@Autowired
	EmployeeDetaisService  employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService  employeeDetailsService;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private EmployeeRepository employeeRepository;	
	
	@Autowired
	private Login_Service login_Service;
	
	private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // 16 bytes key (Change it to a secure key)


	@GetMapping("/viewProfile")
	public String viewProfile(@RequestParam("empid") String empid, Model model,HttpSession session) {
		
		String loggedEmail = (String) session.getAttribute("email");
		String loggedEmpId = employeeRepository.findEmpidByEmail(loggedEmail);
		int loggedUserDept = employeeRepository.findDeptIdByEmpId(loggedEmpId);
		
		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		DisplayEmployessEntity profileDetails = employeeService.getEmployeeByEmpid(empid);
		System.out.println("employee-----"+profileDetails);
		
		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(loggedEmpId);
	
	    if (permissions.isPresent()) {
	        System.out.println("permissionspermissionspermissionspermissions"+permissions);
	        PermissionsEntity permissionEntity = permissions.get();
	        model.addAttribute("permissions", permissionEntity);
	    } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
	    
	    
	    Optional<PermissionsEntity> permissionstoDisplay = permissionRepo.findByUserId(empid);
	    if (permissionstoDisplay.isPresent()) {
	        System.out.println("permissionstoDisplay"+permissionstoDisplay);
	        PermissionsEntity permissionEntitytoDisplay = permissionstoDisplay.get();
	        model.addAttribute("permissionstoDisplay", permissionEntitytoDisplay);
	    } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
	    
	    String email = (String) session.getAttribute("email");
	    String empId = employeeRepository.findEmpidByEmail(email);
	    DisplayEmployessEntity loggedAdminDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
			
	    //ViewProfile.logged admin details
	    model.addAttribute("empDetailsByEmpId", loggedAdminDetailsByEmpId);
		// System.out.println("emp details================"+employee);
		model.addAttribute("departments",departments);
		
		//employee details based on clicked employee
        model.addAttribute("employee", profileDetails);
        /*if(loggedUserDept==0) {
        	return "EmployeeProfile";
        } else {
        	return "User/UserEmployeeProfile";	
        }*/
        return "User/UserEmployeeProfile";	
	}
	
	@GetMapping("/fetchEmployeeDocumentDetailsOnlyByEmpId")
	@ResponseBody
	public EmployeeOnlyDocument_Entity selectEmpDetailsForOnlyDocumentsViewById(@RequestParam("emp_id") String emp_id) {
		return employeeService.selectEmpDetailsForOnlyDocumentsViewById(emp_id);
	}
	
	@PostMapping("/updateEmployeeDocUploads")
 	public ResponseEntity<String> updateDocUploads(@RequestParam("emp_id") String emp_id,
 	                                               @RequestParam(value = "profilePic", required = false) MultipartFile profilePic,
 	                                               @RequestParam(value = "panPic", required = false) MultipartFile panPic,
 	                                               @RequestParam(value = "aadharPic", required = false) MultipartFile aadharPic,
 	                                               @RequestParam(value = "tenthPic", required = false) MultipartFile tenthPic,
 	                                               @RequestParam(value = "pucPic", required = false) MultipartFile pucPic,
 	                                               @RequestParam(value = "degreePic", required = false) MultipartFile degreePic,
 	                                               @RequestParam(value = "offer_letter", required = false) MultipartFile offer_letter) {

 		boolean isUpdated = false;
 		
 		String profilePicPath="", panPicPath="", aadharPicPath="", voterIdPicPath="", tenthPicPath="", pucPicPath="", degreePicPath="", offerLetterPath="";


 	    try {
 	    	
 	    	 profilePicPath = saveImage(profilePic);
 	    	 panPicPath = saveImage(panPic);
			 aadharPicPath = saveImage(aadharPic);
			 tenthPicPath = saveImage(tenthPic);
			 pucPicPath = saveImage(pucPic);
			 degreePicPath = saveImage(degreePic);
			 //offerLetterPath = saveImage(offer_letter);
 	    	
 	        // Fetch existing document paths from the database
 	    	EmployeeDetailsEntity existingEmployee = employeeDetailsService.selectEmpDetailsForDocumentsViewById(emp_id);
 	        
 	        // Check each file and update only if a new file is uploaded
 	    	profilePicPath = (profilePic != null && !profilePic.isEmpty()) ? profilePicPath : existingEmployee.getProfilePicPath();
 	    	panPicPath = (panPic != null && !panPic.isEmpty()) ? panPicPath : existingEmployee.getPanPicPath();
 	    	aadharPicPath = (aadharPic != null && !aadharPic.isEmpty()) ? aadharPicPath : existingEmployee.getAadhar_pic();
 	    	tenthPicPath = (tenthPic != null && !tenthPic.isEmpty()) ? tenthPicPath : existingEmployee.getTenth_pic();
 	    	pucPicPath = (pucPic != null && !pucPic.isEmpty()) ? pucPicPath : existingEmployee.getPuc_pic();
 	    	degreePicPath = (degreePic != null && !degreePic.isEmpty()) ? degreePicPath : existingEmployee.getDegree_pic();
 	    	//offerLetterPath = (offer_letter != null && !offer_letter.isEmpty()) ? offerLetterPath : existingEmployee.getOffer_letter();

 	        // Update only the changed document paths in the database
 	        isUpdated = employeeDetailsService.updateEmployeeDocs(emp_id, profilePicPath, panPicPath, aadharPicPath, tenthPicPath, pucPicPath, degreePicPath, offerLetterPath);

 	    } catch (IOException e) {
 	        System.out.println(e.toString());
 	    }

 	    if (isUpdated) {
 	        return ResponseEntity.ok("Document uploaded successfully");
 	    } else {
 	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload documents");
 	    }
 	}

	
	/*@PostMapping("/updatePassword")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatePassword(
	        @RequestParam("newPassword") String newPassword,
	        @RequestParam("currentPassword") String currentPassword,
	        @RequestParam("empId") String empId) {

	    System.out.println("newPassword=====" + newPassword);
	    System.out.println("empId=====" + empId);
	    System.out.println("currentPassword=====" + currentPassword);
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // Fetch the employee by empId
	    	System.out.println("empId=="+empId);
	        DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empId);
	        String oldPassword = employee.getPassword();
	        System.out.println("oldPassword=="+oldPassword);
	        
	        // Compare old and new passwords
	        if (!currentPassword.equals(oldPassword)) {
	            response.put("success", false);
	            response.put("message", "Old password is incorrect.");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	        
	        // If passwords match, update the password
	        employeeService.updateEmployeePassword(empId, newPassword);

	        response.put("success", true);
	        response.put("message", "Password updated successfully!");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error updating password: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}*/
	@PostMapping("/updatePassword")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatePassword(
	        @RequestParam("newPassword") String newPassword,
	        @RequestParam("empId") String empId) {

	    System.out.println("newPassword=====" + newPassword);
	    System.out.println("empId=====" + empId);
	    Map<String, Object> response = new HashMap<>();

	    try {
	    	
	        // Fetch the employee by empId
	    	System.out.println("empId=="+empId);
	        DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empId);
	        String oldPassword = employee.getPassword();
	        System.out.println("oldPassword=="+oldPassword);
	        
	        //newPassword = hashPassword(newPassword);	
	        
	        String rawPassword = newPassword;
	        try {
	        	
				String encryptedPassword = encrypt(rawPassword); // Encrypting password
				// If passwords match, update the password
		        employeeService.updateEmployeePassword(empId, encryptedPassword);
		        
				//employee.setPassword(encryptedPassword);
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        
	        response.put("success", true);
	        response.put("message", "Password updated successfully!");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error updating password: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	
	@PostMapping("/verifyCurrentPassword")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> verifyCurrentPassword(
	        @RequestParam("empId") String empId,
	        @RequestParam("currentPassword") String currentPassword) {

	    Map<String, Object> response = new HashMap<>();
	    try {
	    	
	    	System.out.println("currentPassword-rrrr"+currentPassword);
	    	System.out.println("empIdrrrr-"+empId);
	        DisplayEmployessEntity employee = employeeService.getEmployeeByEmpid(empId);
	        System.out.println("employeeeee-"+employee);
	        String dbPassword = employee.getPassword();
	        
	        try {
				String encryptedPassword = encrypt(currentPassword); // Encrypting password
				if (!encryptedPassword.equals(dbPassword)) {
			        //if (!currentPassword.equals(dbPassword)) {
			            response.put("success", false);
			            response.put("message", "Current password is incorrect.");
			            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			        }
				     response.put("success", true);
			         response.put("message", "Current password is correct.");
			       
				//employee.setPassword(encryptedPassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error verifying password: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	    return ResponseEntity.ok(response); 
	}
	
	
	public static String encrypt(String data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
	private String hashPassword(String password) {
        try {
            // Create a SHA-256 digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());

            // Convert byte array into hex format
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }
	

	private String saveImage(MultipartFile file) throws IOException {
 		//System.out.println("hoooooooo");
	    if (!file.isEmpty()) {
	    	
	    	/*String contentType = file.getContentType();
	        if (contentType == null || !contentType.startsWith("image/")) {
	            throw new IOException("Invalid file type. Only image files are allowed.");
	        }*/
	    	
	        // Create the directory if it doesn't existC:\Users\Ravi\Desktop\EmpDocs
	        // Path uploadDirectory = Paths.get("C:/Users/COMP/Desktop/EmpDocs");file:///C:/Users/COMP/Desktop/EmpDocs/
	    	// file:///D:/Desktop/EmpDocs/
	    	
	    	/*Path uploadDirectory = Paths.get("/D:/EmpDocs/");
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }
	        
	        Path uploadDirectory2 = Paths.get("src/main/resources/static/assets/EmpBackupDocs/");
	        
	        if (!Files.exists(uploadDirectory2)) {
	            Files.createDirectories(uploadDirectory2);
	        }*/
	    	Path uploadDirectory = Paths.get("D:/Desktop/EmpDocs/");
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }
	        
	        Path uploadDirectory2 = Paths.get("src/main/resources/static/assets/EmpBackupDocs/");	        
	        if (!Files.exists(uploadDirectory2)) {
	            Files.createDirectories(uploadDirectory2);
	        }

	        // Generate a unique file name
	        //String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	        String fileName = file.getOriginalFilename();

	        Path filePath = uploadDirectory.resolve(fileName);
	        Path filePath2 = uploadDirectory2.resolve(fileName);

	        // Save the file to the file system
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        Files.copy(file.getInputStream(), filePath2, StandardCopyOption.REPLACE_EXISTING);

	        // Return the relative path for web access
	        return "Desktop/EmpDocs/" + fileName;
	    }
	    return null;
	}

	@PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute EmployeeDetailsEntity employee,RedirectAttributes redirectAttributes) {
		
		try {
			System.out.println("employee-----"+employee);
	        employeeService.updateEmployeeDetails(employee);
	        redirectAttributes.addFlashAttribute("message", "Employee details updated successfully."); 
	    } catch (EntityNotFoundException e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	    }
		 
		// Redirect to the viewProfile method with the employee's empid
	    return "redirect:/viewProfile?empid=" + employee.getEmpId(); 
	    
    }
}
