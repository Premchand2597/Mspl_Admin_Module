package com.example.mspl_connect.PayslipController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class EmployeeValidator {
	
	public static Map<String, Set<String>> validateEmployeeDataForOnlyAphabets(String name, String dept, String designation, String bankName, int rowNumber) {
        Map<String, Set<String>> errorsMap = new HashMap<>();
        String rowInfo = " (Row " + rowNumber + ")";

        if (name == null || !name.matches("^[a-zA-Z\\.\\s]+$")) {
            errorsMap.computeIfAbsent("Only alphabets allowed", k -> new HashSet<>()).add("Employee Name" + rowInfo);
        }

        if (dept == null || !dept.matches("^[a-zA-Z\\s&-]+$")) {
            errorsMap.computeIfAbsent("Only alphabets, spaces, hyphens, and ampersands allowed", k -> new HashSet<>()).add("Department Name" + rowInfo);
        }

        if (designation == null || !designation.matches("^[a-zA-Z\\s&-]+$")) {
            errorsMap.computeIfAbsent("Only alphabets allowed", k -> new HashSet<>()).add("Designation Name" + rowInfo);
        }

        if (bankName == null || !bankName.matches("^[a-zA-Z\\s]+$")) {
            errorsMap.computeIfAbsent("Only alphabets allowed", k -> new HashSet<>()).add("Bank Name" + rowInfo);
        }

        return errorsMap;
    }

	public static Map<String, Set<String>> validateEmployeeDataForEmail(String email, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";

	    if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@melangesystems\\.com$")) {
	        errorsMap.computeIfAbsent("Valid Email Id format is", k -> new HashSet<>()).add("username@melangesystems.com" + rowInfo);
	    }

	    return errorsMap;
	}


	public static Map<String, Set<String>> validateEmployeeDataForOnlyNumbers(Long uanNo, int dayNo, int workedDays, double basic, double hra, double conveyance,
	        double medicalAllowance, double otherAllowance, double arrears, double bonus, double otherAllowance1, double reimbursement, double totalEarning,
	        double pf, double esi, double pt, double it, double loan, double others, double others1, double totalDeduction, double netSalary, int rowNumber) {

	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";
	    
	    //System.out.println(basic);

	    if (uanNo == null || uanNo < 0 || !String.valueOf(uanNo).matches("[0-9]+")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("UAN No" + rowInfo);
	    }

	    if (dayNo <= 0 || !String.valueOf(dayNo).matches("[0-9]+")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("No of Days" + rowInfo);
	    }

	    if (workedDays <= 0 || !String.valueOf(workedDays).matches("[0-9]+")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("No of Worked Days" + rowInfo);
	    }

	    if (basic < 0.0 || !String.valueOf(basic).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Basic" + rowInfo);
	    }

	    if (hra < 0.0 || !String.valueOf(hra).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("HRA" + rowInfo);
	    }

	    if (conveyance <= 0.0 || !String.valueOf(conveyance).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Conveyance" + rowInfo);
	    }

	    if (medicalAllowance < 0.0 || !String.valueOf(medicalAllowance).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Medical Allowance" + rowInfo);
	    }

	    if (otherAllowance < 0.0 || !String.valueOf(otherAllowance).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Other Allowance" + rowInfo);
	    }

	    if (arrears < 0.0 || !String.valueOf(arrears).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Arrears" + rowInfo);
	    }

	    if (bonus < 0.0 || !String.valueOf(bonus).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Bonus" + rowInfo);
	    }
	    
	    if (otherAllowance1 < 0.0 || !String.valueOf(otherAllowance1).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Other Allowance 1" + rowInfo);
	    }

	    if (reimbursement < 0.0 || !String.valueOf(reimbursement).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Reimbursement" + rowInfo);
	    }
	    
	    if (totalEarning <= 0.0 || !String.valueOf(totalEarning).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Total Earnings" + rowInfo);
	    }

	    if (pf < 0.0 || !String.valueOf(pf).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("PF" + rowInfo);
	    }
	    
	    if (esi < 0.0 || !String.valueOf(esi).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("ESI" + rowInfo);
	    }

	    if (pt < 0.0 || !String.valueOf(pt).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("PT" + rowInfo);
	    }
	    
	    if (it < 0.0 || !String.valueOf(it).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Income Tax" + rowInfo);
	    }

	    if (loan < 0.0 || !String.valueOf(loan).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Loan" + rowInfo);
	    }
	    
	    if (others < 0.0 || !String.valueOf(others).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Others" + rowInfo);
	    }

	    if (others1 < 0.0 || !String.valueOf(others1).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Others 1" + rowInfo);
	    }
	    
	    if (totalDeduction <= 0.0 || !String.valueOf(totalDeduction).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Total Deduction" + rowInfo);
	    }

	    if (netSalary <= 0.0 || !String.valueOf(netSalary).matches("\\d+(\\.\\d+)?")) {
	        errorsMap.computeIfAbsent("Only digits allowed", k -> new HashSet<>()).add("Net Salary" + rowInfo);
	    }

	    return errorsMap;
	}

	public static Map<String, Set<String>> validateDateOfJoining(String date, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";

	    try {
	        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	    } catch (DateTimeParseException e) {
	        errorsMap.computeIfAbsent("Valid Date of Joining format is", k -> new HashSet<>()).add("dd.MM.yyyy" + rowInfo);
	    }

	    return errorsMap;
	}
	
	public static Map<String, Set<String>> validateBankAccountNo(String accNo, String pfNo, String esiNo, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";
	    
	    //System.out.println("Accno == "+accNo+" PF no == "+pfNo+" Esi No == "+esiNo);

	    if (accNo == null || !accNo.matches("^(?=.*\\d)[a-zA-Z0-9.]*$") || accNo == "") {
	        errorsMap.computeIfAbsent("Only alpha-numeric or numeric", k -> new HashSet<>()).add("Bank A/c No" + rowInfo);
	    }
	    
	    if (pfNo == null || !pfNo.matches("^[a-zA-Z0-9.\\-]+$") || pfNo == "") {
	        errorsMap.computeIfAbsent("Only alpha-numeric or numeric or '-' symbol", k -> new HashSet<>()).add("PF No" + rowInfo);
	    }
	    
	    if (esiNo == null || !esiNo.matches("^[a-zA-Z0-9.\\-]+$") || esiNo.isEmpty()) {
	        errorsMap.computeIfAbsent("Only alpha-numeric or numeric or '-' symbol", k -> new HashSet<>()).add("ESI No" + rowInfo);
	    }

	    return errorsMap;
	}
	
	public static Map<String, Set<String>> validatePanNo(String panNo, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";

	    if (panNo == null || !panNo.matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
	        errorsMap.computeIfAbsent("Valid PAN No format is", k -> new HashSet<>()).add("ABCDE1234Z" + rowInfo);
	    }
	    
	    return errorsMap;
	}
	
	/*public static Map<String, Set<String>> validatePFAccountNo(String pfNo, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();

	    String rowInfo = " (Row " + rowNumber + ")";

	    return errorsMap;
	}*/

	public static Map<String, Set<String>> validatePaySlipMonth(String month, int rowNumber) {
	    Map<String, Set<String>> errorsMap = new HashMap<>();
	    
	    String rowInfo = " (Row " + rowNumber + ")";

	    try {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM.yyyy", Locale.ENGLISH);
            YearMonth yearMonth = YearMonth.parse(month, formatter);
            
            // Get full month name
            String fullMonthName = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	    } catch (DateTimeParseException e) {
	        errorsMap.computeIfAbsent("Valid Pay Slip month format is", k -> new HashSet<>()).add("month.yyyy" + rowInfo);
	    }

	    return errorsMap;
	}
}

