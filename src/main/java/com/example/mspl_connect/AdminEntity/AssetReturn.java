package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "asset_return")
public class AssetReturn {
	  public AssetReturn(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime returnedAt, String remarks,
			String rejectionRemarks, String status, String approvedBy, LocalDateTime approvedAt, String approvedName) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.returnedAt = returnedAt;
		this.remarks = remarks;
		this.rejectionRemarks = rejectionRemarks;
		this.status = status;
		this.approvedBy = approvedBy;
		this.approvedAt = approvedAt;
		this.approvedName = approvedName;
	}
	public AssetReturn(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime returnedAt, String remarks,
			String rejectionRemarks, String status) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.returnedAt = returnedAt;
		this.remarks = remarks;
		this.rejectionRemarks = rejectionRemarks;
		this.status = status;
	}
	public AssetReturn(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime returnedAt, String remarks,
			String status) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.returnedAt = returnedAt;
		this.remarks = remarks;
		this.status = status;
	}
	public AssetReturn() {
		super();
	}
	public AssetReturn(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime returnedAt) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.returnedAt = returnedAt;
	}
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String assignedAssetId;   // AssignedAssets.assigned_asset_id
	    private String assetId;           // AssignedAssets.asset_id
	    private String assetType;         // AssignedAssets.asset_type
	    private Integer quantity;         // AssignedAssets.quantity
	    private String refAssetId;        // AssignedAssets.ref_asset_id
	    private String description;       // AssignedAssets.description

	    private String senderEmpId;       // who returned the asset
	    private LocalDateTime returnedAt; // return timestamp
	    private String remarks;           // <-- new field
	    private String rejectionRemarks;
	    private String status = "Pending"; // <-- default value
	    
	    @Column(name = "approved_by")
	    private String approvedBy; // email or empId of approving employee

	    @Column(name = "approved_at")
	    private LocalDateTime approvedAt; // date & time of approval

	    @Transient  // not stored in DB
	    private String senderName;
	    
	    @Column(name = "approved_name")
	    private String approvedName;  
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getAssignedAssetId() {
			return assignedAssetId;
		}
		public void setAssignedAssetId(String assignedAssetId) {
			this.assignedAssetId = assignedAssetId;
		}
		public String getAssetId() {
			return assetId;
		}
		public void setAssetId(String assetId) {
			this.assetId = assetId;
		}
		public String getAssetType() {
			return assetType;
		}
		public void setAssetType(String assetType) {
			this.assetType = assetType;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getRefAssetId() {
			return refAssetId;
		}
		public void setRefAssetId(String refAssetId) {
			this.refAssetId = refAssetId;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getSenderEmpId() {
			return senderEmpId;
		}
		public void setSenderEmpId(String senderEmpId) {
			this.senderEmpId = senderEmpId;
		}
		public LocalDateTime getReturnedAt() {
			return returnedAt;
		}
		public void setReturnedAt(LocalDateTime returnedAt) {
			this.returnedAt = returnedAt;
		}
		@Override
		public String toString() {
			return "AssetReturn [id=" + id + ", assignedAssetId=" + assignedAssetId + ", assetId=" + assetId
					+ ", assetType=" + assetType + ", quantity=" + quantity + ", refAssetId=" + refAssetId
					+ ", description=" + description + ", senderEmpId=" + senderEmpId + ", returnedAt=" + returnedAt
					+ "]";
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getRejectionRemarks() {
			return rejectionRemarks;
		}
		public void setRejectionRemarks(String rejectionRemarks) {
			this.rejectionRemarks = rejectionRemarks;
		}
		public String getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(String approvedBy) {
			this.approvedBy = approvedBy;
		}
		public LocalDateTime getApprovedAt() {
			return approvedAt;
		}
		public void setApprovedAt(LocalDateTime approvedAt) {
			this.approvedAt = approvedAt;
		}
		public String getApprovedName() {
			return approvedName;
		}
		public void setApprovedName(String approvedName) {
			this.approvedName = approvedName;
		}
		public String getSenderName() {
			return senderName;
		}
		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}
	    
	    
	    

}
