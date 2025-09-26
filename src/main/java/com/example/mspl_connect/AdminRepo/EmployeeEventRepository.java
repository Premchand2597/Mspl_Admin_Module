package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EmployeeEvent;


@Repository
public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Integer>{
	
	@Query(nativeQuery = true, value="select * from event_converted_table order by event_date desc")
	List<EmployeeEvent> fetchAllEventsDataBasedRecentlyCompleted();


}
