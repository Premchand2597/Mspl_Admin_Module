package com.example.mspl_connect.Sales_Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_invoice_item")
public class PurchaseInvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partNo;
    private String hsnCode;
    private String description;
    private Integer quantity;
    private Double price;
    private Double lineTotal;
    private Double cgst;
    private Double sgst;
    private Double igst;
    private Double totalValue;
    
    private String p_id;
    
    private String sale_order_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_invoice_id")
    @JsonBackReference
    private PurchaseInvoice purchaseInvoice;
    
    @Override
    public String toString() {
        return "PurchaseInvoiceItem [id=" + id + ", partNo=" + partNo + ", hsnCode=" + hsnCode + ", description="
                + description + ", quantity=" + quantity + ", price=" + price + ", lineTotal=" + lineTotal + ", cgst="
                + cgst + ", sgst=" + sgst + ", igst=" + igst + ", totalValue=" + totalValue + ", p_id=" + p_id
                + ", sale_order_id=" + sale_order_id + ", purchaseInvoiceId=" + 
                (purchaseInvoice != null ? purchaseInvoice.getId() : "null") + "]";
    }

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getSale_order_id() {
		return sale_order_id;
	}

	public void setSale_order_id(String sale_order_id) {
		this.sale_order_id = sale_order_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public PurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
	}

	public PurchaseInvoiceItem(Long id, String partNo, String hsnCode, String description, Integer quantity,
			Double price, Double lineTotal, Double cgst, Double sgst, Double igst, Double totalValue, String p_id,
			String sale_order_id, PurchaseInvoice purchaseInvoice) {
		super();
		this.id = id;
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
		this.p_id = p_id;
		this.sale_order_id = sale_order_id;
		this.purchaseInvoice = purchaseInvoice;
	}

	public PurchaseInvoiceItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}

