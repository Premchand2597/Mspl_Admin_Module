package com.example.mspl_connect.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.AdminEntity.MoatCandidatesCount_Entity;
import com.example.mspl_connect.AdminRepo.MoatCandidatesCount_Repo;

@Service
public class MoatCandidateCounts_Service {

	@Autowired
	private MoatCandidatesCount_Repo moatCandidatesCount_Repo;
	
	public MoatCandidatesCount_Entity countMoatTotalAttendedTotalSelectedTotalRejectedCandidatesList() {
		return moatCandidatesCount_Repo.countMoatTotalAttendedTotalSelectedTotalRejectedCandidates();
	}
}
