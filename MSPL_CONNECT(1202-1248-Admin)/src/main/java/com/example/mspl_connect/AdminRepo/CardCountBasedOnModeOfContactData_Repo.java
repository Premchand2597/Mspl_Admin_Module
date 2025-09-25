package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CardCountBasedOnModeOfContactData_Entity;


@Repository
public interface CardCountBasedOnModeOfContactData_Repo extends JpaRepository<CardCountBasedOnModeOfContactData_Entity, String>{

	@Query(nativeQuery = true, value="select sum(case when remarks = 'Selected' then 1 else 0 end) as mode_of_contact_select, sum(case when remarks = 'Rejected' then 1 else 0 end) as mode_of_contact_reject, sum(case when remarks = 'Onhold' then 1 else 0 end) as mode_of_contact_onhold, sum(case when (remarks <> 'Selected' and remarks <> 'Rejected' and remarks <> 'Onhold') then 1 else 0 end) as mode_of_contact_pending from moat_candidate_mode_of_contact")
	CardCountBasedOnModeOfContactData_Entity fetchModeOfContactAllTypeOfStatusCountForCards();
	
}
