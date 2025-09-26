package com.example.mspl_connect.Entity;

public class ProjectChartResponse {
    private String empName;
    private String email;
    private int totalCompletedWork;
    private int overall_assigned_task;
    private int totalSumOfCompletedWork;

    public ProjectChartResponse(String empName, String email, int totalCompletedWork, int overall_assigned_task, int totalSumOfCompletedWork) {
		super();
		this.empName = empName;
		this.email = email;
		this.totalCompletedWork = totalCompletedWork;
		this.overall_assigned_task = overall_assigned_task;
		this.totalSumOfCompletedWork = totalSumOfCompletedWork;
	}

	@Override
	public String toString() {
		return "ProjectChartResponse [empName=" + empName + ", email=" + email + ", totalCompletedWork="
				+ totalCompletedWork + ", overall_assigned_task=" + overall_assigned_task + ", totalSumOfCompletedWork="
				+ totalSumOfCompletedWork + "]";
	}

	// Getters
    public String getEmpName() {
        return empName;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalCompletedWork() {
        return totalCompletedWork;
    }

    public int getTotalSumOfCompletedWork() {
        return totalSumOfCompletedWork;
    }

    // Setters
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOverall_assigned_task() {
		return overall_assigned_task;
	}

	public void setOverall_assigned_task(int overall_assigned_task) {
		this.overall_assigned_task = overall_assigned_task;
	}

	public void setTotalCompletedWork(int totalCompletedWork) {
        this.totalCompletedWork = totalCompletedWork;
    }

    public void setTotalSumOfCompletedWork(int totalSumOfCompletedWork) {
        this.totalSumOfCompletedWork = totalSumOfCompletedWork;
    }
}
