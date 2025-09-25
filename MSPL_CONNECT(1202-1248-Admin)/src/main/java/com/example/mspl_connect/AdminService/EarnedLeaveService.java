package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.EarnedLeave;
import com.example.mspl_connect.AdminEntity.LeaveApplication;
import com.example.mspl_connect.AdminRepo.EarnedLeaveRepository;
import com.example.mspl_connect.AdminRepo.LeaveApplicationRepository;

import jakarta.transaction.Transactional;

@Service
public class EarnedLeaveService {

	// Inject EarnedLeaveRepository
    @Autowired
    private EarnedLeaveRepository earnedLeaveRepository;
    
   
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    //@Autowired
    //private LeaveApplicationService leaveApplicationService;


	 

 /* public void saveOrUpdateEarnedLeave(String email, double earnedLeave, String monthYear, String empId, Double elUsed, LocalDate incrementDate) {
	    System.out.println("Checking if earned leave data exists for email: " + email + ", month: " + monthYear);
	    
	    // Print the incoming earnedLeave parameter
	    System.out.println("Incoming earnedLeave parameter: " + earnedLeave);

	    System.out.println("Checking if earned leave data exists for email: " + email + ", month: " + monthYear);

	    // Check if earned leave data already exists for the current month and user
	    EarnedLeave existingEarnedLeave = earnedLeaveRepository.findByEmailAndMonthYear(email, monthYear);

	    if (existingEarnedLeave != null) {
	        System.out.println("Existing earned leave found. Current earned leave: " + existingEarnedLeave.getEarnedLeave());

	        // If data exists, calculate the new earned leave value
	        
	        double newEarnedLeave = existingEarnedLeave.getEarnedLeave() + earnedLeave;
	        //System.out.println("[[",earnedLeave);
	        System.out.println("New earned leave after addition: " + newEarnedLeave);

	        // Update the earned leave only if the new value is >= 1.5
	        if (newEarnedLeave >= 1.5) {
	            existingEarnedLeave.setEarnedLeave(newEarnedLeave);
	            System.out.println("Earned leave updated to: " + newEarnedLeave);

	            // Keep the same remaining leave
	            existingEarnedLeave.setElremaining(existingEarnedLeave.getElremaining());
	            System.out.println("Remaining leave unchanged: " + existingEarnedLeave.getElremaining());

	            // Update lastIncrementDate to the date on which leave was incremented
	            existingEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
	            System.out.println("Last increment date updated to: " + incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

	            // Check if used earned leave (elUsed) is provided
	            if (elUsed != null && elUsed > 0) {
	                // Subtract elUsed from the earned leave
	                double updatedRemaining = existingEarnedLeave.getElremaining() - elUsed;
	                existingEarnedLeave.setElremaining(updatedRemaining);
	                existingEarnedLeave.setElUsed(elUsed); // Update elUsed

	                System.out.println("Used leave: " + elUsed + ", Updated remaining leave: " + updatedRemaining);
	            }

	            // Save the updated earned leave data
	            earnedLeaveRepository.save(existingEarnedLeave);
	            System.out.println("Existing earned leave record saved/updated.");
	        } else {
	            System.out.println("New earned leave value is less than 1.5, no update performed.");
	        }
	    } else {
	        System.out.println("No existing earned leave record found for email: " + email + ", month: " + monthYear);

	        // If data doesn't exist and earned leave count reaches 1.5, save a new record
	        if (earnedLeave >= 1.5) {
	            EarnedLeave newEarnedLeave = new EarnedLeave();
	            newEarnedLeave.setEmail(email);
	            newEarnedLeave.setEmpId(empId);
	            newEarnedLeave.setEarnedLeave(earnedLeave);
	            newEarnedLeave.setMonthYear(monthYear);

	            // Set elRemaining equal to earnedLeave when creating the record
	            newEarnedLeave.setElremaining(earnedLeave);
	            newEarnedLeave.setElUsed(0.0); // Initially set elUsed to 0

	            // Set lastIncrementDate to the date on which leave was incremented
	            newEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

	            // Save the new earned leave record
	            earnedLeaveRepository.save(newEarnedLeave);
	            System.out.println("New earned leave record saved: " + earnedLeave + " for email: " + email + ", month: " + monthYear);
	        } else {
	            System.out.println("Earned leave is less than 1.5, no new record created.");
	        }
	    }
	}*/

