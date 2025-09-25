package com.example.mspl_connect.Sales_Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name ="delivery_challan")
public class Add_DC_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name = "qid")
	private String qid;

	@Column(name = "date_time")
	private String date_time;

	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_legal_name")
	private String customerlName;

	@Column(name = "gst_number")
	private String gstNumber;
	
	@Column(name = "order_method")
	private String orderMethod;

	@Column(name = "contact_person_name")
	private String contactPersonName;

	@Column(name = "designation")
	private String designation;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;

	@Column(name = "shipment_address")
	private String shipmentAddress;	
	
	@Column(name = "tax_type")
	private String taxType;
	
	private int review;
	private String email_sent_status;
	private String honorifics1;
	private String honorifics2;
	private String prepared_by;
	
	  public Add_DC_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	  
	public Add_DC_Entity(String qid, String date_time, String customerName, String customerlName,
			String gstNumber, String orderMethod, String contactPersonName, String designation, String mobileNumber,
			String emailId, String deliveryAddress, String shipmentAddress, String taxType,
			List<ProductDetails> products) {
		super();
		this.qid = qid;
		this.date_time = date_time;
		this.customerName = customerName;
		this.customerlName = customerlName;
		this.gstNumber = gstNumber;
		this.orderMethod = orderMethod;
		this.contactPersonName = contactPersonName;
		this.designation = designation;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.deliveryAddress = deliveryAddress;
		this.shipmentAddress = shipmentAddress;
		this.taxType = taxType;
		this.products = products;
	}


	@Override
	public String toString() {
		return "Add_Quotation_Entity [qid=" + qid + ", date_time=" + date_time + ", customerName=" + customerName
				+ ", customerlName=" + customerlName + ", gstNumber=" + gstNumber + ", orderMethod=" + orderMethod
				+ ", contactPersonName=" + contactPersonName + ", designation=" + designation + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", deliveryAddress=" + deliveryAddress + ", shipmentAddress="
				+ shipmentAddress + ", taxType=" + taxType + ", products=" + products + "]";
	}


	public String getCustomerlName() {
		return customerlName;
	}

	public void setCustomerlName(String customerlName) {
		this.customerlName = customerlName;
	}



	public String getGstNumber() {
		return gstNumber;
	}



	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getTaxType() {
		return taxType;
	}



	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}


	public String getQid() {
		return qid;
	}


	public void setQid(String qid) {
		this.qid = qid;
	}


	public String getDate_time() {
		return date_time;
	}


	public void setDate_time(String date_time) {
		this.date_time = date_time;
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


	public String getmobileNumber() {
		return mobileNumber;
	}


	public void setmobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	
	public List<ProductDetails> getProducts() {
		return products;
	}


	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}


	public int getReview() {
		return review;
	}


	public String getEmail_sent_status() {
		return email_sent_status;
	}

	public void setEmail_sent_status(String email_sent_status) {
		this.email_sent_status = email_sent_status;
	}

	public void setReview(int review) {
		this.review = review;
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


	@ElementCollection	  
	@CollectionTable(name = "delivery_challan_products", joinColumns = @JoinColumn(name = "qid")) 
	private List<ProductDetails> products = new ArrayList<>();
	  
	  
	  @Embeddable 
	  public static class ProductDetails { 
		  @Column(name = "id")
		  private int slNo;
		  
		  @Column(name = "product_name")
		  private String productName; 
		  
		  @Column(name = "hsn_code")
		  private String hsnCode;
		  
		  @Column(name = "part_number")
		  private String partNumber;
		  
		  @Column(name = "description")
		  private String description;
		  
		 
		  @Column(name = "quantity")
		  private int quantity; 
		  
		  @Column(name = "price")
		  private double price; 
		  
		  @Column(name = "discount")
		  private int discount;
		  
		  @Column(name = "total_price")
		  private double total;
		  
		  @Column(name = "cgst")
		  private double cgst;
		  
		  @Column(name = "sgst")
		  private double sgst;
		  
		  @Column(name = "igst")
		  private double igst;
		  
		  @Column(name = "total_with_tax")
		  private String totalwithTax;
		  
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getTotalwithTax() {
			return totalwithTax;
		}
		public void setTotalwithTax(String totalwithTax) {
			this.totalwithTax = totalwithTax;
		}
		
		
	 

}
	  
}
