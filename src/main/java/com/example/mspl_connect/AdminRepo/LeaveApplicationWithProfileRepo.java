package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;

public interface LeaveApplicationWithProfileRepo {
	
	 List<LeaveApplicationWithProfile> getNewleaveRequestWithProfile(String empId);
	 List<LeaveApplicationWithProfile> getNewleaveRequestWithProfileForSA1();
	 
	 List<LeaveApplicationWithProfile> getleaveRequestByEmpID(String empId);
	 
	 List<LeaveApplicationWithProfile> findByEmpidNot(String empId);
	 List<LeaveApplicationWithProfile> getProccessedleaveRequest(String empId);
	 List<LeaveApplicationWithProfile> getProccessedleaveRequestFoeSA(String empId);
	 
	 List<LeaveApplicationWithProfile> getRejectedleaveRequest(String empId);
	 List<LeaveApplicationWithProfile> getRejectedleaveRequestForSA(String empId);
	 

}
