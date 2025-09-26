package com.example.mspl_connect.Entity;


import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "employee_details")
public class EmployeeDetailsEntity1 {
	    public EmployeeDetailsEntity1() {
		super();
	}

		public EmployeeDetailsEntity1(int id, String empId, int deptId, int roleId, String firstName, String lastName,
			String email, String mobileNo, String address, String dob, String doj, String adharNo, String panCard,
			String gender, String password, String linkedin_link, String twitter_link, String profile_pic_path,
			String primaryEmergencyContactName, String primaryEmergencyContactRelation,
			String primaryEmergencyContactNumber, String secondaryEmergencyContactName,
			String secondaryEmergencyContactRelation, String secondaryEmergencyContactNumber, MultipartFile profilePic,
			MultipartFile panPic) {
		super();
		this.id = id;
		this.empId = empId;
		this.deptId = deptId;
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.dob = dob;
		this.doj = doj;
		this.adharNo = adharNo;
		this.panCard = panCard;
		this.gender = gender;
		this.password = password;
		this.linkedin_link = linkedin_link;
		this.twitter_link = twitter_link;
		this.profile_pic_path = profile_pic_path;
		this.primaryEmergencyContactName = primaryEmergencyContactName;
		this.primaryEmergencyContactRelation = primaryEmergencyContactRelation;
		this.primaryEmergencyContactNumber = primaryEmergencyContactNumber;
		this.secondaryEmergencyContactName = secondaryEmergencyContactName;
		this.secondaryEmergencyContactRelation = secondaryEmergencyContactRelation;
		this.secondaryEmergencyContactNumber = secondaryEmergencyContactNumber;
		this.profilePic = profilePic;
		this.panPic = panPic;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "empid", nullable = false)
	    private String empId;

	    @Column(name = "dept_id", nullable = false)
	    private int deptId;

	    @Column(name = "role_id", nullable = false)
	    private int roleId;

	    @Column(name = "f_name", nullable = false)
	    private String firstName;

	    @Column(name = "l_name", nullable = false)
	    private String lastName;

	    @Column(name = "email", nullable = false)
	    private String email;

	    @Column(name = "mobile_no", nullable = false)
	    private String mobileNo;

	    @Column(name = "address", nullable = false)
	    private String address;

	    @Column(name = "dob", nullable = false)
	    private String dob; // Consider using LocalDate for date fields

	    @Column(name = "doj", nullable = false)
	    private String doj; // Consider using LocalDate for date fields

	    @Column(name = "adhar_no", nullable = false)
	    private String adharNo;

	    @Column(name = "pan_card", nullable = false)
	    private String panCard;

	    @Column(name = "gender", nullable = false)
	    private String gender;

	    @Column(name = "password", nullable = false)
	    private String password;
	    
	    @Column(name = "linkedin_link")
	    private String linkedin_link;
	    
	    @Column(name = "twitter_link")
	    private String twitter_link;
	    
	    @Column(name = "profile_pic_path")
	    private String  profile_pic_path;

	    // Emergency contact fields
	    @Column(name = "primary_emergency_contact_name", nullable = true)
	    private String primaryEmergencyContactName;

	    @Column(name = "primary_emergency_contact_relation", nullable = true)
	    private String primaryEmergencyContactRelation;

	    @Column(name = "primary_emergency_contact_number", nullable = true)
	    private String primaryEmergencyContactNumber;

	    @Column(name = "secondary_emergency_contact_name", nullable = true)
	    private String secondaryEmergencyContactName;

	    @Column(name = "secondary_emergency_contact_relation", nullable = true)
	    private String secondaryEmergencyContactRelation;

	    @Column(name = "secondary_emergency_contact_number", nullable = true)
	    private String secondaryEmergencyContactNumber;

	    
	    public String getLinkedin_link() {
			return linkedin_link;
		}

		public void setLinkedin_link(String linkedin_link) {
			this.linkedin_link = linkedin_link;
		}

		public String getTwitter_link() {
			return twitter_link;
		}

		public void setTwitter_link(String twitter_link) {
			this.twitter_link = twitter_link;
		}

		@Transient
	    private MultipartFile profilePic;

	    @Transient
	    private MultipartFile panPic;

		

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		

		public int getDeptId() {
			return deptId;
		}

		public void setDeptId(int deptId) {
			System.out.println("/////"+deptId);
			this.deptId = deptId;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getDoj() {
			return doj;
		}

		public void setDoj(String doj) {
			this.doj = doj;
		}

		public String getAdharNo() {
			return adharNo;
		}

		public void setAdharNo(String adharNo) {
			this.adharNo = adharNo;
		}

		public String getPanCard() {
			return panCard;
		}

		public void setPanCard(String panCard) {
			this.panCard = panCard;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public MultipartFile getProfilePic() {
			return profilePic;
		}

		public void setProfilePic(MultipartFile profilePic) {
			this.profilePic = profilePic;
		}

		public MultipartFile getPanPic() {
			return panPic;
		}

		public void setPanPic(MultipartFile panPic) {
			this.panPic = panPic;
		}

		@Override
		public String toString() {
			return "EmployeeDetailsEntity [id=" + id + ", empId=" + empId + ", deptId=" + deptId + ", roleId=" + roleId
					+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobileNo="
					+ mobileNo + ", address=" + address + ", dob=" + dob + ", doj=" + doj + ", adharNo=" + adharNo
					+ ", panCard=" + panCard + ", gender=" + gender + ", password=" + password + ", linkedin_link="
					+ linkedin_link + ", twitter_link=" + twitter_link + ", profilePic=" + profilePic + ", panPic="
					+ panPic + "]";
		}

		public String getProfile_pic_path() {
			return profile_pic_path;
		}

		public void setProfile_pic_path(String profile_pic_path) {
			this.profile_pic_path = profile_pic_path;
		}

		public String getPrimaryEmergencyContactName() {
			return primaryEmergencyContactName;
		}

		public void setPrimaryEmergencyContactName(String primaryEmergencyContactName) {
			this.primaryEmergencyContactName = primaryEmergencyContactName;
		}

		public String getPrimaryEmergencyContactRelation() {
			return primaryEmergencyContactRelation;
		}

		public void setPrimaryEmergencyContactRelation(String primaryEmergencyContactRelation) {
			this.primaryEmergencyContactRelation = primaryEmergencyContactRelation;
		}

		public String getPrimaryEmergencyContactNumber() {
			return primaryEmergencyContactNumber;
		}

		public void setPrimaryEmergencyContactNumber(String primaryEmergencyContactNumber) {
			this.primaryEmergencyContactNumber = primaryEmergencyContactNumber;
		}

		public String getSecondaryEmergencyContactName() {
			return secondaryEmergencyContactName;
		}

		public void setSecondaryEmergencyContactName(String secondaryEmergencyContactName) {
			this.secondaryEmergencyContactName = secondaryEmergencyContactName;
		}

		public String getSecondaryEmergencyContactRelation() {
			return secondaryEmergencyContactRelation;
		}

		public void setSecondaryEmergencyContactRelation(String secondaryEmergencyContactRelation) {
			this.secondaryEmergencyContactRelation = secondaryEmergencyContactRelation;
		}

		public String getSecondaryEmergencyContactNumber() {
			return secondaryEmergencyContactNumber;
		}

		public void setSecondaryEmergencyContactNumber(String secondaryEmergencyContactNumber) {
			this.secondaryEmergencyContactNumber = secondaryEmergencyContactNumber;
		}
}