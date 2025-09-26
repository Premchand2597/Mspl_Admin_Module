package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.SubDeptName_Entity;
import com.example.mspl_connect.AdminRepo.SubDeptName_Repo;


@Service
public class SubDeptName_Service {

	@Autowired
	private SubDeptName_Repo subDeptName_Repo;
	
	public List<SubDeptName_Entity> getSubDeptNamesByUsingDeptId(int dept_id){
		return subDeptName_Repo.fetchSubDeptNameByDept(dept_id);
	}
}
