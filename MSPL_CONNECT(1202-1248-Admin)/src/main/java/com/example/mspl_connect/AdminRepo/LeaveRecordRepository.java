package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.LeaveRecord;
import com.example.mspl_connect.AdminEntity.LeaveUtilized;


public interface LeaveRecordRepository extends JpaRepository<LeaveRecord, Long>{

	//List<LeaveRecord> findByMonthAndYear(String month, int year);

	LeaveRecord findByEmailAndLeaveTypeAndMonthAndYear(String employeeEmail, String string, String currentMonth,
			String financialYear);

	int countByLeaveTypeAndEmail(String leaveType, String email);

	List<LeaveRecord> findByEmailAndLeaveType(String employeeEmail, String leaveType);
	
	List<LeaveRecord> findByEmpId(String empId); 
	
	@Query("SELECT lu FROM LeaveUtilized lu WHERE lu.userEmail = ?1 AND lu.financialYear = ?2 AND lu.leaveType = ?3")
	LeaveUtilized findByUserEmailAndFinancialYearAndLeaveType(String userEmail, String financialYear, String leaveType);


	//List<LeaveRecord> findByEmailAndLeaveType(String string, String userEmail);

	//int countByLeaveType(String leaveType);
	
	/* @Query("SELECT COUNT(l) FROM LeaveRecord l WHERE l.email = :userEmail AND l.leaveType = :leaveType")
	    int findUsedLeaveCountByUserEmailAndLeaveType(@Param("userEmail") String userEmail, @Param("leaveType") String leaveType);*/

/*	 @Query("SELECT l.used FROM LeaveRecord l WHERE l.email = :userEmail AND l.leaveType = :leaveType")
	 List<Integer> findUsedLeaveValuesByUserEmailAndLeaveType(@Param("userEmail") String userEmail, @Param("leaveType") String leaveType);

	 
	boolean existsByEmailAndLeaveTypeAndMonthAndYear(String employeeEmail, String leaveTypeCode, String currentMonth,
			int currentYear);
	
	

	 @Query("SELECT lr FROM LeaveRecord lr WHERE lr.leaveType = ?1 AND lr.month = ?2 AND lr.email = ?3")
	 LeaveRecord findByLeaveTypeAndMonthAndEmail(String leaveType, String month, String email);

	List<LeaveRecord> findByEmail(String email);

	List<LeaveRecord> findByMonthAndEmail(String currentMonth, String email);

	boolean existsByLeaveTypeAndMonthAndEmail(String leaveType, String monthStr, String email);

	LeaveRecord findByEmailAndLeaveTypeAndYear(String employeeEmail, String string, String currentYear);


	 // Method to fetch the added leave count for a specific leave type and user email
    @Query("SELECT lr.added FROM LeaveRecord lr WHERE lr.leaveType = :leaveType AND lr.email = :email")
    int findAddedByLeaveTypeAndEmail(@Param("leaveType") String leaveType, @Param("email") String email);
    
    @Query("SELECT SUM(l.remaining) FROM LeaveRecord l WHERE l.leaveType = :leaveType")
    int sumRemainingLeaveByType(@Param("leaveType") String leaveType);

	//LeaveRecord findByLeaveTypeAndEmail(String leaveType, String email);*/

	List<LeaveRecord> findByLeaveTypeAndEmail(String leaveType, String email);



}
