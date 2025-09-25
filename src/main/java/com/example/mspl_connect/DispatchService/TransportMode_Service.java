package com.example.mspl_connect.DispatchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.TransportMode_Entity;
import com.example.mspl_connect.DispatchRepo.TransportMode_Repo;


@Service
public class TransportMode_Service {

	@Autowired
	private TransportMode_Repo transportMode_Repo;
	
	public String addTransportMode(TransportMode_Entity transportMode_Entity) {
		transportMode_Repo.save(transportMode_Entity);
		return "success";
	}
	
	public boolean isTransportModeExists(String data) {
		String existsData = transportMode_Repo.getTransportModeDataExistsOrNot(data);
		if(existsData != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<TransportMode_Entity> getAllDistinctTransportMode() {
		return transportMode_Repo.fetchDistinctTransportMode();
	}
	
	public void deleteTransportModeDataBasedOnName(String name) {
		transportMode_Repo.deleteTransportModeData(name);
	}
	
}
