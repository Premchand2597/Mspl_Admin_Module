package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeesEntityWithoutDocs_Entity {

	@Id
    private long id;
    private String empid;
    private String email;
    @Column(name="dept_name")
    private String deptName;
    @Column(name="role_name")
    private String roleName;
    @Column(name="full_name")
	private String fullName;
    private String sub_dept_name;
    private String usertype;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSub_dept_name() {
		return sub_dept_name;
	}
	public void setSub_dept_name(String sub_dept_name) {
		this.sub_dept_name = sub_dept_name;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return "EmployeesEntityWithoutDocs_Entity [id=" + id + ", empid=" + empid + ", email=" + email + ", deptName="
				+ deptName + ", roleName=" + roleName + ", fullName=" + fullName + ", sub_dept_name=" + sub_dept_name
				+ ", usertype=" + usertype + "]";
	}
}

