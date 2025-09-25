package com.example.mspl_connect.AdminEntity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="release_update")
public class ReleaseUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String release_note_version;
	private String is_read;
	private String empid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelease_note_version() {
		return release_note_version;
	}
	public void setRelease_note_version(String release_note_version) {
		this.release_note_version = release_note_version;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public ReleaseUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReleaseUpdate(int id, String release_note_version, String is_read, String empid) {
		super();
		this.id = id;
		this.release_note_version = release_note_version;
		this.is_read = is_read;
		this.empid = empid;
	}
	
	
	
}
