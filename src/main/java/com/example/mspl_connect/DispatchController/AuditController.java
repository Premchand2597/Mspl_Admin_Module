package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.DispatchEntity.AuditLog;
import com.example.mspl_connect.DispatchService.AuditService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping("/audit-log")
	public String showAuditLog(Model model,HttpSession session) {
		if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
		String username = (String) session.getAttribute("username"); 
	    model.addAttribute("username", username); 		
	    List<AuditLog> logs = auditService.getAllLogs(); 
	    model.addAttribute("logs", logs);
	    return "dispatch/audit-log"; 
	}

}
