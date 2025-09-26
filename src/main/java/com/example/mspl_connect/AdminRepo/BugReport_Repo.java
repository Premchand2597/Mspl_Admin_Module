package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.BugReport_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface BugReport_Repo extends JpaRepository<BugReport_Entity, Long>{

	@Query(nativeQuery = true, value = "select id from bug_report order by auto_id desc limit 1")
	String fetchRecentTicketId();
	
	@Query(nativeQuery = true, value = "select * from bug_report where bug_status=:clickedTab order by auto_id desc")
	List<BugReport_Entity> fetchFeedbackDataBasedOnStatus(@Param("clickedTab") String clickedTab);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update bug_report set bug_status=:feedback_status, bug_replied_on=:feedback_replied_date_and_time where auto_id=:auto_id")
//	void updateRepliedFeedbackStatus(@Param("auto_id") String auto_id, @Param("feedback_status") String feedback_status, 
//			@Param("feedback_replied_date_and_time") String feedback_replied_date_and_time);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update bug_report set bug_status=:feedback_status, bug_replied_on=:feedback_replied_date_and_time where auto_id=cast(:auto_id as integer)")
	void updateRepliedFeedbackStatus(@Param("auto_id") String auto_id, @Param("feedback_status") String feedback_status, 
			@Param("feedback_replied_date_and_time") String feedback_replied_date_and_time);
}
