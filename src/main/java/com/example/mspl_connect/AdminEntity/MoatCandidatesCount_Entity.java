package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MoatCandidatesCount_Entity {

	@Id
	private long moat_total_attended_test; 
	private long moat_selected_candidates;
	private long moat_rejected_candidates;
	private long moat_pending_candidates;
	private long moat_onhold_candidates;
	
	public long getMoat_total_attended_test() {
		return moat_total_attended_test;
	}

	public void setMoat_total_attended_test(long moat_total_attended_test) {
		this.moat_total_attended_test = moat_total_attended_test;
	}

	public long getMoat_selected_candidates() {
		return moat_selected_candidates;
	}

	public void setMoat_selected_candidates(long moat_selected_candidates) {
		this.moat_selected_candidates = moat_selected_candidates;
	}

	public long getMoat_rejected_candidates() {
		return moat_rejected_candidates;
	}

	public void setMoat_rejected_candidates(long moat_rejected_candidates) {
		this.moat_rejected_candidates = moat_rejected_candidates;
	}

	public long getMoat_pending_candidates() {
		return moat_pending_candidates;
	}

	public void setMoat_pending_candidates(long moat_pending_candidates) {
		this.moat_pending_candidates = moat_pending_candidates;
	}

	public long getMoat_onhold_candidates() {
		return moat_onhold_candidates;
	}

	public void setMoat_onhold_candidates(long moat_onhold_candidates) {
		this.moat_onhold_candidates = moat_onhold_candidates;
	}

	@Override
	public String toString() {
		return "MoatCandidatesCount_Entity [moat_total_attended_test=" + moat_total_attended_test
				+ ", moat_selected_candidates=" + moat_selected_candidates + ", moat_rejected_candidates="
				+ moat_rejected_candidates + ", moat_pending_candidates=" + moat_pending_candidates
				+ ", moat_onhold_candidates=" + moat_onhold_candidates + "]";
	}
	
}
