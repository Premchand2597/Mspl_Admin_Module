package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
 
		public PurchaseOrder() {
			super();
		}

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String poNumber;
	    private LocalDate poDate;
	    private String poGst;
	    private String billingAddress;
	    private String shippingAddress;
	    private String supplierName;
	    private String supplierGst;
	    private String vendorCode;
	    private LocalDateTime createdDateTime;
	    private Double grandTotal;
		private String cust_honorifics;
	    private String cust_name;
	    private String cus_leagal_name;
	    private String gst_no;
	    private String order_mehtod;
	    private String contact_honorifics;
	    private String cont_person_name;
	    private String designation;
	    private String mobile_no;	
	    private String email;
	    private String termsAndConditions; 
	    
	    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private List<PurchaseOrderItem> items = new ArrayList<>();
	    
	    // Add the quotationId field
	    private String quotationId; // New field for Quotation ID
	    private String tax_type;

		@Column(name = "purchase_id")
	    private String purchaseId;  // This is your custom dynamic PO ID

	    @Column(name = "created_by_empid")
	    private String createdByEmpId;
	    
	    @Column(name = "mode_of_payment")
	    private String modeOfPayment;
	    
	    @Column(name = "terms_of_delivery")
	    private String termsOfDelivery;
	    
	    public String getTax_type() {
			return tax_type;
		}
	    
		public void setTax_type(String tax_type) {
			this.tax_type = tax_type;
		}
		
	    public String getModeOfPayment() {
			return modeOfPayment;
		}

		public void setModeOfPayment(String modeOfPayment) {
			this.modeOfPayment = modeOfPayment;
		}

		public String getTermsOfDelivery() {
			return termsOfDelivery;
		}

		public void setTermsOfDelivery(String termsOfDelivery) {
			this.termsOfDelivery = termsOfDelivery;
		}

		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPoNumber() {
			return poNumber;
		}

		public void setPoNumber(String poNumber) {
			this.poNumber = poNumber;
		}

		public LocalDate getPoDate() {
			return poDate;
		}

		public void setPoDate(LocalDate poDate) {
			this.poDate = poDate;
		}

		public String getPoGst() {
			return poGst;
		}

		public void setPoGst(String poGst) {
			this.poGst = poGst;
		}

		public String getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(String billingAddress) {
			this.billingAddress = billingAddress;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}

		public String getSupplierGst() {
			return supplierGst;
		}

		public void setSupplierGst(String supplierGst) {
			this.supplierGst = supplierGst;
		}

		public String getVendorCode() {
			return vendorCode;
		}

		public void setVendorCode(String vendorCode) {
			this.vendorCode = vendorCode;
		}

		public LocalDateTime getCreatedDateTime() {
			return createdDateTime;
		}

		public void setCreatedDateTime(LocalDateTime createdDateTime) {
			this.createdDateTime = createdDateTime;
		}

		public Double getGrandTotal() {
			return grandTotal;
		}

		public void setGrandTotal(Double grandTotal) {
			this.grandTotal = grandTotal;
		}

		public List<PurchaseOrderItem> getItems() {
			return items;
		}

		public void setItems(List<PurchaseOrderItem> items) {
			this.items = items;
		}

		public String getCust_honorifics() {
			return cust_honorifics;
		}

		public void setCust_honorifics(String cust_honorifics) {
			this.cust_honorifics = cust_honorifics;
		}

		public String getCust_name() {
			return cust_name;
		}

		public void setCust_name(String cust_name) {
			this.cust_name = cust_name;
		}

		public String getCus_leagal_name() {
			return cus_leagal_name;
		}

		public void setCus_leagal_name(String cus_leagal_name) {
			this.cus_leagal_name = cus_leagal_name;
		}

		public String getGst_no() {
			return gst_no;
		}

		public void setGst_no(String gst_no) {
			this.gst_no = gst_no;
		}

		public String getOrder_mehtod() {
			return order_mehtod;
		}

		public void setOrder_mehtod(String order_mehtod) {
			this.order_mehtod = order_mehtod;
		}

		public String getContact_honorifics() {
			return contact_honorifics;
		}

		public void setContact_honorifics(String contact_honorifics) {
			this.contact_honorifics = contact_honorifics;
		}

		public String getCont_person_name() {
			return cont_person_name;
		}

		public void setCont_person_name(String cont_person_name) {
			this.cont_person_name = cont_person_name;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getMobile_no() {
			return mobile_no;
		}

		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
 
		public String getTermsAndConditions() {
			return termsAndConditions;
		}

		public void setTermsAndConditions(String termsAndConditions) {
			this.termsAndConditions = termsAndConditions;
		}

		public String getPurchaseId() {
			return purchaseId;
		}

		public void setPurchaseId(String purchaseId) {
			this.purchaseId = purchaseId;
		}

		public String getCreatedByEmpId() {
			return createdByEmpId;
		}

		public void setCreatedByEmpId(String createdByEmpId) {
			this.createdByEmpId = createdByEmpId;
		}

		public String getQuotationId() {
			return quotationId;
		}

		public void setQuotationId(String quotationId) {
			this.quotationId = quotationId;
		}

		public PurchaseOrder(Long id, String poNumber, LocalDate poDate, String poGst, String billingAddress,
				String shippingAddress, String supplierName, String supplierGst, String vendorCode,
				LocalDateTime createdDateTime, Double grandTotal, String cust_honorifics, String cust_name,
				String cus_leagal_name, String gst_no, String order_mehtod, String contact_honorifics,
				String cont_person_name, String designation, String mobile_no, String email, String termsAndConditions,
				String quotationId, String tax_type, String purchaseId, String createdByEmpId, String modeOfPayment,
				String termsOfDelivery, List<PurchaseOrderItem> items) {
			super();
			this.id = id;
			this.poNumber = poNumber;
			this.poDate = poDate;
			this.poGst = poGst;
			this.billingAddress = billingAddress;
			this.shippingAddress = shippingAddress;
			this.supplierName = supplierName;
			this.supplierGst = supplierGst;
			this.vendorCode = vendorCode;
			this.createdDateTime = createdDateTime;
			this.grandTotal = grandTotal;
			this.cust_honorifics = cust_honorifics;
			this.cust_name = cust_name;
			this.cus_leagal_name = cus_leagal_name;
			this.gst_no = gst_no;
			this.order_mehtod = order_mehtod;
			this.contact_honorifics = contact_honorifics;
			this.cont_person_name = cont_person_name;
			this.designation = designation;
			this.mobile_no = mobile_no;
			this.email = email;
			this.termsAndConditions = termsAndConditions;
			this.quotationId = quotationId;
			this.tax_type = tax_type;
			this.purchaseId = purchaseId;
			this.createdByEmpId = createdByEmpId;
			this.modeOfPayment = modeOfPayment;
			this.termsOfDelivery = termsOfDelivery;
			this.items = items;
		}

	
}
