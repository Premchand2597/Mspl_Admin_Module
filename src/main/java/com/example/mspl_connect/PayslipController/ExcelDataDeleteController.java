package com.example.mspl_connect.PayslipController;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ExcelDataDeleteController {
	
	@DeleteMapping("/delete-excel-data")
    public void deleteExcelData() throws IOException {
        String excelFilePath = "src/main/resources/email_status.xlsx";
        clearExcelFile(excelFilePath);
    }

    private void clearExcelFile(String filePath) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.createSheet();
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new IOException("Failed to clear Excel file", e);
        }
    }
}