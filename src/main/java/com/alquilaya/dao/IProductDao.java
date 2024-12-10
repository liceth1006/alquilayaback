package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Product;

public interface IProductDao {
	
	List<ProductProjectionDTO> findProductsByUserId(Integer userId);
	 List<ProductProjectionDTO> findProductsByNameAndCity(String name, String city);
	 Product updateProduct(Product product, Integer id);
	List<ProductProjectionDTO> findAllProjectedProducts();	
	Optional<ProductProjectionDTO> findProjectedProductsById(Integer id);	
	List<ProductProjectionDTO> filterProducts(String categoryName, String supplierName, String productCondition);
	List<ProductProjectionDTO> findProjectedProductsByCategoryId(Integer categoryId);
	Product createProduct(Product product);
	Optional<Product> findProductById(Integer id);
	
	
	
	/*
	String saveProduct(Product product);
	
	List<Product> findAllProducts();
	
	 List<Product> findProductsByCategoryId(Integer categoryId);
	 
	 */

}
