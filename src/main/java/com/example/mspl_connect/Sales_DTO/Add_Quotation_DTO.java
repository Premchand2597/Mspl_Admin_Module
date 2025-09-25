package com.example.mspl_connect.Sales_DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Add_Quotation_DTO {
	
		public Add_Quotation_DTO(String qid, String date_time, String customerName, String vendorlegalName,
			String gstnumber, String orderMethod, String contactPersonName, String designation, String contactDetails,
			String emailId, String deliveryAddress, String shipmentAddress, String taxType, String honorifics1,
			String honorifics2, String prepared_by, String reviseReason, String empid, List<ProductDTO> products) {
		super();
		this.qid = qid;
		this.date_time = date_time;
		this.customerName = customerName;
		this.vendorlegalName = vendorlegalName;
		this.gstnumber = gstnumber;
		this.orderMethod = orderMethod;
		this.contactPersonName = contactPersonName;
		this.designation = designation;
		this.contactDetails = contactDetails;
		this.emailId = emailId;
		this.deliveryAddress = deliveryAddress;
		this.shipmentAddress = shipmentAddress;
		this.taxType = taxType;
		this.honorifics1 = honorifics1;
		this.honorifics2 = honorifics2;
		this.prepared_by = prepared_by;
		this.reviseReason = reviseReason;
		this.empid = empid;
		this.products = products;
	}

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
	    private String reviseReason;
	    private String empid;
	    
	    private List<ProductDTO> products;
	    
	    public String getReviseReason() {
			return reviseReason;
		}

		public void setReviseReason(String reviseReason) {
			this.reviseReason = reviseReason;
		}

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
	        private double price;
	        private int discount;
	        @JsonProperty("taxableValue")
	        private double total;
	        private double cgst;
	        private double sgst;
	        private double igst;
	        @JsonProperty("total")
	        private String total_with_gst; 
	        private String pId;
	        
	        @JsonProperty("qid") 
	        private String qid;
	        
	        // New field for DB-generated quotation ID
	        private Long quotationId;

	        public String getpId() {
				return pId;
			}
			public void setpId(String pId) {
				this.pId = pId;
			}
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
			public double getPrice() {
				return price;
			}
			public void setPrice(double price) {
				this.price = price;
			}
			public int getDiscount() {
				return discount;
			}
			public void setDiscount(int discount) {
				this.discount = discount;
			}
			public double getTotal() {
				return total;
			}
			public void setTotal(double total) {
				this.total = total;
			}
			public double getCgst() {
				return cgst;
			}
			public void setCgst(double cgst) {
				this.cgst = cgst;
			}
			public double getSgst() {
				return sgst;
			}
			public void setSgst(double sgst) {
				this.sgst = sgst;
			}
			public double getIgst() {
				return igst;
			}
			public void setIgst(double igst) {
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
	        
			 public Long getQuotationId() {
			        return quotationId;
			    }

			    public void setQuotationId(Long quotationId) {
			        this.quotationId = quotationId;
			    }
	        
	    }

		public String getEmpid() {
			return empid;
		}

		public void setEmpid(String empid) {
			this.empid = empid;
		}

}
