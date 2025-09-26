package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalSubmittedNotSubmittedCount_Entity;


@Repository
public interface AppraisalSubmittedNotSubmittedCount_Repo extends JpaRepository<AppraisalSubmittedNotSubmittedCount_Entity, String>{
//	@Query(nativeQuery = true, value="select count(apprisal_hr.email) as total_count, COALESCE(sum(case when permissions.apprisal_link = 0 and apprisal_hr.apprisal_link_flag = \"0\" then 1 else 0 end), 0) as submitted_count, COALESCE(sum(case when permissions.apprisal_link <> 0 or apprisal_hr.apprisal_link_flag <> \"0\" then 1 else 0 end), 0) as not_submitted_count from apprisal_hr inner join permissions on permissions.emp_id = apprisal_hr.emp_id where apprisal_hr.quarter_month_year = :clickedQuarterMonthYear")
//	AppraisalSubmittedNotSubmittedCount_Entity fetchAppraisalSubmittedNotSubmittedAndTotalCountValue(@Param("clickedQuarterMonthYear") String clickedQuarterMonthYear);

	@Query(nativeQuery = true, value="""
			
select count(apprisal_hr.email) as total_count, COALESCE(sum(case when permissions.apprisal_link = false and apprisal_hr.apprisal_link_flag = '0' 
then 1 else 0 end), 0) as submitted_count, COALESCE(sum(case when permissions.apprisal_link <> false or apprisal_hr.apprisal_link_flag <> '0' then 1 
else 0 end), 0) as not_submitted_count from apprisal_hr inner join permissions on permissions.emp_id = apprisal_hr.emp_id where 
apprisal_hr.quarter_month_year = :clickedQuarterMonthYear;
			
			""")
	AppraisalSubmittedNotSubmittedCount_Entity fetchAppraisalSubmittedNotSubmittedAndTotalCountValue(@Param("clickedQuarterMonthYear") String clickedQuarterMonthYear);
}
