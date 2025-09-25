package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inter_comm_details")
public class InterComm_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String cell_number;
	private String tele_number;
	private String cubical_number;
	private String seat_place;
	private String mail_id;
	private String floor_number;
	private String room_number;
	
	public String getFloor_number() {
		return floor_number;
	}
	public void setFloor_number(String floor_number) {
		this.floor_number = floor_number;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCell_number() {
		return cell_number;
	}
	public void setCell_number(String cell_number) {
		this.cell_number = cell_number;
	}
	public String getTele_number() {
		return tele_number;
	}
	public void setTele_number(String tele_number) {
		this.tele_number = tele_number;
	}
	public String getCubical_number() {
		return cubical_number;
	}
	public void setCubical_number(String cubical_number) {
		this.cubical_number = cubical_number;
	}
	public String getSeat_place() {
		return seat_place;
	}
	public void setSeat_place(String seat_place) {
		this.seat_place = seat_place;
	}
	@Override
	public String toString() {
		return "InterComm_Entity [id=" + id + ", username=" + username + ", cell_number=" + cell_number
				+ ", tele_number=" + tele_number + ", cubical_number=" + cubical_number + ", seat_place=" + seat_place
				+ ", mail_id=" + mail_id + ", floor_number=" + floor_number + ", room_number=" + room_number + "]";
	}
	
}
