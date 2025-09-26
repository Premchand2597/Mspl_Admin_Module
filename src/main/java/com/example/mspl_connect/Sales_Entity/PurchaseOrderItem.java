package com.example.mspl_connect.Sales_Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_order_items")
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer slNo;
    @Column(name = "module_name")
    private String moduleName;
    private String partNo;
    private String hsnCode;
    private String description;
    private Integer quantity;
    private Double price;
    private Double lineTotal;
    private double cgst;
    private double sgst;
    private double igst;
    private double totalValue;
    
    private String p_id;
    
	 public PurchaseOrderItem() {
		super();
	}

	    public PurchaseOrderItem(Long id, Integer slNo, String moduleName, String partNo, String hsnCode,
			String description, Integer quantity, Double price, Double lineTotal, double cgst, double sgst, double igst,
			double totalValue, PurchaseOrder purchaseOrder) {
			super();
			this.id = id;
			this.slNo = slNo;
			this.moduleName = moduleName;
			this.partNo = partNo;
			this.hsnCode = hsnCode;
			this.description = description;
			this.quantity = quantity;
			this.price = price;
			this.lineTotal = lineTotal;
			this.cgst = cgst;
			this.sgst = sgst;
			this.igst = igst;
			this.totalValue = totalValue;
			this.purchaseOrder = purchaseOrder;
	    }

		@ManyToOne
	    @JoinColumn(name = "purchase_order_id")
	    @JsonBackReference
	    private PurchaseOrder purchaseOrder;
	   
		
		public String getP_id() {
			return p_id;
		}

		public void setP_id(String p_id) {
			this.p_id = p_id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getSlNo() {
			return slNo;
		}

		public void setSlNo(Integer slNo) {
			this.slNo = slNo;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public String getPartNo() {
			return partNo;
		}

		public void setPartNo(String partNo) {
			this.partNo = partNo;
		}

		public String getHsnCode() {
			return hsnCode;
		}

		public void setHsnCode(String hsnCode) {
			this.hsnCode = hsnCode;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Double getLineTotal() {
			return lineTotal;
		}

		public void setLineTotal(Double lineTotal) {
			this.lineTotal = lineTotal;
		}

		public PurchaseOrder getPurchaseOrder() {
			return purchaseOrder;
		}

		public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
			this.purchaseOrder = purchaseOrder;
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

		public double getTotalValue() {
			return totalValue;
		}

		public void setTotalValue(double totalValue) {
			this.totalValue = totalValue;
		}
	
}
