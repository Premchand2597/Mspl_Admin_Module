package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CardCountBasedOnModeOfContactData_Entity {

	@Id
	private String mode_of_contact_select;
	private String mode_of_contact_reject; 
	private String mode_of_contact_onhold;
	private String mode_of_contact_pending;
	
	public String getMode_of_contact_select() {
		return mode_of_contact_select;
	}
	public void setMode_of_contact_select(String mode_of_contact_select) {
		this.mode_of_contact_select = mode_of_contact_select;
	}
	public String getMode_of_contact_reject() {
		return mode_of_contact_reject;
	}
	public void setMode_of_contact_reject(String mode_of_contact_reject) {
		this.mode_of_contact_reject = mode_of_contact_reject;
	}
	public String getMode_of_contact_onhold() {
		return mode_of_contact_onhold;
	}
	public void setMode_of_contact_onhold(String mode_of_contact_onhold) {
		this.mode_of_contact_onhold = mode_of_contact_onhold;
	}
	public String getMode_of_contact_pending() {
		return mode_of_contact_pending;
	}
	public void setMode_of_contact_pending(String mode_of_contact_pending) {
		this.mode_of_contact_pending = mode_of_contact_pending;
	}
	@Override
	public String toString() {
		return "CardCountBasedOnModeOfContactData_Entity [mode_of_contact_select=" + mode_of_contact_select
				+ ", mode_of_contact_reject=" + mode_of_contact_reject + ", mode_of_contact_onhold="
				+ mode_of_contact_onhold + ", mode_of_contact_pending=" + mode_of_contact_pending + "]";
	}
	
}
