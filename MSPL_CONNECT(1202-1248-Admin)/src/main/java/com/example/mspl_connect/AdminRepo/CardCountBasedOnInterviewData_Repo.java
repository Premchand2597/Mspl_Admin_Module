package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.CardCountBasedOnInterviewData_Entity;

@Repository
public interface CardCountBasedOnInterviewData_Repo extends JpaRepository<CardCountBasedOnInterviewData_Entity, String>{

	@Query(nativeQuery = true, value="select sum(case when action = 'approv' then 1 else 0 end) as interview_select, sum(case when action = 'reject' then 1 else 0 end) as interview_reject, sum(case when action = 'onhold' then 1 else 0 end) as interview_onhold, sum(case when (action is null or action = '') then 1 else 0 end) as interview_pending from student")
	CardCountBasedOnInterviewData_Entity fetchInterviewAllTypeOfStatusCountForCards();
}
