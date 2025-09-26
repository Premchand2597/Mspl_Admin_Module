package com.example.mspl_connect.AdminEntity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class MoatCandidateDataWithTestTypeStatus_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String first_name;
	private String last_name;
	private String gender;
	private String email;
	private String password;
	private String mobile_number;
	private String dob;
	@Column(name = "inserted_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String inserted_at;
	private String languages;
	private String action;
	private String candidate_action_reason;
	private String aadhar_number;
	private String father_name;
	private String father_designation;
	private String mother_name;
	private String mother_designation;
	private String marital_status_value;
	private String corresponding_address;
	private String permanent_address;
	private String qualification1;
	private String degree_name1;
	private String university_name1;
	private String percentage1;
	private String year_select1;
	private String qualification2;
	private String degree_name2;
	private String university_name2;
	private String percentage2;
	private String year_select2;
	private String qualification3;
	private String degree_name3;
	private String university_name3;
	private String percentage3;
	private String year_select3;
	private String qualification4;
	private String degree_name4;
	private String university_name4;
	private String percentage4;
	private String year_select4;
	private String qualification5;
	private String degree_name5;
	private String university_name5;
	private String percentage5;
	private String year_select5;
	private String experience;
	private String previous_comp_name1;
	private String year_of_exp1;
	private String candidate_expected_salary_in_lpa;
	private String reference_name;
	private String reference_source_name;
	private String language1;
	private String language_proficiency1;
	private String language2;
	private String language_proficiency2;
	private String language3;
	private String language_proficiency3;
	private String college_name1;
	private String education_completed_type1;
	private String college_name2;
	private String education_completed_type2;
	private String college_name3;
	private String education_completed_type3;
	private String college_name4;
	private String education_completed_type4;
	private String college_name5;
	private String education_completed_type5;
	private String academic_gap;
	private String academic_gap_qualification_name;
	private String academic_gap_type;
	private String academic_gap_number;
	private String academic_gap_reason;
	private String interviewed_by_us;
	private String candidate_join_time;
	private String why_should_hire_you_reason;
	private String exp_company_left_reason;
	private String previous_comp_name2;
	private String previous_comp_name3;
	private String previous_comp_name4;
	private String previous_comp_name5;
	private String exp_from_date1;
	private String exp_from_date2;
	private String exp_from_date3;
	private String exp_from_date4;
	private String exp_from_date5;
	private String exp_to_date1;
	private String exp_to_date2;
	private String exp_to_date3;
	private String exp_to_date4;
	private String exp_to_date5;
	private String exp_designation_name1;
	private String exp_designation_name2;
	private String exp_designation_name3;
	private String exp_designation_name4;
	private String exp_designation_name5;
	private String exp_inhand_salary1;
	private String exp_inhand_salary2;
	private String exp_inhand_salary3;
	private String exp_inhand_salary4;
	private String exp_inhand_salary5;
	private String exp_notice_period_type1;
	private String exp_notice_period_type2;
	private String exp_notice_period_type3;
	private String exp_notice_period_type4;
	private String exp_notice_period_type5;
	private String external_course;
	private String external_course_name;
	private String external_course_start_date;
	private String external_course_completed_date;
	private String external_course_certification_name;
	private String candidate_police_record;
	private String candidate_police_record_reason;
	private String candidate_specially_abled;
	private String candidate_specially_abled_reason;
	private String candidate_illness;
	private String candidate_illness_reason;
	private String candidate_smoke;
	private String candidate_alcohol;
	private String candidate_imported_flag_to_employee_table;
	private int candidate_status_flag;
	private String f2f_total_marks;
	private String f2f_assigned_marks;
	private String f2f_remarks;
	private String hr_total_marks;
	private String hr_assigned_marks;
	private String hr_remarks;

	@Lob
	private byte[] resume_doc;
	    
    @Lob
    private byte[] aadhar_doc;
    
    private String profile_pic_path;
	private String pan_pic_path;
	private String aadhar_pic;
	private String voter_id_pic;
	private String tenth_pic;
	private String puc_pic;
	private String degree_pic;
	private String test_type;
	private String first_round_result;
	private String f2f_round_result;
	private String hr_round_result;
	private String f2f_round_invite_date;
	private String f2f_round_invite_time;
	private String hr_round_invite_date;
	private String hr_round_invite_tme;
	
	private String candidate_response_for_first_ctc_offer;
	private String candidate_response_for_second_ctc_offer;
	private String candidate_response_for_third_ctc_offer;
	
	private String final_status_viewed_flag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getInserted_at() {
		return inserted_at;
	}
	public void setInserted_at(String inserted_at) {
		this.inserted_at = inserted_at;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCandidate_action_reason() {
		return candidate_action_reason;
	}
	public void setCandidate_action_reason(String candidate_action_reason) {
		this.candidate_action_reason = candidate_action_reason;
	}
	public String getAadhar_number() {
		return aadhar_number;
	}
	public void setAadhar_number(String aadhar_number) {
		this.aadhar_number = aadhar_number;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getFather_designation() {
		return father_designation;
	}
	public void setFather_designation(String father_designation) {
		this.father_designation = father_designation;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getMother_designation() {
		return mother_designation;
	}
	public void setMother_designation(String mother_designation) {
		this.mother_designation = mother_designation;
	}
	public String getMarital_status_value() {
		return marital_status_value;
	}
	public void setMarital_status_value(String marital_status_value) {
		this.marital_status_value = marital_status_value;
	}
	public String getCorresponding_address() {
		return corresponding_address;
	}
	public void setCorresponding_address(String corresponding_address) {
		this.corresponding_address = corresponding_address;
	}
	public String getPermanent_address() {
		return permanent_address;
	}
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}
	public String getQualification1() {
		return qualification1;
	}
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}
	public String getDegree_name1() {
		return degree_name1;
	}
	public void setDegree_name1(String degree_name1) {
		this.degree_name1 = degree_name1;
	}
	public String getUniversity_name1() {
		return university_name1;
	}
	public void setUniversity_name1(String university_name1) {
		this.university_name1 = university_name1;
	}
	public String getPercentage1() {
		return percentage1;
	}
	public void setPercentage1(String percentage1) {
		this.percentage1 = percentage1;
	}
	public String getYear_select1() {
		return year_select1;
	}
	public void setYear_select1(String year_select1) {
		this.year_select1 = year_select1;
	}
	public String getQualification2() {
		return qualification2;
	}
	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}
	public String getDegree_name2() {
		return degree_name2;
	}
	public void setDegree_name2(String degree_name2) {
		this.degree_name2 = degree_name2;
	}
	public String getUniversity_name2() {
		return university_name2;
	}
	public void setUniversity_name2(String university_name2) {
		this.university_name2 = university_name2;
	}
	public String getPercentage2() {
		return percentage2;
	}
	public void setPercentage2(String percentage2) {
		this.percentage2 = percentage2;
	}
	public String getYear_select2() {
		return year_select2;
	}
	public void setYear_select2(String year_select2) {
		this.year_select2 = year_select2;
	}
	public String getQualification3() {
		return qualification3;
	}
	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}
	public String getDegree_name3() {
		return degree_name3;
	}
	public void setDegree_name3(String degree_name3) {
		this.degree_name3 = degree_name3;
	}
	public String getUniversity_name3() {
		return university_name3;
	}
	public void setUniversity_name3(String university_name3) {
		this.university_name3 = university_name3;
	}
	public String getPercentage3() {
		return percentage3;
	}
	public void setPercentage3(String percentage3) {
		this.percentage3 = percentage3;
	}
	public String getYear_select3() {
		return year_select3;
	}
	public void setYear_select3(String year_select3) {
		this.year_select3 = year_select3;
	}
	public String getQualification4() {
		return qualification4;
	}
	public void setQualification4(String qualification4) {
		this.qualification4 = qualification4;
	}
	public String getDegree_name4() {
		return degree_name4;
	}
	public void setDegree_name4(String degree_name4) {
		this.degree_name4 = degree_name4;
	}
	public String getUniversity_name4() {
		return university_name4;
	}
	public void setUniversity_name4(String university_name4) {
		this.university_name4 = university_name4;
	}
	public String getPercentage4() {
		return percentage4;
	}
	public void setPercentage4(String percentage4) {
		this.percentage4 = percentage4;
	}
	public String getYear_select4() {
		return year_select4;
	}
	public void setYear_select4(String year_select4) {
		this.year_select4 = year_select4;
	}
	public String getQualification5() {
		return qualification5;
	}
	public void setQualification5(String qualification5) {
		this.qualification5 = qualification5;
	}
	public String getDegree_name5() {
		return degree_name5;
	}
	public void setDegree_name5(String degree_name5) {
		this.degree_name5 = degree_name5;
	}
	public String getUniversity_name5() {
		return university_name5;
	}
	public void setUniversity_name5(String university_name5) {
		this.university_name5 = university_name5;
	}
	public String getPercentage5() {
		return percentage5;
	}
	public void setPercentage5(String percentage5) {
		this.percentage5 = percentage5;
	}
	public String getYear_select5() {
		return year_select5;
	}
	public void setYear_select5(String year_select5) {
		this.year_select5 = year_select5;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getPrevious_comp_name1() {
		return previous_comp_name1;
	}
	public void setPrevious_comp_name1(String previous_comp_name1) {
		this.previous_comp_name1 = previous_comp_name1;
	}
	public String getYear_of_exp1() {
		return year_of_exp1;
	}
	public void setYear_of_exp1(String year_of_exp1) {
		this.year_of_exp1 = year_of_exp1;
	}
	public String getCandidate_expected_salary_in_lpa() {
		return candidate_expected_salary_in_lpa;
	}
	public void setCandidate_expected_salary_in_lpa(String candidate_expected_salary_in_lpa) {
		this.candidate_expected_salary_in_lpa = candidate_expected_salary_in_lpa;
	}
	public String getReference_name() {
		return reference_name;
	}
	public void setReference_name(String reference_name) {
		this.reference_name = reference_name;
	}
	public String getReference_source_name() {
		return reference_source_name;
	}
	public void setReference_source_name(String reference_source_name) {
		this.reference_source_name = reference_source_name;
	}
	public String getLanguage1() {
		return language1;
	}
	public void setLanguage1(String language1) {
		this.language1 = language1;
	}
	public String getLanguage_proficiency1() {
		return language_proficiency1;
	}
	public void setLanguage_proficiency1(String language_proficiency1) {
		this.language_proficiency1 = language_proficiency1;
	}
	public String getLanguage2() {
		return language2;
	}
	public void setLanguage2(String language2) {
		this.language2 = language2;
	}
	public String getLanguage_proficiency2() {
		return language_proficiency2;
	}
	public void setLanguage_proficiency2(String language_proficiency2) {
		this.language_proficiency2 = language_proficiency2;
	}
	public String getLanguage3() {
		return language3;
	}
	public void setLanguage3(String language3) {
		this.language3 = language3;
	}
	public String getLanguage_proficiency3() {
		return language_proficiency3;
	}
	public void setLanguage_proficiency3(String language_proficiency3) {
		this.language_proficiency3 = language_proficiency3;
	}
	public String getCollege_name1() {
		return college_name1;
	}
	public void setCollege_name1(String college_name1) {
		this.college_name1 = college_name1;
	}
	public String getEducation_completed_type1() {
		return education_completed_type1;
	}
	public void setEducation_completed_type1(String education_completed_type1) {
		this.education_completed_type1 = education_completed_type1;
	}
	public String getCollege_name2() {
		return college_name2;
	}
	public void setCollege_name2(String college_name2) {
		this.college_name2 = college_name2;
	}
	public String getEducation_completed_type2() {
		return education_completed_type2;
	}
	public void setEducation_completed_type2(String education_completed_type2) {
		this.education_completed_type2 = education_completed_type2;
	}
	public String getCollege_name3() {
		return college_name3;
	}
	public void setCollege_name3(String college_name3) {
		this.college_name3 = college_name3;
	}
	public String getEducation_completed_type3() {
		return education_completed_type3;
	}
	public void setEducation_completed_type3(String education_completed_type3) {
		this.education_completed_type3 = education_completed_type3;
	}
	public String getCollege_name4() {
		return college_name4;
	}
	public void setCollege_name4(String college_name4) {
		this.college_name4 = college_name4;
	}
	public String getEducation_completed_type4() {
		return education_completed_type4;
	}
	public void setEducation_completed_type4(String education_completed_type4) {
		this.education_completed_type4 = education_completed_type4;
	}
	public String getCollege_name5() {
		return college_name5;
	}
	public void setCollege_name5(String college_name5) {
		this.college_name5 = college_name5;
	}
	public String getEducation_completed_type5() {
		return education_completed_type5;
	}
	public void setEducation_completed_type5(String education_completed_type5) {
		this.education_completed_type5 = education_completed_type5;
	}
	public String getAcademic_gap() {
		return academic_gap;
	}
	public void setAcademic_gap(String academic_gap) {
		this.academic_gap = academic_gap;
	}
	public String getAcademic_gap_qualification_name() {
		return academic_gap_qualification_name;
	}
	public void setAcademic_gap_qualification_name(String academic_gap_qualification_name) {
		this.academic_gap_qualification_name = academic_gap_qualification_name;
	}
	public String getAcademic_gap_type() {
		return academic_gap_type;
	}
	public void setAcademic_gap_type(String academic_gap_type) {
		this.academic_gap_type = academic_gap_type;
	}
	public String getAcademic_gap_number() {
		return academic_gap_number;
	}
	public void setAcademic_gap_number(String academic_gap_number) {
		this.academic_gap_number = academic_gap_number;
	}
	public String getAcademic_gap_reason() {
		return academic_gap_reason;
	}
	public void setAcademic_gap_reason(String academic_gap_reason) {
		this.academic_gap_reason = academic_gap_reason;
	}
	public String getInterviewed_by_us() {
		return interviewed_by_us;
	}
	public void setInterviewed_by_us(String interviewed_by_us) {
		this.interviewed_by_us = interviewed_by_us;
	}
	public String getCandidate_join_time() {
		return candidate_join_time;
	}
	public void setCandidate_join_time(String candidate_join_time) {
		this.candidate_join_time = candidate_join_time;
	}
	public String getWhy_should_hire_you_reason() {
		return why_should_hire_you_reason;
	}
	public void setWhy_should_hire_you_reason(String why_should_hire_you_reason) {
		this.why_should_hire_you_reason = why_should_hire_you_reason;
	}
	public String getExp_company_left_reason() {
		return exp_company_left_reason;
	}
	public void setExp_company_left_reason(String exp_company_left_reason) {
		this.exp_company_left_reason = exp_company_left_reason;
	}
	public String getPrevious_comp_name2() {
		return previous_comp_name2;
	}
	public void setPrevious_comp_name2(String previous_comp_name2) {
		this.previous_comp_name2 = previous_comp_name2;
	}
	public String getPrevious_comp_name3() {
		return previous_comp_name3;
	}
	public void setPrevious_comp_name3(String previous_comp_name3) {
		this.previous_comp_name3 = previous_comp_name3;
	}
	public String getPrevious_comp_name4() {
		return previous_comp_name4;
	}
	public void setPrevious_comp_name4(String previous_comp_name4) {
		this.previous_comp_name4 = previous_comp_name4;
	}
	public String getPrevious_comp_name5() {
		return previous_comp_name5;
	}
	public void setPrevious_comp_name5(String previous_comp_name5) {
		this.previous_comp_name5 = previous_comp_name5;
	}
	public String getExp_from_date1() {
		return exp_from_date1;
	}
	public void setExp_from_date1(String exp_from_date1) {
		this.exp_from_date1 = exp_from_date1;
	}
	public String getExp_from_date2() {
		return exp_from_date2;
	}
	public void setExp_from_date2(String exp_from_date2) {
		this.exp_from_date2 = exp_from_date2;
	}
	public String getExp_from_date3() {
		return exp_from_date3;
	}
	public void setExp_from_date3(String exp_from_date3) {
		this.exp_from_date3 = exp_from_date3;
	}
	public String getExp_from_date4() {
		return exp_from_date4;
	}
	public void setExp_from_date4(String exp_from_date4) {
		this.exp_from_date4 = exp_from_date4;
	}
	public String getExp_from_date5() {
		return exp_from_date5;
	}
	public void setExp_from_date5(String exp_from_date5) {
		this.exp_from_date5 = exp_from_date5;
	}
	public String getExp_to_date1() {
		return exp_to_date1;
	}
	public void setExp_to_date1(String exp_to_date1) {
		this.exp_to_date1 = exp_to_date1;
	}
	public String getExp_to_date2() {
		return exp_to_date2;
	}
	public void setExp_to_date2(String exp_to_date2) {
		this.exp_to_date2 = exp_to_date2;
	}
	public String getExp_to_date3() {
		return exp_to_date3;
	}
	public void setExp_to_date3(String exp_to_date3) {
		this.exp_to_date3 = exp_to_date3;
	}
	public String getExp_to_date4() {
		return exp_to_date4;
	}
	public void setExp_to_date4(String exp_to_date4) {
		this.exp_to_date4 = exp_to_date4;
	}
	public String getExp_to_date5() {
		return exp_to_date5;
	}
	public void setExp_to_date5(String exp_to_date5) {
		this.exp_to_date5 = exp_to_date5;
	}
	public String getExp_designation_name1() {
		return exp_designation_name1;
	}
	public void setExp_designation_name1(String exp_designation_name1) {
		this.exp_designation_name1 = exp_designation_name1;
	}
	public String getExp_designation_name2() {
		return exp_designation_name2;
	}
	public void setExp_designation_name2(String exp_designation_name2) {
		this.exp_designation_name2 = exp_designation_name2;
	}
	public String getExp_designation_name3() {
		return exp_designation_name3;
	}
	public void setExp_designation_name3(String exp_designation_name3) {
		this.exp_designation_name3 = exp_designation_name3;
	}
	public String getExp_designation_name4() {
		return exp_designation_name4;
	}
	public void setExp_designation_name4(String exp_designation_name4) {
		this.exp_designation_name4 = exp_designation_name4;
	}
	public String getExp_designation_name5() {
		return exp_designation_name5;
	}
	public void setExp_designation_name5(String exp_designation_name5) {
		this.exp_designation_name5 = exp_designation_name5;
	}
	public String getExp_inhand_salary1() {
		return exp_inhand_salary1;
	}
	public void setExp_inhand_salary1(String exp_inhand_salary1) {
		this.exp_inhand_salary1 = exp_inhand_salary1;
	}
	public String getExp_inhand_salary2() {
		return exp_inhand_salary2;
	}
	public void setExp_inhand_salary2(String exp_inhand_salary2) {
		this.exp_inhand_salary2 = exp_inhand_salary2;
	}
	public String getExp_inhand_salary3() {
		return exp_inhand_salary3;
	}
	public void setExp_inhand_salary3(String exp_inhand_salary3) {
		this.exp_inhand_salary3 = exp_inhand_salary3;
	}
	public String getExp_inhand_salary4() {
		return exp_inhand_salary4;
	}
	public void setExp_inhand_salary4(String exp_inhand_salary4) {
		this.exp_inhand_salary4 = exp_inhand_salary4;
	}
	public String getExp_inhand_salary5() {
		return exp_inhand_salary5;
	}
	public void setExp_inhand_salary5(String exp_inhand_salary5) {
		this.exp_inhand_salary5 = exp_inhand_salary5;
	}
	public String getExp_notice_period_type1() {
		return exp_notice_period_type1;
	}
	public void setExp_notice_period_type1(String exp_notice_period_type1) {
		this.exp_notice_period_type1 = exp_notice_period_type1;
	}
	public String getExp_notice_period_type2() {
		return exp_notice_period_type2;
	}
	public void setExp_notice_period_type2(String exp_notice_period_type2) {
		this.exp_notice_period_type2 = exp_notice_period_type2;
	}
	public String getExp_notice_period_type3() {
		return exp_notice_period_type3;
	}
	public void setExp_notice_period_type3(String exp_notice_period_type3) {
		this.exp_notice_period_type3 = exp_notice_period_type3;
	}
	public String getExp_notice_period_type4() {
		return exp_notice_period_type4;
	}
	public void setExp_notice_period_type4(String exp_notice_period_type4) {
		this.exp_notice_period_type4 = exp_notice_period_type4;
	}
	public String getExp_notice_period_type5() {
		return exp_notice_period_type5;
	}
	public void setExp_notice_period_type5(String exp_notice_period_type5) {
		this.exp_notice_period_type5 = exp_notice_period_type5;
	}
	public String getExternal_course() {
		return external_course;
	}
	public void setExternal_course(String external_course) {
		this.external_course = external_course;
	}
	public String getExternal_course_name() {
		return external_course_name;
	}
	public void setExternal_course_name(String external_course_name) {
		this.external_course_name = external_course_name;
	}
	public String getExternal_course_start_date() {
		return external_course_start_date;
	}
	public void setExternal_course_start_date(String external_course_start_date) {
		this.external_course_start_date = external_course_start_date;
	}
	public String getExternal_course_completed_date() {
		return external_course_completed_date;
	}
	public void setExternal_course_completed_date(String external_course_completed_date) {
		this.external_course_completed_date = external_course_completed_date;
	}
	public String getExternal_course_certification_name() {
		return external_course_certification_name;
	}
	public void setExternal_course_certification_name(String external_course_certification_name) {
		this.external_course_certification_name = external_course_certification_name;
	}
	public String getCandidate_police_record() {
		return candidate_police_record;
	}
	public void setCandidate_police_record(String candidate_police_record) {
		this.candidate_police_record = candidate_police_record;
	}
	public String getCandidate_police_record_reason() {
		return candidate_police_record_reason;
	}
	public void setCandidate_police_record_reason(String candidate_police_record_reason) {
		this.candidate_police_record_reason = candidate_police_record_reason;
	}
	public String getCandidate_specially_abled() {
		return candidate_specially_abled;
	}
	public void setCandidate_specially_abled(String candidate_specially_abled) {
		this.candidate_specially_abled = candidate_specially_abled;
	}
	public String getCandidate_specially_abled_reason() {
		return candidate_specially_abled_reason;
	}
	public void setCandidate_specially_abled_reason(String candidate_specially_abled_reason) {
		this.candidate_specially_abled_reason = candidate_specially_abled_reason;
	}
	public String getCandidate_illness() {
		return candidate_illness;
	}
	public void setCandidate_illness(String candidate_illness) {
		this.candidate_illness = candidate_illness;
	}
	public String getCandidate_illness_reason() {
		return candidate_illness_reason;
	}
	public void setCandidate_illness_reason(String candidate_illness_reason) {
		this.candidate_illness_reason = candidate_illness_reason;
	}
	public String getCandidate_smoke() {
		return candidate_smoke;
	}
	public void setCandidate_smoke(String candidate_smoke) {
		this.candidate_smoke = candidate_smoke;
	}
	public String getCandidate_alcohol() {
		return candidate_alcohol;
	}
	public void setCandidate_alcohol(String candidate_alcohol) {
		this.candidate_alcohol = candidate_alcohol;
	}
	public String getCandidate_imported_flag_to_employee_table() {
		return candidate_imported_flag_to_employee_table;
	}
	public void setCandidate_imported_flag_to_employee_table(String candidate_imported_flag_to_employee_table) {
		this.candidate_imported_flag_to_employee_table = candidate_imported_flag_to_employee_table;
	}
	public int getCandidate_status_flag() {
		return candidate_status_flag;
	}
	public void setCandidate_status_flag(int candidate_status_flag) {
		this.candidate_status_flag = candidate_status_flag;
	}
	public String getF2f_total_marks() {
		return f2f_total_marks;
	}
	public void setF2f_total_marks(String f2f_total_marks) {
		this.f2f_total_marks = f2f_total_marks;
	}
	public String getF2f_assigned_marks() {
		return f2f_assigned_marks;
	}
	public void setF2f_assigned_marks(String f2f_assigned_marks) {
		this.f2f_assigned_marks = f2f_assigned_marks;
	}
	public String getF2f_remarks() {
		return f2f_remarks;
	}
	public void setF2f_remarks(String f2f_remarks) {
		this.f2f_remarks = f2f_remarks;
	}
	public String getHr_total_marks() {
		return hr_total_marks;
	}
	public void setHr_total_marks(String hr_total_marks) {
		this.hr_total_marks = hr_total_marks;
	}
	public String getHr_assigned_marks() {
		return hr_assigned_marks;
	}
	public void setHr_assigned_marks(String hr_assigned_marks) {
		this.hr_assigned_marks = hr_assigned_marks;
	}
	public String getHr_remarks() {
		return hr_remarks;
	}
	public void setHr_remarks(String hr_remarks) {
		this.hr_remarks = hr_remarks;
	}
	public byte[] getResume_doc() {
		return resume_doc;
	}
	public void setResume_doc(byte[] resume_doc) {
		this.resume_doc = resume_doc;
	}
	public byte[] getAadhar_doc() {
		return aadhar_doc;
	}
	public void setAadhar_doc(byte[] aadhar_doc) {
		this.aadhar_doc = aadhar_doc;
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
	public String getAadhar_pic() {
		return aadhar_pic;
	}
	public void setAadhar_pic(String aadhar_pic) {
		this.aadhar_pic = aadhar_pic;
	}
	public String getVoter_id_pic() {
		return voter_id_pic;
	}
	public void setVoter_id_pic(String voter_id_pic) {
		this.voter_id_pic = voter_id_pic;
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
	public String getTest_type() {
		return test_type;
	}
	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}
	public String getFirst_round_result() {
		return first_round_result;
	}
	public void setFirst_round_result(String first_round_result) {
		this.first_round_result = first_round_result;
	}
	public String getF2f_round_result() {
		return f2f_round_result;
	}
	public void setF2f_round_result(String f2f_round_result) {
		this.f2f_round_result = f2f_round_result;
	}
	public String getHr_round_result() {
		return hr_round_result;
	}
	public void setHr_round_result(String hr_round_result) {
		this.hr_round_result = hr_round_result;
	}
	public String getF2f_round_invite_date() {
		return f2f_round_invite_date;
	}
	public void setF2f_round_invite_date(String f2f_round_invite_date) {
		this.f2f_round_invite_date = f2f_round_invite_date;
	}
	public String getF2f_round_invite_time() {
		return f2f_round_invite_time;
	}
	public void setF2f_round_invite_time(String f2f_round_invite_time) {
		this.f2f_round_invite_time = f2f_round_invite_time;
	}
	public String getHr_round_invite_date() {
		return hr_round_invite_date;
	}
	public void setHr_round_invite_date(String hr_round_invite_date) {
		this.hr_round_invite_date = hr_round_invite_date;
	}
	public String getHr_round_invite_tme() {
		return hr_round_invite_tme;
	}
	public void setHr_round_invite_tme(String hr_round_invite_tme) {
		this.hr_round_invite_tme = hr_round_invite_tme;
	}
	public String getCandidate_response_for_first_ctc_offer() {
		return candidate_response_for_first_ctc_offer;
	}
	public void setCandidate_response_for_first_ctc_offer(String candidate_response_for_first_ctc_offer) {
		this.candidate_response_for_first_ctc_offer = candidate_response_for_first_ctc_offer;
	}
	public String getCandidate_response_for_second_ctc_offer() {
		return candidate_response_for_second_ctc_offer;
	}
	public void setCandidate_response_for_second_ctc_offer(String candidate_response_for_second_ctc_offer) {
		this.candidate_response_for_second_ctc_offer = candidate_response_for_second_ctc_offer;
	}
	public String getCandidate_response_for_third_ctc_offer() {
		return candidate_response_for_third_ctc_offer;
	}
	public void setCandidate_response_for_third_ctc_offer(String candidate_response_for_third_ctc_offer) {
		this.candidate_response_for_third_ctc_offer = candidate_response_for_third_ctc_offer;
	}
	public String getFinal_status_viewed_flag() {
		return final_status_viewed_flag;
	}
	public void setFinal_status_viewed_flag(String final_status_viewed_flag) {
		this.final_status_viewed_flag = final_status_viewed_flag;
	}
	@Override
	public String toString() {
		return "MoatCandidateDataWithTestTypeStatus_Entity [id=" + id + ", first_name=" + first_name + ", last_name="
				+ last_name + ", gender=" + gender + ", email=" + email + ", password=" + password + ", mobile_number="
				+ mobile_number + ", dob=" + dob + ", inserted_at=" + inserted_at + ", languages=" + languages
				+ ", action=" + action + ", candidate_action_reason=" + candidate_action_reason + ", aadhar_number="
				+ aadhar_number + ", father_name=" + father_name + ", father_designation=" + father_designation
				+ ", mother_name=" + mother_name + ", mother_designation=" + mother_designation
				+ ", marital_status_value=" + marital_status_value + ", corresponding_address=" + corresponding_address
				+ ", permanent_address=" + permanent_address + ", qualification1=" + qualification1 + ", degree_name1="
				+ degree_name1 + ", university_name1=" + university_name1 + ", percentage1=" + percentage1
				+ ", year_select1=" + year_select1 + ", qualification2=" + qualification2 + ", degree_name2="
				+ degree_name2 + ", university_name2=" + university_name2 + ", percentage2=" + percentage2
				+ ", year_select2=" + year_select2 + ", qualification3=" + qualification3 + ", degree_name3="
				+ degree_name3 + ", university_name3=" + university_name3 + ", percentage3=" + percentage3
				+ ", year_select3=" + year_select3 + ", qualification4=" + qualification4 + ", degree_name4="
				+ degree_name4 + ", university_name4=" + university_name4 + ", percentage4=" + percentage4
				+ ", year_select4=" + year_select4 + ", qualification5=" + qualification5 + ", degree_name5="
				+ degree_name5 + ", university_name5=" + university_name5 + ", percentage5=" + percentage5
				+ ", year_select5=" + year_select5 + ", experience=" + experience + ", previous_comp_name1="
				+ previous_comp_name1 + ", year_of_exp1=" + year_of_exp1 + ", candidate_expected_salary_in_lpa="
				+ candidate_expected_salary_in_lpa + ", reference_name=" + reference_name + ", reference_source_name="
				+ reference_source_name + ", language1=" + language1 + ", language_proficiency1="
				+ language_proficiency1 + ", language2=" + language2 + ", language_proficiency2="
				+ language_proficiency2 + ", language3=" + language3 + ", language_proficiency3="
				+ language_proficiency3 + ", college_name1=" + college_name1 + ", education_completed_type1="
				+ education_completed_type1 + ", college_name2=" + college_name2 + ", education_completed_type2="
				+ education_completed_type2 + ", college_name3=" + college_name3 + ", education_completed_type3="
				+ education_completed_type3 + ", college_name4=" + college_name4 + ", education_completed_type4="
				+ education_completed_type4 + ", college_name5=" + college_name5 + ", education_completed_type5="
				+ education_completed_type5 + ", academic_gap=" + academic_gap + ", academic_gap_qualification_name="
				+ academic_gap_qualification_name + ", academic_gap_type=" + academic_gap_type
				+ ", academic_gap_number=" + academic_gap_number + ", academic_gap_reason=" + academic_gap_reason
				+ ", interviewed_by_us=" + interviewed_by_us + ", candidate_join_time=" + candidate_join_time
				+ ", why_should_hire_you_reason=" + why_should_hire_you_reason + ", exp_company_left_reason="
				+ exp_company_left_reason + ", previous_comp_name2=" + previous_comp_name2 + ", previous_comp_name3="
				+ previous_comp_name3 + ", previous_comp_name4=" + previous_comp_name4 + ", previous_comp_name5="
				+ previous_comp_name5 + ", exp_from_date1=" + exp_from_date1 + ", exp_from_date2=" + exp_from_date2
				+ ", exp_from_date3=" + exp_from_date3 + ", exp_from_date4=" + exp_from_date4 + ", exp_from_date5="
				+ exp_from_date5 + ", exp_to_date1=" + exp_to_date1 + ", exp_to_date2=" + exp_to_date2
				+ ", exp_to_date3=" + exp_to_date3 + ", exp_to_date4=" + exp_to_date4 + ", exp_to_date5=" + exp_to_date5
				+ ", exp_designation_name1=" + exp_designation_name1 + ", exp_designation_name2="
				+ exp_designation_name2 + ", exp_designation_name3=" + exp_designation_name3
				+ ", exp_designation_name4=" + exp_designation_name4 + ", exp_designation_name5="
				+ exp_designation_name5 + ", exp_inhand_salary1=" + exp_inhand_salary1 + ", exp_inhand_salary2="
				+ exp_inhand_salary2 + ", exp_inhand_salary3=" + exp_inhand_salary3 + ", exp_inhand_salary4="
				+ exp_inhand_salary4 + ", exp_inhand_salary5=" + exp_inhand_salary5 + ", exp_notice_period_type1="
				+ exp_notice_period_type1 + ", exp_notice_period_type2=" + exp_notice_period_type2
				+ ", exp_notice_period_type3=" + exp_notice_period_type3 + ", exp_notice_period_type4="
				+ exp_notice_period_type4 + ", exp_notice_period_type5=" + exp_notice_period_type5
				+ ", external_course=" + external_course + ", external_course_name=" + external_course_name
				+ ", external_course_start_date=" + external_course_start_date + ", external_course_completed_date="
				+ external_course_completed_date + ", external_course_certification_name="
				+ external_course_certification_name + ", candidate_police_record=" + candidate_police_record
				+ ", candidate_police_record_reason=" + candidate_police_record_reason + ", candidate_specially_abled="
				+ candidate_specially_abled + ", candidate_specially_abled_reason=" + candidate_specially_abled_reason
				+ ", candidate_illness=" + candidate_illness + ", candidate_illness_reason=" + candidate_illness_reason
				+ ", candidate_smoke=" + candidate_smoke + ", candidate_alcohol=" + candidate_alcohol
				+ ", candidate_imported_flag_to_employee_table=" + candidate_imported_flag_to_employee_table
				+ ", candidate_status_flag=" + candidate_status_flag + ", f2f_total_marks=" + f2f_total_marks
				+ ", f2f_assigned_marks=" + f2f_assigned_marks + ", f2f_remarks=" + f2f_remarks + ", hr_total_marks="
				+ hr_total_marks + ", hr_assigned_marks=" + hr_assigned_marks + ", hr_remarks=" + hr_remarks
				+ ", resume_doc=" + Arrays.toString(resume_doc) + ", aadhar_doc=" + Arrays.toString(aadhar_doc)
				+ ", profile_pic_path=" + profile_pic_path + ", pan_pic_path=" + pan_pic_path + ", aadhar_pic="
				+ aadhar_pic + ", voter_id_pic=" + voter_id_pic + ", tenth_pic=" + tenth_pic + ", puc_pic=" + puc_pic
				+ ", degree_pic=" + degree_pic + ", test_type=" + test_type + ", first_round_result="
				+ first_round_result + ", f2f_round_result=" + f2f_round_result + ", hr_round_result=" + hr_round_result
				+ ", f2f_round_invite_date=" + f2f_round_invite_date + ", f2f_round_invite_time="
				+ f2f_round_invite_time + ", hr_round_invite_date=" + hr_round_invite_date + ", hr_round_invite_tme="
				+ hr_round_invite_tme + ", candidate_response_for_first_ctc_offer="
				+ candidate_response_for_first_ctc_offer + ", candidate_response_for_second_ctc_offer="
				+ candidate_response_for_second_ctc_offer + ", candidate_response_for_third_ctc_offer="
				+ candidate_response_for_third_ctc_offer + ", final_status_viewed_flag=" + final_status_viewed_flag
				+ "]";
	}
	
}
