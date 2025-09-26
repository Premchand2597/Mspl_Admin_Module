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
@Table(name = "asset_replace")
public class AssetReplace {

    public AssetReplace() {
		super();
	}

	public AssetReplace(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime requestedAt, String remarks,
			String replacementAssetId, String status, LocalDateTime approvedAt, String approvedName) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.requestedAt = requestedAt;
		this.remarks = remarks;
		this.replacementAssetId = replacementAssetId;
		this.status = status;
		this.approvedAt = approvedAt;
		this.approvedName = approvedName;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String assignedAssetId;
    private String assetId;
    private String assetType;
    private Integer quantity;
    private String refAssetId;
    private String description;

    private String senderEmpId;             // who requested replacement
    private LocalDateTime requestedAt;      // timestamp
    private String remarks;                  // remarks from user
    private String replacementAssetId;      // new asset id
    private String status = "Pending";      // default

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_name")
    private String approvedName;

    @Column(name = "old_asset_id")
    private Integer oldAssetId;   // or Long depending on AssignedAssets.id type

    @Transient  // not stored in DB
    private String senderName;

    
    
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getId() {
		return id;
	}



	public AssetReplace(int id, String assignedAssetId, String assetId, String assetType, Integer quantity,
			String refAssetId, String description, String senderEmpId, LocalDateTime requestedAt, String remarks,
			String replacementAssetId, String status, LocalDateTime approvedAt, String approvedName,
			Integer oldAssetId) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.assetId = assetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.refAssetId = refAssetId;
		this.description = description;
		this.senderEmpId = senderEmpId;
		this.requestedAt = requestedAt;
		this.remarks = remarks;
		this.replacementAssetId = replacementAssetId;
		this.status = status;
		this.approvedAt = approvedAt;
		this.approvedName = approvedName;
		this.oldAssetId = oldAssetId;
	}

	public Integer getOldAssetId() {
		return oldAssetId;
	}

	public void setOldAssetId(Integer oldAssetId) {
		this.oldAssetId = oldAssetId;
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

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReplacementAssetId() {
		return replacementAssetId;
	}

	public void setReplacementAssetId(String replacementAssetId) {
		this.replacementAssetId = replacementAssetId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
