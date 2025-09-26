package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Repo extends JpaRepository<AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingInsert_Entity, Long>{

	@Modifying
    @Transactional
	@Query(nativeQuery = true, value="insert into yearwise_overall_performance_ratings(financial_year, emp_id, max_score, allotted_score, overall_percentage, overall_rating) values(:financial_year, :emp_id, :max_score, :allotted_score, :overall_percentage, :overall_ratings)")
	void saveAllAprraisalData(@Param("emp_id") String emp_id, @Param("financial_year") String financial_year, @Param("max_score") String max_score, 
			@Param("allotted_score") String allotted_score, @Param("overall_percentage") String overall_percentage, @Param("overall_ratings") String overall_ratings);
}
