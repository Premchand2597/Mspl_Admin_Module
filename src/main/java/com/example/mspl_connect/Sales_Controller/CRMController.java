package com.example.mspl_connect.Sales_Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Sales_Entity.CrmCompanyDetails;
import com.example.mspl_connect.Sales_Entity.CrmContactPerson;
import com.example.mspl_connect.Sales_Repository.CrmCompanyDetailsRepository;
import com.example.mspl_connect.Sales_Service.CrmCompanyDetailsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CRMController {
	
	 @Autowired
	    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	    @Autowired
		private PermissionRepo permissionRepo;
		
		/*
		 * @Autowired private ApprisalHRRepository appraisalHrRepository;
		 */
		@Autowired
		private EmployeeRepository employeeRepository;
		

		@Autowired
		private AppraisalRepository appraisalRepository;

		
		@Autowired
	    private CrmCompanyDetailsService crmCompanyDetailsService;

		 @Autowired
		    private CrmCompanyDetailsRepository crmcompanyRepository;
		    
	 @GetMapping("/crm")
	    public String crmOverviewPage(HttpSession session,Model model) {
	        // Return the CRM overview page

			String email = (String) session.getAttribute("email");
		    System.out.println("user login "+email);
		    if (email == null) { // Check if session has expired
		        return "redirect:/"; // Redirect to the root mapping (login page)
		    }
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empidpp "+empId);
		    DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		    Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		    boolean showCrmMenu = permissions.isPresent() && Boolean.TRUE.equals(permissions.get().getCrm());

		    model.addAttribute("showCrmMenu", showCrmMenu);
		    System.out.println("====%%%%"+permissions);
		    

			if (permissions.isPresent()) {
				PermissionsEntity permissionEntity = permissions.get();
				if (permissionEntity.isApprisalAccess()) {
					String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
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

		    
			   model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
			   model.addAttribute("empId", empId);
			   model.addAttribute("loggedInUserEmail", email);
			   // Fetch CRM details and add to model
			    List<CrmCompanyDetails> crmDetails = crmCompanyDetailsService.getAllCompanies();
			    System.out.println("pppppppppppp"+crmDetails );
			    model.addAttribute("crmDetails", crmDetails);

	        return "User/CRMSales";
	    }
	 
	 
	 @GetMapping("/crmcompanies")
	    @ResponseBody
	    public List<CrmCompanyDetails> getAllCompanies() {
	        return crmCompanyDetailsService.getAllCompanies();
	    }
	 
	 @GetMapping("/crmcompanies/{companyId}")
	  @ResponseBody
	    public CrmCompanyDetails getCompanyDetailsnew(@PathVariable Long companyId) {
	        return crmCompanyDetailsService.getCompanyById(companyId);
	    }
	 
	 
	 @PostMapping("/crmadd")
	 @ResponseBody
	 public ResponseEntity<Map<String, String>> addCompany(@RequestBody CrmCompanyDetails companyDetails) {
	     try {
	         System.out.println("Received company details: " + companyDetails);
	         
	         if (companyDetails.getContactPersons() != null) {
	             for (CrmContactPerson contact : companyDetails.getContactPersons()) {
	                 contact.setCompany(companyDetails);
	             }
	         }

	         crmCompanyDetailsService.saveCompanyDetails(companyDetails);
	         
	         return ResponseEntity.ok(Map.of("message", "Company details added successfully"));
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body(Map.of("message", "Error adding company details"));
	     }
	 }

	    
	    
	    @GetMapping("/fetchCrmDetails")
	    public List<CrmCompanyDetails> fetchCrmDetails() {
	        return crmCompanyDetailsService.getAllCompanies();
	    }
	    
	    @GetMapping("/fetchCrmDetailserror")
	    @ResponseBody
	    public List<CrmCompanyDetails> fetchCrmDetailserror() {
	        return crmCompanyDetailsService.getAllCompanies();
	    }
	    
	    @DeleteMapping("/crmdelete/{id}")
	    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
	        try {
	        	crmCompanyDetailsService.deleteCompanyById(id);
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the company.");
	        }
	    }
	    
	    @GetMapping("/crmget/{id}")
	    public ResponseEntity<CrmCompanyDetails> getCompanyById(@PathVariable Long id) {
	        Optional<CrmCompanyDetails> company = crmcompanyRepository.findById(id);
	        return company.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	    
	    // Update an existing company
	 /*   @PutMapping("/crmupdate/{id}")
	    public ResponseEntity<CrmCompanyDetails> updateCompany(@PathVariable Long id, @RequestBody CrmCompanyDetails companyDetails) {
	        try {
	            CrmCompanyDetails updatedCompany = crmCompanyDetailsService.updateCompanyDetails(id, companyDetails);
	            return ResponseEntity.ok(updatedCompany);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }*/
	    
	 // Update an existing company
	    @PutMapping("/crmupdate/{id}")
	    @ResponseBody
	    public ResponseEntity<Map<String, String>> updateCompany(@PathVariable Long id, @RequestBody CrmCompanyDetails companyDetails) {
	        try {
	            // Ensure the company has the correct ID
	            companyDetails.setId(id);
	            
	            // Associate contact persons with the company
	            if (companyDetails.getContactPersons() != null) {
	                for (CrmContactPerson contact : companyDetails.getContactPersons()) {
	                    contact.setCompany(companyDetails);
	                }
	            }

	            crmCompanyDetailsService.updateCompanyDetailsnew(companyDetails);
	            
	            return ResponseEntity.ok(Map.of("message", "Company details updated successfully"));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(Map.of("message", "Error updating company details"));
	        }
	    }


	    
	 // Fetch a single company's details
	    @GetMapping("/crmcompany/{id}")
	    public ResponseEntity<CrmCompanyDetails> getCompanyDetails(@PathVariable Long id) {
	      //  Optional<CrmCompanyDetails> company = crmcompanyRepository.findById(id);
	    	Optional<CrmCompanyDetails> company = crmcompanyRepository.findByIdWithContacts(id);
	        
	        return company.map(ResponseEntity::ok)
	                      .orElse(ResponseEntity.notFound().build());
	    }

}
