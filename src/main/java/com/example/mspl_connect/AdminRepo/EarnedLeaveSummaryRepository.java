package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.EarnedLeaveSummary;


@Repository
public interface EarnedLeaveSummaryRepository extends JpaRepository<EarnedLeaveSummary, Long> {

	 @Query("SELECT e FROM EarnedLeaveSummary e WHERE e.empId = :empId")
	    List<EarnedLeaveSummary> getEarnedLeaveSummaryByEmpid(@Param("empId") String empId);
	
}
