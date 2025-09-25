package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.mspl_connect.AdminRepo.DepartmentDocumentRepository;
import com.example.mspl_connect.AdminRepo.DocumentViewRepository;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.UserEntity.DepartmentDocument;
import com.example.mspl_connect.UserEntity.DocumentView;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class DepartmentDocumentService {
	  private final Path internalUploadDir = Paths.get("uploads"); // inside project
	    private final Path externalUploadDir = Paths.get("C:/mspl/uploads"); // outside project

	    @Autowired
	    private DepartmentDocumentRepository repository;

		@Autowired
		private EmployeeRepository employeeRepository;
		
		   @Autowired
		    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;
		   
		   @Autowired
		    private DocumentViewRepository  documentViewRepository ;
		   
		   public DepartmentDocument saveDoc(MultipartFile file, String title, Long dept, String empId, String createdBy) throws IOException {

               Path uploadDirectory = Paths.get("D:/Desktop/EmpDocs/");
                  Path uploadDirectory2 = Paths.get("src/main/resources/static/assets/EmpBackupDocs/");

                  if (!Files.exists(uploadDirectory)) {
                      Files.createDirectories(uploadDirectory);
                  }
                  if (!Files.exists(uploadDirectory2)) {
                      Files.createDirectories(uploadDirectory2);
                  }

          // Clean and define filename
          String fileName = StringUtils.cleanPath(file.getOriginalFilename());

          // Define full paths for both
          Path internalPath = uploadDirectory.resolve(fileName);
          Path externalPath = uploadDirectory2.resolve(fileName);

          // Copy file to both paths
          Files.copy(file.getInputStream(), internalPath, StandardCopyOption.REPLACE_EXISTING);
          Files.copy(file.getInputStream(), externalPath, StandardCopyOption.REPLACE_EXISTING);

          String normalizedPath = "Desktop/EmpDocs/" + fileName;
          // Save file path of either location (internal, external or both — your choice)
          DepartmentDocument doc = new DepartmentDocument();
          doc.setTitle(title);
          doc.setDepartment(dept);
          doc.setUpdatedDate(LocalDateTime.now());
          doc.setFileName(fileName);

          // Option 1: Save internal path
          doc.setFilePath(normalizedPath);
          doc.setEmpId(empId);
          doc.setCreatedBy(createdBy); // ✅ Save createdBy

          // Option 2 (alternative): Save external path
          // doc.setFilePath(externalPath.toString());

          return repository.save(doc);
      }

	    
		   public DepartmentDocument saveDocWithBookDept(MultipartFile file, String title, Long dept,
	                Long bookDeptId, String empId, String createdBy) throws IOException {
	                      // Define your two upload directories
	                Path uploadDirectory = Paths.get("D:/Desktop/EmpDocs/");
	                Path uploadDirectory2 = Paths.get("src/main/resources/static/assets/EmpBackupDocs/");

	                // Ensure both folders exist
	                if (!Files.exists(uploadDirectory)) {
	                    Files.createDirectories(uploadDirectory);
	                }
	                if (!Files.exists(uploadDirectory2)) {
	                    Files.createDirectories(uploadDirectory2);
	                }
	// Clean and define filename
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	// Define full paths
	Path internalPath = uploadDirectory.resolve(fileName);
	Path externalPath = uploadDirectory2.resolve(fileName);

	// Copy file to both paths
	Files.copy(file.getInputStream(), internalPath, StandardCopyOption.REPLACE_EXISTING);
	Files.copy(file.getInputStream(), externalPath, StandardCopyOption.REPLACE_EXISTING);
	//Normalize path for DB
	String normalizedPath = "Desktop/EmpDocs/" + fileName;

	// Build entity
	DepartmentDocument doc = new DepartmentDocument();
	doc.setTitle(title);
	doc.setDepartment(dept);              // default employee dept
	doc.setBookOfKnowledgeDeptId(bookDeptId); // ✅ new field
	doc.setUpdatedDate(LocalDateTime.now());
	doc.setFileName(fileName);
	doc.setFilePath(normalizedPath); // Option 1: internal path
	doc.setEmpId(empId);
	doc.setCreatedBy(createdBy);

	return repository.save(doc);
	}



	    public Path getFilePath(Long id) {
	        DepartmentDocument doc = repository.findById(id).orElse(null);
	        if (doc == null) return null;
	        return Paths.get(doc.getFilePath());
	    }

	    public List<DepartmentDocument> getDocsByDepartment(Long dept) {
	        return repository.findByDepartment(dept);
	    }
	    
	    public DepartmentDocument getById(Long id) {
	        return repository.findById(id).orElse(null);
	    }
	    
	    
	    @Transactional
	    public void recordView(Long documentId, String empId) {
	        // Increment view count on document
	        DepartmentDocument doc = repository.findById(documentId)
	            .orElseThrow(() -> new RuntimeException("Document not found"));

	        doc.setViewCount(doc.getViewCount() == null ? 1 : doc.getViewCount() + 1);
	        repository.save(doc);

	        // Save record in document_views table
	        DocumentView view = new DocumentView();
	        view.setDocumentId(documentId);
	        view.setEmpId(empId);
	        view.setViewTime(LocalDateTime.now());

	        documentViewRepository.save(view);
	    }

}
