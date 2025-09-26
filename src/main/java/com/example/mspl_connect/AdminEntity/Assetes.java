package com.example.mspl_connect.AdminEntity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="assets")
public class Assetes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String asset_name;
	private int quantity;
	private String description;
	private String dept;
	private String modifieddate;
	private String asset_id;
	private String modified_empid;
	private String action;
	private String assigned_to;
	private Integer active_inactive_status;
	 // ✅ New field
    private String remarks;
    
	public String getRemarks() {
		return remarks;
	}
	public Assetes(int id, String asset_name, int quantity, String description, String dept, String modifieddate,
			String asset_id, String modified_empid, String action, String assigned_to, Integer active_inactive_status,
			String remarks) {
		super();
		this.id = id;
		this.asset_name = asset_name;
		this.quantity = quantity;
		this.description = description;
		this.dept = dept;
		this.modifieddate = modifieddate;
		this.asset_id = asset_id;
		this.modified_empid = modified_empid;
		this.action = action;
		this.assigned_to = assigned_to;
		this.active_inactive_status = active_inactive_status;
		this.remarks = remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getActive_inactive_status() {
		return active_inactive_status;
	}
	public void setActive_inactive_status(Integer active_inactive_status) {
		this.active_inactive_status = active_inactive_status;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getModified_empid() {
		return modified_empid;
	}
	public void setModified_empid(String modified_empid) {
		this.modified_empid = modified_empid;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	
	@Override
	public String toString() {
		return "Assetes [id=" + id + ", asset_name=" + asset_name + ", quantity=" + quantity + ", description="
				+ description + ", dept=" + dept + ", modifieddate=" + modifieddate + ", asset_id=" + asset_id
				+ ", modified_empid=" + modified_empid + ", action=" + action + ", assigned_to=" + assigned_to
				+ ", active_inactive_status=" + active_inactive_status + "]";
	}
	 
	public Assetes(int id, String asset_name, int quantity, String description, String dept, String modifieddate,
			String asset_id, String modified_empid, String action, String assigned_to, Integer active_inactive_status) {
		super();
		this.id = id;
		this.asset_name = asset_name;
		this.quantity = quantity;
		this.description = description;
		this.dept = dept;
		this.modifieddate = modifieddate;
		this.asset_id = asset_id;
		this.modified_empid = modified_empid;
		this.action = action;
		this.assigned_to = assigned_to;
		this.active_inactive_status = active_inactive_status;
	}
	public Assetes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
