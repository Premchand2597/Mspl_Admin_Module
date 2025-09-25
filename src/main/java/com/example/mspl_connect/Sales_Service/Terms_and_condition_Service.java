package com.example.mspl_connect.Sales_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;
import com.example.mspl_connect.Sales_Repository.Terms_and_condition_Repo;


@Service
public class Terms_and_condition_Service {

	@Autowired
	private Terms_and_condition_Repo terms_and_condition_Repo;
	
	public void insertTermsAndConditionData(Terms_and_condition_Entity terms_and_condition_Entity) {
		terms_and_condition_Repo.save(terms_and_condition_Entity);
	}
	
	public Terms_and_condition_Entity getRecentInsertedTermsAndConditionDetail() {
		return terms_and_condition_Repo.fetchRecentInsertedTermsAndConditionDetail();
	}

	public Terms_and_condition_Entity getRecentInsertedTermsAndConditionDetailNew(String qId) {
		 
		Terms_and_condition_Entity terms = terms_and_condition_Repo.fetchRecentInsertedTermsAndConditionDetailNew(qId.trim());
		 
		return terms;
	}
	
}
