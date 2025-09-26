package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Holiday_Entity;

import jakarta.transaction.Transactional;




@Repository
public interface Holiday_Repo extends JpaRepository<Holiday_Entity, Long>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="insert into holiday(date, name, day) values(cast(:date as date), :name, :day)")
	void insertHolidayDetails(@Param("date") String date, @Param("name") String name, @Param("day") String day);

	@Query(nativeQuery = true, value = "SELECT * FROM holiday order by month(date) asc")
	List<Holiday_Entity> findAllByOrderByDateAsc();
	
	List<Holiday_Entity> findByDateStartingWith(String year);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM holiday where year(date)=:year")
//	List<Holiday_Entity> findByDateStartingWithYearData(String year);
	
	@Query(nativeQuery = true, value = "SELECT * FROM holiday where EXTRACT(YEAR FROM date)=cast(:year as numeric)")
	List<Holiday_Entity> findByDateStartingWithYearData(String year);
	
//	@Query(nativeQuery = true, value = "select * from holiday where year(date) = :year order by month(date) asc")
//	List<Holiday_Entity> fetchHolidayBasedOnYear(@Param("year") String year);
	
	@Query(nativeQuery = true, value = """
			
			SELECT * 
			FROM holiday 
			WHERE EXTRACT(YEAR FROM date) = cast(:year as numeric)
			ORDER BY EXTRACT(MONTH FROM date) ASC;
			
			""")
	List<Holiday_Entity> fetchHolidayBasedOnYear(@Param("year") String year);
	
//	@Query(nativeQuery = true, value = "select * from holiday where id=:auto_id")
//	Holiday_Entity editHolidayData(@Param("auto_id") String auto_id);
	
	@Query(nativeQuery = true, value = "select * from holiday where id=cast(:auto_id as integer)")
	Holiday_Entity editHolidayData(@Param("auto_id") String auto_id);
	
//	@Modifying
// 	@Transactional
//	@Query(nativeQuery = true, value="update holiday set date=:date, day=:day, name=:name where id=:id")
//	void update_Holiday_details(@Param("id") long id, @Param("date") String date, @Param("day") String day, @Param("name") String name);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update holiday set date=cast(:date as date), day=:day, name=:name where id=:id")
	void update_Holiday_details(@Param("id") long id, @Param("date") String date, @Param("day") String day, @Param("name") String name);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM holiday WHERE id=:id")
	void delete_HolidayDetails(@Param("id") long id);

	/*@Query("SELECT h FROM Holiday h WHERE h.date BETWEEN :startDate AND :endDate")
    List<Holiday_Entity> findHolidaysBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);*/
}
