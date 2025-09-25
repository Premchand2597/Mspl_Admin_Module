package com.example.mspl_connect.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TodayPresentEmpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String eid;
	
	private String first_punch_time;
	private String last_seen;
	private String emp_name;
	private String email;
	private String dept_name;
	private String last_punch_time;
	
	
	
	public String getLast_punch_time() {
		return last_punch_time;
	}

	public void setLast_punch_time(String last_punch_time) {
		this.last_punch_time = last_punch_time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
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
	
	
	
	public String getFirst_punch_time() {
		return first_punch_time;
	}

	public void setFirst_punch_time(String first_punch_time) {
		this.first_punch_time = first_punch_time;
	}

	public String getLast_seen() {
		return last_seen;
	}

	public void setLast_seen(String last_seen) {
		this.last_seen = last_seen;
	}

	public TodayPresentEmpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TodayPresentEmpEntity [eid=" + eid + ", first_punch_time=" + first_punch_time + ", last_seen="
				+ last_seen + ", emp_name=" + emp_name + ", email=" + email + ", dept_name=" + dept_name
				+ ", last_punch_time=" + last_punch_time + "]";
	}
	
	
}
