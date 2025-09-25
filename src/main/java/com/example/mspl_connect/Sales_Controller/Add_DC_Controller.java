package com.example.mspl_connect.Sales_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mspl_connect.Sales_Repository.DeliveryChallanRepository;
import com.example.mspl_connect.Sales_Repository.IAdd_DeliveryChallan;

@Controller
public class Add_DC_Controller {

	@Autowired
	private DeliveryChallanRepository addDCRepository;
	
	@GetMapping("/dc/next-number")
	public ResponseEntity<Integer> getNextDCNumber() {
	    int next = addDCRepository.findMaxDeliveryNoteNo() + 1;
	    return ResponseEntity.ok(next);
	}

}
