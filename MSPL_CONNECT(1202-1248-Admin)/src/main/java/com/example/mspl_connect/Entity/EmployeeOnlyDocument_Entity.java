package com.example.mspl_connect.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class EmployeeOnlyDocument_Entity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "empid", nullable = false)
    private String empId;
	
	
    @Column(name = "profile_pic_path")
	private String profilePicPath;
	    
    
    @Column(name = "pan_pic_path")
    private String panPicPath;
    
    
    private String aadhar_pic;
    
    
    private String tenth_pic;
    
    
    private String puc_pic;
    
    
    private String degree_pic;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public String getPanPicPath() {
		return panPicPath;
	}

	public void setPanPicPath(String panPicPath) {
		this.panPicPath = panPicPath;
	}

	public String getAadhar_pic() {
		return aadhar_pic;
	}

	public void setAadhar_pic(String aadhar_pic) {
		this.aadhar_pic = aadhar_pic;
	}

	public String getTenth_pic() {
		return tenth_pic;
	}

	public void setTenth_pic(String tenth_pic) {
		this.tenth_pic = tenth_pic;
	}

	public String getPuc_pic() {
		return puc_pic;
	}

	public void setPuc_pic(String puc_pic) {
		this.puc_pic = puc_pic;
	}

	public String getDegree_pic() {
		return degree_pic;
	}

	public void setDegree_pic(String degree_pic) {
		this.degree_pic = degree_pic;
	}

	@Override
	public String toString() {
		return "EmployeeOnlyDocument_Entity [id=" + id + ", empId=" + empId + ", profilePicPath=" + profilePicPath
				+ ", panPicPath=" + panPicPath + ", aadhar_pic=" + aadhar_pic + ", tenth_pic=" + tenth_pic
				+ ", puc_pic=" + puc_pic + ", degree_pic=" + degree_pic + "]";
	}
    
}
