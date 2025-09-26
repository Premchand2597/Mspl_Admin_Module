package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.SalaryDetailsEntity;
import com.example.mspl_connect.AdminRepo.SalaryDetailsRepo;

@Service
public class SalaryDetailsService {
	
	@Autowired
	private SalaryDetailsRepo salaryDetailsRepo; 
	
	public List<SalaryDetailsEntity> findByEmpId(String empId){
		return salaryDetailsRepo.findByEmpIdOrderByFinancialYearDesc(empId);
	}
	
	public List<SalaryDetailsEntity> getSalaryDetailsByFiancialYear(){
		String financialYear="(Apr-2024) - (March-2025)";
		return salaryDetailsRepo.findByFinancialYear(financialYear);
	} 	
	
}
