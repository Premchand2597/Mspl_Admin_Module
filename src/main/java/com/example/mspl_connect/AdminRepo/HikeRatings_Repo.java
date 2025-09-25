package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.HikeRatings_Entity;


@Repository
public interface HikeRatings_Repo extends JpaRepository<HikeRatings_Entity, Long>{

	@Query(nativeQuery = true, value="Select * from year_wise_hike_ratings")
	List<HikeRatings_Entity> fetchAllHikeRatingsData();
	
	@Query(nativeQuery = true, value="Select * from year_wise_hike_ratings where financial_year = :financialYear limit 1")
	HikeRatings_Entity fetchHikeRatingsDataBasedOnFinancialYear(@Param("financialYear") String financialYear);
}
