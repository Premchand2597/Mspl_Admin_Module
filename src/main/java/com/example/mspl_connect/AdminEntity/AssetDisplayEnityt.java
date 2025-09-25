package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AssetDisplayEnityt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empid;
	private String asset;
	private String received_date;
	private String return_date;
	private String quantity;
	private String description;
	private String dept;
	private String empname;
	private Integer request_value;
	private String available_date;
	
	public String getAvailable_date() {
		return available_date;
	}
	public void setAvailable_date(String available_date) {
		this.available_date = available_date;
	}
	public Integer getRequest_value() {
		return request_value;
	}
	public void setRequest_value(Integer request_value) {
		this.request_value = request_value;
	}
	@Override
	public String toString() {
		return "AssetDisplayEnityt [id=" + id + ", empid=" + empid + ", asset=" + asset + ", received_date="
				+ received_date + ", return_date=" + return_date + ", quantity=" + quantity + ", description="
				+ description + ", dept=" + dept + ", empname=" + empname + ", request_value=" + request_value
				+ ", available_date=" + available_date + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getReceived_date() {
		return received_date;
	}
	public void setReceived_date(String received_date) {
		this.received_date = received_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}	
}
