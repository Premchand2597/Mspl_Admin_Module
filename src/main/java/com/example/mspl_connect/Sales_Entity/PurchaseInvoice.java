package com.example.mspl_connect.Sales_Entity;

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
@Table(name = "purchase_invoice")
public class PurchaseInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String tax_type;
    private String date_of_supply;
    private String pi_no;
        
    @Column(name = "invoice_id")  // This maps to the DB column
    private String invoiceId;     // This is used in your Java code
    
    @Column(name="approve_by_ac")
    private String approve_by_ac;
    
    @Column(name="place_of_supply")
    private String placeOfSupply;
    
    @Column(name="vehicle")
    private String vehicle;
    
    @Column(name="state_name")
    private String stateName;
    
    @Column(name="sate_code")
    private String sateCode;
    
    @Column(name="transport_mode")
    private String transportMode;
    
    @Column(name = "total_amount")
    private Double totalAmount;
    
    @Column(name = "cgst")
    private Double cgst;
    
    @Column(name = "sgst")
    private Double sgst;
    
    @Column(name = "igst")
    private Double igst;
    
    @Column(name = "total_tax_gst")
    private Double totalTaxGst;
    
    @Column(name = "total_amt_after_tax_before_tcs")
    private Double totalAmtAfterTaxBeforeTcs;
    
    @Column(name = "tcs")
    private Double tcs;
    
    @Column(name = "total_tax")
    private Double totalTax;
    
    @Column(name = "totoal_tax_after_tcs")
    private Double totoalTaxAfterTcs;
    
    @Column(name = "gst_payable_rev_charge")
    private Double gstPayableRevCharge;
    
    @Column(name = "total_amt_after_rev_charge")
    private Double totalAmtAfterRevCharge;
    
    @Column(name = "round_off_decimal")
    private Double roundOffDecimal;
    
    @Column(name = "net_amt")
    private Double netAmt;
    
    @Column(name = "pdf_path")
    private String pdfPath;
    
    private String created_by;

    @OneToMany(mappedBy = "purchaseInvoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PurchaseInvoiceItem> items = new ArrayList<>();
    
	public String getDate_of_supply() {
		return date_of_supply;
	}

	public void setDate_of_supply(String date_of_supply) {
		this.date_of_supply = date_of_supply;
	}

	public String getTax_type() {
		return tax_type;
	}

	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getIgst() {
		return igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public Double getTcs() {
		return tcs;
	}

	public void setTcs(Double tcs) {
		this.tcs = tcs;
	}

	public String getPlaceOfSupply() {
		return placeOfSupply;
	}

	public void setPlaceOfSupply(String placeOfSupply) {
		this.placeOfSupply = placeOfSupply;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getSateCode() {
		return sateCode;
	}

	public void setSateCode(String sateCode) {
		this.sateCode = sateCode;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getApprove_by_ac() {
		return approve_by_ac;
	}

	public void setApprove_by_ac(String approve_by_ac) {
		this.approve_by_ac = approve_by_ac;
	}

	public String getInvoice_id() {
		return invoiceId;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoiceId = invoice_id;
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

	public List<PurchaseInvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseInvoiceItem> items) {
		this.items = items;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalTaxGst() {
		return totalTaxGst;
	}

	public void setTotalTaxGst(Double totalTaxGst) {
		this.totalTaxGst = totalTaxGst;
	}

	public Double getTotalAmtAfterTaxBeforeTcs() {
		return totalAmtAfterTaxBeforeTcs;
	}

	public void setTotalAmtAfterTaxBeforeTcs(Double totalAmtAfterTaxBeforeTcs) {
		this.totalAmtAfterTaxBeforeTcs = totalAmtAfterTaxBeforeTcs;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public Double getTotoalTaxAfterTcs() {
		return totoalTaxAfterTcs;
	}

	public void setTotoalTaxAfterTcs(Double totoalTaxAfterTcs) {
		this.totoalTaxAfterTcs = totoalTaxAfterTcs;
	}

	public Double getGstPayableRevCharge() {
		return gstPayableRevCharge;
	}

	public void setGstPayableRevCharge(Double gstPayableRevCharge) {
		this.gstPayableRevCharge = gstPayableRevCharge;
	}

	public Double getTotalAmtAfterRevCharge() {
		return totalAmtAfterRevCharge;
	}

	public void setTotalAmtAfterRevCharge(Double totalAmtAfterRevCharge) {
		this.totalAmtAfterRevCharge = totalAmtAfterRevCharge;
	}

	public Double getRoundOffDecimal() {
		return roundOffDecimal;
	}

	public void setRoundOffDecimal(Double roundOffDecimal) {
		this.roundOffDecimal = roundOffDecimal;
	}

	public Double getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(Double netAmt) {
		this.netAmt = netAmt;
	}
	
	public String getPi_no() {
		return pi_no;
	}

	public void setPi_no(String pi_no) {
		this.pi_no = pi_no;
	}
	
	

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public PurchaseInvoice() {
	    super(); 
	}

	@Override
	public String toString() {
		return "PurchaseInvoice [id=" + id + ", poNumber=" + poNumber + ", poDate=" + poDate + ", vendorCode="
				+ vendorCode + ", quotationId=" + quotationId + ", billingAddress=" + billingAddress
				+ ", shippingAddress=" + shippingAddress + ", custHonorifics=" + custHonorifics + ", custName="
				+ custName + ", cusLeagalName=" + cusLeagalName + ", gstNo=" + gstNo + ", orderMehtod=" + orderMehtod
				+ ", contactHonorifics=" + contactHonorifics + ", contPersonName=" + contPersonName + ", designation="
				+ designation + ", mobileNo=" + mobileNo + ", email=" + email + ", termsAndConditions="
				+ termsAndConditions + ", modeOfPayment=" + modeOfPayment + ", termsOfDelivery=" + termsOfDelivery
				+ ", purchaseId=" + purchaseId + ", createdDateTime=" + createdDateTime + ", invoiceId=" + invoiceId
				+ ", approve_by_ac=" + approve_by_ac + ", placeOfSupply=" + placeOfSupply + ", vehicle=" + vehicle
				+ ", stateName=" + stateName + ", sateCode=" + sateCode + ", transportMode=" + transportMode
				+ ", totalAmount=" + totalAmount + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst
				+ ", totalTaxGst=" + totalTaxGst + ", totalAmtAfterTaxBeforeTcs=" + totalAmtAfterTaxBeforeTcs + ", tcs="
				+ tcs + ", totalTax=" + totalTax + ", totoalTaxAfterTcs=" + totoalTaxAfterTcs + ", gstPayableRevCharge="
				+ gstPayableRevCharge + ", totalAmtAfterRevCharge=" + totalAmtAfterRevCharge + ", roundOffDecimal="
				+ roundOffDecimal + ", netAmt=" + netAmt + ", pdfPath=" + pdfPath + ", items=" + items + "]";
	}


	 

}

