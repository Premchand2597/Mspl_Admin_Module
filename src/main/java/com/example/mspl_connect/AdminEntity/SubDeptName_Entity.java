package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SubDeptName_Entity {

	@Id
	private String sub_dept_name;

	public String getSub_dept_name() {
		return sub_dept_name;
	}

	public void setSub_dept_name(String sub_dept_name) {
		this.sub_dept_name = sub_dept_name;
	}

	@Override
	public String toString() {
		return "SubDeptName_Entity [sub_dept_name=" + sub_dept_name + "]";
	}
	
}
