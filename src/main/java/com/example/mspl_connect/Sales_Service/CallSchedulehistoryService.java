package com.example.mspl_connect.Sales_Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.mspl_connect.Sales_Entity.CallFeedbackHistory;
import com.example.mspl_connect.Sales_Entity.CallSchedule;
import com.example.mspl_connect.Sales_Repository.CallFeedbackHistoryRepository;
import com.example.mspl_connect.Sales_Repository.CallScheduleRepository;

import jakarta.transaction.Transactional;

public class CallSchedulehistoryService {
	 @Autowired
	    private CallScheduleRepository callScheduleRepository;

	    @Autowired
	    private CallFeedbackHistoryRepository feedbackHistoryRepository;

	    @Transactional
	    public boolean updateCallStatusWithFeedback(Long id, Integer status, String callMadeTimeStr, String feedback, String nextAction, String nextScheduleStr,boolean isReschedule) {
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
	                history.setCallMadeTime(currentCall.getCallMadeTime());
	                history.setFeedback(currentCall.getFeedback());
	                history.setNextAction(currentCall.getNextAction());
	                history.setNextSchedule(currentCall.getNextSchedule());
	                history.setPreviousStatus(currentCall.getCallStatus());
	                history.setCreatedAt(LocalDateTime.now());
	                history.setReschedule(isReschedule); 
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

	                history.setNextSchedule(currentCall.getNextSchedule());
	                history.setPreviousStatus(currentCall.getCallStatus());
	                history.setCreatedAt(LocalDateTime.now());
	                history.setReschedule(isReschedule);
	                feedbackHistoryRepository.save(history);
	            }


	            return true;
	        }

	        return false;
	    }


}
