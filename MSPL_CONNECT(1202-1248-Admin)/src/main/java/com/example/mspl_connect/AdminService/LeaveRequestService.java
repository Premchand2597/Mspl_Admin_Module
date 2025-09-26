package com.example.mspl_connect.AdminService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminController.LeaveRequestsListController;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;
import com.example.mspl_connect.AdminRepo.LeaveApplicationWithProfileRepo;
import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.EmployeeDetailsEntity;
import com.example.mspl_connect.Repository.EmployeeRepository;
import com.example.mspl_connect.Repository.EmployeeRepositoryWithDeptName;
import com.example.mspl_connect.Repository.LeaveApplicationRepo;
import com.google.protobuf.TextFormat.ParseException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestService {
	
	@Autowired
    private LeaveApplicationRepo leaveApplicationRepo;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeRepositoryWithDeptName employeeRepositoryWithDeptName;
	
	@Autowired
	private LeaveApplicationWithProfileRepo applicationWithProfileRepo;
	

	@Autowired
    private JavaMailSender mailSender;

	
	public List<LeaveApplication> getLeaveRequest(int adminDept,String empId){
		List<LeaveApplication> leaveList=leaveApplicationRepo.getleaveRequest(adminDept,empId);
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        
		 return leaveList.stream().map(leave -> {
	            try {
	                String formattedDate = targetFormat.format(originalFormat.parse(leave.getFrom_date()));
	                leave.setFrom_date(formattedDate);
	                
	                String formatteToDate = targetFormat.format(originalFormat.parse(leave.getTo_date()));
	                leave.setTo_date(formatteToDate);
	            } catch (Exception e) {
	                // Handle parsing and formatting exceptions here
	                e.printStackTrace();
	            }
	            return leave;
	        }).collect(Collectors.toList());
	}
	
	public List<LeaveApplicationWithProfile> getNewLeaveRequest(int adminDept,String empId){
		
		System.out.println("hiiiiiiii");
		List<LeaveApplicationWithProfile> leaveList;
		//System.out.println("pavanaaa");
		
		if(adminDept == 0) {
			System.out.println("SSSSSSSSSSuper Admin");
			leaveList = applicationWithProfileRepo.getNewleaveRequestWithProfileForSA1();
			System.out.println("SSSSSSSSSSuper Admin"+leaveList);
		}
		else {
			System.out.println("AAAAAAAAAAAAAdmin");
			leaveList = applicationWithProfileRepo.getNewleaveRequestWithProfile(empId);
		}
		
		//leaveList=applicationWithProfileRepo.getNewleaveRequestWithProfile(empId);	
		
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        
		 return leaveList.stream().map(leave -> {
	            try {
	            	if (leave.getFromDate() != null && !leave.getFromDate().isEmpty()) {
	                    String formattedDate = targetFormat.format(originalFormat.parse(leave.getFromDate()));
	                    leave.setFromDate(formattedDate);
	                } else {
	                    leave.setFromDate("N/A"); // Default value for missing date
	                }
	                
	                if (leave.getToDate() != null && !leave.getToDate().isEmpty()) {
	                    String formattedToDate = targetFormat.format(originalFormat.parse(leave.getToDate()));
	                    leave.setToDate(formattedToDate);
	                } else {
	                    leave.setToDate("N/A"); // Default value for missing date
	                }
	            } catch (Exception e) {
	                // Handle parsing and formatting exceptions here
	                e.printStackTrace();
	            }
	            return leave;
	        }).collect(Collectors.toList());
	}

	
	    @Transactional
	    public void approveLeave(String requestId,String loggedUserName,String status) {
		  System.out.println("apppppppprove");
	    	String empIiiid=leaveApplicationRepo.getEmpIdByID(requestId);
	    	System.out.println("---"+empIiiid);
	    	
		    String email=employeeRepository.findEmailByEmpId(empIiiid);
		    System.out.println("email----"+email);
		    
	        try {
	            leaveApplicationRepo.updateApprovalStatus(requestId, status,loggedUserName);
	            // Send email if the update is successful
	            sendApprovalEmail(email, status,requestId,"");
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid employee ID format", e);
	        } catch (Exception e) {
	            throw new RuntimeException("Error during leave approval", e);
	        }	        
	    }
	    
		public void rejectLeave(String requestId, String loggedUserName, String status, String reason) {
			System.out.println("Rejecteeeeeed");
			String empIiiid=leaveApplicationRepo.getEmpIdByID(requestId);
	    	System.out.println("---"+empIiiid);
	    	
		    String email=employeeRepository.findEmailByEmpId(empIiiid);
		    System.out.println("email----"+email);
		    
			try {
				System.out.println("reason-"+reason);
	            leaveApplicationRepo.updateRejectedStatus(requestId, status,loggedUserName,reason);
	            sendApprovalEmail(email, status,requestId,reason);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid employee ID format", e);
	        } catch (Exception e) {
	            throw new RuntimeException("Error during leave approval", e);
	        }
		}
	    
	    private void sendApprovalEmail(String recipientEmail, String status,String requestId,String reason) throws MessagingException {
	    	     System.out.println("recipientEmail---"+recipientEmail);
	    	     MimeMessage message = mailSender.createMimeMessage();
	    	     MimeMessageHelper helper = new MimeMessageHelper(message, true);
	             String fromEmail = "noreply@melangesystems.com"; // Replace with your authorized email address
	             DisplayEmployessEntity employeeData =  employeeRepositoryWithDeptName.findByEmail(recipientEmail);
	             
	             System.out.println("employeeData....."+employeeData);
	             String prefix = "";
	             String teamLeadPrefix="";
	             String teamCoOrdinatorPrefix="";
	             String hrPrefix="";
	             
	             String teamLeadFullName="";
	             String teamCoordinatorFullName="";
	             
	            System.out.println("requestId----"+requestId);
	 		    LeaveApplication leavedEtailsById=leaveApplicationRepo.findById(requestId);
	 		    System.out.println("leaveDetailsById---" + leavedEtailsById);

	 		    // Convert and format the dates
	 		    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 		    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 		    
	             String fromDate=leavedEtailsById.getFrom_date();
	             String toDate=leavedEtailsById.getTo_date();
	             
	             String formattedFromDate = "";
	             String formattedToDate = "";
	             String hrFullName = "";

	             if (fromDate != null && !fromDate.isEmpty()) {
	                 LocalDate fromLocalDate = LocalDate.parse(fromDate, inputFormatter);
	                 formattedFromDate = fromLocalDate.format(outputFormatter);
	             }

	             if (toDate != null && !toDate.isEmpty()) {
	                 LocalDate toLocalDate = LocalDate.parse(toDate, inputFormatter);
	                 formattedToDate = toLocalDate.format(outputFormatter);
	             }

	             System.out.println("Formatted From Date: " + formattedFromDate);
	             System.out.println("Formatted To Date: " + formattedToDate);
	             
	             String leaveType=leavedEtailsById.getLeaveType();
	             System.out.println("1---"+leaveType);
	             String teamLead = employeeData.getTeam_lead();//System.out.println();
	             System.out.println("2---"+teamLead);
	             String teamCoordinator = employeeData.getTeam_coordinator();
	             System.out.println("3---"+teamCoordinator);
	             String hrEmpid=employeeRepositoryWithDeptName.findHRName();
	             System.out.println("4---"+hrEmpid);
	             
	             if (teamLead != null && !teamLead.isEmpty()) {
		             DisplayEmployessEntity teamLeadGenderAndNameByEmpId = employeeRepositoryWithDeptName.findGenderAndNameByEmpId(teamLead);
		             teamLeadFullName = teamLeadGenderAndNameByEmpId != null && teamLeadGenderAndNameByEmpId.getFullName() != null
		            		    ? teamLeadGenderAndNameByEmpId.getFullName().trim()
		            		    : "";
		             System.out.println("5---"+teamLeadFullName);
		             String teamLeadGender = (teamLeadGenderAndNameByEmpId.getGender() == null || teamLeadGenderAndNameByEmpId.getGender().isEmpty()) ? "" : teamLeadGenderAndNameByEmpId.getGender();
		             System.out.println("6---"+teamLeadGender);
		             
			         	if (teamLeadGender == null || teamLeadGender.trim().isEmpty()) {
							teamLeadPrefix = ""; // Default to empty string if null or empty
						} else if (teamLeadGender.equals("Male")) {
							System.out.println("MALE");
							teamLeadPrefix = "Mr.";
						} else {
							System.out.println("FEMALE");
							teamLeadPrefix = "Ms.";
						}
	             }
	             // System.out.println("+++ Query result: " + Arrays.toString(teamLeadGenderAndNameByEmpId));
	             if (teamCoordinator != null && !teamCoordinator.isEmpty()) {
	             DisplayEmployessEntity teamCoordinatorGenderAndNameByEmpId = employeeRepositoryWithDeptName.findGenderAndNameByEmpId(teamCoordinator);
	             System.out.println("7---"+teamCoordinatorGenderAndNameByEmpId);
	             //String teamCoordinatorFullName = teamCoordinatorGenderAndNameByEmpId.getFullName();
	             teamCoordinatorFullName = teamCoordinatorGenderAndNameByEmpId != null && teamCoordinatorGenderAndNameByEmpId.getFullName() != null
	            		    ? teamCoordinatorGenderAndNameByEmpId.getFullName().trim()
	            		    : "";
	             System.out.println("8---"+hrEmpid);
	             String teamCoordinatorGender = teamCoordinatorGenderAndNameByEmpId.getGender();
	             System.out.println("9---"+teamCoordinatorGender);
	             
					if (teamCoordinatorGender == null || teamCoordinatorGender.trim().isEmpty()) {
						teamCoOrdinatorPrefix = ""; // Default to empty string if null or empty
					} else if (teamCoordinatorGender.equals("Male")) {
						System.out.println("MALE");
						teamCoOrdinatorPrefix = "Mr.";
					} else {
						System.out.println("FEMALE");
						teamCoOrdinatorPrefix = "Ms.";
					}
	             }
	             
	             if (hrEmpid != null && !hrEmpid.isEmpty()) {
		             DisplayEmployessEntity hrGenderAndNameByEmpId = employeeRepositoryWithDeptName.findGenderAndNameByEmpId(hrEmpid);
		             hrFullName = hrGenderAndNameByEmpId.getFullName();
		             String hrGender = hrGenderAndNameByEmpId.getGender();
		             System.out.println("10---"+hrGenderAndNameByEmpId);
		             if(hrGender.equals("Male")) {
			            	System.out.println("MALE");
			            	hrPrefix = "Mr.";
			 		  	  } else {
			 		  		System.out.println("FEMALE");
			 		  		hrPrefix = "Ms.";
			 		 }
	             }
	             //Object[] teamCoordinatorGenderAndNameByEmpId = employeeRepositoryWithDeptName.findGenderAndNameByEmpId(teamCoordinator);
	             
	             if (leaveType.equals("EL")) {
	            	    leaveType = "Earned Leave";
	            	} else if (leaveType.equals("CL")) {
	            	    leaveType = "Casual Leave";
	            	} else if (leaveType.equals("SL")) {
	            	    leaveType = "Sick Leave";
	            	} 
	             
	             
	             System.out.println("employeeData.getGender()"+employeeData.getGender());
	             String gender=employeeData.getGender();
	             System.out.println("employeeData.getGender()"+gender);
	             
	             if(gender.equals("Male")) {
	            	System.out.println("MALE");
	 		  		prefix = "Mr.";
	 		  	  } else {
	 		  		System.out.println("FEMALE");
	 		  		prefix = "Ms.";
	 		  	  }
	            
	            //String originalSenderEmail = "premchand.s@melangesystems.com";
	            	             
	            // helper.setFrom(originalSenderEmail);
	            helper.setFrom(fromEmail); // Configure the "From" email address
	            helper.setTo(recipientEmail);	            
	            helper.setSubject("Your Leave Request Has Been "+ status);  
	            //helper.setText("Dear "+ prefix + employeeData.getFullName() + ",\n\n Your leave Request has been  " + status +"\n\nThank you for using our services.\n\nBest regards,\nThe Melange Team");
	            
	            if(status.equals("Approved")) {
	            	helper.setText(
	            		    "Dear " + prefix + employeeData.getFullName() +
	            		    ",<br><br>We are pleased to inform you that your leave request has been " + status + ". Below are the details:" +
	            		    "<br><br><b>Leave Details:</b><br><br>" +
	            		    "<b>Leave Type:</b> " + leaveType +
	            		    "<br><b>Leave Dates:</b> " + formattedFromDate + " to " + formattedToDate +
	            		    "<br><br>If you have any questions or need to make adjustments, please contact [" + teamLeadPrefix + " " + teamLeadFullName + "/" + teamCoOrdinatorPrefix + " " + teamCoordinatorFullName+"/"+hrPrefix+" "+hrFullName + "]" +
	            		    "<br><br>Enjoy your time off!" +
	            		    "<br><br>Best regards," +
	            		    "<br>The Melange Team",
	            		    true
	            		);

	            }
	            else {	            	
	            	helper.setText(
	            		    "Dear " + prefix + employeeData.getFullName() +
	            		    ",<br><br>We regret to inform you that your recent leave request has been rejected by your manager." +
	            		    "<br><br><b>Leave Details:</b><br><br>" +
	            		    "<b>Leave Type:</b> " + leaveType +
	            		    "<br><b>Leave Dates:</b> " + formattedFromDate + " to " + formattedToDate +
	            		    "<br><b>Reason:</b> " + reason +
	            		     ",<br><br>Please contact your manager directly if you need further clarification on the decision or if you would like to discuss alternate arrangements." +
	            		    "<br><br>If you wish to submit a new request or make any changes, please feel free to update your leave request in the MSPL_CONNECT Application" +
	            		    "<br><br>Best regards," +
	            		    "<br><br>The Melange Team",
	            		    true
	            		);
	            }
	            try {
	            		System.out.println("message-----"+message);
			            mailSender.send(message);
		            } catch (Exception e) {
			            throw new RuntimeException("Error while sending email", e);
			        }
		 }	
	
   /* public List<LeaveApplication> getleaveRequest(int adminDept, String empid) {
        List<LeaveApplication> leaves = leaveApplicationRepo.getleaveRequestByEmpID(empid);
        System.out.println("leaves in service before convert admin++++" + leaves);

        // Define the expected input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat inputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        for (LeaveApplication leave : leaves) {
            try {
                // Convert from_date and to_date from String to Date and format them
                if (leave.getFrom_date() != null) {
                    Date fromDate = inputFormat.parse(leave.getFrom_date()); // Parse the String into Date
                    leave.setFrom_date(outputFormat.format(fromDate));       // Format Date to required format
                }

                if (leave.getTo_date() != null) {
                    Date toDate = inputFormat.parse(leave.getTo_date());     // Parse the String into Date
                    leave.setTo_date(outputFormat.format(toDate));           // Format Date to required format
                }

                // Format requested_date
                // Format requested_date
	            if (leave.getRequested_date() != null) {
	                String requestedDateStr = leave.getRequested_date();

	                System.out.println("requestedDateStr.length()--" + requestedDateStr.length());
	                // Check if requested_date only contains the date (length of 10 means yyyy-MM-dd format)
	                if (requestedDateStr.length() == 10) {
	                    requestedDateStr += " 00:00:00"; // Append time as 00:00:00 if no time exists
	                }

	                // Define formatter for date-time format
	                SimpleDateFormat inputDateTimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                SimpleDateFormat outputDateTimeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	                try {
	                    // Parse date with or without time
	                    Date requestedDate = inputDateTimeFormat1.parse(requestedDateStr);
	                    // Format date to include both date and time
	                    String formattedDate = outputDateTimeFormat1.format(requestedDate);
	                    leave.setRequested_date(formattedDate); // Set the formatted date back to the entity
	                } catch (Exception e) {
	                    e.printStackTrace(); // Log parsing issues
	                }
	            }
            } catch (Exception e) {
                e.printStackTrace(); // Log the error in case of parsing issues
            }
        }

        // Sort leaves by 'way' field in descending order
        leaves.sort((leave1, leave2) -> {
            return Integer.compare(leave2.getWay(), leave1.getWay());
        });

        System.out.println("leaves in service after convert admin++++" + leaves);
        return leaves;
    }*/
	    
	    public List<LeaveApplication> getleaveRequest(int adminDept, String empid) {
	        List<LeaveApplication> leaves = leaveApplicationRepo.getleaveRequestByEmpID(empid);
	        System.out.println("Leaves before conversion: " + leaves);

	        // Define date formatters **once** for reuse
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	        SimpleDateFormat inputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	        for (LeaveApplication leave : leaves) {
	            try {
	                // ✅ Format `from_date`
	                if (leave.getFrom_date() != null && !leave.getFrom_date().isEmpty()) {
	                    leave.setFrom_date(formatDate(leave.getFrom_date(), inputFormat, outputFormat));
	                }

	                // ✅ Format `to_date`
	                if (leave.getTo_date() != null && !leave.getTo_date().isEmpty()) {
	                    leave.setTo_date(formatDate(leave.getTo_date(), inputFormat, outputFormat));
	                }

	                // ✅ Format `requested_date`
	                if (leave.getRequested_date() != null && !leave.getRequested_date().isEmpty()) {
	                    // Check if `requested_date` has a time or not
	                    if (leave.getRequested_date().length() == 10) {
	                        leave.setRequested_date(leave.getRequested_date() + " 00:00:00"); // Append time if missing
	                    }
	                    leave.setRequested_date(formatDate(leave.getRequested_date(), inputDateTimeFormat, outputDateTimeFormat));
	                }
	            } catch (Exception e) {
	                System.err.println("Error formatting dates for leave request: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	        
	        
	        // Sort by `requested_date` in descending order (latest first)
	        leaves.sort((leave1, leave2) -> {
	            try {
	                Date date1 = inputDateTimeFormat.parse(leave1.getRequested_date());
	                Date date2 = inputDateTimeFormat.parse(leave2.getRequested_date());
	                return date2.compareTo(date1); // Descending order (latest first)
	            } catch (Exception e) {
	                return 0; // In case of parsing error, do not change order
	            }
	        });
	        
	        System.out.println("Leaves after conversion: " + leaves);
	        return leaves;
	    }

	    /**
	     * 📌 Helper method to format date safely
	     */
	    private String formatDate(String dateStr, SimpleDateFormat inputFormat, SimpleDateFormat outputFormat) {
	        try {
	            Date date = inputFormat.parse(dateStr);
	            return outputFormat.format(date);
	        } catch (Exception e) {
	            System.err.println("Error formatting date: " + dateStr);
	            return dateStr; // Return the original date if formatting fails
	        }
	    }

	
	public List<LeaveApplication> getProccesedleaveRequest(int adminDept, String empid) {
		
		List<LeaveApplication> leaves;
		if (adminDept == 0) {
			leaves = leaveApplicationRepo.getProccessedleaveRequestFoeSA(empid);
		}
		else {
			leaves = leaveApplicationRepo.getProccessedleaveRequest(empid);
		}
	    //leaves.stream().forEach(i->System.out.println("before convert "+i.getRequested_date()));
	    
	    // Define the expected input and output date formats
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    SimpleDateFormat inputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
	    for (LeaveApplication leave : leaves) {
	        try {
	            // Convert from_date and to_date from String to Date and format them
	            if (leave.getFrom_date() != null) {
	                Date fromDate = inputFormat.parse(leave.getFrom_date()); // Parse the String into Date
	                leave.setFrom_date(outputFormat.format(fromDate));       // Format Date to required format
	            }

	            if (leave.getTo_date() != null) {
	                Date toDate = inputFormat.parse(leave.getTo_date());     // Parse the String into Date
	                leave.setTo_date(outputFormat.format(toDate));           // Format Date to required format
	            }
	            // Format requested_date
	            if (leave.getRequested_date() != null) {
	                String requestedDateStr = leave.getRequested_date();

	                System.out.println("requestedDateStr.length()--" + requestedDateStr.length());
	                // Check if requested_date only contains the date (length of 10 means yyyy-MM-dd format)
	                if (requestedDateStr.length() == 10) {
	                    requestedDateStr += " 00:00:00"; // Append time as 00:00:00 if no time exists
	                }

	                // Define formatter for date-time format
	                SimpleDateFormat inputDateTimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                SimpleDateFormat outputDateTimeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	                try {
	                    // Parse date with or without time
	                    Date requestedDate = inputDateTimeFormat1.parse(requestedDateStr);
	                    // Format date to include both date and time
	                    String formattedDate = outputDateTimeFormat1.format(requestedDate);
	                    leave.setRequested_date(formattedDate); // Set the formatted date back to the entity
	                } catch (Exception e) {
	                    e.printStackTrace(); // Log parsing issues
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace(); // Log the error in case of parsing issues
	        }
	    }
	    
	    // Inside your method before returning leaveList
	    leaves.sort((leave1, leave2) -> {
	        // Sort based on the 'way' value; descending order (1 first, 0 later)
	        return Integer.compare(leave2.getWay(), leave1.getWay());
	    });	  
	    
	    leaves.stream().forEach(i->System.out.println("after convert "+i.getRequested_date()));
	    return leaves;
	}
	
	public List<LeaveApplication> getRejectedleaveRequest(int adminDept, String empid) {
		
		
		List<LeaveApplication> leaves;
		if (adminDept == 0) {
			leaves = leaveApplicationRepo.getRejectedleaveRequestForSA(empid);
		}
		else {
			leaves = leaveApplicationRepo.getRejectedleaveRequest(empid);
		}
	    leaves.stream().forEach(i->System.out.println("before convert "+i.getRequested_date()));
	    
	    // Define the expected input and output date formats
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    SimpleDateFormat inputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
	    for (LeaveApplication leave : leaves) {
	        try {
	            // Convert from_date and to_date from String to Date and format them
	            if (leave.getFrom_date() != null) {
	                Date fromDate = inputFormat.parse(leave.getFrom_date()); // Parse the String into Date
	                leave.setFrom_date(outputFormat.format(fromDate));       // Format Date to required format
	            }

	            if (leave.getTo_date() != null) {
	                Date toDate = inputFormat.parse(leave.getTo_date());     // Parse the String into Date
	                leave.setTo_date(outputFormat.format(toDate));           // Format Date to required format
	            }
	            // Format requested_date
	            if (leave.getRequested_date() != null) {
	                String requestedDateStr = leave.getRequested_date();

	                System.out.println("requestedDateStr.length()--" + requestedDateStr.length());
	                // Check if requested_date only contains the date (length of 10 means yyyy-MM-dd format)
	                if (requestedDateStr.length() == 10) {
	                    requestedDateStr += " 00:00:00"; // Append time as 00:00:00 if no time exists
	                }

	                // Define formatter for date-time format
	                SimpleDateFormat inputDateTimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                SimpleDateFormat outputDateTimeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	                try {
	                    // Parse date with or without time
	                    Date requestedDate = inputDateTimeFormat1.parse(requestedDateStr);
	                    // Format date to include both date and time
	                    String formattedDate = outputDateTimeFormat1.format(requestedDate);
	                    leave.setRequested_date(formattedDate); // Set the formatted date back to the entity
	                } catch (Exception e) {
	                    e.printStackTrace(); // Log parsing issues
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace(); // Log the error in case of parsing issues
	        }
	    }
	    
	    // Inside your method before returning leaveList
	    leaves.sort((leave1, leave2) -> {
	        // Sort based on the 'way' value; descending order (1 first, 0 later)
	        return Integer.compare(leave2.getWay(), leave1.getWay());
	    });	  
	    
	    leaves.stream().forEach(i->System.out.println("after convert "+i.getRequested_date()));
	    return leaves;
	}
	
	
	
	 // Method to convert the requested date format
     /* private String formatDate(Date date, SimpleDateFormat dateFormatter) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "01-01-1970 00:00:00";  // Default date if null (you can adjust this as needed)
        }
     }*/
	public List<LeaveApplication> findByEmpidNot(String loggedempId) {
		
		List<LeaveApplication> leaves = leaveApplicationRepo.findByEmpidNot(loggedempId);    
		System.out.println("leaves in service before convert super admin--"+leaves);
	    // Define the expected input and output date formats
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

	    for (LeaveApplication leave : leaves) {
	        try {
	            // Convert from_date and to_date from String to Date and format them
	            if (leave.getFrom_date() != null) {
	                Date fromDate = inputFormat.parse(leave.getFrom_date()); // Parse the String into Date
	                leave.setFrom_date(outputFormat.format(fromDate));       // Format Date to required format
	            }

	            if (leave.getTo_date() != null) {
	                Date toDate = inputFormat.parse(leave.getTo_date());     // Parse the String into Date
	                leave.setTo_date(outputFormat.format(toDate));           // Format Date to required format
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Log the error in case of parsing issues
	        }
	    }
	    
	    // Inside your method before returning leaveList
	    leaves.sort((leave1, leave2) -> {
	        // Sort based on the 'way' value; descending order (1 first, 0 later)
	        return Integer.compare(leave2.getWay(), leave1.getWay());
	    });
	    
	    System.out.println("leaves in service after convert admin----"+leaves);
	    return leaves;
	}

	public String updateLeaveRequest(LeaveApplication updatedRequest) {
	    System.out.println("updatedRequest in service ===" + updatedRequest);

	    // Fetch the existing leave application from the database
	    Optional<LeaveApplication> existingLeaveRequestOpt = leaveApplicationRepo.findById(updatedRequest.getId());
	    LeaveApplication leaveOldData = null;
	    
	    if (existingLeaveRequestOpt.isPresent()) {
	    	leaveOldData = existingLeaveRequestOpt.get();
	        System.out.println("existingLeaveRequest====="+leaveOldData);
	        // Continue processing
	    } else {
	        System.out.println("leaveApplicationRepo is empty");
	    }
	    System.out.println("existingLeaveRequest after if===== "+leaveOldData);
	    
	    // Check if the leave request exists
	    if (existingLeaveRequestOpt.isPresent()) {
	        LeaveApplication existingLeaveRequest = existingLeaveRequestOpt.get();
	        System.out.println("existingLeaveRequest after inside===== "+leaveOldData);
	        // Update the fields of the existing leave application with the new values
	        existingLeaveRequest.setWay(1);
	        existingLeaveRequest.setEmployee_name(updatedRequest.getEmployee_name());
	        existingLeaveRequest.setLeaveType(updatedRequest.getLeaveType());
	        
	        System.out.println("existingLeaveRequest.getFrom_date()====="+leaveOldData.getFrom_date());
	        System.out.println("existingLeaveRequest.getTo_date()====="+leaveOldData.getTo_date());
	        existingLeaveRequest.setOld_from_date(leaveOldData.getFrom_date());
	        existingLeaveRequest.setOld_to_date(leaveOldData.getTo_date());
	        
	        existingLeaveRequest.setFrom_date(updatedRequest.getFrom_date());
	        existingLeaveRequest.setTo_date(updatedRequest.getTo_date());
	        existingLeaveRequest.setReason(updatedRequest.getReason());
	        existingLeaveRequest.setProcessed(false);
	        existingLeaveRequest.setIs_date_modified(true); 
	        
	        existingLeaveRequest.setLeaveDuration(updatedRequest.getLeaveDuration());
	        existingLeaveRequest.setEdit_flag(1);
	        
	        System.out.println("existingLeaveRequest---"+existingLeaveRequest);
	        // Save the updated leave application back to the database
	        leaveApplicationRepo.save(existingLeaveRequest);

	        return "Leave request updated successfully.";
	    } else {
	        // If the leave request doesn't exist, return an error message
	        return "Leave request not found."; 
	    }
	}

	
	public void sendApprovalEmail(String applicantEmail, String approvingAuthorities, LeaveApplication leaveApplication) throws MessagingException {
	    System.out.println("Sending Leave Approval Email..."+approvingAuthorities);

	    // Fetch employee details to get name and gender
	    DisplayEmployessEntity empDetailsByEmpId = employeeRepositoryWithDeptName.findByEmpid(leaveApplication.getEmpid());
	    
	    if (empDetailsByEmpId == null) {
	        System.err.println("No employee details found for empId: " + leaveApplication.getEmpid());
	        return; // Exit if employee details are not found
	    }

	    // Determine employee prefix based on gender
	    String prefix = "Mr."; // Default to "Mr."
	    if ("Female".equalsIgnoreCase(empDetailsByEmpId.getGender())) {
	        prefix = "Ms.";
	    }
	    String employeeNameWithPrefix = prefix + " " + leaveApplication.getEmployee_name();

	    // Split approving authorities
	    String[] approverNames = approvingAuthorities.split(",");
	    List<String> ccEmails = new ArrayList<>();
	    String toEmail = null;
	    String approverGreeting = "";

	    for (int i = 0; i < approverNames.length; i++) {
	        String approverName = approverNames[i].trim();

	        // Retrieve approver email
	        String approverEmail = employeeRepositoryWithDeptName.findEmailByFullName(approverName);

	        // Retrieve approver gender
	        String approverGender = employeeRepositoryWithDeptName.findGenderByFullName(approverName);
	        String approverPrefix = "Mr.";
	        if ("Female".equalsIgnoreCase(approverGender)) {
	            approverPrefix = "Ms.";
	        }
	        String approverNameWithPrefix = approverPrefix + " " + approverName;

	        if (approverEmail != null && !approverEmail.isEmpty()) {
	            if (i == 0) {
	                // First approver gets "To"
	                toEmail = approverEmail;
	                approverGreeting = approverNameWithPrefix;
	            } else {
	                // Other approvers get CC
	                ccEmails.add(approverEmail);
	            }
	        } else {
	            System.err.println("No email found for approver: " + approverName);
	        }
	    }

	    // If no valid "To" email, exit
	    if (toEmail == null) {
	        System.err.println("No valid approver found to send email.");
	        return;
	    }

	    // Format dates correctly
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    String formattedFromDate = "";
	    String formattedToDate = "";

	    try {
	        formattedFromDate = LocalDate.parse(leaveApplication.getFrom_date(), inputFormatter).format(outputFormatter);
	        formattedToDate = LocalDate.parse(leaveApplication.getTo_date(), inputFormatter).format(outputFormatter);
	    } catch (Exception e) {
	        System.err.println("Error parsing dates: " + e.getMessage());
	    }

	    // Convert leave type abbreviations to full names
	    Map<String, String> leaveTypeMap = Map.of(
	        "SL", "Sick Leave",
	        "CL", "Casual Leave",
	        "EL", "Earned Leave"
	    );
	    
	    String leaveTypeFullName = leaveTypeMap.getOrDefault(leaveApplication.getLeaveType(), leaveApplication.getLeaveType());

	    // Compose email
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    helper.setFrom("noreply@melangesystems.com");
	    helper.setTo(toEmail);
	    
	    if (!ccEmails.isEmpty()) {
	        helper.setCc(ccEmails.toArray(new String[0]));
	    }

	    helper.setSubject("Leave Request Pending Approval for " + employeeNameWithPrefix);

	    String emailBody = String.format(
	        "<p>Dear %s,</p>" +
	        "<p>You have received a new leave request from <strong>%s</strong>. Please review the details of the request below and approve or reject it accordingly:</p>" +
	        "<p><strong>Leave Request Details:</strong></p>" +
	        "<ul>" +
	        "  <li><strong>Employee Name:</strong> %s</li>" +
	        "  <li><strong>Leave Type:</strong> %s</li>" +
	        "  <li><strong>Leave Dates:</strong> %s to %s</li>" +
	        "  <li><strong>Reason:</strong> %s</li>" +
	        "</ul>" +
	        "<p>You can approve or reject the request via the MSPL_CONNECT Application. Please take action at your earliest convenience.</p>" +
	        "<p>Best regards,<br>The Melange Team</p>",
	        approverGreeting, employeeNameWithPrefix,
	        employeeNameWithPrefix, leaveTypeFullName,
	        formattedFromDate, formattedToDate,
	        leaveApplication.getReason() != null ? leaveApplication.getReason() : "Not provided"
	    );

	    helper.setText(emailBody, true);

	    // Send email
	    try {
	        mailSender.send(message);
	        System.out.println("Approval email sent to: " + toEmail);
	    } catch (Exception e) {
	        System.err.println("Error sending email to " + toEmail + ": " + e.getMessage());
	    }
	}
}
