package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.DisplayEmployessEntity;
import com.example.mspl_connect.Entity.DisplayEmployessWithMissPunchEntity;

import jakarta.transaction.Transactional;

@Repository
public interface DisplayEmployeeWithMisspunchRepo extends JpaRepository<DisplayEmployessWithMissPunchEntity, Integer>{

	@Procedure(name="employees_data_with_misspunch_for_admin")
	@Query(nativeQuery = true, value="call employees_data_with_misspunch_for_admin(:loggedAdminEmpid)")
	public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_admin(String loggedAdminEmpid);
	
	@Procedure(name="employees_data_with_misspunch_for_superadmin")
	@Query(nativeQuery = true, value="call employees_data_with_misspunch_for_superadmin(:loggedAdminEmpid)")
	public List<DisplayEmployessWithMissPunchEntity> employees_data_with_misspunch_for_superadmin(String loggedAdminEmpid);
}
