package com.example.mspl_connect.Sales_Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "call_feedback_history")
public class CallFeedbackHistory {
	

	public CallFeedbackHistory(Long id, Long callScheduleId, LocalDateTime callMadeTime, String feedback,
			String nextAction, LocalDateTime nextSchedule, Integer previousStatus, LocalDateTime createdAt,
			boolean isReschedule, String taskDelayReason) {
		super();
		this.id = id;
		this.callScheduleId = callScheduleId;
		this.callMadeTime = callMadeTime;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.nextSchedule = nextSchedule;
		this.previousStatus = previousStatus;
		this.createdAt = createdAt;
		this.isReschedule = isReschedule;
		this.taskDelayReason = taskDelayReason;
	}

	public CallFeedbackHistory(Long id, Long callScheduleId, LocalDateTime callMadeTime, String feedback,
			String nextAction, LocalDateTime nextSchedule, Integer previousStatus, LocalDateTime createdAt,
			boolean isReschedule) {
		super();
		this.id = id;
		this.callScheduleId = callScheduleId;
		this.callMadeTime = callMadeTime;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.nextSchedule = nextSchedule;
		this.previousStatus = previousStatus;
		this.createdAt = createdAt;
		this.isReschedule = isReschedule;
	}

	public CallFeedbackHistory(Long id, Long callScheduleId, LocalDateTime callMadeTime, String feedback,
			String nextAction, LocalDateTime nextSchedule, Integer previousStatus, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.callScheduleId = callScheduleId;
		this.callMadeTime = callMadeTime;
		this.feedback = feedback;
		this.nextAction = nextAction;
		this.nextSchedule = nextSchedule;
		this.previousStatus = previousStatus;
		this.createdAt = createdAt;
	}

	public CallFeedbackHistory() {
		super();
	}

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long callScheduleId;
    
    @Column(nullable = true)
    private LocalDateTime callMadeTime;
    
    @Column(nullable = false, length = 500)
    private String feedback;
    
    @Column(nullable = false)
    private String nextAction;
    
    private LocalDateTime nextSchedule;
    
    @Column(nullable = false)
    private Integer previousStatus;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean isReschedule = false;
    
    @Column(name = "task_delay_reason")
    private String taskDelayReason;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCallScheduleId() {
		return callScheduleId;
	}

	public void setCallScheduleId(Long callScheduleId) {
		this.callScheduleId = callScheduleId;
	}

	

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	

	public Integer getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Integer previousStatus) {
		this.previousStatus = previousStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCallMadeTime() {
		return callMadeTime;
	}

	public void setCallMadeTime(LocalDateTime callMadeTime) {
		this.callMadeTime = callMadeTime;
	}

	public LocalDateTime getNextSchedule() {
		return nextSchedule;
	}

	public void setNextSchedule(LocalDateTime nextSchedule) {
		this.nextSchedule = nextSchedule;
	}

	public boolean isReschedule() {
		return isReschedule;
	}

	public void setReschedule(boolean isReschedule) {
		this.isReschedule = isReschedule;
	}

	public String getTaskDelayReason() {
		return taskDelayReason;
	}

	public void setTaskDelayReason(String taskDelayReason) {
		this.taskDelayReason = taskDelayReason;
	}
}
