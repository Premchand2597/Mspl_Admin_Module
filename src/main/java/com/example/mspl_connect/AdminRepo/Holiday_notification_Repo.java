package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Holiday_notification_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Holiday_notification_Repo extends JpaRepository<Holiday_notification_Entity, Long>{

	@Query(nativeQuery = true, value="select emp_id from holiday_notifications where emp_id=:emp_id")
	String isEmpIdPresentInTheHolidayNotificationTable(@Param("emp_id") String emp_id);
	
	@Query(nativeQuery = true, value="select * from holiday_notifications where emp_id=:emp_id")
	Holiday_notification_Entity fetchDataBasedOnEmpId(@Param("emp_id") String emp_id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update holiday_notifications set flag=:flag, holiday_count=:holiday_count, holiday_year=:holiday_year where emp_id=:emp_id")
	void updateHolidayNotificationFlagValueByEmpId(@Param("emp_id") String emp_id, @Param("flag") String flag, @Param("holiday_count") long holiday_count, 
			@Param("holiday_year") String holiday_year);
}
