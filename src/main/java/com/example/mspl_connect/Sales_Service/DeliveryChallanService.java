package com.example.mspl_connect.Sales_Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.Dispatch;
import com.example.mspl_connect.DispatchEntity.Dispatch_DC;
import com.example.mspl_connect.DispatchRepo.DispatchDCRepository;
import com.example.mspl_connect.DispatchRepo.DispatchRepository;
import com.example.mspl_connect.Sales_Entity.DeliveryChallan;
import com.example.mspl_connect.Sales_Entity.DeliveryChallanDTO;
import com.example.mspl_connect.Sales_Entity.DeliveryChallanDetail;
import com.example.mspl_connect.Sales_Entity.GoodsDetailDTO;
import com.example.mspl_connect.Sales_Repository.DeliveryChallanDetailRepository;
import com.example.mspl_connect.Sales_Repository.DeliveryChallanRepository;
import com.itextpdf.layout.element.Paragraph;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class DeliveryChallanService {

	 @Autowired
	    private DeliveryChallanRepository deliveryChallanRepository;

	    @Autowired
	    private DeliveryChallanDetailRepository deliveryChallanDetailRepository;
	    
	    @Autowired
		private DispatchDCRepository dispatchDCRepository;
	    
	    @Autowired
		private DispatchRepository dispatchRepository;
		
	    @Autowired
	    private JavaMailSender mailSender;

	    @Transactional
	    public void saveDeliveryChallan(DeliveryChallan deliveryChallan, List<DeliveryChallanDetail> goodsDetails) {
	        // Save main Delivery Challan data
	        DeliveryChallan savedChallan = deliveryChallanRepository.save(deliveryChallan);

	        // Save the goods and tax details with the Delivery Challan ID
	        for (DeliveryChallanDetail detail : goodsDetails) {
	            detail.setDeliveryChallanId(savedChallan.getId());
	            deliveryChallanDetailRepository.save(detail);
	        }
	    }
	
	    
	   
	    // Other methods...

	    public DeliveryChallanDTO getDeliveryChallanById(Long id) {
	        // Fetch the delivery challan entity by its ID
	        DeliveryChallan deliveryChallan = deliveryChallanRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Delivery Challan not found with id " + id));
	        // Fetch related goods details manually using deliveryChallanId
	        List<DeliveryChallanDetail> goodsDetails = deliveryChallanDetailRepository.findByDeliveryChallanId(id);

	        // Map the DeliveryChallan entity to DeliveryChallanDTO
	        return mapToDTO(deliveryChallan, goodsDetails);
	    }

	    private DeliveryChallanDTO mapToDTO(DeliveryChallan deliveryChallan, List<DeliveryChallanDetail> goodsDetails) {
	        DeliveryChallanDTO dto = new DeliveryChallanDTO();
	        dto.setPurchaseOrderId(deliveryChallan.getPurchaseOrderId());
	        dto.setQuotationId(deliveryChallan.getQuotationId());
	        dto.setSupplierAddress(deliveryChallan.getSupplierAddress());
	        dto.setBuyer(deliveryChallan.getBuyer());
	        dto.setReferenceNoDate(deliveryChallan.getReferenceNoDate());
	        dto.setDeliveryNoteNo(deliveryChallan.getDeliveryNoteNo());
	        dto.setDated(deliveryChallan.getDated());
	        dto.setReferenceNo(deliveryChallan.getReferenceNo());
	        dto.setOtherReferences(deliveryChallan.getOtherReferences());
	        dto.setTermsOfDelivery(deliveryChallan.getTermsOfDelivery());
	        dto.setPaymentTerms(deliveryChallan.getPaymentTerms());
	        dto.setBuyersOrderNo(deliveryChallan.getBuyersOrderNo());
	        dto.setBuyersOrderDate(deliveryChallan.getBuyersOrderDate());
	        dto.setRemarks(deliveryChallan.getRemarks());
	        dto.setCompanyPan(deliveryChallan.getCompanyPan());
	        dto.setCreatedDate(deliveryChallan.getCreatedDate());
	        dto.setCreatedByEmpId(deliveryChallan.getCreatedByEmpId());
	        dto.setRoundedGrandTotal(deliveryChallan.getRoundedGrandTotal());
	        dto.setDeliveryAddress(deliveryChallan.getDeliveryAddress());
	        
	        List<GoodsDetailDTO> goodsDetailDTOs = new ArrayList<>();
	        for (DeliveryChallanDetail good : goodsDetails) {
	            System.out.println("Mapping goods item: " + good.getDescription());

	            GoodsDetailDTO goodsDTO = new GoodsDetailDTO();

	            System.out.println("Setting Description: " + good.getDescription());
	            goodsDTO.setDescription(good.getDescription());

	            System.out.println("Setting HSN/SAC: " + good.getHsnSac());
	            goodsDTO.setHsnSac(good.getHsnSac());

	            System.out.println("Setting Quantity: " + good.getQuantity());
	            goodsDTO.setQuantity(good.getQuantity());

	            System.out.println("Setting Rate: " + good.getRate());
	            goodsDTO.setRate(good.getRate());

	            System.out.println("Setting Per Unit: " + good.getPerUnit());
	            goodsDTO.setPerUnit(good.getPerUnit());

	            System.out.println("Setting Amount: " + good.getAmount());
	            goodsDTO.setAmount(good.getAmount());

	            System.out.println("Setting CGST Rate: " + good.getCgstRate());
	            goodsDTO.setCgstRate(good.getCgstRate());

	            System.out.println("Setting CGST Amount: " + good.getCgstAmount());
	            goodsDTO.setCgstAmount(good.getCgstAmount());

	            System.out.println("Setting SGST Rate: " + good.getSgstRate());
	            goodsDTO.setSgstRate(good.getSgstRate());

	            System.out.println("Setting SGST Amount: " + good.getSgstAmount());
	            goodsDTO.setSgstAmount(good.getSgstAmount());

	            System.out.println("Setting IGST Rate: " + good.getIgstRate());
	            goodsDTO.setIgstRate(good.getIgstRate());

	            System.out.println("Setting IGST Amount: " + good.getIgstAmount());
	            goodsDTO.setIgstAmount(good.getIgstAmount());

	            System.out.println("Setting Total Tax Amount: " + good.getTotalTaxAmount());
	            goodsDTO.setTotalTaxAmount(good.getTotalTaxAmount());

	            System.out.println("Setting Total With Tax: " + good.getTotalWithTax());
	            goodsDTO.setTotalWithTax(good.getTotalWithTax());

	            goodsDetailDTOs.add(goodsDTO);
	            System.out.println("Added goodsDTO to list: " + goodsDTO);
	        }
	        dto.setGoodsDetails(goodsDetailDTOs);
	        return dto;
	    }

	  
	    
	    
	   /* public void generatePdf(DeliveryChallanDTO dcto, OutputStream outputStream) throws DocumentException {
	        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
	        PdfWriter.getInstance(document, outputStream);
	        document.open();

	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
	        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
	        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
	        Font boldFont2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
	        Font normalFont3 = FontFactory.getFont(FontFactory.HELVETICA, 11);
		    
	        // OUTER WRAPPER TABLE WITH BORDER
	        PdfPTable outerWrapper = new PdfPTable(1);
	        outerWrapper.setWidthPercentage(100);

	        PdfPCell wrapperCell = new PdfPCell();
	        wrapperCell.setBorder(Rectangle.BOX);
	        wrapperCell.setPadding(10f); // add some internal padding

	        // -------- HEADER TABLE (Address + Logo) --------
	        PdfPTable headerTable = new PdfPTable(2);
	        headerTable.setWidthPercentage(100);
	        headerTable.setWidths(new float[]{3, 1});

	     // LEFT COLUMN - Company Details
	        Paragraph companyDetails = new Paragraph();
	        companyDetails.setFont(boldFont2);
	        companyDetails.setLeading(14f);  // Set line spacing

	        companyDetails.add("Melange Systems Private Limited\n");

	        companyDetails.setFont(normalFont3);
	        companyDetails.add("(ISO 9001:2015 Certified Company)\n");

	        companyDetails.add("4/1, 7th Cross, Kumara Park West, Bangalore - 560020\n");
	        companyDetails.add("Phone: +91 8023561023\n");

	        companyDetails.setFont(boldFont2);
	        companyDetails.add("GSTIN: 29AACCM4737H1ZA\n");

	        companyDetails.setFont(normalFont3);
	        companyDetails.add("State: Karnataka, Code: 29\n");
	        companyDetails.add("E-Mail: info@melangesystems.com");

	        // Add to cell
	        PdfPCell addressCell = new PdfPCell(companyDetails);
	        addressCell.setBorder(Rectangle.NO_BORDER);
	        headerTable.addCell(addressCell);

	      
	        try {
	            Image logo = Image.getInstance("src/main/resources/static/assets/img/logo.png");

	            // Slightly increase logo size
	            logo.scaleToFit(140, 90); // Adjust width and height as needed

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
	        Paragraph title = new Paragraph("DELIVERY NOTE", titleFont);
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

	        String buyerAddress = dcto.getBuyer();
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
	        infoTable.addCell(getCell(dcto.getDeliveryNoteNo(), PdfPCell.ALIGN_LEFT));
	        infoTable.addCell(getCell("Dated", PdfPCell.ALIGN_LEFT, boldFont));
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        infoTable.addCell(getCell(dcto.getDated().format(formatter), PdfPCell.ALIGN_LEFT));

	        infoTable.addCell(getCell("Reference No. & Date", PdfPCell.ALIGN_LEFT, boldFont));
	        infoTable.addCell(getCell(dcto.getReferenceNoDate(), PdfPCell.ALIGN_LEFT));
	        infoTable.addCell(getCell("Buyer's Order No.", PdfPCell.ALIGN_LEFT, boldFont));
	        infoTable.addCell(getCell(dcto.getBuyersOrderNo(), PdfPCell.ALIGN_LEFT));

	        infoTable.addCell(getCell("Mode/Terms of Payment", PdfPCell.ALIGN_LEFT, boldFont));
	        infoTable.addCell(getCell(dcto.getPaymentTerms(), PdfPCell.ALIGN_LEFT));
	        infoTable.addCell(getCell("Other References", PdfPCell.ALIGN_LEFT, boldFont));
	        infoTable.addCell(getCell(dcto.getOtherReferences(), PdfPCell.ALIGN_LEFT));

	        infoTable.addCell(getCell("Terms of Delivery", PdfPCell.ALIGN_LEFT, boldFont));
	        PdfPCell termsCell = new PdfPCell(new Phrase(dcto.getTermsOfDelivery(), normalFont));
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
	        for (GoodsDetailDTO detail : dcto.getGoodsDetails()) {
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
	        }

	        // TOTAL label under Description (span across S.No + Description)
	        cell = new PdfPCell(new Phrase("TOTAL", new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
	        cell.setColspan(2); // spans S.No and Description
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorderWidthLeft(0); // match description column style
	        cell.setBorderWidthRight(0); // optional: make clean look
	        cell.setMinimumHeight(25f);
	        productTable.addCell(cell);

	        // HSN/SAC - empty
	        cell = new PdfPCell(new Phrase(""));
	        cell.setMinimumHeight(25f);
	        productTable.addCell(cell);

	        // Quantity - total quantity
	        cell = new PdfPCell(new Phrase(String.valueOf(totalQuantity), new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setMinimumHeight(25f);
	        productTable.addCell(cell);

	        // Rate - empty
	        cell = new PdfPCell(new Phrase(""));
	        cell.setMinimumHeight(25f);
	        productTable.addCell(cell);

	        // Amount - total amount aligned right
	        cell = new PdfPCell(new Phrase("₹" + String.format("%.2f", grandTotalAmount), new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorderWidthLeft(0); // match amount cell styling
	        cell.setBorderWidthRight(0);
	        cell.setMinimumHeight(25f);
	        productTable.addCell(cell);

	     // ✅ END FOOTER

	        // Add to wrapper
	        wrapperCell.addElement(productTable);

	        
	     // Convert total amount to words
	        String amountInWords = convertNumberToWords(grandTotalAmount);

	        // Create full line with label and value
	        String fullLine = "Amount Chargeable (in words): " + amountInWords;

	        // Add as a bold paragraph
	        Paragraph amountInWordsParagraph = new Paragraph(fullLine, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
	        amountInWordsParagraph.setSpacingBefore(10f);
	        wrapperCell.addElement(amountInWordsParagraph);

	        // -------- TAX SUMMARY --------
	     /*   PdfPTable taxSummary = new PdfPTable(5);
	        taxSummary.setWidthPercentage(100);
	        taxSummary.setSpacingBefore(15);
	        taxSummary.setWidths(new int[]{2, 2, 2, 2, 2});
	        addTableHeader(taxSummary, new String[]{"HSN/SAC", "Tax Rate", "Taxable Value", "Tax Amount", "Total"});

	        for (GoodsDetailDTO tax : dcto.getGoodsDetails()) {
	            String taxRate = "";
	            double taxAmount = 0;

	            if (tax.getCgstRate() != null && tax.getSgstRate() != null) {
	                taxRate = tax.getCgstRate() + " + " + tax.getSgstRate();
	                taxAmount = tax.getCgstAmount() + tax.getSgstAmount();
	            } else if (tax.getIgstRate() != null) {
	                taxRate = tax.getIgstRate();
	                taxAmount = tax.getIgstAmount();
	            }

	            taxSummary.addCell(tax.getHsnSac());
	            taxSummary.addCell(taxRate);
	            taxSummary.addCell("₹" + tax.getAmount());
	            taxSummary.addCell("₹" + taxAmount);
	            taxSummary.addCell("₹" + (tax.getAmount() + taxAmount));
	        }
	        wrapperCell.addElement(taxSummary); 

	        // -------- REMARKS + FOOTER --------
	        Paragraph remarks = new Paragraph("Remarks:\n" + dcto.getRemarks(), normalFont);
	        remarks.setSpacingBefore(10);
	        wrapperCell.addElement(remarks);

	        Font normalFont1 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
	        Font boldFont1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

	        Phrase panPhrase = new Phrase();
	        panPhrase.add(new Chunk("Company’s PAN : ", normalFont1));
	        panPhrase.add(new Chunk("AACCM4737H", boldFont1));

	        Paragraph pan = new Paragraph(panPhrase);
	        pan.setSpacingBefore(10);
	        wrapperCell.addElement(pan);

	     // Create a 3-column footer table (left | divider | right)
	        PdfPTable footerTable = new PdfPTable(3);
	        footerTable.setWidthPercentage(100);
	        footerTable.setWidths(new float[]{48, 4, 48}); // Adjust widths

	        // Create font
	        Font normalFont2 = FontFactory.getFont(FontFactory.HELVETICA, 10);

	        // Line separator (spans all 3 columns)
	        PdfPCell lineCell1 = new PdfPCell();
	        lineCell1.setColspan(3);
	      
	        lineCell1.setBorder(Rectangle.BOTTOM);
	        lineCell1.setBorderWidthBottom(1f);
	        lineCell1.setFixedHeight(10f);
	        lineCell1.setBorderColorBottom(BaseColor.BLACK);
	        lineCell1.setPadding(0);
	        footerTable.addCell(lineCell1);
	     // Set the table width percentage (applies to the entire table)
	        footerTable.setWidthPercentage(104); //for the line above to extend 
	        // Set a fixed height for all footer content cells
	        float footerRowHeight = 50f; // Adjust this height as needed

	        // Left cell
	        PdfPCell leftCell = new PdfPCell(new Phrase("Recd. in Good Condition", normalFont2));
	        leftCell.setBorder(Rectangle.NO_BORDER);
	        leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        leftCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        leftCell.setFixedHeight(footerRowHeight);
	        footerTable.addCell(leftCell);

	        // Divider cell (vertical line)
	        PdfPCell dividerCell = new PdfPCell();
	        dividerCell.setBorder(Rectangle.LEFT);
	        dividerCell.setBorderWidthLeft(1f);
	       // dividerCell.setWidthPercentage(104);
	        dividerCell.setBorderColorLeft(BaseColor.GRAY); // Line color
	        dividerCell.setFixedHeight(footerRowHeight );
	        dividerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        dividerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        dividerCell.setPhrase(new Phrase("")); // Keep empty
	     // Set the table's width percentage (not cell)
	        //dividerCell.setWidthPercentage(104);
	        footerTable.addCell(dividerCell);

	        // Right cell
	        PdfPCell rightCell = new PdfPCell(new Phrase("for Melange Systems Pvt Ltd.\n\n Authorised Signatory", normalFont2));
	        rightCell.setBorder(Rectangle.NO_BORDER);
	        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        rightCell.setFixedHeight(footerRowHeight);
	        footerTable.addCell(rightCell);

	        // Spacing before footer
	        footerTable.setSpacingBefore(20);

	        // Add footer to wrapper
	        wrapperCell.addElement(footerTable);
	        outerWrapper.addCell(wrapperCell);

	        // Add to document
	        document.add(outerWrapper);


	        document.close();
	    }


	    private PdfPCell getCell(String text, int alignment) {
	        return getCell(text, alignment, FontFactory.getFont(FontFactory.HELVETICA, 10));
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
	    */
	    
	    public static String convertNumberToWords(double amount) {
	        long rupees = (long) amount;
	        int paise = (int) Math.round((amount - rupees) * 100);
	        
	        String words = NumberToWordsConverter.convert(rupees) + " Rupees";
	        if (paise > 0) {
	            words += " and " + NumberToWordsConverter.convert(paise) + " Paise";
	        }
	        return words + " Only";
	    }

	  /*  private PdfPCell createCellWithContent(Paragraph content) {
	        PdfPCell cell = new PdfPCell(content);
	        cell.setBorder(PdfPCell.NO_BORDER);  // Remove border for better formatting
	        cell.setPadding(5);
	        return cell;
	    }
	    
	    private PdfPCell createCellWithContent(Paragraph content) {
	        PdfPCell cell = new PdfPCell();
	        cell.addElement(content);
	        cell.setBorder(Rectangle.NO_BORDER); // 👈 removes border
	        return cell;
	    }

	    private void addTableHeader(PdfPTable table, String[] headers) {
	        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
	        for (String columnTitle : headers) {
	            PdfPCell header = new PdfPCell();
	            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            header.setHorizontalAlignment(Element.ALIGN_CENTER);
	            header.setPhrase(new Phrase(columnTitle, headFont));
	            table.addCell(header);
	        }
	    }*/

	    
	    public DeliveryChallanDTO convertToDTO(DeliveryChallan dc) {
	        // Map basic fields from DeliveryChallan entity
	        String supplierAddress = dc.getSupplierAddress();
	        String buyer = dc.getBuyer();
	        String deliveryNoteNo = dc.getDeliveryNoteNo();
	        LocalDate dated = dc.getDated();
	        String referenceNo = dc.getReferenceNo();
	        String otherReferences = dc.getOtherReferences();
	        String termsOfDelivery = dc.getTermsOfDelivery();
	        String buyersOrderNo = dc.getBuyersOrderNo();
	        LocalDate buyersOrderDate = dc.getBuyersOrderDate();
	        String remarks = dc.getRemarks();
	        String companyPan = dc.getCompanyPan();
	        String paymentTerms = dc.getPaymentTerms();
	        String referenceNoDate = dc.getReferenceNoDate();
	        String purchaseOrderId = dc.getPurchaseOrderId();
	        String quotationId = dc.getQuotationId();
	        LocalDateTime createdDate = dc.getCreatedDate();
	        String createdByEmpId = dc.getCreatedByEmpId();

	        // Fetch associated DeliveryChallanDetails from the database (if not already fetched)
	        List<DeliveryChallanDetail> goodsDetailsEntities = deliveryChallanDetailRepository.findByDeliveryChallanId(dc.getId());

	        // Convert DeliveryChallanDetail to GoodsDetailDTO
	        List<GoodsDetailDTO> goodsDetails = goodsDetailsEntities.stream()
	            .map(detail -> new GoodsDetailDTO(
	                detail.getDescription(),
	                detail.getHsnSac(),
	                detail.getQuantity(),
	                detail.getRate(),
	                detail.getPerUnit(),
	                detail.getAmount(),
	                detail.getCgstRate(),
	                detail.getCgstAmount(),
	                detail.getSgstRate(),
	                detail.getSgstAmount(),
	                detail.getIgstRate(),
	                detail.getIgstAmount(),
	                detail.getTotalTaxAmount(),
	                detail.getTotalWithTax()
	            ))
	            .collect(Collectors.toList());

	        // Return the DTO with goodsDetails
	        return new DeliveryChallanDTO(
	            supplierAddress, 
	            buyer, 
	            deliveryNoteNo, 
	            dated, 
	            referenceNo, 
	            otherReferences, 
	            termsOfDelivery, 
	            buyersOrderNo, 
	            buyersOrderDate, 
	            remarks, 
	            companyPan, 
	            paymentTerms, 
	            referenceNoDate, 
	            purchaseOrderId, 
	            quotationId, 
	            goodsDetails
	        );
	    }

	    
	    public class NumberToWordsConverter {

	        private static final String[] units = {
	            "", "One", "Two", "Three", "Four", "Five",
	            "Six", "Seven", "Eight", "Nine", "Ten",
	            "Eleven", "Twelve", "Thirteen", "Fourteen",
	            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
	        };

	        private static final String[] tens = {
	            "", "", "Twenty", "Thirty", "Forty", "Fifty",
	            "Sixty", "Seventy", "Eighty", "Ninety"
	        };

	        public static String convert(long number) {
	            if (number == 0) {
	                return "Zero";
	            }

	            StringBuilder result = new StringBuilder();

	            if ((number / 10000000) > 0) {
	                result.append(convert(number / 10000000)).append(" Crore ");
	                number %= 10000000;
	            }

	            if ((number / 100000) > 0) {
	                result.append(convert(number / 100000)).append(" Lakh ");
	                number %= 100000;
	            }

	            if ((number / 1000) > 0) {
	                result.append(convert(number / 1000)).append(" Thousand ");
	                number %= 1000;
	            }

	            if ((number / 100) > 0) {
	                result.append(convert(number / 100)).append(" Hundred ");
	                number %= 100;
	            }

	            if (number > 0) {
	                if (result.length() > 0) {
	                    result.append("and ");
	                }

	                if (number < 20) {
	                    result.append(units[(int) number]);
	                } else {
	                    result.append(tens[(int) number / 10]);
	                    if ((number % 10) > 0) {
	                        result.append(" ").append(units[(int) number % 10]);
	                    }
	                }
	            }

	            return result.toString().trim();
	        }
	    }

		public Dispatch saveDispatch(Dispatch dispatch) {
			if(dispatch.getReturnedQty() == null || dispatch.getReturnedQty().isEmpty() || dispatch.getReturnedQty() == "") {
				dispatch.setReturnedQty("0");
			}
		    return dispatchRepository.save(dispatch);
		}
		
	    public List<Dispatch> getAllDispatches() {
	        return dispatchRepository.findAllByOrderByIdDesc();
	    }
	    
	    public List<Dispatch_DC> getAllDCDispatches() {
	        return dispatchDCRepository.findAllByOrderByIdDesc();
	    }
	    
	    public long getTotalDispatches() {
			return dispatchRepository.getTotalModulesDispatchedSum();
		}
	    
	    public long getTotalReturned() {
			return dispatchRepository.getTotalModulesReturnedSum();
		}

		public Dispatch editDispatch(Dispatch dispatch) {
			 return dispatchRepository.save(dispatch);
		}
		
	 	public void sendEmailWithAttachment(String from, String to, String cc, String subject, String message, String password, byte[] pdfBytes, HttpSession session) throws MessagingException, IOException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        //String loggedInEmailIdValue = (String) session.getAttribute("email");
	        
	        helper.setFrom(from);
	        //helper.setTo(to);
	        
	        // Handling to dynamically (multiple or single email)
	        if (to != null && !to.trim().isEmpty()) {
	            String[] toArray = to.contains(",") ? to.split("\\s*,\\s*") : new String[]{to};
	            helper.setTo(toArray);
	        }
	        
	        // Handling CC dynamically (multiple or single email)
	        if (cc != null && !cc.trim().isEmpty()) {
	            String[] ccArray = cc.contains(",") ? cc.split("\\s*,\\s*") : new String[]{cc};
	            helper.setCc(ccArray);
	        }
	        
	        helper.setSubject(subject);
	        helper.setText(message);

	        // Attach PDF file
	        if(pdfBytes != null) {
	        	String timestamp = getFormattedDateTime();
	        	String fileName = "dispatched_details_" + timestamp + ".pdf";
	        	helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));
	        }
	        mailSender.send(mimeMessage);
	    }
	 	
	 	private String getFormattedDateTime() {
	 	    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	 	    return java.time.LocalDateTime.now().format(formatter);
	 	}

	 	public String updateRetunedDetailsData(String returnedDate, String returnedQty, long auto_id) {
	 		int result = dispatchRepository.updateRetunedDetails(returnedDate, returnedQty, auto_id);
	 		if(result > 0) {
	 			return "success";
	 		}else {
	 			return "failure";
	 		}
		}
	 	
	 	public Dispatch_DC saveDispatchDc(Dispatch_DC dispatch) {
		    return dispatchDCRepository.save(dispatch);
		}
	 	
	 	public void updatePriceDetails(String price, String total_price, String description, long id) {
	 		dispatchRepository.updatePriceDetails(price, total_price, description, id);
		}
	 	
}
