package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Event_Entity;
import com.example.mspl_connect.Entity.Event;

import jakarta.transaction.Transactional;


@Repository
public interface AddEvents_Repo extends JpaRepository<Event, String>{

	@Query(nativeQuery = true, value="Select * from events_table where id=:id")
	Event fetchEventsDataBasedOnId(@Param("id") String id);
	
	/*@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update events_table set announcement_converted_flag = 0 where id=:announcement_id")
	void updateAnnouncementConvertedToEventsFlag(@Param("announcement_id") String announcement_id);*/
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update events_table set announcement_converted_flag = 0 where id=cast(:announcement_id as integer)")
	void updateAnnouncementConvertedToEventsFlag(@Param("announcement_id") String announcement_id);
	
	@Query(nativeQuery = true, value="select count(flag) from announcement_notifications where flag = 1 and emp_id=:empid")
	Integer countAnnouncementFromAnnouncementNotiByEmpid(String empid);
	
//	@Modifying
//    @Transactional
//    @Query(value = "update events_table set event_title=:announcementTitle, event_date=:date, from_time=:fromTime, to_time=:toTime, event_location=:location, event_pic=:announcementPicPath where id=:id", nativeQuery = true)
//    int updateAnnouncementDetailsData(@Param("id") String id, @Param("announcementTitle") String announcementTitle, @Param("date") String date, 
//    		@Param("fromTime") String fromTime, @Param("toTime") String toTime, @Param("location") String location, 
//    		@Param("announcementPicPath") String announcementPicPath);
	
	@Modifying
    @Transactional
    @Query(value = "update events_table set event_title=:announcementTitle, event_date=:date, from_time=:fromTime, to_time=:toTime, event_location=:location, event_pic=:announcementPicPath where id=cast(:id as integer)", nativeQuery = true)
    int updateAnnouncementDetailsData(@Param("id") String id, @Param("announcementTitle") String announcementTitle, @Param("date") String date, 
    		@Param("fromTime") String fromTime, @Param("toTime") String toTime, @Param("location") String location, 
    		@Param("announcementPicPath") String announcementPicPath);
	
}
