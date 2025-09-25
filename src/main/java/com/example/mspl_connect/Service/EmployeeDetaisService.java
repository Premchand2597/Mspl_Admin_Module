package com.example.mspl_connect.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EmployeeNameEmailDTO;
import com.example.mspl_connect.AdminEntity.InterComm_Entity;
import com.example.mspl_connect.AdminEntity.LeaveBalanceDetailsList_Entity;
import com.example.mspl_connect.AdminRepo.AttendenceRepo;
import com.example.mspl_connect.AdminRepo.GroupMailId_Repo;
import com.example.mspl_connect.AdminRepo.Inter_Comm_Repo;
import com.example.mspl_connect.AdminRepo.LeaveBalanceDetailsList_Repo;
import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;
import com.example.mspl_connect.Entity.Employee;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Entity.EmployeeOnlyDocument_Entity;
import com.example.mspl_connect.Entity.GroupMailId_Entity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Entity.TodayPresentEmpEntity;
import com.example.mspl_connect.Repository.DisplayEmployeeWithMisspunchRepo;
import com.example.mspl_connect.Repository.EmployeeOnlyDocument_Repo;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.FindEmployee;
import com.example.mspl_connect.Repository.PermissionRepo;

import jakarta.mail.internet.MimeMessage;
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
	
	@Autowired
	private LeaveBalanceDetailsList_Repo leaveBalanceDetailsList_Repo;
	
	@Autowired
	private PermissionRepo permissionRepo;
	
	@Autowired
	private Inter_Comm_Repo inter_Comm_Repo;
	
	@Autowired
	private GroupMailId_Repo groupMailId_Repo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // 16 bytes key (Change it to a secure key)
	
    public EmployeeDetailsEntity saveEmployee(EmployeeDetailsEntity employeeDetailsEntity) {
		String currentAddress = employeeDetailsEntity.getCorr_address();
		if (currentAddress == null || currentAddress.trim().isEmpty()) { // Check for null or empty after trimming spaces
	        employeeDetailsEntity.setCorr_address(employeeDetailsEntity.getAddress());
	    }
		
		String alternativeSaturdayLeave = (employeeDetailsEntity.getAlternativeSaturdayEffectiveFrom() != null && !employeeDetailsEntity.getAlternativeSaturdayEffectiveFrom().isEmpty()) ? employeeDetailsEntity.getAlternativeSaturdayEffectiveFrom() : null;
		employeeDetailsEntity.setAlternativeSaturdayEffectiveFrom(alternativeSaturdayLeave);
		
		String permissionReturnedEmpId = permissionRepo.findByUserId_Custom(employeeDetailsEntity.getEmpId());
		if (employeeDetailsEntity.getEmpId() != null && permissionReturnedEmpId == null) {
            permissionRepo.insertPermissionAccessForNewEmployee(employeeDetailsEntity.getEmpId());
        }
		
		String interCommEmailId = inter_Comm_Repo.findEmailExitsOrNot(employeeDetailsEntity.getEmail());
		if (employeeDetailsEntity.getEmail() != null && interCommEmailId == null) {
			String empFullName = employeeDetailsEntity.getFirstName()+" "+employeeDetailsEntity.getLastName();
			inter_Comm_Repo.insertIntercommDetailsForNewEmployee(empFullName, employeeDetailsEntity.getMobileNo(), employeeDetailsEntity.getEmail());
        }
		
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
		//Define date formatters
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
	        emp.setFirst_punch_time(validateAndFormatDate((String) result[4]));
	        emp.setLast_punch_time(validateAndFormatDate((String) result[5]));
	        emp.setLast_seen(validateAndFormatDate((String) result[6]));

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
	
	public DisplayEmployessEntity getEmployeeByEmpid(String empid)   {
		
		DisplayEmployessEntity empProfileDetailsForUpdateForm =  emWithDeptName.findByEmpid(empid);

		return empProfileDetailsForUpdateForm; 
		
	}
	
	
	public static String decrypt(String encryptedData) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
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
	 
	   /* @Transactional
	    public void updateEmployeePassword(String empId, String newPassword) {
	    	
	    	System.out.println("empId in updateEmployeePassword---"+empId);
	    	
	    	System.out.println("empIddddddddd==="+empId);
	    	System.out.println("newPassword==="+newPassword);
	        int rowsUpdated = employeeRepository.updateEmployeePassword(empId, newPassword);
	        
	        int changePasswordFlagValue= emWithDeptName.updatepaswordChangeFlageValue(empId);
	        
	        DisplayEmployessEntity passwordChangingEmployeeDetails =  emWithDeptName.findByEmpid(empId);
	        System.out.println("Team co-ordinatore==="+passwordChangingEmployeeDetails.getTeam_coordinator());
	        System.out.println("Team lead==="+passwordChangingEmployeeDetails.getTeam_lead());
	        
	        if (rowsUpdated == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }
	        
	        if (changePasswordFlagValue == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }

	    }*/
	    
	    public void updateEmployeePassword(String empId, String newPassword) {
	    	
	    	System.out.println("bbb");
	        int rowsUpdated = employeeRepository.updateEmployeePassword(empId, newPassword);
	        System.out.println("cc");
	        int changePasswordFlagValue= emWithDeptName.updatepaswordChangeFlageValue(empId);
	        
	        if (rowsUpdated == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }
	        
	        if (changePasswordFlagValue == 0) {
	            throw new RuntimeException("Failed to update password for employee with ID: " + empId);
	        }
	        
	        // If password update is successful, send email notification
	        sendPasswordUpdateEmail(empId);
	        
	    }
	    
	    
	    private void sendPasswordUpdateEmail(String empId) {
	        System.out.println("sendPasswordUpdateEmail");

	        // Fetch employee details
	        Optional<EmployeeDetailsEntity> emailByIdOption = employeeRepository.findByEmpId(empId);
	        
	        if (emailByIdOption.isEmpty()) {
	            System.out.println("No employee found with empId: " + empId);
	            return; // Exit if employee not found
	        }

	        EmployeeDetailsEntity employeeDetails = emailByIdOption.get();
	        String fromEmail = "noreply@melangesystems.com"; // Replace with your authorized email address
	        String toEmail = employeeDetails.getEmail();
	        String empFullName = employeeDetails.getFirstName() + " " + employeeDetails.getLastName();

	        // Fetch team leader and coordinator emails
	        String teamLeaderEmpId = employeeDetails.getTeam_lead_name();
	        String teamCoordinatorEmpId = employeeDetails.getTeam_co_name();

	        String TeamLeadEmail = employeeRepository.findEmailByEmpId(teamLeaderEmpId);
	        String TeamCordinatorEmail = employeeRepository.findEmailByEmpId(teamCoordinatorEmpId);

	        System.out.println("TeamLeadEmail: " + TeamLeadEmail);
	        System.out.println("TeamCordinatorEmail: " + TeamCordinatorEmail);
	        System.out.println("To Email: " + toEmail);

	        try {
	            // Create a MIME message for HTML support
	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

	            helper.setFrom(fromEmail);
	            helper.setTo(toEmail);

	            // Collect CC emails into a list
	            List<String> ccEmails = new ArrayList<>();
	            if (TeamLeadEmail != null && !TeamLeadEmail.isEmpty()) {
	                ccEmails.add(TeamLeadEmail);
	            }
	            if (TeamCordinatorEmail != null && !TeamCordinatorEmail.isEmpty()) {
	                ccEmails.add(TeamCordinatorEmail);
	            }

	            // Set CC only if there are valid emails
	            if (!ccEmails.isEmpty()) {
	                helper.setCc(ccEmails.toArray(new String[0]));
	            }

	            helper.setSubject(empFullName+" - MSPL Account Password Has Been Changed");

	            // HTML content for email
	            String emailContent = "<p>Dear " + empFullName + ",</p>" +
	                    "<p>We wish to inform you that <b>the password for your MSPLConnect account has been successfully changed.</b></p>" +
	                    "<p>If you made this change, you can safely ignore this email. However, if you did not initiate this request, " +
	                    "please contact HR or your reporting manager immediately to secure your account.</p>" +
	                    "<p>Thank you for your attention to this matter.</p>" +
	                    "<p>Best regards,<br>The Melange Team</p>";

	            helper.setText(emailContent, true); // Enable HTML content

	            // Send email
	            mailSender.send(mimeMessage); 
	            System.out.println("Password update email sent to: " + toEmail);
	            
	        } catch (Exception e) {
	            System.err.println("Error sending email: " + e.getMessage());
	        }
	    }

	    
	    /* private void sendPasswordUpdateEmail(String empId) {
	    	System.out.println("sendPasswordUpdateEmail");
	    	
	    	EmployeeDetailsEntity employeeDetails = null;
	    	Optional<EmployeeDetailsEntity> emailByIdOption = employeeRepository.findByEmpId(empId);
	    	
	    	String fromEmail = "noreply@melangesystems.com"; // Replace with your authorized email address
	    			
	    	if(emailByIdOption.isPresent()) {
	    		employeeDetails = emailByIdOption.get();
	    	}
	    	
	    	String toEmail = employeeDetails.getEmail();
	    	String employeeF_Name = employeeDetails.getFirstName();
	    	String employeeL_name = employeeDetails.getLastName();
	    	String empFullName =  employeeF_Name +" "+employeeL_name;
	    	
	    	String teamLeaderEmpId = employeeDetails.getTeam_lead_name();
	    	String teamCoordinatorEmpId = employeeDetails.getTeam_co_name();
	    	
	    	String TeamLeadEmail = employeeRepository.findEmailByEmpId(teamLeaderEmpId);
	    	String TeamCordinatorEmail = employeeRepository.findEmailByEmpId(teamCoordinatorEmpId);
	    	
	    	System.out.println("TeamLeadEmail--"+TeamLeadEmail);
	    	System.out.println("TeamCordinatorEmail--"+TeamCordinatorEmail);
	    	
	    	System.out.println("toEmailmmmmm"+toEmail);
	        SimpleMailMessage message = new SimpleMailMessage();
	        
	        message.setFrom(fromEmail); 
	        message.setTo(toEmail);
	        
	        // Collect CC emails into a list
	        List<String> ccEmails = new ArrayList<>();
	        if(TeamLeadEmail != null && !TeamLeadEmail.isEmpty()) {
	        	ccEmails.add(TeamLeadEmail);
	        }
	        
	        if(TeamCordinatorEmail != null && !TeamCordinatorEmail.isEmpty()) {
	        	ccEmails.add(TeamCordinatorEmail);
	        }
	       
	        // Set CC only if there are valid emails
	        if (!ccEmails.isEmpty()) {
	            message.setCc(ccEmails.toArray(new String[0]));
	        }

	        
	        message.setSubject("Your MSPL Account Password Has Been Changed");
	        message.setText("Dear "+empFullName+"\n\nWe wish to inform you that the password for your MSPLConnect account has been successfully changed."
	        		+ "  If you made this change, you can safely ignore this email. However, if you did not initiate this request,"
	        		+ "  please contact HR or your reporting manager immediately to secure your account. \n\n Thank you for your attention to this matter. "
	        		+ "  \n\n Best regards,\n The Melange Team");

	        mailSender.send(message);
	        System.out.println("Password update email sent to: " + toEmail); 
	        
	    }*/
	
	    
	    @Transactional
	    public void updateEmployeeDetails(EmployeeDetailsEntity updatedEmployee) {
	    	 Optional<EmployeeDetailsEntity> existingEmployeeOptional  = employeeRepository.findByEmpId(updatedEmployee.getEmpId());
	    	 if (existingEmployeeOptional.isPresent()) {
	    	        EmployeeDetailsEntity existingEmployee = existingEmployeeOptional.get();
	    	        
	    	        String inActiveReasonValue = null;
	    	        
	    	        //System.out.println("holaaaa= "+updatedEmployee.getAlternative_saturday_effective_from());
	    	        //System.out.println("holiiii= "+updatedEmployee.getLast_working_date());
	    	        
    String resignationDateValue = (updatedEmployee.getResignation_date() != null && !updatedEmployee.getResignation_date().isEmpty()) ? updatedEmployee.getResignation_date() : null;
    String lastWorkingDateValue = (updatedEmployee.getLast_working_date() != null && !updatedEmployee.getLast_working_date().isEmpty()) ? updatedEmployee.getLast_working_date() : null;
    
    if(updatedEmployee.getEmployee_type().equals("0")) {
    	inActiveReasonValue = (updatedEmployee.getIn_active_reason() != null && !updatedEmployee.getIn_active_reason().isEmpty()) ? updatedEmployee.getIn_active_reason() : null;
    }else {
    	inActiveReasonValue = null;
    }
    
    String alternativeSaturdayLeave = (updatedEmployee.getAlternativeSaturdayEffectiveFrom() != null && !updatedEmployee.getAlternativeSaturdayEffectiveFrom().isEmpty()) ? updatedEmployee.getAlternativeSaturdayEffectiveFrom() : null;
	
    //System.out.println("alternativeSaturdayLeave === "+alternativeSaturdayLeave+" ----- "+updatedEmployee.getAlternativeSaturdayEffectiveFrom());
    
				    String rawPassword = updatedEmployee.getPassword();
				    String rawPan = updatedEmployee.getPanCard();
				    String rawAadhar = updatedEmployee.getAdharNo();
				    String encryptedPassword = null, encryptedPan = null, encryptedAdhar = null;
				    try {
						encryptedPassword = encrypt(rawPassword); // Encrypting password
						encryptedPan = encrypt(rawPan);
						encryptedAdhar = encrypt(rawAadhar);
						//updatedEmployee.setPassword(encryptedPassword);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    
				    if (!existingEmployee.getPassword().equals(encryptedPassword)) {
				        sendPasswordUpdateEmail(existingEmployee.getEmpId());
				    }
				    
				    String oldEmpFullName = existingEmployee.getFirstName()+" "+existingEmployee.getLastName();
				    String newEmpFullName = updatedEmployee.getFirstName().trim()+" "+updatedEmployee.getLastName().trim();
				    
				    String oldEmpMobileNo = existingEmployee.getMobileNo();
				    String newEmpMobileNo = updatedEmployee.getMobileNo().trim();
				    
				    String oldEmpEmail = existingEmployee.getEmail();
				    String newEmpEmail = updatedEmployee.getEmail().trim();
				    
				    //System.out.println("Email checking == "+oldEmpEmail+" "+newEmpEmail);
				    
				    if(!oldEmpFullName.equals(newEmpFullName) || !oldEmpMobileNo.equals(newEmpMobileNo) || !oldEmpEmail.equals(newEmpEmail)) {
				    	//System.out.println("coming == "+oldEmpEmail+" "+newEmpEmail);
				    	updateIntercommDetailsIfEmpDetailsUpdated(oldEmpEmail, newEmpFullName, newEmpMobileNo, newEmpEmail);
				    	updateEmailInGroupIfEmpDetailsUpdated(oldEmpEmail, newEmpEmail);
				    }
				    
				    String currentAddress = updatedEmployee.getCorr_address();
					if (currentAddress == null || currentAddress.trim().isEmpty()) { // Check for null or empty after trimming spaces
						existingEmployee.setCorr_address(updatedEmployee.getAddress());
				    }else {
				    	existingEmployee.setCorr_address(updatedEmployee.getCorr_address());
				    }
    
	    	        // Update fields as necessary
	    	        existingEmployee.setDeptId(updatedEmployee.getDeptId());
	    	        existingEmployee.setRoleId(updatedEmployee.getRoleId());
	    	        existingEmployee.setFirstName(updatedEmployee.getFirstName());
	    	        existingEmployee.setLastName(updatedEmployee.getLastName());
	    	        existingEmployee.setEmail(updatedEmployee.getEmail());
	    	        existingEmployee.setMobileNo(updatedEmployee.getMobileNo());
	    	        existingEmployee.setAddress(updatedEmployee.getAddress());
	    	        existingEmployee.setAdharNo(encryptedAdhar);
	    	        existingEmployee.setPanCard(encryptedPan);
	    	        existingEmployee.setDob(updatedEmployee.getDob());
	    	        existingEmployee.setDoj(updatedEmployee.getDoj());
	    	        existingEmployee.setGender(updatedEmployee.getGender());
	    	        existingEmployee.setPassword(encryptedPassword);
	    	        existingEmployee.setPersonal_email(updatedEmployee.getPersonal_email());
	    	        existingEmployee.setFather_name(updatedEmployee.getFather_name());
	    	        existingEmployee.setMother_name(updatedEmployee.getMother_name());
	    	        existingEmployee.setQualification_tenth(updatedEmployee.getQualification_tenth());
	    	        existingEmployee.setTenth_school_name(updatedEmployee.getTenth_school_name());
	    	        existingEmployee.setTenth_board_name(updatedEmployee.getTenth_board_name());
	    	        existingEmployee.setTenth_percentage(updatedEmployee.getTenth_percentage());
	    	        existingEmployee.setTenth_completed_date(updatedEmployee.getTenth_completed_date());
	    	        existingEmployee.setQualification_puc(updatedEmployee.getQualification_puc());
	    	        existingEmployee.setPuc_college_name(updatedEmployee.getPuc_college_name());
	    	        existingEmployee.setPuc_board_name(updatedEmployee.getPuc_board_name());
	    	        existingEmployee.setPuc_percentage(updatedEmployee.getPuc_percentage());
	    	        existingEmployee.setPuc_completed_date(updatedEmployee.getPuc_completed_date());
	    	        existingEmployee.setQualification_ug(updatedEmployee.getQualification_ug());
	    	        existingEmployee.setUg_college_name(updatedEmployee.getUg_college_name());
	    	        existingEmployee.setUg_degree_name(updatedEmployee.getUg_degree_name());
	    	        existingEmployee.setUg_university_name(updatedEmployee.getUg_university_name());
	    	        existingEmployee.setUg_type(updatedEmployee.getUg_type());
	    	        existingEmployee.setUg_percentage(updatedEmployee.getUg_percentage());
	    	        existingEmployee.setUg_completed_date(updatedEmployee.getUg_completed_date());
	    	        existingEmployee.setQualification_other(updatedEmployee.getQualification_other());
	    	        existingEmployee.setOther_college_name(updatedEmployee.getOther_college_name());
	    	        existingEmployee.setOther_degree_name(updatedEmployee.getOther_degree_name());
	    	        existingEmployee.setOther_university_name(updatedEmployee.getOther_university_name());
	    	        existingEmployee.setOther_type(updatedEmployee.getOther_type());
	    	        existingEmployee.setOther_percentage(updatedEmployee.getOther_percentage());
	    	        existingEmployee.setOther_completed_date(updatedEmployee.getOther_completed_date());
	    	        existingEmployee.setQualification_pg(updatedEmployee.getQualification_pg());
	    	        existingEmployee.setPg_college_name(updatedEmployee.getPg_college_name());
	    	        existingEmployee.setPg_degree_name(updatedEmployee.getPg_degree_name());
	    	        existingEmployee.setPg_university_name(updatedEmployee.getPg_university_name());
	    	        existingEmployee.setPg_type(updatedEmployee.getPg_type());
	    	        existingEmployee.setPg_percentage(updatedEmployee.getPg_percentage());
	    	        existingEmployee.setPg_completed_date(updatedEmployee.getPg_completed_date());
	    	        existingEmployee.setCandidate_type(updatedEmployee.getCandidate_type());
	    	        existingEmployee.setFresher_expected_salary(updatedEmployee.getFresher_expected_salary());
	    	        existingEmployee.setExperience_total_years(updatedEmployee.getExperience_total_years());
	    	        existingEmployee.setExperience_expected_salary(updatedEmployee.getExperience_expected_salary());
	    	        existingEmployee.setSub_dept_name(updatedEmployee.getSub_dept_name());
	    	        existingEmployee.setTeam_lead_name(updatedEmployee.getTeam_lead_name());
	    	        existingEmployee.setTeam_co_name(updatedEmployee.getTeam_co_name());
	    	        existingEmployee.setCurrent_salary(updatedEmployee.getCurrent_salary());
	    	        existingEmployee.setProbation_completed_date(updatedEmployee.getProbation_completed_date());
	    	        existingEmployee.setEmployee_type(updatedEmployee.getEmployee_type());
	    	        existingEmployee.setUsertype(updatedEmployee.getUsertype());
	    	        existingEmployee.setPrimary_emergency_contact_name(updatedEmployee.getPrimary_emergency_contact_name());
	    	        existingEmployee.setPrimary_emergency_contact_relation(updatedEmployee.getPrimary_emergency_contact_relation());
	    	        existingEmployee.setPrimary_emergency_contact_number(updatedEmployee.getPrimary_emergency_contact_number());
	    	        existingEmployee.setSecondary_emergency_contact_name(updatedEmployee.getSecondary_emergency_contact_name());
	    	        existingEmployee.setSecondary_emergency_contact_relation(updatedEmployee.getSecondary_emergency_contact_relation());
	    	        existingEmployee.setSecondary_emergency_contact_number(updatedEmployee.getSecondary_emergency_contact_number());
	    	        existingEmployee.setResignation_date(resignationDateValue);
	    	        existingEmployee.setLast_working_date(lastWorkingDateValue);
	    	        existingEmployee.setPassword_change_flag("0");
	    	        existingEmployee.setIn_active_reason(inActiveReasonValue);
	    	        existingEmployee.setBlood_group(updatedEmployee.getBlood_group());
	    	        existingEmployee.setAlternativeSaturdayEffectiveFrom(alternativeSaturdayLeave);
	    	        existingEmployee.setOfficial_dob(updatedEmployee.getOfficial_dob());
	    	        // Add other fields as necessary
	    	        
	    	        employeeRepository.save(existingEmployee); // Save updated employee
	        } else {
	            // Handle case where employee does not exist
	            throw new EntityNotFoundException("Employee not found with empId: " + updatedEmployee.getEmpId());
	        }
	    }
	    
	    public static String encrypt(String data) throws Exception {
	        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
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
	    public boolean updateEmployeeDocs(String empId, String profile_pic_path, String pan_pic_path, String aadhar_pic, String tenth_pic,
	    		String puc_pic, String degree_pic, String offer_letter, String pg_pic, String exp_letter_pic, String payslip_pic, String diploma_pic, 
	    		String bank_check_pic, String other_pic, String other_pic2, String other_pic3, String other_pic4) {
	        int rowsUpdated = employeeRepository.updateEmployeeDocumentsUploads(empId, profile_pic_path, pan_pic_path, aadhar_pic, tenth_pic, 
	        		puc_pic, degree_pic, offer_letter, pg_pic, exp_letter_pic, payslip_pic, diploma_pic, bank_check_pic, other_pic, other_pic2, other_pic3, 
	        		other_pic4);
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
		   
		   public List<String> fetchInactiveEmployeesEmailId(){
				return emWithDeptName.getInactiveEmployeesEmailId();
			}
		   
		   @Transactional
		    public void updateImportedToEmployeeTableAsAFlagInStudentTable(String candidateMobileNumber) {
		        employeeRepository.updateImportedToEmployeeTableFlagInStudentTable(candidateMobileNumber);
		    }
		   
		   public boolean isEmpIdAlreadyPresentOrNot(String empid) {
			    String fetchedEmpId = emWithDeptName.checkEmpIdIsAlreadyPresentOrNot(empid);
			    return fetchedEmpId != null && fetchedEmpId.equals(empid);
			}
		   
		   public String fetchLastInsertedEmpIdValue() {
				return emWithDeptName.getLastInsertedEmpIdValue();
			}
		   
		   @Transactional
		    public List<LeaveBalanceDetailsList_Entity> fetchAllEmpsLeaveBalanceDetails(String current_financial_year){
				return leaveBalanceDetailsList_Repo.fetchAllEmployeesLeaveBalanceData(current_financial_year);
			}
		    
		    @Transactional
		    public LeaveBalanceDetailsList_Entity getEmployeesLeaveBalanceDataBasedOnEmpId(String current_financial_year, String empId){
				return leaveBalanceDetailsList_Repo.fetchEmployeesLeaveBalanceDataBasedOnEmpId(current_financial_year, empId);
			}
		    
		    void updateIntercommDetailsIfEmpDetailsUpdated(String old_email, String emp_name, String mobile_no, String email) {
				InterComm_Entity fetchedInterCommData = inter_Comm_Repo.fetchInterCommDataBasedOnEmail(old_email);
				if(fetchedInterCommData != null) {
					inter_Comm_Repo.update_InterComm_details(fetchedInterCommData.getId(), emp_name, mobile_no, email, 
							fetchedInterCommData.getTele_number(), fetchedInterCommData.getCubical_number(), fetchedInterCommData.getSeat_place(), 
							fetchedInterCommData.getFloor_number(), fetchedInterCommData.getRoom_number());
				}
			}
		 
		 public void updateEmailInGroupIfEmpDetailsUpdated(String oldEmail, String newEmail) {
		        GroupMailId_Entity entity = groupMailId_Repo.fetchParticularGroupMailData(oldEmail);
		        if (entity != null) {
		        	groupMailId_Repo.updateGroupMailId(oldEmail, newEmail);
		        }
		    }
		 
		 public String fetchTeamCoOrTeamLeadNameBasedOnHisEmpIdForEmpProfilePageInHR(String empId) {
				return emWithDeptName.fetchTeamCoOrTeamLeadNameBasedOnHisEmpIdForEmpProfilePageInHR(empId);
			}
		 
		 public String getEmployeeTypeBasedOnEmpId(String emp_id) {
		    	return employeeRepository.fetchEmployeeType(emp_id);
		    }
		   
}
