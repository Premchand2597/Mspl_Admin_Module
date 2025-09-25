package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDoList {

	public ToDoList(Long id, String empId, String description, boolean completed, String deadlinetime,
			String deadlinedate) {
		super();
		this.id = id;
		this.empId = empId;
		this.description = description;
		this.completed = completed;
		this.deadlinetime = deadlinetime;
		this.deadlinedate = deadlinedate;
	}



	public ToDoList() {
		super();
	}

	public ToDoList(Long id, String empId, String description, boolean completed) {
		super();
		this.id = id;
		this.empId = empId;
		this.description = description;
		this.completed = completed;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empId;

    private String description;

    private boolean completed =false;

    private String deadlinetime;
    
    private String deadlinedate;
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}



	public String getDeadlinetime() {
		return deadlinetime;
	}

	public void setDeadlinetime(String deadlinetime) {
		this.deadlinetime = deadlinetime;
	}

	public String getDeadlinedate() {
		return deadlinedate;
	}

	public void setDeadlinedate(String deadlinedate) {
		this.deadlinedate = deadlinedate;
	}
}
