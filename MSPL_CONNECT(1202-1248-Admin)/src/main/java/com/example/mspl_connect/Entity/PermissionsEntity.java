package com.example.mspl_connect.Entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissions")
public class PermissionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_id")
    private String userId;

    @Column(name = "interview_access")
    private boolean interviewAccess;
    
    @Column(name = "other_permission1")
    private boolean otherPermission1;
    
    @Column(name = "sales")
    private boolean sales;
    
    @Column(name="accounts")
    private boolean accountsAccess;
    
    @Column(name="store")
    private boolean storeAccess;
    
    @Column(name="apprisal_link")
    private boolean apprisalAccess;
    
    @Column(name="attendance")
    private Boolean attendance;
    
    @Column(name="doc_upload")
    private Boolean doc_upload;
    
    @Column(name="doc_date")
    private String doc_date;
    
	public Boolean getDoc_upload() {
		return doc_upload;
	}

	public void setDoc_upload(Boolean doc_upload) {
		this.doc_upload = doc_upload;
	}

	public String getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}

	public Boolean getAttendance() {
		return attendance;
	}

	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}

	// Add a method to return permission-based links
    public Map<String, Boolean> getPermissionLinks() {
        Map<String, Boolean> links = new HashMap<>();
        links.put("Interview", interviewAccess);
        links.put("Other", otherPermission1);
        links.put("Sales", sales);
        links.put("accounts", accountsAccess);
        links.put("store", storeAccess);
        links.put("apprisalLink", apprisalAccess);
        return links;
    }

	@Override
	public String toString() {
		return "PermissionsEntity [id=" + id + ", userId=" + userId + ", interviewAccess=" + interviewAccess
				+ ", otherPermission1=" + otherPermission1 + ", sales=" + sales + ", accountsAccess=" + accountsAccess
				+ ", storeAccess=" + storeAccess + ", apprisalAccess=" + apprisalAccess + ", attendance=" + attendance
				+ ", doc_upload=" + doc_upload + ", doc_date=" + doc_date + "]";
	}
	

	public boolean isApprisalAccess() {
		return apprisalAccess;
	}

	public void setApprisalAccess(boolean apprisalAccess) {
		this.apprisalAccess = apprisalAccess;
	}

	public boolean isAccountsAccess() {
		return accountsAccess;
	}

	public void setAccountsAccess(boolean accountsAccess) {
		this.accountsAccess = accountsAccess;
	}

	public boolean isStoreAccess() {
		return storeAccess;
	}

	public void setStoreAccess(boolean storeAccess) {
		this.storeAccess = storeAccess;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isInterviewAccess() {
		return interviewAccess;
	}

	public void setInterviewAccess(boolean interviewAccess) {
		this.interviewAccess = interviewAccess;
	}

	public boolean isOtherPermission1() {
		return otherPermission1;
	}

	public void setOtherPermission1(boolean otherPermission1) {
		this.otherPermission1 = otherPermission1;
	} 
	 public boolean isSales() {
			return sales;
		}

		public void setSales(boolean sales) {
			this.sales = sales;
		}
    
}
