package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "all_project_data")
public class ProjectData {

	 public ProjectData() {
		super();
	}

	public ProjectData(Long id, String projectId, String emailId, String projectName, String completionDate,
			String taskName, String taskDescription, String taskDueDate, String taskWeight, String subtask,
			String subtaskDescription, String assignDate, Integer statusFlag, String employeeCompletionDate) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.emailId = emailId;
		this.projectName = projectName;
		this.completionDate = completionDate;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskDueDate = taskDueDate;
		this.taskWeight = taskWeight;
		this.subtask = subtask;
		this.subtaskDescription = subtaskDescription;
		this.assignDate = assignDate;
		this.statusFlag = statusFlag;
		this.employeeCompletionDate = employeeCompletionDate;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "project_id")
	    private String projectId;

	    @Column(name = "email_id")
	    private String emailId;

	    @Column(name = "prj_name")
	    private String projectName;

	    @Column(name = "completion_date")
	    private String completionDate;

	    @Column(name = "task_name")
	    private String taskName;

	    @Column(name = "task_description")
	    private String taskDescription;

	    @Column(name = "task_duedate")
	    private String taskDueDate;

	    @Column(name = "task_weight")
	    private String taskWeight;

	    @Column(name = "subtask")
	    private String subtask;

	    @Column(name = "subtask_description")
	    private String subtaskDescription;

	    @Column(name = "assign_date")
	    private String assignDate;

	    @Column(name = "status_flag")
	    private Integer statusFlag;

	    @Column(name = "employee_completion_date")
	    private String employeeCompletionDate;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getProjectId() {
			return projectId;
		}

		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getCompletionDate() {
			return completionDate;
		}

		public void setCompletionDate(String completionDate) {
			this.completionDate = completionDate;
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

		public String getTaskDueDate() {
			return taskDueDate;
		}

		public void setTaskDueDate(String taskDueDate) {
			this.taskDueDate = taskDueDate;
		}

		public String getTaskWeight() {
			return taskWeight;
		}

		public void setTaskWeight(String taskWeight) {
			this.taskWeight = taskWeight;
		}

		public String getSubtask() {
			return subtask;
		}

		public void setSubtask(String subtask) {
			this.subtask = subtask;
		}

		public String getSubtaskDescription() {
			return subtaskDescription;
		}

		public void setSubtaskDescription(String subtaskDescription) {
			this.subtaskDescription = subtaskDescription;
		}

		public String getAssignDate() {
			return assignDate;
		}

		public void setAssignDate(String assignDate) {
			this.assignDate = assignDate;
		}

		public Integer getStatusFlag() {
			return statusFlag;
		}

		public void setStatusFlag(Integer statusFlag) {
			this.statusFlag = statusFlag;
		}

		public String getEmployeeCompletionDate() {
			return employeeCompletionDate;
		}

		public void setEmployeeCompletionDate(String employeeCompletionDate) {
			this.employeeCompletionDate = employeeCompletionDate;
		}
	
	
}
