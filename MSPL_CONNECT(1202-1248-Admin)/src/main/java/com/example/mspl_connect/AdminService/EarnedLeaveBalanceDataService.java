package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EarnedLeaveBalanceData;
import com.example.mspl_connect.AdminRepo.EarnedLeaveBalanceDataRepository;


@Service
public class EarnedLeaveBalanceDataService {

	 private final EarnedLeaveBalanceDataRepository earnedLeaveBalanceDataRepository;

	    @Autowired
	    public EarnedLeaveBalanceDataService(EarnedLeaveBalanceDataRepository earnedLeaveBalanceDataRepository) {
	        this.earnedLeaveBalanceDataRepository = earnedLeaveBalanceDataRepository;
	    }
	
	 // Method to insert a new record every time
    public EarnedLeaveBalanceData insertEarnedLeaveBalanceData(String email, String empId, double earnedLeave, double elUsed, double elRemaining, LocalDate lastIncrementDate, String monthYear) {
    	
    	System.out.println("ppppp");
        EarnedLeaveBalanceData data = new EarnedLeaveBalanceData();
        data.setEmail(email);
        data.setEmpId(empId);
        data.setEarnedLeave(earnedLeave);
        data.setElUsed(elUsed);
        data.setElRemaining(elRemaining);
        data.setLastIncrementDate(lastIncrementDate != null ? lastIncrementDate.toString() : null);
        data.setMonthYear(monthYear);
        System.out.println("ppppp " +data );
        return earnedLeaveBalanceDataRepository.save(data);  // Save as a new record
    }
 
    public List<String> getAllIncrementDates(String empId) {
        return earnedLeaveBalanceDataRepository.findLastIncrementDatesByEmpId(empId);
    }

}
