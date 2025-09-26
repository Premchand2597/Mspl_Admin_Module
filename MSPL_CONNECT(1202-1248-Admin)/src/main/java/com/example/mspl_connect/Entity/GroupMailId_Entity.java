package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_mail_id_table")
public class GroupMailId_Entity {

	@Id
	private long id;
	private String main_mail_id;
	private String sub_mail_id;
	private String group_mail_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMain_mail_id() {
		return main_mail_id;
	}
	public void setMain_mail_id(String main_mail_id) {
		this.main_mail_id = main_mail_id;
	}
	public String getSub_mail_id() {
		return sub_mail_id;
	}
	public void setSub_mail_id(String sub_mail_id) {
		this.sub_mail_id = sub_mail_id;
	}
	public String getGroup_mail_id() {
		return group_mail_id;
	}
	public void setGroup_mail_id(String group_mail_id) {
		this.group_mail_id = group_mail_id;
	}
	@Override
	public String toString() {
		return "GroupMailId_Entity [id=" + id + ", main_mail_id=" + main_mail_id + ", sub_mail_id=" + sub_mail_id
				+ ", group_mail_id=" + group_mail_id + "]";
	}
	
}
