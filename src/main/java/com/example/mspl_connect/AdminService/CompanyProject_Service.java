package com.example.mspl_connect.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.CompanyProject_Entity;
import com.example.mspl_connect.AdminRepo.CompanyProject_Repo;

import jakarta.transaction.Transactional;

@Service
public class CompanyProject_Service {

	@Autowired
	 private CompanyProject_Repo companyProject_Repo;
	 
	 @Transactional
	 public String save(CompanyProject_Entity compProjectList) {
		 companyProject_Repo.save(compProjectList);
		 return "success";
	 }
	 
	 public List<CompanyProject_Entity> compProjectList(){
			return companyProject_Repo.fetchCompProjectsLists();
	}
	 
	 public CompanyProject_Entity fetchCompanyDataForEdit(String auto_id){
			return companyProject_Repo.ediCompProjectData(auto_id);
	}
	 
	 @Transactional
    public String updateCompProjectData(int id, String project_name, String description, String completed_date, String project_manager, String team_members, String department, String client) {
	 companyProject_Repo.update_compProject_details(id, project_name, description, completed_date, project_manager, team_members, department, client);
	 return "success";
	}
	 
	 @Transactional
		public String deleteCompProjectData(int id) {
		 companyProject_Repo.delete_companyProjectDetails(id);
		 return "success";
		}
}
