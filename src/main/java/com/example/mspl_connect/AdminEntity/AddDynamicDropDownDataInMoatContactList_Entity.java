package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dynamic_dropdown_data_for_moat_contact_list")
public class AddDynamicDropDownDataInMoatContactList_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String status_number;
	private String data_value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus_number() {
		return status_number;
	}
	public void setStatus_number(String status_number) {
		this.status_number = status_number;
	}
	public String getData_value() {
		return data_value;
	}
	public void setData_value(String data_value) {
		this.data_value = data_value;
	}
	@Override
	public String toString() {
		return "AddDynamicDropDownDataInMoatContactList_Entity [id=" + id + ", status_number=" + status_number
				+ ", data_value=" + data_value + "]";
	}
	
}
