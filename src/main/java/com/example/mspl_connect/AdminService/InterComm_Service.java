package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.InterComm_Entity;
import com.example.mspl_connect.AdminRepo.Inter_Comm_Repo;

import jakarta.transaction.Transactional;

@Service
public class InterComm_Service {

	 @Autowired
	 private Inter_Comm_Repo inter_Comm_Repo;
	 
	 @Transactional
	 public String save(InterComm_Entity interCommList) {
		 inter_Comm_Repo.save(interCommList);
		 return "success";
	 }
	 
	public List<InterComm_Entity> interCommList(){
		return inter_Comm_Repo.getInterCommDetails();
	}
	
	
	public InterComm_Entity fetchInterCommDataForEdit(String auto_id){
		return inter_Comm_Repo.editInterCommData(auto_id);
	}
	
	@Transactional
    public String updateInterCommData(int id, String username, String cell_number, String mail_id, String tele_number, String cubical_number, String seat_place, String floor_number, String room_number) {
		inter_Comm_Repo.update_InterComm_details(id, username, cell_number, mail_id, tele_number, cubical_number, seat_place, floor_number, room_number);
		return "success";
	}
	
	@Transactional
	public String deleteInterCommData(int id) {
		inter_Comm_Repo.delete_InterCommDetails(id);
		return "success";
	 
	}
}
