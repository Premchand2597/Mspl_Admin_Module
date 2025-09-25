package com.example.mspl_connect.AdminService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;
import com.example.mspl_connect.AdminEntity.ApprisalHR_Entity;
import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminEntity.HikeRatings_Entity;
import com.example.mspl_connect.AdminRepo.ApprisalHR_Repo;
import com.example.mspl_connect.AdminRepo.EmployeeCustomDetailsForApprisal_Repo;
import com.example.mspl_connect.AdminRepo.HikeRatings_Repo;

import jakarta.transaction.Transactional;

@Service
public class ApprisalHR_Service {

	@Autowired
	private ApprisalHR_Repo apprisalHR_Repo;
	
	@Autowired
	private EmployeeCustomDetailsForApprisal_Repo employeeCustomDetailsForApprisal_Repo;
	
	@Autowired
	private HikeRatings_Repo hikeRatings_Repo;
	
	@Transactional
	public boolean insertApprisalData(List<AppraisalHrEntity> apprisal_Entities) {
	    Date currentDate = new Date();  // Create a single date instance for all records
	    for (AppraisalHrEntity entity : apprisal_Entities) {
	        entity.setApprisalSendDate(currentDate);
	        entity.setApprisalLinkFlag(true);
	    }
	    apprisalHR_Repo.saveAll(apprisal_Entities);  // Save all entities in one go
	    return true;
	}
	
	public boolean getHikeRatingsdataBasedOnFinancialYearIsPresent(String financialYear){
		HikeRatings_Entity data = hikeRatings_Repo.fetchHikeRatingsDataBasedOnFinancialYear(financialYear);
		if(data != null) {
			return true;
		}
		return false;
	}

	
	public List<EmployeeCustomDetailsForApprisal_Entity> getAllEmployeeNames(){
		return employeeCustomDetailsForApprisal_Repo.fetchAllEmployeeNames();
	}
	
	public EmployeeCustomDetailsForApprisal_Entity getAllEmployeeDetailsBasedOnEmpNames(String emp_full_name_with_emp_id){
		return employeeCustomDetailsForApprisal_Repo.fetchEmployeeDetailsBasedOnEmpNames(emp_full_name_with_emp_id);
	}
	
	public List<HikeRatings_Entity> getAllHikeRatingsdata(){
		return hikeRatings_Repo.fetchAllHikeRatingsData();
	}
	
	public String insertHikePercentRatings(HikeRatings_Entity hikeRatings_Entity) {
		hikeRatings_Repo.save(hikeRatings_Entity);
		return "success";
	}
	
	public List<Object[]> fetchAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValue(String quarterMonthAndYear) {
		return apprisalHR_Repo.getAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValue(quarterMonthAndYear);
	}
	
	public List<AppraisalHrEntity> getAppraisalSentByHRDataBasedOnEmpIdAndYear(String empId, String financialYearRange) {
	    String[] years = financialYearRange.split(" - ");
	    
	    // Extract start and end year, removing any non-numeric characters (e.g., parentheses)
	    String startYear = years[0].split("-")[1].replaceAll("\\D", "").trim(); // E.g., "2024"
	    String endYear = years[1].split("-")[1].replaceAll("\\D", "").trim();   // E.g., "2025"
	    
	    //System.out.println("Start Year === " + startYear + " End Year === " + endYear);

	    // Format strings to match financial year format in DB
	    String firstChoiceYear = "(Apr - June) - " + startYear;
	    String secondChoiceYear = "(July - Sep) - " + startYear;
	    String thirdChoiceYear = "(Oct - Dec) - " + startYear;
	    String fourthChoiceYear = "(Jan - March) - " + (Integer.parseInt(startYear) + 1);

	    //System.out.println("firstChoiceYear === " + firstChoiceYear + " secondChoiceYear === " + secondChoiceYear+ " thirdChoiceYear === " + thirdChoiceYear+ " fourthChoiceYear === " + fourthChoiceYear);

	    return apprisalHR_Repo.fetchAllAppraisalSentByHRDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	}
}
