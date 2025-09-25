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
import com.example.mspl_connect.AdminRepo.AssetRepo;
import com.example.mspl_connect.AdminRepo.Events_Repo;
import com.example.mspl_connect.AdminRepo.ToDoListRepository;
import com.example.mspl_connect.AdminService.AppraisalService;
import com.example.mspl_connect.AdminService.NotificationService;
import com.example.mspl_connect.Entity.NtificationCountEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.ProjectRepository;
import com.example.mspl_connect.Repository.ProjectSaveRepo;

import jakarta.servlet.http.HttpSession;



@Controller
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AssetRepo assetRepo;
	
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
	
	
	@GetMapping("/flag")
    public ResponseEntity<NtificationCountEntity> getFlagValue(HttpSession session) {
		
		// Retrieve user details from session
	    String email = (String) session.getAttribute("email");	 
	    //System.out.println(email);
	    String empId = employeeRepository.findEmpidByEmail(email);
	    
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
		
		if(leaveStatusChangeValue>0) {
			leaveStatusChangeValue=1;
		}
		
		//System.out.println(leaveStatusChangeValue);
		NtificationCountEntity response;
		
		int saAssetValue = assetRepo.findByRequestValue();
		int adminAssetValue = assetRepo.findByRequestValueAndDeptId(empId);
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
		//System.out.println("announcementNotification----"+announcementNotification);
		
		// get to-do events notification count
		Integer tasksByUpcomingDeadline = toDoListRepository.findTasksByUpcomingDeadline(empId);
		
		// get  notification count
		Integer getNewFeatureFlagCountValue = toDoListRepository.getFeatureUpdateFlagCountByEmpid(empId);
		
		Integer getNewReleaseNoteNotification = toDoListRepository.getNewFeatureNotification(empId);
		//System.out.println(getNewReleaseNoteNotification);
		// get new release notification count
		
		//System.out.println("tasksByUpcomingDeadline==  "+tasksByUpcomingDeadline);
		//System.out.println("tasksByUpcomingDeadline== " + tasksByUpcomingDeadline + 
        // " | Current Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
	 	if(adminDept == 0) {
			totalNotificationCount = leaveCountForSA + 0 + announcementNotification + leaveStatusChangeValue + tasksByUpcomingDeadline + getNewFeatureFlagCountValue + getNewReleaseNoteNotification;
			response = new NtificationCountEntity(leaveCountForSA,0,totalNotificationCount,prjectflagValue,hrAppraisalNotification,empAppraisalflag,adminAppraisalDuedate,announcementNotification,leaveStatusChangeValue,tasksByUpcomingDeadline,getNewFeatureFlagCountValue,getNewReleaseNoteNotification);
		} else {
			totalNotificationCount = leavecount + adminAssetValue + prjectflagValue + hrAppraisalNotification + empAppraisalflag + adminAppraisalDuedate + announcementNotification + leaveStatusChangeValue + tasksByUpcomingDeadline + getNewFeatureFlagCountValue +getNewReleaseNoteNotification;
			response = new NtificationCountEntity(leavecount,adminAssetValue,totalNotificationCount,prjectflagValue,hrAppraisalNotification,empAppraisalflag,adminAppraisalDuedate,announcementNotification,leaveStatusChangeValue,tasksByUpcomingDeadline,getNewFeatureFlagCountValue,getNewReleaseNoteNotification);
		}
	 	
	 	//System.out.println("totalNotificationCount"+totalNotificationCount);
	 	return ResponseEntity.ok(response);
	 	
    } 
	
}
