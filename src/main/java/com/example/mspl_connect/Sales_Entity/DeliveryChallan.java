package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_challan_new")
public class DeliveryChallan {

	 public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName, Double roundedGrandTotal, String deliveryAddress) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
		this.purchaseOrderId = purchaseOrderId;
		this.quotationId = quotationId;
		this.createdDate = createdDate;
		this.createdByEmpId = createdByEmpId;
		this.billingCompanyName = billingCompanyName;
		this.roundedGrandTotal = roundedGrandTotal;
		this.deliveryAddress = deliveryAddress;
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName, Double roundedGrandTotal) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
		this.purchaseOrderId = purchaseOrderId;
		this.quotationId = quotationId;
		this.createdDate = createdDate;
		this.createdByEmpId = createdByEmpId;
		this.billingCompanyName = billingCompanyName;
		this.roundedGrandTotal = roundedGrandTotal;
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
		this.purchaseOrderId = purchaseOrderId;
		this.quotationId = quotationId;
		this.createdDate = createdDate;
		this.createdByEmpId = createdByEmpId;
		this.billingCompanyName = billingCompanyName;
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
		this.purchaseOrderId = purchaseOrderId;
		this.quotationId = quotationId;
		this.createdDate = createdDate;
		this.createdByEmpId = createdByEmpId;
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
		this.purchaseOrderId = purchaseOrderId;
		this.quotationId = quotationId;
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
		this.paymentTerms = paymentTerms;
		this.referenceNoDate = referenceNoDate;
	}
	public DeliveryChallan() {
		super();
	}
	public DeliveryChallan(Long id, String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan) {
		super();
		this.id = id;
		this.supplierAddress = supplierAddress;
		this.buyer = buyer;
		this.deliveryNoteNo = deliveryNoteNo;
		this.dated = dated;
		this.referenceNo = referenceNo;
		this.otherReferences = otherReferences;
		this.termsOfDelivery = termsOfDelivery;
		this.buyersOrderNo = buyersOrderNo;
		this.buyersOrderDate = buyersOrderDate;
		this.remarks = remarks;
		this.companyPan = companyPan;
	}
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @Column(name = "supplier_address")
	    private String supplierAddress;
	 
	 @Column(name = "buyer")
	    private String buyer;
	 
	 @Column(name = "delivery_note_no")
	    private String deliveryNoteNo;
	 
	 @Column(name = "dated")
	    private LocalDate dated;
	 
	 @Column(name = "reference_no")
	    private String referenceNo;
	 
	 @Column(name = "other_references")
	    private String otherReferences;
	 
	 @Column(name = "terms_of_delivery")
	    private String termsOfDelivery;
	 
	 @Column(name = "buyers_order_no")
	    private String buyersOrderNo;
	 
	 @Column(name = "buyers_order_date")
	    private LocalDate buyersOrderDate;
	 
	 @Column(name = "remarks")
	    private String remarks;
	    
	    @Column(name = "company_pan")
	    private String companyPan;
	    
	    @Column(name = "payment_terms")
	    private String paymentTerms;
	    
	    @Column(name = "reference_no_date")
	    private String referenceNoDate;
	    
	    
	    @Column(name = "purchase_order_id")
	    private String purchaseOrderId;

	    @Column(name = "quotation_id")
	    private String quotationId;
	    
	    

	    @Column(name = "created_date")
	    private LocalDateTime createdDate;

	    @Column(name = "created_by_emp_id")
	    private String createdByEmpId;
	    
	    @Column(name = "billing_company_name")
	    private String billingCompanyName;
	    
	    @Column(name = "rounded_grand_total")
	    private Double roundedGrandTotal;  // New Column

	    
	    @Column(name = "delivery_address")  // New column for Delivery Address
	    private String deliveryAddress;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSupplierAddress() {
			return supplierAddress;
		}
		public void setSupplierAddress(String supplierAddress) {
			this.supplierAddress = supplierAddress;
		}
		public String getBuyer() {
			return buyer;
		}
		public void setBuyer(String buyer) {
			this.buyer = buyer;
		}
		public String getDeliveryNoteNo() {
			return deliveryNoteNo;
		}
		public void setDeliveryNoteNo(String deliveryNoteNo) {
			this.deliveryNoteNo = deliveryNoteNo;
		}
		public LocalDate getDated() {
			return dated;
		}
		public void setDated(LocalDate dated) {
			this.dated = dated;
		}
		public String getReferenceNo() {
			return referenceNo;
		}
		public void setReferenceNo(String referenceNo) {
			this.referenceNo = referenceNo;
		}
		public String getOtherReferences() {
			return otherReferences;
		}
		public void setOtherReferences(String otherReferences) {
			this.otherReferences = otherReferences;
		}
		public String getTermsOfDelivery() {
			return termsOfDelivery;
		}
		public void setTermsOfDelivery(String termsOfDelivery) {
			this.termsOfDelivery = termsOfDelivery;
		}
		public String getBuyersOrderNo() {
			return buyersOrderNo;
		}
		public void setBuyersOrderNo(String buyersOrderNo) {
			this.buyersOrderNo = buyersOrderNo;
		}
		public LocalDate getBuyersOrderDate() {
			return buyersOrderDate;
		}
		public void setBuyersOrderDate(LocalDate buyersOrderDate) {
			this.buyersOrderDate = buyersOrderDate;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getCompanyPan() {
			return companyPan;
		}
		public void setCompanyPan(String companyPan) {
			this.companyPan = companyPan;
		}
		public String getPaymentTerms() {
			return paymentTerms;
		}
		public void setPaymentTerms(String paymentTerms) {
			this.paymentTerms = paymentTerms;
		}
		public String getReferenceNoDate() {
			return referenceNoDate;
		}
		public void setReferenceNoDate(String referenceNoDate) {
			this.referenceNoDate = referenceNoDate;
		}
		public String getPurchaseOrderId() {
			return purchaseOrderId;
		}
		public void setPurchaseOrderId(String purchaseOrderId) {
			this.purchaseOrderId = purchaseOrderId;
		}
		public String getQuotationId() {
			return quotationId;
		}
		public void setQuotationId(String quotationId) {
			this.quotationId = quotationId;
		}
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
		public String getCreatedByEmpId() {
			return createdByEmpId;
		}
		public void setCreatedByEmpId(String createdByEmpId) {
			this.createdByEmpId = createdByEmpId;
		}
		public String getBillingCompanyName() {
			return billingCompanyName;
		}
		public void setBillingCompanyName(String billingCompanyName) {
			this.billingCompanyName = billingCompanyName;
		}
		public Double getRoundedGrandTotal() {
			return roundedGrandTotal;
		}
		public void setRoundedGrandTotal(Double roundedGrandTotal) {
			this.roundedGrandTotal = roundedGrandTotal;
		}
		public String getDeliveryAddress() {
			return deliveryAddress;
		}
		public void setDeliveryAddress(String deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
		}

	
}
