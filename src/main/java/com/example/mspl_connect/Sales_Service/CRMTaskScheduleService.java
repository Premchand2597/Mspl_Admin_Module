package com.example.mspl_connect.Sales_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.CRMTaskSchedule;
import com.example.mspl_connect.Sales_Repository.CRMTaskScheduleRepository;

@Service
public class CRMTaskScheduleService {
	 @Autowired
	    private CRMTaskScheduleRepository taskRepository;

	    public CRMTaskSchedule saveTask(CRMTaskSchedule task) {
	        return taskRepository.save(task);
	    }
}
