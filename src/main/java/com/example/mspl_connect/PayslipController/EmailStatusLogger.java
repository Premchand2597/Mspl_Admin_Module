package com.example.mspl_connect.PayslipController;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class EmailStatusLogger {

    private static final String FILE_NAME = "src/main/resources/email_status.xlsx";
    private static final String FILE_PATH =  FILE_NAME; // Update this path accordingly


    public static synchronized void logEmailStatus(String empId, String employeeName, String email, String status) {
        if ("NOT_SENT".equalsIgnoreCase(status)) {
            // Skip logging if status is "NOT_SENT"
            return;
        }

        try {
            File file = new File(FILE_PATH);
            Workbook workbook;
            Sheet sheet;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Email Status");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Timestamp");
                headerRow.createCell(1).setCellValue("Employee Name");
                headerRow.createCell(2).setCellValue("Email");
                headerRow.createCell(3).setCellValue("Status");
                headerRow.createCell(4).setCellValue("empId");
            }

            boolean isUpdated = false;
            int rowCount = sheet.getLastRowNum();

            for (int i = 0; i <= rowCount; i++) { // Start from 1 to skip header row
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(1).getStringCellValue().equals(employeeName) && row.getCell(4).getStringCellValue().equals(empId)) {
                    row.getCell(0).setCellValue(LocalDateTime.now().toString());
                    row.getCell(2).setCellValue(email); // Update email if necessary
                    row.getCell(3).setCellValue(status);
                    isUpdated = true;
                    break;
                }
            }

            if (!isUpdated) {
                Row newRow = sheet.createRow(++rowCount);
                newRow.createCell(0).setCellValue(LocalDateTime.now().toString());
                newRow.createCell(1).setCellValue(employeeName);
                newRow.createCell(2).setCellValue(email);
                newRow.createCell(3).setCellValue(status);
                newRow.createCell(4).setCellValue(empId);
            }
            
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}