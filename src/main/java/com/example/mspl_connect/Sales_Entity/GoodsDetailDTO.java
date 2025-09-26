package com.example.mspl_connect.Sales_Entity;

public class GoodsDetailDTO {
	 public GoodsDetailDTO() {
		super();
	}
	public GoodsDetailDTO(String description, String hsnSac, int quantity, double rate, String perUnit, double amount,
			String cgstRate, double cgstAmount, String sgstRate, double sgstAmount, String igstRate, double igstAmount,
			double totalTaxAmount, Double totalWithTax) {
		super();
		this.description = description;
		this.hsnSac = hsnSac;
		this.quantity = quantity;
		this.rate = rate;
		this.perUnit = perUnit;
		this.amount = amount;
		this.cgstRate = cgstRate;
		this.cgstAmount = cgstAmount;
		this.sgstRate = sgstRate;
		this.sgstAmount = sgstAmount;
		this.igstRate = igstRate;
		this.igstAmount = igstAmount;
		this.totalTaxAmount = totalTaxAmount;
		this.totalWithTax = totalWithTax;
	}
	private String description;
	    private String hsnSac;
	    private int quantity;
	    private double rate;
	    private String perUnit;
	    private double amount;

	    private String cgstRate;
	    private double cgstAmount;
	    private String sgstRate;
	    private double sgstAmount;
	    private String igstRate;
	    private double igstAmount;
	    private double totalTaxAmount;
	    private Double totalWithTax;

	 // Method to determine the GST type
	    public String getGstType() {
	        if (igstAmount > 0) {
	            return "IGST";
	        } else if (cgstAmount > 0 && sgstAmount > 0) {
	            return "CGST + SGST";
	        }
	        return "";
	    }
	    
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getHsnSac() {
			return hsnSac;
		}
		public void setHsnSac(String hsnSac) {
			this.hsnSac = hsnSac;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getRate() {
			return rate;
		}
		public void setRate(double rate) {
			this.rate = rate;
		}
		public String getPerUnit() {
			return perUnit;
		}
		public void setPerUnit(String perUnit) {
			this.perUnit = perUnit;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getCgstRate() {
			return cgstRate;
		}
		public void setCgstRate(String cgstRate) {
			this.cgstRate = cgstRate;
		}
		public double getCgstAmount() {
			return cgstAmount;
		}
		public void setCgstAmount(double cgstAmount) {
			this.cgstAmount = cgstAmount;
		}
		public String getSgstRate() {
			return sgstRate;
		}
		public void setSgstRate(String sgstRate) {
			this.sgstRate = sgstRate;
		}
		public double getSgstAmount() {
			return sgstAmount;
		}
		public void setSgstAmount(double sgstAmount) {
			this.sgstAmount = sgstAmount;
		}
		public String getIgstRate() {
			return igstRate;
		}
		public void setIgstRate(String igstRate) {
			this.igstRate = igstRate;
		}
		public double getIgstAmount() {
			return igstAmount;
		}
		public void setIgstAmount(double igstAmount) {
			this.igstAmount = igstAmount;
		}
		public double getTotalTaxAmount() {
			return totalTaxAmount;
		}
		public void setTotalTaxAmount(double totalTaxAmount) {
			this.totalTaxAmount = totalTaxAmount;
		}
		public Double getTotalWithTax() {
			return totalWithTax;
		}
		public void setTotalWithTax(Double totalWithTax) {
			this.totalWithTax = totalWithTax;
		}

}
