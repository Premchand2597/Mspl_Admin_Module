package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
	
	@Id
	private int id;
	private String employeeId;
    private String taskName;
    private String taskDescription;
    private String mainTaskDescription;
   
	private int taskWeight;
    
	private String taskCompletionDate;
    private String emp_mail;
    private String prj_name;  
	private String projectId;
    private String completionDate;  
    private String subTaskName;
    private String assign_date;
	private String prjManager;
 
	public String getPrjManager() {
		return prjManager;
	}

	public void setPrjManager(String prjManager) {
		this.prjManager = prjManager;
	}

	public String getAssign_date() {
		return assign_date;
	}

	public void setAssign_date(String assign_date) {
		this.assign_date = assign_date;
	}

	public Task(int id, String employeeId, String taskName, String taskDescription, int taskWeight,
			String taskCompletionDate, String emp_mail, String prj_name, String projectId, String completionDate,String subTaskName,String mainTaskDescription) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskWeight = taskWeight;
		this.taskCompletionDate = taskCompletionDate;
		this.emp_mail = emp_mail;
		this.prj_name = prj_name;
		this.projectId = projectId;
		this.completionDate = completionDate;
		this.subTaskName = subTaskName;
		this.mainTaskDescription = mainTaskDescription;
	}
	
	 public String getMainTaskDescription() {
			return mainTaskDescription;
		}


		public void setMainTaskDescription(String mainTaskDescription) {
			this.mainTaskDescription = mainTaskDescription;
		}
	public String getSubTaskName() {
		return subTaskName;
	}
	public void setSubTaskName(String subTaskName) {
		this.subTaskName = subTaskName;
	}
    public String getPrj_name() {
		return prj_name;
	}
	public void setPrj_name(String prj_name) {
		this.prj_name = prj_name;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_mail() {
		return emp_mail;
	}
	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public int getTaskWeight() {
		return taskWeight;
	}
	public void setTaskWeight(int taskWeight) {
		this.taskWeight = taskWeight;
	}
	public String getTaskCompletionDate() {
		return taskCompletionDate;
	}
	public void setTaskCompletionDate(String taskCompletionDate) {
		this.taskCompletionDate = taskCompletionDate;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", employeeId=" + employeeId + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", mainTaskDescription=" + mainTaskDescription + ", taskWeight=" + taskWeight
				+ ", taskCompletionDate=" + taskCompletionDate + ", emp_mail=" + emp_mail + ", prj_name=" + prj_name
				+ ", projectId=" + projectId + ", completionDate=" + completionDate + ", subTaskName=" + subTaskName
				+ ", assign_date=" + assign_date + ", prjManager=" + prjManager + "]";
	}
}
