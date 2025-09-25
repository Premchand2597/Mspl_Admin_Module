package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.CompProjectCardDetails_Entity;
import com.example.mspl_connect.AdminRepo.CompProjectCardDetails_Repo;

@Service
public class CompProjectCardDetails_Service {

	@Autowired
	private CompProjectCardDetails_Repo compProjectCardDetails_Repo;
	
	public List<CompProjectCardDetails_Entity> getDetailsForCompCard(){
		return compProjectCardDetails_Repo.fetchProjectDetailsOnCard();
	}
}
