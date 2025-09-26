package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class AssignedAssets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String assigned_asset_id;
	private String assigned_to;
	private String asset_type;
	private Integer quantity;
	private String description;
	private String remarks;
	private String asset_id;
	private String ref_asset_id;
	
	@Transient   // not persisted, only for display
	private String empName;
	 
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRef_asset_id() {
		return ref_asset_id;
	}

	public void setRef_asset_id(String ref_asset_id) {
		this.ref_asset_id = ref_asset_id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 
	
	public String getAssigned_asset_id() {
		return assigned_asset_id;
	}
	public void setAssigned_asset_id(String assigned_asset_id) {
		this.assigned_asset_id = assigned_asset_id;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public AssignedAssets() {
		super(); 
	}
	
	@Override
	public String toString() {
		return "AssignedAssets [id=" + id + ", assigned_asset_id=" + assigned_asset_id + ", assigned_to=" + assigned_to
				+ ", asset_type=" + asset_type + ", quantity=" + quantity + ", description=" + description
				+ ", remarks=" + remarks + ", asset_id=" + asset_id + ", ref_asset_id=" + ref_asset_id + "]";
	}

	public AssignedAssets(int id, String assigned_asset_id, String assigned_to, String asset_type, Integer quantity,
			String description, String remarks, String asset_id, String ref_asset_id) {
		super();
		this.id = id;
		this.assigned_asset_id = assigned_asset_id;
		this.assigned_to = assigned_to;
		this.asset_type = asset_type;
		this.quantity = quantity;
		this.description = description;
		this.remarks = remarks;
		this.asset_id = asset_id;
		this.ref_asset_id = ref_asset_id;
	} 

}
