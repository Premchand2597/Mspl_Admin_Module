package com.example.mspl_connect.PayslipController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ImportExcel {

	@GetMapping("/readExcel")
	public String readExcel(){
		return "readExcelPage";
	}
	
	@GetMapping("/sendMail")
	public String sendMail(){
		return "sendMail";
	} 
	
}
