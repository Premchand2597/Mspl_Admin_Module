package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity;


@Repository
public interface AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Repo extends JpaRepository<AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity, Long>{

	/*@Query(value = "SELECT \r\n"
			+ "    yearwise_overall_performance_ratings.*,\r\n"
			+ "    COALESCE(year_wise_hike_ratings.financial_year, yearwise_overall_performance_ratings.financial_year) AS financial_year_in_another_table,\r\n"
			+ "    COALESCE(year_wise_hike_ratings.rating, 0) AS rating,\r\n"
			+ "    COALESCE(year_wise_hike_ratings.hike_percentage, 0) AS hike_percentage,\r\n"
			+ "    CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name,\r\n"
			+ "    employee_details.current_salary,\r\n"
			+ "    employee_details.current_salary + \r\n"
			+ "        ((employee_details.current_salary * COALESCE(year_wise_hike_ratings.hike_percentage, 0)) / 100) AS salary_after_hike,\r\n"
			+ "    CASE \r\n"
			+ "        WHEN COALESCE(year_wise_hike_ratings.rating, 0) = 0 THEN 'Appraisal not submitted'\r\n"
			+ "        ELSE 'Appraisal submitted'\r\n"
			+ "    END AS remarks\r\n"
			+ "FROM \r\n"
			+ "    yearwise_overall_performance_ratings\r\n"
			+ "LEFT JOIN \r\n"
			+ "    year_wise_hike_ratings \r\n"
			+ "    ON yearwise_overall_performance_ratings.financial_year = year_wise_hike_ratings.financial_year \r\n"
			+ "    AND yearwise_overall_performance_ratings.overall_rating = year_wise_hike_ratings.rating\r\n"
			+ "LEFT JOIN \r\n"
			+ "    employee_details \r\n"
			+ "    ON employee_details.empid = yearwise_overall_performance_ratings.emp_id where yearwise_overall_performance_ratings.emp_id = :emp_id and yearwise_overall_performance_ratings.financial_year = :financial_year order by yearwise_overall_performance_ratings.id desc limit 1", nativeQuery = true)
	AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity getAllAppraisalDataWithUpdatedHikeSalary(@Param("emp_id") String emp_id, @Param("financial_year") String financial_year);*/
	
	
	@Query(value = """
			
SELECT  yearwise_overall_performance_ratings.*, COALESCE(year_wise_hike_ratings.financial_year, 
yearwise_overall_performance_ratings.financial_year) AS financial_year_in_another_table, COALESCE(year_wise_hike_ratings.rating::integer, 0) AS rating, 
COALESCE(year_wise_hike_ratings.hike_percentage::integer, 0) AS hike_percentage, CONCAT(employee_details.f_name, ' ', employee_details.l_name) AS emp_name, 
COALESCE(NULLIF(employee_details.current_salary, '')::float8, 0) AS current_salary, COALESCE(NULLIF(employee_details.current_salary, '')::float8, 0) + ((COALESCE(NULLIF(employee_details.current_salary, '')::float8, 0) * 
COALESCE(year_wise_hike_ratings.hike_percentage::integer, 0)) / 100) AS salary_after_hike, CASE WHEN COALESCE(year_wise_hike_ratings.rating::integer, 0) = 0 THEN 
'Appraisal not submitted' ELSE 'Appraisal submitted' END AS remarks FROM yearwise_overall_performance_ratings LEFT JOIN year_wise_hike_ratings ON 
yearwise_overall_performance_ratings.financial_year = year_wise_hike_ratings.financial_year AND 
yearwise_overall_performance_ratings.overall_rating = year_wise_hike_ratings.rating LEFT JOIN employee_details ON 
employee_details.empid = yearwise_overall_performance_ratings.emp_id where yearwise_overall_performance_ratings.emp_id = :emp_id and 
yearwise_overall_performance_ratings.financial_year = :financial_year order by yearwise_overall_performance_ratings.id desc limit 1;
			
			""", nativeQuery = true)
	AppraisalMaxscoreAllottedscoreOverallpercentOverallRatingWithEmpDetailsFetch_Entity getAllAppraisalDataWithUpdatedHikeSalary(@Param("emp_id") String emp_id, @Param("financial_year") String financial_year);
	
}
