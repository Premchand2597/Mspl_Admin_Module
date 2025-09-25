package com.example.mspl_connect.DispatchRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.DispatchEntity.TransportMode_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface TransportMode_Repo extends JpaRepository<TransportMode_Entity, Long>{

	@Query(nativeQuery = true, value = "select distinct name, id from transport_mode order by id desc")
	List<TransportMode_Entity> fetchDistinctTransportMode();
	
	@Query(nativeQuery = true, value = "select name from transport_mode where name=:data")
	String getTransportModeDataExistsOrNot(@Param("data") String data);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from transport_mode where name=:data")
	void deleteTransportModeData(@Param("data") String data);
}
