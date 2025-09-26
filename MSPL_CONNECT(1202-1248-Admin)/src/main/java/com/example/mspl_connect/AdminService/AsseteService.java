package com.example.mspl_connect.AdminService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.AssetDisplayEnityt;
import com.example.mspl_connect.AdminEntity.AsseteEntity;
import com.example.mspl_connect.AdminRepo.AssetDisplayRepo;
import com.example.mspl_connect.AdminRepo.AssetRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class AsseteService {
	
	@Autowired
	private AssetRepo assetRepo;
	
	@Autowired
	private AssetDisplayRepo assetDisplayRepo;
	
	public List<AsseteEntity> getAssetById(String empId){
		return assetRepo.findByEmpid(empId);
	}
	
	public AsseteEntity saveAsset(AsseteEntity assetEntity,int adminDept) {
		  assetEntity.setDept(adminDept);
		  assetEntity.setRequest_value(1);
        return assetRepo.save(assetEntity);
    }

	public List<AsseteEntity> getAssetByDeptId(int adminDept,String empid) {
		return assetRepo.findByDeptAndEmpidNot(adminDept,empid);
	}
	 
	public List<AsseteEntity> getAllAsset(String empid) {
		return assetRepo.findByEmpidNot(empid);
	}
	
	/*
	 * public List<AssetDisplayEnityt> getAllUsersAsset(Integer deptId) {
	 * List<AssetDisplayEnityt> assetes=assetDisplayRepo.findByEmpidNot(deptId);
	 * 
	 * return assetes; }
	 */
	public List<AssetDisplayEnityt> getAllUsersAsset(Integer deptId) {
	    List<AssetDisplayEnityt> assets = assetDisplayRepo.findByEmpidNot(deptId);

	    // Define the expected input and output date formats
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

	    // Iterate over each asset to format the dates
	    for (AssetDisplayEnityt asset : assets) {
	        try {
	            // Convert received_date
	            if (asset.getReceived_date() != null) {
	                Date receivedDate = inputFormat.parse(asset.getReceived_date()); // Parse from String to Date
	                asset.setReceived_date(outputFormat.format(receivedDate));       // Format Date to dd-MM-yyyy
	            }

	            // Convert return_date
	            if (asset.getReturn_date() != null) {
	                Date returnDate = inputFormat.parse(asset.getReturn_date());     // Parse from String to Date
	                asset.setReturn_date(outputFormat.format(returnDate));           // Format Date to dd-MM-yyyy
	            }

	            // Convert available_date
	            if (asset.getAvailable_date() != null) {
	                Date availableDate = inputFormat.parse(asset.getAvailable_date()); // Parse from String to Date
	                asset.setAvailable_date(outputFormat.format(availableDate));       // Format Date to dd-MM-yyyy
	            }
	        } catch (ParseException e) {
	            e.printStackTrace(); // Log any errors that occur during date parsing
	        }
	    }

	    System.out.println("assets......." + assets);
	    return assets;
	}
	
	@Transactional
	public void updateRequestValue(int assetId, int newValue) {
	       // Update the request_value for the given assetId
	       assetRepo.updateRequestValue(assetId, newValue);
	}

	public List<AssetDisplayEnityt> getAllAssetForSA() {
		 List<AssetDisplayEnityt> assets = assetDisplayRepo.findAllAssetsForSA();
		    // Define the expected input and output date formats
		    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

		    // Iterate over each asset to format the dates
		    for (AssetDisplayEnityt asset : assets) {
		        try {
		            // Convert received_date
		            if (asset.getReceived_date() != null) {
		                Date receivedDate = inputFormat.parse(asset.getReceived_date()); // Parse from String to Date
		                asset.setReceived_date(outputFormat.format(receivedDate));       // Format Date to dd-MM-yyyy
		            }

		            // Convert return_date
		            if (asset.getReturn_date() != null) {
		                Date returnDate = inputFormat.parse(asset.getReturn_date());     // Parse from String to Date
		                asset.setReturn_date(outputFormat.format(returnDate));           // Format Date to dd-MM-yyyy
		            }

		            // Convert available_date
		            if (asset.getAvailable_date() != null) {
		                Date availableDate = inputFormat.parse(asset.getAvailable_date()); // Parse from String to Date
		                asset.setAvailable_date(outputFormat.format(availableDate));       // Format Date to dd-MM-yyyy
		            }
		        } catch (ParseException e) {
		            e.printStackTrace(); // Log any errors that occur during date parsing
		        }
		    }

		    System.out.println("assets......." + assets);
		    return assets;
	}

	public void updateAssetAvailable(int assetId, int i) {
		   // Update the request_value for the given assetId
	       assetRepo.updateAssetAvailable(assetId, i);
	}

	public List<AssetDisplayEnityt> getAllAssetForStore() {
		 List<AssetDisplayEnityt> assets =assetDisplayRepo.findAllAssetsForStore();
		// Define the expected input and output date formats
		    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

		    // Iterate over each asset to format the dates
		    for (AssetDisplayEnityt asset : assets) {
		        try {
		            // Convert received_date
		            if (asset.getReceived_date() != null) {
		                Date receivedDate = inputFormat.parse(asset.getReceived_date()); // Parse from String to Date
		                asset.setReceived_date(outputFormat.format(receivedDate));       // Format Date to dd-MM-yyyy
		            }

		            // Convert return_date
		            if (asset.getReturn_date() != null) {
		                Date returnDate = inputFormat.parse(asset.getReturn_date());     // Parse from String to Date
		                asset.setReturn_date(outputFormat.format(returnDate));           // Format Date to dd-MM-yyyy
		            }

		            // Convert available_date
		            if (asset.getAvailable_date() != null) {
		                Date availableDate = inputFormat.parse(asset.getAvailable_date()); // Parse from String to Date
		                asset.setAvailable_date(outputFormat.format(availableDate));       // Format Date to dd-MM-yyyy
		            }
		        } catch (ParseException e) {
		            e.printStackTrace(); // Log any errors that occur during date parsing
		        }
		    }
		    return assets;
	}

	public void updateAssetDelay(int assetId, String availableDate) {
		// Update the request_value for the given assetId
	       assetRepo.updateAssetDelay(assetId,availableDate,4);
	}

	
}
