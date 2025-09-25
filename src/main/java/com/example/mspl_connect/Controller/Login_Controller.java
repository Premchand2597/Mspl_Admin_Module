package com.example.mspl_connect.Controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.AdminEntity.ActiveUser;
import com.example.mspl_connect.AdminEntity.EmployeeCustomDetailsForApprisal_Entity;
import com.example.mspl_connect.AdminRepo.ActiveUserRepository;
import com.example.mspl_connect.AdminService.ActiveUserService;
import com.example.mspl_connect.AdminService.EmployeeCustomDetails_Service;
import com.example.mspl_connect.AdminService.UserStatusService;
import com.example.mspl_connect.Entity.Login_Entity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Service.Login_Service;

import jakarta.servlet.http.HttpSession;

@Controller
public class Login_Controller {
	
	@Autowired
	private Login_Service loginService;
	
	@Autowired
	EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ActiveUserService activeUserService;
	
	@Autowired
	private ActiveUserRepository activeUserRepository;
	
	@Autowired
	private UserStatusService userStatusService;
	
	@Autowired
	private EmployeeCustomDetails_Service employeeCustomDetails_Service;
	
	private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // 16 bytes key (Change it to a secure key)

	@GetMapping("/")
	public String startPage(HttpSession session) {
		
		System.out.println("hiiiii");
		String email = (String) session.getAttribute("email");
		System.out.println("emailllllll"+email);
		
		if (session != null) {
	        session.invalidate(); //Invalidate the session
	    }
		
		return "loginPage";
	}

	
	@PostMapping("/submitLogin")
    @ResponseBody
    public Map<String, Object> loginSubmit(@RequestParam String email, @RequestParam String password, @RequestParam(required = false) String otp, HttpSession session) throws Exception {
        Map<String, Object> response = new HashMap<>();
        
        String empId = employeeRepository.findEmpidByEmail(email);
        
        session.setAttribute("email", email);
        session.setAttribute("loggedAdminEmpId", empId);
        
        String role_name = "", user_type = "";
        
        String encryptedPassword = null;
	    
	    try {
			encryptedPassword = encrypt(password); // Encrypting password
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    Login_Entity loggedInDataWithEmployeeType = loginService.autheticate(email, encryptedPassword);
        
        //Login_Entity loggedInDataWithEmployeeType = loginService.autheticate(email, password);
        System.out.println("1");
        if(loggedInDataWithEmployeeType != null) {
        		System.out.println("2");
                if(loggedInDataWithEmployeeType.getEmployee_type().equals("1")) {
                	
              	      System.out.println("3");
                     if(otp == null || otp.isEmpty()) {
                    	    System.out.println("4");
                            
                    	    // Generate OTP and send it to the user's email
	                        String generatedOTPData = loginService.generateOTP(email);
	                        System.out.println("generatedOTPData==="+generatedOTPData);
	                        //String emailSentResponse = loginService.sendEmailWithOTP(email, generatedOTPData);
	                    //    String emailSentResponse = loginService.sendEmailWithOTP(email, generatedOTPData, loggedInDataWithEmployeeType.getEmp_name(), loggedInDataWithEmployeeType.getGender());
							
                            response.put("otpRequired", true);
                            
                    } else if (loginService.validateOtp(email, otp)) {  
                   
                    	System.out.println("5");   
                        //OTP is correct, proceed to dashboard
                    	
                        role_name = loginService.getLoggedInRoleName(email);
                        user_type = loginService.getLoggedInUserType(email);  
                        System.out.println("email----------"+email);
                        String passwordChangeFalgValue=employeeRepositoryWithDeptName.findPasswordChangeFlagByEmail(email);
                        
                        session.setAttribute("role_name", role_name);
                        session.setAttribute("user_type", user_type);
                        	
                        // Add user to activeUsers Set (Thread-safe Set)
                        //activeUserService.addActiveUser(email); 
                        
                        // ✅ Save Active User upon successful login
                        // activeUserRepository.save(new ActiveUser(email));

                        
                        // Update user status to online (true)
                        // userStatusService.updateUserStatustrue(email, true);

                        if(user_type.equals("Super Admin")){
                        	
                        	EmployeeCustomDetailsForApprisal_Entity fetchEmployeesDistinctUserNamesBasedOnEmailForDispatchDetails = 
    			            		employeeCustomDetails_Service.fetchACtiveEmployeesDistinctUserNamesBasedOnEmail(email);
                        	
                        	//System.out.println("6");
        	            	session.setAttribute("role_name", role_name);
        		            session.setAttribute("user_type", user_type);
        		            session.setAttribute("username", fetchEmployeesDistinctUserNamesBasedOnEmailForDispatchDetails.getEmp_full_name());
        		            
        	            	response.put("redirect","/navbar"); // redirecting to Super Admin page           	
        	            	
                        }
                        
        	            else if(user_type.equals("Admin")){
        	            	//System.out.println("7");
        	            	session.setAttribute("role_name", role_name);
        		            session.setAttribute("user_type", user_type);
        		           
        		            System.out.println("inside admin condition");
        		            
        		            if (passwordChangeFalgValue.trim().equals("1")) {
        		                System.out.println("changed");
								/* response.put("redirect", "/changePassword"); */
        		                response.put("redirect", "/userDashboard");
        		            } else {
        		            	response.put("redirect", "/userDashboard"); // Indicate redirect to Admin(User) dashboard
        		            }
        		           // response.put("redirect", "/userDashboard"); // Indicate redirect to Admin(User) dashboard
        	            }	
        	            else {
        	            	 response.put("error", "Invalid email or password");
        	            }
                        } else {
                       response.put("invalidOTPError", "Invalid OTP");
                       response.put("otpRequired", true);
                    }     
                } else {
                        response.put("error", "Sorry! Your login credentials are inactive");
                }
        } else {
            response.put("error", "Invalid email or password");
        }
        return response; // Return the response as JSON
    }
	public static String encrypt(String data) throws Exception {
		
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
        
    }

}
