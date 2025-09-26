package com.example.mspl_connect.PayslipController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.AdminEntity.SalaryDetailsEntity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminService.SalaryDetailsService;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountantController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalRepository appraisalRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
	
	@Autowired
	private SalaryDetailsService salaryDetailsService;

	@GetMapping("/salaryDetailsPage")
	public String salaryDetailsPage(HttpSession session,Model model) {
		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		String emailid = (String) session.getAttribute("email");
		String empid = employeeRepository.findEmpidByEmail(emailid);
		int adminDept = employeeRepository.findDeptIdByEmpId(empid);
	    String adminDeptName = departmentRepo.findDeptNameByDeptId(adminDept);
		
		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empid);
	    System.out.println("permissions....."+permissions);
	    
	    if (permissions.isPresent()) {
	        PermissionsEntity permissionEntity = permissions.get();
	        if(permissionEntity.isApprisalAccess()) {
	        	 String dueDateForAppriasal=appraisalRepository.getDueDateByEmpid(empid);
	        	 if (dueDateForAppriasal == null) {
	        		    // No data found, set apprisalAccess to false
	        		    permissionEntity.setApprisalAccess(false);
	        		} else {
	        		    // Define date format
	        		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        		    try {
	        		        // Parse due date into LocalDate
	        		        LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter);
	        		        LocalDate currentDate = LocalDate.now(); // Get current date

	        		        // Compare dates
	        		        if (dueDate.isBefore(currentDate)) {
	        		            // If due date is today or earlier, set apprisalAccess to false
	        		            permissionEntity.setApprisalAccess(false);
	        		        } else {
	        		            // If due date is in the future, set apprisalAccess to true
	        		            permissionEntity.setApprisalAccess(true);
	        		        }
	        		    } catch (DateTimeParseException e) {
	        		        // Handle invalid date format gracefully (if necessary)
	        		        permissionEntity.setApprisalAccess(false);
	        		    }
	        		}
	        }
	        model.addAttribute("permissions", permissionEntity);
	    } else {
	        model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
	    }
	    
	    DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empid);
	    
	    //fetch salary details
	    List<SalaryDetailsEntity> appraisalData = salaryDetailsService.getSalaryDetailsByFiancialYear();
	    //appraisalData.stream().forEach(i->System.out.println("dddddddddddddd"+i));
	    
		model.addAttribute("departments",departments);
        model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
        model.addAttribute("appraisalData", appraisalData);
        
		return "Accounts/SalaryDetailsPage";
	}
}
