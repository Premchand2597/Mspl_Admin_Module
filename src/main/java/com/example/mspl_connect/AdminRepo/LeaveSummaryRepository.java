package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.AdminEntity.LeaveSummary;


public interface LeaveSummaryRepository extends JpaRepository<LeaveSummary, Long>  {

	public List<LeaveSummary> getLeaveSummaryByEmpid(String empid);

	
}
