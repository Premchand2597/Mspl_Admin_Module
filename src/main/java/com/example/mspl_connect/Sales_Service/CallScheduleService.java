package com.example.mspl_connect.Sales_Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.CallFeedbackHistory;
import com.example.mspl_connect.Sales_Entity.CallSchedule;
import com.example.mspl_connect.Sales_Repository.CallFeedbackHistoryRepository;
import com.example.mspl_connect.Sales_Repository.CallScheduleRepository;

import jakarta.transaction.Transactional;

@Service
public class CallScheduleService {
	 @Autowired
	    private CallScheduleRepository callScheduleRepository;
	    
	 @Autowired
	    private CallFeedbackHistoryRepository feedbackHistoryRepository;

	 
	    public CallSchedule saveCallSchedule(CallSchedule callSchedule) {
	        return callScheduleRepository.save(callSchedule);
	    }
	    
	    
	    // Method to fetch all call schedules
	    public List<CallSchedule> getAllCallSchedules() {
	        return callScheduleRepository.findAll();
	    }
	    
	    public Optional<CallSchedule> getCallScheduleById(Long id) {
	        return callScheduleRepository.findById(id);
	    }
	    
	    @Transactional
	    public boolean updateCallStatus(Long id, Integer status) {
	        int updated = callScheduleRepository.updateCallStatus(id, status);
	        return updated > 0;
	    }
	    
	    public CallSchedule saveCall(CallSchedule callSchedule) {
	        return callScheduleRepository.save(callSchedule);
	    }
	  /*  @Transactional
	    public boolean updateCallStatusWithFeedback(Long id, Integer status, String callMadeTime, String feedback, String nextAction, String nextSchedule) {
	        Optional<CallSchedule> optionalSchedule = callScheduleRepository.findById(id);

	        if (optionalSchedule.isPresent()) {
	            CallSchedule schedule = optionalSchedule.get();
	            schedule.setCallStatus(status);
	            schedule.setCallMadeTime(LocalDateTime.parse(callMadeTime));
	            schedule.setFeedback(feedback);
	            schedule.setNextAction(nextAction);
	            
	            // Set the next schedule date and time if provided
	            if (nextSchedule != null && !nextSchedule.trim().isEmpty()) {
	                schedule.setNextSchedule(LocalDateTime.parse(nextSchedule));
	            }

	            callScheduleRepository.save(schedule);
	            return true;
	        }

	        return false;
	    }*/

	    

	  /*  @Transactional
	    public boolean updateCallStatusWithFeedback(Long id, Integer status, String callMadeTimeStr, String feedback, String nextAction, String nextScheduleStr) {
	        Optional<CallSchedule> optionalSchedule = callScheduleRepository.findById(id);

	        if (optionalSchedule.isPresent()) {
	            CallSchedule currentCall = optionalSchedule.get();

	            // Save the current state to the feedback history table
	            CallFeedbackHistory history = new CallFeedbackHistory();
	            history.setCallScheduleId(id);
	            history.setCallMadeTime(currentCall.getCallMadeTime());
	            history.setFeedback(currentCall.getFeedback());
	            history.setNextAction(currentCall.getNextAction());
	            history.setNextSchedule(currentCall.getNextSchedule());
	            history.setPreviousStatus(currentCall.getCallStatus());
	            history.setCreatedAt(LocalDateTime.now());
	            feedbackHistoryRepository.save(history);

	            
	            // Update the current call record
	            currentCall.setCallStatus(status);
	            currentCall.setCallMadeTime(LocalDateTime.parse(callMadeTimeStr));
	            currentCall.setFeedback(feedback);
	            currentCall.setNextAction(nextAction);

	            // Set the next schedule date if provided
	            if (nextScheduleStr != null && !nextScheduleStr.trim().isEmpty()) {
	                currentCall.setNextSchedule(LocalDateTime.parse(nextScheduleStr));
	            } else {
	                currentCall.setNextSchedule(null); // Clear if not provided
	            }

	            callScheduleRepository.save(currentCall);
	            return true;
	        }

	        return false;
	    }*/
	    
