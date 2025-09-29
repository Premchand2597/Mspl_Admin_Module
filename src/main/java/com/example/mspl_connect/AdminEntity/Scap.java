package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scap")
public class Scap
 {
	   public Scap() {
		super();
	}

	public Scap(Integer id, String assignedAssetId, String oldAssetId, String assetId, String refAssetId,
			String assetType, int quantity, String assignedTo, LocalDateTime assignedAt, String description,
			LocalDateTime requestedAt, String remarks) {
		super();
		this.id = id;
		this.assignedAssetId = assignedAssetId;
		this.oldAssetId = oldAssetId;
		this.assetId = assetId;
		this.refAssetId = refAssetId;
		this.assetType = assetType;
		this.quantity = quantity;
		this.assignedTo = assignedTo;
		this.assignedAt = assignedAt;
		this.description = description;
		this.requestedAt = requestedAt;
		this.remarks = remarks;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "assigned_asset_id")
	    private String assignedAssetId;

	    @Column(name = "old_asset_id")
	    private String oldAssetId;

	    @Column(name = "asset_id")
	    private String assetId;

	    @Column(name = "ref_asset_id")
	    private String refAssetId;

	    @Column(name = "asset_type")
	    private String assetType;

	    private int quantity;

	    @Column(name = "assigned_to")
	    private String assignedTo;

	    @Column(name = "assigned_at")
	    private LocalDateTime assignedAt;

	    private String description;

	    private LocalDateTime requestedAt;

	    private String remarks;

	    // NEW FIELDS
	    private String senderEmpId;      // employee who requested replacement
	    public Scap(Integer id, String assignedAssetId, String oldAssetId, String assetId, String refAssetId,
				String assetType, int quantity, String assignedTo, LocalDateTime assignedAt, String description,
				LocalDateTime requestedAt, String remarks, String senderEmpId, Integer requestReplaceId) {
			super();
			this.id = id;
			this.assignedAssetId = assignedAssetId;
			this.oldAssetId = oldAssetId;
			this.assetId = assetId;
			this.refAssetId = refAssetId;
			this.assetType = assetType;
			this.quantity = quantity;
			this.assignedTo = assignedTo;
			this.assignedAt = assignedAt;
			this.description = description;
			this.requestedAt = requestedAt;
			this.remarks = remarks;
			this.senderEmpId = senderEmpId;
			this.requestReplaceId = requestReplaceId;
		}

		private Integer requestReplaceId; // reference to AssetReplace row
	    
		public String getSenderEmpId() {
			return senderEmpId;
		}

		public void setSenderEmpId(String senderEmpId) {
			this.senderEmpId = senderEmpId;
		}

		public Integer getRequestReplaceId() {
			return requestReplaceId;
		}

		public void setRequestReplaceId(Integer requestReplaceId) {
			this.requestReplaceId = requestReplaceId;
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

		public String getOldAssetId() {
			return oldAssetId;
		}

		public void setOldAssetId(String oldAssetId) {
			this.oldAssetId = oldAssetId;
		}

		public String getAssetId() {
			return assetId;
		}

		public void setAssetId(String assetId) {
			this.assetId = assetId;
		}

		public String getRefAssetId() {
			return refAssetId;
		}

		public void setRefAssetId(String refAssetId) {
			this.refAssetId = refAssetId;
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

		public String getAssignedTo() {
			return assignedTo;
		}

		public void setAssignedTo(String assignedTo) {
			this.assignedTo = assignedTo;
		}

		public LocalDateTime getAssignedAt() {
			return assignedAt;
		}

		public void setAssignedAt(LocalDateTime assignedAt) {
			this.assignedAt = assignedAt;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		
}
