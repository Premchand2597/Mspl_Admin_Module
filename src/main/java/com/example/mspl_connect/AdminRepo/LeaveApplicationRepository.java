package com.example.mspl_connect.AdminRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.LeaveApplication;


public interface LeaveApplicationRepository  extends JpaRepository<LeaveApplication, Integer>  {
	/*List<LeaveApplication> findByEmployeeEmail(String email);*/
	
	List<LeaveApplication> findByEmployeeEmail(String employeeEmail);

	@Query(nativeQuery = true,value="SELECT COUNT(*) FROM LeaveApplication WHERE employee_email = :employeeEmail AND leave_type = :leaveType AND approved_status = :approvedstatus")
	int countByEmployeeEmailAndLeaveTypeAndApprovedstatus(String employeeEmail, String leaveType, String approvedstatus);

	@Query(value="SELECT COUNT(*) FROM LeaveApplication WHERE employee_email = :employeeEmail   AND from_date = :fromDate AND to_date = :toDate",nativeQuery = true)
	int countByEmployeeEmailAndFromDateAndToDate(String employeeEmail, String fromDate, String toDate);

	List<LeaveApplication> findByApprovedstatus(String string);

	@Query(nativeQuery = true,value="SELECT * FROM LeaveApplication WHERE employee_email = :employeeEmail AND leave_type = :leaveType AND approved_status = :approvedstatus")
	List<LeaveApplication> findByEmployeeEmailAndLeaveTypeAndApprovedstatus(String employeeEmail, String leaveType, String approvedstatus);

	@Query(value = "SELECT * FROM LeaveApplication WHERE leave_type = :leaveType AND approved_status = :approvedstatus", nativeQuery = true)
	List<LeaveApplication> findByLeaveTypeAndApprovedstatus(String leaveType, String approvedstatus);

	List<LeaveApplication> findByApprovedstatusAndProcessed(String string, boolean b);

	 //@Query(value = "SELECT * FROM LeaveApplication WHERE leave_type = :leaveType AND approved_status = :approvedstatus AND processed = :processed", nativeQuery = true)
	 //List<LeaveApplication> findByLeaveTypeAndApprovedstatusAndProcessed(String string, String string2, boolean b);
   
	 @Query("SELECT CASE WHEN COUNT(la) > 0 THEN true ELSE false END FROM LeaveApplication la WHERE la.id = :id")
	 boolean existsById(Long id);
	
	 @Query("SELECT la FROM LeaveApplication la WHERE la.processed = true")
	 List<LeaveApplication> findAllProcessedLeaveApplications();

	 @Query("SELECT la FROM LeaveApplication la WHERE la.employeeEmail = :email AND la.leaveType = :leaveType")
	 List<LeaveApplication> findByEmployeeEmailAndLeaveType(@Param("email") String email, @Param("leaveType") String leaveType);

	 
	 List<LeaveApplication> findByEmpidOrderByIdDesc(String empid);

	@Query(value = "SELECT * FROM LeaveApplication WHERE emp_id = :empid AND leave_type = :leaveType AND approved_status = 'Approved'", nativeQuery = true)
	List<LeaveApplication> findApprovedByEmpidAndLeaveType(String empid, String leaveType);

	@Query(value="SELECT * FROM leave_application WHERE department = :deptId AND empid = :empId AND way = 1", nativeQuery = true)
	List<LeaveApplication> getLeaveRequest(int deptId, String empId);
	
	List<LeaveApplication> findByEmpidAndApprovedstatus(String empId, String string);
	
	@Query("SELECT la FROM LeaveApplication la WHERE la.empid = :empid AND " +
            "la.approvedstatus = 'Approved' AND " +
            "((CAST(la.from_date AS DATE) BETWEEN :startDate AND :endDate) OR " +
            "(CAST(la.to_date AS DATE) BETWEEN :startDate AND :endDate))")
    List<LeaveApplication> getLeaveRecordsForEmployee(String empid, LocalDate startDate, LocalDate endDate);
	
	@Query(value = "SELECT * FROM leave_application la WHERE CAST(la.from_date AS DATE) > CURRENT_DATE AND la.employee_email = :email", nativeQuery = true)
    List<LeaveApplication> findFutureLeaveApplicationsByEmployeeEmail(@Param("email") String email);

	
//	@Query(value = "SELECT * FROM leave_application l WHERE l.empid = :empId " +
//            "AND ((MONTH(STR_TO_DATE(l.from_date, '%Y-%m-%d')) = :month AND YEAR(STR_TO_DATE(l.from_date, '%Y-%m-%d')) = :year) " +
//            "OR (MONTH(STR_TO_DATE(l.to_date, '%Y-%m-%d')) = :month AND YEAR(STR_TO_DATE(l.to_date, '%Y-%m-%d')) = :year)) " +
//            "AND l.approvedstatus = 'Approved'", nativeQuery = true)
//	List<LeaveApplication> findLeavesForMonthAndYear(@Param("empId") String empId, 
//                                               @Param("month") int month,
//                                               @Param("year") int year);
	
	
	@Query(value = """
			
			SELECT * 
			FROM leave_application l 
			WHERE l.empid = :empId 
			AND (
			(EXTRACT(MONTH FROM TO_DATE(l.from_date, 'YYYY-MM-DD')) = :month AND EXTRACT(YEAR FROM TO_DATE(l.from_date, 'YYYY-MM-DD')) = :year)
			OR (EXTRACT(MONTH FROM TO_DATE(l.to_date, 'YYYY-MM-DD')) = :month AND EXTRACT(YEAR FROM TO_DATE(l.to_date, 'YYYY-MM-DD')) = :year)
			)
			AND l.approvedstatus = 'Approved';
				
				""", nativeQuery = true)
			List<LeaveApplication> findLeavesForMonthAndYear(@Param("empId") String empId, 
			                                     @Param("month") int month,
			                                     @Param("year") int year);
	
	
	LeaveApplication findById(int id);

	@Query(nativeQuery = true, value = "SELECT * FROM leave_application request WHERE empid = :approvingLeaveEmpId AND approved_by = 'The Melange Team' AND :approvingLeaveFromDate BETWEEN from_date AND to_date")
	Optional<LeaveApplication> getSystemGeneratedRequestByEmpid(String approvingLeaveEmpId, String approvingLeaveFromDate);

	@Query(nativeQuery = true, value = "SELECT * FROM leave_application WHERE from_date <= :toDate AND to_date >= :fromDate and empid = :empid		 and approvedstatus = 'Approved'")
	List<LeaveApplication> findOverlappingApprovedLeaves(
		    @Param("empid") String empid,
		    @Param("fromDate") String fromDate,
		    @Param("toDate") String toDate
		);
	
}
