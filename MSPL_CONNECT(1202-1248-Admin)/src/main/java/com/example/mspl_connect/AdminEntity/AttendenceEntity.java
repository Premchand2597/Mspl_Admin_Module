package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="attendance")
public class AttendenceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String eid;
	private String pt;
	private String pd;
	private String pdt;
	
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
	
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getPdt() {
		return pdt;
	}
	public void setPdt(String pdt) {
		this.pdt = pdt;
	}
	@Override
	public String toString() {
		return "AttendenceEntity [eid=" + eid + ", pt=" + pt + ", pd=" + pd + ", pdt=" + pdt + "]";
	}
	
	
}
