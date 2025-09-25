package com.example.mspl_connect.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.AttendanceReportGeneratorFinal_DTO;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;


public interface AttendanceRepositoryCustom {

	List<AttendanceDTO> findAttendanceByMonthAndEmployee(String month, String employeeId);
	
	List<AttendanceDTO> findAttendanceByyearAndEmployee(int year, String employeeId);
	
	List<AttendanceDTO> findAttendanceByYearAndEmpId(int year, String employeeId,String fromDate,String toDate);

	List<AttendanceDTO> findAttendanceByDateAndEmployee(String date, String employeeId);
	
	List<AttendanceDTO> findAttendanceByyearAndEmployeeCalendar(int year, String employeeId);
	
	List<AttendanceDTO> findAttendanceByEmployee(String employeeId);

	List<AttendanceDTO> findAttendanceByMonthAndEmployee(String month, String employeeId, int year); //Table View
	
	List<AttendanceDTO> findAttendanceByDateRange(LocalDate startDate, LocalDate endDate, String employeeId) ;
	
	List<AttendanceDTO> findAttendanceBetweenDates(String empId, LocalDate startDate, LocalDate endDate); // View Data
	
	//List<AttendanceDTO> fetchAttendanceByCurrentyearAndCurrentMonthForLoggedInHR(String employeeId);
	List<AttendanceDTO> findAttendanceByEmployeeNotWithYear(String employeeId);
	
	List<AttendanceReportGeneratorFinal_DTO> fetchEmployeeAttendanceForReportGenerator(@Param("startDate") String startDate,
            @Param("endDate") String endDate);
	
	List<AttendanceReportGeneratorFinal_DTO> fetchEmployeeAttendanceForReportGeneratorBasedOnEmpId(@Param("startDate") String startDate,
            @Param("endDate") String endDate, @Param("emp_id") String emp_id);
	
}
