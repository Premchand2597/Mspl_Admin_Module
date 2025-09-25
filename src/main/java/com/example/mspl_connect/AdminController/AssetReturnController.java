package com.example.mspl_connect.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mspl_connect.AdminEntity.AssetReturn;
import com.example.mspl_connect.AdminRepo.AssetRepository;
import com.example.mspl_connect.AdminRepo.AssignedAssetsRepo;
import com.example.mspl_connect.AdminService.AssetReturnService;

@RestController
@RequestMapping("/api/asset-returns")
public class AssetReturnController {

	  @Autowired
	    private AssetReturnService assetReturnservice;

	    @Autowired
	    private AssetRepository assetRepository;
	    
	    
	    @Autowired
	    private  AssignedAssetsRepo AssignedAssetDetailsRepo;
	    
	    @GetMapping
	    public List<AssetReturn> getAllRequests() {
	        return assetReturnservice.getAllRequests();
	    }

	   /* @PostMapping("/{id}/approve")
	    public AssetReturn approveRequest(@PathVariable int id) {
	        return assetReturnservice.approveRequest(id);
	    }*/

	    @PostMapping("/{id}/approve")
	    public AssetReturn approveRequest(
	            @PathVariable int id,
	            @RequestParam String empId) {
	        return assetReturnservice.approveRequest(id, empId);
	    }


	    @PostMapping("/{id}/reject")
	    public AssetReturn rejectRequest(@PathVariable int id, @RequestBody String remarks) {
	        return assetReturnservice.rejectRequest(id, remarks);
	    }
}
