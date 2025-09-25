package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PendingLeaveWithProfilePicData_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String empid;
	private String employee_name;
	private String from_date;
	private String to_date;
	private String profile_pic_path;
	
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
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getProfile_pic_path() {
		return profile_pic_path;
	}
	public void setProfile_pic_path(String profile_pic_path) {
		this.profile_pic_path = profile_pic_path;
	}
	@Override
	public String toString() {
		return "PendingLeaveWithProfilePicData_Entity [id=" + id + ", empid=" + empid + ", employee_name="
				+ employee_name + ", from_date=" + from_date + ", to_date=" + to_date + ", profile_pic_path="
				+ profile_pic_path + "]";
	}
}
