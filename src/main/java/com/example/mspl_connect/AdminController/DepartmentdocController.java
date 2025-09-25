package com.example.mspl_connect.AdminController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.example.mspl_connect.AdminEntity.EmployeesUnderReportingManager_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.ApprisalHR_Repo;
import com.example.mspl_connect.AdminRepo.DocumentViewRepository;
import com.example.mspl_connect.AdminRepo.EmployeesUnderReportingManager_Repo;
import com.example.mspl_connect.AdminService.DepartmentDocumentService;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.Holiday_Repo;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.Holiday_Service;
import com.example.mspl_connect.UserEntity.DepartmentDocument;
import com.example.mspl_connect.UserEntity.DocumentView;

import jakarta.servlet.http.HttpSession;

@Controller
public class DepartmentdocController {
	@Autowired
	private Holiday_Service holiday_Service;
	

	  @Autowired
		private AppraisalRepository appraisalRepository;
	
	 @Autowired
	    private Holiday_Repo holidayRepository; // Assuming you have a repository for the holidays

    @Autowired
    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

    @Autowired
	private PermissionRepo permissionRepo;
	
    @Autowired
   	private ApprisalHR_Repo appraisalHrRepository;
    
	@Autowired
	private EmployeeRepository employeeRepository;
	
	  @Autowired
	    private DepartmentDocumentService service;
	

	   @Autowired
	    private DepartmentRepo departmentRepository;
	    
	    @Autowired
	    private EmployeesUnderReportingManager_Repo employeesUnderReportingManager_Repo;
	    
		   
		   @Autowired
		    private DocumentViewRepository  documentViewRepository ;
	  
	
			@GetMapping("/Departmentdoc")
			public String holidayPage(HttpSession session,Model model) {
				
				if (session.getAttribute("empDetailsByEmpId") == null) {
					return "redirect:/";
				}
				// Retrieve user details from session
				String email = (String) session.getAttribute("email");
				String empId = employeeRepository.findEmpidByEmail(email);
				int adminDept = employeeRepository.findDeptIdByEmpId(empId);
				String adminDeptName = departmentRepository.findDeptNameByDeptId(adminDept);


				Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);

