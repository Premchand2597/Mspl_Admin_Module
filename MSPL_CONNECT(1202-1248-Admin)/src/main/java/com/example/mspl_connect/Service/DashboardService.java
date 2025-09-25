package com.example.mspl_connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Repository.EmployeeRepository;

@Service
public class DashboardService {

	@Autowired
	private EmployeeRepository totalPresentCount;
	
	public int totalPresentCount() {
		return totalPresentCount.totalPresentCount();
	}
	
}
