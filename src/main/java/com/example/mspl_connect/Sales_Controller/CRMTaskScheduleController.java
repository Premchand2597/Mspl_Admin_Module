package com.example.mspl_connect.Sales_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.Sales_Entity.CRMTaskSchedule;
import com.example.mspl_connect.Sales_Service.CRMTaskScheduleService;

@RestController
@RequestMapping("/tasks")
public class CRMTaskScheduleController {
	 @Autowired
	    private CRMTaskScheduleService taskService;

	    @PostMapping("/save")
	    public ResponseEntity<CRMTaskSchedule> saveTask(@RequestBody CRMTaskSchedule task) {
	        CRMTaskSchedule savedTask = taskService.saveTask(task);
	        return ResponseEntity.ok(savedTask);
	    }
}
