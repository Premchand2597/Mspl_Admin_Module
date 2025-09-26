package com.example.mspl_connect.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name="departments")
public class DepartmentTableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dept_id;
	
	@Column(name="dept_name")
    private String deptName;
    private String dept_head_name;
    private String description;
    
    @Column(name="sub_dept_name")
    private String subDepartment;
    
   
	@Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_head_name() {
		return dept_head_name;
	}
	public void setDept_head_name(String dept_head_name) {
		this.dept_head_name = dept_head_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "DepartmentTableEntity [dept_id=" + dept_id + ", deptName=" + deptName + ", dept_head_name="
				+ dept_head_name + ", description=" + description + ", subDepartment=" + subDepartment
				+ ", createdDate=" + createdDate + "]";
	}	
}
