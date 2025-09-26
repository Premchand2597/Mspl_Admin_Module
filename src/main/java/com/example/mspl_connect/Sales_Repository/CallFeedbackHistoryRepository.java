package com.example.mspl_connect.Sales_Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.CallFeedbackHistory;

@Repository
public interface CallFeedbackHistoryRepository extends JpaRepository<CallFeedbackHistory, Long>{
	 List<CallFeedbackHistory> findByCallScheduleId(Long callScheduleId);
}
