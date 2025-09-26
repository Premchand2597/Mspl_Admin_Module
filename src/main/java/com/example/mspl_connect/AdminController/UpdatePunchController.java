package com.example.mspl_connect.AdminController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.AttendenceEntity;
import com.example.mspl_connect.AdminEntity.InsertedAttendanceRecord_Entity;
import com.example.mspl_connect.AdminEntity.PunchRequest;
import com.example.mspl_connect.AdminService.UpdatePunchService;
import com.example.mspl_connect.Repository.EmployeeRepository;

@RestController
public class UpdatePunchController {

    @Autowired
    private UpdatePunchService punchService;
    
    @Autowired
	private EmployeeRepository employeeRepository;
    
    @PostMapping("/fillPunch")
    public ResponseEntity<Map<String, Object>> fillPunch(@RequestBody PunchRequest punchRequest) {
    	
        System.out.println("punchRequest-----"+punchRequest);  // Log incoming request
        Map<String, Object> response = new HashMap<>();

        try {
            boolean isFilled = punchService.fillPunch(punchRequest.getEmpId(), punchRequest.getDate(), punchRequest.getTime());

            if (isFilled) {
                response.put("success", true);
                response.put("message", "Punch filled successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to fill punch. Please try again.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("fetchInsertedAttendanceRecordsBasedOnEmpid")
    @ResponseBody
    public List<InsertedAttendanceRecord_Entity> fetchAllInsertedDetailsBasedOnEmpId(@RequestParam String email){
    	 String empId = employeeRepository.findEmpidByEmail(email);
    	return punchService.fetchAllInsertedDetailsBasedOnEmpId(empId);
    }
    
    @PostMapping("/insertNewAttendanceRecordBasedOnEmpId")
    public ResponseEntity<String> insertNewRecordForAttendance(@RequestBody PunchRequest punchRequest, @RequestParam String status, 
    		@RequestParam(required = false) String compensated_date, @RequestParam(required = false) String wfh_reason, 
    		@RequestParam(required = false) String onduty_reason, @RequestParam(required = false) String others_reason) {

    	String timeValue = "", pdt = "", defaultWFHReason = null, defaultOnDutyReason = null, defaultOthersReason = null;
    	
		if (punchRequest.getTime() != null && !punchRequest.getTime().isEmpty()) {
			timeValue = punchRequest.getTime() + ":00";
        }
    	
    	if(punchRequest.getDate() != null && !punchRequest.getDate().isEmpty()) {
    		pdt = punchRequest.getDate() + " " + timeValue;
    	}
    	
    	InsertedAttendanceRecord_Entity inserValidData = new InsertedAttendanceRecord_Entity();
    	
    	if(compensated_date !=null && !compensated_date.isEmpty()) {
    		List<AttendenceEntity> isAttendanceDataPresent = punchService.checkDataPresentOnParticularDateForEmpId(punchRequest.getEmpId(), compensated_date);
    		if(!isAttendanceDataPresent.isEmpty()) {
    			inserValidData.setCompensate_date(compensated_date);
    		}else{
    			return ResponseEntity.ok("No data found");
    		}
    	}
    	
    	if(wfh_reason != null && !wfh_reason.isEmpty()){
    		defaultWFHReason = wfh_reason;
    	}
    	
    	if(onduty_reason != null && !onduty_reason.isEmpty()){
    		defaultOnDutyReason = onduty_reason;
    	}
    	
    	if(others_reason != null && !others_reason.isEmpty()){
    		defaultOthersReason = others_reason;
    	}
    	
    	inserValidData.setEid(punchRequest.getEmpId());
		inserValidData.setPt(timeValue);
		inserValidData.setPdt(pdt);
		inserValidData.setPd(punchRequest.getDate());
		inserValidData.setInserted_as(status);
		inserValidData.setWfh_reason(defaultWFHReason);
		inserValidData.setOnduty_reason(defaultOnDutyReason);
		inserValidData.setOthers_reason(defaultOthersReason);
		punchService.insertValidAttendanceRecordWithStatus(inserValidData);
    	
    	boolean isFilled = punchService.fillPunch(punchRequest.getEmpId(), punchRequest.getDate(), punchRequest.getTime());
    	if (isFilled) {
            return ResponseEntity.ok("Attendance Inserted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to insert Attendance data");
        }
    }
}

