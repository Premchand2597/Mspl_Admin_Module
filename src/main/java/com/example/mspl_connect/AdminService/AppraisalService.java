package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.Appraisal;
import com.example.mspl_connect.AdminEntity.AppraisalAdminEntity;
import com.example.mspl_connect.AdminEntity.AppraisalForDisplay;
import com.example.mspl_connect.AdminRepo.AppraisalAdminRepo;
import com.example.mspl_connect.AdminRepo.AppraisalForDisplayRepo;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.transaction.Transactional;


@Service
public class AppraisalService {
	
	@Autowired
    private AppraisalRepository appraisalRepository;
	
	@Autowired
	private AppraisalAdminRepo appraisalAdminRepo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private AppraisalForDisplayRepo appraisalForDisplayRepo;
	

  /*  public Appraisal saveAppraisal(Appraisal appraisal) {
        appraisal.setTotalScore(calculateTotalScore(appraisal));
        return appraisalRepository.save(appraisal);
    }

    private int calculateTotalScore(Appraisal appraisal) {
        return appraisal.getProjectScore() + appraisal.getAttendanceScore() +
               appraisal.getTeamCollaborationScore() + appraisal.getCommunicationScore() +
               appraisal.getInitiativeScore() + appraisal.getLeadershipScore() +
               appraisal.getExtraCurricularScore();
    }*/
    
    @Autowired
    public AppraisalService(AppraisalRepository appraisalEmployeeRepository) {
        this.appraisalRepository = appraisalEmployeeRepository;
    }
    
    @Transactional
    public void saveAppraisal(AppraisalAdminEntity appraisalEmployee,String adminEmpId) {
    	
    	/* int updatedAdminAppraisalPermission = permissionRepo.updatePermissionFlag(adminEmpId);
	    if (updatedAdminAppraisalPermission == 0) {
	        // You can throw a custom exception here or just a RuntimeException
	        throw new RuntimeException("Update failed: No rows affected for empId " + adminEmpId);
	    } */	
    	System.out.println("appraisalEmployee===="+appraisalEmployee);
    	appraisalAdminRepo.save(appraisalEmployee);
    }
    
    public List<AppraisalForDisplay> getEmpAppraisalByDept(String dept){
    	//System.out.println("dept/////////////"+dept);
    	List<AppraisalForDisplay> itEmployeesWithAdminFlag = appraisalForDisplayRepo.findByDepartmentAndApprisalAdminFlag(dept);
    	//itEmployeesWithAdminFlag.stream().forEach(null);
    	return itEmployeesWithAdminFlag;

    }

	public Appraisal getEmpAppraisalByEmail(String empid) {
		Appraisal appraisalByEmpId = appraisalRepository.findByEmpId(empid);
		return appraisalByEmpId;
	}

	@Transactional
	public void updateAppraisal(String empId) {
		int updatedRows = appraisalAdminRepo.update(empId);

    	System.out.println("abcd efg ==="+empId);
	    if (updatedRows == 0) {
	        // You can throw a custom exception here or just a RuntimeException
	        throw new RuntimeException("Update failed: No rows affected for empId " + empId);
	    }	
	}
	
	@Transactional
	public void updateAdminAppraisal(String empId) {
		
		int updatedRows = appraisalAdminRepo.updateAdminFlag(empId);
	    if (updatedRows == 0) {
	        // You can throw a custom exception here or just a RuntimeException
	        throw new RuntimeException("Update failed: No rows affected for empId " + empId);
	    }	 
	    
	}
	
	public int getAdminappraisalDuedate(String empid) {
	    Optional<String> dueDateStr = appraisalRepository.submissionDate(empid);
	    
	    if (dueDateStr.isPresent()) {
	        // Parse the due date from String to LocalDate
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Adjust pattern to your date format
	        LocalDate dueDate = LocalDate.parse(dueDateStr.get(), formatter);
	        LocalDate currentDate = LocalDate.now();

	        // Check if the current date is one day before the due date
	        long daysUntilDue = ChronoUnit.DAYS.between(currentDate, dueDate);
	        
	        // Print the result for debugging
	        //System.out.println("Admin appraisal due date: " + dueDate);
	        //System.out.println("Days until due date: " + daysUntilDue);
	        
	        return daysUntilDue == 1 ? 1 : 0;
	    }
	    
	    //System.out.println("No due date found");
	    return 0;  // Return 0 if due date is not present
	}
}
