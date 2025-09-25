package com.example.mspl_connect.AdminService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.ToDoList;
import com.example.mspl_connect.AdminRepo.ToDoListRepository;

@Service
public class ToDoListService {
	
	 @Autowired 
	 private ToDoListRepository todolistRepository;

	 
	 public List<ToDoList> getAllTasks() {
	        return todolistRepository.findAll();
	    }
	 
	 
	 // Get all tasks for a specific employee based on their empId
	    public List<ToDoList> getAllTasksForEmployee(String empId) {
	        return todolistRepository.findAllByEmpId(empId);
	    }

	    // Update task description
	    public void updateTaskDescription(Long taskId, String newDescription, String newDeadlineDate, String newDeadlineTime) {
	        Optional<ToDoList> taskOptional = todolistRepository.findById(taskId);
	        if (taskOptional.isPresent()) {
	            ToDoList task = taskOptional.get();
	            task.setDescription(newDescription);  
	            task.setDeadlinedate(newDeadlineDate);  // Update deadline date
	            task.setDeadlinetime(newDeadlineTime);// Update the task description
	            todolistRepository.save(task);       // Save the updated task back to the database
	        } else {
	            throw new RuntimeException("Task not found with ID: " + taskId);
	        }
	    }
	    
	    // Method to delete task by ID
	    public void deleteTask(Long taskId) {
	        todolistRepository.deleteById(taskId);
	    }

	    // Method to fetch task by ID
	    public Optional<ToDoList> getTaskById(Long taskId) {
	        return todolistRepository.findById(taskId);
	    }
	    
	    
	    // Update task completion status
	    public void updateTaskStatus(Long taskId, boolean completed) {
	        ToDoList task = todolistRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        task.setCompleted(completed);
	        todolistRepository.save(task);
	    }
	    
	    // Add a new task to the repository
	    public void addTask(ToDoList task) {
	        todolistRepository.save(task);
	    }

	  /*  public List<Task> getCompletedTasks() {
	        return taskRepository.findByCompleted(true);
	    }

	    public List<Task> getActiveTasks() {
	        return taskRepository.findByCompleted(false);
	    }

	    public Task addTask(String description) {
	        Task task = new Task(description, false);
	        return taskRepository.save(task);
	    }

	    public Optional<Task> completeTask(Long id) {
	        Optional<Task> task = taskRepository.findById(id);
	        if (task.isPresent()) {
	            task.get().setCompleted(true);
	            taskRepository.save(task.get());
	        }
	        return task;
	    }

	    public void deleteTask(Long id) {
	        taskRepository.deleteById(id);
	    }*/
}
