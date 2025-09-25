package com.example.mspl_connect.Sales_Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "terms_and_condition_table")
public class Terms_and_condition_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
	private long id;
	private String terms_and_condition_data;
	private String q_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerms_and_condition_data() {
		return terms_and_condition_data;
	}
	public void setTerms_and_condition_data(String terms_and_condition_data) {
		this.terms_and_condition_data = terms_and_condition_data;
	} 
	
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	@Override
	public String toString() {
		return "Terms_and_condition_Entity [id=" + id + ", terms_and_condition_data=" + terms_and_condition_data
				+ ", q_id=" + q_id + "]";
	}
	
}
