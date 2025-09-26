package com.example.mspl_connect.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="roles")
public class RoleEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
	
	@Column(name="role_name")
	private String roleName;
	
	private String description;	
	
	@Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
	
	@Column(name="dept_id")
	private int department;
	
	@Transient
	private String deptName;

	public String getDeptName() {
	    return deptName;
	}

	public void setDeptName(String deptName) {
	    this.deptName = deptName;
	}
    
	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleEntity [role_id=" + role_id + ", roleName=" + roleName + ", description=" + description
				+ ", createdDate=" + createdDate + ", department=" + department + ", deptName=" + deptName + "]";
	}
}
