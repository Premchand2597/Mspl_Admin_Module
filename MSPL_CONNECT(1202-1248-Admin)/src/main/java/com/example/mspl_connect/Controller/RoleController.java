package com.example.mspl_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Service.DepartmentService;

@Controller
public class RoleController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/addRole")
    public String saveRole(@ModelAttribute RoleEntity role, RedirectAttributes redirectAttributes) {
        if (role.getRole_id() != null) {
            // Update existing role
            departmentService.updateRole(role);
            redirectAttributes.addFlashAttribute("message", "Role updated successfully!");
        } else {
            // Check if role already exists
            if (departmentService.findByRoleNameAndDepartmentId(role.getRoleName(), role.getDepartment()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Role already exists!");
                return "redirect:/addRole";
            }
            // Save new role
            departmentService.saveRole(role);
            redirectAttributes.addFlashAttribute("message", "Role added successfully!");
        }
        return "redirect:/addRole";
    }
	 
	    @DeleteMapping("/deleteRole/{roleId}")
	    @ResponseBody
	    public ResponseEntity<String> deleteRole(@PathVariable int roleId) {
	        System.out.println("Deleting role with ID: " + roleId);
	        departmentService.deleteRole(roleId);
	        return ResponseEntity.ok("Role deleted successfully.");
	    }
}
