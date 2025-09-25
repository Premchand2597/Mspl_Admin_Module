package com.example.mspl_connect.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_details")
public class DisplayEmployessWithMissPunchEntity {

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
    
    private String misspunch_count;    
    
    private String blood_group;
    private String primary_emergency_contact_number;
    private String employee_type;
    private String candidate_type;
    private String probation_completed_date;
    private String last_working_date;
    private boolean attendance_link;
    
    
	public DisplayEmployessWithMissPunchEntity(int id, String empid, int deptId, int roleId, String fName, String lName,
			String email, String mobileNo, String address, String dob, String doj, String adharNo, String panCard,
			String gender, String password, String deptName, String roleName, String fullName, String linkedin_link,
			String twitter_link, String passwordChangeFlag, String profilePicPath, String panPicPath, String otp,
			String team_coordinator, String team_lead, String misspunch_count, String blood_group,
			String primary_emergency_contact_number) {
		super();
		this.id = id;
		this.empid = empid;
		this.deptId = deptId;
		this.roleId = roleId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.dob = dob;
		this.doj = doj;
		this.adharNo = adharNo;
		this.panCard = panCard;
		this.gender = gender;
		this.password = password;
		this.deptName = deptName;
		this.roleName = roleName;
		this.fullName = fullName;
		this.linkedin_link = linkedin_link;
		this.twitter_link = twitter_link;
		this.passwordChangeFlag = passwordChangeFlag;
		this.profilePicPath = profilePicPath;
		this.panPicPath = panPicPath;
		this.otp = otp;
		this.team_coordinator = team_coordinator;
		this.team_lead = team_lead;
		this.misspunch_count = misspunch_count;
		this.blood_group = blood_group;
		this.primary_emergency_contact_number = primary_emergency_contact_number;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getPrimary_emergency_contact_number() {
		return primary_emergency_contact_number;
	}

	public void setPrimary_emergency_contact_number(String primary_emergency_contact_number) {
		this.primary_emergency_contact_number = primary_emergency_contact_number;
	}

	public String getMisspunch_count() {
		return misspunch_count;
	}

	public void setMisspunch_count(String misspunch_count) {
		this.misspunch_count = misspunch_count;
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
    public DisplayEmployessWithMissPunchEntity() {
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

    public String getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}

	public String getCandidate_type() {
		return candidate_type;
	}

	public void setCandidate_type(String candidate_type) {
		this.candidate_type = candidate_type;
	}

	public String getProbation_completed_date() {
		return probation_completed_date;
	}

	public void setProbation_completed_date(String probation_completed_date) {
		this.probation_completed_date = probation_completed_date;
	}

	public String getLast_working_date() {
		return last_working_date;
	}

	public void setLast_working_date(String last_working_date) {
		this.last_working_date = last_working_date;
	}

	public boolean isAttendance_link() {
		return attendance_link;
	}

	public void setAttendance_link(boolean attendance_link) {
		this.attendance_link = attendance_link;
	}

	@Override
	public String toString() {
		return "DisplayEmployessWithMissPunchEntity [id=" + id + ", empid=" + empid + ", deptId=" + deptId + ", roleId="
				+ roleId + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", dob=" + dob + ", doj=" + doj + ", adharNo=" + adharNo + ", panCard="
				+ panCard + ", gender=" + gender + ", password=" + password + ", deptName=" + deptName + ", roleName="
				+ roleName + ", fullName=" + fullName + ", linkedin_link=" + linkedin_link + ", twitter_link="
				+ twitter_link + ", passwordChangeFlag=" + passwordChangeFlag + ", profilePicPath=" + profilePicPath
				+ ", panPicPath=" + panPicPath + ", otp=" + otp + ", team_coordinator=" + team_coordinator
				+ ", team_lead=" + team_lead + ", misspunch_count=" + misspunch_count + ", blood_group=" + blood_group
				+ ", primary_emergency_contact_number=" + primary_emergency_contact_number + ", employee_type="
				+ employee_type + ", candidate_type=" + candidate_type + ", probation_completed_date="
				+ probation_completed_date + ", last_working_date=" + last_working_date + ", attendance_link="
				+ attendance_link + "]";
	}
}

