package com.example.mspl_connect.AdminService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminRepo.EmployeeCustomDetailsForApprisal_Repo;


@Service
public class EmployeeCustomDetails_Service {

	@Autowired
	private EmployeeCustomDetailsForApprisal_Repo employeeCustomDetailsForApprisal_Repo;
	
	public List<EmployeeCustomDetailsForApprisal_Entity> fetchAllACtiveEmployeesDistinctUserNames(){
		
		List<EmployeeCustomDetailsForApprisal_Entity> fetchAllActiveEmployeeDetails = employeeCustomDetailsForApprisal_Repo.fetchAllActiveEmployeeDetails();
		List<EmployeeCustomDetailsForApprisal_Entity> customizedEmpDetails = new ArrayList<>();
		
		for(EmployeeCustomDetailsForApprisal_Entity data : fetchAllActiveEmployeeDetails) {
			String distinctUserNames = data.getEmp_full_name()+"-"+data.getEmp_id();
			data.setEmp_full_name(distinctUserNames);
			customizedEmpDetails.add(data);
		}
		return customizedEmpDetails;
	}
	
	public EmployeeCustomDetailsForApprisal_Entity fetchACtiveEmployeesDistinctUserNamesBasedOnEmail(String email){
		
		EmployeeCustomDetailsForApprisal_Entity fetchActiveEmployeeDetailsOnEmail = employeeCustomDetailsForApprisal_Repo.fetchActiveEmployeeDetailsBasedOnEmail(email);
		String distinctUserNames = fetchActiveEmployeeDetailsOnEmail.getEmp_full_name()+"-"+fetchActiveEmployeeDetailsOnEmail.getEmp_id();
		fetchActiveEmployeeDetailsOnEmail.setEmp_full_name(distinctUserNames);
		
		return fetchActiveEmployeeDetailsOnEmail;
	}
}
