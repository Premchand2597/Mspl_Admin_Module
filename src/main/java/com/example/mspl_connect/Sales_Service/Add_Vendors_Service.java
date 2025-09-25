package com.example.mspl_connect.Sales_Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.Add_Product_Entity;
import com.example.mspl_connect.Sales_Entity.Add_Vendors_Entity;
import com.example.mspl_connect.Sales_Repository.IAdd_Vendors;

import jakarta.transaction.Transactional;

@Service
public class Add_Vendors_Service {

	@Autowired
	private IAdd_Vendors iAdd_Vendors;
	
	public Add_Vendors_Service(IAdd_Vendors iAdd_Vendors) {
		this.iAdd_Vendors=iAdd_Vendors;
	}
	
	
	public String get_vid() {
	    String lastQid = iAdd_Vendors.getvendor_id();

	    // Get current date and determine the financial year
	    LocalDate now = LocalDate.now();
	    int currentYear = now.getYear();
	    int nextYear = currentYear + 1;

	    // If before April, use the previous year as the financial year start
	    if (now.getMonthValue() < 4) {
	        currentYear -= 1;
	        nextYear = currentYear + 1;
	    }

	    // Format financial year as "2024-25"
	    String financialYear = String.format("%d-%02d", currentYear, nextYear % 100);

	    if (lastQid != null && lastQid.startsWith("MSPL")) {
	        // Ensure lastQid has the expected format
	        String[] parts = lastQid.split("/");
	        if (parts.length == 4) {
	            try {
	                int lastNumber = Integer.parseInt(parts[3]); // Extract the last numeric part
	                return String.format("MSPL/%s/V/%03d", financialYear, lastNumber + 1);
	            } catch (NumberFormatException e) {
	                System.err.println("Invalid lastQid format: " + lastQid);
	            }
	        }
	    }

	    // First record for the financial year
	    return String.format("MSPL/%s/V/001", financialYear);
	}

	
	
	 @Transactional
	    public Add_Vendors_Entity saveVendor(Add_Vendors_Entity vendor) {
	        // Format LocalDateTime to String for stored procedure
	        String dateTimeFormatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	        // Call stored procedure with null handling
	        iAdd_Vendors.addVendor(
	            vendor.getVid(),
	            dateTimeFormatted,
	            vendor.getVname(),
	            vendor.getVlname(),
	            vendor.getGstnumber(),
	            vendor.getContact_p1_name(),
	            vendor.getContact_p1_designation(),
	            vendor.getContact_p1_mobile(),
	            vendor.getContact_p1_email(),
	            vendor.getContact_p2_name() != null ? vendor.getContact_p2_name() : null,
	            vendor.getContact_p2_designation() != null ? vendor.getContact_p2_designation() : null,
	            vendor.getContact_p2_mobile() != null ? vendor.getContact_p2_mobile() : null,
	            vendor.getContact_p2_email() != null ? vendor.getContact_p2_email() : null,
	            vendor.getContact_p3_name() != null ? vendor.getContact_p3_name() : null,
	         	vendor.getContact_p3_designation() != null ? vendor.getContact_p3_designation() : null,
	            vendor.getContact_p3_mobile() != null ? vendor.getContact_p3_mobile() : null,
	            vendor.getContact_p3_email() != null ? vendor.getContact_p3_email() : null,
	            vendor.getDelivery_address_1() != null ? vendor.getDelivery_address_1() : null,
	            vendor.getDelivery_address_2() != null ? vendor.getDelivery_address_2() : null,
	            vendor.getDelivery_address_3() != null ? vendor.getDelivery_address_3() : null,
	            vendor.getBilling_address() != null ? vendor.getBilling_address() : null,
        		vendor.getHonorifics_main() != null ? vendor.getHonorifics_main() : null,
	            vendor.getHonorifics_1() != null ? vendor.getHonorifics_1() : null,
	            vendor.getHonorifics_2() != null ? vendor.getHonorifics_2() : null,
	            vendor.getHonorifics_3() != null ? vendor.getHonorifics_3() : null
	        );

	        return vendor;  // Returning the saved vendor object
	    }
	
	 
	 
	 public List<Add_Vendors_Entity> getAllVendors() {
	        return iAdd_Vendors.fetchVendors();
	    }
    
	 
		public List<String> get_cname() {
			
			return iAdd_Vendors.fetch_cname();
		}
		
		
		 public Map<String, String> getVendorDetails(String vendorName) {
		        List<Object[]> results = iAdd_Vendors.fetchVendorDetails(vendorName);

		        if (results.isEmpty()) {
		            return null; // Return null if no details found
		        }

		        Object[] vendorData = results.get(0);
		        Map<String, String> response = new HashMap<>();
		        response.put("contactPerson", (String) vendorData[0]);
		        response.put("designation", (String) vendorData[1]);
		        response.put("contactNumber", (String) vendorData[2]);
		        response.put("email", (String) vendorData[3]);
		        response.put("deliveryAddress", (String) vendorData[4]);
		        response.put("shipmentAddress", (String) vendorData[5]);

		        return response;
		    }
		 
		 
		 public void updateVendor(Add_Vendors_Entity vendor) {

				iAdd_Vendors.update_vendors(
						vendor.getVid(),
						vendor.getDateTime(),
						vendor.getVname(),
			            vendor.getVlname(),
			            vendor.getGstnumber(),
			            vendor.getContact_p1_name(),
			            vendor.getContact_p1_designation(),
						vendor.getContact_p1_mobile(),
						vendor.getContact_p1_email(),
						vendor.getContact_p2_name(),
			            vendor.getContact_p2_designation(),
						vendor.getContact_p2_mobile(),
						vendor.getContact_p2_email(),
						vendor.getContact_p3_name(),
			            vendor.getContact_p3_designation(),
						vendor.getContact_p3_mobile(),
						vendor.getContact_p3_email(),
						vendor.getDelivery_address_1(),
			            vendor.getDelivery_address_2(),
			            vendor.getDelivery_address_3(),
			            vendor.getBilling_address(),
			            vendor.getHonorifics_main(),
	    	            vendor.getHonorifics_1(),
	    	            vendor.getHonorifics_2(),
	    	            vendor.getHonorifics_3()
			        );
				
		    }
			
		 
		 @Transactional
		    public void deleteVendor(String vid) {
		        int deletedRows = iAdd_Vendors.deleteVendorByVid(vid);
		        if (deletedRows == 0) {
		            throw new RuntimeException("Customer not found!");
		        }
		    }
		 
		 
		 public List<Map<String, String>> getAllCustomers() {
			    return iAdd_Vendors.get_all_customers();
			}

		 
		
		 
}
