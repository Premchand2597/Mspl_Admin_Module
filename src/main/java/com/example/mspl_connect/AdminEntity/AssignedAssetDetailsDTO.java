package com.example.mspl_connect.AdminEntity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
public class AssignedAssetDetailsDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String assignedAssetId;
    private String assignedTo;
    private String assetType;
    private int quantity;
    private String assetId;
    private String ref_asset_id;
    private String remarks;
    
	@Column(name="empname")
    private String empName;
	
	public String getRef_asset_id() {
		return ref_asset_id;
	}
	public void setRef_asset_id(String ref_asset_id) {
		this.ref_asset_id = ref_asset_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssignedAssetId() {
		return assignedAssetId;
	}
	public void setAssignedAssetId(String assignedAssetId) {
		this.assignedAssetId = assignedAssetId;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "AssignedAssetDetailsDTO [id=" + id + ", assignedAssetId=" + assignedAssetId + ", assignedTo="
				+ assignedTo + ", assetType=" + assetType + ", quantity=" + quantity + ", assetId=" + assetId
				+ ", ref_asset_id=" + ref_asset_id + ", remarks=" + remarks + ", empName=" + empName + "]";
	}
	 
	public AssignedAssetDetailsDTO(Integer id, String assignedAssetId, String assignedTo, String assetType,
			int quantity, String assetId, String ref_asset_id, String empName) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assignedTo = assignedTo;
		this.assetType = assetType;
		this.quantity = quantity;
		this.assetId = assetId;
		this.ref_asset_id = ref_asset_id;
		this.empName = empName;
	}
	public AssignedAssetDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
 
}
