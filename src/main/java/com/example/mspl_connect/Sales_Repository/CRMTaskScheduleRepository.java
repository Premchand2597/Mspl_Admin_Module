package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.CRMTaskSchedule;

@Repository
public interface CRMTaskScheduleRepository extends JpaRepository<CRMTaskSchedule, Long>{

}
