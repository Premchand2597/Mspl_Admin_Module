package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Holiday_Entity;




@Repository
public interface Holiday_Repo extends JpaRepository<Holiday_Entity, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM holiday order by month(date) asc")
	List<Holiday_Entity> findAllByOrderByDateAsc();
	
	List<Holiday_Entity> findByDateStartingWith(String year);

	/*@Query("SELECT h FROM Holiday h WHERE h.date BETWEEN :startDate AND :endDate")
    List<Holiday_Entity> findHolidaysBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);*/
}
