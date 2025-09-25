package com.example.mspl_connect.AdminController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminRepo.Events_Repo;
import com.example.mspl_connect.AdminRepo.ToDoListRepository;
import com.example.mspl_connect.AdminService.AppraisalService;
import com.example.mspl_connect.AdminService.NotificationService;
import com.example.mspl_connect.Entity.NtificationCountEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Repository.ProjectRepository;
import com.example.mspl_connect.Repository.ProjectSaveRepo;
import com.example.mspl_connect.Sales_Repository.IAdd_Quotation;

import jakarta.servlet.http.HttpSession;



@Controller
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private DepartmentRepo departmentRepo; 
	
	@Autowired
	private AppraisalRepository appraisalRepository;

	@Autowired
	private AppraisalService appraisalService;
	
	@Autowired
	private Events_Repo events_Repo;
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
    @Autowired
    private IAdd_Quotation iAdd_Quotation;
    
	@Autowired
	private PermissionRepo permissionRepo;
	
	
	@GetMapping("/flag")
    public ResponseEntity<NtificationCountEntity> getFlagValue(HttpSession session) {
		
		// Retrieve user details from session
	    String email = (String) session.getAttribute("email");	 
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedNow = now.format(formatter);
	    String empId = employeeRepository.findEmpidByEmail(email);
	    
	    //System.out.println("............" + email + " at " + formattedNow);
	    //System.out.println("empId===="+empId);
	    Integer adminDept = employeeRepository.findDeptIdByEmpId(empId);
	    
		 // Check if adminDept is null and assign null if needed (although it's already null if no result is found)
		 /*if (adminDept == null) {
		     adminDept = null;
		 }*/
		 
	    //System.out.println("empId====" + adminDept);
		String deptName = departmentRepo.findDeptNameByDeptId(adminDept);
		int totalNotificationCount;
		
        //List<ProjectEntity> completedProjectDetails = projectService.projectBydepartment();
		int leavecount = notificationService.getNewLeaveRequestCount(adminDept,empId);
		
		//System.out.println(leavecount);
		int leaveCountForSA = notificationService.getNewRequestCountForSA(empId);
		
		//to get leave request is accepted or not
		int leaveStatusChangeValue = notificationService.getLeaveStatusChange(email);
		 
		//System.out.println(leaveStatusChangeValue);
		NtificationCountEntity response;
		
		Optional<Integer> hrAppraisalNotificationOpt = appraisalRepository.getHrAppraisalFlagForNotification(empId);
		Integer hrAppraisalNotification = hrAppraisalNotificationOpt.orElse(0); // Use 0 as a default if null
		
		//flag value for project
		int prjectflagValue = projectRepository.findNewProjectFlag(deptName);
		
		// flag for employees appraisal flag
		Optional<Integer> empAppraisalflag1 = appraisalRepository.getEmployeesApprisalForPermission(deptName);
		
		// Convert Optional to int, default to 0 if empty
		int empAppraisalflag = empAppraisalflag1.orElse(0);
		
		//System.out.println("empAppraisalflag..."+empAppraisalflag);
		int adminAppraisalDuedate=appraisalService.getAdminappraisalDuedate(empId);
		
		//System.out.println("adminAppraisalDuedate..."+adminAppraisalDuedate);		
		//get notification count from announcement notification table 
		
		Integer announcementNotification=events_Repo.countAnnouncementFromAnnouncementNotiByEmpid(empId);
		// System.out.println("announcementNotification----"+announcementNotification); 
		
		// get to-do events notification count
		Integer tasksByUpcomingDeadline = toDoListRepository.findTasksByUpcomingDeadline(empId);
		
		// get  notification count
		Integer getNewFeatureFlagCountValue = toDoListRepository.getFeatureUpdateFlagCountByEmpid(empId);
		
		Integer getNewReleaseNoteNotification = toDoListRepository.getNewFeatureNotification(empId);
		
		Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		// System.out.println("permissions....." + permissions);
		
		// if sale permission is enable then only notification should display
		Integer quotationNotification = 0;
		Integer getReviseQuotationCount = 0;

		if (permissions.isPresent()) {
		    PermissionsEntity permissionEntity = permissions.get();
		    
		    if (permissionEntity.isSales()) {
		        quotationNotification = iAdd_Quotation.getQuotationNotificationByAdmin(empId);
		        getReviseQuotationCount = iAdd_Quotation.getReviseQuotationCount();
		    }
		}	
		
		if(adminDept == 0) {
			totalNotificationCount = leaveCountForSA + 0 + announcementNotification + leaveStatusChangeValue + tasksByUpcomingDeadline + getNewFeatureFlagCountValue + getNewReleaseNoteNotification + getReviseQuotationCount;
			response = new NtificationCountEntity(leaveCountForSA,totalNotificationCount,prjectflagValue,hrAppraisalNotification,empAppraisalflag,adminAppraisalDuedate,announcementNotification,leaveStatusChangeValue,tasksByUpcomingDeadline,getNewFeatureFlagCountValue,getNewReleaseNoteNotification,quotationNotification,getReviseQuotationCount);
		} else {
			totalNotificationCount = leavecount  + prjectflagValue + hrAppraisalNotification + empAppraisalflag + adminAppraisalDuedate + announcementNotification + leaveStatusChangeValue + tasksByUpcomingDeadline + getNewFeatureFlagCountValue + getNewReleaseNoteNotification + quotationNotification + getReviseQuotationCount;
			response = new NtificationCountEntity(leavecount,totalNotificationCount,prjectflagValue,hrAppraisalNotification,empAppraisalflag,adminAppraisalDuedate,announcementNotification,leaveStatusChangeValue,tasksByUpcomingDeadline,getNewFeatureFlagCountValue,getNewReleaseNoteNotification,quotationNotification,getReviseQuotationCount);
		}
	 	
	 	//System.out.println("totalNotificationCount"+totalNotificationCount);
	 	return ResponseEntity.ok(response);
	 	
    } 
	
}
