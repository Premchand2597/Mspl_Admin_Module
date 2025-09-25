package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FeatureUpdatesEntity {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String feature_name;
   private String is_read;
   private String empid;
   
	@Override
	public String toString() {
		return "FeatureUpdatesEntity [id=" + id + ", feature_name=" + feature_name + ", is_read=" + is_read + ", empid="
				+ empid + "]";
	}

	public FeatureUpdatesEntity(int id, String feature_name, String is_read, String empid) {
		super();
		this.id = id;
		this.feature_name = feature_name;
		this.is_read = is_read;
		this.empid = empid;
	}

	public FeatureUpdatesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeature_name() {
		return feature_name;
	}

	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}
   
   
   
}
