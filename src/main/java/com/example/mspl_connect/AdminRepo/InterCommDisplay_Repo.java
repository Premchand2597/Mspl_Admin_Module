package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.InterCommDisplay_Entity;


@Repository
public interface InterCommDisplay_Repo extends JpaRepository<InterCommDisplay_Entity, Integer>{

	@Query(nativeQuery = true, value = """
			
			SELECT inter_comm_details.*, employee_details.employee_type FROM inter_comm_details left join employee_details on 
			employee_details.email = inter_comm_details.mail_id where (employee_details.employee_type IS NULL OR 
			employee_details.employee_type = '1') order by username
			
			""")
	List<InterCommDisplay_Entity> fetchAllInterCommDataBasedOnActiveEmployees();
}
