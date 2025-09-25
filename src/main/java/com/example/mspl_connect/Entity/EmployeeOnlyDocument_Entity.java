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
    
    private String offer_letter;
    
    private String pg_pic;
    
    private String exp_letter_pic;
    
    private String payslip_pic;
    
    private String diploma_pic;
    
    private String bank_check_pic;
    
    private String other_pic;
    
    private String other_pic2;
    
    private String other_pic3;
    
    private String other_pic4;

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

	public String getOffer_letter() {
		return offer_letter;
	}

	public void setOffer_letter(String offer_letter) {
		this.offer_letter = offer_letter;
	}

	public String getPg_pic() {
		return pg_pic;
	}

	public void setPg_pic(String pg_pic) {
		this.pg_pic = pg_pic;
	}

	public String getExp_letter_pic() {
		return exp_letter_pic;
	}

	public void setExp_letter_pic(String exp_letter_pic) {
		this.exp_letter_pic = exp_letter_pic;
	}

	public String getPayslip_pic() {
		return payslip_pic;
	}

	public void setPayslip_pic(String payslip_pic) {
		this.payslip_pic = payslip_pic;
	}

	public String getDiploma_pic() {
		return diploma_pic;
	}

	public void setDiploma_pic(String diploma_pic) {
		this.diploma_pic = diploma_pic;
	}

	public String getBank_check_pic() {
		return bank_check_pic;
	}

	public void setBank_check_pic(String bank_check_pic) {
		this.bank_check_pic = bank_check_pic;
	}

	public String getOther_pic() {
		return other_pic;
	}

	public void setOther_pic(String other_pic) {
		this.other_pic = other_pic;
	}

	public String getOther_pic2() {
		return other_pic2;
	}

	public void setOther_pic2(String other_pic2) {
		this.other_pic2 = other_pic2;
	}

	public String getOther_pic3() {
		return other_pic3;
	}

	public void setOther_pic3(String other_pic3) {
		this.other_pic3 = other_pic3;
	}

	public String getOther_pic4() {
		return other_pic4;
	}

	public void setOther_pic4(String other_pic4) {
		this.other_pic4 = other_pic4;
	}

	@Override
	public String toString() {
		return "EmployeeOnlyDocument_Entity [id=" + id + ", empId=" + empId + ", profilePicPath=" + profilePicPath
				+ ", panPicPath=" + panPicPath + ", aadhar_pic=" + aadhar_pic + ", tenth_pic=" + tenth_pic
				+ ", puc_pic=" + puc_pic + ", degree_pic=" + degree_pic + ", offer_letter=" + offer_letter + ", pg_pic="
				+ pg_pic + ", exp_letter_pic=" + exp_letter_pic + ", payslip_pic=" + payslip_pic + ", diploma_pic="
				+ diploma_pic + ", bank_check_pic=" + bank_check_pic + ", other_pic=" + other_pic + ", other_pic2="
				+ other_pic2 + ", other_pic3=" + other_pic3 + ", other_pic4=" + other_pic4 + "]";
	}
    
}
