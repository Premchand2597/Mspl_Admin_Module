package com.example.mspl_connect.Sales_DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Add_DC_DTO {
	
		@JsonProperty("qid") 
		private String qid;

		private String date_time;
		
		@JsonProperty("vendorName")
		private String customerName;
		
		private String vendorlegalName;	
		
		private String gstnumber;	
		
		private String orderMethod;	
		
		@JsonProperty("personName")
	    private String contactPersonName;
	    
	    private String designation;
	    private String contactDetails;
	    private String emailId;	   
	    private String deliveryAddress;	    
	    private String shipmentAddress;	    
	    private String taxType;
	    private String honorifics1;
	    private String honorifics2;
	    private String prepared_by;
	    private int review;
	    private String email_sent_status;
	    
	    private List<ProductDTO> products;
	    
	    
	    public String getPrepared_by() {
			return prepared_by;
		}

		public void setPrepared_by(String prepared_by) {
			this.prepared_by = prepared_by;
		}

		public String getQid() {
			return qid;
		}

		public void setQid(String qid) {
			this.qid = qid;
		}

		public String getqdateTime() {
			
			return date_time;
		}

		public void setqdateTime(String date_time) {
			this.date_time=date_time;
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

		public void setContactDetails(String contactDetails) {
			this.contactDetails = contactDetails;
		}




		public String getEmailId() {
			return emailId;
		}




		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}




		public String getDeliveryAddress() {
			return deliveryAddress;
		}




		public void setDeliveryAddress(String deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
		}




		public String getOrderMethod() {
			return orderMethod;
		}




		public void setOrderMethod(String orderMethod) {
			this.orderMethod = orderMethod;
		}




		public String getShipmentAddress() {
			return shipmentAddress;
		}




		public void setShipmentAddress(String shipmentAddress) {
			this.shipmentAddress = shipmentAddress;
		}




		public List<ProductDTO> getProducts() {
			return products;
		}




		public void setProducts(List<ProductDTO> products) {
			this.products = products;
		}




		public String getDate_time() {
			return date_time;
		}

		public void setDate_time(String date_time) {
			this.date_time = date_time;
		}

		public String getVendorlegalName() {
			return vendorlegalName;
		}

		public void setVendorlegalName(String vendorlegalName) {
			this.vendorlegalName = vendorlegalName;
		}

		public String getGstnumber() {
			return gstnumber;
		}

		public void setGstnumber(String gstnumber) {
			this.gstnumber = gstnumber;
		}

		public String getTaxType() {
			return taxType;
		}

		public void setTaxType(String taxType) {
			this.taxType = taxType;
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

		public static class ProductDTO {
	    	
	    	private int slNo;
	        private String productName;
	        private String hsnCode;
	        private String partNumber; 
	        @JsonProperty("description")
	        private String productDescription; 
	        private int quantity;
	        private String price;
	        private int discount;
	        @JsonProperty("taxableValue")
	        private String total;
	        private String cgst;
	        private String sgst;
	        private String igst;
	        @JsonProperty("total")
	        private String total_with_gst; 
	        
	        @JsonProperty("qid") 
	        private String qid;
	        
	        public String getQid() {
				return qid;
			}
			public void setQid(String qid) {
				this.qid = qid;
			}
			
	        
			public int getSlNo() {
				return slNo;
			}
			public void setSlNo(int slNo) {
				this.slNo = slNo;
			}
			public String getProductName() {
				return productName;
			}
			public void setProductName(String productName) {
				this.productName = productName;
			}
			public int getQuantity() {
				return quantity;
			}
			public void setQuantity(int quantity) {
				this.quantity = quantity;
			}
			public int getDiscount() {
				return discount;
			}
			public void setDiscount(int discount) {
				this.discount = discount;
			}
			
			public String getPrice() {
				return price;
			}
			public void setPrice(String price) {
				this.price = price;
			}
			public String getTotal() {
				return total;
			}
			public void setTotal(String total) {
				this.total = total;
			}
			public String getCgst() {
				return cgst;
			}
			public void setCgst(String cgst) {
				this.cgst = cgst;
			}
			public String getSgst() {
				return sgst;
			}
			public void setSgst(String sgst) {
				this.sgst = sgst;
			}
			public String getIgst() {
				return igst;
			}
			public void setIgst(String igst) {
				this.igst = igst;
			}
			public String getHsnCode() {
				return hsnCode;
			}
			public void setHsnCode(String hsnCode) {
				this.hsnCode = hsnCode;
			}
			public String getPartNumber() {
				return partNumber;
			}
			public void setPartNumber(String partNumber) {
				this.partNumber = partNumber;
			}
			public String getProductDescription() {
				return productDescription;
			}
			public void setProductDescription(String productDescription) {
				this.productDescription = productDescription;
			}
			public String getTotal_with_gst() {
				return total_with_gst;
			}
			public void setTotal_with_gst(String total_with_gst) {
				this.total_with_gst = total_with_gst;
			}	        
	        
	    }

}
