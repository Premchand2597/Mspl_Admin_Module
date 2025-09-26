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
public interface AssetRepo extends JpaRepository<AsseteEntity, Integer> {

	 List<AsseteEntity> findByEmpid(String empId);
	
	 List<AsseteEntity> findByDeptAndEmpidNot(Integer dept, String empid);
	 
	 @Query(nativeQuery = true,value= "SELECT count(*) FROM assets where request_value=1")
	 int findByRequestValue();
	 
	 
	 @Query(nativeQuery = true,value= "SELECT count(*) FROM mspl_connect.assets a  inner join employee_details e on a.empid = e.empid where a.request_value=1 and (e.team_lead_name = :empid or e.team_co_name=:empid)")
	 int findByRequestValueAndDeptId(String empid);
	 
	 @Query(nativeQuery = true,value="select a.id, a.empid, a.asset, a.received_date, a.return_date, a.quantity, a.description, a.dept,ed.f_name as empName FROM assets a inner join employee_details ed on a.empid=ed.empid where a.empid != :empid ")
	 List<AsseteEntity> findByEmpidNot(String empid);
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE AsseteEntity a SET a.request_value = :newValue WHERE a.id = :assetId")
	 void updateRequestValue(@Param("assetId") int assetId, @Param("newValue") int newValue);
	 
	 @Modifying
	 @Transactional
	 @Query(value="UPDATE mspl_connect.assets a SET a.request_value = :i WHERE a.id = :assetId",nativeQuery = true)
	 void updateAssetAvailable(int assetId, int i);

	 @Modifying
	 @Transactional
	 @Query(value="UPDATE mspl_connect.assets a SET a.request_value = :i,available_date=:availableDate WHERE a.id = :assetId",nativeQuery = true)
	 void updateAssetDelay(int assetId, String availableDate,int i);
	 
}
