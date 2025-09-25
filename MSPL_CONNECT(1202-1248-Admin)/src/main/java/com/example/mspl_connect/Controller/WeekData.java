package com.example.mspl_connect.Controller;

import java.time.LocalDate;
import java.util.List;

public class WeekData {

	 public WeekData(String weekLabel) {
		super();
		this.weekLabel = weekLabel;
	}
	public WeekData() {
		super();
	}
	public WeekData(String weekLabel, List<LocalDate> dates) {
		super();
		this.weekLabel = weekLabel;
		this.dates = dates;
	}


	private String weekLabel;
	 private List<LocalDate> dates;
	    
		public String getWeekLabel() {
			return weekLabel;
		}
		public void setWeekLabel(String weekLabel) {
			this.weekLabel = weekLabel;
		}
		public List<LocalDate> getDates() {
			return dates;
		}
		public void setDates(List<LocalDate> dates) {
			this.dates = dates;
		}
	
	
}
