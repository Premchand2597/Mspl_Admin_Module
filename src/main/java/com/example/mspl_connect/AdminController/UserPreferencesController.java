package com.example.mspl_connect.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.UserPreferences;
import com.example.mspl_connect.AdminService.UserPreferencesService;

@Controller
public class UserPreferencesController {

	 @Autowired
	    private UserPreferencesService userPreferencesService;
	
	@PostMapping("/save-preferences")
    public String saveUserPreferences(@ModelAttribute UserPreferences userPreferences, Model model) {
		System.out.println("kk");
        userPreferencesService.saveUserPreferences(userPreferences);
        model.addAttribute("userPreferences", userPreferences);
        return "redirect:/viewProfileEmployee"; // Redirect back to the profile settings page
    }

    @GetMapping("/profile-settings")
    public String getUserPreferences(Model model, @RequestParam("empId") String empId) {
        UserPreferences userPreferences = userPreferencesService.findByEmpId(empId);
        model.addAttribute("userPreferences", userPreferences);
        return "redirect:/viewProfileEmployee";
    }
	
}
