package com.example.mspl_connect.Sales_Repository;

import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.Add_Product_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface IAdd_Vendors extends JpaRepository<Add_Vendors_Entity, String> {
	
	@Query("Select v.vid from Add_Vendors_Entity v ORDER BY v.vid DESC LIMIT 1")
	String getvendor_id();
	
	 @Transactional
	 @Modifying
	    @Query(value = "CALL add_vendors(:v_id, :date_Time, :v_name, :v_lname, :gst_number, :c1_name, :c1_designation, :c1_mobile, :c1_email, :c2_name, :c2_designation, :c2_mobile, :c2_email, :c3_name, :c3_designation, :c3_mobile, :c3_email, :delivery_address1, :delivery_address2, :delivery_address3, :billingAddress, :honorificsMain, :honorifics1, :honorifics2, :honorifics3)", nativeQuery = true)
	    void addVendor(
	        @Param("v_id") String v_id, 
	        @Param("date_Time") String date_time,
	        @Param("v_name") String v_name, 
	        @Param("v_lname") String v_lname,
	        @Param("gst_number") String gst_number, 
	        @Param("c1_name") String c1_name,
	        @Param("c1_designation") String c1_designation,
	        @Param("c1_mobile") String c1_mobile, 
	        @Param("c1_email") String c1_email,
	        @Param("c2_name") String c2_name,
	        @Param("c2_designation") String c2_designation,
	        @Param("c2_mobile") String c2_mobile, 
	        @Param("c2_email") String c2_email,
	        @Param("c3_name") String c3_name,
	        @Param("c3_designation") String c3_designation,
	        @Param("c3_mobile") String c3_mobile, 
	        @Param("c3_email") String c3_email,
	        @Param("delivery_address1") String delivery_address1, 
	        @Param("delivery_address2") String delivery_address2,
	        @Param("delivery_address3") String delivery_address3,
	        @Param("billingAddress") String billingAddress,
	        @Param("honorificsMain") String honorificsMain,
	        @Param("honorifics1") String honorifics1,
	        @Param("honorifics2") String honorifics2,
	        @Param("honorifics3") String honorifics3);	
	 
	 
	 @Query("SELECT v FROM Add_Vendors_Entity v") // Fetching entire entity
	 List<Add_Vendors_Entity> fetchVendors();
	
	 
	 @Query("SELECT v.vname FROM Add_Vendors_Entity v GROUP BY v.vname ORDER BY MAX(v.vid) DESC")
	 List<String> fetch_cname();
	 
	 @Query("SELECT v.contact_p1_name, v.contact_p1_designation, v.contact_p1_mobile, v.contact_p1_email, v.delivery_address_1, v.delivery_address_2, v.delivery_address_3, v.billing_address, v.honorifics_main, v.honorifics_1, v.honorifics_2, v.honorifics_3 FROM Add_Vendors_Entity v WHERE v.vname = :vendorName")
	 List<Object[]> fetchVendorDetails(@Param("vendorName") String vendorName);

	 
	 @Modifying
	 @Transactional
	 @Query(value = "call update_mspl_vendors(:v_id, :dateTime, :v_name, :v_lname, :gst_number, :contact_p1_name, :contact_p1_designation, :contact_p1_mobile, :contact_p1_email, :contact_p2_name, :contact_p2_designation, :contact_p2_mobile, :contact_p2_email, :contact_p3_name, :contact_p3_designation, :contact_p3_mobile, :contact_p3_email, :delivery_address1, :delivery_address2, :delivery_address3, :billingAddress, :honorificsMain, :honorifics1, :honorifics2, :honorifics3)", nativeQuery = true)
	 void update_vendors(
			    @Param("v_id") String v_id, 
		        @Param("dateTime") String dateTime,
		        @Param("v_name") String v_name, 
		    	@Param("v_lname") String v_lname,
		    	@Param("gst_number") String gst_number,
		        @Param("contact_p1_name") String contact_p1_name,
		    	@Param("contact_p1_designation") String contact_p1_designation,
		    	@Param("contact_p1_mobile") String contact_p1_mobile,
		    	@Param("contact_p1_email") String contact_p1_email,
		    	@Param("contact_p2_name") String contact_p2_name,
			    @Param("contact_p2_designation") String contact_p2_designation,
			    @Param("contact_p2_mobile") String contact_p2_mobile,
			    @Param("contact_p2_email") String contact_p2_email,
			    @Param("contact_p3_name") String contact_p3_name,
		    	@Param("contact_p3_designation") String contact_p3_designation,
		    	@Param("contact_p3_mobile") String contact_p3_mobile,
		    	@Param("contact_p3_email") String contact_p3_email,
		    	@Param("delivery_address1") String delivery_address1, 
		        @Param("delivery_address2") String delivery_address2,
		        @Param("delivery_address3") String delivery_address3,
		        @Param("billingAddress") String billingAddress,
		        @Param("honorificsMain") String honorificsMain,
		        @Param("honorifics1") String honorifics1,
		        @Param("honorifics2") String honorifics2,
		        @Param("honorifics3") String honorifics3
			 ); 
	 
	 
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE FROM vendors WHERE vid = :vid", nativeQuery = true)
	 int deleteVendorByVid(@Param("vid") String vid);
	 
	 @Query("SELECT new map(v.vname as name, v.vlname as legalName, v.gstnumber as gst, v.contact_p1_name as c1, v.contact_p1_designation as c11, v.contact_p1_mobile as c111, v.contact_p1_email as c1111,  v.contact_p2_name as c2, v.contact_p2_designation as c22, v.contact_p2_mobile as c222, v.contact_p2_email as c2222,  v.contact_p3_name as c3, v.contact_p3_designation as c33, v.contact_p3_mobile as c333, v.contact_p3_email as c3333, v.delivery_address_1 as delivery_address_1, v.delivery_address_2 as delivery_address_2, v.delivery_address_3 as delivery_address_3, v.billing_address as billing_address, v.honorifics_main as honorifics_main, v.honorifics_1 as honorifics_1, v.honorifics_2 as honorifics_2, v.honorifics_3 as honorifics_3) FROM Add_Vendors_Entity v")
	 List<Map<String, String>> get_all_customers();

}
