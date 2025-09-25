package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.CallSchedule;

import jakarta.transaction.Transactional;


@Repository
public interface CallScheduleRepository extends JpaRepository<CallSchedule, Long> {

	
	 @Modifying
	    @Transactional
	    @Query("UPDATE CallSchedule c SET c.callStatus = :status WHERE c.id = :id")
	    int updateCallStatus(Long id, Integer status);
}
