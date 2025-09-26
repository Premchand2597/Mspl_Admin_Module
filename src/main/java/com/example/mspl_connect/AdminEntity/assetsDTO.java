package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class assetsDTO {

	@Id
	private int id;
	private String emp_id;
	private String asset_type;
	private int quantity;
	private String description;
	private Integer asset_approved;
	
	private String emp_name;
	private Integer assigned_asset_qty;
	
	private String req_date;
	private String requested_by;
	private String approver_name;
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getApprover_name() {
		return approver_name;
	}
	public void setApprover_name(String approver_name) {
		this.approver_name = approver_name;
	}
	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	public String getRequested_by() {
		return requested_by;
	}
	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}
	public Integer getAssigned_asset_qty() {
		return assigned_asset_qty;
	}
	public void setAssigned_asset_qty(Integer assigned_asset_qty) {
		this.assigned_asset_qty = assigned_asset_qty;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Integer getAsset_approved() {
		return asset_approved;
	}
	public void setAsset_approved(Integer asset_approved) {
		this.asset_approved = asset_approved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
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
	 
	public assetsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "assetsDTO [id=" + id + ", emp_id=" + emp_id + ", asset_type=" + asset_type + ", quantity=" + quantity
				+ ", description=" + description + ", asset_approved=" + asset_approved + ", emp_name=" + emp_name
				+ ", assigned_asset_qty=" + assigned_asset_qty + ", req_date=" + req_date + ", requested_by="
				+ requested_by + ", approver_name=" + approver_name + ", status=" + status + "]";
	}
	
}
