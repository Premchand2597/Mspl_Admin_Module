package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalNotificationAndValidationForHR_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface AppraisalNotificationAndValidationForHR_Repo extends JpaRepository<AppraisalNotificationAndValidationForHR_Entity, Integer>{

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update apprisal_notification_table_for_hr set quarter_flag = 1, quarter_send_enabled_btn_flag = 1 where id=cast(:auto_id as integer)")
	int updateQuarterFlagAndQuarterBtnEnableFlag(@Param("auto_id") String auto_id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update apprisal_notification_table_for_hr set quarter_flag = 0, quarter_send_enabled_btn_flag = 0")
	int updateQuarterFlagAndQuarterBtnEnableFlagAfterEnablingTheAppraisalLink();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update apprisal_notification_table_for_hr set appraisal_sent_year = :currYear where id=cast(:auto_id as integer)")
	int updateQuarterFlagAndQuarterBtnEnableFlagAfterInsertingAppraisalDetails(@Param("currYear") String currYear, @Param("auto_id") String auto_id);
}
