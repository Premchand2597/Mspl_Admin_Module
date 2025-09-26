package com.example.mspl_connect.AdminEntity;

public class Appraisal_UserType_EmpId_CurrentFY_DTO {

	private String emp_id;
	private String usertype;
	private String financialYear;
	private String monthAndYear;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getMonthAndYear() {
		return monthAndYear;
	}
	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	@Override
	public String toString() {
		return "Appraisal_UserType_EmpId_CurrentFY_DTO [emp_id=" + emp_id + ", usertype=" + usertype
				+ ", financialYear=" + financialYear + ", monthAndYear=" + monthAndYear + "]";
	}
	
}
