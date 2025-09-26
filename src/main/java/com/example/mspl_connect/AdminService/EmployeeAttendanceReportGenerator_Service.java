package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AttendanceReportGeneratorFinal_DTO;
import com.example.mspl_connect.Repository.AttendanceRepositoryCustom;


@Service
public class EmployeeAttendanceReportGenerator_Service {

	//@Autowired
	//private EmployeeAttendanceReportGenerator_Repo employeeAttendanceReportGenerator_Repo;
	
	@Autowired
	private AttendanceRepositoryCustom attendanceRepositoryCustom;
	
	/*@Transactional
	public EmployeeAttendanceReportGenerator_Entity fetchAttendanceReportByEmpId(String month, String year, String emp_id) {
		return employeeAttendanceReportGenerator_Repo.generateAttendanceReportBasedOnEmpid(month, year, emp_id);
	}
	
	@Transactional
	public List<EmployeeAttendanceReportGenerator_Entity> fetchAllAttendanceReport(String month, String year) {
		return employeeAttendanceReportGenerator_Repo.generateAttendanceReport(month, year);
	}*/
	
	public List<AttendanceReportGeneratorFinal_DTO> getAttendanceReportGenerator(String startDate, String endDate) {
        return attendanceRepositoryCustom.fetchEmployeeAttendanceForReportGenerator(startDate, endDate);
    }
	
	public List<AttendanceReportGeneratorFinal_DTO> getAttendanceReportGeneratorByUsingEmpId(String startDate, String endDate, String emp_id) {
		//System.out.println("attendanceRepositoryCustom.fetchEmployeeAttendanceForReportGeneratorBasedOnEmpId(startDate, endDate, emp_id) == "+attendanceRepositoryCustom.fetchEmployeeAttendanceForReportGeneratorBasedOnEmpId(startDate, endDate, emp_id));
        return attendanceRepositoryCustom.fetchEmployeeAttendanceForReportGeneratorBasedOnEmpId(startDate, endDate, emp_id);
    }
}
