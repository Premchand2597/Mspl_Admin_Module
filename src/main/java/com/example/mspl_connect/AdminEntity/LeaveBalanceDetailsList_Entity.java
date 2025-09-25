package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LeaveBalanceDetailsList_Entity {

	@Id
	private String empid;
	private String emp_name;
	private String email;
	private String merged_table_emp_id;
	private String financial_year;
	private String sl;
	private String cl;
	private String el;
	private String ml;
	private String merged_table_email;
	private String dept_name;
	private String role_name;
	private String gender;
	
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMerged_table_emp_id() {
		return merged_table_emp_id;
	}
	public void setMerged_table_emp_id(String merged_table_emp_id) {
		this.merged_table_emp_id = merged_table_emp_id;
	}
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getCl() {
		return cl;
	}
	public void setCl(String cl) {
		this.cl = cl;
	}
	public String getEl() {
		return el;
	}
	public void setEl(String el) {
		this.el = el;
	}
	public String getMl() {
		return ml;
	}
	public void setMl(String ml) {
		this.ml = ml;
	}
	public String getMerged_table_email() {
		return merged_table_email;
	}
	public void setMerged_table_email(String merged_table_email) {
		this.merged_table_email = merged_table_email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "LeaveBalanceDetailsList_Entity [empid=" + empid + ", emp_name=" + emp_name + ", email=" + email
				+ ", merged_table_emp_id=" + merged_table_emp_id + ", financial_year=" + financial_year + ", sl=" + sl
				+ ", cl=" + cl + ", el=" + el + ", ml=" + ml + ", merged_table_email=" + merged_table_email
				+ ", dept_name=" + dept_name + ", role_name=" + role_name + ", gender=" + gender + "]";
	}
	
}
