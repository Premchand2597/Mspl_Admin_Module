package com.example.mspl_connect.AdminEntity;

public class AttendanceReportGeneratorFinal_DTO {

	public AttendanceReportGeneratorFinal_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String emp_name; 
	private String eid;
	private String pd;
	private String punch_in;
	private String punch_out;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getPunch_in() {
		return punch_in;
	}
	public void setPunch_in(String punch_in) {
		this.punch_in = punch_in;
	}
	public String getPunch_out() {
		return punch_out;
	}
	public void setPunch_out(String punch_out) {
		this.punch_out = punch_out;
	}
	@Override
	public String toString() {
		return "AttendanceReportGeneratorFinal_DTO [emp_name=" + emp_name + ", eid=" + eid + ", pd=" + pd
				+ ", punch_in=" + punch_in + ", punch_out=" + punch_out + "]";
	}
	public AttendanceReportGeneratorFinal_DTO(String emp_name, String eid, String pd, String punch_in,
			String punch_out) {
		this.emp_name = emp_name;
		this.eid = eid;
		this.pd = pd;
		this.punch_in = punch_in;
		this.punch_out = punch_out;
	}
	
}
