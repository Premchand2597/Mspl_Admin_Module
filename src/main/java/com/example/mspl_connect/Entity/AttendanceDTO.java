package com.example.mspl_connect.Entity;

public class AttendanceDTO {


	
	public AttendanceDTO(String eid, String date, String inTime, String outTime, String totalHours, String overtime,
			String employeeName, String undertime) {
		super();
		this.eid = eid;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
		this.totalHours = totalHours;
		this.overtime = overtime;
		this.employeeName = employeeName;
		this.undertime = undertime;
	}
	public AttendanceDTO(String eid, String date, String inTime, String outTime, String totalHours, String overtime,
			String employeeName) {
		super();
		this.eid = eid;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
		this.totalHours = totalHours;
		this.overtime = overtime;
		this.employeeName = employeeName;
	}
	public AttendanceDTO(String eid, String inTime, String outTime, String totalHours) {
		super();
		this.eid = eid;
		this.inTime = inTime;
		this.outTime = outTime;
		this.totalHours = totalHours;
	}
	public AttendanceDTO(String eid, String date, String inTime, String outTime, String totalHours, String overtime) {
		super();
		this.eid = eid;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
		this.totalHours = totalHours;
		this.overtime = overtime;
	}
	public AttendanceDTO() {
		super();
	}
	public AttendanceDTO(String eid, String date, String inTime, String outTime, String totalHours) {
		super();
		this.eid = eid;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
		this.totalHours = totalHours;
	}
	private String eid;
    private String date;
    private String inTime;
    private String outTime;
    private String totalHours; 
    private String overtime;
    private String employeeName; 
    private String undertime;
    private String pdt;

    public String getPdt() {
		return pdt;
	}
	public void setPdt(String pdt) {
		this.pdt = pdt;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getUndertime() {
		return undertime;
	}
	public void setUndertime(String undertime) {
		this.undertime = undertime;
	}
	@Override
	public String toString() {
		return "AttendanceDTO [eid=" + eid + ", date=" + date + ", inTime=" + inTime + ", outTime=" + outTime
				+ ", totalHours=" + totalHours + ", overtime=" + overtime + ", employeeName=" + employeeName
				+ ", undertime=" + undertime + ", pdt=" + pdt + "]";
	}
	
}
