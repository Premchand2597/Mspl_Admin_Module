package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;



public class DeliveryChallanDTO {
	   public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName, Double roundedGrandTotal, String deliveryAddress,
			List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName, Double roundedGrandTotal, List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			String billingCompanyName, List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, LocalDateTime createdDate, String createdByEmpId,
			List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	public DeliveryChallanDTO() {
		super();
	}

	public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			String purchaseOrderId, String quotationId, List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	public DeliveryChallanDTO(String supplierAddress, String buyer, String deliveryNoteNo, LocalDate dated,
			String referenceNo, String otherReferences, String termsOfDelivery, String buyersOrderNo,
			LocalDate buyersOrderDate, String remarks, String companyPan, String paymentTerms, String referenceNoDate,
			List<GoodsDetailDTO> goodsDetails) {
		super();
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
		this.goodsDetails = goodsDetails;
	}

	private String supplierAddress;
	    private String buyer;
	    private String deliveryNoteNo;
	    private LocalDate dated;
	    private String referenceNo;
	    private String otherReferences;
	    private String termsOfDelivery;
	    private String buyersOrderNo;
	    private LocalDate buyersOrderDate;
	    private String remarks;
	    private String companyPan;
        private String paymentTerms;
	    private String referenceNoDate;
	    private String purchaseOrderId;
	    private String quotationId;
	    private LocalDateTime createdDate;
	    private String createdByEmpId;
	    private String billingCompanyName;
	    private Double roundedGrandTotal;  // New Column
	    private String deliveryAddress;
	    
	    private List<GoodsDetailDTO> goodsDetails;

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

		public List<GoodsDetailDTO> getGoodsDetails() {
			return goodsDetails;
		}

		public void setGoodsDetails(List<GoodsDetailDTO> goodsDetails) {
			this.goodsDetails = goodsDetails;
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
