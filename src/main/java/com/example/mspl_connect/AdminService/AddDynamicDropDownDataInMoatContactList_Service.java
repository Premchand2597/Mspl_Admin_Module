package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AddDynamicDropDownDataInMoatContactList_Entity;
import com.example.mspl_connect.AdminRepo.AddDynamicDropDownDataInMoatContactList_Repo;

import jakarta.transaction.Transactional;

@Service
public class AddDynamicDropDownDataInMoatContactList_Service {

	@Autowired
	private AddDynamicDropDownDataInMoatContactList_Repo addDynamicDropDownDataInMoatContactList_Repo;
	
	public boolean addDropDownData(String dropDownStatusNumber, String dropDownValue) {
		int rowsAffected = addDynamicDropDownDataInMoatContactList_Repo.saveDropDownData(dropDownStatusNumber, dropDownValue);
        return rowsAffected > 0;
	}
	
	@Transactional
	public List<AddDynamicDropDownDataInMoatContactList_Entity> fetchAllDropDownData(){
		return addDynamicDropDownDataInMoatContactList_Repo.fetchAllDetails();
	}
	
	@Transactional
	 public boolean dropDownDataAlreadyExistsInTable(String dropDownStatusNumber, String dropDownValue) {
		AddDynamicDropDownDataInMoatContactList_Entity isPresent = addDynamicDropDownDataInMoatContactList_Repo.isDataAlreadyExistsInTable(dropDownStatusNumber, dropDownValue);
		if(isPresent == null) {
			return false;
		}else {
			return true;
		}
	 }
}
