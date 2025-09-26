package com.example.mspl_connect.AdminService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AppraisalFromAdmin_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalFromEmployee_Entity;
import com.example.mspl_connect.AdminEntity.Appraisal_InsertUpdatedHikedSalary_Entity;
import com.example.mspl_connect.AdminEntity.Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO;
import com.example.mspl_connect.AdminEntity.EmployeesEntityWithoutDocs_Entity;
import com.example.mspl_connect.AdminRepo.AppraisalFromAdmin_Repo;
import com.example.mspl_connect.AdminRepo.AppraisalFromEmployee_Repo;
import com.example.mspl_connect.AdminRepo.Appraisal_InsertUpdatedHikedSalary_Repo;
import com.example.mspl_connect.AdminRepo.EmployeeDetailsWithoutDocs_Repo;

import jakarta.transaction.Transactional;


@Service
public class AppraisalFromEmployee_Service {

	/*@Autowired
	private AppraisalFromEmployee_Repo appraisalFromEmployee_Repo;
	
	@Autowired
	private EmployeeDetailsWithoutDocs_Repo employeeDetailsWithoutDocs_Repo;
	
	/*public List<AppraisalFromEmployee_Entity> getAllAppraisalDataBasedOnEmpId(String emp_id){
		return appraisalFromEmployee_Repo.fetchAllAppraisalDataBasedOnEmpId(emp_id);
	}
	
	
	public List<EmployeesEntityWithoutDocs_Entity> getAllEmpDetailsWithoutDocs(String empid){
		return employeeDetailsWithoutDocs_Repo.getAllEmployeesWithoutDocsDetails(empid);
	}
	
	
	public List<AppraisalFromEmployee_Entity> getFilteredAppraisalData(String empId, String financialYearRange) {
	    String[] years = financialYearRange.split(" - ");
	    System.out.println("....."+Arrays.toString(years));
	    
	    // Extract start and end year, removing any non-numeric characters (e.g., parentheses)
	    String startYear = years[0].split("-")[1].replaceAll("\\D", "").trim(); // E.g., "2024"
	    String endYear = years[1].split("-")[1].replaceAll("\\D", "").trim();   // E.g., "2025"
	    
	    //System.out.println("Start Year === " + startYear + " End Year === " + endYear);

	    // Format strings to match financial year format in DB
	    String firstChoiceYear = "(Apr - June) - " + startYear;
	    String secondChoiceYear = "(July - Sep) - " + startYear;
	    String thirdChoiceYear = "(Oct - Dec) - " + startYear;
	    String fourthChoiceYear = "(Jan - March) - " + (Integer.parseInt(startYear) + 1);
	    

	    return appraisalFromEmployee_Repo.fetchAllAppraisalDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	}
	
	public List<Appraisal> getFilteredEmployeesAppraisalData(String empId, String financialYearRange) {
	    String[] years = financialYearRange.split(" - ");
	    System.out.println("....."+Arrays.toString(years));
	    
	    // Extract start and end year, removing any non-numeric characters (e.g., parentheses)
	    String startYear = years[0].split("-")[1].replaceAll("\\D", "").trim(); // E.g., "2024"
	    String endYear = years[1].split("-")[1].replaceAll("\\D", "").trim();   // E.g., "2025"
	    
	    //System.out.println("Start Year === " + startYear + " End Year === " + endYear);

	    // Format strings to match financial year format in DB
	    String firstChoiceYear = "(Apr - June) - " + startYear;
	    String secondChoiceYear = "(July - Sep) - " + startYear;
	    String thirdChoiceYear = "(Oct - Dec) - " + startYear;
	    String fourthChoiceYear = "(Jan - March) - " + (Integer.parseInt(startYear) + 1);

	    return appraisalFromEmployee_Repo.fetchAllEmployesAppraisalDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	}
	
	public List<AppraisalFromEmployee_Entity> getFilteredAppraisalDataFromUsers(String empId, String financialYearRange) {
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

	    return appraisalFromEmployee_Repo.fetchAllAppraisalDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	}*/

	@Autowired
	private AppraisalFromEmployee_Repo appraisalFromEmployee_Repo;
	
	@Autowired
	private EmployeeDetailsWithoutDocs_Repo employeeDetailsWithoutDocs_Repo;
	
	//@Autowired
	//private AppraisalSubmittedCountWithEmpId_Repo appraisalSubmittedCountWithEmpId_Repo;
	
	@Autowired
	private AppraisalFromAdmin_Repo appraisalFromAdmin_Repo;
	
	//@Autowired
	//private AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Repo appraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Repo;
	
	@Autowired
	private Appraisal_InsertUpdatedHikedSalary_Repo appraisal_InsertUpdatedHikedSalary_Repo;
	
	/*public List<AppraisalFromEmployee_Entity> getAllAppraisalDataBasedOnEmpId(String emp_id){
		return appraisalFromEmployee_Repo.fetchAllAppraisalDataBasedOnEmpId(emp_id);
	}*/
	
	
	/*
	 * public List<EmployeesEntityWithoutDocs_Entity> getAllEmpDetailsWithoutDocs(){
	 * return employeeDetailsWithoutDocs_Repo.getAllEmployeesWithoutDocsDetails(); }
	 */
	
