package com.example.mspl_connect.DispatchEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dc_dispatch")
public class Dispatch_DC {
		
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
		
	//customer details
	private String customerName;
	private String companyName;
	private String contactNo;
	private String emailId;
	private String companyAddress;
		
	// Product Details
	private String productName1;
	private String rootFirmwareExternal1;
	private String routerFirmwareExternal1;
	private String moduleNo1;
	private String rootFirmwareInternal1;
	private String routerFirmwareInternal1;
	private String antennaType1;
	private String firmwareHardwareVersion1;
	@Column(name = "upload_file1")
	private String uploadFile1;
	private String price1;
	private String total_price1;
	private String description1;
	private String quantity1;

	private String productName2;
	private String rootFirmwareExternal2;
	private String routerFirmwareExternal2;
	private String moduleNo2;
	private String rootFirmwareInternal2;
	private String routerFirmwareInternal2;
	private String antennaType2;
	private String firmwareHardwareVersion2;
	@Column(name = "upload_file2")
	private String uploadFile2;
	private String price2;
	private String total_price2;
	private String description2;
	private String quantity2;

	private String productName3;
	private String rootFirmwareExternal3;
	private String routerFirmwareExternal3;
	private String moduleNo3;
	private String rootFirmwareInternal3;
	private String routerFirmwareInternal3;
	private String antennaType3;
	private String firmwareHardwareVersion3;
	@Column(name = "upload_file3")
	private String uploadFile3;
	private String price3;
	private String total_price3;
	private String description3;
	private String quantity3;

	private String productName4;
	private String rootFirmwareExternal4;
	private String routerFirmwareExternal4;
	private String moduleNo4;
	private String rootFirmwareInternal4;
	private String routerFirmwareInternal4;
	private String antennaType4;
	private String firmwareHardwareVersion4;
	@Column(name = "upload_file4")
	private String uploadFile4;
	private String price4;
	private String total_price4;
	private String description4;
	private String quantity4;

	private String productName5;
	private String rootFirmwareExternal5;
	private String routerFirmwareExternal5;
	private String moduleNo5;
	private String rootFirmwareInternal5;
	private String routerFirmwareInternal5;
	private String antennaType5;
	private String firmwareHardwareVersion5;
	@Column(name = "upload_file5")
	private String uploadFile5;
	private String price5;
	private String total_price5;
	private String description5;
	private String quantity5;

	private String productName6;
	private String rootFirmwareExternal6;
	private String routerFirmwareExternal6;
	private String moduleNo6;
	private String rootFirmwareInternal6;
	private String routerFirmwareInternal6;
	private String antennaType6;
	private String firmwareHardwareVersion6;
	@Column(name = "upload_file6")
	private String uploadFile6;
	private String price6;
	private String total_price6;
	private String description6;
	private String quantity6;

	private String productName7;
	private String rootFirmwareExternal7;
	private String routerFirmwareExternal7;
	private String moduleNo7;
	private String rootFirmwareInternal7;
	private String routerFirmwareInternal7;
	private String antennaType7;
	private String firmwareHardwareVersion7;
	@Column(name = "upload_file7")
	private String uploadFile7;
	private String price7;
	private String total_price7;
	private String description7;
	private String quantity7;

	private String productName8;
	private String rootFirmwareExternal8;
	private String routerFirmwareExternal8;
	private String moduleNo8;
	private String rootFirmwareInternal8;
	private String routerFirmwareInternal8;
	private String antennaType8;
	private String firmwareHardwareVersion8;
	@Column(name = "upload_file8")
	private String uploadFile8;
	private String price8;
	private String total_price8;
	private String description8;
	private String quantity8;

	private String productName9;
	private String rootFirmwareExternal9;
	private String routerFirmwareExternal9;
	private String moduleNo9;
	private String rootFirmwareInternal9;
	private String routerFirmwareInternal9;
	private String antennaType9;
	private String firmwareHardwareVersion9;
	@Column(name = "upload_file9")
	private String uploadFile9;
	private String price9;
	private String total_price9;
	private String description9;
	private String quantity9;

	private String productName10;
	private String rootFirmwareExternal10;
	private String routerFirmwareExternal10;
	private String moduleNo10;
	private String rootFirmwareInternal10;
	private String routerFirmwareInternal10;
	private String antennaType10;
	private String firmwareHardwareVersion10;
	@Column(name = "upload_file10")
	private String uploadFile10;
	private String price10;
	private String total_price10;
	private String description10;
	private String quantity10;

	private String productName11;
	private String rootFirmwareExternal11;
	private String routerFirmwareExternal11;
	private String moduleNo11;
	private String rootFirmwareInternal11;
	private String routerFirmwareInternal11;
	private String antennaType11;
	private String firmwareHardwareVersion11;
	@Column(name = "upload_file11")
	private String uploadFile11;
	private String price11;
	private String total_price11;
	private String description11;
	private String quantity11;

	private String productName12;
	private String rootFirmwareExternal12;
	private String routerFirmwareExternal12;
	private String moduleNo12;
	private String rootFirmwareInternal12;
	private String routerFirmwareInternal12;
	private String antennaType12;
	private String firmwareHardwareVersion12;
	@Column(name = "upload_file12")
	private String uploadFile12;
	private String price12;
	private String total_price12;
	private String description12;
	private String quantity12;

	private String productName13;
	private String rootFirmwareExternal13;
	private String routerFirmwareExternal13;
	private String moduleNo13;
	private String rootFirmwareInternal13;
	private String routerFirmwareInternal13;
	private String antennaType13;
	private String firmwareHardwareVersion13;
	@Column(name = "upload_file13")
	private String uploadFile13;
	private String price13;
	private String total_price13;
	private String description13;
	private String quantity13;

	private String productName14;
	private String rootFirmwareExternal14;
	private String routerFirmwareExternal14;
	private String moduleNo14;
	private String rootFirmwareInternal14;
	private String routerFirmwareInternal14;
	private String antennaType14;
	private String firmwareHardwareVersion14;
	@Column(name = "upload_file14")
	private String uploadFile14;
	private String price14;
	private String total_price14;
	private String description14;
	private String quantity14;

	private String productName15;
	private String rootFirmwareExternal15;
	private String routerFirmwareExternal15;
	private String moduleNo15;
	private String rootFirmwareInternal15;
	private String routerFirmwareInternal15;
	private String antennaType15;
	private String firmwareHardwareVersion15;
	@Column(name = "upload_file15")
	private String uploadFile15;
	private String price15;
	private String total_price15;
	private String description15;
	private String quantity15;

	private String productName16;
	private String rootFirmwareExternal16;
	private String routerFirmwareExternal16;
	private String moduleNo16;
	private String rootFirmwareInternal16;
	private String routerFirmwareInternal16;
	private String antennaType16;
	private String firmwareHardwareVersion16;
	@Column(name = "upload_file16")
	private String uploadFile16;
	private String price16;
	private String total_price16;
	private String description16;
	private String quantity16;

	private String productName17;
	private String rootFirmwareExternal17;
	private String routerFirmwareExternal17;
	private String moduleNo17;
	private String rootFirmwareInternal17;
	private String routerFirmwareInternal17;
	private String antennaType17;
	private String firmwareHardwareVersion17;
	@Column(name = "upload_file17")
	private String uploadFile17;
	private String price17;
	private String total_price17;
	private String description17;
	private String quantity17;

	private String productName18;
	private String rootFirmwareExternal18;
	private String routerFirmwareExternal18;
	private String moduleNo18;
	private String rootFirmwareInternal18;
	private String routerFirmwareInternal18;
	private String antennaType18;
	private String firmwareHardwareVersion18;
	@Column(name = "upload_file18")
	private String uploadFile18;
	private String price18;
	private String total_price18;
	private String description18;
	private String quantity18;

	private String productName19;
	private String rootFirmwareExternal19;
	private String routerFirmwareExternal19;
	private String moduleNo19;
	private String rootFirmwareInternal19;
	private String routerFirmwareInternal19;
	private String antennaType19;
	private String firmwareHardwareVersion19;
	@Column(name = "upload_file19")
	private String uploadFile19;
	private String price19;
	private String total_price19;
	private String description19;
	private String quantity19;

	private String productName20;
	private String rootFirmwareExternal20;
	private String routerFirmwareExternal20;
	private String moduleNo20;
	private String rootFirmwareInternal20;
	private String routerFirmwareInternal20;
	private String antennaType20;
	private String firmwareHardwareVersion20;
	@Column(name = "upload_file20")
	private String uploadFile20;
	private String price20;
	private String total_price20;
	private String description20;
	private String quantity20;

	private String productName21;
	private String rootFirmwareExternal21;
	private String routerFirmwareExternal21;
	private String moduleNo21;
	private String rootFirmwareInternal21;
	private String routerFirmwareInternal21;
	private String antennaType21;
	private String firmwareHardwareVersion21;
	@Column(name = "upload_file21")
	private String uploadFile21;
	private String price21;
	private String total_price21;
	private String description21;
	private String quantity21;

	private String productName22;
	private String rootFirmwareExternal22;
	private String routerFirmwareExternal22;
	private String moduleNo22;
	private String rootFirmwareInternal22;
	private String routerFirmwareInternal22;
	private String antennaType22;
	private String firmwareHardwareVersion22;
	@Column(name = "upload_file22")
	private String uploadFile22;
	private String price22;
	private String total_price22;
	private String description22;
	private String quantity22;

	private String productName23;
	private String rootFirmwareExternal23;
	private String routerFirmwareExternal23;
	private String moduleNo23;
	private String rootFirmwareInternal23;
	private String routerFirmwareInternal23;
	private String antennaType23;
	private String firmwareHardwareVersion23;
	@Column(name = "upload_file23")
	private String uploadFile23;
	private String price23;
	private String total_price23;
	private String description23;
	private String quantity23;

	private String productName24;
	private String rootFirmwareExternal24;
	private String routerFirmwareExternal24;
	private String moduleNo24;
	private String rootFirmwareInternal24;
	private String routerFirmwareInternal24;
	private String antennaType24;
	private String firmwareHardwareVersion24;
	@Column(name = "upload_file24")
	private String uploadFile24;
	private String price24;
	private String total_price24;
	private String description24;
	private String quantity24;

	private String productName25;
	private String rootFirmwareExternal25;
	private String routerFirmwareExternal25;
	private String moduleNo25;
	private String rootFirmwareInternal25;
	private String routerFirmwareInternal25;
	private String antennaType25;
	private String firmwareHardwareVersion25;
	@Column(name = "upload_file25")
	private String uploadFile25;
	private String price25;
	private String total_price25;
	private String description25;
	private String quantity25;
    	
    // Configuration Details
    private String appSKey;
    private String nwkSKey;
    private String appEUI;
    private String deviceAddress;
    private String remarks;
    
    private String returnedQty;
    private String returnedDate;
    
    private String buyer_address;
    private String delivery_address;
    private String payment_terms;
    private String terms_condition;
    
