package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MissedPunchEmpList_Entity {

	@Id
	private String empid; 
	private String emp_name;
	private String dept_name;
	private String distinct_missed_days;
	private String overall_missed_punches;
	
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
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDistinct_missed_days() {
		return distinct_missed_days;
	}
	public void setDistinct_missed_days(String distinct_missed_days) {
		this.distinct_missed_days = distinct_missed_days;
	}
	public String getOverall_missed_punches() {
		return overall_missed_punches;
	}
	public void setOverall_missed_punches(String overall_missed_punches) {
		this.overall_missed_punches = overall_missed_punches;
	}
	@Override
	public String toString() {
		return "MissedPunchEmpList_Entity [empid=" + empid + ", emp_name=" + emp_name + ", dept_name=" + dept_name
				+ ", distinct_missed_days=" + distinct_missed_days + ", overall_missed_punches="
				+ overall_missed_punches + "]";
	}
	
}
