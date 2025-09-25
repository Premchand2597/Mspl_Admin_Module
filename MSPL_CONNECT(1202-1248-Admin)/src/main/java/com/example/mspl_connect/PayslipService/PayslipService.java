package com.example.mspl_connect.PayslipService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mspl_connect.PayslipController.EmailStatusLogger;
import com.example.mspl_connect.PayslipController.EmployeeValidator;
import com.example.mspl_connect.PayslipController.NumberToWordsConverter;
import com.example.mspl_connect.PayslipController.PdfDocumentEventHandler;
import com.example.mspl_connect.PayslipController.ValidationException;
import com.example.mspl_connect.PayslipEntity.EmailStatus;
import com.example.mspl_connect.PayslipEntity.EmployeeForPaySlip;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import jakarta.mail.Address;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;

@Service
public class PayslipService {

    @Autowired
    private JavaMailSender mailSender;
    
    private static final Logger logger = LoggerFactory.getLogger(PayslipService.class);
    
    private static final int THREAD_POOL_SIZE = 10; // Adjust the size according to your needs
    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    
    
    // Method to send payslips from an uploaded file
    public void sendPayslips(MultipartFile file) throws IOException, MessagingException {
        List<EmployeeForPaySlip> employees = parseExcelFile(file);
        sendPayslipsToEmployees(employees);
    }

    // Method to send payslips for selected employees
    public void sendSelectedPayslips(List<EmployeeForPaySlip> employees) throws IOException, MessagingException, ValidationException {
        Map<String, Set<String>> validationErrors = new HashMap<>();
        final List<Integer> rowNumbers = new ArrayList<>(); // List to store row numbers

        for (EmployeeForPaySlip employee : employees) {
            // Validate employee data
            Map<String, Set<String>> errorsMap = new HashMap<>();
            errorsMap.putAll(EmployeeValidator.validateEmployeeDataForOnlyAphabets(employee.getName(), employee.getDepartment(), employee.getDesignation(), employee.getBankName(), rowNumbers.size() + 1));

         // Merge errors into validationErrors map
            errorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));

            errorsMap.clear();

            errorsMap.putAll(EmployeeValidator.validateEmployeeDataForOnlyNumbers(employee.getUanNo(), employee.getDays(), employee.getWorkedDays(),
                    employee.getBasic(), employee.getHra(), employee.getConveyance(), employee.getMedical(), employee.getOtherAllowance(),
                    employee.getArrears(), employee.getBounce(), employee.getOtherAllowance1(), employee.getReimbursement(), employee.getTotalEarnings(),
                    employee.getPfDeduction(), employee.getEsi(), employee.getPt(), employee.getIncomeTax(), employee.getLoan(), employee.getOtherDeductions(),
                    employee.getOtherDeductions1(), employee.getTotalDeductions(), employee.getNetSalary(), rowNumbers.size() + 1));

