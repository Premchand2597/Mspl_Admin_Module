package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.SalaryDetailsEntity;


@Repository
public interface SalaryDetailsRepo extends JpaRepository<SalaryDetailsEntity, Integer>{
	
	// Method to find salary details by empId  financial_year
    List<SalaryDetailsEntity> findByEmpIdOrderByFinancialYearDesc(String empId);

    @Query(nativeQuery = true, value="select * from salary_details where financial_year=:financialYear")
	List<SalaryDetailsEntity> findByFinancialYear(String financialYear);  

}
