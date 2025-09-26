package com.example.mspl_connect.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_details")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empid;
    private String f_name;
    private String l_name;
    private String email;
    private String mobile_no;
    
    private int dept_id;
    private String role_id;
    private String address;
    private String dob;
    private String doj;
    private String adhar_no;
    private String pan_card;
    private String gender;
    private String password;
    private String profile_pic_path;
    private String pan_pic_path;
    private String linkedin_link;
    private String twitter_link;
    private String primary_emergency_contact_name;
    private String primary_emergency_contact_number;
    private String primary_emergency_contact_relation;
    private String secondary_emergency_contact_name;
    private String secondary_emergency_contact_number;
    private String secondary_emergency_contact_relation;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "empid")
    private Employee manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> subordinates;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empid=" + empid + ", f_name=" + f_name + ", l_name=" + l_name + ", email="
				+ email + ", mobile_no=" + mobile_no + ", dept_id=" + dept_id + ", role_id=" + role_id + ", address="
				+ address + ", dob=" + dob + ", doj=" + doj + ", adhar_no=" + adhar_no + ", pan_card=" + pan_card
				+ ", gender=" + gender + ", password=" + password + ", profile_pic_path=" + profile_pic_path
				+ ", pan_pic_path=" + pan_pic_path + ", linkedin_link=" + linkedin_link + ", twitter_link="
				+ twitter_link + ", primary_emergency_contact_name=" + primary_emergency_contact_name
				+ ", primary_emergency_contact_number=" + primary_emergency_contact_number
				+ ", primary_emergency_contact_relation=" + primary_emergency_contact_relation
				+ ", secondary_emergency_contact_name=" + secondary_emergency_contact_name
				+ ", secondary_emergency_contact_number=" + secondary_emergency_contact_number
				+ ", secondary_emergency_contact_relation=" + secondary_emergency_contact_relation + ", personal_email="
				+ personal_email + ", father_name=" + father_name + ", mother_name=" + mother_name + ", corr_address="
				+ corr_address + ", qualification_tenth=" + qualification_tenth + ", tenth_school_name="
				+ tenth_school_name + ", tenth_board_name=" + tenth_board_name + ", tenth_percentage="
				+ tenth_percentage + ", tenth_completed_date=" + tenth_completed_date + ", qualification_puc="
				+ qualification_puc + ", puc_college_name=" + puc_college_name + ", puc_board_name=" + puc_board_name
				+ ", puc_percentage=" + puc_percentage + ", puc_completed_date=" + puc_completed_date
				+ ", qualification_ug=" + qualification_ug + ", ug_college_name=" + ug_college_name
				+ ", ug_degree_name=" + ug_degree_name + ", ug_university_name=" + ug_university_name + ", ug_type="
				+ ug_type + ", ug_percentage=" + ug_percentage + ", ug_completed_date=" + ug_completed_date
				+ ", qualification_pg=" + qualification_pg + ", pg_college_name=" + pg_college_name
				+ ", pg_degree_name=" + pg_degree_name + ", pg_university_name=" + pg_university_name + ", pg_type="
				+ pg_type + ", pg_percentage=" + pg_percentage + ", pg_completed_date=" + pg_completed_date
				+ ", qualification_other=" + qualification_other + ", other_college_name=" + other_college_name
				+ ", other_degree_name=" + other_degree_name + ", other_university_name=" + other_university_name
				+ ", other_type=" + other_type + ", other_percentage=" + other_percentage + ", other_completed_date="
				+ other_completed_date + ", candidate_type=" + candidate_type + ", fresher_expected_salary="
				+ fresher_expected_salary + ", experience_total_years=" + experience_total_years
				+ ", experience_expected_salary=" + experience_expected_salary + ", manager=" + manager
				+ ", subordinates=" + subordinates + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
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

	public String getAdhar_no() {
		return adhar_no;
	}

	public void setAdhar_no(String adhar_no) {
		this.adhar_no = adhar_no;
	}

	public String getPan_card() {
		return pan_card;
	}

	public void setPan_card(String pan_card) {
		this.pan_card = pan_card;
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

	public String getProfile_pic_path() {
		return profile_pic_path;
	}

	public void setProfile_pic_path(String profile_pic_path) {
		this.profile_pic_path = profile_pic_path;
	}

	public String getPan_pic_path() {
		return pan_pic_path;
	}

	public void setPan_pic_path(String pan_pic_path) {
		this.pan_pic_path = pan_pic_path;
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

	public String getCandidate_type() {
		return candidate_type;
	}

	public void setCandidate_type(String candidate_type) {
		this.candidate_type = candidate_type;
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

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
    
}
