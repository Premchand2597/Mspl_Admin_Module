package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

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
	
	@Query(nativeQuery = true, value = "Select * from apprisal_hr where emp_id=:empId and apprisal_link_flag=:apprisalLinkFlag ORDER BY apprisal_submit_date_admin DESC LIMIT 1")
	Optional<AppraisalHrEntity> getAllDataUsingEmpIdAndAppraislaLinkFlag(String empId, Boolean apprisalLinkFlag);
	
	@Query(nativeQuery = true, value = "select emp_id, apprisal_link_flag from apprisal_hr where quarter_month_year = :quarterMonthAndYear")
	List<Object[]> getAppraisalSubmittedAndNotSubmittedEmpIdWithFlagValue(@Param("quarterMonthAndYear") String quarterMonthAndYear);
	
	@Query(nativeQuery = true, value = "SELECT * FROM apprisal_hr WHERE emp_id = :emp_id AND (quarter_month_year LIKE :firstChoiceYear OR quarter_month_year LIKE :secondChoiceYear OR quarter_month_year LIKE :thirdChoiceYear OR quarter_month_year LIKE :fourthChoiceYear)")
    List<AppraisalHrEntity> fetchAllAppraisalSentByHRDataBasedOnEmpIdAndYear(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);
    
    @Modifying
	@Transactional
	@Query(nativeQuery = true, value="update apprisal_hr set apprisal_link_flag = 0 where emp_id=:emp_id")
	void resetAppraisalSubmittedFlagValueForHR(@Param("emp_id") String emp_id);
}