    public void saveOrUpdateEarnedLeave(String email, double earnedLeave, String monthYear, String empId, Double elUsed, LocalDate incrementDate) {
	    System.out.println("Checking if earned leave data exists for email: " + email + ", month: " + monthYear);
	    System.out.println("Checking if earned leave data exists for email: ttt" + earnedLeave + ", month: " + elUsed);
	    // Print the incoming earnedLeave parameter
	    System.out.println("Incoming earnedLeave parameter: " + earnedLeave);

	    // Check if earned leave data already exists for the current month and user
	 //   EarnedLeave existingEarnedLeave = earnedLeaveRepository.findByEmailAndMonthYear(email, monthYear);
	    EarnedLeave existingEarnedLeave = earnedLeaveRepository.findByEmail(email);
        System.out.println("======"+existingEarnedLeave);
	    if (existingEarnedLeave != null) {
	        System.out.println("Existing earned leave found. Current earned leave: " + existingEarnedLeave.getEarnedLeave());

	        // Calculate the new earned leave value
	        double newEarnedLeave = existingEarnedLeave.getEarnedLeave() + earnedLeave;
	        System.out.println("New earned leave after addition: " + newEarnedLeave);

	        // Replace elUsed with the value from the table if passed as null
	        if (elUsed == null) {
	            elUsed = existingEarnedLeave.getElUsed();
	            System.out.println("Replaced null elUsed with value from table: " + elUsed);
	        }
	        // Update the earned leave only if the new value is >= 1.5
	        if (newEarnedLeave >= 1.5) {
	            existingEarnedLeave.setEarnedLeave(newEarnedLeave);
	            System.out.println("Earned leave updated to: " + newEarnedLeave);
	            System.out.println("New earned leave after addition: " + existingEarnedLeave.getElUsed() );

	            // Update elremaining based on elUsed
	            if (elUsed != null && elUsed > 0) {
	                // Subtract elUsed from the earned leave
	                double updatedRemaining = existingEarnedLeave.getEarnedLeave() - elUsed;
	                System.out.println("Used leave: "  + updatedRemaining);
	                existingEarnedLeave.setElremaining(updatedRemaining);
	                existingEarnedLeave.setElUsed(elUsed); // Update elUsed
	                System.out.println("Used leave: " + elUsed + ", Updated remaining leave: " + updatedRemaining);
	            } else {
	                // If elUsed is 0, update elremaining to the new earned leave value
	                existingEarnedLeave.setElremaining(newEarnedLeave);
	                System.out.println("elUsed is 0, remaining leave updated to: " + newEarnedLeave);
	            }

	            // Update lastIncrementDate to the date on which leave was incremented
	            existingEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
	            System.out.println("Last increment date updated to: " + incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

	            // Save the updated earned leave data
	            earnedLeaveRepository.save(existingEarnedLeave);
	            System.out.println("Existing earned leave record saved/updated.");
	        } else {
	            System.out.println("New earned leave value is less than 1.5, no update performed.");
	        }
	    } else {
	        System.out.println("No existing earned leave record found for email: " + email + ", month: " + monthYear);

	        // If data doesn't exist and earned leave count reaches 1.5, save a new record
	        if (earnedLeave >= 1.5) {
	            EarnedLeave newEarnedLeave = new EarnedLeave();
	            newEarnedLeave.setEmail(email);
	            newEarnedLeave.setEmpId(empId);
	            newEarnedLeave.setEarnedLeave(earnedLeave);
	            newEarnedLeave.setMonthYear(monthYear);

	            // Set elRemaining equal to earnedLeave when creating the record
	            newEarnedLeave.setElremaining(earnedLeave);
	            newEarnedLeave.setElUsed(0.0); // Initially set elUsed to 0

	            // Set lastIncrementDate to the date on which leave was incremented
	            newEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

	            // Save the new earned leave record
	            earnedLeaveRepository.save(newEarnedLeave);
	            System.out.println("New earned leave record saved: " + earnedLeave + " for email: " + email + ", month: " + monthYear);
	        } else {
	            System.out.println("Earned leave is less than 1.5, no new record created.");
	        }
	    }
	}

 /* public EarnedLeave saveOrUpdateEarnedLeave(String email, double earnedLeave, String monthYear, String empId, Double elUsed, LocalDate incrementDate) {
	    // Check if earned leave data already exists for the current month and user
	    EarnedLeave existingEarnedLeave = earnedLeaveRepository.findByEmailAndMonthYear(email, monthYear);

	    if (existingEarnedLeave != null) {
	        // If data exists, calculate the new earned leave value
	        double newEarnedLeave = existingEarnedLeave.getEarnedLeave() + earnedLeave;

	        // Update the earned leave only if the new value is >= 1.5
	        if (newEarnedLeave >= 1.5) {
	            existingEarnedLeave.setEarnedLeave(newEarnedLeave);
	            existingEarnedLeave.setElremaining(existingEarnedLeave.getElremaining()); // Keep the same remaining leave
	            
	            // Update lastIncrementDate to the date on which leave was incremented
	            existingEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

	            if (elUsed != null && elUsed > 0) {
	                // Subtract elUsed from the earned leave if used leave is provided
	                double updatedRemaining = existingEarnedLeave.getElremaining() - elUsed;
	                existingEarnedLeave.setElremaining(updatedRemaining);
	                existingEarnedLeave.setElUsed(elUsed); // Update elUsed
	                System.out.println("Earned leave updated: " + newEarnedLeave + ", Remaining leave: " + updatedRemaining);
	            }
	            // Save updated existingEarnedLeave
	            return earnedLeaveRepository.save(existingEarnedLeave);
	        }
	    } else {
	        // If data doesn't exist and earned leave count reaches 1.5, save new record
	        if (earnedLeave >= 1.5) {
	            EarnedLeave newEarnedLeave = new EarnedLeave();
	            newEarnedLeave.setEmail(email);
	            newEarnedLeave.setEmpId(empId);
	            newEarnedLeave.setEarnedLeave(earnedLeave);
	            newEarnedLeave.setMonthYear(monthYear);
	            newEarnedLeave.setElremaining(earnedLeave); // Set elRemaining equal to earnedLeave when creating the record
	            newEarnedLeave.setElUsed(0.0); // Initially set elUsed to 0
	            
	            // Set lastIncrementDate to the date on which leave was incremented
	            newEarnedLeave.setLastIncrementDate(incrementDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
	            
	            return earnedLeaveRepository.save(newEarnedLeave); // Return the newly created earned leave record
	        }
	    }
	    return null; // Return null if no record is created or updated
	}*/

  
    @Transactional
    public void processApprovedELLeave() {
        System.out.println("Processing approved EL leave applications...");

        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAll();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        for (LeaveApplication leaveApplication : leaveApplications) {
            String leaveType = leaveApplication.getLeaveType();
            String currentStatus = leaveApplication.getApprovedstatus();
            String previousStatus = leaveApplication.getPrevious_status(); // Ensure this field is available and updated in your database

            // Process only earned leave (EL)
            if (!"EL".equalsIgnoreCase(leaveType)) {
                continue;
            }

            String email = leaveApplication.getEmployeeEmail();
            LocalDate fromDate = LocalDate.parse(leaveApplication.getFrom_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = LocalDate.parse(leaveApplication.getTo_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Calculate the total number of leave days
            long leaveDays = fromDate.datesUntil(toDate.plusDays(1)).count();

            try {
                EarnedLeave earnedLeave = earnedLeaveRepository.findByEmailAndMonthYear(email, YearMonth.now().toString());

                if (earnedLeave != null) {
                    if ("approved".equalsIgnoreCase(currentStatus) && !"approved".equalsIgnoreCase(previousStatus)) {
                        // If the current status is approved and the previous status was not approved, decrement the count
                        double newElUsed = (earnedLeave.getElUsed() == null ? 0 : earnedLeave.getElUsed()) + leaveDays;
                        earnedLeave.setElUsed(newElUsed);
                        earnedLeaveRepository.save(earnedLeave);
                        leaveApplication.setProcessed(true);
                        System.out.println("Decremented EL for application ID: " + leaveApplication.getId() + ". New EL used: " + newElUsed);
                    } else if (!"approved".equalsIgnoreCase(currentStatus) && "approved".equalsIgnoreCase(previousStatus)) {
                        // If the current status is not approved and the previous status was approved, increment the count
                        double newElUsed = (earnedLeave.getElUsed() == null ? 0 : earnedLeave.getElUsed()) - leaveDays;
                        if (newElUsed < 0) {
                            System.err.println("Error: Used EL cannot be negative for email: " + email);
                            continue;
                        }
                        earnedLeave.setElUsed(newElUsed);
                        earnedLeaveRepository.save(earnedLeave);
                        leaveApplication.setProcessed(false);
                        System.out.println("Incremented EL for application ID: " + leaveApplication.getId() + ". New EL used: " + newElUsed);
                    }

                    // Update the previous status to the current status
                    leaveApplication.setPrevious_status(currentStatus);
                    leaveApplicationRepository.save(leaveApplication);
                } else {
                    System.err.println("No earned leave entry found for email: " + email + " and month: " + YearMonth.now().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error processing earned leave for email: " + email + ". Error: " + e.getMessage());
            }
        }
    }




    
  /*  public double getTotalElUsedForLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Assuming email is used for authentication

        System.out.println("Fetching earned leaves for user with email: " + userEmail);

        List<EarnedLeave> userEarnedLeaves = earnedLeaveRepository.findAllByEmail(userEmail);

        System.out.println("Found " + userEarnedLeaves.size() + " earned leaves for user with email: " + userEmail);

        double totalElUsed = 0.0;
      
        for (EarnedLeave leave : userEarnedLeaves) {
            totalElUsed += Optional.ofNullable(leave.getElUsed()).orElse(0.0);
        }

        System.out.println("Total EL used for user with email " + userEmail + " is: " + totalElUsed);

        return totalElUsed;
    }*/


}
