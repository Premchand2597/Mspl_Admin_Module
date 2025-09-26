package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    
//	@Query(value = "SELECT * FROM events_table WHERE YEAR(event_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
//	List<Event> findEventsForCurrentYear();
	
@Query(value = """
			
			SELECT * FROM events_table WHERE extract(YEAR from event_date::date) = extract(YEAR from CURRENT_DATE);
			
			""", nativeQuery = true)
	List<Event> findEventsForCurrentYear();
	
}
