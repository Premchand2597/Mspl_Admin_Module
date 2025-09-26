package com.example.mspl_connect.Sales_Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.CrmCompanyDetails;
import com.example.mspl_connect.Sales_Entity.CrmContactPerson;
import com.example.mspl_connect.Sales_Repository.CrmCompanyDetailsRepository;

@Service
public class CrmCompanyDetailsService {
	 @Autowired
	    private CrmCompanyDetailsRepository repository;

	    public CrmCompanyDetails saveCompanyDetails(CrmCompanyDetails companyDetails) {
	        return repository.save(companyDetails);
	    }
	    
	    public List<CrmCompanyDetails> getAllCompanies() {
	        return repository.findAll();
	    }
	    
	    public CrmCompanyDetails getCompanyById(Long companyId) {
	        return repository.findById(companyId).orElse(null);
	    }
	    
	    public void deleteCompanyById(Long id) {
	    	repository.deleteById(id);
	    }
	    
	    public CrmCompanyDetails updateCompanyDetailsnew(CrmCompanyDetails companyDetails) {
	        // Fetch existing company to avoid overwriting existing relationships
	        CrmCompanyDetails existingCompany = repository.findById(companyDetails.getId())
	                                                      .orElseThrow(() -> new RuntimeException("Company not found"));

	        // Update main company details
	        existingCompany.setCustomerName(companyDetails.getCustomerName());
	        existingCompany.setContactNumber(companyDetails.getContactNumber());
	        existingCompany.setGstNumber(companyDetails.getGstNumber());
	        existingCompany.setDesignation(companyDetails.getDesignation());
	        existingCompany.setCompanyName(companyDetails.getCompanyName());
	        existingCompany.setCompanyAddress(companyDetails.getCompanyAddress());
	        existingCompany.setTechnology(companyDetails.getTechnology());
	        existingCompany.setLookingFor(companyDetails.getLookingFor());
	        existingCompany.setQuantity(companyDetails.getQuantity());
	        existingCompany.setInterestedLevel(companyDetails.getInterestedLevel());
	        existingCompany.setReferredBy(companyDetails.getReferredBy());
	        existingCompany.setReferredPhoneCompany(companyDetails.getReferredPhoneCompany());
	        existingCompany.setLeadEmail(companyDetails.getLeadEmail());
	        existingCompany.setLeadCompany(companyDetails.getLeadCompany());
	        existingCompany.setProjectName(companyDetails.getProjectName());
	        existingCompany.setProductName(companyDetails.getProductName());
	        existingCompany.setSegment(companyDetails.getSegment());
	        existingCompany.setMoreInfo(companyDetails.getMoreInfo());

	        // Debug main company details
	        System.out.println("Updated main company details for ID: " + existingCompany.getId());

	        // Handle contact person updates
	        List<CrmContactPerson> updatedContacts = companyDetails.getContactPersons();
	        System.out.println("Received Company Details:");
	        System.out.println("ID: " + companyDetails.getId());
	        System.out.println("Contact Persons: " + companyDetails.getContactPersons());

	        if (updatedContacts != null) {
	            // Remove old contacts
	            System.out.println("Clearing old contacts...");
	            existingCompany.getContactPersons().clear();

	            // Add updated contacts
	            for (CrmContactPerson contact : updatedContacts) {
	                contact.setCompany(existingCompany); // Set the company reference

	                if (contact.getId() != null) {
	                    // Update existing contact details
	                    System.out.println("Updating contact with ID: " + contact.getId());
	                    System.out.println("Contact Person: " + contact.getContactPerson());
	                    System.out.println("Contact Number: " + contact.getContactNumber());
	                    System.out.println("Email: " + contact.getEmail());
	                    System.out.println("Designation: " + contact.getDesignation());
	                    System.out.println("Priority: " + contact.getPriority());

	                    Optional<CrmContactPerson> existingContact = existingCompany.getContactPersons().stream()
	                            .filter(c -> c.getId().equals(contact.getId()))
	                            .findFirst();

	                    if (existingContact.isPresent()) {
	                        CrmContactPerson existing = existingContact.get();
	                        existing.setContactPerson(contact.getContactPerson());
	                        existing.setContactNumber(contact.getContactNumber());
	                        existing.setEmail(contact.getEmail());
	                        existing.setDesignation(contact.getDesignation());
	                        existing.setPriority(contact.getPriority());
	                    } else {
	                        System.out.println("Adding new contact with ID: " + contact.getId());
	                        existingCompany.getContactPersons().add(contact);
	                    }
	                } else {
	                    // New contact without ID
	                    System.out.println("Adding new contact without ID:");
	                    System.out.println("Contact Person: " + contact.getContactPerson());
	                    System.out.println("Contact Number: " + contact.getContactNumber());
	                    System.out.println("Email: " + contact.getEmail());
	                    System.out.println("Designation: " + contact.getDesignation());
	                    System.out.println("Priority: " + contact.getPriority());

	                    existingCompany.getContactPersons().add(contact);
	                }
	            }
	        }

	        // Save the updated company with the new contacts
	        CrmCompanyDetails savedCompany = repository.save(existingCompany);
	        System.out.println("Company saved with ID: " + savedCompany.getId());
	        System.out.println("Total contacts saved: " + savedCompany.getContactPersons().size());

	        return savedCompany;
	    }



	    // Update an existing company
	    public CrmCompanyDetails updateCompanyDetails(Long id, CrmCompanyDetails companyDetails) {
	        if (repository.existsById(id)) {
	            companyDetails.setId(id);
	            return repository.save(companyDetails);
	        }
	        throw new RuntimeException("Company with ID " + id + " not found");
	    }
}
