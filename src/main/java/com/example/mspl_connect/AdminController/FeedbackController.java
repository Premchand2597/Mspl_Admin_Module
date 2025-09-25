package com.example.mspl_connect.AdminController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.AdminEntity.BugReport_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.BugReport_Repo;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Controller 
public class FeedbackController {
	
	 private final JavaMailSender mailSender;
	 
	 @Autowired
	 private BugReport_Repo bugReport_Repo;
	 
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 
	 @Autowired
		private DepartmentRepo departmentRepo;
	 
	 @Autowired
		private PermissionRepo permissionRepo;
	 
	 @Autowired
		private AppraisalRepository appraisalRepository;
	 
	 @Autowired
		private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	    public FeedbackController(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }
	    
	    @GetMapping("feedbackReports")
	    public String openFeedbackReportPage(HttpSession session, Model model) {
	    	
	    	// Retrieve user details from session
	 		String email = (String) session.getAttribute("email");
	 		String empId = employeeRepository.findEmpidByEmail(email);
	 		int adminDept = employeeRepository.findDeptIdByEmpId(empId);
	 		String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);

	 		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

	 		if (permissions.isPresent()) {
	 			PermissionsEntity permissionEntity = permissions.get();
	 			if (permissionEntity.isApprisalAccess()) {
	 				String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
	 				if (dueDateForAppriasal == null) {
	 					// No data found, set apprisalAccess to false
	 					permissionEntity.setApprisalAccess(false);
	 				} else {
	 					// Define date format
	 					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 					try {
	 						// Parse due date into LocalDate
	 						LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter);
	 						LocalDate currentDate = LocalDate.now(); // Get current date

	 						// Compare dates
	 						if (dueDate.isBefore(currentDate)) {
	 							// If due date is today or earlier, set apprisalAccess to false
	 							permissionEntity.setApprisalAccess(false);
	 						} else {
	 							// If due date is in the future, set apprisalAccess to true
	 							permissionEntity.setApprisalAccess(true);
	 						}
	 					} catch (DateTimeParseException e) {
	 						// Handle invalid date format gracefully (if necessary)
	 						permissionEntity.setApprisalAccess(false);
	 					}
	 				}
	 			}
	 			model.addAttribute("permissions", permissionEntity);
	 		} else {
	 			model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	 		}

	 		DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
	 		model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
	    	
	    	return "FeedbackReport";
	    }
	
	    @PostMapping("/submitFeedback")
	    public ResponseEntity<Map<String, Object>> submitFeedback(
	    		@RequestParam("id") String feedbackTicketNumber,
	            @RequestParam("name") String name,
	            @RequestParam("emp_id") String emp_id,
	            @RequestParam("email") String userEmail,
	            @RequestParam("message") String message, 
	            @RequestParam("bugType") String bugType,
	            @RequestParam(value = "attachment", required = false) MultipartFile[] attachments) {
	    	
	        Map<String, Object> response = new HashMap<>();
	        
	        try {
	            // Log the number of attachments received
	            // System.out.println("Number of attachments received: " + (attachments != null ? attachments.length : 0));
	        	String attachmentPath = null;
	        	BugReport_Entity bugReport = new BugReport_Entity();
	        	
	        	bugReport.setId(feedbackTicketNumber);
	        	bugReport.setName(name);
	        	bugReport.setEmail(userEmail);
	        	bugReport.setMessage(message);
	        	bugReport.setBug_type(bugType);
	        	bugReport.setEmp_id(emp_id);
	        	bugReport.setBug_reported_on(LocalDateTime.now().withNano(0)); // Set current date and time
	            if (attachments != null) {
	                for (MultipartFile attachment : attachments) {
	                   // System.out.println("Received file: " + attachment.getOriginalFilename());
	                	attachmentPath = saveImage(attachment);
	                }
	            }
	            bugReport.setAttachment(attachmentPath);
	            bugReport.setBug_status("pending");
	            
	            bugReport_Repo.save(bugReport);

	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	            helper.setFrom("noreply@melangesystems.com"); // Application's email
	            helper.setTo("bugs_msplconnect@melangesystems.com"); //email
	            helper.setReplyTo(userEmail); // Logged-in user's email
	         //   helper.setSubject("Feedback from " + name);
	            helper.setSubject("Feedback on " + bugType + " from " + name);

	            helper.setText(
      	            	    "Dear Team,\n\n" +
      	            	    "We have received feedback from " + name + " regarding the " + bugType+"\n\n" +
      	            	    "Feedback Message:\n" +
      	            	    message + "\n\n" +
      	            	    "Let’s review and address the suggested improvements.\n\n" +
      	            	    "Regards,\n" +
      	            	    name, 
      	            	    false
      	            	);

	            if (attachments != null && attachments.length > 0) {
	                for (MultipartFile attachment : attachments) {
	                    if (!attachment.isEmpty()) {
	                        helper.addAttachment(attachment.getOriginalFilename(), attachment);
	                    }
	                }
	            }

	            mailSender.send(mimeMessage);

	            // Return success response
	            response.put("success", true);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Return error response
	            response.put("success", false);
	            return ResponseEntity.status(500).body(response);
	        }
	    }

        //helper.setFrom("noreply.melangesystems@gmail.com"); // Application's email
	    
	    
	    @PostMapping("/replyToFeedback")
	    public ResponseEntity<Map<String, Object>> replyFeedback(
	    		@RequestParam("feedback_ticket_number") String feedback_ticket_number,
	            @RequestParam("feedback_reply_email") String feedback_reply_email,
	            @RequestParam("feedback_reply_name") String feedback_reply_name,
	            @RequestParam("feedback_reply_empid") String feedback_reply_empid,
	            @RequestParam("feedback_replied_date_and_time") String feedback_replied_date_and_time, 
	            @RequestParam("auto_id") String auto_id,
	            @RequestParam("feedback_status") String feedback_status,
	            @RequestParam("feedback_message_for_mail") String feedback_message_for_mail) {
	    	
	        Map<String, Object> response = new HashMap<>();
	        
	        try {
	        	
	        	String gender = employeeRepository.fetchGenderByEmpId(feedback_reply_empid);
	        	String prefix = null;
			  	  
			  	  if(gender.equals("Male")) {
			  		prefix = "Mr.";
			  	  }else{
			  		prefix = "Ms.";
			  	  }

	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	            helper.setFrom("noreply@melangesystems.com"); // Application's email
	            helper.setTo(feedback_reply_email); //email
	            helper.setCc("bugs_msplconnect@melangesystems.com"); //email
	            //helper.setReplyTo(userEmail); // Logged-in user's email
	            helper.setSubject("Re: Update on Ticket #" + feedback_ticket_number + " raised by " + prefix + feedback_reply_name);

	            helper.setText(
      	            	    "Dear "+ prefix + feedback_reply_name +",\n\n" +
      	            	    //"We would like to inform you that the current status of your raised ticket is " + feedback_status + ".\n\n" +
      	            	    "Thank you for reaching out to us\n\n" +feedback_message_for_mail + "\n\n" +
      	            	    "Regards,\n" +
      	            	    "Team - MSPLConnect"
      	            	);

	            mailSender.send(mimeMessage);
	            
	            bugReport_Repo.updateRepliedFeedbackStatus(auto_id, feedback_status, feedback_replied_date_and_time);

	            // Return success response
	            response.put("success", true);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Return error response
	            response.put("success", false);
	            return ResponseEntity.status(500).body(response);
	        }
	    }
	    
	    
	    @GetMapping("recentlySavedTicketNumber")
	    @ResponseBody
	    public String getRecentlyInsertedTicketId() {
	    	//System.out.println("recently ==== "+bugReport_Repo.fetchRecentTicketId());
	    	return bugReport_Repo.fetchRecentTicketId();
	    }
	    
	    @GetMapping("fetchAllDetailsBasedOnStatus")
	    @ResponseBody
	    public List<BugReport_Entity> getAllDetailsBasedOnStatus(@RequestParam String clickedTab) {
	    	return bugReport_Repo.fetchFeedbackDataBasedOnStatus(clickedTab);
	    }
	    
	    
	    private String saveImage(MultipartFile file) throws IOException {
	 		//System.out.println("hoooooooo");
		    if (!file.isEmpty()) {
		    	
		    	/*String contentType = file.getContentType();
		        if (contentType == null || !contentType.startsWith("image/")) {
		            throw new IOException("Invalid file type. Only image files are allowed.");
		        }*/
		    	
		        // Create the directory if it doesn't exist
		        //Path uploadDirectory = Paths.get("C:/Users/COMP/Desktop/EmpDocs");
		    	Path uploadDirectory = Paths.get("D:/Desktop/EmpDocs/");
		    	//Path uploadDirectory = Paths.get("/home/melange/Desktop/EmpDocs/");
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
	
}
