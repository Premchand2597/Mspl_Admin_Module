package com.example.mspl_connect.AdminController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mspl_connect.AdminEntity.ToDoList;
import com.example.mspl_connect.AdminRepo.AppraisalRepository;
import com.example.mspl_connect.AdminService.FeatureUpdatesService;
import com.example.mspl_connect.AdminService.ToDoListService;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.PermissionsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyFavorite {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	/*
	 * @Autowired private NotificationCount_Service notificationCount_Service;
	 */
    @Autowired
    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	 @Autowired
	 private ToDoListService todolistService;
	
	 @Autowired
	 private AppraisalRepository appraisalRepository;

		/*
		 * @Autowired private ApprisalHRRepository appraisalHrRepository;
		 */
	 
	 @Autowired
	 private FeatureUpdatesService featureUpdatesService; 
	
	 @GetMapping("/my-favorites")
	    public String myFavorites(HttpSession session, Model model) {
		  // Retrieve user details from session
		    String email = (String) session.getAttribute("email");
		    System.out.println("user login "+email);
		    if (email == null) { // Check if session has expired
		        return "redirect:/"; // Redirect to the root mapping (login page)
		    }
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empid "+empId);
		    
		    //notificationCount_Service.markFeatureUpdatesAsSeen(empId);
		    
		 DisplayEmployessEntity empDetailsByEmpId=employeeWitFullDetailes.findByEmpid(empId);
		 
		   Optional<PermissionsEntity> permissions = permissionRepo.findByUserId(empId);
		    System.out.println("====%%%%"+permissions);
			if (permissions.isPresent()) {
				PermissionsEntity permissionEntity = permissions.get();

				if (permissionEntity.isApprisalAccess()) {
					String dueDateForAppriasal = appraisalRepository.getDueDateByEmpid(empId);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define date format
					LocalDate dueDate = LocalDate.parse(dueDateForAppriasal, formatter); // Parse due date into LocalDate
					LocalDate currentDate = LocalDate.now(); // Get current date

					if (dueDate.isBefore(currentDate)) { // Compare dates
						permissionEntity.setApprisalAccess(false);// If due date is today or earlier, set apprisalAccess to
																	// false
					} else {
						permissionEntity.setApprisalAccess(true); // If due date is in the future, set apprisalAccess to
																	// true (if needed)
					}
				}
				model.addAttribute("permissions", permissionEntity);
			} else {
				model.addAttribute("permissions", new PermissionsEntity()); // Add a default object to avoid null issues
			}
			
			//make new feature flag value 0 after visit the page
		    featureUpdatesService.updateNewFeatureFlagValue(empId);
		    
		   model.addAttribute("loggedInUserEmail", email);
		   model.addAttribute("empDetailsByEmpId", empDetailsByEmpId);
		   model.addAttribute("empId", empId);
	        return "User/UserFavorite";
	    }
	
		
		@GetMapping("/tasks")
	    public ResponseEntity<List<ToDoList>> getTodoList(HttpSession session) {
	        // Fetch the current user's email from the session
	        String email = (String) session.getAttribute("email");

	        // Find the employee ID associated with the email
	        String empId = employeeRepository.findEmpidByEmail(email);

	        // Fetch the tasks for the current user
	        List<ToDoList> tasks = todolistService.getAllTasksForEmployee(empId);

	        // Return the list of tasks as JSON
	        return ResponseEntity.ok(tasks);
	    }

	 
		@PostMapping("/updateTaskDescription")
		public ResponseEntity<String> updateTaskDescription(@RequestBody Map<String, Object> payload) {
			 System.out.println("????===");
		    Long taskId = Long.valueOf(payload.get("taskId").toString());
		    System.out.println("????"+taskId);
		    String newDescription = payload.get("description").toString();
		    System.out.println("????"+newDescription);
		    String newDeadlineDate = payload.get("deadlinedate").toString();
		    String newDeadlineTime = payload.get("deadlinetime").toString();


		    // Call the service to update the description
		    todolistService.updateTaskDescription(taskId, newDescription, newDeadlineDate, newDeadlineTime);

		    return ResponseEntity.ok("Task description updated");
		}


		// Delete task by ID
	    @DeleteMapping("/deleteTask/{taskId}")
	    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
	        try {
	            todolistService.deleteTask(taskId);
	            return ResponseEntity.ok("Task deleted successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting task");
	        }
	    }

	    // Fetch single task by ID
	    @GetMapping("/getTask/{taskId}")
	    public ResponseEntity<ToDoList> getTaskById(@PathVariable Long taskId) {
	        Optional<ToDoList> task = todolistService.getTaskById(taskId);
	        System.out.println("????");
	        if (task.isPresent()) {
	            return ResponseEntity.ok(task.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
		
		
		// Update task completion status
	    @PostMapping("/updateTaskStatus")
	    public ResponseEntity<String> updateTaskStatus(@RequestBody Map<String, Object> payload, HttpSession session) {
	        Long taskId = Long.valueOf(payload.get("taskId").toString());
	        boolean completed = (boolean) payload.get("completed");

	        // Update the task status in the database
	        todolistService.updateTaskStatus(taskId, completed);

	        return ResponseEntity.ok("Task status updated");
	    }
	 
	 // Method to handle form submission and add a new task
	    @PostMapping("/todolist")
	    public String addTask(@RequestParam("taskDescription") String taskDescription,  @RequestParam("deadlinedate") String deadlinedate,@RequestParam("deadlinetime") String deadlinetime,
	                         HttpSession session) {
	    	String email = (String) session.getAttribute("email");
		    System.out.println("user loginghfhf "+email);
		    String empId = employeeRepository.findEmpidByEmail(email);
		    System.out.println("user empid+++d "+empId);


	        // Create a new task object and set its values
	        ToDoList newTask = new ToDoList();
	        newTask.setDescription(taskDescription);
	      
	        newTask.setEmpId(empId); // Set user email
	        newTask.setDeadlinedate(deadlinedate);
	        newTask.setDeadlinetime(deadlinetime);
	        // Save the new task
	        todolistService.addTask(newTask);

	        // Redirect back to the favorites page (which will now include the new task)
	        return "redirect:/my-favorites";
	    }
	 
	 
}
