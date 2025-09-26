package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminRepo.FeatureUpdatesRepo;

import jakarta.transaction.Transactional;

@Service
public class FeatureUpdatesService {
	
	@Autowired
	private FeatureUpdatesRepo featureUpdatesRepo;
	

	@Transactional
	public void updateNewFeatureFlagValue(String empId) {
	       // Update the request_value for the given assetId
		featureUpdatesRepo.updateAdminFlag(empId);
	}

}
