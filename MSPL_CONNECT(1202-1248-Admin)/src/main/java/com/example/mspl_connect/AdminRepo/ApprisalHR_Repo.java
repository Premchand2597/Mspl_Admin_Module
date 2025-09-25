package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ApprisalHR_Repo extends JpaRepository<AppraisalHrEntity, Long>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update apprisal_hr set apprisal_email_send_flag = 1 where id=:auto_id")
	void updateEmailSendFlagAfterSendingTheEmailToAdmin(@Param("auto_id") String auto_id);
	
}
