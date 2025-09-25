package com.example.mspl_connect.DispatchService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.DispatchEntity.Product;
import com.example.mspl_connect.DispatchRepo.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
	    return productRepository.save(product);
	}

	
	public long getTotalProducts() {
		return productRepository.count();
	}
	
	public Product fetchRecentDetails() {
	    return productRepository.fetchRecentlyInsertedProductDetails();
	}
	
	public void deleteProductDataBasedOnProdNameAndModuleNo(String prodName, String modelNo) {
		productRepository.deleteProductData(prodName, modelNo);
	}
	
	public void deleteData(long id) {
		productRepository.deleteById(id);
	}
	
	public boolean isProductExistsOrNot(String product_name) {
		String exists = productRepository.isProductExists(product_name);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isModelNoExistsOrNot(String modelNo) {
		String exists = productRepository.isModelNoExists(modelNo);
		if(exists != null) {
			return true;
		}else {
			return false;
		}
	}
	
}
