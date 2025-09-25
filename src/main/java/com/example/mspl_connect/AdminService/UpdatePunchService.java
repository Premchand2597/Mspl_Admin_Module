package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AttendenceEntity;
import com.example.mspl_connect.AdminEntity.InsertedAttendanceRecord_Entity;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.AdminRepo.InsertedAttendanceRecord_Repo;
import com.example.mspl_connect.Entity.AttendanceDTO;
import com.example.mspl_connect.Service.AttendanceServiceDto;

@Service
public class UpdatePunchService {

    @Autowired
    private AttendenceRepo attendenceRepo;
    
    @Autowired
	 private AttendanceServiceDto attendanceService;
    
    @Autowired
    private InsertedAttendanceRecord_Repo insertedAttendanceRecord_Repo;


    public boolean fillPunch(String empId, String date, String time) {
        try {
        	
        	// Append ':00' to the time string to add seconds
            if (time != null && !time.isEmpty()) {
                time = time + ":00"; // Add seconds to time (e.g., '13:31' becomes '13:31:00')
            }
        	String pdt="";
        	
        	if(time != null && !time.isEmpty() && date != null && !date.isEmpty()) {
        		pdt = date + " " + time;
        	}
            
        	System.out.println("pdt---"+pdt);
        	
        	System.out.println("empId---"+empId);
        	System.out.println("time---"+time);
        	System.out.println("date---"+date);
        	System.out.println("pdt---"+pdt);
        	
        	AttendanceDTO attendanceDTO = new AttendanceDTO();
        	
        	//AttendenceEntity punch = new AttendenceEntity();
        	attendanceDTO.setEid(empId);
        	attendanceDTO.setInTime(time);
        	attendanceDTO.setDate(date);
        	attendanceDTO.setPdt(pdt);
        	// Insert the record using custom repository method
        	// Call your service to save the attendance record
		     attendanceService.saveAttendance(attendanceDTO);
		     
            //attendenceRepo.insertAttendance(empId, time, date, pdt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<InsertedAttendanceRecord_Entity> fetchAllInsertedDetailsBasedOnEmpId(String empid){
    	return insertedAttendanceRecord_Repo.getAllInsertedDetailsBasedOnEmpId(empid);
    }
    
    public List<AttendenceEntity> checkDataPresentOnParticularDateForEmpId(String empId, String date){
    	return attendenceRepo.isDataPresentOnParticularDateForEmpId(empId, date);
    }
    
    public void insertValidAttendanceRecordWithStatus(InsertedAttendanceRecord_Entity insertedAttendanceRecord_Entity) {
    	insertedAttendanceRecord_Repo.save(insertedAttendanceRecord_Entity);
    }

}
