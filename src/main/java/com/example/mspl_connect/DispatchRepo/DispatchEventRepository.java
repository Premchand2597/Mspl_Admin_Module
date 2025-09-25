package com.example.mspl_connect.DispatchRepo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.DispatchEntity.DispatchEvent;


@Repository
public interface DispatchEventRepository extends JpaRepository<DispatchEvent, Long> {
	  //List<Event> findByScheduledDate(LocalDate scheduledDate);
	
	@Query(nativeQuery = true, value="select * from dispatch_events where scheduled_date=:date")
	List<DispatchEvent> findEventsBasedOnDate(@Param("date") String date);
	
	@Query(nativeQuery = true, value="SELECT COUNT(id) FROM dispatch_events WHERE scheduled_date = TO_CHAR(CURRENT_DATE, 'Dy Mon DD YYYY')")
	long getTodayEventCounts();
	
	@Query(nativeQuery = true, value="select * from dispatch_events where scheduled_date = TO_CHAR(CURRENT_DATE, 'Dy Mon DD YYYY')")
	List<DispatchEvent> findByScheduledDate();
}
