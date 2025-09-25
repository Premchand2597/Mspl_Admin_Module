	package com.example.mspl_connect.AdminEntity;

	public class EmployeeNameEmailDTO {
	    private int id;
	    private String firstName;
	    private String email;

	    // Constructor
	    public EmployeeNameEmailDTO(int id, String firstName, String email) {
	        this.id = id;
	        this.firstName = firstName;
	        this.email = email;
	    }

	    // Getters
	    public int getId() {
	        return id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getEmail() {
	        return email;
	    }
	}


