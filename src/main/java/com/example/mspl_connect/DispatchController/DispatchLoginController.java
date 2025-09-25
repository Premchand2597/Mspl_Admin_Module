package com.example.mspl_connect.DispatchController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchEntity.User;
import com.example.mspl_connect.DispatchService.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DispatchLoginController {
	
	@Autowired
    private UserService userService;

    @GetMapping("/dispatchLogin")
    public String showLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("user", new AddUser());
//        if("true".equals(error)) {
//        	model.addAttribute("error", "Invalid credentials");
//        }
        return "dispatch/login";
    }

    @PostMapping("/login") 
    public String login(@ModelAttribute AddUser user, Model model, HttpSession session) {
    	AddUser existingUser = userService.findByEmail(user.getEmailId());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
        	//String full_name = existingUser.getFirstName();
            //session.setAttribute("username", existingUser.getUserName()); 
            //session.setAttribute("email", existingUser.getEmailId());
            //session.setAttribute("full_name", full_name);
            return "redirect:/dashboard"; 
        } /*else {
            //model.addAttribute("error", "Invalid credentials");
            return "redirect:/?error=true";
        }*/
        else {
        	model.addAttribute("user", new AddUser());
            model.addAttribute("error", "Invalid credentials");
            return "dispatch/login";
        }
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            return "dispatch/login";
        }
        userService.save(user);
        model.addAttribute("success", "Registered successfully. You can log in now.");
        return "dispatch/login";
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate(); 
    	return "redirect:/";
    }    

}
