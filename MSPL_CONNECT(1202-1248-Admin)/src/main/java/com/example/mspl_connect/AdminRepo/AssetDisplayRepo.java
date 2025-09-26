package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.AdminEntity.AssetDisplayEnityt;
import com.example.mspl_connect.AdminEntity.AsseteEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AssetDisplayRepo extends JpaRepository<AssetDisplayEnityt, Integer> {
	 
	 @Query(nativeQuery = true,value="select a.id, a.empid, a.asset, a.received_date, a.return_date, a.quantity, a.description, a.dept,a.request_value,a.available_date,ed.f_name as empname FROM assets a inner join employee_details ed on a.empid=ed.empid where a.dept=:deptId ")
	 List<AssetDisplayEnityt> findByEmpidNot(int deptId);
	 
	 @Query(nativeQuery = true,value="select a.id, a.empid, a.asset, a.received_date, a.return_date, a.quantity, a.description, a.dept,a.request_value,a.available_date,ed.f_name as empname FROM assets a inner join employee_details ed on a.empid=ed.empid")
	 List<AssetDisplayEnityt> findAllAssetsForSA();
	 
	 @Query(nativeQuery = true,value="select a.id, a.empid, a.asset, a.received_date, a.return_date, a.quantity, a.description, a.dept,a.request_value,a.available_date,ed.f_name as empname FROM assets a inner join employee_details ed on a.empid=ed.empid where a.request_value=2")
	 List<AssetDisplayEnityt> findAllAssetsForStore();
	 
}
