package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AddDynamicDropDownDataInMoatContactList_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface AddDynamicDropDownDataInMoatContactList_Repo extends JpaRepository<AddDynamicDropDownDataInMoatContactList_Entity, Long>{

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into dynamic_dropdown_data_for_moat_contact_list(status_number, data_value) values(:dropDownStatusNumber, :dropDownValue)")
	int saveDropDownData(@Param("dropDownStatusNumber") String dropDownStatusNumber, @Param("dropDownValue") String dropDownValue);
	
	@Transactional
	@Query(nativeQuery = true, value="Select * from dynamic_dropdown_data_for_moat_contact_list order by data_value")
	List<AddDynamicDropDownDataInMoatContactList_Entity> fetchAllDetails();
	
	@Query(nativeQuery = true, value="select * from dynamic_dropdown_data_for_moat_contact_list where status_number=:dropDownStatusNumber and data_value=:dropDownValue")
	AddDynamicDropDownDataInMoatContactList_Entity isDataAlreadyExistsInTable(@Param("dropDownStatusNumber") String dropDownStatusNumber, @Param("dropDownValue") String dropDownValue);
}
