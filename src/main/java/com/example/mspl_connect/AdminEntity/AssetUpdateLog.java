package com.example.mspl_connect.AdminEntity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AssetUpdateLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;  // Changed from log_id to logId (Java Naming Convention)

    private String assetId;  // Changed from asset_id to assetId
    private Integer oldQuantity;  // Changed from old_quantity to oldQuantity
    private Integer newQuantity;
    private String oldModifieddate;
    private String newModifieddate;
    private LocalDateTime updatedAt;
    private String modifiedBy;
    private String Action;
    private String AssignedTo;
    
    private String modified_by_name;
    private String assigned_to_name;
    
    //private String modifiedEmpName;

	@Override
	public String toString() {
		return "AssetUpdateLog [logId=" + logId + ", assetId=" + assetId + ", oldQuantity=" + oldQuantity
				+ ", newQuantity=" + newQuantity + ", oldModifieddate=" + oldModifieddate + ", newModifieddate="
				+ newModifieddate + ", updatedAt=" + updatedAt + ", modifiedBy=" + modifiedBy + ", Action=" + Action
				+ ", AssignedTo=" + AssignedTo + ", modifiedEmpName="  + "]";
	}
	
    /*	public String getModifiedEmpName() {
		return modifiedEmpName;
	}

	public void setModifiedEmpName(String modifiedEmpName) {
		this.modifiedEmpName = modifiedEmpName;
	} */

	
	
	
	public String getAssignedTo() {
		return AssignedTo;
	}

	public String getModified_by_name() {
		return modified_by_name;
	}

	public void setModified_by_name(String modified_by_name) {
		this.modified_by_name = modified_by_name;
	}

	public String getAssigned_to_name() {
		return assigned_to_name;
	}

	public void setAssigned_to_name(String assigned_to_name) {
		this.assigned_to_name = assigned_to_name;
	}

	public void setAssignedTo(String assignedTo) {
		AssignedTo = assignedTo;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	} 
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public Integer getOldQuantity() {
		return oldQuantity;
	}

	public void setOldQuantity(Integer oldQuantity) {
		this.oldQuantity = oldQuantity;
	}
 
	public Integer getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(Integer newQuantity) {
		this.newQuantity = newQuantity;
	}

	public String getOldModifieddate() {
		return oldModifieddate;
	}

	public void setOldModifiedDate(String oldModifiedDate) {
		this.oldModifieddate = oldModifiedDate;
	}

	public String getNewModifiedDate() {
		return newModifieddate;
	}

	public void setNewModifiedDate(String newModifiedDate) {
		this.newModifieddate = newModifiedDate;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	} 
	
	public String getModifiedBy() {
		return modifiedBy;
	} 
	
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public AssetUpdateLog() {
		super();
	}
	
}
