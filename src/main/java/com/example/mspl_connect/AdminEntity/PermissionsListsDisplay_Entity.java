package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PermissionsListsDisplay_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emp_details_table_id;
	private String emp_details_table_empid;
	private String emp_name;
	private String id;
	private String emp_id;
	private String interview_access;
	private String other_permission1;
	private String sales;
	private String store;
	private String accounts;
	private String apprisal_link;
	private String attendance;
	private String doc_upload;
	private String crm;
	private String asset_admin;
	private String book_of_knowledge;
	private String dispatch;
	
	public long getEmp_details_table_id() {
		return emp_details_table_id;
	}
	public void setEmp_details_table_id(long emp_details_table_id) {
		this.emp_details_table_id = emp_details_table_id;
	}
	public String getEmp_details_table_empid() {
		return emp_details_table_empid;
	}
	public void setEmp_details_table_empid(String emp_details_table_empid) {
		this.emp_details_table_empid = emp_details_table_empid;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getInterview_access() {
		return interview_access;
	}
	public void setInterview_access(String interview_access) {
		this.interview_access = interview_access;
	}
	public String getOther_permission1() {
		return other_permission1;
	}
	public void setOther_permission1(String other_permission1) {
		this.other_permission1 = other_permission1;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getApprisal_link() {
		return apprisal_link;
	}
	public void setApprisal_link(String apprisal_link) {
		this.apprisal_link = apprisal_link;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getDoc_upload() {
		return doc_upload;
	}
	public void setDoc_upload(String doc_upload) {
		this.doc_upload = doc_upload;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getBook_of_knowledge() {
		return book_of_knowledge;
	}
	public void setBook_of_knowledge(String book_of_knowledge) {
		this.book_of_knowledge = book_of_knowledge;
	}
	public String getAsset_admin() {
		return asset_admin;
	}
	public void setAsset_admin(String asset_admin) {
		this.asset_admin = asset_admin;
	}
	public String getDispatch() {
		return dispatch;
	}
	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
	@Override
	public String toString() {
		return "PermissionsListsDisplay_Entity [emp_details_table_id=" + emp_details_table_id
				+ ", emp_details_table_empid=" + emp_details_table_empid + ", emp_name=" + emp_name + ", id=" + id
				+ ", emp_id=" + emp_id + ", interview_access=" + interview_access + ", other_permission1="
				+ other_permission1 + ", sales=" + sales + ", store=" + store + ", accounts=" + accounts
				+ ", apprisal_link=" + apprisal_link + ", attendance=" + attendance + ", doc_upload=" + doc_upload
				+ ", crm=" + crm + ", asset_admin=" + asset_admin + ", book_of_knowledge=" + book_of_knowledge
				+ ", dispatch=" + dispatch + "]";
	}
}
