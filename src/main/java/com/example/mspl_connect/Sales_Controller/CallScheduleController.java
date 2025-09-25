package com.example.mspl_connect.Sales_Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.Sales_Entity.CallFeedbackHistory;
import com.example.mspl_connect.Sales_Entity.CallSchedule;
import com.example.mspl_connect.Sales_Repository.CallFeedbackHistoryRepository;
import com.example.mspl_connect.Sales_Repository.CallScheduleRepository;
import com.example.mspl_connect.Sales_Service.CallScheduleService;

@Controller
public class CallScheduleController {

	
	 @Autowired
	    private CallScheduleService callScheduleService;
	    
	 
	 @Autowired
	    private CallScheduleRepository callScheduleRepository;
	    
	 @Autowired
	    private CallFeedbackHistoryRepository feedbackHistoryRepository;

	 
	 @PostMapping("/call-schedulesave")
	 public ResponseEntity<?> saveCallSchedule(@RequestBody CallSchedule callSchedule) {
	     // Basic validation
	     if (callSchedule.getCompanyName() == null || callSchedule.getCompanyName().isEmpty()
	         || callSchedule.getFromTime() == null
	         || callSchedule.getToTime() == null
	         || callSchedule.getContactMethod() == null || callSchedule.getContactMethod().isEmpty()) {
	         
	         return ResponseEntity.badRequest().body("Required fields are missing");
	     }

	     CallSchedule saved = callScheduleService.saveCallSchedule(callSchedule);
	     return ResponseEntity.ok(saved);
	 }

	    
	    // Endpoint to fetch all call schedules
	    @GetMapping("/call-scheduleall")
	    @ResponseBody
	    public List<CallSchedule> getAllCallSchedules() {
	        return callScheduleService.getAllCallSchedules();
	    }
	    
	    
	   
	    
	    @GetMapping("/call-scheduleget/{id}")
	    public ResponseEntity<CallSchedule> getCallScheduleById(@PathVariable Long id) {
	        Optional<CallSchedule> schedule = callScheduleService.getCallScheduleById(id);
	        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping("/call-schedule-history/{id}")
	    public ResponseEntity<List<CallFeedbackHistory>> getCallScheduleHistory(@PathVariable Long id) {
	        List<CallFeedbackHistory> historyList = feedbackHistoryRepository.findByCallScheduleId(id);
	        return ResponseEntity.ok(historyList);
	    }

	    @PutMapping("/update-status/{id}/{status}")
	    public ResponseEntity<String> updateCallStatus(@PathVariable Long id, @PathVariable Integer status) {
	        boolean updated = callScheduleService.updateCallStatus(id, status);
	        
	        if (updated) {
	            return ResponseEntity.ok("Call status updated successfully");
	        } else {
	            return ResponseEntity.status(500).body("Failed to update call status");
	        }
	    }
	    
	    @PutMapping("/markCallAttempted/{id}/{status}")
	    public ResponseEntity<String> updateCallStatushold(@PathVariable Long id, @PathVariable Integer status) {
	        boolean updated = callScheduleService.updateCallStatus(id, status);
	        
	        if (updated) {
	            return ResponseEntity.ok("Call status updated successfully");
	        } else {
	            return ResponseEntity.status(500).body("Failed to update call status");
	        }
	    }
	    
	    
	 

/*	    @PostMapping("/call-feedback/{id}")
	    public ResponseEntity<String> submitCallFeedback(@PathVariable Long id, @RequestBody Map<String, Object> feedbackData) {
	        try {
	            String callMadeTime = (String) feedbackData.getOrDefault("callMadeTime", feedbackData.get("meetingDateTime"));
	            String feedback = (String) feedbackData.getOrDefault("feedback", feedbackData.get("meetingNotes"));

	            boolean updated = callScheduleService.updateCallStatusWithFeedback(
	                id,
	                (Integer) feedbackData.get("status"),
	                callMadeTime,
	                feedback,
	                (String) feedbackData.get("nextAction"),
	                (String) feedbackData.getOrDefault("nextSchedule", null)
	            );

	            if (updated) {
	                return ResponseEntity.ok("Feedback submitted successfully");
	            } else {
	                return ResponseEntity.status(404).body("Call schedule not found");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Failed to submit feedback");
	        }
	    }*/
	    
	    @PostMapping("/call-feedback/{id}")
	    public ResponseEntity<String> submitCallFeedback(@PathVariable Long id, @RequestBody Map<String, Object> feedbackData) {
	        try {
	            // Determine callMadeTime with priority: callMadeTime > meetingDateTime > taskCompletedDate
	        	 System.out.println("Received feedbackData: " + feedbackData);

	             // Print individual fields explicitly to check their values
	             System.out.println("callMadeTime: " + feedbackData.get("callMadeTime"));
	             System.out.println("meetingDateTime: " + feedbackData.get("meetingDateTime"));
	             System.out.println("taskCompletedDate: " + feedbackData.get("taskCompletedDate"));

	             System.out.println("feedback: " + feedbackData.get("feedback"));
	             System.out.println("meetingNotes: " + feedbackData.get("meetingNotes"));
	             System.out.println("taskDescription: " + feedbackData.get("taskDescription"));

	             System.out.println("status: " + feedbackData.get("status"));
	             System.out.println("nextAction: " + feedbackData.get("nextAction"));
	             System.out.println("nextSchedule: " + feedbackData.get("nextSchedule"));
	             System.out.println("nextScheduldelayyyyye: " + feedbackData.get("taskDelayReason"));
	            String callMadeTime = null;
	            if (feedbackData.containsKey("callMadeTime")) {
	                callMadeTime = (String) feedbackData.get("callMadeTime");
	            } else if (feedbackData.containsKey("meetingDateTime")) {
	                callMadeTime = (String) feedbackData.get("meetingDateTime");
	            } else if (feedbackData.containsKey("taskCompletedDate")) {
	                callMadeTime = (String) feedbackData.get("taskCompletedDate");
	            }

	            // Determine feedback with priority: feedback > meetingNotes > taskDescription
	            String feedback = null;
	            if (feedbackData.containsKey("feedback")) {
	                feedback = (String) feedbackData.get("feedback");
	            } else if (feedbackData.containsKey("meetingNotes")) {
	                feedback = (String) feedbackData.get("meetingNotes");
	            } else if (feedbackData.containsKey("taskDescription")) {
	                feedback = (String) feedbackData.get("taskDescription");
	            }else if (feedbackData.containsKey("rescheduleReason")) {
	                feedback = (String) feedbackData.get("rescheduleReason");
	            }
	            boolean isReschedule = feedbackData.containsKey("rescheduleReason");
	            String taskDelayReason = (String) feedbackData.get("taskDelayReason");
	            System.out.println("nextScheduldelayyyyye: " + feedbackData.get("taskDelayReason"));
	            boolean updated = callScheduleService.updateCallStatusWithFeedback(
	                id,
	                (Integer) feedbackData.get("status"),
	                callMadeTime,
	                feedback,
	                (String) feedbackData.get("nextAction"),
	                (String) feedbackData.getOrDefault("nextSchedule", null),
	                isReschedule,
	                taskDelayReason // NEW PARAMETER
	            );

	            if (updated) {
	                return ResponseEntity.ok("Feedback submitted successfully");
	            } else {
	                return ResponseEntity.status(404).body("Call schedule not found");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Failed to submit feedback");
	        }
	    }


	    
	    @PostMapping("/savemeeting")
	    public ResponseEntity<CallSchedule> saveCall(@RequestBody CallSchedule callSchedule) {
	        CallSchedule saved = callScheduleService.saveCall(callSchedule);
	        return ResponseEntity.ok(saved);
	    }
	    
	    @PostMapping("/savetask")
	    public ResponseEntity<String> saveCallScheduletask(@RequestBody CallSchedule callSchedule) {
	        try {
	            callScheduleRepository.save(callSchedule);
	            return ResponseEntity.ok("Call Schedule saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Failed to save call schedule.");
	        }
	    }
}
