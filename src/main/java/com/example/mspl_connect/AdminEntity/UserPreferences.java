package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {



	 public UserPreferences(Long id, String empId, String favoriteLanguages, String habits, String activityName,
			String activityDate, String activityTime) {
		super();
		this.id = id;
		this.empId = empId;
		this.favoriteLanguages = favoriteLanguages;
		this.habits = habits;
		this.activityName = activityName;
		this.activityDate = activityDate;
		this.activityTime = activityTime;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String empId;

	    @Column(length = 1000) // Optional, just to ensure enough space for longer lists
	    private String favoriteLanguages; // Store as comma-separated string

	    @Column(length = 1000) // Optional, just to ensure enough space for longer lists
	    private String habits; // Store as comma-separated string

	    private String activityName;

	    private String activityDate; // Store as String (can be converted to LocalDate later)

	    private String activityTime; // Store as String (can be converted to LocalTime later)

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

	

		public String getActivityName() {
			return activityName;
		}

		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}

		public String getActivityDate() {
			return activityDate;
		}

		public void setActivityDate(String activityDate) {
			this.activityDate = activityDate;
		}

		public String getActivityTime() {
			return activityTime;
		}

		public void setActivityTime(String activityTime) {
			this.activityTime = activityTime;
		}

		public String getFavoriteLanguages() {
			return favoriteLanguages;
		}

		public void setFavoriteLanguages(String favoriteLanguages) {
			this.favoriteLanguages = favoriteLanguages;
		}

		public String getHabits() {
			return habits;
		}

		public void setHabits(String habits) {
			this.habits = habits;
		}

	
}
