package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "holiday_notifications")
public class Holiday_notification_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String emp_id;
	private String flag;
	private long holiday_count;
	private String holiday_year;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public long getHoliday_count() {
		return holiday_count;
	}
	public void setHoliday_count(long holiday_count) {
		this.holiday_count = holiday_count;
	}
	public String getHoliday_year() {
		return holiday_year;
	}
	public void setHoliday_year(String holiday_year) {
		this.holiday_year = holiday_year;
	}
	@Override
	public String toString() {
		return "Holiday_notification_Entity [id=" + id + ", emp_id=" + emp_id + ", flag=" + flag + ", holiday_count="
				+ holiday_count + ", holiday_year=" + holiday_year + "]";
	}
	
}
