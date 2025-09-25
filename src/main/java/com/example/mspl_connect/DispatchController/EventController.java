package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.DispatchEntity.DispatchEvent;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;

@Controller

public class EventController {
	
	@Autowired
	private AddUserRepository addUserRepository;
	
	@GetMapping("/event")
	public String showCustomer(Model model) {
	model.addAttribute("event", new DispatchEvent());

    List<String> usernames = addUserRepository.findAllUsernames();
    model.addAttribute("usernames", usernames);

	return "dispatch/event";
	}


}
