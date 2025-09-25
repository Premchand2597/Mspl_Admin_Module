package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="assets")
public class AsseteEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empid;
	private String asset;
	private String received_date;
	private String return_date;
	private int quantity;
	private String description;
	private Integer dept;
	private Integer request_value;
	
	
	public Integer getRequest_value() {
		return request_value;
	}
	public void setRequest_value(Integer request_value) {
		this.request_value = request_value;
	}
	public void setDept(Integer dept) {
		this.dept = dept;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "AsseteEntity [id=" + id + ", empid=" + empid + ", asset=" + asset + ", received_date=" + received_date
				+ ", return_date=" + return_date + ", quantity=" + quantity + ", description=" + description + ", dept="
				+ dept + ", request_value=" + request_value + "]";
	}
	
	
}
