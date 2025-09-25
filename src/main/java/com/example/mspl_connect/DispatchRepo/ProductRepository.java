package com.example.mspl_connect.DispatchRepo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.DispatchEntity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	 Product findByProductName(String productName);
	 
	 @Query(nativeQuery = true, value="select * from product order by id desc limit 1")
	 Product fetchRecentlyInsertedProductDetails();
	
	 @Modifying
	 @Transactional
	 @Query(nativeQuery = true, value = "delete from product where product_name=:prodName and module_no=:modelNo")
	 void deleteProductData(@Param("prodName") String prodName, @Param("modelNo") String modelNo);
	 
	 @Query(nativeQuery = true, value="Select * from product where id=:id")
	 Product getProductsDetailsBasedOnAutoId(@Param("id") long id);
	 
	 void deleteById(Long id);
	 
	 List<Product> findAllByOrderByIdDesc();
	 
	 @Query(nativeQuery = true, value = "select product_name from product where product_name=:product_name")
	 String isProductExists(@Param("product_name") String product_name);
	 
	 @Query(nativeQuery = true, value="select * from product where product_name=:productName and module_no=:modelNo")
	 Product getProductDetailsBasedOnPrdNameAndModelNo(@Param("productName") String productName, @Param("modelNo") String modelNo);
	 
	 @Query(nativeQuery = true, value = "select module_no from product where module_no=:modelNo")
	 String isModelNoExists(@Param("modelNo") String modelNo);
}
	