package com.example.mspl_connect.DispatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.Customer;
import com.example.mspl_connect.DispatchRepo.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer) {
	    return customerRepository.save(customer);
	}
	
	public long getTotalCustomers() {
		return customerRepository.count();
	}
	
	public Customer fetchRecentDetails() {
	    return customerRepository.fetchRecentlyInsertedDetails();
	}
	
	public void deleteCustomerDataBasedOnNameAndContactNo(String name, String contactNo) {
		customerRepository.deleteCustomerData(name, contactNo);
	}
	
	public void deleteData(long id) {
		customerRepository.deleteById(id);
	}
	
	public boolean isEmailExistsOrNot(String email) {
		String exists = customerRepository.isEmailIdExists(email);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isCompanyNameExists(String company_name) {
		String exists = customerRepository.isCompanyNameExists(company_name);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isContactNoExists(String contact_no) {
		String exists = customerRepository.isContactNoExists(contact_no);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}

}
