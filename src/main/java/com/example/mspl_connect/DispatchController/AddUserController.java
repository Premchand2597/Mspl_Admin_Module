package com.example.mspl_connect.DispatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;
import com.example.mspl_connect.DispatchService.AddUserService;
import com.example.mspl_connect.DispatchService.AuditService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AddUserController {
	
	@Autowired
	private AddUserService addUserService;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AddUserRepository addUserRepository;
		
	@GetMapping("/adduser")
	public String showAddUser(Model model,HttpSession session) {
	    if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
		String username = (String) session.getAttribute("username"); 
        model.addAttribute("username", username); 	
		model.addAttribute("addUser",new AddUser());
		model.addAttribute("addUserList", addUserRepository.findAllByOrderByIdDesc());  
		return "dispatch/addUser";
	}
	
	@PostMapping("/submitAddUser")
	public String submitCustomer(
	        @ModelAttribute AddUser addUser,
	        Model model,
	        HttpSession session) {

	    AddUser savedUser = addUserService.saveUser(addUser);

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "INSERT",
	        username,
	        "Added User ID: " + savedUser.getId()
	    );

	    model.addAttribute("message", "Customer form submitted successfully");
	    return "redirect:/adduser";
	}

	@GetMapping("/fetchAllDistinctUserNames")
	@ResponseBody
	public List<String> fetchAllDistinctUserNames(){
		return addUserRepository.getAllDistinctUserNames();
	}
	
	@GetMapping("/fetchDetailsBasedOnId")
	@ResponseBody
	public AddUser getDetailsBasedOnId(@RequestParam long id){
		return addUserRepository.getDetailsBasedOnAutoId(id);
	}
	
	@PostMapping("/submitEditedUser")
	public String submitEditedCustomer(
	        @ModelAttribute AddUser addUser,
	        Model model,
	        HttpSession session) {

	    AddUser savedUser = addUserService.saveUser(addUser);

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "UPDATE",
	        username,
	        "Added User ID: " + savedUser.getId()
	    );
	    return "redirect:/adduser";
	}
	
	@PostMapping("/deleteUserById")
	public ResponseEntity<String> deleteCustomer(@RequestParam long id, HttpSession session) {
	    addUserService.deleteData(id);

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "DELETE",
	        username,
	        "Deleted User ID: " + id
	    );
	    return ResponseEntity.ok("data deleted");
	}
	
	@GetMapping("/checkUserNameExists")
	@ResponseBody
	public boolean isUserNameExists(@RequestParam String name) {
		return addUserService.isUserNameExistsOrNot(name);
	}
	
	@GetMapping("/checkUserEmailExists")
	@ResponseBody
	public boolean isUserEmailExists(@RequestParam String email) {
		return addUserService.isUserEmailExistsOrNot(email);
	}
	
	@GetMapping("/checkContactNoExists")
	@ResponseBody
	public boolean isContactNoExists(@RequestParam String contact_no) {
		return addUserService.isContactNoExists(contact_no);
	}

}
