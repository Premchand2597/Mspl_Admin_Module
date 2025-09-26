package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;

@Repository
public interface LeaveApplicationWithProfileRepo extends JpaRepository<LeaveApplicationWithProfile, Integer>{
	
	 @Query(value="SELECT la.*,ed.profile_pic_path FROM leave_application la inner join employee_details ed on la.empid=ed.empid where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and  la.empid != :empId and la.way=1" , nativeQuery = true)
	 List<LeaveApplicationWithProfile> getNewleaveRequestWithProfile(String empId);
	 
	 @Query(value="SELECT la.*,ed.profile_pic_path FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.way=1" , nativeQuery = true)
	 List<LeaveApplicationWithProfile> getNewleaveRequestWithProfileForSA1();

}
