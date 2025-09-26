package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.YearFlag;



@Repository
public interface YearFlagRepository extends JpaRepository<YearFlag, Integer>{

//	boolean existsByYear(int year);
	boolean existsByYear(String year);
	 boolean existsByYearAndEmployeeId(String financialYear, String string);
	boolean existsByYearAndEmployeeIdAndMonth(String financialYear, String empId, String currentMonth);
}
