package com.example.mspl_connect.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.LinkGeneratedTableEntity;
import com.example.mspl_connect.AdminRepo.LinkGenerator_Repo;

import jakarta.transaction.Transactional;

@Service
public class LinkGenerator_Service {

	@Autowired
	private LinkGenerator_Repo linkGenerator_Repo;
	
	@Transactional
	 public String sendTODB(String url, String email, String test_duration, String supervisor_name, String test_type, String encrypted_data, String dept_name) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = LocalDate.now().format(formatter);
		
		LinkGeneratedTableEntity linkGenerator_Entity = new LinkGeneratedTableEntity();
        linkGenerator_Entity.setEncrypted_link(url);
        linkGenerator_Entity.setCandidateEmail(email);
        linkGenerator_Entity.setTest_duration(test_duration);
        linkGenerator_Entity.setSupervisor_name(supervisor_name);
        linkGenerator_Entity.setLinkCreatedDate(formattedDate); // Set the current date
        linkGenerator_Entity.setEncryptedData(encrypted_data);
        linkGenerator_Entity.setTest_type(test_type);
        linkGenerator_Entity.setDept_name(dept_name);
        linkGenerator_Entity.setTest_valid_flag("1");
        linkGenerator_Repo.save(linkGenerator_Entity);
		 return "success";
	 }
	
	
	public List<LinkGeneratedTableEntity> getAllLinkGeneratedData(){
		return linkGenerator_Repo.fetchAllLinkGeneratedData();
	}
}
