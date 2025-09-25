package com.example.mspl_connect.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EmployeeNameEmailDTO;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;
import com.example.mspl_connect.Entity.Employee;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.EmployeeOnlyDocument_Entity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;
import com.example.mspl_connect.Repository.DisplayEmployeeWithMisspunchRepo;
import com.example.mspl_connect.Repository.EmployeeOnlyDocument_Repo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.FindEmployee;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeDetaisService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeRepositoryWithDeptName emWithDeptName; 	
	
	@Autowired
	private FindEmployee employees;
	
	@Autowired
	private AttendenceRepo attendenceRepo;
	
	@Autowired
	private EmployeeOnlyDocument_Repo employeeOnlyDocument_Repo; 
	
	@Autowired
	private DisplayEmployeeWithMisspunchRepo displayEmployeeWithMisspunchRepo;
	
	public EmployeeDetailsEntity saveEmployee(EmployeeDetailsEntity employeeDetailsEntity) {
		// Encrypt the password
        String encryptedPassword = hashPassword(employeeDetailsEntity.getPassword());
        employeeDetailsEntity.setPassword(encryptedPassword);
        
        return employeeRepository.save(employeeDetailsEntity);
    }
	
	public int totalDeptEmployeCount(int deptId) {
		return employeeRepository.totalDeptEmployeCount(deptId);
	}
	public int totalEmployeCount() {
		return employeeRepository.totalEmployeCount();
	}
	
	@Transactional
	public List<TodayPresentEmpEntity> getTodayPresentEmpList() {
		//System.out.println("aaaaaaaaaaa");
		List<Object[]> results = employeeRepository.fetch_today_present_employee_for_super_admin();
		System.out.println("bbbbbbbbbb === "+results);
		List<TodayPresentEmpEntity> employees = new ArrayList<>();
		// Define date formatters
	    //DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    //DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
	    
	    
	    for (Object[] result : results) {
	    	
	        TodayPresentEmpEntity emp = new TodayPresentEmpEntity();
	        emp.setEid((String) result[0]);
	        emp.setEmp_name((String) result[1]); // assuming result[5] is the employee name
	        emp.setEmail((String) result[2]);
	        emp.setDept_name((String) result[3]);
	        // Apply formatting to date fields
	     // Check and format dates only if they are not "Check-In Pending"
	        emp.setFirst_punch_time(validateAndFormatDate((String) result[4]));
	        emp.setLast_punch_time(validateAndFormatDate((String) result[5]));
	        emp.setLast_seen(validateAndFormatDate((String) result[6]));
	        
	        employees.add(emp);
	        
	    }
	    //System.out.println("cccccccc"+employees);
	    return employees;
	}
	
	private String validateAndFormatDate(String dateStr) {
	    if (dateStr == null || dateStr.equalsIgnoreCase("Check-In Pending") || dateStr.equalsIgnoreCase("No Data")) {
	        return dateStr; // Return as is if it's null or "Check-In Pending"
	    }
	    
	    try {
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        
	        LocalDateTime date = LocalDateTime.parse(dateStr, inputFormatter);
	        return date.format(outputFormatter);
	    } catch (Exception e) {
	        System.err.println("Error parsing date: " + dateStr);
	        return dateStr; // Return original value if parsing fails
	    }
	}

	
	@Transactional
	public List<TodayPresentEmpEntity> getTodayPresentEmpListByDept(String empId) {
	    
	    List<Object[]> results = employeeRepository.fetch_today_present_employee(empId);
	    List<TodayPresentEmpEntity> employees = new ArrayList<>();
	    
	    // Define date formatters
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

	    for (Object[] result : results) {
	        TodayPresentEmpEntity emp = new TodayPresentEmpEntity();
	        emp.setEid((String) result[0]);
	        emp.setEmp_name((String) result[1]);  
	        emp.setEmail((String) result[2]);
	        emp.setDept_name((String) result[3]);

	        // Apply formatting to date fields
	        emp.setFirst_punch_time(formatDate((String) result[4], inputFormatter, outputFormatter));
	        emp.setLast_punch_time(formatDate((String) result[5], inputFormatter, outputFormatter));
	        emp.setLast_seen(formatDate((String) result[6], inputFormatter, outputFormatter));

	        employees.add(emp); 
	    }
	    
	    employees.forEach(i -> System.out.println("Formatted Data: " + i));
	    return employees;
	}

	// Utility method to format date
	private String formatDate(String dateTime, DateTimeFormatter inputFormatter, DateTimeFormatter outputFormatter) {
	    if (dateTime == null || dateTime.equalsIgnoreCase("Absent")) {
	        return "Absent";  // Return "Absent" if the value is null or explicitly "Absent"
	    }
	    try {
	        LocalDateTime date = LocalDateTime.parse(dateTime, inputFormatter);
	        return date.format(outputFormatter);
	    } catch (Exception e) {
	        System.err.println("Error parsing date: " + dateTime);
	        return dateTime;  // Return original if parsing fails
	    }
	}
	
	public List<Object[]> getEmployeeRecentAppTimeByAdmin(String loogedEmpId){
		
	    List<Object[]> originalData = emWithDeptName.getEmployeeRecentAppTimeByAdmin(loogedEmpId);
	    List<Object[]> formattedData = new ArrayList<>();
	    
	    for(Object[] a:originalData) {
	    	System.out.println("ddddd"+Arrays.toString(a));
	    }

	    // Define output date format (for displaying)
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	    for (Object[] row : originalData) {
	        Object[] formattedRow = Arrays.copyOf(row, row.length); // Copy original row

	        try {
	            if (row[2] != null) { // Assuming DateTime is at index 2
	                if (row[2] instanceof java.sql.Timestamp || row[2] instanceof java.util.Date) {
	                    formattedRow[2] = outputFormat.format(row[2]); // Directly format Date/Timestamp
	                } else {
	                    System.err.println("Unexpected data type: " + row[2].getClass());
	                }
	            }
	        } catch (Exception e) {
	            System.err.println("Error formatting date: " + e.getMessage());
	        }
	        
	        formattedData.add(formattedRow); // Add formatted row to new list
	        // System.out.println("Formatted Row: " + Arrays.toString(formattedRow));
	        
	    }
	    return formattedData; // Return updated list
	}
	
	public Optional<EmployeeDetailsEntity> getEmpById(String empid){
		return employeeRepository.findByEmpId(empid);
	}

	public List<EmployeeDetailsEntity> getEmployee(){
		return employeeRepository.findAll();
	}
	
	public List<DisplayEmployessEntity> getEmployeeWithDeptName(){
		return emWithDeptName.getAllEmployees();
	}
	
	@Transactional
	public List<DisplayEmployessWithMissPunchEntity> getAdminDeptEmployees(String loggedAdminEmpid){
		return displayEmployeeWithMisspunchRepo.employees_data_with_misspunch_for_admin(loggedAdminEmpid);
	}
	
	@Transactional
	public List<DisplayEmployessWithMissPunchEntity> getSuperAdminDeptEmployees(String loggedAdminEmpid){
		return displayEmployeeWithMisspunchRepo.employees_data_with_misspunch_for_superadmin(loggedAdminEmpid);
	}
	
	public DisplayEmployessEntity getEmployeeByEmpid(String empid) {
	     return emWithDeptName.findByEmpid(empid);
	}
	
	public List<DisplayEmployessEntity> findUserByDepartmentWithFullName(int deptId){
		return emWithDeptName.findByDeptId(deptId);
	}
	public List<EmployeeDetailsEntity> findUserByDepartment(int deptId){
		System.out.println("deptId.........."+deptId);
		return employeeRepository.findByDeptId(deptId);
	}
	
	
	public List<EmployeeNameEmailDTO> findUserByDepartment1(int deptId){
		System.out.println("deptId.........."+deptId);
		return employeeRepository.findByDeptId1(deptId);
	}
	
	/*
	 * public List<String> findUserByDepartment1(int deptId){
	 * System.out.println("deptId.........."+deptId); return
	 * employeeRepository.findByDeptId1(deptId); }
	 */
	 
	    @Transactional
	    public void updateEmployeePassword(String empId, String newPassword) {
	    	
	        int rowsUpdated = employeeRepository.updateEmployeePassword(empId, newPassword);
	        int changePasswordFlagValue= emWithDeptName.updatepaswordChangeFlageValue(empId);
	        
	        if (rowsUpdated == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }
	        
	        if (changePasswordFlagValue == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }
	        
	    }
	    
	    @Transactional
	    public void updateEmployeeDetails(EmployeeDetailsEntity updatedEmployee) { 
	    	
	    	 Optional<EmployeeDetailsEntity> existingEmployeeOptional  = employeeRepository.findByEmpId(updatedEmployee.getEmpId());
	    	 if (existingEmployeeOptional.isPresent()) {
	    	        EmployeeDetailsEntity existingEmployee = existingEmployeeOptional.get();
	    	         
	    	        System.out.println("updatedEmployee.getPassword()=="+existingEmployee	.getPassword());
	    	        // Update fields as necessary
	    	        existingEmployee.setDeptId(updatedEmployee.getDeptId());
	    	        existingEmployee.setRoleId(updatedEmployee.getRoleId());
	    	        existingEmployee.setFirstName(updatedEmployee.getFirstName());
	    	        existingEmployee.setLastName(updatedEmployee.getLastName());
	    	        existingEmployee.setEmail(updatedEmployee.getEmail());
	    	        existingEmployee.setMobileNo(updatedEmployee.getMobileNo());
	    	        existingEmployee.setAddress(updatedEmployee.getAddress());
	    	        existingEmployee.setAdharNo(updatedEmployee.getAdharNo());
	    	        existingEmployee.setPanCard(updatedEmployee.getPanCard());
	    	        existingEmployee.setDob(updatedEmployee.getDob());
	    	        existingEmployee.setDoj(updatedEmployee.getDoj());
	    	        existingEmployee.setGender(updatedEmployee.getGender());
	    	        
	    	        existingEmployee.setPrimary_emergency_contact_name(updatedEmployee.getPrimary_emergency_contact_name());
	    	        existingEmployee.setPrimary_emergency_contact_number(updatedEmployee.getPrimary_emergency_contact_number());
	    	        existingEmployee.setPrimary_emergency_contact_relation(updatedEmployee.getPrimary_emergency_contact_relation());
	    	        
	    	        existingEmployee.setSecondary_emergency_contact_name(updatedEmployee.getSecondary_emergency_contact_name());
	    	        existingEmployee.setSecondary_emergency_contact_number(updatedEmployee.getSecondary_emergency_contact_number());
	    	        existingEmployee.setSecondary_emergency_contact_relation(updatedEmployee.getSecondary_emergency_contact_relation());
	    	        
	    	        //existingEmployee.setPassword(updatedEmployee.getPassword());
	    	        
	    	        // Add other fields as necessary
	    	        employeeRepository.save(existingEmployee); // Save updated employee
	        } else {
	            // Handle case where employee does not exist
	            throw new EntityNotFoundException("Employee not found with empId: " + updatedEmployee.getEmpId());
	        }
	    }
	   
	    public String loggedEmailIdName(String email) {
			
	    	EmployeeDetailsEntity loggedEmailIdName=employeeRepository.findFirstNameAndLastNameByEmail(email);
			
			String fName = loggedEmailIdName.getFirstName();
			String sName = loggedEmailIdName.getLastName();
			
			return fName +" "+ sName;
		}
	    
	    public EmployeeOnlyDocument_Entity selectEmpDetailsForOnlyDocumentsViewById(String emp_id) {
	    	return employeeOnlyDocument_Repo.selectEmpDetailsForOnlyDocumentsViewById(emp_id);
	    }
	    
	    public EmployeeDetailsEntity selectEmpDetailsForDocumentsViewById(String emp_id) {
	    	return employeeRepository.selectEmpDetailsForDocumentsViewById(emp_id);
	    }
	    @Transactional
	    public boolean updateEmployeeDocs(String empId, byte[] profile_pic_path, byte[] pan_pic_path, byte[] aadhar_pic, byte[] tenth_pic, byte[] puc_pic, byte[] degree_pic) {
	        int rowsUpdated = employeeRepository.updateEmployeeDocumentsUploads(empId, profile_pic_path, pan_pic_path, aadhar_pic, tenth_pic, puc_pic, degree_pic);
	        System.out.println("////////"+rowsUpdated);
	        if (rowsUpdated > 0) {
	            return true;
	        }else {
	        	return false;
	        }
	    }
	    public String getProbationCompletionDate(String email) {
	        // Fetch the employee details based on empId
	    	EmployeeDetailsEntity  employee = employeeRepository.findByEmail(email);
	        return employee != null ? employee.getProbation_completed_date() : null; // Return the probation date or null if not found
	    }
	    
	   

	    @Transactional
		public boolean updateEmployeeDocs(String empId, String profile_pic_path, String pan_pic_path, String aadhar_pic, String tenth_pic, String puc_pic, String degree_pic, String offer_letter) {
		    int rowsUpdated = employeeRepository.updateEmployeeDocumentsUploads(empId, profile_pic_path, pan_pic_path, aadhar_pic, tenth_pic, puc_pic, degree_pic, offer_letter);
		      if (rowsUpdated > 0) {
		          return true;
		      }else {
		        return false;
		      }
		}
	    
	    @Transactional
		public Optional<List<Object[]>> fetch_today_absent_employee(String empId) {
			return attendenceRepo.fetch_today_absent_employee(empId);
		}
	    
	    @Transactional
		public Optional<List<Object[]>> fetch_today_absent_employee_for_super_admin() {
			return attendenceRepo.fetch_today_absent_employee_for_super_admin();
		}
	    
	    @Transactional
		public Integer fetch_today_present_employee_count() {
			Optional<Object[]> result = attendenceRepo.fetch_today_present_employee_count_for_super_admin();
			Integer presentCount=0;
			if(result.isPresent()) {
				Object[] data = result.get();
		        if (data.length > 0 && data[0] != null) {
		            // Cast to Long and convert to Integer
		            presentCount = ((Long) data[0]).intValue();
		        }
			}
			else {
			        System.out.println("No data found.");
			   }
			return presentCount;
		}
	    
	    private String hashPassword(String password) {
	        try {
	            // Create a SHA-256 digest
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] encodedHash = digest.digest(password.getBytes());

	            // Convert byte array into hex format
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : encodedHash) {
	                
	            	String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Error while hashing password", e);
	        }
	    }
	    
	    /* CHAT BOOT  === FOR DISPLAYING THE PROFILE PIC */
		 public List<EmployeeDetailsEntity> getAllEmployees() {
		        return employeeRepository.findAll();
		 }
		 
		   public String getAlternativeSaturdayEffectiveDate(String email) {
		        // Fetch the employee details based on email
		        EmployeeDetailsEntity employee = employeeRepository.findByEmail(email);
		        return employee != null ? employee.getAlternativeSaturdayEffectiveFrom() : null; // Return the alternative Saturday date or null if not found
		    }
}
