package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "moat_candidate_mode_of_contact")
public class ModeOfContactNewCandidates_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String contact_by;
	private String candidate_name;
	private String candidate_email;
	private String candidate_contact_no;
	private String qualification;
	private String candidate_type;
	private String currently_working;
	private String yoe;
	private String current_ctc;
	private String expected_ctc;
	private String notice_period;
	private String communication_point;
	private String technical_part;
	private String technical_lang;
	private String mode_of_contact;
	private String remarks;
	private String contacted_date;
	private String dept;
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContact_by() {
		return contact_by;
	}
	public void setContact_by(String contact_by) {
		this.contact_by = contact_by;
	}
	public String getCandidate_name() {
		return candidate_name;
	}
	public void setCandidate_name(String candidate_name) {
		this.candidate_name = candidate_name;
	}
	public String getCandidate_email() {
		return candidate_email;
	}
	public void setCandidate_email(String candidate_email) {
		this.candidate_email = candidate_email;
	}
	public String getCandidate_contact_no() {
		return candidate_contact_no;
	}
	public void setCandidate_contact_no(String candidate_contact_no) {
		this.candidate_contact_no = candidate_contact_no;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getCandidate_type() {
		return candidate_type;
	}
	public void setCandidate_type(String candidate_type) {
		this.candidate_type = candidate_type;
	}
	public String getCurrently_working() {
		return currently_working;
	}
	public void setCurrently_working(String currently_working) {
		this.currently_working = currently_working;
	}
	public String getYoe() {
		return yoe;
	}
	public void setYoe(String yoe) {
		this.yoe = yoe;
	}
	public String getCurrent_ctc() {
		return current_ctc;
	}
	public void setCurrent_ctc(String current_ctc) {
		this.current_ctc = current_ctc;
	}
	public String getExpected_ctc() {
		return expected_ctc;
	}
	public void setExpected_ctc(String expected_ctc) {
		this.expected_ctc = expected_ctc;
	}
	public String getNotice_period() {
		return notice_period;
	}
	public void setNotice_period(String notice_period) {
		this.notice_period = notice_period;
	}
	public String getCommunication_point() {
		return communication_point;
	}
	public void setCommunication_point(String communication_point) {
		this.communication_point = communication_point;
	}
	public String getTechnical_part() {
		return technical_part;
	}
	public void setTechnical_part(String technical_part) {
		this.technical_part = technical_part;
	}
	public String getTechnical_lang() {
		return technical_lang;
	}
	public void setTechnical_lang(String technical_lang) {
		this.technical_lang = technical_lang;
	}
	public String getMode_of_contact() {
		return mode_of_contact;
	}
	public void setMode_of_contact(String mode_of_contact) {
		this.mode_of_contact = mode_of_contact;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContacted_date() {
		return contacted_date;
	}
	public void setContacted_date(String contacted_date) {
		this.contacted_date = contacted_date;
	}


	public ModeOfContactNewCandidates_Entity(long id, String contact_by, String candidate_name, String candidate_email,
			String candidate_contact_no, String qualification, String candidate_type, String currently_working,
			String yoe, String current_ctc, String expected_ctc, String notice_period, String communication_point,
			String technical_part, String technical_lang, String mode_of_contact, String remarks, String contacted_date,
			String dept) {
		super();
		this.id = id;
		this.contact_by = contact_by;
		this.candidate_name = candidate_name;
		this.candidate_email = candidate_email;
		this.candidate_contact_no = candidate_contact_no;
		this.qualification = qualification;
		this.candidate_type = candidate_type;
		this.currently_working = currently_working;
		this.yoe = yoe;
		this.current_ctc = current_ctc;
		this.expected_ctc = expected_ctc;
		this.notice_period = notice_period;
		this.communication_point = communication_point;
		this.technical_part = technical_part;
		this.technical_lang = technical_lang;
		this.mode_of_contact = mode_of_contact;
		this.remarks = remarks;
		this.contacted_date = contacted_date;
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "ModeOfContactNewCandidates_Entity [id=" + id + ", contact_by=" + contact_by + ", candidate_name="
				+ candidate_name + ", candidate_email=" + candidate_email + ", candidate_contact_no="
				+ candidate_contact_no + ", qualification=" + qualification + ", candidate_type=" + candidate_type
				+ ", currently_working=" + currently_working + ", yoe=" + yoe + ", current_ctc=" + current_ctc
				+ ", expected_ctc=" + expected_ctc + ", notice_period=" + notice_period + ", communication_point="
				+ communication_point + ", technical_part=" + technical_part + ", technical_lang=" + technical_lang
				+ ", mode_of_contact=" + mode_of_contact + ", remarks=" + remarks + ", contacted_date=" + contacted_date
				+ ", dept=" + dept + "]";
	}
	public ModeOfContactNewCandidates_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
