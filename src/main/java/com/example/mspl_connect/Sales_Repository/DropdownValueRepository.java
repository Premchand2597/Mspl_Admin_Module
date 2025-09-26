package com.example.mspl_connect.Sales_Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mspl_connect.Sales_Entity.DropdownValueEntity;

public interface DropdownValueRepository extends JpaRepository<DropdownValueEntity, Long>{
	List<DropdownValueEntity> findByType(String type);
}
