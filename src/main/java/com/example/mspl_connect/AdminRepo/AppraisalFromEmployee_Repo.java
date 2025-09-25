package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Appraisal;
import com.example.mspl_connect.AdminEntity.AppraisalFromEmployee_Entity;


@Repository
public interface AppraisalFromEmployee_Repo extends JpaRepository<AppraisalFromEmployee_Entity, Long>{

	/*@Query(nativeQuery = true, value = "Select * from appraisal_admin where emp_id=:emp_id")
	List<AppraisalFromEmployee_Entity> fetchAllAppraisalDataBasedOnEmpId(@Param("emp_id") String emp_id);*/
	
	
	/*@Query(nativeQuery = true, value = "SELECT * FROM appraisal_admin WHERE emp_id = :emp_id AND (quarter_year LIKE :firstChoiceYear OR quarter_year LIKE :secondChoiceYear OR quarter_year LIKE :thirdChoiceYear OR quarter_year LIKE :fourthChoiceYear)")
    List<Appraisal> fetchAllAdminAppraisalDataBasedOnEmpIdAndYear(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);   
	
	@Query(nativeQuery = true, value = "SELECT * FROM appraisal_employee WHERE emp_id = :emp_id AND (quarter_year LIKE :firstChoiceYear OR quarter_year LIKE :secondChoiceYear OR quarter_year LIKE :thirdChoiceYear OR quarter_year LIKE :fourthChoiceYear)")
    List<Appraisal> fetchAllEmployesAppraisalDataBasedOnEmpIdAndYear(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);   */
	
	/*@Query(nativeQuery = true, value = "Select * from appraisal_admin where emp_id=:emp_id")
	List<AppraisalFromEmployee_Entity> fetchAllAppraisalDataBasedOnEmpId(@Param("emp_id") String emp_id);*/
	
	
	/*@Query(nativeQuery = true, value = "SELECT appraisal_admin.*,appraisal_employee.id as user_id, appraisal_employee.project_score as user_project_score, appraisal_employee.attendance_score as user_attendance_score, appraisal_employee.team_collaboration_score as user_team_collaboration_score, appraisal_employee.communication_score as user_communication_score, appraisal_employee.initiative_score as user_initiative_score, appraisal_employee.leadership_score as user_leadership_score, appraisal_employee.extra_curricular_score as user_extra_curricular_score, appraisal_employee.total_score as user_total_score,appraisal_employee.project_comments as emp_project_comment,appraisal_employee.attendance_comments as emp_attendance_comment,appraisal_employee.team_collaboration_comments as emp_team_collaberation_comment,appraisal_employee.communication_comments as emp_communication_comment,appraisal_employee.initiative_comments as emp_initiative_comment,appraisal_employee.leadership_comments as emp_leadership_comment,appraisal_employee.extra_curricular_comments as emp_extra_curr_comment FROM appraisal_admin left join appraisal_employee on appraisal_employee.emp_id = appraisal_admin.emp_id and appraisal_employee.quarter = appraisal_admin.quarter and appraisal_employee.quarter_year = appraisal_admin.quarter_year WHERE appraisal_admin.emp_id = 'MS159' AND (appraisal_admin.quarter_year LIKE '(Apr - June) - 2024' OR appraisal_admin.quarter_year LIKE '(July - Sep) - 2024' OR appraisal_admin.quarter_year LIKE '(Oct - Dec) - 2024' OR appraisal_admin.quarter_year LIKE '(Jan - March) - 2025');")
    List<AppraisalFromEmployee_Entity> fetchAllAppraisalDataBasedOnEmpIdAndYear(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);*/
	
	@Query(nativeQuery = true, value = "SELECT appraisal_admin.*,appraisal_employee.id as user_id, appraisal_employee.project_score as user_project_score, appraisal_employee.attendance_score as user_attendance_score, appraisal_employee.team_collaboration_score as user_team_collaboration_score, appraisal_employee.communication_score as user_communication_score, appraisal_employee.initiative_score as user_initiative_score,  appraisal_employee.leadership_score as user_leadership_score, appraisal_employee.extra_curricular_score as user_extra_curricular_score,  appraisal_employee.total_score as user_total_score,appraisal_employee.project_comments as emp_project_comment, appraisal_employee.attendance_comments as emp_attendance_comment, appraisal_employee.team_collaboration_comments as emp_team_collaberation_comment, appraisal_employee.communication_comments as emp_communication_comment, appraisal_employee.initiative_comments as emp_initiative_comment,appraisal_employee.leadership_comments as emp_leadership_comment, appraisal_employee.extra_curricular_comments as emp_extra_curr_comment FROM appraisal_admin left join appraisal_employee on appraisal_employee.emp_id = appraisal_admin.emp_id and appraisal_employee.quarter = appraisal_admin.quarter and appraisal_employee.quarter_year = appraisal_admin.quarter_year WHERE appraisal_admin.emp_id = :emp_id AND (appraisal_admin.quarter_year LIKE :firstChoiceYear OR appraisal_admin.quarter_year LIKE :secondChoiceYear OR appraisal_admin.quarter_year LIKE :thirdChoiceYear OR appraisal_admin.quarter_year LIKE :fourthChoiceYear)")
	List<AppraisalFromEmployee_Entity> fetchAllAppraisalDataBasedOnEmpIdAndYear(
	            @Param("emp_id") String emp_id,
	            @Param("firstChoiceYear") String firstChoiceYear,
	            @Param("secondChoiceYear") String secondChoiceYear,
	            @Param("thirdChoiceYear") String thirdChoiceYear,
	            @Param("fourthChoiceYear") String fourthChoiceYear);
    
    @Query(nativeQuery = true, value = "select COALESCE(COUNT(emp_id) * 100, 0) AS max_score, COALESCE(SUM(total_score), 0) AS allotted_score, COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) AS overall_percentage, CASE WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) >= 91) THEN '5' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 78 AND 90) THEN '4' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 60 AND 77) THEN '3' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 45 AND 59) THEN '2' WHEN (COALESCE((SUM(total_score) / (COUNT(emp_id) * 100)) * 100, 0) BETWEEN 1 AND 44) THEN '1' ELSE '0' END AS overall_rating from appraisal_admin where emp_id=:emp_id AND (quarter_year LIKE :firstChoiceYear OR quarter_year LIKE :secondChoiceYear OR quarter_year LIKE :thirdChoiceYear OR quarter_year LIKE :fourthChoiceYear)")
    List<Object[]> fetchAllMaxTotalScoreOverallAndOverallRating(
            @Param("emp_id") String emp_id,
            @Param("firstChoiceYear") String firstChoiceYear,
            @Param("secondChoiceYear") String secondChoiceYear,
            @Param("thirdChoiceYear") String thirdChoiceYear,
            @Param("fourthChoiceYear") String fourthChoiceYear);
}
