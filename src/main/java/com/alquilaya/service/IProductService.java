package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Product;

public interface IProductService {
	List<ProductProjectionDTO> findAllProjectedProducts();
	Product createProduct(Product product);
	 Product updateProduct(Product product, Integer id);
	 List<ProductProjectionDTO> findProductsByNameAndCity(String name, String city);
	List<ProductProjectionDTO> findProjectedProductsByCategoryId(Integer categoryId);
	List<ProductProjectionDTO> filterProducts(String categoryName, String supplierName, String productCondition);
	Optional<ProductProjectionDTO> findProjectedProductsById(Integer id);	
	List<ProductProjectionDTO> findProductsByUserId(Integer userId);
	
	
	

}
