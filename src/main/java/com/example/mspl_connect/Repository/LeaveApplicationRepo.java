package com.example.mspl_connect.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;

import jakarta.transaction.Transactional;

@Repository
public interface LeaveApplicationRepo extends JpaRepository<LeaveApplication, Integer>{
	 
	 @Query(value="SELECT count(way) FROM leave_application la inner join employee_details ed on la.empid = ed.empid where (ed.team_lead_name=:empid or ed.team_co_name=:empid) and la.way=1 and la.empid != :empid",nativeQuery = true)
	 int getNewRequestCount(String empid);
	 
	 @Query(value="SELECT count(way) FROM leave_application where way=1 AND empid != :empid",nativeQuery = true)
	 int getNewRequestCountForSA(String empid);
	 
	 @Query(nativeQuery = true, value="select count(notification_status) from leave_application where employee_email = :email and notification_status = 1")
	 int getNotificationStatusByEmailId(String email);
	 
//	 @Query(value="select empid from  leave_application where id=:id",nativeQuery = true)
//	 String getEmpIdByID(String id);
	 
	 @Query(value="select empid from  leave_application where id=cast(:id as integer)",nativeQuery = true)
	 String getEmpIdByID(String id);
	 
//	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.department = :deptId and  la.empid != :empId and la.way=1 order by id desc" , nativeQuery = true)
//	 List<LeaveApplication> getleaveRequest(int deptId,String empId);
	 
	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.department::int = :deptId and  la.empid != :empId and la.way::int=1 order by id desc" , nativeQuery = true)
	 List<LeaveApplication> getleaveRequest(int deptId,String empId);
	 
	 @Query(value="select la.* from leave_application la inner join employee_details ed on la.empid = ed.empid where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and la.way=1" , nativeQuery = true)
	 List<LeaveApplication> getleaveRequestByEmpID(String empId);
	 
	 @Query(value="select la.* from leave_application la inner join employee_details ed on la.empid = ed.empid where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and la.approvedstatus='Approved' order by la.id desc" , nativeQuery = true)
	 List<LeaveApplication> getProccessedleaveRequest(String empId);
	 
	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.empid != :empId and approvedstatus='Approved' order by la.id desc" , nativeQuery = true)
	 List<LeaveApplication> getProccessedleaveRequestFoeSA(String empId);
	 
	 @Query(value="select la.* from leave_application la inner join employee_details ed on la.empid = ed.empid where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and la.approvedstatus='Rejected' order by la.id desc" , nativeQuery = true)
	 List<LeaveApplication> getRejectedleaveRequest(String empId);
	 
	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.empid != :empId and la.approvedstatus='Rejected' order by la.id desc" , nativeQuery = true)
	 List<LeaveApplication> getRejectedleaveRequestForSA(String empId);
	 
	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.empid != :empId and la.way=1 order by la.id desc" , nativeQuery = true)
	 List<LeaveApplication> findByEmpidNot(String empId);
	 
	 @Query(value="SELECT la.* FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.department = :deptId and  la.empid != :empId and la.way=1" , nativeQuery = true)
	 List<LeaveApplication> getNewleaveRequest(int deptId,String empId);
	 
	 @Query(value="SELECT la.*,ed.profile_pic_path FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.way=1" , nativeQuery = true)
	 List<LeaveApplication> getFullleaveRequest();
	 
	 //@Query(nativeQuery = true,value="SELECT la.*, ed.profile_pic_path FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.empid=:empId")
	 //Optional<LeaveApplication> findByEmpid(String empId);
	 
//	 @Modifying
//	 @Query(value = "UPDATE leave_application SET approvedstatus = :approvedStatus, approved_by=:loggedUserName,way=0,notification_status=1,approved_date_time=:currentDateTime WHERE id = :id", nativeQuery = true)
//	 void updateApprovalStatus(String id,String approvedStatus,String loggedUserName,String currentDateTime);
	 
	 @Modifying
	 @Query(value = "UPDATE leave_application SET approvedstatus = :approvedStatus, approved_by=:loggedUserName,way=0,notification_status=1,approved_date_time=:currentDateTime WHERE id = cast(:id as integer)", nativeQuery = true)
	 void updateApprovalStatus(String id,String approvedStatus,String loggedUserName,String currentDateTime);
	 
//	 @Modifying
//	 @Transactional
//	 @Query(value = "UPDATE leave_application SET approvedstatus = :status, approved_by=:loggedUserName,rejection_reason=:reason,way=0,notification_status=1,approved_date_time=:currentDateTime WHERE id = :id", nativeQuery = true)
//	 void updateRejectedStatus(String id, String status, String loggedUserName, String reason,String currentDateTime);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE leave_application SET approvedstatus = :status, approved_by=:loggedUserName,rejection_reason=:reason,way=0,notification_status=1,approved_date_time=:currentDateTime WHERE id = cast(:id as integer)", nativeQuery = true)
	 void updateRejectedStatus(String id, String status, String loggedUserName, String reason,String currentDateTime);	 
	 
	 @Modifying
	 @Transactional
	 @Query(value = "update leave_application set notification_status = 0 where employee_email = :email", nativeQuery = true)
	 void updateNotificationStatus(String email);
	 
	/*@Query(value="SELECT "
			+ "    id, empid, approvedstatus, approved_by, department, employee_name, "
			+ "    DATE_FORMAT(from_date, '%d-%m-%Y') AS from_date,  "
			+ "    DATE_FORMAT(to_date, '%d-%m-%Y') AS to_date,  "
			+ "    reason, reporting_to, employee_email, processed, rejection_reason,  "
			+ "    way, previous_status, notification_status, timestamp, status_count,  "
			+ "    out_of_station, available_on_phone, email_access, approving_authority,  "
			+ "    requested_date, leave_duration, remarks, is_date_modified ,  "
			+ "    old_from_date, old_to_date, edit_flag "
			+ "FROM leave_application "
			+ "WHERE id = :requestId",nativeQuery = true)
	LeaveApplication findById(String requestId);
*/
	 
//		@Query(value="select * from leave_application where id=:requestId",nativeQuery = true)
//		LeaveApplication findById(String requestId);
	 
	 	@Query(value="select * from leave_application where id=cast(:requestId as integer)",nativeQuery = true)
		LeaveApplication findById(String requestId);
	 
}