	    @Transactional
	    public boolean updateCallStatusWithFeedback(Long id, Integer status, String callMadeTimeStr, String feedback, String nextAction, String nextScheduleStr,boolean isReschedule,String taskDelayReason) {
	        Optional<CallSchedule> optionalSchedule = callScheduleRepository.findById(id);
	        // Print the received parameters for debugging
	        System.out.println("Received values:");
	        System.out.println("ID: " + id);
	        System.out.println("Status: " + status);
	        System.out.println("Call Made Time String: " + callMadeTimeStr);
	        System.out.println("Feedback: " + feedback);
	        System.out.println("Next Action: " + nextAction);
	        System.out.println("Next Schedule String: " + nextScheduleStr);
	        System.out.println("Is Reschedule: " + isReschedule);

	        if (optionalSchedule.isPresent()) {
	            CallSchedule currentCall = optionalSchedule.get();
	            boolean isCallMadeTimeEmpty = currentCall.getNextAction() == null;

	            if (!isCallMadeTimeEmpty) {
	                // Save feedback history with OLD values
	                CallFeedbackHistory history = new CallFeedbackHistory();
	                history.setCallScheduleId(id);
	              //  history.setCallMadeTime(currentCall.getCallMadeTime());
	              //  history.setFeedback(currentCall.getFeedback());
	                // Use NEW callMadeTime and feedback instead of old values
	                if (callMadeTimeStr != null && !callMadeTimeStr.trim().isEmpty()) {
	                    history.setCallMadeTime(LocalDateTime.parse(callMadeTimeStr));
	                } else {
	                    history.setCallMadeTime(null);
	                }

	                history.setFeedback(feedback); // NEW feedback
	                history.setTaskDelayReason(taskDelayReason);

	                history.setNextAction(currentCall.getNextAction());
	                history.setNextSchedule(currentCall.getNextSchedule());
	                history.setPreviousStatus(currentCall.getCallStatus());
	                history.setCreatedAt(LocalDateTime.now());
	                history.setReschedule(isReschedule); 
	                currentCall.setTaskDelayReason(taskDelayReason);

	                feedbackHistoryRepository.save(history);
	            }

	            // Update current call with NEW values
	            currentCall.setCallStatus(status);
	         //   currentCall.setCallMadeTime(LocalDateTime.parse(callMadeTimeStr));
	            // Safely parse callMadeTime
	            if (callMadeTimeStr != null && !callMadeTimeStr.trim().isEmpty()) {
	                currentCall.setCallMadeTime(LocalDateTime.parse(callMadeTimeStr));
	            } else {
	                currentCall.setCallMadeTime(null); // or skip setting if preferred
	            }

	            currentCall.setFeedback(feedback);
	            currentCall.setNextAction(nextAction);

	            if (nextScheduleStr != null && !nextScheduleStr.trim().isEmpty()) {
	                currentCall.setNextSchedule(LocalDateTime.parse(nextScheduleStr));
	            } else {
	                currentCall.setNextSchedule(null);
	            }

	            callScheduleRepository.save(currentCall); // Save updates

	            if (isCallMadeTimeEmpty) {
	                // Save feedback history with NEW values (first time call is made)
	                CallFeedbackHistory history = new CallFeedbackHistory();
	                history.setCallScheduleId(id);
	                history.setCallMadeTime(currentCall.getCallMadeTime());
	                history.setFeedback(currentCall.getFeedback());

	                // If status is 1 or 2, override nextAction to "callback"
	                if (status == 1 || status == 2) {
	                    history.setNextAction("callback");
	                } else {
	                    history.setNextAction(currentCall.getNextAction());
	                }

	                history.setNextSchedule(currentCall.getFromTime());
	                history.setPreviousStatus(currentCall.getCallStatus());
	                history.setCreatedAt(LocalDateTime.now());
	                history.setReschedule(isReschedule);
	                feedbackHistoryRepository.save(history);
	            }

	            // --- Additional: Save feedback history again if action is marked completed ---
	            if ("completed".equalsIgnoreCase(nextAction)) {
	                CallFeedbackHistory completedHistory = new CallFeedbackHistory();
	                completedHistory.setCallScheduleId(id);
	                completedHistory.setCallMadeTime(currentCall.getCallMadeTime());
	                completedHistory.setFeedback(currentCall.getFeedback());
	                completedHistory.setNextAction(currentCall.getNextAction());
	                completedHistory.setNextSchedule(currentCall.getNextSchedule());
	                completedHistory.setPreviousStatus(currentCall.getCallStatus());
	                completedHistory.setCreatedAt(LocalDateTime.now());
	                completedHistory.setReschedule(isReschedule);
	                feedbackHistoryRepository.save(completedHistory);
	            }
	            return true;
	        }

	        return false;
	    }
	   

}
