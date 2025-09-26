package com.example.mspl_connect.Sales_Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Add_Product_Entity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "pid")
	private String pid;
	
	 @Column(name = "pdate")
	private String dateTime;
	
	@Column(name = "pname")
	private String pname;
	
	@Column(name = "pdescription")
	private String pdescription;
	
	@Column(name = "hsn_code")
	private String hsn_code;
	
	@Column(name = "part_number")
	private String part_number;
	
	@Column(name = "remarks")
	private String remarks;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	
	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public String getPart_number() {
		return part_number;
	}

	public void setPart_number(String part_number) {
		this.part_number = part_number;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	@Override
	public String toString() {
		return "Add_Product_Entity [pid=" + pid + ", dateTime=" + dateTime + ", pname=" + pname + ", pdescription="
				+ pdescription + ", hsn_code=" + hsn_code + ", part_number=" + part_number + ", remarks=" + remarks
				+ "]";
	}

	public Add_Product_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Add_Product_Entity(String pid, String dateTime, String pname, String pdescription, String hsn_code,
			String part_number, String remarks) {
		super();
		this.pid = pid;
		this.dateTime = dateTime;
		this.pname = pname;
		this.pdescription = pdescription;
		this.hsn_code = hsn_code;
		this.part_number = part_number;
		this.remarks = remarks;
	}


	
	
	
}
