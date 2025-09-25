package com.example.mspl_connect.Sales_Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.Sales_Service.DropdownValueService;

@RestController
@RequestMapping("/api/dropdown")
public class DropdownValueController {
	 @Autowired
	    private DropdownValueService service;

	    @GetMapping("/{type}")
	    public List<String> getDropdownValues(@PathVariable String type) {
	        return service.getValuesByType(type);
	    }

	    @PostMapping("/add")
	    public ResponseEntity<?> addDropdownValue(@RequestParam String type, @RequestParam String value) {
	        return ResponseEntity.ok(service.addValue(type, value));
	    }
}
