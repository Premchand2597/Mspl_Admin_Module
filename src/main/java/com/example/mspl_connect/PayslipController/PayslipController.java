package com.example.mspl_connect.PayslipController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.PayslipEntity.EmailStatus;
import com.example.mspl_connect.PayslipEntity.EmployeeForPaySlip;
import com.example.mspl_connect.PayslipService.PayslipService;


@RestController
public class PayslipController {

    @Autowired
    private PayslipService payslipService;

    @PostMapping("/uploadPayslip")
    public ResponseEntity<List<EmployeeForPaySlip>> uploadPayslip(@RequestParam("file") MultipartFile file) {
        try {
            List<EmployeeForPaySlip> employees = payslipService.parseExcelFile(file);
            return ResponseEntity.ok(employees);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    
    @PostMapping("/comparingFile")
    public ResponseEntity<List<EmailStatus>> comparingAnotherFile(
            @RequestParam("file") MultipartFile file) {

        try {
            // Load the predefined Excel file
            File predefinedFile = new File("src/main/resources/email_status.xlsx");

            // Parse both files to get email IDs
            List<EmployeeForPaySlip> fileEmployees = payslipService.parseExcelFile(file);
            List<EmailStatus> predefinedEmailStatuses = payslipService.parseEmailStatusFile(predefinedFile);

            // Extract email IDs from employees and predefined statuses
            List<String> fileEmpIds = fileEmployees.stream()
                    .map(EmployeeForPaySlip::getEmployeeId)
                    .collect(Collectors.toList());

            List<String> predefinedEmpIds = predefinedEmailStatuses.stream()
                    .map(EmailStatus::getEmpId)
                    .collect(Collectors.toList());

            // Compare email IDs and collect matches
            List<String> matchingEmpIds = new ArrayList<>(fileEmpIds);
            matchingEmpIds.retainAll(predefinedEmpIds);

            // Create EmailStatus objects for matching email IDs
            List<EmailStatus> matchingEmpIdStatuses = new ArrayList<>();
            for (String emapId : matchingEmpIds) {
                EmailStatus emailStatus = new EmailStatus();
                emailStatus.setEmpId(emapId);
                // Optionally set other properties if needed
                matchingEmpIdStatuses.add(emailStatus);
            }

            return ResponseEntity.ok(matchingEmpIdStatuses);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @PostMapping("/sendPayslips")
    public ResponseEntity<String> sendPayslips(@RequestBody List<EmployeeForPaySlip> selectedEmployees) {
        try {
            payslipService.sendSelectedPayslips(selectedEmployees);
            return ResponseEntity.ok("Payslips sent successfully!");
        }catch (ValidationException e) {
        	return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) { // Catching IllegalArgumentException
            return ResponseEntity.badRequest().body(e.getMessage()); 
        }  catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send payslips: " + e.getMessage());
        }
    }
}
