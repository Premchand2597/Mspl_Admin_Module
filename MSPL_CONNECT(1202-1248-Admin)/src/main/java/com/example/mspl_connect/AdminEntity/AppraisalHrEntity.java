package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "apprisal_hr")
public class AppraisalHrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_id", nullable = false)
    private String empId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "quarter")
    private String quarter;

    @Column(name = "quarter_month_year")
    private String quarterMonthYear;

    @Column(name = "apprisal_send_date")
    private LocalDate apprisalSendDate;

    @Column(name = "apprisal_submit_date")
    private LocalDate apprisalSubmitDate;

    @Column(name = "apprisal_submit_date_admin")
    private LocalDate apprisalSubmitDateAdmin;

    @Column(name = "apprisal_link_flag")
    private Boolean apprisalLinkFlag;

    @Column(name = "apprisal_email_send_flag")
    private Boolean apprisalEmailSendFlag;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getQuarterMonthYear() {
        return quarterMonthYear;
    }

    public void setQuarterMonthYear(String quarterMonthYear) {
        this.quarterMonthYear = quarterMonthYear;
    }

    public LocalDate getApprisalSendDate() {
        return apprisalSendDate;
    }

    public void setApprisalSendDate(LocalDate apprisalSendDate) {
        this.apprisalSendDate = apprisalSendDate;
    }

    public LocalDate getApprisalSubmitDate() {
        return apprisalSubmitDate;
    }

    public void setApprisalSubmitDate(LocalDate apprisalSubmitDate) {
        this.apprisalSubmitDate = apprisalSubmitDate;
    }

    public LocalDate getApprisalSubmitDateAdmin() {
        return apprisalSubmitDateAdmin;
    }

    public void setApprisalSubmitDateAdmin(LocalDate apprisalSubmitDateAdmin) {
        this.apprisalSubmitDateAdmin = apprisalSubmitDateAdmin;
    }

    public Boolean getApprisalLinkFlag() {
        return apprisalLinkFlag;
    }

    public void setApprisalLinkFlag(Boolean apprisalLinkFlag) {
        this.apprisalLinkFlag = apprisalLinkFlag;
    }

    public Boolean getApprisalEmailSendFlag() {
        return apprisalEmailSendFlag;
    }

    public void setApprisalEmailSendFlag(Boolean apprisalEmailSendFlag) {
        this.apprisalEmailSendFlag = apprisalEmailSendFlag;
    }

	@Override
	public String toString() {
		return "AppraisalHrEntity [id=" + id + ", empId=" + empId + ", email=" + email + ", department=" + department
				+ ", quarter=" + quarter + ", quarterMonthYear=" + quarterMonthYear + ", apprisalSendDate="
				+ apprisalSendDate + ", apprisalSubmitDate=" + apprisalSubmitDate + ", apprisalSubmitDateAdmin="
				+ apprisalSubmitDateAdmin + ", apprisalLinkFlag=" + apprisalLinkFlag + ", apprisalEmailSendFlag="
				+ apprisalEmailSendFlag + "]";
	}
    
    
}
