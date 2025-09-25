package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EventConverted_Entity;



@Repository
public interface EventsConverted_Repo extends JpaRepository<EventConverted_Entity, Integer>{

	@Query(nativeQuery = true, value="Select * from event_converted_table where id=:id")
	EventConverted_Entity fetchEventsDataBasedOnId(@Param("id") String id);
	
	@Query(nativeQuery = true, value="select * from event_converted_table order by event_date desc")
	List<EventConverted_Entity> fetchAllEventsDataBasedRecentlyCompleted();
}
