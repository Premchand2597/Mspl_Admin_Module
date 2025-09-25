package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="link_generated_table")
public class LinkGeneratedTableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String encrypted_link;
	
	@Column(name = "candidate_email")
	private String candidateEmail;
	
	private String test_duration;
	private String supervisor_name;
	
	@Column(name = "link_created_date") 
	private String linkCreatedDate;
	
	@Column(name = "encrypted_data")
	private String encryptedData;
	
	@Column(name = "first_access_time")
	private String firstAccessTime;  // Changed to String to store formatted LocalDateTime
	
	@Column(name = "expiration_time")
    private String expirationTime;   // Changed to String to store formatted LocalDateTime
	
	private String test_valid_flag;
	
	private String test_type;
	
	private String dept_name;
    
	public LinkGeneratedTableEntity(int id, String encrypted_link, String candidateEmail, String test_duration,
			String supervisor_name, String linkCreatedDate, String encryptedData, String firstAccessTime,
			String expirationTime, String test_valid_flag, String test_type, String dept_name) {
		super();
		this.id = id;
		this.encrypted_link = encrypted_link;
		this.candidateEmail = candidateEmail;
		this.test_duration = test_duration;
		this.supervisor_name = supervisor_name;
		this.linkCreatedDate = linkCreatedDate;
		this.encryptedData = encryptedData;
		this.firstAccessTime = firstAccessTime;
		this.expirationTime = expirationTime;
		this.test_valid_flag = test_valid_flag;
		this.test_type = test_type;
		this.dept_name = dept_name;
	}

	public String getTest_type() {
		return test_type;
	}

	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}

	public String getTest_valid_flag() {
		return test_valid_flag;
	}

	public void setTest_valid_flag(String test_valid_flag) {
		this.test_valid_flag = test_valid_flag;
	}

	public LinkGeneratedTableEntity() {
		super();
	}

	// Getters and Setters for firstAccessTime and expirationTime as String
	public String getFirstAccessTime() {
		return firstAccessTime;
	}
	public void setFirstAccessTime(String firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	// Other Getters and Setters
	public String getEncryptedData() {
		return encryptedData;
	}
	public String getLinkCreatedDate() {
		return linkCreatedDate;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public void setLinkCreatedDate(String linkCreatedDate) {
		this.linkCreatedDate = linkCreatedDate;
	}
	public String getSupervisor_name() {
		return supervisor_name;
	}
	public void setSupervisor_name(String supervisor_name) {
		this.supervisor_name = supervisor_name;
	}
	public String getTest_duration() {
		return test_duration;
	}
	public void setTest_duration(String test_duration) {
		this.test_duration = test_duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEncrypted_link() {
		return encrypted_link;
	}
	public void setEncrypted_link(String encrypted_link) {
		this.encrypted_link = encrypted_link;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	
	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String toString() {
		return "LinkGeneratedTableEntity [id=" + id + ", encrypted_link=" + encrypted_link + ", candidateEmail="
				+ candidateEmail + ", test_duration=" + test_duration + ", supervisor_name=" + supervisor_name
				+ ", linkCreatedDate=" + linkCreatedDate + ", encryptedData=" + encryptedData + ", firstAccessTime="
				+ firstAccessTime + ", expirationTime=" + expirationTime + ", test_valid_flag=" + test_valid_flag
				+ ", test_type=" + test_type + ", dept_name=" + dept_name + "]";
	}
}
