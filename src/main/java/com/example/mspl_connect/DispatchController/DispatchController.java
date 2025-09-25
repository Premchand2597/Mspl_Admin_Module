package com.example.mspl_connect.DispatchController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mspl_connect.AdminService.EmployeeCustomDetails_Service;
import com.example.mspl_connect.DispatchEntity.AddUser;
import com.example.mspl_connect.DispatchEntity.Customer;
import com.example.mspl_connect.Sales_Entity.DeliveryChallan;
import com.example.mspl_connect.DispatchEntity.Dispatch;
import com.example.mspl_connect.DispatchEntity.Dispatch_DC;
import com.example.mspl_connect.DispatchEntity.Product;
import com.example.mspl_connect.DispatchEntity.TransportMode_Entity;
import com.example.mspl_connect.DispatchRepo.AddUserRepository;
import com.example.mspl_connect.DispatchRepo.CustomerRepository;
import com.example.mspl_connect.Sales_Repository.DeliveryChallanRepository;
import com.example.mspl_connect.DispatchRepo.DispatchDCRepository;
import com.example.mspl_connect.DispatchRepo.DispatchRepository;
import com.example.mspl_connect.DispatchRepo.ProductRepository;
import com.example.mspl_connect.DispatchService.AuditService;
import com.example.mspl_connect.Sales_Service.DeliveryChallanService.NumberToWordsConverter;
import com.example.mspl_connect.DispatchService.TransportMode_Service;
import com.example.mspl_connect.Sales_Service.DeliveryChallanService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class DispatchController {
	
	@Autowired
	private DeliveryChallanService dispatchService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DispatchRepository dispatchRepository;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AddUserRepository addUserRepository;
	
	@Autowired
	private TransportMode_Service transportMode_Service;
	
	@Autowired
    private DeliveryChallanRepository deliveryChallanRepository;
	
	@Autowired
	private DispatchDCRepository dispatchDCRepository;
	
	@Autowired
	private EmployeeCustomDetails_Service employeeCustomDetails_Service;
	
	@GetMapping("/dispatchIframe")
	 public String dispatchIframePage(HttpSession session) {
		// Safely check if the session attribute is null
	    if (session.getAttribute("empDetailsByEmpId") == null) {
	        return "redirect:/";
	    }
		 return "dispatch/DispatchIndexPage";
	 }
	
	@GetMapping("/form")
	public String showForm(Model model,HttpSession session) {
		if (session.getAttribute("email") == null) {
	        return "redirect:/logout";
	    }
		 
		 String username = (String) session.getAttribute("username"); 
		 String email = (String) session.getAttribute("email");
	     model.addAttribute("username", username); 
	         
		 model.addAttribute("dispatch", new Dispatch());
		 model.addAttribute("product", new Product());
		 model.addAttribute("customer", new Customer());
		 model.addAttribute("addUser", new AddUser());
		 model.addAttribute("products", productRepository.findAll());
		 model.addAttribute("customers",customerRepository.findAll());
		 
		 //System.out.println(employeeCustomDetails_Service.fetchAllACtiveEmployeesDistinctUserNames());
		 
		 List<String> transportModes = dispatchRepository.findDistinctTransportModes();
	     model.addAttribute("transportModes", transportModes);
	     model.addAttribute("dispatchList", dispatchRepository.findAll());
	     model.addAttribute("distinctUserNames", employeeCustomDetails_Service.fetchACtiveEmployeesDistinctUserNamesBasedOnEmail(email));
	      
		 return "dispatch/form";
	}
	
	@PostMapping("/submitForm")
	public String submitForm(@ModelAttribute Dispatch dispatch,
	                         //@RequestParam("file") MultipartFile file,
	                         Model model,
	                         HttpSession session) { 		
	    /*try {
	    	 if (!file.isEmpty()) {
	             // Set the folder path outside project
	             String uploadDir = "C:/Users/COMP/Desktop/dispatchDocs/";  // Or wherever you want

	             // Create the directory if it doesn't exist
	             File dir = new File(uploadDir);
	             if (!dir.exists()) {
	                 dir.mkdirs();
	             }

	             // Create a unique file name to avoid overwriting
	             String originalFilename = file.getOriginalFilename();
	             String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

	             // Create the file path
	             Path filePath = Paths.get(uploadDir, uniqueFilename);

	             // Save the file to disk
	             Files.write(filePath, file.getBytes());

	             // Save only the file path in the database
	             dispatch.setUploadFile(uniqueFilename);  // Save just the file name
 
	         }

	        Dispatch savedDispatch = dispatchService.saveDispatch(dispatch);  

	        String username = (String) session.getAttribute("username");
	        if (username == null) {
	            username = "Anonymous";
	        }

	        auditService.logAction(
	            "INSERT",
	            username,
	            "Submitted dispatch form with ID: " + savedDispatch.getId()
	        );

	        model.addAttribute("message", "Dispatch form submitted successfully");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }*/
		
		Dispatch savedDispatch = dispatchService.saveDispatch(dispatch);  

        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "Anonymous";
        }

        auditService.logAction(
            "INSERT",
            username,
            "Added dispatch details with ID: " + savedDispatch.getId()
        );

        model.addAttribute("message", "Dispatch form submitted successfully");

	    return "redirect:/form";
	}

	@GetMapping("/view-dispatch")
	    public String viewAllDispatches(Model model,HttpSession session) {
		 String username = (String) session.getAttribute("username"); 
		 String email = (String) session.getAttribute("email");
	        model.addAttribute("username", username); 
	        model.addAttribute("email", email); 
	        
	        List<Dispatch> allDispatches = dispatchService.getAllDispatches();
	        List<String> transportModes = dispatchRepository.findDistinctTransportModes();
	        
	        //allDispatches.stream().forEach(i->System.out.println("dissspatch==="+i));
	        
	        Dispatch dispatch = new Dispatch();
	        
	        model.addAttribute(dispatch);
	        model.addAttribute("dispatches", allDispatches);
	       // model.addAttribute("transportModes", transportModes);
	        model.addAttribute("distinctUserNames", addUserRepository.getAllDistinctUserNames());
	        
	        model.addAttribute("username", username); 
	         
			 model.addAttribute("dispatch", new Dispatch());
			 model.addAttribute("product", new Product());
			 model.addAttribute("customer", new Customer());
			 model.addAttribute("addUser", new AddUser());
			 model.addAttribute("products", productRepository.findAll());
			 model.addAttribute("customers",customerRepository.findAll());
		     model.addAttribute("transportModes", transportModes);
		     model.addAttribute("dispatchList", dispatchRepository.findAll());
		     model.addAttribute("distinctUserNames", employeeCustomDetails_Service.fetchACtiveEmployeesDistinctUserNamesBasedOnEmail(email));
	        
	        return "dispatch/view-dispatch"; 
	    }
	
	
	@PostMapping("/editDispatch")
	public String editDispatch(@ModelAttribute("dispatch") Dispatch dispatch,
	                           //@RequestParam("file") MultipartFile file,
	                           RedirectAttributes redirectAttributes,
	                           Model model,
	                           HttpSession session) {
		        
		 /*if (!file.isEmpty()) {
             // Set the folder path outside project
             String uploadDir = "C:/Users/COMP/Desktop/dispatchDocs/";  // Or wherever you want

             // Create the directory if it doesn't exist
             File dir = new File(uploadDir);
             if (!dir.exists()) {
                 dir.mkdirs();
             }

             // Create a unique file name to avoid overwriting
             String originalFilename = file.getOriginalFilename();
             String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

             // Create the file path
             Path filePath = Paths.get(uploadDir, uniqueFilename);

             // Save the file to disk
             try {
				Files.write(filePath, file.getBytes());
			 } catch (IOException e) {
				e.printStackTrace();
			 }

             // Save only the file path in the database
             dispatch.setUploadFile(uniqueFilename);  // Save just the file name
         }  */ 
		 //System.out.println("dispatch...."+dispatch);
		 Dispatch editDispatch = dispatchService.editDispatch(dispatch);  
		 
		 String username = (String) session.getAttribute("username");
	        if (username == null) {
	            username = "Anonymous";
	        }

	        auditService.logAction(
	            "UPDATE",
	            username,
	            "Updated dispatch details with ID: " + editDispatch.getId()
	        );
		 
	     return "redirect:/view-dispatch"; // or wherever you want to redirect
	    
	}
	
	@GetMapping("/uploads/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
	    try {
	        Path file = Paths.get("C:/Users/COMP/Desktop/dispatchDocs/").resolve(filename);
	        Resource resource = new UrlResource(file.toUri());

	        if (resource.exists() || resource.isReadable()) {
	            return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	        } else {
	            throw new RuntimeException("Could not read the file: " + filename);
	        }
	    } catch (MalformedURLException e) {
	        throw new RuntimeException("Error: " + e.getMessage());
	    }
	}
	
	@PostMapping("/submitTransportMode")
	public ResponseEntity<String> submitTransportMode(@ModelAttribute TransportMode_Entity transportMode_Entity, HttpSession session) {
		boolean isTransportModeDataAlreadyExists = transportMode_Service.isTransportModeExists(transportMode_Entity.getName());
		if(!isTransportModeDataAlreadyExists) {
			String executedResult = transportMode_Service.addTransportMode(transportMode_Entity);
			if("success".equals(executedResult)) {
				
				String username = (String) session.getAttribute("username");
		        if (username == null) {
		            username = "Anonymous";
		        }

		        auditService.logAction(
		            "INSERT",
		            username,
		            "Added transport mode data with ID: " + transportMode_Entity.getId()
		        );
		        
				return ResponseEntity.ok("successfull");
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("transport mode data not added");
			}
		}else {
			return ResponseEntity.ok("data exists");
		}
	}
	
	@GetMapping("fetchAllDistinctTransportModes")
	@ResponseBody
	public List<TransportMode_Entity> getAllTransportModeData() {
		return transportMode_Service.getAllDistinctTransportMode();
	}
	
	@PostMapping("/deleteTransportModeBasedOnName")
    public ResponseEntity<String> deleteTransportModeDetails(@RequestParam String name, HttpSession session) {
		transportMode_Service.deleteTransportModeDataBasedOnName(name);
		
		String username = (String) session.getAttribute("username");
	    if (username == null) {
	        username = "Anonymous";
	    }

	    auditService.logAction(
	        "DELETE",
	        username,
	        "Deleted Transport Mode : " + name
	    );
	    
		return ResponseEntity.ok("Data deleted successfully");
	}
	
	@GetMapping("/get-dispatch/{id}")
    @ResponseBody
    public ResponseEntity<Dispatch> getDispatchById(@PathVariable Long id) {
    	//System.out.println("iddddddddd"+id);
        Optional<Dispatch> dispatch = dispatchRepository.findById(id);
        if (dispatch.isPresent()) {
            //System.out.println("Fetched Dispatch: " + dispatch.get()); // Console log
            return ResponseEntity.ok(dispatch.get());
        } else {
            //System.out.println("Dispatch not found for ID: " + id); // Console log
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/get-dc-dispatch/{id}")
    @ResponseBody
    public ResponseEntity<Dispatch_DC> getDcDispatchById(@PathVariable Long id) {
    	//System.out.println("iddddddddd"+id);
        Optional<Dispatch_DC> dispatch = dispatchDCRepository.findById(id);
        if (dispatch.isPresent()) {
            //System.out.println("Fetched Dispatch: " + dispatch.get()); // Console log
            return ResponseEntity.ok(dispatch.get());
        } else {
            //System.out.println("Dispatch not found for ID: " + id); // Console log
            return ResponseEntity.notFound().build();
        }
    }
	
 	@PostMapping("/sendEmailToTheSuperiorWithAttachment")
    public ResponseEntity<String> sendEmailToTheCustomerWithAnAttachment(@RequestParam String from,
    																	  @RequestParam String to,
    																	  @RequestParam String cc,
								                                          @RequestParam String subject,
								                                          @RequestParam String message,
								                                          @RequestParam String password,
								                                          @RequestParam(required = false) MultipartFile file,
								                                          HttpSession session) {
 		//System.out.println(from+" "+to+" "+cc+" "+subject+" "+message+" "+password);
 		byte[] filedata = null;
 		
        try {
        	
        	if(file != null) {
        		filedata = file.getBytes();
        	}
        	dispatchService.sendEmailWithAttachment(from, to, cc, subject, message, password, filedata, session);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (IOException | MessagingException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email to the customer: " + e.getMessage());
        }
    }
 	
	@PostMapping("/updateReturnedCustomerData")
	public ResponseEntity<String> updateReturnedCustomerDetails(@RequestParam String returnedDate, @RequestParam String returnedQty, @RequestParam long auto_id, HttpSession session) {
		String result = dispatchService.updateRetunedDetailsData(returnedDate, returnedQty, auto_id);
		if("success".equals(result)) {
			String username = (String) session.getAttribute("username");
	        if (username == null) {
	            username = "Anonymous";
	        }

	        auditService.logAction(
	            "UPDATE",
	            username,
	            "Update the customer product returned details for ID: " + auto_id
	        );
	        
			return ResponseEntity.ok("successfull");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("customer returned data not added");
		}
	}
	
	@GetMapping("getAllDispatchedDetails")
	@ResponseBody
	public List<Dispatch> fetchAllDispatchedDetails(){
		List<Dispatch> allDispatches = dispatchService.getAllDispatches();
		return allDispatches;
	}
	
	@GetMapping("getAllDcDispatchedDetails")
	@ResponseBody
	public List<Dispatch_DC> fetchAllDcDispatchedDetails(){
		List<Dispatch_DC> allDispatches = dispatchService.getAllDCDispatches();
		return allDispatches;
	}
	
 	 @GetMapping("/dcDispatchData")
	 public String openDCData(Model model, HttpSession session) {
 		 //System.out.println("salesss-----");
			
		 // Retrieve user details from session
	    String email = (String) session.getAttribute("email");
	    if (email == null) { // Check if session has expired
	        return "redirect:/logout"; // Redirect to the root mapping (login page)
	    }

//		List<DepartmentTableEntity> departments = departmentService.getAllDepartments();
		//String emailid = (String) session.getAttribute("email");
	    // List<QuotationDTO> quotations = add_DC_Service.getAllQuotationForDC();
	     
	     // Debugging: Print quotation and products
	    /* for (QuotationDTO q : quotations) {
	         System.out.println("Quotation ID: " + q.getQid());
	         System.out.println("Products: " + q.getProducts()); // Check if it's null or empty
	     }*/
	   //  model.addAttribute("quotation", quotations);
		  // Get all delivery challan records (do not filter by empId)
	    List<com.example.mspl_connect.Sales_Entity.DeliveryChallan> deliveryChallans = deliveryChallanRepository.findAll(); // or via service
	    //System.out.println("hiiiii "+deliveryChallans);
	    model.addAttribute("deliveryChallans", deliveryChallans);
	    model.addAttribute("distinctUserNames", employeeCustomDetails_Service.fetchACtiveEmployeesDistinctUserNamesBasedOnEmail(email));
	    model.addAttribute("customers",customerRepository.findAll());
	    model.addAttribute("products", productRepository.findAll());

	     return "dispatch/DC_Page";
	 }
 	 
 	@PostMapping("/insertDispatchDcData")
 	public ResponseEntity<String> saveDispatchDcData(@RequestBody Dispatch_DC dispatch_DC, HttpSession session) {
 		//dispatchService.updatePriceDetails(dispatch_DC.getPrice(), dispatch_DC.getTotal_price(), dispatch_DC.getDescription(), id);
 		//System.out.println("dispatch_DC. === "+dispatch_DC.getDcNo());
 		Dispatch_DC result = dispatchService.saveDispatchDc(dispatch_DC);
 		String username = (String) session.getAttribute("username");
	        if (username == null) {
	            username = "Anonymous";
	        }

	        auditService.logAction(
	            "Generated",
	            username,
	            "DC has been generated for the ID: " + result.getId()
	        );
 			return ResponseEntity.ok("successfull");
 		}
 	
    @PostMapping("/downloadDispatchDcPdf")
    public ResponseEntity<byte[]> downloadPDF(@RequestBody Dispatch_DC dispatch_DC) {
    	Dispatch_DC data = dispatch_DC;
        if (data == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            //generatePdf(data, outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("DispatchDeliveryChallan_" + dispatch_DC.getCompanyName() + ".pdf").build());

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /*public void generatePdf(Dispatch_DC dcto, OutputStream outputStream) throws DocumentException {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font boldFont2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font normalFont3 = FontFactory.getFont(FontFactory.HELVETICA, 8);
	    
        // OUTER WRAPPER TABLE WITH BORDER
        PdfPTable outerWrapper = new PdfPTable(1);
        outerWrapper.setWidthPercentage(100);

        PdfPCell wrapperCell = new PdfPCell();
        wrapperCell.setBorder(Rectangle.BOX);
        wrapperCell.setPadding(5f); // add some internal padding

        // -------- HEADER TABLE (Address + Logo) --------
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{3, 1});

        // LEFT COLUMN - Company Details
        Paragraph companyDetails = new Paragraph();
        companyDetails.setFont(boldFont);
        companyDetails.setLeading(16f);  // Set line spacing
        companyDetails.add("Melange Systems Private Limited\n");

        companyDetails.setFont(normalFont);
        companyDetails.add("No. 4/1, 7th Cross, Kumara Park West, Bangalore 560020\n");
        //companyDetails.add("4/1, 7th Cross, Kumara Park West, Bangalore - 560020\n");
        companyDetails.add("Tel: 080-2356 1023, Telefax: 080-2346 2175\n");

        companyDetails.setFont(boldFont);
        companyDetails.add("GSTIN: 29AACCM4737H1ZA, CIN: U72200KA2000PTC027922\n");

        companyDetails.setFont(boldFont);
        companyDetails.add("Udyam Registration Number: UDYAM-KR-03-0101576\n");
        //companyDetails.add("E-Mail: info@melangesystems.com");

        // Add to cell
        PdfPCell addressCell = new PdfPCell(companyDetails);
        addressCell.setBorder(Rectangle.NO_BORDER);
        headerTable.addCell(addressCell);

      
        try {
            Image logo = Image.getInstance("src/main/resources/static/assets/img/logo.png");

            // Slightly increase logo size
            logo.scaleToFit(140, 40); // Adjust width and height as needed

            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            // Move the logo slightly higher from the bottom
            logoCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            logoCell.setPaddingBottom(20f); // Increased from 10f to 20f for a higher position
            logoCell.setPaddingLeft(-30f); 
            logoCell.setPaddingTop(0f);     // Optional

            headerTable.addCell(logoCell);

        } catch (IOException e) {
            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(emptyCell);
        }


        wrapperCell.addElement(headerTable);

        // Horizontal line below header
        LineSeparator line = new LineSeparator();
        line.setPercentage(104);
        
        line.setLineColor(BaseColor.BLACK);
        line.setLineWidth(0f);
        wrapperCell.addElement(new Chunk(line));

        // -------- TITLE --------
        Paragraph title = new Paragraph("DELIVERY CHALLAN", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(0); // Adjusted to remove extra space before the title
        wrapperCell.addElement(title);

        // Add a horizontal line below the title
        PdfPTable lineTable = new PdfPTable(1);
        lineTable.setWidthPercentage(104);
        PdfPCell lineCell = new PdfPCell(new Phrase(" "));
        lineCell.setBorder(Rectangle.BOTTOM);
        lineCell.setBorderWidthBottom(0.5f); // Thickness of the line
        lineCell.setBorderColor(BaseColor.BLACK); // Color of the line
        lineCell.setFixedHeight(2f); // Set a small fixed height for the line (adjust as needed)
        lineTable.addCell(lineCell);

        // Adjust the space between the title and line by controlling the table's position
        lineTable.setSpacingBefore(4f); // Add a bit more space before the line to move it down
        lineTable.setSpacingAfter(0); // Keep the space after the line minimal

        // Add the line table to the wrapper
        wrapperCell.addElement(lineTable);


     // -------- ADDRESS & INFO TABLE --------
        PdfPTable outerTable = new PdfPTable(2);
        outerTable.setWidthPercentage(104);
        outerTable.setWidths(new float[]{1.5f, 2f});
        outerTable.setSpacingBefore(0);

        // Address Table (Left Side)
        PdfPTable addressTable = new PdfPTable(1);
        addressTable.setWidthPercentage(100);

        String buyerAddress = dcto.getCompanyAddress();
        if (buyerAddress != null && buyerAddress.contains("GSTIN/UIN")) {
            buyerAddress = buyerAddress.replace("GSTIN/UIN", "\nGSTIN/UIN");
        }
        Paragraph buyer = new Paragraph("Buyer (Bill to):\n\n" + buyerAddress, normalFont);
        addressTable.addCell(createCellWithContent(buyer));

        // Info Table (Right Side)
        PdfPTable infoTable = new PdfPTable(4);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(0);
        infoTable.setWidths(new float[]{1.5f, 2f, 1.5f, 2f});

        infoTable.addCell(getCell("Delivery Note No.", PdfPCell.ALIGN_LEFT, boldFont));
        infoTable.addCell(getCell(dcto.getDcNo(), PdfPCell.ALIGN_LEFT, normalFont));
        infoTable.addCell(getCell("Dated", PdfPCell.ALIGN_LEFT, boldFont));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //infoTable.addCell(getCell(dcto.get().format(formatter), PdfPCell.ALIGN_LEFT));

        infoTable.addCell(getCell("Reference No. & Date", PdfPCell.ALIGN_LEFT, boldFont));
        infoTable.addCell(getCell(dcto.getDate(), PdfPCell.ALIGN_LEFT, normalFont));
        infoTable.addCell(getCell("Buyer's Order No.", PdfPCell.ALIGN_LEFT, boldFont));
        infoTable.addCell(getCell(dcto.getCompanyName(), PdfPCell.ALIGN_LEFT, normalFont));

        infoTable.addCell(getCell("Mode/Terms of Payment", PdfPCell.ALIGN_LEFT, boldFont));
        infoTable.addCell(getCell(dcto.getModuleNo(), PdfPCell.ALIGN_LEFT, normalFont));
        infoTable.addCell(getCell("Other References", PdfPCell.ALIGN_LEFT, boldFont));
        infoTable.addCell(getCell(dcto.geteWayBillNo(), PdfPCell.ALIGN_LEFT, normalFont));

        infoTable.addCell(getCell("Terms of Delivery", PdfPCell.ALIGN_LEFT, boldFont));
        PdfPCell termsCell = new PdfPCell(new Phrase(dcto.getDescription(), normalFont));
        termsCell.setColspan(3);
        
        infoTable.addCell(termsCell);

        // Wrap address table in a cell with right border
        PdfPCell addressTableCell = new PdfPCell(addressTable);
        addressTableCell.setBorder(Rectangle.NO_BORDER);
        addressTableCell.setBorderWidthRight(0f); // vertical divider
        addressTableCell.setBorderColorRight(BaseColor.BLACK);
        
        outerTable.addCell(addressTableCell);

        // Wrap info table in a cell with only left border
        PdfPCell infoTableCell = new PdfPCell(infoTable);
        infoTableCell.setBorder(Rectangle.NO_BORDER);
       // infoTableCell.setBorderWidthLeft(0f); // vertical divider continuation
        infoTableCell.setBorderColorLeft(BaseColor.BLACK);
        
        outerTable.addCell(infoTableCell);

        // Add the outer table to the wrapper cell
        wrapperCell.addElement(outerTable);

        // -------- PRODUCT TABLE --------
        PdfPTable productTable = new PdfPTable(6);
        
        productTable.setWidthPercentage(104);
        //productTable.setSpacingBefore(10);
        productTable.setWidths(new int[]{1, 3, 2, 2, 2, 2});
        //Font headerFont = new Font(Font.FontFamily.HELVETICA, 9);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
	      
        float headerHeight = 25f;

        PdfPCell cell;

        // S.No Header
        cell = new PdfPCell(new Phrase("S.No",headerFont));
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        //cell.setBorderWidthTop(8f); 
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // Description Header
        cell = new PdfPCell(new Phrase("Description",headerFont));
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // HSN/SAC Header
        cell = new PdfPCell(new Phrase("HSN/SAC",headerFont));
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // Quantity Header
        cell = new PdfPCell(new Phrase("Quantity",headerFont));
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // Rate Header
        cell = new PdfPCell(new Phrase("Rate",headerFont));
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // Amount Header
        cell = new PdfPCell(new Phrase("Amount",headerFont));
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        cell.setMinimumHeight(headerHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        productTable.addCell(cell);

        // Add actual product rows
        int count = 1;
        /*for (GoodsDetailDTO detail : dcto.getGoodsDetails()) {
        	   // S.No
            cell = new PdfPCell(new Phrase(String.valueOf(count++)));
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            cell.setMinimumHeight(25f);
            cell.setBorderWidthBottom(0f); 
            productTable.addCell(cell);

            // Check and append GST information
            String gstInfo = "";
            if (detail.getIgstAmount() != 0) {
                gstInfo = "IGST";
            } else if (detail.getCgstAmount() != 0 && detail.getSgstAmount() != 0) {
                gstInfo = "CGST + SGST";
            }

         // Build Phrase with description and styled GST
            Phrase phrase = new Phrase();
            Font descriptionFont = new Font(Font.FontFamily.HELVETICA, 10); // Smaller font size

            phrase.add(new Chunk(detail.getDescription() + "\n\n\n\n", descriptionFont)); // Two newlines for spacing

            // Add bold GST text, aligned right
            Font gstFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Chunk gstChunk = new Chunk(gstInfo, gstFont);
            Paragraph gstParagraph = new Paragraph(gstChunk);
            gstParagraph.setAlignment(Element.ALIGN_RIGHT);

            // Add the GST paragraph to phrase
            phrase.add(gstParagraph);

            cell = new PdfPCell(phrase);
            cell.setMinimumHeight(30f); // Slightly taller row for better spacing
            cell.setBorderWidthBottom(0f); 
            productTable.addCell(cell);

            // HSN/SAC
            cell = new PdfPCell(new Phrase(detail.getHsnSac(),descriptionFont));
            cell.setMinimumHeight(25f);
            cell.setBorderWidthBottom(0f); 
            productTable.addCell(cell);

            // Quantity
            cell = new PdfPCell(new Phrase(String.valueOf(detail.getQuantity()),descriptionFont));
            cell.setMinimumHeight(25f);
            cell.setBorderWidthBottom(0f); 
            productTable.addCell(cell);

            // Rate
            cell = new PdfPCell(new Phrase("₹" + detail.getRate(),descriptionFont));
            cell.setMinimumHeight(25f);
            cell.setBorderWidthBottom(0f); 
            productTable.addCell(cell);

         // Build Phrase with main amount and total tax aligned right
            Phrase amountPhrase = new Phrase();
            amountPhrase.add(new Chunk("₹" + detail.getAmount() + "\n\n\n\n\n\n",descriptionFont)); // space for amount

            // Calculate total tax
            double totalTax = detail.getTotalWithTax() ;
            Font taxFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Chunk taxChunk = new Chunk("₹" + totalTax, taxFont);
            Paragraph taxParagraph = new Paragraph(taxChunk);
            taxParagraph.setAlignment(Element.ALIGN_RIGHT);

            // Add the tax paragraph to the phrase
            amountPhrase.add(taxParagraph);

            cell = new PdfPCell(amountPhrase);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            cell.setMinimumHeight(30f);
            cell.setBorderWidthBottom(0f);
            productTable.addCell(cell);

        }

        // Pad with empty rows to maintain fixed size (e.g., 10 rows total)
        int totalRows = 6;
        int addedRows = dcto.getGoodsDetails().size();
        int rowsToAdd = totalRows - addedRows;

        for (int i = 0; i < rowsToAdd; i++) {
            for (int col = 0; col < 6; col++) {
                cell = new PdfPCell(new Phrase(" "));
                cell.setFixedHeight(25f);  // Consistent row height

                // Remove top border to avoid horizontal lines between rows
                cell.setBorderWidthTop(0f);

                // Keep vertical borders
                cell.setBorderWidthLeft(0.5f);
                cell.setBorderWidthRight(0.5f);

                // Only the last row should have a bottom border
                if (i == rowsToAdd - 1) {
                    cell.setBorderWidthBottom(0.5f);
                } else {
                    cell.setBorderWidthBottom(0f);
                }

                productTable.addCell(cell);
            }
        }
     // ⬇️ INSERT FOOTER CODE HERE
     // Calculate grand totals
        double grandTotalAmount = 0;
        int totalQuantity = 0;
        for (GoodsDetailDTO detail : dcto.getGoodsDetails()) {
            grandTotalAmount += detail.getTotalWithTax() + detail.getAmount();
            totalQuantity += detail.getQuantity();
        }*/

        // TOTAL label under Description (span across S.No + Description)
//        cell = new PdfPCell(new Phrase("TOTAL", new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
//        cell.setColspan(2); // spans S.No and Description
//        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cell.setBorderWidthLeft(0); // match description column style
//        cell.setBorderWidthRight(0); // optional: make clean look
//        cell.setMinimumHeight(25f);
//        productTable.addCell(cell);
//
//        // HSN/SAC - empty
//        cell = new PdfPCell(new Phrase(""));
//        cell.setMinimumHeight(25f);
//        productTable.addCell(cell);
//
//        // Quantity - total quantity
//        cell = new PdfPCell(new Phrase(String.valueOf(dcto.getQuantity()), new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setMinimumHeight(25f);
//        productTable.addCell(cell);
//
//        // Rate - empty
//        cell = new PdfPCell(new Phrase(""));
//        cell.setMinimumHeight(25f);
//        productTable.addCell(cell);
//        
//        String total = dcto.getTotal_price(); // assuming it's String
//        double totalPrice = Double.parseDouble(total);
//
//        // Amount - total amount aligned right
//        cell = new PdfPCell(new Phrase("₹" + String.format("%.2f", totalPrice), new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
//        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cell.setBorderWidthLeft(0); // match amount cell styling
//        cell.setBorderWidthRight(0);
//        cell.setMinimumHeight(25f);
//        productTable.addCell(cell);
//
//     // ✅ END FOOTER
//
//        // Add to wrapper
//        wrapperCell.addElement(productTable);
//
//     // Convert total amount to words
//        String amountInWords = convertNumberToWords(totalPrice);
//
//        // Create full line with label and value
//        String fullLine = "Amount Chargeable (in words): " + amountInWords;
//
//        // Add as a bold paragraph
//        Paragraph amountInWordsParagraph = new Paragraph(fullLine, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
//        amountInWordsParagraph.setSpacingBefore(10f);
//        wrapperCell.addElement(amountInWordsParagraph);
//
//        // -------- TAX SUMMARY --------
//     /*   PdfPTable taxSummary = new PdfPTable(5);
//        taxSummary.setWidthPercentage(100);
//        taxSummary.setSpacingBefore(15);
//        taxSummary.setWidths(new int[]{2, 2, 2, 2, 2});
//        addTableHeader(taxSummary, new String[]{"HSN/SAC", "Tax Rate", "Taxable Value", "Tax Amount", "Total"});
//
//        for (GoodsDetailDTO tax : dcto.getGoodsDetails()) {
//            String taxRate = "";
//            double taxAmount = 0;
//
//            if (tax.getCgstRate() != null && tax.getSgstRate() != null) {
//                taxRate = tax.getCgstRate() + " + " + tax.getSgstRate();
//                taxAmount = tax.getCgstAmount() + tax.getSgstAmount();
//            } else if (tax.getIgstRate() != null) {
//                taxRate = tax.getIgstRate();
//                taxAmount = tax.getIgstAmount();
//            }
//
//            taxSummary.addCell(tax.getHsnSac());
//            taxSummary.addCell(taxRate);
//            taxSummary.addCell("₹" + tax.getAmount());
//            taxSummary.addCell("₹" + taxAmount);
//            taxSummary.addCell("₹" + (tax.getAmount() + taxAmount));
//        }
//        wrapperCell.addElement(taxSummary);*/
//
//        // -------- REMARKS + FOOTER --------
//        Paragraph remarks = new Paragraph("Remarks:\n" + dcto.getRemarks(), normalFont);
//        remarks.setSpacingBefore(10);
//        wrapperCell.addElement(remarks);
//
//        Font normalFont1 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
//        Font boldFont1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
//
//        Phrase panPhrase = new Phrase();
//        panPhrase.add(new Chunk("Company’s PAN : ", normalFont1));
//        panPhrase.add(new Chunk("AACCM4737H", boldFont1));
//
//        Paragraph pan = new Paragraph(panPhrase);
//        pan.setSpacingBefore(10);
//        wrapperCell.addElement(pan);
//
//     // Create a 3-column footer table (left | divider | right)
//        PdfPTable footerTable = new PdfPTable(3);
//        footerTable.setWidthPercentage(100);
//        footerTable.setWidths(new float[]{48, 4, 48}); // Adjust widths
//
//        // Create font
//        Font normalFont2 = FontFactory.getFont(FontFactory.HELVETICA, 10);
//
//        // Line separator (spans all 3 columns)
//        PdfPCell lineCell1 = new PdfPCell();
//        lineCell1.setColspan(3);
//      
//        lineCell1.setBorder(Rectangle.BOTTOM);
//        lineCell1.setBorderWidthBottom(1f);
//        lineCell1.setFixedHeight(10f);
//        lineCell1.setBorderColorBottom(BaseColor.BLACK);
//        lineCell1.setPadding(0);
//        footerTable.addCell(lineCell1);
//     // Set the table width percentage (applies to the entire table)
//        footerTable.setWidthPercentage(104); //for the line above to extend 
//        // Set a fixed height for all footer content cells
//        float footerRowHeight = 50f; // Adjust this height as needed
//
//        // Left cell
//        PdfPCell leftCell = new PdfPCell(new Phrase("Recd. in Good Condition", normalFont2));
//        leftCell.setBorder(Rectangle.NO_BORDER);
//        leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        leftCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        leftCell.setFixedHeight(footerRowHeight);
//        footerTable.addCell(leftCell);
//
//        // Divider cell (vertical line)
//        PdfPCell dividerCell = new PdfPCell();
//        dividerCell.setBorder(Rectangle.LEFT);
//        dividerCell.setBorderWidthLeft(1f);
//       // dividerCell.setWidthPercentage(104);
//        dividerCell.setBorderColorLeft(BaseColor.GRAY); // Line color
//        dividerCell.setFixedHeight(footerRowHeight );
//        dividerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        dividerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        dividerCell.setPhrase(new Phrase("")); // Keep empty
//     // Set the table's width percentage (not cell)
//        //dividerCell.setWidthPercentage(104);
//        footerTable.addCell(dividerCell);
//
//        // Right cell
//        PdfPCell rightCell = new PdfPCell(new Phrase("for Melange Systems Pvt Ltd.\n\n Authorised Signatory", normalFont2));
//        rightCell.setBorder(Rectangle.NO_BORDER);
//        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        rightCell.setFixedHeight(footerRowHeight);
//        footerTable.addCell(rightCell);
//
//        // Spacing before footer
//        footerTable.setSpacingBefore(20);
//
//        // Add footer to wrapper
//        wrapperCell.addElement(footerTable);
//        outerWrapper.addCell(wrapperCell);
//
//        // Add to document
//        document.add(outerWrapper);
//
//
//        document.close();
//    }*/
    
    public static String convertNumberToWords(double amount) {
        long rupees = (long) amount;
        int paise = (int) Math.round((amount - rupees) * 100);
        
        String words = NumberToWordsConverter.convert(rupees) + " Rupees";
        if (paise > 0) {
            words += " and " + NumberToWordsConverter.convert(paise) + " Paise";
        }
        return words + " Only";
    }
    
    private PdfPCell createCellWithContent(Paragraph content) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(content);
        cell.setBorder(Rectangle.NO_BORDER); // 👈 removes border
        return cell;
    }
    
    private PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.BOX); // Enable all borders
        cell.disableBorderSide(PdfPCell.TOP); // Remove only the top border
        
        return cell;
    }
	
}

