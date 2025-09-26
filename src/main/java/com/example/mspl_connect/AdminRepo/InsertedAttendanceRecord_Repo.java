package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.InsertedAttendanceRecord_Entity;


@Repository
public interface InsertedAttendanceRecord_Repo extends JpaRepository<InsertedAttendanceRecord_Entity, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM inserted_attendance where eid=:empid")
	List<InsertedAttendanceRecord_Entity> getAllInsertedDetailsBasedOnEmpId(@Param("empid") String empid);
}