				if (permissions.isPresent()) {
					PermissionsEntity permissionEntity = permissions.get();
					if (permissionEntity.isApprisalAccess()) {
						String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define date format
						LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter); // Parse due date into LocalDate
						LocalDate currentDate = LocalDate.now(); // Get current date

						if (dueDate.isBefore(currentDate)) { // Compare dates
							permissionEntity.setApprisalAccess(false);// If due date is today or earlier, set apprisalAccess to
																		// false
						} else {
							permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
																		// true (if needed)
						}
					}
					model.addAttribute("permissions", permissionEntity);
				} else {
					model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
				}
				boolean canUpload = permissions.map(PermissionsEntity::isBook_of_knowledge).orElse(false);

				    System.out.println("====%%%%"+canUpload);
				        model.addAttribute("canUpload", canUpload);      
				DisplayEmployessEntity empDetailsByEmpId = employeeWitFullDetailes.findByEmpid(empId);
				model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
				return "User/departmentdoc";
			}
			
	

    @GetMapping("/{department}")
    @ResponseBody
    public List<DepartmentDocument> getByDept(@PathVariable Long department) {
    	 List<DepartmentDocument> docs = service.getDocsByDepartment(department);
    	    for (DepartmentDocument doc : docs) {
    	        DisplayEmployessEntity emp = employeeWitFullDetailes.findByEmpid(doc.getEmpId());
    	        if (emp != null) {
    	            doc.setFullName(emp.getFName() + " " + emp.getLName());
    	        } else {
    	            doc.setFullName("N/A");
    	        }
    	    }
    	    return docs;
    }

    @PostMapping("/documentsupload")
    public ResponseEntity<?> uploadDoc(@RequestParam("file") MultipartFile file,
                                       @RequestParam("title") String title, @RequestParam("createdBy") String createdBy,
                                       @RequestParam(value = "deptId", required = false) Long manualDeptId,// ✅ New
                                        HttpSession session) {
    	  String email = (String) session.getAttribute("email");
    	    if (email == null) {
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please login again.");
    	    }

    	    String empId = employeeRepository.findEmpidByEmail(email);
    	    DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);
            System.out.println("jiiiiiiiiii " +empDetails );
            
            System.out.println("➡️ Upload request received");
            System.out.println("   📧 Email: " + email);
            System.out.println("   🆔 EmpId: " + empId);
            System.out.println("   👤 EmpDetails: " + empDetails);
            System.out.println("   📄 Title: " + title);
            System.out.println("   👤 CreatedBy: " + createdBy);
            System.out.println("   📂 File Name: " + file.getOriginalFilename());
            System.out.println("   🏢 DefaultDeptId: " + empDetails.getDeptId());
            System.out.println("   🏢 ManualDeptId: " + manualDeptId);
        try {
        	Long defaultDeptId = Long.valueOf(empDetails.getDeptId());
        	 // if user selected a manual Book of Knowledge department, save it
            if (manualDeptId != null) {
                service.saveDocWithBookDept(file, title, defaultDeptId, manualDeptId, empId, createdBy);
            } else {
                // otherwise fallback to employee’s department
                service.saveDoc(file, title, defaultDeptId, empId, createdBy);
            }
            return ResponseEntity.ok("Uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Path path = service.getFilePath(id);
        if (path == null || !Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());
        String contentType = Files.probeContentType(path);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"")
                .body(resource);
    }
    
    
    
    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewDocumentInline(@PathVariable Long id) {
        DepartmentDocument document = service.getById(id); // Fetch document by ID

        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        // Always build absolute path using base directory + filename
        Path path = Paths.get("D:/Desktop/EmpDocs/", document.getFileName());

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(path);

        // Detect MIME type dynamically (instead of forcing PDF)
        String contentType;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            contentType = "application/octet-stream"; // fallback
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }


    
    @PostMapping("/documents/{id}/view")
    public ResponseEntity<?> recordDocumentView(@PathVariable Long id, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
        }

        String empId = employeeRepository.findEmpidByEmail(email);
        if (empId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        try {
            service.recordView(id, empId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to record view");
        }
    }

    
    
   /* @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewDocumentInline(@PathVariable Long id) throws IOException, InterruptedException {
        DepartmentDocument document = service.getById(id);

        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        // Replace Windows backslashes with forward slashes
        String cleanedPath = document.getFilePath().replace("\\", "/");
        File originalFile = new File(cleanedPath);

        if (!originalFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        String fileName = originalFile.getName();
        String extension = getExtension(fileName).toLowerCase();
        File fileToServe;

        if (extension.equals("pdf")) {
            fileToServe = originalFile;
        } else if (extension.equals("doc") || extension.equals("docx")) {
            fileToServe = convertWordToPdf(originalFile);
            if (fileToServe == null || !fileToServe.exists()) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.status(415).body(null); // Unsupported Media Type
        }

        Resource resource = new FileSystemResource(fileToServe);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileToServe.getName() + "\"")
            .body(resource);
    }

    private String getExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot == -1) ? "" : fileName.substring(dot + 1);
    }

    // LibreOffice-based converter
    private File convertWordToPdf(File inputFile) throws IOException, InterruptedException {
        // Use system temp directory to save converted PDF
        File outputDir = new File(System.getProperty("java.io.tmpdir"));
        String baseName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
        File outputPdf = new File(outputDir, baseName + ".pdf");

        ProcessBuilder pb = new ProcessBuilder(
        		"C:\\Program Files\\LibreOffice\\program\\soffice.exe", // ✅ Windows path to soffice
              
                "--headless",
                "--convert-to",
                "pdf",
                "--outdir",
                outputDir.getAbsolutePath(),
                inputFile.getAbsolutePath()
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();
        int exitCode = process.waitFor();

        if (exitCode == 0 && outputPdf.exists()) {
            return outputPdf;
        } else {
            return null;
        }
    }*/
    
    @GetMapping("/departmentsall")
    @ResponseBody
    public List<DepartmentTableEntity> getAllDepartments() {
    	System.out.println("poooooooooooooo");
        return departmentRepository.findAll();
    }
    
    @GetMapping("/employee-department")
    @ResponseBody
    public ResponseEntity<Integer> getEmployeeDepartment( HttpSession session) {
    	 String email = (String) session.getAttribute("email");
 	   
        String empId = employeeRepository.findEmpidByEmail(email);
        DisplayEmployessEntity empDetails = employeeWitFullDetailes.findByEmpid(empId);

        if (empDetails != null) {
            return ResponseEntity.ok(empDetails.getDeptId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
  /*  @GetMapping("/employees-under-manager")
    public ResponseEntity<?> getEmployeesUnderManager(HttpSession session) {
        // 1️⃣ Get email from session
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Session expired. Please login again.");
        }

        // 2️⃣ Get empId from email
        String empId = employeeRepository.findEmpidByEmail(email);
        if (empId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee ID not found for email: " + email);
        }

        // 3️⃣ Fetch employees under this manager
        List<EmployeesUnderReportingManager_Entity> employees =
                employeesUnderReportingManager_Repo.getAllEmployeesWorkingUnderReportingManager(empId);

        // 4️⃣ Build JSON-safe list
        List<Map<String, Object>> result = employees.stream()
                .map(emp -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("empId", emp.getId());
                    map.put("fullName", emp.getEmployees_under_reporting_manager());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }*/
    @GetMapping("/employees-under-manager")
    public ResponseEntity<?> getEmployeesUnderManager(HttpSession session) {
        // 1️⃣ Get email from session
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Session expired. Please login again.");
        }

        // 2️⃣ Get empId from email
        String empId = employeeRepository.findEmpidByEmail(email);
        if (empId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee ID not found for email: " + email);
        }

        // 3️⃣ Fetch employees under this manager
        List<EmployeesUnderReportingManager_Entity> employees =
                employeesUnderReportingManager_Repo.getAllEmployeesWorkingUnderReportingManager(empId);

        // 4️⃣ Build JSON-safe list
        List<Map<String, Object>> result = employees.stream()
                .map(emp -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("empId", emp.getId());
                    map.put("fullName", emp.getEmployees_under_reporting_manager());
                    return map;
                })
                .collect(Collectors.toList());

        // 5️⃣ Add logged-in user also
        Map<String, Object> loggedInUser = new HashMap<>();
        loggedInUser.put("empId", empId);
        loggedInUser.put("fullName", employeeRepository.findFullNameByEmail(email)); // 👈 make sure you have this method
        result.add(0, loggedInUser); // add at the top

        return ResponseEntity.ok(result);
    }

    @GetMapping("/documents/{id}/viewers")
    public ResponseEntity<?> getDocumentViewers(@PathVariable Long id) {
        List<DocumentView> views = documentViewRepository.findByDocumentIdOrderByViewTimeDesc(id);

        List<Map<String, Object>> result = views.stream().map(view -> {
            Map<String, Object> map = new HashMap<>();
            map.put("empId", view.getEmpId());
            map.put("fullName", employeeRepository.findEmployeeNameByEmpId(view.getEmpId()));
            map.put("viewTime", view.getViewTime());
            return map;
        }).collect(Collectors.toList());


        return ResponseEntity.ok(result);
    }



   
}
