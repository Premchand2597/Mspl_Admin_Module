package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EarnedLeaveInsertManually_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface EarnedLeaveInsertManually_Repo extends JpaRepository<EarnedLeaveInsertManually_Entity, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM earned_leave where emp_id=:empid order by id desc limit 1")
	EarnedLeaveInsertManually_Entity getPreviousEarnedLeaveDetailsBasedOnEmpid(@Param("empid") String empid);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update earned_leave set earned_leave=:earned_leave, el_used=:el_used, elremaining=:elremaining, last_increment_date = curdate(), email=:email, month_year=:month_year, remarks=:remarks where emp_id=:empid order by id desc limit 1")
//	int updateEarnedLeaveDataBasedOnEmpId(@Param("empid") String empid, @Param("earned_leave") double earned_leave, @Param("el_used") double el_used, 
//			@Param("elremaining") double elremaining, @Param("email") String email, @Param("month_year") String month_year, @Param("remarks") String remarks);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = """
	    UPDATE earned_leave
	    SET earned_leave = :earned_leave,
	        el_used = :el_used,
	        elremaining = :elremaining,
	        last_increment_date = CURRENT_DATE,
	        email = :email,
	        month_year = :month_year,
	        remarks = :remarks
	    WHERE id = (
	        SELECT id FROM earned_leave
	        WHERE emp_id = :empid
	        ORDER BY id DESC
	        LIMIT 1
	    )
	""")
	int updateEarnedLeaveDataBasedOnEmpId(@Param("empid") String empid,
	                                      @Param("earned_leave") double earned_leave,
	                                      @Param("el_used") double el_used,
	                                      @Param("elremaining") double elremaining,
	                                      @Param("email") String email,
	                                      @Param("month_year") String month_year,
	                                      @Param("remarks") String remarks);

	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into earned_leave(earned_leave, el_used, elremaining, email, emp_id, last_increment_date, month_year, remarks) values(:earned_leave, 0, :elremaining, :email, :emp_id, curdate(), :month_year, :remarks)")
	int insertEarnedLeaveDataForNewEmployee(@Param("emp_id") String emp_id, @Param("earned_leave") double earned_leave, 
			@Param("elremaining") double elremaining, @Param("email") String email, @Param("month_year") String month_year, @Param("remarks") String remarks);
}
