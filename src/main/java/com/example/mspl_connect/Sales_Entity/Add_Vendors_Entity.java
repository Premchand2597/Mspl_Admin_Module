package com.example.mspl_connect.Sales_Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Add_Vendors_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vid")
	private String vid;
	
	@Column(name = "date_time")
	private String dateTime;
	
	@Column(name = "vname")
	private String vname;
	
	@Column(name = "vlname")
	private String vlname;
	
	@Column(name = "gstnumber")
	private String gstnumber;
	
	@Column(name = "contact_p1_name")
	private String contact_p1_name;
	
	@Column(name = "contact_p1_designation")
	private String contact_p1_designation;
	
	@Column(name="contact_p1_mobile")
	private String contact_p1_mobile;
	
	@Column(name="contact_p1_email")
	private String contact_p1_email;
	
	@Column(name="contact_p2_name")
	private String contact_p2_name;
	
	@Column(name = "contact_p2_designation")
	private String contact_p2_designation;
	
	@Column(name="contact_p2_mobile")
	private String contact_p2_mobile;
	
	@Column(name="contact_p2_email")
	private String contact_p2_email;
	
	@Column(name="contact_p3_name")
	private String contact_p3_name;
	
	@Column(name = "contact_p3_designation")
	private String contact_p3_designation;
	
	@Column(name="contact_p3_mobile")
	private String contact_p3_mobile;
	
	@Column(name="contact_p3_email")
	private String contact_p3_email;
	
	@Column(name="delivery_address_1")
	private String delivery_address_1;
	
	@Column(name="delivery_address_2")
	private String delivery_address_2;
	
	@Column(name="delivery_address_3")
	private String delivery_address_3;
	
	@Column(name="billing_address")
	private String billing_address;
	
	private String honorifics_main;
	private String honorifics_1;
	private String honorifics_2;
	private String honorifics_3;
	
	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVlname() {
		return vlname;
	}

	public void setVlname(String vlname) {
		this.vlname = vlname;
	}

	public String getGstnumber() {
		return gstnumber;
	}

	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
	}

	public String getContact_p1_name() {
		return contact_p1_name;
	}

	public void setContact_p1_name(String contact_p1_name) {
		this.contact_p1_name = contact_p1_name;
	}

	public String getContact_p1_mobile() {
		return contact_p1_mobile;
	}

	public void setContact_p1_mobile(String contact_p1_mobile) {
		this.contact_p1_mobile = contact_p1_mobile;
	}

	public String getContact_p1_email() {
		return contact_p1_email;
	}

	public void setContact_p1_email(String contact_p1_email) {
		this.contact_p1_email = contact_p1_email;
	}

	public String getContact_p2_name() {
		return contact_p2_name;
	}

	public void setContact_p2_name(String contact_p2_name) {
		this.contact_p2_name = contact_p2_name;
	}

	public String getContact_p2_mobile() {
		return contact_p2_mobile;
	}

	public void setContact_p2_mobile(String contact_p2_mobile) {
		this.contact_p2_mobile = contact_p2_mobile;
	}

	public String getContact_p2_email() {
		return contact_p2_email;
	}

	public void setContact_p2_email(String contact_p2_email) {
		this.contact_p2_email = contact_p2_email;
	}

	public String getContact_p3_name() {
		return contact_p3_name;
	}

	public void setContact_p3_name(String contact_p3_name) {
		this.contact_p3_name = contact_p3_name;
	}

	public String getContact_p3_mobile() {
		return contact_p3_mobile;
	}

	public void setContact_p3_mobile(String contact_p3_mobile) {
		this.contact_p3_mobile = contact_p3_mobile;
	}

	public String getContact_p3_email() {
		return contact_p3_email;
	}

	public void setContact_p3_email(String contact_p3_email) {
		this.contact_p3_email = contact_p3_email;
	}

	
	public String getContact_p1_designation() {
		return contact_p1_designation;
	}

	public void setContact_p1_designation(String contact_p1_designation) {
		this.contact_p1_designation = contact_p1_designation;
	}

	public String getContact_p2_designation() {
		return contact_p2_designation;
	}

	public void setContact_p2_designation(String contact_p2_designation) {
		this.contact_p2_designation = contact_p2_designation;
	}

	public String getContact_p3_designation() {
		return contact_p3_designation;
	}

	public void setContact_p3_designation(String contact_p3_designation) {
		this.contact_p3_designation = contact_p3_designation;
	}
	
	public String getDelivery_address_1() {
		return delivery_address_1;
	}

	public void setDelivery_address_1(String delivery_address_1) {
		this.delivery_address_1 = delivery_address_1;
	}

	public String getDelivery_address_2() {
		return delivery_address_2;
	}

	public void setDelivery_address_2(String delivery_address_2) {
		this.delivery_address_2 = delivery_address_2;
	}

	public String getDelivery_address_3() {
		return delivery_address_3;
	}

	public void setDelivery_address_3(String delivery_address_3) {
		this.delivery_address_3 = delivery_address_3;
	}
	
	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}	
	
	public String getHonorifics_main() {
		return honorifics_main;
	}

	public void setHonorifics_main(String honorifics_main) {
		this.honorifics_main = honorifics_main;
	}

	public String getHonorifics_1() {
		return honorifics_1;
	}

	public void setHonorifics_1(String honorifics_1) {
		this.honorifics_1 = honorifics_1;
	}

	public String getHonorifics_2() {
		return honorifics_2;
	}

	public void setHonorifics_2(String honorifics_2) {
		this.honorifics_2 = honorifics_2;
	}

	public String getHonorifics_3() {
		return honorifics_3;
	}

	public void setHonorifics_3(String honorifics_3) {
		this.honorifics_3 = honorifics_3;
	}


	public Add_Vendors_Entity(String vid, String dateTime, String vname, String vlname, String gstnumber,
			String contact_p1_name, String contact_p1_designation, String contact_p1_mobile, String contact_p1_email,
			String contact_p2_name, String contact_p2_designation, String contact_p2_mobile, String contact_p2_email,
			String contact_p3_name, String contact_p3_designation, String contact_p3_mobile, String contact_p3_email,
			String delivery_address_1, String delivery_address_2, String delivery_address_3, String billing_address,
			String honorifics_main, String honorifics_1, String honorifics_2, String honorifics_3) {
		super();
		this.vid = vid;
		this.dateTime = dateTime;
		this.vname = vname;
		this.vlname = vlname;
		this.gstnumber = gstnumber;
		this.contact_p1_name = contact_p1_name;
		this.contact_p1_designation = contact_p1_designation;
		this.contact_p1_mobile = contact_p1_mobile;
		this.contact_p1_email = contact_p1_email;
		this.contact_p2_name = contact_p2_name;
		this.contact_p2_designation = contact_p2_designation;
		this.contact_p2_mobile = contact_p2_mobile;
		this.contact_p2_email = contact_p2_email;
		this.contact_p3_name = contact_p3_name;
		this.contact_p3_designation = contact_p3_designation;
		this.contact_p3_mobile = contact_p3_mobile;
		this.contact_p3_email = contact_p3_email;
		this.delivery_address_1 = delivery_address_1;
		this.delivery_address_2 = delivery_address_2;
		this.delivery_address_3 = delivery_address_3;
		this.billing_address = billing_address;
		this.honorifics_main = honorifics_main;
		this.honorifics_1 = honorifics_1;
		this.honorifics_2 = honorifics_2;
		this.honorifics_3 = honorifics_3;
	}

	@Override
	public String toString() {
		return "Add_Vendors_Entity [vid=" + vid + ", dateTime=" + dateTime + ", vname=" + vname + ", vlname=" + vlname
				+ ", gstnumber=" + gstnumber + ", contact_p1_name=" + contact_p1_name + ", contact_p1_designation="
				+ contact_p1_designation + ", contact_p1_mobile=" + contact_p1_mobile + ", contact_p1_email="
				+ contact_p1_email + ", contact_p2_name=" + contact_p2_name + ", contact_p2_designation="
				+ contact_p2_designation + ", contact_p2_mobile=" + contact_p2_mobile + ", contact_p2_email="
				+ contact_p2_email + ", contact_p3_name=" + contact_p3_name + ", contact_p3_designation="
				+ contact_p3_designation + ", contact_p3_mobile=" + contact_p3_mobile + ", contact_p3_email="
				+ contact_p3_email + ", delivery_address_1=" + delivery_address_1 + ", delivery_address_2="
				+ delivery_address_2 + ", delivery_address_3=" + delivery_address_3 + ", billing_address="
				+ billing_address + ", honorifics_main=" + honorifics_main + ", honorifics_1=" + honorifics_1
				+ ", honorifics_2=" + honorifics_2 + ", honorifics_3=" + honorifics_3 + "]";
	}

	public Add_Vendors_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
