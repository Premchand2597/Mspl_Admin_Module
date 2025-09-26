package com.example.mspl_connect.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_details")
public class EmployeeDetailsEntity {
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

	    @Column(name = "password")
	    private String password;
	    
	    @Column(name = "linkedin_link")
	    private String linkedin_link;
	    
	    @Column(name = "twitter_link")
	    private String twitter_link;
	    

	    @Column(name = "alternative_saturday_effective_from")
	   	private String alternativeSaturdayEffectiveFrom;

	    
	  

		private String personal_email; 
	    private String father_name;
	    private String mother_name;
	    private String corr_address;
	    private String qualification_tenth;
	    private String tenth_school_name;
	    private String tenth_board_name;
	    private String tenth_percentage;
	    private String tenth_completed_date;
	    private String qualification_puc;
	    private String puc_college_name;
	    private String puc_board_name;
	    private String puc_percentage;
	    private String puc_completed_date;
	    private String qualification_ug;
	    private String ug_college_name;
	    private String ug_degree_name;
	    private String ug_university_name;
	    private String ug_type;
	    private String ug_percentage;
	    private String ug_completed_date;
	    private String qualification_pg;
	    private String pg_college_name;
	    private String pg_degree_name;
	    private String pg_university_name;
	    private String pg_type;
	    private String pg_percentage;
	    private String pg_completed_date;
	    private String qualification_other;
	    private String other_college_name;
	    private String other_degree_name;
	    private String other_university_name;
	    private String other_type;
	    private String other_percentage;
	    private String other_completed_date;
	    private String candidate_type;
	    private String fresher_expected_salary;
	    private String experience_total_years;
	    private String experience_expected_salary;
	    
	    private String sub_dept_name;
	    private String team_lead_name;
	    private String team_co_name;
	    private String offer_letter;
	    
	    private String primary_emergency_contact_name;
	    private String primary_emergency_contact_number;
	    private String primary_emergency_contact_relation;
	    
