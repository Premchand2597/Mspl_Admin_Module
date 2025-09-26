package com.example.mspl_connect.UserEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_views")
public class DocumentView {
	  public DocumentView() {
		super();
	}

	  public DocumentView(Long id, Long documentId, String empId, LocalDateTime viewTime) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.empId = empId;
		this.viewTime = viewTime;
	}

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "document_id")
	    private Long documentId;

	    @Column(name = "emp_id")
	    private String empId;

	    @Column(name = "view_time")
	    private LocalDateTime viewTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getDocumentId() {
			return documentId;
		}

		public void setDocumentId(Long documentId) {
			this.documentId = documentId;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public LocalDateTime getViewTime() {
			return viewTime;
		}

		public void setViewTime(LocalDateTime viewTime) {
			this.viewTime = viewTime;
		}
	    
	    
}
