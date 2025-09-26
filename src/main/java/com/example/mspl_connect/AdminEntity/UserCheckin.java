package com.example.mspl_connect.AdminEntity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_checkins")
public class UserCheckin {

	 public UserCheckin() {
		super();
	}

	public UserCheckin(Long id, String empId, String checkinDate, int checkinFlag, int checkoutFlag) {
		super();
		this.id = id;
		this.empId = empId;
		this.checkinDate = checkinDate;
		this.checkinFlag = checkinFlag;
		this.checkoutFlag = checkoutFlag;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "emp_Id", nullable = false)
	    private String empId;

	    @Column(name = "date", nullable = false)
	    private String checkinDate;

	    @Column(name = "checkin_flag", nullable = false)
	    private int checkinFlag;
	    
	    @Column(name="checkout_flag")
	    private int checkoutFlag;

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

		public String getCheckinDate() {
			return checkinDate;
		}

		public void setCheckinDate(String checkinDate) {
			this.checkinDate = checkinDate;
		}

		public int getCheckinFlag() {
			return checkinFlag;
		}

		public void setCheckinFlag(int checkinFlag) {
			this.checkinFlag = checkinFlag;
		}

		public int getCheckoutFlag() {
			return checkoutFlag;
		}

		public void setCheckoutFlag(int checkoutFlag) {
			this.checkoutFlag = checkoutFlag;
		}
	
}
