package com.example.mspl_connect.AdminController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.AdminRepo.BugReport_Repo;
import com.example.mspl_connect.Repository.EmployeeRepository;

import jakarta.mail.internet.MimeMessage;

@Controller 
public class FeedbackController {

	
	@Autowired
	 private BugReport_Repo bugReport_Repo;
	 
	 @Autowired
	 private EmployeeRepository employeeRepository;
	
	
	 private final JavaMailSender mailSender;

	    public FeedbackController(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }
	
	   /* @PostMapping("/submitFeedback")
	    public ResponseEntity<Map<String, Object>> submitFeedback(
	            @RequestParam("name") String name,
	            @RequestParam("email") String userEmail,
	            @RequestParam("message") String message, @RequestParam("bugType") String bugType,
	            @RequestParam(value = "attachment", required = false) MultipartFile[] attachments) {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            // Log the number of attachments received
	          //  System.out.println("Number of attachments received: " + (attachments != null ? attachments.length : 0));

	            // Log the names of each attachment received
	            if (attachments != null) {
	                for (MultipartFile attachment : attachments) {
	                   // System.out.println("Received file: " + attachment.getOriginalFilename());
	                }
	            }

	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	            helper.setFrom("noreply@melangesystems.com"); // Application's email
	           helper.setTo("bugs_msplconnect@melangesystems.com"); //email
	            //helper.setTo("saniya.anjum@melangesystems.com"); //email

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
	    }*/
	    
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
	        	System.out.println("hhheeelllooooo");
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
	
}
