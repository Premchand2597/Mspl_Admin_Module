package com.example.mspl_connect.UserEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "department_documents")
public class DepartmentDocument {
	  public DepartmentDocument(Long id, String title, String fileName, String filePath, Long department, String empId,
			String createdBy, Integer viewCount, LocalDateTime updatedDate, String fullName) {
		super();
		this.id = id;
		this.title = title;
		this.fileName = fileName;
		this.filePath = filePath;
		this.department = department;
		this.empId = empId;
		this.createdBy = createdBy;
		this.viewCount = viewCount;
		this.updatedDate = updatedDate;
		this.fullName = fullName;
	}


	  public DepartmentDocument(Long id, String title, String fileName, String filePath, Long department, String empId,
			String createdBy, LocalDateTime updatedDate, String fullName) {
		super();
		this.id = id;
		this.title = title;
		this.fileName = fileName;
		this.filePath = filePath;
		this.department = department;
		this.empId = empId;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.fullName = fullName;
	}


	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String fileName;
	    private String filePath;

		private Long department; // Save department ID, not name
		
		@Column(name = "empid")
		private String empId;
		
		@Column(name = "created_by")
		  private String createdBy; // ✅ Added
	 
		   @Column(name = "view_count")
		    private Integer viewCount = 0;  // Initialize to zero
		   
		@Column(name = "updated_date")
	    private LocalDateTime updatedDate;
	    

	    @Column(name = "bookofknowledge_dept_id")
	    private Long bookOfKnowledgeDeptId; // ✅ New: store manual dept if chosen
		
		public Long getBookOfKnowledgeDeptId() {
			return bookOfKnowledgeDeptId;
		}


		public DepartmentDocument(Long id, String title, String fileName, String filePath, Long department,
				String empId, String createdBy, Integer viewCount, LocalDateTime updatedDate,
				Long bookOfKnowledgeDeptId, String fullName) {
			super();
			this.id = id;
			this.title = title;
			this.fileName = fileName;
			this.filePath = filePath;
			this.department = department;
			this.empId = empId;
			this.createdBy = createdBy;
			this.viewCount = viewCount;
			this.updatedDate = updatedDate;
			this.bookOfKnowledgeDeptId = bookOfKnowledgeDeptId;
			this.fullName = fullName;
		}


		public void setBookOfKnowledgeDeptId(Long bookOfKnowledgeDeptId) {
			this.bookOfKnowledgeDeptId = bookOfKnowledgeDeptId;
		}


		@Transient
		private String fullName;
		
	    public DepartmentDocument() {
			super();
		}


		public DepartmentDocument(Long id, String title, String fileName, String filePath, Long department,
				String empId, LocalDateTime updatedDate) {
			super();
			this.id = id;
			this.title = title;
			this.fileName = fileName;
			this.filePath = filePath;
			this.department = department;
			this.empId = empId;
			this.updatedDate = updatedDate;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


		public String getFilePath() {
			return filePath;
		}


		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}


		public Long getDepartment() {
			return department;
		}


		public void setDepartment(Long department) {
			this.department = department;
		}


		public String getEmpId() {
			return empId;
		}


		public void setEmpId(String empId) {
			this.empId = empId;
		}


		public LocalDateTime getUpdatedDate() {
			return updatedDate;
		}


		public void setUpdatedDate(LocalDateTime updatedDate) {
			this.updatedDate = updatedDate;
		}


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getCreatedBy() {
			return createdBy;
		}


		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}


		public Integer getViewCount() {
			return viewCount;
		}


		public void setViewCount(Integer viewCount) {
			this.viewCount = viewCount;
		}


		


}
