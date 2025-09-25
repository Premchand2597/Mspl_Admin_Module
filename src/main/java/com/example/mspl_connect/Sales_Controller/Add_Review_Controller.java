package com.example.mspl_connect.Sales_Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.Sales_DTO.Add_Quotation_DTO;
import com.example.mspl_connect.Sales_DTO.QuotationDTO;
import com.example.mspl_connect.Sales_Entity.EmailDraftBodyMessage_Entity;
import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;
import com.example.mspl_connect.Sales_Entity.fetchReportingManagerEmailByEmployeeEmail_Entity;
import com.example.mspl_connect.Sales_Service.Add_Quotation_Service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class Add_Review_Controller {
	
	@Autowired
	private Add_Quotation_Service quotationService;
	
	 @GetMapping("/add_review")
	 public String addQuotation(Model model) {
	     List<QuotationDTO> quotations = quotationService.getAllQuotationForReview();
	     
	     // Debugging: Print quotation and products
	     for (QuotationDTO q : quotations) {
	         System.out.println("Quotation ID: " + q.getQid());
	         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
	     }
	     model.addAttribute("quotation", quotations);
	     return "User/Review";
	 }
	 
	 @PostMapping("/insertEmailDraft")
	 public ResponseEntity<String> saveEmailDraft(@RequestBody EmailDraftBodyMessage_Entity emailDraftBodyMessage_Entity) {
	     try {
	    	 //System.out.println("emailDraftBodyMessage_Entity.getEmail_draft() == " +emailDraftBodyMessage_Entity.getEmail_draft());
	         quotationService.insertEmailDraftBodyToDB(emailDraftBodyMessage_Entity);
	         return ResponseEntity.ok("success");
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save quotation: " + e.getMessage());
	     }
	 }	 
	 
	 @PostMapping("/updateEmailSentMailStatusForManuallySentByTheAdmin")
	 public ResponseEntity<String> toggleEmailSentMailStatusForManuallySentByTheAdmin(@RequestParam String emailStatusFlag, @RequestParam String quotationId) {
	     try {
	    	 quotationService.updateEmailSentStatusForCustomer(emailStatusFlag, quotationId);
	         return ResponseEntity.ok("success");
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Email sent flag status: " + e.getMessage());
	     }
	 }
	 
	 @GetMapping("/retrieveEmailSentFlagStatusValue")
	 @ResponseBody
	 public String retrieveEmailSentFlagStatusValueBasedOnQuotationId(@RequestParam String quotationId) {
		 return quotationService.retrieveEmailSentFlagStatusValue(quotationId);
	 }
	 
	 @GetMapping("/retrieveReviewFlagStatusValue")
	 @ResponseBody
	 public String retrieveReviewFlagStatusValueBasedOnQuotationId(@RequestParam String quotationId) {
		 return quotationService.retrieveReviewFlagStatusValue(quotationId);
	 }
	 
	 @GetMapping("/fetchReportingManagersEmailBasedOnEmpEmail")
	 @ResponseBody
	 public fetchReportingManagerEmailByEmployeeEmail_Entity getReportingManagersEmailBasedOnEmpEmail(@RequestParam String employee_email) {
	     return quotationService.getReportingManagerEmailIdBasedOnEmpEmail(employee_email);
	 }
	 
	 @GetMapping("/returnRecentlyInsertedEmailDraftMessageDetail")
	 @ResponseBody
	 public EmailDraftBodyMessage_Entity getInsertedEmailDraftMessageDetail() {
	     return quotationService.getRecentInsertedEmailDraftDetail();
	 }
	 
	 	@PostMapping("/sendEmailToTheCustomerWithAttachment")
	    public ResponseEntity<String> sendEmailToTheCustomerWithAnAttachment(@RequestParam String from,
	    																	  @RequestParam String to,
	    																	  @RequestParam String cc,
									                                          @RequestParam String subject,
									                                          @RequestParam String message,
									                                          @RequestParam String password,
									                                          @RequestParam(required = false) MultipartFile file,
									                                          @RequestParam String quotationId,
									                                          @RequestParam String fileName,
									                                          HttpSession session) {
	 		byte[] filedata = null;
	 		
	        try {
	        	
	        	if(file != null) {
	        		filedata = file.getBytes();
	        	}
	        	quotationService.sendEmailWithAttachment(from, to, cc, subject, message, password, filedata,session,fileName);
	        	quotationService.updateEmailSentStatusForCustomer("1", quotationId);
	        	System.out.println("Email sent successfully!");
	            return ResponseEntity.ok("Email sent successfully!");
	        } catch (IOException | MessagingException e) {
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email to the customer: " + e.getMessage());
	        }
	    }

}
