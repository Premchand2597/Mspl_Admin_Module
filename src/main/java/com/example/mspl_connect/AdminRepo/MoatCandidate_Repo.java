package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatCandidate_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface MoatCandidate_Repo extends JpaRepository<MoatCandidate_Entity, Long>{

	@Procedure(name = "fetch_student_data")
	@Query(nativeQuery = true, value="call fetch_student_data()")
	List<MoatCandidate_Entity> fetch_student_data();
	
	/*@Query(nativeQuery = true, value="SELECT * FROM student ORDER BY CASE WHEN action = 'approv' THEN 0 WHEN action = 'reject' THEN 1 ELSE 2 END, id DESC")
	List<MoatCandidate_Entity> fetch_student_data();*/
	
	@Query(nativeQuery = true, value="SELECT * FROM student where action='approv' order by inserted_at desc")
	List<MoatCandidate_Entity> fetch_Selected_Candidate_data();
	
	@Query(nativeQuery = true, value="SELECT * FROM student where action='reject' order by inserted_at desc")
	List<MoatCandidate_Entity> fetch_Rejected_Candidate_data();
	
//	@Query(nativeQuery = true, value="SELECT * FROM student where id=:candidate_id")
//	MoatCandidate_Entity fetch_Candidate_data_By_Id(@Param("candidate_id") String candidate_id);
	
	@Query(nativeQuery = true, value="SELECT * FROM student where id=cast(:candidate_id as integer)")
	MoatCandidate_Entity fetch_Candidate_data_By_Id(@Param("candidate_id") String candidate_id);
	
	/*@Query(nativeQuery = true, value="SELECT * FROM student where id=:candidate_id")
	MoatCandidate_Entity fetch_Rejected_Candidate_data_By_Id(@Param("candidate_id") String candidate_id);*/
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value="update student set candidate_status_flag = 0 where id=:candidate_id")
//	void update_candidate_flag(@Param("candidate_id") String candidate_id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update student set candidate_status_flag = 0 where id=cast(:candidate_id as integer)")
	void update_candidate_flag(@Param("candidate_id") String candidate_id);
	
	/*@Procedure(name = "fetch_interviewd_student_details_by_dept")
	@Query(nativeQuery = true, value = "fetch_interviewd_student_details_by_dept(:loggedadmin_dept,:action_value)")
	List<MoatCandidate_Entity> fetch_interviewd_student_details_by_dept(@Param("action") String action,@Param("loggedadmin_dept") String loggedadmin_dept);
	*/
	
	@Query(nativeQuery = true,value="SELECT  DISTINCT s.email,s.id, s.first_name, s.last_name, s.gender, s.password, s.mobile_number, s.dob, s.inserted_at, s.languages, s.action, s.candidate_action_reason, s.aadhar_number, s.father_name, s.father_designation, s.mother_name, s.mother_designation, s.marital_status_value, s.corresponding_address, s.permanent_address, s.qualification1, s.degree_name1, s.university_name1, s.percentage1, s.year_select1, s.qualification2, s.degree_name2, s.university_name2, s.percentage2, s.year_select2, s.qualification3, s.degree_name3, s.university_name3, s.percentage3, s.year_select3, s.qualification4, s.degree_name4, s.university_name4, s.percentage4, s.year_select4, s.qualification5, s.degree_name5, s.university_name5, s.percentage5, s.year_select5, s.experience, s.previous_comp_name1, s.year_of_exp1, s.candidate_expected_salary_in_lpa, s.reference_name, s.reference_source_name, s.language1, s.language_proficiency1, s.language2, s.language_proficiency2, s.language3, s.language_proficiency3, s.resume_doc, s.aadhar_doc, s.college_name1, s.education_completed_type1, s.college_name2, s.education_completed_type2, s.college_name3, s.education_completed_type3, s.college_name4, s.education_completed_type4, s.college_name5, s.education_completed_type5, s.academic_gap, s.academic_gap_qualification_name, s.academic_gap_type, s.academic_gap_number, s.academic_gap_reason, s.interviewed_by_us, s.candidate_join_time, s.why_should_hire_you_reason, s.exp_company_left_reason, s.previous_comp_name2, s.previous_comp_name3, s.previous_comp_name4, s.previous_comp_name5, s.exp_from_date1, s.exp_from_date2, s.exp_from_date3, s.exp_from_date4, s.exp_from_date5, s.exp_to_date1, s.exp_to_date2, s.exp_to_date3, s.exp_to_date4, s.exp_to_date5, s.exp_designation_name1, s.exp_designation_name2, s.exp_designation_name3, s.exp_designation_name4, s.exp_designation_name5, s.exp_inhand_salary1, s.exp_inhand_salary2, s.exp_inhand_salary3, s.exp_inhand_salary4, s.exp_inhand_salary5, s.exp_notice_period_type1, s.exp_notice_period_type2, s.exp_notice_period_type3, s.exp_notice_period_type4, s.exp_notice_period_type5, s.external_course, s.external_course_name, s.external_course_start_date, s.external_course_completed_date, s.external_course_certification_name, s.candidate_police_record, s.candidate_police_record_reason, s.candidate_specially_abled, s.candidate_specially_abled_reason, s.candidate_illness, s.candidate_illness_reason, s.candidate_smoke, s.candidate_alcohol, s.candidate_imported_flag_to_employee_table, s.candidate_status_flag, s.profile_pic_path, s.pan_pic_path, s.aadhar_pic, s.voter_id_pic, s.tenth_pic, s.puc_pic, s.degree_pic, s.exp_letter_pic, s.pg_pic, s.payslip_pic, s.f2f_total_marks, s.f2f_assigned_marks, s.f2f_remarks, s.hr_total_marks, s.hr_assigned_marks, s.hr_remarks, s.first_ctc_offer, s.candidate_response_for_first_ctc_offer, s.candidate_remark_for_first_ctc_offer, s.second_ctc_offer, s.candidate_response_for_second_ctc_offer, s.candidate_remark_for_second_ctc_offer, s.third_ctc_offer, s.candidate_response_for_third_ctc_offer, s.candidate_remark_for_third_ctc_offer, s.doc_upload_flag, s.exp_current_salary_in_lpa, s.test_status_flagg,s.bank_check_pic,s.diploma_pic,s.other_pic,s.user_login_status_expiration FROM student s inner join link_generated_table lgt on s.email = lgt.candidate_email WHERE ((:action IS NULL AND s.action IS NULL) OR (:action IS NOT NULL AND s.action = :action)) and lgt.dept_name=:loggedadmin_dept")
	List<MoatCandidate_Entity> fetch_interviewd_student_details_by_dept(String action,@Param("loggedadmin_dept") String loggedadmin_dept);
	
	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE (:action IS NULL AND action IS NULL) OR (action = :action) order by id desc")
	List<MoatCandidate_Entity> fetch_student_data_based_on_status(@Param("action") String action);
	
}
