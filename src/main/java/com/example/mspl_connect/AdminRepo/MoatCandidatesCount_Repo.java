package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.MoatCandidatesCount_Entity;


@Repository
public interface MoatCandidatesCount_Repo extends JpaRepository<MoatCandidatesCount_Entity, Long>{

	@Query(nativeQuery = true, value = "select count(*) as moat_total_attended_test, sum(case when action = \"approv\" then 1 else 0 end) as moat_selected_candidates, sum(case when action = \"Reject\" then 1 else 0 end) as moat_rejected_candidates, sum(case when action = \"\" or action is null or action = \"null\" then 1 else 0 end) as moat_pending_candidates, sum(case when action = \"onhold\" then 1 else 0 end) as moat_onhold_candidates from student")
	MoatCandidatesCount_Entity countMoatTotalAttendedTotalSelectedTotalRejectedCandidates();
}
