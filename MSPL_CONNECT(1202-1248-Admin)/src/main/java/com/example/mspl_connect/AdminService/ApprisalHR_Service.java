package com.example.mspl_connect.AdminService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;
import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminRepo.ApprisalHR_Repo;
import com.example.mspl_connect.AdminRepo.EmployeeCustomDetailsForApprisal_Repo;

import jakarta.transaction.Transactional;

@Service
public class ApprisalHR_Service {

	@Autowired
	private ApprisalHR_Repo apprisalHR_Repo;
	
	@Autowired
	private EmployeeCustomDetailsForApprisal_Repo employeeCustomDetailsForApprisal_Repo;

	
	public List<EmployeeCustomDetailsForApprisal_Entity> getAllEmployeeNames(){
		return employeeCustomDetailsForApprisal_Repo.fetchAllEmployeeNames();
	}
	
	public EmployeeCustomDetailsForApprisal_Entity getAllEmployeeDetailsBasedOnEmpNames(String emp_full_name_with_emp_id){
		return employeeCustomDetailsForApprisal_Repo.fetchEmployeeDetailsBasedOnEmpNames(emp_full_name_with_emp_id);
	}
}
