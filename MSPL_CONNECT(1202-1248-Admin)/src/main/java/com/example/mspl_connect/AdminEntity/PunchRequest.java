package com.example.mspl_connect.AdminEntity;

public class PunchRequest {
    private String empId;
    private String date;
    private String time;

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

	@Override
	public String toString() {
		return "PunchRequest [empId=" + empId + ", date=" + date + ", time=" + time + "]";
	}

	public PunchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PunchRequest(String empId, String date, String time) {
		super();
		this.empId = empId;
		this.date = date;
		this.time = time;
	}
    
}