    private String tax_type;
    
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	public String getProductName1() {
		return productName1;
	}
	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}
	public String getRootFirmwareExternal1() {
		return rootFirmwareExternal1;
	}
	public void setRootFirmwareExternal1(String rootFirmwareExternal1) {
		this.rootFirmwareExternal1 = rootFirmwareExternal1;
	}
	public String getRouterFirmwareExternal1() {
		return routerFirmwareExternal1;
	}
	public void setRouterFirmwareExternal1(String routerFirmwareExternal1) {
		this.routerFirmwareExternal1 = routerFirmwareExternal1;
	}
	public String getModuleNo1() {
		return moduleNo1;
	}
	public void setModuleNo1(String moduleNo1) {
		this.moduleNo1 = moduleNo1;
	}
	public String getRootFirmwareInternal1() {
		return rootFirmwareInternal1;
	}
	public void setRootFirmwareInternal1(String rootFirmwareInternal1) {
		this.rootFirmwareInternal1 = rootFirmwareInternal1;
	}
	public String getRouterFirmwareInternal1() {
		return routerFirmwareInternal1;
	}
	public void setRouterFirmwareInternal1(String routerFirmwareInternal1) {
		this.routerFirmwareInternal1 = routerFirmwareInternal1;
	}
	public String getAntennaType1() {
		return antennaType1;
	}
	public void setAntennaType1(String antennaType1) {
		this.antennaType1 = antennaType1;
	}
	public String getFirmwareHardwareVersion1() {
		return firmwareHardwareVersion1;
	}
	public void setFirmwareHardwareVersion1(String firmwareHardwareVersion1) {
		this.firmwareHardwareVersion1 = firmwareHardwareVersion1;
	}
	public String getUploadFile1() {
		return uploadFile1;
	}
	public void setUploadFile1(String uploadFile1) {
		this.uploadFile1 = uploadFile1;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getTotal_price1() {
		return total_price1;
	}
	public void setTotal_price1(String total_price1) {
		this.total_price1 = total_price1;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getQuantity1() {
		return quantity1;
	}
	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}
	public String getProductName2() {
		return productName2;
	}
	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}
	public String getRootFirmwareExternal2() {
		return rootFirmwareExternal2;
	}
	public void setRootFirmwareExternal2(String rootFirmwareExternal2) {
		this.rootFirmwareExternal2 = rootFirmwareExternal2;
	}
	public String getRouterFirmwareExternal2() {
		return routerFirmwareExternal2;
	}
	public void setRouterFirmwareExternal2(String routerFirmwareExternal2) {
		this.routerFirmwareExternal2 = routerFirmwareExternal2;
	}
	public String getModuleNo2() {
		return moduleNo2;
	}
	public void setModuleNo2(String moduleNo2) {
		this.moduleNo2 = moduleNo2;
	}
	public String getRootFirmwareInternal2() {
		return rootFirmwareInternal2;
	}
	public void setRootFirmwareInternal2(String rootFirmwareInternal2) {
		this.rootFirmwareInternal2 = rootFirmwareInternal2;
	}
	public String getRouterFirmwareInternal2() {
		return routerFirmwareInternal2;
	}
	public void setRouterFirmwareInternal2(String routerFirmwareInternal2) {
		this.routerFirmwareInternal2 = routerFirmwareInternal2;
	}
	public String getAntennaType2() {
		return antennaType2;
	}
	public void setAntennaType2(String antennaType2) {
		this.antennaType2 = antennaType2;
	}
	public String getFirmwareHardwareVersion2() {
		return firmwareHardwareVersion2;
	}
	public void setFirmwareHardwareVersion2(String firmwareHardwareVersion2) {
		this.firmwareHardwareVersion2 = firmwareHardwareVersion2;
	}
	public String getUploadFile2() {
		return uploadFile2;
	}
	public void setUploadFile2(String uploadFile2) {
		this.uploadFile2 = uploadFile2;
	}
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	public String getTotal_price2() {
		return total_price2;
	}
	public void setTotal_price2(String total_price2) {
		this.total_price2 = total_price2;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getQuantity2() {
		return quantity2;
	}
	public void setQuantity2(String quantity2) {
		this.quantity2 = quantity2;
	}
	public String getProductName3() {
		return productName3;
	}
	public void setProductName3(String productName3) {
		this.productName3 = productName3;
	}
	public String getRootFirmwareExternal3() {
		return rootFirmwareExternal3;
	}
	public void setRootFirmwareExternal3(String rootFirmwareExternal3) {
		this.rootFirmwareExternal3 = rootFirmwareExternal3;
	}
	public String getRouterFirmwareExternal3() {
		return routerFirmwareExternal3;
	}
	public void setRouterFirmwareExternal3(String routerFirmwareExternal3) {
		this.routerFirmwareExternal3 = routerFirmwareExternal3;
	}
	public String getModuleNo3() {
		return moduleNo3;
	}
	public void setModuleNo3(String moduleNo3) {
		this.moduleNo3 = moduleNo3;
	}
	public String getRootFirmwareInternal3() {
		return rootFirmwareInternal3;
	}
	public void setRootFirmwareInternal3(String rootFirmwareInternal3) {
		this.rootFirmwareInternal3 = rootFirmwareInternal3;
	}
	public String getRouterFirmwareInternal3() {
		return routerFirmwareInternal3;
	}
	public void setRouterFirmwareInternal3(String routerFirmwareInternal3) {
		this.routerFirmwareInternal3 = routerFirmwareInternal3;
	}
	public String getAntennaType3() {
		return antennaType3;
	}
	public void setAntennaType3(String antennaType3) {
		this.antennaType3 = antennaType3;
	}
	public String getFirmwareHardwareVersion3() {
		return firmwareHardwareVersion3;
	}
	public void setFirmwareHardwareVersion3(String firmwareHardwareVersion3) {
		this.firmwareHardwareVersion3 = firmwareHardwareVersion3;
	}
	public String getUploadFile3() {
		return uploadFile3;
	}
	public void setUploadFile3(String uploadFile3) {
		this.uploadFile3 = uploadFile3;
	}
	public String getPrice3() {
		return price3;
	}
	public void setPrice3(String price3) {
		this.price3 = price3;
	}
	public String getTotal_price3() {
		return total_price3;
	}
	public void setTotal_price3(String total_price3) {
		this.total_price3 = total_price3;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	public String getQuantity3() {
		return quantity3;
	}
	public void setQuantity3(String quantity3) {
		this.quantity3 = quantity3;
	}
	public String getProductName4() {
		return productName4;
	}
	public void setProductName4(String productName4) {
		this.productName4 = productName4;
	}
	public String getRootFirmwareExternal4() {
		return rootFirmwareExternal4;
	}
	public void setRootFirmwareExternal4(String rootFirmwareExternal4) {
		this.rootFirmwareExternal4 = rootFirmwareExternal4;
	}
	public String getRouterFirmwareExternal4() {
		return routerFirmwareExternal4;
	}
	public void setRouterFirmwareExternal4(String routerFirmwareExternal4) {
		this.routerFirmwareExternal4 = routerFirmwareExternal4;
	}
	public String getModuleNo4() {
		return moduleNo4;
	}
	public void setModuleNo4(String moduleNo4) {
		this.moduleNo4 = moduleNo4;
	}
	public String getRootFirmwareInternal4() {
		return rootFirmwareInternal4;
	}
	public void setRootFirmwareInternal4(String rootFirmwareInternal4) {
		this.rootFirmwareInternal4 = rootFirmwareInternal4;
	}
	public String getRouterFirmwareInternal4() {
		return routerFirmwareInternal4;
	}
	public void setRouterFirmwareInternal4(String routerFirmwareInternal4) {
		this.routerFirmwareInternal4 = routerFirmwareInternal4;
	}
	public String getAntennaType4() {
		return antennaType4;
	}
	public void setAntennaType4(String antennaType4) {
		this.antennaType4 = antennaType4;
	}
	public String getFirmwareHardwareVersion4() {
		return firmwareHardwareVersion4;
	}
	public void setFirmwareHardwareVersion4(String firmwareHardwareVersion4) {
		this.firmwareHardwareVersion4 = firmwareHardwareVersion4;
	}
	public String getUploadFile4() {
		return uploadFile4;
	}
	public void setUploadFile4(String uploadFile4) {
		this.uploadFile4 = uploadFile4;
	}
	public String getPrice4() {
		return price4;
	}
	public void setPrice4(String price4) {
		this.price4 = price4;
	}
	public String getTotal_price4() {
		return total_price4;
	}
	public void setTotal_price4(String total_price4) {
		this.total_price4 = total_price4;
	}
	public String getDescription4() {
		return description4;
	}
	public void setDescription4(String description4) {
		this.description4 = description4;
	}
	public String getQuantity4() {
		return quantity4;
	}
	public void setQuantity4(String quantity4) {
		this.quantity4 = quantity4;
	}
	public String getProductName5() {
		return productName5;
	}
	public void setProductName5(String productName5) {
		this.productName5 = productName5;
	}
	public String getRootFirmwareExternal5() {
		return rootFirmwareExternal5;
	}
	public void setRootFirmwareExternal5(String rootFirmwareExternal5) {
		this.rootFirmwareExternal5 = rootFirmwareExternal5;
	}
	public String getRouterFirmwareExternal5() {
		return routerFirmwareExternal5;
	}
	public void setRouterFirmwareExternal5(String routerFirmwareExternal5) {
		this.routerFirmwareExternal5 = routerFirmwareExternal5;
	}
	public String getModuleNo5() {
		return moduleNo5;
	}
	public void setModuleNo5(String moduleNo5) {
		this.moduleNo5 = moduleNo5;
	}
	public String getRootFirmwareInternal5() {
		return rootFirmwareInternal5;
	}
	public void setRootFirmwareInternal5(String rootFirmwareInternal5) {
		this.rootFirmwareInternal5 = rootFirmwareInternal5;
	}
	public String getRouterFirmwareInternal5() {
		return routerFirmwareInternal5;
	}
	public void setRouterFirmwareInternal5(String routerFirmwareInternal5) {
		this.routerFirmwareInternal5 = routerFirmwareInternal5;
	}
	public String getAntennaType5() {
		return antennaType5;
	}
	public void setAntennaType5(String antennaType5) {
		this.antennaType5 = antennaType5;
	}
	public String getFirmwareHardwareVersion5() {
		return firmwareHardwareVersion5;
	}
	public void setFirmwareHardwareVersion5(String firmwareHardwareVersion5) {
		this.firmwareHardwareVersion5 = firmwareHardwareVersion5;
	}
	public String getUploadFile5() {
		return uploadFile5;
	}
	public void setUploadFile5(String uploadFile5) {
		this.uploadFile5 = uploadFile5;
	}
	public String getPrice5() {
		return price5;
	}
	public void setPrice5(String price5) {
		this.price5 = price5;
	}
	public String getTotal_price5() {
		return total_price5;
	}
	public void setTotal_price5(String total_price5) {
		this.total_price5 = total_price5;
	}
	public String getDescription5() {
		return description5;
	}
	public void setDescription5(String description5) {
		this.description5 = description5;
	}
	public String getQuantity5() {
		return quantity5;
	}
	public void setQuantity5(String quantity5) {
		this.quantity5 = quantity5;
	}
	public String getProductName6() {
		return productName6;
	}
	public void setProductName6(String productName6) {
		this.productName6 = productName6;
	}
	public String getRootFirmwareExternal6() {
		return rootFirmwareExternal6;
	}
	public void setRootFirmwareExternal6(String rootFirmwareExternal6) {
		this.rootFirmwareExternal6 = rootFirmwareExternal6;
	}
	public String getRouterFirmwareExternal6() {
		return routerFirmwareExternal6;
	}
	public void setRouterFirmwareExternal6(String routerFirmwareExternal6) {
		this.routerFirmwareExternal6 = routerFirmwareExternal6;
	}
	public String getModuleNo6() {
		return moduleNo6;
	}
	public void setModuleNo6(String moduleNo6) {
		this.moduleNo6 = moduleNo6;
	}
	public String getRootFirmwareInternal6() {
		return rootFirmwareInternal6;
	}
	public void setRootFirmwareInternal6(String rootFirmwareInternal6) {
		this.rootFirmwareInternal6 = rootFirmwareInternal6;
	}
	public String getRouterFirmwareInternal6() {
		return routerFirmwareInternal6;
	}
	public void setRouterFirmwareInternal6(String routerFirmwareInternal6) {
		this.routerFirmwareInternal6 = routerFirmwareInternal6;
	}
	public String getAntennaType6() {
		return antennaType6;
	}
	public void setAntennaType6(String antennaType6) {
		this.antennaType6 = antennaType6;
	}
	public String getFirmwareHardwareVersion6() {
		return firmwareHardwareVersion6;
	}
	public void setFirmwareHardwareVersion6(String firmwareHardwareVersion6) {
		this.firmwareHardwareVersion6 = firmwareHardwareVersion6;
	}
	public String getUploadFile6() {
		return uploadFile6;
	}
	public void setUploadFile6(String uploadFile6) {
		this.uploadFile6 = uploadFile6;
	}
	public String getPrice6() {
		return price6;
	}
	public void setPrice6(String price6) {
		this.price6 = price6;
	}
	public String getTotal_price6() {
		return total_price6;
	}
	public void setTotal_price6(String total_price6) {
		this.total_price6 = total_price6;
	}
	public String getDescription6() {
		return description6;
	}
	public void setDescription6(String description6) {
		this.description6 = description6;
	}
	public String getQuantity6() {
		return quantity6;
	}
	public void setQuantity6(String quantity6) {
		this.quantity6 = quantity6;
	}
	public String getProductName7() {
		return productName7;
	}
	public void setProductName7(String productName7) {
		this.productName7 = productName7;
	}
	public String getRootFirmwareExternal7() {
		return rootFirmwareExternal7;
	}
	public void setRootFirmwareExternal7(String rootFirmwareExternal7) {
		this.rootFirmwareExternal7 = rootFirmwareExternal7;
	}
	public String getRouterFirmwareExternal7() {
		return routerFirmwareExternal7;
	}
	public void setRouterFirmwareExternal7(String routerFirmwareExternal7) {
		this.routerFirmwareExternal7 = routerFirmwareExternal7;
	}
	public String getModuleNo7() {
		return moduleNo7;
	}
	public void setModuleNo7(String moduleNo7) {
		this.moduleNo7 = moduleNo7;
	}
	public String getRootFirmwareInternal7() {
		return rootFirmwareInternal7;
	}
	public void setRootFirmwareInternal7(String rootFirmwareInternal7) {
		this.rootFirmwareInternal7 = rootFirmwareInternal7;
	}
	public String getRouterFirmwareInternal7() {
		return routerFirmwareInternal7;
	}
	public void setRouterFirmwareInternal7(String routerFirmwareInternal7) {
		this.routerFirmwareInternal7 = routerFirmwareInternal7;
	}
	public String getAntennaType7() {
		return antennaType7;
	}
	public void setAntennaType7(String antennaType7) {
		this.antennaType7 = antennaType7;
	}
	public String getFirmwareHardwareVersion7() {
		return firmwareHardwareVersion7;
	}
	public void setFirmwareHardwareVersion7(String firmwareHardwareVersion7) {
		this.firmwareHardwareVersion7 = firmwareHardwareVersion7;
	}
	public String getUploadFile7() {
		return uploadFile7;
	}
	public void setUploadFile7(String uploadFile7) {
		this.uploadFile7 = uploadFile7;
	}
	public String getPrice7() {
		return price7;
	}
	public void setPrice7(String price7) {
		this.price7 = price7;
	}
	public String getTotal_price7() {
		return total_price7;
	}
	public void setTotal_price7(String total_price7) {
		this.total_price7 = total_price7;
	}
	public String getDescription7() {
		return description7;
	}
	public void setDescription7(String description7) {
		this.description7 = description7;
	}
	public String getQuantity7() {
		return quantity7;
	}
	public void setQuantity7(String quantity7) {
		this.quantity7 = quantity7;
	}
	public String getProductName8() {
		return productName8;
	}
	public void setProductName8(String productName8) {
		this.productName8 = productName8;
	}
	public String getRootFirmwareExternal8() {
		return rootFirmwareExternal8;
	}
	public void setRootFirmwareExternal8(String rootFirmwareExternal8) {
		this.rootFirmwareExternal8 = rootFirmwareExternal8;
	}
	public String getRouterFirmwareExternal8() {
		return routerFirmwareExternal8;
	}
	public void setRouterFirmwareExternal8(String routerFirmwareExternal8) {
		this.routerFirmwareExternal8 = routerFirmwareExternal8;
	}
	public String getModuleNo8() {
		return moduleNo8;
	}
	public void setModuleNo8(String moduleNo8) {
		this.moduleNo8 = moduleNo8;
	}
	public String getRootFirmwareInternal8() {
		return rootFirmwareInternal8;
	}
	public void setRootFirmwareInternal8(String rootFirmwareInternal8) {
		this.rootFirmwareInternal8 = rootFirmwareInternal8;
	}
	public String getRouterFirmwareInternal8() {
		return routerFirmwareInternal8;
	}
	public void setRouterFirmwareInternal8(String routerFirmwareInternal8) {
		this.routerFirmwareInternal8 = routerFirmwareInternal8;
	}
	public String getAntennaType8() {
		return antennaType8;
	}
	public void setAntennaType8(String antennaType8) {
		this.antennaType8 = antennaType8;
	}
	public String getFirmwareHardwareVersion8() {
		return firmwareHardwareVersion8;
	}
	public void setFirmwareHardwareVersion8(String firmwareHardwareVersion8) {
		this.firmwareHardwareVersion8 = firmwareHardwareVersion8;
	}
	public String getUploadFile8() {
		return uploadFile8;
	}
	public void setUploadFile8(String uploadFile8) {
		this.uploadFile8 = uploadFile8;
	}
	public String getPrice8() {
		return price8;
	}
	public void setPrice8(String price8) {
		this.price8 = price8;
	}
	public String getTotal_price8() {
		return total_price8;
	}
	public void setTotal_price8(String total_price8) {
		this.total_price8 = total_price8;
	}
	public String getDescription8() {
		return description8;
	}
	public void setDescription8(String description8) {
		this.description8 = description8;
	}
	public String getQuantity8() {
		return quantity8;
	}
	public void setQuantity8(String quantity8) {
		this.quantity8 = quantity8;
	}
	public String getProductName9() {
		return productName9;
	}
	public void setProductName9(String productName9) {
		this.productName9 = productName9;
	}
	public String getRootFirmwareExternal9() {
		return rootFirmwareExternal9;
	}
	public void setRootFirmwareExternal9(String rootFirmwareExternal9) {
		this.rootFirmwareExternal9 = rootFirmwareExternal9;
	}
	public String getRouterFirmwareExternal9() {
		return routerFirmwareExternal9;
	}
	public void setRouterFirmwareExternal9(String routerFirmwareExternal9) {
		this.routerFirmwareExternal9 = routerFirmwareExternal9;
	}
	public String getModuleNo9() {
		return moduleNo9;
	}
	public void setModuleNo9(String moduleNo9) {
		this.moduleNo9 = moduleNo9;
	}
	public String getRootFirmwareInternal9() {
		return rootFirmwareInternal9;
	}
	public void setRootFirmwareInternal9(String rootFirmwareInternal9) {
		this.rootFirmwareInternal9 = rootFirmwareInternal9;
	}
	public String getRouterFirmwareInternal9() {
		return routerFirmwareInternal9;
	}
	public void setRouterFirmwareInternal9(String routerFirmwareInternal9) {
		this.routerFirmwareInternal9 = routerFirmwareInternal9;
	}
	public String getAntennaType9() {
		return antennaType9;
	}
	public void setAntennaType9(String antennaType9) {
		this.antennaType9 = antennaType9;
	}
	public String getFirmwareHardwareVersion9() {
		return firmwareHardwareVersion9;
	}
	public void setFirmwareHardwareVersion9(String firmwareHardwareVersion9) {
		this.firmwareHardwareVersion9 = firmwareHardwareVersion9;
	}
	public String getUploadFile9() {
		return uploadFile9;
	}
	public void setUploadFile9(String uploadFile9) {
		this.uploadFile9 = uploadFile9;
	}
	public String getPrice9() {
		return price9;
	}
	public void setPrice9(String price9) {
		this.price9 = price9;
	}
	public String getTotal_price9() {
		return total_price9;
	}
	public void setTotal_price9(String total_price9) {
		this.total_price9 = total_price9;
	}
	public String getDescription9() {
		return description9;
	}
	public void setDescription9(String description9) {
		this.description9 = description9;
	}
	public String getQuantity9() {
		return quantity9;
	}
	public void setQuantity9(String quantity9) {
		this.quantity9 = quantity9;
	}
	public String getProductName10() {
		return productName10;
	}
	public void setProductName10(String productName10) {
		this.productName10 = productName10;
	}
	public String getRootFirmwareExternal10() {
		return rootFirmwareExternal10;
	}
	public void setRootFirmwareExternal10(String rootFirmwareExternal10) {
		this.rootFirmwareExternal10 = rootFirmwareExternal10;
	}
	public String getRouterFirmwareExternal10() {
		return routerFirmwareExternal10;
	}
	public void setRouterFirmwareExternal10(String routerFirmwareExternal10) {
		this.routerFirmwareExternal10 = routerFirmwareExternal10;
	}
	public String getModuleNo10() {
		return moduleNo10;
	}
	public void setModuleNo10(String moduleNo10) {
		this.moduleNo10 = moduleNo10;
	}
	public String getRootFirmwareInternal10() {
		return rootFirmwareInternal10;
	}
	public void setRootFirmwareInternal10(String rootFirmwareInternal10) {
		this.rootFirmwareInternal10 = rootFirmwareInternal10;
	}
	public String getRouterFirmwareInternal10() {
		return routerFirmwareInternal10;
	}
	public void setRouterFirmwareInternal10(String routerFirmwareInternal10) {
		this.routerFirmwareInternal10 = routerFirmwareInternal10;
	}
	public String getAntennaType10() {
		return antennaType10;
	}
	public void setAntennaType10(String antennaType10) {
		this.antennaType10 = antennaType10;
	}
	public String getFirmwareHardwareVersion10() {
		return firmwareHardwareVersion10;
	}
	public void setFirmwareHardwareVersion10(String firmwareHardwareVersion10) {
		this.firmwareHardwareVersion10 = firmwareHardwareVersion10;
	}
	public String getUploadFile10() {
		return uploadFile10;
	}
	public void setUploadFile10(String uploadFile10) {
		this.uploadFile10 = uploadFile10;
	}
	public String getPrice10() {
		return price10;
	}
	public void setPrice10(String price10) {
		this.price10 = price10;
	}
	public String getTotal_price10() {
		return total_price10;
	}
	public void setTotal_price10(String total_price10) {
		this.total_price10 = total_price10;
	}
	public String getDescription10() {
		return description10;
	}
	public void setDescription10(String description10) {
		this.description10 = description10;
	}
	public String getQuantity10() {
		return quantity10;
	}
	public void setQuantity10(String quantity10) {
		this.quantity10 = quantity10;
	}
	public String getProductName11() {
		return productName11;
	}
	public void setProductName11(String productName11) {
		this.productName11 = productName11;
	}
	public String getRootFirmwareExternal11() {
		return rootFirmwareExternal11;
	}
	public void setRootFirmwareExternal11(String rootFirmwareExternal11) {
		this.rootFirmwareExternal11 = rootFirmwareExternal11;
	}
	public String getRouterFirmwareExternal11() {
		return routerFirmwareExternal11;
	}
	public void setRouterFirmwareExternal11(String routerFirmwareExternal11) {
		this.routerFirmwareExternal11 = routerFirmwareExternal11;
	}
	public String getModuleNo11() {
		return moduleNo11;
	}
	public void setModuleNo11(String moduleNo11) {
		this.moduleNo11 = moduleNo11;
	}
	public String getRootFirmwareInternal11() {
		return rootFirmwareInternal11;
	}
	public void setRootFirmwareInternal11(String rootFirmwareInternal11) {
		this.rootFirmwareInternal11 = rootFirmwareInternal11;
	}
	public String getRouterFirmwareInternal11() {
		return routerFirmwareInternal11;
	}
	public void setRouterFirmwareInternal11(String routerFirmwareInternal11) {
		this.routerFirmwareInternal11 = routerFirmwareInternal11;
	}
	public String getAntennaType11() {
		return antennaType11;
	}
	public void setAntennaType11(String antennaType11) {
		this.antennaType11 = antennaType11;
	}
	public String getFirmwareHardwareVersion11() {
		return firmwareHardwareVersion11;
	}
	public void setFirmwareHardwareVersion11(String firmwareHardwareVersion11) {
		this.firmwareHardwareVersion11 = firmwareHardwareVersion11;
	}
	public String getUploadFile11() {
		return uploadFile11;
	}
	public void setUploadFile11(String uploadFile11) {
		this.uploadFile11 = uploadFile11;
	}
	public String getPrice11() {
		return price11;
	}
	public void setPrice11(String price11) {
		this.price11 = price11;
	}
	public String getTotal_price11() {
		return total_price11;
	}
	public void setTotal_price11(String total_price11) {
		this.total_price11 = total_price11;
	}
	public String getDescription11() {
		return description11;
	}
	public void setDescription11(String description11) {
		this.description11 = description11;
	}
	public String getQuantity11() {
		return quantity11;
	}
	public void setQuantity11(String quantity11) {
		this.quantity11 = quantity11;
	}
	public String getProductName12() {
		return productName12;
	}
	public void setProductName12(String productName12) {
		this.productName12 = productName12;
	}
	public String getRootFirmwareExternal12() {
		return rootFirmwareExternal12;
	}
	public void setRootFirmwareExternal12(String rootFirmwareExternal12) {
		this.rootFirmwareExternal12 = rootFirmwareExternal12;
	}
	public String getRouterFirmwareExternal12() {
		return routerFirmwareExternal12;
	}
	public void setRouterFirmwareExternal12(String routerFirmwareExternal12) {
		this.routerFirmwareExternal12 = routerFirmwareExternal12;
	}
	public String getModuleNo12() {
		return moduleNo12;
	}
	public void setModuleNo12(String moduleNo12) {
		this.moduleNo12 = moduleNo12;
	}
	public String getRootFirmwareInternal12() {
		return rootFirmwareInternal12;
	}
	public void setRootFirmwareInternal12(String rootFirmwareInternal12) {
		this.rootFirmwareInternal12 = rootFirmwareInternal12;
	}
	public String getRouterFirmwareInternal12() {
		return routerFirmwareInternal12;
	}
	public void setRouterFirmwareInternal12(String routerFirmwareInternal12) {
		this.routerFirmwareInternal12 = routerFirmwareInternal12;
	}
	public String getAntennaType12() {
		return antennaType12;
	}
	public void setAntennaType12(String antennaType12) {
		this.antennaType12 = antennaType12;
	}
	public String getFirmwareHardwareVersion12() {
		return firmwareHardwareVersion12;
	}
	public void setFirmwareHardwareVersion12(String firmwareHardwareVersion12) {
		this.firmwareHardwareVersion12 = firmwareHardwareVersion12;
	}
	public String getUploadFile12() {
		return uploadFile12;
	}
	public void setUploadFile12(String uploadFile12) {
		this.uploadFile12 = uploadFile12;
	}
	public String getPrice12() {
		return price12;
	}
	public void setPrice12(String price12) {
		this.price12 = price12;
	}
	public String getTotal_price12() {
		return total_price12;
	}
	public void setTotal_price12(String total_price12) {
		this.total_price12 = total_price12;
	}
	public String getDescription12() {
		return description12;
	}
	public void setDescription12(String description12) {
		this.description12 = description12;
	}
	public String getQuantity12() {
		return quantity12;
	}
	public void setQuantity12(String quantity12) {
		this.quantity12 = quantity12;
	}
	public String getProductName13() {
		return productName13;
	}
	public void setProductName13(String productName13) {
		this.productName13 = productName13;
	}
	public String getRootFirmwareExternal13() {
		return rootFirmwareExternal13;
	}
	public void setRootFirmwareExternal13(String rootFirmwareExternal13) {
		this.rootFirmwareExternal13 = rootFirmwareExternal13;
	}
	public String getRouterFirmwareExternal13() {
		return routerFirmwareExternal13;
	}
	public void setRouterFirmwareExternal13(String routerFirmwareExternal13) {
		this.routerFirmwareExternal13 = routerFirmwareExternal13;
	}
	public String getModuleNo13() {
		return moduleNo13;
	}
	public void setModuleNo13(String moduleNo13) {
		this.moduleNo13 = moduleNo13;
	}
	public String getRootFirmwareInternal13() {
		return rootFirmwareInternal13;
	}
	public void setRootFirmwareInternal13(String rootFirmwareInternal13) {
		this.rootFirmwareInternal13 = rootFirmwareInternal13;
	}
	public String getRouterFirmwareInternal13() {
		return routerFirmwareInternal13;
	}
	public void setRouterFirmwareInternal13(String routerFirmwareInternal13) {
		this.routerFirmwareInternal13 = routerFirmwareInternal13;
	}
	public String getAntennaType13() {
		return antennaType13;
	}
	public void setAntennaType13(String antennaType13) {
		this.antennaType13 = antennaType13;
	}
	public String getFirmwareHardwareVersion13() {
		return firmwareHardwareVersion13;
	}
	public void setFirmwareHardwareVersion13(String firmwareHardwareVersion13) {
		this.firmwareHardwareVersion13 = firmwareHardwareVersion13;
	}
	public String getUploadFile13() {
		return uploadFile13;
	}
	public void setUploadFile13(String uploadFile13) {
		this.uploadFile13 = uploadFile13;
	}
	public String getPrice13() {
		return price13;
	}
	public void setPrice13(String price13) {
		this.price13 = price13;
	}
	public String getTotal_price13() {
		return total_price13;
	}
	public void setTotal_price13(String total_price13) {
		this.total_price13 = total_price13;
	}
	public String getDescription13() {
		return description13;
	}
	public void setDescription13(String description13) {
		this.description13 = description13;
	}
	public String getQuantity13() {
		return quantity13;
	}
	public void setQuantity13(String quantity13) {
		this.quantity13 = quantity13;
	}
	public String getProductName14() {
		return productName14;
	}
	public void setProductName14(String productName14) {
		this.productName14 = productName14;
	}
	public String getRootFirmwareExternal14() {
		return rootFirmwareExternal14;
	}
	public void setRootFirmwareExternal14(String rootFirmwareExternal14) {
		this.rootFirmwareExternal14 = rootFirmwareExternal14;
	}
	public String getRouterFirmwareExternal14() {
		return routerFirmwareExternal14;
	}
	public void setRouterFirmwareExternal14(String routerFirmwareExternal14) {
		this.routerFirmwareExternal14 = routerFirmwareExternal14;
	}
	public String getModuleNo14() {
		return moduleNo14;
	}
	public void setModuleNo14(String moduleNo14) {
		this.moduleNo14 = moduleNo14;
	}
	public String getRootFirmwareInternal14() {
		return rootFirmwareInternal14;
	}
	public void setRootFirmwareInternal14(String rootFirmwareInternal14) {
		this.rootFirmwareInternal14 = rootFirmwareInternal14;
	}
	public String getRouterFirmwareInternal14() {
		return routerFirmwareInternal14;
	}
	public void setRouterFirmwareInternal14(String routerFirmwareInternal14) {
		this.routerFirmwareInternal14 = routerFirmwareInternal14;
	}
	public String getAntennaType14() {
		return antennaType14;
	}
	public void setAntennaType14(String antennaType14) {
		this.antennaType14 = antennaType14;
	}
	public String getFirmwareHardwareVersion14() {
		return firmwareHardwareVersion14;
	}
	public void setFirmwareHardwareVersion14(String firmwareHardwareVersion14) {
		this.firmwareHardwareVersion14 = firmwareHardwareVersion14;
	}
	public String getUploadFile14() {
		return uploadFile14;
	}
	public void setUploadFile14(String uploadFile14) {
		this.uploadFile14 = uploadFile14;
	}
	public String getPrice14() {
		return price14;
	}
	public void setPrice14(String price14) {
		this.price14 = price14;
	}
	public String getTotal_price14() {
		return total_price14;
	}
	public void setTotal_price14(String total_price14) {
		this.total_price14 = total_price14;
	}
	public String getDescription14() {
		return description14;
	}
	public void setDescription14(String description14) {
		this.description14 = description14;
	}
	public String getQuantity14() {
		return quantity14;
	}
	public void setQuantity14(String quantity14) {
		this.quantity14 = quantity14;
	}
	public String getProductName15() {
		return productName15;
	}
	public void setProductName15(String productName15) {
		this.productName15 = productName15;
	}
	public String getRootFirmwareExternal15() {
		return rootFirmwareExternal15;
	}
	public void setRootFirmwareExternal15(String rootFirmwareExternal15) {
		this.rootFirmwareExternal15 = rootFirmwareExternal15;
	}
	public String getRouterFirmwareExternal15() {
		return routerFirmwareExternal15;
	}
	public void setRouterFirmwareExternal15(String routerFirmwareExternal15) {
		this.routerFirmwareExternal15 = routerFirmwareExternal15;
	}
	public String getModuleNo15() {
		return moduleNo15;
	}
	public void setModuleNo15(String moduleNo15) {
		this.moduleNo15 = moduleNo15;
	}
	public String getRootFirmwareInternal15() {
		return rootFirmwareInternal15;
	}
	public void setRootFirmwareInternal15(String rootFirmwareInternal15) {
		this.rootFirmwareInternal15 = rootFirmwareInternal15;
	}
	public String getRouterFirmwareInternal15() {
		return routerFirmwareInternal15;
	}
	public void setRouterFirmwareInternal15(String routerFirmwareInternal15) {
		this.routerFirmwareInternal15 = routerFirmwareInternal15;
	}
	public String getAntennaType15() {
		return antennaType15;
	}
	public void setAntennaType15(String antennaType15) {
		this.antennaType15 = antennaType15;
	}
	public String getFirmwareHardwareVersion15() {
		return firmwareHardwareVersion15;
	}
	public void setFirmwareHardwareVersion15(String firmwareHardwareVersion15) {
		this.firmwareHardwareVersion15 = firmwareHardwareVersion15;
	}
	public String getUploadFile15() {
		return uploadFile15;
	}
	public void setUploadFile15(String uploadFile15) {
		this.uploadFile15 = uploadFile15;
	}
	public String getPrice15() {
		return price15;
	}
	public void setPrice15(String price15) {
		this.price15 = price15;
	}
	public String getTotal_price15() {
		return total_price15;
	}
	public void setTotal_price15(String total_price15) {
		this.total_price15 = total_price15;
	}
	public String getDescription15() {
		return description15;
	}
	public void setDescription15(String description15) {
		this.description15 = description15;
	}
	public String getQuantity15() {
		return quantity15;
	}
	public void setQuantity15(String quantity15) {
		this.quantity15 = quantity15;
	}
	public String getProductName16() {
		return productName16;
	}
	public void setProductName16(String productName16) {
		this.productName16 = productName16;
	}
	public String getRootFirmwareExternal16() {
		return rootFirmwareExternal16;
	}
	public void setRootFirmwareExternal16(String rootFirmwareExternal16) {
		this.rootFirmwareExternal16 = rootFirmwareExternal16;
	}
	public String getRouterFirmwareExternal16() {
		return routerFirmwareExternal16;
	}
	public void setRouterFirmwareExternal16(String routerFirmwareExternal16) {
		this.routerFirmwareExternal16 = routerFirmwareExternal16;
	}
	public String getModuleNo16() {
		return moduleNo16;
	}
	public void setModuleNo16(String moduleNo16) {
		this.moduleNo16 = moduleNo16;
	}
	public String getRootFirmwareInternal16() {
		return rootFirmwareInternal16;
	}
	public void setRootFirmwareInternal16(String rootFirmwareInternal16) {
		this.rootFirmwareInternal16 = rootFirmwareInternal16;
	}
	public String getRouterFirmwareInternal16() {
		return routerFirmwareInternal16;
	}
	public void setRouterFirmwareInternal16(String routerFirmwareInternal16) {
		this.routerFirmwareInternal16 = routerFirmwareInternal16;
	}
	public String getAntennaType16() {
		return antennaType16;
	}
	public void setAntennaType16(String antennaType16) {
		this.antennaType16 = antennaType16;
	}
	public String getFirmwareHardwareVersion16() {
		return firmwareHardwareVersion16;
	}
	public void setFirmwareHardwareVersion16(String firmwareHardwareVersion16) {
		this.firmwareHardwareVersion16 = firmwareHardwareVersion16;
	}
	public String getUploadFile16() {
		return uploadFile16;
	}
	public void setUploadFile16(String uploadFile16) {
		this.uploadFile16 = uploadFile16;
	}
	public String getPrice16() {
		return price16;
	}
	public void setPrice16(String price16) {
		this.price16 = price16;
	}
	public String getTotal_price16() {
		return total_price16;
	}
	public void setTotal_price16(String total_price16) {
		this.total_price16 = total_price16;
	}
	public String getDescription16() {
		return description16;
	}
	public void setDescription16(String description16) {
		this.description16 = description16;
	}
	public String getQuantity16() {
		return quantity16;
	}
	public void setQuantity16(String quantity16) {
		this.quantity16 = quantity16;
	}
	public String getProductName17() {
		return productName17;
	}
	public void setProductName17(String productName17) {
		this.productName17 = productName17;
	}
	public String getRootFirmwareExternal17() {
		return rootFirmwareExternal17;
	}
	public void setRootFirmwareExternal17(String rootFirmwareExternal17) {
		this.rootFirmwareExternal17 = rootFirmwareExternal17;
	}
	public String getRouterFirmwareExternal17() {
		return routerFirmwareExternal17;
	}
	public void setRouterFirmwareExternal17(String routerFirmwareExternal17) {
		this.routerFirmwareExternal17 = routerFirmwareExternal17;
	}
	public String getModuleNo17() {
		return moduleNo17;
	}
	public void setModuleNo17(String moduleNo17) {
		this.moduleNo17 = moduleNo17;
	}
	public String getRootFirmwareInternal17() {
		return rootFirmwareInternal17;
	}
	public void setRootFirmwareInternal17(String rootFirmwareInternal17) {
		this.rootFirmwareInternal17 = rootFirmwareInternal17;
	}
	public String getRouterFirmwareInternal17() {
		return routerFirmwareInternal17;
	}
	public void setRouterFirmwareInternal17(String routerFirmwareInternal17) {
		this.routerFirmwareInternal17 = routerFirmwareInternal17;
	}
	public String getAntennaType17() {
		return antennaType17;
	}
	public void setAntennaType17(String antennaType17) {
		this.antennaType17 = antennaType17;
	}
	public String getFirmwareHardwareVersion17() {
		return firmwareHardwareVersion17;
	}
	public void setFirmwareHardwareVersion17(String firmwareHardwareVersion17) {
		this.firmwareHardwareVersion17 = firmwareHardwareVersion17;
	}
	public String getUploadFile17() {
		return uploadFile17;
	}
	public void setUploadFile17(String uploadFile17) {
		this.uploadFile17 = uploadFile17;
	}
	public String getPrice17() {
		return price17;
	}
	public void setPrice17(String price17) {
		this.price17 = price17;
	}
	public String getTotal_price17() {
		return total_price17;
	}
	public void setTotal_price17(String total_price17) {
		this.total_price17 = total_price17;
	}
	public String getDescription17() {
		return description17;
	}
	public void setDescription17(String description17) {
		this.description17 = description17;
	}
	public String getQuantity17() {
		return quantity17;
	}
	public void setQuantity17(String quantity17) {
		this.quantity17 = quantity17;
	}
	public String getProductName18() {
		return productName18;
	}
	public void setProductName18(String productName18) {
		this.productName18 = productName18;
	}
	public String getRootFirmwareExternal18() {
		return rootFirmwareExternal18;
	}
	public void setRootFirmwareExternal18(String rootFirmwareExternal18) {
		this.rootFirmwareExternal18 = rootFirmwareExternal18;
	}
	public String getRouterFirmwareExternal18() {
		return routerFirmwareExternal18;
	}
	public void setRouterFirmwareExternal18(String routerFirmwareExternal18) {
		this.routerFirmwareExternal18 = routerFirmwareExternal18;
	}
	public String getModuleNo18() {
		return moduleNo18;
	}
	public void setModuleNo18(String moduleNo18) {
		this.moduleNo18 = moduleNo18;
	}
	public String getRootFirmwareInternal18() {
		return rootFirmwareInternal18;
	}
	public void setRootFirmwareInternal18(String rootFirmwareInternal18) {
		this.rootFirmwareInternal18 = rootFirmwareInternal18;
	}
	public String getRouterFirmwareInternal18() {
		return routerFirmwareInternal18;
	}
	public void setRouterFirmwareInternal18(String routerFirmwareInternal18) {
		this.routerFirmwareInternal18 = routerFirmwareInternal18;
	}
	public String getAntennaType18() {
		return antennaType18;
	}
	public void setAntennaType18(String antennaType18) {
		this.antennaType18 = antennaType18;
	}
	public String getFirmwareHardwareVersion18() {
		return firmwareHardwareVersion18;
	}
	public void setFirmwareHardwareVersion18(String firmwareHardwareVersion18) {
		this.firmwareHardwareVersion18 = firmwareHardwareVersion18;
	}
	public String getUploadFile18() {
		return uploadFile18;
	}
	public void setUploadFile18(String uploadFile18) {
		this.uploadFile18 = uploadFile18;
	}
	public String getPrice18() {
		return price18;
	}
	public void setPrice18(String price18) {
		this.price18 = price18;
	}
	public String getTotal_price18() {
		return total_price18;
	}
	public void setTotal_price18(String total_price18) {
		this.total_price18 = total_price18;
	}
	public String getDescription18() {
		return description18;
	}
	public void setDescription18(String description18) {
		this.description18 = description18;
	}
	public String getQuantity18() {
		return quantity18;
	}
	public void setQuantity18(String quantity18) {
		this.quantity18 = quantity18;
	}
	public String getProductName19() {
		return productName19;
	}
	public void setProductName19(String productName19) {
		this.productName19 = productName19;
	}
	public String getRootFirmwareExternal19() {
		return rootFirmwareExternal19;
	}
	public void setRootFirmwareExternal19(String rootFirmwareExternal19) {
		this.rootFirmwareExternal19 = rootFirmwareExternal19;
	}
	public String getRouterFirmwareExternal19() {
		return routerFirmwareExternal19;
	}
	public void setRouterFirmwareExternal19(String routerFirmwareExternal19) {
		this.routerFirmwareExternal19 = routerFirmwareExternal19;
	}
	public String getModuleNo19() {
		return moduleNo19;
	}
	public void setModuleNo19(String moduleNo19) {
		this.moduleNo19 = moduleNo19;
	}
	public String getRootFirmwareInternal19() {
		return rootFirmwareInternal19;
	}
	public void setRootFirmwareInternal19(String rootFirmwareInternal19) {
		this.rootFirmwareInternal19 = rootFirmwareInternal19;
	}
	public String getRouterFirmwareInternal19() {
		return routerFirmwareInternal19;
	}
	public void setRouterFirmwareInternal19(String routerFirmwareInternal19) {
		this.routerFirmwareInternal19 = routerFirmwareInternal19;
	}
	public String getAntennaType19() {
		return antennaType19;
	}
	public void setAntennaType19(String antennaType19) {
		this.antennaType19 = antennaType19;
	}
	public String getFirmwareHardwareVersion19() {
		return firmwareHardwareVersion19;
	}
	public void setFirmwareHardwareVersion19(String firmwareHardwareVersion19) {
		this.firmwareHardwareVersion19 = firmwareHardwareVersion19;
	}
	public String getUploadFile19() {
		return uploadFile19;
	}
	public void setUploadFile19(String uploadFile19) {
		this.uploadFile19 = uploadFile19;
	}
	public String getPrice19() {
		return price19;
	}
	public void setPrice19(String price19) {
		this.price19 = price19;
	}
	public String getTotal_price19() {
		return total_price19;
	}
	public void setTotal_price19(String total_price19) {
		this.total_price19 = total_price19;
	}
	public String getDescription19() {
		return description19;
	}
	public void setDescription19(String description19) {
		this.description19 = description19;
	}
	public String getQuantity19() {
		return quantity19;
	}
	public void setQuantity19(String quantity19) {
		this.quantity19 = quantity19;
	}
	public String getProductName20() {
		return productName20;
	}
	public void setProductName20(String productName20) {
		this.productName20 = productName20;
	}
	public String getRootFirmwareExternal20() {
		return rootFirmwareExternal20;
	}
	public void setRootFirmwareExternal20(String rootFirmwareExternal20) {
		this.rootFirmwareExternal20 = rootFirmwareExternal20;
	}
	public String getRouterFirmwareExternal20() {
		return routerFirmwareExternal20;
	}
	public void setRouterFirmwareExternal20(String routerFirmwareExternal20) {
		this.routerFirmwareExternal20 = routerFirmwareExternal20;
	}
	public String getModuleNo20() {
		return moduleNo20;
	}
	public void setModuleNo20(String moduleNo20) {
		this.moduleNo20 = moduleNo20;
	}
	public String getRootFirmwareInternal20() {
		return rootFirmwareInternal20;
	}
	public void setRootFirmwareInternal20(String rootFirmwareInternal20) {
		this.rootFirmwareInternal20 = rootFirmwareInternal20;
	}
	public String getRouterFirmwareInternal20() {
		return routerFirmwareInternal20;
	}
	public void setRouterFirmwareInternal20(String routerFirmwareInternal20) {
		this.routerFirmwareInternal20 = routerFirmwareInternal20;
	}
	public String getAntennaType20() {
		return antennaType20;
	}
	public void setAntennaType20(String antennaType20) {
		this.antennaType20 = antennaType20;
	}
	public String getFirmwareHardwareVersion20() {
		return firmwareHardwareVersion20;
	}
	public void setFirmwareHardwareVersion20(String firmwareHardwareVersion20) {
		this.firmwareHardwareVersion20 = firmwareHardwareVersion20;
	}
	public String getUploadFile20() {
		return uploadFile20;
	}
	public void setUploadFile20(String uploadFile20) {
		this.uploadFile20 = uploadFile20;
	}
	public String getPrice20() {
		return price20;
	}
	public void setPrice20(String price20) {
		this.price20 = price20;
	}
	public String getTotal_price20() {
		return total_price20;
	}
	public void setTotal_price20(String total_price20) {
		this.total_price20 = total_price20;
	}
	public String getDescription20() {
		return description20;
	}
	public void setDescription20(String description20) {
		this.description20 = description20;
	}
	public String getQuantity20() {
		return quantity20;
	}
	public void setQuantity20(String quantity20) {
		this.quantity20 = quantity20;
	}
	public String getProductName21() {
		return productName21;
	}
	public void setProductName21(String productName21) {
		this.productName21 = productName21;
	}
	public String getRootFirmwareExternal21() {
		return rootFirmwareExternal21;
	}
	public void setRootFirmwareExternal21(String rootFirmwareExternal21) {
		this.rootFirmwareExternal21 = rootFirmwareExternal21;
	}
	public String getRouterFirmwareExternal21() {
		return routerFirmwareExternal21;
	}
	public void setRouterFirmwareExternal21(String routerFirmwareExternal21) {
		this.routerFirmwareExternal21 = routerFirmwareExternal21;
	}
	public String getModuleNo21() {
		return moduleNo21;
	}
	public void setModuleNo21(String moduleNo21) {
		this.moduleNo21 = moduleNo21;
	}
	public String getRootFirmwareInternal21() {
		return rootFirmwareInternal21;
	}
	public void setRootFirmwareInternal21(String rootFirmwareInternal21) {
		this.rootFirmwareInternal21 = rootFirmwareInternal21;
	}
	public String getRouterFirmwareInternal21() {
		return routerFirmwareInternal21;
	}
	public void setRouterFirmwareInternal21(String routerFirmwareInternal21) {
		this.routerFirmwareInternal21 = routerFirmwareInternal21;
	}
	public String getAntennaType21() {
		return antennaType21;
	}
	public void setAntennaType21(String antennaType21) {
		this.antennaType21 = antennaType21;
	}
	public String getFirmwareHardwareVersion21() {
		return firmwareHardwareVersion21;
	}
	public void setFirmwareHardwareVersion21(String firmwareHardwareVersion21) {
		this.firmwareHardwareVersion21 = firmwareHardwareVersion21;
	}
	public String getUploadFile21() {
		return uploadFile21;
	}
	public void setUploadFile21(String uploadFile21) {
		this.uploadFile21 = uploadFile21;
	}
	public String getPrice21() {
		return price21;
	}
	public void setPrice21(String price21) {
		this.price21 = price21;
	}
	public String getTotal_price21() {
		return total_price21;
	}
	public void setTotal_price21(String total_price21) {
		this.total_price21 = total_price21;
	}
	public String getDescription21() {
		return description21;
	}
	public void setDescription21(String description21) {
		this.description21 = description21;
	}
	public String getQuantity21() {
		return quantity21;
	}
	public void setQuantity21(String quantity21) {
		this.quantity21 = quantity21;
	}
	public String getProductName22() {
		return productName22;
	}
	public void setProductName22(String productName22) {
		this.productName22 = productName22;
	}
	public String getRootFirmwareExternal22() {
		return rootFirmwareExternal22;
	}
	public void setRootFirmwareExternal22(String rootFirmwareExternal22) {
		this.rootFirmwareExternal22 = rootFirmwareExternal22;
	}
	public String getRouterFirmwareExternal22() {
		return routerFirmwareExternal22;
	}
	public void setRouterFirmwareExternal22(String routerFirmwareExternal22) {
		this.routerFirmwareExternal22 = routerFirmwareExternal22;
	}
	public String getModuleNo22() {
		return moduleNo22;
	}
	public void setModuleNo22(String moduleNo22) {
		this.moduleNo22 = moduleNo22;
	}
	public String getRootFirmwareInternal22() {
		return rootFirmwareInternal22;
	}
	public void setRootFirmwareInternal22(String rootFirmwareInternal22) {
		this.rootFirmwareInternal22 = rootFirmwareInternal22;
	}
	public String getRouterFirmwareInternal22() {
		return routerFirmwareInternal22;
	}
	public void setRouterFirmwareInternal22(String routerFirmwareInternal22) {
		this.routerFirmwareInternal22 = routerFirmwareInternal22;
	}
	public String getAntennaType22() {
		return antennaType22;
	}
	public void setAntennaType22(String antennaType22) {
		this.antennaType22 = antennaType22;
	}
	public String getFirmwareHardwareVersion22() {
		return firmwareHardwareVersion22;
	}
	public void setFirmwareHardwareVersion22(String firmwareHardwareVersion22) {
		this.firmwareHardwareVersion22 = firmwareHardwareVersion22;
	}
	public String getUploadFile22() {
		return uploadFile22;
	}
	public void setUploadFile22(String uploadFile22) {
		this.uploadFile22 = uploadFile22;
	}
	public String getPrice22() {
		return price22;
	}
	public void setPrice22(String price22) {
		this.price22 = price22;
	}
	public String getTotal_price22() {
		return total_price22;
	}
	public void setTotal_price22(String total_price22) {
		this.total_price22 = total_price22;
	}
	public String getDescription22() {
		return description22;
	}
	public void setDescription22(String description22) {
		this.description22 = description22;
	}
	public String getQuantity22() {
		return quantity22;
	}
	public void setQuantity22(String quantity22) {
		this.quantity22 = quantity22;
	}
	public String getProductName23() {
		return productName23;
	}
	public void setProductName23(String productName23) {
		this.productName23 = productName23;
	}
	public String getRootFirmwareExternal23() {
		return rootFirmwareExternal23;
	}
	public void setRootFirmwareExternal23(String rootFirmwareExternal23) {
		this.rootFirmwareExternal23 = rootFirmwareExternal23;
	}
	public String getRouterFirmwareExternal23() {
		return routerFirmwareExternal23;
	}
	public void setRouterFirmwareExternal23(String routerFirmwareExternal23) {
		this.routerFirmwareExternal23 = routerFirmwareExternal23;
	}
	public String getModuleNo23() {
		return moduleNo23;
	}
	public void setModuleNo23(String moduleNo23) {
		this.moduleNo23 = moduleNo23;
	}
	public String getRootFirmwareInternal23() {
		return rootFirmwareInternal23;
	}
	public void setRootFirmwareInternal23(String rootFirmwareInternal23) {
		this.rootFirmwareInternal23 = rootFirmwareInternal23;
	}
	public String getRouterFirmwareInternal23() {
		return routerFirmwareInternal23;
	}
	public void setRouterFirmwareInternal23(String routerFirmwareInternal23) {
		this.routerFirmwareInternal23 = routerFirmwareInternal23;
	}
	public String getAntennaType23() {
		return antennaType23;
	}
	public void setAntennaType23(String antennaType23) {
		this.antennaType23 = antennaType23;
	}
	public String getFirmwareHardwareVersion23() {
		return firmwareHardwareVersion23;
	}
	public void setFirmwareHardwareVersion23(String firmwareHardwareVersion23) {
		this.firmwareHardwareVersion23 = firmwareHardwareVersion23;
	}
	public String getUploadFile23() {
		return uploadFile23;
	}
	public void setUploadFile23(String uploadFile23) {
		this.uploadFile23 = uploadFile23;
	}
	public String getPrice23() {
		return price23;
	}
	public void setPrice23(String price23) {
		this.price23 = price23;
	}
	public String getTotal_price23() {
		return total_price23;
	}
	public void setTotal_price23(String total_price23) {
		this.total_price23 = total_price23;
	}
	public String getDescription23() {
		return description23;
	}
	public void setDescription23(String description23) {
		this.description23 = description23;
	}
	public String getQuantity23() {
		return quantity23;
	}
	public void setQuantity23(String quantity23) {
		this.quantity23 = quantity23;
	}
	public String getProductName24() {
		return productName24;
	}
	public void setProductName24(String productName24) {
		this.productName24 = productName24;
	}
	public String getRootFirmwareExternal24() {
		return rootFirmwareExternal24;
	}
	public void setRootFirmwareExternal24(String rootFirmwareExternal24) {
		this.rootFirmwareExternal24 = rootFirmwareExternal24;
	}
	public String getRouterFirmwareExternal24() {
		return routerFirmwareExternal24;
	}
	public void setRouterFirmwareExternal24(String routerFirmwareExternal24) {
		this.routerFirmwareExternal24 = routerFirmwareExternal24;
	}
	public String getModuleNo24() {
		return moduleNo24;
	}
	public void setModuleNo24(String moduleNo24) {
		this.moduleNo24 = moduleNo24;
	}
	public String getRootFirmwareInternal24() {
		return rootFirmwareInternal24;
	}
	public void setRootFirmwareInternal24(String rootFirmwareInternal24) {
		this.rootFirmwareInternal24 = rootFirmwareInternal24;
	}
	public String getRouterFirmwareInternal24() {
		return routerFirmwareInternal24;
	}
	public void setRouterFirmwareInternal24(String routerFirmwareInternal24) {
		this.routerFirmwareInternal24 = routerFirmwareInternal24;
	}
	public String getAntennaType24() {
		return antennaType24;
	}
	public void setAntennaType24(String antennaType24) {
		this.antennaType24 = antennaType24;
	}
	public String getFirmwareHardwareVersion24() {
		return firmwareHardwareVersion24;
	}
	public void setFirmwareHardwareVersion24(String firmwareHardwareVersion24) {
		this.firmwareHardwareVersion24 = firmwareHardwareVersion24;
	}
	public String getUploadFile24() {
		return uploadFile24;
	}
	public void setUploadFile24(String uploadFile24) {
		this.uploadFile24 = uploadFile24;
	}
	public String getPrice24() {
		return price24;
	}
	public void setPrice24(String price24) {
		this.price24 = price24;
	}
	public String getTotal_price24() {
		return total_price24;
	}
	public void setTotal_price24(String total_price24) {
		this.total_price24 = total_price24;
	}
	public String getDescription24() {
		return description24;
	}
	public void setDescription24(String description24) {
		this.description24 = description24;
	}
	public String getQuantity24() {
		return quantity24;
	}
	public void setQuantity24(String quantity24) {
		this.quantity24 = quantity24;
	}
	public String getProductName25() {
		return productName25;
	}
	public void setProductName25(String productName25) {
		this.productName25 = productName25;
	}
	public String getRootFirmwareExternal25() {
		return rootFirmwareExternal25;
	}
	public void setRootFirmwareExternal25(String rootFirmwareExternal25) {
		this.rootFirmwareExternal25 = rootFirmwareExternal25;
	}
	public String getRouterFirmwareExternal25() {
		return routerFirmwareExternal25;
	}
	public void setRouterFirmwareExternal25(String routerFirmwareExternal25) {
		this.routerFirmwareExternal25 = routerFirmwareExternal25;
	}
	public String getModuleNo25() {
		return moduleNo25;
	}
	public void setModuleNo25(String moduleNo25) {
		this.moduleNo25 = moduleNo25;
	}
	public String getRootFirmwareInternal25() {
		return rootFirmwareInternal25;
	}
	public void setRootFirmwareInternal25(String rootFirmwareInternal25) {
		this.rootFirmwareInternal25 = rootFirmwareInternal25;
	}
	public String getRouterFirmwareInternal25() {
		return routerFirmwareInternal25;
	}
	public void setRouterFirmwareInternal25(String routerFirmwareInternal25) {
		this.routerFirmwareInternal25 = routerFirmwareInternal25;
	}
	public String getAntennaType25() {
		return antennaType25;
	}
	public void setAntennaType25(String antennaType25) {
		this.antennaType25 = antennaType25;
	}
	public String getFirmwareHardwareVersion25() {
		return firmwareHardwareVersion25;
	}
	public void setFirmwareHardwareVersion25(String firmwareHardwareVersion25) {
		this.firmwareHardwareVersion25 = firmwareHardwareVersion25;
	}
	public String getUploadFile25() {
		return uploadFile25;
	}
	public void setUploadFile25(String uploadFile25) {
		this.uploadFile25 = uploadFile25;
	}
	public String getPrice25() {
		return price25;
	}
	public void setPrice25(String price25) {
		this.price25 = price25;
	}
	public String getTotal_price25() {
		return total_price25;
	}
	public void setTotal_price25(String total_price25) {
		this.total_price25 = total_price25;
	}
	public String getDescription25() {
		return description25;
	}
	public void setDescription25(String description25) {
		this.description25 = description25;
	}
	public String getQuantity25() {
		return quantity25;
	}
	public void setQuantity25(String quantity25) {
		this.quantity25 = quantity25;
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
	public String getBuyer_address() {
		return buyer_address;
	}
	public void setBuyer_address(String buyer_address) {
		this.buyer_address = buyer_address;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getPayment_terms() {
		return payment_terms;
	}
	public void setPayment_terms(String payment_terms) {
		this.payment_terms = payment_terms;
	}
	public String getTerms_condition() {
		return terms_condition;
	}
	public void setTerms_condition(String terms_condition) {
		this.terms_condition = terms_condition;
	}
	public String getTax_type() {
		return tax_type;
	}
	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}
	@Override
	public String toString() {
		return "Dispatch_DC [id=" + id + ", userName=" + userName + ", transportMode=" + transportMode + ", invoiceNo="
				+ invoiceNo + ", date=" + date + ", dcNo=" + dcNo + ", eWayBillNo=" + eWayBillNo + ", customerName="
				+ customerName + ", companyName=" + companyName + ", contactNo=" + contactNo + ", emailId=" + emailId
				+ ", companyAddress=" + companyAddress + ", productName1=" + productName1 + ", rootFirmwareExternal1="
				+ rootFirmwareExternal1 + ", routerFirmwareExternal1=" + routerFirmwareExternal1 + ", moduleNo1="
				+ moduleNo1 + ", rootFirmwareInternal1=" + rootFirmwareInternal1 + ", routerFirmwareInternal1="
				+ routerFirmwareInternal1 + ", antennaType1=" + antennaType1 + ", firmwareHardwareVersion1="
				+ firmwareHardwareVersion1 + ", uploadFile1=" + uploadFile1 + ", price1=" + price1 + ", total_price1="
				+ total_price1 + ", description1=" + description1 + ", quantity1=" + quantity1 + ", productName2="
				+ productName2 + ", rootFirmwareExternal2=" + rootFirmwareExternal2 + ", routerFirmwareExternal2="
				+ routerFirmwareExternal2 + ", moduleNo2=" + moduleNo2 + ", rootFirmwareInternal2="
				+ rootFirmwareInternal2 + ", routerFirmwareInternal2=" + routerFirmwareInternal2 + ", antennaType2="
				+ antennaType2 + ", firmwareHardwareVersion2=" + firmwareHardwareVersion2 + ", uploadFile2="
				+ uploadFile2 + ", price2=" + price2 + ", total_price2=" + total_price2 + ", description2="
				+ description2 + ", quantity2=" + quantity2 + ", productName3=" + productName3
				+ ", rootFirmwareExternal3=" + rootFirmwareExternal3 + ", routerFirmwareExternal3="
				+ routerFirmwareExternal3 + ", moduleNo3=" + moduleNo3 + ", rootFirmwareInternal3="
				+ rootFirmwareInternal3 + ", routerFirmwareInternal3=" + routerFirmwareInternal3 + ", antennaType3="
				+ antennaType3 + ", firmwareHardwareVersion3=" + firmwareHardwareVersion3 + ", uploadFile3="
				+ uploadFile3 + ", price3=" + price3 + ", total_price3=" + total_price3 + ", description3="
				+ description3 + ", quantity3=" + quantity3 + ", productName4=" + productName4
				+ ", rootFirmwareExternal4=" + rootFirmwareExternal4 + ", routerFirmwareExternal4="
				+ routerFirmwareExternal4 + ", moduleNo4=" + moduleNo4 + ", rootFirmwareInternal4="
				+ rootFirmwareInternal4 + ", routerFirmwareInternal4=" + routerFirmwareInternal4 + ", antennaType4="
				+ antennaType4 + ", firmwareHardwareVersion4=" + firmwareHardwareVersion4 + ", uploadFile4="
				+ uploadFile4 + ", price4=" + price4 + ", total_price4=" + total_price4 + ", description4="
				+ description4 + ", quantity4=" + quantity4 + ", productName5=" + productName5
				+ ", rootFirmwareExternal5=" + rootFirmwareExternal5 + ", routerFirmwareExternal5="
				+ routerFirmwareExternal5 + ", moduleNo5=" + moduleNo5 + ", rootFirmwareInternal5="
				+ rootFirmwareInternal5 + ", routerFirmwareInternal5=" + routerFirmwareInternal5 + ", antennaType5="
				+ antennaType5 + ", firmwareHardwareVersion5=" + firmwareHardwareVersion5 + ", uploadFile5="
				+ uploadFile5 + ", price5=" + price5 + ", total_price5=" + total_price5 + ", description5="
				+ description5 + ", quantity5=" + quantity5 + ", productName6=" + productName6
				+ ", rootFirmwareExternal6=" + rootFirmwareExternal6 + ", routerFirmwareExternal6="
				+ routerFirmwareExternal6 + ", moduleNo6=" + moduleNo6 + ", rootFirmwareInternal6="
				+ rootFirmwareInternal6 + ", routerFirmwareInternal6=" + routerFirmwareInternal6 + ", antennaType6="
				+ antennaType6 + ", firmwareHardwareVersion6=" + firmwareHardwareVersion6 + ", uploadFile6="
				+ uploadFile6 + ", price6=" + price6 + ", total_price6=" + total_price6 + ", description6="
				+ description6 + ", quantity6=" + quantity6 + ", productName7=" + productName7
				+ ", rootFirmwareExternal7=" + rootFirmwareExternal7 + ", routerFirmwareExternal7="
				+ routerFirmwareExternal7 + ", moduleNo7=" + moduleNo7 + ", rootFirmwareInternal7="
				+ rootFirmwareInternal7 + ", routerFirmwareInternal7=" + routerFirmwareInternal7 + ", antennaType7="
				+ antennaType7 + ", firmwareHardwareVersion7=" + firmwareHardwareVersion7 + ", uploadFile7="
				+ uploadFile7 + ", price7=" + price7 + ", total_price7=" + total_price7 + ", description7="
				+ description7 + ", quantity7=" + quantity7 + ", productName8=" + productName8
				+ ", rootFirmwareExternal8=" + rootFirmwareExternal8 + ", routerFirmwareExternal8="
				+ routerFirmwareExternal8 + ", moduleNo8=" + moduleNo8 + ", rootFirmwareInternal8="
				+ rootFirmwareInternal8 + ", routerFirmwareInternal8=" + routerFirmwareInternal8 + ", antennaType8="
				+ antennaType8 + ", firmwareHardwareVersion8=" + firmwareHardwareVersion8 + ", uploadFile8="
				+ uploadFile8 + ", price8=" + price8 + ", total_price8=" + total_price8 + ", description8="
				+ description8 + ", quantity8=" + quantity8 + ", productName9=" + productName9
				+ ", rootFirmwareExternal9=" + rootFirmwareExternal9 + ", routerFirmwareExternal9="
				+ routerFirmwareExternal9 + ", moduleNo9=" + moduleNo9 + ", rootFirmwareInternal9="
				+ rootFirmwareInternal9 + ", routerFirmwareInternal9=" + routerFirmwareInternal9 + ", antennaType9="
				+ antennaType9 + ", firmwareHardwareVersion9=" + firmwareHardwareVersion9 + ", uploadFile9="
				+ uploadFile9 + ", price9=" + price9 + ", total_price9=" + total_price9 + ", description9="
				+ description9 + ", quantity9=" + quantity9 + ", productName10=" + productName10
				+ ", rootFirmwareExternal10=" + rootFirmwareExternal10 + ", routerFirmwareExternal10="
				+ routerFirmwareExternal10 + ", moduleNo10=" + moduleNo10 + ", rootFirmwareInternal10="
				+ rootFirmwareInternal10 + ", routerFirmwareInternal10=" + routerFirmwareInternal10 + ", antennaType10="
				+ antennaType10 + ", firmwareHardwareVersion10=" + firmwareHardwareVersion10 + ", uploadFile10="
				+ uploadFile10 + ", price10=" + price10 + ", total_price10=" + total_price10 + ", description10="
				+ description10 + ", quantity10=" + quantity10 + ", productName11=" + productName11
				+ ", rootFirmwareExternal11=" + rootFirmwareExternal11 + ", routerFirmwareExternal11="
				+ routerFirmwareExternal11 + ", moduleNo11=" + moduleNo11 + ", rootFirmwareInternal11="
				+ rootFirmwareInternal11 + ", routerFirmwareInternal11=" + routerFirmwareInternal11 + ", antennaType11="
				+ antennaType11 + ", firmwareHardwareVersion11=" + firmwareHardwareVersion11 + ", uploadFile11="
				+ uploadFile11 + ", price11=" + price11 + ", total_price11=" + total_price11 + ", description11="
				+ description11 + ", quantity11=" + quantity11 + ", productName12=" + productName12
				+ ", rootFirmwareExternal12=" + rootFirmwareExternal12 + ", routerFirmwareExternal12="
				+ routerFirmwareExternal12 + ", moduleNo12=" + moduleNo12 + ", rootFirmwareInternal12="
				+ rootFirmwareInternal12 + ", routerFirmwareInternal12=" + routerFirmwareInternal12 + ", antennaType12="
				+ antennaType12 + ", firmwareHardwareVersion12=" + firmwareHardwareVersion12 + ", uploadFile12="
				+ uploadFile12 + ", price12=" + price12 + ", total_price12=" + total_price12 + ", description12="
				+ description12 + ", quantity12=" + quantity12 + ", productName13=" + productName13
				+ ", rootFirmwareExternal13=" + rootFirmwareExternal13 + ", routerFirmwareExternal13="
				+ routerFirmwareExternal13 + ", moduleNo13=" + moduleNo13 + ", rootFirmwareInternal13="
				+ rootFirmwareInternal13 + ", routerFirmwareInternal13=" + routerFirmwareInternal13 + ", antennaType13="
				+ antennaType13 + ", firmwareHardwareVersion13=" + firmwareHardwareVersion13 + ", uploadFile13="
				+ uploadFile13 + ", price13=" + price13 + ", total_price13=" + total_price13 + ", description13="
				+ description13 + ", quantity13=" + quantity13 + ", productName14=" + productName14
				+ ", rootFirmwareExternal14=" + rootFirmwareExternal14 + ", routerFirmwareExternal14="
				+ routerFirmwareExternal14 + ", moduleNo14=" + moduleNo14 + ", rootFirmwareInternal14="
				+ rootFirmwareInternal14 + ", routerFirmwareInternal14=" + routerFirmwareInternal14 + ", antennaType14="
				+ antennaType14 + ", firmwareHardwareVersion14=" + firmwareHardwareVersion14 + ", uploadFile14="
				+ uploadFile14 + ", price14=" + price14 + ", total_price14=" + total_price14 + ", description14="
				+ description14 + ", quantity14=" + quantity14 + ", productName15=" + productName15
				+ ", rootFirmwareExternal15=" + rootFirmwareExternal15 + ", routerFirmwareExternal15="
				+ routerFirmwareExternal15 + ", moduleNo15=" + moduleNo15 + ", rootFirmwareInternal15="
				+ rootFirmwareInternal15 + ", routerFirmwareInternal15=" + routerFirmwareInternal15 + ", antennaType15="
				+ antennaType15 + ", firmwareHardwareVersion15=" + firmwareHardwareVersion15 + ", uploadFile15="
				+ uploadFile15 + ", price15=" + price15 + ", total_price15=" + total_price15 + ", description15="
				+ description15 + ", quantity15=" + quantity15 + ", productName16=" + productName16
				+ ", rootFirmwareExternal16=" + rootFirmwareExternal16 + ", routerFirmwareExternal16="
				+ routerFirmwareExternal16 + ", moduleNo16=" + moduleNo16 + ", rootFirmwareInternal16="
				+ rootFirmwareInternal16 + ", routerFirmwareInternal16=" + routerFirmwareInternal16 + ", antennaType16="
				+ antennaType16 + ", firmwareHardwareVersion16=" + firmwareHardwareVersion16 + ", uploadFile16="
				+ uploadFile16 + ", price16=" + price16 + ", total_price16=" + total_price16 + ", description16="
				+ description16 + ", quantity16=" + quantity16 + ", productName17=" + productName17
				+ ", rootFirmwareExternal17=" + rootFirmwareExternal17 + ", routerFirmwareExternal17="
				+ routerFirmwareExternal17 + ", moduleNo17=" + moduleNo17 + ", rootFirmwareInternal17="
				+ rootFirmwareInternal17 + ", routerFirmwareInternal17=" + routerFirmwareInternal17 + ", antennaType17="
				+ antennaType17 + ", firmwareHardwareVersion17=" + firmwareHardwareVersion17 + ", uploadFile17="
				+ uploadFile17 + ", price17=" + price17 + ", total_price17=" + total_price17 + ", description17="
				+ description17 + ", quantity17=" + quantity17 + ", productName18=" + productName18
				+ ", rootFirmwareExternal18=" + rootFirmwareExternal18 + ", routerFirmwareExternal18="
				+ routerFirmwareExternal18 + ", moduleNo18=" + moduleNo18 + ", rootFirmwareInternal18="
				+ rootFirmwareInternal18 + ", routerFirmwareInternal18=" + routerFirmwareInternal18 + ", antennaType18="
				+ antennaType18 + ", firmwareHardwareVersion18=" + firmwareHardwareVersion18 + ", uploadFile18="
				+ uploadFile18 + ", price18=" + price18 + ", total_price18=" + total_price18 + ", description18="
				+ description18 + ", quantity18=" + quantity18 + ", productName19=" + productName19
				+ ", rootFirmwareExternal19=" + rootFirmwareExternal19 + ", routerFirmwareExternal19="
				+ routerFirmwareExternal19 + ", moduleNo19=" + moduleNo19 + ", rootFirmwareInternal19="
				+ rootFirmwareInternal19 + ", routerFirmwareInternal19=" + routerFirmwareInternal19 + ", antennaType19="
				+ antennaType19 + ", firmwareHardwareVersion19=" + firmwareHardwareVersion19 + ", uploadFile19="
				+ uploadFile19 + ", price19=" + price19 + ", total_price19=" + total_price19 + ", description19="
				+ description19 + ", quantity19=" + quantity19 + ", productName20=" + productName20
				+ ", rootFirmwareExternal20=" + rootFirmwareExternal20 + ", routerFirmwareExternal20="
				+ routerFirmwareExternal20 + ", moduleNo20=" + moduleNo20 + ", rootFirmwareInternal20="
				+ rootFirmwareInternal20 + ", routerFirmwareInternal20=" + routerFirmwareInternal20 + ", antennaType20="
				+ antennaType20 + ", firmwareHardwareVersion20=" + firmwareHardwareVersion20 + ", uploadFile20="
				+ uploadFile20 + ", price20=" + price20 + ", total_price20=" + total_price20 + ", description20="
				+ description20 + ", quantity20=" + quantity20 + ", productName21=" + productName21
				+ ", rootFirmwareExternal21=" + rootFirmwareExternal21 + ", routerFirmwareExternal21="
				+ routerFirmwareExternal21 + ", moduleNo21=" + moduleNo21 + ", rootFirmwareInternal21="
				+ rootFirmwareInternal21 + ", routerFirmwareInternal21=" + routerFirmwareInternal21 + ", antennaType21="
				+ antennaType21 + ", firmwareHardwareVersion21=" + firmwareHardwareVersion21 + ", uploadFile21="
				+ uploadFile21 + ", price21=" + price21 + ", total_price21=" + total_price21 + ", description21="
				+ description21 + ", quantity21=" + quantity21 + ", productName22=" + productName22
				+ ", rootFirmwareExternal22=" + rootFirmwareExternal22 + ", routerFirmwareExternal22="
				+ routerFirmwareExternal22 + ", moduleNo22=" + moduleNo22 + ", rootFirmwareInternal22="
				+ rootFirmwareInternal22 + ", routerFirmwareInternal22=" + routerFirmwareInternal22 + ", antennaType22="
				+ antennaType22 + ", firmwareHardwareVersion22=" + firmwareHardwareVersion22 + ", uploadFile22="
				+ uploadFile22 + ", price22=" + price22 + ", total_price22=" + total_price22 + ", description22="
				+ description22 + ", quantity22=" + quantity22 + ", productName23=" + productName23
				+ ", rootFirmwareExternal23=" + rootFirmwareExternal23 + ", routerFirmwareExternal23="
				+ routerFirmwareExternal23 + ", moduleNo23=" + moduleNo23 + ", rootFirmwareInternal23="
				+ rootFirmwareInternal23 + ", routerFirmwareInternal23=" + routerFirmwareInternal23 + ", antennaType23="
				+ antennaType23 + ", firmwareHardwareVersion23=" + firmwareHardwareVersion23 + ", uploadFile23="
				+ uploadFile23 + ", price23=" + price23 + ", total_price23=" + total_price23 + ", description23="
				+ description23 + ", quantity23=" + quantity23 + ", productName24=" + productName24
				+ ", rootFirmwareExternal24=" + rootFirmwareExternal24 + ", routerFirmwareExternal24="
				+ routerFirmwareExternal24 + ", moduleNo24=" + moduleNo24 + ", rootFirmwareInternal24="
				+ rootFirmwareInternal24 + ", routerFirmwareInternal24=" + routerFirmwareInternal24 + ", antennaType24="
				+ antennaType24 + ", firmwareHardwareVersion24=" + firmwareHardwareVersion24 + ", uploadFile24="
				+ uploadFile24 + ", price24=" + price24 + ", total_price24=" + total_price24 + ", description24="
				+ description24 + ", quantity24=" + quantity24 + ", productName25=" + productName25
				+ ", rootFirmwareExternal25=" + rootFirmwareExternal25 + ", routerFirmwareExternal25="
				+ routerFirmwareExternal25 + ", moduleNo25=" + moduleNo25 + ", rootFirmwareInternal25="
				+ rootFirmwareInternal25 + ", routerFirmwareInternal25=" + routerFirmwareInternal25 + ", antennaType25="
				+ antennaType25 + ", firmwareHardwareVersion25=" + firmwareHardwareVersion25 + ", uploadFile25="
				+ uploadFile25 + ", price25=" + price25 + ", total_price25=" + total_price25 + ", description25="
				+ description25 + ", quantity25=" + quantity25 + ", appSKey=" + appSKey + ", nwkSKey=" + nwkSKey
				+ ", appEUI=" + appEUI + ", deviceAddress=" + deviceAddress + ", remarks=" + remarks + ", returnedQty="
				+ returnedQty + ", returnedDate=" + returnedDate + ", buyer_address=" + buyer_address
				+ ", delivery_address=" + delivery_address + ", payment_terms=" + payment_terms + ", terms_condition="
				+ terms_condition + ", tax_type=" + tax_type + "]";
	}
    
}