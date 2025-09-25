package com.example.mspl_connect.Sales_DTO;

public class ProductDTO {
	
	private String productName;
	private String hsnCode;
	private String partNumber;
	private String description;
    private int quantity;
    private String price;
    private double discount;
    private String total;
    private String cgst;
    private String sgst;
    private String igst;
    private String total_value;
    private String qid;
    
    private String pId;
 
    
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
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
 
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
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
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTotal_value() {
		return total_value;
	}
	public void setTotal_value(String total_value) {
		this.total_value = total_value;
	}
	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", hsnCode=" + hsnCode + ", partNumber=" + partNumber
				+ ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", discount="
				+ discount + ", total=" + total + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst
				+ ", total_value=" + total_value + ", qid=" + qid + ", pId=" + pId + "]";
	}
	

    

}
