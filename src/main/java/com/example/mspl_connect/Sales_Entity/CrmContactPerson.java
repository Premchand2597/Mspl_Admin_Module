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
@Table(name = "crm_contact_persons")
public class CrmContactPerson {
	public CrmContactPerson(Long id, String contactPerson, String contactNumber, String email, String designation,
			String priority, CrmCompanyDetails company) {
		super();
		this.id = id;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.email = email;
		this.designation = designation;
		this.priority = priority;
		this.company = company;
	}

	public CrmContactPerson() {
		super();
	}

	public CrmContactPerson(Long id, String contactPerson, String contactNumber, String email, String designation,
			CrmCompanyDetails company) {
		super();
		this.id = id;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.email = email;
		this.designation = designation;
		this.company = company;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactPerson;
    private String contactNumber;
    private String email;
    private String designation;
    
    @Column(nullable = true)
    private String priority;  // Now nullable
    
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private CrmCompanyDetails company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public CrmCompanyDetails getCompany() {
		return company;
	}

	public void setCompany(CrmCompanyDetails company) {
		this.company = company;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
