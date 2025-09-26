package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Login_Entity {

	@Id
	private long sl_no;
	private String usertype;
	private String role_name;
	private String email;
	private String password;
	private String employee_type;
	private String otp;
	private String emp_name;
	private String gender;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public long getSl_no() {
		return sl_no;
	}
	public void setSl_no(long sl_no) {
		this.sl_no = sl_no;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getEmployee_type() {
		return employee_type;
	}
	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}
	@Override
	public String toString() {
		return "Login_Entity [sl_no=" + sl_no + ", usertype=" + usertype + ", role_name=" + role_name + ", email="
				+ email + ", password=" + password + ", employee_type=" + employee_type + ", otp=" + otp + ", emp_name="
				+ emp_name + ", gender=" + gender + "]";
	}
}