	public List<EmployeesEntityWithoutDocs_Entity> getAllEmpDetailsWithoutDocs(String LoggedEmpid){
		return employeeDetailsWithoutDocs_Repo.getAllEmployeesWithoutDocsDetails(LoggedEmpid);
	}
	
	
	public List<AppraisalFromEmployee_Entity> getFilteredAppraisalDataFromUsers(String empId, String financialYearRange) {
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

	    System.out.println("empId"+empId+"firstChoiceYear === " + firstChoiceYear + " secondChoiceYear === " + secondChoiceYear+ " thirdChoiceYear === " + thirdChoiceYear+ " fourthChoiceYear === " + fourthChoiceYear);
	    List<AppraisalFromEmployee_Entity> employeeAppraisalDataBasedOnEmpIdAndYear = appraisalFromEmployee_Repo.fetchAllAppraisalDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	    //employeeAppraisalDataBasedOnEmpIdAndYear.stream().forEach(i -> System.out.println("aaaaaaaaaa"+i));
	    // Ensure demerit_point is not null
	    for (AppraisalFromEmployee_Entity appraisal : employeeAppraisalDataBasedOnEmpIdAndYear) {
	        if (appraisal.getDemerit_point() == null) {
	            appraisal.setDemerit_point("0");
	        }
	    }
	    
	    return employeeAppraisalDataBasedOnEmpIdAndYear;
	 
	}
	
	/*
	 * public List<AppraisalSubmittedCountWithEmpId_Entity>
	 * fetchAppraisalSubmittedCountWithEmpIdDetails(){ return
	 * appraisalSubmittedCountWithEmpId_Repo.
	 * getAppraisalSubmittedCountWithEmpIdDetails(); }
	 */
	
	/*
	 * public void resetAppraisalSubmittedCount(String emp_id) {
	 * appraisalSubmittedCountWithEmpId_Repo.resetAppraisalSubmittedCount(emp_id); }
	 */
	
	
	public List<AppraisalFromAdmin_Entity> getFilteredAppraisalDataFromAdmin(String empId, String financialYearRange) {
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

	    return appraisalFromAdmin_Repo.fetchAllAppraisalDataBasedOnEmpIdAndYear(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);
	}
	
	
	
public List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> getAppraisalMaxScoreAllottedScoreOverallPercentageOverallRatingDataByUser(String empId, String financialYearRange) {
        
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
		
		
		List<Object[]> rawData = appraisalFromEmployee_Repo.fetchAllMaxTotalScoreOverallAndOverallRating(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);

        // Map raw data to DTOs
        List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> appraisalData = new ArrayList<>();
        for (Object[] row : rawData) {
            Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO dto = new Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO();
            dto.setMax_score(String.valueOf(row[0]));
            dto.setAllotted_score(String.valueOf(row[1]));
            dto.setOverall_percentage(String.valueOf(row[2]));
            dto.setOverall_rating(String.valueOf(row[3]));
            appraisalData.add(dto);
        }
        return appraisalData;
    }
	
	
	
	public List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> getAppraisalMaxScoreAllottedScoreOverallPercentageOverallRatingDataByAdmin(String empId, String financialYearRange) {
        
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
		
		
		List<Object[]> rawData = appraisalFromAdmin_Repo.fetchAllMaxTotalScoreOverallAndOverallRating(empId, firstChoiceYear, secondChoiceYear, thirdChoiceYear, fourthChoiceYear);

        // Map raw data to DTOs
        List<Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO> appraisalData = new ArrayList<>();
        for (Object[] row : rawData) {
            Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO dto = new Appraisal_Maxscore_Allottedscore_Overallpercent_OverallRating_DTO();
            dto.setMax_score(String.valueOf(row[0]));
            dto.setAllotted_score(String.valueOf(row[1]));
            dto.setOverall_percentage(String.valueOf(row[2]));
            dto.setOverall_rating(String.valueOf(row[3]));
            appraisalData.add(dto);
        }
        return appraisalData;
    }
	
	
	
	
	@Transactional
	public void insertAllUpdatedSalaryHikeDetails(String emp_id, String financial_year, String emp_name, String total_performance, String old_salary, String hike, String salary_after_hike, String remarks, String hike_affect_from) {
		appraisal_InsertUpdatedHikedSalary_Repo.saveAllAprraisalUpadtedSalaryAfterHike(emp_id, financial_year, emp_name, total_performance, old_salary, hike, salary_after_hike, remarks, hike_affect_from);
	}
	
	@Transactional
	public void submitManuallyEnteredSalaryDetails(Appraisal_InsertUpdatedHikedSalary_Entity appraisal_InsertUpdatedHikedSalary_Entity) {
		appraisal_InsertUpdatedHikedSalary_Repo.save(appraisal_InsertUpdatedHikedSalary_Entity);
	}
	
	
	public List<Appraisal_InsertUpdatedHikedSalary_Entity> getUpdatedSalaryAfterHikeBasedOnEmpId(String emp_id){
		return appraisal_InsertUpdatedHikedSalary_Repo.fetchHikedSalaryByUsingEmpId(emp_id);
	}

	

}
