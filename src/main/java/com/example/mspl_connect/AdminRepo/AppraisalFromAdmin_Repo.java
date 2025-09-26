package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalFromAdmin_Entity;


@Repository
public interface AppraisalFromAdmin_Repo extends JpaRepository<AppraisalFromAdmin_Entity, Long>{
	
	@Query(nativeQuery = true, value = "SELECT apprisal_admin_evaluated.*,apprisal_by_admin.id as user_id, apprisal_by_admin.project_planning_points as user_project_planning_points, apprisal_by_admin.team_leadership_points as user_team_leadership_points, apprisal_by_admin.stakeholder_comm_points as user_stakeholder_comm_points, apprisal_by_admin.budget_cost_points as user_budget_cost_points, apprisal_by_admin.risk_management_points as user_risk_management_points, apprisal_by_admin.quality_assurance_points as user_quality_assurance_points, apprisal_by_admin.total_score as user_total_score FROM apprisal_admin_evaluated left join apprisal_by_admin on apprisal_by_admin.emp_id = apprisal_admin_evaluated.emp_id and apprisal_by_admin.quarter = apprisal_admin_evaluated.quarter and apprisal_by_admin.quarter_year = apprisal_admin_evaluated.quarter_year WHERE apprisal_admin_evaluated.emp_id = :emp_id AND (apprisal_admin_evaluated.quarter_year LIKE :firstChoiceYear OR apprisal_admin_evaluated.quarter_year LIKE :secondChoiceYear OR apprisal_admin_evaluated.quarter_year LIKE :thirdChoiceYear OR apprisal_admin_evaluated.quarter_year LIKE :fourthChoiceYear)")
    List<AppraisalFromAdmin_Entity> fetchAllAppraisalDataBasedOnEmpIdAndYear(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);
    
    
//    @Query(nativeQuery = true, value = "select COALESCE(COUNT(emp_id) * 100, 0) AS max_score, COALESCE(SUM(total_score), 0) AS allotted_score, COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) AS overall_percentage, CASE WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) >= 91) THEN '5' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 78 AND 90) THEN '4' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 60 AND 77) THEN '3' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 45 AND 59) THEN '2' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 1 AND 44) THEN '1' ELSE '0' END AS overall_rating from apprisal_by_admin where emp_id=:emp_id AND (quarter_year LIKE :firstChoiceYear OR quarter_year LIKE :secondChoiceYear OR quarter_year LIKE :thirdChoiceYear OR quarter_year LIKE :fourthChoiceYear)")
//    List<Object[]> fetchAllMaxTotalScoreOverallAndOverallRating(
//            @Param("emp_id") String emp_id,
//            @Param("firstChoiceYear") String firstChoiceYear,
//            @Param("secondChoiceYear") String secondChoiceYear,
//            @Param("thirdChoiceYear") String thirdChoiceYear,
//            @Param("fourthChoiceYear") String fourthChoiceYear);
    
    @Query(nativeQuery = true, value = """
    		
    		select COALESCE(COUNT(emp_id) * 100, 0) AS max_score, COALESCE(SUM(total_score::integer), 0) AS allotted_score, 
    		COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) AS overall_percentage, 
    		CASE WHEN (COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) >= 91) THEN '5' WHEN 
    		(COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 78 AND 90) THEN '4' WHEN 
    		(COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 60 AND 77) THEN '3' WHEN 
    		(COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 45 AND 59) THEN '2' WHEN 
    		(COALESCE((SUM(total_score::integer) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 1 AND 44) THEN '1' ELSE '0' END AS overall_rating from apprisal_by_admin 
    		where emp_id=:emp_id AND (quarter_year LIKE :firstChoiceYear OR quarter_year LIKE :secondChoiceYear OR quarter_year LIKE :thirdChoiceYear OR 
    		quarter_year LIKE :fourthChoiceYear);
    		    		
    		    		""")
    		    List<Object[]> fetchAllMaxTotalScoreOverallAndOverallRating(
    		            @Param("emp_id") String emp_id,
    		            @Param("firstChoiceYear") String firstChoiceYear,
    		            @Param("secondChoiceYear") String secondChoiceYear,
    		            @Param("thirdChoiceYear") String thirdChoiceYear,
    		            @Param("fourthChoiceYear") String fourthChoiceYear);
}
