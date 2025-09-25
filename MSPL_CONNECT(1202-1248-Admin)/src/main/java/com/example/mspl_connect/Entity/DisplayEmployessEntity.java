package com.example.mspl_connect.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="employee_details")
public class DisplayEmployessEntity {

	@Id
    private int id;
    private String empid;
    @Column(name="dept_id")
    private int deptId;
    @Column(name="role_id")
    private int roleId;
    @Column(name="f_name")
    private String fName;
    @Column(name="l_name")
    private String lName;
    private String email;    
    @Column(name="mobile_no")
    private String mobileNo;
    private String address;
    private String dob;
    private String doj;
    
    @Column(name="adhar_no")
    private String adharNo;
    
    @Column(name="pan_card")
    private String panCard;
    private String gender;
    private String password;
    
    @Column(name="dept_name")
    private String deptName;
    @Column(name="role_name")
    private String roleName;
    @Column(name="full_name")
	private String fullName;
    
    @Column(name = "linkedin_link")
    private String linkedin_link;
    
    @Column(name = "twitter_link")
    private String twitter_link;
    
    @Column(name = "password_change_flag")
    private String passwordChangeFlag;
    
    @Lob
    @Column(name = "profile_pic_path")
	private String profilePicPath;

    @Column(name = "pan_pic_path")
    private String panPicPath;
    
    @Column(name= "otp")
    private String otp;
    
    @Column(name = "team_co_name")
    private String team_coordinator;
    
    @Column(name = "team_lead_name")
    private String team_lead;
    
    @Column(name = "corr_address")
    private String corr_address;
    
     
	private String primary_emergency_contact_name;
    private String primary_emergency_contact_number;
    private String primary_emergency_contact_relation;
    private String secondary_emergency_contact_name;
    private String secondary_emergency_contact_number;
    private String secondary_emergency_contact_relation;
    
    
	public String getCorr_address() {
		return corr_address;
	}

	public void setCorr_address(String corr_address) {
		this.corr_address = corr_address;
	}

	public String getPasswordChangeFlag() {
		return passwordChangeFlag;
	}

	public void setPasswordChangeFlag(String passwordChangeFlag) {
		this.passwordChangeFlag = passwordChangeFlag;
	}

	public String getTeam_coordinator() {
		return team_coordinator;
	}

	public void setTeam_coordinator(String team_coordinator) {
		this.team_coordinator = team_coordinator;
	}

	public String getTeam_lead() {
		return team_lead;
	}

	public void setTeam_lead(String team_lead) {
		this.team_lead = team_lead;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



    // Constructors
    public DisplayEmployessEntity() {
        // Default constructor
    }

   

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPrimary_emergency_contact_name() {
		return primary_emergency_contact_name;
	}

	public void setPrimary_emergency_contact_name(String primary_emergency_contact_name) {
		this.primary_emergency_contact_name = primary_emergency_contact_name;
	}

	public String getPrimary_emergency_contact_number() {
		return primary_emergency_contact_number;
	}

	public void setPrimary_emergency_contact_number(String primary_emergency_contact_number) {
		this.primary_emergency_contact_number = primary_emergency_contact_number;
	}

	public String getPrimary_emergency_contact_relation() {
		return primary_emergency_contact_relation;
	}

	public void setPrimary_emergency_contact_relation(String primary_emergency_contact_relation) {
		this.primary_emergency_contact_relation = primary_emergency_contact_relation;
	}

	public String getSecondary_emergency_contact_name() {
		return secondary_emergency_contact_name;
	}

	public void setSecondary_emergency_contact_name(String secondary_emergency_contact_name) {
		this.secondary_emergency_contact_name = secondary_emergency_contact_name;
	}

	public String getSecondary_emergency_contact_number() {
		return secondary_emergency_contact_number;
	}

	public void setSecondary_emergency_contact_number(String secondary_emergency_contact_number) {
		this.secondary_emergency_contact_number = secondary_emergency_contact_number;
	}

	public String getSecondary_emergency_contact_relation() {
		return secondary_emergency_contact_relation;
	}

	public void setSecondary_emergency_contact_relation(String secondary_emergency_contact_relation) {
		this.secondary_emergency_contact_relation = secondary_emergency_contact_relation;
	}

	@Override
	public String toString() {
		return "DisplayEmployessEntity [id=" + id + ", empid=" + empid + ", deptId=" + deptId + ", roleId=" + roleId
				+ ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", mobileNo=" + mobileNo + ", address="
				+ address + ", dob=" + dob + ", doj=" + doj + ", adharNo=" + adharNo + ", panCard=" + panCard
				+ ", gender=" + gender + ", password=" + password + ", deptName=" + deptName + ", roleName=" + roleName
				+ ", fullName=" + fullName + ", linkedin_link=" + linkedin_link + ", twitter_link=" + twitter_link
				+ ", passwordChangeFlag=" + passwordChangeFlag + ", profilePicPath=" + profilePicPath + ", panPicPath="
				+ panPicPath + ", otp=" + otp + ", team_coordinator=" + team_coordinator + ", team_lead=" + team_lead
				+ ", corr_address=" + corr_address + ", primary_emergency_contact_name="
				+ primary_emergency_contact_name + ", primary_emergency_contact_number="
				+ primary_emergency_contact_number + ", primary_emergency_contact_relation="
				+ primary_emergency_contact_relation + ", secondary_emergency_contact_name="
				+ secondary_emergency_contact_name + ", secondary_emergency_contact_number="
				+ secondary_emergency_contact_number + ", secondary_emergency_contact_relation="
				+ secondary_emergency_contact_relation + "]";
	}
}

