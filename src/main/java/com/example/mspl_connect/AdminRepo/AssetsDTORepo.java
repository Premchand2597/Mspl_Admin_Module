package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.assetsDTO;

@Repository
public interface AssetsDTORepo extends JpaRepository<assetsDTO, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT ar.*, \r\n"
			+ "    CONCAT(requester.f_name, ' ', requester.l_name) AS emp_name,\r\n"
			+ "    CONCAT(approver.f_name, ' ', approver.l_name) AS approver_name\r\n"
			+ "FROM \r\n"
			+ "    assets_request ar\r\n"
			+ "INNER JOIN \r\n"
			+ "    employee_details requester ON requester.empid = ar.emp_id\r\n"
			+ "LEFT JOIN \r\n"
			+ "    employee_details approver ON approver.empid = ar.requested_by\r\n"
			+ "WHERE \r\n"
			+ "    ar.quantity > ar.assigned_asset_qty;") 
	List<assetsDTO> findAll();
	//select ar.*,concat(ed.f_name,' ',ed.l_name) as emp_name from assets_request ar join employee_details ed on ar.emp_id = ed.empid where (ed.team_co_name='MS122' or ed.team_lead_name='MS122') and ar.quantity > ar.assigned_asset_qty
	
}
