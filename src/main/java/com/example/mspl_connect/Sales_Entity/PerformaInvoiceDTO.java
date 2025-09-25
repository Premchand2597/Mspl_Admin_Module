package com.example.mspl_connect.Sales_Entity;

public class PerformaInvoiceDTO {

    private Long id;

    private String poNumber;
    private String poDate;
    private String vendorCode;
    private String quotationId;
    private String billingAddress;
    private String shippingAddress;
    private String custHonorifics;
    private String custName;
    private String cusLeagalName;
    private String gstNo;
    private String orderMehtod;
    private String contactHonorifics;
    private String contPersonName;
    private String designation;
    private String mobileNo;
    private String email;
    private String termsAndConditions;
    private String modeOfPayment;
    private String termsOfDelivery;
    private String purchaseId;
    private String createdDateTime;
    private String invoice_id;
    
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
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
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
	public String getCustHonorifics() {
		return custHonorifics;
	}
	public void setCustHonorifics(String custHonorifics) {
		this.custHonorifics = custHonorifics;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCusLeagalName() {
		return cusLeagalName;
	}
	public void setCusLeagalName(String cusLeagalName) {
		this.cusLeagalName = cusLeagalName;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getOrderMehtod() {
		return orderMehtod;
	}
	public void setOrderMehtod(String orderMehtod) {
		this.orderMehtod = orderMehtod;
	}
	public String getContactHonorifics() {
		return contactHonorifics;
	}
	public void setContactHonorifics(String contactHonorifics) {
		this.contactHonorifics = contactHonorifics;
	}
	public String getContPersonName() {
		return contPersonName;
	}
	public void setContPersonName(String contPersonName) {
		this.contPersonName = contPersonName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	@Override
	public String toString() {
		return "PurchaseInvoiceDTO [id=" + id + ", poNumber=" + poNumber + ", poDate=" + poDate + ", vendorCode="
				+ vendorCode + ", quotationId=" + quotationId + ", billingAddress=" + billingAddress
				+ ", shippingAddress=" + shippingAddress + ", custHonorifics=" + custHonorifics + ", custName="
				+ custName + ", cusLeagalName=" + cusLeagalName + ", gstNo=" + gstNo + ", orderMehtod=" + orderMehtod
				+ ", contactHonorifics=" + contactHonorifics + ", contPersonName=" + contPersonName + ", designation="
				+ designation + ", mobileNo=" + mobileNo + ", email=" + email + ", termsAndConditions="
				+ termsAndConditions + ", modeOfPayment=" + modeOfPayment + ", termsOfDelivery=" + termsOfDelivery
				+ ", purchaseId=" + purchaseId + ", createdDateTime=" + createdDateTime + ", invoice_id=" + invoice_id
				+ ", approveByAc=" + "]";
	}
	
	 
	public PerformaInvoiceDTO(Long id, String poNumber, String poDate, String vendorCode, String quotationId,
			String billingAddress, String shippingAddress, String custHonorifics, String custName, String cusLeagalName,
			String gstNo, String orderMehtod, String contactHonorifics, String contPersonName, String designation,
			String mobileNo, String email, String termsAndConditions, String modeOfPayment, String termsOfDelivery,
			String purchaseId, String createdDateTime, String invoice_id, String approveByAc) {
		super();
		this.id = id;
		this.poNumber = poNumber;
		this.poDate = poDate;
		this.vendorCode = vendorCode;
		this.quotationId = quotationId;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.custHonorifics = custHonorifics;
		this.custName = custName;
		this.cusLeagalName = cusLeagalName;
		this.gstNo = gstNo;
		this.orderMehtod = orderMehtod;
		this.contactHonorifics = contactHonorifics;
		this.contPersonName = contPersonName;
		this.designation = designation;
		this.mobileNo = mobileNo;
		this.email = email;
		this.termsAndConditions = termsAndConditions;
		this.modeOfPayment = modeOfPayment;
		this.termsOfDelivery = termsOfDelivery;
		this.purchaseId = purchaseId;
		this.createdDateTime = createdDateTime;
		this.invoice_id = invoice_id; 
	}
	public PerformaInvoiceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

