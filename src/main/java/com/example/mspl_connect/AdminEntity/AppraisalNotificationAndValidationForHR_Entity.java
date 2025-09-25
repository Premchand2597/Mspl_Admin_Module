package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "apprisal_notification_table_for_hr")
public class AppraisalNotificationAndValidationForHR_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String quarter;
	private String quarter_flag;
	private String quarter_send_enabled_btn_flag;
	private String appraisal_send_month;
	private String appraisal_sent_year;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarter_flag() {
		return quarter_flag;
	}
	public void setQuarter_flag(String quarter_flag) {
		this.quarter_flag = quarter_flag;
	}
	public String getQuarter_send_enabled_btn_flag() {
		return quarter_send_enabled_btn_flag;
	}
	public void setQuarter_send_enabled_btn_flag(String quarter_send_enabled_btn_flag) {
		this.quarter_send_enabled_btn_flag = quarter_send_enabled_btn_flag;
	}
	public String getAppraisal_send_month() {
		return appraisal_send_month;
	}
	public void setAppraisal_send_month(String appraisal_send_month) {
		this.appraisal_send_month = appraisal_send_month;
	}
	public String getAppraisal_sent_year() {
		return appraisal_sent_year;
	}
	public void setAppraisal_sent_year(String appraisal_sent_year) {
		this.appraisal_sent_year = appraisal_sent_year;
	}
	@Override
	public String toString() {
		return "AppraisalNotificationAndValidationForHR_Entity [id=" + id + ", quarter=" + quarter + ", quarter_flag="
				+ quarter_flag + ", quarter_send_enabled_btn_flag=" + quarter_send_enabled_btn_flag
				+ ", appraisal_send_month=" + appraisal_send_month + ", appraisal_sent_year=" + appraisal_sent_year
				+ "]";
	}
	
}