	    private String secondary_emergency_contact_name;
	    private String secondary_emergency_contact_number;
	    private String secondary_emergency_contact_relation;
	    
	    
	    
	    
	   /* @Column(name = "profile_pic_path")
		private String profilePicPath;
	    
	    @Column(name = "pan_pic_path")
		private String panPicPath;
	    
	    private String aadhar_pic;
	    //private String voter_id_pic;
	    private String tenth_pic;
	    private String puc_pic;
	    private String degree_pic;*/
	    
	    
	    public EmployeeDetailsEntity(int id, String empId, int deptId, int roleId, String firstName, String lastName,
				String email, String mobileNo, String address, String dob, String doj, String adharNo, String panCard,
				String gender, String password, String linkedin_link, String twitter_link,
				String alternativeSaturdayEffectiveFrom, String personal_email, String father_name, String mother_name,
				String corr_address, String qualification_tenth, String tenth_school_name, String tenth_board_name,
				String tenth_percentage, String tenth_completed_date, String qualification_puc, String puc_college_name,
				String puc_board_name, String puc_percentage, String puc_completed_date, String qualification_ug,
				String ug_college_name, String ug_degree_name, String ug_university_name, String ug_type,
				String ug_percentage, String ug_completed_date, String qualification_pg, String pg_college_name,
				String pg_degree_name, String pg_university_name, String pg_type, String pg_percentage,
				String pg_completed_date, String qualification_other, String other_college_name,
				String other_degree_name, String other_university_name, String other_type, String other_percentage,
				String other_completed_date, String candidate_type, String fresher_expected_salary,
				String experience_total_years, String experience_expected_salary, String sub_dept_name,
				String team_lead_name, String team_co_name, String offer_letter, String primary_emergency_contact_name,
				String primary_emergency_contact_number, String primary_emergency_contact_relation,
				String secondary_emergency_contact_name, String secondary_emergency_contact_number,
				String secondary_emergency_contact_relation, String profilePicPath, String panPicPath,
				String aadhar_pic, String tenth_pic, String puc_pic, String degree_pic, String current_salary,
				String probation_completed_date, String employee_type, String usertype, String otp) {
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
			this.alternativeSaturdayEffectiveFrom = alternativeSaturdayEffectiveFrom;
			this.personal_email = personal_email;
			this.father_name = father_name;
			this.mother_name = mother_name;
			this.corr_address = corr_address;
			this.qualification_tenth = qualification_tenth;
			this.tenth_school_name = tenth_school_name;
			this.tenth_board_name = tenth_board_name;
			this.tenth_percentage = tenth_percentage;
			this.tenth_completed_date = tenth_completed_date;
			this.qualification_puc = qualification_puc;
			this.puc_college_name = puc_college_name;
			this.puc_board_name = puc_board_name;
			this.puc_percentage = puc_percentage;
			this.puc_completed_date = puc_completed_date;
			this.qualification_ug = qualification_ug;
			this.ug_college_name = ug_college_name;
			this.ug_degree_name = ug_degree_name;
			this.ug_university_name = ug_university_name;
			this.ug_type = ug_type;
			this.ug_percentage = ug_percentage;
			this.ug_completed_date = ug_completed_date;
			this.qualification_pg = qualification_pg;
			this.pg_college_name = pg_college_name;
			this.pg_degree_name = pg_degree_name;
			this.pg_university_name = pg_university_name;
			this.pg_type = pg_type;
			this.pg_percentage = pg_percentage;
			this.pg_completed_date = pg_completed_date;
			this.qualification_other = qualification_other;
			this.other_college_name = other_college_name;
			this.other_degree_name = other_degree_name;
			this.other_university_name = other_university_name;
			this.other_type = other_type;
			this.other_percentage = other_percentage;
			this.other_completed_date = other_completed_date;
			this.candidate_type = candidate_type;
			this.fresher_expected_salary = fresher_expected_salary;
			this.experience_total_years = experience_total_years;
			this.experience_expected_salary = experience_expected_salary;
			this.sub_dept_name = sub_dept_name;
			this.team_lead_name = team_lead_name;
			this.team_co_name = team_co_name;
			this.offer_letter = offer_letter;
			this.primary_emergency_contact_name = primary_emergency_contact_name;
			this.primary_emergency_contact_number = primary_emergency_contact_number;
			this.primary_emergency_contact_relation = primary_emergency_contact_relation;
			this.secondary_emergency_contact_name = secondary_emergency_contact_name;
			this.secondary_emergency_contact_number = secondary_emergency_contact_number;
			this.secondary_emergency_contact_relation = secondary_emergency_contact_relation;
			this.profilePicPath = profilePicPath;
			this.panPicPath = panPicPath;
			this.aadhar_pic = aadhar_pic;
			this.tenth_pic = tenth_pic;
			this.puc_pic = puc_pic;
			this.degree_pic = degree_pic;
			this.current_salary = current_salary;
			this.probation_completed_date = probation_completed_date;
			this.employee_type = employee_type;
			this.usertype = usertype;
			this.otp = otp;
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

		@Lob
	    @Column(name = "profile_pic_path")
		private String profilePicPath;
		    
	    public String getOffer_letter() {
			return offer_letter;
		}

		public void setOffer_letter(String offer_letter) {
			this.offer_letter = offer_letter;
		}

		@Lob
	    @Column(name = "pan_pic_path")
	    private String panPicPath;
	    
	    @Lob
	    @Column(name = "aadhar_pic")
	    private String aadhar_pic;
	    
	    @Lob
	    private String tenth_pic;
	    
	    @Lob
	    private String puc_pic;
	    
	    @Lob
	    private String degree_pic;
	    
	    public String getAlternativeSaturdayEffectiveFrom() {
			return alternativeSaturdayEffectiveFrom;
		}

		public void setAlternativeSaturdayEffectiveFrom(String alternativeSaturdayEffectiveFrom) {
			this.alternativeSaturdayEffectiveFrom = alternativeSaturdayEffectiveFrom;
		}
		
	    private String current_salary;
	    private String probation_completed_date;
	    
	    private String employee_type;
	    
	    private String usertype;
	    private String otp;
	    

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
		
		/*public String getProfilePicPath() {
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
		}*/

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
			//System.out.println("/////"+deptId);
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


		public String getPersonal_email() {
			return personal_email;
		}

		public void setPersonal_email(String personal_email) {
			this.personal_email = personal_email;
		}

		public String getFather_name() {
			return father_name;
		}

		public void setFather_name(String father_name) {
			this.father_name = father_name;
		}

		public String getMother_name() {
			return mother_name;
		}

		public void setMother_name(String mother_name) {
			this.mother_name = mother_name;
		}

		public String getCorr_address() {
			return corr_address;
		}

		public void setCorr_address(String corr_address) {
			this.corr_address = corr_address;
		}

		public String getQualification_tenth() {
			return qualification_tenth;
		}

		public void setQualification_tenth(String qualification_tenth) {
			this.qualification_tenth = qualification_tenth;
		}

		public String getTenth_school_name() {
			return tenth_school_name;
		}

		public void setTenth_school_name(String tenth_school_name) {
			this.tenth_school_name = tenth_school_name;
		}

		public String getTenth_board_name() {
			return tenth_board_name;
		}

		public void setTenth_board_name(String tenth_board_name) {
			this.tenth_board_name = tenth_board_name;
		}

		public String getTenth_percentage() {
			return tenth_percentage;
		}

		public void setTenth_percentage(String tenth_percentage) {
			this.tenth_percentage = tenth_percentage;
		}

		public String getTenth_completed_date() {
			return tenth_completed_date;
		}

		public void setTenth_completed_date(String tenth_completed_date) {
			this.tenth_completed_date = tenth_completed_date;
		}

		public String getQualification_puc() {
			return qualification_puc;
		}

		public void setQualification_puc(String qualification_puc) {
			this.qualification_puc = qualification_puc;
		}

		public String getPuc_college_name() {
			return puc_college_name;
		}

		public void setPuc_college_name(String puc_college_name) {
			this.puc_college_name = puc_college_name;
		}

		public String getPuc_board_name() {
			return puc_board_name;
		}

		public void setPuc_board_name(String puc_board_name) {
			this.puc_board_name = puc_board_name;
		}

		public String getPuc_percentage() {
			return puc_percentage;
		}

		public void setPuc_percentage(String puc_percentage) {
			this.puc_percentage = puc_percentage;
		}

		public String getPuc_completed_date() {
			return puc_completed_date;
		}

		public void setPuc_completed_date(String puc_completed_date) {
			this.puc_completed_date = puc_completed_date;
		}

		public String getQualification_ug() {
			return qualification_ug;
		}

		public void setQualification_ug(String qualification_ug) {
			this.qualification_ug = qualification_ug;
		}

		public String getUg_college_name() {
			return ug_college_name;
		}

		public void setUg_college_name(String ug_college_name) {
			this.ug_college_name = ug_college_name;
		}

		public String getUg_degree_name() {
			return ug_degree_name;
		}

		public void setUg_degree_name(String ug_degree_name) {
			this.ug_degree_name = ug_degree_name;
		}

		public String getUg_university_name() {
			return ug_university_name;
		}

		public void setUg_university_name(String ug_university_name) {
			this.ug_university_name = ug_university_name;
		}

		public String getUg_type() {
			return ug_type;
		}

		public void setUg_type(String ug_type) {
			this.ug_type = ug_type;
		}

		public String getUg_percentage() {
			return ug_percentage;
		}

		public void setUg_percentage(String ug_percentage) {
			this.ug_percentage = ug_percentage;
		}

		public String getUg_completed_date() {
			return ug_completed_date;
		}

		public void setUg_completed_date(String ug_completed_date) {
			this.ug_completed_date = ug_completed_date;
		}

		public String getQualification_pg() {
			return qualification_pg;
		}

		public void setQualification_pg(String qualification_pg) {
			this.qualification_pg = qualification_pg;
		}

		public String getPg_college_name() {
			return pg_college_name;
		}

		public void setPg_college_name(String pg_college_name) {
			this.pg_college_name = pg_college_name;
		}

		public String getPg_degree_name() {
			return pg_degree_name;
		}

		public void setPg_degree_name(String pg_degree_name) {
			this.pg_degree_name = pg_degree_name;
		}

		public String getPg_university_name() {
			return pg_university_name;
		}

		public void setPg_university_name(String pg_university_name) {
			this.pg_university_name = pg_university_name;
		}

		public String getPg_type() {
			return pg_type;
		}

		public void setPg_type(String pg_type) {
			this.pg_type = pg_type;
		}

		public String getPg_percentage() {
			return pg_percentage;
		}

		public void setPg_percentage(String pg_percentage) {
			this.pg_percentage = pg_percentage;
		}

		public String getPg_completed_date() {
			return pg_completed_date;
		}

		public void setPg_completed_date(String pg_completed_date) {
			this.pg_completed_date = pg_completed_date;
		}

		public String getQualification_other() {
			return qualification_other;
		}

		public void setQualification_other(String qualification_other) {
			this.qualification_other = qualification_other;
		}

		public String getOther_college_name() {
			return other_college_name;
		}

		public void setOther_college_name(String other_college_name) {
			this.other_college_name = other_college_name;
		}

		public String getOther_degree_name() {
			return other_degree_name;
		}

		public void setOther_degree_name(String other_degree_name) {
			this.other_degree_name = other_degree_name;
		}

		public String getOther_university_name() {
			return other_university_name;
		}

		public void setOther_university_name(String other_university_name) {
			this.other_university_name = other_university_name;
		}

		public String getOther_type() {
			return other_type;
		}

		public void setOther_type(String other_type) {
			this.other_type = other_type;
		}

		public String getOther_percentage() {
			return other_percentage;
		}

		public void setOther_percentage(String other_percentage) {
			this.other_percentage = other_percentage;
		}

		public String getOther_completed_date() {
			return other_completed_date;
		}

		public void setOther_completed_date(String other_completed_date) {
			this.other_completed_date = other_completed_date;
		}


		public String getFresher_expected_salary() {
			return fresher_expected_salary;
		}

		public void setFresher_expected_salary(String fresher_expected_salary) {
			this.fresher_expected_salary = fresher_expected_salary;
		}


		public String getExperience_total_years() {
			return experience_total_years;
		}

		public void setExperience_total_years(String experience_total_years) {
			this.experience_total_years = experience_total_years;
		}

		public String getExperience_expected_salary() {
			return experience_expected_salary;
		}

		public void setExperience_expected_salary(String experience_expected_salary) {
			this.experience_expected_salary = experience_expected_salary;
		}

		public String getCandidate_type() {
			return candidate_type;
		}

		public void setCandidate_type(String candidate_type) {
			this.candidate_type = candidate_type;
		}

		/*public String getAadhar_pic() {
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
		}*/
		
		

		public String getCurrent_salary() {
			return current_salary;
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

		public void setCurrent_salary(String current_salary) {
			this.current_salary = current_salary;
		}

		public String getSub_dept_name() {
			return sub_dept_name;
		}

		public void setSub_dept_name(String sub_dept_name) {
			this.sub_dept_name = sub_dept_name;
		}

		public String getTeam_lead_name() {
			return team_lead_name;
		}

		public void setTeam_lead_name(String team_lead_name) {
			this.team_lead_name = team_lead_name;
		}

		public String getTeam_co_name() {
			return team_co_name;
		}

		public void setTeam_co_name(String team_co_name) {
			this.team_co_name = team_co_name;
		}

		public String getProbation_completed_date() {
			return probation_completed_date;
		}

		public void setProbation_completed_date(String probation_completed_date) {
			this.probation_completed_date = probation_completed_date;
		}

		public String getEmployee_type() {
			return employee_type;
		}

		public void setEmployee_type(String employee_type) {
			this.employee_type = employee_type;
		}

		public String getUsertype() {
			return usertype;
		}

		public void setUsertype(String usertype) {
			this.usertype = usertype;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		@Override
		public String toString() {
			return "EmployeeDetailsEntity [id=" + id + ", empId=" + empId + ", deptId=" + deptId + ", roleId=" + roleId
					+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobileNo="
					+ mobileNo + ", address=" + address + ", dob=" + dob + ", doj=" + doj + ", adharNo=" + adharNo
					+ ", panCard=" + panCard + ", gender=" + gender + ", password=" + password + ", linkedin_link="
					+ linkedin_link + ", twitter_link=" + twitter_link + ", personal_email=" + personal_email
					+ ", father_name=" + father_name + ", mother_name=" + mother_name + ", corr_address=" + corr_address
					+ ", qualification_tenth=" + qualification_tenth + ", tenth_school_name=" + tenth_school_name
					+ ", tenth_board_name=" + tenth_board_name + ", tenth_percentage=" + tenth_percentage
					+ ", tenth_completed_date=" + tenth_completed_date + ", qualification_puc=" + qualification_puc
					+ ", puc_college_name=" + puc_college_name + ", puc_board_name=" + puc_board_name
					+ ", puc_percentage=" + puc_percentage + ", puc_completed_date=" + puc_completed_date
					+ ", qualification_ug=" + qualification_ug + ", ug_college_name=" + ug_college_name
					+ ", ug_degree_name=" + ug_degree_name + ", ug_university_name=" + ug_university_name + ", ug_type="
					+ ug_type + ", ug_percentage=" + ug_percentage + ", ug_completed_date=" + ug_completed_date
					+ ", qualification_pg=" + qualification_pg + ", pg_college_name=" + pg_college_name
					+ ", pg_degree_name=" + pg_degree_name + ", pg_university_name=" + pg_university_name + ", pg_type="
					+ pg_type + ", pg_percentage=" + pg_percentage + ", pg_completed_date=" + pg_completed_date
					+ ", qualification_other=" + qualification_other + ", other_college_name=" + other_college_name
					+ ", other_degree_name=" + other_degree_name + ", other_university_name=" + other_university_name
					+ ", other_type=" + other_type + ", other_percentage=" + other_percentage
					+ ", other_completed_date=" + other_completed_date + ", candidate_type=" + candidate_type
					+ ", fresher_expected_salary=" + fresher_expected_salary + ", experience_total_years="
					+ experience_total_years + ", experience_expected_salary=" + experience_expected_salary
					+ ", sub_dept_name=" + sub_dept_name + ", team_lead_name=" + team_lead_name + ", team_co_name="
					+ team_co_name + ", profilePicPath=" + profilePicPath + ", panPicPath=" + panPicPath
					+ ", aadhar_pic=" + aadhar_pic + ", tenth_pic=" + tenth_pic + ", puc_pic=" + puc_pic
					+ ", degree_pic=" + degree_pic + ", current_salary=" + current_salary
					+ ", probation_completed_date=" + probation_completed_date + ", employee_type=" + employee_type
					+ ", usertype=" + usertype + ", otp=" + otp + "]";
		}

		public EmployeeDetailsEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
