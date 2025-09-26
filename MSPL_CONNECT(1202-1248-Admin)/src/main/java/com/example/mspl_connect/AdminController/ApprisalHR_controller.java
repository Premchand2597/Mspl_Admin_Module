package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.AppraisalFromAdmin_Entity;
import com.example.mspl_connect.AdminEntity.AppraisalFromEmployee_Entity;
import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminEntity.EmployeesEntityWithoutDocs_Entity;
import com.example.mspl_connect.AdminEntity.SalaryDetailsEntity;
import com.example.mspl_connect.AdminService.AppraisalFromEmployee_Service;
import com.example.mspl_connect.AdminService.ApprisalHR_Service;
import com.example.mspl_connect.AdminService.SalaryDetailsService;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Service.EmployeeDetaisService;
import com.example.mspl_connect.Service.PermissionService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ApprisalHR_controller {
	
	@Autowired
	private ApprisalHR_Service apprisalHR_Service;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private AppraisalFromEmployee_Service appraisalFromEmployee_Service;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SalaryDetailsService salaryDetailsService;
	
	@GetMapping("apprisalEmployee")
	public String openApprisalForm() {
		return "HR/ApprisalForm";
	}
	
	/*@PostMapping("/saveAppraisalForHR")
	@ResponseBody
	public String insertAppraisalDetails(@RequestBody List<ApprisalHR_Entity> apprisalHR_Entities) {
	    boolean isSuccess = apprisalHR_Service.insertApprisalData(apprisalHR_Entities);
	    
	    for(ApprisalHR_Entity ent : apprisalHR_Entities) {
	    	permissionService.updateAppraisalPermission(ent.getEmp_id(), true);
	    }
	    
	    if (isSuccess) {
	    	appraisalNotificationAndValidationForHR_Repo.updateQuarterFlagAndQuarterBtnEnableFlagAfterEnablingTheAppraisalLink();
	        return "Appraisal inserted";
	    } else {
	        return "error";
	    }
	}*/
	
	
	 @GetMapping("/fetchAllEmployeeDetailsExceptDocuments")
	 @ResponseBody
	 public List<EmployeesEntityWithoutDocs_Entity> fetchAllEmployeeDetailsWithoutDocs(HttpSession session){
			String emailid = (String) session.getAttribute("email");
			String LoggedEmpid = employeeRepository.findEmpidByEmail(emailid);
			String adminDept = employeeRepository.findDeptNameByEmpId(LoggedEmpid);
			
		 return appraisalFromEmployee_Service.getAllEmpDetailsWithoutDocs(LoggedEmpid);
	 }
	
	@GetMapping("fetchAllEmployeeNames")
	@ResponseBody
	public List<EmployeeCustomDetailsForApprisal_Entity> getAllEmployeeNames(){
		return apprisalHR_Service.getAllEmployeeNames();
	}
	
	@GetMapping("fetchEmployeeDetailsUsingEmpNames")
	@ResponseBody
	public EmployeeCustomDetailsForApprisal_Entity getEmployeeDataBasedOnEmpNames(@RequestParam("emp_full_name_with_emp_id") String emp_full_name_with_emp_id){
		return apprisalHR_Service.getAllEmployeeDetailsBasedOnEmpNames(emp_full_name_with_emp_id);
	}
	
	
	
	/*@GetMapping("/fetchTeamLeadAndTeamCoEmpIdByUsingEmployeeEmailAndEmpId")
	@ResponseBody
	public TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity fetchTeamLeadAndTeamCoEmpId(@RequestParam("empId") String empId, @RequestParam("employeeEmail") String employeeEmail){
		
		TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity entity = appraisalEmailSendToAdminData_Service.fetchTeamLeadAndTeamCoEmpId(empId, employeeEmail);
		
		if((entity.getTeam_lead_id().equals(null) || entity.getTeam_lead_id().equals("")) && (entity.getTeam_co_id().equals(null) || entity.getTeam_co_id().equals(""))) {
			entity.setTeam_lead_email("");
			entity.setTeam_co_email("");
			entity.setEmployee_email(employeeEmail);
		}else {
			String teamLeadEmail = appraisalEmailSendToAdminData_Service.fetchTeamLeadEmail(entity.getTeam_lead_id());
			String teamCoEmail = appraisalEmailSendToAdminData_Service.fetchTeamCoEmail(entity.getTeam_co_id());
			entity.setTeam_lead_email(teamLeadEmail);
			entity.setTeam_co_email(teamCoEmail);
			entity.setEmployee_email(employeeEmail);
		}
		
		return entity;
	}
	
	@PostMapping("/sendEmailToAdminForWhoNotSubmittedTheAppraisal")
	@ResponseBody
	public String sendEmailToAdminForWhoNotSubmittedTheAppraisal(@RequestBody List<ApprisalEmailSendToAdminFinal_Entity> request) {
		
		if (request == null || request.isEmpty()) {
	        return "No data to send email";
	    }
		
		String result = null;
		
		for(ApprisalEmailSendToAdminFinal_Entity req: request) {
			
			String loggedInEmail = req.getLogged_in_email();
			String auto_id = req.getAuto_id();
			String empEmail = req.getEmployee_email();
			String empName = req.getEmployee_name();
			String teamLeadEmail = req.getTeam_lead_email();
			String teamCoEmail = req.getTeam_co_email();
			String department = req.getDepartment();
			String quarterMonthYear = req.getQuarter_month_year();
			
			try {
				result = apprisalEmailSendToAdminFinal_Service.sendEmailToAdminForUnSubmittedAppraisal(auto_id, loggedInEmail, empEmail, empName, teamLeadEmail, teamCoEmail, department, quarterMonthYear);
			} catch (Exception e) {
				e.printStackTrace();
				return "Failed to send email";
			}
		}
		
		return "success".equals(result) ? "Email Sent" : "No data to send email";
	}
	
	 @GetMapping("/fetchAllAppraisalDataFromEmployeesByUsingEmpId")
	    public ResponseEntity<Map<String, List<Appraisal>>> getAppraisalData(
	            @RequestParam String emp_id, @RequestParam String financialYear) {
		    
	        List<Appraisal> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalData(emp_id, financialYear);
	        List<Appraisal> employeeAppraisalData = appraisalFromEmployee_Service.getFilteredEmployeesAppraisalData(emp_id, financialYear);
	        
	        Map<String, List<Appraisal>> respondData=new HashMap<>();
	        respondData.put("appraisalData", appraisalData);
	        respondData.put("employeeAppraisalData", employeeAppraisalData);
	        
	        return ResponseEntity.ok(respondData);
	    }*/
	
	@GetMapping("appraisalDetailViewPage")
	public String openAppraisalDetailViewPage(@RequestParam("emp_id") String emp_id, @RequestParam("fullName") String fullName, 
			 @RequestParam("roleName") String roleName, @RequestParam("deptName") String deptName, 
			 @RequestParam("subDeptName") String subDeptName, @RequestParam("email") String email, Model model) {
			
			model.addAttribute("emp_id", emp_id);
			model.addAttribute("fullName", fullName);
			model.addAttribute("roleName", roleName);
			model.addAttribute("deptName", deptName);
			model.addAttribute("subDeptName", subDeptName);
			model.addAttribute("email", email);
			
		return "HR/AppraisalDetailViewPage";
	}
	
	@GetMapping("/fetchAllSalaryDetails/{empId}")
	public ResponseEntity<List<SalaryDetailsEntity>> getSalaryDetailsByEmpId(
	        @PathVariable("empId") String empId) { // Use @PathVariable
		
		System.out.println("hello");
	    List<SalaryDetailsEntity> appraisalData = salaryDetailsService.findByEmpId(empId);
	    System.out.println("......"+appraisalData);
	    return ResponseEntity.ok(appraisalData);
	}


	@GetMapping("/fetchAllAppraisalDataFromUsersByUsingEmpId")
    public ResponseEntity<List<AppraisalFromEmployee_Entity>> getAppraisalDataFromUsers(
            @RequestParam String emp_id, @RequestParam String financialYear) {
	    
		System.out.println("empid----"+emp_id);
		System.out.println("financialYear----"+financialYear);
        List<AppraisalFromEmployee_Entity> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalDataFromUsers(emp_id, financialYear);
        
        appraisalData.stream().forEach(i->System.out.println("Data === "+i));
        
        return ResponseEntity.ok(appraisalData);
        
    }

	@GetMapping("/fetchAllAppraisalDataFromAdminsByUsingEmpId")
    public ResponseEntity<List<AppraisalFromAdmin_Entity>> getAppraisalDataFromAdmin(
            @RequestParam String emp_id, @RequestParam String financialYear) {
	 
        List<AppraisalFromAdmin_Entity> appraisalData = appraisalFromEmployee_Service.getFilteredAppraisalDataFromAdmin(emp_id, financialYear);
        
        //System.out.println("Data === "+appraisalData);
        
        return ResponseEntity.ok(appraisalData);
    }
	
	
}
