package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inserted_attendance")
public class InsertedAttendanceRecord_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String eid;
	private String pt;
	private String pdt;
	private String pd;
	private String inserted_as;
	private String compensate_date;
	private String wfh_reason;
	private String onduty_reason;
	private String others_reason;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public String getPdt() {
		return pdt;
	}
	public void setPdt(String pdt) {
		this.pdt = pdt;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getInserted_as() {
		return inserted_as;
	}
	public void setInserted_as(String inserted_as) {
		this.inserted_as = inserted_as;
	}
	public String getCompensate_date() {
		return compensate_date;
	}
	public void setCompensate_date(String compensate_date) {
		this.compensate_date = compensate_date;
	}
	public String getWfh_reason() {
		return wfh_reason;
	}
	public void setWfh_reason(String wfh_reason) {
		this.wfh_reason = wfh_reason;
	}
	public String getOnduty_reason() {
		return onduty_reason;
	}
	public void setOnduty_reason(String onduty_reason) {
		this.onduty_reason = onduty_reason;
	}
	public String getOthers_reason() {
		return others_reason;
	}
	public void setOthers_reason(String others_reason) {
		this.others_reason = others_reason;
	}
	@Override
	public String toString() {
		return "InsertedAttendanceRecord_Entity [id=" + id + ", eid=" + eid + ", pt=" + pt + ", pdt=" + pdt + ", pd="
				+ pd + ", inserted_as=" + inserted_as + ", compensate_date=" + compensate_date + ", wfh_reason="
				+ wfh_reason + ", onduty_reason=" + onduty_reason + ", others_reason=" + others_reason + "]";
	}
	
}
