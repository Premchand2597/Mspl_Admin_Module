package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.AdminService.ToDoListService;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.PermissionRepo;
import com.example.mspl_connect.Service.DepartmentService;
import com.example.mspl_connect.Service.EmployeeDetaisService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ToDoController {
	
	@Autowired
    private ToDoListService todolistService;
	

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeDetaisService detaisService;
	
	@Autowired
	private PermissionRepo permissionRepo;
	

    @Autowired
    private EmployeeRepositoryWithDeptName employeeWitFullDetailes;

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	



}
