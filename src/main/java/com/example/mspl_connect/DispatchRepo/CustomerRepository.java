package com.example.mspl_connect.DispatchRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.DispatchEntity.Customer;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 Customer findByCustomerName(String customerName);
	 
	 @Query(nativeQuery = true, value="select * from customer order by id desc limit 1")
	 Customer fetchRecentlyInsertedDetails();
	 
	 @Modifying
	 @Transactional
	 @Query(nativeQuery = true, value = "delete from customer where customer_name=:name and contact_no=:contactNo")
	 void deleteCustomerData(@Param("name") String name, @Param("contactNo") String contactNo);
	 
	 @Query(nativeQuery = true, value="Select * from customer where id=:id")
	 Customer getCustomerDetailsBasedOnAutoId(@Param("id") long id);
	 
	 void deleteById(Long id);
	 
	 List<Customer> findAllByOrderByIdDesc();
	 
	 @Query(nativeQuery = true, value = "select email_id from customer where email_id=:email")
	 String isEmailIdExists(@Param("email") String email);
	 
	 @Query(nativeQuery = true, value = "select company_name from customer where company_name=:company_name")
	 String isCompanyNameExists(@Param("company_name") String company_name);
	 
	 @Query(nativeQuery = true, value = "select contact_no from customer where contact_no=:contact_no")
	 String isContactNoExists(@Param("contact_no") String contact_no);
}
