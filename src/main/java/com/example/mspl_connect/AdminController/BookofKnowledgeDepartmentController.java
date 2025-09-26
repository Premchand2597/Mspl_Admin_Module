package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mspl_connect.AdminRepo.DepartmentDocumentRepository;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.UserEntity.BookofknowledgeDepartment;
import com.example.mspl_connect.UserEntity.DepartmentDocument;
import com.example.mspl_connect.UserRepository.BookofKnowledgeDepartmentRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class BookofKnowledgeDepartmentController {

	@Autowired
    private BookofKnowledgeDepartmentRepository BookofKnowledgeDepartmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	  @Autowired
	    private DepartmentDocumentRepository repository;
	 // Fetch all departments
   /* @GetMapping("/all")
    public List<BookofknowledgeDepartment> getAllDepartments() {
        return BookofKnowledgeDepartmentRepository.findAll();
    }*/

   
    
 // Create new department
 // Endpoint to create a new department
    @PostMapping("/createbookofknowledgedepartment")
    public ResponseEntity<?> createDepartment(@RequestBody Map<String, String> payload, HttpSession session) {

        // Extract values from the request body
        String deptName = payload.get("deptName");
        String deptDesc = payload.get("deptDesc"); // optional description

        System.out.println("Received request to create department:");
        System.out.println("Dept Name: " + deptName);
        System.out.println("Dept Desc: " + deptDesc);

        // Validate department name (required field)
        if (deptName == null || deptName.trim().isEmpty()) {
            System.out.println("Validation failed: Department name is required");
            return ResponseEntity.badRequest().body("Department name is required");
        }

        // Check if a department with the same name already exists in the database
        if (BookofKnowledgeDepartmentRepository.findByDeptName(deptName).isPresent()) {
            System.out.println("Department already exists: " + deptName);
            return ResponseEntity.badRequest().body("Department already exists");
        }

        // 👇 Fetch current user empId from session email
        String email = (String) session.getAttribute("email");
        String empId = employeeRepository.findEmpidByEmail(email);

        // 👇 Set creation date/time
        LocalDateTime now = LocalDateTime.now();

        
        // Create a new department entity
        //BookofknowledgeDepartment dept = new BookofknowledgeDepartment(deptName, deptDesc);
     // Create entity with new fields
        BookofknowledgeDepartment dept = new BookofknowledgeDepartment(deptName, deptDesc, empId, now);

        System.out.println("Saving new department to the database: " + dept);

        // Save the department entity to the database
        BookofKnowledgeDepartmentRepository.save(dept);

        System.out.println("Department saved successfully: " + dept);

        // Return the saved department as the response
        return ResponseEntity.ok(dept);
    }

    @GetMapping("/bookofknowledgedepartmentsall")
    public ResponseEntity<List<BookofknowledgeDepartment>> getAllBokDepartments() {
        return ResponseEntity.ok(BookofKnowledgeDepartmentRepository.findAll());
    }

    
    @GetMapping("/bookofknowledgedocuments/{deptId}")
    public ResponseEntity<List<DepartmentDocument>> getBookDeptDocuments(@PathVariable Long deptId) {
        try {
            // Assuming you have a service/repo for documents linked to Book of Knowledge dept
            List<DepartmentDocument> docs = repository.findByBookOfKnowledgeDeptId(deptId);

            if (docs == null || docs.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }
            return ResponseEntity.ok(docs);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.emptyList());
        }
    }

}
