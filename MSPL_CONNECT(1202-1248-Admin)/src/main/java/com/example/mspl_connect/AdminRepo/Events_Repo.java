package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Event_Entity;

import jakarta.transaction.Transactional;


@Repository
public interface Events_Repo extends JpaRepository<Event_Entity, Integer>{

	@Query(nativeQuery = true, value="Select * from events_table where id=:id")
	Event_Entity fetchEventsDataBasedOnId(@Param("id") String id);
	
	/*@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update events_table set announcement_converted_flag = 0 where id=:announcement_id")
	void updateAnnouncementConvertedToEventsFlag(@Param("announcement_id") String announcement_id);*/
	
	@Query(nativeQuery = true, value="select count(flag) from announcement_notifications where flag = 1 and emp_id=:empid")
	Integer countAnnouncementFromAnnouncementNotiByEmpid(String empid);
	
	
	
}
