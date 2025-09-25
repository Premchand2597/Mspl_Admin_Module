package com.example.mspl_connect.DispatchEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dispatch")
public class Dispatch {
		
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
		
	//user details
	private String userName;
	@Column(name = "transport_mode") 
	private String transportMode;
	private String invoiceNo;
	private String date;
	private String dcNo;
	private String eWayBillNo;
	private String quantity;
		
	//customer details
	private String customerName;
	private String companyName;
	private String contactNo;
	private String emailId;
	private String companyAddress;
		
	// Product Details
    private String productName;
    private String rootFirmwareExternal;
    private String routerFirmwareExternal;
    private String moduleNo;
    private String rootFirmwareInternal;
    private String routerFirmwareInternal;
    private String antennaType;
    private String firmwareHardwareVersion;
     
    @Column(name = "upload_file")
    private String uploadFile;
    	
    // Configuration Details
    private String appSKey;
    private String nwkSKey;
    private String appEUI;
    private String deviceAddress;
    private String remarks;
    
    private String returnedQty;
    private String returnedDate;
    
    private String price;
    private String total_price;
    private String description;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDcNo() {
		return dcNo;
	}
	public void setDcNo(String dcNo) {
		this.dcNo = dcNo;
	}
	public String geteWayBillNo() {
		return eWayBillNo;
	}
	public void seteWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRootFirmwareExternal() {
		return rootFirmwareExternal;
	}
	public void setRootFirmwareExternal(String rootFirmwareExternal) {
		this.rootFirmwareExternal = rootFirmwareExternal;
	}
	public String getRouterFirmwareExternal() {
		return routerFirmwareExternal;
	}
	public void setRouterFirmwareExternal(String routerFirmwareExternal) {
		this.routerFirmwareExternal = routerFirmwareExternal;
	}
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public String getRootFirmwareInternal() {
		return rootFirmwareInternal;
	}
	public void setRootFirmwareInternal(String rootFirmwareInternal) {
		this.rootFirmwareInternal = rootFirmwareInternal;
	}
	public String getRouterFirmwareInternal() {
		return routerFirmwareInternal;
	}
	public void setRouterFirmwareInternal(String routerFirmwareInternal) {
		this.routerFirmwareInternal = routerFirmwareInternal;
	}
	public String getAntennaType() {
		return antennaType;
	}
	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}
	public String getFirmwareHardwareVersion() {
		return firmwareHardwareVersion;
	}
	public void setFirmwareHardwareVersion(String firmwareHardwareVersion) {
		this.firmwareHardwareVersion = firmwareHardwareVersion;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getAppSKey() {
		return appSKey;
	}
	public void setAppSKey(String appSKey) {
		this.appSKey = appSKey;
	}
	public String getNwkSKey() {
		return nwkSKey;
	}
	public void setNwkSKey(String nwkSKey) {
		this.nwkSKey = nwkSKey;
	}
	public String getAppEUI() {
		return appEUI;
	}
	public void setAppEUI(String appEUI) {
		this.appEUI = appEUI;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getReturnedQty() {
		return returnedQty;
	}
	public void setReturnedQty(String returnedQty) {
		this.returnedQty = returnedQty;
	}
	public String getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Dispatch [id=" + id + ", userName=" + userName + ", transportMode=" + transportMode + ", invoiceNo="
				+ invoiceNo + ", date=" + date + ", dcNo=" + dcNo + ", eWayBillNo=" + eWayBillNo + ", quantity="
				+ quantity + ", customerName=" + customerName + ", companyName=" + companyName + ", contactNo="
				+ contactNo + ", emailId=" + emailId + ", companyAddress=" + companyAddress + ", productName="
				+ productName + ", rootFirmwareExternal=" + rootFirmwareExternal + ", routerFirmwareExternal="
				+ routerFirmwareExternal + ", moduleNo=" + moduleNo + ", rootFirmwareInternal=" + rootFirmwareInternal
				+ ", routerFirmwareInternal=" + routerFirmwareInternal + ", antennaType=" + antennaType
				+ ", firmwareHardwareVersion=" + firmwareHardwareVersion + ", uploadFile=" + uploadFile + ", appSKey="
				+ appSKey + ", nwkSKey=" + nwkSKey + ", appEUI=" + appEUI + ", deviceAddress=" + deviceAddress
				+ ", remarks=" + remarks + ", returnedQty=" + returnedQty + ", returnedDate=" + returnedDate
				+ ", price=" + price + ", total_price=" + total_price + ", description=" + description + "]";
	}
    
}