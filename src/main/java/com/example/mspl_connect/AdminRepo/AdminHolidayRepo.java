package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.HolidaysList;

@Repository
public interface AdminHolidayRepo extends JpaRepository<HolidaysList, Integer>{
	   
//	    @Query(nativeQuery = true, value="SELECT * FROM holiday h WHERE YEAR(STR_TO_DATE(h.date, '%Y-%m-%d')) = :year")
//	    List<HolidaysList> findByYear(@Param("year") String year);
	
	@Query(nativeQuery = true, value="""
    		
    		SELECT * FROM holiday h WHERE EXTRACT(YEAR FROM h.date::date) = cast(:year as numeric);
    		
    		""")
    List<HolidaysList> findByYear(@Param("year") String year);

//	    @Query(nativeQuery = true, value="SELECT DISTINCT YEAR(STR_TO_DATE(h.date, '%Y-%m-%d')) FROM holiday h")
//	    List<Integer> findDistinctYears();
	
	@Query(nativeQuery = true, value="""
    		
    		SELECT DISTINCT EXTRACT(YEAR FROM h.date) AS year FROM holiday h;
    		
    		""")
    List<Integer> findDistinctYears();
	    
}
