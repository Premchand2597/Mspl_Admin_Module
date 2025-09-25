package com.example.mspl_connect.DispatchService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.Dispatch;
import com.example.mspl_connect.DispatchEntity.Dispatch_DC;
import com.example.mspl_connect.DispatchRepo.DispatchDCRepository;
import com.example.mspl_connect.DispatchRepo.DispatchRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

/*@Service
public class DispatchService {
	
	@Autowired
	private DispatchRepository dispatchRepository;
	
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
	private DispatchDCRepository dispatchDCRepository;
	
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
    
}*/