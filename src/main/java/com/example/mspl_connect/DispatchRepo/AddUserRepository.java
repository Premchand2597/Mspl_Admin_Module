package com.example.mspl_connect.DispatchRepo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.DispatchEntity.AddUser;


public interface AddUserRepository extends JpaRepository<AddUser, Long> {
	AddUser findByEmailId(String email);
	
	@Query("SELECT u.userName FROM AddUser u")
	List<String> findAllUsernames();

	@Query(nativeQuery = true, value="Select distinct(user_name) from addusers")
	List<String> getAllDistinctUserNames();
	
	@Query(nativeQuery = true, value="Select * from addusers where id=:id")
	AddUser getDetailsBasedOnAutoId(@Param("id") long id);
	
	void deleteById(Long id);
	
	List<AddUser> findAllByOrderByIdDesc();
	
	@Query(nativeQuery = true, value="select user_name from addusers where user_name=:name")
	String isUserNameExists(@Param("name") String name);
	
	@Query(nativeQuery = true, value="select email_id from addusers where email_id=:emailId")
	String isUserEmailExists(@Param("emailId") String emailId);
	
	@Query(nativeQuery = true, value="select contact_no from addusers where contact_no=:contact_no")
	String isContactNoExists(@Param("contact_no") String contact_no);
}
