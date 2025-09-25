package com.example.mspl_connect.DispatchEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "product_name", nullable = false)
	 	private String productName;
	    private String rootFirmwareExternal;
	    private String routerFirmwareExternal;
	    private String moduleNo;
	    
	    @Column(name = "root_firmware_internal")
	    private String rootFirmwareInternal;
	
	    @Column(name = "router_firmware_internal")
	    private String routerFirmwareInternal;
	
	    private String antennaType;
	    private String firmwareHardwareVersion;
	    
	    @Column(name = "upload_file")
	    private String uploadFile; 
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
}