         // Merge errors into validationErrors map
            errorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));

         // Validate Date of Joining
            Map<String, Set<String>> dateErrorsMap = EmployeeValidator.validateDateOfJoining(employee.getDateOfJoining(), rowNumbers.size() + 1);
         // Merge date errors into validationErrors map
            dateErrorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));
            
         // Validate Bank A/c and PF No
            Map<String, Set<String>> bankAccErrorsMap = EmployeeValidator.validateBankAccountNo(employee.getBankAccount(), employee.getPfNumber(), employee.getEsi_num(), rowNumbers.size() + 1);
         // Merge email errors into validationErrors map
            bankAccErrorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));

         // Validate PAN no
            Map<String, Set<String>> panNoErrorsMap = EmployeeValidator.validatePanNo(employee.getPan(), rowNumbers.size() + 1);
         // Merge email errors into validationErrors map
            panNoErrorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));

         // Validate Email
            Map<String, Set<String>> emailErrorsMap = EmployeeValidator.validateEmployeeDataForEmail(employee.getEmail(), rowNumbers.size() + 1);
         // Merge email errors into validationErrors map
            emailErrorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));


         // Validate Pay Slip Month
            Map<String, Set<String>> paySlipMonthErrorsMap = EmployeeValidator.validatePaySlipMonth(employee.getMonth(), rowNumbers.size() + 1);
            // Merge pay slip month errors into validationErrors map
            paySlipMonthErrorsMap.forEach((errorType, fieldErrors) ->
                    validationErrors.computeIfAbsent(errorType, k -> new HashSet<>()).addAll(fieldErrors.stream()
                            .map(fieldError -> fieldError + " (Row " + rowNumbers.size() + 1 + ")")
                            .collect(Collectors.toSet())));
            
            rowNumbers.add(rowNumbers.size() + 1); // Add current row number to list
        }

     // Prepare final error messages
        Set<String> finalErrors = new HashSet<>();
        validationErrors.forEach((errorType, fieldErrors) -> {
            StringBuilder errorMessage = new StringBuilder("<div style='text-align: left;'><b>" + errorType + ":</b> ");
            
         // Collect distinct row numbers
            List<Integer> sortedRowNumbers = fieldErrors.stream()
            		.map(fieldError -> Integer.parseInt(fieldError.split(" \\(Row ")[1].replace(")", "")))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

         // Collect distinct field names
            Set<String> distinctErrors = fieldErrors.stream()
                    .map(fieldError -> fieldError.split(" \\(Row ")[0]) // Extract field name only
                    .collect(Collectors.toSet());
            
            String rowNumbersStr = sortedRowNumbers.stream()
                    .map(String::valueOf) // Convert Integer to String
                    .collect(Collectors.joining(", ")); // Join row numbers with comma and space

            errorMessage.append("(").append(rowNumbersStr).append(")<br>").append(String.join(", ", distinctErrors));
            errorMessage.append("</div>");
            finalErrors.add(errorMessage.toString());
        });


        // If any validation errors exist, throw ValidationException with all errors
        if (!finalErrors.isEmpty()) {
            throw new ValidationException(new ArrayList<>(finalErrors));
        }

        sendPayslipsToEmployees(employees);
    }


    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    private void sendPayslipsToEmployees(List<EmployeeForPaySlip> employees) throws IOException, MessagingException {
        try {
            for (EmployeeForPaySlip employee : employees) {
                CompletableFuture.runAsync(() -> {
                    try {
                        String password = employee.getPan(); // Replace with actual logic if needed
                        
                        ByteArrayOutputStream pdfOutputStream = generatePayslipPdf(employee, password);
                        try {
							sendEmailWithAttachment(employee, pdfOutputStream.toByteArray());
						} catch (Exception e) {
							e.printStackTrace();
						}


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).orTimeout(7000, TimeUnit.SECONDS); // Adjust timeout as needed
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception properly
        }
    }

    

    // Shutdown ExecutorService when no longer needed
    public void shutdownExecutorService() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
    
    
    public class FormatUtils {
        public static String formatValue(double value, int fractionDigits) {
            StringBuilder pattern = new StringBuilder("0.");
            for (int i = 0; i < fractionDigits; i++) {
                pattern.append("0");
            }
            DecimalFormat df = new DecimalFormat(pattern.toString());
            return df.format(value);
        }
    }
    
    public class WatermarkEventHandler implements IEventHandler {

        private ImageData watermarkImageData;
        private float rotationAngle; // in radians
        private float scaleFactor; // scaling factor for the watermark image

        public WatermarkEventHandler(String imagePath, float rotationAngle, float scaleFactor) {
            try {
                watermarkImageData = ImageDataFactory.create(imagePath);
                this.rotationAngle = rotationAngle;
                this.scaleFactor = scaleFactor;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), docEvent.getDocument());
            Rectangle rect = page.getPageSize();

            float pageWidth = rect.getWidth();
            float pageHeight = rect.getHeight();  

            // Calculate the dimensions to maintain aspect ratio
            float imageWidth = watermarkImageData.getWidth() * scaleFactor; // Apply the scaling factor
            float imageHeight = watermarkImageData.getHeight() * scaleFactor; // Apply the scaling factor
            float aspectRatio = imageWidth / imageHeight;

            float scaledWidth;
            float scaledHeight;

            if (pageWidth / aspectRatio < pageHeight) {
                scaledWidth = pageWidth;
                scaledHeight = pageWidth / aspectRatio;
            } else {
                scaledHeight = pageHeight;
                scaledWidth = pageHeight * aspectRatio;
            }

            // Adjust the size of the watermark image by the scaling factor
            scaledWidth *= scaleFactor;
            scaledHeight *= scaleFactor;

            // Position the image at the top of the page
            float x = (pageWidth - scaledWidth) / 2;
            float y = (pageHeight - scaledHeight) / 1.1f; // Shift the image to the top

            // Set opacity for the image to make it a dull background
            PdfExtGState gState = new PdfExtGState().setFillOpacity(0.4f); // Adjust the opacity as needed
            canvas.saveState();
            canvas.setExtGState(gState);

            // Apply rotation and add the image
            canvas.concatMatrix((float) Math.cos(rotationAngle), (float) Math.sin(rotationAngle), 
                                (float) -Math.sin(rotationAngle), (float) Math.cos(rotationAngle), 
                                x + scaledWidth / 2, y + scaledHeight / 3.5f);

            // Watermark image width and length
            canvas.addImage(watermarkImageData, -scaledWidth / 2, -scaledHeight / 2, scaledWidth, false);

            canvas.restoreState();
            canvas.release();
        }
    }



    public List<EmployeeForPaySlip> parseExcelFile(MultipartFile file) throws IOException {
        List<EmployeeForPaySlip> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // skip header row
            }
            
            EmployeeForPaySlip employee = new EmployeeForPaySlip();
            employee.setName(getCellValueAsString(row.getCell(0)));
            employee.setEmployeeId(getCellValueAsString(row.getCell(1)));
            employee.setDepartment(getCellValueAsString(row.getCell(2)));
            employee.setDesignation(getCellValueAsString(row.getCell(3)));
            employee.setDateOfJoining(getCellValueAsString(row.getCell(4)));
            employee.setBankAccount(getCellValueAsString(row.getCell(5)));
            employee.setBankName(getCellValueAsString(row.getCell(6)));
            employee.setPfNumber(getCellValueAsString(row.getCell(7)));
            employee.setUanNo(getCellValueAsLong(row.getCell(8)));
            employee.setEsi_num(getCellValueAsString(row.getCell(9)));
            employee.setPan(getCellValueAsString(row.getCell(10)));
            employee.setDays((int) getCellValueAsNumeric(row.getCell(11)));
            employee.setWorkedDays((int) getCellValueAsNumeric(row.getCell(12)));
            employee.setEmail(getCellValueAsString(row.getCell(13))); 
            employee.setBasic(getCellValueAsNumeric(row.getCell(14)));
            employee.setHra(getCellValueAsNumeric(row.getCell(15)));
            employee.setConveyance(getCellValueAsNumeric(row.getCell(16)));
            employee.setMedical(getCellValueAsNumeric(row.getCell(17)));
            employee.setOtherAllowance(getCellValueAsNumeric(row.getCell(18)));
            employee.setArrears(getCellValueAsNumeric(row.getCell(19)));
            employee.setBounce(getCellValueAsNumeric(row.getCell(20)));
            employee.setOtherAllowance1(getCellValueAsNumeric(row.getCell(21)));
            employee.setReimbursement(getCellValueAsNumeric(row.getCell(22)));
            employee.setTotalEarnings(getCellValueAsNumeric(row.getCell(23)));
            employee.setPfDeduction(getCellValueAsNumeric(row.getCell(24)));
            employee.setEsi(getCellValueAsNumeric(row.getCell(25)));
            employee.setPt(getCellValueAsNumeric(row.getCell(26)));
            employee.setIncomeTax(getCellValueAsNumeric(row.getCell(27))); 
            employee.setLoan(getCellValueAsNumeric(row.getCell(28)));
            employee.setOtherDeductions(getCellValueAsNumeric(row.getCell(29)));
            employee.setOtherDeductions1(getCellValueAsNumeric(row.getCell(30)));
            employee.setTotalDeductions(getCellValueAsNumeric(row.getCell(31)));
            employee.setNetSalary(getCellValueAsNumeric(row.getCell(32)));
            employee.setMonth(getCellValueAsString(row.getCell(33)));
            

            employees.add(employee);
        }
        workbook.close();
        return employees;
    }
    
    public List<EmailStatus> parseEmailStatusFile(File file) throws IOException {
        List<EmailStatus> emailStatuses = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            EmailStatus empId = new EmailStatus();
            empId.setEmpId(getCellValueAsString(row.getCell(4))); // Adjust cell index as per your Excel structure
            // Optionally set other properties if needed
            emailStatuses.add(empId);
        }

        workbook.close();
        return emailStatuses;
    }

    

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    
    private Long getCellValueAsLong(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                try {
                    return Long.parseLong(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            case NUMERIC:
                return (long) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1L : 0L;
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        try {
                            return Long.parseLong(cell.getStringCellValue());
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    case NUMERIC:
                        return (long) cell.getNumericCellValue();
                    case BOOLEAN:
                        return cell.getBooleanCellValue() ? 1L : 0L;
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    // Attempt to parse numeric value from string
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0.0; // Handle parsing error gracefully
                }
            case FORMULA:
                try {
                    // Evaluate formula cell and return numeric result
                    Workbook workbook = cell.getSheet().getWorkbook();
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    CellValue cellValue = evaluator.evaluate(cell);
                    return cellValue.getNumberValue();
                } catch (Exception e) {
                    return 0.0; // Handle formula evaluation error
                }
                default:
                return 0.0;
        }
    }

    private ByteArrayOutputStream generatePayslipPdf(EmployeeForPaySlip employee, String password) throws IOException {
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream,
                new WriterProperties().setStandardEncryption(password.getBytes(), password.getBytes(),
                        EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.STANDARD_ENCRYPTION_128))) {
        	
            PdfDocument pdfDoc = new PdfDocument(writer);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc);
        
        // Add the event handler for adding borders
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new PdfDocumentEventHandler());
        
        // Add the watermark event handler
        WatermarkEventHandler eventHandler = new WatermarkEventHandler("compressed_logo.png", (float)Math.toRadians(30), 0.8f); // 45-degree rotation
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, eventHandler);
        
        // Set the default font size for the document
        float fontSize = 8;
        
        // Add logo and company details in one row using a table
        try {
            Image logo = new Image(ImageDataFactory.create("compressed_logo.png"));
            logo.setWidth(100); // Adjust width as per your requirement
            logo.setHeight(36);
            logo.setMarginTop(-20);
            logo.setMarginLeft(30);

            Paragraph companyName = new Paragraph("Melange Systems Private Limited")
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBold()
                    .setFontSize(9)
                    .setMarginLeft(20)
                    .setMarginTop(9)
                    .setMarginRight(30)
                    .setMarginBottom(0); // Adjust margin bottom to reduce spacing

            // Combine company name and address in one paragraph
            Paragraph companyDetails = new Paragraph()
                    .add(companyName)
                    .add(new Paragraph("#4/1, 7th Cross, Kumara Park West, Bangalore - 560 020")
                            .setTextAlignment(TextAlignment.LEFT)
                            .setFontSize(fontSize)
                            .setMarginLeft(20)
                            .setMarginRight(30)
                            .setMarginTop(-20)).setMarginTop(-20); // Add margin to separate the lines

            // Create a table with two columns
            Table headerTable = new Table(new float[]{1, 2});
            headerTable.setWidthPercent(100);

            // Add the Logo to the first cell
            headerTable.addCell(new com.itextpdf.layout.element.Cell().add(logo).setBorder(Border.NO_BORDER).setMarginLeft(30).setMarginTop(-22));

            // Add the company details to the second cell
            headerTable.addCell(new com.itextpdf.layout.element.Cell().add(companyDetails).setBorder(Border.NO_BORDER).setMarginRight(30).setMarginTop(-20).setTextAlignment(TextAlignment.RIGHT));

            document.add(headerTable);
        } catch (Exception e) {
            // Handle exception if logo image cannot be loaded
            e.printStackTrace();
        }
        
        
        // Add a horizontal line separator
        LineSeparator separator = new LineSeparator(new SolidLine());
        separator.setMarginTop(0);
        separator.setMarginBottom(0);
        document.add(separator);

        // Reduce line space between paragraphs globally
        document.setMargins(0, 0, 0, 0);

        Paragraph payslipMonth = new Paragraph("Payslip for the Month of "+employee.getMonth())
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(8)
                .setBold()
                .setUnderline()
                .setMarginTop(2);
        
        payslipMonth.setMarginBottom(0); // Set a small margin bottom between paragraphs
        document.add(payslipMonth);
        

        // Employee details section
        document.add(new Paragraph("Employee Details:")
        		.setBold()
        		.setFontSize(fontSize)
        		.setMarginLeft(4)
        		.setMarginTop(1));
        
        // Define column widths for both tables to ensure they have equal widths
        float[] columnWidths = {1, 1};
        
        int fractionDigits = 2; // This can be set dynamically based on your requirements
        
        Table empDetailsTable = new Table(new float[]{4,2});
        empDetailsTable.setWidthPercent(100);
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Employee Name").setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getName()).setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Employee ID").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getEmployeeId()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Department").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getDepartment()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Designation").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getDesignation()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Date of Joining").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getDateOfJoining()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Bank A/c").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getBankAccount()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Bank Name").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getBankName()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        
        
        Table empDetailsTable2 = new Table(new float[]{4,2});
        empDetailsTable2.setWidthPercent(100);
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("PF No").setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getPfNumber()).setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("UAN No").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+String.valueOf(employee.getUanNo())).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("ESI No").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+String.valueOf(employee.getEsi_num())).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("PAN No").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+employee.getPan()).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Working Days").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+String.valueOf(employee.getDays())).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Paid Days").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+String.valueOf(employee.getWorkedDays())).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Email Id").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        empDetailsTable2.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(": "+String.valueOf(employee.getEmail())).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));

        // Create a container table with one row and two columns
        Table empDetailsContainerTable = new Table(columnWidths);
        empDetailsContainerTable.setMarginTop(-4).setMarginBottom(4).setWidthPercent(100);

        // Add empDetailsTable and empDetailsTable2 to the empDetailsContainerTable
        empDetailsContainerTable.addCell(new com.itextpdf.layout.element.Cell().add(empDetailsTable).setBorder(Border.NO_BORDER).setWidthPercent(50));
        empDetailsContainerTable.addCell(new com.itextpdf.layout.element.Cell().add(empDetailsTable2).setBorder(Border.NO_BORDER).setWidthPercent(50));

        document.add(empDetailsContainerTable);
        
        //System.out.println("----------------------------------"+employee.getBasic());
        
        // Earnings section
        Table earningsTable = new Table(new float[]{4, 2});
        earningsTable.setWidthPercent(100);
		/*
		 * earningsTable.addCell(new com.itextpdf.layout.element.Cell(1, 2) .add(new
		 * Paragraph("Earnings") .setBold() .setFontSize(fontSize)
		 * .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
		 */


        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Category").setBold().setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Amount").setBold().setFontSize(fontSize)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        
        // Add flexible earnings data
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Basic").setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getBasic(), fractionDigits)).setFontSize(fontSize)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("House Rent Allowance").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getHra(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Conveyance").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getConveyance(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Medical Allowance").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getMedical(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Other Allowance").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getOtherAllowance(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Arrears").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getArrears(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Bonus").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getBounce(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Other Allowance 1").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getOtherAllowance1(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Reimbursement").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getReimbursement(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Total Earnings"))
        		.setFontSize(fontSize)
        		.setPadding(0).setPaddingTop(-3).setPaddingLeft(2)
        		.setBorder(Border.NO_BORDER)
        		.setBold());
        earningsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(String.valueOf(FormatUtils.formatValue(employee.getTotalEarnings(), fractionDigits))))
        		.setFontSize(fontSize)
        		.setPadding(0).setPaddingTop(-3)
        		.setBorder(Border.NO_BORDER)
        		.setTextAlignment(TextAlignment.RIGHT)
        		.setBold());

        // Deductions section
        Table deductionsTable = new Table(new float[]{4, 2});
        
        deductionsTable.setWidthPercent(100);
		/*
		 * deductionsTable.addCell(new com.itextpdf.layout.element.Cell(1, 2) .add(new
		 * Paragraph("Deductions") .setBold() .setFontSize(fontSize)
		 * .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
		 */

        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Category").setBold().setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Amount").setBold().setFontSize(fontSize)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

        // Add flexible deductions data
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("PF").setFontSize(fontSize)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getPfDeduction(), fractionDigits)).setFontSize(fontSize)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("ESI").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getEsi(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("PT").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getPt(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Income Tax").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getIncomeTax(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Loan").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getLoan(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Others").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getOtherDeductions(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Others 1").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(FormatUtils.formatValue(employee.getOtherDeductions1(), fractionDigits)).setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("-").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("-").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("-").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER));
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("-").setFontSize(fontSize).setPadding(0).setPaddingTop(-3)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph("Total Deductions:"))
        		.setFontSize(fontSize)
        		.setPadding(0).setPaddingTop(-3).setPaddingLeft(2)
        		.setBorder(Border.NO_BORDER)
        		.setBold());
        deductionsTable.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(String.valueOf(FormatUtils.formatValue(employee.getTotalDeductions(), fractionDigits))))
        		.setFontSize(fontSize)
        		.setPadding(0).setPaddingTop(-3)
        		.setBorder(Border.NO_BORDER)
        		.setTextAlignment(TextAlignment.RIGHT)
        		.setBold());

        // Create a container table with one row and two columns
       // document.add(new Paragraph("\n"));
        Table containerTable = new Table(columnWidths);
        containerTable.setMarginTop(-5).setWidthPercent(100);

        // Add earningsTable and deductionsTable to the containerTable
        containerTable.addCell(new com.itextpdf.layout.element.Cell().add(earningsTable).setWidthPercent(50));
        containerTable.addCell(new com.itextpdf.layout.element.Cell().add(deductionsTable).setWidthPercent(50));
        
        containerTable.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Earnings").setWidthPercent(50).setBold().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER));
        containerTable.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Deductions").setWidthPercent(50).setBold().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER));

        // Add the containerTable to the document
        document.add(containerTable);

        // Net Salary section
        double netSalary = employee.getNetSalary();

        Table table1 = new Table(new float[]{4, 2}); // Create a table with two columns
        table1.setWidthPercent(100);

        // Create a Paragraph for the "Amount in words" with bold text for "Amount in words"
        Paragraph amountInWordsParagraph = new Paragraph();
        Text boldAmountInWordsText = new Text("Amount in words: ").setBold();
        Text amountInWordsText = new Text(NumberToWordsConverter.convert(netSalary) + " Only");
        amountInWordsParagraph.add(boldAmountInWordsText).setMarginTop(4);
        amountInWordsParagraph.add(amountInWordsText).setMarginTop(4);

        table1.addCell(new com.itextpdf.layout.element.Cell(1, 1).add(amountInWordsParagraph).setFontSize(fontSize).setBorder(Border.NO_BORDER));
        table1.addCell(new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph("Net Salary: " + FormatUtils.formatValue(netSalary, fractionDigits)).setFontSize(fontSize).setMarginTop(4).setBold()).setUnderline().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(4));

        document.add(table1);
        
        // Create a new Paragraph object
        Paragraph paragraph = new Paragraph();

        // Create a Text object for the bold part
        //Text boldText = new Text("Note: ").setBold();

        // Create a Text object for the rest of the text
        Text normalText = new Text("This document is computer generated and does not need any seal or signature");

        // Add both Text objects to the Paragraph
        //paragraph.add(boldText).setMargin(0).setMarginTop(8);
        paragraph.add(normalText).setMargin(0).setMarginTop(30);

        // Set text alignment and font size
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setFontSize(fontSize).setMargin(0).setMarginTop(30);

        // Add the paragraph to the document
        document.add(paragraph);

        // Close the document
        document.close();
        
    } catch (Exception e) {
    	
    	System.err.println("Error sending email: " + e.getMessage());
        
    }
     // Return the PDF as a ByteArrayOutputStream
		return outputStream;
 }
    
    private void sendEmailWithAttachment(EmployeeForPaySlip employee, byte[] pdfBytes) throws Exception {
  	  MimeMessage message = mailSender.createMimeMessage();

        try {
      	  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            String originalSenderEmail = "premchand.s@melangesystems.com";
            
            helper.setFrom(originalSenderEmail);
            helper.setTo(employee.getEmail());
            helper.setSubject("Payslip for " + employee.getMonth());
            
            helper.setText("Dear " + employee.getName() + ",\n\nPlease find attached your Payslip for the month of "+employee.getMonth()+".\nYour PAN number ( in CAPS)  is the password to open the file.\n\nFeel free to get in touch with me in case you need any further information or clarifications regarding the payslip.\n\nRegards\n\nSatheesh C\nManager - Accounts");
            helper.addAttachment("Payslip_("+ employee.getMonth() + ").pdf", new jakarta.mail.util.ByteArrayDataSource(pdfBytes, "application/pdf"));
            
            mailSender.send(message);
            
            EmailStatusLogger.logEmailStatus(employee.getEmployeeId(), employee.getName(), employee.getEmail(), "SENT");
            
            // Update status or mark email as sent
            employee.setEmailSent(true); // Assuming you have setEmailSent method in Employee class 
            
            // Save sent message to "Sent" folder
            saveToSentFolder(message, employee.getEmail(), originalSenderEmail);
            
            // Notify the client via WebSocket
            messagingTemplate.convertAndSend("/topic/emailStatus", employee);
            
            logger.info("Email sent successfully to {}", employee.getEmail());
            
        } catch (MessagingException e) {
        	
        	// Log status in file
        	EmailStatusLogger.logEmailStatus(employee.getEmployeeId(), employee.getName(), employee.getEmail(), "NOT_SENT");
            logger.error("Failed to send email to {}", employee.getEmail(), e);
        } catch (MailException e) {
        	
        	// Log status in file
        	EmailStatusLogger.logEmailStatus(employee.getEmployeeId(), employee.getName(), employee.getEmail(), "NOT_SENT");
            logger.error("Mail server issue while sending email to {}", employee.getEmail(), e);
        }
    }
    
    private void saveToSentFolder(MimeMessage message, String recipientEmail, String senderEmail) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", "imap.ionos.com");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect("imap.ionos.com", senderEmail, "Premchand@6513");

        Folder sentFolder = store.getFolder("Sent");
        if (!sentFolder.exists()) {
            sentFolder.create(Folder.HOLDS_MESSAGES);
        } 
        
        sentFolder.open(Folder.READ_WRITE);
        
        // Append the message to the Sent folder
        Message[] messages = new Message[]{message};
        sentFolder.appendMessages(messages);

        sentFolder.close(false);
        store.close();
    }
}