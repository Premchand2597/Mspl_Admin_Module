package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

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
	
}
