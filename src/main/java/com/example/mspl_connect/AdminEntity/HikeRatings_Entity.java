package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "year_wise_hike_ratings")
public class HikeRatings_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String financial_year; 
	private String rating;
	private String hike_percentage;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getHike_percentage() {
		return hike_percentage;
	}
	public void setHike_percentage(String hike_percentage) {
		this.hike_percentage = hike_percentage;
	}
	@Override
	public String toString() {
		return "HikeRatings_Entity [id=" + id + ", financial_year=" + financial_year + ", rating=" + rating
				+ ", hike_percentage=" + hike_percentage + "]";
	}
	
}
