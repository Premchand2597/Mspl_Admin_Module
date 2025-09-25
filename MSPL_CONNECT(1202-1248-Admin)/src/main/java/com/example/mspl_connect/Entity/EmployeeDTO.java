package com.example.mspl_connect.Entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

public class EmployeeDTO {
	
	    private int id;

	    private String empId;

	    private int deptId;

	    private int roleId;

	    private String firstName;

	    private String lastName;

	    private String email;

	    private String mobileNo;

	    private String address;

	    private String dob; // Consider using LocalDate for date fields

	    private String doj; // Consider using LocalDate for date fields

	    private String adharNo;

	    private String panCard;

	    private String gender;

	    private String password;
	    
	    private String linkedin_link;
	    
	    private String twitter_link;

	    private MultipartFile profilePic;

	    private MultipartFile panPic;
}
