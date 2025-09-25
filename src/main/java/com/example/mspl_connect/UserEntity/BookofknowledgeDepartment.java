package com.example.mspl_connect.UserEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookofknowledgedepartment")
public class BookofknowledgeDepartment {
	  
    public BookofknowledgeDepartment(Long dept_id, String deptName, String deptDesc) {
		super();
		this.dept_id = dept_id;
		this.deptName = deptName;
		this.deptDesc = deptDesc;
	}

	public BookofknowledgeDepartment() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dept_id;

	   @Column(name = "deptname", nullable = false, unique = true)
    private String deptName;

    // Optional: description
    @Column(name = "dept_desc") 
    private String deptDesc;

    // 👇 New fields
    @Column(name = "created_by_empid") 
    private String createdByEmpId;

    public BookofknowledgeDepartment(String deptName, String deptDesc, String createdByEmpId, LocalDateTime createdAt) {
		super();
		this.deptName = deptName;
		this.deptDesc = deptDesc;
		this.createdByEmpId = createdByEmpId;
		this.createdAt = createdAt;
	}

	@Column(name = "created_at") 
    private LocalDateTime createdAt;
    
    public BookofknowledgeDepartment(Long dept_id, String deptName, String deptDesc, String createdByEmpId,
			LocalDateTime createdAt) {
		super();
		this.dept_id = dept_id;
		this.deptName = deptName;
		this.deptDesc = deptDesc;
		this.createdByEmpId = createdByEmpId;
		this.createdAt = createdAt;
	}

	
	public String getCreatedByEmpId() {
		return createdByEmpId;
	}

	public void setCreatedByEmpId(String createdByEmpId) {
		this.createdByEmpId = createdByEmpId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public BookofknowledgeDepartment(String deptName, String deptDesc) {
		super();
		this.deptName = deptName;
		this.deptDesc = deptDesc;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}


}
