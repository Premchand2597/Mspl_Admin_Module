package com.example.mspl_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="holiday")
public class Holiday_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  id;
	private String date;
	private String name;
	private String day;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "Holiday_Entity [id=" + id + ", date=" + date + ", name=" + name + ", day=" + day + "]";
	}
}
