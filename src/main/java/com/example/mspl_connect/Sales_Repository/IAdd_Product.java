package com.example.mspl_connect.Sales_Repository;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.Add_Product_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface IAdd_Product extends JpaRepository<Add_Product_Entity, String> {
	

	 @Query("SELECT p.pid FROM Add_Product_Entity p ORDER BY p.pid DESC LIMIT 1")
	    String findLastPid();
	 
	 
	
	 
//	 @Transactional
//	 @Modifying
//	    @Query(value = "CALL add_mspl_products(:p_id, :p_date, :p_name, :hsn_code, :part_number, :p_description, :remarks)", nativeQuery = true)
//	    void addProduct(
//	        @Param("p_id") String p_id, 
//	        @Param("p_date") String p_date,
//	        @Param("p_name") String p_name, 
//	    	@Param("hsn_code") String hsn_code,
//	    	@Param("part_number") String part_number,
//	        @Param("p_description") String p_description,
//	    	@Param("remarks") String remarks
//);
	 
	 @Transactional
	 @Modifying
	    @Query(value = """
	    		
	    		INSERT INTO products(pid,pdate,pname,hsn_code,part_number,pdescription,remarks) VALUES (:p_id, cast(:p_date as timestamp), :p_name, :hsn_code, :part_number, :p_description, :remarks);
	    		
	    		""", nativeQuery = true)
	    void addProduct(
	        @Param("p_id") String p_id, 
	        @Param("p_date") String p_date,
	        @Param("p_name") String p_name, 
	    	@Param("hsn_code") String hsn_code,
	    	@Param("part_number") String part_number,
	        @Param("p_description") String p_description,
	    	@Param("remarks") String remarks
);
	 

	 @Query("SELECT p FROM Add_Product_Entity p") // Fetching entire entity
	 List<Add_Product_Entity> fetchProducts();
	 
	 
	 @Query("SELECT p.pname FROM Add_Product_Entity p GROUP BY p.pname ORDER BY MAX(p.pid) DESC")
	 List<String> fetch_pname();
	 
//	 @Modifying
//	 @Transactional
//	 @Query(value = "call update_mspl_products(:p_id, :dateTime, :p_name, :hsncode, :partnumber, :p_description, :remarks)", nativeQuery = true)
//	 void update_products(
//			    @Param("p_id") String p_id, 
//		        @Param("dateTime") String dateTime,
//		        @Param("p_name") String p_name, 
//		    	@Param("hsncode") String hsn_code,
//		    	@Param("partnumber") String part_number,
//		        @Param("p_description") String p_description,
//		    	@Param("remarks") String remarks
//			 ); 
	 
	 @Modifying
	 @Transactional
	 @Query(value = """
	 		
	 		update products set pdate=cast(:dateTime as timestamp), pname=:p_name, hsn_code = :hsn_code, part_number=:part_number, pdescription = :p_description, remarks = :remarks where pid = :p_id; 

	 		
	 		""", nativeQuery = true)
	 void update_products(
			    @Param("p_id") String p_id, 
		        @Param("dateTime") String dateTime,
		        @Param("p_name") String p_name, 
		    	@Param("hsn_code") String hsn_code,
		    	@Param("part_number") String part_number,
		        @Param("p_description") String p_description,
		    	@Param("remarks") String remarks
			 );
	 
	 
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE FROM products WHERE pid = :pid", nativeQuery = true)
	 int deleteProductByPid(@Param("pid") String pid);
	 
	 @Query(value = "select p.pname, p.hsn_code, p.part_number, p.pdescription,p.pid from Add_Product_Entity p")
	 List<Object[]> fetch_product();
	 
	
}
