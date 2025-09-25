package com.example.mspl_connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/addDepartment")
	public String saveDepartment(@ModelAttribute DepartmentTableEntity department, RedirectAttributes redirectAttributes) {
	    // Check if department already exists
	    if (departmentService.findByDept_name(department.getDeptName()).isPresent()) {
	        redirectAttributes.addFlashAttribute("error", "Department already exists!");
	        return "redirect:/addDepartment";
	    }
	    
	    // Save the new department
	    departmentService.saveDepartment(department);
	    redirectAttributes.addFlashAttribute("message", "Department added successfully!");
	    return "redirect:/addDepartment";
	}
	
	@PostMapping("/updateDepartment")
	public String updateDepartment(@ModelAttribute DepartmentTableEntity department, RedirectAttributes redirectAttributes) {
	    System.out.println("edit form data==================="+ department);
	    departmentService.updateDepartment(department);
	    redirectAttributes.addFlashAttribute("message", "Department updated successfully!");
	    return "redirect:/addDepartment"; // Adjust the redirect path as needed
	}
	 
	 public List<DepartmentTableEntity> gettAllDepatement(){
		 return departmentService.getAllDepartments();
	 }
	 
	 @DeleteMapping("/deleteDept/{deptId}")
	 @ResponseBody
	 public ResponseEntity<String> deleteDept(@PathVariable int deptId){
		 System.out.println("delete only department");
		 try {
			 //departmentService.deleteRoleByDeptId(deptId);
			 departmentService.deleteDepat(deptId);
		     return ResponseEntity.ok("Department deleted successfully.");
		 }
		 catch (DataIntegrityViolationException e) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete department: foreign key constraint fails.");
		}
	  }
	 
	 @DeleteMapping("/deleteDeptWithRoles/{deptId}")
	 @ResponseBody
	public ResponseEntity<String> deleteDeptWithRole(@PathVariable int deptId){
		 System.out.println("delete department with role");
		 try {
			 //departmentService.deleteRoleByDeptId(deptId);
			 departmentService.deleteDepartment(deptId);
		     return ResponseEntity.ok("Department deleted successfully.");
		 }
		 catch (DataIntegrityViolationException e) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete department: foreign key constraint fails.");
		}
	 }	
	 
	 
}
