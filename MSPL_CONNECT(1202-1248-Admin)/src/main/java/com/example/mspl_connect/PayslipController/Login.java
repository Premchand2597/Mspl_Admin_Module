package com.example.mspl_connect.PayslipController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paySlipLogin")
public class Login {
	@GetMapping
	public String loginPage()
	{
		return "loginPage";	
	} 
	
	
	@PostMapping("/loginValidate")
	public String validateLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		
		//System.out.println(email+"  "+password);
		
		if(email.equals("accounts@gmail.com") && password.equals("mspl")) {
			return "redirect:sendMail";
		}else {
			model.addAttribute("error", "Invalid email or password");
   	     	return "loginPage";
		}
	}
}