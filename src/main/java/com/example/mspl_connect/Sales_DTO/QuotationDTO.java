package com.example.mspl_connect.Sales_DTO;

import java.util.List;

public class QuotationDTO {
	
		private String qid;
	    private String dateTime;
	    private String customerName;
	    private String customerLegalName;
	    private String gstNumber;
	    private String orderMethod;
	    private String contactPersonName;
	    private String designation;
	    private String contactDetails;
	    private String emailId;	   
	    private String deliveryAddress;
	    private String shipmentAddress;
	    private String tax_type;
	    private int review;
	    private String email_sent_status;
	    private String honorifics1;
	    private String honorifics2;
	    private String prepared_by;
	    private String revise_reason;
	    private String terminate_reason;
	    
	    private String empid;
	    
	    private List<ProductDTO> products;
	    
		public String getEmpid() {
			return empid;
		}
		public void setEmpid(String empid) {
			this.empid = empid;
		}
		public String getTerminate_reason() {
			return terminate_reason;
		}
		public void setTerminate_reason(String terminate_reason) {
			this.terminate_reason = terminate_reason;
		}
		
		public String getQid() {
			return qid;
		}
		public void setQid(String qid) {
			this.qid = qid;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getContactPersonName() {
			return contactPersonName;
		}
		public void setContactPersonName(String contactPersonName) {
			this.contactPersonName = contactPersonName;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getContactDetails() {
			return contactDetails;
		}
		public void setContactDetails(String contactDetails) {
			this.contactDetails = contactDetails;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getOrderMethod() {
			return orderMethod;
		}
		public void setOrderMethod(String orderMethod) {
			this.orderMethod = orderMethod;
		}
		public String getDeliveryAddress() {
			return deliveryAddress;
		}
		public void setDeliveryAddress(String deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
		}
		public String getShipmentAddress() {
			return shipmentAddress;
		}
		public void setShipmentAddress(String shipmentAddress) {
			this.shipmentAddress = shipmentAddress;
		}
		
		public String getCustomerLegalName() {
			return customerLegalName;
		}
		public void setCustomerLegalName(String customerLegalName) {
			this.customerLegalName = customerLegalName;
		}
		public String getGstNumber() {
			return gstNumber;
		}
		public void setGstNumber(String gstNumber) {
			this.gstNumber = gstNumber;
		}
		public String getTax_type() {
			return tax_type;
		}
		public void setTax_type(String tax_type) {
			this.tax_type = tax_type;
		}
		public List<ProductDTO> getProducts() {
			return products;
		}
		public void setProducts(List<ProductDTO> products) {
			this.products = products;
		}
		public int getReview() {
			return review;
		}
		public void setReview(int review) {
			this.review = review;
		}
		public String getEmail_sent_status() {
			return email_sent_status;
		}
		public void setEmail_sent_status(String email_sent_status) {
			this.email_sent_status = email_sent_status;
		}
		public String getHonorifics1() {
			return honorifics1;
		}
		public void setHonorifics1(String honorifics1) {
			this.honorifics1 = honorifics1;
		}
		public String getHonorifics2() {
			return honorifics2;
		}
		public void setHonorifics2(String honorifics2) {
			this.honorifics2 = honorifics2;
		}
		public String getPrepared_by() {
			return prepared_by;
		}
		public void setPrepared_by(String prepared_by) {
			this.prepared_by = prepared_by;
		}
		
		public String getReviseReason() {
			return revise_reason;
		}
		public void setReviseReason(String reviseReason) {
			this.revise_reason = reviseReason;
		}
		@Override
		public String toString() {
			return "QuotationDTO [qid=" + qid + ", dateTime=" + dateTime + ", customerName=" + customerName
					+ ", customerLegalName=" + customerLegalName + ", gstNumber=" + gstNumber + ", orderMethod="
					+ orderMethod + ", contactPersonName=" + contactPersonName + ", designation=" + designation
					+ ", contactDetails=" + contactDetails + ", emailId=" + emailId + ", deliveryAddress="
					+ deliveryAddress + ", shipmentAddress=" + shipmentAddress + ", tax_type=" + tax_type + ", review="
					+ review + ", email_sent_status=" + email_sent_status + ", honorifics1=" + honorifics1
					+ ", honorifics2=" + honorifics2 + ", prepared_by=" + prepared_by + ", revise_reason="
					+ revise_reason + ", terminate_reason=" + terminate_reason + ", products=" + products + "]";
		}
	    
}
