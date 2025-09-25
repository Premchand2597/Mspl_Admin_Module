package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AbsenteesDetailsForCurrentDay_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_id;
	private String absent_emp_name;
	private String profile_pic_path;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAbsent_emp_name() {
		return absent_emp_name;
	}
	public void setAbsent_emp_name(String absent_emp_name) {
		this.absent_emp_name = absent_emp_name;
	}
	public String getProfile_pic_path() {
		return profile_pic_path;
	}
	public void setProfile_pic_path(String profile_pic_path) {
		this.profile_pic_path = profile_pic_path;
	}
	@Override
	public String toString() {
		return "AbsenteesDetailsForCurrentDay_Entity [id=" + id + ", emp_id=" + emp_id + ", absent_emp_name="
				+ absent_emp_name + ", profile_pic_path=" + profile_pic_path + "]";
	}
	
}
