package com.example.mspl_connect.Sales_Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.PurchaseOrder;
import com.example.mspl_connect.Sales_Entity.PurchaseOrderItem;
import com.example.mspl_connect.Sales_Repository.PurchaseOrderRepository;

import jakarta.mail.internet.MimeMessage;


@Service
public class PurchaseOrderService {
	
	 @Autowired
	    private PurchaseOrderRepository purchaseOrderRepository;

	    public PurchaseOrder savePurchaseOrder(PurchaseOrder order) {
	        
	    	for (PurchaseOrderItem item : order.getItems()) {
	            item.setPurchaseOrder(order); // Set reference
	        }
	        
	        System.out.println("orderr----"+order);
	        return purchaseOrderRepository.save(order);
	        
	    } 
	 
	    /* public PurchaseOrder savePurchaseOrder(PurchaseOrder order) {

		    for (PurchaseOrderItem item : order.getItems()) {
		        item.setPurchaseOrder(order); // Set reference
		    }

		    // Save to database
		    PurchaseOrder savedOrder = purchaseOrderRepository.save(order);

		    // Only send mail after successful save (savedOrder should have ID set)
		    if (savedOrder != null && savedOrder.getId() != null) {
		        sendPurchaseOrderEmail(savedOrder);
		    }

		    return savedOrder;
		} */
	   
	 
	   
	   public List<PurchaseOrder> getAllPurchaseOrders() {
		    return purchaseOrderRepository.findAll();
		}
	   
	// Method to fetch purchase order by ID
	    public PurchaseOrder getPurchaseOrderById(Long purchaseId) {
	        // Use the repository to find the purchase order by its ID
	        return purchaseOrderRepository.findById(purchaseId)
	                .orElseThrow(() -> new RuntimeException("Purchase Order not found for ID: " + purchaseId));
	    }
	   
}
