package com.example.mspl_connect.AdminController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.mspl_connect.AdminRepo.GroupMailId_Repo;
import com.example.mspl_connect.AdminService.GroupMailId_Service;
import com.example.mspl_connect.Entity.GroupMailId_Entity;

import jakarta.servlet.http.HttpSession;

@Controller
public class GroupMailId_Controller {
	
	@Autowired
	private GroupMailId_Service groupMailId_Service;
	
	@Autowired
	private GroupMailId_Repo groupMailId_Repo;

	/*@GetMapping("/groupMailId")
	public String viewGroupMailIdPage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		return "HR/GroupMailId";
	}*/
	
	
	@PostMapping("/saveGroupMailIdList")
	 public String insertGroupMailId(@ModelAttribute("groupMailIds") GroupMailId_Entity groupMailIds, Model model, HttpSession session) {
		// Step 1: Get the original string
	    String original = groupMailIds.getGroup_mail_id();

	    // Step 2: Split by comma, trim each email, and join again
	    String cleaned = Arrays.stream(original.split(","))
	                           .map(String::trim)
	                           .filter(s -> !s.isEmpty()) // Optional: remove empty entries
	                           .collect(Collectors.joining(","));

	    // Step 3: Set the cleaned string back
	    groupMailIds.setGroup_mail_id(cleaned);
		 	
		 String result = groupMailId_Service.insertGroupMailIds(groupMailIds);
		  
		 if("success".equals(result)) {
			 return "redirect:groupMailId";
		 }else {
			 return "error";
		 }
	 }
	
	@GetMapping("/fetchGroupMailIdData")
	@ResponseBody
	public List<GroupMailId_Entity> getGroupMailIdDetails() {
		return groupMailId_Service.fetchGroupMailId();
	}
	
	
	@GetMapping("/fetchGroupMailIdDataToEdit")
	@ResponseBody
	public GroupMailId_Entity editInterCommData(@RequestParam("auto_id") String auto_id){
		return groupMailId_Service.fetchGroupMailIdDataForEdit(auto_id);
	}
	
	
	@PostMapping("/updateGroupMailIdData")
    public ResponseEntity<String> updateGroupMailIdData(@RequestParam("id") long id, @RequestParam("main_mail_id") String main_mail_id, 
    		@RequestParam("sub_mail_id") String sub_mail_id, @RequestParam("group_mail_id") String group_mail_id) {
		
		//System.out.println("id=== "+id);
		
		// Step 1: Get the original string
	    String original = group_mail_id;

	    // Step 2: Split by comma, trim each email, and join again
	    String cleaned = Arrays.stream(original.split(","))
	                           .map(String::trim)
	                           .filter(s -> !s.isEmpty()) // Optional: remove empty entries
	                           .collect(Collectors.joining(","));

		String result = groupMailId_Service.updateGroupMailIdData(id, main_mail_id, sub_mail_id, cleaned);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("GroupMailId details updated successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update GroupMailId details.");
	    }
	}
	
	
	@PostMapping("/deleteGroupMailIdById")
    public ResponseEntity<String> deleteGroupMailIdData(@RequestParam long id) {
		//System.out.println("id== "+id);
		String result = groupMailId_Service.deleteGroupMailIdData(id);
		if ("success".equals(result)) {
	        return ResponseEntity.ok("GroupMailId detail deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete GroupMailId detail.");
	    }
	}
	
}