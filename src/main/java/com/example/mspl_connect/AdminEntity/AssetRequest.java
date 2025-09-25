package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="assets_request")
public class AssetRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="emp_id")
    private String empId;
    private String assetType;
    private int quantity;
    private String description;
    private Integer asset_approved;
    
    private Integer assigned_asset_qty;
    
    private String requested_by;
    private String req_date;
    private Integer status;
    
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRequested_by() {
		return requested_by;
	}
	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}
	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	public Integer getAssigned_asset_qty() {
		return assigned_asset_qty;
	}
	public void setAssigned_asset_qty(Integer assigned_asset_qty) {
		this.assigned_asset_qty = assigned_asset_qty;
	}
	public Integer getAsset_approved() {
		return asset_approved;
	}
	public void setAsset_approved(Integer asset_approved) {
		this.asset_approved = asset_approved;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AssetRequest [id=" + id + ", empId=" + empId + ", assetType=" + assetType + ", quantity=" + quantity
				+ ", description=" + description + ", asset_approved=" + asset_approved + ", assigned_asset_qty="
				+ assigned_asset_qty + ", requested_by=" + requested_by + ", req_date=" + req_date + ", status="
				+ status + "]";
	}
 
	public AssetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public AssetRequest(Long id, String empId, String assetType, int quantity, String description,
			Integer asset_approved, Integer assigned_asset_qty, String requested_by, String req_date, Integer status) {
		super();
		this.id = id;
		this.empId = empId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.description = description;
		this.asset_approved = asset_approved;
		this.assigned_asset_qty = assigned_asset_qty;
		this.requested_by = requested_by;
		this.req_date = req_date;
		this.status = status;
	}
	
}
