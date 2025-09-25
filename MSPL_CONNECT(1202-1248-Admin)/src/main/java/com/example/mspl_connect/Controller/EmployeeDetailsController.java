package com.example.mspl_connect.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.RoleRepo;
import com.example.mspl_connect.Service.EmployeeDetaisService;

@Controller
public class EmployeeDetailsController {
	
	@Autowired
	private EmployeeDetaisService  employeeDetailsService;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo; 
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/addEmployee")
	public String saveEmployeeDetails(@ModelAttribute EmployeeDetailsEntity employee, 
	                                  @RequestParam("profilePic") MultipartFile profilePic,
	                                  @RequestParam("panPic") MultipartFile panPic,
	                                  RedirectAttributes redirectAttributes) {
	    if (employeeDetailsService.getEmpById(employee.getEmpId()).isPresent()) {
	        redirectAttributes.addFlashAttribute("error", "Employee already exists!");
	        return "redirect:/employee";
	    }

	    try {
	        // Save profilePic to the server
	        //String profilePicPath = saveImage(profilePic);
	        //employee.setProfilePicPath(profilePicPath);

	        // Save panPic to the server
	        String panPicPath = saveImage(panPic);
	       // employee.setPanPicPath(panPicPath);
	        
	        // Save employee to database
	        employeeDetailsService.saveEmployee(employee);
	        
	        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("error", "Failed to upload images.");
	    }
	    return "redirect:/employee";
	}

	private String saveImage(MultipartFile file) throws IOException {
	    if (!file.isEmpty()) {
	        // Create the directory if it doesn't exist
	        Path uploadDirectory = Paths.get("src/main/resources/static/assets/img/");
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }

	        // Generate a unique file name
	        //String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	        String fileName = file.getOriginalFilename();

	        Path filePath = uploadDirectory.resolve(fileName);

	        // Save the file to the file system
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Return the relative path for web access
	        return "assets/img/" + fileName;
	    }
	    return null;
	}

	 @GetMapping("/getDesignations")
	    public ResponseEntity<List<RoleEntity>> getDesignations(@RequestParam int deptId) {
	        List<RoleEntity> designations = roleRepo.findByDepartment(deptId);
	        return ResponseEntity.ok(designations);
	    }
	 
	 @GetMapping("/getSubDepartment")
	 public ResponseEntity<List<DepartmentTableEntity>> getSubDepartEntity(@RequestParam int deptId){
		 List<DepartmentTableEntity> departentWithSubDepartment=departmentRepo.findByDepartment(deptId);
		 departentWithSubDepartment.stream().forEach(dept -> System.out.println("dept==="+dept));
		 
		 return ResponseEntity.ok(departentWithSubDepartment);
	 }
	 
	 
	 
	 /*FOR CHAT BOOT*/
	 @GetMapping("/employees/names")
	    public ResponseEntity<List<Map<String, String>>> getEmployeeNames() {
	        List<Map<String, String>> employeeNames = employeeRepository.findAll()
	                .stream()
	                .map(employee -> {
	                    Map<String, String> map = new HashMap<>();
	                    map.put("name", employee.getFirstName() + " " + employee.getLastName());
	                    map.put("email", employee.getEmail()); // Assuming getEmail() returns the employee's email
	                    return map;
	                })
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(employeeNames);
	 }

/*	 @GetMapping("/fetchEmployeeDetails")
	 public String getEmployeeDetails(Model model) {
	     System.out.println("Endpoint /fetchEmployeeDetails invoked");
	     List<EmployeeDetailsEntity> employees = employeeDetailsService.getAllEmployees();
	     System.out.println("Fetched employees: " + employees);
	     
	     List<Map<String, String>> employeeList = employees.stream().map(emp -> {
	         Map<String, String> employeeData = new HashMap<>();
	         employeeData.put("email", emp.getEmail());
	         employeeData.put("profilePicPath", emp.getProfile_pic_path() != null ? emp.getProfile_pic_path() : "default-avatar.png");
	         return employeeData;
	     }).collect(Collectors.toList());
	     
	     System.out.println("detttttt "+ employeeList);
	     // Add the employee list to the model
	     model.addAttribute("employees", employeeList);
	     
	     // Return the Thymeleaf template name
	     return "User/UserFavorite"; // This should map to `employeeDetails.html` in your templates folder
	 }*/
	 
	 @GetMapping("/fetchEmployeeDetails")
	 @ResponseBody
	 public List<Map<String, String>> getEmployeeDetails() {
	     System.out.println("Endpoint /fetchEmployeeDetails invoked");
	     List<EmployeeDetailsEntity> employees = employeeDetailsService.getAllEmployees();
	     System.out.println("Fetched employees: " + employees);
	     
	     List<Map<String, String>> employeeList = employees.stream().map(emp -> {
	         Map<String, String> employeeData = new HashMap<>();
	         employeeData.put("email", emp.getEmail());
	         employeeData.put("profilePicPath", emp.getProfilePicPath() != null ? emp.getProfilePicPath() : "default-avatar.png");
	         return employeeData;
	     }).collect(Collectors.toList());
	     
	     System.out.println("Employee details: " + employeeList);
	     return employeeList;  // This will return JSON, which will be handled on the frontend
	 }

}
