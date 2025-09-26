package com.example.mspl_connect.AdminController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.PunchRequest;
import com.example.mspl_connect.AdminService.UpdatePunchService;

@RestController
public class UpdatePunchController {

    @Autowired
    private UpdatePunchService punchService;

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
}

