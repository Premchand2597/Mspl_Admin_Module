package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "crm_company_details")
public class CrmCompanyDetails {
	 public CrmCompanyDetails(Long id, String customerName, String contactNumber, String gstNumber, String companyName,
			String companyAddress, String technology, String lookingFor, Integer quantity, String interestedLevel,
			String referredBy, String referredPhoneCompany, String moreInfo, String contactPerson, String designation,
			String domain, LocalDateTime createdAt, String leadCompany, String leadEmail, String projectName,
			String productName, String segment, List<CrmContactPerson> contactPersons) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.gstNumber = gstNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.technology = technology;
		this.lookingFor = lookingFor;
		this.quantity = quantity;
		this.interestedLevel = interestedLevel;
		this.referredBy = referredBy;
		this.referredPhoneCompany = referredPhoneCompany;
		this.moreInfo = moreInfo;
		this.contactPerson = contactPerson;
		this.designation = designation;
		this.domain = domain;
		this.createdAt = createdAt;
		this.leadCompany = leadCompany;
		this.leadEmail = leadEmail;
		this.projectName = projectName;
		this.productName = productName;
		this.segment = segment;
		this.contactPersons = contactPersons;
	}

	public CrmCompanyDetails(Long id, String customerName, String contactNumber, String gstNumber, String companyName,
			String companyAddress, String technology, String lookingFor, Integer quantity,
			String interestedLevel, String referredBy, String referredPhoneCompany, String moreInfo,
			String contactPerson, String designation, String domain, LocalDateTime createdAt,
			List<CrmContactPerson> contactPersons) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.gstNumber = gstNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.technology = technology;
		this.lookingFor = lookingFor;
		
		this.quantity = quantity;
		this.interestedLevel = interestedLevel;
		this.referredBy = referredBy;
		this.referredPhoneCompany = referredPhoneCompany;
		this.moreInfo = moreInfo;
		this.contactPerson = contactPerson;
		this.designation = designation;
		this.domain = domain;
		this.createdAt = createdAt;
		this.contactPersons = contactPersons;
	}

	public CrmCompanyDetails(Long id, String customerName, String contactNumber, String gstNumber, String companyName,
			String companyAddress, String technology, String lookingFor, Integer quantity,
			String interestedLevel, String referredBy, String referredPhoneCompany, String moreInfo,
			String contactPerson, String designation, String domain, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.gstNumber = gstNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.technology = technology;
		this.lookingFor = lookingFor;
		
		this.quantity = quantity;
		this.interestedLevel = interestedLevel;
		this.referredBy = referredBy;
		this.referredPhoneCompany = referredPhoneCompany;
		this.moreInfo = moreInfo;
		this.contactPerson = contactPerson;
		this.designation = designation;
		this.domain = domain;
		this.createdAt = createdAt;
	}

	public CrmCompanyDetails() {
		super();
	}

	public CrmCompanyDetails(Long id, String customerName, String contactNumber, String gstNumber, String companyName,
			String companyAddress, String technology, String lookingFor,  Integer quantity,
			String interestedLevel, String referredBy, String referredPhoneCompany, String moreInfo,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.gstNumber = gstNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.technology = technology;
		this.lookingFor = lookingFor;

		this.quantity = quantity;
		this.interestedLevel = interestedLevel;
		this.referredBy = referredBy;
		this.referredPhoneCompany = referredPhoneCompany;
		this.moreInfo = moreInfo;
		this.createdAt = createdAt;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "customer_name")
	    private String customerName;

	    @Column(name = "contact_number")
	    private String contactNumber;

	 //   @Column(name = "email_id", nullable = false)
	  //  private String emailId;
	 // Changed from emailId to gstNumber, column name updated too
	    @Column(name = "gst_number")
	    private String gstNumber;
	    
	    @Column(name = "company_name")
	    private String companyName;

	    @Column(name = "company_address")
	    private String companyAddress;

	    @Column(name = "technology")
	    private String technology;

	    @Column(name = "looking_for")
	    private String lookingFor;

	 
	    @Column(name = "quantity", nullable = true) 
	    private Integer quantity;

	    @Column(name = "interested_level")
	    private String interestedLevel;

	    @Column(name = "referred_by")
	    private String referredBy;

	    @Column(name = "referred_phone_company")
	    private String referredPhoneCompany;

	    @Column(name = "more_info", columnDefinition = "TEXT")
	    private String moreInfo;
	    
	    @Column(name = "contact_person", nullable = true)
	    private String contactPerson;

	    @Column(name = "designation", nullable = true)
	    private String designation;

	    @Column(name = "domain", nullable = true)
	    private String domain;
	    
	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "lead_company")
	    private String leadCompany;
	    
	    @Column(name = "lead_email")
	    private String leadEmail;
	    
	    @Column(name = "project_name")
	    private String projectName;
	    
	    @Column(name = "product_name")
	    private String productName;
	    
	    @Column(name = "segment")
	    private String segment;
	    
	    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	    @JsonManagedReference
	    private List<CrmContactPerson> contactPersons;
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getCompanyAddress() {
			return companyAddress;
		}

		public void setCompanyAddress(String companyAddress) {
			this.companyAddress = companyAddress;
		}

		public String getTechnology() {
			return technology;
		}

		public void setTechnology(String technology) {
			this.technology = technology;
		}

		public String getLookingFor() {
			return lookingFor;
		}

		public void setLookingFor(String lookingFor) {
			this.lookingFor = lookingFor;
		}

		

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getInterestedLevel() {
			return interestedLevel;
		}

		public void setInterestedLevel(String interestedLevel) {
			this.interestedLevel = interestedLevel;
		}

		public String getReferredBy() {
			return referredBy;
		}

		public void setReferredBy(String referredBy) {
			this.referredBy = referredBy;
		}

		public String getReferredPhoneCompany() {
			return referredPhoneCompany;
		}

		public void setReferredPhoneCompany(String referredPhoneCompany) {
			this.referredPhoneCompany = referredPhoneCompany;
		}

		public String getMoreInfo() {
			return moreInfo;
		}

		public void setMoreInfo(String moreInfo) {
			this.moreInfo = moreInfo;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public String getContactPerson() {
			return contactPerson;
		}

		public void setContactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public List<CrmContactPerson> getContactPersons() {
			return contactPersons;
		}

		public void setContactPersons(List<CrmContactPerson> contactPersons) {
			this.contactPersons = contactPersons;
		}

		public String getLeadCompany() {
			return leadCompany;
		}

		public void setLeadCompany(String leadCompany) {
			this.leadCompany = leadCompany;
		}

		public String getLeadEmail() {
			return leadEmail;
		}

		public void setLeadEmail(String leadEmail) {
			this.leadEmail = leadEmail;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getSegment() {
			return segment;
		}

		public void setSegment(String segment) {
			this.segment = segment;
		}

		public String getGstNumber() {
			return gstNumber;
		}

		public void setGstNumber(String gstNumber) {
			this.gstNumber = gstNumber;
		}

}
